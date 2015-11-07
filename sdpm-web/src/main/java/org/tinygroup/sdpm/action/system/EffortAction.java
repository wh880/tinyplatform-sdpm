package org.tinygroup.sdpm.action.system;


import com.google.common.collect.Lists;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.util.DateUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.service.inter.TaskService;
import org.tinygroup.sdpm.system.dao.pojo.SystemEffort;
import org.tinygroup.sdpm.system.service.inter.EffortService;
import org.tinygroup.sdpm.util.ProjectUtils;
import org.tinygroup.sdpm.util.UserUtils;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("a/system/effort")
public class EffortAction extends BaseController {
    @Autowired
    private EffortService effortService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String note(Model model) {
        Date date = new Date();
        model.addAttribute("date", date);
        return "/project/note/index";
    }

    @RequestMapping("date/{type}")
    public String date(@PathVariable(value = "type") String type, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        Integer projectId = ProjectUtils.getCurrentProjectId(request, response);
        if ("1".equals(type)) {
            return "/project/note/notetable.page";
        }
        List<OrgUser> user = userService.findTeamUserListByProjectId(projectId);
        model.addAttribute("user", user);
        return "/project/note/notetable.page";
    }

    @RequestMapping("list")
    public String list(int taskId, SystemEffort effort, Model model) {
        String order = "effort_date";
        String orderType = "desc";
        effort.setEffortObjectId(taskId);
        List<SystemEffort> list = effortService.findList(effort, order, orderType);
        List<SystemEffort> effortList = new ArrayList<SystemEffort>();
        if (list.size() > 5) {
            for (int i = 0; i < 5; i++) {
                effortList.add(list.get(i));
            }
        } else {
            effortList = list;
        }
        model.addAttribute("taskId", taskId);
        model.addAttribute("list", effortList);
        return "project/task/note";
    }

    @RequestMapping("save")
    public String add(SystemEffort systemEffort, Model model) {
        if (systemEffort.getEffortDate() != null) {
            if (systemEffort.getEffortId() == null) {
                systemEffort.setEffortBegin(new SimpleDateFormat("yyyy-MM-dd").format(systemEffort.getEffortDate()));
                systemEffort.setEffortAccount(UserUtils.getUserAccount());
            }
            effortService.save(systemEffort);
        }
        return "project/note/notetable.page";
    }

    @RequestMapping("findPager")
    public String findPager(Integer start, Integer limit, String order, String ordertype, Integer effortId, Model model) {
        boolean asc = true;
        if ("desc".equals(ordertype)) {
            asc = false;
        }
        SystemEffort effort = new SystemEffort();
        effort.setEffortId(effortId);
        Pager<SystemEffort> effortPager = effortService.findByPage(start, limit, effort, order, asc);
        model.addAttribute("effortPager", effortPager);
        return "project/note/tableData.pagelet";
    }

    @ResponseBody
    @RequestMapping("event")
    public List<Map<String, Object>> effortEvent(SystemEffort systemEffort, HttpServletResponse response, Date start, Date end) {
        response.setContentType("application/json; charset=UTF-8");
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<SystemEffort> list = effortService.findSystemEffortBetweenDate(systemEffort, start, end);
        if (!CollectionUtil.isEmpty(list)) {
            for (SystemEffort effort : list) {
                String date = DateUtils.formatDate(effort.getEffortDate());
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", effort.getEffortId());
                map.put("title", effort.getEffortWork());
                map.put("start", date);
                map.put("end", date);
                mapList.add(map);
            }
        }
        return mapList;
    }

    @ResponseBody
    @RequestMapping("batchDelete")
    public Map<String, String> batchDelete(String ids) {
        String[] sids = ids.split(",");
        Integer[] intIds = new Integer[sids.length];
        for (int i = 0; i < sids.length; i++) {
            intIds[i] = Integer.valueOf(sids[i]);
        }
        effortService.batchDelete(intIds);
        return resultMap(true, "删除成功");
    }

