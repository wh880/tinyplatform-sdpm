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
    List<ProjectProduct> findProductList(int projectId);

    /**
     * 根据条件查询List
     *
     * @param productId 用于查询条件
     * @return
     */
    List<ProjectProduct> findProjcetList(int productId);

    /**
     * 新增关联
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
