package org.tinygroup.sdpm.util;

import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoclib;
import org.tinygroup.sdpm.document.service.impl.DocServiceImpl;
import org.tinygroup.sdpm.document.service.inter.DocService;
import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.impl.UserServiceImpl;
import org.tinygroup.sdpm.org.service.inter.UserService;import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.product.service.impl.ProductServiceImpl;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.productLine.service.ProductLineService;
import org.tinygroup.sdpm.productLine.service.impl.ProductLineServiceImpl;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.sdpm.project.service.impl.ProjectProductServiceImpl;
import org.tinygroup.sdpm.project.service.impl.ProjectServiceImpl;
import org.tinygroup.sdpm.project.service.impl.TeamServiceImpl;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.project.service.inter.TeamService;

import java.util.ArrayList;
import java.util.List;

/**
 * 常用数据获取工具集
 * Created by Hulk on 2015/10/21.
 */
public class CmsUtils {
    private static final String CMS_CACHE = "cmsCache";
    private static final String CMS_CACHE_PROJECT_LIST = "projectList";
    private static final String CMS_CACHE_PRODUCT_LIST = "productList";
    private static final String CMS_CACHE_PRODUCT_LIST_LINE_ID_ = "productList_lineId_";
    private static final String CMS_CACHE_PRODUCT_LINE_LIST = "projectLineList";
    private static final String CMS_CACHE_DOCLIB_LIST = "docLibList";
    private static final String CMS_CACHE_TEAM_LIST_BY_PRODUCT = "teamList_product";
    private static final String CMS_CACHE_TEAM_LIST_BY_PRODUCT_LINE = "teamList_productLine";
    private static final String CMS_CACHE_PRODUCT_LIST_BY_LINE_USER = "productList_line_user";
    private static final String CMS_CACHE_PRODUCT_LINE_LIST_BY_USER = "productLineList_user";

    private static ProjectService projectService = SpringContextHolder.getBean(ProjectServiceImpl.class);
    private static ProductService productService = SpringContextHolder.getBean(ProductServiceImpl.class);
    private static ProductLineService productLineService = SpringContextHolder.getBean(ProductLineServiceImpl.class);
    private static DocService docService = SpringContextHolder.getBean(DocServiceImpl.class);
    private static ProjectProductService projectProductService= SpringContextHolder.getBean(ProjectProductServiceImpl.class);
    private static TeamService teamService= SpringContextHolder.getBean(TeamServiceImpl.class);
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
        CacheUtils.remove(CMS_CACHE, CMS_CACHE_PROJECT_LIST);
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
     * 获得文档库
     */
    public static DocumentDoclib getDocLib(String libId) {
        List<DocumentDoclib> libList = getDocLibList();
        if (libId == null && libList != null && !libList.isEmpty()) {
            return libList.get(0);
        }
        for (DocumentDoclib doclib : libList) {
            if (doclib.getDocLibId().toString().equals(libId)) {
                return doclib;
            }
        }
        return new DocumentDoclib();
    }

    /**
     * 获得文档库列表
     */
    public static List<DocumentDoclib> getDocLibList() {
        List<DocumentDoclib> libList = (List<DocumentDoclib>) CacheUtils.get(CMS_CACHE, CMS_CACHE_DOCLIB_LIST);
        if (libList == null) {
            libList = docService.findDoclibList(null);
            CacheUtils.put(CMS_CACHE, CMS_CACHE_DOCLIB_LIST, libList);
        }
        return libList;
    }

    /**
     * 清除文档库列表
     */
    public static void removeDocLibList() {
        CacheUtils.remove(CMS_CACHE, CMS_CACHE_DOCLIB_LIST);
    }

    /**
     * 获取产品项目组员列表
     */

    public static String getUserListByProduct(String productId){
        String result = (String) CacheUtils.get(CMS_CACHE, CMS_CACHE_TEAM_LIST_BY_PRODUCT);
        if(StringUtil.isBlank(result)) {
            List<ProjectProduct> projectProducts = projectProductService.findProjects(Integer.parseInt(productId));
            List<List<ProjectTeam>> teamsList = new ArrayList<List<ProjectTeam>>();
            for (ProjectProduct projectProduct : projectProducts) {
                if (projectProduct.getProjectId() != null && projectProduct.getProjectId() > 0) {
                    teamsList.add(teamService.findTeamByProjectId(projectProduct.getProjectId()));
                }
            }
            StringBuffer resultBuffer = new StringBuffer("");
            for (List<ProjectTeam> teams : teamsList) {
                for (ProjectTeam team : teams) {
                    if (!StringUtil.isBlank(team.getTeamUserId())) {
                        if (!StringUtil.isBlank(result.toString())) {
                            resultBuffer.append(",");
                        }
                        resultBuffer.append(team.getTeamUserId());
                    }
                }
            }
            result = resultBuffer.toString();
            CacheUtils.put(CMS_CACHE,CMS_CACHE_TEAM_LIST_BY_PRODUCT,result);
        }
        return result;
    }

