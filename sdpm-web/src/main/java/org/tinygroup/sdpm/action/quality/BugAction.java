package org.tinygroup.sdpm.action.quality;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.sdpm.common.util.CookieUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.quality.service.inter.BugService;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


/**
 * Created by chenpeng15668 on 2015-9-22
 */

@Controller
@RequestMapping("/quality/bug")
public class BugAction extends BaseController {

	@Autowired
	private BugService bugService;
		
	@RequestMapping("")
	public String form(String get,QualityBug bug,Model model){
		bugService.findBugList(bug);
		model.addAttribute("bug", bug);
		model.addAttribute("get", get);
		return "/testManagement/page/Bug.page";
	}
	
	@RequestMapping("/findList")
	public String findList(Integer id,Model model){
		QualityBug bug = new QualityBug();
		bug.setProductId(id);
		List<QualityBug> buglist = bugService.findBugList(bug);
		model.addAttribute("buglist",buglist);
		return "/testManagement/data/BugData.pagelet";
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
		return "/testManagement/data/BugData.pagelet";
	}
	
	@RequestMapping("/reportForm")
	public String reportForm(){
		return "/testManagement/page/reportform.page";
	}
	
	@RequestMapping("/makesure")
	public String makesure(Integer bugId,Model model){
		QualityBug bug = new QualityBug();
	    bug = bugService.findById(bugId);
	    model.addAttribute("bug",bug);
		return "/testManagement/page/tabledemo/makesure.page";
	}
	
	@RequestMapping("/assign")
	public String assign(Integer bugId,Model model){		
		QualityBug bug = new QualityBug();
		bug = bugService.findById(bugId);
		//bug.setBugAssignedDate(new Date());
		model.addAttribute("bug", bug);
		return "/testManagement/page/tabledemo/assign.page";
	}
	
	@RequestMapping("/solve")
	public String solve(Integer bugId,Model model){
		QualityBug bug = new QualityBug();
		bug = bugService.findById(bugId);
		bug.setBugResolvedDate(new Date());
		model.addAttribute("bug", bug);
		return "/testManagement/page/tabledemo/solution.page";
	}
	
	@RequestMapping("/close")
	public String close(Integer bugId,Model model){
		QualityBug bug = new QualityBug();
		bug = bugService.findById(bugId);
		bug.setBugClosedDate(new Date());
		model.addAttribute("bug", bug);
		return "/testManagement/page/tabledemo/shutdown.page";
	}
	
	@RequestMapping("/edit")
	public String edit(Integer bugId,Model model){
		QualityBug bug = new QualityBug();
		bug = bugService.findById(bugId);
		model.addAttribute("bug", bug);
		return "/testManagement/page/tabledemo/edition.page";
	}
	
	@RequestMapping("/editionPaging")
	public String editionPaging(Integer bugId,Model model){
		QualityBug bug = new QualityBug();
		bug = bugService.findById(bugId);
		model.addAttribute("bug", bug);
		return "/testManagement/page/tabledemo/editionpaging.pagelet";
	}
			
	@RequestMapping("/add")
	public String add(){
		return "/testManagement/page/proposeBug.page";
	}
	
	@RequestMapping("/copy")
	public String copy(Integer bugId,Model model){
		QualityBug bug = new QualityBug();
		bug = bugService.findById(bugId);
		model.addAttribute("bug",bug);
		return "/testManagement/page/copyBug.page";
	}
	
	@RequestMapping(value = "/copySave",method = RequestMethod.POST)
	public String copySave(QualityBug bug,Model model){
		bug.setBugOpenedDate(new Date());
		bugService.addBug(bug);
		return "redirect:"+"/quality/bug"; 
	}
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String save(QualityBug bug,Model model){
		if(bug.getBugId() == null){
			bug.setBugOpenedDate(new Date());
			bugService.addBug(bug);			
		}else{
			bug.setBugLastEditedDate(new Date());
			bugService.updateBug(bug);
		}	
	//	model.addAttribute("bugsave",bug);
		return "redirect:"+"/quality/bug";
	}

	@RequestMapping("/projectFindList")
	public String projectFindList(Model model, Integer start, Integer limit, String order, String ordertype, HttpServletRequest request) {
		Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
		boolean asc = ordertype == "asc" ? true : false;
		QualityBug bug = new QualityBug();
		bug.setProjectId(projectId);
		Pager<QualityBug> page = bugService.findBugListPager(start, limit, bug, order, asc);
		model.addAttribute("bugPage", page);
		page.getRecords();
		page.getTotalCount();
		return "project/bug/bugViewTableData.pagelet";
	}
}
