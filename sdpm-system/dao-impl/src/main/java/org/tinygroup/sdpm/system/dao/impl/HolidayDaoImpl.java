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

package org.tinygroup.sdpm.system.dao.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.system.dao.constant.HolidayTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.sdpm.system.dao.pojo.Holiday;
import org.tinygroup.sdpm.system.dao.HolidayDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;
@Repository
public class HolidayDaoImpl extends TinyDslDaoSupport implements HolidayDao {

	public Holiday add(Holiday holiday) {
		return getDslTemplate().insertAndReturnKey(holiday, new InsertGenerateCallback<Holiday>() {
			public Insert generate(Holiday t) {
				Insert insert = insertInto(HOLIDAYTABLE).values(
					HOLIDAYTABLE.HOLIDAY_ID.value(t.getHolidayId()),
					HOLIDAYTABLE.HOLIDAY_NAME.value(t.getHolidayName()),
					HOLIDAYTABLE.HOLIDAY_ACCOUNT.value(t.getHolidayAccount()),
					HOLIDAYTABLE.HOLIDAY_DATE.value(t.getHolidayDate()),
					HOLIDAYTABLE.HOLIDAY_TYPE.value(t.getHolidayType()),
					HOLIDAYTABLE.HOLIDAY_DELETED.value(t.getHolidayDeleted()),
					HOLIDAYTABLE.COMPANY_ID.value(t.getCompanyId()),
					HOLIDAYTABLE.HOLIDAY_DETAIL.value(t.getHolidayDetail()),
					HOLIDAYTABLE.HOILIDAY_REMARK.value(t.getHoilidayRemark()));
				return insert;
			}
		});
	}

