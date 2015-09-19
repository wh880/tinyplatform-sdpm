/**
 * Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 * <p/>
 * Licensed under the GPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.gnu.org/licenses/gpl.html
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tinygroup.sdpm.project.dao.impl;

import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.project.dao.TaskrelationDao;
import org.tinygroup.sdpm.project.dao.pojo.Taskrelation;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;

import java.io.Serializable;
import java.util.List;

import static org.tinygroup.sdpm.project.dao.constant.TaskrelationTable.TASKRELATIONTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class TaskrelationDaoImpl extends TinyDslDaoSupport implements TaskrelationDao {

    public Taskrelation add(Taskrelation taskrelation) {
        return getDslTemplate().insertAndReturnKey(taskrelation, new InsertGenerateCallback<Taskrelation>() {
            public Insert generate(Taskrelation t) {
                Insert insert = insertInto(TASKRELATIONTABLE).values(
                        TASKRELATIONTABLE.ID.value(t.getId()),
                        TASKRELATIONTABLE.PER_TASK.value(t.getPerTask()),
                        TASKRELATIONTABLE.TASKRELATION_CONDITON.value(t.getTaskrelationConditon()),
                        TASKRELATIONTABLE.AFTER_TASK.value(t.getAfterTask()),
                        TASKRELATIONTABLE.TASKRELATION_ACTION.value(t.getTaskrelationAction()));
                return insert;
            }
        });
    }

    public int edit(Taskrelation taskrelation) {
        if (taskrelation == null || taskrelation.getId() == null) {
            return 0;
        }
        return getDslTemplate().update(taskrelation, new UpdateGenerateCallback<Taskrelation>() {
            public Update generate(Taskrelation t) {
                Update update = update(TASKRELATIONTABLE).set(
                        TASKRELATIONTABLE.ID.value(t.getId()),
                        TASKRELATIONTABLE.PER_TASK.value(t.getPerTask()),
                        TASKRELATIONTABLE.TASKRELATION_CONDITON.value(t.getTaskrelationConditon()),
                        TASKRELATIONTABLE.AFTER_TASK.value(t.getAfterTask()),
                        TASKRELATIONTABLE.TASKRELATION_ACTION.value(t.getTaskrelationAction())).where(
                        TASKRELATIONTABLE.ID.eq(t.getId()));
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
                return delete(TASKRELATIONTABLE).where(TASKRELATIONTABLE.ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(TASKRELATIONTABLE).where(TASKRELATIONTABLE.ID.in(t));
            }
        }, pks);
    }

    public Taskrelation getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, Taskrelation.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(TASKRELATIONTABLE).where(TASKRELATIONTABLE.ID.eq(t));
            }
        });
    }

    public List<Taskrelation> query(Taskrelation taskrelation) {
        if (taskrelation == null) {
            taskrelation = new Taskrelation();
        }
        return getDslTemplate().query(taskrelation, new SelectGenerateCallback<Taskrelation>() {

            @SuppressWarnings("rawtypes")
            public Select generate(Taskrelation t) {
                return selectFrom(TASKRELATIONTABLE).where(
                        and(
                                TASKRELATIONTABLE.ID.eq(t.getId()),
                                TASKRELATIONTABLE.PER_TASK.eq(t.getPerTask()),
                                TASKRELATIONTABLE.TASKRELATION_CONDITON.eq(t.getTaskrelationConditon()),
                                TASKRELATIONTABLE.AFTER_TASK.eq(t.getAfterTask()),
                                TASKRELATIONTABLE.TASKRELATION_ACTION.eq(t.getTaskrelationAction())));
            }
        });
    }

    public Pager<Taskrelation> queryPager(int start, int limit, Taskrelation taskrelation) {
        if (taskrelation == null) {
            taskrelation = new Taskrelation();
        }
        return getDslTemplate().queryPager(start, limit, taskrelation, false, new SelectGenerateCallback<Taskrelation>() {

            public Select generate(Taskrelation t) {
                return MysqlSelect.selectFrom(TASKRELATIONTABLE).where(
                        and(
                                TASKRELATIONTABLE.ID.eq(t.getId()),
                                TASKRELATIONTABLE.PER_TASK.eq(t.getPerTask()),
                                TASKRELATIONTABLE.TASKRELATION_CONDITON.eq(t.getTaskrelationConditon()),
                                TASKRELATIONTABLE.AFTER_TASK.eq(t.getAfterTask()),
                                TASKRELATIONTABLE.TASKRELATION_ACTION.eq(t.getTaskrelationAction())));
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<Taskrelation> taskrelations) {
        if (CollectionUtil.isEmpty(taskrelations)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, taskrelations, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(TASKRELATIONTABLE).values(
                        TASKRELATIONTABLE.ID.value(new JdbcNamedParameter("id")),
                        TASKRELATIONTABLE.PER_TASK.value(new JdbcNamedParameter("perTask")),
                        TASKRELATIONTABLE.TASKRELATION_CONDITON.value(new JdbcNamedParameter("taskrelationConditon")),
                        TASKRELATIONTABLE.AFTER_TASK.value(new JdbcNamedParameter("afterTask")),
                        TASKRELATIONTABLE.TASKRELATION_ACTION.value(new JdbcNamedParameter("taskrelationAction")));
            }
        });
    }

    public int[] batchInsert(List<Taskrelation> taskrelations) {
        return batchInsert(true, taskrelations);
    }

    public int[] batchUpdate(List<Taskrelation> taskrelations) {
        if (CollectionUtil.isEmpty(taskrelations)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(taskrelations, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(TASKRELATIONTABLE).set(
                        TASKRELATIONTABLE.ID.value(new JdbcNamedParameter("id")),
                        TASKRELATIONTABLE.PER_TASK.value(new JdbcNamedParameter("perTask")),
                        TASKRELATIONTABLE.TASKRELATION_CONDITON.value(new JdbcNamedParameter("taskrelationConditon")),
                        TASKRELATIONTABLE.AFTER_TASK.value(new JdbcNamedParameter("afterTask")),
                        TASKRELATIONTABLE.TASKRELATION_ACTION.value(new JdbcNamedParameter("taskrelationAction"))).where(
                        TASKRELATIONTABLE.ID.eq(new JdbcNamedParameter("id")));
            }
        });
    }

    public int[] batchDelete(List<Taskrelation> taskrelations) {
        if (CollectionUtil.isEmpty(taskrelations)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(taskrelations, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(TASKRELATIONTABLE).where(and(
                        TASKRELATIONTABLE.ID.eq(new JdbcNamedParameter("id")),
                        TASKRELATIONTABLE.PER_TASK.eq(new JdbcNamedParameter("perTask")),
                        TASKRELATIONTABLE.TASKRELATION_CONDITON.eq(new JdbcNamedParameter("taskrelationConditon")),
                        TASKRELATIONTABLE.AFTER_TASK.eq(new JdbcNamedParameter("afterTask")),
                        TASKRELATIONTABLE.TASKRELATION_ACTION.eq(new JdbcNamedParameter("taskrelationAction"))));
            }
        });
    }

}
