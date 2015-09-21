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
import static org.tinygroup.sdpm.service.dao.constant.ClientUserTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;

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
import org.tinygroup.sdpm.service.dao.pojo.ClientUser;
import org.tinygroup.sdpm.service.dao.ClientUserDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

@Repository
@LogClass("service")
public class ClientUserDaoImpl extends TinyDslDaoSupport implements ClientUserDao {
    @LogMethod("service")
    public ClientUser add(ClientUser clientUser) {
        return getDslTemplate().insertAndReturnKey(clientUser, new InsertGenerateCallback<ClientUser>() {
            public Insert generate(ClientUser t) {
                Insert insert = insertInto(CLIENT_USERTABLE).values(
                        CLIENT_USERTABLE.ID.value(t.getId()),
                        CLIENT_USERTABLE.CLIENT_ID.value(t.getClientId()),
                        CLIENT_USERTABLE.USER_ACCOUNT.value(t.getUserAccount()),
                        CLIENT_USERTABLE.USER_PHONE.value(t.getUserPhone()),
                        CLIENT_USERTABLE.USER_POST.value(t.getUserPost()));
                return insert;
            }
        });
    }
    @LogMethod("service")
    public int edit(ClientUser clientUser) {
        if (clientUser == null || clientUser.getId() == null) {
            return 0;
        }
        return getDslTemplate().update(clientUser, new UpdateGenerateCallback<ClientUser>() {
            public Update generate(ClientUser t) {
                Update update = update(CLIENT_USERTABLE).set(
                        CLIENT_USERTABLE.CLIENT_ID.value(t.getClientId()),
                        CLIENT_USERTABLE.USER_ACCOUNT.value(t.getUserAccount()),
                        CLIENT_USERTABLE.USER_PHONE.value(t.getUserPhone()),
                        CLIENT_USERTABLE.USER_POST.value(t.getUserPost())).where(
                        CLIENT_USERTABLE.ID.eq(t.getId()));
                return update;
            }
        });
    }
    @LogMethod("service")
    public int deleteByKey(Integer pk) {
        if (pk == null) {
            return 0;
        }
        return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
            public Delete generate(Serializable pk) {
                return delete(CLIENT_USERTABLE).where(CLIENT_USERTABLE.ID.eq(pk));
            }
        });
    }
    @LogMethod("service")
    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(CLIENT_USERTABLE).where(CLIENT_USERTABLE.ID.in(t));
            }
        }, pks);
    }
    @LogMethod("service")
    public ClientUser getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, ClientUser.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(CLIENT_USERTABLE).where(CLIENT_USERTABLE.ID.eq(t));
            }
        });
    }
    @LogMethod("service")
    public List<ClientUser> query(ClientUser clientUser) {
        if (clientUser == null) {
            clientUser = new ClientUser();
        }
        return getDslTemplate().query(clientUser, new SelectGenerateCallback<ClientUser>() {

            @SuppressWarnings("rawtypes")
            public Select generate(ClientUser t) {
                return selectFrom(CLIENT_USERTABLE).where(
                        and(
                                CLIENT_USERTABLE.CLIENT_ID.eq(t.getClientId()),
                                CLIENT_USERTABLE.USER_ACCOUNT.eq(t.getUserAccount()),
                                CLIENT_USERTABLE.USER_PHONE.eq(t.getUserPhone()),
                                CLIENT_USERTABLE.USER_POST.eq(t.getUserPost())));
            }
        });
    }
    @LogMethod("service")
    public Pager<ClientUser> queryPager(int start, int limit, ClientUser clientUser) {
        if (clientUser == null) {
            clientUser = new ClientUser();
        }
        return getDslTemplate().queryPager(start, limit, clientUser, false, new SelectGenerateCallback<ClientUser>() {

            public Select generate(ClientUser t) {
                return MysqlSelect.selectFrom(CLIENT_USERTABLE).where(
                        and(
                                CLIENT_USERTABLE.CLIENT_ID.eq(t.getClientId()),
                                CLIENT_USERTABLE.USER_ACCOUNT.eq(t.getUserAccount()),
                                CLIENT_USERTABLE.USER_PHONE.eq(t.getUserPhone()),
                                CLIENT_USERTABLE.USER_POST.eq(t.getUserPost())));
            }
        });
    }
    @LogMethod("service")
    public int[] batchInsert(boolean autoGeneratedKeys, List<ClientUser> clientUsers) {
        if (CollectionUtil.isEmpty(clientUsers)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, clientUsers, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(CLIENT_USERTABLE).values(
                        CLIENT_USERTABLE.CLIENT_ID.value(new JdbcNamedParameter("clientId")),
                        CLIENT_USERTABLE.USER_ACCOUNT.value(new JdbcNamedParameter("userAccount")),
                        CLIENT_USERTABLE.USER_PHONE.value(new JdbcNamedParameter("userPhone")),
                        CLIENT_USERTABLE.USER_POST.value(new JdbcNamedParameter("userPost")));
            }
        });
    }
    @LogMethod("service")
    public int[] batchInsert(List<ClientUser> clientUsers) {
        return batchInsert(true, clientUsers);
    }
    @LogMethod("service")
    public int[] batchUpdate(List<ClientUser> clientUsers) {
        if (CollectionUtil.isEmpty(clientUsers)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(clientUsers, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(CLIENT_USERTABLE).set(
                        CLIENT_USERTABLE.CLIENT_ID.value(new JdbcNamedParameter("clientId")),
                        CLIENT_USERTABLE.USER_ACCOUNT.value(new JdbcNamedParameter("userAccount")),
                        CLIENT_USERTABLE.USER_PHONE.value(new JdbcNamedParameter("userPhone")),
                        CLIENT_USERTABLE.USER_POST.value(new JdbcNamedParameter("userPost"))).where(
                        CLIENT_USERTABLE.ID.eq(new JdbcNamedParameter("id")));
            }
        });
    }
    @LogMethod("service")
    public int[] batchDelete(List<ClientUser> clientUsers) {
        if (CollectionUtil.isEmpty(clientUsers)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(clientUsers, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(CLIENT_USERTABLE).where(and(
                        CLIENT_USERTABLE.ID.eq(new JdbcNamedParameter("id")),
                        CLIENT_USERTABLE.CLIENT_ID.eq(new JdbcNamedParameter("clientId")),
                        CLIENT_USERTABLE.USER_ACCOUNT.eq(new JdbcNamedParameter("userAccount")),
                        CLIENT_USERTABLE.USER_PHONE.eq(new JdbcNamedParameter("userPhone")),
                        CLIENT_USERTABLE.USER_POST.eq(new JdbcNamedParameter("userPost"))));
            }
        });
    }

}
