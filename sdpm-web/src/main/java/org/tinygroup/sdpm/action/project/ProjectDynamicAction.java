package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.util.DateUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.sdpm.project.service.inter.TeamService;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.service.inter.ActionService;
import org.tinygroup.sdpm.util.CookieUtils;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by shenly13343 on 2015-10-16.
 */
@Controller
@RequestMapping("/a/project/dynamic")
public class ProjectDynamicAction extends BaseController {
    @Autowired
    private ActionService actionService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        List<ProjectTeam> teamList = teamService.findTeamByProjectId(projectId);
        model.addAttribute("teamList", teamList);
        return "project/dynamic/index.page";
    }

    @RequestMapping("/find")
    public String find(Integer start, Integer limit, String order, String ordertype, HttpServletRequest request, Model model, String selDate, String teamUserId) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        SystemAction systemAction = new SystemAction();
        systemAction.setActionProject(projectId.toString());
        systemAction.setActionObjectType(LogUtil.LogOperateObject.PROJECT.toString());
        //根据日期来查
        /**
         * 1-今天 2-昨天 3-前天 4-本周 5-上周 6-本月 7-上月 0-所有
         * action_date BETWEEN '2015-10-16 00:00:00' AND '2015-10-16 23:59:59'
         */
        Date date = new Date();
        Date startDate;
        Date endDate;
        if (!StringUtil.isBlank(selDate) && StringUtil.isBlank(teamUserId)) {
            if ("1".equals(selDate)) {
                startDate = DateUtils.getDateStart(date);
                endDate = DateUtils.getDateEnd(date);
            } else if ("2".equals(selDate)) {
                startDate = DateUtils.addDays(DateUtils.getDateStart(date), -1);
                endDate = DateUtils.addDays(DateUtils.getDateEnd(date), -1);
            } else if ("3".equals(selDate)) {
                startDate = DateUtils.addDays(DateUtils.getDateStart(date), -2);
                endDate = DateUtils.addDays(DateUtils.getDateEnd(date), -3);
            } else if ("4".equals(selDate)) {
                startDate = DateUtils.getFirstDayOfWeek(date);
                endDate = DateUtils.getLastDayOfWeek(date);
            } else if ("5".equals(selDate)) {
                startDate = DateUtils.addDays(DateUtils.getFirstDayOfWeek(date), -7);
                endDate = DateUtils.addDays(DateUtils.getLastDayOfWeek(date), -7);
            } else if ("6".equals(selDate)) {
                startDate = DateUtils.getFirstDayOfMonth(date);
                endDate = DateUtils.getLastDayOfMonth(date);
            } else if ("7".equals(selDate)) {
                startDate = DateUtils.getFirstDayOfMonth(DateUtils.addMonths(date, -1));
                endDate = DateUtils.getLastDayOfMonth(DateUtils.addMonths(date, -1));
            } else {
                startDate = null;
                endDate = null;
            }
            if (startDate == null && endDate == null) {
                Pager<SystemAction> actionPager = actionService.findSystemActionPager(start, limit, systemAction, order, ordertype);
                model.addAttribute("actionPager", actionPager);
            } else {
                String startDateStr = DateUtils.formatDate(startDate, "yyyy-MM-dd HH:mm:ss");
                String endDateStr = DateUtils.formatDate(endDate, "yyyy-MM-dd HH:mm:ss");
                Pager<SystemAction> actionPager = actionService.queryBetweenDate(start, limit, systemAction, startDateStr, endDateStr, order, "asc".equals(ordertype) ? true : false);
                model.addAttribute("actionPager", actionPager);
            }
        }
        //根据用户来查
        else if (StringUtil.isBlank(selDate) && !StringUtil.isBlank(teamUserId)) {
            OrgUser user = userService.findUser(teamUserId);
            systemAction.setActionActor(user.getOrgUserId());
            Pager<SystemAction> actionPager = actionService.findSystemActionPager(start, limit, systemAction, order, ordertype);
            model.addAttribute("actionPager", actionPager);
        } else {
            Pager<SystemAction> actionPager = actionService.findSystemActionPager(start, limit, systemAction, order, ordertype);
            model.addAttribute("actionPager", actionPager);
        }
        model.addAttribute("selDate", selDate);

        return "project/dynamic/tableData.pagelet";
    }


}
