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

package org.tinygroup.sdpm.system.dao.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.system.dao.constant.SystemProfileTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.dao.SystemProfileDao;
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
public class SystemProfileDaoImpl extends TinyDslDaoSupport implements SystemProfileDao {

	public SystemProfile add(SystemProfile systemProfile) {
		return getDslTemplate().insertAndReturnKey(systemProfile, new InsertGenerateCallback<SystemProfile>() {
			public Insert generate(SystemProfile t) {
				Insert insert = insertInto(SYSTEM_PROFILETABLE).values(
					SYSTEM_PROFILETABLE.FILE_ID.value(t.getFileId()),
					SYSTEM_PROFILETABLE.FILE_PATHNAME.value(t.getFilePathname()),
					SYSTEM_PROFILETABLE.FILE_TITLE.value(t.getFileTitle()),
					SYSTEM_PROFILETABLE.FILE_EXTENSION.value(t.getFileExtension()),
					SYSTEM_PROFILETABLE.FILE_SIZE.value(t.getFileSize()),
					SYSTEM_PROFILETABLE.FILE_OBJECT_TYPE.value(t.getFileObjectType()),
					SYSTEM_PROFILETABLE.FILE_OBJECT_ID.value(t.getFileObjectId()),
					SYSTEM_PROFILETABLE.FILE_ADDED_BY.value(t.getFileAddedBy()),
					SYSTEM_PROFILETABLE.FILE_ADDED_DATE.value(t.getFileAddedDate()),
					SYSTEM_PROFILETABLE.FILE_DOWNLOADS.value(t.getFileDownloads()),
					SYSTEM_PROFILETABLE.FILE_EXTRA.value(t.getFileExtra()),
					SYSTEM_PROFILETABLE.FILE_DELETED.value(t.getFileDeleted()));
				return insert;
			}
		});
	}

