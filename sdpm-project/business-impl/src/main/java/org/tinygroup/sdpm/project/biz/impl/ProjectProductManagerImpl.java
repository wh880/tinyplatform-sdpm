package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.project.biz.inter.ProjectProductManager;
import org.tinygroup.sdpm.project.dao.ProjectProductDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
@Service
@Transactional
public class ProjectProductManagerImpl implements ProjectProductManager {
    @Autowired
    private ProjectProductDao projectProductDao;

    public void addLink(Integer[] productArray, Integer projectId) {
        projectProductDao.deleteByProjectId(projectId);
        List<ProjectProduct> list = new ArrayList<ProjectProduct>();
        for (Integer productId : productArray) {
            ProjectProduct t = new ProjectProduct();
            t.setProjectId(projectId);
            t.setProductId(productId);
            list.add(t);
        }
        if (list != null || !list.isEmpty()) {
            projectProductDao.batchInsert(list);
        }
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

    public Integer delete(int id) {
        return projectProductDao.deleteByKey(id);
    }
}
