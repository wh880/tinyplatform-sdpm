package org.tinygroup.sdpm.util;

import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.product.service.impl.ProductServiceImpl;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.productLine.service.ProductLineService;
import org.tinygroup.sdpm.productLine.service.impl.ProductLineServiceImpl;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.sdpm.project.service.impl.ProjectProductServiceImpl;
import org.tinygroup.sdpm.project.service.impl.TeamServiceImpl;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;
import org.tinygroup.sdpm.project.service.inter.TeamService;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品数据工具类
 * Created by Hulk on 2015/10/29.
 */
public class ProductUtils {
    private static final String CMS_CACHE = "cmsCache";
    private static final String USER_CACHE_ALL_PRODUCT_LIST_BY_USER = "allProductList_user";
    private static final String USER_CACHE_TEAM_LIST_BY_PRODUCT = "teamList_product";
    private static final String USER_CACHE_TEAM_LIST_BY_PRODUCT_LINE = "teamList_productLine";
    private static final String USER_CACHE_PRODUCT_LIST_BY_LINE_USER = "productList_line_user";
    private static final String USER_CACHE_PRODUCT_LINE_LIST_BY_USER = "productLineList_user";
    private static final String CMS_CACHE_PRODUCT_LIST = "productList";
    private static final String CMS_CACHE_PRODUCT_LIST_LINE_ID_ = "productList_lineId_";
    private static final String CMS_CACHE_PRODUCT_LINE_LIST = "projectLineList";

    private static ProductService productService = SpringContextHolder.getBean(ProductServiceImpl.class);
    private static ProductLineService productLineService = SpringContextHolder.getBean(ProductLineServiceImpl.class);
    private static ProjectProductService projectProductService = SpringContextHolder.getBean(ProjectProductServiceImpl.class);
    private static TeamService teamService = SpringContextHolder.getBean(TeamServiceImpl.class);

    /**
     * 获得产品线列表
     */
    public static List<ProductLine> getProductLineList() {
        List<ProductLine> productLineList = (List<ProductLine>) CacheUtils.get(CMS_CACHE, CMS_CACHE_PRODUCT_LINE_LIST);
        if (productLineList == null) {
            ProductLine productLine = new ProductLine();
            productLine.setDeleted(0);
            productLineList = productLineService.findList(productLine);
            CacheUtils.put(CMS_CACHE, CMS_CACHE_PRODUCT_LINE_LIST, productLineList);
        }
        return productLineList;
    }

    /**
     * 清楚产品线列表
     */
    public static void removeProductLineList() {
        CacheUtils.remove(CMS_CACHE, CMS_CACHE_PRODUCT_LINE_LIST);
    }

    /**
     * 获得产品列表
     */
    public static List<Product> getProductList() {
        List<Product> projectList = (List<Product>) CacheUtils.get(CMS_CACHE, CMS_CACHE_PRODUCT_LIST);
        if (projectList == null) {
            Product product = new Product();
            product.setDeleted(0);
            projectList = productService.findProductList(product);
            CacheUtils.put(CMS_CACHE, CMS_CACHE_PRODUCT_LIST, projectList);
        }
        return projectList;
    }

    /**
     * 清楚所有产品列表
     */
    public static void removeProductList() {
        CacheUtils.remove(CMS_CACHE, CMS_CACHE_PRODUCT_LIST);
    }

    /**
     * 获得产品列表
     */
    public static List<Product> getProductListByLine(String productLineId) {
        if (productLineId == null) {
            return getProductList();
        }
        List<Product> productList = (List<Product>) CacheUtils.get(CMS_CACHE, CMS_CACHE_PRODUCT_LIST_LINE_ID_ + productLineId);
        if (productList == null) {
            Product product = new Product();
            product.setProductLineId(Integer.valueOf(productLineId));
            product.setDeleted(0);
            productList = productService.findProductList(product);
            CacheUtils.put(CMS_CACHE, CMS_CACHE_PRODUCT_LIST_LINE_ID_ + productLineId, productList);
        }
        return productList;
    }

    /**
     * 清楚产品列表
     * （在修改、新增产品时进行调用）
     */
    public static void removeProductList(String productLineId) {
        CacheUtils.remove(CMS_CACHE, CMS_CACHE_PRODUCT_LIST_LINE_ID_ + productLineId);
        removeProductList();
    }

