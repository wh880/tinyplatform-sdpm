package org.tinygroup.sdpm.action.project;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.sdpm.action.project.util.TaskStatusUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.dict.util.DictUtil;
import org.tinygroup.sdpm.dto.UploadProfile;
import org.tinygroup.sdpm.dto.project.EffortList;
import org.tinygroup.sdpm.dto.project.Tasks;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.service.StoryService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.dao.pojo.TaskChartBean;
import org.tinygroup.sdpm.project.service.inter.*;
import org.tinygroup.sdpm.system.dao.pojo.ProfileType;
import org.tinygroup.sdpm.system.dao.pojo.SystemEffort;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.inter.EffortService;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
import org.tinygroup.sdpm.util.CookieUtils;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.ModuleUtil;
import org.tinygroup.sdpm.util.ProjectOperate;
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
    private StoryService storyService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private EffortService effortService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ProjectStoryService projectStoryService;
    @Autowired
    private StoryService productStoryService;
    @Autowired
    private ProjectProductService projectProductService;
    @Autowired
    private BurnService burnService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProfileService profileService;

    @ModelAttribute
    public void init(Model model) {
        initSearchBar(model, "任务");
    }


    @RequiresPermissions(value = {"project", "task"}, logical = Logical.OR)
    @RequestMapping("index")
    public String index(@CookieValue(required = false, value = ProjectOperate.COOKIE_PROJECT_ID) String currentProjectId,
                        HttpServletResponse response, String moduleId, String choose, Model model) {
        if (StringUtil.isBlank(currentProjectId)) {
            Project project = projectOperate.getDefaultProject();
            if (null != project) {
                currentProjectId = String.valueOf(project.getProjectId());
                CookieUtils.setCookie(response, projectOperate.COOKIE_PROJECT_ID, currentProjectId);
                model.addAttribute(projectOperate.COOKIE_PROJECT_ID, currentProjectId);
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
        return "project/index/task/index";
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
    @RequiresPermissions(value = {"pro-task-proposeversion", "pro-Info2-copy", "batch-distribute-task"}, logical = Logical.OR)
    @RequestMapping("/form")
    public String form(HttpServletRequest request, Model model,
                       Integer storyId,
                       String taskId,
                       Integer moduleId) {
        String cookie = CookieUtils.getCookie(request, projectOperate.COOKIE_PROJECT_ID);
        if (StringUtil.isBlank(cookie)) {
            addMessage(model, "请选择项目");
        }
        Integer projectId = Integer.valueOf(cookie);
        model.addAttribute("teamList", userService.findTeamUserListByProjectId(projectId));

        if (taskId != null) {
            ProjectTask task = taskService.findTaskById(Integer.parseInt(taskId));
            model.addAttribute("copyTask", task);
        }
        List<ProductStory> storyList = projectStoryService.findStoryByProject(projectId);
        model.addAttribute("moduleList", findModuleList(storyId, projectId));
        model.addAttribute("moduleId", moduleId);
        model.addAttribute("storyId", storyId);
        model.addAttribute("storyList", storyList);
        return "project/operate/task/common/add";
    }

    /**
     * 新增任务报存
     *
     * @param task
     * @param taskMailToArray
     * @param request
     * @param comment
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(ProjectTask task,
                       String[] taskMailToArray, HttpServletRequest request, String comment, String lastAddress,
                       UploadProfile uploadProfile) throws IOException {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, ProjectOperate.COOKIE_PROJECT_ID));
        task.setTaskProject(projectId);
        String taskMailTo = StringUtil.join(taskMailToArray, ",");
        task.setTaskMailto(taskMailTo);
        task.setTaskLeft(task.getTaskEstimate());
        task = taskService.addTask(task);

        LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.OPENED,
                task.getTaskId().toString(), userUtils.getUserId(),
                null, task.getTaskProject().toString(), null, null, comment);
        try {
            processProfile(uploadProfile, task.getTaskId(), ProfileType.TASK);
        } catch (IOException e) {
            logger.logMessage(LogLevel.ERROR, "上传文件文件出错，请求路径{}", e, request.getRequestURI());
        }
        if (!StringUtil.isBlank(lastAddress)) {
            return "redirect:" + lastAddress;
        }
        return "redirect:" + adminPath + "/project/task/index";
    }

    /**
     * 编辑任务
     *
     * @param taskId
     * @param model
     * @return
     */
    @RequiresPermissions(value = {"pro-task-edit", "pro-Info2-edit"}, logical = Logical.OR)
    @RequestMapping("/edit")
    public String editForm(Integer taskId, Model model) {
        ProjectTask task = taskService.findTaskById(taskId);
        model.addAttribute("task", task);


        SystemProfile systemProfile = new SystemProfile();
        systemProfile.setFileObjectId(taskId);
        systemProfile.setFileObjectType(ProfileType.TASK.getType());
        List<SystemProfile> fileList = profileService.findSystemProfile(systemProfile);
        model.addAttribute("fileList", fileList);


        return "project/operate/task/common/edit";
    }

    /**
     * 编辑保存
     *
     * @param task
     * @param model
     * @param contents
     * @return
     */
    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String editSave(ProjectTask task, Model model, String contents,
                           UploadProfile uploadProfile) throws IOException {
        ProjectTask oldTask = taskService.findTaskById(task.getTaskId());
        taskService.updateEditTask(task);
        processProfile(uploadProfile, task.getTaskId(), ProfileType.TASK);

        LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.EDITED, oldTask.getTaskId().toString(),
                userUtils.getUserId(), null, oldTask.getTaskProject().toString(), oldTask, task, contents);
        model.addAttribute("task", task);
        return "redirect:" + adminPath + "/project/task/index";
    }

    @RequestMapping("/consumeTime")
    public String consumeTime(Integer taskId, Model model) {
        ProjectTask task = taskService.findTaskById(taskId);
        SystemEffort systemEffort = new SystemEffort();
        systemEffort.setEffortObjectId(taskId);
        systemEffort.setEffortObjectType("task");
        List<SystemEffort> systemEffortList = effortService.findSystemEffortList(systemEffort);
        model.addAttribute("task", task);
        model.addAttribute("systemEffortList", systemEffortList);
        return "project/modal/task/note/consumeTime";
    }

    /**
     * 时间消耗
     *
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/consumeTime", method = RequestMethod.POST)
    public String consumeTimeSave(EffortList effortList, Integer taskId) {
        List<SystemEffort> list = effortList.getList();
        ProjectTask task = taskService.findTaskById(taskId);
        for (int i = 0; i < list.size(); i++) {
            SystemEffort systemEffort = list.get(i);
            if (systemEffort.getEffortLeft() == null || systemEffort.getEffortConsumed() == null) {
                list.remove(systemEffort);
                i--;
            } else {
                systemEffort.setEffortObjectType("task");
                systemEffort.setEffortObjectId(taskId);
                systemEffort.setEffortAccount(userUtils.getUserAccount());
                systemEffort.setEffortProject(task.getTaskProject());
            }
        }
        if (!CollectionUtil.isEmpty(list)) {
            SystemEffort systemEffort = list.get(list.size() - 1);
            task.setTaskLeft(systemEffort.getEffortLeft());
            task.setTaskConsumed(systemEffort.getEffortConsumed());
            task.setTaskId(taskId);
            taskService.updateTask(task);
            burnService.updateBurnByProjectId(task.getTaskProject());
        }
        effortService.batchEffortSave(list);
        return "redirect:" + adminPath + "/project/task/index";
    }

    /**
     * 指派
     *
     * @param taskId
     * @param model
     * @return
     */
    @RequiresPermissions("pro-task-call")
    @RequestMapping(value = "/call", method = RequestMethod.GET)
    public String call(Integer taskId, Model model) {
        ProjectTask task = taskService.findTaskById(taskId);
        model.addAttribute("teamList", userService.findTeamUserListByProjectId(task.getTaskProject()));
        model.addAttribute("task", task);
        return "project/operate/task/special/call";
    }

    /**
     * 保存指派
     *
     * @param task
     * @param comment
     * @return
     */
    @RequiresPermissions("pro-task-call")
    @RequestMapping(value = "/call", method = RequestMethod.POST)
    public String callSave(ProjectTask task, String comment) {
        ProjectTask oldTask = taskService.findTaskById(task.getTaskId());
        taskService.updateCallTask(task);
        if (!task.getTaskLeft().equals(oldTask.getTaskLeft())) {
            burnService.updateBurnByProjectId(oldTask.getTaskProject());
        }
        LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.ASSIGNED, task.getTaskId().toString(), userUtils.getUserId(),
                null, oldTask.getTaskProject().toString(), oldTask, task, comment);
        return "redirect:" + adminPath + "/project/task/index";
    }

    @RequiresPermissions("pro-task-finish")
    @RequestMapping(value = "/finish", method = RequestMethod.GET)
    public String finish(Integer taskId, Model model, HttpServletRequest request) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, projectOperate.COOKIE_PROJECT_ID));
        ProjectTask task = taskService.findTaskById(taskId);
        model.addAttribute("task", task);
        model.addAttribute("teamList", userService.findTeamUserListByProjectId(projectId));
        return "project/operate/task/special/finish";
    }

    @RequiresPermissions("pro-task-finish")
    @RequestMapping(value = "/finish", method = RequestMethod.POST)
    public String finishSave(ProjectTask task, String comment) {
        ProjectTask oldTask = taskService.findTaskById(task.getTaskId());
        task.setTaskFinishedBy(userUtils.getUserId());
        task.setTaskFinishedDate(new Date());
        task.setTaskStatus(ProjectTask.DONE);
        taskService.updateFinishTask(task);
        if (oldTask.getTaskStory() != null) {
            ProjectTask task1 = new ProjectTask();
            task1.setTaskStory(oldTask.getTaskStory());
            List<ProjectTask> tasks = taskService.findListTask(task1);
            boolean isDone = true;
            for (ProjectTask projectTask : tasks) {
                if (Integer.parseInt(projectTask.getTaskStatus()) % 3 != 0 && Integer.parseInt(projectTask.getTaskStatus()) != 5) {
                    isDone = false;
                }
            }
            if (isDone) {
                ProductStory story = storyService.findStory(task.getTaskStory());
                story.setStoryStage(ProductStory.STAGE_IS_DONE);
                storyService.updateStory(story);
            }
        }
        if (!oldTask.getTaskConsumed().equals(task.getTaskConsumed())) {
            burnService.updateBurnByProjectId(oldTask.getTaskProject());
        }
        LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.ASSIGNED, task.getTaskId().toString(),
                userUtils.getUserId(),
                null, oldTask.getTaskProject().toString(),
                oldTask, task, comment);
        return "redirect:" + adminPath + "/project/task/index";
    }


    @RequiresPermissions("pro-task-close")
    @RequestMapping(value = "/close", method = RequestMethod.GET)
    public String close(Integer taskId, Model model) {
        ProjectTask task = taskService.findTaskById(taskId);
        model.addAttribute("task", task);
        return "project/operate/task/special/close";
    }

    @RequiresPermissions("pro-task-close")
    @RequestMapping(value = "/close", method = RequestMethod.POST)
    public String closeSave(ProjectTask task, String content) {
        task.setTaskCloseDate(new Date());
        task.setTaskClosedBy(userUtils.getUserId());
        taskService.updateCloseTask(task);
        LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.CLOSED, task.getTaskId().toString(),
                userUtils.getUserId(), null, taskService.findTaskById(task.getTaskId()).getTaskProject().toString(),
                null, null, content);
        return "project/index/task/index.page";
    }

    @RequiresPermissions("pro-task-start")
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String start(Integer taskId, Model model) {
        ProjectTask task = taskService.findTaskById(taskId);
        model.addAttribute("task", task);
        return "project/operate/task/special/start";
    }

    @RequiresPermissions("pro-task-start")
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public String startSave(ProjectTask task, String content) {
        ProjectTask projectTask = taskService.findTaskById(task.getTaskId());
        task.setTaskOpenBy(userUtils.getUserId());
        task.setTaskOpenedDate(new Date());
        taskService.updateStartTask(task);
        LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.STARTED, task.getTaskId().toString(),
                userUtils.getUserId(), null, projectTask.getTaskProject().toString(),
                taskService.findTaskById(task.getTaskId()), task, content);
        if (!projectTask.getTaskConsumed().equals(task.getTaskConsumed()) ||
                !projectTask.getTaskLeft().equals(task.getTaskLeft())) {
            burnService.updateBurnByProjectId(projectTask.getTaskProject());
        }
        return "redirect:" + adminPath + "/project/task/index";
    }

    @RequestMapping("/cancel")
    public String cancel(Integer taskId, Model model) {
        ProjectTask task = taskService.findTaskById(taskId);
        model.addAttribute("task", task);
        return "project/operate/task/special/cancel";
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public String cancelSave(ProjectTask task, String content) {
        task.setTaskCanceledBy(userUtils.getUserId());
        task.setTaskCanceledDate(new Date());
        ProjectTask old = taskService.findTaskById(task.getTaskId());
        taskService.updateCancelTask(task);
        LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.CANCELED, task.getTaskId().toString(),
                userUtils.getUserId(), null, old.getTaskProject().toString(),
                old, task, content);
        return "redirect:" + adminPath + "/project/task/index";
    }

    @RequestMapping("/findList")
    public String findList(Integer projectId, Model model) {
        if (projectId != null) {
            ProjectTask task = new ProjectTask();
            task.setTaskProject(projectId);
            List<ProjectTask> list = taskService.findListTask(task);
            model.addAttribute("tasksList", list);
        }
        return "project/data/task/datalist.pagelet";
    }

    @RequestMapping("/findPager")
    public String findPager(
            Integer start, Integer limit, String statu, String choose, Model model,
            HttpServletRequest request, HttpServletResponse response, String moduleId,
            @RequestParam(required = false, defaultValue = "task_id") String order,
            @RequestParam(defaultValue = "desc")String orderType, Integer key) {
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return redirectProjectForm();
        }
        boolean asc = false;
        if ("desc".equals(orderType)) {
            asc = true;
        }
        ProjectTask task = new ProjectTask();
        task.setTaskNo(key);
        task.setTaskProject(projectId);
        String moduleIds = "";
        if (!StringUtil.isBlank(moduleId)) {
            if (moduleId.contains("p")) {
                moduleIds = ModuleUtil.getConditionByRoot(Integer.parseInt(moduleId.substring(1)), "story");
            } else {
                moduleIds = ModuleUtil.getCondition(Integer.parseInt(moduleId));
            }
        }
        //默认显示未关闭任务
        if (statu == null && choose == null) {
            statu = "0";
        }

        String condition = TaskStatusUtil.getCondition(statu, choose, userUtils.getUserId(), moduleIds);
        Pager<ProjectTask> taskPager;
        if (condition.equals("completeByMe")) {
            OrgUser user = userUtils.getUser();
            taskPager = taskService.findPagerTaskByMe(start, limit, task, order, asc, user);
        } else {
            taskPager = taskService.findTaskPager(start, limit, task, order, asc, condition);
        }
        model.addAttribute("taskPager", taskPager);
        model.addAttribute("statu", statu);
        model.addAttribute("choose", choose);
        return "project/data/task/datalist.pagelet";
    }


    @RequiresPermissions(value = {"pro-task-batchadd", "distribute-task"}, logical = Logical.OR)
    @RequestMapping("/batchAdd")
    public String batchAddForm(Integer storyId, Model model, HttpServletRequest request, HttpServletResponse response, Integer moduleId) {
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return redirectProjectForm();
        }
        model.addAttribute("moduleList", findModuleList(storyId, projectId));
        model.addAttribute("moduleId", moduleId);
        model.addAttribute("teamList", userService.findTeamUserListByProjectId(projectId));
        ProductStory story = productStoryService.findStory(storyId);
        model.addAttribute("story", story);
        return "project/operate/task/common/batchAdd.page";
    }

    @RequestMapping(value = "/batchAdd", method = RequestMethod.POST)
    public String batchAddSave(Tasks tasks, HttpServletRequest request) {
        List<ProjectTask> taskList = tasks.getTaskList();
        for (int i = 0; i < taskList.size(); i++) {
            if (StringUtil.isBlank(taskList.get(i).getTaskName()) || null == (taskList.get(i).getTaskEstimate())) {
                taskList.remove(i);
                i--;
            } else {
                taskList.get(i).setTaskLeft(taskList.get(i).getTaskEstimate());
                taskList.get(i).setTaskConsumed(0f);
            }
        }
        if (taskList.isEmpty()) {
            return "project/index/task/index.page";
        } else {
            Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, projectOperate.COOKIE_PROJECT_ID));
            taskService.batchAddTask(taskList, projectId);
            return "project/index/task/index.page";
        }
    }

    @RequestMapping("/findTask")
    public String findTask(Model model, Integer taskId, HttpServletRequest request, Integer no) {
        if (no != null) {
            Integer currentProjectId = projectOperate.getCurrentProjectId(request);
            if (currentProjectId != null) {
                ProjectTask projectTask = new ProjectTask();
                projectTask.setTaskNo(no);
                projectTask.setTaskProject(currentProjectId);
                List<ProjectTask> listTask = taskService.findListTask(projectTask);
                if (!CollectionUtil.isEmpty(listTask)) {
                    model.addAttribute("task", listTask.get(0));
                } else {
                    return notFoundView();
                }
            }
        } else {
            ProjectTask task = taskService.findTaskById(taskId);
            model.addAttribute("task", task);
        }
        SystemProfile systemProfile = new SystemProfile();
        systemProfile.setFileObjectId(taskId);
        systemProfile.setFileObjectType(ProfileType.TASK.getType());
        List<SystemProfile> profileList = profileService.findSystemProfile(systemProfile);
        model.addAttribute("profileList", profileList);

        return "project/view/info/task/view";
    }

    /**
     * 任务编辑
     *
     * @param taskId
     * @param model
     * @return
     */
    @RequestMapping("/basicInfoEdit")
    public String basicInfoEdit(Integer taskId, Model model) {
        ProjectTask task = taskService.findTaskById(taskId);
        String projectName = projectService.findProjectById(task.getTaskProject()).getProjectName();

        List<ProductStory> storyList = projectStoryService.findStoryByProject(task.getTaskProject());
        model.addAttribute("task", task);
        model.addAttribute("projectName", projectName);
        model.addAttribute("teamList", userService.findTeamUserListByProjectId(task.getTaskProject()));
        model.addAttribute("moduleList", generateModuleList(task.getTaskProject()));
        model.addAttribute("storyList", storyList);
        return "project/edit/rightinfo/task/basicInfoEdit.pagelet";
    }

    /**
     * 任务基本信息
     *
     * @param taskId
     * @param model
     * @return
     */
    @RequestMapping("/basicInformation")
    public String basicInformation(Integer taskId, Model model) {
        ProjectTask task = taskService.findTaskById(taskId);
        Project project = projectService.findProjectById(task.getTaskProject());
        model.addAttribute("task", task);
        model.addAttribute("project", project);

        //查询所属模块string
        String modulePath;
        if (task.getTaskModule() == null) {
            modulePath = "/";
        } else {
            modulePath = ModuleUtil.getPath(task.getTaskModule(), " > ", task.getTaskProject().toString(), true);
        }
        model.addAttribute("modulPath", modulePath);
        //查询相关需求名字
        ProductStory productStory = productStoryService.findStory(task.getTaskStory());
        String storyTitle = productStory == null ? "" : productStory.getStoryTitle();
        model.addAttribute("storyTitle", storyTitle);
        return "project/view/rightinfo/task/basicInformation.pagelet";
    }


    @RequiresPermissions("gantt")
    @RequestMapping("/gantt")
    public String gantt(Model model, String choose) {
        model.addAttribute("choose", choose);
        return "project/operate/task/special/gantt.page";
    }

    @ResponseBody
    @RequestMapping("/gantt/init")
    public List<Map<String, Object>> ganttInit(HttpServletRequest request, HttpServletResponse response) {
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return null;
        }
        Project project = projectOperate.getProject(projectId.toString());
        ProjectTask task = new ProjectTask();
        task.setTaskProject(projectId);
        List<ProjectTask> taskList = taskService.findListTask(task);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

        for (ProjectTask t : taskList) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pID", t.getTaskId());
            map.put("pName", StringUtil.abbreviate(t.getTaskName(), 10));
            if (t.getTaskRealStarted() != null) {
                map.put("pStart", format.format(t.getTaskRealStarted()));
            } else if (t.getTaskEstStared() != null) {
                map.put("pStart", format.format(t.getTaskEstStared()));
            } else {
                map.put("pStart", format.format(project.getProjectBegin()));
            }

            if (t.getTaskDeadLine() != null) {
                map.put("pEnd", format.format(t.getTaskDeadLine()));
            } else {
                map.put("pEnd", format.format(project.getProjectEnd()));
            }

            map.put("pColor", t.getTaskPri());
            OrgUser user = userService.findUser(t.getTaskAssignedTo());
            if (user != null) {
                map.put("pRes", user.getOrgUserRealName());
            }
            //进度
            float comp;
            if ((t.getTaskConsumed() == null || t.getTaskLeft() == null) || (t.getTaskConsumed() + t.getTaskLeft() == 0)) {
                comp = 0f;
            } else {
                comp = t.getTaskConsumed() / (t.getTaskConsumed() + t.getTaskLeft());
            }
            map.put("pComp", String.format("%.2f",comp * 100));
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
        ProjectTask task = taskService.findTaskById(id);

        SystemProfile systemProfile = new SystemProfile();
        systemProfile.setFileObjectId(id);
        systemProfile.setFileObjectType(ProfileType.TASK.getType());
        List<SystemProfile> fileList = profileService.findSystemProfile(systemProfile);
        model.addAttribute("fileList", fileList);

        model.addAttribute("task", task);
        return "project/data/task/gantt/ganttFind.pagelet";
    }

    @RequestMapping("/board")
    public String board(Model model, HttpServletRequest request, HttpServletResponse response) {
        Integer projectId = projectOperate.getCurrentProjectId(request, response);

        ProjectTask projectTask = new ProjectTask();
        projectTask.setTaskProject(projectId);

        projectTask.setTaskStatus(ProjectTask.WAIT);
        List<ProjectTask> resList = taskService.findListTask(projectTask);
        model.addAttribute("waitList", resList);

        projectTask.setTaskStatus(ProjectTask.DOING);
        resList = taskService.findListTask(projectTask);
        model.addAttribute("doingList", resList);

        projectTask.setTaskStatus(ProjectTask.DONE);
        resList = taskService.findListTask(projectTask);
        model.addAttribute("doneList", resList);

        projectTask.setTaskStatus(ProjectTask.CANCEL);
        resList = taskService.findListTask(projectTask);
        model.addAttribute("cancelList", resList);

        projectTask.setTaskStatus(ProjectTask.CLOSE);
        resList = taskService.findListTask(projectTask);
        model.addAttribute("closeList", resList);

        return "project/operate/task/special/board";
    }

    @RequestMapping("/modal/{forward}")
    public String doc(@PathVariable(value = "forward") String forward, Model model, Integer taskId) {
        ProjectTask task = taskService.findTaskById(taskId);
        model.addAttribute("teamList", userService.findTeamUserListByProjectId(task.getTaskProject()));
        model.addAttribute("task", task);
        return "project/modal/task/" + forward + ".pagelet";
    }

    @RequiresPermissions("task-group")
    @RequestMapping("/grouping")
    public String grouping(HttpServletRequest request, HttpServletResponse response,
                           String type, String menuId, Model model) {
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return redirectProjectForm();
        }

        Map<String, List<ProjectTask>> map = taskService.findGroup(DictUtil.getValue("groupType", type), projectId);
        Map<String, List<ProjectTask>> mapDocument = new HashMap<String, List<ProjectTask>>();
        if (type.equals("1")) {
            for (String key : map.keySet()) {
                if (key.isEmpty()) {
                    mapDocument.put("未关联需求", map.get(key));
                } else {
                    ProductStory story = productStoryService.findStory(Integer.valueOf(key));
                    if (story != null) {
                        String title = story.getStoryTitle();
                        mapDocument.put(title, map.get(key));
                    }
                }
            }
        }
        model.addAttribute("mapDocument", mapDocument);
        model.addAttribute("map", map);
        model.addAttribute("type", type);
        model.addAttribute("menuId", menuId);
        return "project/operate/task/special/grouping";
    }

    /**
     * 改变任务状态 用于Ajax请求
     *
     * @param task
     * @param content    备注内容
     * @param taskStatus 改变的状态
     * @return
     */
    @ResponseBody
    @RequestMapping("/changeStatus")
    public Map<String, String> changeStatus(ProjectTask task, String content, String taskStatus) {
        Map<String, String> map;
        task.setTaskLastEditedBy(userUtils.getUserId());
        Integer res;
        LogUtil.LogAction logAction;
        if ("doing".equals(taskStatus)) {
            res = taskService.updateDoingTask(task);
            logAction = LogUtil.LogAction.ACTIVATED;
            map = generateResultMap(res, "激活成功", "激活失败");
        } else if ("close".equals(taskStatus)) {
            res = taskService.updateCloseTask(task);
            map = generateResultMap(res, "关闭成功", "关闭失败");
            logAction = LogUtil.LogAction.CLOSED;
        } else if ("cancel".equals(taskStatus)) {
            res = taskService.updateCancelTask(task);
            logAction = LogUtil.LogAction.CANCELED;
            map = generateResultMap(res, "取消成功", "取消失败");
        } else if ("finish".equals(taskStatus)) {
            res = taskService.updateFinishTask(task);
            logAction = LogUtil.LogAction.FINISHED;
            map = generateResultMap(res, "完成成功", "完成失败");
        } else if ("start".equals(taskStatus)) {
            res = taskService.updateStartTask(task);
            logAction = LogUtil.LogAction.STARTED;
            map = generateResultMap(res, "开始成功", "开始失败");
        } else {
            res = taskService.updateTask(task);
            logAction = LogUtil.LogAction.EDITED;
            map = generateResultMap(res, "编辑成功", "编辑失败");
        }
        LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, logAction, task.getTaskId().toString(),
                userUtils.getUserId(), null, taskService.findTaskById(task.getTaskId()).getTaskProject().toString(),
                taskService.findTaskById(task.getTaskId()), task, content);
        return map;
    }

    /**
     * 报表
     *
     * @return
     */
    @RequiresPermissions("pro-task-report")
    @RequestMapping("/reportform")
    public String reportForm() {
        return "project/operate/task/common/reportform";
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
        return "project/data/task/report/reportFormDate.pagelet";
    }

    private Map<String, String> generateResultMap(Integer res, String successMsg, String falseMsg) {
        if (res > 0) {
            return resultMap(true, successMsg);
        } else {
            return resultMap(false, falseMsg);
        }
    }

    private List<SystemModule> generateModuleList(Integer projectId) {
        SystemModule module = new SystemModule();
        module.setModuleType("task");
        module.setModuleRoot(projectId);
        List<SystemModule> moduleList = moduleService.findModuleList(module);
        for (SystemModule m : moduleList) {
            m.setModuleName(ModuleUtil.getPath(m.getModuleId(), "/", "", false));
        }
        List<ProjectProduct> projectProductList = projectProductService.findProductListByProjectId(projectId);
        for (ProjectProduct pp : projectProductList) {
            module.setModuleType("story");
            module.setModuleRoot(pp.getProductId());
            List<SystemModule> tModuleList = moduleService.findModuleList(module);
            for (SystemModule m : tModuleList) {
                m.setModuleName(ModuleUtil.getPath(m.getModuleId(), "/", "", false));
            }
            moduleList.addAll(tModuleList);
        }
        return moduleList;
    }

    private List<SystemModule> findModuleList(Integer storyId, Integer projectId) {
        ProductStory productStory = storyService.findStory(storyId);
        if (productStory != null) {
            SystemModule systemModule = new SystemModule();
            systemModule.setModuleRoot(productStory.getProductId());
            List<SystemModule> moduleList = moduleService.findModuleList(systemModule);
            for (SystemModule module : moduleList) {
                module.setModuleName(ModuleUtil.getPath(module.getModuleId(), "/", null, false));
            }
            return moduleList;
        } else {
            return generateModuleList(projectId);
        }
    }

}
