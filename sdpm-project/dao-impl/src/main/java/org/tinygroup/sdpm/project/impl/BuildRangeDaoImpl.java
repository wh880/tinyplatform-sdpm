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

package org.tinygroup.sdpm.project.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.project.constant.BuildRangeTable.*;
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
import org.tinygroup.sdpm.project.pojo.BuildRange;
import org.tinygroup.sdpm.project.inter.BuildRangeDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class BuildRangeDaoImpl extends TinyDslDaoSupport implements BuildRangeDao {

	public BuildRange insertObject(BuildRange buildRange) {
		return getDslTemplate().insertObject(buildRange, new InsertGenerateCallback<BuildRange>() {
			public Insert generate(BuildRange t) {
				Insert insert = insertInto(BUILDRANGETABLE).values(
					BUILDRANGETABLE.BUILD_RANGE_ID.value(t.getBuildRangeId()),
					BUILDRANGETABLE.BUILD_ID.value(t.getBuildId()),
					BUILDRANGETABLE.OBJECT_ID.value(t.getObjectId()),
					BUILDRANGETABLE.OBJECT_TYPE.value(t.getObjectType()));
				return insert;
			}
		});
	}

	public BuildRange insertObject(boolean autoGeneratedKeys ,BuildRange buildRange) {
		return getDslTemplate().insertObjectAndReturnKey(autoGeneratedKeys ,buildRange, new InsertGenerateCallback<BuildRange>() {

			public Insert generate(BuildRange t) {
				Insert insert = insertInto(BUILDRANGETABLE).values(
					BUILDRANGETABLE.BUILD_RANGE_ID.value(t.getBuildRangeId()),
					BUILDRANGETABLE.BUILD_ID.value(t.getBuildId()),
					BUILDRANGETABLE.OBJECT_ID.value(t.getObjectId()),
					BUILDRANGETABLE.OBJECT_TYPE.value(t.getObjectType()));
				return insert;
			}
		});
	}

	public int updateObject(BuildRange buildRange) {
		return getDslTemplate().updateObject(buildRange, new UpdateGenerateCallback<BuildRange>() {
			public Update generate(BuildRange t) {
				Update update = update(BUILDRANGETABLE).set(
					BUILDRANGETABLE.BUILD_ID.value(t.getBuildId()),
					BUILDRANGETABLE.OBJECT_ID.value(t.getObjectId()),
					BUILDRANGETABLE.OBJECT_TYPE.value(t.getObjectType())).where(
					BUILDRANGETABLE.BUILD_RANGE_ID.eq(t.getBuildRangeId()));
				return update;
			}
		});
	}

	public int deleteObject(Serializable pk){
		return getDslTemplate().deleteObject(pk, new DeleteGenerateCallback<Serializable>() {
			public Delete generate(Serializable pk) {
				return delete(BUILDRANGETABLE).where(BUILDRANGETABLE.BUILD_RANGE_ID.eq(pk));
			}
		});
	}

	public int deleteObjects(Serializable... pks) {
		return getDslTemplate().deleteObjects(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(BUILDRANGETABLE).where(BUILDRANGETABLE.BUILD_RANGE_ID.in(t));
		}
		},pks);
	}

	public BuildRange getObjectById(Serializable pk) {
		return getDslTemplate().getObjectById(pk, BuildRange.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(BUILDRANGETABLE).where(BUILDRANGETABLE.BUILD_RANGE_ID.eq(t));
			}
		});
	}

	public List<BuildRange> queryObjects(BuildRange buildRange) {
		if(buildRange==null){
			buildRange=new BuildRange();
		}
		return getDslTemplate().queryObjects(buildRange, new SelectGenerateCallback<BuildRange>() {

			@SuppressWarnings("rawtypes")
			public Select generate(BuildRange t) {
				return selectFrom(BUILDRANGETABLE).where(
				and(
					BUILDRANGETABLE.BUILD_ID.eq(t.getBuildId()),
					BUILDRANGETABLE.OBJECT_ID.eq(t.getObjectId()),
					BUILDRANGETABLE.OBJECT_TYPE.eq(t.getObjectType())));
			}
		});
	}

	public Pager<BuildRange> queryObjectsForPage(int start,int limit ,BuildRange buildRange) {
		if(buildRange==null){
			buildRange=new BuildRange();
		}
		return getDslTemplate().queryObjectsForPage(start, limit, buildRange, false, new SelectGenerateCallback<BuildRange>() {

			public Select generate(BuildRange t) {
				return MysqlSelect.selectFrom(BUILDRANGETABLE).where(
				and(
					BUILDRANGETABLE.BUILD_ID.eq(t.getBuildId()),
					BUILDRANGETABLE.OBJECT_ID.eq(t.getObjectId()),
					BUILDRANGETABLE.OBJECT_TYPE.eq(t.getObjectType())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<BuildRange> buildRanges) {
		if (CollectionUtil.isEmpty(buildRanges)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, buildRanges, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(BUILDRANGETABLE).values(
					BUILDRANGETABLE.BUILD_ID.value(new JdbcNamedParameter("buildId")),
					BUILDRANGETABLE.OBJECT_ID.value(new JdbcNamedParameter("objectId")),
					BUILDRANGETABLE.OBJECT_TYPE.value(new JdbcNamedParameter("objectType")));
			}
		});
	}

	public int[] batchInsert(List<BuildRange> buildRanges){
			return batchInsert(true ,buildRanges);
	}

	public int[] batchUpdate(List<BuildRange> buildRanges) {
		if (CollectionUtil.isEmpty(buildRanges)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(buildRanges, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(BUILDRANGETABLE).set(
					BUILDRANGETABLE.BUILD_ID.value(new JdbcNamedParameter("buildId")),
					BUILDRANGETABLE.OBJECT_ID.value(new JdbcNamedParameter("objectId")),
					BUILDRANGETABLE.OBJECT_TYPE.value(new JdbcNamedParameter("objectType"))).where(
				BUILDRANGETABLE.BUILD_RANGE_ID.eq(new JdbcNamedParameter("buildRangeId")));
			}
		});
	}

	public int[] batchDelete(List<BuildRange> buildRanges) {
		if (CollectionUtil.isEmpty(buildRanges)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(buildRanges, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(BUILDRANGETABLE).where(and(
				BUILDRANGETABLE.BUILD_RANGE_ID.eq(new JdbcNamedParameter("buildRangeId")),
				BUILDRANGETABLE.BUILD_ID.eq(new JdbcNamedParameter("buildId")),
				BUILDRANGETABLE.OBJECT_ID.eq(new JdbcNamedParameter("objectId")),
				BUILDRANGETABLE.OBJECT_TYPE.eq(new JdbcNamedParameter("objectType"))));
			}
		});
	}

}
