package org.tinygroup.sdpm.action.project;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.dict.util.DictUtil;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.service.inter.StoryService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.dao.pojo.TaskChartBean;
import org.tinygroup.sdpm.project.service.inter.TaskService;
import org.tinygroup.sdpm.system.dao.pojo.ProfileType;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.inter.ProfileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理任务的相关模块
 * Created by wangying14938 on 2015-09-22.
 */
@Controller
@RequestMapping("/a/project/task")
public class ProjectTaskCommonAction extends BaseController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private StoryService storyService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProfileService profileService;

    @ModelAttribute
    public void init(Model model) {
        initSearchBar(model, "任务");
    }

    @RequiresPermissions("gantt")
    @RequestMapping("/gantt")
    public String gantt(Model model, String choose,
                        HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("choose", choose);

        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return redirectProjectForm();
        }
        List<OrgUser> teamUserList = userService.findTeamUserListByProjectId(projectId);
        model.addAttribute("teamUserList", teamUserList);

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
            Float temp = t.getTaskConsumed() + t.getTaskLeft();
            if ((t.getTaskConsumed() == null || t.getTaskLeft() == null) || temp.compareTo(new Float(0))==0) {
                comp = 0f;
            } else {
                comp = t.getTaskConsumed() / (t.getTaskConsumed() + t.getTaskLeft());
            }
            map.put("pComp", String.format("%.2f", comp * 100));
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

    @RequiresPermissions(value = {"task-group"}, logical = Logical.OR)
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
                    ProductStory story = storyService.findStory(Integer.valueOf(key));
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


}
