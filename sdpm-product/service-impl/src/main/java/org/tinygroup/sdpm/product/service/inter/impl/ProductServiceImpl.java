package org.tinygroup.sdpm.product.service.inter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.ArrayUtil;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.dao.condition.ConditionUtils;
import org.tinygroup.sdpm.product.biz.inter.ProductManager;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductAndLine;
import org.tinygroup.sdpm.product.service.inter.ProductService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Transactional
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
    @Transactional(readOnly = true)
    public Product findProductById(Integer productId) {
        return productManager.find(productId);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findProductWithoutGroupByById(Integer productId) {
        return productManager.findWithoutGroupBy(productId);
    }
    @Transactional(readOnly = true)
    public List<Product> findProductListByIds(Integer... productId) {
        if (ArrayUtil.isEmptyArray(productId)) {
            return new ArrayList<Product>();
        }
        return productManager.findList(productId);
    }
    @Transactional(readOnly = true)
    public List<Product> findProductList(Product product) {
        return productManager.findList(product);
    }
    @Transactional(readOnly = true)
    public List<ProductAndLine> getProductAndLine(Product product) {
        return productManager.getProductAndLine(product);
    }
    @Transactional(readOnly = true)
    public List<String> getProductNameByLineId(Integer productLineId) {
        return productManager.getProductNameByLineId(productLineId);
    }
    @Transactional(readOnly = true)
    public List<Product> getProductByUser(String userId, Integer delete, Integer productLineId, String status) {
        ConditionCarrier carrier = mergeCarrier(status);
        return productManager.getProductByUser(userId, delete, productLineId, carrier);
    }
    @Transactional(readOnly = true)
    public List<Product> getProductByUserWithCount(String userId, Integer delete, boolean noRole, String status) {
        ConditionCarrier carrier = mergeCarrier(status);
        return productManager.getProductByUserWithCount(userId, delete, noRole, carrier);
    }
    @Transactional(readOnly = true)
    public List<Product> getProductByUserAndProductLineWithCount(String userId, Integer productLineId, Integer delete, String status) {
        ConditionCarrier carrier = mergeCarrier(status);
        return productManager.getProductByUserAndProductLineWithCount(userId, productLineId, delete, carrier);
    }
    @Transactional(readOnly = true)
    public Map<String, List<Product>> getUserProductsWithCountMap(String userId, Integer delete, String status) {
        ConditionCarrier carrier = mergeCarrier(status);
        Map<String, List<Product>> productMap = new HashMap<String, List<Product>>();
        List<Product> products = productManager.getProductByUserWithCount(userId, delete, true, carrier);
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
    @Transactional(readOnly = true)
    public List<Product> productInCondition(String condition, Integer limit, Integer... ids) {
        return productManager.productInCondition(condition, limit, ids);
    }
    @Transactional(readOnly = true)
    public Map<String, List<Product>> getUserProductsMap(String userId) {
        Map<String, List<Product>> productMap = new HashMap<String, List<Product>>();
        List<Product> products = productManager.getProductByUser(userId, 0, null, mergeCarrier(Product.CHOOSE_OPENED));
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

    private ConditionCarrier mergeCarrier(String status) {
        ConditionCarrier carrier = new ConditionCarrier();
        if ("open".equals(status)) {
            carrier.put("productStatus",
                    ConditionUtils.Operate.NEQ.getOperate(),
                    ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                    Product.STATUS_CLOSED);
        } else if ("closed".equals(status)) {
            carrier.put("productStatus",
                    ConditionUtils.Operate.EQ.getOperate(),
                    ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                    Product.STATUS_CLOSED);
        }
        return carrier;
    }
}
