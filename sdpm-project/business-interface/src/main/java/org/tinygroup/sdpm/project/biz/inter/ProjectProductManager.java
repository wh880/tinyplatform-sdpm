package org.tinygroup.sdpm.project.biz.inter;

import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
public interface ProjectProductManager {


    public void addLink(Integer[] productArray, Integer projectId);

    /**
     * 根据projectId查找关联产品
     *
     * @param projectProduct 主键
     * @return
     */
     List<ProjectProduct> findList(ProjectProduct projectProduct);

    /**
     * 新增关联
     * @param projectProduct
     * @return
     */
    ProjectProduct add(ProjectProduct projectProduct);

    /**
     * 更新用户
     *
     * @param projectProduct 需要更新的实体类
     * @return
     */
    Integer update(ProjectProduct projectProduct);

}
