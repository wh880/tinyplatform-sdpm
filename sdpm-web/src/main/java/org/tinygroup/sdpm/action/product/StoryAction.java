package org.tinygroup.sdpm.action.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.convert.objectxml.xstream.ObjectToXml;
import org.tinygroup.sdpm.action.product.util.StoryUtil;
import org.tinygroup.sdpm.common.util.sql.SearchInfos;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.product.service.StoryService;
import org.tinygroup.sdpm.product.service.StorySpecService;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

@Controller
@RequestMapping("product/story")
public class StoryAction {
    @Autowired
    private StoryService storyService;
    @Autowired
    private StorySpecService storySpecService;
   
    @RequestMapping("")
    public String storyAction(ProductStory story, String groupOperate, Model model, HttpServletRequest request, HttpServletResponse response){
        String queryString = request.getQueryString();
       if(queryString!=null&&!queryString.contains("choose")){
            return "redirect:/product/story?choose=1&"+queryString;
        }
        return "product/page/project/togglebox.page";
    }
    
    @RequestMapping("save")
    public String save(ProductStory productStory){
    	
    	storyService.addStory(productStory);
    	return "redirect:" + "/product/page/project/togglebox.page";
    }
    
    public String update(ProductStory productStory){
    	
    	storyService.updateStory(productStory);
    	return "redirect:" + "/product/page/project/togglebox.page";
    }
    
    public String updateBatch(List<ProductStory> stories){
    	
    	storyService.updateBatchStory(stories);
    	return "";
    }
    
    @RequestMapping("find")
    public String find(Integer storyId,Model model){
    	
    	ProductStory productStory = storyService.findStory(storyId);
    	ProductStorySpec productStorySpec = storySpecService.findStorySpec(storyId);
    	model.addAttribute("story", productStory);
    	model.addAttribute("storySpec", productStorySpec);
    	return "/product/page/tabledemo/demand-edit.page";
    }
    
    @RequestMapping("/search")
    public String storySearchAction(int page, int pagesize, ProductStory story, String choose, String groupOperate, SearchInfos searchInfos, String order, String ordertype, Model model, HttpServletRequest request){
        Pager<ProductStory> p = storyService.findStoryPager(pagesize*(page - 1),pagesize,story, StoryUtil.getStatusCondition(choose,request),searchInfos,groupOperate,order,"asc".equals(ordertype)?true:false);
        model.addAttribute("storyList",p);
        return "product/data/tabledata.pagelet";
    }
}
