package org.tinygroup.sdpm.action.productLine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.productLine.service.ProductLineService;
import org.tinygroup.tinysqldsl.Pager;

/**
 * 产品线控制器
 * @author wangjie14929
 *
 */
@Controller
@RequestMapping("/productLine")
public class ProductLineAction extends BaseController {
	
	@Autowired
	private ProductLineService productLineService;
/*	
	@RequestMapping("index")
	public String index(@CookieValue(required=false) Integer cookie_productLineId,HttpServletRequest request,HttpServletResponse response,Model model){
		List<ProductLine> list = productLineService.findlist(productLine);
	}
	*/
	
	
	@RequestMapping("/save")
	public String save(ProductLine productLine,Model model){
		productLineService.addProductLine(productLine);
		return "redirect:" + "/productLine/page/tabledemo/list.page";
	}
	
	@RequestMapping("/update")
	public String update(ProductLine productLine){
		productLineService.updateProductLine(productLine);
		return "redirect:" + "/productLine/page/tabledemo/list.page";
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Map delete(Integer productLineId) {
		productLineService.deleteProductLine(productLineId);
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "删除成功");
        return map;
    }
	
	@RequestMapping("/find")
	public String find(Integer productLineId,Model model){
		ProductLine productLine = productLineService.findProductLine(productLineId);
		model.addAttribute("productLine",productLine);
		return "/productLine/page/tabledemo/update.page";
	}
	
	@RequestMapping("/list")
	public String list(ProductLine productLine,
			@RequestParam(required=false,defaultValue="1")int page,
			@RequestParam(required=false,defaultValue="10")int pagesize,
			@RequestParam(required=false,defaultValue="productLineId")String order,
			@RequestParam(required=false,defaultValue="asc")String ordertype,Model model){
		
		Pager<ProductLine> pagerProductLine = productLineService.findProductLinePager(page, pagesize, productLine, order, ordertype);
		
		model.addAttribute("productLine",pagerProductLine);
		return "/productLine/data/productLinedata.pagelet";
	}

}
