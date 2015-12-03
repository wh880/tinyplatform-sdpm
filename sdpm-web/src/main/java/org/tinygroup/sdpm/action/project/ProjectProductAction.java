package org.tinygroup.sdpm.action.project;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 项目关联产品
 * Created by wangying14938 on 2015-09-22.
 */
@Controller
@RequestMapping("/a/project/product")
public class ProjectProductAction extends BaseController {
    @Autowired
    private ProjectProductService projectProductService;
    @Autowired
    private ProductService productService;

    @RequiresPermissions("product")
    @RequestMapping("/findLinkProduct")
    public String findLinkProduct(Model model, HttpServletResponse response, HttpServletRequest request) {
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return redirectProjectForm();
        }
        Map<String, List<Product>> userProductsMap = productService.getUserProductsMap(userUtils.getUserId());
        List<ProjectProduct> linkList = projectProductService.findProducts(projectId);
        List<String> linkIdList = new ArrayList<String>();
        for (ProjectProduct p : linkList) {
            linkIdList.add(p.getProductId().toString());
        }
        model.addAttribute("linkIdList", linkIdList);
        model.addAttribute("userProductsMap", userProductsMap);
        return "project/index/product/view";
    }

    @ResponseBody
    @RequestMapping("/save")
    public Map<String, String> save(Integer[] productIds, HttpServletResponse response, HttpServletRequest request) {
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return resultMap(false, "请选择所在项目");
        }
        projectProductService.addProjectLinkToProduct(productIds, projectId);
        return resultMap(true, "保存成功");
    }
}
