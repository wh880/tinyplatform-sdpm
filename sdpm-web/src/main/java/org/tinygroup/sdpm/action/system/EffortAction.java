package org.tinygroup.sdpm.action.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.system.dao.pojo.Effort;
import org.tinygroup.sdpm.system.service.inter.EffortService;
@Controller
@RequestMapping("system/effort")
public class EffortAction extends BaseController{
	@Autowired
	private EffortService effortService;
	@RequestMapping("list")
	public String list(Effort effort,Model model){
		if(effort==null){
			effort = new Effort();
		}
		List<Effort> list = effortService.find(effort);
		model.addAttribute("list", list);
		return "project/note/notetable.pagelet";
		
	}

}
