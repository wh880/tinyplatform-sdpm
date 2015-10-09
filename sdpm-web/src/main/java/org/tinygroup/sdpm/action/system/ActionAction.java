package org.tinygroup.sdpm.action.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.system.dao.pojo.Holiday;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.service.inter.ActionService;
import org.tinygroup.sdpm.system.service.inter.HolidayService;
@Controller
@RequestMapping("system/action")
public class ActionAction extends BaseController{
	@Autowired
	private ActionService actionService;
//	@Autowired
//	private HolidayService holidayService;
	@RequestMapping("find")
	public String find(SystemAction action,Model model){
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
	
}
