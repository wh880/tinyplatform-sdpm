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

package org.tinygroup.sdpm.system.wrapper;

import java.util.List;
import java.util.UUID;

import org.tinygroup.cepcore.CEPCore;
import org.tinygroup.context.Context;
import org.tinygroup.context.impl.ContextImpl;
import org.tinygroup.event.Event;
import org.tinygroup.event.Parameter;
import org.tinygroup.event.ServiceInfo;
import org.tinygroup.event.ServiceRequest;

public class EffortServiceImplWrapper implements org.tinygroup.sdpm.system.service.inter.EffortService {

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

	public java.util.List<org.tinygroup.sdpm.system.dao.pojo.SystemEffort> findByDate(java.util.Date date) {
		String serviceId = "system_findByDate";

		try{
			Context context = new ContextImpl();
			context.put("date" ,date);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.system.dao.pojo.SystemEffort save(org.tinygroup.sdpm.system.dao.pojo.SystemEffort systemEffort) {
		String serviceId = "system_save";

		try{
			Context context = new ContextImpl();
			context.put("systemEffort" ,systemEffort);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.system.dao.pojo.SystemEffort> findByAccount(java.lang.String account) {
		String serviceId = "system_findByAccount";

		try{
			Context context = new ContextImpl();
			context.put("account" ,account);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.system.dao.pojo.SystemEffort> find(org.tinygroup.sdpm.system.dao.pojo.SystemEffort systemEffort) {
		String serviceId = "system_find";

		try{
			Context context = new ContextImpl();
			context.put("systemEffort" ,systemEffort);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.system.dao.pojo.SystemEffort> findBetweenDate(java.util.Date begindate ,java.util.Date enddate) {
		String serviceId = "system_findBetweenDate";

		try{
			Context context = new ContextImpl();
			context.put("begindate" ,begindate);
			context.put("enddate" ,enddate);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.system.dao.pojo.SystemEffort> findByProject(int projectId) {
		String serviceId = "system_findByProject";

		try{
			Context context = new ContextImpl();
			context.put("projectId" ,projectId);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.tinysqldsl.Pager<org.tinygroup.sdpm.system.dao.pojo.SystemEffort> findByPage(int start ,int limit ,org.tinygroup.sdpm.system.dao.pojo.SystemEffort SystemEffort, String sortName, boolean asc) {
		String serviceId = "system_findByPage";

		try{
			Context context = new ContextImpl();
			context.put("start" ,start);
			context.put("limit" ,limit);
			context.put("SystemEffort" ,SystemEffort);

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