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

package org.tinygroup.sdpm.product.dao.impl;

import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

import java.io.Serializable;
import java.util.List;

import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.product.dao.inter.ReleaseDao;
import org.tinygroup.sdpm.product.dao.pojo.Release;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;

public class ReleaseDaoImpl extends TinyDslDaoSupport implements ReleaseDao {

	public Release add(Release release) {
		return getDslTemplate().insertAndReturnKey(release, new InsertGenerateCallback<Release>() {
			public Insert generate(Release t) {
				Insert insert = insertInto(RELEASETABLE).values(
					RELEASETABLE.RELEASE_ID.value(t.getReleaseId()),
					RELEASETABLE.PRODUCT_ID.value(t.getProductId()),
					RELEASETABLE.BUILD.value(t.getBuild()),
					RELEASETABLE.RELEASE_NAME.value(t.getReleaseName()),
					RELEASETABLE.RELEASE_DATE.value(t.getReleaseDate()),
					RELEASETABLE.RELEASE_STORIES.value(t.getReleaseStories()),
					RELEASETABLE.RELEASE_BUGS.value(t.getReleaseBugs()),
					RELEASETABLE.RELEASE_DESC.value(t.getReleaseDesc()),
					RELEASETABLE.DELETED.value(t.getDeleted()));
				return insert;
			}
		});
	}

	public int edit(Release release) {
		if(release == null || release.getReleaseId() == null){
			return 0;
		}
		return getDslTemplate().update(release, new UpdateGenerateCallback<Release>() {
			public Update generate(Release t) {
				Update update = update(RELEASETABLE).set(
					RELEASETABLE.PRODUCT_ID.value(t.getProductId()),
					RELEASETABLE.BUILD.value(t.getBuild()),
					RELEASETABLE.RELEASE_NAME.value(t.getReleaseName()),
					RELEASETABLE.RELEASE_DATE.value(t.getReleaseDate()),
					RELEASETABLE.RELEASE_STORIES.value(t.getReleaseStories()),
					RELEASETABLE.RELEASE_BUGS.value(t.getReleaseBugs()),
					RELEASETABLE.RELEASE_DESC.value(t.getReleaseDesc()),
					RELEASETABLE.DELETED.value(t.getDeleted())).where(
					RELEASETABLE.RELEASE_ID.eq(t.getReleaseId()));
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
				return delete(RELEASETABLE).where(RELEASETABLE.RELEASE_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(RELEASETABLE).where(RELEASETABLE.RELEASE_ID.in(t));
		}
		},pks);
	}

	public Release getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Release.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(RELEASETABLE).where(RELEASETABLE.RELEASE_ID.eq(t));
			}
		});
	}

	public List<Release> query(Release release) {
		if(release==null){
			release=new Release();
		}
		return getDslTemplate().query(release, new SelectGenerateCallback<Release>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Release t) {
				return selectFrom(RELEASETABLE).where(
				and(
					RELEASETABLE.PRODUCT_ID.eq(t.getProductId()),
					RELEASETABLE.BUILD.eq(t.getBuild()),
					RELEASETABLE.RELEASE_NAME.eq(t.getReleaseName()),
					RELEASETABLE.RELEASE_DATE.eq(t.getReleaseDate()),
					RELEASETABLE.RELEASE_STORIES.eq(t.getReleaseStories()),
					RELEASETABLE.RELEASE_BUGS.eq(t.getReleaseBugs()),
					RELEASETABLE.RELEASE_DESC.eq(t.getReleaseDesc()),
					RELEASETABLE.DELETED.eq(t.getDeleted())));
			}
		});
	}

	public Pager<Release> queryPager(int start,int limit ,Release release) {
		if(release==null){
			release=new Release();
		}
		return getDslTemplate().queryPager(start, limit, release, false, new SelectGenerateCallback<Release>() {

			public Select generate(Release t) {
				return MysqlSelect.selectFrom(RELEASETABLE).where(
				and(
					RELEASETABLE.PRODUCT_ID.eq(t.getProductId()),
					RELEASETABLE.BUILD.eq(t.getBuild()),
					RELEASETABLE.RELEASE_NAME.eq(t.getReleaseName()),
					RELEASETABLE.RELEASE_DATE.eq(t.getReleaseDate()),
					RELEASETABLE.RELEASE_STORIES.eq(t.getReleaseStories()),
					RELEASETABLE.RELEASE_BUGS.eq(t.getReleaseBugs()),
					RELEASETABLE.RELEASE_DESC.eq(t.getReleaseDesc()),
					RELEASETABLE.DELETED.eq(t.getDeleted())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Release> releases) {
		if (CollectionUtil.isEmpty(releases)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, releases, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(RELEASETABLE).values(
					RELEASETABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					RELEASETABLE.BUILD.value(new JdbcNamedParameter("build")),
					RELEASETABLE.RELEASE_NAME.value(new JdbcNamedParameter("releaseName")),
					RELEASETABLE.RELEASE_DATE.value(new JdbcNamedParameter("releaseDate")),
					RELEASETABLE.RELEASE_STORIES.value(new JdbcNamedParameter("releaseStories")),
					RELEASETABLE.RELEASE_BUGS.value(new JdbcNamedParameter("releaseBugs")),
					RELEASETABLE.RELEASE_DESC.value(new JdbcNamedParameter("releaseDesc")),
					RELEASETABLE.DELETED.value(new JdbcNamedParameter("deleted")));
			}
		});
	}

	public int[] batchInsert(List<Release> releases){
			return batchInsert(true ,releases);
	}

	public int[] batchUpdate(List<Release> releases) {
		if (CollectionUtil.isEmpty(releases)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(releases, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(RELEASETABLE).set(
					RELEASETABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					RELEASETABLE.BUILD.value(new JdbcNamedParameter("build")),
					RELEASETABLE.RELEASE_NAME.value(new JdbcNamedParameter("releaseName")),
					RELEASETABLE.RELEASE_DATE.value(new JdbcNamedParameter("releaseDate")),
					RELEASETABLE.RELEASE_STORIES.value(new JdbcNamedParameter("releaseStories")),
					RELEASETABLE.RELEASE_BUGS.value(new JdbcNamedParameter("releaseBugs")),
					RELEASETABLE.RELEASE_DESC.value(new JdbcNamedParameter("releaseDesc")),
					RELEASETABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
				RELEASETABLE.RELEASE_ID.eq(new JdbcNamedParameter("releaseId")));
			}
		});
	}

	public int[] batchDelete(List<Release> releases) {
		if (CollectionUtil.isEmpty(releases)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(releases, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(RELEASETABLE).where(and(
				RELEASETABLE.RELEASE_ID.eq(new JdbcNamedParameter("releaseId")),
				RELEASETABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
				RELEASETABLE.BUILD.eq(new JdbcNamedParameter("build")),
				RELEASETABLE.RELEASE_NAME.eq(new JdbcNamedParameter("releaseName")),
				RELEASETABLE.RELEASE_DATE.eq(new JdbcNamedParameter("releaseDate")),
				RELEASETABLE.RELEASE_STORIES.eq(new JdbcNamedParameter("releaseStories")),
				RELEASETABLE.RELEASE_BUGS.eq(new JdbcNamedParameter("releaseBugs")),
				RELEASETABLE.RELEASE_DESC.eq(new JdbcNamedParameter("releaseDesc")),
				RELEASETABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
			}
		});
	}

}
