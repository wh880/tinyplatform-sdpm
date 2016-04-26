package org.tinygroup.sdpm.productLine.service.inter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.product.biz.inter.ProductManager;
import org.tinygroup.sdpm.productLine.biz.inter.ProductLineManager;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.productLine.service.inter.ProductLineService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

@Component
@Transactional
public class ProductLineServiceImpl implements ProductLineService {

    @Autowired
    private ProductLineManager productLineManager;
    @Autowired
    private ProductManager productManager;

    public ProductLine addProductLine(ProductLine productLine) {
        return productLineManager.add(productLine);
    }

    public int updateProductLine(ProductLine productLine) {
        return productLineManager.update(productLine);
    }
    @Transactional(readOnly = true)
    public ProductLine findProductLine(Integer productLineId) {
        return productLineManager.find(productLineId);
    }

    public int deleteProductLine(Integer productLineId) {
        return productLineManager.delete(productLineId);
    }
    @Transactional(readOnly = true)
    public Pager<ProductLine> findProductLinePagerInIds(int start, int limit, ConditionCarrier carrier, ProductLine productLine, Integer[] ids, String order, String ordertype) {
        return productLineManager.findProductLinePagerInIds(start, limit, carrier, productLine, ids, order, ordertype);
    }
    @Transactional(readOnly = true)
    public List<ProductLine> getProductLineByIds(Integer... ids) {
        return productLineManager.getProductLineByIds(ids);
    }
    @Transactional(readOnly = true)
    public List<ProductLine> getUserProductLine(String userId) {
        List<ProductLine> lines = productLineManager.getUserProductLine(userId);
        List<Integer> idList = productManager.getTeamRoleProductLineIds(userId, 0, null);
        Integer[] ids = new Integer[idList.size()];
        List<ProductLine> productLines = productLineManager.getProductLineByIds(idList.toArray(ids));
        for (ProductLine p : productLines) {
            if (!lines.contains(p)) {
                lines.add(p);
            }
        }
        return lines;
    }
    @Transactional(readOnly = true)
    public Integer[] getUserProductLineIds(String userId) {
        List<ProductLine> lines = getUserProductLine(userId);
        Integer[] ids = new Integer[lines.size()];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = lines.get(i).getProductLineId();
        }
        return ids;
    }
    @Transactional(readOnly = true)
    public List<ProductLine> lineInCondition(String condition, Integer limit, Integer... ids) {
        return productLineManager.lineInCondition(condition, limit, ids);
    }
    @Transactional(readOnly = true)
    public Pager<ProductLine> findProductLineInPage(Integer start, Integer limit, ProductLine productLine, String order, String orderType) {
        return productLineManager.findProductLineInPage(start, limit, productLine, order, orderType);
    }
    @Transactional(readOnly = true)
    public List<ProductLine> findProductLineList(ProductLine productLine) {
        return productLineManager.findList(productLine);
    }
}
