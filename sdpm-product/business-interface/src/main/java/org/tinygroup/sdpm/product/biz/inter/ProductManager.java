package org.tinygroup.sdpm.product.biz.inter;

import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductAndLine;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface ProductManager {
    /**
     * 添加产品
     *
     * @param product
     * @return
     */
    Product add(Product product);

    /**
     * 修改
     *
     * @param product
     * @return
     */
    int update(Product product);

    /**
     * 批量编辑
     *
     * @param products
     * @return
     */
    int[] updateBatch(List<Product> products);

    /**
     * 根据产品ID删除
     *
     * @param productId
     * @return
     */
    int delete(Integer productId);

    /**
     * 根据产品ID查找
     *
     * @param productId
     * @return
     */
    Product find(Integer productId);

    /**
     * 根据产品ID查找
     *
     * @param productId
     * @return
     */
    Product findWithoutGroupBy(Integer productId);

    /**
     * 根据多个id查找
     *
     * @param productId
     * @return
     */
    List<Product> findList(Integer... productId);

    /**
     * 根据对象查找
     *
     * @param product
     * @return
     */
    List<Product> findList(Product product);

    /**
     * 根据对象查找(排序)
     *
     * @param product
     * @return
     */
    List<Product> findList(Product product, String order, String orderType);

    /**
     * T
     * 根据对象查找(分页、排序)
     *
     * @param page
     * @param limit
     * @param product
     * @param order
     * @param orderType
     * @return
     */
    Pager<Product> findPager(int page, int limit, Product product, String order, String orderType);

    /**
     * 根据对象查找(包含产品线的部分信息)
     *
     * @param product
     * @return
     */
    List<ProductAndLine> getProductAndLine(Product product);

    /**
     * 根据产品线ID查找产品的名字
     *
     * @param productLineId
     * @return
     */
    List<String> getProductNameByLineId(Integer productLineId);

    List<Product> getProductByUser(String userId, Integer delete, Integer productLineId, ConditionCarrier carrier);

    List<Product> getProductByUserWithCount(String userId, Integer delete, boolean noRole, ConditionCarrier carrier);

    List<Product> getProductByUserAndProductLineWithCount(String userId, Integer productLineId, Integer delete, ConditionCarrier carrier);

    List<Integer> getTeamRoleProductLineIds(String userId, Integer delete, ConditionCarrier carrier);

    /**
     * 根据输入名称查询
     *
     * @param condition
     * @return
     */
    List<Product> productInCondition(String condition, Integer limit, Integer... ids);

}
