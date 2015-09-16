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

package org.tinygroup.sdpm.project.dao.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.project.dao.constant.TeamTable.*;
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
import org.tinygroup.sdpm.project.dao.pojo.Team;
import org.tinygroup.sdpm.project.dao.TeamDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class TeamDaoImpl extends TinyDslDaoSupport implements TeamDao {

	public Team add(Team team) {
		return getDslTemplate().insertAndReturnKey(team, new InsertGenerateCallback<Team>() {
			public Insert generate(Team t) {
				Insert insert = insertInto(TEAMTABLE).values(
					TEAMTABLE.ID.value(t.getId()),
					TEAMTABLE.PROJECT_ID.value(t.getProjectId()),
					TEAMTABLE.TEAM_ACCOUNT.value(t.getTeamAccount()),
					TEAMTABLE.TEAM_ROLE.value(t.getTeamRole()),
					TEAMTABLE.TEAM_JOIN.value(t.getTeamJoin()),
					TEAMTABLE.TEAM_DAYS.value(t.getTeamDays()),
					TEAMTABLE.TEAM_HOURS.value(t.getTeamHours()));
				return insert;
			}
		});
	}

	public int edit(Team team) {
		if(team == null || team.getId() == null){
			return 0;
		}
		return getDslTemplate().update(team, new UpdateGenerateCallback<Team>() {
			public Update generate(Team t) {
				Update update = update(TEAMTABLE).set(
					TEAMTABLE.PROJECT_ID.value(t.getProjectId()),
					TEAMTABLE.TEAM_ACCOUNT.value(t.getTeamAccount()),
					TEAMTABLE.TEAM_ROLE.value(t.getTeamRole()),
					TEAMTABLE.TEAM_JOIN.value(t.getTeamJoin()),
					TEAMTABLE.TEAM_DAYS.value(t.getTeamDays()),
					TEAMTABLE.TEAM_HOURS.value(t.getTeamHours())).where(
					TEAMTABLE.ID.eq(t.getId()));
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
				return delete(TEAMTABLE).where(TEAMTABLE.ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(TEAMTABLE).where(TEAMTABLE.ID.in(t));
		}
		},pks);
	}

	public Team getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Team.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(TEAMTABLE).where(TEAMTABLE.ID.eq(t));
			}
		});
	}

	public List<Team> query(Team team) {
		if(team==null){
			team=new Team();
		}
		return getDslTemplate().query(team, new SelectGenerateCallback<Team>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Team t) {
				return selectFrom(TEAMTABLE).where(
				and(
					TEAMTABLE.PROJECT_ID.eq(t.getProjectId()),
					TEAMTABLE.TEAM_ACCOUNT.eq(t.getTeamAccount()),
					TEAMTABLE.TEAM_ROLE.eq(t.getTeamRole()),
					TEAMTABLE.TEAM_JOIN.eq(t.getTeamJoin()),
					TEAMTABLE.TEAM_DAYS.eq(t.getTeamDays()),
					TEAMTABLE.TEAM_HOURS.eq(t.getTeamHours())));
			}
		});
	}

	public Pager<Team> queryPager(int start,int limit ,Team team) {
		if(team==null){
			team=new Team();
		}
		return getDslTemplate().queryPager(start, limit, team, false, new SelectGenerateCallback<Team>() {

			public Select generate(Team t) {
				return MysqlSelect.selectFrom(TEAMTABLE).where(
				and(
					TEAMTABLE.PROJECT_ID.eq(t.getProjectId()),
					TEAMTABLE.TEAM_ACCOUNT.eq(t.getTeamAccount()),
					TEAMTABLE.TEAM_ROLE.eq(t.getTeamRole()),
					TEAMTABLE.TEAM_JOIN.eq(t.getTeamJoin()),
					TEAMTABLE.TEAM_DAYS.eq(t.getTeamDays()),
					TEAMTABLE.TEAM_HOURS.eq(t.getTeamHours())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Team> teams) {
		if (CollectionUtil.isEmpty(teams)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, teams, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(TEAMTABLE).values(
					TEAMTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
					TEAMTABLE.TEAM_ACCOUNT.value(new JdbcNamedParameter("teamAccount")),
					TEAMTABLE.TEAM_ROLE.value(new JdbcNamedParameter("teamRole")),
					TEAMTABLE.TEAM_JOIN.value(new JdbcNamedParameter("teamJoin")),
					TEAMTABLE.TEAM_DAYS.value(new JdbcNamedParameter("teamDays")),
					TEAMTABLE.TEAM_HOURS.value(new JdbcNamedParameter("teamHours")));
			}
		});
	}

	public int[] batchInsert(List<Team> teams){
			return batchInsert(true ,teams);
	}

	public int[] batchUpdate(List<Team> teams) {
		if (CollectionUtil.isEmpty(teams)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(teams, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(TEAMTABLE).set(
					TEAMTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
					TEAMTABLE.TEAM_ACCOUNT.value(new JdbcNamedParameter("teamAccount")),
					TEAMTABLE.TEAM_ROLE.value(new JdbcNamedParameter("teamRole")),
					TEAMTABLE.TEAM_JOIN.value(new JdbcNamedParameter("teamJoin")),
					TEAMTABLE.TEAM_DAYS.value(new JdbcNamedParameter("teamDays")),
					TEAMTABLE.TEAM_HOURS.value(new JdbcNamedParameter("teamHours"))).where(
				TEAMTABLE.ID.eq(new JdbcNamedParameter("id")));
			}
		});
	}

	public int[] batchDelete(List<Team> teams) {
		if (CollectionUtil.isEmpty(teams)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(teams, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(TEAMTABLE).where(and(
				TEAMTABLE.ID.eq(new JdbcNamedParameter("id")),
				TEAMTABLE.PROJECT_ID.eq(new JdbcNamedParameter("projectId")),
				TEAMTABLE.TEAM_ACCOUNT.eq(new JdbcNamedParameter("teamAccount")),
				TEAMTABLE.TEAM_ROLE.eq(new JdbcNamedParameter("teamRole")),
				TEAMTABLE.TEAM_JOIN.eq(new JdbcNamedParameter("teamJoin")),
				TEAMTABLE.TEAM_DAYS.eq(new JdbcNamedParameter("teamDays")),
				TEAMTABLE.TEAM_HOURS.eq(new JdbcNamedParameter("teamHours"))));
			}
		});
	}

}
