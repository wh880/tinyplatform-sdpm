package org.tinygroup.sdpm.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.commons.tools.ArrayUtil;
import org.tinygroup.sdpm.product.biz.inter.ProductManager;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductAndLine;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductManager productManager;

    public Product addProduct(Product product) {
        return productManager.add(product);
    }

    public int updateProduct(Product product) {
        return productManager.update(product);
    }

    public int deleteProduct(Integer productId) {

        return productManager.delete(productId);
    }

    public Product findProduct(Integer productId) {
        return productManager.find(productId);
    }

    public List<Product> findProductList(Product product, String order, String ordertype) {
        return productManager.findList(product, order, ordertype);
    }

    public Pager<Product> findProductPager(int page, int limit, Product product, String order, String orderType) {
        return productManager.findPager(page, limit, product, order, orderType);
    }

    public int[] updateBatchProduct(List<Product> products) {
        return productManager.updateBatch(products);
    }

    public List<Product> findProductListByIds(Integer... productId) {
        if (ArrayUtil.isEmptyArray(productId)) {
            return new ArrayList<Product>();
        }
        return productManager.findList(productId);
    }

    public List<Product> findProductList(Product product) {
        return productManager.findList(product);
    }

    public List<ProductAndLine> getProductAndLine(Product product) {
        return productManager.getProductAndLine(product);
    }

    public List<String> getProductNameByLineId(Integer productLineId) {

        return productManager.getProductNameByLineId(productLineId);
    }

    public List<Product> getProductByUser(String userId) {
        return productManager.getProductByUser(userId);
    }

    public List<Product> getProductByUserWithCount(String userId) {
        return productManager.getProductByUserWithCount(userId);
    }

    public List<Product> getProductByUserAndProductLineWithCount(String userId, Integer productLineId) {
        return productManager.getProductByUserAndProductLineWithCount(userId,productLineId);
    }


    public Integer[] getTeamRoleProductLineIds(String userId) {
        List<Integer> ids = productManager.getTeamRoleProductLineIds(userId);
        Integer[] Ids = new Integer[ids.size()];
        return ids.toArray(Ids);
    }


}