    /**
     * 获得产品
     *
     * @param productId
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


    /**
     * 获取产品项目组员列表
     */

    public static String getUserListByProduct(String productId) {
        String result = (String) UserUtils.getCache(USER_CACHE_TEAM_LIST_BY_PRODUCT + productId);
        if (StringUtil.isBlank(result)) {
            List<ProjectProduct> projectProducts = projectProductService.findProjects(Integer.parseInt(productId));
            Product product = getProduct(productId);
            List<List<ProjectTeam>> teamsList = new ArrayList<List<ProjectTeam>>();
            for (ProjectProduct projectProduct : projectProducts) {
                if (projectProduct.getProjectId() != null && projectProduct.getProjectId() > 0) {
                    teamsList.add(teamService.findTeamByProjectId(projectProduct.getProjectId()));
                }
            }
            StringBuffer resultBuffer = new StringBuffer("");
            for (List<ProjectTeam> teams : teamsList) {
                for (ProjectTeam team : teams) {
                    if (!StringUtil.isBlank(team.getTeamUserId()) && !resultBuffer.toString().contains(team.getTeamUserId())) {
                        if (!StringUtil.isBlank(resultBuffer.toString())) {
                            resultBuffer.append(",");
                        }
                        resultBuffer.append(team.getTeamUserId());
                    }
                }
            }
            if (!StringUtil.isBlank(product.getProductOwner()) && !resultBuffer.toString().contains(product.getProductOwner())) {
                if (!StringUtil.isBlank(resultBuffer.toString())) {
                    resultBuffer.append(",");
                }
                resultBuffer.append(product.getProductOwner());
            }
            result = resultBuffer.toString();
            UserUtils.putCache(USER_CACHE_TEAM_LIST_BY_PRODUCT + productId, result);
        }
        return result;
    }

    /**
     * 清除产品项目组员列表
     */
    public static void removeUserListByProduct(String productId) {
        UserUtils.removeCache(USER_CACHE_TEAM_LIST_BY_PRODUCT + productId);
    }

    /**
     * 获取产品线项目组员列表
     */
    public static String getUserListByProductLine(String productLineId) {
        String result = (String) UserUtils.getCache(USER_CACHE_TEAM_LIST_BY_PRODUCT_LINE + productLineId);
        if (StringUtil.isBlank(result)) {
            ProductLine productLine = productLineService.findProductLine(Integer.parseInt(productLineId));
            StringBuffer users = new StringBuffer("");
            Product product = new Product();
            product.setProductLineId(Integer.parseInt(productLineId));
            List<Product> products = productService.findProductList(product);
            for (Product p : products) {
                if (p.getProductId() != null && p.getProductId() > 0) {
                    if (!StringUtil.isBlank(users.toString())) {
                        users.append(",");
                    }
                    users.append(getUserListByProduct(String.valueOf(p.getProductId())));
                }
            }
            if (!StringUtil.isBlank(productLine.getProductLineOwner()) && !users.toString().contains(productLine.getProductLineOwner())) {
                if (!StringUtil.isBlank(users.toString())) {
                    users.append(",");
                }
                users.append(productLine.getProductLineOwner());
            }
            result = users.toString();
            UserUtils.putCache(USER_CACHE_TEAM_LIST_BY_PRODUCT_LINE + productLineId, result);
        }
        return result;
    }

    /**
     * 清除产品线项目组员列表
     */
    public static void removeUserListByProductLine(String productLineId) {
        UserUtils.removeCache(USER_CACHE_TEAM_LIST_BY_PRODUCT_LINE + productLineId);
        List<Product> products = getProductListByLine(productLineId);
        for (Product product : products) {
            removeUserListByProduct(String.valueOf(product.getProductId()));
        }
    }

    /**
     * 获取当前用户可访问的产品线
     */
    public static List<ProductLine> getProductLineListByUser() {
        List<ProductLine> result = (List<ProductLine>) UserUtils.getCache(USER_CACHE_PRODUCT_LINE_LIST_BY_USER);
        if (result == null) {
            List<ProductLine> productLines = getProductLineList();
            result = new ArrayList<ProductLine>();
            String loginId = UserUtils.getUserId();
            for (ProductLine productLine : productLines) {
                int validate = validateProductLine(loginId, productLine);
                if (validate < 3) {
                    result.add(productLine);
                }
            }
            UserUtils.putCache(USER_CACHE_PRODUCT_LINE_LIST_BY_USER, result);
        }
        return result;
    }

