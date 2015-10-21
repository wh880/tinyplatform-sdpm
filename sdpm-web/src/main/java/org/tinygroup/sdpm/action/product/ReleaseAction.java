package org.tinygroup.sdpm.action.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.sdpm.action.system.ProfileUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductRelease;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.product.service.ReleaseService;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.UserUtils;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 发布控制器
 *
 * @author wangjie14929
 *
 */
@Controller
@RequestMapping("/a/product/release")
public class ReleaseAction extends BaseController{

	@Autowired
	private ReleaseService releaseService;
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
		return "/product/page/project/product-release.page";
	}


	@RequestMapping("/save")
	public String save(ProductRelease productRelease, HttpServletRequest request, SystemAction systemAction, @RequestParam(value = "file", required = false) MultipartFile[] file, String[] title) throws IOException {



		productRelease.setProductId((Integer)(request.getSession().getAttribute("sessionProductId")));
		ProductRelease release = releaseService.addRelease(productRelease);
		ProfileUtil profileUtil = new ProfileUtil();
		profileUtil.uploads(file, release.getReleaseId(), "release", title);

		LogUtil.logWithComment(LogUtil.LogOperateObject.RELEASE
				, LogUtil.LogAction.OPENED
				, String.valueOf(release.getReleaseId())
				, UserUtils.getUserId()
				, String.valueOf(release.getProductId())
				, null
				, null
				, null
				, systemAction.getActionComment());

		return "redirect:" + "/product/page/project/product-release.page";
	}
	
	@RequestMapping("/update")
	public String update(ProductRelease release, SystemAction systemAction, @RequestParam(value = "file", required = false) MultipartFile[] file, String[] title) throws IOException {
		ProductRelease release1 = releaseService.findRelease(release.getReleaseId());
		releaseService.updateRelease(release);

		ProfileUtil profileUtil = new ProfileUtil();

		profileUtil.uploads(file, release.getReleaseId(), "release", title);

		LogUtil.logWithComment(LogUtil.LogOperateObject.RELEASE
				, LogUtil.LogAction.EDITED
				, String.valueOf(release.getReleaseId())
				, UserUtils.getUserId()
				, String.valueOf(release.getProductId())
				, null
				, release1
				, release
				, systemAction.getActionComment());

		return "redirect:" + "/product/page/project/product-release.page";
	}
	
	
	@ResponseBody
	@RequestMapping("/delete")
	public Map delete(Integer releaseId,SystemAction systemAction) throws IOException {

		ProductRelease release1 = releaseService.findRelease(releaseId);
		releaseService.deleteRelease(releaseId);
		ProductRelease release = releaseService.findRelease(releaseId);
		Map<String,String> map = new HashMap<String, String>();


		LogUtil.logWithComment(LogUtil.LogOperateObject.RELEASE
				, LogUtil.LogAction.DELETED
				, String.valueOf(releaseId)
				, UserUtils.getUserId()
				, String.valueOf(release.getProductId())
				, null
				, release1
				, release
				, systemAction.getActionComment());

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
		
		if(request.getSession().getAttribute("sessionProductId")!=null){
			release.setProductId((Integer)(request.getSession().getAttribute("sessionProductId")));
		}
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

	@RequestMapping("/addRelease")
	public String addRelease(HttpServletRequest request, Model model) {
		int productId = -1;
		if (request.getSession().getAttribute("sessionProductId") != null) {
			productId = (Integer) request.getSession().getAttribute("sessionProductId");
		}
		Product product = productService.findProduct(productId);
		model.addAttribute("product", product);
		return "/product/page/tabledemo/product-addrelease.page";
	}
}
