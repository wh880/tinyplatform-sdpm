package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.ArrayUtil;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.project.biz.inter.ProjectProductManager;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-20.
 */
@Component
@Transactional
public class ProjectProductServiceImpl implements ProjectProductService {
    @Autowired
    private ProjectProductManager projectProductManager;

    public void addProjectLinkToProduct(String[] productIds, Integer projectId) {
        if (ArrayUtil.isEmptyArray(productIds)) {
            return;
        }
        projectProductManager.addLink(productIds, projectId);
    }

    public void addProductLinkToProject(Integer[] productIds, Integer projectId) {
        projectProductManager.addProductLinkToProject(productIds, projectId);

    }
    @Transactional(readOnly = true)
    public List<Product> findLinkProductByProjectId(Integer projectId) {
        return projectProductManager.findLinkProductByProjectId(projectId);
    }
    @Transactional(readOnly = true)
    public List<ProjectProduct> findProductListByProjectId(Integer projectId) {
        ProjectProduct projectProduct = new ProjectProduct();
        projectProduct.setProjectId(projectId);
        return projectProductManager.findList(projectProduct);
    }
    @Transactional(readOnly = true)
    public List<ProjectProduct> findProjectByProductId(Integer productId) {
        ProjectProduct projectProduct = new ProjectProduct();
        projectProduct.setProductId(productId);
        return projectProductManager.findList(projectProduct);
    }

}
