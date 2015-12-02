package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.dao.complexsearch.SearchInfos;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.project.dao.pojo.Project;
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
    int[] updateLink(List<ProjectStory> projectStoryList);

    List<Project> findProjectsByStory(Integer storyId);

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
    Integer batchDel(Integer projectId, Integer[] storyIds);

    /**
     * 批量添加
     *
     * @param projectStoryList
     * @return
     */
    int[] addLink(List<ProjectStory> projectStoryList);

    /**
     * 根据projectId查询story
     *
     * @param projectId
     * @return
     */
    List<ProductStory> findStoryByProject(Integer projectId);

    /**
     * 查询需求 分页
     *
     * @param projectId
     * @param start
     * @param limit
     * @param order
     * @param ordertype
     * @return
     */
    Pager<ProductStory> findStoryByProject(Integer projectId, Integer start, Integer limit, String order, String ordertype, String moduleId);

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
     * @param ordertype
     * @return
     */
    Pager<ProductStory> findStoryToLink(Integer projectId, Integer start, Integer limit, String order, String ordertype);

    /**
     * 查询所有需求
     *
     * @param story
     * @param statusCondition
     * @param columnName
     * @param asc
     * @param start
     * @param limit
     * @param conditions
     * @param groupOperate
     * @return
     */
    Pager<ProductStory> findStoryPager(int start, int limit, int id, SearchInfos conditions, String groupOperate);


}
