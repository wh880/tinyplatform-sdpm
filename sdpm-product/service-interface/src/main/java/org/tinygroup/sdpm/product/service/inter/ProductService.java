package org.tinygroup.sdpm.product.service.inter;

import org.tinygroup.aopcache.annotation.CacheGet;
import org.tinygroup.aopcache.annotation.CacheRemove;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductAndLine;

import java.util.List;
import java.util.Map;

public interface ProductService {
    String CACHE_USER_PRODUCTS_MAP = "userProductsMap";
    String CACHE_USER_PRODUCTS_LIST = "userProductList";
    String CACHE_PRODUCT_ID = "productId";
    String CACHE_USER_PRODUCTS_LIST_WITH_COUNT = "userProductListWithCount";
    String CACHE_USER_PRODUCTS_LIST_WITH_LINE_COUNT = "userProductListWithLineCount";
    String CACHE_PRODUCT_NAME_BY_LINE_ID = "productNameListByLine";
    String CACHE_USER_PRODUCT_LINE = "userProductLine";
    /**
     * 添加产品
     *
     * @param product
     * @return
     */
    @CacheRemove(removeGroups = {CACHE_USER_PRODUCTS_MAP, CACHE_USER_PRODUCTS_LIST, CACHE_PRODUCT_ID, CACHE_USER_PRODUCTS_LIST_WITH_COUNT, CACHE_USER_PRODUCTS_LIST_WITH_LINE_COUNT, CACHE_PRODUCT_NAME_BY_LINE_ID,CACHE_USER_PRODUCT_LINE})
    Product addProduct(Product product);

    /**
     * 修改
     *
     * @param product
     * @return
     */
    @CacheRemove(removeKeys = "${product.productId}",  group = CACHE_PRODUCT_ID,
            removeGroups = {CACHE_USER_PRODUCTS_MAP, CACHE_USER_PRODUCTS_LIST, CACHE_PRODUCT_ID, CACHE_USER_PRODUCTS_LIST_WITH_COUNT, CACHE_USER_PRODUCTS_LIST_WITH_LINE_COUNT, CACHE_PRODUCT_NAME_BY_LINE_ID,CACHE_USER_PRODUCT_LINE})
    int updateProduct(Product product);

    /**
     * 根据产品ID删除
     *
     * @param productId
     * @return
     */
    @CacheRemove(removeKeys = "${productId}", group = CACHE_PRODUCT_ID,
            removeGroups = {CACHE_USER_PRODUCTS_MAP, CACHE_USER_PRODUCTS_LIST, CACHE_PRODUCT_ID, CACHE_USER_PRODUCTS_LIST_WITH_COUNT, CACHE_USER_PRODUCTS_LIST_WITH_LINE_COUNT, CACHE_PRODUCT_NAME_BY_LINE_ID,CACHE_USER_PRODUCT_LINE})
    int deleteProduct(Integer productId);

    /**
     * 根据产品ID查找
     *
     * @param productId
     * @return
     */
    @CacheGet(key = "${productId}", group = CACHE_PRODUCT_ID)
    Product findProductById(Integer productId);

    /**
     * 根据条件查找列表
     *
     * @param product
     * @return
     */
    List<Product> findProductList(Product product);

    /**
     * 根据多个ID查找
     *
     * @param productId
     * @return
     */
    List<Product> findProductListByIds(Integer... productId);

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
    @CacheGet(key = "${productLineId}", group = CACHE_PRODUCT_NAME_BY_LINE_ID)
    List<String> getProductNameByLineId(Integer productLineId);

    /**
     * 获取当前用户可游览产品
     *
     * @param userId
     * @param delete
     * @param productLineId
     * @return
     */
    @CacheGet(key = "${userId}-${delete}-${productLineId}-${status}", group = CACHE_USER_PRODUCTS_LIST)
    List<Product> getProductByUser(String userId, Integer delete, Integer productLineId, String status);

    /**
     * 获取当前用户可游览产品-附带统计数据
     *
     * @param userId
     * @param delete
     * @param noRole
     * @return
     */
    @CacheGet(key = "${userId}-${delete}-${noRole}-${status}", group = CACHE_USER_PRODUCTS_LIST_WITH_COUNT)
    List<Product> getProductByUserWithCount(String userId, Integer delete, boolean noRole,String status);

    /**
     * 获取当前用户在某产品线下可游览产品-附带统计数据
     *
     * @param userId
     * @param productLineId
     * @param delete
     * @return
     */
    @CacheGet(key = "${userId}-${productLineId}-${delete}-${status}", group = CACHE_USER_PRODUCTS_LIST_WITH_LINE_COUNT)
    List<Product> getProductByUserAndProductLineWithCount(String userId, Integer productLineId, Integer delete,String status);

    /**
     * 获取当前用户可游览产品-以‘产品线-产品’的形式展示
     *
     * @param userId
     * @return
     */
    Map<String, List<Product>> getUserProductsMap(String userId);

    /**
     * 获取当前用户可游览产品-以‘产品线-产品’的形式展示-附带统计数据
     *
     * @param userId
     * @param delete
     * @return
     */
    @CacheGet(key = "${userId}-${delete}-${status}", group = CACHE_USER_PRODUCTS_MAP)
    Map<String, List<Product>> getUserProductsWithCountMap(String userId, Integer delete,String status);

    /**
     * 根据输入名称查询
     *
     * @param condition
     * @return
     */
    List<Product> productInCondition(String condition, Integer limit, Integer... ids);
}
