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
import org.tinygroup.sdpm.product.dao.ProductPlanDao;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.dao.utils.FieldUtil;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.base.FragmentSql;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.tinygroup.sdpm.product.dao.constant.ProductPlanTable.PRODUCT_PLANTABLE;
import static org.tinygroup.sdpm.product.dao.constant.ProductStoryTable.PRODUCT_STORYTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.select;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.tinysqldsl.select.Join.leftJoin;

@Repository
public class ProductPlanDaoImpl extends TinyDslDaoSupport implements ProductPlanDao {

    public static Condition planQueryCondition(ProductPlan t) {
        return t == null ? null : and(
                PRODUCT_PLANTABLE.COMPANY_ID.eq(t.getCompanyId()),
                PRODUCT_PLANTABLE.PRODUCT_ID.eq(t.getProductId()),
                PRODUCT_PLANTABLE.PLAN_NAME.eq(t.getPlanName()),
                PRODUCT_PLANTABLE.PLAN_SPEC.eq(t.getPlanSpec()),
                PRODUCT_PLANTABLE.PLAN_BEGIN_DATE.eq(t.getPlanBeginDate()),
                PRODUCT_PLANTABLE.PLAN_END_DATE.eq(t.getPlanEndDate()),
                PRODUCT_PLANTABLE.DELETED.eq(t.getDeleted()));
    }

    public ProductPlan add(final ProductPlan productPlan) {
        return getDslTemplate().insertAndReturnKey(productPlan, new InsertGenerateCallback<ProductPlan>() {
            public Insert generate(ProductPlan t) {
                Insert insert = insertInto(PRODUCT_PLANTABLE).values(
                        PRODUCT_PLANTABLE.COMPANY_ID.value(t.getCompanyId()),
                        PRODUCT_PLANTABLE.PRODUCT_ID.value(t.getProductId()),
                        PRODUCT_PLANTABLE.PLAN_NAME.value(t.getPlanName()),
                        PRODUCT_PLANTABLE.PLAN_SPEC.value(t.getPlanSpec()),
                        PRODUCT_PLANTABLE.PLAN_BEGIN_DATE.value(t.getPlanBeginDate()),
                        PRODUCT_PLANTABLE.PLAN_END_DATE.value(t.getPlanEndDate()),
                        PRODUCT_PLANTABLE.NO.value(t.getNo()),
                        PRODUCT_PLANTABLE.DELETED.value(t.getDeleted()));
                return insert;
            }
        });
    }

    public int edit(ProductPlan productPlan) {
        if (productPlan == null || productPlan.getPlanId() == null) {
            return 0;
        }
        return getDslTemplate().update(productPlan, new UpdateGenerateCallback<ProductPlan>() {
            public Update generate(ProductPlan t) {
                return update(PRODUCT_PLANTABLE).set(
                        PRODUCT_PLANTABLE.COMPANY_ID.value(t.getCompanyId()),
                        PRODUCT_PLANTABLE.PRODUCT_ID.value(t.getProductId()),
                        PRODUCT_PLANTABLE.PLAN_NAME.value(t.getPlanName()),
                        PRODUCT_PLANTABLE.PLAN_SPEC.value(t.getPlanSpec()),
                        PRODUCT_PLANTABLE.PLAN_BEGIN_DATE.value(t.getPlanBeginDate()),
                        PRODUCT_PLANTABLE.PLAN_END_DATE.value(t.getPlanEndDate()),
                        PRODUCT_PLANTABLE.NO.value(t.getNo()),
                        PRODUCT_PLANTABLE.DELETED.value(t.getDeleted())).where(PRODUCT_PLANTABLE.PLAN_ID.eq(t.getPlanId()));
            }
        });
    }

