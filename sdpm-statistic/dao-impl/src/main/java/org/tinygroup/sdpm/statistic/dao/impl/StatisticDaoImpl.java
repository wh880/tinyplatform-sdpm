package org.tinygroup.sdpm.statistic.dao.impl;

import org.springframework.stereotype.Repository;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.statistic.dao.StatisticDao;
import org.tinygroup.sdpm.statistic.dao.pojo.*;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.expression.relational.ExistsExpression;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.formitem.SubSelect;
import org.tinygroup.tinysqldsl.select.Join;
import org.tinygroup.tinysqldsl.selectitem.FragmentSelectItemSql;

import java.util.Date;
import java.util.List;

import static org.tinygroup.sdpm.org.dao.constant.OrgRoleUserTable.ORG_ROLE_USERTABLE;
import static org.tinygroup.sdpm.org.dao.constant.OrgUserTable.ORG_USERTABLE;
import static org.tinygroup.sdpm.product.dao.constant.ProductStoryTable.PRODUCT_STORYTABLE;
import static org.tinygroup.sdpm.product.dao.constant.ProductTable.PRODUCTTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectProductTable.PROJECT_PRODUCTTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectTable.PROJECTTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectTaskTable.PROJECT_TASKTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectTeamTable.PROJECT_TEAMTABLE;
import static org.tinygroup.sdpm.quality.dao.constant.QualityBugTable.QUALITY_BUGTABLE;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.or;

@Repository
public class StatisticDaoImpl extends TinyDslDaoSupport implements StatisticDao {

    public List<StatisticOrg> findList(StatisticOrg statisticOrg) {
        Select select = MysqlSelect.select(PROJECTTABLE.PROJECT_ID, PROJECTTABLE.PROJECT_NAME,
                FragmentSelectItemSql.fragmentSelect("SUM(project_task.task_estimate) as estimate"),
                FragmentSelectItemSql.fragmentSelect("SUM(project_task.task_consumed) as consumed"),
                FragmentSelectItemSql.fragmentSelect("SUM(project_task.task_consumed)/(SUM(project_task.task_consumed)+SUM(project_task.task_left)) as percent"),
                FragmentSelectItemSql.fragmentSelect("SUM(project_task.task_id) as taskNum")
        )
                .from(PROJECTTABLE).join(
                        Join.leftJoin(PROJECT_TASKTABLE,
                                PROJECT_TASKTABLE.TASK_PROJECT.equal(PROJECTTABLE.PROJECT_ID)))
                .where(PROJECTTABLE.PROJECT_ID.eq(statisticOrg.getProjectId()))
                .groupBy(PROJECTTABLE.PROJECT_ID, PROJECTTABLE.PROJECT_NAME);
        return getDslSession().fetchList(select, StatisticOrg.class);
    }

    /**
     * 每个项目任务的任务数
     *
     * @param projectTaskSta
     * @return
     */
    public List<ProjectTaskSta> findProTasks(ProjectTaskSta projectTaskSta, Date startDate, Date endDate, Integer roleId) {
        Condition condition = null;
        if (startDate != null && endDate != null) {
            condition = and(PROJECT_TASKTABLE.TASK_ASSIGNED_DATE.gte(startDate), PROJECT_TASKTABLE.TASK_ASSIGNED_DATE.lte(endDate));
        }
        if (roleId != null) {
            condition = condition != null ? and(condition, ORG_ROLE_USERTABLE.ORG_ROLE_ID.eq(roleId)) : ORG_ROLE_USERTABLE.ORG_ROLE_ID.eq(roleId);
        }
        if (projectTaskSta.getAssignedTo() == null) {
            return null;
        }
        Select select = MysqlSelect.select(PROJECTTABLE.PROJECT_ID, FragmentSelectItemSql.fragmentSelect("project_task.task_id AS taskId," +
                "project.project_name AS projectName," +
                "project_task.task_assigned_to AS assignedTo," +
                "COUNT(project_task.task_id) AS taskNum," +
                "SUM(project_task.task_estimate) AS estimate," +
                "SUM(project_task.task_left) AS 'left'")).from(PROJECT_TASKTABLE).join(Join.leftJoin(PROJECTTABLE,
                PROJECT_TASKTABLE.TASK_PROJECT.eq(PROJECTTABLE.PROJECT_ID)), Join.leftJoin(ORG_ROLE_USERTABLE, PROJECT_TASKTABLE.TASK_ASSIGNED_TO.eq(ORG_ROLE_USERTABLE.ORG_USER_ID))).where(
                and(
                        condition,
                        PROJECT_TASKTABLE.TASK_ASSIGNED_TO.eq(projectTaskSta.getAssignedTo()))
        ).groupBy(PROJECTTABLE.PROJECT_ID, PROJECT_TASKTABLE.TASK_ID);
        return getDslSession().fetchList(select, ProjectTaskSta.class);
    }

