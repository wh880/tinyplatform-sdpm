package org.tinygroup.sdpm.action.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.service.inter.ActionService;
@Controller
@RequestMapping("system/action")
public class ActionAction extends BaseController{
	@Autowired
	private ActionService actionService;
	@RequestMapping("find")
	public String find(SystemAction action,Model model){
		List<SystemAction> actions=actionService.find(action);
		model.addAttribute("action", actions);
		return "/system/page/holiday/holiday-dynamic.pagelet";
	}
	
}