    /**
     * 清除产品项目组员列表
     */
    public static void removeUserListByProduct(){
        CacheUtils.remove(CMS_CACHE,CMS_CACHE_TEAM_LIST_BY_PRODUCT);
    }

    /**
     * 获取产品线项目组员列表
     */
    public static String getUserListByProductLine(String productLineId){
        String result = (String) UserUtils.getCache(CMS_CACHE_TEAM_LIST_BY_PRODUCT_LINE);
        if(StringUtil.isBlank(result)) {
            StringBuffer users = new StringBuffer("");
            Product product = new Product();
            product.setProductLineId(Integer.parseInt(productLineId));
            List<Product> products = productService.findProductList(product);
            for(Product p : products){
                if(p.getProductId()!=null&&p.getProductId()>0){
                    if(!StringUtil.isBlank(users.toString())){
                        users.append(",");
                    }
                    users.append(getUserListByProduct(String.valueOf(p.getProductId())));
                }
            }
            result = users.toString();
            UserUtils.putCache(CMS_CACHE_TEAM_LIST_BY_PRODUCT_LINE,result);
        }
        return result;
    }

    /**
     * 获取当前用户可访问的产品线
     */
    public static List<ProductLine> getProductLineListByUser(){
        List<ProductLine> result = (List<ProductLine>) UserUtils.getCache(CMS_CACHE_PRODUCT_LINE_LIST_BY_USER);
        if(result==null) {
            List<ProductLine> productLines = getProductLineList();
            result = new ArrayList<ProductLine>();
            String loginId = UserUtils.getUserId();
            for (ProductLine productLine : productLines) {
                if (productLine.getAcl() < 1) {
                    result = productLines;
                    break;
                } else if (productLine.getAcl() == 1) {
                    if (loginId.equals(productLine.getProductLineOwner())) {
                        result.add(productLine);
                        continue;
                    } else if (getUserListByProductLine(String.valueOf(productLine.getProductLineId())).contains(loginId)) {
                        result.add(productLine);
                        continue;
                    }
                } else {
                    if (loginId.equals(productLine.getProductLineOwner())) {
                        result.add(productLine);
                        continue;
                    } else if (getUserListByProductLine(String.valueOf(productLine.getProductLineId())).contains(loginId)) {
                        result.add(productLine);
                        continue;
                    } else {
                        for (OrgRole role : UserUtils.getUserRoleList()) {
                            if (productLine.getProductLineWhiteList().contains(String.valueOf(role.getOrgRoleId()))) {
                                result.add(productLine);
                                break;
                            }
                        }
                    }
                }
            }
            UserUtils.putCache(CMS_CACHE_PRODUCT_LINE_LIST_BY_USER,result);
        }
        return result;
    }

    /**
      * 获取当前产品线中用户可访问的产品
      */
    public static List<Product> getProductListByProductLineUser(String productLineId){
        List<Product> result = (List<Product>) UserUtils.getCache(CMS_CACHE_PRODUCT_LIST_BY_LINE_USER);
        if(result==null) {
            List<Product> products = getProductListByLine(productLineId);
            result = new ArrayList<Product>();
            String loginId = UserUtils.getUserId();
            for (Product product : products) {
                if (product.getAcl() < 1) {
                    result = products;
                    break;
                } else if (product.getAcl() == 1) {
                    if (loginId.equals(product.getProductOwner())) {
                        result.add(product);
                        continue;
                    } else if (getUserListByProduct(String.valueOf(product.getProductId())).contains(loginId)) {
                        result.add(product);
                        continue;
                    }
                } else {
                    if (loginId.equals(product.getProductOwner())) {
                        result.add(product);
                        continue;
                    } else if (getUserListByProduct(String.valueOf(product.getProductId())).contains(loginId)) {
                        result.add(product);
                        continue;
                    } else {
                        for (OrgRole role : UserUtils.getUserRoleList()) {
                            if (product.getProductWhiteList().contains(String.valueOf(role.getOrgRoleId()))) {
                                result.add(product);
                                break;
                            }
                        }
                    }
                }
            }
            UserUtils.putCache(CMS_CACHE_PRODUCT_LIST_BY_LINE_USER,result);
        }
        return result;
    }
}
