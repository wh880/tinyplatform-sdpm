package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.tinysqldsl.Pager;

/**
 * Created by shenly13343 on 2015-09-20.
 */
public interface BuildService {

    /**
     * 创建版本
     *
     * @param projectBuild
     * @return
     */
    public ProjectBuild add(ProjectBuild projectBuild);

    /**
     * 根据id查询
     * @param projectId
     * @param start
     * @param limit
     * @param order
     * @param asc
     * @return
     */
    public Pager<ProjectBuild> findPager(Integer projectId, Integer start, Integer limit, String order, boolean asc);


    /**
     * 编辑版本
     *
     * @param projectBuild
     * @return
     */
    public int updateBuild(ProjectBuild projectBuild);

    /**
     * 根据id删除
     *
     * @param buildId
     * @return
     */
    public Integer deleteBuild(Integer buildId);
    /**
     * 根据id查找
     *
     * @param buildId
     * @return
     */
    public ProjectBuild findBuild(Integer buildId);

}
