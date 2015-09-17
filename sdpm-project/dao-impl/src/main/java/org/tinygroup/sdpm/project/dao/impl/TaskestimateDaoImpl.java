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
import static org.tinygroup.sdpm.project.dao.constant.TaskestimateTable.*;
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
import org.tinygroup.sdpm.project.dao.pojo.Taskestimate;
import org.tinygroup.sdpm.project.dao.TaskestimateDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class TaskestimateDaoImpl extends TinyDslDaoSupport implements TaskestimateDao {

	public Taskestimate add(Taskestimate taskestimate) {
		return getDslTemplate().insertAndReturnKey(taskestimate, new InsertGenerateCallback<Taskestimate>() {
			public Insert generate(Taskestimate t) {
				Insert insert = insertInto(TASKESTIMATETABLE).values(
					TASKESTIMATETABLE.TASKESTIMATE_ID.value(t.getTaskestimateId()),
					TASKESTIMATETABLE.TASK_ID.value(t.getTaskId()),
					TASKESTIMATETABLE.TASKESTIMATE_DATE.value(t.getTaskestimateDate()),
					TASKESTIMATETABLE.TASKESTIMATE_LEFT.value(t.getTaskestimateLeft()),
					TASKESTIMATETABLE.TASKESTIMATE_CONSUMED.value(t.getTaskestimateConsumed()),
					TASKESTIMATETABLE.TASKESTIMATE_ACCOUNT.value(t.getTaskestimateAccount()),
					TASKESTIMATETABLE.TASKESTIMATE_WORK.value(t.getTaskestimateWork()));
				return insert;
			}
		});
	}

	public int edit(Taskestimate taskestimate) {
		if(taskestimate == null || taskestimate.getTaskestimateId() == null){
			return 0;
		}
		return getDslTemplate().update(taskestimate, new UpdateGenerateCallback<Taskestimate>() {
			public Update generate(Taskestimate t) {
				Update update = update(TASKESTIMATETABLE).set(
					TASKESTIMATETABLE.TASK_ID.value(t.getTaskId()),
					TASKESTIMATETABLE.TASKESTIMATE_DATE.value(t.getTaskestimateDate()),
					TASKESTIMATETABLE.TASKESTIMATE_LEFT.value(t.getTaskestimateLeft()),
					TASKESTIMATETABLE.TASKESTIMATE_CONSUMED.value(t.getTaskestimateConsumed()),
					TASKESTIMATETABLE.TASKESTIMATE_ACCOUNT.value(t.getTaskestimateAccount()),
					TASKESTIMATETABLE.TASKESTIMATE_WORK.value(t.getTaskestimateWork())).where(
					TASKESTIMATETABLE.TASKESTIMATE_ID.eq(t.getTaskestimateId()));
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
				return delete(TASKESTIMATETABLE).where(TASKESTIMATETABLE.TASKESTIMATE_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(TASKESTIMATETABLE).where(TASKESTIMATETABLE.TASKESTIMATE_ID.in(t));
		}
		},pks);
	}

	public Taskestimate getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Taskestimate.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(TASKESTIMATETABLE).where(TASKESTIMATETABLE.TASKESTIMATE_ID.eq(t));
			}
		});
	}

	public List<Taskestimate> query(Taskestimate taskestimate) {
		if(taskestimate==null){
			taskestimate=new Taskestimate();
		}
		return getDslTemplate().query(taskestimate, new SelectGenerateCallback<Taskestimate>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Taskestimate t) {
				return selectFrom(TASKESTIMATETABLE).where(
				and(
					TASKESTIMATETABLE.TASK_ID.eq(t.getTaskId()),
					TASKESTIMATETABLE.TASKESTIMATE_DATE.eq(t.getTaskestimateDate()),
					TASKESTIMATETABLE.TASKESTIMATE_LEFT.eq(t.getTaskestimateLeft()),
					TASKESTIMATETABLE.TASKESTIMATE_CONSUMED.eq(t.getTaskestimateConsumed()),
					TASKESTIMATETABLE.TASKESTIMATE_ACCOUNT.eq(t.getTaskestimateAccount()),
					TASKESTIMATETABLE.TASKESTIMATE_WORK.eq(t.getTaskestimateWork())));
			}
		});
	}

	public Pager<Taskestimate> queryPager(int start,int limit ,Taskestimate taskestimate) {
		if(taskestimate==null){
			taskestimate=new Taskestimate();
		}
		return getDslTemplate().queryPager(start, limit, taskestimate, false, new SelectGenerateCallback<Taskestimate>() {

			public Select generate(Taskestimate t) {
				return MysqlSelect.selectFrom(TASKESTIMATETABLE).where(
				and(
					TASKESTIMATETABLE.TASK_ID.eq(t.getTaskId()),
					TASKESTIMATETABLE.TASKESTIMATE_DATE.eq(t.getTaskestimateDate()),
					TASKESTIMATETABLE.TASKESTIMATE_LEFT.eq(t.getTaskestimateLeft()),
					TASKESTIMATETABLE.TASKESTIMATE_CONSUMED.eq(t.getTaskestimateConsumed()),
					TASKESTIMATETABLE.TASKESTIMATE_ACCOUNT.eq(t.getTaskestimateAccount()),
					TASKESTIMATETABLE.TASKESTIMATE_WORK.eq(t.getTaskestimateWork())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Taskestimate> taskestimates) {
		if (CollectionUtil.isEmpty(taskestimates)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, taskestimates, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(TASKESTIMATETABLE).values(
					TASKESTIMATETABLE.TASK_ID.value(new JdbcNamedParameter("taskId")),
					TASKESTIMATETABLE.TASKESTIMATE_DATE.value(new JdbcNamedParameter("taskestimateDate")),
					TASKESTIMATETABLE.TASKESTIMATE_LEFT.value(new JdbcNamedParameter("taskestimateLeft")),
					TASKESTIMATETABLE.TASKESTIMATE_CONSUMED.value(new JdbcNamedParameter("taskestimateConsumed")),
					TASKESTIMATETABLE.TASKESTIMATE_ACCOUNT.value(new JdbcNamedParameter("taskestimateAccount")),
					TASKESTIMATETABLE.TASKESTIMATE_WORK.value(new JdbcNamedParameter("taskestimateWork")));
			}
		});
	}

	public int[] batchInsert(List<Taskestimate> taskestimates){
			return batchInsert(true ,taskestimates);
	}

	public int[] batchUpdate(List<Taskestimate> taskestimates) {
		if (CollectionUtil.isEmpty(taskestimates)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(taskestimates, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(TASKESTIMATETABLE).set(
					TASKESTIMATETABLE.TASK_ID.value(new JdbcNamedParameter("taskId")),
					TASKESTIMATETABLE.TASKESTIMATE_DATE.value(new JdbcNamedParameter("taskestimateDate")),
					TASKESTIMATETABLE.TASKESTIMATE_LEFT.value(new JdbcNamedParameter("taskestimateLeft")),
					TASKESTIMATETABLE.TASKESTIMATE_CONSUMED.value(new JdbcNamedParameter("taskestimateConsumed")),
					TASKESTIMATETABLE.TASKESTIMATE_ACCOUNT.value(new JdbcNamedParameter("taskestimateAccount")),
					TASKESTIMATETABLE.TASKESTIMATE_WORK.value(new JdbcNamedParameter("taskestimateWork"))).where(
				TASKESTIMATETABLE.TASKESTIMATE_ID.eq(new JdbcNamedParameter("taskestimateId")));
			}
		});
	}

	public int[] batchDelete(List<Taskestimate> taskestimates) {
		if (CollectionUtil.isEmpty(taskestimates)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(taskestimates, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(TASKESTIMATETABLE).where(and(
				TASKESTIMATETABLE.TASKESTIMATE_ID.eq(new JdbcNamedParameter("taskestimateId")),
				TASKESTIMATETABLE.TASK_ID.eq(new JdbcNamedParameter("taskId")),
				TASKESTIMATETABLE.TASKESTIMATE_DATE.eq(new JdbcNamedParameter("taskestimateDate")),
				TASKESTIMATETABLE.TASKESTIMATE_LEFT.eq(new JdbcNamedParameter("taskestimateLeft")),
				TASKESTIMATETABLE.TASKESTIMATE_CONSUMED.eq(new JdbcNamedParameter("taskestimateConsumed")),
				TASKESTIMATETABLE.TASKESTIMATE_ACCOUNT.eq(new JdbcNamedParameter("taskestimateAccount")),
				TASKESTIMATETABLE.TASKESTIMATE_WORK.eq(new JdbcNamedParameter("taskestimateWork"))));
			}
		});
	}

}
