package org.tinygroup.sdpm.action.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.convert.objectjson.fastjson.JsonToObject;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.fields.*;
import org.tinygroup.sdpm.org.dao.pojo.OrgGitCommitInfo;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.GitService;
import org.tinygroup.sdpm.org.service.inter.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gongyl on 2016/3/23.
 */
@Controller
@RequestMapping(value = "org/git")
public class GitAction extends BaseController {

    @Autowired
    private GitService gitService;
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/pull")
    public void pull(String hook) {
        logger.logMessage(LogLevel.INFO, "pull开始解析报文{}",hook);
        JsonToObject<Hook> jsonToObject = new JsonToObject<Hook>(Hook.class);
        Hook jsonObject = jsonToObject.convert(hook);
        addCommitInfo(jsonObject);
        logger.logMessage(LogLevel.INFO, "结束报文解析");
    }

    private void addCommitInfo(Hook hook) {
        if (hook == null) {
            logger.logMessage(LogLevel.ERROR, "消息内容为空");
            return;
        }
        PullPushData pullPushData = hook.getPull_push_data();
        if (pullPushData != null) {
            Repository repository = pullPushData.getRepository();
            String repositoryName = "";
            if (repository != null) {
                repositoryName = repository.getName();
                if (repositoryName == null || "".equals(repositoryName)) {
                    logger.logMessage(LogLevel.ERROR, "仓库名为空");
                    return;
                }
            } else {
                logger.logMessage(LogLevel.ERROR, "仓库名为空");
            }
            User user = pullPushData.getUser();
            String gitEmail = user.getEmail();
            OrgUser orgUser = new OrgUser();
            orgUser.setOrgUserSubmitter(gitEmail);
            List<OrgUser> orgUserList = userService.findUserList(orgUser);
            if (orgUserList.size() == 0) {
                logger.logMessage(LogLevel.ERROR, "未查询到git email为：" + gitEmail + "的用户");
                return;
            }
            String authorId = orgUserList.get(0).getOrgUserId();
            List<OrgGitCommitInfo> list = new ArrayList<OrgGitCommitInfo>();
            List<Commit> commits = pullPushData.getCommits();
            for (Commit c : commits) {
                OrgGitCommitInfo orgGitCommitInfo = new OrgGitCommitInfo();
                orgGitCommitInfo.setOrgGitCommitId(c.getId());
                orgGitCommitInfo.setOrgGitAuthorId(authorId);
                orgGitCommitInfo.setOrgGitCommitMessage(c.getMessage());
                orgGitCommitInfo.setOrgGitCommitUrl(c.getUrl());
                orgGitCommitInfo.setOrgGitType("pull");
                orgGitCommitInfo.setOrgGitRepositoryName(repositoryName);
                orgGitCommitInfo.setOrgGitCommitTime(c.getTimestamp());
                list.add(orgGitCommitInfo);
            }
            if (list.size() > 0) {
                gitService.batchInsertGitCommitInfo(list);
            }
        } else {
            logger.logMessage(LogLevel.ERROR, "pushDate内容为空");
        }
    }
}
