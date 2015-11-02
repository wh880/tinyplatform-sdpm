package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.ComplexSearch.SqlUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.sdpm.project.service.inter.BuildService;
import org.tinygroup.sdpm.project.service.inter.ProjectStoryService;
import org.tinygroup.sdpm.project.service.inter.TeamService;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.util.CookieUtils;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.UserUtils;
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
    private TeamService teamService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProjectStoryService projectStoryService;


    @RequestMapping("/productBuildList")
    public String productBuildList(ProjectBuild build, Model model, Integer start, Integer limit, String order, String ordertype, HttpServletRequest request) {

        boolean asc = "asc".equals(ordertype) ? true : false;
        Pager<ProjectBuild> pager = buildService.findPagerBuild(build, start, limit, order, asc);
        model.addAttribute("buildPager", pager);
        return "project/version/tableData.pagelet";
    }


    @RequestMapping("/find")
    public String find(Model model, Integer start, Integer limit, String order, String ordertype, HttpServletRequest request) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, TaskAction.COOKIE_PROJECT_ID));
        boolean asc = "asc".equals(ordertype) ? true : false;
        Pager<ProjectBuild> pager = buildService.findPager(projectId, start, limit, order, asc);
        model.addAttribute("buildPager", pager);
        return "project/version/tableData.pagelet";
    }

    @RequestMapping("/look")
    public String look() {
        return "project/bug/index.page";
    }


    @RequestMapping("/edit")
    public String edit(HttpServletRequest request,Integer projectId, Integer buildId, Model model) {
        if(projectId==null&&projectId<1){
            projectId = Integer.parseInt(CookieUtils.getCookie(request, TaskAction.COOKIE_PROJECT_ID));
        }
        SystemModule module = new SystemModule();
        module.setModuleType("project");
        module.setModuleRoot(projectId);
        List<Product> list = productService.findProductList(new Product(), "productId", "desc");
        List<ProjectTeam> teamList = teamService.findTeamByProjectId(projectId);
        model.addAttribute("teamList", teamList);
        model.addAttribute("prodcutList", list);
        if (buildId != null && buildId != 0) {
            ProjectBuild build = buildService.findBuild(buildId);
            model.addAttribute("build", build);

        } else {
            model.addAttribute("build", null);
        }


        return "project/version/edit.page";
    }


    @RequestMapping(value = "/addsave", method = RequestMethod.POST)
    public String addSave(ProjectBuild build, Model model, String commnet) {
        ProjectBuild temp;
        if (build.getBuildId() == null) {
            build.setBuildBuilder(UserUtils.getUserId());
            temp = buildService.add(build);
            LogUtil.logWithComment(LogUtil.LogOperateObject.BUILD,
                    LogUtil.LogAction.OPENED,
                    String.valueOf(temp.getBuildId()),
                    UserUtils.getUserId(),
                    String.valueOf(temp.getBuildProduct()),
                    String.valueOf(temp.getBuildProject()),
                    null,
                    null,
                    null);
        } else {
            buildService.updateBuild(build);
            LogUtil.logWithComment(LogUtil.LogOperateObject.BUILD,
                    LogUtil.LogAction.EDITED,
                    String.valueOf(build.getBuildId()),
                    UserUtils.getUserId(),
                    String.valueOf(build.getBuildProduct()),
                    String.valueOf(build.getBuildProject()),
                    null,
                    null,
                    null);
        }
        model.addAttribute("build", build);
        return "project/version/index.page";
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public Map<String, String> delete(Integer id, Model model) {
        buildService.softDeleteBuild(id);
        ProjectBuild temp = buildService.findBuild(id);
        LogUtil.logWithComment(LogUtil.LogOperateObject.BUILD,
                LogUtil.LogAction.OPENED,
                String.valueOf(temp.getBuildId()),
                UserUtils.getUserId(),
                String.valueOf(temp.getBuildProduct()),
                String.valueOf(temp.getBuildProject()),
                null,
                null,
                null);
        return resultMap(true, "删除成功");
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
        for (int i = 0, n = list.size(); i < n; i++) {
            LogUtil.logWithComment(LogUtil.LogOperateObject.BUILD,
                    LogUtil.LogAction.DELETED,
                    String.valueOf(list.get(i).getBuildId()),
                    UserUtils.getUserId(),
                    String.valueOf(list.get(i).getBuildProduct()),
                    String.valueOf(list.get(i).getBuildProject()),
                    null,
                    null,
                    null);
        }

        buildService.deleteBuildByIds(list);
        map.put("status", "success");
        map.put("info", "删除成功");
        return map;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, Integer buildId, Model model, String commnet) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, TaskAction.COOKIE_PROJECT_ID));
        SystemModule module = new SystemModule();
        module.setModuleType("project");
        module.setModuleRoot(projectId);
        List<Product> list = productService.findProductList(new Product(), "productId", "desc");
        List<ProjectTeam> teamList = teamService.findTeamByProjectId(projectId);
        model.addAttribute("teamList", teamList);
        model.addAttribute("prodcutList", list);
        if (buildId != null && buildId != 0) {
            ProjectBuild build = buildService.findBuild(buildId);
            model.addAttribute("build", build);

        } else {
            model.addAttribute("build", null);
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
    public String bugListAction(@PathVariable(value = "relate") String relate, int page, int pagesize, QualityBug bug, int id, String groupOperate, SearchInfos searchInfos,
                                Model model) {

        if ("reRelateBug".equals(relate)) {
            bug.setDeleted(0);
            bug.setBugStatus("2");
            Pager<QualityBug> p = buildService.findBugPager(pagesize * (page - 1), pagesize, id, searchInfos, groupOperate);
            model.addAttribute("bugList", p);
            return "/project/task/relation-release/product-al-bug-data.pagelet";
        } else if ("noRelateBug".equals(relate)) {
            String condition = "";
            if (searchInfos != null) {
                if (searchInfos.getInfos().size() > 0 || searchInfos.getInfos() != null) {
                    String sql = SqlUtil.toSql(searchInfos.getInfos(), groupOperate);
                    if (!StringUtil.isBlank(sql)) {
                        condition += sql;
                    }
                }
            }
            bug.setProjectId(null);
            Pager<QualityBug> p = buildService.findnoBugPager(pagesize * (page - 1), pagesize, id, condition, searchInfos, groupOperate);
            model.addAttribute("bugList", p);
            return "/project/task/relation-release/product-al-no-bug-data.pagelet";
        } else if ("leRelateBugRelease".equals(relate)) {
            Pager<QualityBug> p = buildService.findBugLegacyPager(pagesize * (page - 1), pagesize, id, searchInfos, groupOperate);
            model.addAttribute("bugList", p);
            return "/project/task/relation-release/product-al-le-bug-data.pagelet";
        }

        return "";
    }

    /**
     * 关联需求
     *
     * @param ids
     * @param buildId
     * @return
     */
    @ResponseBody
    @RequestMapping("/releateReq")
    public Map<String, String> releateReq(String ids, Integer buildId) {
        Map<String, String> map = new HashMap<String, String>();
        for (String storyId : ids.split(",")) {
            buildService.releateReq(Integer.valueOf(storyId), buildId);
        }
        return resultMap(true, "关联成功");
    }

    @ResponseBody
    @RequestMapping("/releateBug")
    public Map<String, String> releateBug(String ids, Integer buildId) {
        Map<String, String> map = new HashMap<String, String>();
        for (String bugId : ids.split(",")) {
            buildService.releateBug(Integer.valueOf(bugId), buildId);
        }
        return resultMap(true, "关联成功");
    }

    @RequestMapping("/search/reRelateStory")
    public String storyListAction(int page, int pagesize, int id, String groupOperate, SearchInfos searchInfos,
                                  Model model) {
        Pager<ProductStory> p = projectStoryService.findStoryPager(pagesize * (page - 1), pagesize, id, searchInfos, groupOperate);
        model.addAttribute("storys", p);

        return "/project/task/relation-release/product-al-req-data.pagelet";
    }

    @RequestMapping("/search/noRelateStory")
    public String storynoListAction(int page, int pagesize, int id, String groupOperate, SearchInfos searchInfos,
                                    Model model) {
        String condition = "";
        if (searchInfos != null) {
            if (searchInfos.getInfos().size() > 0 || searchInfos.getInfos() != null) {
                String sql = SqlUtil.toSql(searchInfos.getInfos(), groupOperate);
                if (!StringUtil.isBlank(sql)) {
                    condition += sql;
                }
            }
        }
        Pager<ProductStory> p = projectStoryService.findNoStoryPager(pagesize * (page - 1), pagesize, id, condition, searchInfos, groupOperate);
        model.addAttribute("storys", p);

        return "/project/task/relation-release/product-al-no-req-data.pagelet";
    }

    @ResponseBody
    @RequestMapping("/deletereleate")
    public Map deletereleate(Integer storyId, Integer buildId) {
        buildService.deletereleate(storyId, buildId);
        Map<String, String> map = new HashMap<String, String>();
        return resultMap(true, "解除关联成功");

    }

    @ResponseBody
    @RequestMapping("/deletereleateBug")
    public Map deletereleateBug(Integer bugId, Integer buildId) {
        buildService.deletereleateBug(bugId, buildId);
        Map<String, String> map = new HashMap<String, String>();
        return resultMap(true, "解除关联成功");
    }

    @ResponseBody
    @RequestMapping(value = "/batchDeleteReq")
    public Map bctchDelReq(String ids, Integer buildId) {
        Map<String, String> map = new HashMap<String, String>();
        if (ids == null || ids == "") {
            map.put("status", "fail");
            map.put("info", "请至少选择一条数据");
            return map;
        }

        for (String s : ids.split(",")) {
            Integer S = Integer.valueOf(s);
            buildService.deletereleate(S, buildId);
        }
        map.put("status", "success");
        map.put("info", "删除成功");
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/batchDeleteBug")
    public Map bctchDelBug(String ids, Integer buildId) {
        Map<String, String> map = new HashMap<String, String>();
        if (ids == null || ids == "") {
            map.put("status", "fail");
            map.put("info", "请至少选择一条数据");
            return map;
        }

        for (String bug : ids.split(",")) {
            Integer Bug = Integer.valueOf(bug);
            buildService.deletereleateBug(Bug, buildId);
        }
        map.put("status", "success");
        map.put("info", "删除成功");
        return map;
    }


    @ResponseBody
    @RequestMapping("/buildList")
    public List<ProjectBuild> findProjectBuild(ProjectBuild build) {

        List<ProjectBuild> list = buildService.findListBuild(build);

        return list;
    }


}
