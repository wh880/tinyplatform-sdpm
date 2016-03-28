package org.tinygroup.sdpm.action.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.convert.objectjson.fastjson.JsonToObject;
import org.tinygroup.sdpm.org.dao.fields.Commit;
import org.tinygroup.sdpm.org.dao.fields.Hook;
import org.tinygroup.sdpm.org.dao.pojo.OrgGitCommitInfo;
import org.tinygroup.sdpm.org.service.inter.GitService;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/3/23.
 */
@Controller
@RequestMapping(value="org/git")
public class GitAction {

    @Autowired
    private GitService gitService;

    @RequestMapping(value="/convert" ,method = RequestMethod.POST)
    public void convertToJava( HttpServletRequest req){

        int size = req.getContentLength();
        if(size>0){
            try {
                BufferedReader reader = req.getReader();
                StringBuffer jsonStringBuffer = new StringBuffer();
                String temp;
                while ((temp = reader.readLine()) != null) {
                    jsonStringBuffer.append(temp);
                }
                String jsonString = java.net.URLDecoder.decode(jsonStringBuffer.toString(),"utf-8");
                jsonString = jsonString.replaceFirst("hook=","");
                reader.close();
                JsonToObject<Hook> jsonToObject = new JsonToObject<Hook>(Hook.class);
                Hook jsonObject = jsonToObject.convert(jsonString);
                this.addCommitInfo(jsonObject);
                System.out.println("done");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("body is empty");
        }
    }

    public void addCommitInfo(Hook hook){
        String repositoryName = hook.getPush_data().getRepository().getName();
        String authorName;
        List<OrgGitCommitInfo> list = new ArrayList<OrgGitCommitInfo>();
        List<Commit> commits = hook.getPush_data().getCommits();
        for(Commit c:commits) {
            Date date = getDate(c.getTimestamp());
            authorName = gitService.getNameByEmail(c.getAuthor().getEmail());
            OrgGitCommitInfo orgGitCommitInfo = new OrgGitCommitInfo();
            orgGitCommitInfo.setGitAuthorName(authorName);
            orgGitCommitInfo.setGitCommitMessage(c.getMessage());
            orgGitCommitInfo.setGitCommitUrl(c.getUrl());
            orgGitCommitInfo.setGitType("push");
            orgGitCommitInfo.setGitRepositoryName(repositoryName);
            orgGitCommitInfo.setGitCommitTime(date);
            list.add(orgGitCommitInfo);
        }
        gitService.batchInsertGitCommitInfo(list);
    }

    public Date getDate(String dateString){
        String time = dateString;
        int plusLocation = time.indexOf("+");
        int lastColonLocation = time.lastIndexOf(":");
        String time1 = time.substring(0,plusLocation).replace("T"," ");
        String plusTime = time.substring(plusLocation+1,lastColonLocation);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        try {
            date1 = sdf.parse(time1);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);
            calendar.add(Calendar.HOUR,Integer.parseInt(plusTime) );
            date1 = calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }


}
