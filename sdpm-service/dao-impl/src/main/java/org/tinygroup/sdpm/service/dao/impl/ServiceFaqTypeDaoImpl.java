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

package org.tinygroup.sdpm.service.dao.impl;

import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.service.dao.ServiceFaqTypeDao;
import org.tinygroup.sdpm.service.dao.pojo.ServiceFaqType;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.service.dao.constant.ServiceFaqTypeTable.SERVICE_FAQ_TYPETABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class ServiceFaqTypeDaoImpl extends TinyDslDaoSupport implements ServiceFaqTypeDao {

    public ServiceFaqType add(ServiceFaqType serviceFaqType) {
        return getDslTemplate().insertAndReturnKey(serviceFaqType, new InsertGenerateCallback<ServiceFaqType>() {
            public Insert generate(ServiceFaqType t) {
                Insert insert = insertInto(SERVICE_FAQ_TYPETABLE).values(
                        SERVICE_FAQ_TYPETABLE.FAQ_TYPE_ID.value(t.getFaqTypeId()),
                        SERVICE_FAQ_TYPETABLE.FAQ_TYPE.value(t.getFaqType()),
                        SERVICE_FAQ_TYPETABLE.FAQ_PARENT_TYPE_ID.value(t.getFaqParentTypeId()),
                        SERVICE_FAQ_TYPETABLE.FAQ_TYPE_CREATDAY.value(t.getFaqTypeCreatDay()),
                        SERVICE_FAQ_TYPETABLE.FAQ_CREATED_BY.value(t.getFaqCreatedBy()),
                        SERVICE_FAQ_TYPETABLE.DELETED.value(t.getDeleted()));
                return insert;
            }
        });
    }

    public int edit(ServiceFaqType serviceFaqType) {
        if (serviceFaqType == null || serviceFaqType.getFaqTypeId() == null) {
            return 0;
        }
        return getDslTemplate().update(serviceFaqType, new UpdateGenerateCallback<ServiceFaqType>() {
            public Update generate(ServiceFaqType t) {
                Update update = update(SERVICE_FAQ_TYPETABLE).set(
                        SERVICE_FAQ_TYPETABLE.FAQ_TYPE.value(t.getFaqType()),
                        SERVICE_FAQ_TYPETABLE.FAQ_PARENT_TYPE_ID.value(t.getFaqParentTypeId()),
                        SERVICE_FAQ_TYPETABLE.FAQ_TYPE_CREATDAY.value(t.getFaqTypeCreatDay()),
                        SERVICE_FAQ_TYPETABLE.FAQ_CREATED_BY.value(t.getFaqCreatedBy()),
                        SERVICE_FAQ_TYPETABLE.DELETED.value(t.getDeleted())).where(
                        SERVICE_FAQ_TYPETABLE.FAQ_TYPE_ID.eq(t.getFaqTypeId()));
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
                return delete(SERVICE_FAQ_TYPETABLE).where(SERVICE_FAQ_TYPETABLE.FAQ_TYPE_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(SERVICE_FAQ_TYPETABLE).where(SERVICE_FAQ_TYPETABLE.FAQ_TYPE_ID.in(t));
            }
        }, pks);
    }

    public ServiceFaqType getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, ServiceFaqType.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(SERVICE_FAQ_TYPETABLE).where(SERVICE_FAQ_TYPETABLE.FAQ_TYPE_ID.eq(t));
            }
        });
    }

    public List<ServiceFaqType> query(ServiceFaqType serviceFaqType, final OrderBy... orderBies) {
        if (serviceFaqType == null) {
            serviceFaqType = new ServiceFaqType();
        }
        return getDslTemplate().query(serviceFaqType, new SelectGenerateCallback<ServiceFaqType>() {

            @SuppressWarnings("rawtypes")
            public Select generate(ServiceFaqType t) {
                Select select = selectFrom(SERVICE_FAQ_TYPETABLE).where(
                        and(
                                SERVICE_FAQ_TYPETABLE.FAQ_TYPE.eq(t.getFaqType()),
                                SERVICE_FAQ_TYPETABLE.FAQ_PARENT_TYPE_ID.eq(t.getFaqParentTypeId()),
                                SERVICE_FAQ_TYPETABLE.FAQ_TYPE_CREATDAY.eq(t.getFaqTypeCreatDay()),
                                SERVICE_FAQ_TYPETABLE.FAQ_CREATED_BY.eq(t.getFaqCreatedBy()),
                                SERVICE_FAQ_TYPETABLE.DELETED.eq(t.getDeleted())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public Pager<ServiceFaqType> queryPager(int start, int limit, ServiceFaqType serviceFaqType, final OrderBy... orderBies) {
        if (serviceFaqType == null) {
            serviceFaqType = new ServiceFaqType();
        }
        return getDslTemplate().queryPager(start, limit, serviceFaqType, false, new SelectGenerateCallback<ServiceFaqType>() {

            public Select generate(ServiceFaqType t) {
                Select select = MysqlSelect.selectFrom(SERVICE_FAQ_TYPETABLE).where(
                        and(
                                SERVICE_FAQ_TYPETABLE.FAQ_TYPE.eq(t.getFaqType()),
                                SERVICE_FAQ_TYPETABLE.FAQ_PARENT_TYPE_ID.eq(t.getFaqParentTypeId()),
                                SERVICE_FAQ_TYPETABLE.FAQ_TYPE_CREATDAY.eq(t.getFaqTypeCreatDay()),
                                SERVICE_FAQ_TYPETABLE.FAQ_CREATED_BY.eq(t.getFaqCreatedBy()),
                                SERVICE_FAQ_TYPETABLE.DELETED.eq(t.getDeleted())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<ServiceFaqType> serviceFaqTypes) {
        if (CollectionUtil.isEmpty(serviceFaqTypes)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, serviceFaqTypes, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(SERVICE_FAQ_TYPETABLE).values(
                        SERVICE_FAQ_TYPETABLE.FAQ_TYPE.value(new JdbcNamedParameter("faqType")),
                        SERVICE_FAQ_TYPETABLE.FAQ_PARENT_TYPE_ID.value(new JdbcNamedParameter("faqParentTypeId")),
                        SERVICE_FAQ_TYPETABLE.FAQ_TYPE_CREATDAY.value(new JdbcNamedParameter("faqTypeCreatDay")),
                        SERVICE_FAQ_TYPETABLE.FAQ_CREATED_BY.value(new JdbcNamedParameter("faqCreatedBy")),
                        SERVICE_FAQ_TYPETABLE.DELETED.value(new JdbcNamedParameter("deleted")));
            }
        });
    }

    public int[] batchInsert(List<ServiceFaqType> serviceFaqTypes) {
        return batchInsert(true, serviceFaqTypes);
    }

    public int[] batchUpdate(List<ServiceFaqType> serviceFaqTypes) {
        if (CollectionUtil.isEmpty(serviceFaqTypes)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(serviceFaqTypes, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(SERVICE_FAQ_TYPETABLE).set(
                        SERVICE_FAQ_TYPETABLE.FAQ_TYPE.value(new JdbcNamedParameter("faqType")),
                        SERVICE_FAQ_TYPETABLE.FAQ_PARENT_TYPE_ID.value(new JdbcNamedParameter("faqParentTypeId")),
                        SERVICE_FAQ_TYPETABLE.FAQ_TYPE_CREATDAY.value(new JdbcNamedParameter("faqTypeCreatDay")),
                        SERVICE_FAQ_TYPETABLE.FAQ_CREATED_BY.value(new JdbcNamedParameter("faqCreatedBy")),
                        SERVICE_FAQ_TYPETABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
                        SERVICE_FAQ_TYPETABLE.FAQ_TYPE_ID.eq(new JdbcNamedParameter("faqTypeId")));
            }
        });
    }

    public int[] batchDelete(List<ServiceFaqType> serviceFaqTypes) {
        if (CollectionUtil.isEmpty(serviceFaqTypes)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(serviceFaqTypes, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(SERVICE_FAQ_TYPETABLE).where(and(
                        SERVICE_FAQ_TYPETABLE.FAQ_TYPE_ID.eq(new JdbcNamedParameter("faqTypeId")),
                        SERVICE_FAQ_TYPETABLE.FAQ_TYPE.eq(new JdbcNamedParameter("faqType")),
                        SERVICE_FAQ_TYPETABLE.FAQ_PARENT_TYPE_ID.eq(new JdbcNamedParameter("faqParentTypeId")),
                        SERVICE_FAQ_TYPETABLE.FAQ_TYPE_CREATDAY.eq(new JdbcNamedParameter("faqTypeCreatDay")),
                        SERVICE_FAQ_TYPETABLE.FAQ_CREATED_BY.eq(new JdbcNamedParameter("faqCreatedBy")),
                        SERVICE_FAQ_TYPETABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
            }
        });
    }

    private Select addOrderByElements(Select select, OrderBy... orderBies) {
        List<OrderByElement> orderByElements = new ArrayList<OrderByElement>();
        for (int i = 0; orderBies != null && i < orderBies.length; i++) {
            OrderByElement tempElement = orderBies[i].getOrderByElement();
            if (tempElement != null) {
                orderByElements.add(tempElement);
            }
        }
        if (orderByElements.size() > 0) {
            select.orderBy(orderByElements.toArray(new OrderByElement[0]));
        }
        return select;
    }
}
