/**
 * Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 * <p>
 * Licensed under the GPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/gpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tinygroup.sdpm.product.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.product.dao.ProductDao;
import org.tinygroup.sdpm.product.dao.constant.ProductTable;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductAndLine;
import org.tinygroup.sdpm.product.dao.utils.FieldUtil;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.base.FragmentSql;
import org.tinygroup.tinysqldsl.base.Table;
import org.tinygroup.tinysqldsl.expression.Function;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.expression.relational.ExistsExpression;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.formitem.SubSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.product.dao.constant.ProductPlanTable.PRODUCT_PLANTABLE;
import static org.tinygroup.sdpm.product.dao.constant.ProductReleaseTable.PRODUCT_RELEASETABLE;
import static org.tinygroup.sdpm.product.dao.constant.ProductStoryTable.PRODUCT_STORYTABLE;
import static org.tinygroup.sdpm.product.dao.constant.ProductTable.PRODUCTTABLE;
import static org.tinygroup.sdpm.productLine.dao.constant.ProductLineTable.PRODUCT_LINETABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectTeamTable.PROJECT_TEAMTABLE;
import static org.tinygroup.sdpm.quality.dao.constant.QualityBugTable.QUALITY_BUGTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.select;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.or;
import static org.tinygroup.tinysqldsl.select.Join.leftJoin;

@Repository
public class ProductDaoImpl extends TinyDslDaoSupport implements ProductDao {

    public static Condition productQueryCondition(Product t) {
        return
                t == null ? null : and(
                        PRODUCTTABLE.COMPANY_ID.eq(t.getCompanyId()),
                        PRODUCTTABLE.DEPT_ID.eq(t.getDeptId()),
                        PRODUCTTABLE.PRODUCT_LINE_ID.eq(t.getProductLineId()),
                        PRODUCTTABLE.PRODUCT_NAME.eq(t.getProductName()),
                        PRODUCTTABLE.PRODUCT_CODE.eq(t.getProductCode()),
                        PRODUCTTABLE.PRODUCT_ORDER.eq(t.getProductOrder()),
                        PRODUCTTABLE.PRODUCT_STATUS.eq(t.getProductStatus()),
                        PRODUCTTABLE.PRODUCT_DESC.eq(t.getProductDesc()),
                        PRODUCTTABLE.PRODUCT_OWNER.eq(t.getProductOwner()),
                        PRODUCTTABLE.PRODUCT_QUALITY_MANAGER.eq(t.getProductQualityManager()),
                        PRODUCTTABLE.PRODUCT_DELIVERY_MANAGER.eq(t.getProductDeliveryManager()),
                        PRODUCTTABLE.ACL.eq(t.getAcl()),
                        PRODUCTTABLE.PRODUCT_WHITE_LIST.eq(t.getProductWhiteList()),
                        PRODUCTTABLE.PRODUCT_CREATED_BY.eq(t.getProductCreatedBy()),
                        PRODUCTTABLE.PRODUCT_CREATED_DATE.eq(t.getProductCreatedDate()),
                        PRODUCTTABLE.PRODUCT_CREATED_VERSION.eq(t.getProductCreatedVersion()),
                        PRODUCTTABLE.DELETED.eq(t.getDeleted()));
    }

    public Product add(final Product product) {
        return getDslTemplate().insertAndReturnKey(product, new InsertGenerateCallback<Product>() {
            public Insert generate(Product t) {
                Insert insert = insertInto(PRODUCTTABLE).values(
                        PRODUCTTABLE.PRODUCT_ID.value(t.getProductId()),
                        PRODUCTTABLE.COMPANY_ID.value(t.getCompanyId()),
                        PRODUCTTABLE.DEPT_ID.value(t.getDeptId()),
                        PRODUCTTABLE.PRODUCT_LINE_ID.value(t.getProductLineId()),
                        PRODUCTTABLE.PRODUCT_NAME.value(t.getProductName()),
                        PRODUCTTABLE.PRODUCT_CODE.value(t.getProductCode()),
                        PRODUCTTABLE.PRODUCT_ORDER.value(t.getProductOrder()),
                        PRODUCTTABLE.PRODUCT_STATUS.value(t.getProductStatus()),
                        PRODUCTTABLE.PRODUCT_DESC.value(t.getProductDesc()),
                        PRODUCTTABLE.PRODUCT_OWNER.value(t.getProductOwner()),
                        PRODUCTTABLE.PRODUCT_QUALITY_MANAGER.value(t.getProductQualityManager()),
                        PRODUCTTABLE.PRODUCT_DELIVERY_MANAGER.value(t.getProductDeliveryManager()),
                        PRODUCTTABLE.ACL.value(t.getAcl()),
                        PRODUCTTABLE.PRODUCT_WHITE_LIST.value(t.getProductWhiteList()),
                        PRODUCTTABLE.PRODUCT_CREATED_BY.value(t.getProductCreatedBy()),
                        PRODUCTTABLE.PRODUCT_CREATED_DATE.value(t.getProductCreatedDate()),
                        PRODUCTTABLE.PRODUCT_CREATED_VERSION.value(t.getProductCreatedVersion()),
                        PRODUCTTABLE.DELETED.value(t.getDeleted()));
                return insert;
            }
        });
    }

    public int edit(Product product) {
        if (product == null || product.getProductId() == null) {
            return 0;
        }
        return getDslTemplate().update(product, new UpdateGenerateCallback<Product>() {
            public Update generate(Product t) {
                return update(PRODUCTTABLE).set(
                        PRODUCTTABLE.COMPANY_ID.value(t.getCompanyId()),
                        PRODUCTTABLE.DEPT_ID.value(t.getDeptId()),
                        PRODUCTTABLE.PRODUCT_LINE_ID.value(t.getProductLineId()),
                        PRODUCTTABLE.PRODUCT_NAME.value(t.getProductName()),
                        PRODUCTTABLE.PRODUCT_CODE.value(t.getProductCode()),
                        PRODUCTTABLE.PRODUCT_ORDER.value(t.getProductOrder()),
                        PRODUCTTABLE.PRODUCT_STATUS.value(t.getProductStatus()),
                        PRODUCTTABLE.PRODUCT_DESC.value(t.getProductDesc()),
                        PRODUCTTABLE.PRODUCT_OWNER.value(t.getProductOwner()),
                        PRODUCTTABLE.PRODUCT_QUALITY_MANAGER.value(t.getProductQualityManager()),
                        PRODUCTTABLE.PRODUCT_DELIVERY_MANAGER.value(t.getProductDeliveryManager()),
                        PRODUCTTABLE.ACL.value(t.getAcl()),
                        PRODUCTTABLE.PRODUCT_WHITE_LIST.value(t.getProductWhiteList()),
                        PRODUCTTABLE.PRODUCT_CREATED_BY.value(t.getProductCreatedBy()),
                        PRODUCTTABLE.PRODUCT_CREATED_DATE.value(t.getProductCreatedDate()),
                        PRODUCTTABLE.PRODUCT_CREATED_VERSION.value(t.getProductCreatedVersion()),
                        PRODUCTTABLE.DELETED.value(t.getDeleted())).where(
                        PRODUCTTABLE.PRODUCT_ID.eq(t.getProductId()));
            }
        });
    }

    public int deleteByKey(Integer pk) {
        if (pk == null) {
            return 0;
        }
        return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
            public Delete generate(Serializable pk) {
                return delete(PRODUCTTABLE).where(PRODUCTTABLE.PRODUCT_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(PRODUCTTABLE).where(PRODUCTTABLE.PRODUCT_ID.in(t));
            }
        }, pks);
    }

    public List<Product> getByKeys(Integer... pk) {

        SelectGenerateCallback<Serializable[]> callback = new SelectGenerateCallback<Serializable[]>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable[] t) {

                return selectFrom(PRODUCTTABLE).where(PRODUCTTABLE.PRODUCT_ID.in(t));
            }

        };
        Select select = callback.generate(pk);
        return getDslSession().fetchList(select, Product.class);
    }

    public Product getByKey(Integer pk) {
        try {
            return getDslTemplate().getByKey(pk, Product.class, new SelectGenerateCallback<Serializable>() {
                @SuppressWarnings("rawtypes")
                public Select generate(Serializable t) {
                    return selectFrom(PRODUCTTABLE).where(PRODUCTTABLE.PRODUCT_ID.eq(t));
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public List<Product> query(Product product, final OrderBy... orderArgs) {
        if (product == null) {
            product = new Product();
        }
        return getDslTemplate().query(product, new SelectGenerateCallback<Product>() {

            @SuppressWarnings("rawtypes")
            public Select generate(Product t) {
                Select select = selectFrom(PRODUCTTABLE).where(
                        and(
                                PRODUCTTABLE.COMPANY_ID.eq(t.getCompanyId()),
                                PRODUCTTABLE.DEPT_ID.eq(t.getDeptId()),
                                PRODUCTTABLE.PRODUCT_LINE_ID.eq(t.getProductLineId()),
                                PRODUCTTABLE.PRODUCT_NAME.eq(t.getProductName()),
                                PRODUCTTABLE.PRODUCT_CODE.eq(t.getProductCode()),
                                PRODUCTTABLE.PRODUCT_ORDER.eq(t.getProductOrder()),
                                PRODUCTTABLE.PRODUCT_STATUS.eq(t.getProductStatus()),
                                PRODUCTTABLE.PRODUCT_DESC.eq(t.getProductDesc()),
                                PRODUCTTABLE.PRODUCT_OWNER.eq(t.getProductOwner()),
                                PRODUCTTABLE.PRODUCT_QUALITY_MANAGER.eq(t.getProductQualityManager()),
                                PRODUCTTABLE.PRODUCT_DELIVERY_MANAGER.eq(t.getProductDeliveryManager()),
                                PRODUCTTABLE.ACL.eq(t.getAcl()),
                                PRODUCTTABLE.PRODUCT_WHITE_LIST.eq(t.getProductWhiteList()),
                                PRODUCTTABLE.PRODUCT_CREATED_BY.eq(t.getProductCreatedBy()),
                                PRODUCTTABLE.PRODUCT_CREATED_DATE.eq(t.getProductCreatedDate()),
                                PRODUCTTABLE.PRODUCT_CREATED_VERSION.eq(t.getProductCreatedVersion()),
                                PRODUCTTABLE.DELETED.eq(t.getDeleted())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public Pager<Product> queryPager(int start, int limit, Product product, final OrderBy... orderArgs) {
        if (product == null) {
            product = new Product();
        }
        return getDslTemplate().queryPager(start > 0 ? start : 0, limit, product, false, new SelectGenerateCallback<Product>() {

            public Select generate(Product t) {
                Select select = getComplexSelect().where(
                        and(
                                PRODUCTTABLE.COMPANY_ID.eq(t.getCompanyId()),
                                PRODUCTTABLE.DEPT_ID.eq(t.getDeptId()),
                                PRODUCTTABLE.PRODUCT_LINE_ID.eq(t.getProductLineId()),
                                PRODUCTTABLE.PRODUCT_NAME.eq(t.getProductName()),
                                PRODUCTTABLE.PRODUCT_CODE.eq(t.getProductCode()),
                                PRODUCTTABLE.PRODUCT_ORDER.eq(t.getProductOrder()),
                                PRODUCTTABLE.PRODUCT_STATUS.eq(t.getProductStatus()),
                                PRODUCTTABLE.PRODUCT_DESC.eq(t.getProductDesc()),
                                PRODUCTTABLE.PRODUCT_OWNER.eq(t.getProductOwner()),
                                PRODUCTTABLE.PRODUCT_QUALITY_MANAGER.eq(t.getProductQualityManager()),
                                PRODUCTTABLE.PRODUCT_DELIVERY_MANAGER.eq(t.getProductDeliveryManager()),
                                PRODUCTTABLE.ACL.eq(t.getAcl()),
                                PRODUCTTABLE.PRODUCT_WHITE_LIST.eq(t.getProductWhiteList()),
                                PRODUCTTABLE.PRODUCT_CREATED_BY.eq(t.getProductCreatedBy()),
                                PRODUCTTABLE.PRODUCT_CREATED_DATE.eq(t.getProductCreatedDate()),
                                PRODUCTTABLE.PRODUCT_CREATED_VERSION.eq(t.getProductCreatedVersion()),
                                PRODUCTTABLE.DELETED.eq(t.getDeleted()))).groupBy(PRODUCTTABLE.PRODUCT_ID, PRODUCTTABLE.PRODUCT_NAME, new Column(new Table(), "activeSum"),
                        new Column(new Table(), "changeSum"), new Column(new Table(), "draftSum"), new Column(new Table(), "closeSum"),
                        new Column(new Table(), "resolveSum"), new Column(new Table(), "assignSum"), new Column(new Table(), "bugCount"));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<Product> products) {
        if (CollectionUtil.isEmpty(products)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, products, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(PRODUCTTABLE).values(
                        PRODUCTTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
                        PRODUCTTABLE.DEPT_ID.value(new JdbcNamedParameter("deptId")),
                        PRODUCTTABLE.PRODUCT_LINE_ID.value(new JdbcNamedParameter("productLineId")),
                        PRODUCTTABLE.PRODUCT_NAME.value(new JdbcNamedParameter("productName")),
                        PRODUCTTABLE.PRODUCT_CODE.value(new JdbcNamedParameter("productCode")),
                        PRODUCTTABLE.PRODUCT_ORDER.value(new JdbcNamedParameter("productOrder")),
                        PRODUCTTABLE.PRODUCT_STATUS.value(new JdbcNamedParameter("productStatus")),
                        PRODUCTTABLE.PRODUCT_DESC.value(new JdbcNamedParameter("productDesc")),
                        PRODUCTTABLE.PRODUCT_OWNER.value(new JdbcNamedParameter("productOwner")),
                        PRODUCTTABLE.PRODUCT_QUALITY_MANAGER.value(new JdbcNamedParameter("productQualityManager")),
                        PRODUCTTABLE.PRODUCT_DELIVERY_MANAGER.value(new JdbcNamedParameter("productDeliveryManager")),
                        PRODUCTTABLE.ACL.value(new JdbcNamedParameter("acl")),
                        PRODUCTTABLE.PRODUCT_WHITE_LIST.value(new JdbcNamedParameter("productWhiteList")),
                        PRODUCTTABLE.PRODUCT_CREATED_BY.value(new JdbcNamedParameter("productCreatedBy")),
                        PRODUCTTABLE.PRODUCT_CREATED_DATE.value(new JdbcNamedParameter("productCreatedDate")),
                        PRODUCTTABLE.PRODUCT_CREATED_VERSION.value(new JdbcNamedParameter("productCreatedVersion")),
                        PRODUCTTABLE.DELETED.value(new JdbcNamedParameter("deleted")));
            }
        });
    }

    public int[] batchInsert(List<Product> products) {
        return batchInsert(true, products);
    }

    public int[] batchUpdate(List<Product> products) {
        if (CollectionUtil.isEmpty(products)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(products, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(PRODUCTTABLE).set(
                        PRODUCTTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
                        PRODUCTTABLE.DEPT_ID.value(new JdbcNamedParameter("deptId")),
                        PRODUCTTABLE.PRODUCT_LINE_ID.value(new JdbcNamedParameter("productLineId")),
                        PRODUCTTABLE.PRODUCT_NAME.value(new JdbcNamedParameter("productName")),
                        PRODUCTTABLE.PRODUCT_CODE.value(new JdbcNamedParameter("productCode")),
                        PRODUCTTABLE.PRODUCT_ORDER.value(new JdbcNamedParameter("productOrder")),
                        PRODUCTTABLE.PRODUCT_STATUS.value(new JdbcNamedParameter("productStatus")),
                        PRODUCTTABLE.PRODUCT_DESC.value(new JdbcNamedParameter("productDesc")),
                        PRODUCTTABLE.PRODUCT_OWNER.value(new JdbcNamedParameter("productOwner")),
                        PRODUCTTABLE.PRODUCT_QUALITY_MANAGER.value(new JdbcNamedParameter("productQualityManager")),
                        PRODUCTTABLE.PRODUCT_DELIVERY_MANAGER.value(new JdbcNamedParameter("productDeliveryManager")),
                        PRODUCTTABLE.ACL.value(new JdbcNamedParameter("acl")),
                        PRODUCTTABLE.PRODUCT_WHITE_LIST.value(new JdbcNamedParameter("productWhiteList")),
                        PRODUCTTABLE.PRODUCT_CREATED_BY.value(new JdbcNamedParameter("productCreatedBy")),
                        PRODUCTTABLE.PRODUCT_CREATED_DATE.value(new JdbcNamedParameter("productCreatedDate")),
                        PRODUCTTABLE.PRODUCT_CREATED_VERSION.value(new JdbcNamedParameter("productCreatedVersion")),
                        PRODUCTTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
                        PRODUCTTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")));
            }
        });
    }

    public int[] batchDelete(List<Product> products) {
        if (CollectionUtil.isEmpty(products)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(products, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(PRODUCTTABLE).where(and(
                        PRODUCTTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
                        PRODUCTTABLE.COMPANY_ID.eq(new JdbcNamedParameter("companyId")),
                        PRODUCTTABLE.DEPT_ID.eq(new JdbcNamedParameter("deptId")),
                        PRODUCTTABLE.PRODUCT_LINE_ID.eq(new JdbcNamedParameter("productLineId")),
                        PRODUCTTABLE.PRODUCT_NAME.eq(new JdbcNamedParameter("productName")),
                        PRODUCTTABLE.PRODUCT_CODE.eq(new JdbcNamedParameter("productCode")),
                        PRODUCTTABLE.PRODUCT_ORDER.eq(new JdbcNamedParameter("productOrder")),
                        PRODUCTTABLE.PRODUCT_STATUS.eq(new JdbcNamedParameter("productStatus")),
                        PRODUCTTABLE.PRODUCT_DESC.eq(new JdbcNamedParameter("productDesc")),
                        PRODUCTTABLE.PRODUCT_OWNER.eq(new JdbcNamedParameter("productOwner")),
                        PRODUCTTABLE.PRODUCT_QUALITY_MANAGER.eq(new JdbcNamedParameter("productQualityManager")),
                        PRODUCTTABLE.PRODUCT_DELIVERY_MANAGER.eq(new JdbcNamedParameter("productDeliveryManager")),
                        PRODUCTTABLE.ACL.eq(new JdbcNamedParameter("acl")),
                        PRODUCTTABLE.PRODUCT_WHITE_LIST.eq(new JdbcNamedParameter("productWhiteList")),
                        PRODUCTTABLE.PRODUCT_CREATED_BY.eq(new JdbcNamedParameter("productCreatedBy")),
                        PRODUCTTABLE.PRODUCT_CREATED_DATE.eq(new JdbcNamedParameter("productCreatedDate")),
                        PRODUCTTABLE.PRODUCT_CREATED_VERSION.eq(new JdbcNamedParameter("productCreatedVersion")),
                        PRODUCTTABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
            }
        });
    }

    private Select addOrderByElements(Select select, OrderBy... orderBies) {
        if (orderBies == null || orderBies.length == 0) {
            return select;
        }

        List<OrderByElement> orderByElements = new ArrayList<OrderByElement>();
        for (int i = 0; i < orderBies.length; i++) {
            OrderByElement tempElement = null;

            if (orderBies[i] != null) {
                tempElement = orderBies[i].getOrderByElement();
            }
            if (tempElement != null) {
                orderByElements.add(tempElement);
            }
        }
        if (orderByElements.size() > 0) {
            select.orderBy(orderByElements.toArray(new OrderByElement[0]));
        }
        return select;
    }

    public Integer softDelete(Integer id) {
        return getDslTemplate().update(id, new UpdateGenerateCallback<Integer>() {
            public Update generate(Integer id) {
                Update update = update(PRODUCTTABLE).set(
                        PRODUCTTABLE.DELETED.value(FieldUtil.DELETE_YES)).where(
                        PRODUCTTABLE.PRODUCT_ID.eq(id));
                return update;
            }
        });

    }

    public List<ProductAndLine> getProductAndLine(Product t) {

        if (t == null) {
            t = new Product();
        }
        Select select = select(PRODUCTTABLE.PRODUCT_ID, PRODUCTTABLE.PRODUCT_NAME, PRODUCT_LINETABLE.PRODUCT_LINE_ID, PRODUCT_LINETABLE.PRODUCT_LINE_NAME)
                .from(PRODUCTTABLE).join(leftJoin(PRODUCT_LINETABLE, PRODUCTTABLE.PRODUCT_LINE_ID.eq(PRODUCT_LINETABLE.PRODUCT_LINE_ID))).where(
                        and(
                                PRODUCTTABLE.COMPANY_ID.eq(t.getCompanyId()),
                                PRODUCTTABLE.DEPT_ID.eq(t.getDeptId()),
                                PRODUCTTABLE.PRODUCT_LINE_ID.eq(t.getProductLineId()),
                                PRODUCTTABLE.PRODUCT_NAME.eq(t.getProductName()),
                                PRODUCTTABLE.PRODUCT_CODE.eq(t.getProductCode()),
                                PRODUCTTABLE.PRODUCT_ORDER.eq(t.getProductOrder()),
                                PRODUCTTABLE.PRODUCT_STATUS.eq(t.getProductStatus()),
                                PRODUCTTABLE.PRODUCT_DESC.eq(t.getProductDesc()),
                                PRODUCTTABLE.PRODUCT_OWNER.eq(t.getProductOwner()),
                                PRODUCTTABLE.PRODUCT_QUALITY_MANAGER.eq(t.getProductQualityManager()),
                                PRODUCTTABLE.PRODUCT_DELIVERY_MANAGER.eq(t.getProductDeliveryManager()),
                                PRODUCTTABLE.ACL.eq(t.getAcl()),
                                PRODUCTTABLE.PRODUCT_WHITE_LIST.eq(t.getProductWhiteList()),
                                PRODUCTTABLE.PRODUCT_CREATED_BY.eq(t.getProductCreatedBy()),
                                PRODUCTTABLE.PRODUCT_CREATED_DATE.eq(t.getProductCreatedDate()),
                                PRODUCTTABLE.PRODUCT_CREATED_VERSION.eq(t.getProductCreatedVersion()),
                                PRODUCTTABLE.DELETED.eq(t.getDeleted())));
        return getDslSession().fetchList(select, ProductAndLine.class);

    }

    public List<String> getProductNameByLineId(Integer productLineId) {

        Select select = Select.select(PRODUCTTABLE.PRODUCT_NAME).from(PRODUCTTABLE).where(PRODUCTTABLE.PRODUCT_LINE_ID.eq(productLineId));

        return getDslSession().fetchList(select, String.class);
    }

    public List<Product> getProductByUser(String userId, Integer delete, Integer productLineId, Condition condition) {
        if (productLineId != null) {
            condition = condition == null ? PRODUCTTABLE.PRODUCT_LINE_ID.eq(productLineId) : and(condition, PRODUCTTABLE.PRODUCT_LINE_ID.eq(productLineId));
        }
        Select select = select(PRODUCTTABLE.ALL, PRODUCT_LINETABLE.PRODUCT_LINE_NAME.as("productLineName")).from(PRODUCTTABLE).join(
                leftJoin(PRODUCT_LINETABLE, PRODUCTTABLE.PRODUCT_LINE_ID.eq(PRODUCT_LINETABLE.PRODUCT_LINE_ID))
        ).where(
                and(PRODUCTTABLE.DELETED.eq(delete), condition,
                        or(PRODUCTTABLE.ACL.eq(Product.ACl_All),
                                or(PRODUCTTABLE.PRODUCT_CREATED_BY.eq(userId), PRODUCTTABLE.PRODUCT_OWNER.eq(userId), PRODUCTTABLE.PRODUCT_DELIVERY_MANAGER.eq(userId), PRODUCTTABLE.PRODUCT_QUALITY_MANAGER.eq(userId), ExistsExpression.existsCondition(SubSelect.subSelect(selectFrom(PROJECT_TEAMTABLE).
                                        where(and(PRODUCTTABLE.ACL.eq(Product.ACl_TEAM), PROJECT_TEAMTABLE.PRODUCT_ID.eq(PRODUCTTABLE.PRODUCT_ID), PROJECT_TEAMTABLE.TEAM_USER_ID.eq(userId)))))))));
        return getDslSession().fetchList(select, Product.class);
    }

    public List<Product> getProductByUserWithCount(String userId, Integer delete, boolean noRole, Condition condition) {
        Condition con = null;
        if (noRole) {
            con = PRODUCTTABLE.ACL.eq(Product.ACl_All);
        }
        Select select = getComplexSelect().where(
                and(PRODUCTTABLE.DELETED.eq(delete), condition,
                        or(con,
                                or(PRODUCTTABLE.PRODUCT_CREATED_BY.eq(userId), PRODUCTTABLE.PRODUCT_OWNER.eq(userId), PRODUCTTABLE.PRODUCT_DELIVERY_MANAGER.eq(userId), PRODUCTTABLE.PRODUCT_QUALITY_MANAGER.eq(userId), ExistsExpression.existsCondition(SubSelect.subSelect(selectFrom(PROJECT_TEAMTABLE).
                                        where(and(PRODUCTTABLE.ACL.eq(Product.ACl_TEAM), PROJECT_TEAMTABLE.PRODUCT_ID.eq(PRODUCTTABLE.PRODUCT_ID), PROJECT_TEAMTABLE.TEAM_USER_ID.eq(userId))))))))).
                groupBy(PRODUCTTABLE.PRODUCT_ID, PRODUCTTABLE.PRODUCT_NAME, new Column(new Table(), "activeSum"),
                        new Column(new Table(), "changeSum"), new Column(new Table(), "draftSum"), new Column(new Table(), "closeSum"),
                        new Column(new Table(), "resolveSum"), new Column(new Table(), "assignSum"), new Column(new Table(), "bugCount"));
        return getDslSession().fetchList(select, Product.class);
    }

    public List<Product> queryWithCount(Product product) {
        Select select = getComplexSelect().where(
                and(
                        PRODUCTTABLE.COMPANY_ID.eq(product.getCompanyId()),
                        PRODUCTTABLE.DEPT_ID.eq(product.getDeptId()),
                        PRODUCTTABLE.PRODUCT_LINE_ID.eq(product.getProductLineId()),
                        PRODUCTTABLE.PRODUCT_NAME.eq(product.getProductName()),
                        PRODUCTTABLE.PRODUCT_CODE.eq(product.getProductCode()),
                        PRODUCTTABLE.PRODUCT_ORDER.eq(product.getProductOrder()),
                        PRODUCTTABLE.PRODUCT_STATUS.eq(product.getProductStatus()),
                        PRODUCTTABLE.PRODUCT_DESC.eq(product.getProductDesc()),
                        PRODUCTTABLE.PRODUCT_OWNER.eq(product.getProductOwner()),
                        PRODUCTTABLE.PRODUCT_QUALITY_MANAGER.eq(product.getProductQualityManager()),
                        PRODUCTTABLE.PRODUCT_DELIVERY_MANAGER.eq(product.getProductDeliveryManager()),
                        PRODUCTTABLE.ACL.eq(product.getAcl()),
                        PRODUCTTABLE.PRODUCT_WHITE_LIST.eq(product.getProductWhiteList()),
                        PRODUCTTABLE.PRODUCT_CREATED_BY.eq(product.getProductCreatedBy()),
                        PRODUCTTABLE.PRODUCT_CREATED_DATE.eq(product.getProductCreatedDate()),
                        PRODUCTTABLE.PRODUCT_CREATED_VERSION.eq(product.getProductCreatedVersion()),
                        PRODUCTTABLE.DELETED.eq(product.getDeleted())));
        return getDslSession().fetchList(select, Product.class);
    }

    public List<Product> getProductByUserAndProductLineWithCount(String userId, Integer productLineId, Integer delete, Condition condition) {
        Select select = getComplexSelect().where(
                and(PRODUCTTABLE.DELETED.eq(delete), condition,
                        or(PRODUCTTABLE.ACL.eq(Product.ACl_All),
                                or(PRODUCTTABLE.PRODUCT_CREATED_BY.eq(userId), PRODUCTTABLE.PRODUCT_OWNER.eq(userId), PRODUCTTABLE.PRODUCT_DELIVERY_MANAGER.eq(userId), PRODUCTTABLE.PRODUCT_QUALITY_MANAGER.eq(userId), ExistsExpression.existsCondition(SubSelect.subSelect(selectFrom(PROJECT_TEAMTABLE).
                                        where(and(PRODUCTTABLE.ACL.eq(Product.ACl_TEAM), PROJECT_TEAMTABLE.PRODUCT_ID.eq(PRODUCTTABLE.PRODUCT_ID), PROJECT_TEAMTABLE.TEAM_USER_ID.eq(userId))))))), PRODUCTTABLE.PRODUCT_LINE_ID.eq(productLineId))).
                groupBy(PRODUCTTABLE.PRODUCT_ID, PRODUCTTABLE.PRODUCT_NAME, new Column(new Table(), "activeSum"),
                        new Column(new Table(), "changeSum"), new Column(new Table(), "draftSum"), new Column(new Table(), "closeSum"),
                        new Column(new Table(), "resolveSum"), new Column(new Table(), "assignSum"), new Column(new Table(), "bugCount"));
        return getDslSession().fetchList(select, Product.class);
    }

    public List<Product> productInCondition(String condition, Integer limit, Integer... ids) {
        Select select = MysqlSelect.select(PRODUCTTABLE.PRODUCT_ID, PRODUCTTABLE.PRODUCT_NAME).from(PRODUCTTABLE).where(and(PRODUCTTABLE.PRODUCT_NAME.like(condition), PRODUCTTABLE.DELETED.eq(0), PRODUCTTABLE.PRODUCT_ID.in(ids))).limit(0, limit);
        return getDslSession().fetchList(select, Product.class);
    }

    @Override
    public Product getProductWithoutGroupBy(Integer productId) {
        Select select = selectFrom(PRODUCTTABLE).where(PRODUCTTABLE.PRODUCT_ID.eq(productId));
        return getDslSession().fetchOneResult(select, Product.class);
    }

    private Select getComplexSelect() {

        ProductTable subProduct = (ProductTable) PRODUCTTABLE.as("subProduct");
        Function planFunction = PRODUCT_PLANTABLE.PLAN_ID.count();
        Function releaseFunction = PRODUCT_RELEASETABLE.RELEASE_ID.count();
        planFunction.setDistinct(true);
        releaseFunction.setDistinct(true);
        //查询product基本数据subProduct.deleted,subProduct.product_created_by,subProduct.product_owner,subProduct.product_delivery_manager,subProduct.product_quality_manager,subProduct.acl
        SubSelect product = SubSelect.subSelect(select(subProduct.PRODUCT_ID, subProduct.PRODUCT_NAME,subProduct.PRODUCT_STATUS,
                subProduct.DELETED, subProduct.PRODUCT_CREATED_BY,
                subProduct.PRODUCT_OWNER, subProduct.PRODUCT_DELIVERY_MANAGER,
                subProduct.PRODUCT_QUALITY_MANAGER, subProduct.ACL,
                PRODUCT_LINETABLE.PRODUCT_LINE_NAME.as("productLineName"),
                planFunction.as("planCount"),
                releaseFunction.as("releaseCount")).from(subProduct).join(
                leftJoin(PRODUCT_PLANTABLE, subProduct.PRODUCT_ID.eq(PRODUCT_PLANTABLE.PRODUCT_ID)),
                leftJoin(PRODUCT_RELEASETABLE, subProduct.PRODUCT_ID.eq(PRODUCT_RELEASETABLE.PRODUCT_ID)),
                leftJoin(PRODUCT_LINETABLE, subProduct.PRODUCT_LINE_ID.eq(PRODUCT_LINETABLE.PRODUCT_LINE_ID))
        ).groupBy(subProduct.PRODUCT_ID, subProduct.PRODUCT_NAME, PRODUCT_LINETABLE.PRODUCT_LINE_NAME,
                subProduct.DELETED, subProduct.PRODUCT_CREATED_BY,
                subProduct.PRODUCT_OWNER, subProduct.PRODUCT_DELIVERY_MANAGER,
                subProduct.PRODUCT_QUALITY_MANAGER, subProduct.ACL), "product", true);
        //统计各类story总数
        SubSelect subStorySelect = SubSelect.subSelect(select(PRODUCT_STORYTABLE.PRODUCT_ID,
                FragmentSql.fragmentSelect("SUM(CASE WHEN product_story.`story_status`=1 THEN 1 ELSE 0 END) activeSum"),
                FragmentSql.fragmentSelect("SUM(CASE WHEN product_story.`story_status`=3 THEN 1 ELSE 0 END) changeSum"),
                FragmentSql.fragmentSelect("SUM(CASE WHEN product_story.`story_status`=0 THEN 1 ELSE 0 END) draftSum"),
                FragmentSql.fragmentSelect("SUM(CASE WHEN product_story.`story_status`=2 THEN 1 ELSE 0 END) closeSum")).
                from(PRODUCT_STORYTABLE).
                where(PRODUCT_STORYTABLE.DELETED.eq(0)).
                groupBy(PRODUCT_STORYTABLE.PRODUCT_ID), "subStorySelect", true);
        //统计分类bug总数
        SubSelect subBugSelect = SubSelect.subSelect(select(QUALITY_BUGTABLE.PRODUCT_ID, QUALITY_BUGTABLE.BUG_ID.count().as("bugCount"),
                FragmentSql.fragmentSelect("SUM(CASE WHEN quality_bug.`bug_status`=1 THEN 1 ELSE 0 END) resolveSum"),
                FragmentSql.fragmentSelect("SUM(CASE WHEN quality_bug.`bug_assigned_to` IS NULL THEN 1 ELSE 0 END) assignSum")).
                from(QUALITY_BUGTABLE).
                where(QUALITY_BUGTABLE.DELETED.eq(0)).
                groupBy(QUALITY_BUGTABLE.PRODUCT_ID), "subBugSelect", true);

        return MysqlSelect.select(PRODUCTTABLE.PRODUCT_ID, PRODUCTTABLE.PRODUCT_NAME,
                new Column("activeSum"),
                new Column("changeSum"),
                new Column("draftSum"),
                new Column("closeSum"),
                new Column("resolveSum"),
                new Column("assignSum"),
                new Column("bugCount")
        ).from(product
        ).join(
                leftJoin(subStorySelect, new Column(new Table("subStorySelect"), "product_id").eq(new Column(new Table("product"), "product_id"))),
                leftJoin(subBugSelect, new Column(new Table("subBugSelect"), "product_id").eq(new Column(new Table("product"), "product_id")))
        );

    }
}
