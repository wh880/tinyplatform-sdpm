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

import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.org.dao.OrgUserDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.org.dao.constant.OrgUserTable.ORG_USERTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

public class OrgUserDaoImpl extends TinyDslDaoSupport implements OrgUserDao {

	public OrgUser add(OrgUser orgUser) {
		return getDslTemplate().insertAndReturnKey(orgUser, new InsertGenerateCallback<OrgUser>() {
			public Insert generate(OrgUser t) {
				Insert insert = insertInto(ORG_USERTABLE).values(
						ORG_USERTABLE.ORG_USER_ID.value(t.getOrgUserId()),
					ORG_USERTABLE.ORG_DEPT_ID.value(t.getOrgDeptId()),
					ORG_USERTABLE.ORG_USER_ACCOUNT.value(t.getOrgUserAccount()),
					ORG_USERTABLE.ORG_USER_PASSWORD.value(t.getOrgUserPassword()),
					ORG_USERTABLE.ORG_USER_ROLE.value(t.getOrgUserRole()),
					ORG_USERTABLE.ORG_USER_REALNAME.value(t.getOrgUserRealName()),
					ORG_USERTABLE.ORG_USER_NICKNAME.value(t.getOrgUserNickname()),
					ORG_USERTABLE.ORG_USER_SUBMITTER.value(t.getOrgUserSubmitter()),
					ORG_USERTABLE.ORG_USER_AVATAR.value(t.getOrgUserAvatar()),
					ORG_USERTABLE.ORG_USER_BIRTHDAY.value(t.getOrgUserBirthday()),
					ORG_USERTABLE.ORG_USER_GENDER.value(t.getOrgUserGender()),
					ORG_USERTABLE.ORG_USER_EMAIL.value(t.getOrgUserEmail()),
					ORG_USERTABLE.ORG_USER_SKYPE.value(t.getOrgUserSKYPE()),
					ORG_USERTABLE.ORG_USER_QQ.value(t.getOrgUserQQ()),
					ORG_USERTABLE.ORG_USER_YAHOO.value(t.getOrgUserYahoo()),
					ORG_USERTABLE.ORG_USER_GTALK.value(t.getOrgUserGTalk()),
					ORG_USERTABLE.ORG_USER_WANGWANG.value(t.getOrgUserWANGWANG()),
					ORG_USERTABLE.ORG_USER_MOBILE.value(t.getOrgUserMobile()),
					ORG_USERTABLE.ORG_USER_PHONE.value(t.getOrgUserPhone()),
					ORG_USERTABLE.ORG_USER_ADDRESS.value(t.getOrgUserAddress()),
					ORG_USERTABLE.ORG_USER_ZIPCODE.value(t.getOrgUserZipCode()),
					ORG_USERTABLE.ORG_USER_JOIN.value(t.getOrgUserJoin()),
					ORG_USERTABLE.ORG_USER_VISITS.value(t.getOrgUserVisits()),
					ORG_USERTABLE.ORG_USER_IP.value(t.getOrgUserIp()),
					ORG_USERTABLE.ORG_USER_LAST.value(t.getOrgUserLast()),
					ORG_USERTABLE.ORG_USER_FAILS.value(t.getOrgUserFails()),
					ORG_USERTABLE.ORG_USER_LOCKED.value(t.getOrgUserLocked()),
					ORG_USERTABLE.ORG_USER_DELETED.value(t.getOrgUserDeleted()));
				return insert;
			}
		});
	}

