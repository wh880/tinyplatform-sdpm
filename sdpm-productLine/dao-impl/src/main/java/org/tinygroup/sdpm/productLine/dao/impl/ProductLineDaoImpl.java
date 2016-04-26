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

package org.tinygroup.sdpm.productLine.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.productLine.dao.ProductLineDao;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.tinysqldsl.selectitem.FragmentSelectItemSql;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.productLine.dao.constant.ProductLineTable.PRODUCT_LINETABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.or;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class ProductLineDaoImpl extends TinyDslDaoSupport implements ProductLineDao {

    public ProductLine add(final ProductLine productLine) {
        return getDslTemplate().insertAndReturnKey(productLine, new InsertGenerateCallback<ProductLine>() {
            public Insert generate(ProductLine t) {
                Insert insert = insertInto(PRODUCT_LINETABLE).values(
                        PRODUCT_LINETABLE.COMPANY_ID.value(t.getCompanyId()),
                        PRODUCT_LINETABLE.DEPT_ID.value(t.getDeptId()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_ROOT.value(t.getProductLineRoot()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_PARENT.value(t.getProductLineParent()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_NAME.value(t.getProductLineName()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_CODE.value(t.getProductLineCode()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_ORDER.value(t.getProductLineOrder()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_SPEC.value(t.getProductLineSpec()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_STATUS.value(t.getProductLineStatus()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_OWNER.value(t.getProductLineOwner()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_QUALITY_MANAGER.value(t.getProductLineQualityManager()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_DELIVERY_MANAGER.value(t.getProductLineDeliveryManager()),
                        PRODUCT_LINETABLE.ACL.value(t.getAcl()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_WHITE_LIST.value(t.getProductLineWhiteList()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_CREATED_BY.value(t.getProductLineCreatedBy()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_CREATED_DATE.value(t.getProductLineCreatedDate()),
                        PRODUCT_LINETABLE.DELETED.value(t.getDeleted()));
                return insert;
            }
        });
    }

    public int edit(ProductLine productLine) {
        if (productLine == null || productLine.getProductLineId() == null) {
            return 0;
        }
        return getDslTemplate().update(productLine, new UpdateGenerateCallback<ProductLine>() {
            public Update generate(ProductLine t) {
                Update update = update(PRODUCT_LINETABLE).set(
                        PRODUCT_LINETABLE.COMPANY_ID.value(t.getCompanyId()),
                        PRODUCT_LINETABLE.DEPT_ID.value(t.getDeptId()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_ROOT.value(t.getProductLineRoot()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_PARENT.value(t.getProductLineParent()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_NAME.value(t.getProductLineName()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_CODE.value(t.getProductLineCode()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_ORDER.value(t.getProductLineOrder()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_SPEC.value(t.getProductLineSpec()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_STATUS.value(t.getProductLineStatus()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_OWNER.value(t.getProductLineOwner()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_QUALITY_MANAGER.value(t.getProductLineQualityManager()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_DELIVERY_MANAGER.value(t.getProductLineDeliveryManager()),
                        PRODUCT_LINETABLE.ACL.value(t.getAcl()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_WHITE_LIST.value(t.getProductLineWhiteList()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_CREATED_BY.value(t.getProductLineCreatedBy()),
                        PRODUCT_LINETABLE.PRODUCT_LINE_CREATED_DATE.value(t.getProductLineCreatedDate()),
                        PRODUCT_LINETABLE.DELETED.value(t.getDeleted())).where(PRODUCT_LINETABLE.PRODUCT_LINE_ID.eq(t.getProductLineId()));
                return update;
            }
        });
    }

    public int deleteByKey(Integer pk) {
        if (pk == null) {
            return 0;
        }
        return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
            public Delete generate(Serializable pk) {
                return delete(PRODUCT_LINETABLE).where(PRODUCT_LINETABLE.PRODUCT_LINE_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(PRODUCT_LINETABLE).where(PRODUCT_LINETABLE.PRODUCT_LINE_ID.in(t));
            }
        }, pks);
    }

    public ProductLine getByKey(Integer pk) {
        try {
            return getDslTemplate().getByKey(pk, ProductLine.class, new SelectGenerateCallback<Serializable>() {
                @SuppressWarnings("rawtypes")
                public Select generate(Serializable t) {
                    return selectFrom(PRODUCT_LINETABLE).where(PRODUCT_LINETABLE.PRODUCT_LINE_ID.eq(t));
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return new ProductLine();
        }

    }

    public List<ProductLine> query(ProductLine productLine, final OrderBy... orderArgs) {
        if (productLine == null) {
            productLine = new ProductLine();
        }
        return getDslTemplate().query(productLine, new SelectGenerateCallback<ProductLine>() {

            @SuppressWarnings("rawtypes")
            public Select generate(ProductLine t) {
                Select select = selectFrom(PRODUCT_LINETABLE).where(
                        and(
                                PRODUCT_LINETABLE.COMPANY_ID.eq(t.getCompanyId()),
                                PRODUCT_LINETABLE.DEPT_ID.eq(t.getDeptId()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_ROOT.eq(t.getProductLineRoot()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_PARENT.eq(t.getProductLineParent()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_NAME.eq(t.getProductLineName()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_CODE.eq(t.getProductLineCode()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_ORDER.eq(t.getProductLineOrder()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_SPEC.eq(t.getProductLineSpec()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_STATUS.eq(t.getProductLineStatus()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_OWNER.eq(t.getProductLineOwner()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_QUALITY_MANAGER.eq(t.getProductLineQualityManager()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_DELIVERY_MANAGER.eq(t.getProductLineDeliveryManager()),
                                PRODUCT_LINETABLE.ACL.eq(t.getAcl()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_WHITE_LIST.eq(t.getProductLineWhiteList()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_CREATED_BY.eq(t.getProductLineCreatedBy()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_CREATED_DATE.eq(t.getProductLineCreatedDate()),
                                PRODUCT_LINETABLE.DELETED.eq(t.getDeleted())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public Pager<ProductLine> queryPager(int start, int limit, ProductLine productLine, final OrderBy... orderArgs) {
        if (productLine == null) {
            productLine = new ProductLine();
        }
        return getDslTemplate().queryPager(start, limit, productLine, false, new SelectGenerateCallback<ProductLine>() {

            public Select generate(ProductLine t) {
                Select select = MysqlSelect.selectFrom(PRODUCT_LINETABLE).where(
                        and(
                                PRODUCT_LINETABLE.COMPANY_ID.eq(t.getCompanyId()),
                                PRODUCT_LINETABLE.DEPT_ID.eq(t.getDeptId()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_ROOT.eq(t.getProductLineRoot()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_PARENT.eq(t.getProductLineParent()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_NAME.eq(t.getProductLineName()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_CODE.eq(t.getProductLineCode()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_ORDER.eq(t.getProductLineOrder()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_SPEC.eq(t.getProductLineSpec()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_STATUS.eq(t.getProductLineStatus()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_OWNER.eq(t.getProductLineOwner()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_QUALITY_MANAGER.eq(t.getProductLineQualityManager()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_DELIVERY_MANAGER.eq(t.getProductLineDeliveryManager()),
                                PRODUCT_LINETABLE.ACL.eq(t.getAcl()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_WHITE_LIST.eq(t.getProductLineWhiteList()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_CREATED_BY.eq(t.getProductLineCreatedBy()),
                                PRODUCT_LINETABLE.PRODUCT_LINE_CREATED_DATE.eq(t.getProductLineCreatedDate()),
                                PRODUCT_LINETABLE.DELETED.eq(t.getDeleted())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public Pager<ProductLine> findList(int start, int limit, final Condition condition, ProductLine productLine, final OrderBy... orderArgs) {
        if (productLine == null) {
            productLine = new ProductLine();
        }

        return getDslTemplate().queryPager(start, limit, productLine, false, new SelectGenerateCallback<ProductLine>() {

            public Select generate(ProductLine t) {
                Select select = MysqlSelect.select(FragmentSelectItemSql.fragmentSelect("product_line.*,u1.org_user_account as ownerName,u2.org_user_account as qualityManagerName,u3.org_user_account as qualityManagerName"))
                        .from(FragmentSelectItemSql.fragmentFrom("product_line left join org_user u1 on u1.org_user_id = product_line.product_line_owner left join  org_user u2 on u2.org_user_id = product_line.product_line_quality_manager left join  org_user u3 on u3.org_user_id = product_line.product_line_delivery_manager"))
                        .where(
                                and(
                                        condition,
                                        PRODUCT_LINETABLE.COMPANY_ID.eq(t.getCompanyId()),
                                        PRODUCT_LINETABLE.DEPT_ID.eq(t.getDeptId()),
                                        PRODUCT_LINETABLE.PRODUCT_LINE_ROOT.eq(t.getProductLineRoot()),
                                        PRODUCT_LINETABLE.PRODUCT_LINE_PARENT.eq(t.getProductLineParent()),
                                        PRODUCT_LINETABLE.PRODUCT_LINE_NAME.eq(t.getProductLineName()),
                                        PRODUCT_LINETABLE.PRODUCT_LINE_CODE.eq(t.getProductLineCode()),
                                        PRODUCT_LINETABLE.PRODUCT_LINE_ORDER.eq(t.getProductLineOrder()),
                                        PRODUCT_LINETABLE.PRODUCT_LINE_SPEC.eq(t.getProductLineSpec()),
                                        PRODUCT_LINETABLE.PRODUCT_LINE_STATUS.eq(t.getProductLineStatus()),
                                        PRODUCT_LINETABLE.PRODUCT_LINE_OWNER.eq(t.getProductLineOwner()),
                                        PRODUCT_LINETABLE.PRODUCT_LINE_QUALITY_MANAGER.eq(t.getProductLineQualityManager()),
                                        PRODUCT_LINETABLE.PRODUCT_LINE_DELIVERY_MANAGER.eq(t.getProductLineDeliveryManager()),
                                        PRODUCT_LINETABLE.ACL.eq(t.getAcl()),
                                        PRODUCT_LINETABLE.PRODUCT_LINE_WHITE_LIST.eq(t.getProductLineWhiteList()),
                                        PRODUCT_LINETABLE.PRODUCT_LINE_CREATED_BY.eq(t.getProductLineCreatedBy()),
                                        PRODUCT_LINETABLE.PRODUCT_LINE_CREATED_DATE.eq(t.getProductLineCreatedDate()),
                                        PRODUCT_LINETABLE.DELETED.eq(t.getDeleted())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public Pager<ProductLine> findList(int start, int limit, Condition condition, ProductLine productLine, Integer[] ids, OrderBy... orderArgs) {
        return findList(start, limit, and(condition, PRODUCT_LINETABLE.PRODUCT_LINE_ID.in(ids)), productLine, orderArgs);
    }

    public List<ProductLine> getByKeys(Integer... ids) {
        SelectGenerateCallback<Serializable[]> callback = new SelectGenerateCallback<Serializable[]>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable[] t) {
                return selectFrom(PRODUCT_LINETABLE).where(and(PRODUCT_LINETABLE.DELETED.eq(0), PRODUCT_LINETABLE.PRODUCT_LINE_ID.in(t)));
            }

        };
        Select select = callback.generate(ids);
        return getDslSession().fetchList(select, ProductLine.class);
    }

    public List<ProductLine> getUserProductLines(String userId) {
        Select select = selectFrom(PRODUCT_LINETABLE).where(
                and(PRODUCT_LINETABLE.DELETED.eq(0),
                        or(PRODUCT_LINETABLE.ACL.eq(ProductLine.ACl_All),
                                or(PRODUCT_LINETABLE.PRODUCT_LINE_CREATED_BY.eq(userId), PRODUCT_LINETABLE.PRODUCT_LINE_OWNER.eq(userId), PRODUCT_LINETABLE.PRODUCT_LINE_DELIVERY_MANAGER.eq(userId), PRODUCT_LINETABLE.PRODUCT_LINE_QUALITY_MANAGER.eq(userId)))));
        return getDslSession().fetchList(select, ProductLine.class);
    }

    public List<ProductLine> lineInCondition(String condition, Integer limit, Integer... ids) {
        Select select = MysqlSelect.select(PRODUCT_LINETABLE.PRODUCT_LINE_ID, PRODUCT_LINETABLE.PRODUCT_LINE_NAME).from(PRODUCT_LINETABLE).where(and(PRODUCT_LINETABLE.DELETED.eq(0), PRODUCT_LINETABLE.PRODUCT_LINE_NAME.like(condition), PRODUCT_LINETABLE.PRODUCT_LINE_ID.in(ids))).limit(0, limit);
        return getDslSession().fetchList(select, ProductLine.class);
    }


    public int[] batchInsert(boolean autoGeneratedKeys, List<ProductLine> productLines) {
        if (CollectionUtil.isEmpty(productLines)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, productLines, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(PRODUCT_LINETABLE).values(
                        PRODUCT_LINETABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
                        PRODUCT_LINETABLE.DEPT_ID.value(new JdbcNamedParameter("deptId")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_ROOT.value(new JdbcNamedParameter("productLineRoot")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_PARENT.value(new JdbcNamedParameter("productLineParent")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_NAME.value(new JdbcNamedParameter("productLineName")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_CODE.value(new JdbcNamedParameter("productLineCode")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_ORDER.value(new JdbcNamedParameter("productLineOrder")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_SPEC.value(new JdbcNamedParameter("productLineSpec")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_STATUS.value(new JdbcNamedParameter("productLineStatus")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_OWNER.value(new JdbcNamedParameter("productLineOwner")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_QUALITY_MANAGER.value(new JdbcNamedParameter("productLineQualityManager")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_DELIVERY_MANAGER.value(new JdbcNamedParameter("productLineDeliveryManager")),
                        PRODUCT_LINETABLE.ACL.value(new JdbcNamedParameter("acl")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_WHITE_LIST.value(new JdbcNamedParameter("productLineWhiteList")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_CREATED_BY.value(new JdbcNamedParameter("productLineCreatedBy")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_CREATED_DATE.value(new JdbcNamedParameter("productLineCreatedDate")),
                        PRODUCT_LINETABLE.DELETED.value(new JdbcNamedParameter("deleted")));
            }
        });
    }

    public int[] batchInsert(List<ProductLine> productLines) {
        return batchInsert(true, productLines);
    }

    public int[] batchUpdate(List<ProductLine> productLines) {
        if (CollectionUtil.isEmpty(productLines)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(productLines, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(PRODUCT_LINETABLE).set(
                        PRODUCT_LINETABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
                        PRODUCT_LINETABLE.DEPT_ID.value(new JdbcNamedParameter("deptId")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_ROOT.value(new JdbcNamedParameter("productLineRoot")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_PARENT.value(new JdbcNamedParameter("productLineParent")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_NAME.value(new JdbcNamedParameter("productLineName")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_CODE.value(new JdbcNamedParameter("productLineCode")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_ORDER.value(new JdbcNamedParameter("productLineOrder")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_SPEC.value(new JdbcNamedParameter("productLineSpec")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_STATUS.value(new JdbcNamedParameter("productLineStatus")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_OWNER.value(new JdbcNamedParameter("productLineOwner")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_QUALITY_MANAGER.value(new JdbcNamedParameter("productLineQualityManager")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_DELIVERY_MANAGER.value(new JdbcNamedParameter("productLineDeliveryManager")),
                        PRODUCT_LINETABLE.ACL.value(new JdbcNamedParameter("acl")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_WHITE_LIST.value(new JdbcNamedParameter("productLineWhiteList")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_CREATED_BY.value(new JdbcNamedParameter("productLineCreatedBy")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_CREATED_DATE.value(new JdbcNamedParameter("productLineCreatedDate")),
                        PRODUCT_LINETABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
                        PRODUCT_LINETABLE.PRODUCT_LINE_ID.eq(new JdbcNamedParameter("productLineId")));
            }
        });
    }

    public int[] batchDelete(List<ProductLine> productLines) {
        if (CollectionUtil.isEmpty(productLines)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(productLines, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(PRODUCT_LINETABLE).where(and(
                        PRODUCT_LINETABLE.PRODUCT_LINE_ID.eq(new JdbcNamedParameter("productLineId")),
                        PRODUCT_LINETABLE.COMPANY_ID.eq(new JdbcNamedParameter("companyId")),
                        PRODUCT_LINETABLE.DEPT_ID.eq(new JdbcNamedParameter("deptId")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_ROOT.eq(new JdbcNamedParameter("productLineRoot")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_PARENT.eq(new JdbcNamedParameter("productLineParent")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_NAME.eq(new JdbcNamedParameter("productLineName")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_CODE.eq(new JdbcNamedParameter("productLineCode")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_ORDER.eq(new JdbcNamedParameter("productLineOrder")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_SPEC.eq(new JdbcNamedParameter("productLineSpec")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_STATUS.eq(new JdbcNamedParameter("productLineStatus")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_OWNER.eq(new JdbcNamedParameter("productLineOwner")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_QUALITY_MANAGER.eq(new JdbcNamedParameter("productLineQualityManager")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_DELIVERY_MANAGER.eq(new JdbcNamedParameter("productLineDeliveryManager")),
                        PRODUCT_LINETABLE.ACL.eq(new JdbcNamedParameter("acl")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_WHITE_LIST.eq(new JdbcNamedParameter("productLineWhiteList")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_CREATED_BY.eq(new JdbcNamedParameter("productLineCreatedBy")),
                        PRODUCT_LINETABLE.PRODUCT_LINE_CREATED_DATE.eq(new JdbcNamedParameter("productLineCreatedDate")),
                        PRODUCT_LINETABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
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
                Update update = update(PRODUCT_LINETABLE).set(
                        PRODUCT_LINETABLE.DELETED.value(FieldUtil.DELETE_YES), PRODUCT_LINETABLE.PRODUCT_LINE_STATUS.value(ProductLine.STATUS_DELETED)).where(
                        PRODUCT_LINETABLE.PRODUCT_LINE_ID.eq(id));
                return update;
            }
        });

    }


}
