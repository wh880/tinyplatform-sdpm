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
import static org.tinygroup.sdpm.system.dao.constant.ProfileTable.*;
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
import org.tinygroup.sdpm.common.log.annotation.LogClass;
import org.tinygroup.sdpm.common.log.annotation.LogMethod;
import org.tinygroup.sdpm.system.dao.pojo.Profile;
import org.tinygroup.sdpm.system.dao.ProfileDao;
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
@LogClass("profile")
public class ProfileDaoImpl extends TinyDslDaoSupport implements ProfileDao {
	 @LogMethod("add")
	public Profile add(Profile profile) {
		return getDslTemplate().insertAndReturnKey(profile, new InsertGenerateCallback<Profile>() {
			public Insert generate(Profile t) {
				Insert insert = insertInto(PROFILETABLE).values(
					PROFILETABLE.FILE_ID.value(t.getFileId()),
					PROFILETABLE.FILE_PATHNAME.value(t.getFilePathname()),
					PROFILETABLE.FILE_TITLE.value(t.getFileTitle()),
					PROFILETABLE.FILE_EXTENSION.value(t.getFileExtension()),
					PROFILETABLE.FILE_SIZE.value(t.getFileSize()),
					PROFILETABLE.FILE_OBJECTTYPE.value(t.getFileObjectType()),
					PROFILETABLE.FILE_OBJECTID.value(t.getFileObjectID()),
					PROFILETABLE.FILE_ADDEDBY.value(t.getFileAddedBy()),
					PROFILETABLE.FILE_ADDEDDATE.value(t.getFileAddedDate()),
					PROFILETABLE.FILE_DOWNLOADS.value(t.getFileDownloads()),
					PROFILETABLE.FILE_EXTRA.value(t.getFileExtra()),
					PROFILETABLE.FILE_DELETED.value(t.getFileDeleted()));
				return insert;
			}
		});
	}
	 @LogMethod("edit")
	public int edit(Profile profile) {
		if(profile == null || profile.getFileId() == null){
			return 0;
		}
		return getDslTemplate().update(profile, new UpdateGenerateCallback<Profile>() {
			public Update generate(Profile t) {
				Update update = update(PROFILETABLE).set(
					PROFILETABLE.FILE_PATHNAME.value(t.getFilePathname()),
					PROFILETABLE.FILE_TITLE.value(t.getFileTitle()),
					PROFILETABLE.FILE_EXTENSION.value(t.getFileExtension()),
					PROFILETABLE.FILE_SIZE.value(t.getFileSize()),
					PROFILETABLE.FILE_OBJECTTYPE.value(t.getFileObjectType()),
					PROFILETABLE.FILE_OBJECTID.value(t.getFileObjectID()),
					PROFILETABLE.FILE_ADDEDBY.value(t.getFileAddedBy()),
					PROFILETABLE.FILE_ADDEDDATE.value(t.getFileAddedDate()),
					PROFILETABLE.FILE_DOWNLOADS.value(t.getFileDownloads()),
					PROFILETABLE.FILE_EXTRA.value(t.getFileExtra()),
					PROFILETABLE.FILE_DELETED.value(t.getFileDeleted())).where(
					PROFILETABLE.FILE_ID.eq(t.getFileId()));
				return update;
			}
		});
	}
	 @LogMethod("delete")
	public int deleteByKey(Integer pk){
		if(pk == null){
			return 0;
		}
		return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
			public Delete generate(Serializable pk) {
				return delete(PROFILETABLE).where(PROFILETABLE.FILE_ID.eq(pk));
			}
		});
	}
	 @LogMethod("delete")
	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(PROFILETABLE).where(PROFILETABLE.FILE_ID.in(t));
		}
		},pks);
	}

	public Profile getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Profile.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(PROFILETABLE).where(PROFILETABLE.FILE_ID.eq(t));
			}
		});
	}

	public List<Profile> query(Profile profile ,final OrderBy... orderBies) {
		if(profile==null){
			profile=new Profile();
		}
		return getDslTemplate().query(profile, new SelectGenerateCallback<Profile>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Profile t) {
				Select select = selectFrom(PROFILETABLE).where(
				and(
					PROFILETABLE.FILE_PATHNAME.eq(t.getFilePathname()),
					PROFILETABLE.FILE_TITLE.eq(t.getFileTitle()),
					PROFILETABLE.FILE_EXTENSION.eq(t.getFileExtension()),
					PROFILETABLE.FILE_SIZE.eq(t.getFileSize()),
					PROFILETABLE.FILE_OBJECTTYPE.eq(t.getFileObjectType()),
					PROFILETABLE.FILE_OBJECTID.eq(t.getFileObjectID()),
					PROFILETABLE.FILE_ADDEDBY.eq(t.getFileAddedBy()),
					PROFILETABLE.FILE_ADDEDDATE.eq(t.getFileAddedDate()),
					PROFILETABLE.FILE_DOWNLOADS.eq(t.getFileDownloads()),
					PROFILETABLE.FILE_EXTRA.eq(t.getFileExtra()),
					PROFILETABLE.FILE_DELETED.eq(t.getFileDeleted())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<Profile> queryPager(int start,int limit ,Profile profile ,final OrderBy... orderBies) {
		if(profile==null){
			profile=new Profile();
		}
		return getDslTemplate().queryPager(start, limit, profile, false, new SelectGenerateCallback<Profile>() {

			public Select generate(Profile t) {
				Select select = MysqlSelect.selectFrom(PROFILETABLE).where(
				and(
					PROFILETABLE.FILE_PATHNAME.eq(t.getFilePathname()),
					PROFILETABLE.FILE_TITLE.eq(t.getFileTitle()),
					PROFILETABLE.FILE_EXTENSION.eq(t.getFileExtension()),
					PROFILETABLE.FILE_SIZE.eq(t.getFileSize()),
					PROFILETABLE.FILE_OBJECTTYPE.eq(t.getFileObjectType()),
					PROFILETABLE.FILE_OBJECTID.eq(t.getFileObjectID()),
					PROFILETABLE.FILE_ADDEDBY.eq(t.getFileAddedBy()),
					PROFILETABLE.FILE_ADDEDDATE.eq(t.getFileAddedDate()),
					PROFILETABLE.FILE_DOWNLOADS.eq(t.getFileDownloads()),
					PROFILETABLE.FILE_EXTRA.eq(t.getFileExtra()),
					PROFILETABLE.FILE_DELETED.eq(t.getFileDeleted())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Profile> profiles) {
		if (CollectionUtil.isEmpty(profiles)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, profiles, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(PROFILETABLE).values(
					PROFILETABLE.FILE_PATHNAME.value(new JdbcNamedParameter("filePathname")),
					PROFILETABLE.FILE_TITLE.value(new JdbcNamedParameter("fileTitle")),
					PROFILETABLE.FILE_EXTENSION.value(new JdbcNamedParameter("fileExtension")),
					PROFILETABLE.FILE_SIZE.value(new JdbcNamedParameter("fileSize")),
					PROFILETABLE.FILE_OBJECTTYPE.value(new JdbcNamedParameter("fileObjectType")),
					PROFILETABLE.FILE_OBJECTID.value(new JdbcNamedParameter("fileObjectID")),
					PROFILETABLE.FILE_ADDEDBY.value(new JdbcNamedParameter("fileAddedBy")),
					PROFILETABLE.FILE_ADDEDDATE.value(new JdbcNamedParameter("fileAddedDate")),
					PROFILETABLE.FILE_DOWNLOADS.value(new JdbcNamedParameter("fileDownloads")),
					PROFILETABLE.FILE_EXTRA.value(new JdbcNamedParameter("fileExtra")),
					PROFILETABLE.FILE_DELETED.value(new JdbcNamedParameter("fileDeleted")));
			}
		});
	}

	public int[] batchInsert(List<Profile> profiles){
			return batchInsert(true ,profiles);
	}

	public int[] batchUpdate(List<Profile> profiles) {
		if (CollectionUtil.isEmpty(profiles)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(profiles, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(PROFILETABLE).set(
					PROFILETABLE.FILE_PATHNAME.value(new JdbcNamedParameter("filePathname")),
					PROFILETABLE.FILE_TITLE.value(new JdbcNamedParameter("fileTitle")),
					PROFILETABLE.FILE_EXTENSION.value(new JdbcNamedParameter("fileExtension")),
					PROFILETABLE.FILE_SIZE.value(new JdbcNamedParameter("fileSize")),
					PROFILETABLE.FILE_OBJECTTYPE.value(new JdbcNamedParameter("fileObjectType")),
					PROFILETABLE.FILE_OBJECTID.value(new JdbcNamedParameter("fileObjectID")),
					PROFILETABLE.FILE_ADDEDBY.value(new JdbcNamedParameter("fileAddedBy")),
					PROFILETABLE.FILE_ADDEDDATE.value(new JdbcNamedParameter("fileAddedDate")),
					PROFILETABLE.FILE_DOWNLOADS.value(new JdbcNamedParameter("fileDownloads")),
					PROFILETABLE.FILE_EXTRA.value(new JdbcNamedParameter("fileExtra")),
					PROFILETABLE.FILE_DELETED.value(new JdbcNamedParameter("fileDeleted"))).where(
				PROFILETABLE.FILE_ID.eq(new JdbcNamedParameter("fileId")));
			}
		});
	}

	public int[] batchDelete(List<Profile> profiles) {
		if (CollectionUtil.isEmpty(profiles)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(profiles, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(PROFILETABLE).where(and(
				PROFILETABLE.FILE_ID.eq(new JdbcNamedParameter("fileId")),
				PROFILETABLE.FILE_PATHNAME.eq(new JdbcNamedParameter("filePathname")),
				PROFILETABLE.FILE_TITLE.eq(new JdbcNamedParameter("fileTitle")),
				PROFILETABLE.FILE_EXTENSION.eq(new JdbcNamedParameter("fileExtension")),
				PROFILETABLE.FILE_SIZE.eq(new JdbcNamedParameter("fileSize")),
				PROFILETABLE.FILE_OBJECTTYPE.eq(new JdbcNamedParameter("fileObjectType")),
				PROFILETABLE.FILE_OBJECTID.eq(new JdbcNamedParameter("fileObjectID")),
				PROFILETABLE.FILE_ADDEDBY.eq(new JdbcNamedParameter("fileAddedBy")),
				PROFILETABLE.FILE_ADDEDDATE.eq(new JdbcNamedParameter("fileAddedDate")),
				PROFILETABLE.FILE_DOWNLOADS.eq(new JdbcNamedParameter("fileDownloads")),
				PROFILETABLE.FILE_EXTRA.eq(new JdbcNamedParameter("fileExtra")),
				PROFILETABLE.FILE_DELETED.eq(new JdbcNamedParameter("fileDeleted"))));
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
