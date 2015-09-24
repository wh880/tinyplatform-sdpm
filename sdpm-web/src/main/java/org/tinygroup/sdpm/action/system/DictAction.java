package org.tinygroup.sdpm.action.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.system.dao.pojo.SystemDict;
import org.tinygroup.sdpm.system.service.inter.DictService;
import org.tinygroup.tinysqldsl.Pager;
@Controller
@RequestMapping("system/dict")
public class DictAction extends BaseController{
	@Autowired
	private DictService dictService;
	public String list(Integer moduleId, String columnName,String ordertype,Model model){
		  boolean asc = true;
	       if ("desc".equals(ordertype)) {
	           asc = false;
	       }
	       SystemDict dict = new SystemDict();
	       dict.setModuleId(moduleId);
		   dictService.findDictList(dict, columnName, asc);
		return "system/page/dictionaries/dictitem_list.page";
		
	}
			

}
