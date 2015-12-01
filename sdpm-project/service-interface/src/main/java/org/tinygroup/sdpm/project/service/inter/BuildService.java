package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-20.
 */
public interface BuildService {

    /**
     * 根据版本的逻辑id软删除
     *
     * @param buildId
     * @return
     */
    Integer softDeleteBuild(Integer buildId);

    /**
     * 创建版本
     *
     * @param projectBuild
     * @return
     */
    ProjectBuild addBuild(ProjectBuild projectBuild);

    /**
     * 根据id查询
     *
     * @param projectId
     * @param start
     * @param limit
     * @param order
     * @param asc
     * @return
     */
    Pager<ProjectBuild> findBuildPagerWithOrder(Integer projectId, Integer start, Integer limit, String order, boolean asc);


    /**
     * 编辑版本
     *
     * @param projectBuild
     * @return
     */
    Integer updateBuild(ProjectBuild projectBuild);

    /**
     * 根据id查找
     *
     * @param buildId
     * @return
     */
    ProjectBuild findBuild(Integer buildId);

    /**
     * 批量删除
     *
     * @param ids 版本id
     * @return
     */
    Integer deleteBuildByIds(List<ProjectBuild> ids);

    /**
     * 根据任务状态进行查询
     *
     * @param projectBuild
     * @return
     */
    List<ProjectBuild> findListBuild(ProjectBuild projectBuild);


    /**
     * 需求分页
     *
     * @param projectBuild
     * @return
     */
    Pager<ProjectBuild> findPagerBuild(ProjectBuild projectBuild, Integer start, Integer limit, String order, boolean asc);

    /**
     * 多Id查询
     *
     * @param ids
     * @return
     */
    List<ProjectBuild> getBuildByIds(String... ids);

    /**
     * 根据多产品查询build
     *
     * @param ids
     * @return
     */
    List<ProjectBuild> getBuildByProducts(Integer... ids);

    /**
     * 根据输入名称和产品查询
     *
     * @param condition
     * @param productId
     * @return
     */
    List<ProjectBuild> buildInCondition(String condition, Integer productId, Integer projectId);
}
