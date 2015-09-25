package org.tinygroup.sdpm.action.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.convert.objectxml.xstream.ObjectToXml;
import org.tinygroup.sdpm.common.util.sql.SearchInfos;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.product.service.StoryService;
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
    private ProductService productService;
    @RequestMapping("")
    public String storyAction(ProductStory story, String groupOperate, Model model,HttpServletResponse response){
        List list = productService.findProductList(new Product(),"product_created_date",false);
        model.addAttribute("productList",list);
        model.addAttribute("storyStatus",story.getStoryStatus());
        return "product/page/project/togglebox.page";
    }
    @RequestMapping("/search")
    public String storySearchAction(int page, int limit, ProductStory story,String groupOperate, SearchInfos searchInfos, String sortName, String asc, Model model, HttpServletRequest request){
        Pager<ProductStory> p = storyService.findStoryPager(limit*(page - 1),limit,story,searchInfos,groupOperate,sortName,"asc".equals(asc)?true:false);
        model.addAttribute("storyList",p);
        return "product/data/tabledata.pagelet";
    }
}
