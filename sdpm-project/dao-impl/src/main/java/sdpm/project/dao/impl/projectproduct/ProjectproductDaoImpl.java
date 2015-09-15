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

package sdpm.project.dao.impl.projectproduct;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static sdpm.project.dao.inter.projectproduct.constant.ProjectproductTable.*;
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
import sdpm.project.dao.inter.projectproduct.pojo.Projectproduct;
import sdpm.project.dao.inter.projectproduct.ProjectproductDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class ProjectproductDaoImpl extends TinyDslDaoSupport implements ProjectproductDao {

	public Projectproduct add(Projectproduct projectproduct) {
		return getDslTemplate().insertAndReturnKey(projectproduct, new InsertGenerateCallback<Projectproduct>() {
			public Insert generate(Projectproduct t) {
				Insert insert = insertInto(PROJECTPRODUCTTABLE).values(
					PROJECTPRODUCTTABLE.PROJECT_ID.value(t.getProjectId()),
					PROJECTPRODUCTTABLE.PRODUCT_ID.value(t.getProductId()));
				return insert;
			}
		});
	}

	public int edit(Projectproduct projectproduct) {
		if(projectproduct == null || projectproduct.getProjectId() == null){
			return 0;
		}
		return getDslTemplate().update(projectproduct, new UpdateGenerateCallback<Projectproduct>() {
			public Update generate(Projectproduct t) {
				Update update = update(PROJECTPRODUCTTABLE).set(
					PROJECTPRODUCTTABLE.PROJECT_ID.value(t.getProjectId()),
					PROJECTPRODUCTTABLE.PRODUCT_ID.value(t.getProductId())).where(
					PROJECTPRODUCTTABLE.PROJECT_ID.eq(t.getProjectId()));
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
				return delete(PROJECTPRODUCTTABLE).where(PROJECTPRODUCTTABLE.PROJECT_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(PROJECTPRODUCTTABLE).where(PROJECTPRODUCTTABLE.PROJECT_ID.in(t));
		}
		},pks);
	}

	public Projectproduct getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Projectproduct.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(PROJECTPRODUCTTABLE).where(PROJECTPRODUCTTABLE.PROJECT_ID.eq(t));
			}
		});
	}

	public List<Projectproduct> query(Projectproduct projectproduct) {
		if(projectproduct==null){
			projectproduct=new Projectproduct();
		}
		return getDslTemplate().query(projectproduct, new SelectGenerateCallback<Projectproduct>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Projectproduct t) {
				return selectFrom(PROJECTPRODUCTTABLE).where(
				and(
					PROJECTPRODUCTTABLE.PROJECT_ID.eq(t.getProjectId()),
					PROJECTPRODUCTTABLE.PRODUCT_ID.eq(t.getProductId())));
			}
		});
	}

	public Pager<Projectproduct> queryPager(int start,int limit ,Projectproduct projectproduct) {
		if(projectproduct==null){
			projectproduct=new Projectproduct();
		}
		return getDslTemplate().queryPager(start, limit, projectproduct, false, new SelectGenerateCallback<Projectproduct>() {

			public Select generate(Projectproduct t) {
				return MysqlSelect.selectFrom(PROJECTPRODUCTTABLE).where(
				and(
					PROJECTPRODUCTTABLE.PROJECT_ID.eq(t.getProjectId()),
					PROJECTPRODUCTTABLE.PRODUCT_ID.eq(t.getProductId())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Projectproduct> projectproducts) {
		if (CollectionUtil.isEmpty(projectproducts)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, projectproducts, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(PROJECTPRODUCTTABLE).values(
					PROJECTPRODUCTTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
					PROJECTPRODUCTTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")));
			}
		});
	}

	public int[] batchInsert(List<Projectproduct> projectproducts){
			return batchInsert(true ,projectproducts);
	}

	public int[] batchUpdate(List<Projectproduct> projectproducts) {
		if (CollectionUtil.isEmpty(projectproducts)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(projectproducts, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(PROJECTPRODUCTTABLE).set(
					PROJECTPRODUCTTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
					PROJECTPRODUCTTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId"))).where(
				PROJECTPRODUCTTABLE.PROJECT_ID.eq(new JdbcNamedParameter("projectId")));
			}
		});
	}

	public int[] batchDelete(List<Projectproduct> projectproducts) {
		if (CollectionUtil.isEmpty(projectproducts)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(projectproducts, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(PROJECTPRODUCTTABLE).where(and(
				PROJECTPRODUCTTABLE.PROJECT_ID.eq(new JdbcNamedParameter("projectId")),
				PROJECTPRODUCTTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId"))));
			}
		});
	}

}
