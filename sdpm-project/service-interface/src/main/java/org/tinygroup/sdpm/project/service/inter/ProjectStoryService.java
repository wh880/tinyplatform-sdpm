package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.dao.complexsearch.SearchInfos;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.project.dao.pojo.ProjectStory;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;


/**
 * Created by shenly13343 on 2015-09-20.
 */
public interface ProjectStoryService {

    /**
     * 批量更新关联
     *
     * @param projectStoryList
     * @return
     */
    int[] updateProjectStoryLink(List<ProjectStory> projectStoryList);

    /**
     * 通用查询
     *
     * @param projectStory
     * @return
     */
    List<ProjectStory> findByProjectStory(ProjectStory projectStory);

    /**
     * 批量删除关联
     *
     * @param projectId
     * @param storyIds
     * @return
     */
    Integer batchDelStory(Integer projectId, Integer[] storyIds);

    /**
     * 批量添加
     *
     * @param projectStoryList
     * @return
     */
    int[] addProjectStoryLink(List<ProjectStory> projectStoryList);

    /**
     * 根据projectId查询story
     *
     * @param projectId
     * @return
     */
    List<ProductStory> findStoryByProject(Integer projectId);

    /**
     * 根据projectId和条件查询story
     *
     * @param projectId
     * @return
     */
    List<ProductStory> findStoryByProjectAndModule(Integer projectId, ProductStory story);

    /**
     * 查询需求 分页
     *
     * @param projectId
     * @param start
     * @param limit
     * @param order
     * @param orderType
     * @return
     */
    Pager<ProductStory> findStoryPagerByProject(Integer projectId, Integer start, Integer limit, String order, String orderType, String moduleId);

    /**
     * 删除
     *
     * @param projectId
     * @param storyId
     * @return
     */
    Integer deleteProjectStory(Integer projectId, Integer storyId);

    /**
     * 查找待关联需求
     *
     * @param projectId
     * @param start
     * @param limit
     * @param order
     * @param orderType
     * @param isNotInProjectStory
     * @return
     */
    Pager<ProductStory> findStoryToLink(Integer projectId, Integer start, Integer limit, String order, String orderType, Boolean isNotInProjectStory);

    /**
     * 查询所有需求
     *
     * @param start
     * @param limit
     * @param id
     * @param conditions
     * @param groupOperate
     * @return
     */
    Pager<ProductStory> findStoryPager(int start, int limit, int id, SearchInfos conditions, String groupOperate);


}
