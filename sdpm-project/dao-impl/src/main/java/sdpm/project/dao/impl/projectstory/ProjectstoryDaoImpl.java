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

package sdpm.project.dao.impl.projectstory;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static sdpm.project.dao.inter.projectstory.constant.ProjectstoryTable.*;
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
import sdpm.project.dao.inter.projectstory.pojo.Projectstory;
import sdpm.project.dao.inter.projectstory.ProjectstoryDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class ProjectstoryDaoImpl extends TinyDslDaoSupport implements ProjectstoryDao {

	public Projectstory add(Projectstory projectstory) {
		return getDslTemplate().insertAndReturnKey(projectstory, new InsertGenerateCallback<Projectstory>() {
			public Insert generate(Projectstory t) {
				Insert insert = insertInto(PROJECTSTORYTABLE).values(
					PROJECTSTORYTABLE.ID.value(t.getId()),
					PROJECTSTORYTABLE.PROJECT_ID.value(t.getProjectId()),
					PROJECTSTORYTABLE.PRODUCT_ID.value(t.getProductId()),
					PROJECTSTORYTABLE.STORY_ID.value(t.getStoryId()),
					PROJECTSTORYTABLE.STORY_VERSION.value(t.getStoryVersion()));
				return insert;
			}
		});
	}

	public int edit(Projectstory projectstory) {
		if(projectstory == null || projectstory.getId() == null){
			return 0;
		}
		return getDslTemplate().update(projectstory, new UpdateGenerateCallback<Projectstory>() {
			public Update generate(Projectstory t) {
				Update update = update(PROJECTSTORYTABLE).set(
					PROJECTSTORYTABLE.PROJECT_ID.value(t.getProjectId()),
					PROJECTSTORYTABLE.PRODUCT_ID.value(t.getProductId()),
					PROJECTSTORYTABLE.STORY_ID.value(t.getStoryId()),
					PROJECTSTORYTABLE.STORY_VERSION.value(t.getStoryVersion())).where(
					PROJECTSTORYTABLE.ID.eq(t.getId()));
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
				return delete(PROJECTSTORYTABLE).where(PROJECTSTORYTABLE.ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(PROJECTSTORYTABLE).where(PROJECTSTORYTABLE.ID.in(t));
		}
		},pks);
	}

	public Projectstory getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Projectstory.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(PROJECTSTORYTABLE).where(PROJECTSTORYTABLE.ID.eq(t));
			}
		});
	}

	public List<Projectstory> query(Projectstory projectstory) {
		if(projectstory==null){
			projectstory=new Projectstory();
		}
		return getDslTemplate().query(projectstory, new SelectGenerateCallback<Projectstory>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Projectstory t) {
				return selectFrom(PROJECTSTORYTABLE).where(
				and(
					PROJECTSTORYTABLE.PROJECT_ID.eq(t.getProjectId()),
					PROJECTSTORYTABLE.PRODUCT_ID.eq(t.getProductId()),
					PROJECTSTORYTABLE.STORY_ID.eq(t.getStoryId()),
					PROJECTSTORYTABLE.STORY_VERSION.eq(t.getStoryVersion())));
			}
		});
	}

	public Pager<Projectstory> queryPager(int start,int limit ,Projectstory projectstory) {
		if(projectstory==null){
			projectstory=new Projectstory();
		}
		return getDslTemplate().queryPager(start, limit, projectstory, false, new SelectGenerateCallback<Projectstory>() {

			public Select generate(Projectstory t) {
				return MysqlSelect.selectFrom(PROJECTSTORYTABLE).where(
				and(
					PROJECTSTORYTABLE.PROJECT_ID.eq(t.getProjectId()),
					PROJECTSTORYTABLE.PRODUCT_ID.eq(t.getProductId()),
					PROJECTSTORYTABLE.STORY_ID.eq(t.getStoryId()),
					PROJECTSTORYTABLE.STORY_VERSION.eq(t.getStoryVersion())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Projectstory> projectstorys) {
		if (CollectionUtil.isEmpty(projectstorys)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, projectstorys, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(PROJECTSTORYTABLE).values(
					PROJECTSTORYTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
					PROJECTSTORYTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					PROJECTSTORYTABLE.STORY_ID.value(new JdbcNamedParameter("storyId")),
					PROJECTSTORYTABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")));
			}
		});
	}

	public int[] batchInsert(List<Projectstory> projectstorys){
			return batchInsert(true ,projectstorys);
	}

	public int[] batchUpdate(List<Projectstory> projectstorys) {
		if (CollectionUtil.isEmpty(projectstorys)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(projectstorys, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(PROJECTSTORYTABLE).set(
					PROJECTSTORYTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
					PROJECTSTORYTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					PROJECTSTORYTABLE.STORY_ID.value(new JdbcNamedParameter("storyId")),
					PROJECTSTORYTABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion"))).where(
				PROJECTSTORYTABLE.ID.eq(new JdbcNamedParameter("id")));
			}
		});
	}

	public int[] batchDelete(List<Projectstory> projectstorys) {
		if (CollectionUtil.isEmpty(projectstorys)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(projectstorys, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(PROJECTSTORYTABLE).where(and(
				PROJECTSTORYTABLE.ID.eq(new JdbcNamedParameter("id")),
				PROJECTSTORYTABLE.PROJECT_ID.eq(new JdbcNamedParameter("projectId")),
				PROJECTSTORYTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
				PROJECTSTORYTABLE.STORY_ID.eq(new JdbcNamedParameter("storyId")),
				PROJECTSTORYTABLE.STORY_VERSION.eq(new JdbcNamedParameter("storyVersion"))));
			}
		});
	}

}
