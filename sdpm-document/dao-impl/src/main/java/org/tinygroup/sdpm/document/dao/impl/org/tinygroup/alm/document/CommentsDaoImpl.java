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

package org.tinygroup.sdpm.document.dao.impl.org.tinygroup.alm.document;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.document.dao.org.tinygroup.alm.document.constant.CommentsTable.*;
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
import org.tinygroup.sdpm.document.dao.org.tinygroup.alm.document.pojo.Comments;
import org.tinygroup.sdpm.document.dao.org.tinygroup.alm.document.CommentsDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class CommentsDaoImpl extends TinyDslDaoSupport implements CommentsDao {

	public Comments add(Comments comments) {
		return getDslTemplate().insertAndReturnKey(comments, new InsertGenerateCallback<Comments>() {
			public Insert generate(Comments t) {
				Insert insert = insertInto(COMMENTSTABLE).values(
					COMMENTSTABLE.COMMENT_ID.value(t.getCommentId()),
					COMMENTSTABLE.COMMENT_DOCID.value(t.getCommentDocid()),
					COMMENTSTABLE.COMMENT_USERID.value(t.getCommentUserid()),
					COMMENTSTABLE.COMMENT_TXT.value(t.getCommentTxt()),
					COMMENTSTABLE.COMMENT_TIME.value(t.getCommentTime()));
				return insert;
			}
		});
	}

	public int edit(Comments comments) {
		if(comments == null || comments.getCommentId() == null){
			return 0;
		}
		return getDslTemplate().update(comments, new UpdateGenerateCallback<Comments>() {
			public Update generate(Comments t) {
				Update update = update(COMMENTSTABLE).set(
					COMMENTSTABLE.COMMENT_DOCID.value(t.getCommentDocid()),
					COMMENTSTABLE.COMMENT_USERID.value(t.getCommentUserid()),
					COMMENTSTABLE.COMMENT_TXT.value(t.getCommentTxt()),
					COMMENTSTABLE.COMMENT_TIME.value(t.getCommentTime())).where(
					COMMENTSTABLE.COMMENT_ID.eq(t.getCommentId()));
				return update;
			}
		});
	}

	public int deleteByKey(BigInteger pk){
		if(pk == null){
			return 0;
		}
		return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
			public Delete generate(Serializable pk) {
				return delete(COMMENTSTABLE).where(COMMENTSTABLE.COMMENT_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(BigInteger... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(COMMENTSTABLE).where(COMMENTSTABLE.COMMENT_ID.in(t));
		}
		},pks);
	}

	public Comments getByKey(BigInteger pk) {
		return getDslTemplate().getByKey(pk, Comments.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(COMMENTSTABLE).where(COMMENTSTABLE.COMMENT_ID.eq(t));
			}
		});
	}

	public List<Comments> query(Comments comments) {
		if(comments==null){
			comments=new Comments();
		}
		return getDslTemplate().query(comments, new SelectGenerateCallback<Comments>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Comments t) {
				return selectFrom(COMMENTSTABLE).where(
				and(
					COMMENTSTABLE.COMMENT_DOCID.eq(t.getCommentDocid()),
					COMMENTSTABLE.COMMENT_USERID.eq(t.getCommentUserid()),
					COMMENTSTABLE.COMMENT_TXT.eq(t.getCommentTxt()),
					COMMENTSTABLE.COMMENT_TIME.eq(t.getCommentTime())));
			}
		});
	}

	public Pager<Comments> queryPager(int start,int limit ,Comments comments) {
		if(comments==null){
			comments=new Comments();
		}
		return getDslTemplate().queryPager(start, limit, comments, false, new SelectGenerateCallback<Comments>() {

			public Select generate(Comments t) {
				return MysqlSelect.selectFrom(COMMENTSTABLE).where(
				and(
					COMMENTSTABLE.COMMENT_DOCID.eq(t.getCommentDocid()),
					COMMENTSTABLE.COMMENT_USERID.eq(t.getCommentUserid()),
					COMMENTSTABLE.COMMENT_TXT.eq(t.getCommentTxt()),
					COMMENTSTABLE.COMMENT_TIME.eq(t.getCommentTime())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Comments> commentss) {
		if (CollectionUtil.isEmpty(commentss)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, commentss, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(COMMENTSTABLE).values(
					COMMENTSTABLE.COMMENT_DOCID.value(new JdbcNamedParameter("commentDocid")),
					COMMENTSTABLE.COMMENT_USERID.value(new JdbcNamedParameter("commentUserid")),
					COMMENTSTABLE.COMMENT_TXT.value(new JdbcNamedParameter("commentTxt")),
					COMMENTSTABLE.COMMENT_TIME.value(new JdbcNamedParameter("commentTime")));
			}
		});
	}

	public int[] batchInsert(List<Comments> commentss){
			return batchInsert(true ,commentss);
	}

	public int[] batchUpdate(List<Comments> commentss) {
		if (CollectionUtil.isEmpty(commentss)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(commentss, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(COMMENTSTABLE).set(
					COMMENTSTABLE.COMMENT_DOCID.value(new JdbcNamedParameter("commentDocid")),
					COMMENTSTABLE.COMMENT_USERID.value(new JdbcNamedParameter("commentUserid")),
					COMMENTSTABLE.COMMENT_TXT.value(new JdbcNamedParameter("commentTxt")),
					COMMENTSTABLE.COMMENT_TIME.value(new JdbcNamedParameter("commentTime"))).where(
				COMMENTSTABLE.COMMENT_ID.eq(new JdbcNamedParameter("commentId")));
			}
		});
	}

	public int[] batchDelete(List<Comments> commentss) {
		if (CollectionUtil.isEmpty(commentss)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(commentss, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(COMMENTSTABLE).where(and(
				COMMENTSTABLE.COMMENT_ID.eq(new JdbcNamedParameter("commentId")),
				COMMENTSTABLE.COMMENT_DOCID.eq(new JdbcNamedParameter("commentDocid")),
				COMMENTSTABLE.COMMENT_USERID.eq(new JdbcNamedParameter("commentUserid")),
				COMMENTSTABLE.COMMENT_TXT.eq(new JdbcNamedParameter("commentTxt")),
				COMMENTSTABLE.COMMENT_TIME.eq(new JdbcNamedParameter("commentTime"))));
			}
		});
	}

}
