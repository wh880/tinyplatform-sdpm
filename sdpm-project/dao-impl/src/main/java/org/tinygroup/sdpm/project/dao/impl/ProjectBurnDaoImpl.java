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
import org.tinygroup.sdpm.project.dao.ProjectBurnDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBurn;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.project.dao.constant.ProjectBurnTable.PROJECT_BURNTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

public class ProjectBurnDaoImpl extends TinyDslDaoSupport implements ProjectBurnDao {

	public ProjectBurn add(ProjectBurn projectBurn) {
		return getDslTemplate().insertAndReturnKey(projectBurn, new InsertGenerateCallback<ProjectBurn>() {
			public Insert generate(ProjectBurn t) {
				Insert insert = insertInto(PROJECT_BURNTABLE).values(
						PROJECT_BURNTABLE.ID.value(t.getId()),
						PROJECT_BURNTABLE.PROJECT_ID.value(t.getProjectId()),
						PROJECT_BURNTABLE.BURN_DATE.value(t.getBurnDate()),
						PROJECT_BURNTABLE.BURN_LEFT.value(t.getBurnLeft()),
						PROJECT_BURNTABLE.BURN_CONSUMED.value(t.getBurnConsumed()));
				return insert;
			}
		});
	}

	public int edit(ProjectBurn projectBurn) {
		if (projectBurn == null || projectBurn.getId() == null) {
			return 0;
		}
		return getDslTemplate().update(projectBurn, new UpdateGenerateCallback<ProjectBurn>() {
			public Update generate(ProjectBurn t) {
				Update update = update(PROJECT_BURNTABLE).set(
						PROJECT_BURNTABLE.PROJECT_ID.value(t.getProjectId()),
						PROJECT_BURNTABLE.BURN_DATE.value(t.getBurnDate()),
						PROJECT_BURNTABLE.BURN_LEFT.value(t.getBurnLeft()),
						PROJECT_BURNTABLE.BURN_CONSUMED.value(t.getBurnConsumed())).where(
						PROJECT_BURNTABLE.ID.eq(t.getId()));
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
				return delete(PROJECT_BURNTABLE).where(PROJECT_BURNTABLE.ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if (pks == null || pks.length == 0) {
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(PROJECT_BURNTABLE).where(PROJECT_BURNTABLE.ID.in(t));
			}
		}, pks);
	}

	public ProjectBurn getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, ProjectBurn.class, new SelectGenerateCallback<Serializable>() {
			@SuppressWarnings("rawtypes")
			public Select generate(Serializable t) {
				return selectFrom(PROJECT_BURNTABLE).where(PROJECT_BURNTABLE.ID.eq(t));
			}
		});
	}

	public List<ProjectBurn> query(ProjectBurn projectBurn, final OrderBy... orderBies) {
		if (projectBurn == null) {
			projectBurn = new ProjectBurn();
		}
		return getDslTemplate().query(projectBurn, new SelectGenerateCallback<ProjectBurn>() {

			@SuppressWarnings("rawtypes")
			public Select generate(ProjectBurn t) {
				Select select = selectFrom(PROJECT_BURNTABLE).where(
						and(
								PROJECT_BURNTABLE.PROJECT_ID.eq(t.getProjectId()),
								PROJECT_BURNTABLE.BURN_DATE.eq(t.getBurnDate()),
								PROJECT_BURNTABLE.BURN_LEFT.eq(t.getBurnLeft()),
								PROJECT_BURNTABLE.BURN_CONSUMED.eq(t.getBurnConsumed())));
				return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<ProjectBurn> queryPager(int start, int limit, ProjectBurn projectBurn, final OrderBy... orderBies) {
		if (projectBurn == null) {
			projectBurn = new ProjectBurn();
		}
		return getDslTemplate().queryPager(start, limit, projectBurn, false, new SelectGenerateCallback<ProjectBurn>() {

			public Select generate(ProjectBurn t) {
				Select select = MysqlSelect.selectFrom(PROJECT_BURNTABLE).where(
						and(
								PROJECT_BURNTABLE.PROJECT_ID.eq(t.getProjectId()),
								PROJECT_BURNTABLE.BURN_DATE.eq(t.getBurnDate()),
								PROJECT_BURNTABLE.BURN_LEFT.eq(t.getBurnLeft()),
								PROJECT_BURNTABLE.BURN_CONSUMED.eq(t.getBurnConsumed())));
				return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys, List<ProjectBurn> projectBurns) {
		if (CollectionUtil.isEmpty(projectBurns)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, projectBurns, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(PROJECT_BURNTABLE).values(
						PROJECT_BURNTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
						PROJECT_BURNTABLE.BURN_DATE.value(new JdbcNamedParameter("burnDate")),
						PROJECT_BURNTABLE.BURN_LEFT.value(new JdbcNamedParameter("burnLeft")),
						PROJECT_BURNTABLE.BURN_CONSUMED.value(new JdbcNamedParameter("burnConsumed")));
			}
		});
	}

	public int[] batchInsert(List<ProjectBurn> projectBurns) {
		return batchInsert(true, projectBurns);
	}

	public int[] batchUpdate(List<ProjectBurn> projectBurns) {
		if (CollectionUtil.isEmpty(projectBurns)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(projectBurns, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(PROJECT_BURNTABLE).set(
						PROJECT_BURNTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
						PROJECT_BURNTABLE.BURN_DATE.value(new JdbcNamedParameter("burnDate")),
						PROJECT_BURNTABLE.BURN_LEFT.value(new JdbcNamedParameter("burnLeft")),
						PROJECT_BURNTABLE.BURN_CONSUMED.value(new JdbcNamedParameter("burnConsumed"))).where(
						PROJECT_BURNTABLE.ID.eq(new JdbcNamedParameter("id")));
			}
		});
	}

	public int[] batchDelete(List<ProjectBurn> projectBurns) {
		if (CollectionUtil.isEmpty(projectBurns)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(projectBurns, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(PROJECT_BURNTABLE).where(and(
						PROJECT_BURNTABLE.ID.eq(new JdbcNamedParameter("id")),
						PROJECT_BURNTABLE.PROJECT_ID.eq(new JdbcNamedParameter("projectId")),
						PROJECT_BURNTABLE.BURN_DATE.eq(new JdbcNamedParameter("burnDate")),
						PROJECT_BURNTABLE.BURN_LEFT.eq(new JdbcNamedParameter("burnLeft")),
						PROJECT_BURNTABLE.BURN_CONSUMED.eq(new JdbcNamedParameter("burnConsumed"))));
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
