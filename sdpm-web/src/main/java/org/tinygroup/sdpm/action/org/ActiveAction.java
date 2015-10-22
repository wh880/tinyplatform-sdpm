package org.tinygroup.sdpm.action.org;

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
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.service.inter.ActionService;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015-10-17.
 */

@Controller
@RequestMapping("/a/org/active")
public class ActiveAction extends BaseController {

    @Autowired
    private ActionService actionService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProductService productService;


    /**
     * 显示动态页面，并查询用户、产品、迭代的列表
     *
     * @param request
     * @param model
     * @return
     */
    @RequiresPermissions("organizationActive")
    @RequestMapping("/show")
    public String show(HttpServletRequest request, Model model) {
        List<OrgUser> userList = userService.findUserList(new OrgUser());
        List<Product> productList = productService.findProductList(new Product());
        List<Project> projectList = projectService.findList();
        model.addAttribute("userList", userList);
        model.addAttribute("productList", productList);
        model.addAttribute("projectList", projectList);
        return "organization/active/active.page";
    }

    /**
     * 根据选择的时间段、用户、产品、项目来查询相应的动态
     * @param start
     * @param limit
     * @param order
     * @param ordertype
     * @param request
     * @param model
     * @param selDate
     * @param orgUserId
     * @param productId
     * @param projectId
     * @return
     */
    @RequiresPermissions("organizationActive")
    @RequestMapping("/find")
    public String find(Integer start, Integer limit, String order, String ordertype, HttpServletRequest request, Model model, String selDate, String orgUserId, String productId, String projectId) {
        SystemAction systemAction = new SystemAction();
        //根据日期来查
        /**
         * 1-今天 2-昨天 3-前天 4-本周 5-上周 6-本月 7-上月 0-所有
         * action_date BETWEEN '2015-10-16 00:00:00' AND '2015-10-16 23:59:59'
         */
        Date date = new Date();
        Date startDate;
        Date endDate;
        if (!StringUtil.isBlank(selDate) && StringUtil.isBlank(orgUserId)
                && StringUtil.isBlank(productId) && StringUtil.isBlank(projectId)) {
            if ("1".equals(selDate)) {
                startDate = DateUtils.getDateStart(date);
                endDate = DateUtils.getDateEnd(date);
            } else if ("2".equals(selDate)) {
                startDate = DateUtils.addDays(DateUtils.getDateStart(date), -1);
                endDate = DateUtils.addDays(DateUtils.getDateEnd(date), -1);
            } else if ("3".equals(selDate)) {
                startDate = DateUtils.addDays(DateUtils.getDateStart(date), -2);
                endDate = DateUtils.addDays(DateUtils.getDateEnd(date), -2);
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
        //根据用户,项目,产品来查
        else {
            if (!StringUtil.isBlank(orgUserId)) {
                systemAction.setActionActor(orgUserId);
            }
            if (!StringUtil.isBlank(productId)) {
                systemAction.setActionProduct(productId);
            }
            if (!StringUtil.isBlank(projectId)) {
                systemAction.setActionProject(projectId);
            }
            Pager<SystemAction> actionPager = actionService.findSystemActionPager(start, limit, systemAction, order, ordertype);

            model.addAttribute("actionPager", actionPager);
        }
        model.addAttribute("selDate", selDate);

        return "/organization/active/activeTable.pagelet";
    }
}
