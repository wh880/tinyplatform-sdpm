package org.tinygroup.sdpm.action.quality;

import java.util.List;

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
	
	@RequestMapping("")
	public String form(String get,QualityTestCase testCase,Model model){
		testCaseService.findTestCaseList(testCase);
		model.addAttribute("get", get);
		model.addAttribute("testCase", testCase);
		return "testManagement/page/cases.page";
	}	
	
	@RequestMapping("/findPager")
	public String findPager(Integer start,Integer limit,String order,String ordertype,QualityTestCase testcase,Model model){
		boolean asc = true;		
		if("desc".equals(ordertype)){
			asc = false;
		}
		Pager<QualityTestCase> casepager = testCaseService.findTestCasePager(start, limit, testcase, order,asc);
		model.addAttribute("casepager",casepager);
		return "testManagement/data/BugData.pagelet";
	}
	
	@RequestMapping("/add")
	public String add(){
		return "testManagement/page/proposecase.page";
	}
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String save(QualityTestCase testcase,Model model){
		if(testcase.getCaseId() == null){
			testCaseService.addTestCase(testcase);
		}else{
			testCaseService.updateTestCase(testcase);
		}
		model.addAttribute("testcase", testcase);
		return "redirect:"+"quality/testcase";
	}
	
	@RequestMapping(value = "/batchSave",method = RequestMethod.POST)
	public String batchSave(List<QualityTestCase> testcases,Model model){
		testCaseService.batchUpdateTestCase(testcases);
		model.addAttribute("testcases",testcases);
		return "redirect:"+"quality/testcase";
	}
	
	@RequestMapping("/execution")
	public String execution(Integer caseId,Model model){
		 QualityTestCase testcase = testCaseService.findById(caseId);
		 model.addAttribute("testcase", testcase);
		return "/testManagement/page/tabledemo/execution.pagelet";
	}
	
	//预留，需要新增一个页面
	@RequestMapping("/batchExecution")
	public String batchExecution(){
		return "";
	}
	
	@RequestMapping("/result")
	public String result(Integer caseId,Model model){
		QualityTestCase testCase = testCaseService.findById(caseId);
		model.addAttribute("testCase", testCase);
		return "/testManagement/page/tabledemo/result.pagelet";
	}
	
	@RequestMapping("/edit")
	public String edit(Integer caseId,Model model){
		QualityTestCase testCase = testCaseService.findById(caseId);
		model.addAttribute("testCase", testCase);
		return "/testManagement/page/tabledemo/editioncase.page";
	}
	
	@RequestMapping("/copy")
	public String copy(Integer caseId,Model model){
		QualityTestCase testCase = testCaseService.findById(caseId);
		model.addAttribute("testCase", testCase);
		return "/testManagement/page/copyCase.page";
	}
	
	@RequestMapping(value = "/copySave",method = RequestMethod.POST)
	public String copySave(QualityTestCase testCase,Model model){
		testCaseService.addTestCase(testCase);
		return "redirect:"+"quality/testcase";
	}
	
	@RequestMapping("/delete")
	public String delete(Integer testCaseId,Model model){
		testCaseService.deleteById(testCaseId);
		return "redirect:"+"quality/testcase";
	}
	
	@RequestMapping("/batchDelete")
	public String batchDelete(List<QualityTestCase> testcases,Model model){
		testCaseService.batchDeleteTestCase(testcases);
		return "redirect"+"quality/testcase";
	}
}
