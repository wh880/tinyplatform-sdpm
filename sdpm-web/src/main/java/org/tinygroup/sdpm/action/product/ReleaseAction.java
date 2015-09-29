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
@RequestMapping("product/release")
public class ReleaseAction extends BaseController{

	@Autowired
	private ReleaseService releaseService;
	
	@RequestMapping("/save")
	public  String save(ProductRelease productRelease){
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
		releaseService.deletePlan(releaseId);
		Map<String,String> map = new HashMap<String, String>();
		map.put("status", "sucess");
		map.put("info","删除成功");
		return map;
	}
	
	@RequestMapping("find")
		public String find(Integer releaseId,Model model){
			ProductRelease release = releaseService.findRelease(releaseId);
			model.addAttribute("productRelease",release);
			return "redirect:" + "/product/page/project/product-release.page";
		}
	
	@RequestMapping("list")
		public String list(ProductRelease release,
		@RequestParam(required=false,defaultValue="1")int page,
		@RequestParam(required=false,defaultValue="10")int pagesize,
		@RequestParam(required=false,defaultValue="releaseId")String order,
		@RequestParam(required=false,defaultValue="asc")String ordertype,Model model ){
		
		Pager<ProductRelease> pagerProductRelease = releaseService.findReleasePager(page, pagesize, release, order, ordertype);
		
		model.addAttribute("productRelease",pagerProductRelease);
		return "/product/data/allproduct-release.pagelet";
		}
}

