package org.tinygroup.sdpm.action.project.dto;

import org.tinygroup.sdpm.system.dao.pojo.SystemEffort;

import java.util.List;

/**
 * Created by Hulk on 2015/11/9.
 */
public class EffortList {
    private List<SystemEffort> List;

    public java.util.List<SystemEffort> getList() {
        return List;
    }

    public void setList(java.util.List<SystemEffort> list) {
        List = list;
    }
}
