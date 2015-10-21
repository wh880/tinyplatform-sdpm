package org.tinygroup.sdpm.action.statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.service.PlanService;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.product.service.StoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MCK on 2015/10/19.
 */
@Controller
@RequestMapping("a/statistic")
public class StatisticAction extends BaseController {
    @Autowired
    private ProductService productService;
    @Autowired
    private PlanService planService;
    @Autowired
    private StoryService storyService;
    @RequestMapping("index")
    public String index (Model model){

        return "/statistic/page/product.page";
    }
    @RequestMapping("product/all")
    public String productAll(Model model){
        List<Product> products=productService.findProductList(new Product());
        Map<Product,List<ProductPlan>> map=  new HashMap<Product, List<ProductPlan>>();
        for(int i=0,n=products.size();i<n;i++){
            ProductPlan plan =new ProductPlan();
            plan.setDeleted(0);
            plan.setProductId(products.get(i).getProductId());
            List<ProductPlan> productPlans = planService.statisticfind(plan);
            map.put(products.get(i),productPlans);

        }

        model.addAttribute("product",map);
        return "/statistic/data/productData1.page";
    }
}
