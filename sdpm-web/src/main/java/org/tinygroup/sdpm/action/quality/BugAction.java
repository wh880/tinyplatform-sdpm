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
		
	@RequestMapping
	public String form(String get,Model model){
		
		model.addAttribute("get", get);
		return "/testManagement/page/Bug.page";
	}
	
	@RequestMapping("/findList")
	public String findList(Integer id,Model model){
		QualityBug bug = new QualityBug();
		bug.setProductId(id);
		List<QualityBug> buglist = bugService.findBugList(bug);
		model.addAttribute("buglist",buglist);
		return "testManagement/data/BugData.pagelet";
	}
	
	@RequestMapping("/findBug")
	public String findBugPager(Integer start,Integer limit,String order,String ordertype,QualityBug bug,Model model){
		boolean asc = true;		
		if("desc".equals(ordertype)){
			asc = false;
		}
		//QualityBug bug = new QualityBug();
		//	bug.setProductId(id);
		Pager<QualityBug> bugpager = bugService.findBugListPager(start, limit, bug, order, asc);
		model.addAttribute("bugpager",bugpager);
		return "testManagement/data/BugData.pagelet";
	}
	
	@RequestMapping("/makesure")
	public String makesure(){
		return "testManagement/page/tabledemo/makesure.page";
	}
	
	@RequestMapping("/assign")
	public String assign(){
		return "testManagement/page/tabledemo/assign.page";
	}
	
	@RequestMapping("/solve")
	public String solve(){
		return "testManagement/page/tabledemo/assign.page";
	}
	
	@RequestMapping("/close")
	public String close(){
		return "testManagement/page/tabledemo/shutdown.page";
	}
	
	@RequestMapping("/edit")
	public String edit(){
		return "testManagement/page/tabledemo/edition.page";
	}
			
	@RequestMapping("/add")
	public String add(){
		return "testManagement/page/proposeBug.page";
	}
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String save(QualityBug bug,Model model){
		if(bug.getBugId() == null){
			bugService.addBug(bug);
		}else{
			bugService.updateBug(bug);
		}	
		model.addAttribute("bugsave",bug);
		return "redirect:"+"quality/bug";
	}
}
