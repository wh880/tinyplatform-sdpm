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
import static org.tinygroup.sdpm.project.dao.constant.BuildTable.*;
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
import org.tinygroup.sdpm.project.dao.pojo.Build;
import org.tinygroup.sdpm.project.dao.BuildDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class BuildDaoImpl extends TinyDslDaoSupport implements BuildDao {

	public Build add(Build build) {
		return getDslTemplate().insertAndReturnKey(build, new InsertGenerateCallback<Build>() {
			public Insert generate(Build t) {
				Insert insert = insertInto(BUILDTABLE).values(
					BUILDTABLE.BUILD_ID.value(t.getBuildId()),
					BUILDTABLE.BUILD_PRODUCT.value(t.getBuildProduct()),
					BUILDTABLE.BUILD_PROJECT.value(t.getBuildProject()),
					BUILDTABLE.BUILD_NAME.value(t.getBuildName()),
					BUILDTABLE.BUILD_SCM_PATH.value(t.getBuildScmPath()),
					BUILDTABLE.BUILD_FILE_PATH.value(t.getBuildFilePath()),
					BUILDTABLE.BUILD_DATE.value(t.getBuildDate()),
					BUILDTABLE.BUILD_STORIES.value(t.getBuildStories()),
					BUILDTABLE.BUILD_BUGS.value(t.getBuildBugs()),
					BUILDTABLE.BUILD_BUILDER.value(t.getBuildBuilder()),
					BUILDTABLE.BUILD_DESC.value(t.getBuildDesc()),
					BUILDTABLE.BUILD_DELETED.value(t.getBuildDeleted()));
				return insert;
			}
		});
	}

	public int edit(Build build) {
		if(build == null || build.getBuildId() == null){
			return 0;
		}
		return getDslTemplate().update(build, new UpdateGenerateCallback<Build>() {
			public Update generate(Build t) {
				Update update = update(BUILDTABLE).set(
					BUILDTABLE.BUILD_PRODUCT.value(t.getBuildProduct()),
					BUILDTABLE.BUILD_PROJECT.value(t.getBuildProject()),
					BUILDTABLE.BUILD_NAME.value(t.getBuildName()),
					BUILDTABLE.BUILD_SCM_PATH.value(t.getBuildScmPath()),
					BUILDTABLE.BUILD_FILE_PATH.value(t.getBuildFilePath()),
					BUILDTABLE.BUILD_DATE.value(t.getBuildDate()),
					BUILDTABLE.BUILD_STORIES.value(t.getBuildStories()),
					BUILDTABLE.BUILD_BUGS.value(t.getBuildBugs()),
					BUILDTABLE.BUILD_BUILDER.value(t.getBuildBuilder()),
					BUILDTABLE.BUILD_DESC.value(t.getBuildDesc()),
					BUILDTABLE.BUILD_DELETED.value(t.getBuildDeleted())).where(
					BUILDTABLE.BUILD_ID.eq(t.getBuildId()));
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
				return delete(BUILDTABLE).where(BUILDTABLE.BUILD_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(BUILDTABLE).where(BUILDTABLE.BUILD_ID.in(t));
		}
		},pks);
	}

	public Build getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Build.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(BUILDTABLE).where(BUILDTABLE.BUILD_ID.eq(t));
			}
		});
	}

	public List<Build> query(Build build) {
		if(build==null){
			build=new Build();
		}
		return getDslTemplate().query(build, new SelectGenerateCallback<Build>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Build t) {
				return selectFrom(BUILDTABLE).where(
				and(
					BUILDTABLE.BUILD_PRODUCT.eq(t.getBuildProduct()),
					BUILDTABLE.BUILD_PROJECT.eq(t.getBuildProject()),
					BUILDTABLE.BUILD_NAME.eq(t.getBuildName()),
					BUILDTABLE.BUILD_SCM_PATH.eq(t.getBuildScmPath()),
					BUILDTABLE.BUILD_FILE_PATH.eq(t.getBuildFilePath()),
					BUILDTABLE.BUILD_DATE.eq(t.getBuildDate()),
					BUILDTABLE.BUILD_STORIES.eq(t.getBuildStories()),
					BUILDTABLE.BUILD_BUGS.eq(t.getBuildBugs()),
					BUILDTABLE.BUILD_BUILDER.eq(t.getBuildBuilder()),
					BUILDTABLE.BUILD_DESC.eq(t.getBuildDesc()),
					BUILDTABLE.BUILD_DELETED.eq(t.getBuildDeleted())));
			}
		});
	}

	public Pager<Build> queryPager(int start,int limit ,Build build) {
		if(build==null){
			build=new Build();
		}
		return getDslTemplate().queryPager(start, limit, build, false, new SelectGenerateCallback<Build>() {

			public Select generate(Build t) {
				return MysqlSelect.selectFrom(BUILDTABLE).where(
				and(
					BUILDTABLE.BUILD_PRODUCT.eq(t.getBuildProduct()),
					BUILDTABLE.BUILD_PROJECT.eq(t.getBuildProject()),
					BUILDTABLE.BUILD_NAME.eq(t.getBuildName()),
					BUILDTABLE.BUILD_SCM_PATH.eq(t.getBuildScmPath()),
					BUILDTABLE.BUILD_FILE_PATH.eq(t.getBuildFilePath()),
					BUILDTABLE.BUILD_DATE.eq(t.getBuildDate()),
					BUILDTABLE.BUILD_STORIES.eq(t.getBuildStories()),
					BUILDTABLE.BUILD_BUGS.eq(t.getBuildBugs()),
					BUILDTABLE.BUILD_BUILDER.eq(t.getBuildBuilder()),
					BUILDTABLE.BUILD_DESC.eq(t.getBuildDesc()),
					BUILDTABLE.BUILD_DELETED.eq(t.getBuildDeleted())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Build> builds) {
		if (CollectionUtil.isEmpty(builds)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, builds, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(BUILDTABLE).values(
					BUILDTABLE.BUILD_PRODUCT.value(new JdbcNamedParameter("buildProduct")),
					BUILDTABLE.BUILD_PROJECT.value(new JdbcNamedParameter("buildProject")),
					BUILDTABLE.BUILD_NAME.value(new JdbcNamedParameter("buildName")),
					BUILDTABLE.BUILD_SCM_PATH.value(new JdbcNamedParameter("buildScmPath")),
					BUILDTABLE.BUILD_FILE_PATH.value(new JdbcNamedParameter("buildFilePath")),
					BUILDTABLE.BUILD_DATE.value(new JdbcNamedParameter("buildDate")),
					BUILDTABLE.BUILD_STORIES.value(new JdbcNamedParameter("buildStories")),
					BUILDTABLE.BUILD_BUGS.value(new JdbcNamedParameter("buildBugs")),
					BUILDTABLE.BUILD_BUILDER.value(new JdbcNamedParameter("buildBuilder")),
					BUILDTABLE.BUILD_DESC.value(new JdbcNamedParameter("buildDesc")),
					BUILDTABLE.BUILD_DELETED.value(new JdbcNamedParameter("buildDeleted")));
			}
		});
	}

	public int[] batchInsert(List<Build> builds){
			return batchInsert(true ,builds);
	}

	public int[] batchUpdate(List<Build> builds) {
		if (CollectionUtil.isEmpty(builds)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(builds, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(BUILDTABLE).set(
					BUILDTABLE.BUILD_PRODUCT.value(new JdbcNamedParameter("buildProduct")),
					BUILDTABLE.BUILD_PROJECT.value(new JdbcNamedParameter("buildProject")),
					BUILDTABLE.BUILD_NAME.value(new JdbcNamedParameter("buildName")),
					BUILDTABLE.BUILD_SCM_PATH.value(new JdbcNamedParameter("buildScmPath")),
					BUILDTABLE.BUILD_FILE_PATH.value(new JdbcNamedParameter("buildFilePath")),
					BUILDTABLE.BUILD_DATE.value(new JdbcNamedParameter("buildDate")),
					BUILDTABLE.BUILD_STORIES.value(new JdbcNamedParameter("buildStories")),
					BUILDTABLE.BUILD_BUGS.value(new JdbcNamedParameter("buildBugs")),
					BUILDTABLE.BUILD_BUILDER.value(new JdbcNamedParameter("buildBuilder")),
					BUILDTABLE.BUILD_DESC.value(new JdbcNamedParameter("buildDesc")),
					BUILDTABLE.BUILD_DELETED.value(new JdbcNamedParameter("buildDeleted"))).where(
				BUILDTABLE.BUILD_ID.eq(new JdbcNamedParameter("buildId")));
			}
		});
	}

	public int[] batchDelete(List<Build> builds) {
		if (CollectionUtil.isEmpty(builds)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(builds, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(BUILDTABLE).where(and(
				BUILDTABLE.BUILD_ID.eq(new JdbcNamedParameter("buildId")),
				BUILDTABLE.BUILD_PRODUCT.eq(new JdbcNamedParameter("buildProduct")),
				BUILDTABLE.BUILD_PROJECT.eq(new JdbcNamedParameter("buildProject")),
				BUILDTABLE.BUILD_NAME.eq(new JdbcNamedParameter("buildName")),
				BUILDTABLE.BUILD_SCM_PATH.eq(new JdbcNamedParameter("buildScmPath")),
				BUILDTABLE.BUILD_FILE_PATH.eq(new JdbcNamedParameter("buildFilePath")),
				BUILDTABLE.BUILD_DATE.eq(new JdbcNamedParameter("buildDate")),
				BUILDTABLE.BUILD_STORIES.eq(new JdbcNamedParameter("buildStories")),
				BUILDTABLE.BUILD_BUGS.eq(new JdbcNamedParameter("buildBugs")),
				BUILDTABLE.BUILD_BUILDER.eq(new JdbcNamedParameter("buildBuilder")),
				BUILDTABLE.BUILD_DESC.eq(new JdbcNamedParameter("buildDesc")),
				BUILDTABLE.BUILD_DELETED.eq(new JdbcNamedParameter("buildDeleted"))));
			}
		});
	}

}
