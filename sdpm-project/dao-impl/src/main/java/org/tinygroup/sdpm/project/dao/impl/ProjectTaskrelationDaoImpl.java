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

import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.common.log.annotation.LogClass;
import org.tinygroup.sdpm.common.log.annotation.LogMethod;
import org.tinygroup.sdpm.project.dao.ProjectTaskrelationDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTaskrelation;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.project.dao.constant.ProjectTaskrelationTable.PROJECT_TASKRELATIONTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
@LogClass("project")
@Repository
public class ProjectTaskrelationDaoImpl extends TinyDslDaoSupport implements ProjectTaskrelationDao {
	@LogMethod("add")
	public ProjectTaskrelation add(ProjectTaskrelation projectTaskrelation) {
		return getDslTemplate().insertAndReturnKey(projectTaskrelation, new InsertGenerateCallback<ProjectTaskrelation>() {
			public Insert generate(ProjectTaskrelation t) {
				Insert insert = insertInto(PROJECT_TASKRELATIONTABLE).values(
						PROJECT_TASKRELATIONTABLE.ID.value(t.getId()),
						PROJECT_TASKRELATIONTABLE.PER_TASK.value(t.getPerTask()),
						PROJECT_TASKRELATIONTABLE.TASKRELATION_CONDITON.value(t.getTaskrelationConditon()),
						PROJECT_TASKRELATIONTABLE.AFTER_TASK.value(t.getAfterTask()),
						PROJECT_TASKRELATIONTABLE.TASKRELATION_ACTION.value(t.getTaskrelationAction()));
				return insert;
			}
		});
	}
	@LogMethod("edit")
	public int edit(ProjectTaskrelation projectTaskrelation) {
		if (projectTaskrelation == null || projectTaskrelation.getId() == null) {
			return 0;
		}
		return getDslTemplate().update(projectTaskrelation, new UpdateGenerateCallback<ProjectTaskrelation>() {
			public Update generate(ProjectTaskrelation t) {
				Update update = update(PROJECT_TASKRELATIONTABLE).set(
						PROJECT_TASKRELATIONTABLE.PER_TASK.value(t.getPerTask()),
						PROJECT_TASKRELATIONTABLE.TASKRELATION_CONDITON.value(t.getTaskrelationConditon()),
						PROJECT_TASKRELATIONTABLE.AFTER_TASK.value(t.getAfterTask()),
						PROJECT_TASKRELATIONTABLE.TASKRELATION_ACTION.value(t.getTaskrelationAction())).where(
						PROJECT_TASKRELATIONTABLE.ID.eq(t.getId()));
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
				return delete(PROJECT_TASKRELATIONTABLE).where(PROJECT_TASKRELATIONTABLE.ID.eq(pk));
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
				return delete(PROJECT_TASKRELATIONTABLE).where(PROJECT_TASKRELATIONTABLE.ID.in(t));
			}
		}, pks);
	}

