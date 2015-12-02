package org.tinygroup.sdpm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.productLine.service.ProductLineService;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;
import org.tinygroup.sdpm.project.service.inter.TeamService;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 产品数据工具类
 * Created by Hulk on 2015/10/29.
 */
@Service
public class ProductUtils {
    public static final String COOKIE_PRODUCT_ID = "cookieProductId";

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductLineService productLineService;
    @Autowired
    private ProjectProductService projectProductService;
    @Autowired
    private TeamService teamService;

    /**
     * 获得产品线列表
     */
    public List<ProductLine> getProductLineList() {
        ProductLine productLine = new ProductLine();
        productLine.setDeleted(0);
        List<ProductLine> productLineList = productLineService.findList(productLine);
        return productLineList;
    }

    /**
     * 获得产品列表
     */
    public List<Product> getProductList() {
        Product product = new Product();
        product.setDeleted(0);
        List<Product> projectList = productService.findProductList(product);
        return projectList;
    }

    /**
     * 获得产品列表
     */
    public List<Product> getProductListByLine(String productLineId) {
        if (StringUtil.isBlank(productLineId)) {
            return new ArrayList<Product>();
        }
        Product product = new Product();
        product.setProductLineId(Integer.valueOf(productLineId));
        product.setDeleted(0);
        return productService.findProductList(product);
    }


    /**
     * 获得产品
     *
     * @param productId
     */
    public Product getProduct(String productId) {
        if (StringUtil.isBlank(productId)) {
            return new Product();
        }
        List<Product> productList = getProductList();
        if (productId == null && productList != null && !productList.isEmpty()) {
            return productList.get(0);
        }
        for (Product product : productList) {
            if (product.getProductId().toString().equals(productId)) {
                return product;
            }
        }
        return new Product();
    }

    /**
     * 获取所有产品中用户可访问的产品
     */
    public List<Product> getAllProductListByUser() {
        return productService.getProductByUser(UserUtils.getUserId(), 0, null);
    }


}
