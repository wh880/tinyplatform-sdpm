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

package org.tinygroup.sdpm.document.dao.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.document.constant.AttachmentTable.ATTACHMENTTABLE;
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
import org.tinygroup.sdpm.document.pojo.Attachment;
import org.tinygroup.sdpm.document.dao.inter.AttachmentDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class AttachmentDaoImpl extends TinyDslDaoSupport implements AttachmentDao {

	public Attachment add(Attachment attachment) {
		return getDslTemplate().insertAndReturnKey(attachment, new InsertGenerateCallback<Attachment>() {
			public Insert generate(Attachment t) {
				Insert insert = insertInto(ATTACHMENTTABLE).values(
					ATTACHMENTTABLE.ATTACH_ID.value(t.getAttachId()),
					ATTACHMENTTABLE.ATTACH_NAME.value(t.getAttachName()),
					ATTACHMENTTABLE.ATTACH_TYPE.value(t.getAttachType()),
					ATTACHMENTTABLE.ATTACH_URL.value(t.getAttachUrl()));
				return insert;
			}
		});
	}

	public int edit(Attachment attachment) {
		if(attachment == null || attachment.getAttachId() == null){
			return 0;
		}
		return getDslTemplate().update(attachment, new UpdateGenerateCallback<Attachment>() {
			public Update generate(Attachment t) {
				Update update = update(ATTACHMENTTABLE).set(
					ATTACHMENTTABLE.ATTACH_NAME.value(t.getAttachName()),
					ATTACHMENTTABLE.ATTACH_TYPE.value(t.getAttachType()),
					ATTACHMENTTABLE.ATTACH_URL.value(t.getAttachUrl())).where(
					ATTACHMENTTABLE.ATTACH_ID.eq(t.getAttachId()));
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
				return delete(ATTACHMENTTABLE).where(ATTACHMENTTABLE.ATTACH_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(ATTACHMENTTABLE).where(ATTACHMENTTABLE.ATTACH_ID.in(t));
		}
		},pks);
	}

	public Attachment getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Attachment.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(ATTACHMENTTABLE).where(ATTACHMENTTABLE.ATTACH_ID.eq(t));
			}
		});
	}

	public List<Attachment> query(Attachment attachment) {
		if(attachment==null){
			attachment=new Attachment();
		}
		return getDslTemplate().query(attachment, new SelectGenerateCallback<Attachment>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Attachment t) {
				return selectFrom(ATTACHMENTTABLE).where(
				and(
					ATTACHMENTTABLE.ATTACH_NAME.eq(t.getAttachName()),
					ATTACHMENTTABLE.ATTACH_TYPE.eq(t.getAttachType()),
					ATTACHMENTTABLE.ATTACH_URL.eq(t.getAttachUrl())));
			}
		});
	}

	public Pager<Attachment> queryPager(int start,int limit ,Attachment attachment) {
		if(attachment==null){
			attachment=new Attachment();
		}
		return getDslTemplate().queryPager(start, limit, attachment, false, new SelectGenerateCallback<Attachment>() {

			public Select generate(Attachment t) {
				return MysqlSelect.selectFrom(ATTACHMENTTABLE).where(
				and(
					ATTACHMENTTABLE.ATTACH_NAME.eq(t.getAttachName()),
					ATTACHMENTTABLE.ATTACH_TYPE.eq(t.getAttachType()),
					ATTACHMENTTABLE.ATTACH_URL.eq(t.getAttachUrl())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Attachment> attachments) {
		if (CollectionUtil.isEmpty(attachments)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, attachments, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(ATTACHMENTTABLE).values(
					ATTACHMENTTABLE.ATTACH_NAME.value(new JdbcNamedParameter("attachName")),
					ATTACHMENTTABLE.ATTACH_TYPE.value(new JdbcNamedParameter("attachType")),
					ATTACHMENTTABLE.ATTACH_URL.value(new JdbcNamedParameter("attachUrl")));
			}
		});
	}

	public int[] batchInsert(List<Attachment> attachments){
			return batchInsert(true ,attachments);
	}

	public int[] batchUpdate(List<Attachment> attachments) {
		if (CollectionUtil.isEmpty(attachments)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(attachments, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(ATTACHMENTTABLE).set(
					ATTACHMENTTABLE.ATTACH_NAME.value(new JdbcNamedParameter("attachName")),
					ATTACHMENTTABLE.ATTACH_TYPE.value(new JdbcNamedParameter("attachType")),
					ATTACHMENTTABLE.ATTACH_URL.value(new JdbcNamedParameter("attachUrl"))).where(
				ATTACHMENTTABLE.ATTACH_ID.eq(new JdbcNamedParameter("attachId")));
			}
		});
	}

	public int[] batchDelete(List<Attachment> attachments) {
		if (CollectionUtil.isEmpty(attachments)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(attachments, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(ATTACHMENTTABLE).where(and(
				ATTACHMENTTABLE.ATTACH_ID.eq(new JdbcNamedParameter("attachId")),
				ATTACHMENTTABLE.ATTACH_NAME.eq(new JdbcNamedParameter("attachName")),
				ATTACHMENTTABLE.ATTACH_TYPE.eq(new JdbcNamedParameter("attachType")),
				ATTACHMENTTABLE.ATTACH_URL.eq(new JdbcNamedParameter("attachUrl"))));
			}
		});
	}

}
