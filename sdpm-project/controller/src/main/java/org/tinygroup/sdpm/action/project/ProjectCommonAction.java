package org.tinygroup.sdpm.action.project;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.util.ProjectOperate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by shenly13343 on 2015-09-28.
 */
@Controller
@RequestMapping("/a/project/manage")
public class ProjectCommonAction extends BaseController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectProductService projectProductService;

    @RequestMapping("/bug/index")
    public String jumpBugIndex(@CookieValue(value= ProjectOperate.COOKIE_PROJECT_ID)String currentProjectId,Model model) {
        List<ProjectProduct> projectProductList=projectProductService.findProductListByProjectId(Integer.parseInt(currentProjectId));
        if(projectProductList.size()==0)
        {
            model.addAttribute("linkInfo",1);
        }else
        {
            model.addAttribute("linkInfo",0);
        }
        return "project/index/bug/index";
    }

    @RequiresPermissions("test")
    @RequestMapping("/test/index")
    public String jumpTestIndex(@CookieValue(value= ProjectOperate.COOKIE_PROJECT_ID)String currentProjectId,Model model) {
        List<ProjectProduct> projectProductList=projectProductService.findProductListByProjectId(Integer.parseInt(currentProjectId));
        if(projectProductList.size()==0)
        {
            model.addAttribute("linkInfo",1);
        }else
        {
            model.addAttribute("linkInfo",0);
        }
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