    public int[] batchUpdateDel(List<ProductPlan> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(ids, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(PRODUCT_PLANTABLE).set(
                        PRODUCT_PLANTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
                        PRODUCT_PLANTABLE.PLAN_ID.eq(new JdbcNamedParameter("planId")));
            }
        });
    }

    public List<ProductPlan> statisticQuery(ProductPlan productPlan, final boolean isOverdue, final OrderBy... orderArgs) {
        if (productPlan == null) {
            productPlan = new ProductPlan();
        }
        return getDslTemplate().query(productPlan, new SelectGenerateCallback<ProductPlan>() {
            public Select generate(ProductPlan t) {
                Condition condition = null;
                if (isOverdue) {
                    condition = and(PRODUCT_PLANTABLE.PLAN_END_DATE.isNotNull(), PRODUCT_PLANTABLE.PLAN_END_DATE.lte(new Date()));
                }
                Select select = select(PRODUCT_PLANTABLE.PLAN_BEGIN_DATE, PRODUCT_PLANTABLE.PLAN_END_DATE, FragmentSql.fragmentSelect("product_plan.plan_id,product_plan.plan_name,SUM(CASE WHEN product_story.`story_status`=1 THEN 1 ELSE 0 END) draft," +
                        "SUM(CASE WHEN product_story.`story_status`=2 THEN 1 ELSE 0 END) active," +
                        "SUM(CASE WHEN product_story.`story_status`=3 THEN 1 ELSE 0 END) `change`," +
                        "SUM(CASE WHEN product_story.`story_status`=4 THEN 1 ELSE 0 END) `close`"
                )).from(PRODUCT_PLANTABLE).join(leftJoin(PRODUCT_STORYTABLE, PRODUCT_PLANTABLE.PLAN_ID.eq(PRODUCT_STORYTABLE.PLAN_ID))).where(
                        and(
                                condition,
                                PRODUCT_PLANTABLE.COMPANY_ID.eq(t.getCompanyId()),
                                PRODUCT_PLANTABLE.PRODUCT_ID.eq(t.getProductId()),
                                PRODUCT_PLANTABLE.PLAN_NAME.eq(t.getPlanName()),
                                PRODUCT_PLANTABLE.PLAN_SPEC.eq(t.getPlanSpec()),
                                PRODUCT_PLANTABLE.PLAN_BEGIN_DATE.eq(t.getPlanBeginDate()),
                                PRODUCT_PLANTABLE.PLAN_END_DATE.eq(t.getPlanEndDate()),
                                PRODUCT_PLANTABLE.DELETED.eq(t.getDeleted()))).groupBy(PRODUCT_PLANTABLE.PLAN_ID, PRODUCT_PLANTABLE.PLAN_NAME, PRODUCT_PLANTABLE.PLAN_BEGIN_DATE, PRODUCT_PLANTABLE.PLAN_END_DATE);
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public Integer getMaxNo(Integer productId) {
        Select select = select(PRODUCT_PLANTABLE.NO.max()).from(PRODUCT_PLANTABLE).where(PRODUCT_PLANTABLE.PRODUCT_ID.eq(productId));
        return getDslSession().fetchOneResult(select, Integer.class);
    }

    public Integer batchDelete(Integer... ids) {
        UpdateGenerateCallback callback = new UpdateGenerateCallback<Serializable[]>() {

            public Update generate(Serializable[] serializables) {
                return update(PRODUCT_PLANTABLE).set(
                        PRODUCT_PLANTABLE.DELETED.value(FieldUtil.DELETE_YES)).where(
                        PRODUCT_PLANTABLE.PLAN_ID.in(serializables));
            }
        };
        return getDslSession().execute(callback.generate(ids));
    }

    public Integer deletePlanByProduct(Integer productId) {
        Update update = update(PRODUCT_PLANTABLE).set(PRODUCT_PLANTABLE.DELETED.value(1)).where(and(PRODUCT_PLANTABLE.PRODUCT_ID.eq(productId), PRODUCT_PLANTABLE.DELETED.eq(0)));
        return getDslSession().execute(update);
    }

    public int deleteByKey(Integer pk) {
        if (pk == null) {
            return 0;
        }
        return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
            public Delete generate(Serializable pk) {
                return delete(PRODUCT_PLANTABLE).where(PRODUCT_PLANTABLE.PLAN_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(PRODUCT_PLANTABLE).where(PRODUCT_PLANTABLE.PLAN_ID.in(t));
            }
        }, pks);
    }

    public List<ProductPlan> getByKeys(Integer... pk) {

        SelectGenerateCallback<Serializable[]> callback = new SelectGenerateCallback<Serializable[]>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable[] t) {

                return selectFrom(PRODUCT_PLANTABLE).where(PRODUCT_PLANTABLE.PLAN_ID.in(t));
            }

        };
        Select select = callback.generate(pk);
        return getDslSession().fetchList(select, ProductPlan.class);
    }

    public ProductPlan getByKey(Integer pk) {
        try {
            return getDslTemplate().getByKey(pk, ProductPlan.class, new SelectGenerateCallback<Serializable>() {
                @SuppressWarnings("rawtypes")
                public Select generate(Serializable t) {
                    return selectFrom(PRODUCT_PLANTABLE).where(PRODUCT_PLANTABLE.PLAN_ID.eq(t));
                }
            });
        } catch (EmptyResultDataAccessException e) {

            return null;
        }

    }

    public List<ProductPlan> query(ProductPlan productPlan, final OrderBy... orderArgs) {
        if (productPlan == null) {
            productPlan = new ProductPlan();
        }
        return getDslTemplate().query(productPlan, new SelectGenerateCallback<ProductPlan>() {
            @SuppressWarnings("rawtypes")
            public Select generate(ProductPlan t) {

                Select select = selectFrom(PRODUCT_PLANTABLE).where(
                        and(
                                PRODUCT_PLANTABLE.COMPANY_ID.eq(t.getCompanyId()),
                                PRODUCT_PLANTABLE.PRODUCT_ID.eq(t.getProductId()),
                                PRODUCT_PLANTABLE.PLAN_NAME.eq(t.getPlanName()),
                                PRODUCT_PLANTABLE.PLAN_SPEC.eq(t.getPlanSpec()),
                                PRODUCT_PLANTABLE.PLAN_BEGIN_DATE.eq(t.getPlanBeginDate()),
                                PRODUCT_PLANTABLE.PLAN_END_DATE.eq(t.getPlanEndDate()),
                                PRODUCT_PLANTABLE.DELETED.eq(t.getDeleted()),
                                PRODUCT_PLANTABLE.NO.eq(t.getNo())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public Pager<ProductPlan> queryPager(int start, int limit, ProductPlan productPlan, final OrderBy... orderArgs) {
        if (productPlan == null) {
            productPlan = new ProductPlan();
        }
        return getDslTemplate().queryPager(start > 0 ? start : 0, limit, productPlan, false, new SelectGenerateCallback<ProductPlan>() {

            public Select generate(ProductPlan t) {
                Select select = MysqlSelect.selectFrom(PRODUCT_PLANTABLE).where(
                        and(
                                PRODUCT_PLANTABLE.COMPANY_ID.eq(t.getCompanyId()),
                                PRODUCT_PLANTABLE.PRODUCT_ID.eq(t.getProductId()),
                                PRODUCT_PLANTABLE.PLAN_NAME.eq(t.getPlanName()),
                                PRODUCT_PLANTABLE.PLAN_SPEC.eq(t.getPlanSpec()),
                                PRODUCT_PLANTABLE.PLAN_BEGIN_DATE.eq(t.getPlanBeginDate()),
                                PRODUCT_PLANTABLE.PLAN_END_DATE.eq(t.getPlanEndDate()),
                                PRODUCT_PLANTABLE.DELETED.eq(t.getDeleted()))
                );
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<ProductPlan> productPlans) {
        if (CollectionUtil.isEmpty(productPlans)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, productPlans, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(PRODUCT_PLANTABLE).values(
                        PRODUCT_PLANTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
                        PRODUCT_PLANTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
                        PRODUCT_PLANTABLE.PLAN_NAME.value(new JdbcNamedParameter("planName")),
                        PRODUCT_PLANTABLE.PLAN_SPEC.value(new JdbcNamedParameter("planSpec")),
                        PRODUCT_PLANTABLE.PLAN_BEGIN_DATE.value(new JdbcNamedParameter("planBeginDate")),
                        PRODUCT_PLANTABLE.PLAN_END_DATE.value(new JdbcNamedParameter("planEndDate")),
                        PRODUCT_PLANTABLE.DELETED.value(new JdbcNamedParameter("deleted")),
                        PRODUCT_PLANTABLE.NO.value(new JdbcNamedParameter("no")));
            }
        });
    }

    public int[] batchInsert(List<ProductPlan> productPlans) {
        return batchInsert(true, productPlans);
    }

    public int[] batchUpdate(List<ProductPlan> productPlans) {
        if (CollectionUtil.isEmpty(productPlans)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(productPlans, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(PRODUCT_PLANTABLE).set(
                        PRODUCT_PLANTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
                        PRODUCT_PLANTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
                        PRODUCT_PLANTABLE.PLAN_NAME.value(new JdbcNamedParameter("planName")),
                        PRODUCT_PLANTABLE.PLAN_SPEC.value(new JdbcNamedParameter("planSpec")),
                        PRODUCT_PLANTABLE.PLAN_BEGIN_DATE.value(new JdbcNamedParameter("planBeginDate")),
                        PRODUCT_PLANTABLE.PLAN_END_DATE.value(new JdbcNamedParameter("planEndDate")),
                        PRODUCT_PLANTABLE.DELETED.value(new JdbcNamedParameter("deleted")),
                        PRODUCT_PLANTABLE.NO.value(new JdbcNamedParameter("no"))).where(
                        PRODUCT_PLANTABLE.PLAN_ID.eq(new JdbcNamedParameter("planId")));
            }
        });
    }

    public int[] batchDelete(List<ProductPlan> productPlans) {
        if (CollectionUtil.isEmpty(productPlans)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(productPlans, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(PRODUCT_PLANTABLE).where(and(
                        PRODUCT_PLANTABLE.COMPANY_ID.eq(new JdbcNamedParameter("companyId")),
                        PRODUCT_PLANTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
                        PRODUCT_PLANTABLE.PLAN_NAME.eq(new JdbcNamedParameter("planName")),
                        PRODUCT_PLANTABLE.PLAN_SPEC.eq(new JdbcNamedParameter("planSpec")),
                        PRODUCT_PLANTABLE.PLAN_BEGIN_DATE.eq(new JdbcNamedParameter("planBeginDate")),
                        PRODUCT_PLANTABLE.PLAN_END_DATE.eq(new JdbcNamedParameter("planEndDate")),
                        PRODUCT_PLANTABLE.DELETED.eq(new JdbcNamedParameter("deleted")),
                        PRODUCT_PLANTABLE.NO.eq(new JdbcNamedParameter("no"))));
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
                Update update = update(PRODUCT_PLANTABLE).set(
                        PRODUCT_PLANTABLE.DELETED.value(FieldUtil.DELETE_YES)).where(
                        PRODUCT_PLANTABLE.PLAN_ID.eq(id));
                return update;
            }
        });

    }
}
