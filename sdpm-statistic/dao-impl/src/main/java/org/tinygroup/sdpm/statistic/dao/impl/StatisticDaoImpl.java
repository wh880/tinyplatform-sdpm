package org.tinygroup.sdpm.statistic.dao.impl;

import org.springframework.stereotype.Repository;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.statistic.dao.StatisticDao;
import org.tinygroup.sdpm.statistic.dao.pojo.ProjectTaskSta;
import org.tinygroup.sdpm.statistic.dao.pojo.QualityBugSta;
import org.tinygroup.sdpm.statistic.dao.pojo.StatisticOrg;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.Join;
import org.tinygroup.tinysqldsl.selectitem.FragmentSelectItemSql;

import java.util.List;

import static org.tinygroup.sdpm.org.dao.constant.OrgUserTable.ORG_USERTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectTable.PROJECTTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectTaskTable.PROJECT_TASKTABLE;
import static org.tinygroup.sdpm.quality.dao.constant.QualityBugTable.QUALITY_BUGTABLE;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.product.dao.constant.ProductStoryTable.PRODUCT_STORYTABLE;
@Repository
public class StatisticDaoImpl extends TinyDslDaoSupport implements StatisticDao{

	public List<StatisticOrg> findList(StatisticOrg statisticOrg) {
		// TODO Auto-generated method stub
		 Select select = MysqlSelect.select(PROJECTTABLE.ALL,
	                FragmentSelectItemSql.fragmentSelect("SUM(project_task.task_estimate) as estimate"),
	                FragmentSelectItemSql.fragmentSelect("SUM(project_task.task_consumed) as consumed"),
	                FragmentSelectItemSql.fragmentSelect("SUM(project_task.task_consumed)/(SUM(project_task.task_consumed)+SUM(project_task.task_left)) as percent"),
	                FragmentSelectItemSql.fragmentSelect("SUM(project_task.task_id) as taskNum")
	        )
	                .from(PROJECTTABLE).join(
	                        Join.leftJoin(PROJECT_TASKTABLE,
	                                PROJECT_TASKTABLE.TASK_PROJECT.equal(PROJECTTABLE.PROJECT_ID)))
	                .where(PROJECTTABLE.PROJECT_ID.eq(statisticOrg.getProjectId()))
	                .groupBy(PROJECTTABLE.PROJECT_ID);
	        return getDslSession().fetchList(select, StatisticOrg.class);
	}

	public List<ProjectTaskSta> findProTasks(ProjectTaskSta projectTaskSta) {
		if(projectTaskSta.getAssignedTo()==null){
			return null;
		}
		Select select = MysqlSelect.select(FragmentSelectItemSql.fragmentSelect("project_task.task_id AS taskId," +
				"project.project_name AS projectName," +
				"project_task.task_assigned_to AS assignedTo," +
				"SUM(project_task.task_id) AS taskNum," +
				"SUM(project_task.task_estimate) AS estimate," +
				"SUM(project_task.task_left) AS 'left'")).from(PROJECT_TASKTABLE).join(Join.leftJoin(PROJECTTABLE,
				PROJECT_TASKTABLE.TASK_PROJECT.eq(PROJECTTABLE.PROJECT_ID)),Join.leftJoin(ORG_USERTABLE,
				PROJECT_TASKTABLE.TASK_ASSIGNED_TO.eq(ORG_USERTABLE.ORG_USER_ID))).where(and(PROJECT_TASKTABLE.TASK_ASSIGNED_TO.eq(
				projectTaskSta.getAssignedTo()),
				PROJECT_TASKTABLE.TASK_ASSIGNED_DATE.eq(projectTaskSta.getAssignedDate()),
				ORG_USERTABLE.ORG_USER_ID.eq(projectTaskSta.getAssignedTo()))
		).groupBy(PROJECTTABLE.PROJECT_ID);
		return getDslSession().fetchList(select,ProjectTaskSta.class);
	}
	public List<QualityBugSta> findBugCreate(QualityBugSta qualityBugSta){
		if (qualityBugSta.getUserId()==null){
			return null;
		}
		Select select = MysqlSelect.select(FragmentSelectItemSql.fragmentSelect("org_user.org_user_id AS userId," +
				"SUM( CASE WHEN quality_bug.bug_status = 1 THEN 1 ELSE 0 END ) repeatBug,"+
				"SUM(CASE WHEN quality_bug.bug_status = 2 THEN	1	ELSE	0	END	) designEd,"+
				"SUM(CASE WHEN quality_bug.bug_status = 3 THEN	1	ELSE	0	END	) externalCause,"+
				"SUM(CASE WHEN quality_bug.bug_status = 4 THEN	1	ELSE	0	END	) solved,"+
				"SUM(CASE WHEN quality_bug.bug_status = 5 THEN	1	ELSE	0	END	) notReproduce,"+
				"SUM(CASE WHEN quality_bug.bug_status = 6 THEN	1	ELSE	0	END	) delayResolved,"+
				"SUM(CASE WHEN quality_bug.bug_status = 7 THEN	1	ELSE	0	END	) notResolved,"+
				"COUNT(product_story.story_from_bug) AS toStroy")).from(QUALITY_BUGTABLE).join(Join.leftJoin(
				ORG_USERTABLE,ORG_USERTABLE.ORG_USER_ID.eq(QUALITY_BUGTABLE.BUG_OPENED_BY)
		),Join.leftJoin(PRODUCT_STORYTABLE,PRODUCT_STORYTABLE.STORY_FROM_BUG.eq(QUALITY_BUGTABLE.BUG_ID))).where(and(
				QUALITY_BUGTABLE.BUG_OPENED_DATE.eq(qualityBugSta.getBugCreateDate()),
				QUALITY_BUGTABLE.PRODUCT_ID.eq(qualityBugSta.getProductId()),
				QUALITY_BUGTABLE.PROJECT_ID.eq(qualityBugSta.getProjectId()))).groupBy(ORG_USERTABLE.ORG_USER_ID);
		return getDslSession().fetchList(select,QualityBugSta.class);
	}



}
