package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.project.dao.pojo.Burn;
import org.tinygroup.sdpm.project.service.inter.BurnService;

import java.util.List;

/**
 * Created by wangying14938 on 2015-09-18.
 */
@Component
public class BurnServiceImpl implements BurnService {
    @Autowired
    public int save(Burn burn) {
        return 0;
    }

    public List<Burn> findById(int projectId) {
        return null;
    }

    public int a(int a) {
        return 0;
    }
}
