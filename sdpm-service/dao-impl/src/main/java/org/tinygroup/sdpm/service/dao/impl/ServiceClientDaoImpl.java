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
import org.tinygroup.sdpm.service.dao.ServiceClientDao;
import org.tinygroup.sdpm.service.dao.pojo.ServiceClient;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.service.dao.constant.ServiceClientTable.SERVICE_CLIENTTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class ServiceClientDaoImpl extends TinyDslDaoSupport implements ServiceClientDao {


    public ServiceClient add(ServiceClient serviceClient) {
        return getDslTemplate().insertAndReturnKey(serviceClient, new InsertGenerateCallback<ServiceClient>() {
            public Insert generate(ServiceClient t) {
                Insert insert = insertInto(SERVICE_CLIENTTABLE).values(
                        SERVICE_CLIENTTABLE.CLIENT_ID.value(t.getClientId()),
                        SERVICE_CLIENTTABLE.CLIENT_NAME.value(t.getClientName()),
                        SERVICE_CLIENTTABLE.CLIENT_SPEC.value(t.getClientSpec()),
                        SERVICE_CLIENTTABLE.CLIENT_N_O.value(t.getClientNO()),
                        SERVICE_CLIENTTABLE.CLIENT_DEPT_ID.value(t.getClientDeptId()),
                        SERVICE_CLIENTTABLE.CLIENT_CREATED_BY.value(t.getClientCreatedBy()),
                        SERVICE_CLIENTTABLE.CLIENT_CREATE_DATE.value(t.getClientCreateDate()),
                        SERVICE_CLIENTTABLE.CLIENT_STATUS.value(t.getClientStatus()),
                        SERVICE_CLIENTTABLE.USER_PHONE.value(t.getUserPhone()),
                        SERVICE_CLIENTTABLE.USER_ACCOUNT.value(t.getUserAccount()),
                        SERVICE_CLIENTTABLE.USER_POST.value(t.getUserPost()),
                        SERVICE_CLIENTTABLE.DELETED.value(t.getDeleted()));
                return insert;
            }
        });
    }

    public int edit(ServiceClient serviceClient) {
        if (serviceClient == null || serviceClient.getClientId() == null) {
            return 0;
        }
        return getDslTemplate().update(serviceClient, new UpdateGenerateCallback<ServiceClient>() {
            public Update generate(ServiceClient t) {
                Update update = update(SERVICE_CLIENTTABLE).set(
                        SERVICE_CLIENTTABLE.CLIENT_NAME.value(t.getClientName()),
                        SERVICE_CLIENTTABLE.CLIENT_SPEC.value(t.getClientSpec()),
                        SERVICE_CLIENTTABLE.CLIENT_N_O.value(t.getClientNO()),
                        SERVICE_CLIENTTABLE.CLIENT_DEPT_ID.value(t.getClientDeptId()),
                        SERVICE_CLIENTTABLE.CLIENT_CREATED_BY.value(t.getClientCreatedBy()),
                        SERVICE_CLIENTTABLE.CLIENT_CREATE_DATE.value(t.getClientCreateDate()),
                        SERVICE_CLIENTTABLE.CLIENT_STATUS.value(t.getClientStatus()),
                        SERVICE_CLIENTTABLE.USER_PHONE.value(t.getUserPhone()),
                        SERVICE_CLIENTTABLE.USER_ACCOUNT.value(t.getUserAccount()),
                        SERVICE_CLIENTTABLE.USER_POST.value(t.getUserPost()),
                        SERVICE_CLIENTTABLE.DELETED.value(t.getDeleted())).where(
                        SERVICE_CLIENTTABLE.CLIENT_ID.eq(t.getClientId()));
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
                return delete(SERVICE_CLIENTTABLE).where(SERVICE_CLIENTTABLE.CLIENT_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(SERVICE_CLIENTTABLE).where(SERVICE_CLIENTTABLE.CLIENT_ID.in(t));
            }
        }, pks);
    }

    public ServiceClient getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, ServiceClient.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(SERVICE_CLIENTTABLE).where(SERVICE_CLIENTTABLE.CLIENT_ID.eq(t));
            }
        });
    }

    public List<ServiceClient> query(ServiceClient serviceClient, final OrderBy... orderBies) {
        if (serviceClient == null) {
            serviceClient = new ServiceClient();
        }
        return getDslTemplate().query(serviceClient, new SelectGenerateCallback<ServiceClient>() {

            @SuppressWarnings("rawtypes")
            public Select generate(ServiceClient t) {
                Select select = selectFrom(SERVICE_CLIENTTABLE).where(
                        and(
                                SERVICE_CLIENTTABLE.CLIENT_NAME.eq(t.getClientName()),
                                SERVICE_CLIENTTABLE.CLIENT_SPEC.eq(t.getClientSpec()),
                                SERVICE_CLIENTTABLE.CLIENT_N_O.eq(t.getClientNO()),
                                SERVICE_CLIENTTABLE.CLIENT_DEPT_ID.eq(t.getClientDeptId()),
                                SERVICE_CLIENTTABLE.CLIENT_CREATED_BY.eq(t.getClientCreatedBy()),
                                SERVICE_CLIENTTABLE.CLIENT_CREATE_DATE.eq(t.getClientCreateDate()),
                                SERVICE_CLIENTTABLE.CLIENT_STATUS.eq(t.getClientStatus()),
                                SERVICE_CLIENTTABLE.USER_PHONE.eq(t.getUserPhone()),
                                SERVICE_CLIENTTABLE.USER_ACCOUNT.eq(t.getUserAccount()),
                                SERVICE_CLIENTTABLE.USER_POST.eq(t.getUserPost()),
                                SERVICE_CLIENTTABLE.DELETED.eq(t.getDeleted())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public Pager<ServiceClient> queryPager(int start, int limit, ServiceClient serviceClient, final OrderBy... orderBies) {
        if (serviceClient == null) {
            serviceClient = new ServiceClient();
        }
        return getDslTemplate().queryPager(start, limit, serviceClient, false, new SelectGenerateCallback<ServiceClient>() {

            public Select generate(ServiceClient t) {
                Select select = MysqlSelect.selectFrom(SERVICE_CLIENTTABLE).where(
                        and(
                                SERVICE_CLIENTTABLE.CLIENT_NAME.eq(t.getClientName()),
                                SERVICE_CLIENTTABLE.CLIENT_SPEC.eq(t.getClientSpec()),
                                SERVICE_CLIENTTABLE.CLIENT_N_O.eq(t.getClientNO()),
                                SERVICE_CLIENTTABLE.CLIENT_DEPT_ID.eq(t.getClientDeptId()),
                                SERVICE_CLIENTTABLE.CLIENT_CREATED_BY.eq(t.getClientCreatedBy()),
                                SERVICE_CLIENTTABLE.CLIENT_CREATE_DATE.eq(t.getClientCreateDate()),
                                SERVICE_CLIENTTABLE.CLIENT_STATUS.eq(t.getClientStatus()),
                                SERVICE_CLIENTTABLE.USER_PHONE.eq(t.getUserPhone()),
                                SERVICE_CLIENTTABLE.USER_ACCOUNT.eq(t.getUserAccount()),
                                SERVICE_CLIENTTABLE.USER_POST.eq(t.getUserPost()),
                                SERVICE_CLIENTTABLE.DELETED.eq(t.getDeleted())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<ServiceClient> serviceClients) {
        if (CollectionUtil.isEmpty(serviceClients)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, serviceClients, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(SERVICE_CLIENTTABLE).values(
                        SERVICE_CLIENTTABLE.CLIENT_NAME.value(new JdbcNamedParameter("clientName")),
                        SERVICE_CLIENTTABLE.CLIENT_SPEC.value(new JdbcNamedParameter("clientSpec")),
                        SERVICE_CLIENTTABLE.CLIENT_N_O.value(new JdbcNamedParameter("clientNO")),
                        SERVICE_CLIENTTABLE.CLIENT_DEPT_ID.value(new JdbcNamedParameter("clientDeptId")),
                        SERVICE_CLIENTTABLE.CLIENT_CREATED_BY.value(new JdbcNamedParameter("clientCreatedBy")),
                        SERVICE_CLIENTTABLE.CLIENT_CREATE_DATE.value(new JdbcNamedParameter("clientCreateDate")),
                        SERVICE_CLIENTTABLE.CLIENT_STATUS.value(new JdbcNamedParameter("clientStatus")),
                        SERVICE_CLIENTTABLE.USER_PHONE.value(new JdbcNamedParameter("userPhone")),
                        SERVICE_CLIENTTABLE.USER_ACCOUNT.value(new JdbcNamedParameter("userAccount")),
                        SERVICE_CLIENTTABLE.USER_POST.value(new JdbcNamedParameter("userPost")),
                        SERVICE_CLIENTTABLE.DELETED.value(new JdbcNamedParameter("deleted")));
            }
        });
    }

    public int[] batchInsert(List<ServiceClient> serviceClients) {
        return batchInsert(true, serviceClients);
    }

    public int[] batchUpdate(List<ServiceClient> serviceClients) {
        if (CollectionUtil.isEmpty(serviceClients)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(serviceClients, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(SERVICE_CLIENTTABLE).set(
                        SERVICE_CLIENTTABLE.CLIENT_NAME.value(new JdbcNamedParameter("clientName")),
                        SERVICE_CLIENTTABLE.CLIENT_SPEC.value(new JdbcNamedParameter("clientSpec")),
                        SERVICE_CLIENTTABLE.CLIENT_N_O.value(new JdbcNamedParameter("clientNO")),
                        SERVICE_CLIENTTABLE.CLIENT_DEPT_ID.value(new JdbcNamedParameter("clientDeptId")),
                        SERVICE_CLIENTTABLE.CLIENT_CREATED_BY.value(new JdbcNamedParameter("clientCreatedBy")),
                        SERVICE_CLIENTTABLE.CLIENT_CREATE_DATE.value(new JdbcNamedParameter("clientCreateDate")),
                        SERVICE_CLIENTTABLE.CLIENT_STATUS.value(new JdbcNamedParameter("clientStatus")),
                        SERVICE_CLIENTTABLE.USER_PHONE.value(new JdbcNamedParameter("userPhone")),
                        SERVICE_CLIENTTABLE.USER_ACCOUNT.value(new JdbcNamedParameter("userAccount")),
                        SERVICE_CLIENTTABLE.USER_POST.value(new JdbcNamedParameter("userPost")),
                        SERVICE_CLIENTTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
                        SERVICE_CLIENTTABLE.CLIENT_ID.eq(new JdbcNamedParameter("clientId")));
            }
        });
    }

    public int[] batchDelete(List<ServiceClient> serviceClients) {
        if (CollectionUtil.isEmpty(serviceClients)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(serviceClients, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(SERVICE_CLIENTTABLE).where(and(
                        SERVICE_CLIENTTABLE.CLIENT_ID.eq(new JdbcNamedParameter("clientId")),
                        SERVICE_CLIENTTABLE.CLIENT_NAME.eq(new JdbcNamedParameter("clientName")),
                        SERVICE_CLIENTTABLE.CLIENT_SPEC.eq(new JdbcNamedParameter("clientSpec")),
                        SERVICE_CLIENTTABLE.CLIENT_N_O.eq(new JdbcNamedParameter("clientNO")),
                        SERVICE_CLIENTTABLE.CLIENT_DEPT_ID.eq(new JdbcNamedParameter("clientDeptId")),
                        SERVICE_CLIENTTABLE.CLIENT_CREATED_BY.eq(new JdbcNamedParameter("clientCreatedBy")),
                        SERVICE_CLIENTTABLE.CLIENT_CREATE_DATE.eq(new JdbcNamedParameter("clientCreateDate")),
                        SERVICE_CLIENTTABLE.CLIENT_STATUS.eq(new JdbcNamedParameter("clientStatus")),
                        SERVICE_CLIENTTABLE.USER_PHONE.eq(new JdbcNamedParameter("userPhone")),
                        SERVICE_CLIENTTABLE.USER_ACCOUNT.eq(new JdbcNamedParameter("userAccount")),
                        SERVICE_CLIENTTABLE.USER_POST.eq(new JdbcNamedParameter("userPost")),
                        SERVICE_CLIENTTABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
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

    public Integer softDelete(Integer id) {
        return getDslTemplate().update(id, new UpdateGenerateCallback<Integer>() {
            public Update generate(Integer id) {
                Update update = update(SERVICE_CLIENTTABLE).set(
                        SERVICE_CLIENTTABLE.DELETED.value(ServiceClient.DELETE_YES)).where(
                        SERVICE_CLIENTTABLE.CLIENT_ID.eq(id));
                return update;
            }
        });

    }

    public int[] softDeleteBatch(List<ServiceClient> list) {
        if (CollectionUtil.isEmpty(list)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(list, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(SERVICE_CLIENTTABLE).set(
                        SERVICE_CLIENTTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
                        SERVICE_CLIENTTABLE.CLIENT_ID.eq(new JdbcNamedParameter("clientId")));
            }
        });
    }
}
