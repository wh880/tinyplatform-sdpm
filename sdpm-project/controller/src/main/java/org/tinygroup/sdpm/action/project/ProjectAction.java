package org.tinygroup.sdpm.action.project;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tinygroup.commons.tools.ArrayUtil;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.util.Collections3;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.RoleService;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.inter.ProductService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.service.dto.BurnDTO;
import org.tinygroup.sdpm.project.service.inter.*;
import org.tinygroup.sdpm.system.dao.pojo.SystemConfig;
import org.tinygroup.sdpm.util.CookieUtils;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.weblayer.mvc.WebContextAware;

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
public class ProjectAction extends BaseController implements WebContextAware {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProjectProductService projectProductService;
    @Autowired
    private BurnService burnService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private TaskService taskService;


    @ModelAttribute
    public void init(Model model) {
        initSearchBar(model, "项目");
    }

    @RequiresPermissions("survey")
    @RequestMapping("/view")
    public String view(Model model, HttpServletRequest request, HttpServletResponse response,
                       Integer projectId) {
        if (null == projectId) {
            projectId = projectOperate.getCurrentProjectId(request, response);
            if (projectId == null) {
                return redirectProjectForm();
            }
        }
        Project project = projectService.findProjectById(projectId);
        if (project == null) {
            return notFoundView();
        }
        CookieUtils.setCookie(response, projectOperate.COOKIE_PROJECT_ID, projectId.toString());
        model.addAttribute("project", project);
        return "/project/view/info/project/index";
    }

    /**
     * 新增项目表单
     *
     * @return
     */
    @RequiresPermissions(value = {"project-op-add", "batch-distribute-task", "pro-Info2-copy", "pro-task-proposeversion"}, logical = Logical.OR)
    @RequestMapping("/form")
    public String form(Model model) {
        OrgRole orgRole = new OrgRole();
        orgRole.setOrgRoleType(OrgRole.ROLE_TYPE_PROJECT);
        List<OrgRole> roleList = roleService.findSystemRoles();
        model.addAttribute("roleList", roleList);
        return "project/operate/project/form";
    }

    /**
     * 校验项目名称
     *
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/validProjectName")
    public Map validProjectName(String param) {
        Project project = new Project();
        project.setProjectName(param);
        List<Project> projectList = projectService.findProjectList(project, null, null);
        if (CollectionUtil.isEmpty(projectList)) {
            return resultMap(true, "验证成功！");
        } else {
            return resultMap(false, "已经存在相同的项目名，请更换项目名称。");
        }
    }

    /**
     * 新增项目表单保存
     *
     * @param response
     * @param project
     * @param linkProduct
     * @param whiteList
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(HttpServletResponse response, Project project,
                       String linkProduct, Integer[] whiteList) {
        project.setProjectOpenedBy(userUtils.getUserId());
        project.setProjectWhiteList(StringUtil.join(whiteList, ","));
        project = projectService.addProject(project);
        String[] productIds = linkProduct.split(",");
        projectProductService.addProjectLinkToProduct(productIds, project.getProjectId());
        CookieUtils.setCookie(response, projectOperate.COOKIE_PROJECT_ID, project.getProjectId().toString());
        LogUtil.log(LogUtil.LogOperateObject.PROJECT, LogUtil.LogAction.CREATED, project.getProjectId().toString(), userUtils.getUserId());
        return "redirect:" + adminPath + "/project/guide";
    }

    /**
     * 添加项目后的导向
     *
     * @return
     */
    @RequestMapping(value = "/guide")
    public String guide() {
        return "project/index/project/guide";
    }

    /**
     * 所有项目列表
     *
     * @return
     */
    @RequiresPermissions("project-op-all")
    @RequestMapping("/list")
    public String list() {
        return "project/index/project/list";
    }

