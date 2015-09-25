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

package org.tinygroup.sdpm.quality.wrapper;

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

@Component("bugService")
public class BugServiceImplWrapper implements org.tinygroup.sdpm.quality.service.inter.BugService {
	
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

	public java.util.List<org.tinygroup.sdpm.quality.dao.pojo.QualityBug> findBugList(org.tinygroup.sdpm.quality.dao.pojo.QualityBug bug) {
		String serviceId = "quality_findBugList";

		try{
			Context context = new ContextImpl();
			context.put("bug" ,bug);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.quality.dao.pojo.QualityBug addBug(org.tinygroup.sdpm.quality.dao.pojo.QualityBug bug) {
		String serviceId = "quality_addBug";

		try{
			Context context = new ContextImpl();
			context.put("bug" ,bug);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.quality.dao.pojo.QualityBug findById(int id) {
		String serviceId = "quality_findById";

		try{
			Context context = new ContextImpl();
			context.put("id" ,id);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int updateBug(org.tinygroup.sdpm.quality.dao.pojo.QualityBug bug) {
		String serviceId = "quality_updateBug";

		try{
			Context context = new ContextImpl();
			context.put("bug" ,bug);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int[] batchUpdateBug(java.util.List<org.tinygroup.sdpm.quality.dao.pojo.QualityBug> bugs) {
		String serviceId = "quality_batchUpdateBug";

		try{
			Context context = new ContextImpl();
			context.put("bugs" ,bugs);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.tinysqldsl.Pager findBugListPager(java.lang.Integer start ,java.lang.Integer limit ,org.tinygroup.sdpm.quality.dao.pojo.QualityBug bug ,java.lang.String sortName ,boolean asc) {
		String serviceId = "quality_findBugListPager";

		try{
			Context context = new ContextImpl();
			context.put("start" ,start);
			context.put("limit" ,limit);
			context.put("bug" ,bug);
			context.put("sortName" ,sortName);
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