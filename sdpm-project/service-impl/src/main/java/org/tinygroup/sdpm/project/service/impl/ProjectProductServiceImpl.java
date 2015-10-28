package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.product.biz.inter.ProductManager;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.project.biz.inter.ProjectProductManager;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-20.
 */
@Component
public class ProjectProductServiceImpl implements ProjectProductService {
    @Autowired
    private ProjectProductManager projectProductManager;
    @Autowired
    private ProductManager productManager;

    public void addLink(Integer[] productArray, Integer projectId) {
        projectProductManager.addLink(productArray, projectId);
    }

    public List<Product> findLinkProduct() {
        List<Product> list = productManager.findList(new Product());
        return list;
    }

    public List<ProjectProduct> findProducts(Integer projectId) {
        ProjectProduct projectProduct = new ProjectProduct();
        projectProduct.setProjectId(projectId);
        return projectProductManager.findList(projectProduct);
    }

    public List<ProjectProduct> findProjects(Integer productId) {
        ProjectProduct projectProduct = new ProjectProduct();
        projectProduct.setProductId(productId);
        return projectProductManager.findList(projectProduct);
    }

    public int add(int projectId, int productId) {
        return 0;
    }

    public int delete(int id) {
        return 0;
    }
}