    /**
     * 数据表格List
     *
     * @param start
     * @param limit
     * @param order
     * @param orderType
     * @param model
     * @return
     */
    @RequestMapping("/list/data")
    public String listData(Integer start, Integer limit,
                           String order, String orderType, Integer key, Model model) {
        Integer[] userProjectIds = projectOperate.getUserProjectIdList();
        if (key != null && !ArrayUtil.isEmptyArray(userProjectIds)) {//用于搜索
            userProjectIds = new Integer[]{key};
        }
        Pager<Project> projectPager = projectService.findProjects(start, limit, order, orderType, userProjectIds);
        Integer interval = 2;
        if (projectPager != null) {
            for (Project project : projectPager.getRecords()) {
                BurnDTO burnDTO = burnService.initBurn(project.getProjectId(), interval);
                project.setBurnValue(burnDTO.getLeftValues());
            }
        }
        model.addAttribute("projectPager", projectPager);
        return "project/data/project/listData.pagelet";
    }

    @RequiresPermissions("pro-survey-edit")
    @RequestMapping("/edit")
    public String editForm(Integer projectId, Model model) {
        Project project = projectService.findProjectById(projectId);
        model.addAttribute("project", project);
        List<ProjectProduct> projectProductList = projectProductService.findProductListByProjectId(projectId);
        String productIds = Collections3.extractToString(projectProductList, "productId", ",");
        model.addAttribute("productIds", productIds);
        model.addAttribute("teamList", userService.findTeamUserListByProjectId(projectId));
        model.addAttribute("productList", productUtils.getAllProductListByUser());
        return "project/operate/project/edit";
    }

