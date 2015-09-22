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

package org.tinygroup.sdpm.quality.dao.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.quality.dao.constant.TestTaskTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.List;

import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.Pager;
import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
	import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.sdpm.quality.dao.pojo.TestTask;
import org.tinygroup.sdpm.common.log.annotation.LogClass;
import org.tinygroup.sdpm.common.log.annotation.LogMethod;
import org.tinygroup.sdpm.quality.dao.TestTaskDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;


@LogClass("testTask")
@Repository
public class TestTaskDaoImpl extends TinyDslDaoSupport implements TestTaskDao {
	
	@LogMethod("add")
	public TestTask add(TestTask testTask) {
		return getDslTemplate().insertAndReturnKey(testTask, new InsertGenerateCallback<TestTask>() {
			public Insert generate(TestTask t) {
				Insert insert = insertInto(TESTTASKTABLE).values(
					TESTTASKTABLE.TESTVERSION_ID.value(t.getTestversionId()),
					TESTTASKTABLE.TESTTASK_TITLE.value(t.getTesttaskTitle()),
					TESTTASKTABLE.PRODUCT_ID.value(t.getProductId()),
					TESTTASKTABLE.PROJECT_ID.value(t.getProjectId()),
					TESTTASKTABLE.BUILD.value(t.getBuild()),
					TESTTASKTABLE.TESTTASK_OWNER.value(t.getTesttaskOwner()),
					TESTTASKTABLE.PRIORITY.value(t.getPriority()),
					TESTTASKTABLE.TESTTASK_BEGIN.value(t.getTesttaskBegin()),
					TESTTASKTABLE.TESTTASK_END.value(t.getTesttaskEnd()),
					TESTTASKTABLE.TESTTASK_DESC.value(t.getTesttaskDesc()),
					TESTTASKTABLE.TESTTASK_REPORT.value(t.getTesttaskReport()),
					TESTTASKTABLE.TESTTASK_STATUS.value(t.getTesttaskStatus()),
					TESTTASKTABLE.DELETED.value(t.getDeleted()));
				return insert;
			}
		});
	}
	@LogMethod("edit")
	public int edit(TestTask testTask) {
		if(testTask == null || testTask.getTestversionId() == null){
			return 0;
		}
		return getDslTemplate().update(testTask, new UpdateGenerateCallback<TestTask>() {
			public Update generate(TestTask t) {
				Update update = update(TESTTASKTABLE).set(
					TESTTASKTABLE.TESTTASK_TITLE.value(t.getTesttaskTitle()),
					TESTTASKTABLE.PRODUCT_ID.value(t.getProductId()),
					TESTTASKTABLE.PROJECT_ID.value(t.getProjectId()),
					TESTTASKTABLE.BUILD.value(t.getBuild()),
					TESTTASKTABLE.TESTTASK_OWNER.value(t.getTesttaskOwner()),
					TESTTASKTABLE.PRIORITY.value(t.getPriority()),
					TESTTASKTABLE.TESTTASK_BEGIN.value(t.getTesttaskBegin()),
					TESTTASKTABLE.TESTTASK_END.value(t.getTesttaskEnd()),
					TESTTASKTABLE.TESTTASK_DESC.value(t.getTesttaskDesc()),
					TESTTASKTABLE.TESTTASK_REPORT.value(t.getTesttaskReport()),
					TESTTASKTABLE.TESTTASK_STATUS.value(t.getTesttaskStatus()),
					TESTTASKTABLE.DELETED.value(t.getDeleted())).where(
					TESTTASKTABLE.TESTVERSION_ID.eq(t.getTestversionId()));
				return update;
			}
		});
	}
	@LogMethod("deleteByKey")
	public int deleteByKey(Integer pk){
		if(pk == null){
			return 0;
		}
		return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
			public Delete generate(Serializable pk) {
				return delete(TESTTASKTABLE).where(TESTTASKTABLE.TESTVERSION_ID.eq(pk));
			}
		});
	}
	@LogMethod("deleteByKeys")
	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(TESTTASKTABLE).where(TESTTASKTABLE.TESTVERSION_ID.in(t));
		}
		},pks);
	}

	public TestTask getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, TestTask.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(TESTTASKTABLE).where(TESTTASKTABLE.TESTVERSION_ID.eq(t));
			}
		});
	}

	public List<TestTask> query(TestTask testTask ,final OrderBy... orderBies) {
		if(testTask==null){
			testTask=new TestTask();
		}
		return getDslTemplate().query(testTask, new SelectGenerateCallback<TestTask>() {

			@SuppressWarnings("rawtypes")
			public Select generate(TestTask t) {
				Select select = selectFrom(TESTTASKTABLE).where(
				and(
					TESTTASKTABLE.TESTTASK_TITLE.eq(t.getTesttaskTitle()),
					TESTTASKTABLE.PRODUCT_ID.eq(t.getProductId()),
					TESTTASKTABLE.PROJECT_ID.eq(t.getProjectId()),
					TESTTASKTABLE.BUILD.eq(t.getBuild()),
					TESTTASKTABLE.TESTTASK_OWNER.eq(t.getTesttaskOwner()),
					TESTTASKTABLE.PRIORITY.eq(t.getPriority()),
					TESTTASKTABLE.TESTTASK_BEGIN.eq(t.getTesttaskBegin()),
					TESTTASKTABLE.TESTTASK_END.eq(t.getTesttaskEnd()),
					TESTTASKTABLE.TESTTASK_DESC.eq(t.getTesttaskDesc()),
					TESTTASKTABLE.TESTTASK_REPORT.eq(t.getTesttaskReport()),
					TESTTASKTABLE.TESTTASK_STATUS.eq(t.getTesttaskStatus()),
					TESTTASKTABLE.DELETED.eq(t.getDeleted())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<TestTask> queryPager(int start,int limit ,TestTask testTask ,final OrderBy... orderBies) {
		if(testTask==null){
			testTask=new TestTask();
		}
		return getDslTemplate().queryPager(start, limit, testTask, false, new SelectGenerateCallback<TestTask>() {

			public Select generate(TestTask t) {
				Select select = MysqlSelect.selectFrom(TESTTASKTABLE).where(
				and(
					TESTTASKTABLE.TESTTASK_TITLE.eq(t.getTesttaskTitle()),
					TESTTASKTABLE.PRODUCT_ID.eq(t.getProductId()),
					TESTTASKTABLE.PROJECT_ID.eq(t.getProjectId()),
					TESTTASKTABLE.BUILD.eq(t.getBuild()),
					TESTTASKTABLE.TESTTASK_OWNER.eq(t.getTesttaskOwner()),
					TESTTASKTABLE.PRIORITY.eq(t.getPriority()),
					TESTTASKTABLE.TESTTASK_BEGIN.eq(t.getTesttaskBegin()),
					TESTTASKTABLE.TESTTASK_END.eq(t.getTesttaskEnd()),
					TESTTASKTABLE.TESTTASK_DESC.eq(t.getTesttaskDesc()),
					TESTTASKTABLE.TESTTASK_REPORT.eq(t.getTesttaskReport()),
					TESTTASKTABLE.TESTTASK_STATUS.eq(t.getTesttaskStatus()),
					TESTTASKTABLE.DELETED.eq(t.getDeleted())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<TestTask> testTasks) {
		if (CollectionUtil.isEmpty(testTasks)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, testTasks, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(TESTTASKTABLE).values(
					TESTTASKTABLE.TESTTASK_TITLE.value(new JdbcNamedParameter("testtaskTitle")),
					TESTTASKTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					TESTTASKTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
					TESTTASKTABLE.BUILD.value(new JdbcNamedParameter("build")),
					TESTTASKTABLE.TESTTASK_OWNER.value(new JdbcNamedParameter("testtaskOwner")),
					TESTTASKTABLE.PRIORITY.value(new JdbcNamedParameter("priority")),
					TESTTASKTABLE.TESTTASK_BEGIN.value(new JdbcNamedParameter("testtaskBegin")),
					TESTTASKTABLE.TESTTASK_END.value(new JdbcNamedParameter("testtaskEnd")),
					TESTTASKTABLE.TESTTASK_DESC.value(new JdbcNamedParameter("testtaskDesc")),
					TESTTASKTABLE.TESTTASK_REPORT.value(new JdbcNamedParameter("testtaskReport")),
					TESTTASKTABLE.TESTTASK_STATUS.value(new JdbcNamedParameter("testtaskStatus")),
					TESTTASKTABLE.DELETED.value(new JdbcNamedParameter("deleted")));
			}
		});
	}

	public int[] batchInsert(List<TestTask> testTasks){
			return batchInsert(true ,testTasks);
	}
	@LogMethod("batchUpdate")
	public int[] batchUpdate(List<TestTask> testTasks) {
		if (CollectionUtil.isEmpty(testTasks)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(testTasks, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(TESTTASKTABLE).set(
					TESTTASKTABLE.TESTTASK_TITLE.value(new JdbcNamedParameter("testtaskTitle")),
					TESTTASKTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					TESTTASKTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
					TESTTASKTABLE.BUILD.value(new JdbcNamedParameter("build")),
					TESTTASKTABLE.TESTTASK_OWNER.value(new JdbcNamedParameter("testtaskOwner")),
					TESTTASKTABLE.PRIORITY.value(new JdbcNamedParameter("priority")),
					TESTTASKTABLE.TESTTASK_BEGIN.value(new JdbcNamedParameter("testtaskBegin")),
					TESTTASKTABLE.TESTTASK_END.value(new JdbcNamedParameter("testtaskEnd")),
					TESTTASKTABLE.TESTTASK_DESC.value(new JdbcNamedParameter("testtaskDesc")),
					TESTTASKTABLE.TESTTASK_REPORT.value(new JdbcNamedParameter("testtaskReport")),
					TESTTASKTABLE.TESTTASK_STATUS.value(new JdbcNamedParameter("testtaskStatus")),
					TESTTASKTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
				TESTTASKTABLE.TESTVERSION_ID.eq(new JdbcNamedParameter("testversionId")));
			}
		});
	}
	@LogMethod("batchDelete")
	public int[] batchDelete(List<TestTask> testTasks) {
		if (CollectionUtil.isEmpty(testTasks)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(testTasks, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(TESTTASKTABLE).where(and(
				TESTTASKTABLE.TESTVERSION_ID.eq(new JdbcNamedParameter("testversionId")),
				TESTTASKTABLE.TESTTASK_TITLE.eq(new JdbcNamedParameter("testtaskTitle")),
				TESTTASKTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
				TESTTASKTABLE.PROJECT_ID.eq(new JdbcNamedParameter("projectId")),
				TESTTASKTABLE.BUILD.eq(new JdbcNamedParameter("build")),
				TESTTASKTABLE.TESTTASK_OWNER.eq(new JdbcNamedParameter("testtaskOwner")),
				TESTTASKTABLE.PRIORITY.eq(new JdbcNamedParameter("priority")),
				TESTTASKTABLE.TESTTASK_BEGIN.eq(new JdbcNamedParameter("testtaskBegin")),
				TESTTASKTABLE.TESTTASK_END.eq(new JdbcNamedParameter("testtaskEnd")),
				TESTTASKTABLE.TESTTASK_DESC.eq(new JdbcNamedParameter("testtaskDesc")),
				TESTTASKTABLE.TESTTASK_REPORT.eq(new JdbcNamedParameter("testtaskReport")),
				TESTTASKTABLE.TESTTASK_STATUS.eq(new JdbcNamedParameter("testtaskStatus")),
				TESTTASKTABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
			}
		});
	}

	private  Select addOrderByElements(Select select ,OrderBy... orderBies){
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
