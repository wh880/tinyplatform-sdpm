package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.project.biz.inter.ProjectProductManager;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-20.
 */
@Component
public class ProjectProductServiceImpl implements ProjectProductService {

    @Autowired
    private ProjectProductManager projectProductManager;

    public List<Integer> findProducts(int porjectId) {
        return null;
    }

    public List<Integer> findProjects(int productId) {
        return null;
    }

    public int add(int projectId, int productId) {
        return 0;
    }

    public int delete(int id) {
        return 0;
    }
}
