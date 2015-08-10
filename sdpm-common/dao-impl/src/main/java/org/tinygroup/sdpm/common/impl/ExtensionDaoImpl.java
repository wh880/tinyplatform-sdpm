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

package org.tinygroup.sdpm.common.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.common.constant.ExtensionTable.*;
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
import org.tinygroup.sdpm.common.pojo.Extension;
import org.tinygroup.sdpm.common.inter.ExtensionDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class ExtensionDaoImpl extends TinyDslDaoSupport implements ExtensionDao {

	public Extension insertObject(Extension extension) {
		return getDslTemplate().insertObject(extension, new InsertGenerateCallback<Extension>() {
			public Insert generate(Extension t) {
				Insert insert = insertInto(EXTENSIONTABLE).values(
					EXTENSIONTABLE.EXTENSION_ID.value(t.getExtensionId()),
					EXTENSIONTABLE.COMPANY_ID.value(t.getCompanyId()),
					EXTENSIONTABLE.EXTENSION_NAME.value(t.getExtensionName()),
					EXTENSIONTABLE.EXTENSION_CODE.value(t.getExtensionCode()),
					EXTENSIONTABLE.EXTENSION_VERSION.value(t.getExtensionVersion()),
					EXTENSIONTABLE.EXTENSION_AUTHOR.value(t.getExtensionAuthor()),
					EXTENSIONTABLE.EXTENSION_DESC.value(t.getExtensionDesc()),
					EXTENSIONTABLE.EXTENSION_LICENSE.value(t.getExtensionLicense()),
					EXTENSIONTABLE.EXTENSION_TYPE.value(t.getExtensionType()),
					EXTENSIONTABLE.EXTENSION_SITE.value(t.getExtensionSite()),
					EXTENSIONTABLE.EXTENSION_SYSTEMVERSION.value(t.getExtensionSystemversion()),
					EXTENSIONTABLE.EXTENSION_INSTALLEDDATE.value(t.getExtensionInstalleddate()),
					EXTENSIONTABLE.EXTENSION_FILES.value(t.getExtensionFiles()),
					EXTENSIONTABLE.EXTENSION_STATUS.value(t.getExtensionStatus()));
				return insert;
			}
		});
	}

	public Extension insertObject(boolean autoGeneratedKeys ,Extension extension) {
		return getDslTemplate().insertObjectAndReturnKey(autoGeneratedKeys ,extension, new InsertGenerateCallback<Extension>() {

			public Insert generate(Extension t) {
				Insert insert = insertInto(EXTENSIONTABLE).values(
					EXTENSIONTABLE.EXTENSION_ID.value(t.getExtensionId()),
					EXTENSIONTABLE.COMPANY_ID.value(t.getCompanyId()),
					EXTENSIONTABLE.EXTENSION_NAME.value(t.getExtensionName()),
					EXTENSIONTABLE.EXTENSION_CODE.value(t.getExtensionCode()),
					EXTENSIONTABLE.EXTENSION_VERSION.value(t.getExtensionVersion()),
					EXTENSIONTABLE.EXTENSION_AUTHOR.value(t.getExtensionAuthor()),
					EXTENSIONTABLE.EXTENSION_DESC.value(t.getExtensionDesc()),
					EXTENSIONTABLE.EXTENSION_LICENSE.value(t.getExtensionLicense()),
					EXTENSIONTABLE.EXTENSION_TYPE.value(t.getExtensionType()),
					EXTENSIONTABLE.EXTENSION_SITE.value(t.getExtensionSite()),
					EXTENSIONTABLE.EXTENSION_SYSTEMVERSION.value(t.getExtensionSystemversion()),
					EXTENSIONTABLE.EXTENSION_INSTALLEDDATE.value(t.getExtensionInstalleddate()),
					EXTENSIONTABLE.EXTENSION_FILES.value(t.getExtensionFiles()),
					EXTENSIONTABLE.EXTENSION_STATUS.value(t.getExtensionStatus()));
				return insert;
			}
		});
	}

	public int updateObject(Extension extension) {
		return getDslTemplate().updateObject(extension, new UpdateGenerateCallback<Extension>() {
			public Update generate(Extension t) {
				Update update = update(EXTENSIONTABLE).set(
					EXTENSIONTABLE.COMPANY_ID.value(t.getCompanyId()),
					EXTENSIONTABLE.EXTENSION_NAME.value(t.getExtensionName()),
					EXTENSIONTABLE.EXTENSION_CODE.value(t.getExtensionCode()),
					EXTENSIONTABLE.EXTENSION_VERSION.value(t.getExtensionVersion()),
					EXTENSIONTABLE.EXTENSION_AUTHOR.value(t.getExtensionAuthor()),
					EXTENSIONTABLE.EXTENSION_DESC.value(t.getExtensionDesc()),
					EXTENSIONTABLE.EXTENSION_LICENSE.value(t.getExtensionLicense()),
					EXTENSIONTABLE.EXTENSION_TYPE.value(t.getExtensionType()),
					EXTENSIONTABLE.EXTENSION_SITE.value(t.getExtensionSite()),
					EXTENSIONTABLE.EXTENSION_SYSTEMVERSION.value(t.getExtensionSystemversion()),
					EXTENSIONTABLE.EXTENSION_INSTALLEDDATE.value(t.getExtensionInstalleddate()),
					EXTENSIONTABLE.EXTENSION_FILES.value(t.getExtensionFiles()),
					EXTENSIONTABLE.EXTENSION_STATUS.value(t.getExtensionStatus())).where(
					EXTENSIONTABLE.EXTENSION_ID.eq(t.getExtensionId()));
				return update;
			}
		});
	}

	public int deleteObject(Serializable pk){
		return getDslTemplate().deleteObject(pk, new DeleteGenerateCallback<Serializable>() {
			public Delete generate(Serializable pk) {
				return delete(EXTENSIONTABLE).where(EXTENSIONTABLE.EXTENSION_ID.eq(pk));
			}
		});
	}

	public int deleteObjects(Serializable... pks) {
		return getDslTemplate().deleteObjects(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(EXTENSIONTABLE).where(EXTENSIONTABLE.EXTENSION_ID.in(t));
		}
		},pks);
	}

	public Extension getObjectById(Serializable pk) {
		return getDslTemplate().getObjectById(pk, Extension.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(EXTENSIONTABLE).where(EXTENSIONTABLE.EXTENSION_ID.eq(t));
			}
		});
	}

	public List<Extension> queryObjects(Extension extension) {
		if(extension==null){
			extension=new Extension();
		}
		return getDslTemplate().queryObjects(extension, new SelectGenerateCallback<Extension>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Extension t) {
				return selectFrom(EXTENSIONTABLE).where(
				and(
					EXTENSIONTABLE.COMPANY_ID.eq(t.getCompanyId()),
					EXTENSIONTABLE.EXTENSION_NAME.eq(t.getExtensionName()),
					EXTENSIONTABLE.EXTENSION_CODE.eq(t.getExtensionCode()),
					EXTENSIONTABLE.EXTENSION_VERSION.eq(t.getExtensionVersion()),
					EXTENSIONTABLE.EXTENSION_AUTHOR.eq(t.getExtensionAuthor()),
					EXTENSIONTABLE.EXTENSION_DESC.eq(t.getExtensionDesc()),
					EXTENSIONTABLE.EXTENSION_LICENSE.eq(t.getExtensionLicense()),
					EXTENSIONTABLE.EXTENSION_TYPE.eq(t.getExtensionType()),
					EXTENSIONTABLE.EXTENSION_SITE.eq(t.getExtensionSite()),
					EXTENSIONTABLE.EXTENSION_SYSTEMVERSION.eq(t.getExtensionSystemversion()),
					EXTENSIONTABLE.EXTENSION_INSTALLEDDATE.eq(t.getExtensionInstalleddate()),
					EXTENSIONTABLE.EXTENSION_FILES.eq(t.getExtensionFiles()),
					EXTENSIONTABLE.EXTENSION_STATUS.eq(t.getExtensionStatus())));
			}
		});
	}

	public Pager<Extension> queryObjectsForPage(int start,int limit ,Extension extension) {
		if(extension==null){
			extension=new Extension();
		}
		return getDslTemplate().queryObjectsForPage(start, limit, extension, false, new SelectGenerateCallback<Extension>() {

			public Select generate(Extension t) {
				return MysqlSelect.selectFrom(EXTENSIONTABLE).where(
				and(
					EXTENSIONTABLE.COMPANY_ID.eq(t.getCompanyId()),
					EXTENSIONTABLE.EXTENSION_NAME.eq(t.getExtensionName()),
					EXTENSIONTABLE.EXTENSION_CODE.eq(t.getExtensionCode()),
					EXTENSIONTABLE.EXTENSION_VERSION.eq(t.getExtensionVersion()),
					EXTENSIONTABLE.EXTENSION_AUTHOR.eq(t.getExtensionAuthor()),
					EXTENSIONTABLE.EXTENSION_DESC.eq(t.getExtensionDesc()),
					EXTENSIONTABLE.EXTENSION_LICENSE.eq(t.getExtensionLicense()),
					EXTENSIONTABLE.EXTENSION_TYPE.eq(t.getExtensionType()),
					EXTENSIONTABLE.EXTENSION_SITE.eq(t.getExtensionSite()),
					EXTENSIONTABLE.EXTENSION_SYSTEMVERSION.eq(t.getExtensionSystemversion()),
					EXTENSIONTABLE.EXTENSION_INSTALLEDDATE.eq(t.getExtensionInstalleddate()),
					EXTENSIONTABLE.EXTENSION_FILES.eq(t.getExtensionFiles()),
					EXTENSIONTABLE.EXTENSION_STATUS.eq(t.getExtensionStatus())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Extension> extensions) {
		if (CollectionUtil.isEmpty(extensions)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, extensions, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(EXTENSIONTABLE).values(
					EXTENSIONTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
					EXTENSIONTABLE.EXTENSION_NAME.value(new JdbcNamedParameter("extensionName")),
					EXTENSIONTABLE.EXTENSION_CODE.value(new JdbcNamedParameter("extensionCode")),
					EXTENSIONTABLE.EXTENSION_VERSION.value(new JdbcNamedParameter("extensionVersion")),
					EXTENSIONTABLE.EXTENSION_AUTHOR.value(new JdbcNamedParameter("extensionAuthor")),
					EXTENSIONTABLE.EXTENSION_DESC.value(new JdbcNamedParameter("extensionDesc")),
					EXTENSIONTABLE.EXTENSION_LICENSE.value(new JdbcNamedParameter("extensionLicense")),
					EXTENSIONTABLE.EXTENSION_TYPE.value(new JdbcNamedParameter("extensionType")),
					EXTENSIONTABLE.EXTENSION_SITE.value(new JdbcNamedParameter("extensionSite")),
					EXTENSIONTABLE.EXTENSION_SYSTEMVERSION.value(new JdbcNamedParameter("extensionSystemversion")),
					EXTENSIONTABLE.EXTENSION_INSTALLEDDATE.value(new JdbcNamedParameter("extensionInstalleddate")),
					EXTENSIONTABLE.EXTENSION_FILES.value(new JdbcNamedParameter("extensionFiles")),
					EXTENSIONTABLE.EXTENSION_STATUS.value(new JdbcNamedParameter("extensionStatus")));
			}
		});
	}

	public int[] batchInsert(List<Extension> extensions){
			return batchInsert(true ,extensions);
	}

	public int[] batchUpdate(List<Extension> extensions) {
		if (CollectionUtil.isEmpty(extensions)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(extensions, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(EXTENSIONTABLE).set(
					EXTENSIONTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
					EXTENSIONTABLE.EXTENSION_NAME.value(new JdbcNamedParameter("extensionName")),
					EXTENSIONTABLE.EXTENSION_CODE.value(new JdbcNamedParameter("extensionCode")),
					EXTENSIONTABLE.EXTENSION_VERSION.value(new JdbcNamedParameter("extensionVersion")),
					EXTENSIONTABLE.EXTENSION_AUTHOR.value(new JdbcNamedParameter("extensionAuthor")),
					EXTENSIONTABLE.EXTENSION_DESC.value(new JdbcNamedParameter("extensionDesc")),
					EXTENSIONTABLE.EXTENSION_LICENSE.value(new JdbcNamedParameter("extensionLicense")),
					EXTENSIONTABLE.EXTENSION_TYPE.value(new JdbcNamedParameter("extensionType")),
					EXTENSIONTABLE.EXTENSION_SITE.value(new JdbcNamedParameter("extensionSite")),
					EXTENSIONTABLE.EXTENSION_SYSTEMVERSION.value(new JdbcNamedParameter("extensionSystemversion")),
					EXTENSIONTABLE.EXTENSION_INSTALLEDDATE.value(new JdbcNamedParameter("extensionInstalleddate")),
					EXTENSIONTABLE.EXTENSION_FILES.value(new JdbcNamedParameter("extensionFiles")),
					EXTENSIONTABLE.EXTENSION_STATUS.value(new JdbcNamedParameter("extensionStatus"))).where(
				EXTENSIONTABLE.EXTENSION_ID.eq(new JdbcNamedParameter("extensionId")));
			}
		});
	}

	public int[] batchDelete(List<Extension> extensions) {
		if (CollectionUtil.isEmpty(extensions)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(extensions, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(EXTENSIONTABLE).where(and(
				EXTENSIONTABLE.EXTENSION_ID.eq(new JdbcNamedParameter("extensionId")),
				EXTENSIONTABLE.COMPANY_ID.eq(new JdbcNamedParameter("companyId")),
				EXTENSIONTABLE.EXTENSION_NAME.eq(new JdbcNamedParameter("extensionName")),
				EXTENSIONTABLE.EXTENSION_CODE.eq(new JdbcNamedParameter("extensionCode")),
				EXTENSIONTABLE.EXTENSION_VERSION.eq(new JdbcNamedParameter("extensionVersion")),
				EXTENSIONTABLE.EXTENSION_AUTHOR.eq(new JdbcNamedParameter("extensionAuthor")),
				EXTENSIONTABLE.EXTENSION_DESC.eq(new JdbcNamedParameter("extensionDesc")),
				EXTENSIONTABLE.EXTENSION_LICENSE.eq(new JdbcNamedParameter("extensionLicense")),
				EXTENSIONTABLE.EXTENSION_TYPE.eq(new JdbcNamedParameter("extensionType")),
				EXTENSIONTABLE.EXTENSION_SITE.eq(new JdbcNamedParameter("extensionSite")),
				EXTENSIONTABLE.EXTENSION_SYSTEMVERSION.eq(new JdbcNamedParameter("extensionSystemversion")),
				EXTENSIONTABLE.EXTENSION_INSTALLEDDATE.eq(new JdbcNamedParameter("extensionInstalleddate")),
				EXTENSIONTABLE.EXTENSION_FILES.eq(new JdbcNamedParameter("extensionFiles")),
				EXTENSIONTABLE.EXTENSION_STATUS.eq(new JdbcNamedParameter("extensionStatus"))));
			}
		});
	}

}
