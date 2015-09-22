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

import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.common.log.annotation.LogClass;
import org.tinygroup.sdpm.common.log.annotation.LogMethod;
import org.tinygroup.sdpm.project.dao.ProjectTaskestimateDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTaskestimate;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.project.dao.constant.ProjectTaskestimateTable.PROJECT_TASKESTIMATETABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
@LogClass("project")
public class ProjectTaskestimateDaoImpl extends TinyDslDaoSupport implements ProjectTaskestimateDao {
	@LogMethod("add")
	public ProjectTaskestimate add(ProjectTaskestimate projectTaskestimate) {
		return getDslTemplate().insertAndReturnKey(projectTaskestimate, new InsertGenerateCallback<ProjectTaskestimate>() {
			public Insert generate(ProjectTaskestimate t) {
				Insert insert = insertInto(PROJECT_TASKESTIMATETABLE).values(
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_ID.value(t.getTaskestimateId()),
						PROJECT_TASKESTIMATETABLE.TASK_ID.value(t.getTaskId()),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_DATE.value(t.getTaskestimateDate()),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_LEFT.value(t.getTaskestimateLeft()),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_CONSUMED.value(t.getTaskestimateConsumed()),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_ACCOUNT.value(t.getTaskestimateAccount()),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_WORK.value(t.getTaskestimateWork()));
				return insert;
			}
		});
	}
	@LogMethod("edit")
	public int edit(ProjectTaskestimate projectTaskestimate) {
		if (projectTaskestimate == null || projectTaskestimate.getTaskestimateId() == null) {
			return 0;
		}
		return getDslTemplate().update(projectTaskestimate, new UpdateGenerateCallback<ProjectTaskestimate>() {
			public Update generate(ProjectTaskestimate t) {
				Update update = update(PROJECT_TASKESTIMATETABLE).set(
						PROJECT_TASKESTIMATETABLE.TASK_ID.value(t.getTaskId()),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_DATE.value(t.getTaskestimateDate()),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_LEFT.value(t.getTaskestimateLeft()),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_CONSUMED.value(t.getTaskestimateConsumed()),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_ACCOUNT.value(t.getTaskestimateAccount()),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_WORK.value(t.getTaskestimateWork())).where(
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_ID.eq(t.getTaskestimateId()));
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
				return delete(PROJECT_TASKESTIMATETABLE).where(PROJECT_TASKESTIMATETABLE.TASKESTIMATE_ID.eq(pk));
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
				return delete(PROJECT_TASKESTIMATETABLE).where(PROJECT_TASKESTIMATETABLE.TASKESTIMATE_ID.in(t));
			}
		}, pks);
	}

