package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tinygroup.sdpm.action.product.util.StoryUtil;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.ComplexSearch.SqlUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.impl.FieldUtil;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.product.service.StoryService;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.sdpm.project.service.inter.*;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.quality.service.inter.BugService;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.util.CookieUtils;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangying14938 on 2015-09-22.版本
 */
@Controller
@RequestMapping("/a/project/version")
public class BuildAction extends BaseController {
    @Autowired
    private BuildService buildService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProjectProductService projectProductService;
    @Autowired
    private BugService bugService;
    @Autowired
    private StoryService storyService;
    @Autowired
    private ProjectStoryService projectStoryService;


    @RequestMapping("/find")
    public String find(Model model, Integer start, Integer limit, String order, String ordertype, HttpServletRequest request) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        boolean asc = "asc".equals(ordertype) ? true : false;
        Pager<ProjectBuild> pager = buildService.findPager(projectId, start, limit, order, asc);
        model.addAttribute("buildPager", pager);
        return "project/version/tableData.pagelet";
    }

    @RequestMapping("/look")
    public String look(Integer buildId, Model model) {
        return "project/bug/index.page";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(ProjectBuild build, Model model) {
        if (build.getBuildId() == null) {
            buildService.add(build);
        } else {
            buildService.updateBuild(build);
        }
        model.addAttribute("build", build);
        return "project/version/index.page";
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, Integer buildId, Model model) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        SystemModule module = new SystemModule();
        module.setModuleType("project");
        module.setModuleRoot(projectId);
        List<Product> list = productService.findProductList(new Product(), "productId", "desc");
        List<ProjectTeam> teamList = teamService.findTeamByProjectId(projectId);
        model.addAttribute("teamList", teamList);
        model.addAttribute("prodcutList", list);
        if (buildId != null) {
            ProjectBuild build = buildService.findBuild(buildId);
            model.addAttribute("build", build);

        }
        return "project/version/edit.page";
    }


    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String editSave(ProjectBuild build, Model model) {
        if (build.getBuildId() == null) {
            buildService.add(build);
        } else {
            buildService.updateBuild(build);
        }
        model.addAttribute("build", build);
        return "project/version/index.page";
    }

    @RequestMapping(value = "/addsave", method = RequestMethod.POST)
    public String addSave(ProjectBuild build, Model model) {
        if (build.getBuildId() == null) {
            buildService.add(build);
        } else {
            buildService.updateBuild(build);
        }
        model.addAttribute("build", build);
        return "project/version/index.page";
    }


    @ResponseBody
    @RequestMapping(value = "/delete")
    public Map<String, String> delete(Integer id, Model model) {
        Integer res = buildService.softDeleteBuild(id);
        Map<String, String> map = new HashMap<String, String>();
        if (res > 0) {
            map.put("status", "y");
            map.put("info", "删除成功");
        } else {
            map.put("status", "n");
            map.put("info", "删除失败");
        }

        return map;
    }

    @RequestMapping("/product-al-bug")
    public String jumpalBug() {
        return "/project/task/relation-release/product-al-bug.page";
    }

    @RequestMapping("/product-al-le-bug")
    public String jumpleBug() {
        return "/project/task/relation-release/product-al-le-bug.page";
    }

    @RequestMapping("/product-al-no-bug")
    public String jumpanoBug() {
        return "/project/task/relation-release/product-al-no-bug.page";
    }

    @ResponseBody
    @RequestMapping(value = "/batchDelete")
    public Map bctchDelDoc(String ids) {
        Map<String, String> map = new HashMap<String, String>();
        if (ids == null || ids == "") {
            map.put("status", "fail");
            map.put("info", "请至少选择一条数据");
            return map;
        }
        List<ProjectBuild> list = new ArrayList<ProjectBuild>();
        for (String s : ids.split(",")) {
            ProjectBuild build = new ProjectBuild();
            build.setBuildId(Integer.valueOf(s));
            build.setBuildDeleted("1");
            list.add(build);
        }
        buildService.deleteBuildByIds(list);
        map.put("status", "success");
        map.put("info", "删除成功");
        return map;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, Integer buildId, Model model) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        SystemModule module = new SystemModule();
        module.setModuleType("project");
        module.setModuleRoot(projectId);
        List<Product> list = productService.findProductList(new Product(), "productId", "desc");
        List<ProjectTeam> teamList = teamService.findTeamByProjectId(projectId);
        model.addAttribute("teamList", teamList);
        model.addAttribute("prodcutList", list);
        if (buildId != null) {
            ProjectBuild build = buildService.findBuild(buildId);
            model.addAttribute("build", build);

        }
        return "project/version/add.page";
    }

    @RequestMapping("/productalbug")
    public String productalbug(Integer buildId, Model model) {
        if (buildId != null) {
            ProjectBuild build = buildService.findBuild(buildId);
            model.addAttribute("build", build);
            //还需要查询其他相关任务剩余时间的信息
            return "/project/task/relation-release/product-al-bug.page";
        }
        return "error";
    }

    @RequestMapping("/releasebaseinfo")
    public String releasebaseinfo(Integer buildId, Model model) {
        if (buildId != null) {
            ProjectBuild build = buildService.findBuild(buildId);
            model.addAttribute("build", build);
            //还需要查询其他相关任务剩余时间的信息
            return "/project/task/relation-release/releasebaseinfo.pagelet";
        }
        return "error";
    }

    @RequestMapping("/forword/{forwordPager}")
    public String forword(@PathVariable(value = "forwordPager") String forwordPager, Integer buildId, Model model) {
        ProjectBuild build = buildService.findBuild(buildId);
        model.addAttribute("build", build);
        if ("alBug".equals(forwordPager)) {
            return "project/task/relation-release/product-al-bug.page";
        } else if ("alnoBug".equals(forwordPager)) {
            return "project/task/relation-release/product-al-no-bug.page";
        } else if ("alleBug".equals(forwordPager)) {
            return "project/task/relation-release/product-al-le-bug.page";
        } else if ("alnoReq".equals(forwordPager)) {
            return "project/task/relation-release/product-al-no-req.page";
        } else if ("alReq".equals(forwordPager)) {
            return "project/task/relation-release/product-al-req.page";
        }
        return "";
    }


    @RequestMapping("/bugSearch/{relate}")
    public String bugListAction(@PathVariable(value="relate")String relate, int page, int pagesize, QualityBug bug, SearchInfos searchInfos,
                                @RequestParam(required = false, defaultValue = "bugId") String order,
                                @RequestParam(required = false, defaultValue = "asc") String ordertype,
                                Model model, HttpServletRequest request){
        //bug.setProductId((Integer)(request.getSession().getAttribute("sessionProductId")));
        if ("reRelateBug".equals(relate)) {
            bug.setDeleted(0);
            bug.setBugStatus("3");
            Pager<QualityBug> p = bugService.findBugListPager(pagesize*(page - 1), pagesize,searchInfos != null ? SqlUtil.toSql(searchInfos.getInfos(), "") : "", bug, null, "asc".equals(ordertype)?true:false);
            model.addAttribute("bugList",p);
            return "/project/task/relation-release/product-al-bug-data.pagelet";
        }else if ("noRelateBug".equals(relate)) {
            bug.setProjectId(null);
            Pager<QualityBug> p = bugService.findBugListPager(pagesize*(page - 1), pagesize,searchInfos != null ? SqlUtil.toSql(searchInfos.getInfos(), "") : "", bug, null, "asc".equals(ordertype)?true:false);
            model.addAttribute("bugList",p);
            return "/project/task/relation-release/product-al-no-bug-data.pagelet";
        }else if ("reRelateBugRelease".equals(relate)) {
            bug.setDeleted(0);
            Pager<QualityBug> p = bugService.findBugListPager(pagesize*(page - 1), pagesize,searchInfos != null ? SqlUtil.toSql(searchInfos.getInfos(), "") : "", bug, null, "asc".equals(ordertype)?true:false);
            model.addAttribute("bugList",p);
            return "/project/task/relation-release/product-al-bug-data.pagelet";
        }else if ("noRelateBugRelease".equals(relate)) {
            Pager<QualityBug> p = bugService.findBugListPager(pagesize*(page - 1), pagesize,searchInfos != null ? SqlUtil.toSql(searchInfos.getInfos(), "") : "", bug, null, "asc".equals(ordertype)?true:false);
            model.addAttribute("bugList",p);
            return "/project/task/relation-release/product-al-no-bug-data.pagelet";
        }else if ("leRelateBugRelease".equals(relate)) {
            Pager<QualityBug> p = bugService.findBugListPager(pagesize*(page - 1), pagesize,searchInfos != null ? SqlUtil.toSql(searchInfos.getInfos(), "") : "", bug, null, "asc".equals(ordertype)?true:false);
            model.addAttribute("bugList",p);
            return "/project/task/relation-release/product-al-le-bug-data.pagelet";
        }
        return "";
    }


