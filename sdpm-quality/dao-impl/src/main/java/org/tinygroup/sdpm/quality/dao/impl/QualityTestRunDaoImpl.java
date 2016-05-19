/**
 * Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 * <p>
 * Licensed under the GPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/gpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tinygroup.sdpm.quality.dao.impl;

import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.quality.dao.QualityTestRunDao;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.Join;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.quality.dao.constant.QualityTestCaseTable.QUALITY_TEST_CASETABLE;
import static org.tinygroup.sdpm.quality.dao.constant.QualityTestRunTable.QUALITY_TEST_RUNTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class QualityTestRunDaoImpl extends TinyDslDaoSupport implements QualityTestRunDao {

    public QualityTestRun add(QualityTestRun qualityTestRun) {
        return getDslTemplate().insertAndReturnKey(qualityTestRun, new InsertGenerateCallback<QualityTestRun>() {
            public Insert generate(QualityTestRun t) {
                Insert insert = insertInto(QUALITY_TEST_RUNTABLE).values(
                        QUALITY_TEST_RUNTABLE.TASK_ID.value(t.getTaskId()),
                        QUALITY_TEST_RUNTABLE.CASE_ID.value(t.getCaseId()),
                        QUALITY_TEST_RUNTABLE.CASE_VERSION.value(t.getCaseVersion()),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_ASSIGNED_TO.value(t.getTestRunAssignedTo()),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUNNER.value(t.getTestRunLastRunner()),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUN_DATE.value(t.getTestRunLastRunDate()),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUN_RESULT.value(t.getTestRunLastRunResult()),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_STATUS.value(t.getTestRunStatus()));
                return insert;
            }
        });
    }

    public int edit(final QualityTestRun qualityTestRun) {
        if (qualityTestRun == null || qualityTestRun.getTestRunId() == null) {
            return 0;
        }
        return getDslTemplate().update(qualityTestRun, new UpdateGenerateCallback<QualityTestRun>() {
            public Update generate(QualityTestRun t) {
                Update update = update(QUALITY_TEST_RUNTABLE).set(
                        QUALITY_TEST_RUNTABLE.TASK_ID.value(t.getTaskId()),
                        QUALITY_TEST_RUNTABLE.CASE_ID.value(t.getCaseId()),
                        QUALITY_TEST_RUNTABLE.CASE_VERSION.value(t.getCaseVersion()),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_ASSIGNED_TO.value(t.getTestRunAssignedTo()),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUNNER.value(t.getTestRunLastRunner()),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUN_DATE.value(t.getTestRunLastRunDate()),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUN_RESULT.value(t.getTestRunLastRunResult()),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_STATUS.value(t.getTestRunStatus())).where(QUALITY_TEST_RUNTABLE.TEST_RUN_ID.eq(t.getTestRunId()));
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
                return delete(QUALITY_TEST_RUNTABLE).where(QUALITY_TEST_RUNTABLE.TEST_RUN_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(QUALITY_TEST_RUNTABLE).where(QUALITY_TEST_RUNTABLE.TEST_RUN_ID.in(t));
            }
        }, pks);
    }

    public QualityTestRun getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, QualityTestRun.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(QUALITY_TEST_RUNTABLE).where(QUALITY_TEST_RUNTABLE.TEST_RUN_ID.eq(t));
            }
        });
    }

    public List<QualityTestRun> query(QualityTestRun qualityTestRun, final OrderBy... orderArgs) {
        if (qualityTestRun == null) {
            qualityTestRun = new QualityTestRun();
        }
        return getDslTemplate().query(qualityTestRun, new SelectGenerateCallback<QualityTestRun>() {

            @SuppressWarnings("rawtypes")
            public Select generate(QualityTestRun t) {
                Select select = MysqlSelect.select(QUALITY_TEST_RUNTABLE.ALL,
                        QUALITY_TEST_CASETABLE.CASE_TITLE.as("caseTitle"),
                        QUALITY_TEST_CASETABLE.CASE_TYPE.as("caseType"),
                        QUALITY_TEST_CASETABLE.PRIORITY.as("priority"),
                        QUALITY_TEST_CASETABLE.STORY_ID.as("storyId"),
                        QUALITY_TEST_CASETABLE.NO.as("no")).from(
                        QUALITY_TEST_RUNTABLE
                ).join(
                        Join.leftJoin(
                                QUALITY_TEST_CASETABLE,
                                QUALITY_TEST_CASETABLE.CASE_ID.eq(QUALITY_TEST_RUNTABLE.CASE_ID))).where(
                        and(
                                QUALITY_TEST_RUNTABLE.TASK_ID.eq(t.getTaskId()),
                                QUALITY_TEST_RUNTABLE.CASE_ID.eq(t.getCaseId()),
                                QUALITY_TEST_RUNTABLE.CASE_VERSION.eq(t.getCaseVersion()),
                                QUALITY_TEST_RUNTABLE.TEST_RUN_ASSIGNED_TO.eq(t.getTestRunAssignedTo()),
                                QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUNNER.eq(t.getTestRunLastRunner()),
                                QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUN_DATE.eq(t.getTestRunLastRunDate()),
                                QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUN_RESULT.eq(t.getTestRunLastRunResult()),
                                QUALITY_TEST_RUNTABLE.TEST_RUN_STATUS.eq(t.getTestRunStatus()),
                                QUALITY_TEST_CASETABLE.DELETED.eq(0)));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public Pager<QualityTestRun> queryPager(int start, int limit, QualityTestRun qualityTestRun, final OrderBy... orderArgs) {
        if (qualityTestRun == null) {
            qualityTestRun = new QualityTestRun();
        }
        return getDslTemplate().queryPager(start, limit, qualityTestRun, false, new SelectGenerateCallback<QualityTestRun>() {

            public Select generate(QualityTestRun t) {
                Select select = MysqlSelect.selectFrom(QUALITY_TEST_RUNTABLE).where(
                        and(
                                QUALITY_TEST_RUNTABLE.TASK_ID.eq(t.getTaskId()),
                                QUALITY_TEST_RUNTABLE.CASE_ID.eq(t.getCaseId()),
                                QUALITY_TEST_RUNTABLE.CASE_VERSION.eq(t.getCaseVersion()),
                                QUALITY_TEST_RUNTABLE.TEST_RUN_ASSIGNED_TO.eq(t.getTestRunAssignedTo()),
                                QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUNNER.eq(t.getTestRunLastRunner()),
                                QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUN_DATE.eq(t.getTestRunLastRunDate()),
                                QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUN_RESULT.eq(t.getTestRunLastRunResult()),
                                QUALITY_TEST_RUNTABLE.TEST_RUN_STATUS.eq(t.getTestRunStatus())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<QualityTestRun> qualityTestRuns) {
        if (CollectionUtil.isEmpty(qualityTestRuns)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, qualityTestRuns, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(QUALITY_TEST_RUNTABLE).values(
                        QUALITY_TEST_RUNTABLE.TASK_ID.value(new JdbcNamedParameter("taskId")),
                        QUALITY_TEST_RUNTABLE.CASE_ID.value(new JdbcNamedParameter("caseId")),
                        QUALITY_TEST_RUNTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_ASSIGNED_TO.value(new JdbcNamedParameter("testRunAssignedTo")),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUNNER.value(new JdbcNamedParameter("testRunLastRunner")),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUN_DATE.value(new JdbcNamedParameter("testRunLastRunDate")),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUN_RESULT.value(new JdbcNamedParameter("testRunLastRunResult")),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_STATUS.value(new JdbcNamedParameter("testRunStatus")));
            }
        });
    }

    public int[] batchInsert(List<QualityTestRun> qualityTestRuns) {
        return batchInsert(true, qualityTestRuns);
    }

    public int[] batchUpdate(List<QualityTestRun> qualityTestRuns) {
        if (CollectionUtil.isEmpty(qualityTestRuns)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(qualityTestRuns, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(QUALITY_TEST_RUNTABLE).set(
                        QUALITY_TEST_RUNTABLE.TASK_ID.value(new JdbcNamedParameter("taskId")),
                        QUALITY_TEST_RUNTABLE.CASE_ID.value(new JdbcNamedParameter("caseId")),
                        QUALITY_TEST_RUNTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_ASSIGNED_TO.value(new JdbcNamedParameter("testRunAssignedTo")),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUNNER.value(new JdbcNamedParameter("testRunLastRunner")),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUN_DATE.value(new JdbcNamedParameter("testRunLastRunDate")),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUN_RESULT.value(new JdbcNamedParameter("testRunLastRunResult")),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_STATUS.value(new JdbcNamedParameter("testRunStatus"))).where(
                        QUALITY_TEST_RUNTABLE.TEST_RUN_ID.eq(new JdbcNamedParameter("testRunId")));
            }
        });
    }

    public int[] batchDelete(List<QualityTestRun> qualityTestRuns) {
        if (CollectionUtil.isEmpty(qualityTestRuns)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(qualityTestRuns, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(QUALITY_TEST_RUNTABLE).where(and(
                        QUALITY_TEST_RUNTABLE.TEST_RUN_ID.eq(new JdbcNamedParameter("testRunId")),
                        QUALITY_TEST_RUNTABLE.TASK_ID.eq(new JdbcNamedParameter("taskId")),
                        QUALITY_TEST_RUNTABLE.CASE_ID.eq(new JdbcNamedParameter("caseId")),
                        QUALITY_TEST_RUNTABLE.CASE_VERSION.eq(new JdbcNamedParameter("caseVersion")),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_ASSIGNED_TO.eq(new JdbcNamedParameter("testRunAssignedTo")),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUNNER.eq(new JdbcNamedParameter("testRunLastRunner")),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUN_DATE.eq(new JdbcNamedParameter("testRunLastRunDate")),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUN_RESULT.eq(new JdbcNamedParameter("testRunLastRunResult")),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_STATUS.eq(new JdbcNamedParameter("testRunStatus"))));
            }
        });
    }

    private Select addOrderByElements(Select select, OrderBy... orderBies) {
        List<OrderByElement> orderByElements = new ArrayList<OrderByElement>();
        for (int i = 0; orderBies != null && i < orderBies.length; i++) {
            OrderByElement tempElement = null;
            if (orderBies[i] != null) {
                tempElement = orderBies[i].getOrderByElement();
            }
            if (tempElement != null) {
                orderByElements.add(tempElement);
            }
        }
        if (orderByElements.size() > 0) {
            select.orderBy(orderByElements.toArray(new OrderByElement[0]));
        }
        return select;
    }

    public Pager<QualityTestRun> queryPager(int start, int limit, QualityTestRun testRun, Condition condition, OrderBy... orderArgs) {
        Select select = MysqlSelect.select(QUALITY_TEST_RUNTABLE.ALL,
                QUALITY_TEST_CASETABLE.CASE_TITLE.as("caseTitle"),
                QUALITY_TEST_CASETABLE.CASE_TYPE.as("caseType"),
                QUALITY_TEST_CASETABLE.PRIORITY.as("priority"),
                QUALITY_TEST_CASETABLE.NO.as("no")).from(QUALITY_TEST_RUNTABLE).join(
                Join.leftJoin(
                        QUALITY_TEST_CASETABLE,
                        QUALITY_TEST_CASETABLE.CASE_ID.eq(QUALITY_TEST_RUNTABLE.CASE_ID))).where(
                and(
                        condition,
                        QUALITY_TEST_RUNTABLE.TASK_ID.eq(testRun.getTaskId()),
                        QUALITY_TEST_RUNTABLE.CASE_ID.eq(testRun.getCaseId()),
                        QUALITY_TEST_RUNTABLE.CASE_VERSION.eq(testRun.getCaseVersion()),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_ASSIGNED_TO.eq(testRun.getTestRunAssignedTo()),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUNNER.eq(testRun.getTestRunLastRunner()),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUN_DATE.eq(testRun.getTestRunLastRunDate()),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_LAST_RUN_RESULT.eq(testRun.getTestRunLastRunResult()),
                        QUALITY_TEST_RUNTABLE.TEST_RUN_STATUS.eq(testRun.getTestRunStatus()),
                        QUALITY_TEST_CASETABLE.DELETED.eq(0)));
        select = addOrderByElements(select, orderArgs);
        return getDslSession().fetchPage(select, start, limit, false, QualityTestRun.class);
    }

    public Integer deleteByCase(Integer caseId) {
        Delete delete = delete(QUALITY_TEST_RUNTABLE).where(QUALITY_TEST_RUNTABLE.CASE_ID.eq(caseId));
        return getDslSession().execute(delete);
    }

    @Override
    public List<QualityTestRun> findTestRunByTestVersionId(Integer testversionId) {
        Select select = MysqlSelect.select(QUALITY_TEST_RUNTABLE.ALL,
                QUALITY_TEST_CASETABLE.CASE_TITLE.as("caseTitle"),
                QUALITY_TEST_CASETABLE.CASE_TYPE.as("caseType"),
                QUALITY_TEST_CASETABLE.PRIORITY.as("priority"),
                QUALITY_TEST_CASETABLE.NO.as("no")).from(QUALITY_TEST_RUNTABLE).join(
                Join.leftJoin(
                        QUALITY_TEST_CASETABLE,
                        QUALITY_TEST_CASETABLE.CASE_ID.eq(QUALITY_TEST_RUNTABLE.CASE_ID))).where(
                and(
                        QUALITY_TEST_RUNTABLE.TASK_ID.eq(testversionId),
                        QUALITY_TEST_CASETABLE.DELETED.eq(0)));
        return getDslSession().fetchList(select,QualityTestRun.class);
    }
}
