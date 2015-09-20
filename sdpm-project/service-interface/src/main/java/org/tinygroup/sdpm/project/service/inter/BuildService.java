package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
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
     * 根据项目id查询所有版本
     *
     * @param page
     * @param pagesize
     * @param projectId
     * @return
     */
    public Pager<ProjectBuild> findPager(int page, int pagesize, int projectId);

    /**
     * 编辑版本
     *
     * @param projectBuild
     * @return
     */
    public ProjectBuild updateBuild(ProjectBuild projectBuild);

    /**
     * 根据id删除
     *
     * @param buildId
     * @return
     */
    public Integer deleteBuild(Integer buildId);
}
