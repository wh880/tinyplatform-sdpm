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
import static org.tinygroup.sdpm.quality.dao.constant.QualityTestTaskTable.*;
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
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestTask;
import org.tinygroup.sdpm.quality.dao.QualityTestTaskDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

@Repository
public class QualityTestTaskDaoImpl extends TinyDslDaoSupport implements QualityTestTaskDao {

	public QualityTestTask add(QualityTestTask qualityTestTask) {
		return getDslTemplate().insertAndReturnKey(qualityTestTask, new InsertGenerateCallback<QualityTestTask>() {
			public Insert generate(QualityTestTask t) {
				Insert insert = insertInto(QUALITY_TESTTASKTABLE).values(
					QUALITY_TESTTASKTABLE.TESTVERSION_ID.value(t.getTestversionId()),
					QUALITY_TESTTASKTABLE.TESTTASK_TITLE.value(t.getTesttaskTitle()),
					QUALITY_TESTTASKTABLE.PRODUCT_ID.value(t.getProductId()),
					QUALITY_TESTTASKTABLE.PROJECT_ID.value(t.getProjectId()),
					QUALITY_TESTTASKTABLE.BUILD.value(t.getBuild()),
					QUALITY_TESTTASKTABLE.TESTTASK_OWNER.value(t.getTesttaskOwner()),
					QUALITY_TESTTASKTABLE.PRIORITY.value(t.getPriority()),
					QUALITY_TESTTASKTABLE.TESTTASK_BEGIN.value(t.getTesttaskBegin()),
					QUALITY_TESTTASKTABLE.TESTTASK_END.value(t.getTesttaskEnd()),
					QUALITY_TESTTASKTABLE.TESTTASK_DESC.value(t.getTesttaskDesc()),
					QUALITY_TESTTASKTABLE.TESTTASK_REPORT.value(t.getTesttaskReport()),
					QUALITY_TESTTASKTABLE.TESTTASK_STATUS.value(t.getTesttaskStatus()),
					QUALITY_TESTTASKTABLE.DELETED.value(t.getDeleted()));
				return insert;
			}
		});
	}

