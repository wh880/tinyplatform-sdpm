package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
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
    Pager<ProjectBuild> findPager(Integer projectId, Integer start, Integer limit, String order, boolean asc);


    /**
     * 编辑版本
     *
     * @param projectBuild
     * @return
     */
    Integer updateBuild(ProjectBuild projectBuild);

    /**
     * 根据产品id进行软删删除
     *
     * @param productId
     * @return
     */
    Integer deleteBuildByProductId(Integer productId);

    /**
     * 根据id查找
     *
     * @param buildId
     * @return
     */
    ProjectBuild findBuild(Integer buildId);

    /**
     * 批量删除
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
     * 删除关联
     *
     * @param storyId
     * @param buildId
     * @return
     */
     Integer deleteBuildStory(Integer storyId, Integer buildId);

    /**
     * 关联需求
     *
     * @param storyId
     * @param buildId
     * @return
     */
     Integer linkBuildStory(Integer storyId, Integer buildId);

    /**
     * 删除关联
     *
     * @param bugId
     * @param buildId
     * @return
     */
     Integer deleteBuildBug(Integer bugId, Integer buildId);


    /**
     * bug分页
     *
     * @param id
     * @param start
     * @param limit
     * @param conditions
     * @param groupOperate
     * @return
     */
     Pager<QualityBug> findBugPager(int start, int limit, int id, SearchInfos conditions, String groupOperate);

    /**
     * 未关联bug分页
     *
     * @param id
     * @param start
     * @param limit
     * @param conditions
     * @param groupOperate
     * @return
     */
     Pager<QualityBug> findNoBuildBug(int start, int limit, int id, String condition, SearchInfos conditions, String groupOperate);

    /**
     * 未关联bug分页
     *
     * @param id
     * @param start
     * @param limit
     * @param conditions
     * @param groupOperate
     * @return
     */
     Pager<QualityBug> findBugLegacyPager(int start, int limit, int id, SearchInfos conditions, String groupOperate);

    List<ProjectBuild> getBuildByIds(String... ids);

}
