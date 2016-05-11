package org.tinygroup.sdpm.action.project;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.project.service.inter.ProjectService;

/**
 * Created by shenly13343 on 2015-09-28.
 */
@Controller
@RequestMapping("/a/project/manage")
public class ProjectCommonAction extends BaseController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/bug/index")
    public String jumpBugIndex() {
        return "project/index/bug/index";
    }

    @RequiresPermissions("test")
    @RequestMapping("/test/index")
    public String jumpTestIndex() {
        return "project/index/test/index.page";
    }


    @RequestMapping("/selModelTask")
    public String selModelTask(String moduleId) {
        return "redirect:" + adminPath + "/project/task/index?moduleId=" + moduleId;
    }

    @RequestMapping("/selModelDemand")
    public String selModelDemand(String moduleId) {
        return "redirect:" + adminPath + "/project/demand/index?moduleId=" + moduleId;
    }

}