    /**
     * 清除当前用户可访问的产品线
     */
    public static void removeProductLineListByUser() {
        UserUtils.removeCache(USER_CACHE_PRODUCT_LINE_LIST_BY_USER);
        List<ProductLine> productLines = getProductLineList();
        for (ProductLine productLine : productLines) {
            removeProductListByProductLineUser(String.valueOf(productLine.getProductLineId()));
        }
    }

    /**
     * 获取当前产品线中用户可访问的产品
     */
    public static List<Product> getProductListByProductLineUser(String productLineId) {
        List<Product> result = (List<Product>) UserUtils.getCache(USER_CACHE_PRODUCT_LIST_BY_LINE_USER + productLineId);
        if (result == null) {
            List<Product> products = getProductListByLine(productLineId);
            result = new ArrayList<Product>();
            String loginId = UserUtils.getUserId();
            for (Product product : products) {
                int validate = validateProduct(loginId, product);
                if (validate < 3) {
                    result.add(product);
                }
            }
            UserUtils.putCache(USER_CACHE_PRODUCT_LIST_BY_LINE_USER + productLineId, result);
        }
        return result;
    }

    /**
     * 清除当前产品线用户可访问的产品
     */
    public static void removeProductListByProductLineUser(String productLineId) {
        UserUtils.removeCache(USER_CACHE_PRODUCT_LIST_BY_LINE_USER + productLineId);
        removeUserListByProductLine(productLineId);
    }

    /**
     * 获取所有产品中用户可访问的产品
     */
    public static List<Product> getAllProductListByUser() {
        List<Product> result = (List<Product>) UserUtils.getCache(USER_CACHE_ALL_PRODUCT_LIST_BY_USER);
        if (result == null) {
            List<Product> products = getProductList();
            result = new ArrayList<Product>();
            String loginId = UserUtils.getUserId();
            for (Product product : products) {
                if (validateProduct(loginId, product) < 3) {
                    result.add(product);
                }
            }
            UserUtils.putCache(USER_CACHE_ALL_PRODUCT_LIST_BY_USER, result);
        }
        return result;
    }

    /**
     * 清除当前用户可访问的产品
     */
    public static void removeAllProductListByUser() {
        UserUtils.removeCache(USER_CACHE_ALL_PRODUCT_LIST_BY_USER);
        List<Product> products = getProductList();
        for (Product product : products) {
            removeUserListByProduct(String.valueOf(product.getProductId()));
        }
    }

    public static String getFirstProductLine() {
        List<ProductLine> productLines = getProductLineListByUser();
        for (ProductLine productLine : productLines) {
            if (getProductListByProductLineUser(String.valueOf(productLine.getProductLineId())).size() > 0) {
                return productLine.getProductLineId().toString();
            }
        }
        return null;
    }

    private static Integer validateProductLine(String loginId, ProductLine productLine) {
        if (productLine.getAcl() < 1) {
            return 2;
        } else if (productLine.getAcl() == 1) {
            if (getUserListByProductLine(String.valueOf(productLine.getProductLineId())).contains(loginId)) {
                return 2;
            }
            return 3;
        } else {
            if (getUserListByProductLine(String.valueOf(productLine.getProductLineId())).contains(loginId)) {
                return 2;
            } else {
                for (OrgRole role : UserUtils.getUserRoleList()) {
                    if (productLine.getProductLineWhiteList().contains(String.valueOf(role.getOrgRoleId()))) {
                        return 2;
                    }
                }
            }
        }
        return 3;
    }

    private static Integer validateProduct(String loginId, Product product) {
        if (product.getAcl() < 1) {
            return 2;
        } else if (product.getAcl() == 1) {
            if (getUserListByProduct(String.valueOf(product.getProductId())).contains(loginId)) {
                return 2;
            }
            return 3;
        } else {
            if (getUserListByProduct(String.valueOf(product.getProductId())).contains(loginId)) {
                return 2;
            } else {
                for (OrgRole role : UserUtils.getUserRoleList()) {
                    if (product.getProductWhiteList().contains(String.valueOf(role.getOrgRoleId()))) {
                        return 2;
                    }
                }
            }
        }
        return 3;
    }

}
