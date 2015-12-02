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
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.common.log.annotation.LogClass;
import org.tinygroup.sdpm.common.log.annotation.LogMethod;
import org.tinygroup.sdpm.dao.complexsearch.SearchInfos;
import org.tinygroup.sdpm.dao.complexsearch.SqlUtil;
import org.tinygroup.sdpm.dao.update.UpdateUtil;
import org.tinygroup.sdpm.document.dao.DocumentDocDao;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoc;
import org.tinygroup.sdpm.org.dao.constant.OrgUserTable;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.base.FragmentSql;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.Join;
import org.tinygroup.tinysqldsl.select.OrderByElement;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.document.dao.constant.DocumentDocTable.DOCUMENT_DOCTABLE;
import static org.tinygroup.sdpm.document.dao.constant.DocumentDoclibTable.DOCUMENT_DOCLIBTABLE;
import static org.tinygroup.sdpm.product.dao.constant.ProductTable.PRODUCTTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectTable.PROJECTTABLE;
import static org.tinygroup.sdpm.system.dao.constant.SystemModuleTable.SYSTEM_MODULETABLE;
import static org.tinygroup.sdpm.org.dao.constant.OrgUserTable.ORG_USERTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
@LogClass("doc")
public class DocumentDocDaoImpl extends TinyDslDaoSupport implements DocumentDocDao {

	public DocumentDoc add(DocumentDoc documentDoc) {
		return getDslTemplate().insertAndReturnKey(documentDoc, new InsertGenerateCallback<DocumentDoc>() {
			public Insert generate(DocumentDoc t) {
				Insert insert = insertInto(DOCUMENT_DOCTABLE).values(
						DOCUMENT_DOCTABLE.DOC_ID.value(t.getDocId()),
						DOCUMENT_DOCTABLE.DOC_PRODUCT.value(t.getDocProduct()),
						DOCUMENT_DOCTABLE.DOC_PROJECT.value(t.getDocProject()),
						DOCUMENT_DOCTABLE.DOC_LIB_ID.value(t.getDocLibId()),
						DOCUMENT_DOCTABLE.DOC_MODULE.value(t.getDocModule()),
						DOCUMENT_DOCTABLE.DOC_TITLE.value(t.getDocTitle()),
						DOCUMENT_DOCTABLE.DOC_DIGEST.value(t.getDocDigest()),
						DOCUMENT_DOCTABLE.DOC_KEYWORDS.value(t.getDocKeywords()),
						DOCUMENT_DOCTABLE.DOC_TYPE.value(t.getDocType()),
						DOCUMENT_DOCTABLE.DOC_CONTENT.value(t.getDocContent()),
						DOCUMENT_DOCTABLE.DOC_URL.value(t.getDocUrl()),
						DOCUMENT_DOCTABLE.DOC_ATTACH.value(t.getDocAttach()),
						DOCUMENT_DOCTABLE.DOC_VIEWS.value(t.getDocViews()),
						DOCUMENT_DOCTABLE.DOC_ADDED_BY.value(t.getDocAddedBy()),
						DOCUMENT_DOCTABLE.DOC_ADDED_DATE.value(t.getDocAddedDate()),
						DOCUMENT_DOCTABLE.DOC_EDITED_BY.value(t.getDocEditedBy()),
						DOCUMENT_DOCTABLE.DOC_EDITED_DATE.value(t.getDocEditedDate()),
						DOCUMENT_DOCTABLE.DOC_DELETED.value(t.getDocDeleted()));
				return insert;
			}
		});
	}