	public int edit(OrgUser orgUser) {
		if(orgUser == null || orgUser.getOrgUserId() == null){
			return 0;
		}
		return getDslTemplate().update(orgUser, new UpdateGenerateCallback<OrgUser>() {
			public Update generate(OrgUser t) {
				Update update = update(ORG_USERTABLE).set(
					ORG_USERTABLE.ORG_DEPT_ID.value(t.getOrgDeptId()),
					ORG_USERTABLE.ORG_USER_ACCOUNT.value(t.getOrgUserAccount()),
					ORG_USERTABLE.ORG_USER_PASSWORD.value(t.getOrgUserPassword()),
					ORG_USERTABLE.ORG_USER_ROLE.value(t.getOrgUserRole()),
					ORG_USERTABLE.ORG_USER_REALNAME.value(t.getOrgUserRealName()),
					ORG_USERTABLE.ORG_USER_NICKNAME.value(t.getOrgUserNickname()),
					ORG_USERTABLE.ORG_USER_SUBMITTER.value(t.getOrgUserSubmitter()),
					ORG_USERTABLE.ORG_USER_AVATAR.value(t.getOrgUserAvatar()),
					ORG_USERTABLE.ORG_USER_BIRTHDAY.value(t.getOrgUserBirthday()),
					ORG_USERTABLE.ORG_USER_GENDER.value(t.getOrgUserGender()),
					ORG_USERTABLE.ORG_USER_EMAIL.value(t.getOrgUserEmail()),
					ORG_USERTABLE.ORG_USER_SKYPE.value(t.getOrgUserSKYPE()),
					ORG_USERTABLE.ORG_USER_QQ.value(t.getOrgUserQQ()),
					ORG_USERTABLE.ORG_USER_YAHOO.value(t.getOrgUserYahoo()),
					ORG_USERTABLE.ORG_USER_GTALK.value(t.getOrgUserGTalk()),
					ORG_USERTABLE.ORG_USER_WANGWANG.value(t.getOrgUserWANGWANG()),
					ORG_USERTABLE.ORG_USER_MOBILE.value(t.getOrgUserMobile()),
					ORG_USERTABLE.ORG_USER_PHONE.value(t.getOrgUserPhone()),
					ORG_USERTABLE.ORG_USER_ADDRESS.value(t.getOrgUserAddress()),
					ORG_USERTABLE.ORG_USER_ZIPCODE.value(t.getOrgUserZipCode()),
					ORG_USERTABLE.ORG_USER_JOIN.value(t.getOrgUserJoin()),
					ORG_USERTABLE.ORG_USER_VISITS.value(t.getOrgUserVisits()),
					ORG_USERTABLE.ORG_USER_IP.value(t.getOrgUserIp()),
					ORG_USERTABLE.ORG_USER_LAST.value(t.getOrgUserLast()),
					ORG_USERTABLE.ORG_USER_FAILS.value(t.getOrgUserFails()),
					ORG_USERTABLE.ORG_USER_LOCKED.value(t.getOrgUserLocked()),
					ORG_USERTABLE.ORG_USER_DELETED.value(t.getOrgUserDeleted())).where(
					ORG_USERTABLE.ORG_USER_ID.eq(t.getOrgUserId()));
				return update;
			}
		});
	}

	public int deleteByKey(String pk){
		if(pk == null){
			return 0;
		}
		return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
			public Delete generate(Serializable pk) {
				return delete(ORG_USERTABLE).where(ORG_USERTABLE.ORG_USER_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(String... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(ORG_USERTABLE).where(ORG_USERTABLE.ORG_USER_ID.in(t));
		}
		},pks);
	}

	public List<OrgUser> query(OrgUser orgUser, OrderBy... orderBies) {
		return null;
	}

	public Pager<OrgUser> queryPager(int i, int i1, OrgUser orgUser, OrderBy... orderBies) {
		return null;
	}

