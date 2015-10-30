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

import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.common.log.annotation.LogClass;
import org.tinygroup.sdpm.common.log.annotation.LogMethod;
import org.tinygroup.sdpm.document.dao.DocumentDoclibDao;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDocLib;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.document.dao.constant.DocumentDoclibTable.DOCUMENT_DOCLIBTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
@LogClass("doclib")
public class DocumentDoclibDaoImpl extends TinyDslDaoSupport implements DocumentDoclibDao {
	
	@LogMethod("add")
	public DocumentDocLib add(DocumentDocLib documentDocLib) {
		return getDslTemplate().insertAndReturnKey(documentDocLib, new InsertGenerateCallback<DocumentDocLib>() {
			public Insert generate(DocumentDocLib t) {
				Insert insert = insertInto(DOCUMENT_DOCLIBTABLE).values(
					DOCUMENT_DOCLIBTABLE.DOC_LIB_ID.value(t.getDocLibId()),
					DOCUMENT_DOCLIBTABLE.DOC_LIB_NAME.value(t.getDocLibName()),
					DOCUMENT_DOCLIBTABLE.DOC_LIB_DELETED.value(t.getDocLibDeleted()),
					DOCUMENT_DOCLIBTABLE.DOC_LIB_ADDED_DATE.value(t.getDocLibAddedDate()),
					DOCUMENT_DOCLIBTABLE.DOC_LIB_EDITED_DATE.value(t.getDocLibEditedDate()));
				return insert;
			}
		});
	}
	
	@LogMethod("edit")
	public int edit(DocumentDocLib documentDocLib) {
		if (documentDocLib == null || documentDocLib.getDocLibId() == null) {
			return 0;
		}
		return getDslTemplate().update(documentDocLib, new UpdateGenerateCallback<DocumentDocLib>() {
			public Update generate(DocumentDocLib t) {
				Update update = update(DOCUMENT_DOCLIBTABLE).set(
					DOCUMENT_DOCLIBTABLE.DOC_LIB_NAME.value(t.getDocLibName()),
					DOCUMENT_DOCLIBTABLE.DOC_LIB_DELETED.value(t.getDocLibDeleted()),
					DOCUMENT_DOCLIBTABLE.DOC_LIB_ADDED_DATE.value(t.getDocLibAddedDate()),
					DOCUMENT_DOCLIBTABLE.DOC_LIB_EDITED_DATE.value(t.getDocLibEditedDate())).where(
					DOCUMENT_DOCLIBTABLE.DOC_LIB_ID.eq(t.getDocLibId()));
				return update;
			}
		});
	}

