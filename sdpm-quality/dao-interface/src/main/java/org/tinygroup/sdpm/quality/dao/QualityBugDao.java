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

package org.tinygroup.sdpm.quality.dao;

import org.tinygroup.jdbctemplatedslsession.daosupport.BaseDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.quality.dao.pojo.BugCount;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;

import java.util.List;

public interface QualityBugDao extends BaseDao<QualityBug, Integer> {

    Integer softDelete(Integer id);

    Pager<QualityBug> queryPager(int start, int limit, final Condition conditions, QualityBug qualityBug, final OrderBy... orderArgs);

    int[] batchUpdateDel(List<QualityBug> ids);

    List<BugCount> getCount(String code, Integer productId);

    BugCount getBugsNotInType(String type, Integer productId);

    Pager<QualityBug> queryStoryChangedBugs(int start, int limit, Condition conditions, QualityBug qualityBug, final OrderBy... orderArgs);

    Pager<QualityBug> queryBugPage(int start, int limit, Integer[] ids, final OrderBy... orderArgs);

    Integer getMaxNo(Integer productId);

    Integer deleteBugsByProduct(Integer product);

    Integer deleteBugsByStory(Integer storyId);

    Integer deleteBugsByTask(Integer taskId);

    List<QualityBug> getBugsInReleaseDoc(QualityBug bug);

    List<QualityBug> bugInCondition(String condition, Integer limit, Integer productId);

    QualityBug findBugByBugId(Integer bugId);

    List<QualityBug> findBugByProductIdAndBugTitle(String bugTitle, Integer productId,String status);

}