    /**
     * Bug创建表
     *
     * @param qualityBugSta
     * @return
     */
    public List<QualityBugSta> findBugCreate(QualityBugSta qualityBugSta, Date startDate, Date endDate, Integer cProject, Integer cProduct) {
        Condition condition = null;
        if (startDate != null && endDate != null) {
            condition = and(QUALITY_BUGTABLE.BUG_OPENED_DATE.gte(startDate), QUALITY_BUGTABLE.BUG_OPENED_DATE.lte(endDate));
        }
        if (cProject != null) {
            condition = condition != null ? and(condition, QUALITY_BUGTABLE.PROJECT_ID.eq(cProject)) : QUALITY_BUGTABLE.PROJECT_ID.eq(cProject);
        }
        if (cProduct != null) {
            condition = condition != null ? and(condition, QUALITY_BUGTABLE.PRODUCT_ID.eq(cProduct)) : QUALITY_BUGTABLE.PRODUCT_ID.eq(cProduct);
        }
        if (qualityBugSta == null) {
            qualityBugSta = new QualityBugSta();
        }
        Select select = MysqlSelect.select(FragmentSelectItemSql.fragmentSelect("org_user.org_user_id AS userId," +
                "SUM( CASE WHEN quality_bug.bug_resolution = 1 THEN 1 ELSE 0 END ) repeatBug," +
                "SUM(CASE WHEN quality_bug.bug_resolution = 2 THEN	1 ELSE 0 END	) designEd," +
                "SUM(CASE WHEN quality_bug.bug_resolution = 3 THEN	1 ELSE 0 END	) externalCause," +
                "SUM(CASE WHEN quality_bug.bug_resolution = 4 THEN	1 ELSE 0 END	) solved," +
                "SUM(CASE WHEN quality_bug.bug_resolution = 5 THEN	1 ELSE 0 END	) notReproduce," +
                "SUM(CASE WHEN quality_bug.bug_resolution = 6 THEN	1 ELSE 0 END	) delayResolved," +
                "SUM(CASE WHEN quality_bug.bug_resolution = 7 THEN	1 ELSE 0 END	) notResolved," +
                "COUNT(product_story.story_from_bug) AS toStory," +
                " COUNT(quality_bug.bug_id) AS bugNum")).from(QUALITY_BUGTABLE).join(Join.leftJoin(
                ORG_USERTABLE, ORG_USERTABLE.ORG_USER_ID.eq(QUALITY_BUGTABLE.BUG_OPENED_BY)
        ), Join.leftJoin(PRODUCT_STORYTABLE, PRODUCT_STORYTABLE.STORY_FROM_BUG.eq(QUALITY_BUGTABLE.BUG_ID))).where(and(
                condition,
                QUALITY_BUGTABLE.BUG_OPENED_DATE.eq(qualityBugSta.getBugCreateDate()),
                QUALITY_BUGTABLE.PRODUCT_ID.eq(qualityBugSta.getProductId()),
                QUALITY_BUGTABLE.PROJECT_ID.eq(qualityBugSta.getProjectId()))).groupBy(ORG_USERTABLE.ORG_USER_ID);
        return getDslSession().fetchList(select, QualityBugSta.class);
    }

