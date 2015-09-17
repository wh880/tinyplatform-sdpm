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

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.product.dao.constant.StorySpecTable.*;
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
import org.tinygroup.sdpm.product.dao.pojo.StorySpec;
import org.tinygroup.sdpm.product.dao.StorySpecDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class StorySpecDaoImpl extends TinyDslDaoSupport implements StorySpecDao {

	public StorySpec add(StorySpec storySpec) {
		return getDslTemplate().insertAndReturnKey(storySpec, new InsertGenerateCallback<StorySpec>() {
			public Insert generate(StorySpec t) {
				Insert insert = insertInto(STORYSPECTABLE).values(
					STORYSPECTABLE.COMPANY_ID.value(t.getCompanyId()),
					STORYSPECTABLE.STORY_ID.value(t.getStoryId()),
					STORYSPECTABLE.STORY_VERSION.value(t.getStoryVersion()),
					STORYSPECTABLE.STORY_TITLE.value(t.getStoryTitle()),
					STORYSPECTABLE.STORY_SPEC.value(t.getStorySpec()),
					STORYSPECTABLE.STORY_VERIFICATION.value(t.getStoryVerification()));
				return insert;
			}
		});
	}

	public int edit(StorySpec storySpec) {
		if(storySpec == null || storySpec.getCompanyId() == null){
			return 0;
		}
		return getDslTemplate().update(storySpec, new UpdateGenerateCallback<StorySpec>() {
			public Update generate(StorySpec t) {
				Update update = update(STORYSPECTABLE).set(
					STORYSPECTABLE.COMPANY_ID.value(t.getCompanyId()),
					STORYSPECTABLE.STORY_ID.value(t.getStoryId()),
					STORYSPECTABLE.STORY_VERSION.value(t.getStoryVersion()),
					STORYSPECTABLE.STORY_TITLE.value(t.getStoryTitle()),
					STORYSPECTABLE.STORY_SPEC.value(t.getStorySpec()),
					STORYSPECTABLE.STORY_VERIFICATION.value(t.getStoryVerification())).where(
					STORYSPECTABLE.COMPANY_ID.eq(t.getCompanyId()));
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
				return delete(STORYSPECTABLE).where(STORYSPECTABLE.COMPANY_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(STORYSPECTABLE).where(STORYSPECTABLE.COMPANY_ID.in(t));
		}
		},pks);
	}

	public StorySpec getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, StorySpec.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(STORYSPECTABLE).where(STORYSPECTABLE.COMPANY_ID.eq(t));
			}
		});
	}

	public List<StorySpec> query(StorySpec storySpec) {
		if(storySpec==null){
			storySpec=new StorySpec();
		}
		return getDslTemplate().query(storySpec, new SelectGenerateCallback<StorySpec>() {

			@SuppressWarnings("rawtypes")
			public Select generate(StorySpec t) {
				return selectFrom(STORYSPECTABLE).where(
				and(
					STORYSPECTABLE.COMPANY_ID.eq(t.getCompanyId()),
					STORYSPECTABLE.STORY_ID.eq(t.getStoryId()),
					STORYSPECTABLE.STORY_VERSION.eq(t.getStoryVersion()),
					STORYSPECTABLE.STORY_TITLE.eq(t.getStoryTitle()),
					STORYSPECTABLE.STORY_SPEC.eq(t.getStorySpec()),
					STORYSPECTABLE.STORY_VERIFICATION.eq(t.getStoryVerification())));
			}
		});
	}

	public Pager<StorySpec> queryPager(int start,int limit ,StorySpec storySpec) {
		if(storySpec==null){
			storySpec=new StorySpec();
		}
		return getDslTemplate().queryPager(start, limit, storySpec, false, new SelectGenerateCallback<StorySpec>() {

			public Select generate(StorySpec t) {
				return MysqlSelect.selectFrom(STORYSPECTABLE).where(
				and(
					STORYSPECTABLE.COMPANY_ID.eq(t.getCompanyId()),
					STORYSPECTABLE.STORY_ID.eq(t.getStoryId()),
					STORYSPECTABLE.STORY_VERSION.eq(t.getStoryVersion()),
					STORYSPECTABLE.STORY_TITLE.eq(t.getStoryTitle()),
					STORYSPECTABLE.STORY_SPEC.eq(t.getStorySpec()),
					STORYSPECTABLE.STORY_VERIFICATION.eq(t.getStoryVerification())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<StorySpec> storySpecs) {
		if (CollectionUtil.isEmpty(storySpecs)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, storySpecs, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(STORYSPECTABLE).values(
					STORYSPECTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
					STORYSPECTABLE.STORY_ID.value(new JdbcNamedParameter("storyId")),
					STORYSPECTABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
					STORYSPECTABLE.STORY_TITLE.value(new JdbcNamedParameter("storyTitle")),
					STORYSPECTABLE.STORY_SPEC.value(new JdbcNamedParameter("storySpec")),
					STORYSPECTABLE.STORY_VERIFICATION.value(new JdbcNamedParameter("storyVerification")));
			}
		});
	}

	public int[] batchInsert(List<StorySpec> storySpecs){
			return batchInsert(true ,storySpecs);
	}

	public int[] batchUpdate(List<StorySpec> storySpecs) {
		if (CollectionUtil.isEmpty(storySpecs)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(storySpecs, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(STORYSPECTABLE).set(
					STORYSPECTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
					STORYSPECTABLE.STORY_ID.value(new JdbcNamedParameter("storyId")),
					STORYSPECTABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
					STORYSPECTABLE.STORY_TITLE.value(new JdbcNamedParameter("storyTitle")),
					STORYSPECTABLE.STORY_SPEC.value(new JdbcNamedParameter("storySpec")),
					STORYSPECTABLE.STORY_VERIFICATION.value(new JdbcNamedParameter("storyVerification"))).where(
				STORYSPECTABLE.COMPANY_ID.eq(new JdbcNamedParameter("companyId")));
			}
		});
	}

	public int[] batchDelete(List<StorySpec> storySpecs) {
		if (CollectionUtil.isEmpty(storySpecs)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(storySpecs, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(STORYSPECTABLE).where(and(
				STORYSPECTABLE.COMPANY_ID.eq(new JdbcNamedParameter("companyId")),
				STORYSPECTABLE.STORY_ID.eq(new JdbcNamedParameter("storyId")),
				STORYSPECTABLE.STORY_VERSION.eq(new JdbcNamedParameter("storyVersion")),
				STORYSPECTABLE.STORY_TITLE.eq(new JdbcNamedParameter("storyTitle")),
				STORYSPECTABLE.STORY_SPEC.eq(new JdbcNamedParameter("storySpec")),
				STORYSPECTABLE.STORY_VERIFICATION.eq(new JdbcNamedParameter("storyVerification"))));
			}
		});
	}

}
