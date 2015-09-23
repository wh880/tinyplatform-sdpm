package org.tinygroup.sdpm.action.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.util.sql.SearchInfos;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.service.StoryService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("product/stroy")
public class StoryAction {
    @Autowired
    private StoryService storyService;
    @RequestMapping("")
    public String storyAction(ProductStory story, Model model){

        return "product/page/project/togglebox.page";
    }
    @RequestMapping("/search")
    public String storySearchAction(ProductStory story, SearchInfos searchInfos, String groupOperate, Model model){
        return null;
    }
}
