package org.tinygroup.sdpm.product.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.dao.condition.ConditionUtils;
import org.tinygroup.sdpm.org.dao.OrgRoleUserDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleUser;
import org.tinygroup.sdpm.product.biz.inter.ProductManager;
import org.tinygroup.sdpm.product.dao.ProductDao;
import org.tinygroup.sdpm.product.dao.ProductPlanDao;
import org.tinygroup.sdpm.product.dao.ProductReleaseDao;
import org.tinygroup.sdpm.product.dao.ProductStoryDao;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductAndLine;
import org.tinygroup.sdpm.product.dao.utils.FieldUtil;
import org.tinygroup.sdpm.project.dao.impl.ProjectBuildDaoImpl;
import org.tinygroup.sdpm.quality.dao.QualityBugDao;
import org.tinygroup.sdpm.quality.dao.QualityTestCaseDao;
import org.tinygroup.sdpm.quality.dao.QualityTestTaskDao;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductManagerImpl implements ProductManager {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private OrgRoleUserDao orgRoleUserDao;
    @Autowired
    private ProductStoryDao productStoryDao;
    @Autowired
    private ProductReleaseDao productReleaseDao;
    @Autowired
    private ProductPlanDao productPlanDao;
    @Autowired
    private ProjectBuildDaoImpl projectBuildDao;
    @Autowired
    private QualityBugDao qualityBugDao;
    @Autowired
    private QualityTestCaseDao qualityTestCaseDao;
    @Autowired
    private QualityTestTaskDao qualityTestTaskDao;

    public Product add(Product product) {
        return productDao.add(product);
    }

    public int update(Product product) {
    /*    if (2 != product.getAcl()) {
            product.setProductWhiteList("");
        }*/
        return productDao.edit(product);
    }

    public int delete(Integer productId) {
        //删计划
        productPlanDao.deletePlanByProduct(productId);
        //删需求
        productStoryDao.deleteStoryByProduct(productId);
        //删发布
        productReleaseDao.deleteReleaseByProduct(productId);
        //删版本
        projectBuildDao.deleteBuildByProductId(productId);
        //删bug
        qualityBugDao.deleteBugsByProduct(productId);
        //删case
        qualityTestCaseDao.deleteCaseByProduct(productId);
        //删testTask
        qualityTestTaskDao.deleteTestTaskByProduct(productId);

        Product product = new Product();
        product.setProductId(productId);
        product.setDeleted(FieldUtil.DELETE_YES);
        product.setProductStatus(Product.STATUS_CLOSED);
        return productDao.edit(product);
    }

    public Product find(Integer productId) {
        return productDao.getByKey(productId);
    }

    @Override
    public Product findWithoutGroupBy(Integer productId) {
        return productDao.getProductWithoutGroupBy(productId);
    }


    public int[] updateBatch(List<Product> products) {
        return productDao.batchUpdate(products);
    }

    public List<Product> findList(Product product) {
        return productDao.query(product, null);
    }

    public List<Product> findList(Product product, String order, String ordertype) {
        if (StringUtil.isBlank(order)) {
            return productDao.query(product);
        }
        return productDao.query(product, new OrderBy(NameUtil.resolveNameDesc("product." + order), !("desc".equals(ordertype)) ? true : false));
    }

    public Pager<Product> findPager(int page, int limit, Product product, String order, String ordertype) {

        return productDao.queryPager((page - 1) * limit, limit, product, (order == null || "".equals(order)) ? null : new OrderBy(NameUtil.resolveNameDesc("product." + order), !("desc".equals(ordertype)) ? true : false));
    }

    public List<Product> findList(Integer... productId) {
        if (productId == null || productId.length == 0) return new ArrayList<Product>();
        return productDao.getByKeys(productId);
    }

    public List<ProductAndLine> getProductAndLine(Product product) {

        return productDao.getProductAndLine(product);
    }

    public List<String> getProductNameByLineId(Integer productLineId) {

        return productDao.getProductNameByLineId(productLineId);
    }

    public List<Product> getProductByUser(String userId, Integer delete, Integer productLineId, ConditionCarrier carrier) {
        List<Product> productList = productDao.getProductByUser(userId, delete, productLineId, mergeCondition(carrier));
        Product product = new Product();
        product.setDeleted(delete);
        product.setAcl(Product.ACl_TEAM_AND_ROLE);
        List<Product> products = productDao.query(product);
        OrgRoleUser role = new OrgRoleUser();
        role.setOrgUserId(userId);
        List<OrgRoleUser> orgRoles = orgRoleUserDao.query(role);
        return mergeUserProducts(productList, products, orgRoles);
    }

    public List<Product> getProductByUserWithCount(String userId, Integer delete, boolean noRole, ConditionCarrier carrier) {
        List<Product> productList = productDao.getProductByUserWithCount(userId, delete, noRole, mergeCondition(carrier));
        Product product = new Product();
        product.setDeleted(delete);
        product.setAcl(Product.ACl_TEAM_AND_ROLE);
        List<Product> products = productDao.queryWithCount(product);
        OrgRoleUser role = new OrgRoleUser();
        role.setOrgUserId(userId);
        List<OrgRoleUser> orgRoles = orgRoleUserDao.query(role);
        return mergeUserProducts(productList, products, orgRoles);
    }

    public List<Product> getProductByUserAndProductLineWithCount(String userId, Integer productLineId, Integer delete, ConditionCarrier carrier) {
        return productDao.getProductByUserAndProductLineWithCount(userId, productLineId, delete, mergeCondition(carrier));
    }

    public List<Integer> getTeamRoleProductLineIds(String userId, Integer delete, ConditionCarrier carrier) {
        List<Integer> pIds = new ArrayList<Integer>();
        for (Product product : getProductByUser(userId, delete, null, carrier)) {
            if (!pIds.contains(product.getProductLineId())) {
                pIds.add(product.getProductLineId());
            }
        }
        return pIds;
    }

    public List<Product> productInCondition(String condition, Integer limit, Integer... ids) {
        if (ids == null || ids.length == 0) return new ArrayList<Product>();
        return productDao.productInCondition(condition, limit, ids);
    }

    private List<Product> mergeUserProducts(List<Product> productWithOutRole, List<Product> productWithRole, List<OrgRoleUser> roleUsers) {
        if (productWithRole.size() == 0) return productWithOutRole;
        for (OrgRoleUser orgRoleUser : roleUsers) {
            for (Product product1 : productWithRole) {
                String whiteList = product1.getProductWhiteList();
                if (whiteList != null) {
                    String[] ids = whiteList.split(",");
                    List<String> idList = Arrays.asList(ids);
                    if (idList.contains(String.valueOf(orgRoleUser.getOrgRoleId())) && !productWithOutRole.contains(product1)) {
                        productWithOutRole.add(product1);
                    }
                }
            }
        }
        return productWithOutRole;
    }

    private Condition mergeCondition(ConditionCarrier carrier) {
        return ConditionUtils.mergeCondition(carrier, null);
    }
}
