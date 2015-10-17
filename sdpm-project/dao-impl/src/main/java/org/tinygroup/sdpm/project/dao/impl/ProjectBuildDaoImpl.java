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
import org.tinygroup.sdpm.common.util.update.UpdateUtil;
import org.tinygroup.sdpm.product.dao.pojo.ProductAndLine;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.project.dao.ProjectBuildDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.project.dao.constant.ProjectBuildTable.PROJECT_BUILDTABLE;
import static org.tinygroup.sdpm.product.dao.constant.ProductTable.PRODUCTTABLE;
import static org.tinygroup.sdpm.productLine.dao.constant.ProductLineTable.PRODUCT_LINETABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.select;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.select.Join.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@LogClass("build")
@Repository
public class ProjectBuildDaoImpl extends TinyDslDaoSupport implements ProjectBuildDao {


	@LogMethod("add")
	public ProjectBuild add(ProjectBuild projectBuild) {
		return getDslTemplate().insertAndReturnKey(projectBuild, new InsertGenerateCallback<ProjectBuild>() {
			public Insert generate(ProjectBuild t) {
				Insert insert = insertInto(PROJECT_BUILDTABLE).values(
						PROJECT_BUILDTABLE.BUILD_ID.value(t.getBuildId()),
						PROJECT_BUILDTABLE.BUILD_PRODUCT.value(t.getBuildProduct()),
						PROJECT_BUILDTABLE.BUILD_PROJECT.value(t.getBuildProject()),
						PROJECT_BUILDTABLE.BUILD_NAME.value(t.getBuildName()),
						PROJECT_BUILDTABLE.BUILD_SCM_PATH.value(t.getBuildScmPath()),
						PROJECT_BUILDTABLE.BUILD_FILE_PATH.value(t.getBuildFilePath()),
						PROJECT_BUILDTABLE.BUILD_DATE.value(t.getBuildDate()),
						PROJECT_BUILDTABLE.BUILD_STORIES.value(t.getBuildStories()),
						PROJECT_BUILDTABLE.BUILD_BUGS.value(t.getBuildBugs()),
						PROJECT_BUILDTABLE.BUILD_BUILDER.value(t.getBuildBuilder()),
						PROJECT_BUILDTABLE.BUILD_DESC.value(t.getBuildDesc()),
						PROJECT_BUILDTABLE.BUILD_DELETED.value(t.getBuildDeleted()));
				return insert;
			}
		});
	}

	public int edit(ProjectBuild projectBuild) {
		return 0;
	}

	@LogMethod("edits")
	public Integer edits(ProjectBuild projectBuild) {
		if (projectBuild == null || projectBuild.getBuildId() == null) {
			return 0;
		}
		Update update = UpdateUtil.getUpdate(PROJECT_BUILDTABLE,projectBuild);
		getDslSession().execute(update);

		return 0;
	}

	public Integer softDelete(ProjectBuild build) {
		Update update = update(PROJECT_BUILDTABLE).set(PROJECT_BUILDTABLE.BUILD_DELETED.value(build.getBuildDeleted()))
				.where(PROJECT_BUILDTABLE.BUILD_ID.eq(build.getBuildId()));
		return getDslSession().execute(update);
	}