	public int edit(Holiday holiday) {
		if(holiday == null || holiday.getHolidayId() == null){
			return 0;
		}
		return getDslTemplate().update(holiday, new UpdateGenerateCallback<Holiday>() {
			public Update generate(Holiday t) {
				Update update = update(HOLIDAYTABLE).set(
					HOLIDAYTABLE.HOLIDAY_NAME.value(t.getHolidayName()),
					HOLIDAYTABLE.HOLIDAY_ACCOUNT.value(t.getHolidayAccount()),
					HOLIDAYTABLE.HOLIDAY_DATE.value(t.getHolidayDate()),
					HOLIDAYTABLE.HOLIDAY_TYPE.value(t.getHolidayType()),
					HOLIDAYTABLE.HOLIDAY_DELETED.value(t.getHolidayDeleted()),
					HOLIDAYTABLE.COMPANY_ID.value(t.getCompanyId()),
					HOLIDAYTABLE.HOLIDAY_DETAIL.value(t.getHolidayDetail()),
					HOLIDAYTABLE.HOILIDAY_REMARK.value(t.getHoilidayRemark())).where(
					HOLIDAYTABLE.HOLIDAY_ID.eq(t.getHolidayId()));
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
				return delete(HOLIDAYTABLE).where(HOLIDAYTABLE.HOLIDAY_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(HOLIDAYTABLE).where(HOLIDAYTABLE.HOLIDAY_ID.in(t));
		}
		},pks);
	}

	public Holiday getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Holiday.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(HOLIDAYTABLE).where(HOLIDAYTABLE.HOLIDAY_ID.eq(t));
			}
		});
	}

	public List<Holiday> query(Holiday holiday ,final OrderBy... orderArgs) {
		if(holiday==null){
			holiday=new Holiday();
		}
		return getDslTemplate().query(holiday, new SelectGenerateCallback<Holiday>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Holiday t) {
				Select select = selectFrom(HOLIDAYTABLE).where(
				and(
					HOLIDAYTABLE.HOLIDAY_NAME.eq(t.getHolidayName()),
					HOLIDAYTABLE.HOLIDAY_ACCOUNT.eq(t.getHolidayAccount()),
					HOLIDAYTABLE.HOLIDAY_DATE.eq(t.getHolidayDate()),
					HOLIDAYTABLE.HOLIDAY_TYPE.eq(t.getHolidayType()),
					HOLIDAYTABLE.HOLIDAY_DELETED.eq(t.getHolidayDeleted()),
					HOLIDAYTABLE.COMPANY_ID.eq(t.getCompanyId()),
					HOLIDAYTABLE.HOLIDAY_DETAIL.eq(t.getHolidayDetail()),
					HOLIDAYTABLE.HOILIDAY_REMARK.eq(t.getHoilidayRemark())));
			return addOrderByElements(select, orderArgs);
			}
		});
	}

	public Pager<Holiday> queryPager(int start,int limit ,Holiday holiday ,final OrderBy... orderArgs) {
		if(holiday==null){
			holiday=new Holiday();
		}
		return getDslTemplate().queryPager(start, limit, holiday, false, new SelectGenerateCallback<Holiday>() {

			public Select generate(Holiday t) {
				Select select = MysqlSelect.selectFrom(HOLIDAYTABLE).where(
				and(
					HOLIDAYTABLE.HOLIDAY_NAME.eq(t.getHolidayName()),
					HOLIDAYTABLE.HOLIDAY_ACCOUNT.eq(t.getHolidayAccount()),
					HOLIDAYTABLE.HOLIDAY_DATE.eq(t.getHolidayDate()),
					HOLIDAYTABLE.HOLIDAY_TYPE.eq(t.getHolidayType()),
					HOLIDAYTABLE.HOLIDAY_DELETED.eq(t.getHolidayDeleted()),
					HOLIDAYTABLE.COMPANY_ID.eq(t.getCompanyId()),
					HOLIDAYTABLE.HOLIDAY_DETAIL.eq(t.getHolidayDetail()),
					HOLIDAYTABLE.HOILIDAY_REMARK.eq(t.getHoilidayRemark())));
			return addOrderByElements(select, orderArgs);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Holiday> holidays) {
		if (CollectionUtil.isEmpty(holidays)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, holidays, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(HOLIDAYTABLE).values(
					HOLIDAYTABLE.HOLIDAY_NAME.value(new JdbcNamedParameter("holidayName")),
					HOLIDAYTABLE.HOLIDAY_ACCOUNT.value(new JdbcNamedParameter("holidayAccount")),
					HOLIDAYTABLE.HOLIDAY_DATE.value(new JdbcNamedParameter("holidayDate")),
					HOLIDAYTABLE.HOLIDAY_TYPE.value(new JdbcNamedParameter("holidayType")),
					HOLIDAYTABLE.HOLIDAY_DELETED.value(new JdbcNamedParameter("holidayDeleted")),
					HOLIDAYTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
					HOLIDAYTABLE.HOLIDAY_DETAIL.value(new JdbcNamedParameter("holidayDetail")),
					HOLIDAYTABLE.HOILIDAY_REMARK.value(new JdbcNamedParameter("hoilidayRemark")));
			}
		});
	}

	public int[] batchInsert(List<Holiday> holidays){
			return batchInsert(true ,holidays);
	}

	public int[] batchUpdate(List<Holiday> holidays) {
		if (CollectionUtil.isEmpty(holidays)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(holidays, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(HOLIDAYTABLE).set(
					HOLIDAYTABLE.HOLIDAY_NAME.value(new JdbcNamedParameter("holidayName")),
					HOLIDAYTABLE.HOLIDAY_ACCOUNT.value(new JdbcNamedParameter("holidayAccount")),
					HOLIDAYTABLE.HOLIDAY_DATE.value(new JdbcNamedParameter("holidayDate")),
					HOLIDAYTABLE.HOLIDAY_TYPE.value(new JdbcNamedParameter("holidayType")),
					HOLIDAYTABLE.HOLIDAY_DELETED.value(new JdbcNamedParameter("holidayDeleted")),
					HOLIDAYTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
					HOLIDAYTABLE.HOLIDAY_DETAIL.value(new JdbcNamedParameter("holidayDetail")),
					HOLIDAYTABLE.HOILIDAY_REMARK.value(new JdbcNamedParameter("hoilidayRemark"))).where(
				HOLIDAYTABLE.HOLIDAY_ID.eq(new JdbcNamedParameter("holidayId")));
			}
		});
	}

	public int[] batchDelete(List<Holiday> holidays) {
		if (CollectionUtil.isEmpty(holidays)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(holidays, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(HOLIDAYTABLE).where(and(
				HOLIDAYTABLE.HOLIDAY_ID.eq(new JdbcNamedParameter("holidayId")),
				HOLIDAYTABLE.HOLIDAY_NAME.eq(new JdbcNamedParameter("holidayName")),
				HOLIDAYTABLE.HOLIDAY_ACCOUNT.eq(new JdbcNamedParameter("holidayAccount")),
				HOLIDAYTABLE.HOLIDAY_DATE.eq(new JdbcNamedParameter("holidayDate")),
				HOLIDAYTABLE.HOLIDAY_TYPE.eq(new JdbcNamedParameter("holidayType")),
				HOLIDAYTABLE.HOLIDAY_DELETED.eq(new JdbcNamedParameter("holidayDeleted")),
				HOLIDAYTABLE.COMPANY_ID.eq(new JdbcNamedParameter("companyId")),
				HOLIDAYTABLE.HOLIDAY_DETAIL.eq(new JdbcNamedParameter("holidayDetail")),
				HOLIDAYTABLE.HOILIDAY_REMARK.eq(new JdbcNamedParameter("hoilidayRemark"))));
			}
		});
	}

	private  Select addOrderByElements(Select select ,OrderBy... orderBies){
		List<OrderByElement> orderByElements = new ArrayList<OrderByElement>();
		for (int i = 0; orderBies != null && i < orderBies.length; i++) {
			OrderByElement tempElement = null;
			if(orderBies[i] != null){
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
	public int softDelete(Holiday holiday) {
		// TODO Auto-generated method stub
		if(holiday == null || holiday.getHolidayId() == null){
			return 0;
		}
		return getDslTemplate().update(holiday, new UpdateGenerateCallback<Holiday>() {
			public Update generate(Holiday t) {
				Update update = update(HOLIDAYTABLE).set(
					
					HOLIDAYTABLE.HOLIDAY_DELETED.value(t.getHolidayDeleted())
					).where(
					HOLIDAYTABLE.HOLIDAY_ID.eq(t.getHolidayId()));
				return update;
			}
		});
	}

}
