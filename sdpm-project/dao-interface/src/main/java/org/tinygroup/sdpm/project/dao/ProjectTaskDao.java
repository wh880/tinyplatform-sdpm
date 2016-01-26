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

package org.tinygroup.sdpm.project.dao;

import org.tinygroup.jdbctemplatedslsession.daosupport.BaseDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.dao.pojo.TaskChartBean;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;

import java.util.List;

public interface ProjectTaskDao extends BaseDao<ProjectTask, Integer> {

    /**
     * 统计项目相关的任务工时信息
     *
     * @param projectId
     * @return
     */
    ProjectTask getProjectTaskTimeInfo(Integer projectId);

    /**
     * 获取项目最大编号
     *
     * @param projectId
     * @return
     */
    Integer getMaxNo(Integer projectId);

    Pager<ProjectTask> queryPagerByStatus(int start, int limit, ProjectTask projectTask, final Condition condition, final OrderBy... orderBies);

    /**
     * 由我解决
     *
     * @param start
     * @param limit
     * @param projectTask
     * @param user
     * @param orderBies
     * @return
     */
    Pager<ProjectTask> queryPagerByMe(int start, int limit, ProjectTask projectTask, final OrgUser user, final OrderBy... orderBies);

    /**
     * 根据storyid相关任务数量
     *
     * @param storyId
     * @return
     */
    Integer getSumByStory(Integer storyId);


    ProjectTask findTaskStory(Integer taskId);

    /**
     * 根据指派进行分类
     *
     * @return
     */
    List<TaskChartBean> queryChartAssigned();

    /**
     * 根据任务状态进行分类
     *
     * @return
     */
    List<TaskChartBean> queryChartStatus();

    /**
     * 根据优先级进行分类
     *
     * @return
     */
    List<TaskChartBean> queryChartPri();

    /**
     * 根据截至日期进行分类
     *
     * @return
     */
    List<TaskChartBean> queryChartDeadLine();

    /**
     * 根据模块进行分类
     *
     * @return
     */
    List<TaskChartBean> queryChartModule();

    /**
     * 根据项目进行分类
     *
     * @return
     */
    List<TaskChartBean> queryChartProject();

    /**
     * 根据任务类型进行分类
     *
     * @return
     */
    List<TaskChartBean> queryChartType();

    /**
     * 根据完成者分类
     *
     * @return
     */
    List<TaskChartBean> queryChartFinishedBy();

    /**
     * 根据产品id查询待关联的bug list
     */
    List<QualityBug> findRelationBugByProjectID(Integer projectId);

    ProjectTask findTaskByTaskId(Integer taskId);

}
