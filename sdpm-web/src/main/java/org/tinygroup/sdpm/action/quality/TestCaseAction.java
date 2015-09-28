package org.tinygroup.sdpm.action.quality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;
import org.tinygroup.sdpm.quality.service.inter.TestCaseService;
import org.tinygroup.tinysqldsl.Pager;

/**
 * Created by chenpeng15668 on 2015-9-24
 */

@Controller
@RequestMapping("quality/testcase")
public class TestCaseAction extends BaseController {
	
	@Autowired
	private TestCaseService testCaseService;
	
	@RequestMapping
	public String form(Integer get){
		return "testManagement/page/cases.page";
	}
	
	@RequestMapping("findPager")
	public String findPager(Integer start,Integer limit,String order,String ordertype,QualityTestCase testcase,Model model){
		boolean asc = true;		
		if("desc".equals(ordertype)){
			asc = false;
		}
		Pager<QualityTestCase> casepager = testCaseService.findTestCasePager(start, limit, testcase, order,asc);
		model.addAttribute("casepager",casepager);
		return "testManagement/data/BugData.pagelet";
	}
	
	@RequestMapping("add")
	public String add(){
		return "testManagement/page/proposecase.page";
	}
	
	@RequestMapping(value = "save",method = RequestMethod.POST)
	public String save(QualityTestCase testcase,Model model){
		if(testcase.getCaseId() == null){
			testCaseService.addTestCase(testcase);
		}else{
			testCaseService.updateTestCase(testcase);
		}
		model.addAttribute("testcase", testcase);
		return "redirect:"+"quality/testcase";
	}
	
	

}