	public ProjectTaskrelation getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, ProjectTaskrelation.class, new SelectGenerateCallback<Serializable>() {
			@SuppressWarnings("rawtypes")
			public Select generate(Serializable t) {
				return selectFrom(PROJECT_TASKRELATIONTABLE).where(PROJECT_TASKRELATIONTABLE.ID.eq(t));
			}
		});
	}

	public List<ProjectTaskrelation> query(ProjectTaskrelation projectTaskrelation, final OrderBy... orderBies) {
		if (projectTaskrelation == null) {
			projectTaskrelation = new ProjectTaskrelation();
		}
		return getDslTemplate().query(projectTaskrelation, new SelectGenerateCallback<ProjectTaskrelation>() {

			@SuppressWarnings("rawtypes")
			public Select generate(ProjectTaskrelation t) {
				Select select = selectFrom(PROJECT_TASKRELATIONTABLE).where(
						and(
								PROJECT_TASKRELATIONTABLE.PER_TASK.eq(t.getPerTask()),
								PROJECT_TASKRELATIONTABLE.TASKRELATION_CONDITON.eq(t.getTaskrelationConditon()),
								PROJECT_TASKRELATIONTABLE.AFTER_TASK.eq(t.getAfterTask()),
								PROJECT_TASKRELATIONTABLE.TASKRELATION_ACTION.eq(t.getTaskrelationAction())));
				return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<ProjectTaskrelation> queryPager(int start, int limit, ProjectTaskrelation projectTaskrelation, final OrderBy... orderBies) {
		if (projectTaskrelation == null) {
			projectTaskrelation = new ProjectTaskrelation();
		}
		return getDslTemplate().queryPager(start, limit, projectTaskrelation, false, new SelectGenerateCallback<ProjectTaskrelation>() {

			public Select generate(ProjectTaskrelation t) {
				Select select = MysqlSelect.selectFrom(PROJECT_TASKRELATIONTABLE).where(
						and(
								PROJECT_TASKRELATIONTABLE.PER_TASK.eq(t.getPerTask()),
								PROJECT_TASKRELATIONTABLE.TASKRELATION_CONDITON.eq(t.getTaskrelationConditon()),
								PROJECT_TASKRELATIONTABLE.AFTER_TASK.eq(t.getAfterTask()),
								PROJECT_TASKRELATIONTABLE.TASKRELATION_ACTION.eq(t.getTaskrelationAction())));
				return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys, List<ProjectTaskrelation> projectTaskrelations) {
		if (CollectionUtil.isEmpty(projectTaskrelations)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, projectTaskrelations, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(PROJECT_TASKRELATIONTABLE).values(
						PROJECT_TASKRELATIONTABLE.PER_TASK.value(new JdbcNamedParameter("perTask")),
						PROJECT_TASKRELATIONTABLE.TASKRELATION_CONDITON.value(new JdbcNamedParameter("taskrelationConditon")),
						PROJECT_TASKRELATIONTABLE.AFTER_TASK.value(new JdbcNamedParameter("afterTask")),
						PROJECT_TASKRELATIONTABLE.TASKRELATION_ACTION.value(new JdbcNamedParameter("taskrelationAction")));
			}
		});
	}

	public int[] batchInsert(List<ProjectTaskrelation> projectTaskrelations) {
		return batchInsert(true, projectTaskrelations);
	}
	@LogMethod("batchUpdate")
	public int[] batchUpdate(List<ProjectTaskrelation> projectTaskrelations) {
		if (CollectionUtil.isEmpty(projectTaskrelations)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(projectTaskrelations, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(PROJECT_TASKRELATIONTABLE).set(
						PROJECT_TASKRELATIONTABLE.PER_TASK.value(new JdbcNamedParameter("perTask")),
						PROJECT_TASKRELATIONTABLE.TASKRELATION_CONDITON.value(new JdbcNamedParameter("taskrelationConditon")),
						PROJECT_TASKRELATIONTABLE.AFTER_TASK.value(new JdbcNamedParameter("afterTask")),
						PROJECT_TASKRELATIONTABLE.TASKRELATION_ACTION.value(new JdbcNamedParameter("taskrelationAction"))).where(
						PROJECT_TASKRELATIONTABLE.ID.eq(new JdbcNamedParameter("id")));
			}
		});
	}
	@LogMethod("batchDelete")
	public int[] batchDelete(List<ProjectTaskrelation> projectTaskrelations) {
		if (CollectionUtil.isEmpty(projectTaskrelations)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(projectTaskrelations, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(PROJECT_TASKRELATIONTABLE).where(and(
						PROJECT_TASKRELATIONTABLE.ID.eq(new JdbcNamedParameter("id")),
						PROJECT_TASKRELATIONTABLE.PER_TASK.eq(new JdbcNamedParameter("perTask")),
						PROJECT_TASKRELATIONTABLE.TASKRELATION_CONDITON.eq(new JdbcNamedParameter("taskrelationConditon")),
						PROJECT_TASKRELATIONTABLE.AFTER_TASK.eq(new JdbcNamedParameter("afterTask")),
						PROJECT_TASKRELATIONTABLE.TASKRELATION_ACTION.eq(new JdbcNamedParameter("taskrelationAction"))));
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