    /**
     * 编辑已有项目
     *
     * @param project
     * @param model
     * @param whiteList
     * @param productIds
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(Project project, Model model, Integer[] whiteList, String productIds) {
        project.setProjectWhiteList(StringUtil.join(whiteList, ","));
        String[] pIds = productIds.split(",");
        if (!ArrayUtil.isEmptyArray(pIds)) {
            projectProductService.addProjectLinkToProduct(pIds, project.getProjectId());
        }
        projectService.updateProject(project);
        model.addAttribute("project", project);
        return "redirect:" + adminPath + "/project/view?projectId=" + project.getProjectId();
    }

    @RequiresPermissions("pro-survey-delay")
    @RequestMapping("/delay")
    public String delay(Integer projectId, Model model) {
        Project project = projectService.findProjectById(projectId);
        model.addAttribute("project", project);
        return "/project/modal/project/delay.pagelet";
    }

    @ResponseBody
    @RequestMapping(value = "/delay", method = RequestMethod.POST)
    public Map<String, String> delaySave(Project project, String content) {
        Project oldProject = projectService.findProjectById(project.getProjectId());
        Integer res = projectService.updateProject(project);
        LogUtil.logWithComment(LogUtil.LogOperateObject.PROJECT, LogUtil.LogAction.DELAYED, oldProject.getProjectId().toString(),
                userUtils.getUserId(), null, oldProject.getProjectId().toString(), oldProject, project, content);
        return resultMap(res > 0 ? true : false, res > 0 ? "延期成功" : "延期失败");
    }

    @RequiresPermissions("pro-survey-hangUp")
    @RequestMapping(value = "/hangUp", method = RequestMethod.GET)
    public String hangUp(Integer projectId, Model model) {
        Project project = projectService.findProjectById(projectId);
        model.addAttribute("project", project);
        return "/project/modal/project/hangUp.pagelet";
    }

    @ResponseBody
    @RequestMapping(value = "hangUp", method = RequestMethod.POST)
    public Map<String, String> hangUpSave(Project project, String content) {
        Project oldProject = projectService.findProjectById(project.getProjectId());
        project.setProjectStatus(Project.HANGUP);
        Integer res = projectService.updateProject(project);
        LogUtil.logWithComment(LogUtil.LogOperateObject.PROJECT, LogUtil.LogAction.SUSPENDED, oldProject.getProjectId().toString(),
                userUtils.getUserId(), null, oldProject.getProjectId().toString(), oldProject, project, content);
        return resultMap(res > 0 ? true : false, res > 0 ? "挂起成功" : "挂起失败");
    }

    @RequiresPermissions("pro-survey-doing")
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String start(Integer projectId, Model model) {
        Project project = projectService.findProjectById(projectId);
        model.addAttribute("project", project);
        return "/project/modal/project/start.pagelet";
    }

    @ResponseBody
    @RequestMapping(value = "start", method = RequestMethod.POST)
    public Map<String, String> startSave(Project project, String content) {
        Project oldProject = projectService.findProjectById(project.getProjectId());
        project.setProjectStatus(Project.DOING);

        Integer res = projectService.updateProject(project);
        LogUtil.logWithComment(LogUtil.LogOperateObject.PROJECT, LogUtil.LogAction.STARTED, oldProject.getProjectId().toString(),
                userUtils.getUserId(), null, oldProject.getProjectId().toString(), oldProject, project, content);
        return resultMap(res > 0 ? true : false, res > 0 ? "开始成功" : "开始失败");
    }

    @RequiresPermissions("pro-survey-active")
    @RequestMapping(value = "/doing", method = RequestMethod.GET)
    public String doing(Integer projectId, Model model) {
        Project project = projectService.findProjectById(projectId);
        model.addAttribute("project", project);
        return "/project/modal/project/doing.pagelet";
    }

    @ResponseBody
    @RequestMapping(value = "doing", method = RequestMethod.POST)
    public Map<String, String> doingSave(Project project, String content) {
        Project oldProject = projectService.findProjectById(project.getProjectId());
        project.setProjectStatus(Project.DOING);
        Integer res = projectService.updateProject(project);
        LogUtil.logWithComment(LogUtil.LogOperateObject.PROJECT, LogUtil.LogAction.ACTIVATED, oldProject.getProjectId().toString(),
                userUtils.getUserId(), null, oldProject.getProjectId().toString(), oldProject, project, content);
        return resultMap(res > 0 ? true : false, res > 0 ? "激活成功" : "激活失败");
    }

    @RequiresPermissions("pro-survey-finish")
    @RequestMapping("/finish")
    public String finish(Integer projectId, Model model) {
        model.addAttribute("teamList", userService.findTeamUserListByProjectId(projectId));

        Project project = projectService.findProjectById(projectId);
        model.addAttribute("project", project);
        return "/project/modal/project/finish.pagelet";
    }

    @ResponseBody
    @RequestMapping(value = "finish", method = RequestMethod.POST)
    public Map<String, String> finishSave(Project project, String content) {
        Project oldProject = projectService.findProjectById(project.getProjectId());
        project.setProjectStatus(Project.FINISH);
        project.setProjectCloseBy(userUtils.getUserId());
        project.setProjectCloseDate(new Date());
        Integer res = projectService.updateProject(project);
        LogUtil.logWithComment(LogUtil.LogOperateObject.PROJECT, LogUtil.LogAction.FINISHED, oldProject.getProjectId().toString(),
                userUtils.getUserId(), null, oldProject.getProjectId().toString(), oldProject, project, content);
        return resultMap(res > 0 ? true : false, res > 0 ? "结束成功" : "结束失败");
    }

    /**
     * 项目基本信息
     *
     * @param projectID
     * @param model
     * @return
     */
    @RequestMapping("/basicInformation")
    public String basicInformation(Integer projectID, Model model) {
        Project project = projectService.findProjectById(projectID);
        List<ProjectProduct> projectProduct = projectProductService.findProductListByProjectId(projectID);
        List<Integer> productIds = Collections3.extractToList(projectProduct, "productId");
        List<Product> productList = productService.findProductListByIds(productIds.toArray(new Integer[0]));
        model.addAttribute("project", project);
        model.addAttribute("productList", productList);

        ProjectTask projectTaskTimeInfo = taskService.getProjectTaskTimeInfo(projectID);
        float taskLeft = 0, taskEstimate = 0, taskConsumed = 0;
        if (null != projectTaskTimeInfo.getTaskLeft()) {
            taskLeft = projectTaskTimeInfo.getTaskLeft();
        }
        if (null != projectTaskTimeInfo.getTaskConsumed()) {
            taskConsumed = projectTaskTimeInfo.getTaskConsumed();
        }
        if (null != projectTaskTimeInfo.getTaskEstimate()) {
            taskEstimate = projectTaskTimeInfo.getTaskEstimate();
        }
        model.addAttribute("taskLeft", taskLeft);
        model.addAttribute("taskConsumed", taskConsumed);
        model.addAttribute("taskEstimate", taskEstimate);

        Integer projectTeamTimeInfo = teamService.getProjectTeamTimeInfo(projectID);
        model.addAttribute("projectTeamTimeInfo", projectTeamTimeInfo);
        return "project/view/rightinfo/project/basicInformation.pagelet";
    }

