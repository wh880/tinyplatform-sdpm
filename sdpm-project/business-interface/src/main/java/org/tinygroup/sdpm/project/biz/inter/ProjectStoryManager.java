package org.tinygroup.sdpm.project.biz.inter;

import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.dao.pojo.ProjectStory;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-21.
 */
public interface ProjectStoryManager {
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
}
