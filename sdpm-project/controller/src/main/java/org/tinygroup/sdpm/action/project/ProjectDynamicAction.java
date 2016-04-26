package org.tinygroup.sdpm.action.project;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.util.DateUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.project.service.inter.TeamService;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.service.inter.ActionService;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequiresPermissions("dynamic")
    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return redirectProjectForm();
        }
        List<OrgUser> teamList = userService.findTeamUserListByProjectId(projectId);
        model.addAttribute("teamList", teamList);
        return "project/index/dynamic/index.page";
    }

    @RequestMapping("/find")
    public String find(Integer start, Integer limit, String order, String ordertype,
                       HttpServletRequest request, HttpServletResponse response, Model model, String selDate, String teamUserId) {
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return redirectProjectForm();
        }
        SystemAction systemAction = new SystemAction();
        systemAction.setActionProject(projectId.toString());
//        systemAction.setActionObjectType(LogUtil.LogOperateObject.PROJECT.toString());
        //根据日期来查
        /**
         * 1-今天 2-昨天 3-前天 4-本周 5-上周 6-本月 7-上月 0-所有
         * action_date BETWEEN '2015-10-16 00:00:00' AND '2015-10-16 23:59:59'
         */
        Date startDate = new Date();
        Date endDate = new Date();
        if (!StringUtil.isBlank(selDate) && StringUtil.isBlank(teamUserId)) {
            if (selDate.equals("0")) {
                startDate = null;
                endDate = null;
            } else {
                betweenDate(selDate, startDate, endDate);
            }
            if (startDate == null) {
                Pager<SystemAction> actionPager = actionService.findSystemActionPager(start, limit, systemAction, order, ordertype);
                model.addAttribute("actionPager", actionPager);
            } else {
                String startDateStr = DateUtils.formatDate(startDate, "yyyy-MM-dd HH:mm:ss");
                String endDateStr = DateUtils.formatDate(endDate, "yyyy-MM-dd HH:mm:ss");
                Pager<SystemAction> actionPager = actionService.queryActionBetweenDate(start, limit, systemAction, startDateStr, endDateStr, order, "asc".equals(ordertype) ? true : false);
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

        return "project/data/dynamic/tableData.pagelet";
    }


}
