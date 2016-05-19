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

package org.tinygroup.sdpm.product.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.product.dao.ProductStoryDao;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.pojo.StoryCount;
import org.tinygroup.sdpm.product.dao.utils.FieldUtil;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.base.FragmentSql;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.Join;
import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.tinysqldsl.selectitem.FragmentSelectItemSql;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.tinygroup.sdpm.org.dao.constant.OrgUserTable.ORG_USERTABLE;
import static org.tinygroup.sdpm.product.dao.constant.ProductPlanTable.PRODUCT_PLANTABLE;
import static org.tinygroup.sdpm.product.dao.constant.ProductStorySpecTable.PRODUCT_STORY_SPECTABLE;
import static org.tinygroup.sdpm.product.dao.constant.ProductStoryTable.PRODUCT_STORYTABLE;
import static org.tinygroup.sdpm.product.dao.constant.ProductTable.PRODUCTTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectStoryTable.PROJECT_STORYTABLE;
import static org.tinygroup.sdpm.system.dao.constant.SystemModuleTable.SYSTEM_MODULETABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.select;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.tinysqldsl.select.Join.leftJoin;
import static org.tinygroup.tinysqldsl.select.Join.newJoin;

@Repository
public class ProductStoryDaoImpl extends TinyDslDaoSupport implements ProductStoryDao {

    public static Condition[] storyPueryCondition(ProductStory t, Condition... condition) {

        Condition[] con = t == null ? new Condition[]{PRODUCT_STORYTABLE.STORY_ID.isNotNull()} : new Condition[]{
                PRODUCT_STORYTABLE.COMPANY_ID.eq(t.getCompanyId()),
                PRODUCT_STORYTABLE.PRODUCT_ID.eq(t.getProductId()),
                PRODUCT_STORYTABLE.STORY_PARENT_ID.eq(t.getStoryParentId()),
                PRODUCT_STORYTABLE.MODULE_ID.eq(t.getModuleId()),
                PRODUCT_STORYTABLE.PLAN_ID.eq(t.getPlanId()),
                PRODUCT_STORYTABLE.STORY_STATUS.eq(t.getStoryStatus()),
                PRODUCT_STORYTABLE.STORY_SOURCE.eq(t.getStorySource()),
                PRODUCT_STORYTABLE.STORY_FROM_BUG.eq(t.getStoryFromBug()),
                PRODUCT_STORYTABLE.STORY_TITLE.eq(t.getStoryTitle()),
                PRODUCT_STORYTABLE.STORY_KEYWORDS.eq(t.getStoryKeywords()),
                PRODUCT_STORYTABLE.STORY_TYPE.eq(t.getStoryType()),
                PRODUCT_STORYTABLE.STORY_PRI.eq(t.getStoryPri()),
                PRODUCT_STORYTABLE.STORY_ESTIMATE.eq(t.getStoryEstimate()),
                PRODUCT_STORYTABLE.STORY_STAGE.eq(t.getStoryStage()),
                PRODUCT_STORYTABLE.STORY_MAILTO.eq(t.getStoryMailto()),
                PRODUCT_STORYTABLE.STORY_OPENED_BY.eq(t.getStoryOpenedBy()),
                PRODUCT_STORYTABLE.STORY_OPENED_DATE.eq(t.getStoryOpenedDate()),
                PRODUCT_STORYTABLE.STORY_ASSIGNED_TO.eq(t.getStoryAssignedTo()),
                PRODUCT_STORYTABLE.STORY_ASSIGNED_DATE.eq(t.getStoryAssignedDate()),
                PRODUCT_STORYTABLE.STORY_LAST_EDITED_BY.eq(t.getStoryLastEditedBy()),
                PRODUCT_STORYTABLE.STORY_LAST_EDITED_DATE.eq(t.getStoryLastEditedDate()),
                PRODUCT_STORYTABLE.STORY_REVIEWED_BY.eq(t.getStoryReviewedBy()),
                PRODUCT_STORYTABLE.STORY_REVIEWED_DATE.eq(t.getStoryReviewedDate()),
                PRODUCT_STORYTABLE.STORY_CLOSED_BY.eq(t.getStoryClosedBy()),
                PRODUCT_STORYTABLE.STORY_CLOSED_DATE.eq(t.getStoryClosedDate()),
                PRODUCT_STORYTABLE.STORY_CLOSED_REASON.eq(t.getStoryClosedReason()),
                PRODUCT_STORYTABLE.TO_BUG.eq(t.getToBug()),
                PRODUCT_STORYTABLE.STORY_LINK_STORIES.eq(t.getStoryLinkStories()),
                PRODUCT_STORYTABLE.STORY_CHILD_STORIES.eq(t.getStoryChildStories()),
                PRODUCT_STORYTABLE.STORY_DUPLICATE_STORY.eq(t.getStoryDuplicateStory()),
                PRODUCT_STORYTABLE.STORY_VERSION.eq(t.getStoryVersion()),
                PRODUCT_STORYTABLE.BUILD_ID.eq(t.getBuildId()),
                PRODUCT_STORYTABLE.CLIENT_REQUEST_ID.eq(t.getClientRequestId()),
                PRODUCT_STORYTABLE.DELETED.eq(t.getDeleted())};

        if (condition != null && condition.length > 0) {
            int conLen1 = con.length;//保存第一个数组长度
            int conLen2 = condition.length;//保存第二个数组长度
            con = Arrays.copyOf(con, conLen1 + conLen2);//扩容
            System.arraycopy(condition, 0, con, conLen1, conLen2);//将第二个数组与第一个数组合并
        }

        return con;
    }

    public ProductStory add(final ProductStory productStory) {
        return getDslTemplate().insertAndReturnKey(productStory, new InsertGenerateCallback<ProductStory>() {
            public Insert generate(ProductStory t) {
                Insert insert = /*InsertUtil.getInsert(PRODUCT_STORYTABLE, productStory);*/

                        insertInto(PRODUCT_STORYTABLE).values(
                                PRODUCT_STORYTABLE.STORY_ID.value(t.getStoryId()),
                                PRODUCT_STORYTABLE.COMPANY_ID.value(t.getCompanyId()),
                                PRODUCT_STORYTABLE.PRODUCT_ID.value(t.getProductId()),
                                PRODUCT_STORYTABLE.STORY_PARENT_ID.value(t.getStoryParentId()),
                                PRODUCT_STORYTABLE.MODULE_ID.value(t.getModuleId()),
                                PRODUCT_STORYTABLE.PLAN_ID.value(t.getPlanId()),
                                PRODUCT_STORYTABLE.STORY_STATUS.value(t.getStoryStatus()),
                                PRODUCT_STORYTABLE.STORY_SOURCE.value(t.getStorySource()),
                                PRODUCT_STORYTABLE.STORY_FROM_BUG.value(t.getStoryFromBug()),
                                PRODUCT_STORYTABLE.STORY_TITLE.value(t.getStoryTitle()),
                                PRODUCT_STORYTABLE.STORY_KEYWORDS.value(t.getStoryKeywords()),
                                PRODUCT_STORYTABLE.STORY_TYPE.value(t.getStoryType()),
                                PRODUCT_STORYTABLE.STORY_PRI.value(t.getStoryPri()),
                                PRODUCT_STORYTABLE.STORY_ESTIMATE.value(t.getStoryEstimate()),
                                PRODUCT_STORYTABLE.STORY_STAGE.value(t.getStoryStage()),
                                PRODUCT_STORYTABLE.STORY_MAILTO.value(t.getStoryMailto()),
                                PRODUCT_STORYTABLE.STORY_OPENED_BY.value(t.getStoryOpenedBy()),
                                PRODUCT_STORYTABLE.STORY_OPENED_DATE.value(t.getStoryOpenedDate()),
                                PRODUCT_STORYTABLE.STORY_ASSIGNED_TO.value(t.getStoryAssignedTo()),
                                PRODUCT_STORYTABLE.STORY_ASSIGNED_DATE.value(t.getStoryAssignedDate()),
                                PRODUCT_STORYTABLE.STORY_LAST_EDITED_BY.value(t.getStoryLastEditedBy()),
                                PRODUCT_STORYTABLE.STORY_LAST_EDITED_DATE.value(t.getStoryLastEditedDate()),
                                PRODUCT_STORYTABLE.STORY_REVIEWED_BY.value(t.getStoryReviewedBy()),
                                PRODUCT_STORYTABLE.STORY_REVIEWED_DATE.value(t.getStoryReviewedDate()),
                                PRODUCT_STORYTABLE.STORY_CLOSED_BY.value(t.getStoryClosedBy()),
                                PRODUCT_STORYTABLE.STORY_CLOSED_DATE.value(t.getStoryClosedDate()),
                                PRODUCT_STORYTABLE.STORY_CLOSED_REASON.value(t.getStoryClosedReason()),
                                PRODUCT_STORYTABLE.TO_BUG.value(t.getToBug()),
                                PRODUCT_STORYTABLE.STORY_LINK_STORIES.value(t.getStoryLinkStories()),
                                PRODUCT_STORYTABLE.STORY_CHILD_STORIES.value(t.getStoryChildStories()),
                                PRODUCT_STORYTABLE.STORY_DUPLICATE_STORY.value(t.getStoryDuplicateStory()),
                                PRODUCT_STORYTABLE.STORY_VERSION.value(t.getStoryVersion()),
                                PRODUCT_STORYTABLE.BUILD_ID.value(t.getBuildId()),
                                PRODUCT_STORYTABLE.CLIENT_REQUEST_ID.value(t.getClientRequestId()),
                                PRODUCT_STORYTABLE.DELETED.value(t.getDeleted()),
                                PRODUCT_STORYTABLE.NO.value(t.getNo()));
                return insert;
            }
        });
    }

