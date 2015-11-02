package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
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
    public int[] updateLink(List<ProjectStory> projectStoryList);

    public List<Project> findProjectsByStory(Integer storyId);

    /**
     * 通用查询
     *
     * @param projectStory
     * @return
     */
    public List<ProjectStory> findByProjectStory(ProjectStory projectStory);

    /**
     * 批量删除关联
     * @param condition
     * @return
     */
    public Integer batchDel(String condition);

    /**
     * 批量添加
     * @param projectStoryList
     * @return
     */
    public int[] addLink(List<ProjectStory> projectStoryList);

    /**
     * 根据projectId查询story
     * @param projectId
     * @return
     */
    public List<ProductStory> findStoryByProject(Integer projectId);

    /**
     * 查询需求 分页
     * @param projectId
     * @param start
     * @param limit
     * @param order
     * @param ordertype
     * @return
     */
    public Pager<ProductStory> findStoryByProject(Integer projectId, Integer start, Integer limit, String order, String ordertype, String moduleId);

    /**
     * 删除
     * @param projectId
     * @param storyId
     * @return
     */
    public Integer deleteProjectStory(Integer projectId, Integer storyId);

    /**
     * 查找待关联需求
     * @param projectId
     * @param start
     * @param limit
     * @param order
     * @param ordertype
     * @return
     */
    public Pager<ProductStory> findStoryToLink(Integer projectId, Integer start, Integer limit, String order, String ordertype);

    /**
     * 查询所有需求
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
    public Pager<ProductStory> findStoryPager(int start, int limit, int id, SearchInfos conditions, String groupOperate);


    public Pager<ProductStory> findNoStoryPager(int start, int limit, int id, String condition, SearchInfos conditions, String groupOperate);
}
