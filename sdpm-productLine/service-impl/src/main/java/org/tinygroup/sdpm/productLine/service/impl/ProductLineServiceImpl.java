package org.tinygroup.sdpm.productLine.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.product.biz.inter.ProductManager;
import org.tinygroup.sdpm.productLine.biz.inter.ProductLineManager;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.productLine.service.ProductLineService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

@Component
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

    public ProductLine findProductLine(Integer productLineId) {

        return productLineManager.find(productLineId);
    }

    public int[] updatebatchProductLine(List<ProductLine> productLine) {

        return productLineManager.updateBatch(productLine);
    }

    public int deleteProductLine(Integer productLineId) {

        return productLineManager.delete(productLineId);
    }

    public List<ProductLine> findProductLineList(ProductLine productLine, String order, String ordertype) {

        return productLineManager.findlist(productLine, order, ordertype);
    }

    public Pager<ProductLine> findProductLinePager(int page, int pagesize, String condition, ProductLine productLine, String order,
                                                   String ordertype) {

        return productLineManager.findPager(page, pagesize, condition, productLine, order, ordertype);
    }

    public Pager<ProductLine> findProductLinePagerInIds(int start, int limit, String condition, ProductLine productLine, Integer[] ids, String order, String ordertype) {
        return productLineManager.findProductLinePagerInIds(start, limit, condition, productLine, ids, order, ordertype);
    }

    public List<ProductLine> getProductLineByIds(Integer... ids) {
        return productLineManager.getProductLineByIds(ids);
    }

    public List<ProductLine> getUserProductLine(String userId) {
        List<ProductLine> lines = productLineManager.getUserProductLine(userId);
        List<Integer> idList = productManager.getTeamRoleProductLineIds(userId, 0);
        Integer[] ids = new Integer[idList.size()];
        List<ProductLine> productLines = productLineManager.getProductLineByIds(idList.toArray(ids));
        for (ProductLine p : productLines) {
            if (!lines.contains(p)) {
                lines.add(p);
            }
        }
        return lines;
    }

    public Integer[] getUserProductLineIds(String userId) {
        List<ProductLine> lines = getUserProductLine(userId);
        Integer[] ids = new Integer[lines.size()];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = lines.get(i).getProductLineId();
        }
        return ids;
    }

    public List<ProductLine> lineInCondition(String condition, Integer... ids) {
        return productLineManager.lineInCondition(condition, ids);
    }

    public List<ProductLine> findList(ProductLine productLine) {

        return productLineManager.findList(productLine);
    }


}
