package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.action.project.util.TaskStatusUtil;
import org.tinygroup.sdpm.action.system.ProfileUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.service.StoryService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.sdpm.project.service.inter.*;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.sdpm.util.CookieUtils;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.ModuleUtil;
import org.tinygroup.sdpm.util.UserUtils;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wangying14938 on 2015-09-22.任务
 */
@Controller
@RequestMapping("/a/project/task")
public class TaskAction extends BaseController {
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

    @RequestMapping("index")
    public String index(@CookieValue(required = false) Integer cookie_projectId, HttpServletResponse response, HttpServletRequest request, Model model, String moduleId) {
        List<Project> list = projectService.findList();
        Project selProject = new Project();
        if (list == null || list.isEmpty()) {
            return "redirect:" + adminPath + "/project/add";
        } else {
            if (cookie_projectId == null) {
                selProject = list.get(0);
                //maxAge=-1意为永久
                CookieUtils.setCookie(response, "cookie_projectId", selProject.getProjectId().toString(), -1);
            } else {
                boolean flag = false;
                for (Project p : list) {
                    if (p.getProjectId() == cookie_projectId) {
                        selProject = p;
                        CookieUtils.setCookie(response, "cookie_projectId", cookie_projectId.toString(), -1);
                        flag = true;
                        break;
                    }
                }
                //若数据库中无cookies中projectId对应的项目，则返回选中第一条
                if (!flag) {
                    selProject = list.get(0);
                    CookieUtils.setCookie(response, "cookie_projectId", selProject.getProjectId().toString(), -1);
                }
            }
        }
        //model.addAttribute("selProject", selProject);
        //model.addAttribute("projectList", list);
        request.getSession().setAttribute("selProject", selProject);
        request.getSession().setAttribute("projectList", list);
        if (moduleId != null) {
            model.addAttribute("moduleId", moduleId);
            return "project/task/index.page";
        } else {
            return "project/task/index.page";
        }

    }

    @RequestMapping("/edit")
    public String form(Integer taskId, Model model) {
        if (taskId != null) {
            ProjectTask task = taskService.findTask(taskId);
            model.addAttribute("task", task);
            //还需要查询其他相关任务剩余时间的信息
            return "project/task/edit.page";
        }
        return "error";
    }

    @RequestMapping("/call")
    public String call(Integer taskId, Model model, HttpServletRequest request) {
        if (taskId != null) {
            Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
            ProjectTask task = taskService.findTask(taskId);
            List<ProjectTeam> team = teamService.findTeamByProjectId(projectId);
            model.addAttribute("teamList", team);
            model.addAttribute("task", task);
            return "project/task/call.page";
        }
        return "error";
    }

    @RequestMapping("/finish")
    public String finish(Integer taskId, Model model, HttpServletRequest request) {
        if (taskId != null) {
            Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
            List<ProjectTeam> teamList = teamService.findTeamByProjectId(projectId);
            ProjectTask task = taskService.findTask(taskId);
            model.addAttribute("task", task);
            model.addAttribute("teamList", teamList);
            //还需要查询其他相关任务剩余时间的信息
            return "project/task/finish.page";
        }
        return "error";
    }

    @RequestMapping("/note")
    public String note(Integer taskId, Model model) {
        if (taskId != null) {
            ProjectTask task = taskService.findTask(taskId);
            model.addAttribute("task", task);
            //还需要查询其他相关任务剩余时间的信息
            return "project/task/note.page";
        }
        return "error";
    }

    @RequestMapping("/close")
    public String close(Integer taskId, Model model) {
        ProjectTask task = taskService.findTask(taskId);
        model.addAttribute("task", task);
        return "project/task/close.page";
    }

