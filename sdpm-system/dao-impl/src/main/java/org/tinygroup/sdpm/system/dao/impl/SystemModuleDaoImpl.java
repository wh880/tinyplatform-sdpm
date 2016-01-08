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

package org.tinygroup.sdpm.system.dao.impl;

import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.system.dao.SystemModuleDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.system.dao.constant.SystemDictTable.SYSTEM_DICTTABLE;
import static org.tinygroup.sdpm.system.dao.constant.SystemModuleTable.SYSTEM_MODULETABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class SystemModuleDaoImpl extends TinyDslDaoSupport implements SystemModuleDao {

    public SystemModule add(SystemModule systemModule) {
        return getDslTemplate().insertAndReturnKey(systemModule, new InsertGenerateCallback<SystemModule>() {
            public Insert generate(SystemModule t) {
                Insert insert = insertInto(SYSTEM_MODULETABLE).values(
                        SYSTEM_MODULETABLE.MODULE_ROOT.value(t.getModuleRoot()),
                        SYSTEM_MODULETABLE.MODULE_NAME.value(t.getModuleName()),
                        SYSTEM_MODULETABLE.MODULE_PATH.value(t.getModulePath()),
                        SYSTEM_MODULETABLE.MODULE_PARENT.value(t.getModuleParent()),
                        SYSTEM_MODULETABLE.MODULE_GRADE.value(t.getModuleGrade()),
                        SYSTEM_MODULETABLE.MODULE_ORDER.value(t.getModuleOrder()),
                        SYSTEM_MODULETABLE.MODULE_TYPE.value(t.getModuleType()),
                        SYSTEM_MODULETABLE.MODULE_OWNER.value(t.getModuleOwner()),
//					SYSTEM_MODULETABLE.MODULE_ID.value(t.getModuleId()),
                        SYSTEM_MODULETABLE.MODULE_TITLE.value(t.getModuleTitle()));
                return insert;
            }
        });
    }

    public int edit(SystemModule systemModule) {
        if (systemModule == null || systemModule.getModuleId() == null) {
            return 0;
        }
        return getDslTemplate().update(systemModule, new UpdateGenerateCallback<SystemModule>() {
            public Update generate(SystemModule t) {
                Update update = update(SYSTEM_MODULETABLE).set(
                        SYSTEM_MODULETABLE.MODULE_ROOT.value(t.getModuleRoot()),
                        SYSTEM_MODULETABLE.MODULE_NAME.value(t.getModuleName()),
                        SYSTEM_MODULETABLE.MODULE_PATH.value(t.getModulePath()),
                        SYSTEM_MODULETABLE.MODULE_PARENT.value(t.getModuleParent()),
                        SYSTEM_MODULETABLE.MODULE_GRADE.value(t.getModuleGrade()),
                        SYSTEM_MODULETABLE.MODULE_ORDER.value(t.getModuleOrder()),
                        SYSTEM_MODULETABLE.MODULE_TYPE.value(t.getModuleType()),
                        SYSTEM_MODULETABLE.MODULE_OWNER.value(t.getModuleOwner()),
                        SYSTEM_MODULETABLE.MODULE_TITLE.value(t.getModuleTitle())).where(
                        SYSTEM_MODULETABLE.MODULE_ID.eq(t.getModuleId()));
                return update;
            }
        });
    }

    public int editNameAndTitle(SystemModule systemModule) {
        if (systemModule == null || systemModule.getModuleId() == null) {
            return 0;
        }
        return getDslTemplate().update(systemModule, new UpdateGenerateCallback<SystemModule>() {
            public Update generate(SystemModule t) {
                Update update = update(SYSTEM_MODULETABLE).set(
                        SYSTEM_MODULETABLE.MODULE_PARENT.value(t.getModuleParent()),
                        SYSTEM_MODULETABLE.MODULE_NAME.value(t.getModuleName()),
                        SYSTEM_MODULETABLE.MODULE_TITLE.value(t.getModuleTitle())).where(
                        SYSTEM_MODULETABLE.MODULE_ID.eq(t.getModuleId()));
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
                return delete(SYSTEM_MODULETABLE).where(SYSTEM_MODULETABLE.MODULE_ID.eq(pk));
            }
        });
    }

    public int deletebyKeyAndedit(Integer id) {
        if (id == null) {
            return 0;
        } else {
            Delete delete = delete(SYSTEM_MODULETABLE).where(SYSTEM_MODULETABLE.MODULE_ID.eq(id));
            int n = getDslTemplate().getDslSession().execute(delete);
            Update update = update(SYSTEM_DICTTABLE).set(SYSTEM_DICTTABLE.DELETED.value(1)).where
                    (SYSTEM_DICTTABLE.MODULE_ID.eq(id));
            int m = getDslTemplate().getDslSession().execute(update);
            if (n == 1 && m == 1) {
                return 1;
            }
        }
        return 1;

    }

    public int deleteByType(String type) {
        return getDslTemplate().getDslSession().execute(delete(SYSTEM_MODULETABLE).where(SYSTEM_MODULETABLE.MODULE_TYPE.eq(type)));
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(SYSTEM_MODULETABLE).where(SYSTEM_MODULETABLE.MODULE_ID.in(t));
            }
        }, pks);
    }

    public SystemModule getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, SystemModule.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(SYSTEM_MODULETABLE).where(SYSTEM_MODULETABLE.MODULE_ID.eq(t));
            }
        });
    }

    public List<SystemModule> query(SystemModule systemModule, final OrderBy... orderBies) {
        if (systemModule == null) {
            systemModule = new SystemModule();
        }
        return getDslTemplate().query(systemModule, new SelectGenerateCallback<SystemModule>() {

            @SuppressWarnings("rawtypes")
            public Select generate(SystemModule t) {
                Select select = selectFrom(SYSTEM_MODULETABLE).where(
                        and(
                                SYSTEM_MODULETABLE.MODULE_ROOT.eq(t.getModuleRoot()),
                                SYSTEM_MODULETABLE.MODULE_NAME.eq(t.getModuleName()),
                                SYSTEM_MODULETABLE.MODULE_PATH.eq(t.getModulePath()),
                                SYSTEM_MODULETABLE.MODULE_PARENT.eq(t.getModuleParent()),
                                SYSTEM_MODULETABLE.MODULE_GRADE.eq(t.getModuleGrade()),
                                SYSTEM_MODULETABLE.MODULE_ORDER.eq(t.getModuleOrder()),
                                SYSTEM_MODULETABLE.MODULE_TYPE.eq(t.getModuleType()),
                                SYSTEM_MODULETABLE.MODULE_OWNER.eq(t.getModuleOwner()),
                                SYSTEM_MODULETABLE.MODULE_TITLE.eq(t.getModuleTitle())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public Pager<SystemModule> queryPager(int start, int limit, SystemModule systemModule, final OrderBy... orderBies) {
        if (systemModule == null) {
            systemModule = new SystemModule();
        }
        return getDslTemplate().queryPager(start, limit, systemModule, false, new SelectGenerateCallback<SystemModule>() {

            public Select generate(SystemModule t) {
                Select select = MysqlSelect.selectFrom(SYSTEM_MODULETABLE).where(
                        and(
                                SYSTEM_MODULETABLE.MODULE_ROOT.eq(t.getModuleRoot()),
                                SYSTEM_MODULETABLE.MODULE_NAME.eq(t.getModuleName()),
                                SYSTEM_MODULETABLE.MODULE_PATH.eq(t.getModulePath()),
                                SYSTEM_MODULETABLE.MODULE_PARENT.eq(t.getModuleParent()),
                                SYSTEM_MODULETABLE.MODULE_GRADE.eq(t.getModuleGrade()),
                                SYSTEM_MODULETABLE.MODULE_ORDER.eq(t.getModuleOrder()),
                                SYSTEM_MODULETABLE.MODULE_TYPE.eq(t.getModuleType()),
                                SYSTEM_MODULETABLE.MODULE_OWNER.eq(t.getModuleOwner()),
                                SYSTEM_MODULETABLE.MODULE_TITLE.eq(t.getModuleTitle())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<SystemModule> systemModules) {
        if (CollectionUtil.isEmpty(systemModules)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, systemModules, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(SYSTEM_MODULETABLE).values(
                        SYSTEM_MODULETABLE.MODULE_ROOT.value(new JdbcNamedParameter("moduleRoot")),
                        SYSTEM_MODULETABLE.MODULE_NAME.value(new JdbcNamedParameter("moduleName")),
                        SYSTEM_MODULETABLE.MODULE_PATH.value(new JdbcNamedParameter("modulePath")),
                        SYSTEM_MODULETABLE.MODULE_PARENT.value(new JdbcNamedParameter("moduleParent")),
                        SYSTEM_MODULETABLE.MODULE_GRADE.value(new JdbcNamedParameter("moduleGrade")),
                        SYSTEM_MODULETABLE.MODULE_ORDER.value(new JdbcNamedParameter("moduleOrder")),
                        SYSTEM_MODULETABLE.MODULE_TYPE.value(new JdbcNamedParameter("moduleType")),
                        SYSTEM_MODULETABLE.MODULE_OWNER.value(new JdbcNamedParameter("moduleOwner")),
                        SYSTEM_MODULETABLE.MODULE_TITLE.value(new JdbcNamedParameter("moduleTitle")));
            }
        });
    }

    public int[] batchInsert(List<SystemModule> systemModules) {
        return batchInsert(true, systemModules);
    }

    public int[] batchUpdate(List<SystemModule> systemModules) {
        if (CollectionUtil.isEmpty(systemModules)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(systemModules, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(SYSTEM_MODULETABLE).set(
                        SYSTEM_MODULETABLE.MODULE_ROOT.value(new JdbcNamedParameter("moduleRoot")),
                        SYSTEM_MODULETABLE.MODULE_NAME.value(new JdbcNamedParameter("moduleName")),
                        SYSTEM_MODULETABLE.MODULE_PATH.value(new JdbcNamedParameter("modulePath")),
                        SYSTEM_MODULETABLE.MODULE_PARENT.value(new JdbcNamedParameter("moduleParent")),
                        SYSTEM_MODULETABLE.MODULE_GRADE.value(new JdbcNamedParameter("moduleGrade")),
                        SYSTEM_MODULETABLE.MODULE_ORDER.value(new JdbcNamedParameter("moduleOrder")),
                        SYSTEM_MODULETABLE.MODULE_TYPE.value(new JdbcNamedParameter("moduleType")),
                        SYSTEM_MODULETABLE.MODULE_OWNER.value(new JdbcNamedParameter("moduleOwner")),
                        SYSTEM_MODULETABLE.MODULE_TITLE.value(new JdbcNamedParameter("moduleTitle"))).where(
                        SYSTEM_MODULETABLE.MODULE_ID.eq(new JdbcNamedParameter("moduleId")));
            }
        });
    }

    public int[] batchDelete(List<SystemModule> systemModules) {
        if (CollectionUtil.isEmpty(systemModules)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(systemModules, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(SYSTEM_MODULETABLE).where(and(
                        SYSTEM_MODULETABLE.MODULE_ROOT.eq(new JdbcNamedParameter("moduleRoot")),
                        SYSTEM_MODULETABLE.MODULE_NAME.eq(new JdbcNamedParameter("moduleName")),
                        SYSTEM_MODULETABLE.MODULE_PATH.eq(new JdbcNamedParameter("modulePath")),
                        SYSTEM_MODULETABLE.MODULE_PARENT.eq(new JdbcNamedParameter("moduleParent")),
                        SYSTEM_MODULETABLE.MODULE_GRADE.eq(new JdbcNamedParameter("moduleGrade")),
                        SYSTEM_MODULETABLE.MODULE_ORDER.eq(new JdbcNamedParameter("moduleOrder")),
                        SYSTEM_MODULETABLE.MODULE_TYPE.eq(new JdbcNamedParameter("moduleType")),
                        SYSTEM_MODULETABLE.MODULE_OWNER.eq(new JdbcNamedParameter("moduleOwner")),
                        SYSTEM_MODULETABLE.MODULE_ID.eq(new JdbcNamedParameter("moduleId")),
                        SYSTEM_MODULETABLE.MODULE_TITLE.eq(new JdbcNamedParameter("moduleTitle"))));
            }
        });
    }

    private Select addOrderByElements(Select select, OrderBy... orderBies) {
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

    public int batchdelete(String ids) {
        Delete delete = delete(SYSTEM_MODULETABLE).where(SYSTEM_MODULETABLE.MODULE_ID.in(ids));
        return getDslSession().execute(delete);
    }

    @Override
    public int deleteByObject(SystemModule systemModule) {
        Delete delete = delete(SYSTEM_MODULETABLE).where(
                and(
                        SYSTEM_MODULETABLE.MODULE_ROOT.eq(systemModule.getModuleRoot()),
                        SYSTEM_MODULETABLE.MODULE_NAME.eq(systemModule.getModuleName()),
                        SYSTEM_MODULETABLE.MODULE_PATH.eq(systemModule.getModulePath()),
                        SYSTEM_MODULETABLE.MODULE_PARENT.eq(systemModule.getModuleParent()),
                        SYSTEM_MODULETABLE.MODULE_GRADE.eq(systemModule.getModuleGrade()),
                        SYSTEM_MODULETABLE.MODULE_ORDER.eq(systemModule.getModuleOrder()),
                        SYSTEM_MODULETABLE.MODULE_TYPE.eq(systemModule.getModuleType()),
                        SYSTEM_MODULETABLE.MODULE_OWNER.eq(systemModule.getModuleOwner()),
                        SYSTEM_MODULETABLE.MODULE_TITLE.eq(systemModule.getModuleTitle())));
        return getDslSession().execute(delete);
    }
}
