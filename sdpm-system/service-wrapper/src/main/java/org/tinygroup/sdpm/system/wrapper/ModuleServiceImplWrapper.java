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

public class ModuleServiceImplWrapper implements org.tinygroup.sdpm.system.service.inter.ModuleService {

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

	public org.tinygroup.sdpm.system.dao.pojo.SystemModule edit(org.tinygroup.sdpm.system.dao.pojo.SystemModule systemModule) {
		String serviceId = "module_edit";

		try{
			Context context = new ContextImpl();
			context.put("systemModule" ,systemModule);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int deleteById(int id) {
		String serviceId = "module_deleteById";

		try{
			Context context = new ContextImpl();
			context.put("id" ,id);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int delete(org.tinygroup.sdpm.system.dao.pojo.SystemModule systemModule) {
		String serviceId = "module_delete";

		try{
			Context context = new ContextImpl();
			context.put("systemModule" ,systemModule);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.system.dao.pojo.SystemModule findById(int id) {
		String serviceId = "module_findById";

		try{
			Context context = new ContextImpl();
			context.put("id" ,id);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.system.dao.pojo.SystemModule> findModules(org.tinygroup.sdpm.system.dao.pojo.SystemModule systemModule) {
		String serviceId = "module_findModules";

		try{
			Context context = new ContextImpl();
			context.put("systemModule" ,systemModule);

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