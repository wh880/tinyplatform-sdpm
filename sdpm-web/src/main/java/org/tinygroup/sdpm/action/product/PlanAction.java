package org.tinygroup.sdpm.action.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.impl.FieldUtil;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.service.PlanService;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.UserUtils;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 计划控制器
 * 
 * @author wangjie14929
 *
 */
@Controller
@RequestMapping("/a/product/plan")
public class PlanAction  extends BaseController{
	@Autowired
	private PlanService planService;
	
	@Autowired
	private ProductService productService;


	@RequestMapping("/content")
	public String release(HttpServletRequest request, Model model) {
		int productId = -1;
		if (request.getSession().getAttribute("sessionProductId") != null) {
			productId = (Integer) request.getSession().getAttribute("sessionProductId");
		}
		Product product = productService.findProduct(productId);
		model.addAttribute("product", product);
		return "/product/page/project/product-plan.page";
	}


	@RequestMapping("/save")
	public String save(ProductPlan productPlan, HttpServletRequest request, SystemAction systemAction) throws IOException {
		
		if(request.getSession().getAttribute("sessionProductId")!=null){
			productPlan.setProductId((Integer)(request.getSession().getAttribute("sessionProductId")));
		}
		productPlan.setDeleted(FieldUtil.DELETE_NO);
		ProductPlan productPlan1 = planService.addPlan(productPlan);

		LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCTPLAN
				, LogUtil.LogAction.OPENED
				, String.valueOf(productPlan1.getPlanId())
				, UserUtils.getUserId()
				, String.valueOf(productPlan1.getProductId())
				, null
				, null
				, null
				, systemAction.getActionComment());


		return "redirect:" + "/product/page/project/product-plan.page";
	}
	
	@RequestMapping("/update")
	public 	String update(ProductPlan plan,SystemAction systemAction) throws IOException {
		ProductPlan plan1 = planService.findPlan(plan.getPlanId());
		planService.updatePlan(plan);

		LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCTPLAN
				, LogUtil.LogAction.EDITED
				, String.valueOf(plan.getPlanId())
				, UserUtils.getUserId()
				, String.valueOf(plan.getProductId())
				, null
				, plan1
				, plan
				, systemAction.getActionComment());


		return " redirect:" + "/product/page/project/product-plan.page";
		
	}

	@RequestMapping("/addplan")
	public 	String addplan(HttpServletRequest request,Model model){

			int productId = -1;
			if (request.getSession().getAttribute("sessionProductId")!=null){
				productId = (Integer)request.getSession().getAttribute("sessionProductId");
			}
			Product product = productService.findProduct(productId);
			model.addAttribute("product",product);
		return "/product/page/tabledemo/product-addplan.page";

	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Map delete(Integer planId,SystemAction systemAction) throws IOException {
		ProductPlan plan1 = planService.findPlan(planId);
        planService.deletePlan(planId);
		ProductPlan plan = planService.findPlan(planId);
        Map<String, String> map = new HashMap<String, String>();

		LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCTPLAN
				, LogUtil.LogAction.DELETED
				, String.valueOf(planId)
				, UserUtils.getUserId()
				, String.valueOf(plan.getProductId())
				, null
				, plan1
				, plan
				, systemAction.getActionComment());


		map.put("status", "success");
        map.put("info", "删除成功");
        return map;
    }
	
	@RequestMapping("/find")
	public String find(HttpServletRequest request,Integer planId,Model model){

		int productId = -1;
		if(request.getSession().getAttribute("sessionProductId")!=null){
			productId = (Integer)request.getSession().getAttribute("sessionProductId");
		}
		Product product = productService.findProduct(productId)	;
		model.addAttribute("product",product);
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
		
		model.addAttribute("plan",planService.findPlan(planId));
		
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
		
		Pager<ProductPlan>  pagerProductPlan = null;
		if(request.getSession().getAttribute("sessionProductId")!=null){
			plan.setProductId((Integer)(request.getSession().getAttribute("sessionProductId")));
			plan.setDeleted(FieldUtil.DELETE_NO);
			pagerProductPlan = planService.findProductPlanPager(page, pagesize, plan, order, ordertype);
		}
		

		model.addAttribute("productPlan",pagerProductPlan);

		return "/product/data/allproduct-plan.pagelet";
	}

	@ResponseBody
    @RequestMapping("/planList")
    public List<ProductPlan> findPlan(ProductPlan plan){
    	
    	List<ProductPlan> list = planService.findPlanList(plan);
    	
    	return list;
    }

	
	@ResponseBody
	@RequestMapping(value="/batchDelete")
	public Map bctchDelPlan(String ids)
	{		
		Map<String,String> map = new HashMap<String,String>();
		if(ids == null){
			map.put("status", "fail");
		    map.put("info", "删除失败");
			return map;
		}
		 List<ProductPlan> list = new ArrayList<ProductPlan>();
		for(String s : ids.split(",")){			
			ProductPlan plan= new ProductPlan();
			plan.setPlanId(Integer.valueOf(s));
			plan.setDeleted(1);
			list.add(plan);
		}	
		planService.deleteBatchPlan(list);
		for(ProductPlan plan:list){
			LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCTPLAN
					, LogUtil.LogAction.DELETED
					,String.valueOf(plan.getProductId())
					,UserUtils.getUserId()
					,String.valueOf(plan.getProductId())
					,null
					,null
					,null
					,null);
		}
		map.put("status", "success");
	    map.put("info", "删除成功");
	    return map;
	}
	
}
