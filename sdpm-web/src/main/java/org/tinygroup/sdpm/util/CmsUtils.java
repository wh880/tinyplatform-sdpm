package org.tinygroup.sdpm.util;

import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.product.service.impl.ProductServiceImpl;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.productLine.service.ProductLineService;
import org.tinygroup.sdpm.productLine.service.impl.ProductLineServiceImpl;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.service.impl.ProjectServiceImpl;
import org.tinygroup.sdpm.project.service.inter.ProjectService;

import java.util.List;

/**
 * 常用数据获取工具集
 * Created by Hulk on 2015/10/21.
 */
public class CmsUtils {
    private static final String CMS_CACHE = "cmsCache";
    private static final String CMS_CACHE_PROJECT_LIST = "projectList";
    private static final String CMS_CACHE_PRODUCCT_LIST = "productList";
    private static final String CMS_CACHE_PRODUCCT_LINE_LIST = "projectLineList";

    private static ProjectService projectService = SpringContextHolder.getBean(ProjectServiceImpl.class);
    private static ProductService productService = SpringContextHolder.getBean(ProductServiceImpl.class);
    private static ProductLineService productLineService = SpringContextHolder.getBean(ProductLineServiceImpl.class);

    /**
     * 获得项目列表
     */
    public static List<Project> getProjectList() {
        List<Project> projectList = (List<Project>) CacheUtils.get(CMS_CACHE, CMS_CACHE_PROJECT_LIST);
        if (projectList == null) {
            projectList = projectService.findList();
            CacheUtils.put(CMS_CACHE, CMS_CACHE_PROJECT_LIST, projectList);
        }
        return projectList;
    }

    /**
     * 清楚项目列表
     */
    public static void removeProjectList() {
        CacheUtils.remove(CMS_CACHE, CMS_CACHE_PROJECT_LIST);
    }

    /**
     * 获得项目列表
     */
    public static Project getProject(String projectId) {
        List<Project> projectList = getProjectList();
        if (projectId == null && projectList != null && !projectList.isEmpty()) {
            return projectList.get(0);
        }
        for (Project project : projectList) {
            if (project.getProjectId().toString().equals(projectId)) {
                return project;
            }
        }
        return new Project();
    }

    /**
     * 获得产品线列表
     */
    public static List<ProductLine> getProductLineList() {
        List<ProductLine> productLineList = (List<ProductLine>) CacheUtils.get(CMS_CACHE, CMS_CACHE_PRODUCCT_LINE_LIST);
        if (productLineList == null) {
            productLineList = productLineService.findList(null);
            CacheUtils.put(CMS_CACHE, CMS_CACHE_PRODUCCT_LINE_LIST, productLineList);
        }
        return productLineList;
    }

    /**
     * 清楚产品线列表
     */
    public static void removeProductLineList() {
        CacheUtils.remove(CMS_CACHE, CMS_CACHE_PRODUCCT_LINE_LIST);
    }

    /**
     * 获得产品线列表
     */
    public static List<Product> getProductList() {
        List<Product> projectList = (List<Product>) CacheUtils.get(CMS_CACHE, CMS_CACHE_PRODUCCT_LIST);
        if (projectList == null) {
            projectList = productService.findProductList(null);
            CacheUtils.put(CMS_CACHE, CMS_CACHE_PRODUCCT_LIST, projectList);
        }
        return projectList;
    }

    /**
     * 清楚所有项目列表
     */
    public static void removeProductList() {
        CacheUtils.remove(CMS_CACHE, CMS_CACHE_PROJECT_LIST);
    }


    /**
     * 获得产品线列表
     */
    public static List<Product> getProductListByLine(Integer productLineId) {
        if (productLineId == null) {
            return getProductList();
        }
        List<Product> productList = (List<Product>) CacheUtils.get(CMS_CACHE, CMS_CACHE_PRODUCCT_LIST + productLineId);
        if (productList == null) {
            Product product = new Product();
            product.setProductLineId(productLineId);
            productList = productService.findProductList(product);
            CacheUtils.put(CMS_CACHE, CMS_CACHE_PRODUCCT_LIST + productLineId, productList);
        }
        return productList;
    }

    /**
     * 清楚项目列表
     * （在修改、新增产品时进行调用）
     */
    public static void removeProductList(Integer productLineId) {
        CacheUtils.remove(CMS_CACHE, CMS_CACHE_PROJECT_LIST + productLineId);
        removeProductList();
    }

    /**
     * 获得产品
     */
    public static Product getProduct(String productId) {
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

}