	public int edit(DocumentDoc documentDoc) {
		if(documentDoc == null || documentDoc.getDocId() == null){
			return 0;
		}
		return getDslTemplate().update(documentDoc, new UpdateGenerateCallback<DocumentDoc>() {
			public Update generate(DocumentDoc t) {
				Update update = update(DOCUMENT_DOCTABLE).set(
						DOCUMENT_DOCTABLE.DOC_PRODUCT.value(t.getDocProduct()),
						DOCUMENT_DOCTABLE.DOC_PROJECT.value(t.getDocProject()),
						DOCUMENT_DOCTABLE.DOC_LIB_ID.value(t.getDocLibId()),
						DOCUMENT_DOCTABLE.DOC_MODULE.value(t.getDocModule()),
						DOCUMENT_DOCTABLE.DOC_TITLE.value(t.getDocTitle()),
						DOCUMENT_DOCTABLE.DOC_DIGEST.value(t.getDocDigest()),
						DOCUMENT_DOCTABLE.DOC_KEYWORDS.value(t.getDocKeywords()),
						DOCUMENT_DOCTABLE.DOC_TYPE.value(t.getDocType()),
						DOCUMENT_DOCTABLE.DOC_CONTENT.value(t.getDocContent()),
						DOCUMENT_DOCTABLE.DOC_URL.value(t.getDocUrl()),
						DOCUMENT_DOCTABLE.DOC_ATTACH.value(t.getDocAttach()),
						DOCUMENT_DOCTABLE.DOC_VIEWS.value(t.getDocViews()),
						DOCUMENT_DOCTABLE.DOC_ADDED_BY.value(t.getDocAddedBy()),
						DOCUMENT_DOCTABLE.DOC_ADDED_DATE.value(t.getDocAddedDate()),
						DOCUMENT_DOCTABLE.DOC_EDITED_BY.value(t.getDocEditedBy()),
						DOCUMENT_DOCTABLE.DOC_EDITED_DATE.value(t.getDocEditedDate()),
						DOCUMENT_DOCTABLE.DOC_DELETED.value(t.getDocDeleted())).where(
						DOCUMENT_DOCTABLE.DOC_ID.eq(t.getDocId()));
				return update;
			}
		});
	}

	public int editDoc(final DocumentDoc documentDoc) {
		if(documentDoc == null || documentDoc.getDocId() == null){
			return 0;
		}
		return getDslTemplate().update(documentDoc, new UpdateGenerateCallback<DocumentDoc>() {
			public Update generate(DocumentDoc t) {
				Update update = UpdateUtil.getUpdate(DOCUMENT_DOCTABLE,documentDoc);
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
				return delete(DOCUMENT_DOCTABLE).where(DOCUMENT_DOCTABLE.DOC_ID.eq(pk));
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
				return delete(DOCUMENT_DOCTABLE).where(DOCUMENT_DOCTABLE.DOC_ID.in(t));
			}
		},pks);
	}

