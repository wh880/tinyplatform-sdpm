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
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.dao.pojo.ProductRelease;
import org.tinygroup.sdpm.product.service.ReleaseService;
import org.tinygroup.tinysqldsl.Pager;


/**
 * 发布控制器
 *
 * @author wangjie14929
 *
 */
@Controller
@RequestMapping("/product/release")
public class ReleaseAction extends BaseController{

	@Autowired
	private ReleaseService releaseService;
	
	@RequestMapping("/save")
	public  String save(ProductRelease productRelease,Model model,HttpServletRequest request){
		
		productRelease.setProductId((Integer)(request.getSession().getAttribute("sessionProductId")));
		releaseService.addRelease(productRelease);
		return "redirect:" + "/product/page/project/product-release.page";
	}
	
	@RequestMapping("/update")
	public String update(ProductRelease release){
		releaseService.updateRelease(release);
		return "redirect:" + "/product/page/project/product-release.page";
	}
	
	
	@ResponseBody
	@RequestMapping("/delete")
	public Map delete(Integer releaseId){
		releaseService.deleteRelease(releaseId);
		Map<String,String> map = new HashMap<String, String>();
		map.put("status", "success");
		map.put("info","删除成功");
		return map;
	}
	
	@RequestMapping("find")
		public String find(Integer releaseId,Model model){
			ProductRelease release = releaseService.findRelease(releaseId);
			model.addAttribute("release",release);
			return  "/product/page/tabledemo/product-release-update.page";
		}
	
	
	@RequestMapping("/find/{forwordPager}")
	public String find(@PathVariable(value="forwordPager")String forwordPager,Integer releaseId,Model model){
		
		ProductRelease release = releaseService.findRelease(releaseId);
		model.addAttribute("release",release);
		if("relateStory".equals(forwordPager)){
			return "/product/page/tabledemo/relation-release/releasebaseinfo.pagelet";
		}
		
		return "";
	}
	
	@RequestMapping("/forword/{forwordPager}")
	public String forword(@PathVariable(value="forwordPager")String forwordPager,Integer releaseId,Model model){
		
		model.addAttribute("releaseId",releaseId);
		
		if ("reRelateStory".equals(forwordPager)) {
			return "/product/page/tabledemo/relation-release/product-al-req.page";
		}else if("noRelateStory".equals(forwordPager)){
			return "/product/page/tabledemo/relation-release/product-al-no-req.page";
		}else if ("reRelateBug".equals(forwordPager)) {
			return "/product/page/tabledemo/relation-release/product-al-bug.page";
		}else if ("noRelateBug".equals(forwordPager)) {
			return "/product/page/tabledemo/relation-release/product-al-no-bug.page";
		}
		else if ("leRelateBug".equals(forwordPager)) {
			return "product/page/tabledemo/relation-release/product-al-le-bug.page";
		}
		
		
		
		return "/product/page/tabledemo/relation-plan/planbaseinfo.pagelet";
	}
	
	@RequestMapping("list")
		public String list(ProductRelease release,
		@RequestParam(required=false,defaultValue="1")int page,
		@RequestParam(required=false,defaultValue="10")int pagesize,
		@RequestParam(required=false,defaultValue="releaseId")String order,
		@RequestParam(required=false,defaultValue="asc")String ordertype,Model model,HttpServletRequest request ){
		
		release.setProductId((Integer)(request.getSession().getAttribute("sessionProductId")));
		Pager<ProductRelease> pagerProductRelease = releaseService.findReleasePager(page, pagesize, release, order, ordertype);
		
		model.addAttribute("productRelease",pagerProductRelease);
		return "/product/data/allproduct-release.pagelet";
		}

	@ResponseBody
	@RequestMapping("/releaseList")
	public List<ProductRelease> findRelease(ProductRelease release){
		List<ProductRelease> list = releaseService.findReleaseList(release);
		return list;
	}
}

