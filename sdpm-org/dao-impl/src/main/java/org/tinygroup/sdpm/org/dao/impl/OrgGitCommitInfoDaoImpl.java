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

package org.tinygroup.sdpm.org.dao.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.org.dao.constant.OrgGitCommitInfoTable.*;
import static org.tinygroup.sdpm.org.dao.constant.OrgUserTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.Pager;
import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.sdpm.org.dao.pojo.OrgGitCommitInfo;
import org.tinygroup.sdpm.org.dao.OrgGitCommitInfoDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
@Repository
public class OrgGitCommitInfoDaoImpl extends TinyDslDaoSupport implements OrgGitCommitInfoDao {


	public String getNameByEmail(String email){
		OrgUser orgUser = new OrgUser();
		orgUser.setOrgUserEmail(email);
		List<OrgUser> list = getDslTemplate().query(orgUser, new SelectGenerateCallback<OrgUser>(){
			public Select generate(OrgUser t){
				Select select = selectFrom(ORG_USERTABLE).where(and(
						ORG_USERTABLE.ORG_USER_EMAIL.eq(t.getOrgUserEmail())
				));
				return select;
			}
		});
		if(list.size()>1||list.size()==0){
			return null;
		}else {
			return list.get(0).getOrgUserRealName();
		}
	}

