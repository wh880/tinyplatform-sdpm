package org.tinygroup.sdpm.productLine.biz.inter;

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

    /**
     * 根据对象查找(排序)
     *
     * @param productLine
     * @param order
     * @param ordertype
     * @return
     */
    List<ProductLine> findlist(ProductLine productLine, String order, String ordertype);

    /**
     * 对象查找(分页、排序)
     *
     * @param page
     * @param pagesize
     * @param productLine
     * @param order
     * @param ordertype
     * @return
     */
    Pager<ProductLine> findPager(int page, int pagesize, String condition, ProductLine productLine, String order, String ordertype);

    Pager<ProductLine> findProductLinePagerInIds(int start, int limit, String condition, ProductLine productLine, Integer[] ids, String order, String ordertype);

    List<ProductLine> getProductLineByIds(Integer... ids);

    List<ProductLine> getUserProductLine(String userId);

    List<ProductLine> lineInCondition(String condition, Integer... ids);
}
