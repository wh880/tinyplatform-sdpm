package org.tinygroup.sdpm.action.quality;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.quality.service.inter.BugService;
import org.tinygroup.tinysqldsl.Pager;

/**
 * Created by chenpeng15668 on 2015-9-22
 */

@Controller
@RequestMapping("/quality/bug")
public class BugAction extends BaseController {

	@Autowired
	private BugService bugService;
	
	//
	@RequestMapping("/form")
	public String form(Integer id,Model model){
		
		return "";
	}
	
	
	
	@RequestMapping("/findBug")
	public String findBugPager(Integer start,Integer limit,String order,Integer id,Model model){
		boolean asc = true;		
		if("desc".equals(order)){
			asc = false;
		}
		QualityBug bug = new QualityBug();
		bug.setProductId(id);
		Pager<QualityBug> bugPager = bugService.findBugListPager(start, limit, bug, order, asc);
		model.addAttribute("bugPager",bugPager);
		return "testManagement/data/BugData.pagelet";
	}
	
	/*@RequestMapping("add")
	public String add(Bug bug,Model model){
		Bug bugadd =	bugService.addBug(bug);
		model.addAttribute("bugadd",bugadd);
		return "testManagement/page/proposeBug.page";
	}*/
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String save(QualityBug bug,Model model){
		if(bug.getBugId() == null){
			bugService.addBug(bug);
		}else{
			bugService.updateBug(bug);
		}	
		model.addAttribute("bugsave",bug);
		return "testManagement/page/Bug.page";
	}
}
