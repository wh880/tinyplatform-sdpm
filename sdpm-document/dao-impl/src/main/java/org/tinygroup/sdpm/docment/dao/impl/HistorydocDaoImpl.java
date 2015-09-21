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

package org.tinygroup.sdpm.docment.dao.impl;

import static org.tinygroup.sdpm.docment.constant.HistorydocTable.*;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;
import java.util.List;

import org.tinygroup.sdpm.docment.dao.inter.HistorydocDao;
import org.tinygroup.sdpm.docment.pojo.Historydoc;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class HistorydocDaoImpl extends TinyDslDaoSupport implements HistorydocDao {

	public Historydoc add(Historydoc historydoc) {
		return getDslTemplate().insertAndReturnKey(historydoc, new InsertGenerateCallback<Historydoc>() {
			public Insert generate(Historydoc t) {
				Insert insert = insertInto(HISTORYDOCTABLE).values(
					HISTORYDOCTABLE.RECORD_ID.value(t.getRecordId()),
					HISTORYDOCTABLE.DOC_ID.value(t.getDocId()),
					HISTORYDOCTABLE.REC_TIME.value(t.getRecTime()),
					HISTORYDOCTABLE.REC_WHO.value(t.getRecWho()));
				return insert;
			}
		});
	}

	public int edit(Historydoc historydoc) {
		if(historydoc == null || historydoc.getRecordId() == null){
			return 0;
		}
		return getDslTemplate().update(historydoc, new UpdateGenerateCallback<Historydoc>() {
			public Update generate(Historydoc t) {
				Update update = update(HISTORYDOCTABLE).set(
						HISTORYDOCTABLE.DOC_ID.value(t.getDocId()),
					HISTORYDOCTABLE.REC_TIME.value(t.getRecTime()),
					HISTORYDOCTABLE.REC_WHO.value(t.getRecWho())).where(
					HISTORYDOCTABLE.RECORD_ID.eq(t.getRecordId()));
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
				return delete(HISTORYDOCTABLE).where(HISTORYDOCTABLE.RECORD_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(HISTORYDOCTABLE).where(HISTORYDOCTABLE.RECORD_ID.in(t));
		}
		},pks);
	}

	public Historydoc getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Historydoc.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(HISTORYDOCTABLE).where(HISTORYDOCTABLE.RECORD_ID.eq(t));
			}
		});
	}

	public List<Historydoc> query(Historydoc historydoc) {
		if(historydoc==null){
			historydoc=new Historydoc();
		}
		return getDslTemplate().query(historydoc, new SelectGenerateCallback<Historydoc>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Historydoc t) {
				return selectFrom(HISTORYDOCTABLE).where(
				and(
						HISTORYDOCTABLE.DOC_ID.eq(t.getDocId()),
					HISTORYDOCTABLE.REC_TIME.eq(t.getRecTime()),
					HISTORYDOCTABLE.REC_WHO.eq(t.getRecWho())));
			}
		});
	}

	public Pager<Historydoc> queryPager(int start,int limit ,Historydoc historydoc) {
		if(historydoc==null){
			historydoc=new Historydoc();
		}
		return getDslTemplate().queryPager(start, limit, historydoc, false, new SelectGenerateCallback<Historydoc>() {

			public Select generate(Historydoc t) {
				return MysqlSelect.selectFrom(HISTORYDOCTABLE).where(
				and(
						HISTORYDOCTABLE.DOC_ID.eq(t.getDocId()),
					HISTORYDOCTABLE.REC_TIME.eq(t.getRecTime()),
					HISTORYDOCTABLE.REC_WHO.eq(t.getRecWho())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Historydoc> historydocs) {
		if (CollectionUtil.isEmpty(historydocs)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, historydocs, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(HISTORYDOCTABLE).values(
						HISTORYDOCTABLE.DOC_ID.value(new JdbcNamedParameter("docId")),
					HISTORYDOCTABLE.REC_TIME.value(new JdbcNamedParameter("recTime")),
					HISTORYDOCTABLE.REC_WHO.value(new JdbcNamedParameter("recWho")));
			}
		});
	}

	public int[] batchInsert(List<Historydoc> historydocs){
			return batchInsert(true ,historydocs);
	}

	public int[] batchUpdate(List<Historydoc> historydocs) {
		if (CollectionUtil.isEmpty(historydocs)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(historydocs, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(HISTORYDOCTABLE).set(
						HISTORYDOCTABLE.DOC_ID.value(new JdbcNamedParameter("docId")),
					HISTORYDOCTABLE.REC_TIME.value(new JdbcNamedParameter("recTime")),
					HISTORYDOCTABLE.REC_WHO.value(new JdbcNamedParameter("recWho"))).where(
				HISTORYDOCTABLE.RECORD_ID.eq(new JdbcNamedParameter("recordId")));
			}
		});
	}

	public int[] batchDelete(List<Historydoc> historydocs) {
		if (CollectionUtil.isEmpty(historydocs)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(historydocs, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(HISTORYDOCTABLE).where(and(
				HISTORYDOCTABLE.RECORD_ID.eq(new JdbcNamedParameter("recordId")),
				HISTORYDOCTABLE.DOC_ID.value(new JdbcNamedParameter("docId")),
				HISTORYDOCTABLE.REC_TIME.eq(new JdbcNamedParameter("recTime")),
				HISTORYDOCTABLE.REC_WHO.eq(new JdbcNamedParameter("recWho"))));
			}
		});
	}
/**
 * 也不知道靠不靠谱，通过相同的文档ID查出所以历史记录
 * select * form t_tablename where doc_id=docid;
 * 这个dsl不熟悉
 */
	public List<Historydoc> getWithSameDocId(final Integer docid) {
		//
		Historydoc historydoc = null;
		return getDslTemplate().query(historydoc, new SelectGenerateCallback<Historydoc>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Historydoc t) {
				return selectFrom(HISTORYDOCTABLE).where(
						HISTORYDOCTABLE.DOC_ID.eq(docid));
			}
		});
	}

}
