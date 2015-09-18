package org.tinygroup.sdpm.project.service.inter;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-17.
 */
public interface ProjectproductService {
    /**
     * 通过projectId获得productId列表
     */
    public List<Integer> findProducts(int porjectId);

    /**
     * 通过prodcutId获得projectId列表
     */
    public List<Integer> findProjects(int productId);

    /**
     * 增加product和project映射
     */
    public int save(int projectId, int productId);

    /**
     * 删除
     */
}
