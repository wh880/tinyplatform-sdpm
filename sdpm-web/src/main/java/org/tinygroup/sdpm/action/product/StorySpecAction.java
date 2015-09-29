package org.tinygroup.sdpm.action.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.sdpm.product.service.StorySpecService;

@Controller
@RequestMapping("product/storySpec")
public class StorySpecAction extends BaseController{
	
	@Autowired
	private StorySpecService specService;
	
	@RequestMapping("find")
	public String find(Integer storyId,Model model){
		
		ProductStorySpec storySpec = specService.findStorySpec(storyId);
		model.addAttribute("storySpec", storySpec);
		return "/product/page/tabledemo/demand-edit.page";
	}
	
}