	public int edit(QualityTestTask qualityTestTask) {
		if(qualityTestTask == null || qualityTestTask.getTestversionId() == null){
			return 0;
		}
		return getDslTemplate().update(qualityTestTask, new UpdateGenerateCallback<QualityTestTask>() {
			public Update generate(QualityTestTask t) {
				Update update = update(QUALITY_TESTTASKTABLE).set(
					QUALITY_TESTTASKTABLE.TESTTASK_TITLE.value(t.getTesttaskTitle()),
					QUALITY_TESTTASKTABLE.PRODUCT_ID.value(t.getProductId()),
					QUALITY_TESTTASKTABLE.PROJECT_ID.value(t.getProjectId()),
					QUALITY_TESTTASKTABLE.BUILD.value(t.getBuild()),
					QUALITY_TESTTASKTABLE.TESTTASK_OWNER.value(t.getTesttaskOwner()),
					QUALITY_TESTTASKTABLE.PRIORITY.value(t.getPriority()),
					QUALITY_TESTTASKTABLE.TESTTASK_BEGIN.value(t.getTesttaskBegin()),
					QUALITY_TESTTASKTABLE.TESTTASK_END.value(t.getTesttaskEnd()),
					QUALITY_TESTTASKTABLE.TESTTASK_DESC.value(t.getTesttaskDesc()),
					QUALITY_TESTTASKTABLE.TESTTASK_REPORT.value(t.getTesttaskReport()),
					QUALITY_TESTTASKTABLE.TESTTASK_STATUS.value(t.getTesttaskStatus()),
					QUALITY_TESTTASKTABLE.DELETED.value(t.getDeleted())).where(
					QUALITY_TESTTASKTABLE.TESTVERSION_ID.eq(t.getTestversionId()));
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
				return delete(QUALITY_TESTTASKTABLE).where(QUALITY_TESTTASKTABLE.TESTVERSION_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(QUALITY_TESTTASKTABLE).where(QUALITY_TESTTASKTABLE.TESTVERSION_ID.in(t));
		}
		},pks);
	}

	public QualityTestTask getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, QualityTestTask.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(QUALITY_TESTTASKTABLE).where(QUALITY_TESTTASKTABLE.TESTVERSION_ID.eq(t));
			}
		});
	}

	public List<QualityTestTask> query(QualityTestTask qualityTestTask ,final OrderBy... orderArgs) {
		if(qualityTestTask==null){
			qualityTestTask=new QualityTestTask();
		}
		return getDslTemplate().query(qualityTestTask, new SelectGenerateCallback<QualityTestTask>() {

			@SuppressWarnings("rawtypes")
			public Select generate(QualityTestTask t) {
				Select select = selectFrom(QUALITY_TESTTASKTABLE).where(
				and(
					QUALITY_TESTTASKTABLE.TESTTASK_TITLE.eq(t.getTesttaskTitle()),
					QUALITY_TESTTASKTABLE.PRODUCT_ID.eq(t.getProductId()),
					QUALITY_TESTTASKTABLE.PROJECT_ID.eq(t.getProjectId()),
					QUALITY_TESTTASKTABLE.BUILD.eq(t.getBuild()),
					QUALITY_TESTTASKTABLE.TESTTASK_OWNER.eq(t.getTesttaskOwner()),
					QUALITY_TESTTASKTABLE.PRIORITY.eq(t.getPriority()),
					QUALITY_TESTTASKTABLE.TESTTASK_BEGIN.eq(t.getTesttaskBegin()),
					QUALITY_TESTTASKTABLE.TESTTASK_END.eq(t.getTesttaskEnd()),
					QUALITY_TESTTASKTABLE.TESTTASK_DESC.eq(t.getTesttaskDesc()),
					QUALITY_TESTTASKTABLE.TESTTASK_REPORT.eq(t.getTesttaskReport()),
					QUALITY_TESTTASKTABLE.TESTTASK_STATUS.eq(t.getTesttaskStatus()),
					QUALITY_TESTTASKTABLE.DELETED.eq(t.getDeleted())));
			return addOrderByElements(select, orderArgs);
			}
		});
	}

	public Pager<QualityTestTask> queryPager(int start,int limit ,QualityTestTask qualityTestTask ,final OrderBy... orderArgs) {
		if(qualityTestTask==null){
			qualityTestTask=new QualityTestTask();
		}
		return getDslTemplate().queryPager(start, limit, qualityTestTask, false, new SelectGenerateCallback<QualityTestTask>() {

			public Select generate(QualityTestTask t) {
				Select select = MysqlSelect.selectFrom(QUALITY_TESTTASKTABLE).where(
				and(
					QUALITY_TESTTASKTABLE.TESTTASK_TITLE.eq(t.getTesttaskTitle()),
					QUALITY_TESTTASKTABLE.PRODUCT_ID.eq(t.getProductId()),
					QUALITY_TESTTASKTABLE.PROJECT_ID.eq(t.getProjectId()),
					QUALITY_TESTTASKTABLE.BUILD.eq(t.getBuild()),
					QUALITY_TESTTASKTABLE.TESTTASK_OWNER.eq(t.getTesttaskOwner()),
					QUALITY_TESTTASKTABLE.PRIORITY.eq(t.getPriority()),
					QUALITY_TESTTASKTABLE.TESTTASK_BEGIN.eq(t.getTesttaskBegin()),
					QUALITY_TESTTASKTABLE.TESTTASK_END.eq(t.getTesttaskEnd()),
					QUALITY_TESTTASKTABLE.TESTTASK_DESC.eq(t.getTesttaskDesc()),
					QUALITY_TESTTASKTABLE.TESTTASK_REPORT.eq(t.getTesttaskReport()),
					QUALITY_TESTTASKTABLE.TESTTASK_STATUS.eq(t.getTesttaskStatus()),
					QUALITY_TESTTASKTABLE.DELETED.eq(t.getDeleted())));
			return addOrderByElements(select, orderArgs);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<QualityTestTask> qualityTestTasks) {
		if (CollectionUtil.isEmpty(qualityTestTasks)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, qualityTestTasks, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(QUALITY_TESTTASKTABLE).values(
					QUALITY_TESTTASKTABLE.TESTTASK_TITLE.value(new JdbcNamedParameter("testtaskTitle")),
					QUALITY_TESTTASKTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					QUALITY_TESTTASKTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
					QUALITY_TESTTASKTABLE.BUILD.value(new JdbcNamedParameter("build")),
					QUALITY_TESTTASKTABLE.TESTTASK_OWNER.value(new JdbcNamedParameter("testtaskOwner")),
					QUALITY_TESTTASKTABLE.PRIORITY.value(new JdbcNamedParameter("priority")),
					QUALITY_TESTTASKTABLE.TESTTASK_BEGIN.value(new JdbcNamedParameter("testtaskBegin")),
					QUALITY_TESTTASKTABLE.TESTTASK_END.value(new JdbcNamedParameter("testtaskEnd")),
					QUALITY_TESTTASKTABLE.TESTTASK_DESC.value(new JdbcNamedParameter("testtaskDesc")),
					QUALITY_TESTTASKTABLE.TESTTASK_REPORT.value(new JdbcNamedParameter("testtaskReport")),
					QUALITY_TESTTASKTABLE.TESTTASK_STATUS.value(new JdbcNamedParameter("testtaskStatus")),
					QUALITY_TESTTASKTABLE.DELETED.value(new JdbcNamedParameter("deleted")));
			}
		});
	}

	public int[] batchInsert(List<QualityTestTask> qualityTestTasks){
			return batchInsert(true ,qualityTestTasks);
	}

	public int[] batchUpdate(List<QualityTestTask> qualityTestTasks) {
		if (CollectionUtil.isEmpty(qualityTestTasks)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(qualityTestTasks, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(QUALITY_TESTTASKTABLE).set(
					QUALITY_TESTTASKTABLE.TESTTASK_TITLE.value(new JdbcNamedParameter("testtaskTitle")),
					QUALITY_TESTTASKTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					QUALITY_TESTTASKTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
					QUALITY_TESTTASKTABLE.BUILD.value(new JdbcNamedParameter("build")),
					QUALITY_TESTTASKTABLE.TESTTASK_OWNER.value(new JdbcNamedParameter("testtaskOwner")),
					QUALITY_TESTTASKTABLE.PRIORITY.value(new JdbcNamedParameter("priority")),
					QUALITY_TESTTASKTABLE.TESTTASK_BEGIN.value(new JdbcNamedParameter("testtaskBegin")),
					QUALITY_TESTTASKTABLE.TESTTASK_END.value(new JdbcNamedParameter("testtaskEnd")),
					QUALITY_TESTTASKTABLE.TESTTASK_DESC.value(new JdbcNamedParameter("testtaskDesc")),
					QUALITY_TESTTASKTABLE.TESTTASK_REPORT.value(new JdbcNamedParameter("testtaskReport")),
					QUALITY_TESTTASKTABLE.TESTTASK_STATUS.value(new JdbcNamedParameter("testtaskStatus")),
					QUALITY_TESTTASKTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
				QUALITY_TESTTASKTABLE.TESTVERSION_ID.eq(new JdbcNamedParameter("testversionId")));
			}
		});
	}

	public int[] batchDelete(List<QualityTestTask> qualityTestTasks) {
		if (CollectionUtil.isEmpty(qualityTestTasks)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(qualityTestTasks, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(QUALITY_TESTTASKTABLE).where(and(
				QUALITY_TESTTASKTABLE.TESTVERSION_ID.eq(new JdbcNamedParameter("testversionId")),
				QUALITY_TESTTASKTABLE.TESTTASK_TITLE.eq(new JdbcNamedParameter("testtaskTitle")),
				QUALITY_TESTTASKTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
				QUALITY_TESTTASKTABLE.PROJECT_ID.eq(new JdbcNamedParameter("projectId")),
				QUALITY_TESTTASKTABLE.BUILD.eq(new JdbcNamedParameter("build")),
				QUALITY_TESTTASKTABLE.TESTTASK_OWNER.eq(new JdbcNamedParameter("testtaskOwner")),
				QUALITY_TESTTASKTABLE.PRIORITY.eq(new JdbcNamedParameter("priority")),
				QUALITY_TESTTASKTABLE.TESTTASK_BEGIN.eq(new JdbcNamedParameter("testtaskBegin")),
				QUALITY_TESTTASKTABLE.TESTTASK_END.eq(new JdbcNamedParameter("testtaskEnd")),
				QUALITY_TESTTASKTABLE.TESTTASK_DESC.eq(new JdbcNamedParameter("testtaskDesc")),
				QUALITY_TESTTASKTABLE.TESTTASK_REPORT.eq(new JdbcNamedParameter("testtaskReport")),
				QUALITY_TESTTASKTABLE.TESTTASK_STATUS.eq(new JdbcNamedParameter("testtaskStatus")),
				QUALITY_TESTTASKTABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
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
