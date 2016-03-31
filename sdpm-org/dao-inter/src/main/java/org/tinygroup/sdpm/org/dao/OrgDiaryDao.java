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

package org.tinygroup.sdpm.org.dao;

import org.tinygroup.jdbctemplatedslsession.daosupport.BaseDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiary;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryAndUserDO;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public interface OrgDiaryDao extends BaseDao<OrgDiary, Integer> {

    List<OrgDiary> getByTimeAndUser(String orgUserId, Integer orgDiaryYear, Integer orgDiaryWeek);

    List<OrgDiary> findListByUser(List<String> list);

    Pager<OrgDiaryAndUserDO> findPagerByUserId(String orgUserId, Integer start, Integer limit);

    List<OrgDiaryAndUserDO> findListByUserId(String orgUserId);

    List<OrgDiary> findListOneWeek(Integer year, Integer week);

    List<OrgDiary> findSubordinateOneWeek(List<String> list, Integer year, Integer week);

    List<OrgUser> findUser(String userId, Integer year, Integer week);

    List<OrgDiary> findDiaryByUserLatest(String userId, Integer year, Integer week);

    Pager<OrgDiaryAndUserDO> findPagerSubordinateOneWeek(List<String> list, Integer year, Integer week, Integer start, Integer limit);

    List<OrgDiaryAndUserDO> findListSubordinateOneWeek(List<String> list, Integer year, Integer week);

    List<OrgDiaryAndUserDO> findListByWhiteList(String userId, Integer year, Integer week);

    Pager<OrgDiaryAndUserDO> findPagerByWhiteList(String userId, Integer year, Integer week, Integer start, Integer limit);

}
