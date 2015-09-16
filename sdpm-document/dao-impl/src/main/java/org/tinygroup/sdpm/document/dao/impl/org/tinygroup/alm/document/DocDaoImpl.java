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
import static org.tinygroup.sdpm.document.dao.org.tinygroup.alm.document.constant.DocTable.*;
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
import org.tinygroup.sdpm.document.dao.org.tinygroup.alm.document.pojo.Doc;
import org.tinygroup.sdpm.document.dao.org.tinygroup.alm.document.DocDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class DocDaoImpl extends TinyDslDaoSupport implements DocDao {

	public Doc add(Doc doc) {
		return getDslTemplate().insertAndReturnKey(doc, new InsertGenerateCallback<Doc>() {
			public Insert generate(Doc t) {
				Insert insert = insertInto(DOCTABLE).values(
					DOCTABLE.DOC_ID.value(t.getDocId()),
					DOCTABLE.DOC_PRODUCT.value(t.getDocProduct()),
					DOCTABLE.DOC_PROJECT.value(t.getDocProject()),
					DOCTABLE.DOC_LIB.value(t.getDocLib()),
					DOCTABLE.DOC_MODULE.value(t.getDocModule()),
					DOCTABLE.DOC_TITLE.value(t.getDocTitle()),
					DOCTABLE.DOC_DIGEST.value(t.getDocDigest()),
					DOCTABLE.DOC_KEYWORDS.value(t.getDocKeywords()),
					DOCTABLE.DOC_TYPE.value(t.getDocType()),
					DOCTABLE.DOC_CONTENT.value(t.getDocContent()),
					DOCTABLE.DOC_URL.value(t.getDocUrl()),
					DOCTABLE.DOC_VIEWS.value(t.getDocViews()),
					DOCTABLE.DOC_ADDEDBY.value(t.getDocAddedBy()),
					DOCTABLE.DOC_ADDEDDATE.value(t.getDocAddedDate()),
					DOCTABLE.DOC_EDITEDBY.value(t.getDocEditedBy()),
					DOCTABLE.DOC_EDITEDDATE.value(t.getDocEditedDate()),
					DOCTABLE.DOC_DELETED.value(t.getDocDeleted()));
				return insert;
			}
		});
	}

	public int edit(Doc doc) {
		if(doc == null || doc.getDocId() == null){
			return 0;
		}
		return getDslTemplate().update(doc, new UpdateGenerateCallback<Doc>() {
			public Update generate(Doc t) {
				Update update = update(DOCTABLE).set(
					DOCTABLE.DOC_PRODUCT.value(t.getDocProduct()),
					DOCTABLE.DOC_PROJECT.value(t.getDocProject()),
					DOCTABLE.DOC_LIB.value(t.getDocLib()),
					DOCTABLE.DOC_MODULE.value(t.getDocModule()),
					DOCTABLE.DOC_TITLE.value(t.getDocTitle()),
					DOCTABLE.DOC_DIGEST.value(t.getDocDigest()),
					DOCTABLE.DOC_KEYWORDS.value(t.getDocKeywords()),
					DOCTABLE.DOC_TYPE.value(t.getDocType()),
					DOCTABLE.DOC_CONTENT.value(t.getDocContent()),
					DOCTABLE.DOC_URL.value(t.getDocUrl()),
					DOCTABLE.DOC_VIEWS.value(t.getDocViews()),
					DOCTABLE.DOC_ADDEDBY.value(t.getDocAddedBy()),
					DOCTABLE.DOC_ADDEDDATE.value(t.getDocAddedDate()),
					DOCTABLE.DOC_EDITEDBY.value(t.getDocEditedBy()),
					DOCTABLE.DOC_EDITEDDATE.value(t.getDocEditedDate()),
					DOCTABLE.DOC_DELETED.value(t.getDocDeleted())).where(
					DOCTABLE.DOC_ID.eq(t.getDocId()));
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
				return delete(DOCTABLE).where(DOCTABLE.DOC_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(BigInteger... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(DOCTABLE).where(DOCTABLE.DOC_ID.in(t));
		}
		},pks);
	}

	public Doc getByKey(BigInteger pk) {
		return getDslTemplate().getByKey(pk, Doc.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(DOCTABLE).where(DOCTABLE.DOC_ID.eq(t));
			}
		});
	}

	public List<Doc> query(Doc doc) {
		if(doc==null){
			doc=new Doc();
		}
		return getDslTemplate().query(doc, new SelectGenerateCallback<Doc>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Doc t) {
				return selectFrom(DOCTABLE).where(
				and(
					DOCTABLE.DOC_PRODUCT.eq(t.getDocProduct()),
					DOCTABLE.DOC_PROJECT.eq(t.getDocProject()),
					DOCTABLE.DOC_LIB.eq(t.getDocLib()),
					DOCTABLE.DOC_MODULE.eq(t.getDocModule()),
					DOCTABLE.DOC_TITLE.eq(t.getDocTitle()),
					DOCTABLE.DOC_DIGEST.eq(t.getDocDigest()),
					DOCTABLE.DOC_KEYWORDS.eq(t.getDocKeywords()),
					DOCTABLE.DOC_TYPE.eq(t.getDocType()),
					DOCTABLE.DOC_CONTENT.eq(t.getDocContent()),
					DOCTABLE.DOC_URL.eq(t.getDocUrl()),
					DOCTABLE.DOC_VIEWS.eq(t.getDocViews()),
					DOCTABLE.DOC_ADDEDBY.eq(t.getDocAddedBy()),
					DOCTABLE.DOC_ADDEDDATE.eq(t.getDocAddedDate()),
					DOCTABLE.DOC_EDITEDBY.eq(t.getDocEditedBy()),
					DOCTABLE.DOC_EDITEDDATE.eq(t.getDocEditedDate()),
					DOCTABLE.DOC_DELETED.eq(t.getDocDeleted())));
			}
		});
	}

	public Pager<Doc> queryPager(int start,int limit ,Doc doc) {
		if(doc==null){
			doc=new Doc();
		}
		return getDslTemplate().queryPager(start, limit, doc, false, new SelectGenerateCallback<Doc>() {

			public Select generate(Doc t) {
				return MysqlSelect.selectFrom(DOCTABLE).where(
				and(
					DOCTABLE.DOC_PRODUCT.eq(t.getDocProduct()),
					DOCTABLE.DOC_PROJECT.eq(t.getDocProject()),
					DOCTABLE.DOC_LIB.eq(t.getDocLib()),
					DOCTABLE.DOC_MODULE.eq(t.getDocModule()),
					DOCTABLE.DOC_TITLE.eq(t.getDocTitle()),
					DOCTABLE.DOC_DIGEST.eq(t.getDocDigest()),
					DOCTABLE.DOC_KEYWORDS.eq(t.getDocKeywords()),
					DOCTABLE.DOC_TYPE.eq(t.getDocType()),
					DOCTABLE.DOC_CONTENT.eq(t.getDocContent()),
					DOCTABLE.DOC_URL.eq(t.getDocUrl()),
					DOCTABLE.DOC_VIEWS.eq(t.getDocViews()),
					DOCTABLE.DOC_ADDEDBY.eq(t.getDocAddedBy()),
					DOCTABLE.DOC_ADDEDDATE.eq(t.getDocAddedDate()),
					DOCTABLE.DOC_EDITEDBY.eq(t.getDocEditedBy()),
					DOCTABLE.DOC_EDITEDDATE.eq(t.getDocEditedDate()),
					DOCTABLE.DOC_DELETED.eq(t.getDocDeleted())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Doc> docs) {
		if (CollectionUtil.isEmpty(docs)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, docs, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(DOCTABLE).values(
					DOCTABLE.DOC_PRODUCT.value(new JdbcNamedParameter("docProduct")),
					DOCTABLE.DOC_PROJECT.value(new JdbcNamedParameter("docProject")),
					DOCTABLE.DOC_LIB.value(new JdbcNamedParameter("docLib")),
					DOCTABLE.DOC_MODULE.value(new JdbcNamedParameter("docModule")),
					DOCTABLE.DOC_TITLE.value(new JdbcNamedParameter("docTitle")),
					DOCTABLE.DOC_DIGEST.value(new JdbcNamedParameter("docDigest")),
					DOCTABLE.DOC_KEYWORDS.value(new JdbcNamedParameter("docKeywords")),
					DOCTABLE.DOC_TYPE.value(new JdbcNamedParameter("docType")),
					DOCTABLE.DOC_CONTENT.value(new JdbcNamedParameter("docContent")),
					DOCTABLE.DOC_URL.value(new JdbcNamedParameter("docUrl")),
					DOCTABLE.DOC_VIEWS.value(new JdbcNamedParameter("docViews")),
					DOCTABLE.DOC_ADDEDBY.value(new JdbcNamedParameter("docAddedBy")),
					DOCTABLE.DOC_ADDEDDATE.value(new JdbcNamedParameter("docAddedDate")),
					DOCTABLE.DOC_EDITEDBY.value(new JdbcNamedParameter("docEditedBy")),
					DOCTABLE.DOC_EDITEDDATE.value(new JdbcNamedParameter("docEditedDate")),
					DOCTABLE.DOC_DELETED.value(new JdbcNamedParameter("docDeleted")));
			}
		});
	}

	public int[] batchInsert(List<Doc> docs){
			return batchInsert(true ,docs);
	}

	public int[] batchUpdate(List<Doc> docs) {
		if (CollectionUtil.isEmpty(docs)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(docs, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(DOCTABLE).set(
					DOCTABLE.DOC_PRODUCT.value(new JdbcNamedParameter("docProduct")),
					DOCTABLE.DOC_PROJECT.value(new JdbcNamedParameter("docProject")),
					DOCTABLE.DOC_LIB.value(new JdbcNamedParameter("docLib")),
					DOCTABLE.DOC_MODULE.value(new JdbcNamedParameter("docModule")),
					DOCTABLE.DOC_TITLE.value(new JdbcNamedParameter("docTitle")),
					DOCTABLE.DOC_DIGEST.value(new JdbcNamedParameter("docDigest")),
					DOCTABLE.DOC_KEYWORDS.value(new JdbcNamedParameter("docKeywords")),
					DOCTABLE.DOC_TYPE.value(new JdbcNamedParameter("docType")),
					DOCTABLE.DOC_CONTENT.value(new JdbcNamedParameter("docContent")),
					DOCTABLE.DOC_URL.value(new JdbcNamedParameter("docUrl")),
					DOCTABLE.DOC_VIEWS.value(new JdbcNamedParameter("docViews")),
					DOCTABLE.DOC_ADDEDBY.value(new JdbcNamedParameter("docAddedBy")),
					DOCTABLE.DOC_ADDEDDATE.value(new JdbcNamedParameter("docAddedDate")),
					DOCTABLE.DOC_EDITEDBY.value(new JdbcNamedParameter("docEditedBy")),
					DOCTABLE.DOC_EDITEDDATE.value(new JdbcNamedParameter("docEditedDate")),
					DOCTABLE.DOC_DELETED.value(new JdbcNamedParameter("docDeleted"))).where(
				DOCTABLE.DOC_ID.eq(new JdbcNamedParameter("docId")));
			}
		});
	}

	public int[] batchDelete(List<Doc> docs) {
		if (CollectionUtil.isEmpty(docs)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(docs, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(DOCTABLE).where(and(
				DOCTABLE.DOC_ID.eq(new JdbcNamedParameter("docId")),
				DOCTABLE.DOC_PRODUCT.eq(new JdbcNamedParameter("docProduct")),
				DOCTABLE.DOC_PROJECT.eq(new JdbcNamedParameter("docProject")),
				DOCTABLE.DOC_LIB.eq(new JdbcNamedParameter("docLib")),
				DOCTABLE.DOC_MODULE.eq(new JdbcNamedParameter("docModule")),
				DOCTABLE.DOC_TITLE.eq(new JdbcNamedParameter("docTitle")),
				DOCTABLE.DOC_DIGEST.eq(new JdbcNamedParameter("docDigest")),
				DOCTABLE.DOC_KEYWORDS.eq(new JdbcNamedParameter("docKeywords")),
				DOCTABLE.DOC_TYPE.eq(new JdbcNamedParameter("docType")),
				DOCTABLE.DOC_CONTENT.eq(new JdbcNamedParameter("docContent")),
				DOCTABLE.DOC_URL.eq(new JdbcNamedParameter("docUrl")),
				DOCTABLE.DOC_VIEWS.eq(new JdbcNamedParameter("docViews")),
				DOCTABLE.DOC_ADDEDBY.eq(new JdbcNamedParameter("docAddedBy")),
				DOCTABLE.DOC_ADDEDDATE.eq(new JdbcNamedParameter("docAddedDate")),
				DOCTABLE.DOC_EDITEDBY.eq(new JdbcNamedParameter("docEditedBy")),
				DOCTABLE.DOC_EDITEDDATE.eq(new JdbcNamedParameter("docEditedDate")),
				DOCTABLE.DOC_DELETED.eq(new JdbcNamedParameter("docDeleted"))));
			}
		});
	}

}
