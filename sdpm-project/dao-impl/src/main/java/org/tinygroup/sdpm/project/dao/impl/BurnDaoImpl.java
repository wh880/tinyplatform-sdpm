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

package org.tinygroup.sdpm.project.dao.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.project.dao.constant.BurnTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;

import java.util.List;

import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.sdpm.project.dao.pojo.Burn;
import org.tinygroup.sdpm.project.dao.BurnDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class BurnDaoImpl extends TinyDslDaoSupport implements BurnDao {

	public Burn add(Burn burn) {
		return getDslTemplate().insertAndReturnKey(burn, new InsertGenerateCallback<Burn>() {
			public Insert generate(Burn t) {
				Insert insert = insertInto(BURNTABLE).values(
					BURNTABLE.PROJECT_ID.value(t.getProjectId()),
					BURNTABLE.BURN_DATE.value(t.getBurnDate()),
					BURNTABLE.BURN_LEFT.value(t.getBurnLeft()),
					BURNTABLE.BURN_CONSUMED.value(t.getBurnConsumed()));
				return insert;
			}
		});
	}

	public int edit(Burn burn) {
		if(burn == null || burn.getProjectId() == null){
			return 0;
		}
		return getDslTemplate().update(burn, new UpdateGenerateCallback<Burn>() {
			public Update generate(Burn t) {
				Update update = update(BURNTABLE).set(
					BURNTABLE.BURN_DATE.value(t.getBurnDate()),
					BURNTABLE.BURN_LEFT.value(t.getBurnLeft()),
					BURNTABLE.BURN_CONSUMED.value(t.getBurnConsumed())).where(
					BURNTABLE.PROJECT_ID.eq(t.getProjectId()));
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
				return delete(BURNTABLE).where(BURNTABLE.PROJECT_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(BURNTABLE).where(BURNTABLE.PROJECT_ID.in(t));
		}
		},pks);
	}

	public Burn getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Burn.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(BURNTABLE).where(BURNTABLE.PROJECT_ID.eq(t));
			}
		});
	}

	public List<Burn> query(Burn burn) {
		if(burn==null){
			burn=new Burn();
		}
		return getDslTemplate().query(burn, new SelectGenerateCallback<Burn>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Burn t) {
				return selectFrom(BURNTABLE).where(
				and(
					BURNTABLE.BURN_DATE.eq(t.getBurnDate()),
					BURNTABLE.BURN_LEFT.eq(t.getBurnLeft()),
					BURNTABLE.BURN_CONSUMED.eq(t.getBurnConsumed())));
			}
		});
	}

	public Pager<Burn> queryPager(int start,int limit ,Burn burn) {
		if(burn==null){
			burn=new Burn();
		}
		return getDslTemplate().queryPager(start, limit, burn, false, new SelectGenerateCallback<Burn>() {

			public Select generate(Burn t) {
				return MysqlSelect.selectFrom(BURNTABLE).where(
				and(
					BURNTABLE.BURN_DATE.eq(t.getBurnDate()),
					BURNTABLE.BURN_LEFT.eq(t.getBurnLeft()),
					BURNTABLE.BURN_CONSUMED.eq(t.getBurnConsumed())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Burn> burns) {
		if (CollectionUtil.isEmpty(burns)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, burns, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(BURNTABLE).values(
					BURNTABLE.BURN_DATE.value(new JdbcNamedParameter("burnDate")),
					BURNTABLE.BURN_LEFT.value(new JdbcNamedParameter("burnLeft")),
					BURNTABLE.BURN_CONSUMED.value(new JdbcNamedParameter("burnConsumed")));
			}
		});
	}

	public int[] batchInsert(List<Burn> burns){
			return batchInsert(true ,burns);
	}

	public int[] batchUpdate(List<Burn> burns) {
		if (CollectionUtil.isEmpty(burns)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(burns, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(BURNTABLE).set(
					BURNTABLE.BURN_DATE.value(new JdbcNamedParameter("burnDate")),
					BURNTABLE.BURN_LEFT.value(new JdbcNamedParameter("burnLeft")),
					BURNTABLE.BURN_CONSUMED.value(new JdbcNamedParameter("burnConsumed"))).where(
				BURNTABLE.PROJECT_ID.eq(new JdbcNamedParameter("projectId")));
			}
		});
	}

	public int[] batchDelete(List<Burn> burns) {
		if (CollectionUtil.isEmpty(burns)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(burns, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(BURNTABLE).where(and(
				BURNTABLE.PROJECT_ID.eq(new JdbcNamedParameter("projectId")),
				BURNTABLE.BURN_DATE.eq(new JdbcNamedParameter("burnDate")),
				BURNTABLE.BURN_LEFT.eq(new JdbcNamedParameter("burnLeft")),
				BURNTABLE.BURN_CONSUMED.eq(new JdbcNamedParameter("burnConsumed"))));
			}
		});
	}

}
