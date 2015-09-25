package org.tinygroup.sdpm.action.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
@Controller
@RequestMapping("system/module")
public class ModuleAction extends BaseController{
//	@Autowired
//	private ModuleService moduleService;
//	@RequestMapping("List")
//	public String listModule(Integer root,Model model){
//		List<SystemModule> list = moduleService.findByRoot(root);
//		model.addAttribute("list", list);
//		return ""
//	}
	

}
