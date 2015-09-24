/**
 * Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 * <p/>
 * Licensed under the GPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.gnu.org/licenses/gpl.html
 * <p/>
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
import org.tinygroup.sdpm.service.dao.ServiceReplyDao;
import org.tinygroup.sdpm.service.dao.pojo.ServiceReply;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.service.dao.constant.ServiceReplyTable.SERVICE_REPLYTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class ServiceReplyDaoImpl extends TinyDslDaoSupport implements ServiceReplyDao {

    public ServiceReply add(ServiceReply serviceReply) {
        return getDslTemplate().insertAndReturnKey(serviceReply, new InsertGenerateCallback<ServiceReply>() {
            public Insert generate(ServiceReply t) {
                Insert insert = insertInto(SERVICE_REPLYTABLE).values(
                        SERVICE_REPLYTABLE.REPLY_ID.value(t.getReplyId()),
                        SERVICE_REPLYTABLE.CLIENT_REQUEST_ID.value(t.getClientRequestId()),
                        SERVICE_REPLYTABLE.REPLY_OPINION.value(t.getReplyOpinion()),
                        SERVICE_REPLYTABLE.REPLY_SPEC.value(t.getReplySpec()),
                        SERVICE_REPLYTABLE.REPLY_COMMITMENT_DATE.value(t.getReplyCommitmentDate()),
                        SERVICE_REPLYTABLE.REPLY_DO_BY.value(t.getReplyDoBy()),
                        SERVICE_REPLYTABLE.REPLY_DO_DATE.value(t.getReplyDoDate()),
                        SERVICE_REPLYTABLE.REPLIER.value(t.getReplier()),
                        SERVICE_REPLYTABLE.REPLY_DATE.value(t.getReplyDate()),
                        SERVICE_REPLYTABLE.REPLY_DONE.value(t.getReplyDone()));
                return insert;
            }
        });
    }

    public int edit(ServiceReply serviceReply) {
        if (serviceReply == null || serviceReply.getReplyId() == null) {
            return 0;
        }
        return getDslTemplate().update(serviceReply, new UpdateGenerateCallback<ServiceReply>() {
            public Update generate(ServiceReply t) {
                Update update = update(SERVICE_REPLYTABLE).set(
                        SERVICE_REPLYTABLE.CLIENT_REQUEST_ID.value(t.getClientRequestId()),
                        SERVICE_REPLYTABLE.REPLY_OPINION.value(t.getReplyOpinion()),
                        SERVICE_REPLYTABLE.REPLY_SPEC.value(t.getReplySpec()),
                        SERVICE_REPLYTABLE.REPLY_COMMITMENT_DATE.value(t.getReplyCommitmentDate()),
                        SERVICE_REPLYTABLE.REPLY_DO_BY.value(t.getReplyDoBy()),
                        SERVICE_REPLYTABLE.REPLY_DO_DATE.value(t.getReplyDoDate()),
                        SERVICE_REPLYTABLE.REPLIER.value(t.getReplier()),
                        SERVICE_REPLYTABLE.REPLY_DATE.value(t.getReplyDate()),
                        SERVICE_REPLYTABLE.REPLY_DONE.value(t.getReplyDone())).where(
                        SERVICE_REPLYTABLE.REPLY_ID.eq(t.getReplyId()));
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
                return delete(SERVICE_REPLYTABLE).where(SERVICE_REPLYTABLE.REPLY_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(SERVICE_REPLYTABLE).where(SERVICE_REPLYTABLE.REPLY_ID.in(t));
            }
        }, pks);
    }

    public ServiceReply getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, ServiceReply.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(SERVICE_REPLYTABLE).where(SERVICE_REPLYTABLE.REPLY_ID.eq(t));
            }
        });
    }

    public List<ServiceReply> query(ServiceReply serviceReply, final OrderBy... orderBies) {
        if (serviceReply == null) {
            serviceReply = new ServiceReply();
        }
        return getDslTemplate().query(serviceReply, new SelectGenerateCallback<ServiceReply>() {

            @SuppressWarnings("rawtypes")
            public Select generate(ServiceReply t) {
                Select select = selectFrom(SERVICE_REPLYTABLE).where(
                        and(
                                SERVICE_REPLYTABLE.CLIENT_REQUEST_ID.eq(t.getClientRequestId()),
                                SERVICE_REPLYTABLE.REPLY_OPINION.eq(t.getReplyOpinion()),
                                SERVICE_REPLYTABLE.REPLY_SPEC.eq(t.getReplySpec()),
                                SERVICE_REPLYTABLE.REPLY_COMMITMENT_DATE.eq(t.getReplyCommitmentDate()),
                                SERVICE_REPLYTABLE.REPLY_DO_BY.eq(t.getReplyDoBy()),
                                SERVICE_REPLYTABLE.REPLY_DO_DATE.eq(t.getReplyDoDate()),
                                SERVICE_REPLYTABLE.REPLIER.eq(t.getReplier()),
                                SERVICE_REPLYTABLE.REPLY_DATE.eq(t.getReplyDate()),
                                SERVICE_REPLYTABLE.REPLY_DONE.eq(t.getReplyDone())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public Pager<ServiceReply> queryPager(int start, int limit, ServiceReply serviceReply, final OrderBy... orderBies) {
        if (serviceReply == null) {
            serviceReply = new ServiceReply();
        }
        return getDslTemplate().queryPager(start, limit, serviceReply, false, new SelectGenerateCallback<ServiceReply>() {

            public Select generate(ServiceReply t) {
                Select select = MysqlSelect.selectFrom(SERVICE_REPLYTABLE).where(
                        and(
                                SERVICE_REPLYTABLE.CLIENT_REQUEST_ID.eq(t.getClientRequestId()),
                                SERVICE_REPLYTABLE.REPLY_OPINION.eq(t.getReplyOpinion()),
                                SERVICE_REPLYTABLE.REPLY_SPEC.eq(t.getReplySpec()),
                                SERVICE_REPLYTABLE.REPLY_COMMITMENT_DATE.eq(t.getReplyCommitmentDate()),
                                SERVICE_REPLYTABLE.REPLY_DO_BY.eq(t.getReplyDoBy()),
                                SERVICE_REPLYTABLE.REPLY_DO_DATE.eq(t.getReplyDoDate()),
                                SERVICE_REPLYTABLE.REPLIER.eq(t.getReplier()),
                                SERVICE_REPLYTABLE.REPLY_DATE.eq(t.getReplyDate()),
                                SERVICE_REPLYTABLE.REPLY_DONE.eq(t.getReplyDone())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<ServiceReply> serviceReplys) {
        if (CollectionUtil.isEmpty(serviceReplys)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, serviceReplys, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(SERVICE_REPLYTABLE).values(
                        SERVICE_REPLYTABLE.CLIENT_REQUEST_ID.value(new JdbcNamedParameter("clientRequestId")),
                        SERVICE_REPLYTABLE.REPLY_OPINION.value(new JdbcNamedParameter("replyOpinion")),
                        SERVICE_REPLYTABLE.REPLY_SPEC.value(new JdbcNamedParameter("replySpec")),
                        SERVICE_REPLYTABLE.REPLY_COMMITMENT_DATE.value(new JdbcNamedParameter("replyCommitmentDate")),
                        SERVICE_REPLYTABLE.REPLY_DO_BY.value(new JdbcNamedParameter("replyDoBy")),
                        SERVICE_REPLYTABLE.REPLY_DO_DATE.value(new JdbcNamedParameter("replyDoDate")),
                        SERVICE_REPLYTABLE.REPLIER.value(new JdbcNamedParameter("replier")),
                        SERVICE_REPLYTABLE.REPLY_DATE.value(new JdbcNamedParameter("replyDate")),
                        SERVICE_REPLYTABLE.REPLY_DONE.value(new JdbcNamedParameter("replyDone")));
            }
        });
    }

    public int[] batchInsert(List<ServiceReply> serviceReplys) {
        return batchInsert(true, serviceReplys);
    }

    public int[] batchUpdate(List<ServiceReply> serviceReplys) {
        if (CollectionUtil.isEmpty(serviceReplys)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(serviceReplys, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(SERVICE_REPLYTABLE).set(
                        SERVICE_REPLYTABLE.CLIENT_REQUEST_ID.value(new JdbcNamedParameter("clientRequestId")),
                        SERVICE_REPLYTABLE.REPLY_OPINION.value(new JdbcNamedParameter("replyOpinion")),
                        SERVICE_REPLYTABLE.REPLY_SPEC.value(new JdbcNamedParameter("replySpec")),
                        SERVICE_REPLYTABLE.REPLY_COMMITMENT_DATE.value(new JdbcNamedParameter("replyCommitmentDate")),
                        SERVICE_REPLYTABLE.REPLY_DO_BY.value(new JdbcNamedParameter("replyDoBy")),
                        SERVICE_REPLYTABLE.REPLY_DO_DATE.value(new JdbcNamedParameter("replyDoDate")),
                        SERVICE_REPLYTABLE.REPLIER.value(new JdbcNamedParameter("replier")),
                        SERVICE_REPLYTABLE.REPLY_DATE.value(new JdbcNamedParameter("replyDate")),
                        SERVICE_REPLYTABLE.REPLY_DONE.value(new JdbcNamedParameter("replyDone"))).where(
                        SERVICE_REPLYTABLE.REPLY_ID.eq(new JdbcNamedParameter("replyId")));
            }
        });
    }

    public int[] batchDelete(List<ServiceReply> serviceReplys) {
        if (CollectionUtil.isEmpty(serviceReplys)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(serviceReplys, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(SERVICE_REPLYTABLE).where(and(
                        SERVICE_REPLYTABLE.REPLY_ID.eq(new JdbcNamedParameter("replyId")),
                        SERVICE_REPLYTABLE.CLIENT_REQUEST_ID.eq(new JdbcNamedParameter("clientRequestId")),
                        SERVICE_REPLYTABLE.REPLY_OPINION.eq(new JdbcNamedParameter("replyOpinion")),
                        SERVICE_REPLYTABLE.REPLY_SPEC.eq(new JdbcNamedParameter("replySpec")),
                        SERVICE_REPLYTABLE.REPLY_COMMITMENT_DATE.eq(new JdbcNamedParameter("replyCommitmentDate")),
                        SERVICE_REPLYTABLE.REPLY_DO_BY.eq(new JdbcNamedParameter("replyDoBy")),
                        SERVICE_REPLYTABLE.REPLY_DO_DATE.eq(new JdbcNamedParameter("replyDoDate")),
                        SERVICE_REPLYTABLE.REPLIER.eq(new JdbcNamedParameter("replier")),
                        SERVICE_REPLYTABLE.REPLY_DATE.eq(new JdbcNamedParameter("replyDate")),
                        SERVICE_REPLYTABLE.REPLY_DONE.eq(new JdbcNamedParameter("replyDone"))));
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
