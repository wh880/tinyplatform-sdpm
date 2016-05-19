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
import org.tinygroup.sdpm.service.dao.ServiceFaqDao;
import org.tinygroup.sdpm.service.dao.pojo.ServiceFaq;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.service.dao.constant.ServiceFaqTable.SERVICE_FAQTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class ServiceFaqDaoImpl extends TinyDslDaoSupport implements ServiceFaqDao {
    public static final Integer DELETE_YES = 1;
    public static final Integer DELETE_NO = 0;

    public ServiceFaq add(ServiceFaq serviceFaq) {
        return getDslTemplate().insertAndReturnKey(serviceFaq, new InsertGenerateCallback<ServiceFaq>() {
            public Insert generate(ServiceFaq t) {
                Insert insert = insertInto(SERVICE_FAQTABLE).values(
                        SERVICE_FAQTABLE.FAQ_ID.value(t.getFaqId()),
                        SERVICE_FAQTABLE.FAQ_QUESTION.value(t.getFaqQuestion()),
                        SERVICE_FAQTABLE.FAQ_ANSWER.value(t.getFaqAnswer()),
                        SERVICE_FAQTABLE.DELETED.value(t.getDeleted()),
                        SERVICE_FAQTABLE.FAQ_CREATE_DATE.value(t.getFaqCreateDate()),
                        SERVICE_FAQTABLE.FAQ_CREATED_BY.value(t.getFaqCreatedBy()),
                        SERVICE_FAQTABLE.PRODUCT_ID.value(t.getProductId()),
                        SERVICE_FAQTABLE.FAQ_KEYWORDS.value(t.getFaqKeywords()),
                        SERVICE_FAQTABLE.FAQ_SOURCE_ID.value(t.getFaqSourceId()),
                        SERVICE_FAQTABLE.FAQ_SOURCE.value(t.getFaqSource()),
                        SERVICE_FAQTABLE.FAQ_REPLIED_BY.value(t.getFaqRepliedBy()),
                        SERVICE_FAQTABLE.FAQ_REPLY_DATE.value(t.getFaqReplyDate()),
                        SERVICE_FAQTABLE.FAQ_TYPE_ID.value(t.getFaqTypeId()));
                return insert;
            }
        });
    }

    public int edit(ServiceFaq serviceFaq) {
        if (serviceFaq == null || serviceFaq.getFaqId() == null) {
            return 0;
        }
        return getDslTemplate().update(serviceFaq, new UpdateGenerateCallback<ServiceFaq>() {
            public Update generate(ServiceFaq t) {
                Update update = update(SERVICE_FAQTABLE).set(
                        SERVICE_FAQTABLE.FAQ_QUESTION.value(t.getFaqQuestion()),
                        SERVICE_FAQTABLE.FAQ_ANSWER.value(t.getFaqAnswer()),
                        SERVICE_FAQTABLE.DELETED.value(t.getDeleted()),
                        SERVICE_FAQTABLE.FAQ_CREATE_DATE.value(t.getFaqCreateDate()),
                        SERVICE_FAQTABLE.FAQ_CREATED_BY.value(t.getFaqCreatedBy()),
                        SERVICE_FAQTABLE.PRODUCT_ID.value(t.getProductId()),
                        SERVICE_FAQTABLE.FAQ_KEYWORDS.value(t.getFaqKeywords()),
                        SERVICE_FAQTABLE.FAQ_SOURCE_ID.value(t.getFaqSourceId()),
                        SERVICE_FAQTABLE.FAQ_SOURCE.value(t.getFaqSource()),
                        SERVICE_FAQTABLE.FAQ_REPLIED_BY.value(t.getFaqRepliedBy()),
                        SERVICE_FAQTABLE.FAQ_REPLY_DATE.value(t.getFaqReplyDate()),
                        SERVICE_FAQTABLE.FAQ_TYPE_ID.value(t.getFaqTypeId())).where(
                        SERVICE_FAQTABLE.FAQ_ID.eq(t.getFaqId()));
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
                return delete(SERVICE_FAQTABLE).where(SERVICE_FAQTABLE.FAQ_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(SERVICE_FAQTABLE).where(SERVICE_FAQTABLE.FAQ_ID.in(t));
            }
        }, pks);
    }

    public ServiceFaq getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, ServiceFaq.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(SERVICE_FAQTABLE).where(SERVICE_FAQTABLE.FAQ_ID.eq(t));
            }
        });
    }

    public List<ServiceFaq> query(ServiceFaq serviceFaq, final OrderBy... orderBies) {
        if (serviceFaq == null) {
            serviceFaq = new ServiceFaq();
        }
        return getDslTemplate().query(serviceFaq, new SelectGenerateCallback<ServiceFaq>() {

            @SuppressWarnings("rawtypes")
            public Select generate(ServiceFaq t) {
                Select select = selectFrom(SERVICE_FAQTABLE).where(
                        and(
                                SERVICE_FAQTABLE.FAQ_QUESTION.eq(t.getFaqQuestion()),
                                SERVICE_FAQTABLE.FAQ_ANSWER.eq(t.getFaqAnswer()),
                                SERVICE_FAQTABLE.DELETED.eq(t.getDeleted()),
                                SERVICE_FAQTABLE.FAQ_CREATE_DATE.eq(t.getFaqCreateDate()),
                                SERVICE_FAQTABLE.FAQ_CREATED_BY.eq(t.getFaqCreatedBy()),
                                SERVICE_FAQTABLE.PRODUCT_ID.eq(t.getProductId()),
                                SERVICE_FAQTABLE.FAQ_KEYWORDS.eq(t.getFaqKeywords()),
                                SERVICE_FAQTABLE.FAQ_SOURCE_ID.eq(t.getFaqSourceId()),
                                SERVICE_FAQTABLE.FAQ_SOURCE.eq(t.getFaqSource()),
                                SERVICE_FAQTABLE.FAQ_REPLIED_BY.eq(t.getFaqRepliedBy()),
                                SERVICE_FAQTABLE.FAQ_REPLY_DATE.eq(t.getFaqReplyDate()),
                                SERVICE_FAQTABLE.FAQ_TYPE_ID.eq(t.getFaqTypeId())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public Pager<ServiceFaq> queryPager(int start, int limit, ServiceFaq serviceFaq, final OrderBy... orderBies) {
        if (serviceFaq == null) {
            serviceFaq = new ServiceFaq();
        }
        return getDslTemplate().queryPager(start, limit, serviceFaq, false, new SelectGenerateCallback<ServiceFaq>() {

            public Select generate(ServiceFaq t) {
                Select select = MysqlSelect.selectFrom(SERVICE_FAQTABLE).where(
                        and(
                                SERVICE_FAQTABLE.FAQ_ID.eq(t.getFaqId()),
                                SERVICE_FAQTABLE.FAQ_QUESTION.eq(t.getFaqQuestion()),
                                SERVICE_FAQTABLE.FAQ_ANSWER.eq(t.getFaqAnswer()),
                                SERVICE_FAQTABLE.DELETED.eq(t.getDeleted()),
                                SERVICE_FAQTABLE.FAQ_CREATE_DATE.eq(t.getFaqCreateDate()),
                                SERVICE_FAQTABLE.FAQ_CREATED_BY.eq(t.getFaqCreatedBy()),
                                SERVICE_FAQTABLE.PRODUCT_ID.eq(t.getProductId()),
                                SERVICE_FAQTABLE.FAQ_KEYWORDS.eq(t.getFaqKeywords()),
                                SERVICE_FAQTABLE.FAQ_SOURCE_ID.eq(t.getFaqSourceId()),
                                SERVICE_FAQTABLE.FAQ_SOURCE.eq(t.getFaqSource()),
                                SERVICE_FAQTABLE.FAQ_REPLIED_BY.eq(t.getFaqRepliedBy()),
                                SERVICE_FAQTABLE.FAQ_REPLY_DATE.eq(t.getFaqReplyDate()),
                                SERVICE_FAQTABLE.FAQ_TYPE_ID.eq(t.getFaqTypeId())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<ServiceFaq> serviceFaqs) {
        if (CollectionUtil.isEmpty(serviceFaqs)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, serviceFaqs, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(SERVICE_FAQTABLE).values(
                        SERVICE_FAQTABLE.FAQ_QUESTION.value(new JdbcNamedParameter("faqQuestion")),
                        SERVICE_FAQTABLE.FAQ_ANSWER.value(new JdbcNamedParameter("faqAnswer")),
                        SERVICE_FAQTABLE.DELETED.value(new JdbcNamedParameter("deleted")),
                        SERVICE_FAQTABLE.FAQ_CREATE_DATE.value(new JdbcNamedParameter("faqCreateDate")),
                        SERVICE_FAQTABLE.FAQ_CREATED_BY.value(new JdbcNamedParameter("faqCreatedBy")),
                        SERVICE_FAQTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
                        SERVICE_FAQTABLE.FAQ_KEYWORDS.value(new JdbcNamedParameter("faqKeywords")),
                        SERVICE_FAQTABLE.FAQ_SOURCE_ID.value(new JdbcNamedParameter("faqSourceId")),
                        SERVICE_FAQTABLE.FAQ_SOURCE.value(new JdbcNamedParameter("faqSource")),
                        SERVICE_FAQTABLE.FAQ_REPLIED_BY.value(new JdbcNamedParameter("faqRepliedBy")),
                        SERVICE_FAQTABLE.FAQ_REPLY_DATE.value(new JdbcNamedParameter("faqReplyDate")),
                        SERVICE_FAQTABLE.FAQ_TYPE_ID.value(new JdbcNamedParameter("faqTypeId")));
            }
        });
    }

    public int[] batchInsert(List<ServiceFaq> serviceFaqs) {
        return batchInsert(true, serviceFaqs);
    }

    public int[] batchUpdate(List<ServiceFaq> serviceFaqs) {
        if (CollectionUtil.isEmpty(serviceFaqs)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(serviceFaqs, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(SERVICE_FAQTABLE).set(
                        SERVICE_FAQTABLE.FAQ_QUESTION.value(new JdbcNamedParameter("faqQuestion")),
                        SERVICE_FAQTABLE.FAQ_ANSWER.value(new JdbcNamedParameter("faqAnswer")),
                        SERVICE_FAQTABLE.DELETED.value(new JdbcNamedParameter("deleted")),
                        SERVICE_FAQTABLE.FAQ_CREATE_DATE.value(new JdbcNamedParameter("faqCreateDate")),
                        SERVICE_FAQTABLE.FAQ_CREATED_BY.value(new JdbcNamedParameter("faqCreatedBy")),
                        SERVICE_FAQTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
                        SERVICE_FAQTABLE.FAQ_KEYWORDS.value(new JdbcNamedParameter("faqKeywords")),
                        SERVICE_FAQTABLE.FAQ_SOURCE_ID.value(new JdbcNamedParameter("faqSourceId")),
                        SERVICE_FAQTABLE.FAQ_SOURCE.value(new JdbcNamedParameter("faqSource")),
                        SERVICE_FAQTABLE.FAQ_REPLIED_BY.value(new JdbcNamedParameter("faqRepliedBy")),
                        SERVICE_FAQTABLE.FAQ_REPLY_DATE.value(new JdbcNamedParameter("faqReplyDate")),
                        SERVICE_FAQTABLE.FAQ_TYPE_ID.value(new JdbcNamedParameter("faqTypeId"))).where(
                        SERVICE_FAQTABLE.FAQ_ID.eq(new JdbcNamedParameter("faqId")));
            }
        });
    }

    public int[] batchDelete(List<ServiceFaq> serviceFaqs) {
        if (CollectionUtil.isEmpty(serviceFaqs)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(serviceFaqs, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(SERVICE_FAQTABLE).where(and(
                        SERVICE_FAQTABLE.FAQ_ID.eq(new JdbcNamedParameter("faqId")),
                        SERVICE_FAQTABLE.FAQ_QUESTION.eq(new JdbcNamedParameter("faqQuestion")),
                        SERVICE_FAQTABLE.FAQ_ANSWER.eq(new JdbcNamedParameter("faqAnswer")),
                        SERVICE_FAQTABLE.DELETED.eq(new JdbcNamedParameter("deleted")),
                        SERVICE_FAQTABLE.FAQ_CREATE_DATE.eq(new JdbcNamedParameter("faqCreateDate")),
                        SERVICE_FAQTABLE.FAQ_CREATED_BY.eq(new JdbcNamedParameter("faqCreatedBy")),
                        SERVICE_FAQTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
                        SERVICE_FAQTABLE.FAQ_KEYWORDS.eq(new JdbcNamedParameter("faqKeywords")),
                        SERVICE_FAQTABLE.FAQ_SOURCE_ID.eq(new JdbcNamedParameter("faqSourceId")),
                        SERVICE_FAQTABLE.FAQ_SOURCE.eq(new JdbcNamedParameter("faqSource")),
                        SERVICE_FAQTABLE.FAQ_REPLIED_BY.eq(new JdbcNamedParameter("faqRepliedBy")),
                        SERVICE_FAQTABLE.FAQ_REPLY_DATE.eq(new JdbcNamedParameter("faqReplyDate")),
                        SERVICE_FAQTABLE.FAQ_TYPE_ID.eq(new JdbcNamedParameter("faqTypeId"))));
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

    /*查询问题总条数*//*
    public Integer SelectByKey() {
		Select select = select(SERVICE_FAQTABLE.FAQ_ID.count()).from(SERVICE_FAQTABLE);
		return getDslSession().count(select);
	}*/
    public Pager<ServiceFaq> getPagerByDeptId(int start, int limit, final Integer deptId, final OrderBy... orderBies) {

        Select select = MysqlSelect.selectFrom(SERVICE_FAQTABLE).where(
                and(
                        SERVICE_FAQTABLE.FAQ_TYPE_ID.eq(deptId),
                        SERVICE_FAQTABLE.DELETED.eq(0)));
        return getDslSession().fetchPage(select, start, limit, false, ServiceFaq.class);
    }

    public Pager<ServiceFaq> searchPager(Integer start, Integer limit, ServiceFaq faq, final String faqQuestion, final OrderBy... orderBies) {
        if (faq == null) {
            faq = new ServiceFaq();
        }
        return getDslTemplate().queryPager(start, limit, faq, false, new SelectGenerateCallback<ServiceFaq>() {

            public Select generate(ServiceFaq t) {
                Select select = MysqlSelect.selectFrom(SERVICE_FAQTABLE).where(
                        and(
                                SERVICE_FAQTABLE.FAQ_ID.eq(t.getFaqId()),
                                SERVICE_FAQTABLE.FAQ_QUESTION.like(faqQuestion),
                                SERVICE_FAQTABLE.FAQ_ANSWER.eq(t.getFaqAnswer()),
                                SERVICE_FAQTABLE.DELETED.eq(t.getDeleted()),
                                SERVICE_FAQTABLE.FAQ_CREATE_DATE.eq(t.getFaqCreateDate()),
                                SERVICE_FAQTABLE.FAQ_CREATED_BY.eq(t.getFaqCreatedBy()),
                                SERVICE_FAQTABLE.PRODUCT_ID.eq(t.getProductId()),
                                SERVICE_FAQTABLE.FAQ_KEYWORDS.eq(t.getFaqKeywords()),
                                SERVICE_FAQTABLE.FAQ_SOURCE_ID.eq(t.getFaqSourceId()),
                                SERVICE_FAQTABLE.FAQ_SOURCE.eq(t.getFaqSource()),
                                SERVICE_FAQTABLE.FAQ_REPLIED_BY.eq(t.getFaqRepliedBy()),
                                SERVICE_FAQTABLE.FAQ_REPLY_DATE.eq(t.getFaqReplyDate()),
                                SERVICE_FAQTABLE.FAQ_TYPE_ID.eq(t.getFaqTypeId())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    /*点击问题进去，里面的删除的方法*/
    public Integer softDelete(Integer id) {
        return getDslTemplate().update(id, new UpdateGenerateCallback<Integer>() {
            public Update generate(Integer id) {
                Update update = update(SERVICE_FAQTABLE).set(
                        SERVICE_FAQTABLE.DELETED.value(DELETE_YES)).where(
                        SERVICE_FAQTABLE.FAQ_ID.eq(id));
                return update;
            }
        });

    }
}