	@LogMethod("deleteByKey")
	public int deleteByKey(Integer pk) {
		if (pk == null) {
			return 0;
		}
		return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
			public Delete generate(Serializable pk) {
				return delete(PROJECT_BUILDTABLE).where(PROJECT_BUILDTABLE.BUILD_ID.eq(pk));
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
				return delete(PROJECT_BUILDTABLE).where(PROJECT_BUILDTABLE.BUILD_ID.in(t));
			}
		}, pks);
	}

	public ProjectBuild getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, ProjectBuild.class, new SelectGenerateCallback<Serializable>() {
			@SuppressWarnings("rawtypes")
			public Select generate(Serializable t) {
				return selectFrom(PROJECT_BUILDTABLE).where(PROJECT_BUILDTABLE.BUILD_ID.eq(t));
			}
		});
	}

	public List<ProjectBuild> query(ProjectBuild projectBuild, final OrderBy... orderBies) {
		if (projectBuild == null) {
			projectBuild = new ProjectBuild();
		}
		return getDslTemplate().query(projectBuild, new SelectGenerateCallback<ProjectBuild>() {

			@SuppressWarnings("rawtypes")
			public Select generate(ProjectBuild t) {
				Select select = selectFrom(PROJECT_BUILDTABLE).where(
						and(
								PROJECT_BUILDTABLE.BUILD_PRODUCT.eq(t.getBuildProduct()),
								PROJECT_BUILDTABLE.BUILD_PROJECT.eq(t.getBuildProject()),
								PROJECT_BUILDTABLE.BUILD_NAME.eq(t.getBuildName()),
								PROJECT_BUILDTABLE.BUILD_SCM_PATH.eq(t.getBuildScmPath()),
								PROJECT_BUILDTABLE.BUILD_FILE_PATH.eq(t.getBuildFilePath()),
								PROJECT_BUILDTABLE.BUILD_DATE.eq(t.getBuildDate()),
								PROJECT_BUILDTABLE.BUILD_STORIES.eq(t.getBuildStories()),
								PROJECT_BUILDTABLE.BUILD_BUGS.eq(t.getBuildBugs()),
								PROJECT_BUILDTABLE.BUILD_BUILDER.eq(t.getBuildBuilder()),
								PROJECT_BUILDTABLE.BUILD_DESC.eq(t.getBuildDesc()),
								PROJECT_BUILDTABLE.BUILD_DELETED.eq(t.getBuildDeleted())));
				return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<ProjectBuild> queryPager(int start, int limit, ProjectBuild projectBuild, final OrderBy... orderBies) {
		if (projectBuild == null) {
			projectBuild = new ProjectBuild();
		}
		return getDslTemplate().queryPager(start, limit, projectBuild, false, new SelectGenerateCallback<ProjectBuild>() {

			public Select generate(ProjectBuild t) {
				Select select = MysqlSelect.selectFrom(PROJECT_BUILDTABLE).where(
						and(
								PROJECT_BUILDTABLE.BUILD_PRODUCT.eq(t.getBuildProduct()),
								PROJECT_BUILDTABLE.BUILD_PROJECT.eq(t.getBuildProject()),
								PROJECT_BUILDTABLE.BUILD_NAME.eq(t.getBuildName()),
								PROJECT_BUILDTABLE.BUILD_SCM_PATH.eq(t.getBuildScmPath()),
								PROJECT_BUILDTABLE.BUILD_FILE_PATH.eq(t.getBuildFilePath()),
								PROJECT_BUILDTABLE.BUILD_DATE.eq(t.getBuildDate()),
								PROJECT_BUILDTABLE.BUILD_STORIES.eq(t.getBuildStories()),
								PROJECT_BUILDTABLE.BUILD_BUGS.eq(t.getBuildBugs()),
								PROJECT_BUILDTABLE.BUILD_BUILDER.eq(t.getBuildBuilder()),
								PROJECT_BUILDTABLE.BUILD_DESC.eq(t.getBuildDesc()),
								PROJECT_BUILDTABLE.BUILD_DELETED.eq(t.getBuildDeleted())));
				return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys, List<ProjectBuild> projectBuilds) {
		if (CollectionUtil.isEmpty(projectBuilds)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, projectBuilds, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(PROJECT_BUILDTABLE).values(
						PROJECT_BUILDTABLE.BUILD_PRODUCT.value(new JdbcNamedParameter("buildProduct")),
						PROJECT_BUILDTABLE.BUILD_PROJECT.value(new JdbcNamedParameter("buildProject")),
						PROJECT_BUILDTABLE.BUILD_NAME.value(new JdbcNamedParameter("buildName")),
						PROJECT_BUILDTABLE.BUILD_SCM_PATH.value(new JdbcNamedParameter("buildScmPath")),
						PROJECT_BUILDTABLE.BUILD_FILE_PATH.value(new JdbcNamedParameter("buildFilePath")),
						PROJECT_BUILDTABLE.BUILD_DATE.value(new JdbcNamedParameter("buildDate")),
						PROJECT_BUILDTABLE.BUILD_STORIES.value(new JdbcNamedParameter("buildStories")),
						PROJECT_BUILDTABLE.BUILD_BUGS.value(new JdbcNamedParameter("buildBugs")),
						PROJECT_BUILDTABLE.BUILD_BUILDER.value(new JdbcNamedParameter("buildBuilder")),
						PROJECT_BUILDTABLE.BUILD_DESC.value(new JdbcNamedParameter("buildDesc")),
						PROJECT_BUILDTABLE.BUILD_DELETED.value(new JdbcNamedParameter("buildDeleted")));
			}
		});
	}

	public int[] batchInsert(List<ProjectBuild> projectBuilds) {
		return batchInsert(true, projectBuilds);
	}
	@LogMethod("batchUpdate")
	public int[] batchUpdate(List<ProjectBuild> projectBuilds) {
		if (CollectionUtil.isEmpty(projectBuilds)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(projectBuilds, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(PROJECT_BUILDTABLE).set(
						PROJECT_BUILDTABLE.BUILD_PRODUCT.value(new JdbcNamedParameter("buildProduct")),
						PROJECT_BUILDTABLE.BUILD_PROJECT.value(new JdbcNamedParameter("buildProject")),
						PROJECT_BUILDTABLE.BUILD_NAME.value(new JdbcNamedParameter("buildName")),
						PROJECT_BUILDTABLE.BUILD_SCM_PATH.value(new JdbcNamedParameter("buildScmPath")),
						PROJECT_BUILDTABLE.BUILD_FILE_PATH.value(new JdbcNamedParameter("buildFilePath")),
						PROJECT_BUILDTABLE.BUILD_DATE.value(new JdbcNamedParameter("buildDate")),
						PROJECT_BUILDTABLE.BUILD_STORIES.value(new JdbcNamedParameter("buildStories")),
						PROJECT_BUILDTABLE.BUILD_BUGS.value(new JdbcNamedParameter("buildBugs")),
						PROJECT_BUILDTABLE.BUILD_BUILDER.value(new JdbcNamedParameter("buildBuilder")),
						PROJECT_BUILDTABLE.BUILD_DESC.value(new JdbcNamedParameter("buildDesc")),
						PROJECT_BUILDTABLE.BUILD_DELETED.value(new JdbcNamedParameter("buildDeleted"))).where(
						PROJECT_BUILDTABLE.BUILD_ID.eq(new JdbcNamedParameter("buildId")));
			}
		});
	}
	@LogMethod("batchDelete")
	public int[] batchDelete(List<ProjectBuild> projectBuilds) {
		if (CollectionUtil.isEmpty(projectBuilds)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(projectBuilds, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(PROJECT_BUILDTABLE).where(and(
						PROJECT_BUILDTABLE.BUILD_ID.eq(new JdbcNamedParameter("buildId")),
						PROJECT_BUILDTABLE.BUILD_PRODUCT.eq(new JdbcNamedParameter("buildProduct")),
						PROJECT_BUILDTABLE.BUILD_PROJECT.eq(new JdbcNamedParameter("buildProject")),
						PROJECT_BUILDTABLE.BUILD_NAME.eq(new JdbcNamedParameter("buildName")),
						PROJECT_BUILDTABLE.BUILD_SCM_PATH.eq(new JdbcNamedParameter("buildScmPath")),
						PROJECT_BUILDTABLE.BUILD_FILE_PATH.eq(new JdbcNamedParameter("buildFilePath")),
						PROJECT_BUILDTABLE.BUILD_DATE.eq(new JdbcNamedParameter("buildDate")),
						PROJECT_BUILDTABLE.BUILD_STORIES.eq(new JdbcNamedParameter("buildStories")),
						PROJECT_BUILDTABLE.BUILD_BUGS.eq(new JdbcNamedParameter("buildBugs")),
						PROJECT_BUILDTABLE.BUILD_BUILDER.eq(new JdbcNamedParameter("buildBuilder")),
						PROJECT_BUILDTABLE.BUILD_DESC.eq(new JdbcNamedParameter("buildDesc")),
						PROJECT_BUILDTABLE.BUILD_DELETED.eq(new JdbcNamedParameter("buildDeleted"))));
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
	public int[] batchUpdateDel(List<ProjectBuild> builds){
		if (CollectionUtil.isEmpty(builds)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(builds, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(PROJECT_BUILDTABLE).set(
						PROJECT_BUILDTABLE.BUILD_DELETED.value(new JdbcNamedParameter("buildDeleted"))).where(
						PROJECT_BUILDTABLE.BUILD_ID.eq(new JdbcNamedParameter("buildId")));
			}
		});

	}
	
	public List<ProductAndLine> getProductLineTree(ProductLine t){
		
		Select select = select(PRODUCT_LINETABLE.PRODUCT_LINE_ID,PRODUCT_LINETABLE.PRODUCT_LINE_NAME,PRODUCTTABLE.PRODUCT_ID,PRODUCTTABLE.PRODUCT_NAME,PROJECT_BUILDTABLE.BUILD_ID,PROJECT_BUILDTABLE.BUILD_NAME).
				from(PRODUCT_LINETABLE).join(leftJoin(PRODUCTTABLE, PRODUCT_LINETABLE.PRODUCT_LINE_ID.eq(PRODUCTTABLE.PRODUCT_LINE_ID))).join(leftJoin(PROJECT_BUILDTABLE, PRODUCTTABLE.PRODUCT_ID.eq(PROJECT_BUILDTABLE.BUILD_PRODUCT))).
				where(
						and(	
								PRODUCT_LINETABLE.PRODUCT_LINE_ID.isNotNull(),
								PRODUCTTABLE.PRODUCT_ID.isNotNull(),
								PROJECT_BUILDTABLE.BUILD_ID.isNotNull(),
								
								PRODUCT_LINETABLE.COMPANY_ID.eq(t.getCompanyId()),
								PRODUCT_LINETABLE.DEPT_ID.eq(t.getDeptId()),
								PRODUCT_LINETABLE.PRODUCT_LINE_ROOT.eq(t.getProductLineRoot()),
								PRODUCT_LINETABLE.PRODUCT_LINE_PARENT.eq(t.getProductLineParent()),
								PRODUCT_LINETABLE.PRODUCT_LINE_NAME.eq(t.getProductLineName()),
								PRODUCT_LINETABLE.PRODUCT_LINE_CODE.eq(t.getProductLineCode()),
								PRODUCT_LINETABLE.PRODUCT_LINE_ORDER.eq(t.getProductLineOrder()),
								PRODUCT_LINETABLE.PRODUCT_LINE_SPEC.eq(t.getProductLineSpec()),
								PRODUCT_LINETABLE.PRODUCT_LINE_STATUS.eq(t.getProductLineStatus()),
								PRODUCT_LINETABLE.PRODUCT_LINE_OWNER.eq(t.getProductLineOwner()),
								PRODUCT_LINETABLE.PRODUCT_LINE_QUALITY_MANAGER.eq(t.getProductLineQualityManager()),
								PRODUCT_LINETABLE.PRODUCT_LINE_DELIVERY_MANAGER.eq(t.getProductLineDeliveryManager()),
								PRODUCT_LINETABLE.ACL.eq(t.getAcl()),
								PRODUCT_LINETABLE.PRODUCT_LINE_WHITE_LIST.eq(t.getProductLineWhiteList()),
								PRODUCT_LINETABLE.PRODUCT_LINE_CREATED_BY.eq(t.getProductLineCreatedBy()),
								PRODUCT_LINETABLE.PRODUCT_LINE_CREATED_DATE.eq(t.getProductLineCreatedDate()),
								PRODUCT_LINETABLE.DELETED.eq(t.getDeleted())));
		List<ProductAndLine> productLines = getDslSession().fetchList(select, ProductAndLine.class);
		return productLines;
	}
}
