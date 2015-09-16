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

import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

import java.io.Serializable;
import java.util.List;

import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.product.dao.inter.StoryDao;
import org.tinygroup.sdpm.product.dao.pojo.Story;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;

public class StoryDaoImpl extends TinyDslDaoSupport implements StoryDao {

	public Story add(Story story) {
		return getDslTemplate().insertAndReturnKey(story, new InsertGenerateCallback<Story>() {
			public Insert generate(Story t) {
				Insert insert = insertInto(STORYTABLE).values(
					STORYTABLE.STORY_ID.value(t.getStoryId()),
					STORYTABLE.COMPANY_ID.value(t.getCompanyId()),
					STORYTABLE.PRODUCT_ID.value(t.getProductId()),
					STORYTABLE.STORY_PARENTID.value(t.getStoryParentId()),
					STORYTABLE.MODULE_ID.value(t.getModuleId()),
					STORYTABLE.PLAN_ID.value(t.getPlanId()),
					STORYTABLE.STORY_STATUS.value(t.getStoryStatus()),
					STORYTABLE.STORY_SOURCE.value(t.getStorySource()),
					STORYTABLE.STORY_FROMBUG.value(t.getStoryFromBug()),
					STORYTABLE.STORY_TITLE.value(t.getStoryTitle()),
					STORYTABLE.STORY_KEYWORDS.value(t.getStoryKeywords()),
					STORYTABLE.STORY_TYPE.value(t.getStoryType()),
					STORYTABLE.STORY_PRI.value(t.getStoryPri()),
					STORYTABLE.STORY_ESTIMATE.value(t.getStoryEstimate()),
					STORYTABLE.STORY_STAGE.value(t.getStoryStage()),
					STORYTABLE.STORY_MAILTO.value(t.getStoryMailto()),
					STORYTABLE.STORY_OPENEDBY.value(t.getStoryOpenedBy()),
					STORYTABLE.STORY_OPENEDDATE.value(t.getStoryOpenedDate()),
					STORYTABLE.STORY_ASSIGNEDTO.value(t.getStoryAssignedTo()),
					STORYTABLE.STORY_ASSIGNEDDATE.value(t.getStoryAssignedDate()),
					STORYTABLE.STORY_LASTEDITEDBY.value(t.getStoryLastEditedBy()),
					STORYTABLE.STORY_LASTEDITEDDATE.value(t.getStoryLastEditedDate()),
					STORYTABLE.STORY_REVIEWEDBY.value(t.getStoryReviewedBy()),
					STORYTABLE.STORY_REVIEWEDDATE.value(t.getStoryReviewedDate()),
					STORYTABLE.STORY_CLOSEDBY.value(t.getStoryClosedBy()),
					STORYTABLE.STORY_CLOSEDDATE.value(t.getStoryClosedDate()),
					STORYTABLE.STORY_CLOSEDREASON.value(t.getStoryClosedReason()),
					STORYTABLE.STORY_TOBUG.value(t.getStoryToBug()),
					STORYTABLE.STORY_LINKSTORIES.value(t.getStoryLinkStories()),
					STORYTABLE.STORY_CHILDSTORIES.value(t.getStoryChildStories()),
					STORYTABLE.STORY_DUPLICATESTORY.value(t.getStoryDuplicateStory()),
					STORYTABLE.STORY_VERSION.value(t.getStoryVersion()),
					STORYTABLE.BUG_ID.value(t.getBugId()),
					STORYTABLE.CLIENT_REQUEST_ID.value(t.getClientRequestId()),
					STORYTABLE.DELETED.value(t.getDeleted()));
				return insert;
			}
		});
	}

