package org.tinygroup.sdpm.action.statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.service.PlanService;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.statistic.dao.pojo.ProjectTaskSta;
import org.tinygroup.sdpm.statistic.service.inter.StatisticService;

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
    private ProjectService projectService;
    @Autowired
    private ProjectProductService projectProductService;
    @Autowired
    private StatisticService statisticService;
    @Autowired
    private UserService userService;
    @RequestMapping("{type}/all")
    public String productAll(Model model, @PathVariable(value="type")String type){
        model.addAttribute("order", "1");
        if("product".equals(type)) {
            List<Product> products = productService.findProductList(new Product());
            Map<Product, List<ProductPlan>> map = new HashMap<Product, List<ProductPlan>>();
            for (int i = 0, n = products.size(); i < n; i++) {
                ProductPlan plan = new ProductPlan();
                plan.setDeleted(0);
                plan.setProductId(products.get(i).getProductId());
                List<ProductPlan> productPlans = planService.statisticfind(plan);
                map.put(products.get(i), productPlans);
            }
            model.addAttribute("product", map);
            return "/statistic/page/product.page";
        }
        if ("project".equals(type)){
            List<Project> projects= projectService.findProjects(new Project());

            model.addAttribute("projects",projects);
            return "/statistic/page/project.page";
        }
        if("org".equals(type)){
            List<OrgUser> orgUsers = userService.findUserList(new OrgUser());
            Map<OrgUser,List<ProjectTaskSta>> map = new HashMap<OrgUser, List<ProjectTaskSta>>();
            for(int i=0,n=orgUsers.size();i<n;i++){
               ProjectTaskSta projectTaskSta = new ProjectTaskSta();
                projectTaskSta.setAssignedTo(orgUsers.get(i).getOrgUserId());
                List<ProjectTaskSta> projectTaskStas=statisticService.findProTasks(projectTaskSta);
                map.put(orgUsers.get(i),projectTaskStas);
            }

        	model.addAttribute("orgsmap", map);
        	return  "/statistic/page/org.page";
        }
        return null;
    }
    @RequestMapping("product/invest")
    public String productInvest(Model model){
        List<Product> products = productService.findProductList(new Product());
        Map<Product,List<ProjectProduct>> map = new HashMap<Product, List<ProjectProduct>>();

        for(int i=0,n=products.size();i<n;i++){
            List<ProjectProduct> projectProducts=projectProductService.findProjects(products.get(i).getProductId());
            map.put(products.get(i),projectProducts);
        }
        model.addAttribute("productmap",map);
        model.addAttribute("order","2");
        return  "/statistic/page/product.page";
    }
}
