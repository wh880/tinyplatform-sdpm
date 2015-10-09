package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.util.CookieUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wangying14938 on 2015-09-22.
 */
@Controller
@RequestMapping("/project/product")
public class ProjectproductAction extends BaseController {
    @Autowired
    private ProjectProductService projectProductService;

    @RequestMapping("/findLinkProduct")
    public String findLinkProduct(Model model, HttpServletRequest request) {
        List<Product> productList = projectProductService.findLinkProduct();
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        List<ProjectProduct> linkList = projectProductService.findProducts(projectId);
        String linked = "";
        for (ProjectProduct p : linkList) {
            if (linked != "") {
                linked = linked + "," + p.getProductId();
            } else {
                linked = linked + p.getProductId();
            }
        }
        model.addAttribute("linkString", linked);
        model.addAttribute("productList", productList);
        return "project/product/index.page";
    }

    @RequestMapping("/save")
    public String save(Integer[] array, Model model, HttpServletRequest request) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        projectProductService.addLink(array, projectId);
        return "redirect:/project/product/findLinkProduct";
    }
}