	public List<OrgGitCommitInfo> findOrgGitCommitInfoByNameAndDate(String name, final Date beginDate, final Date endDate){
		OrgGitCommitInfo orgGitCommitInfo = new OrgGitCommitInfo();
		orgGitCommitInfo.setGitAuthorName(name);
		return getDslTemplate().query(orgGitCommitInfo,new SelectGenerateCallback<OrgGitCommitInfo>(){

			@Override
			public Select generate(OrgGitCommitInfo orgGitCommitInfo) {
				return selectFrom(ORG_GIT_COMMIT_INFO_TABLE).where(and(
						ORG_GIT_COMMIT_INFO_TABLE.GIT_AUTHOR_NAME.eq(orgGitCommitInfo.getGitAuthorName()),
						ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_TIME.between(beginDate,endDate)
				));
			}
		});
	}


	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrgGitCommitInfo add(OrgGitCommitInfo orgGitCommitInfo) {
		return getDslTemplate().insertAndReturnKey(orgGitCommitInfo, new InsertGenerateCallback<OrgGitCommitInfo>() {
			public Insert generate(OrgGitCommitInfo t) {
				Insert insert = insertInto(ORG_GIT_COMMIT_INFO_TABLE).values(
					ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_ID.value(t.getGitCommitId()),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_TIME.value(t.getGitCommitTime()),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_REPOSITORY_NAME.value(t.getGitRepositoryName()),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_AUTHOR_NAME.value(t.getGitAuthorName()),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_MESSAGE.value(t.getGitCommitMessage()),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_URL.value(t.getGitCommitUrl()),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_TYPE.value(t.getGitType()));
				return insert;
			}
		});
	}

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int edit(OrgGitCommitInfo orgGitCommitInfo) {
		if(orgGitCommitInfo == null || orgGitCommitInfo.getGitCommitId() == null){
			return 0;
		}
		return getDslTemplate().update(orgGitCommitInfo, new UpdateGenerateCallback<OrgGitCommitInfo>() {
			public Update generate(OrgGitCommitInfo t) {
				Update update = update(ORG_GIT_COMMIT_INFO_TABLE).set(
					ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_TIME.value(t.getGitCommitTime()),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_REPOSITORY_NAME.value(t.getGitRepositoryName()),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_AUTHOR_NAME.value(t.getGitAuthorName()),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_MESSAGE.value(t.getGitCommitMessage()),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_URL.value(t.getGitCommitUrl()),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_TYPE.value(t.getGitType())).where(
					ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_ID.eq(t.getGitCommitId()));
				return update;
			}
		});
	}

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int deleteByKey(Integer pk){
		if(pk == null){
			return 0;
		}
		return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
			public Delete generate(Serializable pk) {
				return delete(ORG_GIT_COMMIT_INFO_TABLE).where(ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_ID.eq(pk));
			}
		});
	}

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(ORG_GIT_COMMIT_INFO_TABLE).where(ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_ID.in(t));
		}
		},pks);
	}

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrgGitCommitInfo getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, OrgGitCommitInfo.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(ORG_GIT_COMMIT_INFO_TABLE).where(ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_ID.eq(t));
			}
		});
	}

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<OrgGitCommitInfo> query(OrgGitCommitInfo orgGitCommitInfo ,final OrderBy... orderArgs) {
		if(orgGitCommitInfo==null){
			orgGitCommitInfo=new OrgGitCommitInfo();
		}
		return getDslTemplate().query(orgGitCommitInfo, new SelectGenerateCallback<OrgGitCommitInfo>() {

			@SuppressWarnings("rawtypes")
			public Select generate(OrgGitCommitInfo t) {
				Select select = selectFrom(ORG_GIT_COMMIT_INFO_TABLE).where(
				and(
					ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_TIME.eq(t.getGitCommitTime()),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_REPOSITORY_NAME.eq(t.getGitRepositoryName()),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_AUTHOR_NAME.eq(t.getGitAuthorName()),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_MESSAGE.eq(t.getGitCommitMessage()),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_URL.eq(t.getGitCommitUrl()),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_TYPE.eq(t.getGitType())));
			return addOrderByElements(select, orderArgs);
			}
		});
	}

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Pager<OrgGitCommitInfo> queryPager(int start,int limit ,OrgGitCommitInfo orgGitCommitInfo ,final OrderBy... orderArgs) {
		if(orgGitCommitInfo==null){
			orgGitCommitInfo=new OrgGitCommitInfo();
		}
		return getDslTemplate().queryPager(start, limit, orgGitCommitInfo, false, new SelectGenerateCallback<OrgGitCommitInfo>() {

			public Select generate(OrgGitCommitInfo t) {
				Select select = Select.selectFrom(ORG_GIT_COMMIT_INFO_TABLE).where(
				and(
					ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_TIME.eq(t.getGitCommitTime()),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_REPOSITORY_NAME.eq(t.getGitRepositoryName()),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_AUTHOR_NAME.eq(t.getGitAuthorName()),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_MESSAGE.eq(t.getGitCommitMessage()),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_URL.eq(t.getGitCommitUrl()),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_TYPE.eq(t.getGitType())));
			return addOrderByElements(select, orderArgs);
			}
		});
	}

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int[] batchInsert(boolean autoGeneratedKeys ,List<OrgGitCommitInfo> orgGitCommitInfos) {
		if (CollectionUtil.isEmpty(orgGitCommitInfos)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, orgGitCommitInfos, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(ORG_GIT_COMMIT_INFO_TABLE).values(
					ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_TIME.value(new JdbcNamedParameter("gitCommitTime")),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_REPOSITORY_NAME.value(new JdbcNamedParameter("gitRepositoryName")),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_AUTHOR_NAME.value(new JdbcNamedParameter("gitAuthorName")),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_MESSAGE.value(new JdbcNamedParameter("gitCommitMessage")),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_URL.value(new JdbcNamedParameter("gitCommitUrl")),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_TYPE.value(new JdbcNamedParameter("gitType")));
			}
		});
	}

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int[] batchInsert(List<OrgGitCommitInfo> orgGitCommitInfos){
			return batchInsert(true ,orgGitCommitInfos);
	}

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int[] batchUpdate(List<OrgGitCommitInfo> orgGitCommitInfos) {
		if (CollectionUtil.isEmpty(orgGitCommitInfos)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(orgGitCommitInfos, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(ORG_GIT_COMMIT_INFO_TABLE).set(
					ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_TIME.value(new JdbcNamedParameter("gitCommitTime")),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_REPOSITORY_NAME.value(new JdbcNamedParameter("gitRepositoryName")),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_AUTHOR_NAME.value(new JdbcNamedParameter("gitAuthorName")),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_MESSAGE.value(new JdbcNamedParameter("gitCommitMessage")),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_URL.value(new JdbcNamedParameter("gitCommitUrl")),
					ORG_GIT_COMMIT_INFO_TABLE.GIT_TYPE.value(new JdbcNamedParameter("gitType"))).where(
				ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_ID.eq(new JdbcNamedParameter("gitCommitId")));
			}
		});
	}

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int[] batchDelete(List<OrgGitCommitInfo> orgGitCommitInfos) {
		if (CollectionUtil.isEmpty(orgGitCommitInfos)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(orgGitCommitInfos, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(ORG_GIT_COMMIT_INFO_TABLE).where(and(
				ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_ID.eq(new JdbcNamedParameter("gitCommitId")),
				ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_TIME.eq(new JdbcNamedParameter("gitCommitTime")),
				ORG_GIT_COMMIT_INFO_TABLE.GIT_REPOSITORY_NAME.eq(new JdbcNamedParameter("gitRepositoryName")),
				ORG_GIT_COMMIT_INFO_TABLE.GIT_AUTHOR_NAME.eq(new JdbcNamedParameter("gitAuthorName")),
				ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_MESSAGE.eq(new JdbcNamedParameter("gitCommitMessage")),
				ORG_GIT_COMMIT_INFO_TABLE.GIT_COMMIT_URL.eq(new JdbcNamedParameter("gitCommitUrl")),
				ORG_GIT_COMMIT_INFO_TABLE.GIT_TYPE.eq(new JdbcNamedParameter("gitType"))));
			}
		});
	}

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private  Select addOrderByElements(Select select ,OrderBy... orderBies){
		if (orderBies == null || orderBies.length == 0) {
			return select;
		}
		List<OrderByElement> orderByElements = new ArrayList<OrderByElement>();
		for (int i = 0; orderBies[i] != null && i < orderBies.length; i++) {
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
