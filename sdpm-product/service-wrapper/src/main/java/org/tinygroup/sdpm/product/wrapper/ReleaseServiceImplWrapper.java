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

package org.tinygroup.sdpm.product.wrapper;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.cepcore.CEPCore;
import org.tinygroup.context.Context;
import org.tinygroup.context.impl.ContextImpl;
import org.tinygroup.event.Event;
import org.tinygroup.event.Parameter;
import org.tinygroup.event.ServiceInfo;
import org.tinygroup.event.ServiceRequest;
@Component
public class ReleaseServiceImplWrapper implements org.tinygroup.sdpm.product.service.ReleaseService {
@Autowired
	CEPCore cepcore;

	public CEPCore getCore() {
		return cepcore;
	}

	public void setCore(CEPCore cepcore) {
		this.cepcore = cepcore;
	}

	private Event getEvent(String serviceId,Context context) throws Exception{
		Event event = new Event();
		event.setEventId(UUID.randomUUID().toString());
		ServiceRequest serviceRequest = new ServiceRequest();
		serviceRequest.setContext(context);
		serviceRequest.setServiceId(serviceId);
		event.setServiceRequest(serviceRequest);
		return event;
	}

	public org.tinygroup.sdpm.product.dao.pojo.ProductRelease addRelease(org.tinygroup.sdpm.product.dao.pojo.ProductRelease release) {
		String serviceId = "product_addRelease";

		try{
			Context context = new ContextImpl();
			context.put("release" ,release);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int updateRelease(org.tinygroup.sdpm.product.dao.pojo.ProductRelease release) {
		String serviceId = "product_updateRelease";

		try{
			Context context = new ContextImpl();
			context.put("release" ,release);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int[] updateBatchRelease(java.util.List<org.tinygroup.sdpm.product.dao.pojo.ProductRelease> releases) {
		String serviceId = "product_updateBatchRelease";

		try{
			Context context = new ContextImpl();
			context.put("releases" ,releases);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int deleteRelease(java.lang.Integer releaseId) {
		String serviceId = "product_deleteRelease";

		try{
			Context context = new ContextImpl();
			context.put("releaseId" ,releaseId);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.product.dao.pojo.ProductRelease> findReleaseList(org.tinygroup.sdpm.product.dao.pojo.ProductRelease release ,java.lang.String columnName ,boolean asc) {
		String serviceId = "product_findReleaseList";

		try{
			Context context = new ContextImpl();
			context.put("release" ,release);
			context.put("columnName" ,columnName);
			context.put("asc" ,asc);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.tinysqldsl.Pager<org.tinygroup.sdpm.product.dao.pojo.ProductRelease> findReleasePager(int start ,int limit ,org.tinygroup.sdpm.product.dao.pojo.ProductRelease release ,java.lang.String columnName ,boolean asc) {
		String serviceId = "product_findReleasePager";

		try{
			Context context = new ContextImpl();
			context.put("start" ,start);
			context.put("limit" ,limit);
			context.put("release" ,release);
			context.put("columnName" ,columnName);
			context.put("asc" ,asc);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	private <T> T callServiceAndCallBack(String serviceId,Context context) throws Exception{
		Event event = getEvent(serviceId,context);
		cepcore.process(event);
		ServiceInfo info = cepcore.getServiceInfo(serviceId);
		List<Parameter> resultsParam = info.getResults();
		if (resultsParam==null||resultsParam.size() == 0) {
			return null;
	}
		return event.getServiceRequest().getContext()
			.get(resultsParam.get(0).getName());
	}

}