package org.tinygroup.sdpm.action.system;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.ModuleService;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
@Controller
@RequestMapping("system/module")
public class ModuleAction extends BaseController{
	@Autowired
	private ModuleService moduleService;
//	@RequestMapping("List")
//	public String listModule(Integer root,Model model){
//		List<SystemModule> list = moduleService.findByRoot(root);
//		model.addAttribute("list", list);
//		return "syys";
//	}
	@ResponseBody
	@RequestMapping("tree")
	public List<Map<String,Object>> ajax(SystemModule systemModule,HttpServletResponse response){
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<SystemModule> list = moduleService.findModules(systemModule);
		mergeModule(list,mapList,0);
		return mapList;
	}
	

	private void mergeModule(List<SystemModule> systemModules, List<Map<String, Object>> maps, int parent){
		for(SystemModule systemModule : systemModules){
			if(systemModule.getModuleParent() == parent){
				int size = maps.size();
				Map<String, Object> mapTop = Maps.newHashMap();
				mapTop.put("id", "p" + systemModule.getModuleId());
				mapTop.put("pId", parent!=0?"p"+parent:parent);
				mapTop.put("open", true);
				mergeModule(systemModules,maps,systemModule.getModuleId());
				mapTop.put("isParent", maps.size()>size?true:false);
				mapTop.put("name",systemModule.getModuleName());
				maps.add(mapTop);
//				systemModules.remove(i);
			}
		}
	}
}
