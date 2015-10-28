package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.util.DateUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBurn;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.sdpm.project.service.inter.BurnService;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.project.service.inter.TeamService;
import org.tinygroup.sdpm.util.CmsUtils;
import org.tinygroup.sdpm.util.CookieUtils;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.UserUtils;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by shenly13343 on 2015-09-21.
 */
@Controller
@RequestMapping("/a/project")
public class ProjectAction extends BaseController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProjectProductService projectProductService;
    @Autowired
    private BurnService burnService;
    @Autowired
    private UserService userService;

    @RequestMapping("/find")
    public String find(Integer projectId) {
        if (projectId == null) {
            projectService.findById(projectId);
        }
        return "project/addProject.page";
    }

    @ResponseBody
    @RequestMapping("/batchDelete")
    public Map batchDelete(@RequestParam(value = "ids") String projectIds) {
        if (StringUtil.isBlank(projectIds)) {
            return resultMap(false, "请选择删除的项目");
        }
        String[] split = projectIds.split(",");
        if (split == null || split.length == 0) {
            return resultMap(false, "请选择删除的项目");
        }
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (String id : split) {
            ids.add(Integer.valueOf(id));
        }
        Integer[] integers = new Integer[ids.size()];
        projectService.batchDeleteProject(ids.toArray(integers));
        return resultMap(true, "删除项目成功");
    }

    @RequestMapping("/findProjects")
    public String findProjects(Integer start, Integer limit, String order, String ordertype, Model model) {
        Pager<Project> projectPager = projectService.findProjects(start, limit, order, ordertype);
        Integer interval = 2;
        if (projectPager != null) {
            for (Project project : projectPager.getRecords()) {
                List<ProjectBurn> burn = burnService.findBurnByProjectId(project.getProjectId());
                String burnValue = "";
                int i = 0;
                Date startDate = project.getProjectBegin();
                Date endDate = project.getProjectEnd();
                Date nextDate = startDate;
                float tLeft = 0f;
                for (ProjectBurn projectBurn : burn) {
                    if (projectBurn.getBurnDate().getTime() == startDate.getTime()) {
                        tLeft = projectBurn.getBurnLeft();
                    }
                }
                burnValue = tLeft + "";
                while (nextDate.before(endDate)) {
                    nextDate = DateUtils.addDays(nextDate, 1);
                    for (ProjectBurn projectBurn : burn) {
                        if (projectBurn.getBurnDate().getTime() == nextDate.getTime()) {
                            tLeft = projectBurn.getBurnLeft();
                        }
                    }
                    if (i % interval == 0) {
                        burnValue = burnValue + "," + tLeft;
                    }
                    i++;
                }
                project.setBurnValue(burnValue);
            }
        }
        model.addAttribute("projectPager", projectPager);
        return "project/allProjectData.pagelet";
    }

    @RequestMapping("/preadd")
    public String preadd(Model model) {
        List<Product> list = productService.findProductList(new Product(), "productId", "desc");
        model.addAttribute("prodcutList", list);
        return "project/addProject.page";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPageGet() {
        return "project/allProject.page";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addForm(Model model, HttpServletResponse response,
                          HttpServletRequest request, Project project,
                          Integer[] linkProduct, Integer[] whiteList) {
        String whiteListStr = "";
        if (whiteList != null) {
            if (whiteList.length > 0) {
                for (Integer i : whiteList) {
                    if (StringUtil.isBlank(whiteListStr)) {
                        whiteListStr = whiteListStr + i;
                    } else {
                        whiteListStr = whiteListStr + "," + i;
                    }
                }
            }
        }
        project.setProjectWhiteList(whiteListStr);
        Project tProject = projectService.addProject(project);
        projectProductService.addLink(linkProduct, tProject.getProjectId());

        CookieUtils.setCookie(response, TaskAction.COOKIE_PROJECT_ID, tProject.getProjectId().toString());
        CmsUtils.removeProjectList();
        return "project/allProject.page";
    }

    @RequestMapping("/allProject")
    public String jumpAllProject() {
        return "project/allProject.page";
    }

    @RequestMapping("/edit")
    public String form(Integer projectId, Model model) {
        if (projectId != null) {
            Project project = projectService.findById(projectId);
            List<ProjectTeam> teamList = teamService.findTeamByProjectId(project.getProjectId());
            model.addAttribute("teamList", teamList);
            model.addAttribute("project", project);
            List<Product> list = productService.findProductList(new Product(), "productId", "desc");
            model.addAttribute("prodcutList", list);
            return "project/survey/edit.page";
        }
        return "error";
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String editSave(Project project, Model model, Integer[] whiteList, Integer[] linkProduct) {
        if (project.getProjectId() == null) {
            Project resProject = projectService.addProject(project);
            projectProductService.addLink(linkProduct, resProject.getProjectId());
        } else {
            projectService.updateProject(project);
        }
        model.addAttribute("project", project);
        return "project/survey/index.page";
    }


    @RequestMapping("/delay")
    public String delay(Integer projectId, Model model) {
        Project project = projectService.findById(projectId);
        model.addAttribute("project", project);
        return "/project/survey/delay.pagelet";
    }

    @ResponseBody
    @RequestMapping(value = "/delaysave", method = RequestMethod.POST)
    public Map<String, String> delaySave(Project project, String content) {
        Project oldProject = projectService.findById(project.getProjectId());
        Integer res = projectService.updateProject(project);
        LogUtil.logWithComment(LogUtil.LogOperateObject.PROJECT, LogUtil.LogAction.DELAYED, oldProject.getProjectId().toString(),
                UserUtils.getUserId(), null, oldProject.getProjectId().toString(), oldProject, project, content);
        return resultMap(res > 0 ? true : false, res > 0 ? "延期成功" : "延期失败");
    }

    @RequestMapping("/hangUp")
    public String hangUp(Integer projectId, Model model) {
        Project project = projectService.findById(projectId);
        model.addAttribute("project", project);
        return "/project/survey/hangUp.pagelet";
    }

    @ResponseBody
    @RequestMapping(value = "hangUp", method = RequestMethod.POST)
    public Map<String, String> hangUpSave(Project project, String content) {
        Project oldProject = projectService.findById(project.getProjectId());
        project.setProjectStatus(project.HANGUP);
        Integer res = projectService.updateProject(project);
        LogUtil.logWithComment(LogUtil.LogOperateObject.PROJECT, LogUtil.LogAction.SUSPENDED, oldProject.getProjectId().toString(),
                UserUtils.getUserId(), null, oldProject.getProjectId().toString(), oldProject, project, content);
        return resultMap(res > 0 ? true : false, res > 0 ? "挂起成功" : "挂起失败");
    }

    @RequestMapping("/start")
    public String start(Integer projectId, Model model) {
        Project project = projectService.findById(projectId);
        model.addAttribute("project", project);
        return "/project/survey/start.pagelet";
    }

    @ResponseBody
    @RequestMapping(value = "start", method = RequestMethod.POST)
    public Map<String, String> startSave(Project project, String content) {
        Project oldProject = projectService.findById(project.getProjectId());
        project.setProjectStatus(project.DOING);
        Integer res = projectService.updateProject(project);
        LogUtil.logWithComment(LogUtil.LogOperateObject.PROJECT, LogUtil.LogAction.STARTED, oldProject.getProjectId().toString(),
                UserUtils.getUserId(), null, oldProject.getProjectId().toString(), oldProject, project, content);
        return resultMap(res > 0 ? true : false, res > 0 ? "开始成功" : "开始失败");
    }

    @RequestMapping("/doing")
    public String doing(Integer projectId, Model model) {
        Project project = projectService.findById(projectId);
        model.addAttribute("project", project);
        return "/project/survey/doing.pagelet";
    }

    @ResponseBody
    @RequestMapping(value = "doing", method = RequestMethod.POST)
    public Map<String, String> doingSave(Project project, String content) {
        Project oldProject = projectService.findById(project.getProjectId());
        project.setProjectStatus(project.DOING);
        Integer res = projectService.updateProject(project);
        LogUtil.logWithComment(LogUtil.LogOperateObject.PROJECT, LogUtil.LogAction.ACTIVATED, oldProject.getProjectId().toString(),
                UserUtils.getUserId(), null, oldProject.getProjectId().toString(), oldProject, project, content);
        return resultMap(res > 0 ? true : false, res > 0 ? "激活成功" : "激活失败");
    }

    @RequestMapping("/finish")
    public String finish(Integer projectId, Model model) {
        Project project = projectService.findById(projectId);
        model.addAttribute("project", project);
        return "/project/survey/doing.pagelet";
    }

    @ResponseBody
    @RequestMapping(value = "finish", method = RequestMethod.POST)
    public Map<String, String> finishSave(Project project, String content) {
        Project oldProject = projectService.findById(project.getProjectId());
        project.setProjectStatus(project.FINISH);
        Integer res = projectService.updateProject(project);
        LogUtil.logWithComment(LogUtil.LogOperateObject.PROJECT, LogUtil.LogAction.FINISHED, oldProject.getProjectId().toString(),
                UserUtils.getUserId(), null, oldProject.getProjectId().toString(), oldProject, project, content);
        return resultMap(res > 0 ? true : false, res > 0 ? "完成成功" : "完成失败");
    }


    @RequestMapping("/basicInformation")
    public String basicInformation(Integer projectID, Model model) {
        Project project = projectService.findById(projectID);
        List<ProjectProduct> list = projectProductService.findProducts(projectID);
        Integer[] ids = new Integer[list.size()];
        List<Integer> productIdList = new ArrayList<Integer>();
        for (ProjectProduct t : list) {
            productIdList.add(t.getProductId());
        }
        List<Product> productlist = productService.findProductListByIds(productIdList.toArray(ids));
        model.addAttribute("project", project);
        model.addAttribute("productlist", productlist);
        return "project/survey/basicInformation.pagelet";
    }

    @RequestMapping("/findManager")
    public String findManager(String projectId, Model model) {
        Project project = projectService.findById(Integer.parseInt(projectId));
        //项目负责人
        OrgUser projectPm = userService.findUser(project.getProjectPm());
        //测试负责人
        OrgUser productQd = userService.findUser(project.getProjectQd());
        //发布负责人
        OrgUser productRd = userService.findUser(project.getProjectRd());

        model.addAttribute("projectPm", projectPm);
        model.addAttribute("productQd", productQd);
        model.addAttribute("productRd", productRd);

        return "organization/others/projectUserBaseInfo.pagelet";
    }

}