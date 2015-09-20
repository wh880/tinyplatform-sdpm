package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.project.biz.inter.BurnManager;
import org.tinygroup.sdpm.project.dao.pojo.Burn;
import org.tinygroup.sdpm.project.service.inter.BurnService;

import java.util.List;

/**
 * Created by wangying14938 on 2015-09-18.
 */
@Component
public class BurnServiceImpl implements BurnService {
    @Autowired
    private BurnManager burnManager;

    public List<Burn> findById(int projectId) {
        return null;
    }

    public int addBurn(Burn burn) {
        return 0;
    }

    public List<Burn> findBurnByProjectId(int projectId) {
        return null;
    }

    public Integer deleteBurnByProjectDate(int projectId, String date) {
        return null;
    }
}
