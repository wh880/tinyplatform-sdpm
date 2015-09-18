package org.tinygroup.sdpm.productLine.biz.inter;

import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;

import java.util.List;

public interface ProductLineManager {
    /**
     * 添加产品线
     * @param productLine
     * @return
     */
    ProductLine add(ProductLine productLine);

    /**
     * 编辑产品线
     * @param productLine
     * @return
     */
    int update(ProductLine productLine);

    /**
     * 根据主键查询
     * @param productLineId
     * @return
     */
    ProductLine findById(Integer productLineId);

    /**
     * 根据状态查找
     * @param productLineStatus
     * @return
     */
    List<ProductLine> findByStatus(String productLineStatus);

}
