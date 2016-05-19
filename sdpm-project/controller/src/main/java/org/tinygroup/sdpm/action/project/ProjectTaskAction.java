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
import org.tinygroup.logger.LogLevel;
import org.tinygroup.sdpm.action.project.util.TaskStatusUtil;
import org.tinygroup.sdpm.common.util.DateUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.dao.complexsearch.SearchInfo;
import org.tinygroup.sdpm.dao.complexsearch.SearchInfos;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.dto.UploadProfile;
import org.tinygroup.sdpm.dto.project.EffortList;
import org.tinygroup.sdpm.dto.project.Tasks;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.sdpm.product.dao.utils.FieldUtil;
import org.tinygroup.sdpm.product.service.inter.ProductService;
import org.tinygroup.sdpm.product.service.inter.StoryService;
import org.tinygroup.sdpm.product.service.inter.StorySpecService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.service.inter.*;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.quality.service.inter.BugService;
import org.tinygroup.sdpm.system.dao.pojo.*;
import org.tinygroup.sdpm.system.service.inter.ActionService;
import org.tinygroup.sdpm.system.service.inter.EffortService;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
import org.tinygroup.sdpm.util.*;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 处理任务的控制器
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
    private ProjectProductService projectProductService;
    @Autowired
    private BurnService burnService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ActionService actionService;
    @Autowired
    private StorySpecService storySpecService;
    @Autowired
    private BugService bugService;

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
        List<OrgUser> teamUserList = userService.findTeamUserListByProjectId(Integer.valueOf(currentProjectId));
        model.addAttribute("teamUserList", teamUserList);

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
    public String form(HttpServletRequest request, HttpServletResponse response, Model model,
                       Integer storyId,
                       String taskId,
                       String moduleId) {
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return redirectProjectForm();
        }
        model.addAttribute("teamList", userService.findTeamUserListByProjectId(projectId));

        if (taskId != null) {
            ProjectTask task = taskService.findTaskById(Integer.parseInt(taskId));
            model.addAttribute("copyTask", task);
        }
        List<ProductStory> storyList = projectStoryService.findStoryByProject(projectId);
