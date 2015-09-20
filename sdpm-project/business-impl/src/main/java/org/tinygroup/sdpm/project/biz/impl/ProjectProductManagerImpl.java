package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.project.biz.inter.ProjectProductManager;
import org.tinygroup.sdpm.project.dao.ProjectProductDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
@Service
@Transactional
public class ProjectProductManagerImpl implements ProjectProductManager {
    @Autowired
    private ProjectProductDao projectProductDao;

    public ProjectProduct find(String id) {
        return null;
    }

    public List<ProjectProduct> findList(ProjectProduct projectproduct) {
        return null;
    }

    public ProjectProduct add(ProjectProduct projectproduct) {
        return null;
    }

    public List<Integer> findProductList(int projectId) {
        return null;
    }

    public List<Integer> findProjcetList(int productId) {
        return null;
    }

    public ProjectProduct add(Integer projectId, int productId) {
        return null;
    }

    public ProjectProduct update(ProjectProduct projectproduct) {
        return null;
    }

    public Integer delete(String id) {
        return null;
    }
}
