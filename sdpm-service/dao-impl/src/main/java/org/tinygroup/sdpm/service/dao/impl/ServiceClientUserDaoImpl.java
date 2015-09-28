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

import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.service.dao.ServiceClientUserDao;
import org.tinygroup.sdpm.service.dao.pojo.ServiceClientUser;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.service.dao.constant.ServiceClientUserTable.SERVICE_CLIENT_USERTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
@Repository
public class ServiceClientUserDaoImpl extends TinyDslDaoSupport implements ServiceClientUserDao {

	public ServiceClientUser add(ServiceClientUser serviceClientUser) {
		return getDslTemplate().insertAndReturnKey(serviceClientUser, new InsertGenerateCallback<ServiceClientUser>() {
			public Insert generate(ServiceClientUser t) {
				Insert insert = insertInto(SERVICE_CLIENT_USERTABLE).values(
						SERVICE_CLIENT_USERTABLE.ID.value(t.getId()),
						SERVICE_CLIENT_USERTABLE.CLIENT_ID.value(t.getClientId()),
						SERVICE_CLIENT_USERTABLE.USER_ACCOUNT.value(t.getUserAccount()),
						SERVICE_CLIENT_USERTABLE.USER_PHONE.value(t.getUserPhone()),
						SERVICE_CLIENT_USERTABLE.USER_POST.value(t.getUserPost()),
						SERVICE_CLIENT_USERTABLE.DELETED.value(t.getDeleted()));
				return insert;
			}
		});
	}

	public int edit(ServiceClientUser serviceClientUser) {
		if (serviceClientUser == null || serviceClientUser.getId() == null) {
			return 0;
		}
		return getDslTemplate().update(serviceClientUser, new UpdateGenerateCallback<ServiceClientUser>() {
			public Update generate(ServiceClientUser t) {
				Update update = update(SERVICE_CLIENT_USERTABLE).set(
						SERVICE_CLIENT_USERTABLE.CLIENT_ID.value(t.getClientId()),
						SERVICE_CLIENT_USERTABLE.USER_ACCOUNT.value(t.getUserAccount()),
						SERVICE_CLIENT_USERTABLE.USER_PHONE.value(t.getUserPhone()),
						SERVICE_CLIENT_USERTABLE.USER_POST.value(t.getUserPost()),
						SERVICE_CLIENT_USERTABLE.DELETED.value(t.getDeleted())).where(
						SERVICE_CLIENT_USERTABLE.ID.eq(t.getId()));
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
				return delete(SERVICE_CLIENT_USERTABLE).where(SERVICE_CLIENT_USERTABLE.ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if (pks == null || pks.length == 0) {
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(SERVICE_CLIENT_USERTABLE).where(SERVICE_CLIENT_USERTABLE.ID.in(t));
			}
		}, pks);
	}

	public ServiceClientUser getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, ServiceClientUser.class, new SelectGenerateCallback<Serializable>() {
			@SuppressWarnings("rawtypes")
			public Select generate(Serializable t) {
				return selectFrom(SERVICE_CLIENT_USERTABLE).where(SERVICE_CLIENT_USERTABLE.ID.eq(t));
			}
		});
	}