	public DocumentDoc getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, DocumentDoc.class, new SelectGenerateCallback<Serializable>() {
			@SuppressWarnings("rawtypes")
			public Select generate(Serializable t) {
				return selectFrom(DOCUMENT_DOCTABLE).where(DOCUMENT_DOCTABLE.DOC_ID.eq(t));
			}
		});
	}

	public List<DocumentDoc> query(DocumentDoc documentDoc ,final OrderBy... orderArgs) {
		if(documentDoc==null){
			documentDoc=new DocumentDoc();
		}
		return getDslTemplate().query(documentDoc, new SelectGenerateCallback<DocumentDoc>() {

			@SuppressWarnings("rawtypes")
			public Select generate(DocumentDoc t) {
				Select select = selectFrom(DOCUMENT_DOCTABLE).where(
						and(
								DOCUMENT_DOCTABLE.DOC_PRODUCT.eq(t.getDocProduct()),
								DOCUMENT_DOCTABLE.DOC_PROJECT.eq(t.getDocProject()),
								DOCUMENT_DOCTABLE.DOC_LIB_ID.eq(t.getDocLibId()),
								DOCUMENT_DOCTABLE.DOC_MODULE.eq(t.getDocModule()),
								DOCUMENT_DOCTABLE.DOC_TITLE.eq(t.getDocTitle()),
								DOCUMENT_DOCTABLE.DOC_DIGEST.eq(t.getDocDigest()),
								DOCUMENT_DOCTABLE.DOC_KEYWORDS.eq(t.getDocKeywords()),
								DOCUMENT_DOCTABLE.DOC_TYPE.eq(t.getDocType()),
								DOCUMENT_DOCTABLE.DOC_CONTENT.eq(t.getDocContent()),
								DOCUMENT_DOCTABLE.DOC_URL.eq(t.getDocUrl()),
								DOCUMENT_DOCTABLE.DOC_ATTACH.eq(t.getDocAttach()),
								DOCUMENT_DOCTABLE.DOC_VIEWS.eq(t.getDocViews()),
								DOCUMENT_DOCTABLE.DOC_ADDED_BY.eq(t.getDocAddedBy()),
								DOCUMENT_DOCTABLE.DOC_ADDED_DATE.eq(t.getDocAddedDate()),
								DOCUMENT_DOCTABLE.DOC_EDITED_BY.eq(t.getDocEditedBy()),
								DOCUMENT_DOCTABLE.DOC_EDITED_DATE.eq(t.getDocEditedDate()),
								DOCUMENT_DOCTABLE.DOC_DELETED.eq(t.getDocDeleted())));
				return addOrderByElements(select, orderArgs);
			}
		});
	}

	public Pager<DocumentDoc> queryPager(int start,int limit ,DocumentDoc documentDoc ,final OrderBy... orderArgs) {
		if(documentDoc==null){
			documentDoc=new DocumentDoc();
		}
		return getDslTemplate().queryPager(start, limit, documentDoc, false, new SelectGenerateCallback<DocumentDoc>() {

			public Select generate(DocumentDoc t) {
				Select select = MysqlSelect.selectFrom(DOCUMENT_DOCTABLE).where(
						and(
								DOCUMENT_DOCTABLE.DOC_PRODUCT.eq(t.getDocProduct()),
								DOCUMENT_DOCTABLE.DOC_PROJECT.eq(t.getDocProject()),
								DOCUMENT_DOCTABLE.DOC_LIB_ID.eq(t.getDocLibId()),
								DOCUMENT_DOCTABLE.DOC_MODULE.eq(t.getDocModule()),
								DOCUMENT_DOCTABLE.DOC_TITLE.eq(t.getDocTitle()),
								DOCUMENT_DOCTABLE.DOC_DIGEST.eq(t.getDocDigest()),
								DOCUMENT_DOCTABLE.DOC_KEYWORDS.eq(t.getDocKeywords()),
								DOCUMENT_DOCTABLE.DOC_TYPE.eq(t.getDocType()),
								DOCUMENT_DOCTABLE.DOC_CONTENT.eq(t.getDocContent()),
								DOCUMENT_DOCTABLE.DOC_URL.eq(t.getDocUrl()),
								DOCUMENT_DOCTABLE.DOC_ATTACH.eq(t.getDocAttach()),
								DOCUMENT_DOCTABLE.DOC_VIEWS.eq(t.getDocViews()),
								DOCUMENT_DOCTABLE.DOC_ADDED_BY.eq(t.getDocAddedBy()),
								DOCUMENT_DOCTABLE.DOC_ADDED_DATE.eq(t.getDocAddedDate()),
								DOCUMENT_DOCTABLE.DOC_EDITED_BY.eq(t.getDocEditedBy()),
								DOCUMENT_DOCTABLE.DOC_EDITED_DATE.eq(t.getDocEditedDate()),
								DOCUMENT_DOCTABLE.DOC_DELETED.eq(t.getDocDeleted())));
				return addOrderByElements(select, orderArgs);
			}
		});
	}

	public Pager<DocumentDoc> complexQuery(int start, int limit,
										   DocumentDoc doc,
										   final String condition,
										   final SearchInfos searchConditions,
										   final String groupOperate,
										   final OrderBy... orderBys) {

		if(doc == null){
			doc =new DocumentDoc();
		}
		return getDslTemplate().queryPager(start, limit, doc, false, new SelectGenerateCallback<DocumentDoc>() {

			public Select generate(DocumentDoc t){

				Condition con = null;
				if(!StringUtil.isBlank(condition)){
					con = FragmentSql.fragmentCondition(condition);
				}
				if(searchConditions!=null) {
					String searchCondition = SqlUtil.toSql(searchConditions.getInfos(), groupOperate);
					if (!StringUtil.isBlank(searchCondition)) {
						con = con == null ? FragmentSql.fragmentCondition(searchCondition) : and(con, FragmentSql.fragmentCondition(searchCondition));
					}
				}
				Select select = MysqlSelect.select(DOCUMENT_DOCTABLE.ALL,
						DOCUMENT_DOCLIBTABLE.DOC_LIB_NAME.as("docLibName"),
						PRODUCTTABLE.PRODUCT_NAME.as("productName"),
						PROJECTTABLE.PROJECT_NAME.as("projectName"),
						SYSTEM_MODULETABLE.MODULE_NAME.as("moduleName"),
						((OrgUserTable) ORG_USERTABLE.as("docAddUserTable")).ORG_USER_REAL_NAME.as("docAddName"),
						((OrgUserTable) ORG_USERTABLE.as("docEditUserTable")).ORG_USER_REAL_NAME.as("docEditName"))
						.from(DOCUMENT_DOCTABLE).join(
								Join.leftJoin(DOCUMENT_DOCLIBTABLE, DOCUMENT_DOCLIBTABLE.DOC_LIB_ID.eq(DOCUMENT_DOCTABLE.DOC_LIB_ID)),
								Join.leftJoin(PRODUCTTABLE, PRODUCTTABLE.PRODUCT_ID.eq(DOCUMENT_DOCTABLE.DOC_PRODUCT)),
								Join.leftJoin(PROJECTTABLE, PROJECTTABLE.PROJECT_ID.eq(DOCUMENT_DOCTABLE.DOC_PROJECT)),
								Join.leftJoin(SYSTEM_MODULETABLE, SYSTEM_MODULETABLE.MODULE_ID.eq(DOCUMENT_DOCTABLE.DOC_MODULE)),
								Join.leftJoin(ORG_USERTABLE.as("docAddUserTable"), ((OrgUserTable) ORG_USERTABLE.as("docAddUserTable")).ORG_USER_ID.eq(DOCUMENT_DOCTABLE.DOC_ADDED_BY)),
								Join.leftJoin(ORG_USERTABLE.as("docEditUserTable"), ((OrgUserTable) ORG_USERTABLE.as("docEditUserTable")).ORG_USER_ID.eq(DOCUMENT_DOCTABLE.DOC_EDITED_BY))
						)
						.where(and(
								con,
								DOCUMENT_DOCTABLE.DOC_PRODUCT.eq(t.getDocProduct()),
								DOCUMENT_DOCTABLE.DOC_PROJECT.eq(t.getDocProject()),
								DOCUMENT_DOCTABLE.DOC_LIB_ID.eq(t.getDocLibId()),
								DOCUMENT_DOCTABLE.DOC_MODULE.eq(t.getDocModule()),
								DOCUMENT_DOCTABLE.DOC_TITLE.eq(t.getDocTitle()),
								DOCUMENT_DOCTABLE.DOC_DIGEST.eq(t.getDocDigest()),
								DOCUMENT_DOCTABLE.DOC_KEYWORDS.eq(t.getDocKeywords()),
								DOCUMENT_DOCTABLE.DOC_TYPE.eq(t.getDocType()),
								DOCUMENT_DOCTABLE.DOC_CONTENT.eq(t.getDocContent()),
								DOCUMENT_DOCTABLE.DOC_URL.eq(t.getDocUrl()),
								DOCUMENT_DOCTABLE.DOC_ATTACH.eq(t.getDocAttach()),
								DOCUMENT_DOCTABLE.DOC_VIEWS.eq(t.getDocViews()),
								DOCUMENT_DOCTABLE.DOC_ADDED_BY.eq(t.getDocAddedBy()),
								DOCUMENT_DOCTABLE.DOC_ADDED_DATE.eq(t.getDocAddedDate()),
								DOCUMENT_DOCTABLE.DOC_EDITED_BY.eq(t.getDocEditedBy()),
								DOCUMENT_DOCTABLE.DOC_EDITED_DATE.eq(t.getDocEditedDate()),
								DOCUMENT_DOCTABLE.DOC_DELETED.eq(t.getDocDeleted())));
				return addOrderByElements(select, orderBys);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<DocumentDoc> documentDocs) {
		if (CollectionUtil.isEmpty(documentDocs)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, documentDocs, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(DOCUMENT_DOCTABLE).values(
						DOCUMENT_DOCTABLE.DOC_PRODUCT.value(new JdbcNamedParameter("docProduct")),
						DOCUMENT_DOCTABLE.DOC_PROJECT.value(new JdbcNamedParameter("docProject")),
						DOCUMENT_DOCTABLE.DOC_LIB_ID.value(new JdbcNamedParameter("docLibId")),
						DOCUMENT_DOCTABLE.DOC_MODULE.value(new JdbcNamedParameter("docModule")),
						DOCUMENT_DOCTABLE.DOC_TITLE.value(new JdbcNamedParameter("docTitle")),
						DOCUMENT_DOCTABLE.DOC_DIGEST.value(new JdbcNamedParameter("docDigest")),
						DOCUMENT_DOCTABLE.DOC_KEYWORDS.value(new JdbcNamedParameter("docKeywords")),
						DOCUMENT_DOCTABLE.DOC_TYPE.value(new JdbcNamedParameter("docType")),
						DOCUMENT_DOCTABLE.DOC_CONTENT.value(new JdbcNamedParameter("docContent")),
						DOCUMENT_DOCTABLE.DOC_URL.value(new JdbcNamedParameter("docUrl")),
						DOCUMENT_DOCTABLE.DOC_ATTACH.value(new JdbcNamedParameter("docAttach")),
						DOCUMENT_DOCTABLE.DOC_VIEWS.value(new JdbcNamedParameter("docViews")),
						DOCUMENT_DOCTABLE.DOC_ADDED_BY.value(new JdbcNamedParameter("docAddedBy")),
						DOCUMENT_DOCTABLE.DOC_ADDED_DATE.value(new JdbcNamedParameter("docAddedDate")),
						DOCUMENT_DOCTABLE.DOC_EDITED_BY.value(new JdbcNamedParameter("docEditedBy")),
						DOCUMENT_DOCTABLE.DOC_EDITED_DATE.value(new JdbcNamedParameter("docEditedDate")),
						DOCUMENT_DOCTABLE.DOC_DELETED.value(new JdbcNamedParameter("docDeleted")));
			}
		});
	}

	public int[] batchInsert(List<DocumentDoc> documentDocs){
		return batchInsert(true ,documentDocs);
	}

	public int[] batchUpdate(List<DocumentDoc> documentDocs) {
		if (CollectionUtil.isEmpty(documentDocs)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(documentDocs, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(DOCUMENT_DOCTABLE).set(
						DOCUMENT_DOCTABLE.DOC_PRODUCT.value(new JdbcNamedParameter("docProduct")),
						DOCUMENT_DOCTABLE.DOC_PROJECT.value(new JdbcNamedParameter("docProject")),
						DOCUMENT_DOCTABLE.DOC_LIB_ID.value(new JdbcNamedParameter("docLibId")),
						DOCUMENT_DOCTABLE.DOC_MODULE.value(new JdbcNamedParameter("docModule")),
						DOCUMENT_DOCTABLE.DOC_TITLE.value(new JdbcNamedParameter("docTitle")),
						DOCUMENT_DOCTABLE.DOC_DIGEST.value(new JdbcNamedParameter("docDigest")),
						DOCUMENT_DOCTABLE.DOC_KEYWORDS.value(new JdbcNamedParameter("docKeywords")),
						DOCUMENT_DOCTABLE.DOC_TYPE.value(new JdbcNamedParameter("docType")),
						DOCUMENT_DOCTABLE.DOC_CONTENT.value(new JdbcNamedParameter("docContent")),
						DOCUMENT_DOCTABLE.DOC_URL.value(new JdbcNamedParameter("docUrl")),
						DOCUMENT_DOCTABLE.DOC_ATTACH.value(new JdbcNamedParameter("docAttach")),
						DOCUMENT_DOCTABLE.DOC_VIEWS.value(new JdbcNamedParameter("docViews")),
						DOCUMENT_DOCTABLE.DOC_ADDED_BY.value(new JdbcNamedParameter("docAddedBy")),
						DOCUMENT_DOCTABLE.DOC_ADDED_DATE.value(new JdbcNamedParameter("docAddedDate")),
						DOCUMENT_DOCTABLE.DOC_EDITED_BY.value(new JdbcNamedParameter("docEditedBy")),
						DOCUMENT_DOCTABLE.DOC_EDITED_DATE.value(new JdbcNamedParameter("docEditedDate")),
						DOCUMENT_DOCTABLE.DOC_DELETED.value(new JdbcNamedParameter("docDeleted"))).where(
						DOCUMENT_DOCTABLE.DOC_ID.eq(new JdbcNamedParameter("docId")));
			}
		});
	}

	public int[] batchDelete(List<DocumentDoc> documentDocs) {
		if (CollectionUtil.isEmpty(documentDocs)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(documentDocs, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(DOCUMENT_DOCTABLE).where(and(
						DOCUMENT_DOCTABLE.DOC_ID.eq(new JdbcNamedParameter("docId")),
						DOCUMENT_DOCTABLE.DOC_PRODUCT.eq(new JdbcNamedParameter("docProduct")),
						DOCUMENT_DOCTABLE.DOC_PROJECT.eq(new JdbcNamedParameter("docProject")),
						DOCUMENT_DOCTABLE.DOC_LIB_ID.eq(new JdbcNamedParameter("docLibId")),
						DOCUMENT_DOCTABLE.DOC_MODULE.eq(new JdbcNamedParameter("docModule")),
						DOCUMENT_DOCTABLE.DOC_TITLE.eq(new JdbcNamedParameter("docTitle")),
						DOCUMENT_DOCTABLE.DOC_DIGEST.eq(new JdbcNamedParameter("docDigest")),
						DOCUMENT_DOCTABLE.DOC_KEYWORDS.eq(new JdbcNamedParameter("docKeywords")),
						DOCUMENT_DOCTABLE.DOC_TYPE.eq(new JdbcNamedParameter("docType")),
						DOCUMENT_DOCTABLE.DOC_CONTENT.eq(new JdbcNamedParameter("docContent")),
						DOCUMENT_DOCTABLE.DOC_URL.eq(new JdbcNamedParameter("docUrl")),
						DOCUMENT_DOCTABLE.DOC_ATTACH.eq(new JdbcNamedParameter("docAttach")),
						DOCUMENT_DOCTABLE.DOC_VIEWS.eq(new JdbcNamedParameter("docViews")),
						DOCUMENT_DOCTABLE.DOC_ADDED_BY.eq(new JdbcNamedParameter("docAddedBy")),
						DOCUMENT_DOCTABLE.DOC_ADDED_DATE.eq(new JdbcNamedParameter("docAddedDate")),
						DOCUMENT_DOCTABLE.DOC_EDITED_BY.eq(new JdbcNamedParameter("docEditedBy")),
						DOCUMENT_DOCTABLE.DOC_EDITED_DATE.eq(new JdbcNamedParameter("docEditedDate")),
						DOCUMENT_DOCTABLE.DOC_DELETED.eq(new JdbcNamedParameter("docDeleted"))));
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

	public int[] batchUpdateDel(List<DocumentDoc> documentDocs) {
		if (CollectionUtil.isEmpty(documentDocs)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(documentDocs, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(DOCUMENT_DOCTABLE).set(
						DOCUMENT_DOCTABLE.DOC_DELETED.value(new JdbcNamedParameter("docDeleted"))).where(
						DOCUMENT_DOCTABLE.DOC_ID.eq(new JdbcNamedParameter("docId")));
			}
		});

	}


}
