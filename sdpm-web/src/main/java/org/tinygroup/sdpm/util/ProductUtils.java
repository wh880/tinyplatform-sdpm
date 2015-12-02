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
    private static final String CMS_CACHE = "cmsCache";
    private static final String USER_CACHE_ALL_PRODUCT_LIST_BY_USER = "allProductList_user";
    private static final String USER_CACHE_TEAM_LIST_BY_PRODUCT = "teamList_product";
    private static final String USER_CACHE_TEAM_LIST_BY_PRODUCT_LINE = "teamList_productLine";
    private static final String USER_CACHE_PRODUCT_LIST_BY_LINE_USER = "productList_line_user";
    private static final String USER_CACHE_PRODUCT_LINE_LIST_BY_USER = "productLineList_user";
    private static final String CMS_CACHE_PRODUCT_LIST = "productList";
    private static final String CMS_CACHE_PRODUCT_LIST_LINE_ID_ = "productList_lineId_";
    private static final String CMS_CACHE_PRODUCT_LINE_LIST = "projectLineList";

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
     * 清楚产品线列表
     */
    public void removeProductLineList() {
        CacheUtils.remove(CMS_CACHE, CMS_CACHE_PRODUCT_LINE_LIST);
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
     * 清楚所有产品列表
     */
    public void removeProductList() {
        CacheUtils.remove(CMS_CACHE, CMS_CACHE_PRODUCT_LIST);
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
     * 清楚产品列表
     * （在修改、新增产品时进行调用）
     */
    public void removeProductList(String productLineId) {
        CacheUtils.remove(CMS_CACHE, CMS_CACHE_PRODUCT_LIST_LINE_ID_ + productLineId);
        removeProductList();
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
     * 获取产品项目组员列表
     */
    public String getUserListByProduct(String productId) {
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
                        if (!resultBuffer.toString().contains(team.getTeamUserId())) {
                            if (!StringUtil.isBlank(resultBuffer.toString())) {
                                resultBuffer.append(",");
                            }
                            resultBuffer.append(team.getTeamUserId());
                        }
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
    public void removeUserListByProduct(String productId) {
        UserUtils.removeCache(USER_CACHE_TEAM_LIST_BY_PRODUCT + productId);
    }

    /**
     * 获取产品线项目组员列表
     */
    public String getUserListByProductLine(String productLineId) {
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
    public void removeUserListByProductLine(String productLineId) {
        UserUtils.removeCache(USER_CACHE_TEAM_LIST_BY_PRODUCT_LINE + productLineId);
        List<Product> products = getProductListByLine(productLineId);
        for (Product product : products) {
            removeUserListByProduct(String.valueOf(product.getProductId()));
        }
    }

    /**
     * 获取当前用户可访问的产品线
     */
    public List<ProductLine> getProductLineListByUser() {
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
    public void removeProductLineListByUser() {
        UserUtils.removeCache(USER_CACHE_PRODUCT_LINE_LIST_BY_USER);
        List<ProductLine> productLines = getProductLineList();
        for (ProductLine productLine : productLines) {
            removeProductListByProductLineUser(String.valueOf(productLine.getProductLineId()));
        }
    }

    /**
     * 获取当前产品线中用户可访问的产品
     */
    public List<Product> getProductListByProductLineUser(String productLineId) {
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
    public void removeProductListByProductLineUser(String productLineId) {
        UserUtils.removeCache(USER_CACHE_PRODUCT_LIST_BY_LINE_USER + productLineId);
        removeUserListByProductLine(productLineId);
    }

    /**
     * 获取所有产品中用户可访问的产品
     */
    public List<Product> getAllProductListByUser() {
        List<Product> result = (List<Product>) UserUtils.getCache(USER_CACHE_ALL_PRODUCT_LIST_BY_USER);
//        if (result == null) {
//            List<Product> products = getProductList();
//            result = new ArrayList<Product>();
//            String loginId = userUtils.getUserId();
//            for (Product product : products) {
//                if (validateProduct(loginId, product) < 3) {
//                    result.add(product);
//                }
//            }
//            userUtils.putCache(USER_CACHE_ALL_PRODUCT_LIST_BY_USER, result);
//        }
        if (result == null) {
            result = productService.getProductByUser(UserUtils.getUserId(), 0, null);
            UserUtils.putCache(USER_CACHE_ALL_PRODUCT_LIST_BY_USER, result);
        }
        return result;
    }

    /**
     * 清除当前用户可访问的产品
     */
    public void removeAllProductListByUser() {
        UserUtils.removeCache(USER_CACHE_ALL_PRODUCT_LIST_BY_USER);
        List<Product> products = getProductList();
        for (Product product : products) {
            removeUserListByProduct(String.valueOf(product.getProductId()));
        }
    }

    public String getFirstProductLine() {
        List<ProductLine> productLines = getProductLineListByUser();
        for (ProductLine productLine : productLines) {
            if (getProductListByProductLineUser(String.valueOf(productLine.getProductLineId())).size() > 0) {
                return productLine.getProductLineId().toString();
            }
        }
        return null;
    }

    public void prepareForFirst(HttpServletResponse response) {
        String firstProductLineId = getFirstProductLine();
        if (!StringUtil.isBlank(firstProductLineId)) {
            CookieUtils.setCookie(response, "cookieProductLineId", firstProductLineId);
            if (getProductListByProductLineUser(firstProductLineId).size() > 0) {
                CookieUtils.setCookie(response, "cookieProductId", String.valueOf(getProductListByProductLineUser(firstProductLineId).get(0).getProductId()));
            }
        } else if (getProductLineListByUser().size() > 0) {
            firstProductLineId = String.valueOf(getProductLineListByUser().get(0).getProductLineId());
            CookieUtils.setCookie(response, "cookieProductLineId", firstProductLineId);
            if (getProductListByProductLineUser(firstProductLineId).size() > 0) {
                CookieUtils.setCookie(response, "cookieProductId", String.valueOf(getProductListByProductLineUser(firstProductLineId).get(0).getProductId()));
            }
        }
    }

    private Integer validateProductLine(String loginId, ProductLine productLine) {
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
                //TODO:FIX
//                for (OrgRole role : UserUtils.getUserRoleList()) {
//                    if (productLine.getProductLineWhiteList().contains(String.valueOf(role.getOrgRoleId()))) {
//                        return 2;
//                    }
//                }
            }
        }
        return 3;
    }

    private Integer validateProduct(String loginId, Product product) {
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
                //TODO:FIX

//                for (OrgRole role : UserUtils.getUserRoleList()) {
//                    if (StringUtil.isBlank(product.getProductWhiteList())) return 3;
//                    if (product.getProductWhiteList().contains(String.valueOf(role.getOrgRoleId()))) {
//                        return 2;
//                    }
//                }
            }
        }
        return 3;
    }

}
