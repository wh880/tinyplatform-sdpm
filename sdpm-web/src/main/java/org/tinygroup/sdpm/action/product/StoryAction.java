package org.tinygroup.sdpm.action.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.sdpm.action.product.util.StoryUtil;
import org.tinygroup.sdpm.action.system.ProfileUtil;
import org.tinygroup.sdpm.common.log.LogPrepareUtil;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.ComplexSearch.SqlUtil;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.product.dao.impl.FieldUtil;
import org.tinygroup.sdpm.product.dao.pojo.*;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.product.service.StoryService;
import org.tinygroup.sdpm.product.service.StorySpecService;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.quality.service.inter.BugService;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.sdpm.util.ModuleUtil;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


@Controller
@RequestMapping("/a/product/story")
public class StoryAction extends BaseController{
    @Autowired
    private StoryService storyService;
    @Autowired
    private StorySpecService storySpecService;
    @Autowired
	private BugService bugService;
    @Autowired
    private ModuleService moduleService;
	@Autowired
	private ProductService productService;
    
    
   
    @RequestMapping("")
    public String storyAction(ProductStory story, String groupOperate, Model model, HttpServletRequest request, HttpServletResponse response){
      
    	
    	String queryString = request.getQueryString();
       if(queryString!=null&&!queryString.contains("choose")){
		   return "redirect:" + adminPath + "/product/story?choose=1&" + queryString;
	   }
        return "/product/page/project/togglebox.page";
    }

	@RequestMapping("/addstory")
	public String addstory(HttpServletRequest request,Model model){
		int productId = -1;
		if (request.getSession().getAttribute("sessionProductId")!=null){
			productId = (Integer)request.getSession().getAttribute("sessionProductId");
		}
		Product product = productService.findProduct(productId);
		model.addAttribute("product",product);

		return "/product/page/tabledemo/product-demand-add.page";
	}


   @RequestMapping("/save")
    public String save(ProductStory productStory,ProductStorySpec storySpec,@RequestParam(value = "file", required = false)MultipartFile[] file,String[] title,HttpServletRequest request) throws IOException{
    	
    	
    	productStory.setProductId((Integer)(request.getSession().getAttribute("sessionProductId")));
    	ProductStory story=storyService.addStory(productStory, storySpec);
    	 ProfileUtil profileUtil = new ProfileUtil();
    	 
         profileUtil.uploads(file, story.getStoryId(), "story", title);
    	return "redirect:" + "/product/page/project/togglebox.page";
    }
    
    @RequestMapping("/update")
    public String update(ProductStory productStory){
        ProductStory story = storyService.findStory(productStory.getStoryId());
    	storyService.updateStory(productStory);
        OrgUser user = (OrgUser) LogPrepareUtil.getSession().getAttribute("user");
        SystemAction action = new SystemAction();
        action.setActionObjectId(String.valueOf(productStory.getStoryId()));
        action.setActionProduct(String.valueOf(story.getProductId()));
        action.setActionObjectType("story");
        action.setActionAction("edit");
        action.setActionActor(user != null?user.getOrgUserId():"0");
        logService.log(story,productStory,action);
    	return "redirect:" + "/product/page/project/togglebox.page";
    }

    @RequestMapping("/list")
    public String list(ProductStory productStory, Model model) {
        return "/product/page/project/togglebox.page";
    }
    
    @RequestMapping("/list/data")
    public String listData(Integer moduleId,Integer start,Integer limit,ProductStory productStory,Model model){
    	if (moduleId == null || moduleId == -1){
    		
    	}else{
    		
    	}
    	return "";
    }
    

    @ResponseBody
    @RequestMapping("/updateBatch")
    public int[] updateBatch(@RequestBody ProductStory[] stories){
    	List<ProductStory>  productStories = new ArrayList<ProductStory>();
    	if(stories!=null&&stories.length>0){
    		productStories = Arrays.asList(stories);
    	}
    	return storyService.updateBatchStory(productStories);
    }
    
    @RequestMapping("/closeBatch")
    public String closeBatch(StoryCollection stories){
    	
    	storyService.updateBatchStory(stories.getProductStories());
		return "redirect:" + adminPath + "/product/story?currentPageId=3";
	}
    
