package org.tinygroup.sdpm.product.service;

import org.tinygroup.aopcache.annotation.CacheGet;
import org.tinygroup.aopcache.annotation.CachePut;
import org.tinygroup.aopcache.annotation.CacheRemove;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductAndLine;

import java.util.List;
import java.util.Map;

public interface ProductService {
    String CACHE_USER_PRODUCTS_MAP = "UserProductsMap";
    String CACHE_PRODUCT_ID = "productId";

    /**
     * 添加产品
     *
     * @param product
     * @return
     */
    Product addProduct(Product product);

    /**
     * 修改
     *
     * @param product
     * @return
     */
    @CachePut(keys = "${product.productId}",parameterNames = "product",group = CACHE_PRODUCT_ID)
    int updateProduct(Product product);

    /**
     * 根据产品ID删除
     *
     * @param productId
     * @return
     */
    @CacheRemove(removeKeys = "${productId}", group = CACHE_PRODUCT_ID)
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
    List<String> getProductNameByLineId(Integer productLineId);

    List<Product> getProductByUser(String userId, Integer delete, Integer productLineId);

    List<Product> getProductByUserWithCount(String userId, Integer delete, boolean noRole);

    List<Product> getProductByUserAndProductLineWithCount(String userId, Integer productLineId, Integer delete);

    @CacheGet(key = "${userId}", group = CACHE_USER_PRODUCTS_MAP)
    Map<String, List<Product>> getUserProductsMap(String userId);

    Map<String, List<Product>> getUserProductsWithCountMap(String userId, Integer delete);

    /**
     * 根据输入名称查询
     *
     * @param condition
     * @return
     */
    List<Product> productInCondition(String condition, Integer... ids);
}
