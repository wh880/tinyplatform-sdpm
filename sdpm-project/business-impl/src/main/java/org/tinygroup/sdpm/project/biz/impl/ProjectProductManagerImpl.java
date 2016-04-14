package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.ArrayUtil;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.project.biz.inter.ProjectProductManager;
import org.tinygroup.sdpm.project.dao.ProjectProductDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
@Service
public class ProjectProductManagerImpl implements ProjectProductManager {
    @Autowired
    private ProjectProductDao projectProductDao;

    public List<Product> findLinkProductByProjectId(Integer projectId) {
        if (projectId == null) {
            return new ArrayList<Product>();
        }
        return projectProductDao.findLinkProductByProjectId(projectId);
    }

    public void addLink(String[] productIds, Integer projectId) {
        if (projectId == null) {
            return;
        }
        projectProductDao.deleteByProjectId(projectId);
        if (ArrayUtil.isEmptyArray(productIds)) {
            return;
        }
        List<ProjectProduct> list = new ArrayList<ProjectProduct>();
        for (String productId : productIds) {
            if (StringUtil.isBlank(productId)) {
                continue;
            }
            ProjectProduct t = new ProjectProduct();
            t.setProjectId(projectId);
            t.setProductId(Integer.valueOf(productId));
            list.add(t);
        }
        projectProductDao.batchInsert(list);
    }

    public void addProductLinkToProject(Integer[] productArray, Integer projectId) {
        if (projectId == null) {
            return;
        }
        projectProductDao.deleteByProjectId(projectId);
        if (ArrayUtil.isEmptyArray(productArray)) {
            return;
        }
        List<ProjectProduct> list = new ArrayList<ProjectProduct>();
        for (Integer productId : productArray) {
            if (null == productId) {
                continue;
            }
            ProjectProduct t = new ProjectProduct();
            t.setProjectId(projectId);
            t.setProductId(productId);
            list.add(t);
        }
        projectProductDao.batchInsert(list);
    }

    public List<ProjectProduct> findList(ProjectProduct projectProduct) {
        return projectProductDao.query(projectProduct);
    }

    public ProjectProduct add(ProjectProduct projectProduct) {
        return projectProductDao.add(projectProduct);
    }

    public Integer update(ProjectProduct projectproduct) {
        return projectProductDao.edit(projectproduct);
    }
}
