package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-17.
 */
public interface ProjectProductService {

    /**
     * 添加关联
     * @param productArray
     * @param projectId
     */
    public void addLink(Integer[] productArray, Integer projectId);

    /**
     * 查询所有产品
     *
     * @return
     */
    public List<Product> findLinkProduct();

    /**
     * 通过projectId获得productId列表
     * @param porjectId
     * @return
     */
    public List<ProjectProduct> findProducts(Integer projectId);

    /**
     * 通过prodcutId获得projectId列表
     * @param productId
     * @return
     */
    public List<ProjectProduct> findProjects(Integer productId);

    /**
     * 增加product和project映射
     * @param projectId
     * @param productId
     * @return
     */
    public int add(int projectId, int productId);

    /**
     *
     * @param id
     * @return
     */
    public int delete(int id);
}
