package org.tinygroup.sdpm.action.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.system.dao.pojo.SystemDict;
import org.tinygroup.sdpm.system.dao.pojo.SystemEffort;
import org.tinygroup.sdpm.system.service.inter.DictService;
import org.tinygroup.tinysqldsl.Pager;
@Controller
@RequestMapping("system/dict")
public class DictAction extends BaseController{
	@Autowired
	private DictService dictService;
	@RequestMapping("list")
	public String list(SystemDict dict,Model model){
		List<SystemDict> dictList  =dictService.findDictList(dict);
		   model.addAttribute("dictList", dictList);
		return "/system/page/dictionaries/dict_list.page";
		
	}
	@RequestMapping("findPager")
	public String findPager(Integer start,Integer limit,String order ,String ordertype, Integer muduleId, Model model){
		   boolean asc = true;
	       if ("desc".equals(ordertype)) {
	           asc = false;
	       }
	      SystemDict systemDict = new SystemDict();
	      systemDict.setModuleId(muduleId);
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
//	@RequestMapping("check2")
//    public String checkDict2(Integer dictId,Model model){
//    	 SystemDict dict =dictService.findDict(dictId);
//    	 model.addAttribute("dict", dict);
//    	 return "/system/page/dictionaries/dict_view.pagelet";
//     }
	@RequestMapping("delete")
	public String deleteDict(Integer dictId){
		dictService.deleteDict(dictId);
		return "system/page/dictionaries/dictitem.page";
	}
//	@RequestMapping("delete2")
//	public String deleteDict2(Integer dictId){
//		dictService.deleteDict(dictId);
//		return "system/page/dictionaries/dict_list.page";
//	}
	@RequestMapping(value ="save",method = RequestMethod.POST)
	public String saveDict(SystemDict systemDict ,Model model){
		if(systemDict.getDictId()==null){
			
			dictService.addDict(systemDict);
		}
		else{
			dictService.updateDict(systemDict);
		}
		model.addAttribute("dict", systemDict);
		return "system/page/dictionaries/dictitem.page";
	}
//	@RequestMapping(value ="save2",method = RequestMethod.POST)
//	public String saveDict2(SystemDict systemDict ,Model model){
//		if(systemDict.getDictId()==null){
//			
//			dictService.addDict(systemDict);
//		}
//		else{
//			dictService.updateDict(systemDict);
//		}
//		model.addAttribute("dict", systemDict);
//		return "system/page/dictionaries/dict_list.pagelet";
//	}


}
