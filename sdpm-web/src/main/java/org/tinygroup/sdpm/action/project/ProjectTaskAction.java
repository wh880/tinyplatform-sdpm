package org.tinygroup.sdpm.action.project;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.action.project.dto.Tasks;
import org.tinygroup.sdpm.action.project.util.TaskStatusUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.dict.util.DictUtil;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.service.StoryService;
import org.tinygroup.sdpm.project.dao.pojo.*;
import org.tinygroup.sdpm.project.service.inter.*;
import org.tinygroup.sdpm.system.dao.pojo.ProfileType;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.sdpm.util.*;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 任务
 * Created by wangying14938 on 2015-09-22.
 */
@Controller
@RequestMapping("/a/project/task")
public class ProjectTaskAction extends BaseController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ProjectStoryService storyService;
    @Autowired
    private StoryService productStoryService;
    @Autowired
    private ProjectProductService projectProductService;
    @Autowired
    private BurnService burnService;
    @Autowired
    private UserService userService;

    @RequiresPermissions(value = {"task", "project"}, logical = Logical.OR)
    @RequestMapping("index")
    public String index(@CookieValue(required = false, value = ProjectUtils.COOKIE_PROJECT_ID) String currentProjectId,
                        HttpServletResponse response, String moduleId, String choose, Model model) {
        if (StringUtil.isBlank(currentProjectId)) {
            Project project = ProjectUtils.getDefaultProject();
            if (null != project) {
                currentProjectId = String.valueOf(project.getProjectId());
                CookieUtils.setCookie(response, ProjectUtils.COOKIE_PROJECT_ID, currentProjectId);
                model.addAttribute(ProjectUtils.COOKIE_PROJECT_ID, currentProjectId);
            } else {
                return "redirect:" + adminPath + "/project/form";
            }
        }
        if (moduleId != null) {
            model.addAttribute("moduleId", moduleId);
        }
        if (StringUtil.isBlank(choose)) {
            model.addAttribute("choose", choose);
        }
        return "project/task/index";
    }

    @RequestMapping("/edit")
    public String form(Integer taskId, Model model) {
        ProjectTask task = taskService.findTask(taskId);
        model.addAttribute("task", task);
        return "project/task/edit.page";
    }

    /**
     * 指派
     *
     * @param taskId
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/call", method = RequestMethod.GET)
    public String call(Integer taskId, Model model, HttpServletRequest request) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, ProjectUtils.COOKIE_PROJECT_ID));
        ProjectTask task = taskService.findTask(taskId);
        List<OrgUser> team = userService.findTeamUserListByProjectId(projectId);
        model.addAttribute("teamList", team);
        model.addAttribute("task", task);
        return "project/task/call";
    }

    /**
     * 保存指派
     *
     * @param task
     * @param model
     * @param commnet
     * @return
     */
    @RequestMapping(value = "/call", method = RequestMethod.POST)
    public String callSave(ProjectTask task, Model model, String commnet) {
        if (task.getTaskId() == null) {
            taskService.addTask(task);
        } else {
            ProjectTask oldTask = taskService.findTask(task.getTaskId());
            taskService.updateCallTask(task);

            LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.ASSIGNED, task.getTaskId().toString(), UserUtils.getUserId(),
                    null, oldTask.getTaskProject().toString(), oldTask, task, commnet);

        }
        model.addAttribute("task", task);
        return "project/task/index.page";
    }

    @RequestMapping(value = "/finish", method = RequestMethod.GET)
    public String finish(Integer taskId, Model model, HttpServletRequest request) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, ProjectUtils.COOKIE_PROJECT_ID));
        List<ProjectTeam> teamList = teamService.findTeamByProjectId(projectId);
        ProjectTask task = taskService.findTask(taskId);
        model.addAttribute("task", task);
        model.addAttribute("teamList", teamList);
        //还需要查询其他相关任务剩余时间的信息
        return "project/task/finish";
    }

    @RequestMapping(value = "/finish", method = RequestMethod.POST)
    public String finishSave(ProjectTask task, Model model, String commnet) {
        if (task.getTaskId() == null) {
            taskService.addTask(task);
        } else {
            taskService.updateFinishTask(task);
            LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.ASSIGNED, task.getTaskId().toString(), UserUtils.getUserId(),
                    null, taskService.findTask(task.getTaskId()).getTaskProject().toString(),
                    taskService.findTask(task.getTaskId()), task, commnet);
        }

        model.addAttribute("task", task);
        return "project/task/index.page";
    }

    @RequestMapping("/note")
    public String note(Integer taskId, Model model) {
        ProjectTask task = taskService.findTask(taskId);
        model.addAttribute("task", task);
        //还需要查询其他相关任务剩余时间的信息
        return "project/task/note";
    }

    @RequestMapping(value = "/close", method = RequestMethod.GET)
    public String close(Integer taskId, Model model) {
        ProjectTask task = taskService.findTask(taskId);
        model.addAttribute("task", task);
        return "project/task/close";
    }

    @RequestMapping(value = "/close", method = RequestMethod.POST)
    public String closeSave(ProjectTask task, String content) {
        taskService.updateCloseTask(task);
        LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.CLOSED, task.getTaskId().toString(),
                UserUtils.getUserId(), null, taskService.findTask(task.getTaskId()).getTaskProject().toString(),
                taskService.findTask(task.getTaskId()), task, content);
        return "project/task/index.page";
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String start(Integer taskId, Model model) {
        ProjectTask task = taskService.findTask(taskId);
        model.addAttribute("task", task);
        return "project/task/start";
    }

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public String startSave(ProjectTask task, Model model, String content) {
        if (task.getTaskId() == null) {
            taskService.addTask(task);
        } else {
            taskService.updateStartTask(task);
            LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.STARTED, task.getTaskId().toString(),
                    UserUtils.getUserId(), null, taskService.findTask(task.getTaskId()).getTaskProject().toString(),
                    taskService.findTask(task.getTaskId()), task, content);
            burnService.updateDate(task.getTaskId());
        }
        model.addAttribute("task", task);
        return "redirect:" + adminPath + "project/task/index";
    }

    @RequestMapping("/cancel")
    public String cancel(Integer taskId, Model model) {
        ProjectTask task = taskService.findTask(taskId);
        model.addAttribute("task", task);
        return "project/task/cancel";
    }

    @RequestMapping("/cancel")
    public String cancelSave(ProjectTask task, String content) {
        if (task.getTaskId() == null) {
            taskService.addTask(task);
        } else {
            taskService.updateCancelTask(task);
            LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.CANCELED, task.getTaskId().toString(),
                    UserUtils.getUserId(), null, taskService.findTask(task.getTaskId()).getTaskProject().toString(),
                    taskService.findTask(task.getTaskId()), task, content);
        }
        return "redirect:" + adminPath + "project/task/index";
    }

    @RequestMapping("/findList")
    public String findList(Integer projectId, Model model) {
        if (projectId != null) {
            ProjectTask task = new ProjectTask();
            task.setTaskProject(projectId);
            List<ProjectTask> list = taskService.findListTask(task);
            model.addAttribute("tasksList", list);
        }
        return "project/task/datalist.pagelet";
    }

    @RequestMapping("/findPager")
    public String findPager(
            Integer start, Integer limit, String statu, String choose, Model model,
            HttpServletRequest request, HttpServletResponse response, String moduleId,
            @RequestParam(required = false, defaultValue = "task_id") String order,
            String ordertype) {
        Integer projectId = ProjectUtils.getCurrentProjectId(request, response);
        if (projectId == null) {
            return redirectProjectForm();
        }
        boolean asc = false;
        if ("desc".equals(ordertype)) {
            asc = true;
        }
        ProjectTask task = new ProjectTask();
        task.setTaskProject(projectId);
        String moduleIds = "";
        if (!StringUtil.isBlank(moduleId)) {
            if (moduleId.contains("p")) {
                moduleIds = ModuleUtil.getConditionByRoot(Integer.parseInt(moduleId.substring(1)), moduleService);
            } else {
                moduleIds = ModuleUtil.getCondition(Integer.parseInt(moduleId), moduleService);
            }
        }
        //默认显示未关闭任务
        if (statu == null && choose == null) {
            statu = "0";
        }

        String condition = TaskStatusUtil.getCondition(statu, choose, UserUtils.getUserId(), moduleIds);
        Pager<ProjectTask> taskPager;
        if (condition.equals("completeByMe")) {
            OrgUser user = UserUtils.getUser();
            taskPager = taskService.findPagerTaskByMe(start, limit, task, order, asc, user);
        } else {
            taskPager = taskService.findTaskPager(start, limit, task, order, asc, condition);
        }
        model.addAttribute("taskPager", taskPager);
        model.addAttribute("statu", statu);
        model.addAttribute("choose", choose);
        return "project/task/datalist.pagelet";
    }

    @RequestMapping("/save")
    public String save(ProjectTask task, @RequestParam(value = "file", required = false) MultipartFile file,
                       Model model, String[] taskMailtoArray, HttpServletRequest request, String comment) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, ProjectUtils.COOKIE_PROJECT_ID));
        task.setTaskProject(projectId);
        if (task.getTaskId() == null) {
            String taskMailTo = "";
            if (taskMailtoArray != null) {
                for (String i : taskMailtoArray) {
                    if (StringUtil.isBlank(taskMailTo)) {
                        taskMailTo = taskMailTo + i;
                    } else {
                        taskMailTo = taskMailTo + "," + i;
                    }
                }
            }
            task.setTaskMailto(taskMailTo);
            ProjectTask newtask = taskService.addTask(task);
            LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.OPENED,
                    newtask.getTaskId().toString(), UserUtils.getUserId(),
                    null, newtask.getTaskProject().toString(), null,
                    newtask, comment);
            try {
                uploadNoTitle(file, newtask.getTaskId(), ProfileType.TASK);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            taskService.updateTask(task);
        }
        model.addAttribute("task", task);
        return "redirect:" + adminPath + "project/task/index";
    }


    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String editSave(ProjectTask task, Model model, String contents) {
        ProjectTask oldTask = taskService.findTask(task.getTaskId());
        taskService.updateEditTask(task);
        LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.EDITED, oldTask.getTaskId().toString(),
                UserUtils.getUserId(), null, oldTask.getTaskProject().toString(), oldTask, task, contents);
        model.addAttribute("task", task);
        return "redirect:" + adminPath + "project/task/index";
    }

    /**
     * 新增任务表单
     *
     * @param request
     * @param model
     * @param storyId
     * @param taskId
     * @return
     */
    @RequestMapping("/form")
    public String preAdd(HttpServletRequest request, Model model, Integer storyId, String taskId) {
        String cookie = CookieUtils.getCookie(request, ProjectUtils.COOKIE_PROJECT_ID);
        if (StringUtil.isBlank(cookie)) {
            addMessage(model, "请选择项目");

        }
        Integer projectId = Integer.valueOf(cookie);
        model.addAttribute("team", teamService.findTeamByProjectId(projectId));
        SystemModule module = new SystemModule();
        module.setModuleType("task");
        module.setModuleRoot(projectId);
        List<SystemModule> moduleList = moduleService.findModuleList(module);
        for (SystemModule m : moduleList) {
            m.setModuleName(ModuleUtil.getPath(m.getModuleId(), "/", moduleService, "", false));
        }
        List<ProjectProduct> projectProductList = projectProductService.findProducts(projectId);
        for (ProjectProduct pp : projectProductList) {
            module.setModuleType("story");
            module.setModuleRoot(pp.getProductId());
            List<SystemModule> tModuleList = moduleService.findModuleList(module);
            for (SystemModule m : tModuleList) {
                m.setModuleName(ModuleUtil.getPath(m.getModuleId(), "/", moduleService, "", false));
            }
            moduleList.addAll(tModuleList);
        }
        if (taskId != null) {
            ProjectTask task = taskService.findTask(Integer.parseInt(taskId));
            model.addAttribute("copyTask", task);
        }

        List<ProductStory> storyList = storyService.findStoryByProject(projectId);
        ProductStory story = new ProductStory();
        if (storyId != null) {
            story = productStoryService.findStory(storyId);
        }
        model.addAttribute("story", story);

        model.addAttribute("moduleList", moduleList);
        model.addAttribute("storyList", storyList);

        return "project/task/add";
    }

    @RequestMapping("/batchadd")
    public String batchAdd(Integer taskId, Model model) {
        if (taskId != null) {
            ProjectTask task = taskService.findTask(taskId);
            model.addAttribute("task", task);
            //还需要查询其他相关任务剩余时间的信息
            return "project/task/batchAdd.page";
        }
        return "project/task/batchAdd.page";
    }

    @RequestMapping("/findTask")
    public String findTask(Model model, Integer taskId) {
        ProjectTask task = taskService.findTask(taskId);
        model.addAttribute("task", task);

        return "project/task/IDLink.page";
    }

    @RequestMapping("/basicInfoEdit")
    public String basicInfoEdit(Integer taskId, Model model) {
        ProjectTask task = taskService.findTask(taskId);
        String projectName = projectService.findProjectById(task.getTaskProject()).getProjectName();
        List<ProjectTeam> teamList = teamService.findTeamByProjectId(task.getTaskProject());
        SystemModule module = new SystemModule();
        module.setModuleType("task");
        module.setModuleRoot(task.getTaskProject());
        List<SystemModule> moduleList = moduleService.findModuleList(module);
        for (SystemModule m : moduleList) {
            m.setModuleName(ModuleUtil.getPath(m.getModuleId(), "/", moduleService, "", false));
        }
        List<ProjectProduct> projectProductList = projectProductService.findProducts(task.getTaskProject());
        for (ProjectProduct pp : projectProductList) {
            module.setModuleType("story");
            module.setModuleRoot(pp.getProductId());
            List<SystemModule> tModuleList = moduleService.findModuleList(module);
            for (SystemModule m : tModuleList) {
                m.setModuleName(ModuleUtil.getPath(m.getModuleId(), "/", moduleService, "", false));
            }
            moduleList.addAll(tModuleList);
        }

        List<ProductStory> storyList = storyService.findStoryByProject(task.getTaskProject());
        model.addAttribute("task", task);
        model.addAttribute("projectName", projectName);
        model.addAttribute("teamList", teamList);
        model.addAttribute("moduleList", moduleList);
        model.addAttribute("storyList", storyList);
        return "project/task/basicInfoEdit.pagelet";
    }

    @RequestMapping("/basicInformation")
    public String basicInformation(Integer taskId, Model model) {
        ProjectTask task = taskService.findTask(taskId);
        Project project = projectService.findProjectById(task.getTaskProject());
        model.addAttribute("task", task);
        model.addAttribute("project", project);

        //查询所属模块string
        String modulPath = "";
        if (task.getTaskModule() == null) {
            modulPath = "/";
        } else {
            modulPath = ModuleUtil.getPath(task.getTaskModule(), " > ", moduleService, task.getTaskProject().toString(), true);
        }
        model.addAttribute("modulPath", modulPath);
        //查询相关需求名字
        ProductStory productStory = productStoryService.findStory(task.getTaskStory());
        String storyTitle = productStory == null ? "" : productStory.getStoryTitle();
        model.addAttribute("storyTitle", storyTitle);
        return "project/task/basicInformation.pagelet";
    }

    @RequestMapping("/preBatchAdd")
    public String preBatchAdd(@CookieValue(required = false, value = ProjectUtils.COOKIE_PROJECT_ID) String projectId,
                              Model model, HttpServletRequest request) {
        model.addAttribute("teamList", teamService.findTeamByProjectId(Integer.parseInt(projectId)));

        List<ProductStory> storyList = storyService.findStoryByProject(Integer.parseInt(projectId));
        model.addAttribute("storyList", storyList);

        SystemModule module = new SystemModule();
        module.setModuleType("task");
        module.setModuleRoot(Integer.parseInt(projectId));
        List<SystemModule> moduleList = moduleService.findModuleList(module);
        for (SystemModule m : moduleList) {
            m.setModuleName(ModuleUtil.getPath(m.getModuleId(), "/", moduleService, "", false));
        }
        List<ProjectProduct> projectProductList = projectProductService.findProducts(Integer.parseInt(projectId));
        for (ProjectProduct pp : projectProductList) {
            module.setModuleType("story");
            module.setModuleRoot(pp.getProductId());
            List<SystemModule> tModuleList = moduleService.findModuleList(module);
            for (SystemModule m : tModuleList) {
                m.setModuleName(ModuleUtil.getPath(m.getModuleId(), "/", moduleService, "", false));
            }
            moduleList.addAll(tModuleList);
        }
        model.addAttribute("modulesList", moduleList);

        return "project/task/batchAdd.page";
    }

    @RequestMapping("/batchAdd")
    public String batchAdd(Tasks tasks, HttpServletRequest request) {
        List<ProjectTask> taskList = tasks.getTaskList();
        for (int i = 0; i < taskList.size(); i++) {
            if (StringUtil.isBlank(taskList.get(i).getTaskName())) {
                taskList.remove(i);
                i--;
            }
        }
        if (taskList.isEmpty()) {
            return "project/task/index.page";
        } else {
            Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, ProjectUtils.COOKIE_PROJECT_ID));
            taskService.batchAdd(taskList, projectId);
            return "project/task/index.page";
        }
    }

    @RequestMapping("/gantt")
    public String gantt(Model model, String choose) {
        model.addAttribute("choose", choose);
        return "project/task/gantt.page";
    }

    @ResponseBody
    @RequestMapping("/gantt/init")
    public List<Map<String, String>> ganttInit(@CookieValue(value = ProjectUtils.COOKIE_PROJECT_ID, required = false) String projectId,
                                               HttpServletResponse response) {
        List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
        if (StringUtil.isBlank(projectId)) {
            Project project = ProjectUtils.getProject(projectId);
            if (project.getProjectId() != null) {
                projectId = project.getProjectId().toString();
                CookieUtils.setCookie(response, ProjectUtils.COOKIE_PROJECT_ID, projectId);
            } else {
                return null;
            }
        }
        ProjectTask task = new ProjectTask();
        task.setTaskProject(Integer.valueOf(projectId));
        List<ProjectTask> taskList = taskService.findListTask(task);

        for (ProjectTask t : taskList) {
            SimpleDateFormat format = new SimpleDateFormat("M/d/yyyy");
            Map<String, String> map = new HashMap<String, String>();
            map.put("pID", t.getTaskId().toString());
            map.put("pName", t.getTaskName());
            if (t.getTaskRealStarted() != null) {
                map.put("pStart", format.format(t.getTaskRealStarted()));
            } else {
                map.put("pStart", "");
            }
            if (t.getTaskFinishedDate() != null) {
                map.put("pEnd", format.format(t.getTaskFinishedDate()));
            } else {
                map.put("pEnd", format.format(new Date()));
            }

            map.put("pColor", t.getTaskPri().toString());
            map.put("pRes", userService.findUser(t.getTaskAssignedTo()).getOrgUserRealName());
            //进度
            float comp;
            if ((t.getTaskConsumed() == null || t.getTaskConsumed() == 0) || (t.getTaskConsumed() + t.getTaskLeft() == 0)) {
                comp = 0f;
            } else {
                comp = t.getTaskConsumed() / (t.getTaskConsumed() + t.getTaskLeft());
            }
            map.put("pComp", String.valueOf(comp * 100));
            map.put("pGroup", "0");
            map.put("pParent", "0");
            map.put("pOpen", "1");
            map.put("pDepend", "0");
            map.put("pCaption", "");
            resultList.add(map);
        }
        return resultList;
    }

    @RequestMapping("/gantt/find")
    public String ganttFind(Integer id, Model model) {
        ProjectTask task = taskService.findTask(id);
        model.addAttribute("task", task);
        return "project/task/ganttFind.pagelet";
    }

    @RequestMapping("/board")
    public String board(Model model, String ajax) {
        ProjectTask task = new ProjectTask();
        task.setTaskStatus(task.WAIT);
        List<ProjectTask> resList = taskService.findListTask(task);
        model.addAttribute("waitList", resList);

        task.setTaskStatus(task.DOING);
        resList = taskService.findListTask(task);
        model.addAttribute("doingList", resList);

        task.setTaskStatus(task.DONE);
        resList = taskService.findListTask(task);
        model.addAttribute("doneList", resList);

        task.setTaskStatus(task.CANCEL);
        resList = taskService.findListTask(task);
        model.addAttribute("cancelList", resList);

        task.setTaskStatus(task.CLOSE);
        resList = taskService.findListTask(task);
        model.addAttribute("closeList", resList);

        return "project/task/board.page" + (ajax != null ? "let" : "");
    }

    @RequestMapping("/modal/{forward}")
    public String doc(@PathVariable(value = "forward") String forward, Model model, String taskId) {
        ProjectTask task = taskService.findTask(Integer.parseInt(taskId));
        model.addAttribute("teamList", teamService.findTeamByProjectId(task.getTaskProject()));
        model.addAttribute("task", task);
        return "project/task/modal/" + forward + ".pagelet";
    }

    @RequestMapping("/grouping")
    public String grouping(@CookieValue(value = ProjectUtils.COOKIE_PROJECT_ID, required = false) String projectId,
                           String type, Model model) {
        Map<String, List<ProjectTask>> map = taskService.findGroup(DictUtil.getValue("groupType", type), Integer.parseInt(projectId));
        Map<String, List<ProjectTask>> mapDocument = new HashMap<String, List<ProjectTask>>();
        if (type.equals("1")) {
            for (String key : map.keySet()) {
                if (key.isEmpty()) {
                    mapDocument.put(key, map.get(key));
                } else {
                    String t = productStoryService.findStory(Integer.parseInt(key)).getStoryTitle();
                    mapDocument.put(t, map.get(key));
                }
            }
        }
        model.addAttribute("mapDocument", mapDocument);
        model.addAttribute("map", map);
        model.addAttribute("type", type);
        return "project/task/grouping.page";
    }

    @ResponseBody
    @RequestMapping("/ajaxChangeStatu")
    public Map<String, String> ajaxChangeStatu(ProjectTask task, String content, String taskStatus) {
        Map<String, String> map;
        task.setTaskLastEditedBy(UserUtils.getUserId());
        Integer res;
        LogUtil.LogAction logAction = null;
        if ("doing".equals(taskStatus)) {
            res = taskService.updateDoingTask(task);
            logAction = LogUtil.LogAction.ACTIVATED;
            map = getMap(res, "激活成功", "激活失败");
        } else if ("close".equals(taskStatus)) {
            res = taskService.updateCloseTask(task);
            map = getMap(res, "关闭成功", "关闭失败");
            logAction = LogUtil.LogAction.CLOSED;
        } else if ("cancel".equals(taskStatus)) {
            res = taskService.updateCancelTask(task);
            logAction = LogUtil.LogAction.CANCELED;
            map = getMap(res, "取消成功", "取消失败");
        } else if ("finish".equals(taskStatus)) {
            res = taskService.updateFinishTask(task);
            logAction = LogUtil.LogAction.FINISHED;
            map = getMap(res, "完成成功", "完成失败");
        } else if ("start".equals(taskStatus)) {
            res = taskService.updateStartTask(task);
            logAction = LogUtil.LogAction.STARTED;
            map = getMap(res, "开始成功", "开始失败");
        } else {
            res = taskService.updateTask(task);
            logAction = LogUtil.LogAction.EDITED;
            map = getMap(res, "编辑成功", "编辑失败");
        }
        LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, logAction, task.getTaskId().toString(),
                UserUtils.getUserId(), null, taskService.findTask(task.getTaskId()).getTaskProject().toString(),
                taskService.findTask(task.getTaskId()), task, content);
        return map;
    }

    @RequestMapping("/reportform")
    public String reportform() {
        return "project/task/reportform.page";
    }

    @RequestMapping("/buildChart")
    public String buildChart(String ids, Model model) {
        Map<String, List> map = new HashMap<String, List>();
        String[] idArray = ids.split(",");
        if (idArray.length > 0) {
            for (String id : idArray) {
                List<TaskChartBean> list = taskService.buildChart(id);
                //部分内容需要格式化内容
                if ("3".equals(id)) {
                    for (TaskChartBean bean : list) {
                        bean.setTitle(DictUtil.getValue("taskType", bean.getTitle()));
                    }
                } else if ("5".equals(id)) {
                    for (TaskChartBean bean : list) {
                        bean.setTitle(DictUtil.getValue("taskStatus", bean.getTitle()));
                    }
                }
                map.put(DictUtil.getValue("chartType", id), list);
            }
        }
        model.addAttribute("map", map);
        return "project/task/reportFormDate.pagelet";
    }

    private Map<String, String> getMap(Integer res, String successMsg, String falseMsg) {
        Map<String, String> map = new HashMap<String, String>();
        if (res > 0) {
            map.putAll(resultMap(true, successMsg));
        } else {
            map.putAll(resultMap(false, falseMsg));
        }
        return map;
    }

}