    /**
     * bug指派表
     *
     * @param qualityBugCall
     * @return
     */
    public List<QualityBugCall> findBugCall(QualityBugCall qualityBugCall) {
        if (qualityBugCall.getUserId() == null) {
            return null;
        }
        Select select = MysqlSelect.select(QUALITY_BUGTABLE.BUG_ASSIGNED_TO, FragmentSelectItemSql.fragmentSelect("product.product_name As 'productName'," +
                "COUNT(quality_bug.product_id) AS 'productBugNum'")).from(PRODUCTTABLE).join(
                Join.leftJoin(QUALITY_BUGTABLE, QUALITY_BUGTABLE.PRODUCT_ID.eq(PRODUCTTABLE.PRODUCT_ID)),
                Join.leftJoin(ORG_USERTABLE, QUALITY_BUGTABLE.BUG_ASSIGNED_TO.eq(ORG_USERTABLE.ORG_USER_ID))).where(
                ORG_USERTABLE.ORG_USER_ID.eq(qualityBugCall.getUserId())).groupBy(QUALITY_BUGTABLE.BUG_ASSIGNED_TO, PRODUCTTABLE.PRODUCT_NAME);
        return getDslSession().fetchList(select, QualityBugCall.class);
    }

    /**
     * 被指派人和
     *
     * @param assigned
     * @return
     */
    public List<Assigned> findAssigned(Assigned assigned) {
        if (assigned == null) {
            assigned = new Assigned();
        }
        Select select = MysqlSelect.select(QUALITY_BUGTABLE.BUG_ASSIGNED_TO, FragmentSelectItemSql.fragmentSelect("org_user.org_user_id As 'userId'," +
                "COUNT(quality_bug.bug_id) As 'bugNum'")).from(ORG_USERTABLE).join(Join.leftJoin(
                QUALITY_BUGTABLE, ORG_USERTABLE.ORG_USER_ID.eq(QUALITY_BUGTABLE.BUG_ASSIGNED_TO))).where(
                ORG_USERTABLE.ORG_USER_ID.eq(assigned.getUserId())).groupBy(QUALITY_BUGTABLE.BUG_ASSIGNED_TO, ORG_USERTABLE.ORG_USER_ID);
        return getDslSession().fetchList(select, Assigned.class);
    }

    /**
     * 产品投入表
     *
     * @param productProject
     * @return
     */
    public List<ProductProject> productProjects(ProductProject productProject, boolean deleted, String userId) {
        Integer delete = 0;
        if (deleted) {
            delete = 1;
        }
        if (productProject == null) {
            productProject = new ProductProject();
        }
        Select select = MysqlSelect.select(PRODUCTTABLE.PRODUCT_ID, FragmentSelectItemSql.fragmentSelect(
                "count(distinct(project_product.project_id)) As projectNum ,sum(project_task.task_consumed) As consumedSum"),
                PRODUCTTABLE.PRODUCT_NAME.as("productName")).from(PRODUCTTABLE).join(
                Join.leftJoin(PROJECT_PRODUCTTABLE, PROJECT_PRODUCTTABLE.PRODUCT_ID.eq(PRODUCTTABLE.PRODUCT_ID)),
                Join.leftJoin(PROJECT_TASKTABLE, PROJECT_TASKTABLE.TASK_PROJECT.eq(PROJECT_PRODUCTTABLE.PROJECT_ID))).where(and(PRODUCTTABLE.DELETED.eq(delete),
                or(PRODUCTTABLE.ACL.eq(Product.ACl_All),
                        or(PRODUCTTABLE.PRODUCT_OWNER.eq(userId), PRODUCTTABLE.PRODUCT_DELIVERY_MANAGER.eq(userId), PRODUCTTABLE.PRODUCT_QUALITY_MANAGER.eq(userId), ExistsExpression.existsCondition(SubSelect.subSelect(selectFrom(PROJECT_TEAMTABLE).
                                where(and(PRODUCTTABLE.ACL.eq(Product.ACl_TEAM), PROJECT_TEAMTABLE.PRODUCT_ID.eq(PRODUCTTABLE.PRODUCT_ID), PROJECT_TEAMTABLE.TEAM_USER_ID.eq(userId))))))))).
                groupBy(PRODUCTTABLE.PRODUCT_ID);
        return getDslSession().fetchList(select, ProductProject.class);
    }


}
