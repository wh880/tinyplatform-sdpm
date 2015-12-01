package org.tinygroup.sdpm.productLine.biz.inter;

import org.tinygroup.sdpm.common.condition.ConditionCarrier;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface ProductLineManager {

    /**
     * 添加产品线
     *
     * @param productLine
     * @return
     */
    ProductLine add(ProductLine productLine);

    /**
     * 编辑产品线
     *
     * @param productLine
     * @return
     */
    int update(ProductLine productLine);

    /**
     * 批量编辑
     *
     * @param productLine
     * @return
     */
    int[] updateBatch(List<ProductLine> productLine);

    /**
     * 根据Id删除
     *
     * @param productLineId
     * @return
     */
    int delete(Integer productLineId);

    /**
     * 根据主键查询
     *
     * @param productLineId
     * @return
     */
    ProductLine find(Integer productLineId);

    /**
     * 查找所有项目
     *
     * @param productLine
     * @return
     */
    List<ProductLine> findList(ProductLine productLine);

    Pager<ProductLine> findProductLinePagerInIds(int start, int limit, ConditionCarrier carrier, ProductLine productLine, Integer[] ids, String order, String ordertype);

    List<ProductLine> getProductLineByIds(Integer... ids);

    List<ProductLine> getUserProductLine(String userId);

    List<ProductLine> lineInCondition(String condition, Integer... ids);
}
