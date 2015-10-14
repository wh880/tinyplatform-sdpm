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

package org.tinygroup.sdpm.project.dao;

import org.tinygroup.jdbctemplatedslsession.daosupport.BaseDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.tinysqldsl.Pager;

public interface ProjectTaskDao extends BaseDao<ProjectTask, Integer> {

    public Integer batchSoftDel(String condition);

    public Pager<ProjectTask> queryPagerByStuta(int start, int limit, ProjectTask projectTask, final String condition, final OrderBy... orderBies);

    /**
     * 根据storyid相关任务数量
     *
     * @param storyId
     * @return
     */
    public Integer getSumByStory(Integer storyId);

    Integer editTask(ProjectTask task);

    Integer editcall(ProjectTask task);

    Integer editfinish(ProjectTask task);

    Integer editstart(ProjectTask task);

    Integer editclose(ProjectTask task);

    ProjectTask findTaskStory(Integer taskId);

    public Integer updateColum(ProjectTask projectTask);
}
