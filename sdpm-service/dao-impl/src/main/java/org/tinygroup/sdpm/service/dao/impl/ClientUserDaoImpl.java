/**
 *  Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.tinygroup.sdpm.service.dao.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.service.dao.constant.ClientUserTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.List;

import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
	import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.sdpm.service.dao.pojo.ClientUser;
import org.tinygroup.sdpm.service.dao.ClientUserDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class ClientUserDaoImpl extends TinyDslDaoSupport implements ClientUserDao {

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

	public int edit(ClientUser clientUser) {
		if(clientUser == null || clientUser.getId() == null){
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

	public int deleteByKey(Integer pk){
		if(pk == null){
			return 0;
		}
		return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
			public Delete generate(Serializable pk) {
				return delete(CLIENT_USERTABLE).where(CLIENT_USERTABLE.ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(CLIENT_USERTABLE).where(CLIENT_USERTABLE.ID.in(t));
		}
		},pks);
	}

	public ClientUser getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, ClientUser.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(CLIENT_USERTABLE).where(CLIENT_USERTABLE.ID.eq(t));
			}
		});
	}

	public List<ClientUser> query(ClientUser clientUser ,final OrderBy... orderBies) {
		if(clientUser==null){
			clientUser=new ClientUser();
		}
		return getDslTemplate().query(clientUser, new SelectGenerateCallback<ClientUser>() {

			@SuppressWarnings("rawtypes")
			public Select generate(ClientUser t) {
				Select select = selectFrom(CLIENT_USERTABLE).where(
				and(
					CLIENT_USERTABLE.CLIENT_ID.eq(t.getClientId()),
					CLIENT_USERTABLE.USER_ACCOUNT.eq(t.getUserAccount()),
					CLIENT_USERTABLE.USER_PHONE.eq(t.getUserPhone()),
					CLIENT_USERTABLE.USER_POST.eq(t.getUserPost())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<ClientUser> queryPager(int start,int limit ,ClientUser clientUser ,final OrderBy... orderBies) {
		if(clientUser==null){
			clientUser=new ClientUser();
		}
		return getDslTemplate().queryPager(start, limit, clientUser, false, new SelectGenerateCallback<ClientUser>() {

			public Select generate(ClientUser t) {
				Select select = MysqlSelect.selectFrom(CLIENT_USERTABLE).where(
				and(
					CLIENT_USERTABLE.CLIENT_ID.eq(t.getClientId()),
					CLIENT_USERTABLE.USER_ACCOUNT.eq(t.getUserAccount()),
					CLIENT_USERTABLE.USER_PHONE.eq(t.getUserPhone()),
					CLIENT_USERTABLE.USER_POST.eq(t.getUserPost())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<ClientUser> clientUsers) {
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

	public int[] batchInsert(List<ClientUser> clientUsers){
			return batchInsert(true ,clientUsers);
	}

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

	private  Select addOrderByElements(Select select ,OrderBy... orderBies){
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
