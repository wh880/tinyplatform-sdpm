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
import org.tinygroup.sdpm.dao.update.UpdateUtil;
import org.tinygroup.sdpm.quality.dao.QualityTestTaskDao;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestTask;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.Join;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.project.dao.constant.ProjectBuildTable.PROJECT_BUILDTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectTable.PROJECTTABLE;
import static org.tinygroup.sdpm.quality.dao.constant.QualityTestTaskTable.QUALITY_TEST_TASKTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.select;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class QualityTestTaskDaoImpl extends TinyDslDaoSupport implements QualityTestTaskDao {

    public QualityTestTask add(QualityTestTask qualityTestTask) {
        return getDslTemplate().insertAndReturnKey(qualityTestTask, new InsertGenerateCallback<QualityTestTask>() {
            public Insert generate(QualityTestTask t) {
                Insert insert = insertInto(QUALITY_TEST_TASKTABLE).values(
                        QUALITY_TEST_TASKTABLE.TESTVERSION_ID.value(t.getTestversionId()),
                        QUALITY_TEST_TASKTABLE.TESTTASK_TITLE.value(t.getTesttaskTitle()),
                        QUALITY_TEST_TASKTABLE.PRODUCT_ID.value(t.getProductId()),
                        QUALITY_TEST_TASKTABLE.PROJECT_ID.value(t.getProjectId()),
                        QUALITY_TEST_TASKTABLE.BUILD_NAME.value(t.getBuildName()),
                        QUALITY_TEST_TASKTABLE.TESTTASK_OWNER.value(t.getTesttaskOwner()),
                        QUALITY_TEST_TASKTABLE.PRIORITY.value(t.getPriority()),
                        QUALITY_TEST_TASKTABLE.TESTTASK_BEGIN.value(t.getTesttaskBegin()),
                        QUALITY_TEST_TASKTABLE.TESTTASK_END.value(t.getTesttaskEnd()),
                        QUALITY_TEST_TASKTABLE.TESTTASK_DESC.value(t.getTesttaskDesc()),
                        QUALITY_TEST_TASKTABLE.TESTTASK_REPORT.value(t.getTesttaskReport()),
                        QUALITY_TEST_TASKTABLE.TESTTASK_STATUS.value(t.getTesttaskStatus()),
                        QUALITY_TEST_TASKTABLE.DELETED.value(t.getDeleted()),
                        QUALITY_TEST_TASKTABLE.NO.value(t.getNo()));
                return insert;
            }
        });
    }

    public int edit(final QualityTestTask qualityTestTask) {
        if (qualityTestTask == null || qualityTestTask.getTestversionId() == null) {
            return 0;
        }
        return getDslTemplate().update(qualityTestTask, new UpdateGenerateCallback<QualityTestTask>() {
            public Update generate(QualityTestTask t) {
                Update update = UpdateUtil.getUpdate(QUALITY_TEST_TASKTABLE, qualityTestTask);
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
                return delete(QUALITY_TEST_TASKTABLE).where(QUALITY_TEST_TASKTABLE.TESTVERSION_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(QUALITY_TEST_TASKTABLE).where(QUALITY_TEST_TASKTABLE.TESTVERSION_ID.in(t));
            }
        }, pks);
    }

    public QualityTestTask getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, QualityTestTask.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return select(QUALITY_TEST_TASKTABLE.ALL,
                        PROJECT_BUILDTABLE.BUILD_NAME.as("searchBuildName"),
                        PROJECTTABLE.PROJECT_NAME.as("projectName")).
                        from(QUALITY_TEST_TASKTABLE).join(
                        Join.leftJoin(PROJECTTABLE, PROJECTTABLE.PROJECT_ID.eq(QUALITY_TEST_TASKTABLE.PROJECT_ID)),
                        Join.leftJoin(PROJECT_BUILDTABLE, PROJECT_BUILDTABLE.BUILD_ID.eq(QUALITY_TEST_TASKTABLE.BUILD_NAME))
                ).
                        where(QUALITY_TEST_TASKTABLE.TESTVERSION_ID.eq(t));
            }
        });
    }

    public List<QualityTestTask> query(QualityTestTask qualityTestTask, final OrderBy... orderArgs) {
        if (qualityTestTask == null) {
            qualityTestTask = new QualityTestTask();
        }
        return getDslTemplate().query(qualityTestTask, new SelectGenerateCallback<QualityTestTask>() {

            @SuppressWarnings("rawtypes")
            public Select generate(QualityTestTask t) {
                Select select = selectFrom(QUALITY_TEST_TASKTABLE).where(
                        and(
                                QUALITY_TEST_TASKTABLE.TESTTASK_TITLE.eq(t.getTesttaskTitle()),
                                QUALITY_TEST_TASKTABLE.PRODUCT_ID.eq(t.getProductId()),
                                QUALITY_TEST_TASKTABLE.PROJECT_ID.eq(t.getProjectId()),
                                QUALITY_TEST_TASKTABLE.BUILD_NAME.eq(t.getBuildName()),
                                QUALITY_TEST_TASKTABLE.TESTTASK_OWNER.eq(t.getTesttaskOwner()),
                                QUALITY_TEST_TASKTABLE.PRIORITY.eq(t.getPriority()),
                                QUALITY_TEST_TASKTABLE.TESTTASK_BEGIN.eq(t.getTesttaskBegin()),
                                QUALITY_TEST_TASKTABLE.TESTTASK_END.eq(t.getTesttaskEnd()),
                                QUALITY_TEST_TASKTABLE.TESTTASK_DESC.eq(t.getTesttaskDesc()),
                                QUALITY_TEST_TASKTABLE.TESTTASK_REPORT.eq(t.getTesttaskReport()),
                                QUALITY_TEST_TASKTABLE.TESTTASK_STATUS.eq(t.getTesttaskStatus()),
                                QUALITY_TEST_TASKTABLE.DELETED.eq(t.getDeleted()),
                                QUALITY_TEST_TASKTABLE.NO.eq(t.getNo())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public Pager<QualityTestTask> queryPager(int start, int limit, QualityTestTask qualityTestTask, final OrderBy... orderArgs) {
        if (qualityTestTask == null) {
            qualityTestTask = new QualityTestTask();
        }
        return getDslTemplate().queryPager(start, limit, qualityTestTask, false, new SelectGenerateCallback<QualityTestTask>() {

            public Select generate(QualityTestTask t) {
                Select select = MysqlSelect.selectFrom(QUALITY_TEST_TASKTABLE).where(
                        and(
                                QUALITY_TEST_TASKTABLE.TESTTASK_TITLE.eq(t.getTesttaskTitle()),
                                QUALITY_TEST_TASKTABLE.PRODUCT_ID.eq(t.getProductId()),
                                QUALITY_TEST_TASKTABLE.PROJECT_ID.eq(t.getProjectId()),
                                QUALITY_TEST_TASKTABLE.BUILD_NAME.eq(t.getBuildName()),
                                QUALITY_TEST_TASKTABLE.TESTTASK_OWNER.eq(t.getTesttaskOwner()),
                                QUALITY_TEST_TASKTABLE.PRIORITY.eq(t.getPriority()),
                                QUALITY_TEST_TASKTABLE.TESTTASK_BEGIN.eq(t.getTesttaskBegin()),
                                QUALITY_TEST_TASKTABLE.TESTTASK_END.eq(t.getTesttaskEnd()),
                                QUALITY_TEST_TASKTABLE.TESTTASK_DESC.eq(t.getTesttaskDesc()),
                                QUALITY_TEST_TASKTABLE.TESTTASK_REPORT.eq(t.getTesttaskReport()),
                                QUALITY_TEST_TASKTABLE.TESTTASK_STATUS.eq(t.getTesttaskStatus()),
                                QUALITY_TEST_TASKTABLE.DELETED.eq(t.getDeleted())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<QualityTestTask> qualityTestTasks) {
        if (CollectionUtil.isEmpty(qualityTestTasks)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, qualityTestTasks, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(QUALITY_TEST_TASKTABLE).values(
                        QUALITY_TEST_TASKTABLE.TESTTASK_TITLE.value(new JdbcNamedParameter("testtaskTitle")),
                        QUALITY_TEST_TASKTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
                        QUALITY_TEST_TASKTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
                        QUALITY_TEST_TASKTABLE.BUILD_NAME.value(new JdbcNamedParameter("buildName")),
                        QUALITY_TEST_TASKTABLE.TESTTASK_OWNER.value(new JdbcNamedParameter("testtaskOwner")),
                        QUALITY_TEST_TASKTABLE.PRIORITY.value(new JdbcNamedParameter("priority")),
                        QUALITY_TEST_TASKTABLE.TESTTASK_BEGIN.value(new JdbcNamedParameter("testtaskBegin")),
                        QUALITY_TEST_TASKTABLE.TESTTASK_END.value(new JdbcNamedParameter("testtaskEnd")),
                        QUALITY_TEST_TASKTABLE.TESTTASK_DESC.value(new JdbcNamedParameter("testtaskDesc")),
                        QUALITY_TEST_TASKTABLE.TESTTASK_REPORT.value(new JdbcNamedParameter("testtaskReport")),
                        QUALITY_TEST_TASKTABLE.TESTTASK_STATUS.value(new JdbcNamedParameter("testtaskStatus")),
                        QUALITY_TEST_TASKTABLE.DELETED.value(new JdbcNamedParameter("deleted")));
            }
        });
    }

    public int[] batchInsert(List<QualityTestTask> qualityTestTasks) {
        return batchInsert(true, qualityTestTasks);
    }

    public int[] batchUpdate(List<QualityTestTask> qualityTestTasks) {
        if (CollectionUtil.isEmpty(qualityTestTasks)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(qualityTestTasks, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(QUALITY_TEST_TASKTABLE).set(
                        QUALITY_TEST_TASKTABLE.TESTTASK_TITLE.value(new JdbcNamedParameter("testtaskTitle")),
                        QUALITY_TEST_TASKTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
                        QUALITY_TEST_TASKTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
                        QUALITY_TEST_TASKTABLE.BUILD_NAME.value(new JdbcNamedParameter("buildName")),
                        QUALITY_TEST_TASKTABLE.TESTTASK_OWNER.value(new JdbcNamedParameter("testtaskOwner")),
                        QUALITY_TEST_TASKTABLE.PRIORITY.value(new JdbcNamedParameter("priority")),
                        QUALITY_TEST_TASKTABLE.TESTTASK_BEGIN.value(new JdbcNamedParameter("testtaskBegin")),
                        QUALITY_TEST_TASKTABLE.TESTTASK_END.value(new JdbcNamedParameter("testtaskEnd")),
                        QUALITY_TEST_TASKTABLE.TESTTASK_DESC.value(new JdbcNamedParameter("testtaskDesc")),
                        QUALITY_TEST_TASKTABLE.TESTTASK_REPORT.value(new JdbcNamedParameter("testtaskReport")),
                        QUALITY_TEST_TASKTABLE.TESTTASK_STATUS.value(new JdbcNamedParameter("testtaskStatus")),
                        QUALITY_TEST_TASKTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
                        QUALITY_TEST_TASKTABLE.TESTVERSION_ID.eq(new JdbcNamedParameter("testversionId")));
            }
        });
    }

    public int[] batchDelete(List<QualityTestTask> qualityTestTasks) {
        if (CollectionUtil.isEmpty(qualityTestTasks)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(qualityTestTasks, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(QUALITY_TEST_TASKTABLE).where(and(
                        QUALITY_TEST_TASKTABLE.TESTVERSION_ID.eq(new JdbcNamedParameter("testversionId")),
                        QUALITY_TEST_TASKTABLE.TESTTASK_TITLE.eq(new JdbcNamedParameter("testtaskTitle")),
                        QUALITY_TEST_TASKTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
                        QUALITY_TEST_TASKTABLE.PROJECT_ID.eq(new JdbcNamedParameter("projectId")),
                        QUALITY_TEST_TASKTABLE.BUILD_NAME.eq(new JdbcNamedParameter("buildName")),
                        QUALITY_TEST_TASKTABLE.TESTTASK_OWNER.eq(new JdbcNamedParameter("testtaskOwner")),
                        QUALITY_TEST_TASKTABLE.PRIORITY.eq(new JdbcNamedParameter("priority")),
                        QUALITY_TEST_TASKTABLE.TESTTASK_BEGIN.eq(new JdbcNamedParameter("testtaskBegin")),
                        QUALITY_TEST_TASKTABLE.TESTTASK_END.eq(new JdbcNamedParameter("testtaskEnd")),
                        QUALITY_TEST_TASKTABLE.TESTTASK_DESC.eq(new JdbcNamedParameter("testtaskDesc")),
                        QUALITY_TEST_TASKTABLE.TESTTASK_REPORT.eq(new JdbcNamedParameter("testtaskReport")),
                        QUALITY_TEST_TASKTABLE.TESTTASK_STATUS.eq(new JdbcNamedParameter("testtaskStatus")),
                        QUALITY_TEST_TASKTABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
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

    public Pager<QualityTestTask> queryPager(int start, int limit, QualityTestTask qualityTestTask, Condition condition, OrderBy... orderArgs) {
        Select select = MysqlSelect.select(QUALITY_TEST_TASKTABLE.ALL, PROJECTTABLE.PROJECT_NAME.as("projectName"), PROJECT_BUILDTABLE.BUILD_NAME.as("searchBuildName")).from(QUALITY_TEST_TASKTABLE).join(
                Join.leftJoin(PROJECTTABLE, PROJECTTABLE.PROJECT_ID.eq(QUALITY_TEST_TASKTABLE.PROJECT_ID)),
                Join.leftJoin(PROJECT_BUILDTABLE, PROJECT_BUILDTABLE.BUILD_ID.eq(QUALITY_TEST_TASKTABLE.BUILD_NAME))).where(
                and(
                        condition,
                        QUALITY_TEST_TASKTABLE.TESTTASK_TITLE.eq(qualityTestTask.getTesttaskTitle()),
                        QUALITY_TEST_TASKTABLE.PRODUCT_ID.eq(qualityTestTask.getProductId()),
                        QUALITY_TEST_TASKTABLE.PROJECT_ID.eq(qualityTestTask.getProjectId()),
                        QUALITY_TEST_TASKTABLE.BUILD_NAME.eq(qualityTestTask.getBuildName()),
                        QUALITY_TEST_TASKTABLE.TESTTASK_OWNER.eq(qualityTestTask.getTesttaskOwner()),
                        QUALITY_TEST_TASKTABLE.PRIORITY.eq(qualityTestTask.getPriority()),
                        QUALITY_TEST_TASKTABLE.TESTTASK_BEGIN.eq(qualityTestTask.getTesttaskBegin()),
                        QUALITY_TEST_TASKTABLE.TESTTASK_END.eq(qualityTestTask.getTesttaskEnd()),
                        QUALITY_TEST_TASKTABLE.TESTTASK_DESC.eq(qualityTestTask.getTesttaskDesc()),
                        QUALITY_TEST_TASKTABLE.TESTTASK_REPORT.eq(qualityTestTask.getTesttaskReport()),
                        QUALITY_TEST_TASKTABLE.TESTTASK_STATUS.eq(qualityTestTask.getTesttaskStatus()),
                        QUALITY_TEST_TASKTABLE.DELETED.eq(qualityTestTask.getDeleted())));
        select = addOrderByElements(select, orderArgs);
        return getDslSession().fetchPage(select, start, limit, false, QualityTestTask.class);
    }

    public Integer getMaxNo(Integer productId) {
        Select select = select(QUALITY_TEST_TASKTABLE.NO.max()).from(QUALITY_TEST_TASKTABLE).where(QUALITY_TEST_TASKTABLE.PRODUCT_ID.eq(productId));
        return getDslSession().fetchOneResult(select, Integer.class);
    }

    public Integer deleteTestTaskByProduct(Integer productId) {
        Update update = update(QUALITY_TEST_TASKTABLE).set(QUALITY_TEST_TASKTABLE.DELETED.value(1)).where(and(QUALITY_TEST_TASKTABLE.PRODUCT_ID.eq(productId), QUALITY_TEST_TASKTABLE.DELETED.eq(0)));
        return getDslSession().execute(update);
    }
}
