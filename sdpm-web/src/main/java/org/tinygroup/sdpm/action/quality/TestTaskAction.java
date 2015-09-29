package org.tinygroup.sdpm.action.quality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestTask;
import org.tinygroup.sdpm.quality.service.inter.TestTaskService;
import org.tinygroup.tinysqldsl.Pager;

/**
 * Created by chenpeng15668 on 2015-9-24
 */

@Controller
@RequestMapping("quality/testTask")
public class TestTaskAction extends BaseController {
	
	@Autowired
	private TestTaskService testTaskService;
	
	@RequestMapping
	public String form(String get,Model model){
		return "/testManagement/page/version.page";
	}
	
	@RequestMapping("/findPager")
	public String findPager(Integer start,Integer limit,String order,String ordertype,QualityTestTask testtask,Model model){
		boolean asc = true;
		if("desc".equals(ordertype)){
			asc = false;
		}
		Pager<QualityTestTask> testtaskpager = testTaskService.findTestTaskPager(start, limit, testtask, order, asc);
		model.addAttribute("testtaskpager",testtaskpager);
		return "/testManagement/data/versionData.pagelet";
	}
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String save(QualityTestTask testtask,Model model){
		if(testtask.getTestversionId() == null){
			testTaskService.addTestTask(testtask);
		}else{
			testTaskService.updateTestTask(testtask);
		}
		model.addAttribute("testtask",testtask);
		return "redirect:"+"quality/testTask";
	}
	
	@RequestMapping("/add")
	public String add(){
		return "/testManagement/page/proposeversion.page";
	}
	
	@RequestMapping("/versionInfo")
	public String versionInfo(){
		return "/testManagement/page/versionSituation.page";
	}
	
	@RequestMapping("/linkCase")
	public String linkCase(){
		return "/testManagement/page/versionLink.page";
	}
	
	@RequestMapping("/edit")
	public String edit(){
		return "/testManagement/page/tabledemo/editionversion.page";
	}
}
