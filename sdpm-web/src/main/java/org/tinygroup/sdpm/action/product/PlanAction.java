package org.tinygroup.sdpm.action.product;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.service.PlanService;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.tinysqldsl.Pager;

/**
 * 计划控制器
 * 
 * @author wangjie14929
 *
 */
@Controller
@RequestMapping("/product/plan")
public class PlanAction  extends BaseController{
	@Autowired
	private PlanService planService;
	
	@Autowired
	private ProductService productService;

	@RequestMapping("/save")
	public String save(ProductPlan productPlan, Model model) {
		planService.addPlan(productPlan);
		return "redirect:" + "/product/page/project/product-plan.page";
	}
	
	@RequestMapping("/update")
	public 	String update(ProductPlan plan){
		
		planService.updatePlan(plan);
		return "redirect:" + "/product/page/project/product-plan.page";
		
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Map delete(Integer planId) {
        planService.deletePlan(planId);
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "删除成功");
        return map;
    }
	
	@RequestMapping("/find")
	public String find(Integer planId,Model model){
		
		ProductPlan plan = planService.findPlan(planId);
		
		model.addAttribute("plan",plan);
		
		return "/product/page/tabledemo/product-plan-update.page";
	}

	@RequestMapping("/list")
	public String list(ProductPlan plan,

			@RequestParam(required = false,defaultValue = "1")int page,
			@RequestParam(required = false,defaultValue = "10")int pagesize,
			@RequestParam(required = false,defaultValue = "planId")String order,
			@RequestParam(required = false,defaultValue = "asc")String ordertype,Model model){

		Pager<ProductPlan>  pagerProductPlan = planService.findProductPlanPager(page, pagesize, plan, order, ordertype);

		model.addAttribute("productPlan",pagerProductPlan);

		return "/product/data/allproduct-plan.pagelet";
	}

	

	
}
