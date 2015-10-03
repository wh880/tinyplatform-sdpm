package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.util.CookieUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.project.service.inter.BuildService;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangying14938 on 2015-09-22.版本
 */
@Controller
@RequestMapping("/project/version")
public class BuildAction extends BaseController {
    @Autowired
    private BuildService buildService;

    @RequestMapping("/find")
    public String find(Model model, Integer start, Integer limit, String order, String ordertype, HttpServletRequest request) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        boolean asc = ordertype == "asc" ? true : false;
        Pager<ProjectBuild> pager = buildService.findPager(projectId, start, limit, order, asc);
        model.addAttribute("buildPager", pager);
        return "project/version/tableData.pagelet";
    }
}