	public int edit(SystemProfile systemProfile) {
		if(systemProfile == null || systemProfile.getFileId() == null){
			return 0;
		}
		return getDslTemplate().update(systemProfile, new UpdateGenerateCallback<SystemProfile>() {
			public Update generate(SystemProfile t) {
				Update update = update(SYSTEM_PROFILETABLE).set(
					SYSTEM_PROFILETABLE.FILE_PATHNAME.value(t.getFilePathname()),
					SYSTEM_PROFILETABLE.FILE_TITLE.value(t.getFileTitle()),
					SYSTEM_PROFILETABLE.FILE_EXTENSION.value(t.getFileExtension()),
					SYSTEM_PROFILETABLE.FILE_SIZE.value(t.getFileSize()),
					SYSTEM_PROFILETABLE.FILE_OBJECT_TYPE.value(t.getFileObjectType()),
					SYSTEM_PROFILETABLE.FILE_OBJECT_ID.value(t.getFileObjectId()),
					SYSTEM_PROFILETABLE.FILE_ADDED_BY.value(t.getFileAddedBy()),
					SYSTEM_PROFILETABLE.FILE_ADDED_DATE.value(t.getFileAddedDate()),
					SYSTEM_PROFILETABLE.FILE_DOWNLOADS.value(t.getFileDownloads()),
					SYSTEM_PROFILETABLE.FILE_EXTRA.value(t.getFileExtra()),
					SYSTEM_PROFILETABLE.FILE_DELETED.value(t.getFileDeleted())).where(
					SYSTEM_PROFILETABLE.FILE_ID.eq(t.getFileId()));
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
				return delete(SYSTEM_PROFILETABLE).where(SYSTEM_PROFILETABLE.FILE_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(SYSTEM_PROFILETABLE).where(SYSTEM_PROFILETABLE.FILE_ID.in(t));
		}
		},pks);
	}

	public SystemProfile getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, SystemProfile.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(SYSTEM_PROFILETABLE).where(SYSTEM_PROFILETABLE.FILE_ID.eq(t));
			}
		});
	}

	public List<SystemProfile> query(SystemProfile systemProfile ,final OrderBy... orderBies) {
		if(systemProfile==null){
			systemProfile=new SystemProfile();
		}
		return getDslTemplate().query(systemProfile, new SelectGenerateCallback<SystemProfile>() {

			@SuppressWarnings("rawtypes")
			public Select generate(SystemProfile t) {
				Select select = selectFrom(SYSTEM_PROFILETABLE).where(
				and(
					SYSTEM_PROFILETABLE.FILE_PATHNAME.eq(t.getFilePathname()),
					SYSTEM_PROFILETABLE.FILE_TITLE.eq(t.getFileTitle()),
					SYSTEM_PROFILETABLE.FILE_EXTENSION.eq(t.getFileExtension()),
					SYSTEM_PROFILETABLE.FILE_SIZE.eq(t.getFileSize()),
					SYSTEM_PROFILETABLE.FILE_OBJECT_TYPE.eq(t.getFileObjectType()),
					SYSTEM_PROFILETABLE.FILE_OBJECT_ID.eq(t.getFileObjectId()),
					SYSTEM_PROFILETABLE.FILE_ADDED_BY.eq(t.getFileAddedBy()),
					SYSTEM_PROFILETABLE.FILE_ADDED_DATE.eq(t.getFileAddedDate()),
					SYSTEM_PROFILETABLE.FILE_DOWNLOADS.eq(t.getFileDownloads()),
					SYSTEM_PROFILETABLE.FILE_EXTRA.eq(t.getFileExtra()),
					SYSTEM_PROFILETABLE.FILE_DELETED.eq(t.getFileDeleted())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<SystemProfile> queryPager(int start,int limit ,SystemProfile systemProfile ,final OrderBy... orderBies) {
		if(systemProfile==null){
			systemProfile=new SystemProfile();
		}
		return getDslTemplate().queryPager(start, limit, systemProfile, false, new SelectGenerateCallback<SystemProfile>() {

			public Select generate(SystemProfile t) {
				Select select = MysqlSelect.selectFrom(SYSTEM_PROFILETABLE).where(
				and(
					SYSTEM_PROFILETABLE.FILE_PATHNAME.eq(t.getFilePathname()),
					SYSTEM_PROFILETABLE.FILE_TITLE.eq(t.getFileTitle()),
					SYSTEM_PROFILETABLE.FILE_EXTENSION.eq(t.getFileExtension()),
					SYSTEM_PROFILETABLE.FILE_SIZE.eq(t.getFileSize()),
					SYSTEM_PROFILETABLE.FILE_OBJECT_TYPE.eq(t.getFileObjectType()),
					SYSTEM_PROFILETABLE.FILE_OBJECT_ID.eq(t.getFileObjectId()),
					SYSTEM_PROFILETABLE.FILE_ADDED_BY.eq(t.getFileAddedBy()),
					SYSTEM_PROFILETABLE.FILE_ADDED_DATE.eq(t.getFileAddedDate()),
					SYSTEM_PROFILETABLE.FILE_DOWNLOADS.eq(t.getFileDownloads()),
					SYSTEM_PROFILETABLE.FILE_EXTRA.eq(t.getFileExtra()),
					SYSTEM_PROFILETABLE.FILE_DELETED.eq(t.getFileDeleted())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<SystemProfile> systemProfiles) {
		if (CollectionUtil.isEmpty(systemProfiles)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, systemProfiles, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(SYSTEM_PROFILETABLE).values(
					SYSTEM_PROFILETABLE.FILE_PATHNAME.value(new JdbcNamedParameter("filePathname")),
					SYSTEM_PROFILETABLE.FILE_TITLE.value(new JdbcNamedParameter("fileTitle")),
					SYSTEM_PROFILETABLE.FILE_EXTENSION.value(new JdbcNamedParameter("fileExtension")),
					SYSTEM_PROFILETABLE.FILE_SIZE.value(new JdbcNamedParameter("fileSize")),
					SYSTEM_PROFILETABLE.FILE_OBJECT_TYPE.value(new JdbcNamedParameter("fileObjectType")),
					SYSTEM_PROFILETABLE.FILE_OBJECT_ID.value(new JdbcNamedParameter("fileObjectId")),
					SYSTEM_PROFILETABLE.FILE_ADDED_BY.value(new JdbcNamedParameter("fileAddedBy")),
					SYSTEM_PROFILETABLE.FILE_ADDED_DATE.value(new JdbcNamedParameter("fileAddedDate")),
					SYSTEM_PROFILETABLE.FILE_DOWNLOADS.value(new JdbcNamedParameter("fileDownloads")),
					SYSTEM_PROFILETABLE.FILE_EXTRA.value(new JdbcNamedParameter("fileExtra")),
					SYSTEM_PROFILETABLE.FILE_DELETED.value(new JdbcNamedParameter("fileDeleted")));
			}
		});
	}

	public int[] batchInsert(List<SystemProfile> systemProfiles){
			return batchInsert(true ,systemProfiles);
	}

	public int[] batchUpdate(List<SystemProfile> systemProfiles) {
		if (CollectionUtil.isEmpty(systemProfiles)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(systemProfiles, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(SYSTEM_PROFILETABLE).set(
					SYSTEM_PROFILETABLE.FILE_PATHNAME.value(new JdbcNamedParameter("filePathname")),
					SYSTEM_PROFILETABLE.FILE_TITLE.value(new JdbcNamedParameter("fileTitle")),
					SYSTEM_PROFILETABLE.FILE_EXTENSION.value(new JdbcNamedParameter("fileExtension")),
					SYSTEM_PROFILETABLE.FILE_SIZE.value(new JdbcNamedParameter("fileSize")),
					SYSTEM_PROFILETABLE.FILE_OBJECT_TYPE.value(new JdbcNamedParameter("fileObjectType")),
					SYSTEM_PROFILETABLE.FILE_OBJECT_ID.value(new JdbcNamedParameter("fileObjectId")),
					SYSTEM_PROFILETABLE.FILE_ADDED_BY.value(new JdbcNamedParameter("fileAddedBy")),
					SYSTEM_PROFILETABLE.FILE_ADDED_DATE.value(new JdbcNamedParameter("fileAddedDate")),
					SYSTEM_PROFILETABLE.FILE_DOWNLOADS.value(new JdbcNamedParameter("fileDownloads")),
					SYSTEM_PROFILETABLE.FILE_EXTRA.value(new JdbcNamedParameter("fileExtra")),
					SYSTEM_PROFILETABLE.FILE_DELETED.value(new JdbcNamedParameter("fileDeleted"))).where(
				SYSTEM_PROFILETABLE.FILE_ID.eq(new JdbcNamedParameter("fileId")));
			}
		});
	}

	public int[] batchDelete(List<SystemProfile> systemProfiles) {
		if (CollectionUtil.isEmpty(systemProfiles)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(systemProfiles, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(SYSTEM_PROFILETABLE).where(and(
				SYSTEM_PROFILETABLE.FILE_ID.eq(new JdbcNamedParameter("fileId")),
				SYSTEM_PROFILETABLE.FILE_PATHNAME.eq(new JdbcNamedParameter("filePathname")),
				SYSTEM_PROFILETABLE.FILE_TITLE.eq(new JdbcNamedParameter("fileTitle")),
				SYSTEM_PROFILETABLE.FILE_EXTENSION.eq(new JdbcNamedParameter("fileExtension")),
				SYSTEM_PROFILETABLE.FILE_SIZE.eq(new JdbcNamedParameter("fileSize")),
				SYSTEM_PROFILETABLE.FILE_OBJECT_TYPE.eq(new JdbcNamedParameter("fileObjectType")),
				SYSTEM_PROFILETABLE.FILE_OBJECT_ID.eq(new JdbcNamedParameter("fileObjectId")),
				SYSTEM_PROFILETABLE.FILE_ADDED_BY.eq(new JdbcNamedParameter("fileAddedBy")),
				SYSTEM_PROFILETABLE.FILE_ADDED_DATE.eq(new JdbcNamedParameter("fileAddedDate")),
				SYSTEM_PROFILETABLE.FILE_DOWNLOADS.eq(new JdbcNamedParameter("fileDownloads")),
				SYSTEM_PROFILETABLE.FILE_EXTRA.eq(new JdbcNamedParameter("fileExtra")),
				SYSTEM_PROFILETABLE.FILE_DELETED.eq(new JdbcNamedParameter("fileDeleted"))));
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
