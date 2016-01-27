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

package org.tinygroup.sdpm.system.dao;


import org.tinygroup.jdbctemplatedslsession.daosupport.BaseDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;

import java.util.Date;
import java.util.List;

public interface SystemActionDao extends BaseDao<SystemAction, Integer> {
    SystemAction getActionAndObject(SystemAction systemAction);

    Pager<SystemAction> queryPager(int start, int limit, Condition dateCondition, SystemAction systemAction, final OrderBy... orderBies);

    Pager<SystemAction> findByDate(int start, int limit, SystemAction action, String startDate, String endDate, OrderBy... orderArgs);

    /**
     * 通过一组系统日志Id查找相对应的系统日志
     *
     * @param idList
     * @return
     */
    List<SystemAction> findActionListByIdList(List<Integer> idList);


    List<SystemAction> findActionListByIdListAndType(List<String> bugList, List<String> taskList, List<String> storyList);

    /**
     * 查询某一用户于某一个时间段内的系统日志信息（周报所需信息任务的创建、完成
     * 需求的创建、完成
     * bug的创建、完成）
     *
     * @param userId
     * @param beginDate
     * @param endDate
     * @return
     */
    List<SystemAction> findActionListByUserIdAndDate(String userId, Date beginDate, Date endDate);
}