	public List<ServiceClientUser> query(ServiceClientUser serviceClientUser, final OrderBy... orderBies) {
		if (serviceClientUser == null) {
			serviceClientUser = new ServiceClientUser();
		}
		return getDslTemplate().query(serviceClientUser, new SelectGenerateCallback<ServiceClientUser>() {

			@SuppressWarnings("rawtypes")
			public Select generate(ServiceClientUser t) {
				Select select = selectFrom(SERVICE_CLIENT_USERTABLE).where(
//				and(
						SERVICE_CLIENT_USERTABLE.CLIENT_ID.eq(t.getClientId()));
					/*SERVICE_CLIENT_USERTABLE.USER_ACCOUNT.eq(t.getUserAccount()),
					SERVICE_CLIENT_USERTABLE.USER_PHONE.eq(t.getUserPhone()),
					SERVICE_CLIENT_USERTABLE.USER_POST.eq(t.getUserPost()),
					SERVICE_CLIENT_USERTABLE.DELETED.eq(t.getDeleted())*/
				return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<ServiceClientUser> queryPager(int start, int limit, ServiceClientUser serviceClientUser, final OrderBy... orderBies) {
		if (serviceClientUser == null) {
			serviceClientUser = new ServiceClientUser();
		}
		return getDslTemplate().queryPager(start, limit, serviceClientUser, false, new SelectGenerateCallback<ServiceClientUser>() {

			public Select generate(ServiceClientUser t) {
				Select select = MysqlSelect.selectFrom(SERVICE_CLIENT_USERTABLE).where(
						and(
								SERVICE_CLIENT_USERTABLE.CLIENT_ID.eq(t.getClientId()),
								SERVICE_CLIENT_USERTABLE.USER_ACCOUNT.eq(t.getUserAccount()),
								SERVICE_CLIENT_USERTABLE.USER_PHONE.eq(t.getUserPhone()),
								SERVICE_CLIENT_USERTABLE.USER_POST.eq(t.getUserPost()),
								SERVICE_CLIENT_USERTABLE.DELETED.eq(t.getDeleted())));
				return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys, List<ServiceClientUser> serviceClientUsers) {
		if (CollectionUtil.isEmpty(serviceClientUsers)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, serviceClientUsers, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(SERVICE_CLIENT_USERTABLE).values(
						SERVICE_CLIENT_USERTABLE.CLIENT_ID.value(new JdbcNamedParameter("clientId")),
						SERVICE_CLIENT_USERTABLE.USER_ACCOUNT.value(new JdbcNamedParameter("userAccount")),
						SERVICE_CLIENT_USERTABLE.USER_PHONE.value(new JdbcNamedParameter("userPhone")),
						SERVICE_CLIENT_USERTABLE.USER_POST.value(new JdbcNamedParameter("userPost")),
						SERVICE_CLIENT_USERTABLE.DELETED.value(new JdbcNamedParameter("deleted")));
			}
		});
	}

	public int[] batchInsert(List<ServiceClientUser> serviceClientUsers) {
		return batchInsert(true, serviceClientUsers);
	}

	public int[] batchUpdate(List<ServiceClientUser> serviceClientUsers) {
		if (CollectionUtil.isEmpty(serviceClientUsers)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(serviceClientUsers, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(SERVICE_CLIENT_USERTABLE).set(
						SERVICE_CLIENT_USERTABLE.CLIENT_ID.value(new JdbcNamedParameter("clientId")),
						SERVICE_CLIENT_USERTABLE.USER_ACCOUNT.value(new JdbcNamedParameter("userAccount")),
						SERVICE_CLIENT_USERTABLE.USER_PHONE.value(new JdbcNamedParameter("userPhone")),
						SERVICE_CLIENT_USERTABLE.USER_POST.value(new JdbcNamedParameter("userPost")),
						SERVICE_CLIENT_USERTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
						SERVICE_CLIENT_USERTABLE.ID.eq(new JdbcNamedParameter("id")));
			}
		});
	}

	public int[] batchDelete(List<ServiceClientUser> serviceClientUsers) {
		if (CollectionUtil.isEmpty(serviceClientUsers)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(serviceClientUsers, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(SERVICE_CLIENT_USERTABLE).where(and(
						SERVICE_CLIENT_USERTABLE.ID.eq(new JdbcNamedParameter("id")),
						SERVICE_CLIENT_USERTABLE.CLIENT_ID.eq(new JdbcNamedParameter("clientId")),
						SERVICE_CLIENT_USERTABLE.USER_ACCOUNT.eq(new JdbcNamedParameter("userAccount")),
						SERVICE_CLIENT_USERTABLE.USER_PHONE.eq(new JdbcNamedParameter("userPhone")),
						SERVICE_CLIENT_USERTABLE.USER_POST.eq(new JdbcNamedParameter("userPost")),
						SERVICE_CLIENT_USERTABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
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
