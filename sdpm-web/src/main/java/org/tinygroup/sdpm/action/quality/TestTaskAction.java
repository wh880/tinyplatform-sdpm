package org.tinygroup.sdpm.action.quality;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.action.quality.util.QualityUtil;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.ComplexSearch.SqlUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestTask;
import org.tinygroup.sdpm.quality.service.inter.TestTaskService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by chenpeng15668 on 2015-9-24
 */

@Controller
@RequestMapping("/a/quality/version")
public class TestTaskAction extends BaseController {
	
	@Autowired
	private TestTaskService testTaskService;
	@Autowired
	private ProductService productService;

	
	@RequestMapping("")
	public String form(HttpServletRequest request,String get,Model model){
		List<Product> list = (List<Product>) request.getSession().getAttribute("qualityProductList");
		String queryString = request.getQueryString();
		if(list == null|| list.size()==0){
			list = productService.findProductList(new Product(),"productId","desc");
			request.getSession().setAttribute("qualityProductList",list);
		}
		if(null==request.getSession().getAttribute("qualityProductId")||""==request.getSession().getAttribute("qualityProductId")){
			request.getSession().setAttribute("qualityProductId",list.size()>0?list.get(0).getProductId():0);
		}else{
			request.getSession().removeAttribute("qualityProductId");
			request.getSession().setAttribute("qualityProductId",list.size()>0?list.get(0).getProductId():0);
		}

//		if(queryString!=null&&!queryString.contains("status")){
//			if(!queryString.contains("currentPageId"))queryString = queryString+"&currentPageId=5";
//			return "redirect:/a/quality/bug?status=tbugstatus&"+queryString;
//		}
//		if(StringUtil.isBlank(queryString)){
//			return "redirect:/a/quality/bug?status=tbugstatus&currentPageId=5";
//		}
		return "/testManagement/page/version.page";
	}
	
	@RequestMapping("/findPager")
	public String findPager(Integer start, Integer limit, SearchInfos infos, String groupOperate, String order, String ordertype, String status, QualityTestTask testtask, Model model, HttpServletRequest request){
		boolean asc = true;
		if("desc".equals(ordertype)){
			asc = false;
		}
		String conditions = "";
		conditions = StringUtil.isBlank(SqlUtil.toSql(infos.getInfos(),groupOperate))?
				conditions:(StringUtil.isBlank(conditions)?SqlUtil.toSql(infos.getInfos(),groupOperate):conditions+" and "+ SqlUtil.toSql(infos.getInfos(),groupOperate));
		testtask.setProductId((Integer) request.getSession().getAttribute("qualityProductId"));
		Pager<QualityTestTask> verpager = testTaskService.findTestTaskPager(start, limit, testtask,conditions, order, asc);
		model.addAttribute("verPager",verpager);
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
		return "redirect:"+"/a/quality/version";
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
	public String edit(Integer testversionId,Model model){
		QualityTestTask testTask = testTaskService.findById(testversionId);
		model.addAttribute("testTask",testTask);	
		return "/testManagement/page/tabledemo/editionversion.page";
	}
}
