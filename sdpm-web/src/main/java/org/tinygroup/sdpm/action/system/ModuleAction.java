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
	public List<Map<String,Object>> ajax(HttpServletResponse response){
		  response.setContentType("application/json; charset=UTF-8");
		  List<Map<String, Object>> mapList = Lists.newArrayList();
	     String[] title = {"花湖", "哈哈", "可以", "这是"};
	     for (int i = 1; i < 5; i++) {
	            Map<String, Object> mapTop = Maps.newHashMap();
	            mapTop.put("id", "p" + i);
	            mapTop.put("pId", 0);
	            mapTop.put("open", true);
	            mapTop.put("isParent", true);
	            mapTop.put("name", title[i - 1]);
	            mapList.add(mapTop);
	            List<SystemModule> list = moduleService.findByRoot(i);
	            for (SystemModule s : list) {
	                Map<String, Object> map = Maps.newHashMap();
	                map.put("id", s.getModuleId());
	                map.put("pId", "p" + i);
	                map.put("name", s.getModuleName());
	                mapList.add(map);
	            }
	        }
	        return mapList;
		
	}
	

}