    @RequestMapping("/start")
    public String start(Integer taskId, Model model) {
        if (taskId != null) {
            ProjectTask task = taskService.findTask(taskId);
            model.addAttribute("task", task);
            //还需要查询其他相关任务剩余时间的信息
            return "project/task/start.page";
        }
        return "error";
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
    public String findPager(Integer start, Integer limit, String order, String ordertype, String statu, String choose, String group, Integer projectId, Model model, HttpServletRequest request, String moduleId) {
        boolean asc = true;
        if ("desc".equals(ordertype)) {
            asc = false;
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


        String condition = TaskStatusUtil.getCondition(statu, choose, request, moduleIds);
        Pager<ProjectTask> taskPager = taskService.findPagerTask(start, limit, task, order, asc, condition, group);
        model.addAttribute("taskPager", taskPager);
        model.addAttribute("statu", statu);
        model.addAttribute("choose", choose);
        model.addAttribute("group", group);
        return "project/task/datalist.pagelet";
    }

    @RequestMapping("/save")
    public String save(ProjectTask task, @RequestParam(value = "file", required = false) MultipartFile file,
                       Model model, String[] taskMailtoArray, HttpServletRequest request, String comment) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
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

            ProfileUtil profileUtil = new ProfileUtil();
            try {
                profileUtil.uploadNoTitle(file, newtask.getTaskId(), "task");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            taskService.updateTask(task);
        }
        model.addAttribute("task", task);
        return "project/task/index.page";
    }

    @RequestMapping(value = "/closesave", method = RequestMethod.POST)
    public String closesave(ProjectTask task, Model model, SystemAction systemAction) {
        taskService.updateCloseTask(task);

        systemAction.setActionObjectId(String.valueOf(task.getTaskId()));
        systemAction.setActionProject(String.valueOf(task.getTaskProject()));
        systemAction.setActionObjectType("task");
        systemAction.setActionActor("close");
        logService.log(systemAction);
        return "project/task/index.page";
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String editSave(ProjectTask task, Model model, SystemAction systemAction) {
        taskService.updateEditTask(task);

        systemAction.setActionObjectId(String.valueOf(task.getTaskId()));
        systemAction.setActionProject(String.valueOf(task.getTaskProject()));
        systemAction.setActionObjectType("task");
        systemAction.setActionActor("close");
        logService.log(systemAction);
        model.addAttribute("task", task);
        return "project/task/index.page";
    }

    @RequestMapping(value = "/callsave", method = RequestMethod.POST)
    public String callSave(ProjectTask task, Model model, String commnet) {
        if (task.getTaskId() == null) {
            taskService.addTask(task);
        } else {
            ProjectTask oldTask = taskService.findTask(task.getTaskId());
            taskService.updateCallTask(task);

            LogUtil.logWithComment(LogUtil.LogOperateObject.TASK, LogUtil.LogAction.ASSIGNED, task.getTaskId().toString(), UserUtils.getUserId(),
                    null, task.getTaskProject().toString(), oldTask, task, commnet);

        }
        model.addAttribute("task", task);
        return "project/task/index.page";
    }

    @RequestMapping(value = "/finishsave", method = RequestMethod.POST)
    public String finishsave(ProjectTask task, Model model) {
        if (task.getTaskId() == null) {
            taskService.addTask(task);
        } else {
            taskService.updateFinishTask(task);
        }
        model.addAttribute("task", task);
        return "project/task/index.page";
    }

    @RequestMapping(value = "/startsave", method = RequestMethod.POST)
    public String startsave(ProjectTask task, Model model) {
        if (task.getTaskId() == null) {
            taskService.addTask(task);
        } else {
            taskService.updateStartTask(task);
        }
        model.addAttribute("task", task);
        return "project/task/index.page";
    }

    @RequestMapping("/preadd")
    public String preadd(HttpServletRequest request, Model model, Integer storyId) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));

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


        List<ProductStory> storyList = storyService.findStoryByProject(projectId);
        ProductStory story = new ProductStory();
        if (storyId != null) {
            story = productStoryService.findStory(storyId);

        }
        model.addAttribute("story", story);

        model.addAttribute("moduleList", moduleList);
        model.addAttribute("storyList", storyList);

        return "project/task/add.page";
    }

    @RequestMapping("/batchadd")
    public String batchadd(Integer taskId, Model model) {
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
        String projectName = projectService.findById(task.getTaskProject()).getProjectName();
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
        Project project = projectService.findById(task.getTaskProject());
        model.addAttribute("task", task);
        model.addAttribute("project", project);

        //查询所属模块string
        String modulPath = ModuleUtil.getPath(task.getTaskModule(), " > ", moduleService, task.getTaskProject().toString(), true);
        model.addAttribute("modulPath", modulPath);
        //查询相关需求名字
        String storyTitle = productStoryService.findStory(task.getTaskStory()).getStoryTitle();
        model.addAttribute("storyTitle", storyTitle);
        return "project/task/basicInformation.pagelet";
    }

    @RequestMapping("/preBatchAdd")
    public String preBatchAdd(Model model, HttpServletRequest request) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        model.addAttribute("teamList", teamService.findTeamByProjectId(projectId));

        List<ProductStory> storyList = storyService.findStoryByProject(projectId);
        model.addAttribute("storyList", storyList);

        SystemModule systemModule = new SystemModule();
        systemModule.setModuleRoot(projectId);
        systemModule.setModuleType("project");
        List<SystemModule> modulesList = moduleService.findModuleList(systemModule);
        model.addAttribute("modulesList", modulesList);

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
            Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
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
    public List<Map<String, String>> ganttInit(HttpServletRequest request) {
        List<Map<String, String>> resList = new ArrayList<Map<String, String>>();
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));

        ProjectTask task = new ProjectTask();
        task.setTaskProject(projectId);
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
            map.put("pRes", t.getTaskAssignedTo());
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
            map.put("pDepend", "1");
            map.put("pCaption", "brian");
            resList.add(map);
        }
        return resList;
    }

    @RequestMapping("/gantt/find")
    public String ganttFind(Integer id, Model model) {
        ProjectTask task = taskService.findTask(id);
        model.addAttribute("task", task);
        return "project/task/ganttFind.pagelet";
    }


}
