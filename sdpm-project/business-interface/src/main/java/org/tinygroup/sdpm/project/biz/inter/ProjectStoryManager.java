package org.tinygroup.sdpm.project.biz.inter;

import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.project.dao.pojo.ProjectStory;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-21.
 */
public interface ProjectStoryManager {

    /**
     * 批量硬删除
     *
     * @param storyIds
     * @param projectId
     * @return
     */
    Integer batchDel(Integer[] storyIds, Integer projectId);

    /**
     * 批量关联
     *
     * @param projectStoryList
     * @return
     */
    int[] linkStory(List<ProjectStory> projectStoryList);

    int[] updateLink(List<ProjectStory> projectStoryList);

    /**
     * 查找用于关联的需求
     *
     * @param projectId
     * @param start
     * @param limit
     * @param order
     * @param orderType
     * @return
     */
    Pager<ProductStory> findStoryToLink(Integer projectId, Integer start, Integer limit, String order, String orderType);

    /**
     * 根据projectId查找关联产品
     *
     * @param projectId 对象
     * @return
     */
    List<ProjectStory> findStoryList(Integer projectId);

    /**
     * 根据条件查询List
     *
     * @param projectStory 用于查询条件
     * @return
     */
    List<ProjectStory> findList(ProjectStory projectStory);


    /**
     * 根据id进行删除
     *
     * @param id 主键
     * @return
     */
    Integer delete(int id);

    /**
     * 根据项目和需求删除关联
     *
     * @param projectId
     * @param storyId
     * @return
     */
    Integer deleteByProjectStory(Integer projectId, Integer storyId);

    /**
     * 根据项目和需求删除关联
     *
     * @param
     * @param
     * @return
     */
    Pager<ProjectStory> findPager(int start, int limit, ProjectStory story, String statusCondition, SearchInfos conditions,
                                  String groupOperate, String columnName, boolean asc);
}
