package org.tinygroup.sdpm.statistic.dao.impl;

import org.springframework.stereotype.Repository;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.statistic.dao.StatisticDao;
import org.tinygroup.sdpm.statistic.dao.pojo.ProjectTaskSta;
import org.tinygroup.sdpm.statistic.dao.pojo.StatisticOrg;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.base.Alias;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.Join;
import org.tinygroup.tinysqldsl.selectitem.FragmentSelectItemSql;

import java.util.List;

import static org.tinygroup.sdpm.project.dao.constant.ProjectTable.PROJECTTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectTaskTable.PROJECT_TASKTABLE;
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
		PROJECT_TASKTABLE.setAlias(new Alias("a"));
		PROJECTTABLE.setAlias(new Alias("b"));
		if(projectTaskSta.getAssignedTo()==null){
			return null;
		}
		Select select = MysqlSelect.select(FragmentSelectItemSql.fragmentSelect("a.task_id AS taskId," +
				"b.project_name AS projectName," +
				"  a.task_assigned_to AS assignedTo," +
				"SUM(a.task_id) AS taskNum," +
				"SUM(a.task_estimate) AS estimate," +
				"SUM(a.task_left) AS 'left'")).from(PROJECT_TASKTABLE).join(Join.leftJoin(PROJECTTABLE,
				PROJECT_TASKTABLE.TASK_PROJECT.eq(PROJECTTABLE.PROJECT_ID))).where(PROJECT_TASKTABLE.TASK_ASSIGNED_TO.eq(
				projectTaskSta.getAssignedTo())).groupBy(PROJECTTABLE.PROJECT_ID);
		return getDslSession().fetchList(select,ProjectTaskSta.class);
	}


}
