package org.tinygroup.sdpm.action.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemHistory;
import org.tinygroup.sdpm.system.service.inter.ActionService;
import org.tinygroup.sdpm.system.service.inter.HistoryService;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("a/system/action")
public class ActionAction extends BaseController {
    @Autowired
    private ActionService actionService;
    @Autowired
    private HistoryService historyService;

    @RequestMapping("find")
    public String find(SystemAction action, Model model) {

        List<SystemAction> actions = actionService.findAction(action, null, false);

        model.addAttribute("action", actions);
        return "/system/page/holiday/holiday-dynamic.pagelet";
    }


    @RequestMapping("/list")
    public String list(SystemAction action, String choice,
                       @CookieValue(value = "cookieProductId", defaultValue = "0") String cookieProductId,
                       @RequestParam(required = false, defaultValue = "0") int start,
                       @RequestParam(required = false, defaultValue = "10") int limit,
                       @RequestParam(required = false, defaultValue = "actionDate") String order,
                       @RequestParam(required = false, defaultValue = "asc") String ordertype, Model model, HttpServletRequest request) {


        if (Integer.parseInt(cookieProductId) > 0) {
            action.setActionProduct(cookieProductId);
        }

        Pager<SystemAction> pagerSystemAction = actionService.queryActionPager(start, limit, ActionUtil.getActionDateCondition(choice), action, order, ordertype);

        model.addAttribute("systemAction", pagerSystemAction);


        return "/product/data/tinydynamicdata.pagelet";
    }

    @RequestMapping("ajax/history")
    public String getHistory(SystemAction action, Model model) {
        List<SystemAction> actions = actionService.findAction(action, "actionId", true);
        Map<SystemAction, List<SystemHistory>> map = new LinkedHashMap<SystemAction, List<SystemHistory>>();
        for (SystemAction action1 : actions) {
            SystemHistory history = new SystemHistory();
            history.setHistoryAction(action1.getActionId());
            List<SystemHistory> histories = historyService.findSystemHistory(history);
            map.put(action1, histories);
        }
        model.addAttribute("actionMap", map);
        return "/system/page/history/historyRecord.pagelet";
    }

}
