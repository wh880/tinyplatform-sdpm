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

package sdpm.metadata.common.org.tinygroup.alm.quality;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static sdpm.quality.dao.inter.org.tinygroup.alm.quality.constant.TestPlanTable.*;
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
import sdpm.quality.dao.inter.org.tinygroup.alm.quality.pojo.TestPlan;
import sdpm.quality.dao.inter.org.tinygroup.alm.quality.TestPlanDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class TestPlanDaoImpl extends TinyDslDaoSupport implements TestPlanDao {

	public TestPlan add(TestPlan testPlan) {
		return getDslTemplate().insertAndReturnKey(testPlan, new InsertGenerateCallback<TestPlan>() {
			public Insert generate(TestPlan t) {
				Insert insert = insertInto(TESTPLANTABLE).values(
					TESTPLANTABLE.TESTPLAN_ID.value(t.getTestplanID()),
					TESTPLANTABLE.TESTPLAN_NAME.value(t.getTestplanName()),
					TESTPLANTABLE.PRODUCT_ID.value(t.getProductID()),
					TESTPLANTABLE.PROJECT_ID.value(t.getProjectID()),
					TESTPLANTABLE.BUILD.value(t.getBuild()),
					TESTPLANTABLE.TESTPLAN_OWNER.value(t.getTestplanOwner()),
					TESTPLANTABLE.PRIORITY.value(t.getPriority()),
					TESTPLANTABLE.TESTPLAN_BEGIN.value(t.getTestplanBegin()),
					TESTPLANTABLE.TESTPLAN_END.value(t.getTestplanEnd()),
					TESTPLANTABLE.TESTPLAN_DESC.value(t.getTestplanDesc()),
					TESTPLANTABLE.TESTPLAN_REPORT.value(t.getTestplanReport()),
					TESTPLANTABLE.TESTPLAN_STATUS.value(t.getTestplanStatus()),
					TESTPLANTABLE.DELETED.value(t.getDeleted()));
				return insert;
			}
		});
	}

	public int edit(TestPlan testPlan) {
		if(testPlan == null || testPlan.getTestplanID() == null){
			return 0;
		}
		return getDslTemplate().update(testPlan, new UpdateGenerateCallback<TestPlan>() {
			public Update generate(TestPlan t) {
				Update update = update(TESTPLANTABLE).set(
					TESTPLANTABLE.TESTPLAN_NAME.value(t.getTestplanName()),
					TESTPLANTABLE.PRODUCT_ID.value(t.getProductID()),
					TESTPLANTABLE.PROJECT_ID.value(t.getProjectID()),
					TESTPLANTABLE.BUILD.value(t.getBuild()),
					TESTPLANTABLE.TESTPLAN_OWNER.value(t.getTestplanOwner()),
					TESTPLANTABLE.PRIORITY.value(t.getPriority()),
					TESTPLANTABLE.TESTPLAN_BEGIN.value(t.getTestplanBegin()),
					TESTPLANTABLE.TESTPLAN_END.value(t.getTestplanEnd()),
					TESTPLANTABLE.TESTPLAN_DESC.value(t.getTestplanDesc()),
					TESTPLANTABLE.TESTPLAN_REPORT.value(t.getTestplanReport()),
					TESTPLANTABLE.TESTPLAN_STATUS.value(t.getTestplanStatus()),
					TESTPLANTABLE.DELETED.value(t.getDeleted())).where(
					TESTPLANTABLE.TESTPLAN_ID.eq(t.getTestplanID()));
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
				return delete(TESTPLANTABLE).where(TESTPLANTABLE.TESTPLAN_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(TESTPLANTABLE).where(TESTPLANTABLE.TESTPLAN_ID.in(t));
		}
		},pks);
	}

	public TestPlan getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, TestPlan.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(TESTPLANTABLE).where(TESTPLANTABLE.TESTPLAN_ID.eq(t));
			}
		});
	}

	public List<TestPlan> query(TestPlan testPlan) {
		if(testPlan==null){
			testPlan=new TestPlan();
		}
		return getDslTemplate().query(testPlan, new SelectGenerateCallback<TestPlan>() {

			@SuppressWarnings("rawtypes")
			public Select generate(TestPlan t) {
				return selectFrom(TESTPLANTABLE).where(
				and(
					TESTPLANTABLE.TESTPLAN_NAME.eq(t.getTestplanName()),
					TESTPLANTABLE.PRODUCT_ID.eq(t.getProductID()),
					TESTPLANTABLE.PROJECT_ID.eq(t.getProjectID()),
					TESTPLANTABLE.BUILD.eq(t.getBuild()),
					TESTPLANTABLE.TESTPLAN_OWNER.eq(t.getTestplanOwner()),
					TESTPLANTABLE.PRIORITY.eq(t.getPriority()),
					TESTPLANTABLE.TESTPLAN_BEGIN.eq(t.getTestplanBegin()),
					TESTPLANTABLE.TESTPLAN_END.eq(t.getTestplanEnd()),
					TESTPLANTABLE.TESTPLAN_DESC.eq(t.getTestplanDesc()),
					TESTPLANTABLE.TESTPLAN_REPORT.eq(t.getTestplanReport()),
					TESTPLANTABLE.TESTPLAN_STATUS.eq(t.getTestplanStatus()),
					TESTPLANTABLE.DELETED.eq(t.getDeleted())));
			}
		});
	}

	public Pager<TestPlan> queryPager(int start,int limit ,TestPlan testPlan) {
		if(testPlan==null){
			testPlan=new TestPlan();
		}
		return getDslTemplate().queryPager(start, limit, testPlan, false, new SelectGenerateCallback<TestPlan>() {

			public Select generate(TestPlan t) {
				return MysqlSelect.selectFrom(TESTPLANTABLE).where(
				and(
					TESTPLANTABLE.TESTPLAN_NAME.eq(t.getTestplanName()),
					TESTPLANTABLE.PRODUCT_ID.eq(t.getProductID()),
					TESTPLANTABLE.PROJECT_ID.eq(t.getProjectID()),
					TESTPLANTABLE.BUILD.eq(t.getBuild()),
					TESTPLANTABLE.TESTPLAN_OWNER.eq(t.getTestplanOwner()),
					TESTPLANTABLE.PRIORITY.eq(t.getPriority()),
					TESTPLANTABLE.TESTPLAN_BEGIN.eq(t.getTestplanBegin()),
					TESTPLANTABLE.TESTPLAN_END.eq(t.getTestplanEnd()),
					TESTPLANTABLE.TESTPLAN_DESC.eq(t.getTestplanDesc()),
					TESTPLANTABLE.TESTPLAN_REPORT.eq(t.getTestplanReport()),
					TESTPLANTABLE.TESTPLAN_STATUS.eq(t.getTestplanStatus()),
					TESTPLANTABLE.DELETED.eq(t.getDeleted())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<TestPlan> testPlans) {
		if (CollectionUtil.isEmpty(testPlans)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, testPlans, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(TESTPLANTABLE).values(
					TESTPLANTABLE.TESTPLAN_NAME.value(new JdbcNamedParameter("testplanName")),
					TESTPLANTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productID")),
					TESTPLANTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectID")),
					TESTPLANTABLE.BUILD.value(new JdbcNamedParameter("build")),
					TESTPLANTABLE.TESTPLAN_OWNER.value(new JdbcNamedParameter("testplanOwner")),
					TESTPLANTABLE.PRIORITY.value(new JdbcNamedParameter("priority")),
					TESTPLANTABLE.TESTPLAN_BEGIN.value(new JdbcNamedParameter("testplanBegin")),
					TESTPLANTABLE.TESTPLAN_END.value(new JdbcNamedParameter("testplanEnd")),
					TESTPLANTABLE.TESTPLAN_DESC.value(new JdbcNamedParameter("testplanDesc")),
					TESTPLANTABLE.TESTPLAN_REPORT.value(new JdbcNamedParameter("testplanReport")),
					TESTPLANTABLE.TESTPLAN_STATUS.value(new JdbcNamedParameter("testplanStatus")),
					TESTPLANTABLE.DELETED.value(new JdbcNamedParameter("deleted")));
			}
		});
	}

	public int[] batchInsert(List<TestPlan> testPlans){
			return batchInsert(true ,testPlans);
	}

	public int[] batchUpdate(List<TestPlan> testPlans) {
		if (CollectionUtil.isEmpty(testPlans)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(testPlans, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(TESTPLANTABLE).set(
					TESTPLANTABLE.TESTPLAN_NAME.value(new JdbcNamedParameter("testplanName")),
					TESTPLANTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productID")),
					TESTPLANTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectID")),
					TESTPLANTABLE.BUILD.value(new JdbcNamedParameter("build")),
					TESTPLANTABLE.TESTPLAN_OWNER.value(new JdbcNamedParameter("testplanOwner")),
					TESTPLANTABLE.PRIORITY.value(new JdbcNamedParameter("priority")),
					TESTPLANTABLE.TESTPLAN_BEGIN.value(new JdbcNamedParameter("testplanBegin")),
					TESTPLANTABLE.TESTPLAN_END.value(new JdbcNamedParameter("testplanEnd")),
					TESTPLANTABLE.TESTPLAN_DESC.value(new JdbcNamedParameter("testplanDesc")),
					TESTPLANTABLE.TESTPLAN_REPORT.value(new JdbcNamedParameter("testplanReport")),
					TESTPLANTABLE.TESTPLAN_STATUS.value(new JdbcNamedParameter("testplanStatus")),
					TESTPLANTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
				TESTPLANTABLE.TESTPLAN_ID.eq(new JdbcNamedParameter("testplanID")));
			}
		});
	}

	public int[] batchDelete(List<TestPlan> testPlans) {
		if (CollectionUtil.isEmpty(testPlans)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(testPlans, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(TESTPLANTABLE).where(and(
				TESTPLANTABLE.TESTPLAN_ID.eq(new JdbcNamedParameter("testplanID")),
				TESTPLANTABLE.TESTPLAN_NAME.eq(new JdbcNamedParameter("testplanName")),
				TESTPLANTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productID")),
				TESTPLANTABLE.PROJECT_ID.eq(new JdbcNamedParameter("projectID")),
				TESTPLANTABLE.BUILD.eq(new JdbcNamedParameter("build")),
				TESTPLANTABLE.TESTPLAN_OWNER.eq(new JdbcNamedParameter("testplanOwner")),
				TESTPLANTABLE.PRIORITY.eq(new JdbcNamedParameter("priority")),
				TESTPLANTABLE.TESTPLAN_BEGIN.eq(new JdbcNamedParameter("testplanBegin")),
				TESTPLANTABLE.TESTPLAN_END.eq(new JdbcNamedParameter("testplanEnd")),
				TESTPLANTABLE.TESTPLAN_DESC.eq(new JdbcNamedParameter("testplanDesc")),
				TESTPLANTABLE.TESTPLAN_REPORT.eq(new JdbcNamedParameter("testplanReport")),
				TESTPLANTABLE.TESTPLAN_STATUS.eq(new JdbcNamedParameter("testplanStatus")),
				TESTPLANTABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
			}
		});
	}

}
