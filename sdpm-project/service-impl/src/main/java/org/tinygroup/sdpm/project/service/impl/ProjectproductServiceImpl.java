package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;

import java.util.List;

/**
 * Created by wangying14938 on 2015-09-18.
 */
@Component
public class ProjectProductServiceImpl implements ProjectProductService {
    @Autowired
    public List<Integer> findProducts(int porjectId) {
        return null;
    }

    public List<Integer> findProjects(int productId) {
        return null;
    }

    public int save(int projectId, int productId) {
        return 0;
    }

    public int deleteById(int id) {
        return 0;
    }
}