//    @RequestMapping("/search/{relate}")
//    public String storyListAction(@PathVariable(value="relate")String relate, int page, int pagesize,
//                                  ProductStory story, String choose, String groupOperate, SearchInfos searchInfos,
//                                  @RequestParam(required = false, defaultValue = "storyId") String order,
//                                  @RequestParam(required = false, defaultValue = "asc") String ordertype,
//                                  Model model, HttpServletRequest request){
//
//
//        story.setStoryId((Integer)(request.getSession().getAttribute("storyId")));
//        Pager<ProductStory> p =projectStoryService.findStoryPager(pagesize*(page - 1),pagesize,story, StoryUtil.getStatusCondition(choose,request),searchInfos,groupOperate,FieldUtil.stringFormat(order),"asc".equals(ordertype)?true:false);
//        model.addAttribute("story",p);
//
//        if("reRelateStory".equals(relate)){
//            return "/project/task/relation-release/product-al-req-data.pagelet";
//        }else if ("noRelateStory".equals(relate)) {
//            return "/project/task/relation-release/product-al-no-req-data.pagelet";
//        }else if ("reRelateStoryRelease".equals(relate)) {
//            return "/project/task/relation-release/product-al-req-data.pagelet";
//        }else if ("noRelateStoryRelease".equals(relate)) {
//            return "/project/task/relation-release/product-al-no-req-data.pagelet";
//        }
//        return "";
//    }

    @RequestMapping("/search/{relate}")
    public String findStory(@PathVariable(value="relate")String relate,Model model, Integer start, Integer limit, String order, String ordertype, HttpServletRequest request) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        Pager<ProductStory> storyList = projectStoryService.findStoryToLink(projectId, start, limit, order, ordertype);
        model.addAttribute("story", storyList);
        if("reRelateStory".equals(relate)){
            return "/project/task/relation-release/product-al-req-data.pagelet";
        }else if ("noRelateStory".equals(relate)) {
            return "/project/task/relation-release/product-al-no-req-data.pagelet";
        }else if ("reRelateStoryRelease".equals(relate)) {
            return "/project/task/relation-release/product-al-req-data.pagelet";
        }else if ("noRelateStoryRelease".equals(relate)) {
            return "/project/task/relation-release/product-al-no-req-data.pagelet";
        }
        return "";
    }


}
