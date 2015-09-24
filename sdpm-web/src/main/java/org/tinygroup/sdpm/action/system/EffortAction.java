package org.tinygroup.sdpm.action.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.system.dao.pojo.SystemEffort;
import org.tinygroup.sdpm.system.service.inter.EffortService;
@Controller
@RequestMapping("system/effort")
public class EffortAction extends BaseController{
	@Autowired
	private EffortService effortService;
	@RequestMapping("list")
	public String list(SystemEffort effort,Model model){
		
		List<SystemEffort> list = effortService.find(effort);
		model.addAttribute("list", list);
		return "project/note/tableData.page";
		
	}
	@RequestMapping("add")
	public String add(SystemEffort systemEffort,Model model){
		effortService.save(systemEffort);
		return "redirect:" + "/system/effort/list/";
	}

}