    @RequestMapping("date")
    public String findByDate(int start, int limit, String order, String orderType,
                             @RequestParam(required = false, defaultValue = "0") Integer date,
                             @RequestParam(value = "", required = false) String effortAccount,
                             SystemEffort systemEffort, Model model) throws ParseException {
        Date startDate = new Date();
        Date endDate = new Date();
        Pager<SystemEffort> pager = null;
        boolean asc = true;
        if ("desc".equals(orderType)) {
            asc = false;
        }
        if (date == 0) {
            pager = effortService.findByPage(start, limit, systemEffort, order, asc);//全部日志
        } else if (date == 1) {
            systemEffort.setEffortDate(new Date());
            pager = effortService.findByPage(start, limit, systemEffort, order, asc);
        } else if (date == 2) {
            Date yesterday = DateUtils.addDays(new Date(), -1);
            systemEffort.setEffortDate(yesterday);
            pager = effortService.findByPage(start, limit, systemEffort, order, asc);
        } else if (date == 3) {
            startDate = DateUtils.getFirstDayOfWeek(startDate);
            endDate = DateUtils.getLastDayOfWeek(startDate);
            startDate = DateUtils.getDateStart(startDate);
            endDate = DateUtils.getDateEnd(endDate);
            pager = effortService.findSystemEffortPagerByDate(start, limit, systemEffort, startDate, endDate, order, asc);
        } else if (date == 4) {
            startDate = DateUtils.addDays(DateUtils.getFirstDayOfWeek(startDate), -7);
            endDate = DateUtils.addDays(DateUtils.getLastDayOfWeek(endDate), -7);
            startDate = DateUtils.getDateStart(startDate);
            endDate = DateUtils.getDateEnd(endDate);
            pager = effortService.findSystemEffortPagerByDate(start, limit, systemEffort, startDate, endDate, order, asc);

        } else if (date == 5) {
            startDate = DateUtils.getFirstDayOfMonth(startDate);
            endDate = DateUtils.getLastDayOfMonth(endDate);
            startDate = DateUtils.getDateStart(startDate);
            endDate = DateUtils.getDateEnd(endDate);
            pager = effortService.findSystemEffortPagerByDate(start, limit, systemEffort, startDate, endDate, order, asc);
        } else if (date == 6) {
            startDate = DateUtils.getFirstDayOfMonth(DateUtils.getLastDayOfLastMonth(startDate));
            endDate = DateUtils.getLastDayOfLastMonth(endDate);
            startDate = DateUtils.getDateStart(startDate);
            endDate = DateUtils.getDateEnd(endDate);
            pager = effortService.findSystemEffortPagerByDate(start, limit, systemEffort, startDate, endDate, order, asc);
        }

        if (!StringUtil.isBlank(effortAccount) && date == null) {
            systemEffort.setEffortAccount(effortAccount);
            pager = effortService.findByPage(start, limit, systemEffort, order, asc);
        }
        model.addAttribute("effortPager", pager);
        return "project/note/tableData.pagelet";
    }

    @RequestMapping("calendar")
    public String calendar(String Action, Integer id, String date, Model model) throws Exception {
        ProjectTask task = new ProjectTask();
        List<ProjectTask> taskList = taskService.findListTask(task);
        List<OrgUser> users = userService.findUserList(new OrgUser());
        model.addAttribute("users", users);
        model.addAttribute("taskList", taskList);
        if (Action.equals("add")) {
            SystemEffort effort = new SystemEffort();
            effort.setEffortBegin(date);
            model.addAttribute("effort", effort);
        }
        if (Action.equals("edit")) {
            SystemEffort effort = effortService.findSystemEffortById(id);
            model.addAttribute("effort", effort);
        }
        return "project/note/calendarEvent.pagelet";
    }

    @Deprecated
    @ResponseBody
    @RequestMapping("operate")
    public Map<String, String> operate(SystemEffort effort, String date, String isvip, Model model) {
        Map<String, String> map = new HashedMap();
        if (isvip.equals("y")) {
            effortService.batchDelete(effort.getEffortId());
            map.put("status", "y");
            map.put("info", "1");

            return map;
        } else if (isvip.equals("n")) {
            effortService.save(effort);
            map.put("status", "y");
            map.put("info", "1");
            return map;
        } else {
            map.put("status", "y");
            map.put("info", "2");
            return map;
        }
    }

}
