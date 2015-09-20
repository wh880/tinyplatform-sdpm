package org.tinygroup.sdpm.project.biz.inter;

import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
public interface ProjectProductManager {
    /**
     * 根据projectId查找关联产品
     *
     * @param projectId 主键
     * @return
     */
    List<Integer> findProductList(int projectId);

    /**
     * 根据条件查询List
     *
     * @param productId 用于查询条件
     * @return
     */
    List<Integer> findProjcetList(int productId);

    /**
     * 新增关联
     * @param projectId
     * @param productId
     * @return
     */
    ProjectProduct add(Integer projectId, int productId);

    /**
     * 更新用户
     *
     * @param projectproduct 需要更新的实体类
     * @return
     */
    ProjectProduct update(ProjectProduct projectproduct);

    /**
     * 根据id进行删除
     *
     * @param id 主键
     * @return
     */
    Integer delete(String id);
}