	public int edit(Story story) {
		if(story == null || story.getStoryId() == null){
			return 0;
		}
		return getDslTemplate().update(story, new UpdateGenerateCallback<Story>() {
			public Update generate(Story t) {
				Update update = update(STORYTABLE).set(
					STORYTABLE.COMPANY_ID.value(t.getCompanyId()),
					STORYTABLE.PRODUCT_ID.value(t.getProductId()),
					STORYTABLE.STORY_PARENTID.value(t.getStoryParentId()),
					STORYTABLE.MODULE_ID.value(t.getModuleId()),
					STORYTABLE.PLAN_ID.value(t.getPlanId()),
					STORYTABLE.STORY_STATUS.value(t.getStoryStatus()),
					STORYTABLE.STORY_SOURCE.value(t.getStorySource()),
					STORYTABLE.STORY_FROMBUG.value(t.getStoryFromBug()),
					STORYTABLE.STORY_TITLE.value(t.getStoryTitle()),
					STORYTABLE.STORY_KEYWORDS.value(t.getStoryKeywords()),
					STORYTABLE.STORY_TYPE.value(t.getStoryType()),
					STORYTABLE.STORY_PRI.value(t.getStoryPri()),
					STORYTABLE.STORY_ESTIMATE.value(t.getStoryEstimate()),
					STORYTABLE.STORY_STAGE.value(t.getStoryStage()),
					STORYTABLE.STORY_MAILTO.value(t.getStoryMailto()),
					STORYTABLE.STORY_OPENEDBY.value(t.getStoryOpenedBy()),
					STORYTABLE.STORY_OPENEDDATE.value(t.getStoryOpenedDate()),
					STORYTABLE.STORY_ASSIGNEDTO.value(t.getStoryAssignedTo()),
					STORYTABLE.STORY_ASSIGNEDDATE.value(t.getStoryAssignedDate()),
					STORYTABLE.STORY_LASTEDITEDBY.value(t.getStoryLastEditedBy()),
					STORYTABLE.STORY_LASTEDITEDDATE.value(t.getStoryLastEditedDate()),
					STORYTABLE.STORY_REVIEWEDBY.value(t.getStoryReviewedBy()),
					STORYTABLE.STORY_REVIEWEDDATE.value(t.getStoryReviewedDate()),
					STORYTABLE.STORY_CLOSEDBY.value(t.getStoryClosedBy()),
					STORYTABLE.STORY_CLOSEDDATE.value(t.getStoryClosedDate()),
					STORYTABLE.STORY_CLOSEDREASON.value(t.getStoryClosedReason()),
					STORYTABLE.STORY_TOBUG.value(t.getStoryToBug()),
					STORYTABLE.STORY_LINKSTORIES.value(t.getStoryLinkStories()),
					STORYTABLE.STORY_CHILDSTORIES.value(t.getStoryChildStories()),
					STORYTABLE.STORY_DUPLICATESTORY.value(t.getStoryDuplicateStory()),
					STORYTABLE.STORY_VERSION.value(t.getStoryVersion()),
					STORYTABLE.BUG_ID.value(t.getBugId()),
					STORYTABLE.CLIENT_REQUEST_ID.value(t.getClientRequestId()),
					STORYTABLE.DELETED.value(t.getDeleted())).where(
					STORYTABLE.STORY_ID.eq(t.getStoryId()));
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
				return delete(STORYTABLE).where(STORYTABLE.STORY_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(STORYTABLE).where(STORYTABLE.STORY_ID.in(t));
		}
		},pks);
	}

	public Story getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Story.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(STORYTABLE).where(STORYTABLE.STORY_ID.eq(t));
			}
		});
	}

	public List<Story> query(Story story) {
		if(story==null){
			story=new Story();
		}
		return getDslTemplate().query(story, new SelectGenerateCallback<Story>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Story t) {
				return selectFrom(STORYTABLE).where(
				and(
					STORYTABLE.COMPANY_ID.eq(t.getCompanyId()),
					STORYTABLE.PRODUCT_ID.eq(t.getProductId()),
					STORYTABLE.STORY_PARENTID.eq(t.getStoryParentId()),
					STORYTABLE.MODULE_ID.eq(t.getModuleId()),
					STORYTABLE.PLAN_ID.eq(t.getPlanId()),
					STORYTABLE.STORY_STATUS.eq(t.getStoryStatus()),
					STORYTABLE.STORY_SOURCE.eq(t.getStorySource()),
					STORYTABLE.STORY_FROMBUG.eq(t.getStoryFromBug()),
					STORYTABLE.STORY_TITLE.eq(t.getStoryTitle()),
					STORYTABLE.STORY_KEYWORDS.eq(t.getStoryKeywords()),
					STORYTABLE.STORY_TYPE.eq(t.getStoryType()),
					STORYTABLE.STORY_PRI.eq(t.getStoryPri()),
					STORYTABLE.STORY_ESTIMATE.eq(t.getStoryEstimate()),
					STORYTABLE.STORY_STAGE.eq(t.getStoryStage()),
					STORYTABLE.STORY_MAILTO.eq(t.getStoryMailto()),
					STORYTABLE.STORY_OPENEDBY.eq(t.getStoryOpenedBy()),
					STORYTABLE.STORY_OPENEDDATE.eq(t.getStoryOpenedDate()),
					STORYTABLE.STORY_ASSIGNEDTO.eq(t.getStoryAssignedTo()),
					STORYTABLE.STORY_ASSIGNEDDATE.eq(t.getStoryAssignedDate()),
					STORYTABLE.STORY_LASTEDITEDBY.eq(t.getStoryLastEditedBy()),
					STORYTABLE.STORY_LASTEDITEDDATE.eq(t.getStoryLastEditedDate()),
					STORYTABLE.STORY_REVIEWEDBY.eq(t.getStoryReviewedBy()),
					STORYTABLE.STORY_REVIEWEDDATE.eq(t.getStoryReviewedDate()),
					STORYTABLE.STORY_CLOSEDBY.eq(t.getStoryClosedBy()),
					STORYTABLE.STORY_CLOSEDDATE.eq(t.getStoryClosedDate()),
					STORYTABLE.STORY_CLOSEDREASON.eq(t.getStoryClosedReason()),
					STORYTABLE.STORY_TOBUG.eq(t.getStoryToBug()),
					STORYTABLE.STORY_LINKSTORIES.eq(t.getStoryLinkStories()),
					STORYTABLE.STORY_CHILDSTORIES.eq(t.getStoryChildStories()),
					STORYTABLE.STORY_DUPLICATESTORY.eq(t.getStoryDuplicateStory()),
					STORYTABLE.STORY_VERSION.eq(t.getStoryVersion()),
					STORYTABLE.BUG_ID.eq(t.getBugId()),
					STORYTABLE.CLIENT_REQUEST_ID.eq(t.getClientRequestId()),
					STORYTABLE.DELETED.eq(t.getDeleted())));
			}
		});
	}

	public Pager<Story> queryPager(int start,int limit ,Story story) {
		if(story==null){
			story=new Story();
		}
		return getDslTemplate().queryPager(start, limit, story, false, new SelectGenerateCallback<Story>() {

			public Select generate(Story t) {
				return MysqlSelect.selectFrom(STORYTABLE).where(
				and(
					STORYTABLE.COMPANY_ID.eq(t.getCompanyId()),
					STORYTABLE.PRODUCT_ID.eq(t.getProductId()),
					STORYTABLE.STORY_PARENTID.eq(t.getStoryParentId()),
					STORYTABLE.MODULE_ID.eq(t.getModuleId()),
					STORYTABLE.PLAN_ID.eq(t.getPlanId()),
					STORYTABLE.STORY_STATUS.eq(t.getStoryStatus()),
					STORYTABLE.STORY_SOURCE.eq(t.getStorySource()),
					STORYTABLE.STORY_FROMBUG.eq(t.getStoryFromBug()),
					STORYTABLE.STORY_TITLE.eq(t.getStoryTitle()),
					STORYTABLE.STORY_KEYWORDS.eq(t.getStoryKeywords()),
					STORYTABLE.STORY_TYPE.eq(t.getStoryType()),
					STORYTABLE.STORY_PRI.eq(t.getStoryPri()),
					STORYTABLE.STORY_ESTIMATE.eq(t.getStoryEstimate()),
					STORYTABLE.STORY_STAGE.eq(t.getStoryStage()),
					STORYTABLE.STORY_MAILTO.eq(t.getStoryMailto()),
					STORYTABLE.STORY_OPENEDBY.eq(t.getStoryOpenedBy()),
					STORYTABLE.STORY_OPENEDDATE.eq(t.getStoryOpenedDate()),
					STORYTABLE.STORY_ASSIGNEDTO.eq(t.getStoryAssignedTo()),
					STORYTABLE.STORY_ASSIGNEDDATE.eq(t.getStoryAssignedDate()),
					STORYTABLE.STORY_LASTEDITEDBY.eq(t.getStoryLastEditedBy()),
					STORYTABLE.STORY_LASTEDITEDDATE.eq(t.getStoryLastEditedDate()),
					STORYTABLE.STORY_REVIEWEDBY.eq(t.getStoryReviewedBy()),
					STORYTABLE.STORY_REVIEWEDDATE.eq(t.getStoryReviewedDate()),
					STORYTABLE.STORY_CLOSEDBY.eq(t.getStoryClosedBy()),
					STORYTABLE.STORY_CLOSEDDATE.eq(t.getStoryClosedDate()),
					STORYTABLE.STORY_CLOSEDREASON.eq(t.getStoryClosedReason()),
					STORYTABLE.STORY_TOBUG.eq(t.getStoryToBug()),
					STORYTABLE.STORY_LINKSTORIES.eq(t.getStoryLinkStories()),
					STORYTABLE.STORY_CHILDSTORIES.eq(t.getStoryChildStories()),
					STORYTABLE.STORY_DUPLICATESTORY.eq(t.getStoryDuplicateStory()),
					STORYTABLE.STORY_VERSION.eq(t.getStoryVersion()),
					STORYTABLE.BUG_ID.eq(t.getBugId()),
					STORYTABLE.CLIENT_REQUEST_ID.eq(t.getClientRequestId()),
					STORYTABLE.DELETED.eq(t.getDeleted())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Story> storys) {
		if (CollectionUtil.isEmpty(storys)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, storys, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(STORYTABLE).values(
					STORYTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
					STORYTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					STORYTABLE.STORY_PARENTID.value(new JdbcNamedParameter("storyParentId")),
					STORYTABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")),
					STORYTABLE.PLAN_ID.value(new JdbcNamedParameter("planId")),
					STORYTABLE.STORY_STATUS.value(new JdbcNamedParameter("storyStatus")),
					STORYTABLE.STORY_SOURCE.value(new JdbcNamedParameter("storySource")),
					STORYTABLE.STORY_FROMBUG.value(new JdbcNamedParameter("storyFromBug")),
					STORYTABLE.STORY_TITLE.value(new JdbcNamedParameter("storyTitle")),
					STORYTABLE.STORY_KEYWORDS.value(new JdbcNamedParameter("storyKeywords")),
					STORYTABLE.STORY_TYPE.value(new JdbcNamedParameter("storyType")),
					STORYTABLE.STORY_PRI.value(new JdbcNamedParameter("storyPri")),
					STORYTABLE.STORY_ESTIMATE.value(new JdbcNamedParameter("storyEstimate")),
					STORYTABLE.STORY_STAGE.value(new JdbcNamedParameter("storyStage")),
					STORYTABLE.STORY_MAILTO.value(new JdbcNamedParameter("storyMailto")),
					STORYTABLE.STORY_OPENEDBY.value(new JdbcNamedParameter("storyOpenedBy")),
					STORYTABLE.STORY_OPENEDDATE.value(new JdbcNamedParameter("storyOpenedDate")),
					STORYTABLE.STORY_ASSIGNEDTO.value(new JdbcNamedParameter("storyAssignedTo")),
					STORYTABLE.STORY_ASSIGNEDDATE.value(new JdbcNamedParameter("storyAssignedDate")),
					STORYTABLE.STORY_LASTEDITEDBY.value(new JdbcNamedParameter("storyLastEditedBy")),
					STORYTABLE.STORY_LASTEDITEDDATE.value(new JdbcNamedParameter("storyLastEditedDate")),
					STORYTABLE.STORY_REVIEWEDBY.value(new JdbcNamedParameter("storyReviewedBy")),
					STORYTABLE.STORY_REVIEWEDDATE.value(new JdbcNamedParameter("storyReviewedDate")),
					STORYTABLE.STORY_CLOSEDBY.value(new JdbcNamedParameter("storyClosedBy")),
					STORYTABLE.STORY_CLOSEDDATE.value(new JdbcNamedParameter("storyClosedDate")),
					STORYTABLE.STORY_CLOSEDREASON.value(new JdbcNamedParameter("storyClosedReason")),
					STORYTABLE.STORY_TOBUG.value(new JdbcNamedParameter("storyToBug")),
					STORYTABLE.STORY_LINKSTORIES.value(new JdbcNamedParameter("storyLinkStories")),
					STORYTABLE.STORY_CHILDSTORIES.value(new JdbcNamedParameter("storyChildStories")),
					STORYTABLE.STORY_DUPLICATESTORY.value(new JdbcNamedParameter("storyDuplicateStory")),
					STORYTABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
					STORYTABLE.BUG_ID.value(new JdbcNamedParameter("bugId")),
					STORYTABLE.CLIENT_REQUEST_ID.value(new JdbcNamedParameter("clientRequestId")),
					STORYTABLE.DELETED.value(new JdbcNamedParameter("deleted")));
			}
		});
	}

	public int[] batchInsert(List<Story> storys){
			return batchInsert(true ,storys);
	}

	public int[] batchUpdate(List<Story> storys) {
		if (CollectionUtil.isEmpty(storys)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(storys, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(STORYTABLE).set(
					STORYTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
					STORYTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					STORYTABLE.STORY_PARENTID.value(new JdbcNamedParameter("storyParentId")),
					STORYTABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")),
					STORYTABLE.PLAN_ID.value(new JdbcNamedParameter("planId")),
					STORYTABLE.STORY_STATUS.value(new JdbcNamedParameter("storyStatus")),
					STORYTABLE.STORY_SOURCE.value(new JdbcNamedParameter("storySource")),
					STORYTABLE.STORY_FROMBUG.value(new JdbcNamedParameter("storyFromBug")),
					STORYTABLE.STORY_TITLE.value(new JdbcNamedParameter("storyTitle")),
					STORYTABLE.STORY_KEYWORDS.value(new JdbcNamedParameter("storyKeywords")),
					STORYTABLE.STORY_TYPE.value(new JdbcNamedParameter("storyType")),
					STORYTABLE.STORY_PRI.value(new JdbcNamedParameter("storyPri")),
					STORYTABLE.STORY_ESTIMATE.value(new JdbcNamedParameter("storyEstimate")),
					STORYTABLE.STORY_STAGE.value(new JdbcNamedParameter("storyStage")),
					STORYTABLE.STORY_MAILTO.value(new JdbcNamedParameter("storyMailto")),
					STORYTABLE.STORY_OPENEDBY.value(new JdbcNamedParameter("storyOpenedBy")),
					STORYTABLE.STORY_OPENEDDATE.value(new JdbcNamedParameter("storyOpenedDate")),
					STORYTABLE.STORY_ASSIGNEDTO.value(new JdbcNamedParameter("storyAssignedTo")),
					STORYTABLE.STORY_ASSIGNEDDATE.value(new JdbcNamedParameter("storyAssignedDate")),
					STORYTABLE.STORY_LASTEDITEDBY.value(new JdbcNamedParameter("storyLastEditedBy")),
					STORYTABLE.STORY_LASTEDITEDDATE.value(new JdbcNamedParameter("storyLastEditedDate")),
					STORYTABLE.STORY_REVIEWEDBY.value(new JdbcNamedParameter("storyReviewedBy")),
					STORYTABLE.STORY_REVIEWEDDATE.value(new JdbcNamedParameter("storyReviewedDate")),
					STORYTABLE.STORY_CLOSEDBY.value(new JdbcNamedParameter("storyClosedBy")),
					STORYTABLE.STORY_CLOSEDDATE.value(new JdbcNamedParameter("storyClosedDate")),
					STORYTABLE.STORY_CLOSEDREASON.value(new JdbcNamedParameter("storyClosedReason")),
					STORYTABLE.STORY_TOBUG.value(new JdbcNamedParameter("storyToBug")),
					STORYTABLE.STORY_LINKSTORIES.value(new JdbcNamedParameter("storyLinkStories")),
					STORYTABLE.STORY_CHILDSTORIES.value(new JdbcNamedParameter("storyChildStories")),
					STORYTABLE.STORY_DUPLICATESTORY.value(new JdbcNamedParameter("storyDuplicateStory")),
					STORYTABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
					STORYTABLE.BUG_ID.value(new JdbcNamedParameter("bugId")),
					STORYTABLE.CLIENT_REQUEST_ID.value(new JdbcNamedParameter("clientRequestId")),
					STORYTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
				STORYTABLE.STORY_ID.eq(new JdbcNamedParameter("storyId")));
			}
		});
	}

	public int[] batchDelete(List<Story> storys) {
		if (CollectionUtil.isEmpty(storys)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(storys, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(STORYTABLE).where(and(
				STORYTABLE.STORY_ID.eq(new JdbcNamedParameter("storyId")),
				STORYTABLE.COMPANY_ID.eq(new JdbcNamedParameter("companyId")),
				STORYTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
				STORYTABLE.STORY_PARENTID.eq(new JdbcNamedParameter("storyParentId")),
				STORYTABLE.MODULE_ID.eq(new JdbcNamedParameter("moduleId")),
				STORYTABLE.PLAN_ID.eq(new JdbcNamedParameter("planId")),
				STORYTABLE.STORY_STATUS.eq(new JdbcNamedParameter("storyStatus")),
				STORYTABLE.STORY_SOURCE.eq(new JdbcNamedParameter("storySource")),
				STORYTABLE.STORY_FROMBUG.eq(new JdbcNamedParameter("storyFromBug")),
				STORYTABLE.STORY_TITLE.eq(new JdbcNamedParameter("storyTitle")),
				STORYTABLE.STORY_KEYWORDS.eq(new JdbcNamedParameter("storyKeywords")),
				STORYTABLE.STORY_TYPE.eq(new JdbcNamedParameter("storyType")),
				STORYTABLE.STORY_PRI.eq(new JdbcNamedParameter("storyPri")),
				STORYTABLE.STORY_ESTIMATE.eq(new JdbcNamedParameter("storyEstimate")),
				STORYTABLE.STORY_STAGE.eq(new JdbcNamedParameter("storyStage")),
				STORYTABLE.STORY_MAILTO.eq(new JdbcNamedParameter("storyMailto")),
				STORYTABLE.STORY_OPENEDBY.eq(new JdbcNamedParameter("storyOpenedBy")),
				STORYTABLE.STORY_OPENEDDATE.eq(new JdbcNamedParameter("storyOpenedDate")),
				STORYTABLE.STORY_ASSIGNEDTO.eq(new JdbcNamedParameter("storyAssignedTo")),
				STORYTABLE.STORY_ASSIGNEDDATE.eq(new JdbcNamedParameter("storyAssignedDate")),
				STORYTABLE.STORY_LASTEDITEDBY.eq(new JdbcNamedParameter("storyLastEditedBy")),
				STORYTABLE.STORY_LASTEDITEDDATE.eq(new JdbcNamedParameter("storyLastEditedDate")),
				STORYTABLE.STORY_REVIEWEDBY.eq(new JdbcNamedParameter("storyReviewedBy")),
				STORYTABLE.STORY_REVIEWEDDATE.eq(new JdbcNamedParameter("storyReviewedDate")),
				STORYTABLE.STORY_CLOSEDBY.eq(new JdbcNamedParameter("storyClosedBy")),
				STORYTABLE.STORY_CLOSEDDATE.eq(new JdbcNamedParameter("storyClosedDate")),
				STORYTABLE.STORY_CLOSEDREASON.eq(new JdbcNamedParameter("storyClosedReason")),
				STORYTABLE.STORY_TOBUG.eq(new JdbcNamedParameter("storyToBug")),
				STORYTABLE.STORY_LINKSTORIES.eq(new JdbcNamedParameter("storyLinkStories")),
				STORYTABLE.STORY_CHILDSTORIES.eq(new JdbcNamedParameter("storyChildStories")),
				STORYTABLE.STORY_DUPLICATESTORY.eq(new JdbcNamedParameter("storyDuplicateStory")),
				STORYTABLE.STORY_VERSION.eq(new JdbcNamedParameter("storyVersion")),
				STORYTABLE.BUG_ID.eq(new JdbcNamedParameter("bugId")),
				STORYTABLE.CLIENT_REQUEST_ID.eq(new JdbcNamedParameter("clientRequestId")),
				STORYTABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
			}
		});
	}

}
