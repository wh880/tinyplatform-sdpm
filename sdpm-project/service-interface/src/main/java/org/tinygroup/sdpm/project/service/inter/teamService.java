package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.project.dao.pojo.Team;

/**
 * Created by shenly13343 on 2015-09-17.
 */
public interface teamService {
    /**
     * 新增成员
     */
    public int add(Team team);

    /**
     * 删除成员
     */
    public int delete(Team team);


}
