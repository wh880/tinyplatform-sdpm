/**
 * Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 * <p/>
 * Licensed under the GPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.gnu.org/licenses/gpl.html
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tinygroup.sdpm.service.dao;

import org.tinygroup.jdbctemplatedslsession.daosupport.BaseDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.service.dao.pojo.ServiceRequest;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface ServiceRequestDao extends BaseDao<ServiceRequest, Integer> {
    Integer close(ServiceRequest clientRequest);

    Integer softDelete(Integer id);
    Integer saveReply(ServiceRequest clientRequest);

    Pager<ServiceRequest> queryPagerBy(int start, int limit, ServiceRequest t, Integer statues, OrderBy... orderArgs);

    int[] batchUpdateReply(List<ServiceRequest> list);
    Integer changeStatus(Integer id);

    int[] batchUpdateReview(List<ServiceRequest> list);

    int[] softDeleteBatch(List<ServiceRequest> list);
}
