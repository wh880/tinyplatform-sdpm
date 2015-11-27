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
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.statistic.dao.pojo.*;
import org.tinygroup.sdpm.statistic.service.inter.StatisticService;
import org.tinygroup.sdpm.util.UserUtils;

import java.util.Date;
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
    public String productAll(Model model, @PathVariable(value = "type") String type , Integer deleted,Integer overdue,Date startDate,Date endDate,Integer workHours,Integer roleId) {
        model.addAttribute("order", "1");
        if ("product".equals(type)) {
            Integer delete = 0;
            if(deleted!=null&&deleted==1){
                delete=deleted;
            }
            List<Product> products = productService.getProductByUser(UserUtils.getUserId(),delete,null);
            Map<Product, List<ProductPlan>> map = new HashMap<Product, List<ProductPlan>>();
            for (int i = 0, n = products.size(); i < n; i++) {
                ProductPlan plan = new ProductPlan();
                plan.setDeleted(0);
                plan.setProductId(products.get(i).getProductId());
                List<ProductPlan> productPlans = planService.statisticfind(plan,overdue!=null&&overdue==1?true:false);
                map.put(products.get(i), productPlans);
            }
            model.addAttribute("product", map);
            return "/statistic/page/product.page";
        }
        if ("project".equals(type)) {
            model.addAttribute("startDate",startDate);
            model.addAttribute("endDate",endDate);
            List<Project> projects = projectService.findProjects(null,startDate,endDate);
            model.addAttribute("projects", projects);
            return "/statistic/page/project.page";
        }
        if ("org".equals(type)) {
            if(startDate!=null&&endDate!=null){
                model.addAttribute("startDate",startDate);
                model.addAttribute("endDate",endDate);
            }
            if(workHours!=null){
                model.addAttribute("workHours",workHours);
            }else{
                model.addAttribute("workHours",7);
            }
            if(roleId!=null){
                model.addAttribute("roleId",roleId);
            }
            List<OrgUser> orgUsers = userService.findUserList(new OrgUser());
            Map<OrgUser, List<ProjectTaskSta>> map = new HashMap<OrgUser, List<ProjectTaskSta>>();
            for (int i = 0, n = orgUsers.size(); i < n; i++) {
                ProjectTaskSta projectTaskSta = new ProjectTaskSta();
                projectTaskSta.setAssignedTo(orgUsers.get(i).getOrgUserId());
                List<ProjectTaskSta> projectTaskStas = statisticService.findProTasks(projectTaskSta,startDate,endDate,roleId);
                if(projectTaskStas.size()>0) {
                    map.put(orgUsers.get(i), projectTaskStas);
                }
            }
            model.addAttribute("orgsmap", map);
            return "/statistic/page/org.page";
        }
        return null;
    }

    @RequestMapping("product/invest")
    public String productInvest(Model model,Integer deleted) {
//        List<Product> products = productService.findProductList(new Product());
//        Map<Product,List<ProjectProduct>> map = new HashMap<Product, List<ProjectProduct>>();
//
//        for(int i=0,n=products.size();i<n;i++){
//            List<ProjectProduct> projectProducts=projectProductService.findProjects(products.get(i).getProductId());
//            map.put(products.get(i),projectProducts);
//        }
//        model.addAttribute("productmap",map);
        List<ProductProject> productProjects = statisticService.productProjects(new ProductProject(),deleted!=null&&deleted==1?true:false,UserUtils.getUserId());
        model.addAttribute("proPros", productProjects);
        model.addAttribute("order", "2");
        return "/statistic/page/product.page";
    }

    @RequestMapping("quality/bugCreate")
    public String qualityBugCreate(Model model,Date startDate,Date endDate,Integer cProject,Integer cProduct) {
        model.addAttribute("order", "1");
        List<QualityBugSta> bugStas = statisticService.findBugCreate(new QualityBugSta(),startDate,endDate,cProject,cProduct);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("bugStas", bugStas);
        model.addAttribute("cProject", cProject);
        model.addAttribute("cProduct", cProduct);
        return "/statistic/page/test.page";
    }

    @RequestMapping("quality/bugAssigned")
    public String qualityBugAssigned(Model model) {
        model.addAttribute("order", "2");
        List<Assigned> assigneds = statisticService.findAssigned(new Assigned());
        Map<Assigned, List<QualityBugCall>> map = new HashMap<Assigned, List<QualityBugCall>>();
        for (int i = 0, n = assigneds.size(); i < n; i++) {
            QualityBugCall qualityBugCall = new QualityBugCall();
            String a = assigneds.get(i).getUserId();
            qualityBugCall.setUserId(a);
            List<QualityBugCall> qualityBugCalls = statisticService.findBugCall(qualityBugCall);
            map.put(assigneds.get(i), qualityBugCalls);
        }
        model.addAttribute("bugAss", map);
        return "/statistic/page/test.page";
    }
}
