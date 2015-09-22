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

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.service.dao.constant.ClientTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tinygroup.sdpm.common.log.annotation.LogClass;
import org.tinygroup.sdpm.common.log.annotation.LogMethod;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.sdpm.service.dao.pojo.Client;
import org.tinygroup.sdpm.service.dao.ClientDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

@LogClass("client")
@Repository
public class ClientDaoImpl extends TinyDslDaoSupport implements ClientDao {
    @LogMethod("add")
    public Client add(Client client) {
        return getDslTemplate().insertAndReturnKey(client, new InsertGenerateCallback<Client>() {
            public Insert generate(Client t) {
                Insert insert = insertInto(CLIENTTABLE).values(
                        CLIENTTABLE.CLIENT_ID.value(t.getClientId()),
                        CLIENTTABLE.CLIENT_NAME.value(t.getClientName()),
                        CLIENTTABLE.CLIENT_SPEC.value(t.getClientSpec()),
                        CLIENTTABLE.CLIENT_N_O.value(t.getClientNO()),
                        CLIENTTABLE.CLIENT_DEPT_ID.value(t.getClientDeptId()),
                        CLIENTTABLE.CLIENT_CREATED_BY.value(t.getClientCreatedBy()),
                        CLIENTTABLE.CLIENT_CREATE_DATE.value(t.getClientCreateDate()),
                        CLIENTTABLE.CLIENT_STATUS.value(t.getClientStatus()),
                        CLIENTTABLE.USER_PHONE.value(t.getUserPhone()),
                        CLIENTTABLE.USER_ACCOUNT.value(t.getUserAccount()),
                        CLIENTTABLE.USER_POST.value(t.getUserPost()),
                        CLIENTTABLE.DELETED.value(t.getDeleted()));
                return insert;
            }
        });
    }
    @LogMethod("edit")
    public int edit(Client client) {
        if (client == null || client.getClientId() == null) {
            return 0;
        }
        return getDslTemplate().update(client, new UpdateGenerateCallback<Client>() {
            public Update generate(Client t) {
                Update update = update(CLIENTTABLE).set(
                        CLIENTTABLE.CLIENT_NAME.value(t.getClientName()),
                        CLIENTTABLE.CLIENT_SPEC.value(t.getClientSpec()),
                        CLIENTTABLE.CLIENT_N_O.value(t.getClientNO()),
                        CLIENTTABLE.CLIENT_DEPT_ID.value(t.getClientDeptId()),
                        CLIENTTABLE.CLIENT_CREATED_BY.value(t.getClientCreatedBy()),
                        CLIENTTABLE.CLIENT_CREATE_DATE.value(t.getClientCreateDate()),
                        CLIENTTABLE.CLIENT_STATUS.value(t.getClientStatus()),
                        CLIENTTABLE.USER_PHONE.value(t.getUserPhone()),
                        CLIENTTABLE.USER_ACCOUNT.value(t.getUserAccount()),
                        CLIENTTABLE.USER_POST.value(t.getUserPost()),
                        CLIENTTABLE.DELETED.value(t.getDeleted())).where(
                        CLIENTTABLE.CLIENT_ID.eq(t.getClientId()));
                return update;
            }
        });
    }
    @LogMethod("deleteByKey")
    public int deleteByKey(Integer pk) {
        if (pk == null) {
            return 0;
        }
        return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
            public Delete generate(Serializable pk) {
                return delete(CLIENTTABLE).where(CLIENTTABLE.CLIENT_ID.eq(pk));
            }
        });
    }
    @LogMethod("deleteByKeys")
    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(CLIENTTABLE).where(CLIENTTABLE.CLIENT_ID.in(t));
            }
        }, pks);
    }
    @LogMethod("getByKey")
    public Client getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, Client.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(CLIENTTABLE).where(CLIENTTABLE.CLIENT_ID.eq(t));
            }
        });
    }
    @LogMethod("query")
    public List<Client> query(Client client, final OrderBy... orderBies) {
        if (client == null) {
            client = new Client();
        }
        return getDslTemplate().query(client, new SelectGenerateCallback<Client>() {

            @SuppressWarnings("rawtypes")
            public Select generate(Client t) {
                Select select = selectFrom(CLIENTTABLE).where(
                        and(
                                CLIENTTABLE.CLIENT_NAME.eq(t.getClientName()),
                                CLIENTTABLE.CLIENT_SPEC.eq(t.getClientSpec()),
                                CLIENTTABLE.CLIENT_N_O.eq(t.getClientNO()),
                                CLIENTTABLE.CLIENT_DEPT_ID.eq(t.getClientDeptId()),
                                CLIENTTABLE.CLIENT_CREATED_BY.eq(t.getClientCreatedBy()),
                                CLIENTTABLE.CLIENT_CREATE_DATE.eq(t.getClientCreateDate()),
                                CLIENTTABLE.CLIENT_STATUS.eq(t.getClientStatus()),
                                CLIENTTABLE.USER_PHONE.eq(t.getUserPhone()),
                                CLIENTTABLE.USER_ACCOUNT.eq(t.getUserAccount()),
                                CLIENTTABLE.USER_POST.eq(t.getUserPost()),
                                CLIENTTABLE.DELETED.eq(t.getDeleted())));
                return addOrderByElements(select, orderBies);
            }
        });
    }
    @LogMethod("queryPager")
    public Pager<Client> queryPager(int start, int limit, Client client, final OrderBy... orderBies) {
        if (client == null) {
            client = new Client();
        }
        return getDslTemplate().queryPager(start, limit, client, false, new SelectGenerateCallback<Client>() {

            public Select generate(Client t) {
                Select select = MysqlSelect.selectFrom(CLIENTTABLE).where(
                        and(
                                CLIENTTABLE.CLIENT_NAME.eq(t.getClientName()),
                                CLIENTTABLE.CLIENT_SPEC.eq(t.getClientSpec()),
                                CLIENTTABLE.CLIENT_N_O.eq(t.getClientNO()),
                                CLIENTTABLE.CLIENT_DEPT_ID.eq(t.getClientDeptId()),
                                CLIENTTABLE.CLIENT_CREATED_BY.eq(t.getClientCreatedBy()),
                                CLIENTTABLE.CLIENT_CREATE_DATE.eq(t.getClientCreateDate()),
                                CLIENTTABLE.CLIENT_STATUS.eq(t.getClientStatus()),
                                CLIENTTABLE.USER_PHONE.eq(t.getUserPhone()),
                                CLIENTTABLE.USER_ACCOUNT.eq(t.getUserAccount()),
                                CLIENTTABLE.USER_POST.eq(t.getUserPost()),
                                CLIENTTABLE.DELETED.eq(t.getDeleted())));
                return addOrderByElements(select, orderBies);
            }
        });
    }
    @LogMethod("batchInsert")
    public int[] batchInsert(boolean autoGeneratedKeys, List<Client> clients) {
        if (CollectionUtil.isEmpty(clients)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, clients, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(CLIENTTABLE).values(
                        CLIENTTABLE.CLIENT_NAME.value(new JdbcNamedParameter("clientName")),
                        CLIENTTABLE.CLIENT_SPEC.value(new JdbcNamedParameter("clientSpec")),
                        CLIENTTABLE.CLIENT_N_O.value(new JdbcNamedParameter("clientNO")),
                        CLIENTTABLE.CLIENT_DEPT_ID.value(new JdbcNamedParameter("clientDeptId")),
                        CLIENTTABLE.CLIENT_CREATED_BY.value(new JdbcNamedParameter("clientCreatedBy")),
                        CLIENTTABLE.CLIENT_CREATE_DATE.value(new JdbcNamedParameter("clientCreateDate")),
                        CLIENTTABLE.CLIENT_STATUS.value(new JdbcNamedParameter("clientStatus")),
                        CLIENTTABLE.USER_PHONE.value(new JdbcNamedParameter("userPhone")),
                        CLIENTTABLE.USER_ACCOUNT.value(new JdbcNamedParameter("userAccount")),
                        CLIENTTABLE.USER_POST.value(new JdbcNamedParameter("userPost")),
                        CLIENTTABLE.DELETED.value(new JdbcNamedParameter("deleted")));
            }
        });
    }
    @LogMethod("batchInsert")
    public int[] batchInsert(List<Client> clients) {
        return batchInsert(true, clients);
    }
    @LogMethod("batchUpdate")
    public int[] batchUpdate(List<Client> clients) {
        if (CollectionUtil.isEmpty(clients)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(clients, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(CLIENTTABLE).set(
                        CLIENTTABLE.CLIENT_NAME.value(new JdbcNamedParameter("clientName")),
                        CLIENTTABLE.CLIENT_SPEC.value(new JdbcNamedParameter("clientSpec")),
                        CLIENTTABLE.CLIENT_N_O.value(new JdbcNamedParameter("clientNO")),
                        CLIENTTABLE.CLIENT_DEPT_ID.value(new JdbcNamedParameter("clientDeptId")),
                        CLIENTTABLE.CLIENT_CREATED_BY.value(new JdbcNamedParameter("clientCreatedBy")),
                        CLIENTTABLE.CLIENT_CREATE_DATE.value(new JdbcNamedParameter("clientCreateDate")),
                        CLIENTTABLE.CLIENT_STATUS.value(new JdbcNamedParameter("clientStatus")),
                        CLIENTTABLE.USER_PHONE.value(new JdbcNamedParameter("userPhone")),
                        CLIENTTABLE.USER_ACCOUNT.value(new JdbcNamedParameter("userAccount")),
                        CLIENTTABLE.USER_POST.value(new JdbcNamedParameter("userPost")),
                        CLIENTTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
                        CLIENTTABLE.CLIENT_ID.eq(new JdbcNamedParameter("clientId")));
            }
        });
    }
    @LogMethod("batchDelete")
    public int[] batchDelete(List<Client> clients) {
        if (CollectionUtil.isEmpty(clients)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(clients, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(CLIENTTABLE).where(and(
                        CLIENTTABLE.CLIENT_ID.eq(new JdbcNamedParameter("clientId")),
                        CLIENTTABLE.CLIENT_NAME.eq(new JdbcNamedParameter("clientName")),
                        CLIENTTABLE.CLIENT_SPEC.eq(new JdbcNamedParameter("clientSpec")),
                        CLIENTTABLE.CLIENT_N_O.eq(new JdbcNamedParameter("clientNO")),
                        CLIENTTABLE.CLIENT_DEPT_ID.eq(new JdbcNamedParameter("clientDeptId")),
                        CLIENTTABLE.CLIENT_CREATED_BY.eq(new JdbcNamedParameter("clientCreatedBy")),
                        CLIENTTABLE.CLIENT_CREATE_DATE.eq(new JdbcNamedParameter("clientCreateDate")),
                        CLIENTTABLE.CLIENT_STATUS.eq(new JdbcNamedParameter("clientStatus")),
                        CLIENTTABLE.USER_PHONE.eq(new JdbcNamedParameter("userPhone")),
                        CLIENTTABLE.USER_ACCOUNT.eq(new JdbcNamedParameter("userAccount")),
                        CLIENTTABLE.USER_POST.eq(new JdbcNamedParameter("userPost")),
                        CLIENTTABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
            }
        });
    }
    @LogMethod("addOrderByElements")
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
