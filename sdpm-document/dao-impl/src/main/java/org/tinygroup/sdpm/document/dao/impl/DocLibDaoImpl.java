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
import static org.tinygroup.sdpm.document.dao.constant.DocLibTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.List;

import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
	import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.sdpm.document.dao.pojo.DocLib;
import org.tinygroup.sdpm.document.dao.DocLibDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class DocLibDaoImpl extends TinyDslDaoSupport implements DocLibDao {

	public DocLib add(DocLib docLib) {
		return getDslTemplate().insertAndReturnKey(docLib, new InsertGenerateCallback<DocLib>() {
			public Insert generate(DocLib t) {
				Insert insert = insertInto(DOCLIBTABLE).values(
					DOCLIBTABLE.DOC_LIBID.value(t.getDocLibid()),
					DOCLIBTABLE.DOC_LIBNAME.value(t.getDocLibname()),
					DOCLIBTABLE.DOC_LIB_DELETED.value(t.getDocLibDeleted()),
					DOCLIBTABLE.DOC_LIB_ADDTIME.value(t.getDocLibAddtime()),
					DOCLIBTABLE.DOC_LIB_UPDTIME.value(t.getDocLibUpdtime()));
				return insert;
			}
		});
	}

	public int edit(DocLib docLib) {
		if(docLib == null || docLib.getDocLibid() == null){
			return 0;
		}
		return getDslTemplate().update(docLib, new UpdateGenerateCallback<DocLib>() {
			public Update generate(DocLib t) {
				Update update = update(DOCLIBTABLE).set(
					DOCLIBTABLE.DOC_LIBNAME.value(t.getDocLibname()),
					DOCLIBTABLE.DOC_LIB_DELETED.value(t.getDocLibDeleted()),
					DOCLIBTABLE.DOC_LIB_ADDTIME.value(t.getDocLibAddtime()),
					DOCLIBTABLE.DOC_LIB_UPDTIME.value(t.getDocLibUpdtime())).where(
					DOCLIBTABLE.DOC_LIBID.eq(t.getDocLibid()));
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
				return delete(DOCLIBTABLE).where(DOCLIBTABLE.DOC_LIBID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(DOCLIBTABLE).where(DOCLIBTABLE.DOC_LIBID.in(t));
		}
		},pks);
	}

	public DocLib getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, DocLib.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(DOCLIBTABLE).where(DOCLIBTABLE.DOC_LIBID.eq(t));
			}
		});
	}

	public List<DocLib> query(DocLib docLib ,final OrderBy... orderBies) {
		if(docLib==null){
			docLib=new DocLib();
		}
		return getDslTemplate().query(docLib, new SelectGenerateCallback<DocLib>() {

			@SuppressWarnings("rawtypes")
			public Select generate(DocLib t) {
				Select select = selectFrom(DOCLIBTABLE).where(
				and(
					DOCLIBTABLE.DOC_LIBNAME.eq(t.getDocLibname()),
					DOCLIBTABLE.DOC_LIB_DELETED.eq(t.getDocLibDeleted()),
					DOCLIBTABLE.DOC_LIB_ADDTIME.eq(t.getDocLibAddtime()),
					DOCLIBTABLE.DOC_LIB_UPDTIME.eq(t.getDocLibUpdtime())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<DocLib> queryPager(int start,int limit ,DocLib docLib ,final OrderBy... orderBies) {
		if(docLib==null){
			docLib=new DocLib();
		}
		return getDslTemplate().queryPager(start, limit, docLib, false, new SelectGenerateCallback<DocLib>() {

			public Select generate(DocLib t) {
				Select select = MysqlSelect.selectFrom(DOCLIBTABLE).where(
				and(
					DOCLIBTABLE.DOC_LIBNAME.eq(t.getDocLibname()),
					DOCLIBTABLE.DOC_LIB_DELETED.eq(t.getDocLibDeleted()),
					DOCLIBTABLE.DOC_LIB_ADDTIME.eq(t.getDocLibAddtime()),
					DOCLIBTABLE.DOC_LIB_UPDTIME.eq(t.getDocLibUpdtime())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<DocLib> docLibs) {
		if (CollectionUtil.isEmpty(docLibs)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, docLibs, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(DOCLIBTABLE).values(
					DOCLIBTABLE.DOC_LIBNAME.value(new JdbcNamedParameter("docLibname")),
					DOCLIBTABLE.DOC_LIB_DELETED.value(new JdbcNamedParameter("docLibDeleted")),
					DOCLIBTABLE.DOC_LIB_ADDTIME.value(new JdbcNamedParameter("docLibAddtime")),
					DOCLIBTABLE.DOC_LIB_UPDTIME.value(new JdbcNamedParameter("docLibUpdtime")));
			}
		});
	}

	public int[] batchInsert(List<DocLib> docLibs){
			return batchInsert(true ,docLibs);
	}

	public int[] batchUpdate(List<DocLib> docLibs) {
		if (CollectionUtil.isEmpty(docLibs)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(docLibs, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(DOCLIBTABLE).set(
					DOCLIBTABLE.DOC_LIBNAME.value(new JdbcNamedParameter("docLibname")),
					DOCLIBTABLE.DOC_LIB_DELETED.value(new JdbcNamedParameter("docLibDeleted")),
					DOCLIBTABLE.DOC_LIB_ADDTIME.value(new JdbcNamedParameter("docLibAddtime")),
					DOCLIBTABLE.DOC_LIB_UPDTIME.value(new JdbcNamedParameter("docLibUpdtime"))).where(
				DOCLIBTABLE.DOC_LIBID.eq(new JdbcNamedParameter("docLibid")));
			}
		});
	}

	public int[] batchDelete(List<DocLib> docLibs) {
		if (CollectionUtil.isEmpty(docLibs)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(docLibs, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(DOCLIBTABLE).where(and(
				DOCLIBTABLE.DOC_LIBID.eq(new JdbcNamedParameter("docLibid")),
				DOCLIBTABLE.DOC_LIBNAME.eq(new JdbcNamedParameter("docLibname")),
				DOCLIBTABLE.DOC_LIB_DELETED.eq(new JdbcNamedParameter("docLibDeleted")),
				DOCLIBTABLE.DOC_LIB_ADDTIME.eq(new JdbcNamedParameter("docLibAddtime")),
				DOCLIBTABLE.DOC_LIB_UPDTIME.eq(new JdbcNamedParameter("docLibUpdtime"))));
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
