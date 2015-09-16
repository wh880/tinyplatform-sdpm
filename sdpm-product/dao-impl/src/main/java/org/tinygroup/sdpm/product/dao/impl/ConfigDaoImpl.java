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

package org.tinygroup.sdpm.product.dao.impl;

import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

import java.io.Serializable;
import java.util.List;

import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.product.dao.inter.ConfigDao;
import org.tinygroup.sdpm.product.dao.pojo.Config;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;

public class ConfigDaoImpl extends TinyDslDaoSupport implements ConfigDao {

	public Config add(Config config) {
		return getDslTemplate().insertAndReturnKey(config, new InsertGenerateCallback<Config>() {
			public Insert generate(Config t) {
				Insert insert = insertInto(CONFIGTABLE).values(
					CONFIGTABLE.CONFIG_ID.value(t.getConfigId()),
					CONFIGTABLE.CONFIG_OWNER.value(t.getConfigOwner()),
					CONFIGTABLE.CONFIG_MODULE.value(t.getConfigModule()),
					CONFIGTABLE.CONFIG_SECTION.value(t.getConfigSection()),
					CONFIGTABLE.CONFIG_KEY.value(t.getConfigKey()),
					CONFIGTABLE.CONFIG_VALUE.value(t.getConfigValue()));
				return insert;
			}
		});
	}

	public int edit(Config config) {
		if(config == null || config.getConfigId() == null){
			return 0;
		}
		return getDslTemplate().update(config, new UpdateGenerateCallback<Config>() {
			public Update generate(Config t) {
				Update update = update(CONFIGTABLE).set(
					CONFIGTABLE.CONFIG_OWNER.value(t.getConfigOwner()),
					CONFIGTABLE.CONFIG_MODULE.value(t.getConfigModule()),
					CONFIGTABLE.CONFIG_SECTION.value(t.getConfigSection()),
					CONFIGTABLE.CONFIG_KEY.value(t.getConfigKey()),
					CONFIGTABLE.CONFIG_VALUE.value(t.getConfigValue())).where(
					CONFIGTABLE.CONFIG_ID.eq(t.getConfigId()));
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
				return delete(CONFIGTABLE).where(CONFIGTABLE.CONFIG_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(CONFIGTABLE).where(CONFIGTABLE.CONFIG_ID.in(t));
		}
		},pks);
	}

	public Config getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Config.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(CONFIGTABLE).where(CONFIGTABLE.CONFIG_ID.eq(t));
			}
		});
	}

	public List<Config> query(Config config) {
		if(config==null){
			config=new Config();
		}
		return getDslTemplate().query(config, new SelectGenerateCallback<Config>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Config t) {
				return selectFrom(CONFIGTABLE).where(
				and(
					CONFIGTABLE.CONFIG_OWNER.eq(t.getConfigOwner()),
					CONFIGTABLE.CONFIG_MODULE.eq(t.getConfigModule()),
					CONFIGTABLE.CONFIG_SECTION.eq(t.getConfigSection()),
					CONFIGTABLE.CONFIG_KEY.eq(t.getConfigKey()),
					CONFIGTABLE.CONFIG_VALUE.eq(t.getConfigValue())));
			}
		});
	}

	public Pager<Config> queryPager(int start,int limit ,Config config) {
		if(config==null){
			config=new Config();
		}
		return getDslTemplate().queryPager(start, limit, config, false, new SelectGenerateCallback<Config>() {

			public Select generate(Config t) {
				return MysqlSelect.selectFrom(CONFIGTABLE).where(
				and(
					CONFIGTABLE.CONFIG_OWNER.eq(t.getConfigOwner()),
					CONFIGTABLE.CONFIG_MODULE.eq(t.getConfigModule()),
					CONFIGTABLE.CONFIG_SECTION.eq(t.getConfigSection()),
					CONFIGTABLE.CONFIG_KEY.eq(t.getConfigKey()),
					CONFIGTABLE.CONFIG_VALUE.eq(t.getConfigValue())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Config> configs) {
		if (CollectionUtil.isEmpty(configs)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, configs, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(CONFIGTABLE).values(
					CONFIGTABLE.CONFIG_OWNER.value(new JdbcNamedParameter("configOwner")),
					CONFIGTABLE.CONFIG_MODULE.value(new JdbcNamedParameter("configModule")),
					CONFIGTABLE.CONFIG_SECTION.value(new JdbcNamedParameter("configSection")),
					CONFIGTABLE.CONFIG_KEY.value(new JdbcNamedParameter("configKey")),
					CONFIGTABLE.CONFIG_VALUE.value(new JdbcNamedParameter("configValue")));
			}
		});
	}

	public int[] batchInsert(List<Config> configs){
			return batchInsert(true ,configs);
	}

	public int[] batchUpdate(List<Config> configs) {
		if (CollectionUtil.isEmpty(configs)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(configs, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(CONFIGTABLE).set(
					CONFIGTABLE.CONFIG_OWNER.value(new JdbcNamedParameter("configOwner")),
					CONFIGTABLE.CONFIG_MODULE.value(new JdbcNamedParameter("configModule")),
					CONFIGTABLE.CONFIG_SECTION.value(new JdbcNamedParameter("configSection")),
					CONFIGTABLE.CONFIG_KEY.value(new JdbcNamedParameter("configKey")),
					CONFIGTABLE.CONFIG_VALUE.value(new JdbcNamedParameter("configValue"))).where(
				CONFIGTABLE.CONFIG_ID.eq(new JdbcNamedParameter("configId")));
			}
		});
	}

	public int[] batchDelete(List<Config> configs) {
		if (CollectionUtil.isEmpty(configs)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(configs, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(CONFIGTABLE).where(and(
				CONFIGTABLE.CONFIG_ID.eq(new JdbcNamedParameter("configId")),
				CONFIGTABLE.CONFIG_OWNER.eq(new JdbcNamedParameter("configOwner")),
				CONFIGTABLE.CONFIG_MODULE.eq(new JdbcNamedParameter("configModule")),
				CONFIGTABLE.CONFIG_SECTION.eq(new JdbcNamedParameter("configSection")),
				CONFIGTABLE.CONFIG_KEY.eq(new JdbcNamedParameter("configKey")),
				CONFIGTABLE.CONFIG_VALUE.eq(new JdbcNamedParameter("configValue"))));
			}
		});
	}

}
