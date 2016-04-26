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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.product.dao.ProductStorySpecDao;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.product.dao.constant.ProductStorySpecTable.PRODUCT_STORY_SPECTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.select;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class ProductStorySpecDaoImpl extends TinyDslDaoSupport implements ProductStorySpecDao {

    public static Condition productStorySpecPueryCondition(ProductStorySpec t) {
        return t == null ? null : and(
                PRODUCT_STORY_SPECTABLE.COMPANY_ID.eq(t.getCompanyId()),
                PRODUCT_STORY_SPECTABLE.STORY_ID.eq(t.getStoryId()),
                PRODUCT_STORY_SPECTABLE.STORY_VERSION.eq(t.getStoryVersion()),
                PRODUCT_STORY_SPECTABLE.STORY_TITLE.eq(t.getStoryTitle()),
                PRODUCT_STORY_SPECTABLE.STORY_SPEC.eq(t.getStorySpec()),
                PRODUCT_STORY_SPECTABLE.STORY_VERIFICATION.eq(t.getStoryVerification()));
    }

    public ProductStorySpec add(final ProductStorySpec productStorySpec) {
        return getDslTemplate().insertAndReturnKey(productStorySpec, new InsertGenerateCallback<ProductStorySpec>() {
            public Insert generate(ProductStorySpec t) {
                Insert insert = insertInto(PRODUCT_STORY_SPECTABLE).values(
                        PRODUCT_STORY_SPECTABLE.COMPANY_ID.value(t.getCompanyId()),
                        PRODUCT_STORY_SPECTABLE.STORY_ID.value(t.getStoryId()),
                        PRODUCT_STORY_SPECTABLE.STORY_VERSION.value(t.getStoryVersion()),
                        PRODUCT_STORY_SPECTABLE.STORY_TITLE.value(t.getStoryTitle()),
                        PRODUCT_STORY_SPECTABLE.STORY_SPEC.value(t.getStorySpec()),
                        PRODUCT_STORY_SPECTABLE.STORY_VERIFICATION.value(t.getStoryVerification()));
                return insert;
            }
        });
    }

    public int edit(ProductStorySpec productStorySpec) {
        if (productStorySpec == null || productStorySpec.getStoryspecId() == null) {
            return 0;
        }
        return getDslTemplate().update(productStorySpec, new UpdateGenerateCallback<ProductStorySpec>() {
            public Update generate(ProductStorySpec t) {
                Update update = update(PRODUCT_STORY_SPECTABLE).set(
                        PRODUCT_STORY_SPECTABLE.COMPANY_ID.value(t.getCompanyId()),
                        PRODUCT_STORY_SPECTABLE.STORY_ID.value(t.getStoryId()),
                        PRODUCT_STORY_SPECTABLE.STORY_VERSION.value(t.getStoryVersion()),
                        PRODUCT_STORY_SPECTABLE.STORY_TITLE.value(t.getStoryTitle()),
                        PRODUCT_STORY_SPECTABLE.STORY_SPEC.value(t.getStorySpec()),
                        PRODUCT_STORY_SPECTABLE.STORY_VERIFICATION.value(t.getStoryVerification())).where(PRODUCT_STORY_SPECTABLE.STORYSPEC_ID.eq(t.getStoryspecId()));
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
                return delete(PRODUCT_STORY_SPECTABLE).where(PRODUCT_STORY_SPECTABLE.STORYSPEC_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(PRODUCT_STORY_SPECTABLE).where(PRODUCT_STORY_SPECTABLE.STORYSPEC_ID.in(t));
            }
        }, pks);
    }

    public List<ProductStorySpec> getByKeys(Integer... pk) {

        SelectGenerateCallback<Serializable[]> callback = new SelectGenerateCallback<Serializable[]>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable[] t) {

                return selectFrom(PRODUCT_STORY_SPECTABLE).where(PRODUCT_STORY_SPECTABLE.STORYSPEC_ID.in(t));
            }

        };
        Select select = callback.generate(pk);
        return getDslSession().fetchList(select, ProductStorySpec.class);
    }

    public ProductStorySpec getByKey(Integer pk) {

        try {
            return getDslTemplate().getByKey(pk, ProductStorySpec.class, new SelectGenerateCallback<Serializable>() {
                @SuppressWarnings("rawtypes")
                public Select generate(Serializable t) {
                    return selectFrom(PRODUCT_STORY_SPECTABLE).where(PRODUCT_STORY_SPECTABLE.STORY_ID.eq(t));
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public List<ProductStorySpec> query(ProductStorySpec productStorySpec, final OrderBy... orderArgs) {
        if (productStorySpec == null) {
            productStorySpec = new ProductStorySpec();
        }
        return getDslTemplate().query(productStorySpec, new SelectGenerateCallback<ProductStorySpec>() {

            @SuppressWarnings("rawtypes")
            public Select generate(ProductStorySpec t) {
                Select select = selectFrom(PRODUCT_STORY_SPECTABLE).where(
                        and(
                                PRODUCT_STORY_SPECTABLE.COMPANY_ID.eq(t.getCompanyId()),
                                PRODUCT_STORY_SPECTABLE.STORY_ID.eq(t.getStoryId()),
                                PRODUCT_STORY_SPECTABLE.STORY_VERSION.eq(t.getStoryVersion()),
                                PRODUCT_STORY_SPECTABLE.STORY_TITLE.eq(t.getStoryTitle()),
                                PRODUCT_STORY_SPECTABLE.STORY_SPEC.eq(t.getStorySpec()),
                                PRODUCT_STORY_SPECTABLE.STORY_VERIFICATION.eq(t.getStoryVerification())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public Pager<ProductStorySpec> queryPager(int start, int limit, ProductStorySpec productStorySpec, final OrderBy... orderArgs) {
        if (productStorySpec == null) {
            productStorySpec = new ProductStorySpec();
        }
        return getDslTemplate().queryPager(start > 0 ? start : 0, limit, productStorySpec, false, new SelectGenerateCallback<ProductStorySpec>() {

            public Select generate(ProductStorySpec t) {
                Select select = MysqlSelect.selectFrom(PRODUCT_STORY_SPECTABLE).where(
                        and(
                                PRODUCT_STORY_SPECTABLE.COMPANY_ID.eq(t.getCompanyId()),
                                PRODUCT_STORY_SPECTABLE.STORY_ID.eq(t.getStoryId()),
                                PRODUCT_STORY_SPECTABLE.STORY_VERSION.eq(t.getStoryVersion()),
                                PRODUCT_STORY_SPECTABLE.STORY_TITLE.eq(t.getStoryTitle()),
                                PRODUCT_STORY_SPECTABLE.STORY_SPEC.eq(t.getStorySpec()),
                                PRODUCT_STORY_SPECTABLE.STORY_VERIFICATION.eq(t.getStoryVerification())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<ProductStorySpec> productStorySpecs) {
        if (CollectionUtil.isEmpty(productStorySpecs)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, productStorySpecs, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(PRODUCT_STORY_SPECTABLE).values(
                        PRODUCT_STORY_SPECTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
                        PRODUCT_STORY_SPECTABLE.STORY_ID.value(new JdbcNamedParameter("storyId")),
                        PRODUCT_STORY_SPECTABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
                        PRODUCT_STORY_SPECTABLE.STORY_TITLE.value(new JdbcNamedParameter("storyTitle")),
                        PRODUCT_STORY_SPECTABLE.STORY_SPEC.value(new JdbcNamedParameter("storySpec")),
                        PRODUCT_STORY_SPECTABLE.STORY_VERIFICATION.value(new JdbcNamedParameter("storyVerification")));
            }
        });
    }

    public int[] batchInsert(List<ProductStorySpec> productStorySpecs) {
        return batchInsert(true, productStorySpecs);
    }

    public int[] batchUpdate(List<ProductStorySpec> productStorySpecs) {
        if (CollectionUtil.isEmpty(productStorySpecs)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(productStorySpecs, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(PRODUCT_STORY_SPECTABLE).set(
                        PRODUCT_STORY_SPECTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
                        PRODUCT_STORY_SPECTABLE.STORY_ID.value(new JdbcNamedParameter("storyId")),
                        PRODUCT_STORY_SPECTABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
                        PRODUCT_STORY_SPECTABLE.STORY_TITLE.value(new JdbcNamedParameter("storyTitle")),
                        PRODUCT_STORY_SPECTABLE.STORY_SPEC.value(new JdbcNamedParameter("storySpec")),
                        PRODUCT_STORY_SPECTABLE.STORY_VERIFICATION.value(new JdbcNamedParameter("storyVerification"))).where(
                        PRODUCT_STORY_SPECTABLE.STORYSPEC_ID.eq(new JdbcNamedParameter("storyspecId")));
            }
        });
    }

    public int[] batchDelete(List<ProductStorySpec> productStorySpecs) {
        if (CollectionUtil.isEmpty(productStorySpecs)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(productStorySpecs, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(PRODUCT_STORY_SPECTABLE).where(and(
                        PRODUCT_STORY_SPECTABLE.STORYSPEC_ID.eq(new JdbcNamedParameter("storyspecId")),
                        PRODUCT_STORY_SPECTABLE.COMPANY_ID.eq(new JdbcNamedParameter("companyId")),
                        PRODUCT_STORY_SPECTABLE.STORY_ID.eq(new JdbcNamedParameter("storyId")),
                        PRODUCT_STORY_SPECTABLE.STORY_VERSION.eq(new JdbcNamedParameter("storyVersion")),
                        PRODUCT_STORY_SPECTABLE.STORY_TITLE.eq(new JdbcNamedParameter("storyTitle")),
                        PRODUCT_STORY_SPECTABLE.STORY_SPEC.eq(new JdbcNamedParameter("storySpec")),
                        PRODUCT_STORY_SPECTABLE.STORY_VERIFICATION.eq(new JdbcNamedParameter("storyVerification"))));
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

    public Integer getNewStoryVersion(Integer storyId) {
        ProductStorySpec spec = new ProductStorySpec();
        spec.setStoryId(storyId);
        Pager<ProductStorySpec> pager = queryPager(0, 1, spec, new OrderBy("story_version", false));
        if (pager != null) {
            if (pager.getRecords().size() > 0 || pager.getRecords() != null) {
                return pager.getRecords().get(0).getStoryVersion();
            }
        }
        return -1;
    }

    public Integer getMaxVersion(Integer storyId) {
        Select select = select(PRODUCT_STORY_SPECTABLE.STORY_VERSION.max()).from(PRODUCT_STORY_SPECTABLE).where(PRODUCT_STORY_SPECTABLE.STORY_ID.eq(storyId));
        return getDslSession().fetchOneResult(select, Integer.class);
    }
}
