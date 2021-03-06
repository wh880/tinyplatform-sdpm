/**
 * Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 * <p>
 * Licensed under the GPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/gpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tinygroup.sdpm.project.dao.impl;

import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.project.dao.ProjectProductDao;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.product.dao.constant.ProductTable.PRODUCTTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectProductTable.PROJECT_PRODUCTTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.tinysqldsl.formitem.SubSelect.subSelect;

@Repository
public class ProjectProductDaoImpl extends TinyDslDaoSupport implements ProjectProductDao {

    public int deleteByProjectId(Integer projectId) {
        Delete delete = delete(PROJECT_PRODUCTTABLE).where(PROJECT_PRODUCTTABLE.PROJECT_ID.eq(projectId));
        return getDslSession().execute(delete);
    }

    public List<Product> findLinkProductByProjectId(Integer projectId) {
        Select subSelect = Select.select(PROJECT_PRODUCTTABLE.PRODUCT_ID).from(PROJECT_PRODUCTTABLE)
                .where(PROJECT_PRODUCTTABLE.PROJECT_ID.eq(projectId));
        Select select = selectFrom(PRODUCTTABLE)
                .where(and(
                        PRODUCTTABLE.PRODUCT_ID.inExpression(subSelect(subSelect)),
                        PRODUCTTABLE.DELETED.eq(Project.DELETE_NO)
                ));
        return getDslSession().fetchList(select, Product.class);
    }


    public ProjectProduct add(ProjectProduct projectProduct) {
        return getDslTemplate().insertAndReturnKey(projectProduct, new InsertGenerateCallback<ProjectProduct>() {
            public Insert generate(ProjectProduct t) {
                Insert insert = insertInto(PROJECT_PRODUCTTABLE).values(
                        PROJECT_PRODUCTTABLE.ID.value(t.getId()),
                        PROJECT_PRODUCTTABLE.PROJECT_ID.value(t.getProjectId()),
                        PROJECT_PRODUCTTABLE.PRODUCT_ID.value(t.getProductId()));
                return insert;
            }
        });
    }

    public int edit(ProjectProduct projectProduct) {
        if (projectProduct == null || projectProduct.getId() == null) {
            return 0;
        }
        return getDslTemplate().update(projectProduct, new UpdateGenerateCallback<ProjectProduct>() {
            public Update generate(ProjectProduct t) {
                Update update = update(PROJECT_PRODUCTTABLE).set(
                        PROJECT_PRODUCTTABLE.PROJECT_ID.value(t.getProjectId()),
                        PROJECT_PRODUCTTABLE.PRODUCT_ID.value(t.getProductId())).where(
                        PROJECT_PRODUCTTABLE.ID.eq(t.getId()));
                return update;
            }
        });
    }

    public int deleteByKey(Integer pk) {
        if (pk == null) {
            return 0;
        }
        return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
            public Delete generate(Serializable pk) {
                return delete(PROJECT_PRODUCTTABLE).where(PROJECT_PRODUCTTABLE.ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(PROJECT_PRODUCTTABLE).where(PROJECT_PRODUCTTABLE.ID.in(t));
            }
        }, pks);
    }

    public ProjectProduct getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, ProjectProduct.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(PROJECT_PRODUCTTABLE).where(PROJECT_PRODUCTTABLE.ID.eq(t));
            }
        });
    }

    public List<ProjectProduct> query(ProjectProduct projectProduct, final OrderBy... orderBies) {
        if (projectProduct == null) {
            projectProduct = new ProjectProduct();
        }
        return getDslTemplate().query(projectProduct, new SelectGenerateCallback<ProjectProduct>() {

            @SuppressWarnings("rawtypes")
            public Select generate(ProjectProduct t) {
                Select select = selectFrom(PROJECT_PRODUCTTABLE).where(
                        and(
                                PROJECT_PRODUCTTABLE.PROJECT_ID.eq(t.getProjectId()),
                                PROJECT_PRODUCTTABLE.PRODUCT_ID.eq(t.getProductId())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public Pager<ProjectProduct> queryPager(int start, int limit, ProjectProduct projectProduct, final OrderBy... orderBies) {
        if (projectProduct == null) {
            projectProduct = new ProjectProduct();
        }
        return getDslTemplate().queryPager(start, limit, projectProduct, false, new SelectGenerateCallback<ProjectProduct>() {

            public Select generate(ProjectProduct t) {
                Select select = MysqlSelect.selectFrom(PROJECT_PRODUCTTABLE).where(
                        and(
                                PROJECT_PRODUCTTABLE.PROJECT_ID.eq(t.getProjectId()),
                                PROJECT_PRODUCTTABLE.PRODUCT_ID.eq(t.getProductId())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<ProjectProduct> projectProducts) {
        if (CollectionUtil.isEmpty(projectProducts)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, projectProducts, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(PROJECT_PRODUCTTABLE).values(
                        PROJECT_PRODUCTTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
                        PROJECT_PRODUCTTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")));
            }
        });
    }

    public int[] batchInsert(List<ProjectProduct> projectProducts) {
        return batchInsert(true, projectProducts);
    }

    public int[] batchUpdate(List<ProjectProduct> projectProducts) {
        if (CollectionUtil.isEmpty(projectProducts)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(projectProducts, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(PROJECT_PRODUCTTABLE).set(
                        PROJECT_PRODUCTTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
                        PROJECT_PRODUCTTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId"))).where(
                        PROJECT_PRODUCTTABLE.ID.eq(new JdbcNamedParameter("id")));
            }
        });
    }

    public int[] batchDelete(List<ProjectProduct> projectProducts) {
        if (CollectionUtil.isEmpty(projectProducts)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(projectProducts, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(PROJECT_PRODUCTTABLE).where(and(
                        PROJECT_PRODUCTTABLE.ID.eq(new JdbcNamedParameter("id")),
                        PROJECT_PRODUCTTABLE.PROJECT_ID.eq(new JdbcNamedParameter("projectId")),
                        PROJECT_PRODUCTTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId"))));
            }
        });
    }

    private Select addOrderByElements(Select select, OrderBy... orderByArgs) {
        List<OrderByElement> orderByElements = new ArrayList<OrderByElement>();
        for (int i = 0; orderByArgs != null && i < orderByArgs.length; i++) {
            OrderByElement tempElement = orderByArgs[i].getOrderByElement();
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
