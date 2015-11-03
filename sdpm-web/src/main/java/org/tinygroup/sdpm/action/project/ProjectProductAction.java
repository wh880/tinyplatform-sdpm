package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;
import org.tinygroup.sdpm.util.ProductUtils;
import org.tinygroup.sdpm.util.ProjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangying14938 on 2015-09-22.
 */
@Controller
@RequestMapping("/a/project/product")
public class ProjectProductAction extends BaseController {
    @Autowired
    private ProjectProductService projectProductService;

    @RequestMapping("/findLinkProduct")
    public String findLinkProduct(Model model, HttpServletResponse response, HttpServletRequest request) {
        List<Product> productList = ProductUtils.getProductList();
        Integer projectId = ProjectUtils.getCurrentProjectId(request,response);
        if (projectId==null){
            return redirectProjectForm();
        }
        List<ProjectProduct> linkList = projectProductService.findProducts(projectId);
        List<String> linkIdList = new ArrayList<String>();
        for (ProjectProduct p : linkList) {
            linkIdList.add(p.getProductId().toString());
        }
        model.addAttribute("linkIdList", linkIdList);
        model.addAttribute("productList", productList);
        return "project/product/index.page";
    }

    @RequestMapping("/save")
    public String save(Integer[] array, HttpServletResponse response,HttpServletRequest request) {
        Integer projectId = ProjectUtils.getCurrentProjectId(request,response);
        if (projectId==null){
            return redirectProjectForm();
        }
        projectProductService.addLink(array, projectId);
        return "redirect:" + adminPath + "/project/product/findLinkProduct";
    }
}