	@LogMethod("deleteByKey")
	public int deleteByKey(Integer pk){
		if(pk == null){
			return 0;
		}
		return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
			public Delete generate(Serializable pk) {
				return delete(DOCUMENT_DOCLIBTABLE).where(DOCUMENT_DOCLIBTABLE.DOC_LIB_ID.eq(pk));
			}
		});
	}

	@LogMethod("deleteByKeys")
	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(DOCUMENT_DOCLIBTABLE).where(DOCUMENT_DOCLIBTABLE.DOC_LIB_ID.in(t));
		}
		},pks);
	}

	public DocumentDocLib getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, DocumentDocLib.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(DOCUMENT_DOCLIBTABLE).where(DOCUMENT_DOCLIBTABLE.DOC_LIB_ID.eq(t));
			}
		});
	}

	public List<DocumentDocLib> query(DocumentDocLib documentDocLib, final OrderBy... orderArgs) {
		if (documentDocLib == null) {
			documentDocLib = new DocumentDocLib();
		}
		return getDslTemplate().query(documentDocLib, new SelectGenerateCallback<DocumentDocLib>() {

			@SuppressWarnings("rawtypes")
			public Select generate(DocumentDocLib t) {
				Select select = selectFrom(DOCUMENT_DOCLIBTABLE).where(
				and(
					DOCUMENT_DOCLIBTABLE.DOC_LIB_NAME.eq(t.getDocLibName()),
					DOCUMENT_DOCLIBTABLE.DOC_LIB_DELETED.eq(t.getDocLibDeleted()),
					DOCUMENT_DOCLIBTABLE.DOC_LIB_ADDED_DATE.eq(t.getDocLibAddedDate()),
					DOCUMENT_DOCLIBTABLE.DOC_LIB_EDITED_DATE.eq(t.getDocLibEditedDate())));
			return addOrderByElements(select, orderArgs);
			}
		});
	}

	public Pager<DocumentDocLib> queryPager(int start, int limit, DocumentDocLib documentDocLib, final OrderBy... orderArgs) {
		if (documentDocLib == null) {
			documentDocLib = new DocumentDocLib();
		}
		return getDslTemplate().queryPager(start, limit, documentDocLib, false, new SelectGenerateCallback<DocumentDocLib>() {

			public Select generate(DocumentDocLib t) {
				Select select = MysqlSelect.selectFrom(DOCUMENT_DOCLIBTABLE).where(
				and(
					DOCUMENT_DOCLIBTABLE.DOC_LIB_NAME.eq(t.getDocLibName()),
					DOCUMENT_DOCLIBTABLE.DOC_LIB_DELETED.eq(t.getDocLibDeleted()),
					DOCUMENT_DOCLIBTABLE.DOC_LIB_ADDED_DATE.eq(t.getDocLibAddedDate()),
					DOCUMENT_DOCLIBTABLE.DOC_LIB_EDITED_DATE.eq(t.getDocLibEditedDate())));
			return addOrderByElements(select, orderArgs);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys, List<DocumentDocLib> documentDocLibs) {
		if (CollectionUtil.isEmpty(documentDocLibs)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, documentDocLibs, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(DOCUMENT_DOCLIBTABLE).values(
					DOCUMENT_DOCLIBTABLE.DOC_LIB_NAME.value(new JdbcNamedParameter("docLibName")),
					DOCUMENT_DOCLIBTABLE.DOC_LIB_DELETED.value(new JdbcNamedParameter("docLibDeleted")),
					DOCUMENT_DOCLIBTABLE.DOC_LIB_ADDED_DATE.value(new JdbcNamedParameter("docLibAddedDate")),
					DOCUMENT_DOCLIBTABLE.DOC_LIB_EDITED_DATE.value(new JdbcNamedParameter("docLibEditedDate")));
			}
		});
	}

	public int[] batchInsert(List<DocumentDocLib> documentDocLibs) {
		return batchInsert(true, documentDocLibs);
	}
	
	@LogMethod("batchUpdate")
	public int[] batchUpdate(List<DocumentDocLib> documentDocLibs) {
		if (CollectionUtil.isEmpty(documentDocLibs)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(documentDocLibs, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(DOCUMENT_DOCLIBTABLE).set(
					DOCUMENT_DOCLIBTABLE.DOC_LIB_NAME.value(new JdbcNamedParameter("docLibName")),
					DOCUMENT_DOCLIBTABLE.DOC_LIB_DELETED.value(new JdbcNamedParameter("docLibDeleted")),
					DOCUMENT_DOCLIBTABLE.DOC_LIB_ADDED_DATE.value(new JdbcNamedParameter("docLibAddedDate")),
					DOCUMENT_DOCLIBTABLE.DOC_LIB_EDITED_DATE.value(new JdbcNamedParameter("docLibEditedDate"))).where(
				DOCUMENT_DOCLIBTABLE.DOC_LIB_ID.eq(new JdbcNamedParameter("docLibId")));
			}
		});
	}

	@LogMethod("batchDelete")
	public int[] batchDelete(List<DocumentDocLib> documentDocLibs) {
		if (CollectionUtil.isEmpty(documentDocLibs)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(documentDocLibs, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(DOCUMENT_DOCLIBTABLE).where(and(
				DOCUMENT_DOCLIBTABLE.DOC_LIB_ID.eq(new JdbcNamedParameter("docLibId")),
				DOCUMENT_DOCLIBTABLE.DOC_LIB_NAME.eq(new JdbcNamedParameter("docLibName")),
				DOCUMENT_DOCLIBTABLE.DOC_LIB_DELETED.eq(new JdbcNamedParameter("docLibDeleted")),
				DOCUMENT_DOCLIBTABLE.DOC_LIB_ADDED_DATE.eq(new JdbcNamedParameter("docLibAddedDate")),
				DOCUMENT_DOCLIBTABLE.DOC_LIB_EDITED_DATE.eq(new JdbcNamedParameter("docLibEditedDate"))));
			}
		});
	}

	private  Select addOrderByElements(Select select ,OrderBy... orderBies){
		List<OrderByElement> orderByElements = new ArrayList<OrderByElement>();
		for (int i = 0; orderBies != null && i < orderBies.length; i++) {
			OrderByElement tempElement = null;
			if(orderBies[i]!=null){
				tempElement = orderBies[i].getOrderByElement();
			}
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
