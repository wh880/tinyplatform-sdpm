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
import org.tinygroup.sdpm.project.dao.ProjectBuildDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
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

import static org.tinygroup.sdpm.product.dao.constant.ProductStoryTable.PRODUCT_STORYTABLE;
import static org.tinygroup.sdpm.product.dao.constant.ProductTable.PRODUCTTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectBuildTable.PROJECT_BUILDTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectTable.PROJECTTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.select;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class ProjectBuildDaoImpl extends TinyDslDaoSupport implements ProjectBuildDao {

    public ProjectBuild add(ProjectBuild projectBuild) {
        return getDslTemplate().insertAndReturnKey(projectBuild, new InsertGenerateCallback<ProjectBuild>() {
            public Insert generate(ProjectBuild t) {
                Insert insert = insertInto(PROJECT_BUILDTABLE).values(
                        PROJECT_BUILDTABLE.BUILD_PRODUCT.value(t.getBuildProduct()),
                        PROJECT_BUILDTABLE.BUILD_PROJECT.value(t.getBuildProject()),
                        PROJECT_BUILDTABLE.BUILD_NAME.value(t.getBuildName()),
                        PROJECT_BUILDTABLE.BUILD_SCM_PATH.value(t.getBuildScmPath()),
                        PROJECT_BUILDTABLE.BUILD_FILE_PATH.value(t.getBuildFilePath()),
                        PROJECT_BUILDTABLE.BUILD_DATE.value(t.getBuildDate()),
                        PROJECT_BUILDTABLE.BUILD_STORIES.value(t.getBuildStories()),
                        PROJECT_BUILDTABLE.BUILD_BUGS.value(t.getBuildBugs()),
                        PROJECT_BUILDTABLE.BUILD_BUILDER.value(t.getBuildBuilder()),
                        PROJECT_BUILDTABLE.BUILD_DESC.value(t.getBuildDesc()),
                        PROJECT_BUILDTABLE.BUILD_DELETED.value(t.getBuildDeleted()));
                return insert;
            }
        });
    }

    public int edit(ProjectBuild projectBuild) {
        if (projectBuild == null || projectBuild.getBuildId() == null) {
            return 0;
        }
        Update update = update(PROJECT_BUILDTABLE).set(
                PROJECT_BUILDTABLE.BUILD_PRODUCT.value(projectBuild.getBuildProduct()),
                PROJECT_BUILDTABLE.BUILD_PROJECT.value(projectBuild.getBuildProject()),
                PROJECT_BUILDTABLE.BUILD_NAME.value(projectBuild.getBuildName()),
                PROJECT_BUILDTABLE.BUILD_SCM_PATH.value(projectBuild.getBuildScmPath()),
                PROJECT_BUILDTABLE.BUILD_FILE_PATH.value(projectBuild.getBuildFilePath()),
                PROJECT_BUILDTABLE.BUILD_DATE.value(projectBuild.getBuildDate()),
                PROJECT_BUILDTABLE.BUILD_STORIES.value(projectBuild.getBuildStories()),
                PROJECT_BUILDTABLE.BUILD_BUGS.value(projectBuild.getBuildBugs()),
                PROJECT_BUILDTABLE.BUILD_BUILDER.value(projectBuild.getBuildBuilder()),
                PROJECT_BUILDTABLE.BUILD_DESC.value(projectBuild.getBuildDesc()),
                PROJECT_BUILDTABLE.BUILD_DELETED.value(projectBuild.getBuildDeleted()))
                .where(PROJECT_BUILDTABLE.BUILD_ID.eq(projectBuild.getBuildId()));
        return getDslSession().execute(update, true);
    }

    public Integer deleteBuildByProductId(Integer productId) {
        if (productId == null) {
            return 0;
        }
        Update update = update(PROJECT_BUILDTABLE).set(PROJECT_BUILDTABLE.BUILD_DELETED.value(ProjectBuild.DELETE_YES))
                .where(PROJECT_BUILDTABLE.BUILD_PRODUCT.eq(productId));
        return getDslSession().execute(update);
    }

    public Integer softDelete(ProjectBuild build) {
        Update update = update(PROJECT_BUILDTABLE).set(PROJECT_BUILDTABLE.BUILD_DELETED.value(build.getBuildDeleted()))
                .where(PROJECT_BUILDTABLE.BUILD_ID.eq(build.getBuildId()));
        return getDslSession().execute(update);
    }

    public int deleteByKey(Integer pk) {
        if (pk == null) {
            return 0;
        }
        return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
            public Delete generate(Serializable pk) {
                return delete(PROJECT_BUILDTABLE).where(PROJECT_BUILDTABLE.BUILD_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(PROJECT_BUILDTABLE).where(PROJECT_BUILDTABLE.BUILD_ID.in(t));
            }
        }, pks);
    }

    public ProjectBuild getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, ProjectBuild.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(PROJECT_BUILDTABLE).where(PROJECT_BUILDTABLE.BUILD_ID.eq(t));
            }
        });
    }

    public List<ProjectBuild> query(ProjectBuild projectBuild, final OrderBy... orderBies) {
        if (projectBuild == null) {
            projectBuild = new ProjectBuild();
        }
        return getDslTemplate().query(projectBuild, new SelectGenerateCallback<ProjectBuild>() {

            @SuppressWarnings("rawtypes")
            public Select generate(ProjectBuild t) {
                Select select = selectFrom(PROJECT_BUILDTABLE).where(
                        and(
                                PROJECT_BUILDTABLE.BUILD_PRODUCT.eq(t.getBuildProduct()),
                                PROJECT_BUILDTABLE.BUILD_PROJECT.eq(t.getBuildProject()),
                                PROJECT_BUILDTABLE.BUILD_NAME.eq(t.getBuildName()),
                                PROJECT_BUILDTABLE.BUILD_SCM_PATH.eq(t.getBuildScmPath()),
                                PROJECT_BUILDTABLE.BUILD_FILE_PATH.eq(t.getBuildFilePath()),
                                PROJECT_BUILDTABLE.BUILD_DATE.eq(t.getBuildDate()),
                                PROJECT_BUILDTABLE.BUILD_STORIES.eq(t.getBuildStories()),
                                PROJECT_BUILDTABLE.BUILD_BUGS.eq(t.getBuildBugs()),
                                PROJECT_BUILDTABLE.BUILD_BUILDER.eq(t.getBuildBuilder()),
                                PROJECT_BUILDTABLE.BUILD_DESC.eq(t.getBuildDesc()),
                                PROJECT_BUILDTABLE.BUILD_DELETED.eq(t.getBuildDeleted())));
                return addOrderByElements(select, orderBies);
            }
        });
    }


    public Pager<ProjectBuild> queryPager(int start, int limit, ProjectBuild projectBuild, final OrderBy... orderBies) {
        if (projectBuild == null) {
            projectBuild = new ProjectBuild();
        }
        return getDslTemplate().queryPager(start, limit, projectBuild, false, new SelectGenerateCallback<ProjectBuild>() {

            public Select generate(ProjectBuild t) {
                Select select = MysqlSelect.selectFrom(PROJECT_BUILDTABLE).join(Join.leftJoin(PRODUCTTABLE,
                        PROJECT_BUILDTABLE.BUILD_PRODUCT.eq(PRODUCTTABLE.PRODUCT_ID))).where(
                        and(
                                PROJECT_BUILDTABLE.BUILD_PRODUCT.eq(t.getBuildProduct()),
                                PROJECT_BUILDTABLE.BUILD_PROJECT.eq(t.getBuildProject()),
                                PROJECT_BUILDTABLE.BUILD_NAME.eq(t.getBuildName()),
                                PROJECT_BUILDTABLE.BUILD_SCM_PATH.eq(t.getBuildScmPath()),
                                PROJECT_BUILDTABLE.BUILD_FILE_PATH.eq(t.getBuildFilePath()),
                                PROJECT_BUILDTABLE.BUILD_DATE.eq(t.getBuildDate()),
                                PROJECT_BUILDTABLE.BUILD_STORIES.eq(t.getBuildStories()),
                                PROJECT_BUILDTABLE.BUILD_BUGS.eq(t.getBuildBugs()),
                                PROJECT_BUILDTABLE.BUILD_BUILDER.eq(t.getBuildBuilder()),
                                PROJECT_BUILDTABLE.BUILD_DESC.eq(t.getBuildDesc()),
                                PROJECT_BUILDTABLE.BUILD_DELETED.eq(t.getBuildDeleted())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<ProjectBuild> projectBuilds) {
        if (CollectionUtil.isEmpty(projectBuilds)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, projectBuilds, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(PROJECT_BUILDTABLE).values(
                        PROJECT_BUILDTABLE.BUILD_PRODUCT.value(new JdbcNamedParameter("buildProduct")),
                        PROJECT_BUILDTABLE.BUILD_PROJECT.value(new JdbcNamedParameter("buildProject")),
                        PROJECT_BUILDTABLE.BUILD_NAME.value(new JdbcNamedParameter("buildName")),
                        PROJECT_BUILDTABLE.BUILD_SCM_PATH.value(new JdbcNamedParameter("buildScmPath")),
                        PROJECT_BUILDTABLE.BUILD_FILE_PATH.value(new JdbcNamedParameter("buildFilePath")),
                        PROJECT_BUILDTABLE.BUILD_DATE.value(new JdbcNamedParameter("buildDate")),
                        PROJECT_BUILDTABLE.BUILD_STORIES.value(new JdbcNamedParameter("buildStories")),
                        PROJECT_BUILDTABLE.BUILD_BUGS.value(new JdbcNamedParameter("buildBugs")),
                        PROJECT_BUILDTABLE.BUILD_BUILDER.value(new JdbcNamedParameter("buildBuilder")),
                        PROJECT_BUILDTABLE.BUILD_DESC.value(new JdbcNamedParameter("buildDesc")),
                        PROJECT_BUILDTABLE.BUILD_DELETED.value(new JdbcNamedParameter("buildDeleted")));
            }
        });
    }

    public int[] batchInsert(List<ProjectBuild> projectBuilds) {
        return batchInsert(true, projectBuilds);
    }

    public int[] batchUpdate(List<ProjectBuild> projectBuilds) {
        if (CollectionUtil.isEmpty(projectBuilds)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(projectBuilds, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(PROJECT_BUILDTABLE).set(
                        PROJECT_BUILDTABLE.BUILD_PRODUCT.value(new JdbcNamedParameter("buildProduct")),
                        PROJECT_BUILDTABLE.BUILD_PROJECT.value(new JdbcNamedParameter("buildProject")),
                        PROJECT_BUILDTABLE.BUILD_NAME.value(new JdbcNamedParameter("buildName")),
                        PROJECT_BUILDTABLE.BUILD_SCM_PATH.value(new JdbcNamedParameter("buildScmPath")),
                        PROJECT_BUILDTABLE.BUILD_FILE_PATH.value(new JdbcNamedParameter("buildFilePath")),
                        PROJECT_BUILDTABLE.BUILD_DATE.value(new JdbcNamedParameter("buildDate")),
                        PROJECT_BUILDTABLE.BUILD_STORIES.value(new JdbcNamedParameter("buildStories")),
                        PROJECT_BUILDTABLE.BUILD_BUGS.value(new JdbcNamedParameter("buildBugs")),
                        PROJECT_BUILDTABLE.BUILD_BUILDER.value(new JdbcNamedParameter("buildBuilder")),
                        PROJECT_BUILDTABLE.BUILD_DESC.value(new JdbcNamedParameter("buildDesc")),
                        PROJECT_BUILDTABLE.BUILD_DELETED.value(new JdbcNamedParameter("buildDeleted"))).where(
                        PROJECT_BUILDTABLE.BUILD_ID.eq(new JdbcNamedParameter("buildId")));
            }
        });
    }

    public int[] batchDelete(List<ProjectBuild> projectBuilds) {
        if (CollectionUtil.isEmpty(projectBuilds)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(projectBuilds, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(PROJECT_BUILDTABLE).where(and(
                        PROJECT_BUILDTABLE.BUILD_ID.eq(new JdbcNamedParameter("buildId")),
                        PROJECT_BUILDTABLE.BUILD_PRODUCT.eq(new JdbcNamedParameter("buildProduct")),
                        PROJECT_BUILDTABLE.BUILD_PROJECT.eq(new JdbcNamedParameter("buildProject")),
                        PROJECT_BUILDTABLE.BUILD_NAME.eq(new JdbcNamedParameter("buildName")),
                        PROJECT_BUILDTABLE.BUILD_SCM_PATH.eq(new JdbcNamedParameter("buildScmPath")),
                        PROJECT_BUILDTABLE.BUILD_FILE_PATH.eq(new JdbcNamedParameter("buildFilePath")),
                        PROJECT_BUILDTABLE.BUILD_DATE.eq(new JdbcNamedParameter("buildDate")),
                        PROJECT_BUILDTABLE.BUILD_STORIES.eq(new JdbcNamedParameter("buildStories")),
                        PROJECT_BUILDTABLE.BUILD_BUGS.eq(new JdbcNamedParameter("buildBugs")),
                        PROJECT_BUILDTABLE.BUILD_BUILDER.eq(new JdbcNamedParameter("buildBuilder")),
                        PROJECT_BUILDTABLE.BUILD_DESC.eq(new JdbcNamedParameter("buildDesc")),
                        PROJECT_BUILDTABLE.BUILD_DELETED.eq(new JdbcNamedParameter("buildDeleted"))));
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

    public int[] batchUpdateDel(List<ProjectBuild> builds) {
        if (CollectionUtil.isEmpty(builds)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(builds, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(PROJECT_BUILDTABLE).set(
                        PROJECT_BUILDTABLE.BUILD_DELETED.value(new JdbcNamedParameter("buildDeleted"))).where(
                        PROJECT_BUILDTABLE.BUILD_ID.eq(new JdbcNamedParameter("buildId")));
            }
        });

    }


    public Pager<ProductStory> findBuildStoryList(int start, int limit, Integer buildId, final OrderBy... orderBies) {
        Select select = select(PROJECT_BUILDTABLE.BUILD_STORIES).from(PROJECT_BUILDTABLE)
                .where(PROJECT_BUILDTABLE.BUILD_ID.eq(buildId));
        ProjectBuild test = getDslSession().fetchOneResult(select, ProjectBuild.class);
        final String[] storys = test.getBuildStories().split(",");
        ProductStory productStory = new ProductStory();
        return getDslTemplate().queryPager(start, limit, productStory, false, new SelectGenerateCallback<ProductStory>() {

            public Select generate(ProductStory t) {
                Select select = MysqlSelect.selectFrom(PRODUCT_STORYTABLE).where(
                        PRODUCT_STORYTABLE.STORY_ID.in(storys));
                return addOrderByElements(select, orderBies);
            }
        });

    }

    public List<ProjectBuild> getBuildByKeys(String... ids) {
        Select select = selectFrom(PROJECT_BUILDTABLE).where(and(PROJECT_BUILDTABLE.BUILD_DELETED.eq("0"), PROJECT_BUILDTABLE.BUILD_ID.in(ids)));
        return getDslSession().fetchList(select, ProjectBuild.class);
    }

    public List<ProjectBuild> getBuildByProducts(Integer... ids) {
        Select select = selectFrom(PROJECT_BUILDTABLE).where(and(PROJECT_BUILDTABLE.BUILD_DELETED.eq("0"), PROJECT_BUILDTABLE.BUILD_PRODUCT.in(ids)));
        return getDslSession().fetchList(select, ProjectBuild.class);
    }

    public List<ProjectBuild> buildInCondition(String condition, Integer limit, Integer productId, Integer projectId) {
        Condition con = null;
        if (productId != null) {
            con = PROJECT_BUILDTABLE.BUILD_PRODUCT.eq(productId);
        }
        if (projectId != null) {
            con = con == null ? PROJECT_BUILDTABLE.BUILD_PROJECT.eq(projectId) : and(con, PROJECT_BUILDTABLE.BUILD_PROJECT.eq(projectId));
        }
        Select select = MysqlSelect.select(FragmentSql.fragmentSelect("CONCAT (build_name,'-',project_name) as buildName"), PROJECT_BUILDTABLE.BUILD_ID).from(PROJECT_BUILDTABLE).where(
                and(PROJECT_BUILDTABLE.BUILD_NAME.like(condition), con, PROJECT_BUILDTABLE.BUILD_DELETED.eq(0))
        ).join(Join.leftJoin(PROJECTTABLE, PROJECT_BUILDTABLE.BUILD_PROJECT.eq(PROJECTTABLE.PROJECT_ID))).orderBy(OrderByElement.desc(PROJECT_BUILDTABLE.BUILD_DATE)).limit(0, limit);
        return getDslSession().fetchList(select, ProjectBuild.class);
    }


}
