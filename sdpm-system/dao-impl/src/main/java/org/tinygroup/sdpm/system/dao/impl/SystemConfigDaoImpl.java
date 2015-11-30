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
import org.tinygroup.sdpm.system.dao.SystemConfigDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemConfig;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.system.dao.constant.SystemConfigTable.SYSTEM_CONFIGTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class SystemConfigDaoImpl extends TinyDslDaoSupport implements SystemConfigDao {

    public SystemConfig add(SystemConfig systemConfig) {
        return getDslTemplate().insertAndReturnKey(systemConfig, new InsertGenerateCallback<SystemConfig>() {
            public Insert generate(SystemConfig t) {
                Insert insert = insertInto(SYSTEM_CONFIGTABLE).values(
                        SYSTEM_CONFIGTABLE.CONFIG_ID.value(t.getConfigId()),
                        SYSTEM_CONFIGTABLE.CONFIG_OWNER.value(t.getConfigOwner()),
                        SYSTEM_CONFIGTABLE.CONFIG_MODULE.value(t.getConfigModule()),
                        SYSTEM_CONFIGTABLE.CONFIG_SECTION.value(t.getConfigSection()),
                        SYSTEM_CONFIGTABLE.CONFIG_KEY.value(t.getConfigKey()),
                        SYSTEM_CONFIGTABLE.CONFIG_VALUE.value(t.getConfigValue()),
                        SYSTEM_CONFIGTABLE.DELETED.value(t.getDeleted()));
                return insert;
            }
        });
    }

    public int edit(SystemConfig systemConfig) {
        if (systemConfig == null || systemConfig.getConfigId() == null) {
            return 0;
        }
        return getDslTemplate().update(systemConfig, new UpdateGenerateCallback<SystemConfig>() {
            public Update generate(SystemConfig t) {
                Update update = update(SYSTEM_CONFIGTABLE).set(
                        SYSTEM_CONFIGTABLE.CONFIG_OWNER.value(t.getConfigOwner()),
                        SYSTEM_CONFIGTABLE.CONFIG_MODULE.value(t.getConfigModule()),
                        SYSTEM_CONFIGTABLE.CONFIG_SECTION.value(t.getConfigSection()),
                        SYSTEM_CONFIGTABLE.CONFIG_KEY.value(t.getConfigKey()),
                        SYSTEM_CONFIGTABLE.CONFIG_VALUE.value(t.getConfigValue()),
                        SYSTEM_CONFIGTABLE.DELETED.value(t.getDeleted())).where(
                        SYSTEM_CONFIGTABLE.CONFIG_ID.eq(t.getConfigId()));
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
                return delete(SYSTEM_CONFIGTABLE).where(SYSTEM_CONFIGTABLE.CONFIG_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(SYSTEM_CONFIGTABLE).where(SYSTEM_CONFIGTABLE.CONFIG_ID.in(t));
            }
        }, pks);
    }

    public SystemConfig getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, SystemConfig.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(SYSTEM_CONFIGTABLE).where(SYSTEM_CONFIGTABLE.CONFIG_ID.eq(t));
            }
        });
    }

    public List<SystemConfig> query(SystemConfig systemConfig, final OrderBy... orderBies) {
        if (systemConfig == null) {
            systemConfig = new SystemConfig();
        }
        return getDslTemplate().query(systemConfig, new SelectGenerateCallback<SystemConfig>() {

            @SuppressWarnings("rawtypes")
            public Select generate(SystemConfig t) {
                Select select = selectFrom(SYSTEM_CONFIGTABLE).where(
                        and(
                                SYSTEM_CONFIGTABLE.CONFIG_OWNER.eq(t.getConfigOwner()),
                                SYSTEM_CONFIGTABLE.CONFIG_MODULE.eq(t.getConfigModule()),
                                SYSTEM_CONFIGTABLE.CONFIG_SECTION.eq(t.getConfigSection()),
                                SYSTEM_CONFIGTABLE.CONFIG_KEY.eq(t.getConfigKey()),
                                SYSTEM_CONFIGTABLE.CONFIG_VALUE.eq(t.getConfigValue()),
                                SYSTEM_CONFIGTABLE.DELETED.eq(t.getDeleted())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public Pager<SystemConfig> queryPager(int start, int limit, SystemConfig systemConfig, final OrderBy... orderBies) {
        if (systemConfig == null) {
            systemConfig = new SystemConfig();
        }
        return getDslTemplate().queryPager(start, limit, systemConfig, false, new SelectGenerateCallback<SystemConfig>() {

            public Select generate(SystemConfig t) {
                Select select = MysqlSelect.selectFrom(SYSTEM_CONFIGTABLE).where(
                        and(
                                SYSTEM_CONFIGTABLE.CONFIG_OWNER.eq(t.getConfigOwner()),
                                SYSTEM_CONFIGTABLE.CONFIG_MODULE.eq(t.getConfigModule()),
                                SYSTEM_CONFIGTABLE.CONFIG_SECTION.eq(t.getConfigSection()),
                                SYSTEM_CONFIGTABLE.CONFIG_KEY.eq(t.getConfigKey()),
                                SYSTEM_CONFIGTABLE.CONFIG_VALUE.eq(t.getConfigValue()),
                                SYSTEM_CONFIGTABLE.DELETED.eq(t.getDeleted())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<SystemConfig> systemConfigs) {
        if (CollectionUtil.isEmpty(systemConfigs)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, systemConfigs, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(SYSTEM_CONFIGTABLE).values(
                        SYSTEM_CONFIGTABLE.CONFIG_OWNER.value(new JdbcNamedParameter("configOwner")),
                        SYSTEM_CONFIGTABLE.CONFIG_MODULE.value(new JdbcNamedParameter("configModule")),
                        SYSTEM_CONFIGTABLE.CONFIG_SECTION.value(new JdbcNamedParameter("configSection")),
                        SYSTEM_CONFIGTABLE.CONFIG_KEY.value(new JdbcNamedParameter("configKey")),
                        SYSTEM_CONFIGTABLE.CONFIG_VALUE.value(new JdbcNamedParameter("configValue")),
                        SYSTEM_CONFIGTABLE.DELETED.value(new JdbcNamedParameter("deleted")));
            }
        });
    }

    public int[] batchInsert(List<SystemConfig> systemConfigs) {
        return batchInsert(true, systemConfigs);
    }

    public int[] batchUpdate(List<SystemConfig> systemConfigs) {
        if (CollectionUtil.isEmpty(systemConfigs)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(systemConfigs, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(SYSTEM_CONFIGTABLE).set(
                        SYSTEM_CONFIGTABLE.CONFIG_OWNER.value(new JdbcNamedParameter("configOwner")),
                        SYSTEM_CONFIGTABLE.CONFIG_MODULE.value(new JdbcNamedParameter("configModule")),
                        SYSTEM_CONFIGTABLE.CONFIG_SECTION.value(new JdbcNamedParameter("configSection")),
                        SYSTEM_CONFIGTABLE.CONFIG_KEY.value(new JdbcNamedParameter("configKey")),
                        SYSTEM_CONFIGTABLE.CONFIG_VALUE.value(new JdbcNamedParameter("configValue")),
                        SYSTEM_CONFIGTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
                        SYSTEM_CONFIGTABLE.CONFIG_ID.eq(new JdbcNamedParameter("configId")));
            }
        });
    }

    public int[] batchDelete(List<SystemConfig> systemConfigs) {
        if (CollectionUtil.isEmpty(systemConfigs)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(systemConfigs, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(SYSTEM_CONFIGTABLE).where(and(
                        SYSTEM_CONFIGTABLE.CONFIG_ID.eq(new JdbcNamedParameter("configId")),
                        SYSTEM_CONFIGTABLE.CONFIG_OWNER.eq(new JdbcNamedParameter("configOwner")),
                        SYSTEM_CONFIGTABLE.CONFIG_MODULE.eq(new JdbcNamedParameter("configModule")),
                        SYSTEM_CONFIGTABLE.CONFIG_SECTION.eq(new JdbcNamedParameter("configSection")),
                        SYSTEM_CONFIGTABLE.CONFIG_KEY.eq(new JdbcNamedParameter("configKey")),
                        SYSTEM_CONFIGTABLE.CONFIG_VALUE.eq(new JdbcNamedParameter("configValue")),
                        SYSTEM_CONFIGTABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
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
}
