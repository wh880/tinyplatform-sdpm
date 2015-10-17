package org.tinygroup.sdpm.action.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.system.dao.pojo.Holiday;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemHistory;
import org.tinygroup.sdpm.system.service.inter.ActionService;
import org.tinygroup.sdpm.system.service.inter.HistoryService;
import org.tinygroup.tinysqldsl.Pager;
@Controller
@RequestMapping("a/system/action")
public class ActionAction extends BaseController{
	@Autowired
	private ActionService actionService;
	@Autowired
	private HistoryService historyService;
	@RequestMapping("find")
	public String find(SystemAction action,Model model){
//		action.setActionObjectType("user");
		List<SystemAction> actions=actionService.find(action);
//		Integer[] ids = new Integer[actions.size()];
//		for(int i=0,n=actions.size();i<n;i++){
//			ids[i]=actions.get(i).getActionObjectId();
//		}
//		List<Holiday> holidays=holidayService.findByIds(ids);
//		model.addAttribute("holiday", holidays);
		model.addAttribute("action", actions);
		return "/system/page/holiday/holiday-dynamic.pagelet";
	}
	
	
	@RequestMapping("/list")
	public String list(SystemAction action,String choice,
			@RequestParam(required = false,defaultValue = "1")int page,
			@RequestParam(required = false,defaultValue = "10")int pagesize,
			@RequestParam(required = false,defaultValue = "actionDate")String order,
			@RequestParam(required = false,defaultValue = "asc")String ordertype,Model model){
		
	/*	if(request.getSession().getAttribute("sessionProductId")!=null){
			plan.setProductId((Integer)(request.getSession().getAttribute("sessionProductId")));
		}
		*/
		Pager<SystemAction>  pagerSystemAction = actionService.queryPager(page, pagesize,ActionUtil.getActionDateCondition(choice), action, order, ordertype);

		model.addAttribute("systemAction",pagerSystemAction);

		return "/product/data/tinydynamicdata.pagelet";
	}

	@RequestMapping("ajax/history")
	public String getHistory(SystemAction action, Model model){
		List<SystemAction> actions = actionService.find(action);
		Map<SystemAction,List<SystemHistory>> map = new HashMap<SystemAction, List<SystemHistory>>();
		for(SystemAction action1 : actions){
			SystemHistory history = new SystemHistory();
			history.setHistoryAction(action1.getActionId());
			List<SystemHistory> histories = historyService.find(history);
			map.put(action1,histories);
		}
		model.addAttribute("actionMap",map);
		return "/system/page/history/historyRecord.pagelet";
	}

}