//        List<QualityBug> bugList = projectService.findRelationBugByProjectID(projectId);
        QualityBug qualityBug=new QualityBug();
        qualityBug.setProjectId(projectId);
        qualityBug.setDeleted(0);
        qualityBug.setBugStatus(QualityBug.STATUS_ACTIVE);
        List<QualityBug> bugList=bugService.findBugList(qualityBug);
        model.addAttribute("moduleList", findModuleList(storyId, projectId));
        model.addAttribute("moduleId", moduleId);
        model.addAttribute("storyId", storyId);
        ProductStory story = storyService.findStory(storyId);

        model.addAttribute("story", story);
        model.addAttribute("storyList", storyList);
        model.addAttribute("bugList", bugList);

        model.addAttribute("currentURL",request.getRequestURL().toString());
        model.addAttribute("projectId",projectId);
        return "project/operate/task/common/add.page";
    }


    /**
     * 复制任务表单
     *
     * @param request
     * @param model
     * @param storyId
     * @param taskId
     * @return
     */
    @RequiresPermissions(value = {"pro-task-proposeversion", "pro-Info2-copy", "batch-distribute-task"}, logical = Logical.OR)
    @RequestMapping("/newcopy")
    public String newCopy(HttpServletRequest request, HttpServletResponse response, Model model,
                          Integer storyId,
                          String taskId,
                          String moduleId) {

        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return redirectProjectForm();
        }
        model.addAttribute("teamList", userService.findTeamUserListByProjectId(projectId));

        if (taskId != null) {
            ProjectTask task = taskService.findTaskById(Integer.parseInt(taskId));
            model.addAttribute("task", task);

        }
        List<ProductStory> storyList = projectStoryService.findStoryByProject(projectId);
        List<QualityBug> bugList = projectService.findRelationBugByProjectID(projectId);
        model.addAttribute("moduleList", findModuleList(storyId, projectId));
        model.addAttribute("moduleId", moduleId);
        model.addAttribute("storyId", storyId);
        ProductStory story = storyService.findStory(storyId);

        model.addAttribute("story", story);

        model.addAttribute("storyList", storyList);
        model.addAttribute("bugList", bugList);
        return "project/operate/task/common/copy.page";

    }

    /**
     * 新增任务保存
     *
     * @param task
     * @param taskMailToArray
     * @param request
     * @param comment
     * @return
     */
    @RequiresPermissions(value = {"pro-task-proposeversion", "pro-Info2-copy", "batch-distribute-task"}, logical = Logical.OR)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(ProjectTask task, Integer direction,
                       String[] taskMailToArray, HttpServletRequest request, String comment,
                       UploadProfile uploadProfile) throws IOException {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, ProjectOperate.COOKIE_PROJECT_ID));
        task.setTaskProject(projectId);
        String taskMailTo = StringUtil.join(taskMailToArray, ",");
        task.setTaskMailto(taskMailTo);
        task.setTaskLeft(task.getTaskEstimate());
        task = taskService.addTask(task);
        storyJudge(task.getTaskStory());
        LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.CREATED,
                task.getTaskId().toString(), userUtils.getUserId(),
                null, task.getTaskProject().toString(), null, null, comment);
        try {
            processProfile(uploadProfile, task.getTaskId(), ProfileType.TASK);
        } catch (IOException e) {
            logger.logMessage(LogLevel.ERROR, "上传文件文件出错，请求路径{}", e, request.getRequestURI());
        }
        switch (direction) {
            case 1:
                return "redirect:" + adminPath + "/project/task/form";
            case 2:
                return "redirect:" + adminPath + "/project/task/index";
            case 3:
                return "redirect:" + adminPath + "/project/demand/index";
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
    public String editForm(Integer taskId, Model model,SystemAction action,HttpServletRequest request,
                           @CookieValue(value=ProjectOperate.COOKIE_PROJECT_ID)String currentProjectId) {
        ProjectTask task = taskService.findTaskById(taskId);
        if(task.getTaskProject()!=Integer.parseInt(currentProjectId))
        {
            return "redirect:"+adminPath+"/project/task/index";
        }

        model.addAttribute("task", task);

        //读取备注信息
        String actionComment=getTaskRemark(task);
        model.addAttribute("actionComment",actionComment);

        //读取文档信息
        SystemProfile systemProfile = new SystemProfile();
        systemProfile.setFileObjectId(taskId);
        systemProfile.setFileObjectType(ProfileType.TASK.getType());
        List<SystemProfile> fileList = profileService.findSystemProfile(systemProfile);
        model.addAttribute("fileList", fileList);

        model.addAttribute("currentURL",request.getRequestURL().toString());
        model.addAttribute("projectId",task.getTaskProject());

        return "project/operate/task/common/edit";
    }

    /**
     * 读取备注信息
     */
    private String getTaskRemark(ProjectTask projectTask)
    {
        SystemAction systemAction=new SystemAction();
        systemAction.setActionObjectId(projectTask.getTaskId().toString());
        systemAction.setActionObjectType("task");
        List<SystemAction> actions = actionService.findAction(systemAction, "actionId", false);//false表示倒序
        if(actions.size()==0)
        {
            return "";
        }
        return actions.get(0).getActionComment();//0表示降序排列后的第一条，即为最新那一条
    }

    /**
     * 编辑保存
     *
     * @param task
     * @param model
     * @param comment
     * @return
     */
    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String editSave(ProjectTask task, Model model, String comment,
                           UploadProfile uploadProfile) throws IOException {
        ProjectTask oldTask = taskService.findTaskById(task.getTaskId());
        task.setTaskLastEditedBy(userUtils.getUserId());
        task.setTaskLastEditedDate(new Date());
        if(task.getTaskModule()==null)
        {
            task.setTaskModule(0);
        }
        if(task.getTaskStory()==null)
        {
            task.setTaskStory(0);
        }
        taskService.updateEditTask(task);
        storyJudge(task.getTaskStory());
        processProfile(uploadProfile, task.getTaskId(), ProfileType.TASK);

        LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.EDITED, oldTask.getTaskId().toString(),
                userUtils.getUserId(), null, oldTask.getTaskProject().toString(), oldTask, task, comment);
        model.addAttribute("task", task);
        return "redirect:" + adminPath + "/project/task/index";
    }

    /**
     * 删除任务
     *
     * @param task
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public Map delete(ProjectTask task) {
        task = taskService.findTaskById(task.getTaskId());
        taskService.deleteTask(task.getTaskId());
        storyJudge(task.getTaskId());
        LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.DELETED, task.getTaskId().toString(),
                userUtils.getUserId(), null, task.getTaskProject().toString(), null, null, null);
        return resultMap(true, "删除成功");
    }

    @ResponseBody
    @RequestMapping("batch/storyByModule")
    public List<ProductStory> ajaxStoryByModule(Integer projectId, Integer moduleId) {
        ProductStory story = new ProductStory();
        if (moduleId != null) {
            SystemModule module = moduleService.findById(moduleId);
            if ("projectProduct".equals(module.getModuleType())) {
                story.setProductId(Integer.parseInt(module.getModuleOwner()));
            } else {
                story.setModuleId(moduleId);
            }
        }
        return projectStoryService.findStoryByProjectAndModule(projectId, story);
    }

    @RequestMapping("/consumeTime")
    public String consumeTime(Integer taskId, Model model,@CookieValue(value=ProjectOperate.COOKIE_PROJECT_ID)String currentProjectId) {
        ProjectTask task = taskService.findTaskById(taskId);

        if(task.getTaskProject()!=Integer.parseInt(currentProjectId))
        {
            return "redirect:"+adminPath+"/project/task/index";
        }

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
        Float taskConsumed = task.getTaskConsumed();
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
                Float effortConsumed = systemEffort.getEffortConsumed();
                if (effortConsumed != null) {
                    taskConsumed += effortConsumed;
                }
            }
        }
        if (!CollectionUtil.isEmpty(list)) {
            SystemEffort systemEffort = list.get(list.size() - 1);
            task.setTaskLeft(systemEffort.getEffortLeft());
            task.setTaskConsumed(systemEffort.getEffortConsumed());
            task.setTaskId(taskId);
            task.setTaskConsumed(taskConsumed);
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
    public String call(Integer taskId, Model model,@CookieValue(value=ProjectOperate.COOKIE_PROJECT_ID)String currentProjectId) {
        ProjectTask task = taskService.findTaskById(taskId);

        if(task.getTaskProject()!=Integer.parseInt(currentProjectId))
        {
            return "redirect:"+adminPath+"/project/task/index";
        }

        //读取备注信息
        String actionComment=getTaskRemark(task);
        model.addAttribute("actionComment",actionComment);

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

    @ResponseBody
    @RequiresPermissions("pro-task-call")
    @RequestMapping(value = "batch/call")
    public Map callSave(Integer[] itemId, String userId) {
        if (ArrayUtil.isEmptyArray(itemId)) {
            return resultMap(false, "请选择指派人");
        } else {
            for (Integer id : itemId) {
                ProjectTask task = new ProjectTask();
                task.setTaskId(id);
                task.setTaskAssignedTo(userId);
                task.setTaskAssignedDate(new Date());
                taskService.updateCallTask(task);
                LogUtil.log(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.ASSIGNED, task.getTaskId().toString(), userUtils.getUserId());
            }
            return resultMap(true, "指派成功");
        }
    }

    @RequiresPermissions("pro-task-finish")
    @RequestMapping(value = "/finish", method = RequestMethod.GET)
    public String finish(Integer taskId, Model model, HttpServletRequest request,@CookieValue(value=ProjectOperate.COOKIE_PROJECT_ID)String currentProjectId) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, projectOperate.COOKIE_PROJECT_ID));
        ProjectTask task = taskService.findTaskById(taskId);

        if(task.getTaskProject()!=Integer.parseInt(currentProjectId))
        {
            return "redirect:"+adminPath+"/project/task/index";
        }

        String actionComment=getTaskRemark(task);
        model.addAttribute("actionComment",actionComment);
        model.addAttribute("task", task);
        model.addAttribute("teamList", userService.findTeamUserListByProjectId(projectId));

        //读取备注信息
        String actionCommentt=getTaskRemark(task);
        model.addAttribute("actionComment",actionCommentt);
        return "project/operate/task/special/finish";
    }

    @RequiresPermissions("pro-task-finish")
    @RequestMapping(value = "/finish", method = RequestMethod.POST)
    public String finishSave(ProjectTask task, String comment, UploadProfile uploadProfile) throws IOException {
        ProjectTask oldTask = taskService.findTaskById(task.getTaskId());
        task.setTaskFinishedBy(userUtils.getUserId());
        task.setTaskFinishedDate(task.getTaskFinishedDate());
        task.setTaskStatus(ProjectTask.DONE);
        Float oldConsumed = oldTask.getTaskConsumed();
        Float newConsumed = task.getTaskConsumed() - oldConsumed;
        if (!newConsumed.equals(0f)) {
            SystemEffort systemEffort = new SystemEffort();
            systemEffort.setEffortObjectType("task");
            systemEffort.setEffortObjectId(oldTask.getTaskId());
            systemEffort.setEffortAccount(userUtils.getUserAccount());
            systemEffort.setEffortProject(task.getTaskProject());
            systemEffort.setEffortConsumed(newConsumed);
            systemEffort.setEffortLeft(0f);
            systemEffort.setEffortWork("完成任务：" + oldTask.getTaskName());
            systemEffort.setEffortDate(new Date());
            systemEffort.setEffortBegin(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
            systemEffort.setEffortEnd(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
            effortService.saveSystemEffort(systemEffort);
        }
        taskService.updateFinishTask(task);
        storyJudge(task.getTaskStory());
        processProfile(uploadProfile, task.getTaskId(), ProfileType.TASK);

        if (oldTask.getTaskStory() != null) {
            ProjectTask task1 = new ProjectTask();
            task1.setTaskStory(oldTask.getTaskStory());
            List<ProjectTask> tasks = taskService.findListTask(task1);//查找关联的需求是否已经全部完成
            boolean isDone = true;
            for (ProjectTask projectTask : tasks) {
                if (Integer.parseInt(projectTask.getTaskStatus()) % 3 != 0 && Integer.parseInt(projectTask.getTaskStatus()) != 5) {
                    isDone = false;
                }
            }
            if (isDone) {
                ProductStory story = storyService.findStory(oldTask.getTaskStory());
                if (story != null) {
                    story.setStoryStage(ProductStory.STAGE_IS_DONE);
                    storyService.updateStory(story);
                }
            }
        }
        if (!oldTask.getTaskConsumed().equals(task.getTaskConsumed())) {
            burnService.updateBurnByProjectId(oldTask.getTaskProject());
        }
        LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.FINISHED, task.getTaskId().toString(),
                userUtils.getUserId(),
                null, oldTask.getTaskProject().toString(),
                oldTask, task, comment);
        return "redirect:" + adminPath + "/project/task/index";
    }


    @RequiresPermissions("pro-task-close")
    @RequestMapping(value = "/close", method = RequestMethod.GET)
    public String close(Integer taskId, Model model,@CookieValue(value=ProjectOperate.COOKIE_PROJECT_ID)String currentProjectId) {
        ProjectTask task = taskService.findTaskById(taskId);

        if(task.getTaskProject()!=Integer.parseInt(currentProjectId))
        {
            return "redirect:"+adminPath+"/project/task/index";
        }

        String actionComment=getTaskRemark(task);
        model.addAttribute("actionComment",actionComment);
        model.addAttribute("task", task);
        return "project/operate/task/special/close";
    }

    @RequiresPermissions("pro-task-close")
    @RequestMapping(value = "/close", method = RequestMethod.POST)
    public String closeSave(ProjectTask task, String comment) {
        task.setTaskCloseDate(new Date());
        task.setTaskClosedBy(userUtils.getUserId());
        taskService.updateCloseTask(task);
        storyJudge(task.getTaskId());
        LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.CLOSED, task.getTaskId().toString(),
                userUtils.getUserId(), null, taskService.findTaskById(task.getTaskId()).getTaskProject().toString(),
                null, null, comment);
        return "project/index/task/index.page";
    }

    @ResponseBody
    @RequiresPermissions("pro-task-close")
    @RequestMapping(value = "/batch/close")
    public Map batchCloseSave(Integer[] itemId) {
        if (ArrayUtil.isEmptyArray(itemId)) {
            return resultMap(false, "请选择要关闭的任务");
        } else {
            for (Integer id : itemId) {
                ProjectTask task = new ProjectTask();
                task.setTaskId(id);
                task.setTaskCloseDate(new Date());
                task.setTaskClosedBy(userUtils.getUserId());
                taskService.updateCloseTask(task);
                LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.CLOSED, task.getTaskId().toString(),
                        userUtils.getUserId(), null, taskService.findTaskById(task.getTaskId()).getTaskProject().toString(),
                        null, null, null);
            }
            return resultMap(true, "关闭成功");
        }
    }

    /**
     * 开始任务
     *
     * @param taskId
     * @param model
     * @return
     */
    @RequiresPermissions("pro-task-start")
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String start(Integer taskId, Model model,@CookieValue(value=ProjectOperate.COOKIE_PROJECT_ID)String currentProjectId) {
        ProjectTask task = taskService.findTaskById(taskId);

        if(task.getTaskProject()!=Integer.parseInt(currentProjectId))
        {
            return "redirect:"+adminPath+"/project/task/index";
        }

        String actionComment=getTaskRemark(task);
        model.addAttribute("actionComment",actionComment);
        model.addAttribute("task", task);
        return "project/operate/task/special/start";
    }

    @RequiresPermissions("pro-task-start")
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public String startSave(ProjectTask task, String comment) {
        ProjectTask projectTask = taskService.findTaskById(task.getTaskId());
        task.setTaskOpenBy(userUtils.getUserId());
        task.setTaskOpenedDate(new Date());
        Float taskEstimate = task.getTaskEstimate();
        if (taskEstimate != null) {
            task.setTaskLeft(taskEstimate);
        }
        taskService.updateStartTask(task);
        LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.STARTED, task.getTaskId().toString(),
                userUtils.getUserId(), null, projectTask.getTaskProject().toString(),
                taskService.findTaskById(task.getTaskId()), task, comment);
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
    public String cancelSave(ProjectTask task, String comment) {
        task.setTaskCanceledBy(userUtils.getUserId());
        task.setTaskCanceledDate(new Date());
        ProjectTask old = taskService.findTaskById(task.getTaskId());
        taskService.updateCancelTask(task);
        LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.CANCELED, task.getTaskId().toString(),
                userUtils.getUserId(), null, old.getTaskProject().toString(),
                old, task, comment);
        return "redirect:" + adminPath + "/project/task/index";
    }

    @RequestMapping("/findList")
    public String findList(Integer projectId, Model model) {
        if (projectId != null) {
            ProjectTask task = new ProjectTask();
            task.setTaskProject(projectId);
            List<ProjectTask> tasksList = taskService.findListTask(task);
            model.addAttribute("tasksList", tasksList);
            projectPagerInfoStatistics(model, tasksList);
        }
        return "project/data/task/datalist.pagelet";
    }

    private void projectPagerInfoStatistics(Model model, List<ProjectTask> tasksList) {
        int totalTaskConsumed = 0, totalLeft = 0, totalEstimate = 0, taskSize = 0;
        if (!CollectionUtil.isEmpty(tasksList)) {
            for (ProjectTask projectTask : tasksList) {
                totalTaskConsumed += projectTask.getTaskConsumed();
                totalLeft += projectTask.getTaskLeft();
                totalEstimate += projectTask.getTaskEstimate();
            }
            taskSize = tasksList.size();
        }
        model.addAttribute("totalTaskConsumed", totalTaskConsumed);
        model.addAttribute("totalLeft", totalLeft);
        model.addAttribute("totalEstimate", totalEstimate);
        model.addAttribute("taskSize", taskSize);
    }

    @RequestMapping("/findPager")
    public String findPager(SearchInfos infos,
                            String groupOperate,
                            Integer start,
                            Integer limit,
                            String statu,
                            String choose,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response,
                            String moduleId,
                            @RequestParam(required = false, defaultValue = "task_no") String order,
                            @RequestParam(defaultValue = "desc") String orderType, Integer key) {
        boolean isSearch = false;
        for (SearchInfo info : infos.getInfos()) {
            if (!StringUtil.isBlank(info.getValue())) {
                isSearch = true;
                break;
            }
        }
        if (isSearch) {
            statu = "";
            choose = "";
        }
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        ConditionCarrier carrier = new ConditionCarrier();
        if (projectId == null) {
            return redirectProjectForm();
        }
        boolean asc = false;
        if ("asc".equals(orderType)) {
            asc = true;
        }
        carrier.putSearch("taskSearch", infos, groupOperate);
        ProjectTask task = new ProjectTask();
        task.setTaskNo(key);
        task.setTaskProject(projectId);
        String moduleIds = "";
        if (!StringUtil.isBlank(moduleId)) {
            if (moduleId.contains("p")) {
                //String newModuleId = moduleId.replaceAll("[a-zA-Z]","" ); //去掉moduleId中的字符p
                moduleIds = ModuleUtil.getConditionByRoot(Integer.parseInt(moduleId.substring(1)), "story");
                SystemModule module = new SystemModule();
//                module.setModuleOwner(moduleId.substring(1));
                module.setModuleType("story");
                module.setModuleRoot(Integer.parseInt(moduleId.substring(1)));
                List<SystemModule> moduleList = moduleService.findModuleList(module);
//                moduleIds = StringUtil.isBlank(moduleIds) ? moduleList.get(0).getModuleId().toString() : moduleIds.substring(1, moduleIds.length() - 1) + "," + moduleList.get(0).getModuleId().toString();

                if(moduleList.size()==0)
                {
                    return "redirect:" + adminPath + "/project/task/index?choose=1";
                }
                String[] strings=new String[moduleList.size()];
                for(int i=0;i<moduleList.size();i++)
                {
                    strings[i]=String.valueOf(moduleList.get(i).getModuleId());
                }
                carrier.putIns("taskModule", strings);
            } else {
//                moduleIds = ModuleUtil.getCondition(Integer.parseInt(moduleId));
                String[] strings={moduleId};
                carrier.putIns("taskModule", strings);
            }
        }
        //默认显示未关闭任务
        if (statu == null && choose == null) {
            statu = "0";
        }

        String condition = TaskStatusUtil.getCondition(statu, choose, userUtils.getUserId(), carrier);
        Pager<ProjectTask> taskPager;
        if (condition.equals("completeByMe")) {
            OrgUser user = userUtils.getUser();
            taskPager = taskService.findPagerTaskByMe(start, limit, task, order, asc, user);
        } else {
            taskPager = taskService.findTaskPager(start, limit, task, order, asc, carrier);
        }
        projectPagerInfoStatistics(model, taskPager.getRecords());

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
        List<ProductStory> storyList = projectStoryService.findStoryByProject(projectId);
        model.addAttribute("moduleList", findModuleList(storyId, projectId));
        model.addAttribute("moduleId", moduleId);
        model.addAttribute("storyList", storyList);
        model.addAttribute("teamList", userService.findTeamUserListByProjectId(projectId));
        ProductStory story = storyService.findStory(storyId);
        model.addAttribute("story", story);
        model.addAttribute("currentURL",request.getRequestURL().toString());
        model.addAttribute("projectId",projectId);
        return "project/operate/task/common/batchAdd.page";
    }

    @RequestMapping(value = "/batchAdd", method = RequestMethod.POST)
    public String batchAddSave(Tasks tasks, HttpServletRequest request)
    {
        List<ProjectTask> taskList = tasks.getTaskList();
        for (int i = 0; i < taskList.size(); i++)
        {
            ProjectTask projectTask = taskList.get(i);
            if (StringUtil.isBlank(projectTask.getTaskName()) || null == (projectTask.getTaskEstimate()))
            {
                taskList.remove(i);
                i--;
            } else
            {
                projectTask.setTaskLeft(projectTask.getTaskEstimate());
                projectTask.setTaskConsumed(0f);
                if (null != projectTask.getTaskStory())
                {
                    ProductStory story = storyService.findStory(projectTask.getTaskStory());
                    if (story != null)
                    {
                        projectTask.setStorySpec(story.getStorySpec());
                    }
                }
            }
            projectTask.setTaskOpenBy(UserUtils.getUserId());
        }

        if (taskList.isEmpty())
        {
            return "project/index/task/index.page";
        } else
        {
            for(ProjectTask task:taskList)
            {
                if(StringUtil.equals(task.getTaskPri(),"1,"))
                {
                    task.setTaskPri("1");
                }
            }
            Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, projectOperate.COOKIE_PROJECT_ID));
            taskService.batchAddTask(taskList, projectId);
            for (ProjectTask task : taskList)
            {
                storyJudge(task.getTaskStory());
            }
            return "project/index/task/index.page";
        }
    }

    @RequestMapping("/findTask")
    public String findTask(Model model, Integer taskId, HttpServletRequest request, Integer no,@CookieValue(value=ProjectOperate.COOKIE_PROJECT_ID)String selectedProjectId) {
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

            if(task.getTaskProject()!=Integer.parseInt(selectedProjectId))
            {
                return "redirect:"+adminPath+"/project/task/index";
            }

            model.addAttribute("task", task);

            //获得相关需求描述
            ProductStory productStory=storyService.findStory(task.getTaskStory());
            ProductStorySpec storySpec=null;
            if(productStory!=null)
            {
                storySpec= storySpecService.findStorySpec(task.getTaskStory(), productStory.getStoryVersion());
            }
            model.addAttribute("storySpec",storySpec);
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
//        String projectName = projectService.findProjectById(task.getTaskProject()).getProjectName();
        List<Project> projectList = projectService.getUserProjectList(userUtils.getUserId());
        List<ProductStory> storyList = projectStoryService.findStoryByProject(task.getTaskProject());
        model.addAttribute("task", task);
        model.addAttribute("projectList", projectList);
//        model.addAttribute("projectName", projectName);
        model.addAttribute("teamList", userService.findTeamUserListByProjectId(task.getTaskProject()));
        model.addAttribute("moduleList", generateModuleList(task.getTaskProject()));
        model.addAttribute("storyList", storyList);
        model.addAttribute("userId",UserUtils.getUserId());
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
            modulePath = ModuleUtil.getPath(task.getTaskModule(), " > ", project.getProjectName(), true);
        }
        model.addAttribute("modulPath", modulePath);
        //查询相关需求名字
        ProductStory productStory = storyService.findStory(task.getTaskStory());
        String storyTitle = productStory == null ? "" : productStory.getStoryTitle();
        model.addAttribute("storyTitle", storyTitle);
        return "project/view/rightinfo/task/basicInformation.pagelet";
    }


    /**
     * 改变任务状态 用于Ajax请求
     *
     * @param task
     * @param comment    备注内容
     * @param taskStatus 改变的状态
     * @return
     */
    @ResponseBody
    @RequestMapping("/changeStatus")
    public Map<String, String> changeStatus(ProjectTask task, String comment, String taskStatus) {
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
                taskService.findTaskById(task.getTaskId()), task, comment);
        return map;
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
            if (CollectionUtil.isEmpty(moduleList)) {
                systemModule = new SystemModule();
                systemModule.setModuleOwner(productStory.getProductId().toString());
                moduleList = moduleService.findModuleList(systemModule);
            }
            return moduleList;
        } else {
            return generateModuleList(projectId);
        }
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
            Product product = productService.findProductWithoutGroupByById(pp.getProductId());
            if (product.getDeleted() == 0) {
                module.setModuleType("story");
                module.setModuleRoot(pp.getProductId());
                List<SystemModule> tModuleList = moduleService.findModuleList(module);
                for (SystemModule m : tModuleList) {
                    m.setModuleName(ModuleUtil.getPath(m.getModuleId(), "/", product.getProductName(), true));
                }
                SystemModule module1 = new SystemModule();
                module1.setModuleType("projectProduct");
                module1.setModuleRoot(pp.getProjectId());
                module1.setModuleOwner(pp.getProductId().toString());
                moduleList.addAll(moduleService.findModuleList(module1));
                moduleList.addAll(tModuleList);
            }
        }
        return moduleList;
    }

    private void storyJudge(Integer storyId) {
        if (storyId != null) {
            ProductStory story = storyService.findStory(storyId);
            if (story == null) {
                return;
            }
            ProjectTask projectTask = new ProjectTask();
            projectTask.setTaskStory(storyId);
            projectTask.setTaskDeleted(FieldUtil.DELETE_NO_S);
            List<ProjectTask> taskList = taskService.findListTask(projectTask);
            boolean isDone = true;
            for (ProjectTask task1 : taskList) {
                if (!ProjectTask.CLOSE.equals(task1.getTaskStatus()) && !ProjectTask.DONE.equals(task1.getTaskStatus())) {
                    isDone = false;
                }
            }
            if (isDone) {
                if (!ProductStory.STAGE_IS_DONE.equals(story.getStoryStage())) {
                    story.setStoryStage(ProductStory.STAGE_IS_DONE);
                    storyService.updateStory(story);
                }
            } else {
                if (!ProductStory.STAGE_IS_DOING.equals(story.getStoryStage())) {
                    story.setStoryStage(ProductStory.STAGE_IS_DOING);
                    storyService.updateStory(story);
                }
            }
        }
    }

    /**
     * 判断任务名称是否已经存在
     */
    @ResponseBody
    @RequestMapping("/judgeTaskNameExist")
    public Map judgeTaskNameExist(String param,String currentURL,Integer projectId,String taskNamee)
    {
        if(StringUtil.isBlank(param))
        {
            return resultMap(false, "请输入任务名称");
        }

        if(StringUtil.contains(currentURL,"edit")&&StringUtil.equals(param,taskNamee))
        {
            return resultMap(true,"");
        }

        String taskName = param;
        ProjectTask projectTask=new ProjectTask();
        projectTask.setTaskName(taskName);
        projectTask.setTaskProject(projectId);
        projectTask.setTaskDeleted("0");//0表示未关闭
        List<ProjectTask> tasks=taskService.findListTask(projectTask);
        if (tasks.size() == 0)
        {
            return resultMap(true, "");
        }

        return resultMap(false,"任务名称已存在");
    }
}
