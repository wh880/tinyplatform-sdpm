package org.tinygroup.sdpm.project.biz.inter;

import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
public interface ProjectManager {
    /**
     * 根据主键id查找
     *
     * @param projectId 主键
     * @return
     */
    Project find(int projectId);

    /**
     * 查询所有项目
     *
     * @return
     */
    List<Project> findList();

    /**
     * 查询所有的project
     *
     * @param start
     * @param limit
     * @param sortName
     * @param asc
     * @return
     */
    public Pager<Project> findPagerProjects(Integer start, Integer limit, String sortName, boolean asc);

    /**
     * 新增项目
     *
     * @param project 新增实体类
     * @return
     */
    Project add(Project project);

    /**
     * 更新项目
     *
     * @param project 需要更新的实体类
     * @return
     */
    Integer update(Project project);

    /**
     * 根据id进行软删除
     *
     * @param projectId 主键
     * @return
     */
    Integer delete(int projectId);
}
