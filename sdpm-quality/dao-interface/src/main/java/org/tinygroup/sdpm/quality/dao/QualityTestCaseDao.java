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

package org.tinygroup.sdpm.quality.dao;

import java.util.List;

import org.tinygroup.jdbctemplatedslsession.daosupport.BaseDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;

public interface QualityTestCaseDao extends BaseDao<QualityTestCase,Integer> {
	
	Pager<QualityTestCase> queryPagerRel(int start,int limit ,QualityTestCase qualityTestCase , final String condition,final OrderBy... orderArgs);

	Pager<QualityTestCase> queryStoryChangedCase(int start, int limit , QualityTestCase qualityTestCase , Condition condition, final OrderBy... orderArgs);

	Integer softDelete(Integer id);
	
	List<Integer> getStoryIds(QualityTestCase t);

	Integer getMaxNo(Integer productId);
}
