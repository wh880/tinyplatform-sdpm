package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.util.CookieUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.project.service.inter.ProjectStoryService;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangying14938 on 2015-09-22.需求
 */
@Controller
@RequestMapping("/project/demand")
public class ProjectstoryAction extends BaseController {
    @Autowired
    private ProjectStoryService projectStoryService;

    @RequestMapping("find")
    public String find(Model model, HttpServletRequest request, Integer start, Integer limit, String order, String ordertype) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        Pager<ProductStory> story = projectStoryService.findStoryByProject(projectId, start, limit, order, ordertype);

        model.addAttribute("storys", story);
        //story.getTotalCount()
        return "project/demand/demandTableData.pagelet";

    }
}
