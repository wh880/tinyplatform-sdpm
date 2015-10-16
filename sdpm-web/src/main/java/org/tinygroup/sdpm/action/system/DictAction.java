package org.tinygroup.sdpm.action.system;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.dict.util.DictUtil;
import org.tinygroup.sdpm.system.dao.pojo.SystemDict;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.DictService;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.weblayer.WebContext;
@Controller
@RequestMapping("a/system/dict")
public class DictAction extends BaseController{
	@Autowired
	private DictService dictService;
	@Autowired
	private ModuleService moduleService;
	@RequestMapping("")
	public String toDict(){
		return "/system/page/dictionaries/dictitem.page";
	}

	@RequestMapping("dictItem")
	public String dictItem(SystemModule systemModule, HttpServletRequest request){
		systemModule = moduleService.findById(systemModule.getModuleId());
		request.getSession().setAttribute("dictModule",systemModule);
		return "/system/page/dictionaries/dictitem_list.pagelet";
	}

	@RequestMapping("list")
	public String list(SystemDict dict,Model model){
		List<SystemDict> dictList  =dictService.findDictList(dict);
		model.addAttribute("dictList", dictList);
		return "/system/page/dictionaries/dict_list.page";

	}
	@RequestMapping("findPager")
	public String findPager(Integer start,Integer limit,String order ,String ordertype, Integer moduleId, Model model){
		boolean asc = true;
		if ("desc".equals(ordertype)) {
			asc = false;
		}
		SystemDict systemDict = new SystemDict();
		systemDict.setModuleId(moduleId);
		Pager<SystemDict> dictPager = dictService.findDictPager(start, limit, systemDict, order, asc);
		model.addAttribute("dictPager",dictPager);
		return "system/page/dictionaries/tableData.pagelet";
	}
	@RequestMapping("check")
	public String checkDict(Integer dictId,Model model){
		SystemDict dict =dictService.findDict(dictId);
		model.addAttribute("dict", dict);
		return "system/page/dictionaries/dictitem_view.pagelet";
	}
    @ResponseBody
	@RequestMapping("delete")
	public Map<String, String> deleteDict(Integer dictId){
    	Map<String, String> map = new HashedMap();
		int s=dictService.deleteDict(dictId);
		if(s>0)
		{
		 map.put("status", "success");
	     map.put("info", "删除成功");
		}else{
			map.put("status", "n");
			map.put("info", "删除失败");
		}
		return map;
	}

	@RequestMapping(value ="save",method = RequestMethod.POST)
	public String saveDict(SystemDict systemDict ,Model model){
		if(systemDict.getDictId()==null){
			systemDict = dictService.addDict(systemDict);
			DictUtil.addDict(systemDict);
		}
		else{
			SystemDict oldDict = dictService.findDict(systemDict.getDictId());
			dictService.updateDict(systemDict);
			DictUtil.editDict(oldDict,systemDict);
		}
		model.addAttribute("dict", systemDict);
		return "system/page/dictionaries/dictitem.page";
	}
	@RequestMapping("find")
	public String find(Integer dictId, Model model,HttpServletRequest request){
		if(dictId!=null){
			SystemDict dict= dictService.findDict(dictId);
			SystemModule module = (SystemModule) request.getSession().getAttribute("dictModule");
			model.addAttribute("module",module);
			model.addAttribute("dict", dict);
		}
		else{

			SystemModule module = new SystemModule();
			module.setModuleType("dict");
			List<SystemModule> moduleList = moduleService.findModules(module);
			model.addAttribute("moduleList",moduleList);
		}
		return "/system/page/dictionaries/dictitem_edit.pagelet";
	}
	@ResponseBody
	@RequestMapping("batchDelete")
	public Map<String, String> bechDelete(String ids){
		String[] sids = ids.split(",");
		Integer[] intIds = new Integer[sids.length];
		for(int i=0;i<sids.length;i++){
			intIds[i] = Integer.valueOf(sids[i]);
		}
		dictService.batchDelete(intIds);
		Map<String, String> map = new HashedMap();
		map.put("info", "success");
		map.put("status","y" );
		return map;
	}
	public String findTree(WebContext webContext){
		String str=webContext.get("mo");

		return"";
	}

   @RequestMapping("searsh")
   public List<SystemDict> searsh(SystemDict systemDict){
	   List<SystemDict> list = dictService.findDictList(systemDict);
	   return list;
	   
   }
   
}
