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
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.project.dao.ProjectStoryDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectStory;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.expression.relational.ExistsExpression;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.formitem.SubSelect;
import org.tinygroup.tinysqldsl.select.Join;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.product.dao.constant.ProductPlanTable.PRODUCT_PLANTABLE;
import static org.tinygroup.sdpm.product.dao.constant.ProductStoryTable.PRODUCT_STORYTABLE;
import static org.tinygroup.sdpm.product.dao.constant.ProductTable.PRODUCTTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectProductTable.PROJECT_PRODUCTTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectStoryTable.PROJECT_STORYTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.FragmentSql.fragmentCondition;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class ProjectStoryDaoImpl extends TinyDslDaoSupport implements ProjectStoryDao {

    public Pager<ProductStory> findStory(Integer projectId, Integer start, Integer limit, boolean isNotInProjectStory, final OrderBy... orderByArgs) {
        SubSelect subSelect = SubSelect.subSelect(selectFrom(PROJECT_STORYTABLE).where(
                and(
                        PROJECT_STORYTABLE.STORY_ID.eq(PRODUCT_STORYTABLE.STORY_ID),
                        PROJECT_STORYTABLE.STORY_VERSION.eq(PRODUCT_STORYTABLE.STORY_VERSION),
                        PROJECT_STORYTABLE.PROJECT_ID.eq(projectId)
                )));
        Condition existsCondition = new Condition(new ExistsExpression(subSelect, isNotInProjectStory));
        Select select = Select.select(PRODUCT_STORYTABLE.ALL, PRODUCT_PLANTABLE.PLAN_NAME, PRODUCTTABLE.PRODUCT_NAME)
                .from(PROJECT_PRODUCTTABLE)
                .join(Join.newJoin(PRODUCT_STORYTABLE,
                        and(
                                PRODUCT_STORYTABLE.PRODUCT_ID.eq(PROJECT_PRODUCTTABLE.PRODUCT_ID),
                                PRODUCT_STORYTABLE.STORY_STATUS.in("1", "3")
                        )
                ))
                .join(Join.leftJoin(PRODUCTTABLE, PRODUCTTABLE.PRODUCT_ID.eq(PRODUCT_STORYTABLE.PRODUCT_ID)))
                .join(Join.leftJoin(PRODUCT_PLANTABLE, PRODUCT_PLANTABLE.PLAN_ID.eq(PRODUCT_STORYTABLE.PLAN_ID)))
                .where(
                        and(
                                PROJECT_PRODUCTTABLE.PROJECT_ID.eq(projectId),
                                existsCondition)
                );
        select = addOrderByElements(select, orderByArgs);
        return getDslSession().fetchPage(select, start, limit, false, ProductStory.class);
    }

    public Integer batchDel(Integer projectId, Integer[] storyIds) {
        Delete delete = delete(PROJECT_STORYTABLE).where(
                and(
                        PROJECT_STORYTABLE.PROJECT_ID.eq(projectId),
                        PROJECT_STORYTABLE.STORY_ID.in(storyIds)
                )
        );
        return getDslSession().execute(delete);
    }

    public List<ProjectStory> findByProjectID(Integer projectId) {
        Select select = selectFrom(PROJECT_STORYTABLE).where(PROJECT_STORYTABLE.PROJECT_ID.eq(projectId));
        return getDslSession().fetchList(select, ProjectStory.class);
    }

    public Integer deleteByProjectStory(Integer projectId, Integer storyId) {
        Delete delete = delete(PROJECT_STORYTABLE).where(and(
                PROJECT_STORYTABLE.PROJECT_ID.eq(projectId),
                PROJECT_STORYTABLE.STORY_ID.eq(storyId)
        ));
        return getDslSession().execute(delete);
    }

    public ProjectStory add(ProjectStory projectStory) {
        return getDslTemplate().insertAndReturnKey(projectStory, new InsertGenerateCallback<ProjectStory>() {
            public Insert generate(ProjectStory t) {
                Insert insert = insertInto(PROJECT_STORYTABLE).values(
                        PROJECT_STORYTABLE.ID.value(t.getId()),
                        PROJECT_STORYTABLE.PROJECT_ID.value(t.getProjectId()),
                        PROJECT_STORYTABLE.PRODUCT_ID.value(t.getProductId()),
                        PROJECT_STORYTABLE.STORY_ID.value(t.getStoryId()),
                        PROJECT_STORYTABLE.STORY_VERSION.value(t.getStoryVersion()));
                return insert;
            }
        });
    }

    public int edit(ProjectStory projectStory) {
        if (projectStory == null || projectStory.getId() == null) {
            return 0;
        }
        return getDslTemplate().update(projectStory, new UpdateGenerateCallback<ProjectStory>() {
            public Update generate(ProjectStory t) {
                Update update = update(PROJECT_STORYTABLE).set(
                        PROJECT_STORYTABLE.PROJECT_ID.value(t.getProjectId()),
                        PROJECT_STORYTABLE.PRODUCT_ID.value(t.getProductId()),
                        PROJECT_STORYTABLE.STORY_ID.value(t.getStoryId()),
                        PROJECT_STORYTABLE.STORY_VERSION.value(t.getStoryVersion())).where(
                        PROJECT_STORYTABLE.ID.eq(t.getId()));
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
                return delete(PROJECT_STORYTABLE).where(PROJECT_STORYTABLE.ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(PROJECT_STORYTABLE).where(PROJECT_STORYTABLE.ID.in(t));
            }
        }, pks);
    }

    public ProjectStory getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, ProjectStory.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(PROJECT_STORYTABLE).where(PROJECT_STORYTABLE.ID.eq(t));
            }
        });
    }

    public List<ProjectStory> query(ProjectStory projectStory, final OrderBy... orderBies) {
        if (projectStory == null) {
            projectStory = new ProjectStory();
        }
        return getDslTemplate().query(projectStory, new SelectGenerateCallback<ProjectStory>() {

            @SuppressWarnings("rawtypes")
            public Select generate(ProjectStory t) {
                Select select = selectFrom(PROJECT_STORYTABLE).where(
                        and(
                                PROJECT_STORYTABLE.PROJECT_ID.eq(t.getProjectId()),
                                PROJECT_STORYTABLE.PRODUCT_ID.eq(t.getProductId()),
                                PROJECT_STORYTABLE.STORY_ID.eq(t.getStoryId()),
                                PROJECT_STORYTABLE.STORY_VERSION.eq(t.getStoryVersion())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public Pager<ProjectStory> queryPager(int start, int limit, ProjectStory projectStory, final OrderBy... orderBies) {
        if (projectStory == null) {
            projectStory = new ProjectStory();
        }
        return getDslTemplate().queryPager(start, limit, projectStory, false, new SelectGenerateCallback<ProjectStory>() {

            public Select generate(ProjectStory t) {
                Select select = MysqlSelect.selectFrom(PROJECT_STORYTABLE).where(
                        and(
                                PROJECT_STORYTABLE.PROJECT_ID.eq(t.getProjectId()),
                                PROJECT_STORYTABLE.PRODUCT_ID.eq(t.getProductId()),
                                PROJECT_STORYTABLE.STORY_ID.eq(t.getStoryId()),
                                PROJECT_STORYTABLE.STORY_VERSION.eq(t.getStoryVersion())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<ProjectStory> projectStorys) {
        if (CollectionUtil.isEmpty(projectStorys)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, projectStorys, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(PROJECT_STORYTABLE).values(
                        PROJECT_STORYTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
                        PROJECT_STORYTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
                        PROJECT_STORYTABLE.STORY_ID.value(new JdbcNamedParameter("storyId")),
                        PROJECT_STORYTABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")));
            }
        });
    }

    public int[] batchInsert(List<ProjectStory> projectStorys) {
        return batchInsert(true, projectStorys);
    }

    public int[] batchUpdate(List<ProjectStory> projectStorys) {
        if (CollectionUtil.isEmpty(projectStorys)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(projectStorys, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(PROJECT_STORYTABLE).set(
                        PROJECT_STORYTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
                        PROJECT_STORYTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
                        PROJECT_STORYTABLE.STORY_ID.value(new JdbcNamedParameter("storyId")),
                        PROJECT_STORYTABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion"))).where(
                        PROJECT_STORYTABLE.ID.eq(new JdbcNamedParameter("id")));
            }
        });
    }

    public int[] batchDelete(List<ProjectStory> projectStorys) {
        if (CollectionUtil.isEmpty(projectStorys)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(projectStorys, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(PROJECT_STORYTABLE).where(and(
                        PROJECT_STORYTABLE.ID.eq(new JdbcNamedParameter("id")),
                        PROJECT_STORYTABLE.PROJECT_ID.eq(new JdbcNamedParameter("projectId")),
                        PROJECT_STORYTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
                        PROJECT_STORYTABLE.STORY_ID.eq(new JdbcNamedParameter("storyId")),
                        PROJECT_STORYTABLE.STORY_VERSION.eq(new JdbcNamedParameter("storyVersion"))));
            }
        });
    }

    private Select addOrderByElements(Select select, OrderBy... orderBies) {
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


    public Pager<ProjectStory> complexQuery(int start, int limit, ProjectStory projectStory, final String condition, final OrderBy... orderBys) {
        if (projectStory == null) {
            projectStory = new ProjectStory();
        }
        return getDslTemplate().queryPager(start, limit, projectStory, false, new SelectGenerateCallback<ProjectStory>() {

            public Select generate(ProjectStory t) {
                Select select = MysqlSelect.selectFrom(PROJECT_STORYTABLE).where(and(
                        fragmentCondition(condition),
                        PROJECT_STORYTABLE.PROJECT_ID.eq(t.getProjectId()),
                        PROJECT_STORYTABLE.PRODUCT_ID.eq(t.getProductId()),
                        PROJECT_STORYTABLE.STORY_ID.eq(t.getStoryId()),
                        PROJECT_STORYTABLE.STORY_VERSION.eq(t.getStoryVersion())));
                return addOrderByElements(select, orderBys);
            }
        });
    }
}
