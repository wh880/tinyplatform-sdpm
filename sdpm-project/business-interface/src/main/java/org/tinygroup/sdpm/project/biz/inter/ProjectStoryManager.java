package org.tinygroup.sdpm.project.biz.inter;

import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.dao.pojo.ProjectStory;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-21.
 */
public interface ProjectStoryManager {

    public Integer batchtDel(String condition);

    /**
     * 批量关联
     *
     * @param projectStoryList
     * @return
     */
    public int[] linkStory(List<ProjectStory> projectStoryList);

    /**
     * 查找用于关联的需求
     *
     * @param projectId
     * @param start
     * @param limit
     * @param order
     * @param oredertype
     * @return
     */
    public Pager<ProductStory> findStoryToLink(Integer projectId, Integer start, Integer limit, String order, String oredertype);
    /**
     * 根据projectId查找关联产品
     *
     * @param projectId 对象
     * @return
     */
    public List<ProjectStory> findSrotys(Integer projectId);

    /**
     * 根据条件查询List
     *
     * @param projectStory 用于查询条件
     * @return
     */
    List<ProjectProduct> findList(ProjectStory projectStory);

    /**
     * 新增关联
     *
     * @param projectProduct
     * @return
     */
    ProjectProduct add(ProjectProduct projectProduct);

    /**
     * 更新用户
     *
     * @param projectproduct 需要更新的实体类
     * @return
     */
    Integer update(ProjectProduct projectproduct);

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
    public Integer deleteByProjectStory(Integer projectId, Integer storyId);
}
