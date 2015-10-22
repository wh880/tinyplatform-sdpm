package org.tinygroup.sdpm.product.service;

import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductAndLine;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface ProductService {
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
    int updateProduct(Product product);

    /**
     * 批量修改
     *
     * @param products
     * @return
     */
    int[] updateBatchProduct(List<Product> products);

    /**
     * 根据产品ID删除
     *
     * @param productId
     * @return
     */
    int deleteProduct(Integer productId);

    /**
     * 根据产品ID查找
     *
     * @param productId
     * @return
     */
    Product findProduct(Integer productId);

    /**
     * 根据产品ID查找
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
     * 根据对象查找(排序)
     *
     * @param product
     * @return
     */
    List<Product> findProductList(Product product, String order, String orderType);

    /**
     * 根据对象查找(排序、分页)
     *
     * @param product
     * @return
     */
    Pager<Product> findProductPager(int page, int limit, Product product, String order, String ordertype);

    /**
     * 根据对象查找(包含产品线的部分信息)
     *
     * @param product
     * @return
     */
    List<ProductAndLine> getProductAndLine(Product product);
    
    /**
	 * 根据产品线ID查找产品的名字
	 * @param productLineId
	 * @return
	 */
	List<String> getProductNameByLineId(Integer productLineId);


}