    @RequestMapping("/findManager")
    public String findManager(String projectId, Model model) {
        Project project = projectService.findProjectById(Integer.parseInt(projectId));
        if (project != null) {
            //项目负责人
            OrgUser projectPm = userService.findUser(project.getProjectPm());
            //测试负责人
            OrgUser productQd = userService.findUser(project.getProjectQd());
            //发布负责人
            OrgUser productRd = userService.findUser(project.getProjectRd());
            model.addAttribute("projectPm", projectPm);
            model.addAttribute("productQd", productQd);
            model.addAttribute("productRd", productRd);
        }
        return "organization/others/projectUserBaseInfo.pagelet";
    }

    /**
     * 批量删除项目
     *
     * @param projectIds
     * @return
     */
    @RequiresPermissions("pro-survey-remove")
    @ResponseBody
    @RequestMapping("/batchDelete")
    public Map batchDelete(@RequestParam(value = "ids") String projectIds, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtil.isBlank(projectIds)) {
            return resultMap(false, "请选择删除的项目");
        }
        String[] split = projectIds.split(",");
        if (split == null || split.length == 0) {
            return resultMap(false, "请选择删除的项目");
        }
        Integer currentProjectId = projectOperate.getCurrentProjectId(request, response);
        boolean isCurrent = false;//判断当前cookies中保存的是否为删除值
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (String id : split) {
            if (id.equals(currentProjectId.toString())) {
                isCurrent = true;
            }
            ids.add(Integer.valueOf(id));
        }
        Integer[] integers = new Integer[ids.size()];
        projectService.batchDeleteProject(ids.toArray(integers));
        if (isCurrent) {
            projectOperate.initCurrentProjectId(null, response);
        }
        return resultMap(true, "删除项目成功");
    }

    /**
     * 删除项目
     *
     * @param id
     * @return
     */
    @RequiresPermissions("pro-survey-remove")
    @ResponseBody
    @RequestMapping("/delete")
    public Map delete(Integer id, HttpServletResponse response) {
        if (id == null) {
            return resultMap(false, "请选择删除的项目");
        }
        Project project = projectService.findProjectById(id);
        project.setProjectDeleted(Project.DELETE_YES);
        projectService.updateProject(project);
        projectOperate.initCurrentProjectId(null, response);
        return resultMap(true, "删除项目成功");
    }

    @ResponseBody
    @RequestMapping("ajax/projectInCondition")
    public List<Project> projectInCondition(String key, String initKey, HttpServletRequest request) {
        if (initKey != null) {
            List<Project> result = new ArrayList<Project>();
            result.add(projectService.findProjectById(Integer.parseInt(initKey)));
            return result;
        }
        Integer[] pIds = projectOperate.getUserProjectIdList();
        return projectService.projectInCondition(key, Integer.parseInt(configService.getConfigBySection(SystemConfig.SEARCH_CONFIG).getConfigKey()), pIds);
    }

}