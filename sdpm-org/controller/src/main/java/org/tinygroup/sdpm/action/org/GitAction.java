package org.tinygroup.sdpm.action.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.convert.objectjson.fastjson.JsonToObject;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.fields.*;
import org.tinygroup.sdpm.org.dao.pojo.OrgGitCommitInfo;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.GitService;
import org.tinygroup.sdpm.org.service.inter.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
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
    @RequestMapping(value = "/convert", method = RequestMethod.POST)
    public void convertToJava(HttpServletRequest req) {
        logger.logMessage(LogLevel.INFO, "开始解析报文");
        int size = req.getContentLength();
        if (size > 0) {
            try {
                BufferedReader reader = req.getReader();
                StringBuffer jsonStringBuffer = new StringBuffer();
                String temp;
                while ((temp = reader.readLine()) != null) {
                    jsonStringBuffer.append(temp);
                }
                String jsonString = URLDecoder.decode(jsonStringBuffer.toString(), "utf-8");
                jsonString = jsonString.replaceFirst("hook=", "");
                reader.close();
                logger.logMessage(LogLevel.INFO, "报文内容：{}", jsonString);
                JsonToObject<Hook> jsonToObject = new JsonToObject<Hook>(Hook.class);
                Hook jsonObject = jsonToObject.convert(jsonString);
                addCommitInfo(jsonObject);
            } catch (IOException e) {
                logger.logMessage(LogLevel.ERROR, "convert error", e);
            }
        }
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
            List<OrgGitCommitInfo> list = new ArrayList<OrgGitCommitInfo>();
            List<Commit> commits = pullPushData.getCommits();
            for (Commit c : commits) {
                Author author = c.getAuthor();
                if (author == null) {
                    logger.logMessage(LogLevel.ERROR, "用户消息为空");
                    continue;
                }
                OrgUser orgUser = new OrgUser();
                String gitEmail = author.getEmail();
                if (gitEmail == null) {
                    logger.logMessage(LogLevel.ERROR, "git email为空");
                    return;
                }
                orgUser.setOrgUserSubmitter(gitEmail);
                List<OrgUser> orgUserList = userService.findUserList(orgUser);
                if (orgUserList.size() == 0) {
                    logger.logMessage(LogLevel.ERROR, "未查询到git email为：" + gitEmail + "的用户");
                    return;
                }
                String authorId = orgUserList.get(0).getOrgUserId();
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
