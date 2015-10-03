package org.tinygroup.sdpm.action.system;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.weblayer.WebContext;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
@Controller
@RequestMapping("system/module")
public class ModuleAction extends BaseController{
	@Autowired
	private ModuleService moduleService;
	@ResponseBody
	@RequestMapping("tree")
	public List<Map<String,Object>> ajax(SystemModule systemModule,HttpServletResponse response){
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
//		systemModule.setModuleType("dict");
		List<SystemModule> list = moduleService.findModules(systemModule);
		if(list !=null&&list.size()>0){
			mergeModule(list,mapList,0);
		}
		return mapList;
	}
	@RequestMapping("list")
	public String findModule(String moduleType,Model model){
		SystemModule systemModule= new SystemModule();
		systemModule.setModuleType(moduleType);
		List<SystemModule> list = moduleService.findModules(systemModule);
		model.addAttribute("list", list);
		return "/system/page/dictionaries/dict_list.page";
	}
	@RequestMapping("view")
	public String viewModule(Integer moduleId,Model model){
		SystemModule module= moduleService.findById(moduleId);
		model.addAttribute("module", module);
		return "/system/page/dictionaries/dict_view.pagelet";
	}
	@RequestMapping("delete")
	public String deleteModule(Integer moduleId)
	{
		if(moduleId!=null){
			moduleService.deleteById(moduleId);
		}
		return "redirect: list?moduleType=dict";
	}
	@RequestMapping("find")
	public String find(HttpServletRequest request, WebContext webContext,Integer moduleId,Model model){
		List<SystemModule> list = (List<SystemModule>) request.getSession().getAttribute("moduleList");
		String oldUrl = webContext.get("oldUrl");
		if(list==null||list.size()==0){
			SystemModule systemModule = new SystemModule();
			systemModule.setModuleType("dict");
		    list=moduleService.findModules(systemModule);
		    request.getSession().setAttribute("moduleList", list);
		}
		if(moduleId!=null){
			
		SystemModule module= moduleService.findById(moduleId);
		model.addAttribute("module", module);
		}
		else{
			SystemModule module = new SystemModule();
			moduleService.findModules(module);
			model.addAttribute("module", module);
		}
		return "/system/page/dictionaries/dict_edit.pagelet";
	}
	@RequestMapping("save")
	public String saveModule(SystemModule systemModule,Model model){
		if(systemModule.getModuleId()==null){
			systemModule.setModuleRoot(0);
			systemModule.setModuleGrade(0);
			systemModule.setModuleOrder(0);
			systemModule.setModulePath("1,2,3");
			systemModule.setModuleOwner("dict");
			if(systemModule.getModuleParent()==null){
				systemModule.setModuleParent(0);
			}
			
			moduleService.add(systemModule);
		}
		else{
			moduleService.eidtNameAndTiele(systemModule);
		}
		return "redirect: list?moduleType=dict";
	}
	@ResponseBody
	@RequestMapping("batchDelete")
   public Map<String, String> batchDelete(String ids){
	  String[] sids = ids.split(",");
	  Integer[] intIds = new Integer[sids.length];
		for(int i=0;i<sids.length;i++){
			intIds[i] = Integer.valueOf(sids[i]);
		}
	    moduleService.batchDelete(intIds);
	   Map<String, String> map = new HashedMap();
	   map.put("info", "success");
	   	map.put("status","y" );
	   return map;
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
