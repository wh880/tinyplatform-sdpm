package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-17.
 */
public interface TeamService {
    /**
     * 新增成员
     */
    public int save(ProjectTeam projectTeam);

    /**
     * 移除项目下的成员,根据逻辑id
     */
    public int delete(int id);

    /**
     * 查询项目下的所有成员
     * @param projectId
     * @return
     */
    public List<ProjectTeam> findTeamByProjectId(Integer projectId);

}
