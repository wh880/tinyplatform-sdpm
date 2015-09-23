package org.tinygroup.sdpm.action.quality;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.quality.dao.pojo.Bug;
import org.tinygroup.sdpm.quality.service.inter.BugService;

/**
 * Created by chenpeng15668 on 2015-9-22
 */

@Controller
@RequestMapping("/quality/bug")
public class BugAction extends BaseController {

	@Autowired
	private BugService bugService;
	
	@RequestMapping("/form")
	public String form(Integer id,Bug bug,Model model){
	//	Bug bug = new Bug();
		if(id != null){
			bug.setProductId(id);
			List<Bug> buglist = bugService.findBugList(bug);
			model.addAttribute("bug",buglist);
		}	
		return "testManagement/data/BugData.pagelet";
	}
	
	/*@RequestMapping("add")
	public String add(Bug bug,Model model){
		Bug bugadd =	bugService.addBug(bug);
		model.addAttribute("bugadd",bugadd);
		return "testManagement/page/proposeBug.page";
	}*/
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String save(Bug bug,Model model){
		if(bug.getBugId() == null){
			bugService.addBug(bug);
		}else{
			bugService.updateBug(bug);
		}	
		model.addAttribute("bugsave",bug);
		return "testManagement/page/Bug.page";
	}
}
