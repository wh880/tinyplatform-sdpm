package org.tinygroup.sdpm.productLine.service;

import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface ProductLineService {

    /**
     * 添加产品线
     *
     * @param productLine
     * @return
     */
    ProductLine addProductLine(ProductLine productLine);

    /**
     * 查找所有产品线
     *
     * @param productLine
     * @return
     */
    List<ProductLine> findList(ProductLine productLine);

    /**
     * 编辑产品线
     *
     * @param productLine
     * @return
     */
    int updateProductLine(ProductLine productLine);

    /**
     * 根据ID软删除
     *
     * @param productLineId
     * @return
     */
    int deleteProductLine(Integer productLineId);

    /**
     * 根据主键查询
     *
     * @param productLineId
     * @return
     */
    ProductLine findProductLine(Integer productLineId);

    Pager<ProductLine> findProductLinePagerInIds(int start, int limit, ConditionCarrier carrier, ProductLine productLine, Integer[] ids, String order, String ordertype);

    List<ProductLine> getProductLineByIds(Integer... ids);

    List<ProductLine> getUserProductLine(String userId);

    Integer[] getUserProductLineIds(String userId);

    List<ProductLine> lineInCondition(String condition, Integer... ids);

}
