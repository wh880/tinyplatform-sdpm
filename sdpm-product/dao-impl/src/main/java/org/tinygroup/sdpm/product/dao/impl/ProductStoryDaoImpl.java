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

package org.tinygroup.sdpm.product.dao.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.product.dao.constant.ProductStoryTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;
import static org.tinygroup.tinysqldsl.base.FragmentSql.*;
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
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.ProductStoryDao;
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
public class ProductStoryDaoImpl extends TinyDslDaoSupport implements ProductStoryDao {

	public ProductStory add(ProductStory productStory) {
		return getDslTemplate().insertAndReturnKey(productStory, new InsertGenerateCallback<ProductStory>() {
			public Insert generate(ProductStory t) {
				Insert insert = insertInto(PRODUCT_STORYTABLE).values(
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
					PRODUCT_STORYTABLE.DELETED.value(t.getDeleted()));
				return insert;
			}
		});
	}

	public int edit(ProductStory productStory) {
		if(productStory == null || productStory.getStoryId() == null){
			return 0;
		}
		return getDslTemplate().update(productStory, new UpdateGenerateCallback<ProductStory>() {
			public Update generate(ProductStory t) {
				Update update = update(PRODUCT_STORYTABLE).set(
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
					PRODUCT_STORYTABLE.DELETED.value(t.getDeleted())).where(
					PRODUCT_STORYTABLE.STORY_ID.eq(t.getStoryId()));
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
				return delete(PRODUCT_STORYTABLE).where(PRODUCT_STORYTABLE.STORY_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(PRODUCT_STORYTABLE).where(PRODUCT_STORYTABLE.STORY_ID.in(t));
		}
		},pks);
	}

	public ProductStory getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, ProductStory.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(PRODUCT_STORYTABLE).where(PRODUCT_STORYTABLE.STORY_ID.eq(t));
			}
		});
	}

	public List<ProductStory> query(ProductStory productStory ,final OrderBy... orderArgs) {
		if(productStory==null){
			productStory=new ProductStory();
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
					PRODUCT_STORYTABLE.DELETED.eq(t.getDeleted())));
			return addOrderByElements(select, orderArgs);
			}
		});
	}

	public Pager<ProductStory> queryPager(int start,int limit ,ProductStory productStory ,final OrderBy... orderArgs) {
		if(productStory==null){
			productStory=new ProductStory();
		}
		return getDslTemplate().queryPager(start, limit, productStory, false, new SelectGenerateCallback<ProductStory>() {

			public Select generate(ProductStory t) {
				Select select = MysqlSelect.selectFrom(PRODUCT_STORYTABLE).where(
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
					PRODUCT_STORYTABLE.DELETED.eq(t.getDeleted())));
			return addOrderByElements(select, orderArgs);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<ProductStory> productStorys) {
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

	public int[] batchInsert(List<ProductStory> productStorys){
			return batchInsert(true ,productStorys);
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

	public Pager<ProductStory> complexQuery(int start, int limit, ProductStory productStory, final String condition, final OrderBy... orderBys) {
		if (productStory == null) {
			productStory = new ProductStory();
		}
		return getDslTemplate().queryPager(start, limit, productStory, false, new SelectGenerateCallback<ProductStory>() {

			public Select generate(ProductStory t) {
				Select select = MysqlSelect.selectFrom(PRODUCT_STORYTABLE).where(and(
						fragmentCondition(condition),
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
}
