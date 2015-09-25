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
import static org.tinygroup.sdpm.document.dao.constant.DoclibTable.*;
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
import org.tinygroup.sdpm.document.dao.pojo.Doclib;
import org.tinygroup.sdpm.document.dao.DoclibDao;
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
public class DoclibDaoImpl extends TinyDslDaoSupport implements DoclibDao {

	public Doclib add(Doclib doclib) {
		return getDslTemplate().insertAndReturnKey(doclib, new InsertGenerateCallback<Doclib>() {
			public Insert generate(Doclib t) {
				Insert insert = insertInto(DOCLIBTABLE).values(
					DOCLIBTABLE.DOC_LIB_ID.value(t.getDocLibId()),
					DOCLIBTABLE.DOC_LIB_NAME.value(t.getDocLibName()),
					DOCLIBTABLE.DOC_LIB_DELETED.value(t.getDocLibDeleted()),
					DOCLIBTABLE.DOC_LIB_ADDED_DATE.value(t.getDocLibAddedDate()),
					DOCLIBTABLE.DOC_LIB_EDITED_DATE.value(t.getDocLibEditedDate()));
				return insert;
			}
		});
	}

	public int edit(Doclib doclib) {
		if(doclib == null || doclib.getDocLibId() == null){
			return 0;
		}
		return getDslTemplate().update(doclib, new UpdateGenerateCallback<Doclib>() {
			public Update generate(Doclib t) {
				Update update = update(DOCLIBTABLE).set(
					DOCLIBTABLE.DOC_LIB_NAME.value(t.getDocLibName()),
					DOCLIBTABLE.DOC_LIB_DELETED.value(t.getDocLibDeleted()),
					DOCLIBTABLE.DOC_LIB_ADDED_DATE.value(t.getDocLibAddedDate()),
					DOCLIBTABLE.DOC_LIB_EDITED_DATE.value(t.getDocLibEditedDate())).where(
					DOCLIBTABLE.DOC_LIB_ID.eq(t.getDocLibId()));
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
				return delete(DOCLIBTABLE).where(DOCLIBTABLE.DOC_LIB_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(DOCLIBTABLE).where(DOCLIBTABLE.DOC_LIB_ID.in(t));
		}
		},pks);
	}

	public Doclib getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Doclib.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(DOCLIBTABLE).where(DOCLIBTABLE.DOC_LIB_ID.eq(t));
			}
		});
	}

	public List<Doclib> query(Doclib doclib ,final OrderBy... orderBies) {
		if(doclib==null){
			doclib=new Doclib();
		}
		return getDslTemplate().query(doclib, new SelectGenerateCallback<Doclib>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Doclib t) {
				Select select = selectFrom(DOCLIBTABLE).where(
				and(
					DOCLIBTABLE.DOC_LIB_NAME.eq(t.getDocLibName()),
					DOCLIBTABLE.DOC_LIB_DELETED.eq(t.getDocLibDeleted()),
					DOCLIBTABLE.DOC_LIB_ADDED_DATE.eq(t.getDocLibAddedDate()),
					DOCLIBTABLE.DOC_LIB_EDITED_DATE.eq(t.getDocLibEditedDate())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<Doclib> queryPager(int start,int limit ,Doclib doclib ,final OrderBy... orderBies) {
		if(doclib==null){
			doclib=new Doclib();
		}
		return getDslTemplate().queryPager(start, limit, doclib, false, new SelectGenerateCallback<Doclib>() {

			public Select generate(Doclib t) {
				Select select = MysqlSelect.selectFrom(DOCLIBTABLE).where(
				and(
					DOCLIBTABLE.DOC_LIB_NAME.eq(t.getDocLibName()),
					DOCLIBTABLE.DOC_LIB_DELETED.eq(t.getDocLibDeleted()),
					DOCLIBTABLE.DOC_LIB_ADDED_DATE.eq(t.getDocLibAddedDate()),
					DOCLIBTABLE.DOC_LIB_EDITED_DATE.eq(t.getDocLibEditedDate())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Doclib> doclibs) {
		if (CollectionUtil.isEmpty(doclibs)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, doclibs, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(DOCLIBTABLE).values(
					DOCLIBTABLE.DOC_LIB_NAME.value(new JdbcNamedParameter("docLibName")),
					DOCLIBTABLE.DOC_LIB_DELETED.value(new JdbcNamedParameter("docLibDeleted")),
					DOCLIBTABLE.DOC_LIB_ADDED_DATE.value(new JdbcNamedParameter("docLibAddedDate")),
					DOCLIBTABLE.DOC_LIB_EDITED_DATE.value(new JdbcNamedParameter("docLibEditedDate")));
			}
		});
	}

	public int[] batchInsert(List<Doclib> doclibs){
			return batchInsert(true ,doclibs);
	}

	public int[] batchUpdate(List<Doclib> doclibs) {
		if (CollectionUtil.isEmpty(doclibs)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(doclibs, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(DOCLIBTABLE).set(
					DOCLIBTABLE.DOC_LIB_NAME.value(new JdbcNamedParameter("docLibName")),
					DOCLIBTABLE.DOC_LIB_DELETED.value(new JdbcNamedParameter("docLibDeleted")),
					DOCLIBTABLE.DOC_LIB_ADDED_DATE.value(new JdbcNamedParameter("docLibAddedDate")),
					DOCLIBTABLE.DOC_LIB_EDITED_DATE.value(new JdbcNamedParameter("docLibEditedDate"))).where(
				DOCLIBTABLE.DOC_LIB_ID.eq(new JdbcNamedParameter("docLibId")));
			}
		});
	}

	public int[] batchDelete(List<Doclib> doclibs) {
		if (CollectionUtil.isEmpty(doclibs)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(doclibs, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(DOCLIBTABLE).where(and(
				DOCLIBTABLE.DOC_LIB_ID.eq(new JdbcNamedParameter("docLibId")),
				DOCLIBTABLE.DOC_LIB_NAME.eq(new JdbcNamedParameter("docLibName")),
				DOCLIBTABLE.DOC_LIB_DELETED.eq(new JdbcNamedParameter("docLibDeleted")),
				DOCLIBTABLE.DOC_LIB_ADDED_DATE.eq(new JdbcNamedParameter("docLibAddedDate")),
				DOCLIBTABLE.DOC_LIB_EDITED_DATE.eq(new JdbcNamedParameter("docLibEditedDate"))));
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