	@ResponseBody
	@RequestMapping("/delete")
	public Map delete(Integer storyId) {
		storyService.deleteStory(storyId);
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "删除成功");
        return map;
    }
	
    @RequestMapping("/find")
    public String find(Integer storyId,Model model){
    	
    	ProductStory productStory = storyService.findStory(storyId);
    	model.addAttribute("story", productStory);
    	return "/product/page/tabledemo/editbaseinfo.pagelet";
    }
    
	@RequestMapping("/findByKeys")
	public String findByKeys(Integer[] storyId,Model model){
		//storyId =new  Integer[]{33,34,35,36};
		List<ProductStory> storyList = storyService.findStoryList(storyId);
		model.addAttribute("storyList", storyList);
		return "/product/page/tabledemo/product-demand-del.pagelet";
	}
    
    @RequestMapping("/{forwordPager}/findPager")
    public String find(Integer storyId,@PathVariable(value="forwordPager")String forwordPager,Model model){
    	
    	ProductStory productStory = storyService.findStory(storyId);
    	ProductStorySpec storySpec = storySpecService.findStorySpec(storyId);
    	model.addAttribute("story", productStory);
    	model.addAttribute("storySpec", storySpec);
    	
    	if("productDemandClose".equals(forwordPager)){
    		
    		return "/product/page/tabledemo/product-demand-close.page";
    	}else if ("productDemandReview".equals(forwordPager)) {
    		
    		return "/product/page/tabledemo/product-demand-review.page";
		}else if ("productDemandChange".equals(forwordPager)) {
			
			return "/product/page/tabledemo/product-demand-change.page";
		}else if ("productDemandDetail".equals(forwordPager)) {
			
			return "/product/page/tabledemo/hrefbaseinfo.pagelet";
		}
    	
    	return "";
    }
    
    @RequestMapping("/search")
    public String storySearchAction(int page, int pagesize, ProductStory story, String choose, String groupOperate, SearchInfos searchInfos, String order, String ordertype, Model model, HttpServletRequest request){
        
    	if(request.getSession().getAttribute("sessionProductId")!=null){
    		story.setProductId((Integer)(request.getSession().getAttribute("sessionProductId")));
    	}
    	/*if (story.getModuleId()==-1) {
    		story.setModuleId(null);
		}*/
    	 
    	
    	String condition = StoryUtil.getStatusCondition(choose,request);
    	if(story.getModuleId()!=null&&story.getModuleId()>0){
    		
    		 SystemModule module = new SystemModule();
	       	 module.setModuleId(story.getModuleId());
	   		 StringBuffer stringBuffer = new StringBuffer();
	   		 stringBuffer.append("in (");
	   	     ModuleUtil.getConditionByModule(stringBuffer, module, moduleService);
	   	     stringBuffer.append(")");
	   	     
    		condition = condition + " and " + NameUtil.resolveNameDesc("moduleId") + " " + stringBuffer.toString();
    	}
    	story.setModuleId(null);
    	Pager<ProductStory> p = storyService.findStoryPager(pagesize*(page - 1),pagesize,story, condition,searchInfos,groupOperate,order,"asc".equals(ordertype)?true:false);
        model.addAttribute("storyList",p);
        return "product/data/tabledata.pagelet";
    }
    
    @RequestMapping("/search/{relate}")
    public String storyListAction(@PathVariable(value="relate")String relate,int page, int pagesize,
    		ProductStory story, String choose, String groupOperate, SearchInfos searchInfos,
    		@RequestParam(required = false, defaultValue = "storyId") String order, 
    		@RequestParam(required = false, defaultValue = "asc") String ordertype,
    		Model model, HttpServletRequest request){
    	
    	 
    	if(request.getSession().getAttribute("sessionProductId")!=null){
    		story.setProductId((Integer)(request.getSession().getAttribute("sessionProductId")));
    	}
        
    	
    	
    	String condition = StoryUtil.getStatusCondition(choose,request);
    	if(story.getModuleId()!=null&&story.getModuleId()>0){
    		
    		 SystemModule module = new SystemModule();
	       	 module.setModuleId(story.getModuleId());
	   		 StringBuffer stringBuffer = new StringBuffer();
	   		 stringBuffer.append("in (");
	   	     ModuleUtil.getConditionByModule(stringBuffer, module, moduleService);
	   	     stringBuffer.append(")");
	   	     
    		condition = condition + " and " + NameUtil.resolveNameDesc("moduleId") + " " + stringBuffer.toString();
    	}
    	story.setModuleId(null);
    	Pager<ProductStory> p = storyService.findStoryPager(pagesize*(page - 1),pagesize,story, condition,searchInfos,groupOperate,order,"asc".equals(ordertype)?true:false);
        model.addAttribute("storyList",p);
        
        if("reRelateStory".equals(relate)){
        	return "/product/data/plan/product-al-req-data.pagelet";
        }else if ("noRelateStory".equals(relate)) {
        	return "/product/data/plan/product-al-no-req-data.pagelet";
		}else if ("reRelateStoryRelease".equals(relate)) {
        	return "/product/data/release/product-al-req-data.pagelet";
		}else if ("noRelateStoryRelease".equals(relate)) {
        	return "/product/data/release/product-al-no-req-data.pagelet";
		}
        return "";
    }

    @RequestMapping("/bugSearch/{relate}")
    public String bugListAction(@PathVariable(value="relate")String relate,int page, int pagesize,QualityBug bug,SearchInfos searchInfos,
    		@RequestParam(required = false, defaultValue = "bugId") String order, 
    		@RequestParam(required = false, defaultValue = "asc") String ordertype,
    		Model model, HttpServletRequest request){
    	//bug.setProductId((Integer)(request.getSession().getAttribute("sessionProductId")));
    	
    	Pager<QualityBug> p = bugService.findBugListPager(pagesize*(page - 1), pagesize,searchInfos != null ? SqlUtil.toSql(searchInfos.getInfos(), "") : "", bug, null, "asc".equals(ordertype)?true:false);
    	model.addAttribute("bugList",p);
    	
    	if ("reRelateBug".equals(relate)) {
        	return "/product/data/plan/product-al-bug-data.pagelet";
		}else if ("noRelateBug".equals(relate)) {
        	return "/product/data/plan/product-al-no-bug-data.pagelet";
		}else if ("reRelateBugRelease".equals(relate)) {
        	return "/product/data/release/product-al-bug-data.pagelet";
		}else if ("noRelateBugRelease".equals(relate)) {
        	return "/product/data/release/product-al-no-bug-data.pagelet";
		}else if ("leRelateBugRelease".equals(relate)) {
        	return "/product/data/release/product-al-le-bug-data.pagelet";
		}
    	return "";
    }

    @ResponseBody
    @RequestMapping("/storyList")
    public List<ProductStory> findStory(ProductStory story){

        List<ProductStory> list = storyService.findStoryList(story);

        return list;
    }


    @ResponseBody
    @RequestMapping("/data")
    public List data(String check) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<SystemModule> moduleList = moduleService.findModuleList(new SystemModule());
        if (check == null || !check.equals("n")) {
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("id", -1);
            map1.put("pId", 0);
            map1.put("name", "所有部门");
            list.add(map1);
        }

        for (SystemModule d : moduleList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", d.getModuleId());
            map.put("pId", d.getModuleParent());
            map.put("open", true);
            map.put("add", true);
            map.put("edit", true);
            map.put("name", d.getModuleName());
            list.add(map);
        }
        return list;

    }
    @RequestMapping("/report")
    public String report(ProductStory story,String chexkitem,Model model){
    		
    	/*List<StoryCount> productStoryCount =  storyService.productStoryCount(story);
    	List<StoryCount> modelStoryCount =  storyService.modelStoryCount(story);
    	List<StoryCount> planStoryCount =  storyService.planStoryCount(story);
    	
    	model.addAttribute("productStoryCount", productStoryCount);
    	model.addAttribute("modelStoryCount", modelStoryCount);
    	model.addAttribute("planStoryCount", planStoryCount);
    	*/
    	Map<String, List<StoryCount>> map = storyService.report(chexkitem, story);
    	model.addAttribute("map", map);
    	model.addAttribute("fields", chexkitem);
    	return "/product/page/tabledemo/product-report.page";
    }
    
    

	@ResponseBody
	@RequestMapping(value="/batchDelete")
	public Map bctchDelStory(String ids)
	{		
		Map<String,String> map = new HashMap<String,String>();
		if(ids == null){
			map.put("status", "fail");
		    map.put("info", "删除失败");
			return map;
		}
		 List<ProductStory> list = new ArrayList<ProductStory>();
		for(String s : ids.split(",")){			
			ProductStory story= new ProductStory();
			story.setStoryId(Integer.valueOf(s));
			story.setDeleted(1);
			list.add(story);
		}	
		storyService.deleteBatchStory(list);
		map.put("status", "success");
	    map.put("info", "删除成功");
	    return map;
	}
	
	 @RequestMapping("/toPager/{forwordPager}")
	 public String toPager(@PathVariable(value="forwordPager")String forwordPager,Model model){
		 
		 
		 if("storyReport".equals(forwordPager)){
			 return "product/page/tabledemo/product-report.page";
		 }
		 return "";
	 }
}
