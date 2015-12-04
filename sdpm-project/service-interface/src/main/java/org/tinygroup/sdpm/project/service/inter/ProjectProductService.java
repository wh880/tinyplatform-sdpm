package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-17.
 */
public interface ProjectProductService {

    /**
     * 添加关联,并将原存在的关联删除
     *
     * @param productIds
     * @param projectId
     */
    void addProjectLinkToProduct(Integer[] productIds, Integer projectId);

    /**
     * 查询项目关联的产品
     *
     * @return
     */
    List<Product> findLinkProductByProjectId(Integer projectId);

    /**
     * 通过projectId获得productId列表
     *
     * @param projectId
     * @return
     */
    List<ProjectProduct> findProductListByProjectId(Integer projectId);

    /**
     * 通过prodcutId获得projectId列表
     *
     * @param productId
     * @return
     */
    List<ProjectProduct> findProjectByProductId(Integer productId);

}
