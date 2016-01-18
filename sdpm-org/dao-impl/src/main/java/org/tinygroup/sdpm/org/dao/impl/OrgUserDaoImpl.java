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

package org.tinygroup.sdpm.org.dao.impl;

import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.org.dao.OrgUserDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
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

import static org.tinygroup.sdpm.org.dao.constant.OrgDeptTable.ORG_DEPTTABLE;
import static org.tinygroup.sdpm.org.dao.constant.OrgUserTable.ORG_USERTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectTeamTable.PROJECT_TEAMTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.select;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.or;
import static org.tinygroup.tinysqldsl.formitem.SubSelect.subSelect;

@Repository
public class OrgUserDaoImpl extends TinyDslDaoSupport implements OrgUserDao {

    public OrgUser add(OrgUser orgUser) {
        return getDslTemplate().insertAndReturnKey(orgUser, new InsertGenerateCallback<OrgUser>() {
            public Insert generate(OrgUser t) {
                Insert insert = insertInto(ORG_USERTABLE).values(
                        ORG_USERTABLE.ORG_DEPT_ID.value(t.getOrgDeptId()),
                        ORG_USERTABLE.ORG_USER_ACCOUNT.value(t.getOrgUserAccount()),
                        ORG_USERTABLE.ORG_USER_PASSWORD.value(t.getOrgUserPassword()),
                        ORG_USERTABLE.ORG_USER_ROLE.value(t.getOrgUserRole()),
                        ORG_USERTABLE.ORG_USER_LEADER.value(t.getOrgUserLeader()),
                        ORG_USERTABLE.ORG_USER_REAL_NAME.value(t.getOrgUserRealName()),
                        ORG_USERTABLE.ORG_USER_NICK_NAME.value(t.getOrgUserNickName()),
                        ORG_USERTABLE.ORG_USER_SUBMITTER.value(t.getOrgUserSubmitter()),
                        ORG_USERTABLE.ORG_USER_AVATAR.value(t.getOrgUserAvatar()),
                        ORG_USERTABLE.ORG_USER_BIRTHDAY.value(t.getOrgUserBirthday()),
                        ORG_USERTABLE.ORG_USER_GENDER.value(t.getOrgUserGender()),
                        ORG_USERTABLE.ORG_USER_EMAIL.value(t.getOrgUserEmail()),
                        ORG_USERTABLE.ORG_USER_SKYPE.value(t.getOrgUserSkype()),
                        ORG_USERTABLE.ORG_USER_Q_Q.value(t.getOrgUserQQ()),
                        ORG_USERTABLE.ORG_USER_YAHOO.value(t.getOrgUserYahoo()),
                        ORG_USERTABLE.ORG_USER_G_TALK.value(t.getOrgUserGTalk()),
                        ORG_USERTABLE.ORG_USER_WANG_WANG.value(t.getOrgUserWangWang()),
                        ORG_USERTABLE.ORG_USER_MOBILE.value(t.getOrgUserMobile()),
                        ORG_USERTABLE.ORG_USER_PHONE.value(t.getOrgUserPhone()),
                        ORG_USERTABLE.ORG_USER_ADDRESS.value(t.getOrgUserAddress()),
                        ORG_USERTABLE.ORG_USER_ZIP_CODE.value(t.getOrgUserZipCode()),
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
        if (orgUser == null || orgUser.getOrgUserId() == null) {
            return 0;
        }
        return getDslTemplate().update(orgUser, new UpdateGenerateCallback<OrgUser>() {
            public Update generate(OrgUser t) {
                Update update = update(ORG_USERTABLE).set(
                        ORG_USERTABLE.ORG_DEPT_ID.value(t.getOrgDeptId()),
                        ORG_USERTABLE.ORG_USER_ACCOUNT.value(t.getOrgUserAccount()),
                        ORG_USERTABLE.ORG_USER_PASSWORD.value(t.getOrgUserPassword()),
                        ORG_USERTABLE.ORG_USER_ROLE.value(t.getOrgUserRole()),
                        ORG_USERTABLE.ORG_USER_LEADER.value(t.getOrgUserLeader()),
                        ORG_USERTABLE.ORG_USER_REAL_NAME.value(t.getOrgUserRealName()),
                        ORG_USERTABLE.ORG_USER_NICK_NAME.value(t.getOrgUserNickName()),
                        ORG_USERTABLE.ORG_USER_SUBMITTER.value(t.getOrgUserSubmitter()),
                        ORG_USERTABLE.ORG_USER_AVATAR.value(t.getOrgUserAvatar()),
                        ORG_USERTABLE.ORG_USER_BIRTHDAY.value(t.getOrgUserBirthday()),
                        ORG_USERTABLE.ORG_USER_GENDER.value(t.getOrgUserGender()),
                        ORG_USERTABLE.ORG_USER_EMAIL.value(t.getOrgUserEmail()),
                        ORG_USERTABLE.ORG_USER_SKYPE.value(t.getOrgUserSkype()),
                        ORG_USERTABLE.ORG_USER_Q_Q.value(t.getOrgUserQQ()),
                        ORG_USERTABLE.ORG_USER_YAHOO.value(t.getOrgUserYahoo()),
                        ORG_USERTABLE.ORG_USER_G_TALK.value(t.getOrgUserGTalk()),
                        ORG_USERTABLE.ORG_USER_WANG_WANG.value(t.getOrgUserWangWang()),
                        ORG_USERTABLE.ORG_USER_MOBILE.value(t.getOrgUserMobile()),
                        ORG_USERTABLE.ORG_USER_PHONE.value(t.getOrgUserPhone()),
                        ORG_USERTABLE.ORG_USER_ADDRESS.value(t.getOrgUserAddress()),
                        ORG_USERTABLE.ORG_USER_ZIP_CODE.value(t.getOrgUserZipCode()),
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

    public int deleteByKey(String pk) {
        if (pk == null) {
            return 0;
        }
        return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
            public Delete generate(Serializable pk) {
                return delete(ORG_USERTABLE).where(ORG_USERTABLE.ORG_USER_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(String... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(ORG_USERTABLE).where(ORG_USERTABLE.ORG_USER_ID.in(t));
            }
        }, pks);
    }

    public OrgUser getByKey(String pk) {
        return getDslTemplate().getByKey(pk, OrgUser.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(ORG_USERTABLE).where(ORG_USERTABLE.ORG_USER_ID.eq(t));
            }
        });
    }

    public List<OrgUser> getByKeys(String... pk) {
        SelectGenerateCallback<Serializable[]> callback = new SelectGenerateCallback<Serializable[]>() {
            public Select generate(Serializable[] t) {
                return selectFrom(ORG_USERTABLE).where(ORG_USERTABLE.ORG_USER_ID.in(t));
            }

        };
        Select select = callback.generate(pk);
        return getDslSession().fetchList(select, OrgUser.class);
    }

    public List<OrgUser> getDirectStaffByLeader(String leaderUserId) {
        Select select = selectFrom(ORG_USERTABLE).where(
                and(
                        ORG_USERTABLE.ORG_USER_LEADER.eq(leaderUserId),
                        ORG_USERTABLE.ORG_USER_DELETED.eq(OrgUser.DELETE_NO)
                ));
        return getDslSession().fetchList(select, OrgUser.class);
    }

    @Override
    public List<OrgUser> getDirectStaffByLeaderAndSelf(String leaderUserId) {
        ComplexSelect complexSelect = ComplexSelect.union(selectFrom(ORG_USERTABLE).
                where(ORG_USERTABLE.ORG_USER_ID.eq(leaderUserId)),
                selectFrom(ORG_USERTABLE).where(
                and(
                        ORG_USERTABLE.ORG_USER_LEADER.eq(leaderUserId),
                        ORG_USERTABLE.ORG_USER_DELETED.eq(OrgUser.DELETE_NO)
                )));
        return getDslSession().fetchList(complexSelect, OrgUser.class);
    }

    public List<OrgUser> getTeamUserByProjectId(Integer projectId) {
        Select select = selectFrom(ORG_USERTABLE).where(
                and(ORG_USERTABLE.ORG_USER_ID.inExpression(
                        subSelect(
                                select(PROJECT_TEAMTABLE.TEAM_USER_ID).from(PROJECT_TEAMTABLE)
                                        .where(PROJECT_TEAMTABLE.PROJECT_ID.eq(projectId))
                        )
                ), ORG_USERTABLE.ORG_USER_DELETED.eq(OrgUser.DELETE_NO)));
        return getDslSession().fetchList(select, OrgUser.class);
    }

    public List<OrgUser> userInCondition(String condition, Integer limit, String... ids) {
        Condition con = null;
        if (ids != null) {
            String[] sId = ids.length == 0 ? new String[]{"0"} : ids;
            con = ORG_USERTABLE.ORG_USER_ID.in(sId);
        }
        Select select = MysqlSelect.select(ORG_USERTABLE.ORG_USER_ID,
                FragmentSql.fragmentSelect("CONCAT (org_user_real_name,'-',org_user_account,CASE WHEN org_dept_name IS NOT NULL THEN '-' ELSE ''END,org_dept_name) as orgUserRealName")).
                from(ORG_USERTABLE).join(
                Join.leftJoin(ORG_DEPTTABLE, ORG_DEPTTABLE.ORG_DEPT_ID.eq(ORG_USERTABLE.ORG_DEPT_ID))
        ).where(and(ORG_USERTABLE.ORG_USER_DELETED.eq(OrgUser.DELETE_NO), con,
                or(ORG_USERTABLE.ORG_USER_REAL_NAME.like(condition),
                        ORG_USERTABLE.ORG_USER_ACCOUNT.like(condition),
                        ORG_DEPTTABLE.ORG_DEPT_NAME.like(condition)))).limit(0, limit);
        return getDslSession().fetchList(select, OrgUser.class);
    }

    public Pager<OrgUser> getPagerByDeptId(int start, int limit, final Integer deptId, final OrderBy... orderBies) {
        Select select = MysqlSelect.selectFrom(ORG_USERTABLE).where(
                and(
                        ORG_USERTABLE.ORG_DEPT_ID.eq(deptId),
                        ORG_USERTABLE.ORG_USER_DELETED.eq(OrgUser.DELETE_NO)));
        return getDslSession().fetchPage(select, start, limit, false, OrgUser.class);
    }

    public List<OrgUser> query(OrgUser orgUser, final OrderBy... orderBies) {
        if (orgUser == null) {
            orgUser = new OrgUser();
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
                                ORG_USERTABLE.ORG_USER_REAL_NAME.eq(t.getOrgUserRealName()),
                                ORG_USERTABLE.ORG_USER_NICK_NAME.eq(t.getOrgUserNickName()),
                                ORG_USERTABLE.ORG_USER_SUBMITTER.eq(t.getOrgUserSubmitter()),
                                ORG_USERTABLE.ORG_USER_AVATAR.eq(t.getOrgUserAvatar()),
                                ORG_USERTABLE.ORG_USER_BIRTHDAY.eq(t.getOrgUserBirthday()),
                                ORG_USERTABLE.ORG_USER_GENDER.eq(t.getOrgUserGender()),
                                ORG_USERTABLE.ORG_USER_EMAIL.eq(t.getOrgUserEmail()),
                                ORG_USERTABLE.ORG_USER_LEADER.eq(t.getOrgUserLeader()),
                                ORG_USERTABLE.ORG_USER_SKYPE.eq(t.getOrgUserSkype()),
                                ORG_USERTABLE.ORG_USER_Q_Q.eq(t.getOrgUserQQ()),
                                ORG_USERTABLE.ORG_USER_YAHOO.eq(t.getOrgUserYahoo()),
                                ORG_USERTABLE.ORG_USER_G_TALK.eq(t.getOrgUserGTalk()),
                                ORG_USERTABLE.ORG_USER_WANG_WANG.eq(t.getOrgUserWangWang()),
                                ORG_USERTABLE.ORG_USER_MOBILE.eq(t.getOrgUserMobile()),
                                ORG_USERTABLE.ORG_USER_PHONE.eq(t.getOrgUserPhone()),
                                ORG_USERTABLE.ORG_USER_ADDRESS.eq(t.getOrgUserAddress()),
                                ORG_USERTABLE.ORG_USER_ZIP_CODE.eq(t.getOrgUserZipCode()),
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
        if (orgUser == null) {
            orgUser = new OrgUser();
        }
        return getDslTemplate().queryPager(start, limit, orgUser, false, new SelectGenerateCallback<OrgUser>() {

            public Select generate(OrgUser t) {
                Select select = MysqlSelect.selectFrom(ORG_USERTABLE).where(
                        and(
                                ORG_USERTABLE.ORG_DEPT_ID.eq(t.getOrgDeptId()),
                                ORG_USERTABLE.ORG_USER_ACCOUNT.eq(t.getOrgUserAccount()),
                                ORG_USERTABLE.ORG_USER_PASSWORD.eq(t.getOrgUserPassword()),
                                ORG_USERTABLE.ORG_USER_ROLE.eq(t.getOrgUserRole()),
                                ORG_USERTABLE.ORG_USER_REAL_NAME.eq(t.getOrgUserRealName()),
                                ORG_USERTABLE.ORG_USER_NICK_NAME.eq(t.getOrgUserNickName()),
                                ORG_USERTABLE.ORG_USER_SUBMITTER.eq(t.getOrgUserSubmitter()),
                                ORG_USERTABLE.ORG_USER_AVATAR.eq(t.getOrgUserAvatar()),
                                ORG_USERTABLE.ORG_USER_LEADER.eq(t.getOrgUserLeader()),
                                ORG_USERTABLE.ORG_USER_BIRTHDAY.eq(t.getOrgUserBirthday()),
                                ORG_USERTABLE.ORG_USER_GENDER.eq(t.getOrgUserGender()),
                                ORG_USERTABLE.ORG_USER_EMAIL.eq(t.getOrgUserEmail()),
                                ORG_USERTABLE.ORG_USER_SKYPE.eq(t.getOrgUserSkype()),
                                ORG_USERTABLE.ORG_USER_Q_Q.eq(t.getOrgUserQQ()),
                                ORG_USERTABLE.ORG_USER_YAHOO.eq(t.getOrgUserYahoo()),
                                ORG_USERTABLE.ORG_USER_G_TALK.eq(t.getOrgUserGTalk()),
                                ORG_USERTABLE.ORG_USER_WANG_WANG.eq(t.getOrgUserWangWang()),
                                ORG_USERTABLE.ORG_USER_MOBILE.eq(t.getOrgUserMobile()),
                                ORG_USERTABLE.ORG_USER_PHONE.eq(t.getOrgUserPhone()),
                                ORG_USERTABLE.ORG_USER_ADDRESS.eq(t.getOrgUserAddress()),
                                ORG_USERTABLE.ORG_USER_ZIP_CODE.eq(t.getOrgUserZipCode()),
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


    public int[] batchInsert(boolean autoGeneratedKeys, List<OrgUser> orgUsers) {
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
                        ORG_USERTABLE.ORG_USER_REAL_NAME.value(new JdbcNamedParameter("orgUserRealName")),
                        ORG_USERTABLE.ORG_USER_NICK_NAME.value(new JdbcNamedParameter("orgUserNickName")),
                        ORG_USERTABLE.ORG_USER_SUBMITTER.value(new JdbcNamedParameter("orgUserSubmitter")),
                        ORG_USERTABLE.ORG_USER_AVATAR.value(new JdbcNamedParameter("orgUserAvatar")),
                        ORG_USERTABLE.ORG_USER_BIRTHDAY.value(new JdbcNamedParameter("orgUserBirthday")),
                        ORG_USERTABLE.ORG_USER_LEADER.value(new JdbcNamedParameter("orgUserLeader")),
                        ORG_USERTABLE.ORG_USER_GENDER.value(new JdbcNamedParameter("orgUserGender")),
                        ORG_USERTABLE.ORG_USER_EMAIL.value(new JdbcNamedParameter("orgUserEmail")),
                        ORG_USERTABLE.ORG_USER_SKYPE.value(new JdbcNamedParameter("orgUserSkype")),
                        ORG_USERTABLE.ORG_USER_Q_Q.value(new JdbcNamedParameter("orgUserQQ")),
                        ORG_USERTABLE.ORG_USER_YAHOO.value(new JdbcNamedParameter("orgUserYahoo")),
                        ORG_USERTABLE.ORG_USER_G_TALK.value(new JdbcNamedParameter("orgUserGTalk")),
                        ORG_USERTABLE.ORG_USER_WANG_WANG.value(new JdbcNamedParameter("orgUserWangWang")),
                        ORG_USERTABLE.ORG_USER_MOBILE.value(new JdbcNamedParameter("orgUserMobile")),
                        ORG_USERTABLE.ORG_USER_PHONE.value(new JdbcNamedParameter("orgUserPhone")),
                        ORG_USERTABLE.ORG_USER_ADDRESS.value(new JdbcNamedParameter("orgUserAddress")),
                        ORG_USERTABLE.ORG_USER_ZIP_CODE.value(new JdbcNamedParameter("orgUserZipCode")),
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

    public int[] batchInsert(List<OrgUser> orgUsers) {
        return batchInsert(true, orgUsers);
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
                        ORG_USERTABLE.ORG_USER_REAL_NAME.value(new JdbcNamedParameter("orgUserRealName")),
                        ORG_USERTABLE.ORG_USER_LEADER.value(new JdbcNamedParameter("orgUserLeader")),
                        ORG_USERTABLE.ORG_USER_NICK_NAME.value(new JdbcNamedParameter("orgUserNickName")),
                        ORG_USERTABLE.ORG_USER_SUBMITTER.value(new JdbcNamedParameter("orgUserSubmitter")),
                        ORG_USERTABLE.ORG_USER_AVATAR.value(new JdbcNamedParameter("orgUserAvatar")),
                        ORG_USERTABLE.ORG_USER_BIRTHDAY.value(new JdbcNamedParameter("orgUserBirthday")),
                        ORG_USERTABLE.ORG_USER_GENDER.value(new JdbcNamedParameter("orgUserGender")),
                        ORG_USERTABLE.ORG_USER_EMAIL.value(new JdbcNamedParameter("orgUserEmail")),
                        ORG_USERTABLE.ORG_USER_SKYPE.value(new JdbcNamedParameter("orgUserSkype")),
                        ORG_USERTABLE.ORG_USER_Q_Q.value(new JdbcNamedParameter("orgUserQQ")),
                        ORG_USERTABLE.ORG_USER_YAHOO.value(new JdbcNamedParameter("orgUserYahoo")),
                        ORG_USERTABLE.ORG_USER_G_TALK.value(new JdbcNamedParameter("orgUserGTalk")),
                        ORG_USERTABLE.ORG_USER_WANG_WANG.value(new JdbcNamedParameter("orgUserWangWang")),
                        ORG_USERTABLE.ORG_USER_MOBILE.value(new JdbcNamedParameter("orgUserMobile")),
                        ORG_USERTABLE.ORG_USER_PHONE.value(new JdbcNamedParameter("orgUserPhone")),
                        ORG_USERTABLE.ORG_USER_ADDRESS.value(new JdbcNamedParameter("orgUserAddress")),
                        ORG_USERTABLE.ORG_USER_ZIP_CODE.value(new JdbcNamedParameter("orgUserZipCode")),
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


    public int[] softDeleteBatch(List<OrgUser> list) {
        if (CollectionUtil.isEmpty(list)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(list, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(ORG_USERTABLE).set(
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
                        ORG_USERTABLE.ORG_USER_REAL_NAME.eq(new JdbcNamedParameter("orgUserRealName")),
                        ORG_USERTABLE.ORG_USER_NICK_NAME.eq(new JdbcNamedParameter("orgUserNickName")),
                        ORG_USERTABLE.ORG_USER_SUBMITTER.eq(new JdbcNamedParameter("orgUserSubmitter")),
                        ORG_USERTABLE.ORG_USER_AVATAR.eq(new JdbcNamedParameter("orgUserAvatar")),
                        ORG_USERTABLE.ORG_USER_LEADER.eq(new JdbcNamedParameter("orgUserLeader")),
                        ORG_USERTABLE.ORG_USER_BIRTHDAY.eq(new JdbcNamedParameter("orgUserBirthday")),
                        ORG_USERTABLE.ORG_USER_GENDER.eq(new JdbcNamedParameter("orgUserGender")),
                        ORG_USERTABLE.ORG_USER_EMAIL.eq(new JdbcNamedParameter("orgUserEmail")),
                        ORG_USERTABLE.ORG_USER_SKYPE.eq(new JdbcNamedParameter("orgUserSkype")),
                        ORG_USERTABLE.ORG_USER_Q_Q.eq(new JdbcNamedParameter("orgUserQQ")),
                        ORG_USERTABLE.ORG_USER_YAHOO.eq(new JdbcNamedParameter("orgUserYahoo")),
                        ORG_USERTABLE.ORG_USER_G_TALK.eq(new JdbcNamedParameter("orgUserGTalk")),
                        ORG_USERTABLE.ORG_USER_WANG_WANG.eq(new JdbcNamedParameter("orgUserWangWang")),
                        ORG_USERTABLE.ORG_USER_MOBILE.eq(new JdbcNamedParameter("orgUserMobile")),
                        ORG_USERTABLE.ORG_USER_PHONE.eq(new JdbcNamedParameter("orgUserPhone")),
                        ORG_USERTABLE.ORG_USER_ADDRESS.eq(new JdbcNamedParameter("orgUserAddress")),
                        ORG_USERTABLE.ORG_USER_ZIP_CODE.eq(new JdbcNamedParameter("orgUserZipCode")),
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

    public String getNameById(String id) {
        if (StringUtil.isBlank(id)) {
            return "";
        }
        try {
            Select select = select(ORG_USERTABLE.ORG_USER_ACCOUNT).from(ORG_USERTABLE).where(ORG_USERTABLE.ORG_USER_ID.eq(id));
            return getDslSession().fetchOneResult(select, String.class);
        } catch (Exception e) {
            return "";
        }
    }

    public List<OrgUser> getListById(List<String> list) {
        Select select = Select.selectFrom(ORG_USERTABLE).where(
                ORG_USERTABLE.ORG_USER_ID.in(list.toArray())
        );
        return getDslSession().fetchList(select, OrgUser.class);
    }
}
