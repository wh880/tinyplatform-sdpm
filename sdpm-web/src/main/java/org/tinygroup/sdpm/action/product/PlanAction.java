package org.tinygroup.sdpm.action.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
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
	public String save(ProductPlan productPlan, Model model,HttpServletRequest request) {
		
		productPlan.setProductId((Integer)(request.getSession().getAttribute("sessionProductId")));
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
	
	@RequestMapping("/find/{forwordPager}")
	public String find(@PathVariable(value="forwordPager")String forwordPager,Integer planId,Model model){
		
		ProductPlan plan = planService.findPlan(planId);
		
		model.addAttribute("plan",plan);
		
		
		
		if("relateStory".equals(forwordPager)){
			return "/product/page/tabledemo/relation-plan/planbaseinfo.pagelet";
		}
		
		return "/product/page/tabledemo/relation-plan/planbaseinfo.pagelet";
	}
	
	@RequestMapping("/forword/{forwordPager}")
	public String forword(@PathVariable(value="forwordPager")String forwordPager,Integer planId,Model model){
		
		model.addAttribute("planId",planId);
		
		if ("reRelateStory".equals(forwordPager)) {
			return "/product/page/tabledemo/relation-plan/product-al-req.page";
		}else if("noRelateStory".equals(forwordPager)){
			return "/product/page/tabledemo/relation-plan/product-al-no-req.page";
		}else if ("reRelateBug".equals(forwordPager)) {
			return "product/page/tabledemo/relation-plan/product-al-bug.page";
		}else if ("noRelateBug".equals(forwordPager)) {
			return "product/page/tabledemo/relation-plan/product-al-no-bug.page";
		}
		
		
		
		return "/product/page/tabledemo/relation-plan/planbaseinfo.pagelet";
	}

	@RequestMapping("/list")
	public String list(ProductPlan plan,
			@RequestParam(required = false,defaultValue = "1")int page,
			@RequestParam(required = false,defaultValue = "10")int pagesize,
			@RequestParam(required = false,defaultValue = "planId")String order,
			@RequestParam(required = false,defaultValue = "asc")String ordertype,Model model,HttpServletRequest request){
		
		if(request.getSession().getAttribute("sessionProductId")!=null){
			plan.setProductId((Integer)(request.getSession().getAttribute("sessionProductId")));
		}
		
		Pager<ProductPlan>  pagerProductPlan = planService.findProductPlanPager(page, pagesize, plan, order, ordertype);

		model.addAttribute("productPlan",pagerProductPlan);

		return "/product/data/allproduct-plan.pagelet";
	}

	@ResponseBody
    @RequestMapping("/planList")
    public List<ProductPlan> findPlan(ProductPlan plan){
    	
    	List<ProductPlan> list = planService.findPlanList(plan);
    	
    	return list;
    }

	
}