    public int edit(ProductStory productStory) {
        if (productStory == null || productStory.getStoryId() == null) {
            return 0;
        }
        return getDslTemplate().update(productStory, new UpdateGenerateCallback<ProductStory>() {
            public Update generate(ProductStory t) {
                Update update = /*UpdateUtil.getUpdate(PRODUCT_STORYTABLE, t);*/
                        update(PRODUCT_STORYTABLE).set(
                                PRODUCT_STORYTABLE.COMPANY_ID.value(t.getCompanyId()),
                                PRODUCT_STORYTABLE.PRODUCT_ID.value(t.getProductId()),
                                PRODUCT_STORYTABLE.STORY_PARENT_ID.value(t.getStoryParentId()),
                                PRODUCT_STORYTABLE.MODULE_ID.value(t.getModuleId()),
                                PRODUCT_STORYTABLE.PLAN_ID.value(t.getPlanId()),
                                PRODUCT_STORYTABLE.STORY_STATUS.value(t.getStoryStatus()),
                                PRODUCT_STORYTABLE.STORY_SOURCE.value(t.getStorySource()),
                                PRODUCT_STORYTABLE.STORY_FROM_BUG.value(t.getStoryFromBug()),
                                PRODUCT_STORYTABLE.STORY_TITLE.value(t.getStoryTitle()),
                                PRODUCT_STORYTABLE.STORY_KEYWORDS.value(t.getStoryKeywords()),
                                PRODUCT_STORYTABLE.STORY_TYPE.value(t.getStoryType()),
                                PRODUCT_STORYTABLE.STORY_PRI.value(t.getStoryPri()),
                                PRODUCT_STORYTABLE.STORY_ESTIMATE.value(t.getStoryEstimate()),
                                PRODUCT_STORYTABLE.STORY_STAGE.value(t.getStoryStage()),
                                PRODUCT_STORYTABLE.STORY_MAILTO.value(t.getStoryMailto()),
                                PRODUCT_STORYTABLE.STORY_OPENED_BY.value(t.getStoryOpenedBy()),
                                PRODUCT_STORYTABLE.STORY_OPENED_DATE.value(t.getStoryOpenedDate()),
                                PRODUCT_STORYTABLE.STORY_ASSIGNED_TO.value(t.getStoryAssignedTo()),
                                PRODUCT_STORYTABLE.STORY_ASSIGNED_DATE.value(t.getStoryAssignedDate()),
                                PRODUCT_STORYTABLE.STORY_LAST_EDITED_BY.value(t.getStoryLastEditedBy()),
                                PRODUCT_STORYTABLE.STORY_LAST_EDITED_DATE.value(t.getStoryLastEditedDate()),
                                PRODUCT_STORYTABLE.STORY_REVIEWED_BY.value(t.getStoryReviewedBy()),
                                PRODUCT_STORYTABLE.STORY_REVIEWED_DATE.value(t.getStoryReviewedDate()),
                                PRODUCT_STORYTABLE.STORY_CLOSED_BY.value(t.getStoryClosedBy()),
                                PRODUCT_STORYTABLE.STORY_CLOSED_DATE.value(t.getStoryClosedDate()),
                                PRODUCT_STORYTABLE.STORY_CLOSED_REASON.value(t.getStoryClosedReason()),
                                PRODUCT_STORYTABLE.TO_BUG.value(t.getToBug()),
                                PRODUCT_STORYTABLE.STORY_LINK_STORIES.value(t.getStoryLinkStories()),
                                PRODUCT_STORYTABLE.STORY_CHILD_STORIES.value(t.getStoryChildStories()),
                                PRODUCT_STORYTABLE.STORY_DUPLICATE_STORY.value(t.getStoryDuplicateStory()),
                                PRODUCT_STORYTABLE.STORY_VERSION.value(t.getStoryVersion()),
                                PRODUCT_STORYTABLE.BUILD_ID.value(t.getBuildId()),
                                PRODUCT_STORYTABLE.CLIENT_REQUEST_ID.value(t.getClientRequestId()),
                                PRODUCT_STORYTABLE.DELETED.value(t.getDeleted()),
                                PRODUCT_STORYTABLE.NO.value(t.getNo())).where(
                                PRODUCT_STORYTABLE.STORY_ID.eq(t.getStoryId()));
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
                return delete(PRODUCT_STORYTABLE).where(PRODUCT_STORYTABLE.STORY_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(PRODUCT_STORYTABLE).where(PRODUCT_STORYTABLE.STORY_ID.in(t));
            }
        }, pks);
    }


    public List<ProductStory> getByKeys(final boolean withSpec, ProductStory t, Integer... pk) {
        if (t == null) {
            t = new ProductStory();
        }
        Select select;
        if (withSpec) {
            select = select(PRODUCT_STORYTABLE.ALL,
                    PRODUCT_STORY_SPECTABLE.STORY_SPEC.as("storySpec"),
                    PRODUCTTABLE.PRODUCT_NAME.as("productName")).from(PRODUCT_STORYTABLE).join(
                    leftJoin(PRODUCT_STORY_SPECTABLE, PRODUCT_STORY_SPECTABLE.STORY_ID.eq(PRODUCT_STORYTABLE.STORY_ID)),
                    leftJoin(PRODUCTTABLE, PRODUCTTABLE.PRODUCT_ID.eq(PRODUCT_STORYTABLE.PRODUCT_ID))
            ).where(and(PRODUCT_STORYTABLE.STORY_VERSION.eq(PRODUCT_STORY_SPECTABLE.STORY_VERSION),
                    PRODUCT_STORYTABLE.STORY_ID.in(pk),
                    PRODUCT_STORYTABLE.PRODUCT_ID.eq(t.getProductId()),
                    PRODUCT_STORYTABLE.MODULE_ID.eq(t.getModuleId())));
        } else {
            select = selectFrom(PRODUCT_STORYTABLE).where(and(PRODUCT_STORYTABLE.STORY_ID.in(pk),
                    PRODUCT_STORYTABLE.PRODUCT_ID.eq(t.getProductId()),
                    PRODUCT_STORYTABLE.MODULE_ID.eq(t.getModuleId())));
        }

        return getDslSession().fetchList(select, ProductStory.class);
    }

    public ProductStory getByKey(Integer pk) {
        try {
            return getDslTemplate().getByKey(pk, ProductStory.class, new SelectGenerateCallback<Serializable>() {
                @SuppressWarnings("rawtypes")
                public Select generate(Serializable t) {
                    return selectFrom(PRODUCT_STORYTABLE).where(PRODUCT_STORYTABLE.STORY_ID.eq(t));
                }
            });
        } catch (EmptyResultDataAccessException e) {

            return null;
        }

    }

    public ProductStory getReleteStoryByKey(Integer pk) {
        try {
            return getDslTemplate().getByKey(pk, ProductStory.class, new SelectGenerateCallback<Serializable>() {
                @SuppressWarnings("rawtypes")
                public Select generate(Serializable t) {

                    return select(FragmentSql.fragmentSelect("product_story.*,p.product_name as productName,pl.plan_name as planName,m.module_name as moduleTitle"))
                            .from(FragmentSql.fragmentFrom("product_story left join product p on p.product_id = product_story.product_id left join product_plan pl on pl.plan_id = product_story.plan_id left join system_module m on m.module_id = product_story.module_id"))
                            .where(PRODUCT_STORYTABLE.STORY_ID.eq(t));
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public List<ProductStory> query(ProductStory productStory, final OrderBy... orderArgs) {
        if (productStory == null) {
            productStory = new ProductStory();
        }
        return getDslTemplate().query(productStory, new SelectGenerateCallback<ProductStory>() {

            @SuppressWarnings("rawtypes")
            public Select generate(ProductStory t) {
                Select select = selectFrom(PRODUCT_STORYTABLE).where(
                        and(
                                PRODUCT_STORYTABLE.COMPANY_ID.eq(t.getCompanyId()),
                                PRODUCT_STORYTABLE.PRODUCT_ID.eq(t.getProductId()),
                                PRODUCT_STORYTABLE.STORY_PARENT_ID.eq(t.getStoryParentId()),
                                PRODUCT_STORYTABLE.MODULE_ID.eq(t.getModuleId()),
                                PRODUCT_STORYTABLE.PLAN_ID.eq(t.getPlanId()),
                                PRODUCT_STORYTABLE.STORY_STATUS.eq(t.getStoryStatus()),
                                PRODUCT_STORYTABLE.STORY_SOURCE.eq(t.getStorySource()),
                                PRODUCT_STORYTABLE.STORY_FROM_BUG.eq(t.getStoryFromBug()),
                                PRODUCT_STORYTABLE.STORY_TITLE.eq(t.getStoryTitle()),
                                PRODUCT_STORYTABLE.STORY_KEYWORDS.eq(t.getStoryKeywords()),
                                PRODUCT_STORYTABLE.STORY_TYPE.eq(t.getStoryType()),
                                PRODUCT_STORYTABLE.STORY_PRI.eq(t.getStoryPri()),
                                PRODUCT_STORYTABLE.STORY_ESTIMATE.eq(t.getStoryEstimate()),
                                PRODUCT_STORYTABLE.STORY_STAGE.eq(t.getStoryStage()),
                                PRODUCT_STORYTABLE.STORY_MAILTO.eq(t.getStoryMailto()),
                                PRODUCT_STORYTABLE.STORY_OPENED_BY.eq(t.getStoryOpenedBy()),
                                PRODUCT_STORYTABLE.STORY_OPENED_DATE.eq(t.getStoryOpenedDate()),
                                PRODUCT_STORYTABLE.STORY_ASSIGNED_TO.eq(t.getStoryAssignedTo()),
                                PRODUCT_STORYTABLE.STORY_ASSIGNED_DATE.eq(t.getStoryAssignedDate()),
                                PRODUCT_STORYTABLE.STORY_LAST_EDITED_BY.eq(t.getStoryLastEditedBy()),
                                PRODUCT_STORYTABLE.STORY_LAST_EDITED_DATE.eq(t.getStoryLastEditedDate()),
                                PRODUCT_STORYTABLE.STORY_REVIEWED_BY.eq(t.getStoryReviewedBy()),
                                PRODUCT_STORYTABLE.STORY_REVIEWED_DATE.eq(t.getStoryReviewedDate()),
                                PRODUCT_STORYTABLE.STORY_CLOSED_BY.eq(t.getStoryClosedBy()),
                                PRODUCT_STORYTABLE.STORY_CLOSED_DATE.eq(t.getStoryClosedDate()),
                                PRODUCT_STORYTABLE.STORY_CLOSED_REASON.eq(t.getStoryClosedReason()),
                                PRODUCT_STORYTABLE.TO_BUG.eq(t.getToBug()),
                                PRODUCT_STORYTABLE.STORY_LINK_STORIES.eq(t.getStoryLinkStories()),
                                PRODUCT_STORYTABLE.STORY_CHILD_STORIES.eq(t.getStoryChildStories()),
                                PRODUCT_STORYTABLE.STORY_DUPLICATE_STORY.eq(t.getStoryDuplicateStory()),
                                PRODUCT_STORYTABLE.STORY_VERSION.eq(t.getStoryVersion()),
                                PRODUCT_STORYTABLE.BUILD_ID.eq(t.getBuildId()),
                                PRODUCT_STORYTABLE.CLIENT_REQUEST_ID.eq(t.getClientRequestId()),
                                PRODUCT_STORYTABLE.DELETED.eq(t.getDeleted()),
                                PRODUCT_STORYTABLE.NO.eq(t.getNo())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public Pager<ProductStory> queryPager(int start, int limit, ProductStory productStory, final OrderBy... orderArgs) {
        if (productStory == null) {
            productStory = new ProductStory();
        }
        return getDslTemplate().queryPager(start > 0 ? start : 0, limit, productStory, false, new SelectGenerateCallback<ProductStory>() {
            public Select generate(ProductStory t) {
                Select select = MysqlSelect.select(PRODUCT_STORYTABLE.ALL, PRODUCT_PLANTABLE.PLAN_NAME.as("planName"), PRODUCTTABLE.PRODUCT_NAME.as("productName")).
                        from(PRODUCT_STORYTABLE).join(leftJoin(PRODUCTTABLE, PRODUCT_STORYTABLE.PRODUCT_ID.eq(PRODUCTTABLE.PRODUCT_ID)), leftJoin(PRODUCT_PLANTABLE, PRODUCT_STORYTABLE.PLAN_ID.eq(PRODUCT_PLANTABLE.PLAN_ID)))
                        .where(and(
                                PRODUCT_STORYTABLE.COMPANY_ID.eq(t.getCompanyId()),
                                PRODUCT_STORYTABLE.PRODUCT_ID.eq(t.getProductId()),
                                PRODUCT_STORYTABLE.STORY_PARENT_ID.eq(t.getStoryParentId()),
                                PRODUCT_STORYTABLE.MODULE_ID.eq(t.getModuleId()),
                                PRODUCT_STORYTABLE.PLAN_ID.eq(t.getPlanId()),
                                PRODUCT_STORYTABLE.STORY_STATUS.eq(t.getStoryStatus()),
                                PRODUCT_STORYTABLE.STORY_SOURCE.eq(t.getStorySource()),
                                PRODUCT_STORYTABLE.STORY_FROM_BUG.eq(t.getStoryFromBug()),
                                PRODUCT_STORYTABLE.STORY_TITLE.eq(t.getStoryTitle()),
                                PRODUCT_STORYTABLE.STORY_KEYWORDS.eq(t.getStoryKeywords()),
                                PRODUCT_STORYTABLE.STORY_TYPE.eq(t.getStoryType()),
                                PRODUCT_STORYTABLE.STORY_PRI.eq(t.getStoryPri()),
                                PRODUCT_STORYTABLE.STORY_ESTIMATE.eq(t.getStoryEstimate()),
                                PRODUCT_STORYTABLE.STORY_STAGE.eq(t.getStoryStage()),
                                PRODUCT_STORYTABLE.STORY_MAILTO.eq(t.getStoryMailto()),
                                PRODUCT_STORYTABLE.STORY_OPENED_BY.eq(t.getStoryOpenedBy()),
                                PRODUCT_STORYTABLE.STORY_OPENED_DATE.eq(t.getStoryOpenedDate()),
                                PRODUCT_STORYTABLE.STORY_ASSIGNED_TO.eq(t.getStoryAssignedTo()),
                                PRODUCT_STORYTABLE.STORY_ASSIGNED_DATE.eq(t.getStoryAssignedDate()),
                                PRODUCT_STORYTABLE.STORY_LAST_EDITED_BY.eq(t.getStoryLastEditedBy()),
                                PRODUCT_STORYTABLE.STORY_LAST_EDITED_DATE.eq(t.getStoryLastEditedDate()),
                                PRODUCT_STORYTABLE.STORY_REVIEWED_BY.eq(t.getStoryReviewedBy()),
                                PRODUCT_STORYTABLE.STORY_REVIEWED_DATE.eq(t.getStoryReviewedDate()),
                                PRODUCT_STORYTABLE.STORY_CLOSED_BY.eq(t.getStoryClosedBy()),
                                PRODUCT_STORYTABLE.STORY_CLOSED_DATE.eq(t.getStoryClosedDate()),
                                PRODUCT_STORYTABLE.STORY_CLOSED_REASON.eq(t.getStoryClosedReason()),
                                PRODUCT_STORYTABLE.TO_BUG.eq(t.getToBug()),
                                PRODUCT_STORYTABLE.STORY_LINK_STORIES.eq(t.getStoryLinkStories()),
                                PRODUCT_STORYTABLE.STORY_CHILD_STORIES.eq(t.getStoryChildStories()),
                                PRODUCT_STORYTABLE.STORY_DUPLICATE_STORY.eq(t.getStoryDuplicateStory()),
                                PRODUCT_STORYTABLE.STORY_VERSION.eq(t.getStoryVersion()),
                                PRODUCT_STORYTABLE.BUILD_ID.eq(t.getBuildId()),
                                PRODUCT_STORYTABLE.CLIENT_REQUEST_ID.eq(t.getClientRequestId()),
                                PRODUCT_STORYTABLE.DELETED.eq(t.getDeleted())
                        ));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<ProductStory> productStorys) {
        if (CollectionUtil.isEmpty(productStorys)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, productStorys, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(PRODUCT_STORYTABLE).values(
                        PRODUCT_STORYTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
                        PRODUCT_STORYTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
                        PRODUCT_STORYTABLE.STORY_PARENT_ID.value(new JdbcNamedParameter("storyParentId")),
                        PRODUCT_STORYTABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")),
                        PRODUCT_STORYTABLE.PLAN_ID.value(new JdbcNamedParameter("planId")),
                        PRODUCT_STORYTABLE.STORY_STATUS.value(new JdbcNamedParameter("storyStatus")),
                        PRODUCT_STORYTABLE.STORY_SOURCE.value(new JdbcNamedParameter("storySource")),
                        PRODUCT_STORYTABLE.STORY_FROM_BUG.value(new JdbcNamedParameter("storyFromBug")),
                        PRODUCT_STORYTABLE.STORY_TITLE.value(new JdbcNamedParameter("storyTitle")),
                        PRODUCT_STORYTABLE.STORY_KEYWORDS.value(new JdbcNamedParameter("storyKeywords")),
                        PRODUCT_STORYTABLE.STORY_TYPE.value(new JdbcNamedParameter("storyType")),
                        PRODUCT_STORYTABLE.STORY_PRI.value(new JdbcNamedParameter("storyPri")),
                        PRODUCT_STORYTABLE.STORY_ESTIMATE.value(new JdbcNamedParameter("storyEstimate")),
                        PRODUCT_STORYTABLE.STORY_STAGE.value(new JdbcNamedParameter("storyStage")),
                        PRODUCT_STORYTABLE.STORY_MAILTO.value(new JdbcNamedParameter("storyMailto")),
                        PRODUCT_STORYTABLE.STORY_OPENED_BY.value(new JdbcNamedParameter("storyOpenedBy")),
                        PRODUCT_STORYTABLE.STORY_OPENED_DATE.value(new JdbcNamedParameter("storyOpenedDate")),
                        PRODUCT_STORYTABLE.STORY_ASSIGNED_TO.value(new JdbcNamedParameter("storyAssignedTo")),
                        PRODUCT_STORYTABLE.STORY_ASSIGNED_DATE.value(new JdbcNamedParameter("storyAssignedDate")),
                        PRODUCT_STORYTABLE.STORY_LAST_EDITED_BY.value(new JdbcNamedParameter("storyLastEditedBy")),
                        PRODUCT_STORYTABLE.STORY_LAST_EDITED_DATE.value(new JdbcNamedParameter("storyLastEditedDate")),
                        PRODUCT_STORYTABLE.STORY_REVIEWED_BY.value(new JdbcNamedParameter("storyReviewedBy")),
                        PRODUCT_STORYTABLE.STORY_REVIEWED_DATE.value(new JdbcNamedParameter("storyReviewedDate")),
                        PRODUCT_STORYTABLE.STORY_CLOSED_BY.value(new JdbcNamedParameter("storyClosedBy")),
                        PRODUCT_STORYTABLE.STORY_CLOSED_DATE.value(new JdbcNamedParameter("storyClosedDate")),
                        PRODUCT_STORYTABLE.STORY_CLOSED_REASON.value(new JdbcNamedParameter("storyClosedReason")),
                        PRODUCT_STORYTABLE.TO_BUG.value(new JdbcNamedParameter("toBug")),
                        PRODUCT_STORYTABLE.STORY_LINK_STORIES.value(new JdbcNamedParameter("storyLinkStories")),
                        PRODUCT_STORYTABLE.STORY_CHILD_STORIES.value(new JdbcNamedParameter("storyChildStories")),
                        PRODUCT_STORYTABLE.STORY_DUPLICATE_STORY.value(new JdbcNamedParameter("storyDuplicateStory")),
                        PRODUCT_STORYTABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
                        PRODUCT_STORYTABLE.BUILD_ID.value(new JdbcNamedParameter("buildId")),
                        PRODUCT_STORYTABLE.CLIENT_REQUEST_ID.value(new JdbcNamedParameter("clientRequestId")),
                        PRODUCT_STORYTABLE.DELETED.value(new JdbcNamedParameter("deleted")));
            }
        });
    }

    public int[] batchInsert(List<ProductStory> productStorys) {
        return batchInsert(true, productStorys);
    }

    public int[] batchUpdate(List<ProductStory> productStorys) {
        if (CollectionUtil.isEmpty(productStorys)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(productStorys, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(PRODUCT_STORYTABLE).set(
                        PRODUCT_STORYTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
                        PRODUCT_STORYTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
                        PRODUCT_STORYTABLE.STORY_PARENT_ID.value(new JdbcNamedParameter("storyParentId")),
                        PRODUCT_STORYTABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")),
                        PRODUCT_STORYTABLE.PLAN_ID.value(new JdbcNamedParameter("planId")),
                        PRODUCT_STORYTABLE.STORY_STATUS.value(new JdbcNamedParameter("storyStatus")),
                        PRODUCT_STORYTABLE.STORY_SOURCE.value(new JdbcNamedParameter("storySource")),
                        PRODUCT_STORYTABLE.STORY_FROM_BUG.value(new JdbcNamedParameter("storyFromBug")),
                        PRODUCT_STORYTABLE.STORY_TITLE.value(new JdbcNamedParameter("storyTitle")),
                        PRODUCT_STORYTABLE.STORY_KEYWORDS.value(new JdbcNamedParameter("storyKeywords")),
                        PRODUCT_STORYTABLE.STORY_TYPE.value(new JdbcNamedParameter("storyType")),
                        PRODUCT_STORYTABLE.STORY_PRI.value(new JdbcNamedParameter("storyPri")),
                        PRODUCT_STORYTABLE.STORY_ESTIMATE.value(new JdbcNamedParameter("storyEstimate")),
                        PRODUCT_STORYTABLE.STORY_STAGE.value(new JdbcNamedParameter("storyStage")),
                        PRODUCT_STORYTABLE.STORY_MAILTO.value(new JdbcNamedParameter("storyMailto")),
                        PRODUCT_STORYTABLE.STORY_OPENED_BY.value(new JdbcNamedParameter("storyOpenedBy")),
                        PRODUCT_STORYTABLE.STORY_OPENED_DATE.value(new JdbcNamedParameter("storyOpenedDate")),
                        PRODUCT_STORYTABLE.STORY_ASSIGNED_TO.value(new JdbcNamedParameter("storyAssignedTo")),
                        PRODUCT_STORYTABLE.STORY_ASSIGNED_DATE.value(new JdbcNamedParameter("storyAssignedDate")),
                        PRODUCT_STORYTABLE.STORY_LAST_EDITED_BY.value(new JdbcNamedParameter("storyLastEditedBy")),
                        PRODUCT_STORYTABLE.STORY_LAST_EDITED_DATE.value(new JdbcNamedParameter("storyLastEditedDate")),
                        PRODUCT_STORYTABLE.STORY_REVIEWED_BY.value(new JdbcNamedParameter("storyReviewedBy")),
                        PRODUCT_STORYTABLE.STORY_REVIEWED_DATE.value(new JdbcNamedParameter("storyReviewedDate")),
                        PRODUCT_STORYTABLE.STORY_CLOSED_BY.value(new JdbcNamedParameter("storyClosedBy")),
                        PRODUCT_STORYTABLE.STORY_CLOSED_DATE.value(new JdbcNamedParameter("storyClosedDate")),
                        PRODUCT_STORYTABLE.STORY_CLOSED_REASON.value(new JdbcNamedParameter("storyClosedReason")),
                        PRODUCT_STORYTABLE.TO_BUG.value(new JdbcNamedParameter("toBug")),
                        PRODUCT_STORYTABLE.STORY_LINK_STORIES.value(new JdbcNamedParameter("storyLinkStories")),
                        PRODUCT_STORYTABLE.STORY_CHILD_STORIES.value(new JdbcNamedParameter("storyChildStories")),
                        PRODUCT_STORYTABLE.STORY_DUPLICATE_STORY.value(new JdbcNamedParameter("storyDuplicateStory")),
                        PRODUCT_STORYTABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
                        PRODUCT_STORYTABLE.BUILD_ID.value(new JdbcNamedParameter("buildId")),
                        PRODUCT_STORYTABLE.CLIENT_REQUEST_ID.value(new JdbcNamedParameter("clientRequestId")),
                        PRODUCT_STORYTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
                        PRODUCT_STORYTABLE.STORY_ID.eq(new JdbcNamedParameter("storyId")));
            }
        });
    }

    public int[] batchDelete(List<ProductStory> productStorys) {
        if (CollectionUtil.isEmpty(productStorys)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(productStorys, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(PRODUCT_STORYTABLE).where(and(
                        PRODUCT_STORYTABLE.STORY_ID.eq(new JdbcNamedParameter("storyId")),
                        PRODUCT_STORYTABLE.COMPANY_ID.eq(new JdbcNamedParameter("companyId")),
                        PRODUCT_STORYTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
                        PRODUCT_STORYTABLE.STORY_PARENT_ID.eq(new JdbcNamedParameter("storyParentId")),
                        PRODUCT_STORYTABLE.MODULE_ID.eq(new JdbcNamedParameter("moduleId")),
                        PRODUCT_STORYTABLE.PLAN_ID.eq(new JdbcNamedParameter("planId")),
                        PRODUCT_STORYTABLE.STORY_STATUS.eq(new JdbcNamedParameter("storyStatus")),
                        PRODUCT_STORYTABLE.STORY_SOURCE.eq(new JdbcNamedParameter("storySource")),
                        PRODUCT_STORYTABLE.STORY_FROM_BUG.eq(new JdbcNamedParameter("storyFromBug")),
                        PRODUCT_STORYTABLE.STORY_TITLE.eq(new JdbcNamedParameter("storyTitle")),
                        PRODUCT_STORYTABLE.STORY_KEYWORDS.eq(new JdbcNamedParameter("storyKeywords")),
                        PRODUCT_STORYTABLE.STORY_TYPE.eq(new JdbcNamedParameter("storyType")),
                        PRODUCT_STORYTABLE.STORY_PRI.eq(new JdbcNamedParameter("storyPri")),
                        PRODUCT_STORYTABLE.STORY_ESTIMATE.eq(new JdbcNamedParameter("storyEstimate")),
                        PRODUCT_STORYTABLE.STORY_STAGE.eq(new JdbcNamedParameter("storyStage")),
                        PRODUCT_STORYTABLE.STORY_MAILTO.eq(new JdbcNamedParameter("storyMailto")),
                        PRODUCT_STORYTABLE.STORY_OPENED_BY.eq(new JdbcNamedParameter("storyOpenedBy")),
                        PRODUCT_STORYTABLE.STORY_OPENED_DATE.eq(new JdbcNamedParameter("storyOpenedDate")),
                        PRODUCT_STORYTABLE.STORY_ASSIGNED_TO.eq(new JdbcNamedParameter("storyAssignedTo")),
                        PRODUCT_STORYTABLE.STORY_ASSIGNED_DATE.eq(new JdbcNamedParameter("storyAssignedDate")),
                        PRODUCT_STORYTABLE.STORY_LAST_EDITED_BY.eq(new JdbcNamedParameter("storyLastEditedBy")),
                        PRODUCT_STORYTABLE.STORY_LAST_EDITED_DATE.eq(new JdbcNamedParameter("storyLastEditedDate")),
                        PRODUCT_STORYTABLE.STORY_REVIEWED_BY.eq(new JdbcNamedParameter("storyReviewedBy")),
                        PRODUCT_STORYTABLE.STORY_REVIEWED_DATE.eq(new JdbcNamedParameter("storyReviewedDate")),
                        PRODUCT_STORYTABLE.STORY_CLOSED_BY.eq(new JdbcNamedParameter("storyClosedBy")),
                        PRODUCT_STORYTABLE.STORY_CLOSED_DATE.eq(new JdbcNamedParameter("storyClosedDate")),
                        PRODUCT_STORYTABLE.STORY_CLOSED_REASON.eq(new JdbcNamedParameter("storyClosedReason")),
                        PRODUCT_STORYTABLE.TO_BUG.eq(new JdbcNamedParameter("toBug")),
                        PRODUCT_STORYTABLE.STORY_LINK_STORIES.eq(new JdbcNamedParameter("storyLinkStories")),
                        PRODUCT_STORYTABLE.STORY_CHILD_STORIES.eq(new JdbcNamedParameter("storyChildStories")),
                        PRODUCT_STORYTABLE.STORY_DUPLICATE_STORY.eq(new JdbcNamedParameter("storyDuplicateStory")),
                        PRODUCT_STORYTABLE.STORY_VERSION.eq(new JdbcNamedParameter("storyVersion")),
                        PRODUCT_STORYTABLE.BUILD_ID.eq(new JdbcNamedParameter("buildId")),
                        PRODUCT_STORYTABLE.CLIENT_REQUEST_ID.eq(new JdbcNamedParameter("clientRequestId")),
                        PRODUCT_STORYTABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
            }
        });
    }

    private Select addOrderByElements(Select select, OrderBy... orderBies) {
        if (orderBies == null || orderBies.length == 0) {
            return select;
        }
        List<OrderByElement> orderByElements = new ArrayList<OrderByElement>();
        for (int i = 0; i < orderBies.length; i++) {
            OrderByElement tempElement = null;
            if (orderBies[i] != null) {
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

    public Pager<ProductStory> complexQuery(int start, int limit, ProductStory productStory, final Boolean ignoreDelete, final Condition condition, final OrderBy... orderBys) {
        if (productStory == null) {
            productStory = new ProductStory();
        }
        Pager<ProductStory> pager = getDslTemplate().queryPager(start > 0 ? start : 0, limit, productStory, false, new SelectGenerateCallback<ProductStory>() {

            public Select generate(ProductStory t) {

                Select select = MysqlSelect.select(PRODUCT_STORYTABLE.ALL, PRODUCT_PLANTABLE.PLAN_NAME.as("planName"))
                        .from(PRODUCT_STORYTABLE).join(leftJoin(PRODUCT_PLANTABLE, PRODUCT_PLANTABLE.PLAN_ID.eq(PRODUCT_STORYTABLE.PLAN_ID)))
                        .where(and(
                                condition,
                                PRODUCT_STORYTABLE.COMPANY_ID.eq(t.getCompanyId()),
                                PRODUCT_STORYTABLE.PRODUCT_ID.eq(t.getProductId()),
                                PRODUCT_STORYTABLE.STORY_PARENT_ID.eq(t.getStoryParentId()),
                                PRODUCT_STORYTABLE.MODULE_ID.eq(t.getModuleId()),
                                PRODUCT_STORYTABLE.PLAN_ID.eq(t.getPlanId()),
                                PRODUCT_STORYTABLE.STORY_STATUS.eq(t.getStoryStatus()),
                                PRODUCT_STORYTABLE.STORY_SOURCE.eq(t.getStorySource()),
                                PRODUCT_STORYTABLE.STORY_FROM_BUG.eq(t.getStoryFromBug()),
                                PRODUCT_STORYTABLE.STORY_TITLE.eq(t.getStoryTitle()),
                                PRODUCT_STORYTABLE.STORY_KEYWORDS.eq(t.getStoryKeywords()),
                                PRODUCT_STORYTABLE.STORY_TYPE.eq(t.getStoryType()),
                                PRODUCT_STORYTABLE.STORY_PRI.eq(t.getStoryPri()),
                                PRODUCT_STORYTABLE.STORY_ESTIMATE.eq(t.getStoryEstimate()),
                                PRODUCT_STORYTABLE.STORY_STAGE.eq(t.getStoryStage()),
                                PRODUCT_STORYTABLE.STORY_MAILTO.eq(t.getStoryMailto()),
                                PRODUCT_STORYTABLE.STORY_OPENED_BY.eq(t.getStoryOpenedBy()),
                                PRODUCT_STORYTABLE.STORY_OPENED_DATE.eq(t.getStoryOpenedDate()),
                                PRODUCT_STORYTABLE.STORY_ASSIGNED_TO.eq(t.getStoryAssignedTo()),
                                PRODUCT_STORYTABLE.STORY_ASSIGNED_DATE.eq(t.getStoryAssignedDate()),
                                PRODUCT_STORYTABLE.STORY_LAST_EDITED_BY.eq(t.getStoryLastEditedBy()),
                                PRODUCT_STORYTABLE.STORY_LAST_EDITED_DATE.eq(t.getStoryLastEditedDate()),
                                PRODUCT_STORYTABLE.STORY_REVIEWED_BY.eq(t.getStoryReviewedBy()),
                                PRODUCT_STORYTABLE.STORY_REVIEWED_DATE.eq(t.getStoryReviewedDate()),
                                PRODUCT_STORYTABLE.STORY_CLOSED_BY.eq(t.getStoryClosedBy()),
                                PRODUCT_STORYTABLE.STORY_CLOSED_DATE.eq(t.getStoryClosedDate()),
                                PRODUCT_STORYTABLE.STORY_CLOSED_REASON.eq(t.getStoryClosedReason()),
                                PRODUCT_STORYTABLE.TO_BUG.eq(t.getToBug()),
                                PRODUCT_STORYTABLE.STORY_LINK_STORIES.eq(t.getStoryLinkStories()),
                                PRODUCT_STORYTABLE.STORY_CHILD_STORIES.eq(t.getStoryChildStories()),
                                PRODUCT_STORYTABLE.STORY_DUPLICATE_STORY.eq(t.getStoryDuplicateStory()),
                                PRODUCT_STORYTABLE.STORY_VERSION.eq(t.getStoryVersion()),
                                PRODUCT_STORYTABLE.BUILD_ID.eq(t.getBuildId()),
                                PRODUCT_STORYTABLE.CLIENT_REQUEST_ID.eq(t.getClientRequestId()),
                                PRODUCT_STORYTABLE.DELETED.eq(ignoreDelete ? null : t.getDeleted())
                        ));
                return addOrderByElements(select, orderBys);
            }
        });
        return pager;
    }

    public Pager<ProductStory> complexQueryRel(int start, int limit, ProductStory productStory, final Condition condition, final OrderBy... orderBys) {
        if (productStory == null) {
            productStory = new ProductStory();
        }
        return getDslTemplate().queryPager(start > 0 ? start : 0, limit, productStory, false, new SelectGenerateCallback<ProductStory>() {
            public Select generate(ProductStory t) {
                Select select = MysqlSelect.select(PRODUCT_STORYTABLE.ALL, PRODUCT_PLANTABLE.PLAN_NAME.as("planName"), PRODUCTTABLE.PRODUCT_NAME.as("productName")).
                        from(PRODUCT_STORYTABLE).join(leftJoin(PRODUCTTABLE, PRODUCT_STORYTABLE.PRODUCT_ID.eq(PRODUCTTABLE.PRODUCT_ID)), leftJoin(PRODUCT_PLANTABLE, PRODUCT_STORYTABLE.PLAN_ID.eq(PRODUCT_PLANTABLE.PLAN_ID)))
                        .where(and(
                                condition,
                                PRODUCT_STORYTABLE.COMPANY_ID.eq(t.getCompanyId()),
                                PRODUCT_STORYTABLE.PRODUCT_ID.eq(t.getProductId()),
                                PRODUCT_STORYTABLE.STORY_PARENT_ID.eq(t.getStoryParentId()),
                                PRODUCT_STORYTABLE.MODULE_ID.eq(t.getModuleId()),
                                PRODUCT_STORYTABLE.PLAN_ID.eq(t.getPlanId()),
                                PRODUCT_STORYTABLE.STORY_STATUS.eq(t.getStoryStatus()),
                                PRODUCT_STORYTABLE.STORY_SOURCE.eq(t.getStorySource()),
                                PRODUCT_STORYTABLE.STORY_FROM_BUG.eq(t.getStoryFromBug()),
                                PRODUCT_STORYTABLE.STORY_TITLE.eq(t.getStoryTitle()),
                                PRODUCT_STORYTABLE.STORY_KEYWORDS.eq(t.getStoryKeywords()),
                                PRODUCT_STORYTABLE.STORY_TYPE.eq(t.getStoryType()),
                                PRODUCT_STORYTABLE.STORY_PRI.eq(t.getStoryPri()),
                                PRODUCT_STORYTABLE.STORY_ESTIMATE.eq(t.getStoryEstimate()),
                                PRODUCT_STORYTABLE.STORY_STAGE.eq(t.getStoryStage()),
                                PRODUCT_STORYTABLE.STORY_MAILTO.eq(t.getStoryMailto()),
                                PRODUCT_STORYTABLE.STORY_OPENED_BY.eq(t.getStoryOpenedBy()),
                                PRODUCT_STORYTABLE.STORY_OPENED_DATE.eq(t.getStoryOpenedDate()),
                                PRODUCT_STORYTABLE.STORY_ASSIGNED_TO.eq(t.getStoryAssignedTo()),
                                PRODUCT_STORYTABLE.STORY_ASSIGNED_DATE.eq(t.getStoryAssignedDate()),
                                PRODUCT_STORYTABLE.STORY_LAST_EDITED_BY.eq(t.getStoryLastEditedBy()),
                                PRODUCT_STORYTABLE.STORY_LAST_EDITED_DATE.eq(t.getStoryLastEditedDate()),
                                PRODUCT_STORYTABLE.STORY_REVIEWED_BY.eq(t.getStoryReviewedBy()),
                                PRODUCT_STORYTABLE.STORY_REVIEWED_DATE.eq(t.getStoryReviewedDate()),
                                PRODUCT_STORYTABLE.STORY_CLOSED_BY.eq(t.getStoryClosedBy()),
                                PRODUCT_STORYTABLE.STORY_CLOSED_DATE.eq(t.getStoryClosedDate()),
                                PRODUCT_STORYTABLE.STORY_CLOSED_REASON.eq(t.getStoryClosedReason()),
                                PRODUCT_STORYTABLE.TO_BUG.eq(t.getToBug()),
                                PRODUCT_STORYTABLE.STORY_LINK_STORIES.eq(t.getStoryLinkStories()),
                                PRODUCT_STORYTABLE.STORY_CHILD_STORIES.eq(t.getStoryChildStories()),
                                PRODUCT_STORYTABLE.STORY_DUPLICATE_STORY.eq(t.getStoryDuplicateStory()),
                                PRODUCT_STORYTABLE.STORY_VERSION.eq(t.getStoryVersion()),
                                PRODUCT_STORYTABLE.BUILD_ID.eq(t.getBuildId()),
                                PRODUCT_STORYTABLE.CLIENT_REQUEST_ID.eq(t.getClientRequestId()),
                                PRODUCT_STORYTABLE.DELETED.eq(t.getDeleted())
                        ));
                return addOrderByElements(select, orderBys);
            }
        });
    }

    public List<ProductStory> findpNameBysId(Integer id) {
        Select select = select(PRODUCT_STORYTABLE.STORY_ID, PRODUCTTABLE.PRODUCT_ID, PRODUCTTABLE.PRODUCT_NAME).from(PRODUCT_STORYTABLE).join(leftJoin(PRODUCTTABLE, PRODUCTTABLE.PRODUCT_ID.eq(PRODUCT_STORYTABLE.PRODUCT_ID)));
        return getDslSession().fetchList(select, ProductStory.class);

    }

    public Pager<ProductStory> projectLinkedStory(int start, int limit, ProductStory productStory, Condition condition, OrderBy... orderBys) {
        Select select = MysqlSelect.select(PRODUCT_STORYTABLE.ALL).
                from(PRODUCT_STORYTABLE).join(newJoin(PROJECT_STORYTABLE, PRODUCT_STORYTABLE.STORY_ID.eq(PROJECT_STORYTABLE.STORY_ID)))
                .where(and(
                        condition,
                        PRODUCT_STORYTABLE.COMPANY_ID.eq(productStory.getCompanyId()),
                        PRODUCT_STORYTABLE.PRODUCT_ID.eq(productStory.getProductId()),
                        PRODUCT_STORYTABLE.STORY_PARENT_ID.eq(productStory.getStoryParentId()),
                        PRODUCT_STORYTABLE.MODULE_ID.eq(productStory.getModuleId()),
                        PRODUCT_STORYTABLE.PLAN_ID.eq(productStory.getPlanId()),
                        PRODUCT_STORYTABLE.STORY_STATUS.eq(productStory.getStoryStatus()),
                        PRODUCT_STORYTABLE.STORY_SOURCE.eq(productStory.getStorySource()),
                        PRODUCT_STORYTABLE.STORY_FROM_BUG.eq(productStory.getStoryFromBug()),
                        PRODUCT_STORYTABLE.STORY_TITLE.eq(productStory.getStoryTitle()),
                        PRODUCT_STORYTABLE.STORY_KEYWORDS.eq(productStory.getStoryKeywords()),
                        PRODUCT_STORYTABLE.STORY_TYPE.eq(productStory.getStoryType()),
                        PRODUCT_STORYTABLE.STORY_PRI.eq(productStory.getStoryPri()),
                        PRODUCT_STORYTABLE.STORY_ESTIMATE.eq(productStory.getStoryEstimate()),
                        PRODUCT_STORYTABLE.STORY_STAGE.eq(productStory.getStoryStage()),
                        PRODUCT_STORYTABLE.STORY_MAILTO.eq(productStory.getStoryMailto()),
                        PRODUCT_STORYTABLE.STORY_OPENED_BY.eq(productStory.getStoryOpenedBy()),
                        PRODUCT_STORYTABLE.STORY_OPENED_DATE.eq(productStory.getStoryOpenedDate()),
                        PRODUCT_STORYTABLE.STORY_ASSIGNED_TO.eq(productStory.getStoryAssignedTo()),
                        PRODUCT_STORYTABLE.STORY_ASSIGNED_DATE.eq(productStory.getStoryAssignedDate()),
                        PRODUCT_STORYTABLE.STORY_LAST_EDITED_BY.eq(productStory.getStoryLastEditedBy()),
                        PRODUCT_STORYTABLE.STORY_LAST_EDITED_DATE.eq(productStory.getStoryLastEditedDate()),
                        PRODUCT_STORYTABLE.STORY_REVIEWED_BY.eq(productStory.getStoryReviewedBy()),
                        PRODUCT_STORYTABLE.STORY_REVIEWED_DATE.eq(productStory.getStoryReviewedDate()),
                        PRODUCT_STORYTABLE.STORY_CLOSED_BY.eq(productStory.getStoryClosedBy()),
                        PRODUCT_STORYTABLE.STORY_CLOSED_DATE.eq(productStory.getStoryClosedDate()),
                        PRODUCT_STORYTABLE.STORY_CLOSED_REASON.eq(productStory.getStoryClosedReason()),
                        PRODUCT_STORYTABLE.TO_BUG.eq(productStory.getToBug()),
                        PRODUCT_STORYTABLE.STORY_LINK_STORIES.eq(productStory.getStoryLinkStories()),
                        PRODUCT_STORYTABLE.STORY_CHILD_STORIES.eq(productStory.getStoryChildStories()),
                        PRODUCT_STORYTABLE.STORY_DUPLICATE_STORY.eq(productStory.getStoryDuplicateStory()),
                        PRODUCT_STORYTABLE.STORY_VERSION.eq(productStory.getStoryVersion()),
                        PRODUCT_STORYTABLE.BUILD_ID.eq(productStory.getBuildId()),
                        PRODUCT_STORYTABLE.CLIENT_REQUEST_ID.eq(productStory.getClientRequestId()),
                        PRODUCT_STORYTABLE.DELETED.eq(productStory.getDeleted())
                ));
        return getDslSession().fetchPage(addOrderByElements(select, orderBys), start, limit, false, ProductStory.class);
    }

    public Integer getMaxNo(Integer productId) {
        Select select = select(PRODUCT_STORYTABLE.NO.max()).from(PRODUCT_STORYTABLE).where(PRODUCT_STORYTABLE.PRODUCT_ID.eq(productId));
        return getDslSession().fetchOneResult(select, Integer.class);
    }

    public Integer batchDelete(Integer... ids) {
        UpdateGenerateCallback callback = new UpdateGenerateCallback<Serializable[]>() {

            public Update generate(Serializable[] serializables) {
                return update(PRODUCT_STORYTABLE).set(
                        PRODUCT_STORYTABLE.DELETED.value(FieldUtil.DELETE_YES)).where(
                        PRODUCT_STORYTABLE.STORY_ID.in(serializables));
            }
        };

        return getDslSession().execute(callback.generate(ids));
    }

    public Integer deleteStoryByProduct(Integer productId) {
        Update update = update(PRODUCT_STORYTABLE).set(PRODUCT_STORYTABLE.DELETED.value(1)).where(and(PRODUCT_STORYTABLE.PRODUCT_ID.eq(productId), PRODUCT_STORYTABLE.DELETED.eq(0)));
        return getDslSession().execute(update);
    }

    public Integer deleteStoryByPlan(Integer planId) {
        Update update = update(PRODUCT_STORYTABLE).set(PRODUCT_STORYTABLE.DELETED.value(1)).where(and(PRODUCT_STORYTABLE.PLAN_ID.eq(planId), PRODUCT_STORYTABLE.DELETED.eq(0)));
        return getDslSession().execute(update);
    }

    public List<ProductStory> storyInCondition(String condition, Integer limit, Integer productId, Integer... ids) {
        Condition con = null;
        if (productId != null) {
            con = PRODUCT_STORYTABLE.PRODUCT_ID.eq(productId);
        }
        if (ids != null && ids.length != 0) {
            con = con == null ? PRODUCT_STORYTABLE.STORY_ID.in(ids) : and(con, PRODUCT_STORYTABLE.STORY_ID.in(ids));
        }
        Select select = MysqlSelect.select(PRODUCT_STORYTABLE.STORY_ID, PRODUCT_STORYTABLE.STORY_TITLE).from(PRODUCT_STORYTABLE).where(
                and(PRODUCT_STORYTABLE.DELETED.eq(0), PRODUCT_STORYTABLE.STORY_TITLE.like(condition), con)).limit(0, limit);
        return getDslSession().fetchList(select, ProductStory.class);
    }

    public Integer softDelete(Integer id) {
        return getDslTemplate().update(id, new UpdateGenerateCallback<Integer>() {
            public Update generate(Integer id) {
                Update update = update(PRODUCT_STORYTABLE).set(
                        PRODUCT_STORYTABLE.DELETED.value(FieldUtil.DELETE_YES)).where(
                        PRODUCT_STORYTABLE.STORY_ID.eq(id));
                return update;
            }
        });

    }

    public Integer getCount(ProductStory t, Join join, Condition... condition) {
        Select select = null;
        if (join == null) {
            select = select(PRODUCT_STORYTABLE.STORY_ID.count()).from(PRODUCT_STORYTABLE).where(and(storyPueryCondition(t, condition)));
        } else {
            select = select(PRODUCT_STORYTABLE.STORY_ID.count()).from(PRODUCT_STORYTABLE).join(join).where(and(storyPueryCondition(t, condition)));
        }

        return getDslSession().count(select);
    }

    public List<StoryCount> modelStoryCount(ProductStory t) {
        try {
            if (t == null) {
                t = new ProductStory();
            }
            int nm = getCount(t, leftJoin(SYSTEM_MODULETABLE, SYSTEM_MODULETABLE.MODULE_ID.eq(PRODUCT_STORYTABLE.MODULE_ID)), PRODUCT_STORYTABLE.MODULE_ID.isNotNull());
            Select select = select(PRODUCT_STORYTABLE.MODULE_ID, SYSTEM_MODULETABLE.MODULE_NAME.as("name"), FragmentSelectItemSql.fragmentSelect("count(product_story.story_id) as number"),
                    FragmentSelectItemSql.fragmentSelect("format(count(product_story.story_id)/" + nm + ",2) as percent"))
                    .from(PRODUCT_STORYTABLE).join(leftJoin(SYSTEM_MODULETABLE, SYSTEM_MODULETABLE.MODULE_ID.eq(PRODUCT_STORYTABLE.MODULE_ID)))
                    .where(and(storyPueryCondition(t, PRODUCT_STORYTABLE.MODULE_ID.isNotNull()))).groupBy(PRODUCT_STORYTABLE.MODULE_ID, SYSTEM_MODULETABLE.MODULE_NAME);
            List<StoryCount> storyCounts = getDslSession().fetchList(select, StoryCount.class);
            return storyCounts;
        } catch (Exception e) {
            return null;
        }
    }

    public List<StoryCount> productStoryCount(ProductStory t) {

        try {
            if (t == null) {
                t = new ProductStory();
            }

            int nm = getCount(t, leftJoin(PRODUCTTABLE, PRODUCTTABLE.PRODUCT_ID.eq(PRODUCT_STORYTABLE.PRODUCT_ID)), PRODUCT_STORYTABLE.PRODUCT_ID.isNotNull());
            Select select = select(PRODUCT_STORYTABLE.PRODUCT_ID, PRODUCTTABLE.PRODUCT_NAME.as("name"), FragmentSelectItemSql.fragmentSelect("count(product_story.story_id) as number"),
                    FragmentSelectItemSql.fragmentSelect("format(count(product_story.story_id)/" + nm + ",2) as percent"))
                    .from(PRODUCT_STORYTABLE).join(leftJoin(PRODUCTTABLE, PRODUCTTABLE.PRODUCT_ID.eq(PRODUCT_STORYTABLE.PRODUCT_ID)))
                    .where(and(storyPueryCondition(t, PRODUCT_STORYTABLE.PRODUCT_ID.isNotNull()))).groupBy(PRODUCT_STORYTABLE.PRODUCT_ID, PRODUCTTABLE.PRODUCT_NAME);
            List<StoryCount> storyCounts = getDslSession().fetchList(select, StoryCount.class);
            return storyCounts;
        } catch (Exception e) {
            return null;
        }
    }

    public List<StoryCount> planStoryCount(ProductStory t, ProductPlan plan) {
        try {
            if (t == null) {
                t = new ProductStory();
            }
            if (plan == null) {
                plan = new ProductPlan();
            }

            int nm = getCount(t, leftJoin(PRODUCT_PLANTABLE, PRODUCT_PLANTABLE.PLAN_ID.eq(PRODUCT_STORYTABLE.PLAN_ID)), PRODUCT_STORYTABLE.PLAN_ID.isNotNull());
            Select select = select(PRODUCT_STORYTABLE.PLAN_ID, PRODUCT_PLANTABLE.PLAN_NAME.as("name"), FragmentSelectItemSql.fragmentSelect("count(product_story.story_id) as number"),
                    FragmentSelectItemSql.fragmentSelect("format(count(product_story.story_id)/" + nm + ",2) as percent"))
                    .from(PRODUCT_STORYTABLE).join(leftJoin(PRODUCT_PLANTABLE, PRODUCT_PLANTABLE.PLAN_ID.eq(PRODUCT_STORYTABLE.PLAN_ID)))
                    .where(and(storyPueryCondition(t, PRODUCT_STORYTABLE.PLAN_ID.isNotNull()))).groupBy(PRODUCT_STORYTABLE.PLAN_ID, PRODUCT_PLANTABLE.PLAN_NAME);
            List<StoryCount> storyCounts = getDslSession().fetchList(select, StoryCount.class);
            return storyCounts;
        } catch (Exception e) {
            return null;
        }

    }

    public List<StoryCount> userStoryCount(ProductStory t, String field) {
        try {

            if (t == null) {
                t = new ProductStory();
            }

            Column column = new Column(PRODUCT_STORYTABLE, NameUtil.resolveNameDesc(field));
            int nm = getCount(t, leftJoin(ORG_USERTABLE, ORG_USERTABLE.ORG_USER_ID.eq(column)), column.isNotNull());

            Select select = select(column, ORG_USERTABLE.ORG_USER_REAL_NAME.as("name"), FragmentSelectItemSql.fragmentSelect("count(product_story.story_id) as number"),
                    FragmentSelectItemSql.fragmentSelect("format(count(product_story.story_id)/" + nm + ",2) as percent"))
                    .from(PRODUCT_STORYTABLE).join(leftJoin(ORG_USERTABLE, ORG_USERTABLE.ORG_USER_ID.eq(column))).where(and(storyPueryCondition(t, column.isNotNull()))).groupBy(column, ORG_USERTABLE.ORG_USER_REAL_NAME);

            List<StoryCount> storyCounts = getDslSession().fetchList(select, StoryCount.class);
            return storyCounts;
        } catch (Exception e) {
            return null;
        }
    }

    public Integer countStatus(int productId, int status) {
        Select select = MysqlSelect.select(PRODUCT_STORYTABLE.STORY_STATUS.count()).from(PRODUCT_STORYTABLE).where(PRODUCT_STORYTABLE.PRODUCT_ID.eq(productId)
                .and(PRODUCT_STORYTABLE.STORY_STATUS.eq(status)));
        return getDslTemplate().getDslSession().fetchOneResult(select, null);
    }

	/*
    select system_module.module_name as name,
	count(product_story.story_id) as number,
	format(count(product_story.story_id)/(select count(story_id) from product_story),2) as percent
 	from product_story left join system_module
	on product_story.module_id=system_module.module_id
	 group by product_story.product_id;*/

    public List<StoryCount> fieldStoryCount(ProductStory t, String field) {

        try {
            if (t == null) {
                t = new ProductStory();
            }

            Column column = new Column(PRODUCT_STORYTABLE, NameUtil.resolveNameDesc(field));
            int nm = getCount(t, null, column.isNotNull());

            Select select = select(column.as("name"), FragmentSelectItemSql.fragmentSelect("count(product_story.story_id) as number"),
                    FragmentSelectItemSql.fragmentSelect("format(count(product_story.story_id)/" + nm + ",2) as percent"))
                    .from(PRODUCT_STORYTABLE).where(and(storyPueryCondition(t, column.isNotNull()))).groupBy(column);

            return getDslSession().fetchList(select, StoryCount.class);
        } catch (Exception e) {
            return null;
        }
    }

    public int[] batchUpdateDel(List<ProductStory> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(ids, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(PRODUCT_STORYTABLE).set(
                        PRODUCT_STORYTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
                        PRODUCT_STORYTABLE.STORY_ID.eq(new JdbcNamedParameter("storyId")));
            }
        });
    }

    @Override
    public ProductStory findStoryByStoryId(Integer storyId) {
        Select select = Select.selectFrom(PRODUCT_STORYTABLE).where(PRODUCT_STORYTABLE.STORY_ID.eq(storyId));
        return getDslSession().fetchOneResult(select, ProductStory.class);
    }
}
