package org.tinygroup.sdpm.project.biz.inter;


import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
public interface BuildManager {

    /**
     * 软删除
     *
     * @param build
     * @return
     */
     Integer softDelete(ProjectBuild build);
    /**
     * 根据产品id进行软删删除
     *
     * @param productId
     * @return
     */
    Integer deleteBuildByProductId(Integer productId);
    /**
     * 根据主键id查找
     *
     * @param id 主键
     * @return
     */
    ProjectBuild find(Integer id);

    /**
     * 根据条件查询List
     *
     * @param build 用于查询条件
     * @return
     */
    List<ProjectBuild> findList(ProjectBuild build);

    /**
     * 分页查询
     *
     * @param build
     * @param start
     * @param limit
     * @param order
     * @param asc
     * @return
     */
     Pager<ProjectBuild> findPager(ProjectBuild build, Integer start, Integer limit, String order, boolean asc);

    /**
     * 新增有一个版本
     *
     * @param build 新增实体类
     * @return
     */
    ProjectBuild add(ProjectBuild build);

    /**
     * 更新用户
     *
     * @param build 需要更新的实体类
     * @return
     */
    int update(ProjectBuild build);

    /**
     * 根据id进行软删除
     *
     * @param id 主键
     * @return
     */
    Integer delete(int id);

     int[] batchDelBuildByIds(List<ProjectBuild> keys);
    
    /**
     * 需求列表
     * @param projectBuild
     * @return
     */
     List<ProjectBuild> findStoryList(ProjectBuild projectBuild);

     Pager<ProductStory> findBuildStory(int start, int limit, Integer buildId);
     Pager<ProductStory> findNoBuildStory(int start, int limit, String condition, Integer buildId) ;
    /**
     * 删除关联
     * @param storyId，buildId
     * @return
     */
     Integer deleteBuildStory(Integer storyId, Integer buildId);
    /**
     * 关联需求
     * @param storyId，buildId
     * @return
     */
     Integer linkBuildStory(Integer storyId, Integer buildId);
     Pager<QualityBug> findNoBuildBug(int start, int limit, String condition, Integer buildId);
     Pager<QualityBug> findBuildBug(int start, int limit, Integer buildId);
    /**
     * 删除关联
     * @param storyId，buildId
     * @return
     */
     Integer deleteBuildBug(Integer storyId, Integer buildId);
    /**
     * 关联需求
     * @param storyId，buildId
     * @return
     */
     Integer linkBuildBug(Integer storyId, Integer buildId);
    /**
     * 关联需求
     * @param buildId
     * @return
     */
     Pager<QualityBug> findBuildLegacyBug(int start, int limit, Integer buildId);

    List<ProjectBuild> getBuildByIds(String...ids);

    List<ProjectBuild> getBuildByProducts(Integer... ids);

    /**
     * 根据输入名称和产品查询
     * @param condition
     * @param productId
     * @return
     */
    List<ProjectBuild> buildInCondition(String condition, Integer productId, Integer projectId);
}