	public ProjectTaskestimate getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, ProjectTaskestimate.class, new SelectGenerateCallback<Serializable>() {
			@SuppressWarnings("rawtypes")
			public Select generate(Serializable t) {
				return selectFrom(PROJECT_TASKESTIMATETABLE).where(PROJECT_TASKESTIMATETABLE.TASKESTIMATE_ID.eq(t));
			}
		});
	}

	public List<ProjectTaskestimate> query(ProjectTaskestimate projectTaskestimate, final OrderBy... orderBies) {
		if (projectTaskestimate == null) {
			projectTaskestimate = new ProjectTaskestimate();
		}
		return getDslTemplate().query(projectTaskestimate, new SelectGenerateCallback<ProjectTaskestimate>() {

			@SuppressWarnings("rawtypes")
			public Select generate(ProjectTaskestimate t) {
				Select select = selectFrom(PROJECT_TASKESTIMATETABLE).where(
						and(
								PROJECT_TASKESTIMATETABLE.TASK_ID.eq(t.getTaskId()),
								PROJECT_TASKESTIMATETABLE.TASKESTIMATE_DATE.eq(t.getTaskestimateDate()),
								PROJECT_TASKESTIMATETABLE.TASKESTIMATE_LEFT.eq(t.getTaskestimateLeft()),
								PROJECT_TASKESTIMATETABLE.TASKESTIMATE_CONSUMED.eq(t.getTaskestimateConsumed()),
								PROJECT_TASKESTIMATETABLE.TASKESTIMATE_ACCOUNT.eq(t.getTaskestimateAccount()),
								PROJECT_TASKESTIMATETABLE.TASKESTIMATE_WORK.eq(t.getTaskestimateWork())));
				return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<ProjectTaskestimate> queryPager(int start, int limit, ProjectTaskestimate projectTaskestimate, final OrderBy... orderBies) {
		if (projectTaskestimate == null) {
			projectTaskestimate = new ProjectTaskestimate();
		}
		return getDslTemplate().queryPager(start, limit, projectTaskestimate, false, new SelectGenerateCallback<ProjectTaskestimate>() {

			public Select generate(ProjectTaskestimate t) {
				Select select = MysqlSelect.selectFrom(PROJECT_TASKESTIMATETABLE).where(
						and(
								PROJECT_TASKESTIMATETABLE.TASK_ID.eq(t.getTaskId()),
								PROJECT_TASKESTIMATETABLE.TASKESTIMATE_DATE.eq(t.getTaskestimateDate()),
								PROJECT_TASKESTIMATETABLE.TASKESTIMATE_LEFT.eq(t.getTaskestimateLeft()),
								PROJECT_TASKESTIMATETABLE.TASKESTIMATE_CONSUMED.eq(t.getTaskestimateConsumed()),
								PROJECT_TASKESTIMATETABLE.TASKESTIMATE_ACCOUNT.eq(t.getTaskestimateAccount()),
								PROJECT_TASKESTIMATETABLE.TASKESTIMATE_WORK.eq(t.getTaskestimateWork())));
				return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys, List<ProjectTaskestimate> projectTaskestimates) {
		if (CollectionUtil.isEmpty(projectTaskestimates)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, projectTaskestimates, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(PROJECT_TASKESTIMATETABLE).values(
						PROJECT_TASKESTIMATETABLE.TASK_ID.value(new JdbcNamedParameter("taskId")),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_DATE.value(new JdbcNamedParameter("taskestimateDate")),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_LEFT.value(new JdbcNamedParameter("taskestimateLeft")),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_CONSUMED.value(new JdbcNamedParameter("taskestimateConsumed")),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_ACCOUNT.value(new JdbcNamedParameter("taskestimateAccount")),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_WORK.value(new JdbcNamedParameter("taskestimateWork")));
			}
		});
	}

	public int[] batchInsert(List<ProjectTaskestimate> projectTaskestimates) {
		return batchInsert(true, projectTaskestimates);
	}
	@LogMethod("batchUpdate")
	public int[] batchUpdate(List<ProjectTaskestimate> projectTaskestimates) {
		if (CollectionUtil.isEmpty(projectTaskestimates)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(projectTaskestimates, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(PROJECT_TASKESTIMATETABLE).set(
						PROJECT_TASKESTIMATETABLE.TASK_ID.value(new JdbcNamedParameter("taskId")),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_DATE.value(new JdbcNamedParameter("taskestimateDate")),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_LEFT.value(new JdbcNamedParameter("taskestimateLeft")),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_CONSUMED.value(new JdbcNamedParameter("taskestimateConsumed")),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_ACCOUNT.value(new JdbcNamedParameter("taskestimateAccount")),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_WORK.value(new JdbcNamedParameter("taskestimateWork"))).where(
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_ID.eq(new JdbcNamedParameter("taskestimateId")));
			}
		});
	}
	@LogMethod("batchDelete")
	public int[] batchDelete(List<ProjectTaskestimate> projectTaskestimates) {
		if (CollectionUtil.isEmpty(projectTaskestimates)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(projectTaskestimates, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(PROJECT_TASKESTIMATETABLE).where(and(
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_ID.eq(new JdbcNamedParameter("taskestimateId")),
						PROJECT_TASKESTIMATETABLE.TASK_ID.eq(new JdbcNamedParameter("taskId")),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_DATE.eq(new JdbcNamedParameter("taskestimateDate")),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_LEFT.eq(new JdbcNamedParameter("taskestimateLeft")),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_CONSUMED.eq(new JdbcNamedParameter("taskestimateConsumed")),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_ACCOUNT.eq(new JdbcNamedParameter("taskestimateAccount")),
						PROJECT_TASKESTIMATETABLE.TASKESTIMATE_WORK.eq(new JdbcNamedParameter("taskestimateWork"))));
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
