package org.tinygroup.sdpm.action.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.service.dto.BurnDTO;
import org.tinygroup.sdpm.project.service.inter.BurnService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.project.service.inter.TaskService;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun;
import org.tinygroup.sdpm.quality.service.inter.BugService;
import org.tinygroup.sdpm.quality.service.inter.TestRunService;
import org.tinygroup.sdpm.util.ProductUtils;
import org.tinygroup.sdpm.util.ProjectUtils;
import org.tinygroup.sdpm.util.UserUtils;
import org.tinygroup.tinysqldsl.Pager;

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
    @Autowired
    private BurnService burnService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private BugService bugService;
    @Autowired
    private TestRunService testRunService;

    @RequestMapping(value = {"a", "a/home"})
    public String index(Model model) {
        //首页产品统计展示
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
        //首页指派给我的任务
        ProjectTask task = new ProjectTask();
        task.setTaskAssignedTo(UserUtils.getUserId());
        task.setTaskDeleted(ProjectTask.DELETE_NO);
        List<ProjectTask> tasks = taskService.findListTask(task);
        model.addAttribute("myTaskList",tasks);
        //首页项目统计
        Integer[] userProjectIds = ProjectUtils.getUserProjectIdList();
        Pager<Project> projectPager = projectService.findProjects(0, userProjectIds.length, null, null, userProjectIds);
        Integer interval = 2;
        if (projectPager.getRecords().size() > 0) {
            for (Project project : projectPager.getRecords()) {
                BurnDTO burnDTO = burnService.initBurn(project.getProjectId(), interval);
                project.setBurnValue(burnDTO.getLeftValues());
            }
        }
        model.addAttribute("myProjectList",projectPager.getRecords());
        //首页指派给我的bug
        QualityBug bug = new QualityBug();
        bug.setDeleted(0);
        bug.setBugAssignedTo(UserUtils.getUserId());
        List<QualityBug> bugList = bugService.findBugList(bug);
        model.addAttribute("myBugList",bugList);
        //首页指派给我的用例
        QualityTestRun run = new QualityTestRun();
        run.setTestRunAssignedTo(UserUtils.getUserId());
        List<QualityTestRun> testRuns = testRunService.findTestRunList(run);
        model.addAttribute("runList",testRuns);
        return "main/index.page";
    }
}
