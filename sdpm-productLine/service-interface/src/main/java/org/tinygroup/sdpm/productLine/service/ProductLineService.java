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
    List<ProductLine> findProductLineList(ProductLine productLine);

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

    /**
     * 复合条件-根据ids分页查询产品线
     * @param start
     * @param limit
     * @param carrier
     * @param productLine
     * @param ids
     * @param order
     * @param orderType
     * @return
     */
    Pager<ProductLine> findProductLinePagerInIds(int start, int limit, ConditionCarrier carrier, ProductLine productLine, Integer[] ids, String order, String orderType);

    /**
     * 根据ids查询产品线
     * @param ids
     * @return
     */
    List<ProductLine> getProductLineByIds(Integer... ids);

    /**
     * 获取用户可游览产品线
     * @param userId
     * @return
     */
    List<ProductLine> getUserProductLine(String userId);

    /**
     * 获取用户可游览产品线id
     * @param userId
     * @return
     */
    Integer[] getUserProductLineIds(String userId);

    /**
     * 根据输入名称查询产品线
     * @param condition
     * @param ids
     * @return
     */
    List<ProductLine> lineInCondition(String condition, Integer limit, Integer... ids);

    /**
     * 分页查询productLine
     * @param start
     * @param limit
     * @param productLine
     * @param order
     * @param orderType
     * @return
     */
    Pager<ProductLine> findProductLineInPage(Integer start, Integer limit, ProductLine productLine, String order, String orderType);

}
