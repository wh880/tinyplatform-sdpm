package org.tinygroup.sdpm.action.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.sdpm.product.service.StoryService;
import org.tinygroup.sdpm.product.service.StorySpecService;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.inter.ProfileService;

import java.util.List;

@Controller
@RequestMapping("/a/product/storySpec")
public class StorySpecAction extends BaseController{
	
	@Autowired
	private StorySpecService specService;
	@Autowired
	private ProfileService profileService;
	@Autowired
	private StoryService storyService;
	
	@RequestMapping("/find")
	public String find(Integer storyId,Model model){
		
		ProductStorySpec storySpec = specService.findStorySpec(storyId);
		model.addAttribute("storySpec", storySpec);
		return "/product/page/tabledemo/demand-edit.page";
	}
	
	@RequestMapping("/find/{forward}")
	public String find(@PathVariable(value="forward")String forward,ProductStory story,Model model,SystemProfile systemProfile){
		
		ProductStorySpec storySpec = specService.findStorySpec(story.getStoryId());
		ProductStory productStory = storyService.findStory(story.getStoryId());
		model.addAttribute("storySpec", storySpec);
		model.addAttribute("story", productStory);
		systemProfile.setFileObjectType("story");
		systemProfile.setFileDeleted("0");
		systemProfile.setFileObjectId(story.getStoryId());
		List<SystemProfile> list = profileService.find(systemProfile);
		model.addAttribute("file",list);
		if ("productDemandDetail".equals(forward)) {
			
			return "/product/page/project/demdtablehref.page";
		}
		return "";
	}
	
}
