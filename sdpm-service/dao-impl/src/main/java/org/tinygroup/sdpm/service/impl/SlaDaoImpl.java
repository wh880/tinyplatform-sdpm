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

package org.tinygroup.sdpm.service.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.service.constant.SlaTable.*;
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
import org.tinygroup.sdpm.service.pojo.Sla;
import org.tinygroup.sdpm.service.inter.SlaDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class SlaDaoImpl extends TinyDslDaoSupport implements SlaDao {

	public Sla insertObject(Sla sla) {
		return getDslTemplate().insertObject(sla, new InsertGenerateCallback<Sla>() {
			public Insert generate(Sla t) {
				Insert insert = insertInto(SLATABLE).values(
					SLATABLE.SLA_ID.value(t.getSlaId()),
					SLATABLE.COMPANY_ID.value(t.getCompanyId()),
					SLATABLE.SERVICE_LEVEL.value(t.getServiceLevel()),
					SLATABLE.SERVICE_DEADLINE.value(t.getServiceDeadline()),
					SLATABLE.CLIENT_ID.value(t.getClientId()),
					SLATABLE.PRODUCT_ID.value(t.getProductId()),
					SLATABLE.SLA_TITLE.value(t.getSlaTitle()),
					SLATABLE.SLA_SPEC.value(t.getSlaSpec()),
					SLATABLE.SERVICE_REPLY_TIME_LIMIT.value(t.getServiceReplyTimeLimit()),
					SLATABLE.SERVICE_REVIEW_TIME_LIMIT.value(t.getServiceReviewTimeLimit()),
					SLATABLE.SERVICE_EFFORT_LIMIT.value(t.getServiceEffortLimit()),
					SLATABLE.SERVICE_REQUEST_LIMIT.value(t.getServiceRequestLimit()),
					SLATABLE.SERVICE_TS_ONSITE_LIMIT.value(t.getServiceTsOnsiteLimit()),
					SLATABLE.SERVICE_SPECIALIST.value(t.getServiceSpecialist()),
					SLATABLE.SLA_STATUS.value(t.getSlaStatus()),
					SLATABLE.SLA_CREATED_BY.value(t.getSlaCreatedBy()),
					SLATABLE.SLA_CREATE_DATE.value(t.getSlaCreateDate()),
					SLATABLE.SLA_REVIEWED_BY.value(t.getSlaReviewedBy()),
					SLATABLE.SLA_REVIEW_DATE.value(t.getSlaReviewDate()),
					SLATABLE.SLA_CLOSED_BY.value(t.getSlaClosedBy()),
					SLATABLE.SLA_CLOSE_DATE.value(t.getSlaCloseDate()),
					SLATABLE.SLA_OPEN_COUNT.value(t.getSlaOpenCount()),
					SLATABLE.DELETED.value(t.getDeleted()));
				return insert;
			}
		});
	}

	public Sla insertObject(boolean autoGeneratedKeys ,Sla sla) {
		return getDslTemplate().insertObjectAndReturnKey(autoGeneratedKeys ,sla, new InsertGenerateCallback<Sla>() {

			public Insert generate(Sla t) {
				Insert insert = insertInto(SLATABLE).values(
					SLATABLE.SLA_ID.value(t.getSlaId()),
					SLATABLE.COMPANY_ID.value(t.getCompanyId()),
					SLATABLE.SERVICE_LEVEL.value(t.getServiceLevel()),
					SLATABLE.SERVICE_DEADLINE.value(t.getServiceDeadline()),
					SLATABLE.CLIENT_ID.value(t.getClientId()),
					SLATABLE.PRODUCT_ID.value(t.getProductId()),
					SLATABLE.SLA_TITLE.value(t.getSlaTitle()),
					SLATABLE.SLA_SPEC.value(t.getSlaSpec()),
					SLATABLE.SERVICE_REPLY_TIME_LIMIT.value(t.getServiceReplyTimeLimit()),
					SLATABLE.SERVICE_REVIEW_TIME_LIMIT.value(t.getServiceReviewTimeLimit()),
					SLATABLE.SERVICE_EFFORT_LIMIT.value(t.getServiceEffortLimit()),
					SLATABLE.SERVICE_REQUEST_LIMIT.value(t.getServiceRequestLimit()),
					SLATABLE.SERVICE_TS_ONSITE_LIMIT.value(t.getServiceTsOnsiteLimit()),
					SLATABLE.SERVICE_SPECIALIST.value(t.getServiceSpecialist()),
					SLATABLE.SLA_STATUS.value(t.getSlaStatus()),
					SLATABLE.SLA_CREATED_BY.value(t.getSlaCreatedBy()),
					SLATABLE.SLA_CREATE_DATE.value(t.getSlaCreateDate()),
					SLATABLE.SLA_REVIEWED_BY.value(t.getSlaReviewedBy()),
					SLATABLE.SLA_REVIEW_DATE.value(t.getSlaReviewDate()),
					SLATABLE.SLA_CLOSED_BY.value(t.getSlaClosedBy()),
					SLATABLE.SLA_CLOSE_DATE.value(t.getSlaCloseDate()),
					SLATABLE.SLA_OPEN_COUNT.value(t.getSlaOpenCount()),
					SLATABLE.DELETED.value(t.getDeleted()));
				return insert;
			}
		});
	}

	public int updateObject(Sla sla) {
		return getDslTemplate().updateObject(sla, new UpdateGenerateCallback<Sla>() {
			public Update generate(Sla t) {
				Update update = update(SLATABLE).set(
					SLATABLE.SLA_ID.value(t.getSlaId()),
					SLATABLE.COMPANY_ID.value(t.getCompanyId()),
					SLATABLE.SERVICE_LEVEL.value(t.getServiceLevel()),
					SLATABLE.SERVICE_DEADLINE.value(t.getServiceDeadline()),
					SLATABLE.CLIENT_ID.value(t.getClientId()),
					SLATABLE.PRODUCT_ID.value(t.getProductId()),
					SLATABLE.SLA_TITLE.value(t.getSlaTitle()),
					SLATABLE.SLA_SPEC.value(t.getSlaSpec()),
					SLATABLE.SERVICE_REPLY_TIME_LIMIT.value(t.getServiceReplyTimeLimit()),
					SLATABLE.SERVICE_REVIEW_TIME_LIMIT.value(t.getServiceReviewTimeLimit()),
					SLATABLE.SERVICE_EFFORT_LIMIT.value(t.getServiceEffortLimit()),
					SLATABLE.SERVICE_REQUEST_LIMIT.value(t.getServiceRequestLimit()),
					SLATABLE.SERVICE_TS_ONSITE_LIMIT.value(t.getServiceTsOnsiteLimit()),
					SLATABLE.SERVICE_SPECIALIST.value(t.getServiceSpecialist()),
					SLATABLE.SLA_STATUS.value(t.getSlaStatus()),
					SLATABLE.SLA_CREATED_BY.value(t.getSlaCreatedBy()),
					SLATABLE.SLA_CREATE_DATE.value(t.getSlaCreateDate()),
					SLATABLE.SLA_REVIEWED_BY.value(t.getSlaReviewedBy()),
					SLATABLE.SLA_REVIEW_DATE.value(t.getSlaReviewDate()),
					SLATABLE.SLA_CLOSED_BY.value(t.getSlaClosedBy()),
					SLATABLE.SLA_CLOSE_DATE.value(t.getSlaCloseDate()),
					SLATABLE.SLA_OPEN_COUNT.value(t.getSlaOpenCount()),
					SLATABLE.DELETED.value(t.getDeleted())).where(
					SLATABLE.SLA_ID.eq(t.getSlaId()));
				return update;
			}
		});
	}

	public int deleteObject(Serializable pk){
		return getDslTemplate().deleteObject(pk, new DeleteGenerateCallback<Serializable>() {
			public Delete generate(Serializable pk) {
				return delete(SLATABLE).where(SLATABLE.SLA_ID.eq(pk));
			}
		});
	}

	public int deleteObjects(Serializable... pks) {
		return getDslTemplate().deleteObjects(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(SLATABLE).where(SLATABLE.SLA_ID.in(t));
		}
		},pks);
	}

	public Sla getObjectById(Serializable pk) {
		return getDslTemplate().getObjectById(pk, Sla.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(SLATABLE).where(SLATABLE.SLA_ID.eq(t));
			}
		});
	}

	public List<Sla> queryObjects(Sla sla) {
		if(sla==null){
			sla=new Sla();
		}
		return getDslTemplate().queryObjects(sla, new SelectGenerateCallback<Sla>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Sla t) {
				return selectFrom(SLATABLE).where(
				and(
					SLATABLE.SLA_ID.eq(t.getSlaId()),
					SLATABLE.COMPANY_ID.eq(t.getCompanyId()),
					SLATABLE.SERVICE_LEVEL.eq(t.getServiceLevel()),
					SLATABLE.SERVICE_DEADLINE.eq(t.getServiceDeadline()),
					SLATABLE.CLIENT_ID.eq(t.getClientId()),
					SLATABLE.PRODUCT_ID.eq(t.getProductId()),
					SLATABLE.SLA_TITLE.eq(t.getSlaTitle()),
					SLATABLE.SLA_SPEC.eq(t.getSlaSpec()),
					SLATABLE.SERVICE_REPLY_TIME_LIMIT.eq(t.getServiceReplyTimeLimit()),
					SLATABLE.SERVICE_REVIEW_TIME_LIMIT.eq(t.getServiceReviewTimeLimit()),
					SLATABLE.SERVICE_EFFORT_LIMIT.eq(t.getServiceEffortLimit()),
					SLATABLE.SERVICE_REQUEST_LIMIT.eq(t.getServiceRequestLimit()),
					SLATABLE.SERVICE_TS_ONSITE_LIMIT.eq(t.getServiceTsOnsiteLimit()),
					SLATABLE.SERVICE_SPECIALIST.eq(t.getServiceSpecialist()),
					SLATABLE.SLA_STATUS.eq(t.getSlaStatus()),
					SLATABLE.SLA_CREATED_BY.eq(t.getSlaCreatedBy()),
					SLATABLE.SLA_CREATE_DATE.eq(t.getSlaCreateDate()),
					SLATABLE.SLA_REVIEWED_BY.eq(t.getSlaReviewedBy()),
					SLATABLE.SLA_REVIEW_DATE.eq(t.getSlaReviewDate()),
					SLATABLE.SLA_CLOSED_BY.eq(t.getSlaClosedBy()),
					SLATABLE.SLA_CLOSE_DATE.eq(t.getSlaCloseDate()),
					SLATABLE.SLA_OPEN_COUNT.eq(t.getSlaOpenCount()),
					SLATABLE.DELETED.eq(t.getDeleted())));
			}
		});
	}

	public Pager<Sla> queryObjectsForPage(int start,int limit ,Sla sla) {
		if(sla==null){
			sla=new Sla();
		}
		return getDslTemplate().queryObjectsForPage(start, limit, sla, false, new SelectGenerateCallback<Sla>() {

			public Select generate(Sla t) {
				return MysqlSelect.selectFrom(SLATABLE).where(
				and(
					SLATABLE.SLA_ID.eq(t.getSlaId()),
					SLATABLE.COMPANY_ID.eq(t.getCompanyId()),
					SLATABLE.SERVICE_LEVEL.eq(t.getServiceLevel()),
					SLATABLE.SERVICE_DEADLINE.eq(t.getServiceDeadline()),
					SLATABLE.CLIENT_ID.eq(t.getClientId()),
					SLATABLE.PRODUCT_ID.eq(t.getProductId()),
					SLATABLE.SLA_TITLE.eq(t.getSlaTitle()),
					SLATABLE.SLA_SPEC.eq(t.getSlaSpec()),
					SLATABLE.SERVICE_REPLY_TIME_LIMIT.eq(t.getServiceReplyTimeLimit()),
					SLATABLE.SERVICE_REVIEW_TIME_LIMIT.eq(t.getServiceReviewTimeLimit()),
					SLATABLE.SERVICE_EFFORT_LIMIT.eq(t.getServiceEffortLimit()),
					SLATABLE.SERVICE_REQUEST_LIMIT.eq(t.getServiceRequestLimit()),
					SLATABLE.SERVICE_TS_ONSITE_LIMIT.eq(t.getServiceTsOnsiteLimit()),
					SLATABLE.SERVICE_SPECIALIST.eq(t.getServiceSpecialist()),
					SLATABLE.SLA_STATUS.eq(t.getSlaStatus()),
					SLATABLE.SLA_CREATED_BY.eq(t.getSlaCreatedBy()),
					SLATABLE.SLA_CREATE_DATE.eq(t.getSlaCreateDate()),
					SLATABLE.SLA_REVIEWED_BY.eq(t.getSlaReviewedBy()),
					SLATABLE.SLA_REVIEW_DATE.eq(t.getSlaReviewDate()),
					SLATABLE.SLA_CLOSED_BY.eq(t.getSlaClosedBy()),
					SLATABLE.SLA_CLOSE_DATE.eq(t.getSlaCloseDate()),
					SLATABLE.SLA_OPEN_COUNT.eq(t.getSlaOpenCount()),
					SLATABLE.DELETED.eq(t.getDeleted())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Sla> slas) {
		if (CollectionUtil.isEmpty(slas)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, slas, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(SLATABLE).values(
					SLATABLE.SLA_ID.value(new JdbcNamedParameter("slaId")),
					SLATABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
					SLATABLE.SERVICE_LEVEL.value(new JdbcNamedParameter("serviceLevel")),
					SLATABLE.SERVICE_DEADLINE.value(new JdbcNamedParameter("serviceDeadline")),
					SLATABLE.CLIENT_ID.value(new JdbcNamedParameter("clientId")),
					SLATABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					SLATABLE.SLA_TITLE.value(new JdbcNamedParameter("slaTitle")),
					SLATABLE.SLA_SPEC.value(new JdbcNamedParameter("slaSpec")),
					SLATABLE.SERVICE_REPLY_TIME_LIMIT.value(new JdbcNamedParameter("serviceReplyTimeLimit")),
					SLATABLE.SERVICE_REVIEW_TIME_LIMIT.value(new JdbcNamedParameter("serviceReviewTimeLimit")),
					SLATABLE.SERVICE_EFFORT_LIMIT.value(new JdbcNamedParameter("serviceEffortLimit")),
					SLATABLE.SERVICE_REQUEST_LIMIT.value(new JdbcNamedParameter("serviceRequestLimit")),
					SLATABLE.SERVICE_TS_ONSITE_LIMIT.value(new JdbcNamedParameter("serviceTsOnsiteLimit")),
					SLATABLE.SERVICE_SPECIALIST.value(new JdbcNamedParameter("serviceSpecialist")),
					SLATABLE.SLA_STATUS.value(new JdbcNamedParameter("slaStatus")),
					SLATABLE.SLA_CREATED_BY.value(new JdbcNamedParameter("slaCreatedBy")),
					SLATABLE.SLA_CREATE_DATE.value(new JdbcNamedParameter("slaCreateDate")),
					SLATABLE.SLA_REVIEWED_BY.value(new JdbcNamedParameter("slaReviewedBy")),
					SLATABLE.SLA_REVIEW_DATE.value(new JdbcNamedParameter("slaReviewDate")),
					SLATABLE.SLA_CLOSED_BY.value(new JdbcNamedParameter("slaClosedBy")),
					SLATABLE.SLA_CLOSE_DATE.value(new JdbcNamedParameter("slaCloseDate")),
					SLATABLE.SLA_OPEN_COUNT.value(new JdbcNamedParameter("slaOpenCount")),
					SLATABLE.DELETED.value(new JdbcNamedParameter("deleted")));
			}
		});
	}

	public int[] batchInsert(List<Sla> slas){
			return batchInsert(true ,slas);
	}

	public int[] batchUpdate(List<Sla> slas) {
		if (CollectionUtil.isEmpty(slas)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(slas, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(SLATABLE).set(
					SLATABLE.SLA_ID.value(new JdbcNamedParameter("slaId")),
					SLATABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
					SLATABLE.SERVICE_LEVEL.value(new JdbcNamedParameter("serviceLevel")),
					SLATABLE.SERVICE_DEADLINE.value(new JdbcNamedParameter("serviceDeadline")),
					SLATABLE.CLIENT_ID.value(new JdbcNamedParameter("clientId")),
					SLATABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					SLATABLE.SLA_TITLE.value(new JdbcNamedParameter("slaTitle")),
					SLATABLE.SLA_SPEC.value(new JdbcNamedParameter("slaSpec")),
					SLATABLE.SERVICE_REPLY_TIME_LIMIT.value(new JdbcNamedParameter("serviceReplyTimeLimit")),
					SLATABLE.SERVICE_REVIEW_TIME_LIMIT.value(new JdbcNamedParameter("serviceReviewTimeLimit")),
					SLATABLE.SERVICE_EFFORT_LIMIT.value(new JdbcNamedParameter("serviceEffortLimit")),
					SLATABLE.SERVICE_REQUEST_LIMIT.value(new JdbcNamedParameter("serviceRequestLimit")),
					SLATABLE.SERVICE_TS_ONSITE_LIMIT.value(new JdbcNamedParameter("serviceTsOnsiteLimit")),
					SLATABLE.SERVICE_SPECIALIST.value(new JdbcNamedParameter("serviceSpecialist")),
					SLATABLE.SLA_STATUS.value(new JdbcNamedParameter("slaStatus")),
					SLATABLE.SLA_CREATED_BY.value(new JdbcNamedParameter("slaCreatedBy")),
					SLATABLE.SLA_CREATE_DATE.value(new JdbcNamedParameter("slaCreateDate")),
					SLATABLE.SLA_REVIEWED_BY.value(new JdbcNamedParameter("slaReviewedBy")),
					SLATABLE.SLA_REVIEW_DATE.value(new JdbcNamedParameter("slaReviewDate")),
					SLATABLE.SLA_CLOSED_BY.value(new JdbcNamedParameter("slaClosedBy")),
					SLATABLE.SLA_CLOSE_DATE.value(new JdbcNamedParameter("slaCloseDate")),
					SLATABLE.SLA_OPEN_COUNT.value(new JdbcNamedParameter("slaOpenCount")),
					SLATABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
				SLATABLE.SLA_ID.eq(new JdbcNamedParameter("slaId")));
			}
		});
	}

	public int[] batchDelete(List<Sla> slas) {
		if (CollectionUtil.isEmpty(slas)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(slas, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(SLATABLE).where(and(
				SLATABLE.SLA_ID.eq(new JdbcNamedParameter("slaId")),
				SLATABLE.COMPANY_ID.eq(new JdbcNamedParameter("companyId")),
				SLATABLE.SERVICE_LEVEL.eq(new JdbcNamedParameter("serviceLevel")),
				SLATABLE.SERVICE_DEADLINE.eq(new JdbcNamedParameter("serviceDeadline")),
				SLATABLE.CLIENT_ID.eq(new JdbcNamedParameter("clientId")),
				SLATABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
				SLATABLE.SLA_TITLE.eq(new JdbcNamedParameter("slaTitle")),
				SLATABLE.SLA_SPEC.eq(new JdbcNamedParameter("slaSpec")),
				SLATABLE.SERVICE_REPLY_TIME_LIMIT.eq(new JdbcNamedParameter("serviceReplyTimeLimit")),
				SLATABLE.SERVICE_REVIEW_TIME_LIMIT.eq(new JdbcNamedParameter("serviceReviewTimeLimit")),
				SLATABLE.SERVICE_EFFORT_LIMIT.eq(new JdbcNamedParameter("serviceEffortLimit")),
				SLATABLE.SERVICE_REQUEST_LIMIT.eq(new JdbcNamedParameter("serviceRequestLimit")),
				SLATABLE.SERVICE_TS_ONSITE_LIMIT.eq(new JdbcNamedParameter("serviceTsOnsiteLimit")),
				SLATABLE.SERVICE_SPECIALIST.eq(new JdbcNamedParameter("serviceSpecialist")),
				SLATABLE.SLA_STATUS.eq(new JdbcNamedParameter("slaStatus")),
				SLATABLE.SLA_CREATED_BY.eq(new JdbcNamedParameter("slaCreatedBy")),
				SLATABLE.SLA_CREATE_DATE.eq(new JdbcNamedParameter("slaCreateDate")),
				SLATABLE.SLA_REVIEWED_BY.eq(new JdbcNamedParameter("slaReviewedBy")),
				SLATABLE.SLA_REVIEW_DATE.eq(new JdbcNamedParameter("slaReviewDate")),
				SLATABLE.SLA_CLOSED_BY.eq(new JdbcNamedParameter("slaClosedBy")),
				SLATABLE.SLA_CLOSE_DATE.eq(new JdbcNamedParameter("slaCloseDate")),
				SLATABLE.SLA_OPEN_COUNT.eq(new JdbcNamedParameter("slaOpenCount")),
				SLATABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
			}
		});
	}

}
