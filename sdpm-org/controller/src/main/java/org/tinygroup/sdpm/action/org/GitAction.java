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
import org.tinygroup.sdpm.org.service.inter.GitService;

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

    @ResponseBody
    @RequestMapping(value = "/convert", method = RequestMethod.POST)
    public void convertToJava(HttpServletRequest req) {

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
                logger.error(jsonString);
                JsonToObject<Hook> jsonToObject = new JsonToObject<Hook>(Hook.class);
                Hook jsonObject = jsonToObject.convert(jsonString);
                addCommitInfo(jsonObject);
            } catch (IOException e) {
                logger.error("convert error", e);
            }
        }
    }

    private void addCommitInfo(Hook hook) {
        if (hook == null) {
            return;
        }
        PullPushData pullPushData = hook.getPull_push_data();
        if (pullPushData != null) {
            Repository repository = pullPushData.getRepository();
            String repositoryName = "";
            if (repository != null) {
                repositoryName = repository.getName();
                if (repositoryName == null || "".equals(repositoryName)) {
                    return;
                }
            }
            List<OrgGitCommitInfo> list = new ArrayList<OrgGitCommitInfo>();
            List<Commit> commits = pullPushData.getCommits();
            for (Commit c : commits) {
                Author author = c.getAuthor();
                if (author == null) {
                    logger.log(LogLevel.ERROR,"错误信息");
                    continue;
                }
                String authorId = gitService.getUserIdByEmail(author.getEmail());
                if (authorId == null) {
                    continue;
                }
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
        }
    }
}
