package org.tinygroup.sdpm.action.project;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.commons.tools.ArrayUtil;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.service.inter.StoryService;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.dao.pojo.ProjectStory;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;
import org.tinygroup.sdpm.project.service.inter.ProjectStoryService;
import org.tinygroup.sdpm.util.ProjectOperate;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 需求
 * Created by wangying14938 on 2015-09-22.
 */
@Controller
@RequestMapping("/a/project/demand")
public class ProjectStoryAction extends BaseController {
    @Autowired
    private ProjectStoryService projectStoryService;
    @Autowired
    private StoryService storyService;
    @Autowired
    private ProjectProductService projectProductService;

    @RequiresPermissions("demand")
    @RequestMapping("/index")
    public String index(String moduleId, Model model, @CookieValue(value= ProjectOperate.COOKIE_PROJECT_ID)String currentProjectId) {
        if (moduleId != null) {
            model.addAttribute("moduleId", moduleId);
        }

        List<ProjectProduct> projectProductList=projectProductService.findProductListByProjectId(Integer.parseInt(currentProjectId));
        if(projectProductList.size()==0)
        {
            model.addAttribute("linkInfo",1);
        }else
        {
            model.addAttribute("linkInfo",0);
        }
        return "project/index/demand/index";
    }

    @RequestMapping("/list/data")
    public String listData(HttpServletRequest request, HttpServletResponse response,
                           Model model, Integer start, Integer limit,
                           @RequestParam(required = false, defaultValue = "storyId") String order,
                           @RequestParam(required = false, defaultValue = "desc") String orderType, String moduleId) {

        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return null;
        }
        Pager<ProductStory> storyPager = projectStoryService.findStoryPagerByProject(projectId, start, limit, order, orderType, moduleId);
        model.addAttribute("storyPager", storyPager);
        return "project/data/demand/demandTableData.pagelet";
    }

    @RequiresPermissions("rm-demand")
    @ResponseBody
    @RequestMapping("/delete")
    public Map<String, String> delete(Integer id, HttpServletRequest request, HttpServletResponse response) {
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return resultMap(false, "删除失败");
        }        //根据id进行软删
        ProductStory story = storyService.findStory(id);
        if (story.getPlanId() != null) {
            story.setStoryStage(ProductStory.STAGE_IS_PLANED);
        } else {
            story.setStoryStage(ProductStory.STAGE_NOT_BEGIN);
        }
        storyService.updateStory(story);
        Integer result = projectStoryService.deleteProjectStory(projectId, id);
        if (result > 0) {
            return resultMap(true, "删除成功");
        } else {
            return resultMap(false, "删除失败");
        }
    }

    @RequiresPermissions("pro-demand-relation")
    @RequestMapping("/preLinkStory")
    public String preLinkStory(boolean notInProjectStory, Model model) {
        model.addAttribute("notInProjectStory", notInProjectStory);
        return "project/operate/demand/relation/relateDemand.page";
    }

    @RequestMapping("/findStory")
    public String findStory(Model model, Integer start, Integer limit, String order, String ordertype, boolean notInProjectStory,
                            HttpServletRequest request, HttpServletResponse response) {
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return redirectProjectForm();
        }
        if (StringUtil.isBlank(order)) {
            order = "storyId";
        }
        if (StringUtil.isBlank(ordertype)) {
            ordertype = "desc";
        }
        Pager<ProductStory> storyList = projectStoryService.findStoryToLink(projectId, start, limit, order, ordertype, notInProjectStory);
        model.addAttribute("storyList", storyList);
        return "project/data/demand/relation/relateDemandTableData.pagelet";
    }


    @ResponseBody
    @RequestMapping("/linkStory")
    public Map<String, String> linkStory(String ids, HttpServletResponse response, HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        String[] id = ids.split(",");
        List<ProjectStory> projectStoryList = new ArrayList<ProjectStory>();
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return resultMap(false, "请选择项目");
        }
        for (int i = 0; i < id.length; i++) {
            ProjectStory projectStory = new ProjectStory();
            projectStory.setProjectId(projectId);
            projectStory.setStoryId(Integer.parseInt(id[i]));
            ProductStory productStory = storyService.findStory(Integer.parseInt(id[i]));
            productStory.setStoryStage(ProductStory.STAGE_IS_PROJECTED);
            storyService.updateStory(productStory);
            projectStory.setProductId(productStory.getProductId());
            projectStory.setStoryVersion(productStory.getStoryVersion());
            projectStoryList.add(projectStory);
        }
        List<ProjectStory> updateList = new ArrayList<ProjectStory>();
        List<ProjectStory> insertList = new ArrayList<ProjectStory>();
        for (ProjectStory t : projectStoryList) {
            ProjectStory temp = new ProjectStory();
            temp.setProjectId(t.getProjectId());
            temp.setStoryId(t.getStoryId());
            List<ProjectStory> result = projectStoryService.findByProjectStory(temp);
            if (result.isEmpty()) {
                insertList.add(t);
            } else {
                t.setId(result.get(0).getId());
                updateList.add(t);
            }
        }
        int[] insertResult = projectStoryService.addProjectStoryLink(insertList);
        int[] updateResult = projectStoryService.updateProjectStoryLink(updateList);
        if ((insertResult.length + updateResult.length) > 0) {
            map.put("status", "y");
            map.put("info", "关联成功");
            map.put("url", adminPath + "/project/demand/index");
        } else {
            map.put("status", "n");
            map.put("info", "关联失败");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/batchDel")
    public Map<String, String> batchDel(Integer itemId[], HttpServletRequest request, HttpServletResponse response) {
        if (!ArrayUtil.isEmptyArray(itemId)) {
            Integer projectId = projectOperate.getCurrentProjectId(request, response);
            if (projectId == null) {
                return resultMap(false, "未选择项目");
            }
            for (Integer id : itemId) {
                ProductStory story = storyService.findStory(id);
                if (story.getPlanId() != null) {
                    story.setStoryStage(ProductStory.STAGE_IS_PLANED);
                } else {
                    story.setStoryStage(ProductStory.STAGE_NOT_BEGIN);
                }
                storyService.updateStory(story);
            }
            Integer count = projectStoryService.batchDelStory(projectId, itemId);
            if (count > 0) {
                return resultMap(true, "删除成功");
            } else {
                return resultMap(false, "删除失败");
            }
        } else {
            return resultMap(false, "未选择");
        }
    }
}