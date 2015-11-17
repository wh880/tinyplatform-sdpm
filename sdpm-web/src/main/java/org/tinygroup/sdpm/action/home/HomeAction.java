package org.tinygroup.sdpm.action.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.service.inter.TaskService;
import org.tinygroup.sdpm.util.ProductUtils;
import org.tinygroup.sdpm.util.UserUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangll13383 on 2015/11/16.
 */
@Controller
public class HomeAction {
    @Autowired
    private ProductService productService;

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = {"a", "a/home"})
    public String index(Model model) {
        List<Product> products = ProductUtils.getAllProductListByUser();
        List<Product> countProduct = new ArrayList<Product>();
        for(Product product1: products){
            countProduct.add(productService.findProduct(product1.getProductId()));
        }
        Map<String,Map<String,String>> productSum = new HashMap<String, Map<String, String>>();
        for(Product product:countProduct){
            Map<String,String> sumMap = new HashMap<String, String>();
            sumMap.put("activeSum",product.getActiveSum().toString());
            sumMap.put("assignSum",product.getAssignSum().toString());
            sumMap.put("changedSum",product.getChangeSum().toString());
            sumMap.put("bugCount",product.getBugCount().toString());
            sumMap.put("closeSum",product.getCloseSum().toString());
            sumMap.put("draftSum",product.getDraftSum().toString());
            sumMap.put("planCount",product.getPlanCount().toString());
            sumMap.put("releaseCount",product.getReleaseCount().toString());
            sumMap.put("resolvedSum",product.getResolveSum().toString());
            productSum.put(product.getProductId().toString(),sumMap);
        }
        model.addAttribute("productList",countProduct);
        model.addAttribute("productSumMap",productSum);
        ProjectTask task = new ProjectTask();
        task.setTaskAssignedTo(UserUtils.getUserId());
        task.setTaskDeleted(ProjectTask.DELETE_NO);
        List<ProjectTask> tasks = taskService.findListTask(task);
        model.addAttribute("myTaskList",tasks);
        return "main/index.page";
    }
}
