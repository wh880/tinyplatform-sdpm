package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.util.CookieUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.sdpm.project.service.inter.BuildService;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.project.service.inter.TeamService;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
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
@RequestMapping("/project/version")
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
    public String edit(HttpServletRequest request,Integer buildId, Model model) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        SystemModule module = new SystemModule();
        module.setModuleType("project");
        module.setModuleRoot(projectId);
        List<Product> list = productService.findProductList(new Product(), "productId", "desc");
        List<ProjectTeam> teamList = teamService.findTeamByProjectId(projectId);
        model.addAttribute("teamList", teamList);
        model.addAttribute("prodcutList", list);
        if (buildId != null) {
            ProjectBuild build =buildService.findBuild(buildId);
            model.addAttribute("build",build);

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
    @RequestMapping(value="/batchDelete")
    public Map bctchDelDoc(String ids)
    {
        Map<String,String> map = new HashMap<String,String>();
        if(ids == null || ids == ""){
            map.put("status", "fail");
            map.put("info", "请至少选择一条数据");
            return map;
        }
        List<ProjectBuild> list = new ArrayList<ProjectBuild>();
        for(String s : ids.split(",")){
            ProjectBuild build= new ProjectBuild();
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
    public String add(HttpServletRequest request,Integer buildId, Model model) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        SystemModule module = new SystemModule();
        module.setModuleType("project");
        module.setModuleRoot(projectId);
        List<Product> list = productService.findProductList(new Product(), "productId", "desc");
        List<ProjectTeam> teamList = teamService.findTeamByProjectId(projectId);
        model.addAttribute("teamList", teamList);
        model.addAttribute("prodcutList", list);
        if (buildId != null) {
            ProjectBuild build =buildService.findBuild(buildId);
            model.addAttribute("build",build);

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

}