	public OrgUser getByKey(String pk) {
		return getDslTemplate().getByKey(pk, OrgUser.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(ORG_USERTABLE).where(ORG_USERTABLE.ORG_USER_ID.eq(t));
			}
		});
	}

	public List<OrgUser> query(OrgUser orgUser, final OrderBy... orderBies) {
		if(orgUser==null){
			orgUser=new OrgUser();
		}
		return getDslTemplate().query(orgUser, new SelectGenerateCallback<OrgUser>() {

			@SuppressWarnings("rawtypes")
			public Select generate(OrgUser t) {
				Select select = selectFrom(ORG_USERTABLE).where(
				and(
					ORG_USERTABLE.ORG_DEPT_ID.eq(t.getOrgDeptId()),
					ORG_USERTABLE.ORG_USER_ACCOUNT.eq(t.getOrgUserAccount()),
					ORG_USERTABLE.ORG_USER_PASSWORD.eq(t.getOrgUserPassword()),
					ORG_USERTABLE.ORG_USER_ROLE.eq(t.getOrgUserRole()),
					ORG_USERTABLE.ORG_USER_REALNAME.eq(t.getOrgUserRealName()),
					ORG_USERTABLE.ORG_USER_NICKNAME.eq(t.getOrgUserNickname()),
					ORG_USERTABLE.ORG_USER_SUBMITTER.eq(t.getOrgUserSubmitter()),
					ORG_USERTABLE.ORG_USER_AVATAR.eq(t.getOrgUserAvatar()),
					ORG_USERTABLE.ORG_USER_BIRTHDAY.eq(t.getOrgUserBirthday()),
					ORG_USERTABLE.ORG_USER_GENDER.eq(t.getOrgUserGender()),
					ORG_USERTABLE.ORG_USER_EMAIL.eq(t.getOrgUserEmail()),
					ORG_USERTABLE.ORG_USER_SKYPE.eq(t.getOrgUserSKYPE()),
					ORG_USERTABLE.ORG_USER_QQ.eq(t.getOrgUserQQ()),
					ORG_USERTABLE.ORG_USER_YAHOO.eq(t.getOrgUserYahoo()),
					ORG_USERTABLE.ORG_USER_GTALK.eq(t.getOrgUserGTalk()),
					ORG_USERTABLE.ORG_USER_WANGWANG.eq(t.getOrgUserWANGWANG()),
					ORG_USERTABLE.ORG_USER_MOBILE.eq(t.getOrgUserMobile()),
					ORG_USERTABLE.ORG_USER_PHONE.eq(t.getOrgUserPhone()),
					ORG_USERTABLE.ORG_USER_ADDRESS.eq(t.getOrgUserAddress()),
					ORG_USERTABLE.ORG_USER_ZIPCODE.eq(t.getOrgUserZipCode()),
					ORG_USERTABLE.ORG_USER_JOIN.eq(t.getOrgUserJoin()),
					ORG_USERTABLE.ORG_USER_VISITS.eq(t.getOrgUserVisits()),
					ORG_USERTABLE.ORG_USER_IP.eq(t.getOrgUserIp()),
					ORG_USERTABLE.ORG_USER_LAST.eq(t.getOrgUserLast()),
					ORG_USERTABLE.ORG_USER_FAILS.eq(t.getOrgUserFails()),
					ORG_USERTABLE.ORG_USER_LOCKED.eq(t.getOrgUserLocked()),
					ORG_USERTABLE.ORG_USER_DELETED.eq(t.getOrgUserDeleted())));
				return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<OrgUser> queryPager(int start, int limit, OrgUser orgUser, final OrderBy... orderBies) {
		if(orgUser==null){
			orgUser=new OrgUser();
		}
		return getDslTemplate().queryPager(start, limit, orgUser, false, new SelectGenerateCallback<OrgUser>() {

			public Select generate(OrgUser t) {
				Select select = MysqlSelect.selectFrom(ORG_USERTABLE).where(
				and(
					ORG_USERTABLE.ORG_DEPT_ID.eq(t.getOrgDeptId()),
					ORG_USERTABLE.ORG_USER_ACCOUNT.eq(t.getOrgUserAccount()),
					ORG_USERTABLE.ORG_USER_PASSWORD.eq(t.getOrgUserPassword()),
					ORG_USERTABLE.ORG_USER_ROLE.eq(t.getOrgUserRole()),
					ORG_USERTABLE.ORG_USER_REALNAME.eq(t.getOrgUserRealName()),
					ORG_USERTABLE.ORG_USER_NICKNAME.eq(t.getOrgUserNickname()),
					ORG_USERTABLE.ORG_USER_SUBMITTER.eq(t.getOrgUserSubmitter()),
					ORG_USERTABLE.ORG_USER_AVATAR.eq(t.getOrgUserAvatar()),
					ORG_USERTABLE.ORG_USER_BIRTHDAY.eq(t.getOrgUserBirthday()),
					ORG_USERTABLE.ORG_USER_GENDER.eq(t.getOrgUserGender()),
					ORG_USERTABLE.ORG_USER_EMAIL.eq(t.getOrgUserEmail()),
					ORG_USERTABLE.ORG_USER_SKYPE.eq(t.getOrgUserSKYPE()),
					ORG_USERTABLE.ORG_USER_QQ.eq(t.getOrgUserQQ()),
					ORG_USERTABLE.ORG_USER_YAHOO.eq(t.getOrgUserYahoo()),
					ORG_USERTABLE.ORG_USER_GTALK.eq(t.getOrgUserGTalk()),
					ORG_USERTABLE.ORG_USER_WANGWANG.eq(t.getOrgUserWANGWANG()),
					ORG_USERTABLE.ORG_USER_MOBILE.eq(t.getOrgUserMobile()),
					ORG_USERTABLE.ORG_USER_PHONE.eq(t.getOrgUserPhone()),
					ORG_USERTABLE.ORG_USER_ADDRESS.eq(t.getOrgUserAddress()),
					ORG_USERTABLE.ORG_USER_ZIPCODE.eq(t.getOrgUserZipCode()),
					ORG_USERTABLE.ORG_USER_JOIN.eq(t.getOrgUserJoin()),
					ORG_USERTABLE.ORG_USER_VISITS.eq(t.getOrgUserVisits()),
					ORG_USERTABLE.ORG_USER_IP.eq(t.getOrgUserIp()),
					ORG_USERTABLE.ORG_USER_LAST.eq(t.getOrgUserLast()),
					ORG_USERTABLE.ORG_USER_FAILS.eq(t.getOrgUserFails()),
					ORG_USERTABLE.ORG_USER_LOCKED.eq(t.getOrgUserLocked()),
					ORG_USERTABLE.ORG_USER_DELETED.eq(t.getOrgUserDeleted())));
				return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<OrgUser> orgUsers) {
		if (CollectionUtil.isEmpty(orgUsers)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, orgUsers, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(ORG_USERTABLE).values(
					ORG_USERTABLE.ORG_DEPT_ID.value(new JdbcNamedParameter("orgDeptId")),
					ORG_USERTABLE.ORG_USER_ACCOUNT.value(new JdbcNamedParameter("orgUserAccount")),
					ORG_USERTABLE.ORG_USER_PASSWORD.value(new JdbcNamedParameter("orgUserPassword")),
					ORG_USERTABLE.ORG_USER_ROLE.value(new JdbcNamedParameter("orgUserRole")),
					ORG_USERTABLE.ORG_USER_REALNAME.value(new JdbcNamedParameter("orgUserRealName")),
					ORG_USERTABLE.ORG_USER_NICKNAME.value(new JdbcNamedParameter("orgUserNickname")),
					ORG_USERTABLE.ORG_USER_SUBMITTER.value(new JdbcNamedParameter("orgUserSubmitter")),
					ORG_USERTABLE.ORG_USER_AVATAR.value(new JdbcNamedParameter("orgUserAvatar")),
					ORG_USERTABLE.ORG_USER_BIRTHDAY.value(new JdbcNamedParameter("orgUserBirthday")),
					ORG_USERTABLE.ORG_USER_GENDER.value(new JdbcNamedParameter("orgUserGender")),
					ORG_USERTABLE.ORG_USER_EMAIL.value(new JdbcNamedParameter("orgUserEmail")),
					ORG_USERTABLE.ORG_USER_SKYPE.value(new JdbcNamedParameter("orgUserSKYPE")),
					ORG_USERTABLE.ORG_USER_QQ.value(new JdbcNamedParameter("orgUserQQ")),
					ORG_USERTABLE.ORG_USER_YAHOO.value(new JdbcNamedParameter("orgUserYahoo")),
					ORG_USERTABLE.ORG_USER_GTALK.value(new JdbcNamedParameter("orgUserGTalk")),
					ORG_USERTABLE.ORG_USER_WANGWANG.value(new JdbcNamedParameter("orgUserWANGWANG")),
					ORG_USERTABLE.ORG_USER_MOBILE.value(new JdbcNamedParameter("orgUserMobile")),
					ORG_USERTABLE.ORG_USER_PHONE.value(new JdbcNamedParameter("orgUserPhone")),
					ORG_USERTABLE.ORG_USER_ADDRESS.value(new JdbcNamedParameter("orgUserAddress")),
					ORG_USERTABLE.ORG_USER_ZIPCODE.value(new JdbcNamedParameter("orgUserZipCode")),
					ORG_USERTABLE.ORG_USER_JOIN.value(new JdbcNamedParameter("orgUserJoin")),
					ORG_USERTABLE.ORG_USER_VISITS.value(new JdbcNamedParameter("orgUserVisits")),
					ORG_USERTABLE.ORG_USER_IP.value(new JdbcNamedParameter("orgUserIp")),
					ORG_USERTABLE.ORG_USER_LAST.value(new JdbcNamedParameter("orgUserLast")),
					ORG_USERTABLE.ORG_USER_FAILS.value(new JdbcNamedParameter("orgUserFails")),
					ORG_USERTABLE.ORG_USER_LOCKED.value(new JdbcNamedParameter("orgUserLocked")),
					ORG_USERTABLE.ORG_USER_DELETED.value(new JdbcNamedParameter("orgUserDeleted")));
			}
		});
	}

	public int[] batchInsert(List<OrgUser> orgUsers){
			return batchInsert(true ,orgUsers);
	}

	public int[] batchUpdate(List<OrgUser> orgUsers) {
		if (CollectionUtil.isEmpty(orgUsers)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(orgUsers, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(ORG_USERTABLE).set(
					ORG_USERTABLE.ORG_DEPT_ID.value(new JdbcNamedParameter("orgDeptId")),
					ORG_USERTABLE.ORG_USER_ACCOUNT.value(new JdbcNamedParameter("orgUserAccount")),
					ORG_USERTABLE.ORG_USER_PASSWORD.value(new JdbcNamedParameter("orgUserPassword")),
					ORG_USERTABLE.ORG_USER_ROLE.value(new JdbcNamedParameter("orgUserRole")),
					ORG_USERTABLE.ORG_USER_REALNAME.value(new JdbcNamedParameter("orgUserRealName")),
					ORG_USERTABLE.ORG_USER_NICKNAME.value(new JdbcNamedParameter("orgUserNickname")),
					ORG_USERTABLE.ORG_USER_SUBMITTER.value(new JdbcNamedParameter("orgUserSubmitter")),
					ORG_USERTABLE.ORG_USER_AVATAR.value(new JdbcNamedParameter("orgUserAvatar")),
					ORG_USERTABLE.ORG_USER_BIRTHDAY.value(new JdbcNamedParameter("orgUserBirthday")),
					ORG_USERTABLE.ORG_USER_GENDER.value(new JdbcNamedParameter("orgUserGender")),
					ORG_USERTABLE.ORG_USER_EMAIL.value(new JdbcNamedParameter("orgUserEmail")),
					ORG_USERTABLE.ORG_USER_SKYPE.value(new JdbcNamedParameter("orgUserSKYPE")),
					ORG_USERTABLE.ORG_USER_QQ.value(new JdbcNamedParameter("orgUserQQ")),
					ORG_USERTABLE.ORG_USER_YAHOO.value(new JdbcNamedParameter("orgUserYahoo")),
					ORG_USERTABLE.ORG_USER_GTALK.value(new JdbcNamedParameter("orgUserGTalk")),
					ORG_USERTABLE.ORG_USER_WANGWANG.value(new JdbcNamedParameter("orgUserWANGWANG")),
					ORG_USERTABLE.ORG_USER_MOBILE.value(new JdbcNamedParameter("orgUserMobile")),
					ORG_USERTABLE.ORG_USER_PHONE.value(new JdbcNamedParameter("orgUserPhone")),
					ORG_USERTABLE.ORG_USER_ADDRESS.value(new JdbcNamedParameter("orgUserAddress")),
					ORG_USERTABLE.ORG_USER_ZIPCODE.value(new JdbcNamedParameter("orgUserZipCode")),
					ORG_USERTABLE.ORG_USER_JOIN.value(new JdbcNamedParameter("orgUserJoin")),
					ORG_USERTABLE.ORG_USER_VISITS.value(new JdbcNamedParameter("orgUserVisits")),
					ORG_USERTABLE.ORG_USER_IP.value(new JdbcNamedParameter("orgUserIp")),
					ORG_USERTABLE.ORG_USER_LAST.value(new JdbcNamedParameter("orgUserLast")),
					ORG_USERTABLE.ORG_USER_FAILS.value(new JdbcNamedParameter("orgUserFails")),
					ORG_USERTABLE.ORG_USER_LOCKED.value(new JdbcNamedParameter("orgUserLocked")),
					ORG_USERTABLE.ORG_USER_DELETED.value(new JdbcNamedParameter("orgUserDeleted"))).where(
				ORG_USERTABLE.ORG_USER_ID.eq(new JdbcNamedParameter("orgUserId")));
			}
		});
	}

	public int[] batchDelete(List<OrgUser> orgUsers) {
		if (CollectionUtil.isEmpty(orgUsers)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(orgUsers, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(ORG_USERTABLE).where(and(
				ORG_USERTABLE.ORG_USER_ID.eq(new JdbcNamedParameter("orgUserId")),
				ORG_USERTABLE.ORG_DEPT_ID.eq(new JdbcNamedParameter("orgDeptId")),
				ORG_USERTABLE.ORG_USER_ACCOUNT.eq(new JdbcNamedParameter("orgUserAccount")),
				ORG_USERTABLE.ORG_USER_PASSWORD.eq(new JdbcNamedParameter("orgUserPassword")),
				ORG_USERTABLE.ORG_USER_ROLE.eq(new JdbcNamedParameter("orgUserRole")),
				ORG_USERTABLE.ORG_USER_REALNAME.eq(new JdbcNamedParameter("orgUserRealName")),
				ORG_USERTABLE.ORG_USER_NICKNAME.eq(new JdbcNamedParameter("orgUserNickname")),
				ORG_USERTABLE.ORG_USER_SUBMITTER.eq(new JdbcNamedParameter("orgUserSubmitter")),
				ORG_USERTABLE.ORG_USER_AVATAR.eq(new JdbcNamedParameter("orgUserAvatar")),
				ORG_USERTABLE.ORG_USER_BIRTHDAY.eq(new JdbcNamedParameter("orgUserBirthday")),
				ORG_USERTABLE.ORG_USER_GENDER.eq(new JdbcNamedParameter("orgUserGender")),
				ORG_USERTABLE.ORG_USER_EMAIL.eq(new JdbcNamedParameter("orgUserEmail")),
				ORG_USERTABLE.ORG_USER_SKYPE.eq(new JdbcNamedParameter("orgUserSKYPE")),
				ORG_USERTABLE.ORG_USER_QQ.eq(new JdbcNamedParameter("orgUserQQ")),
				ORG_USERTABLE.ORG_USER_YAHOO.eq(new JdbcNamedParameter("orgUserYahoo")),
				ORG_USERTABLE.ORG_USER_GTALK.eq(new JdbcNamedParameter("orgUserGTalk")),
				ORG_USERTABLE.ORG_USER_WANGWANG.eq(new JdbcNamedParameter("orgUserWANGWANG")),
				ORG_USERTABLE.ORG_USER_MOBILE.eq(new JdbcNamedParameter("orgUserMobile")),
				ORG_USERTABLE.ORG_USER_PHONE.eq(new JdbcNamedParameter("orgUserPhone")),
				ORG_USERTABLE.ORG_USER_ADDRESS.eq(new JdbcNamedParameter("orgUserAddress")),
				ORG_USERTABLE.ORG_USER_ZIPCODE.eq(new JdbcNamedParameter("orgUserZipCode")),
				ORG_USERTABLE.ORG_USER_JOIN.eq(new JdbcNamedParameter("orgUserJoin")),
				ORG_USERTABLE.ORG_USER_VISITS.eq(new JdbcNamedParameter("orgUserVisits")),
				ORG_USERTABLE.ORG_USER_IP.eq(new JdbcNamedParameter("orgUserIp")),
				ORG_USERTABLE.ORG_USER_LAST.eq(new JdbcNamedParameter("orgUserLast")),
				ORG_USERTABLE.ORG_USER_FAILS.eq(new JdbcNamedParameter("orgUserFails")),
				ORG_USERTABLE.ORG_USER_LOCKED.eq(new JdbcNamedParameter("orgUserLocked")),
				ORG_USERTABLE.ORG_USER_DELETED.eq(new JdbcNamedParameter("orgUserDeleted"))));
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
}
