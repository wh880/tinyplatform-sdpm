package org.tinygroup.sdpm.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.commons.tools.ArrayUtil;
import org.tinygroup.sdpm.product.biz.inter.ProductManager;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductAndLine;
import org.tinygroup.sdpm.product.service.ProductService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Product findProductById(Integer productId) {
        return productManager.find(productId);
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

    public List<Product> getProductByUser(String userId, Integer delete, Integer productLineId) {
        return productManager.getProductByUser(userId, delete, productLineId);
    }

    public List<Product> getProductByUserWithCount(String userId, Integer delete, boolean noRole) {
        return productManager.getProductByUserWithCount(userId, delete, noRole);
    }

    public List<Product> getProductByUserAndProductLineWithCount(String userId, Integer productLineId, Integer delete) {
        return productManager.getProductByUserAndProductLineWithCount(userId, productLineId, delete);
    }

    public Map<String, List<Product>> getUserProductsWithCountMap(String userId, Integer delete) {
        Map<String, List<Product>> productMap = new HashMap<String, List<Product>>();
        List<Product> products = productManager.getProductByUserWithCount(userId, delete, true);
        for (Product product1 : products) {
            if (productMap.containsKey(product1.getProductLineName())) {
                productMap.get(product1.getProductLineName()).add(product1);
            } else {
                List<Product> ps = new ArrayList<Product>();
                ps.add(product1);
                productMap.put(product1.getProductLineName(), ps);
            }
        }
        return productMap;
    }

    public List<Product> productInCondition(String condition, Integer... ids) {
        return productManager.productInCondition(condition, ids);
    }

    public Map<String, List<Product>> getUserProductsMap(String userId) {
        Map<String, List<Product>> productMap = new HashMap<String, List<Product>>();
        List<Product> products = productManager.getProductByUser(userId, 0, null);
        for (Product product1 : products) {
            if (productMap.containsKey(product1.getProductLineName())) {
                productMap.get(product1.getProductLineName()).add(product1);
            } else {
                List<Product> ps = new ArrayList<Product>();
                ps.add(product1);
                productMap.put(product1.getProductLineName(), ps);
            }
        }
        return productMap;
    }


}
