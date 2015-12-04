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

import org.tinygroup.cepcore.CEPCore;
import org.tinygroup.context.Context;
import org.tinygroup.context.impl.ContextImpl;
import org.tinygroup.event.Event;
import org.tinygroup.event.Parameter;
import org.tinygroup.event.ServiceInfo;
import org.tinygroup.event.ServiceRequest;

import java.util.List;
import java.util.UUID;

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

	public org.tinygroup.sdpm.system.dao.pojo.SystemModule editModule(org.tinygroup.sdpm.system.dao.pojo.SystemModule systemModule) {
		String serviceId = "editModule";

		try{
			Context context = new ContextImpl();
			context.put("systemModule" ,systemModule);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.lang.Integer deleteModuleById(java.lang.Integer id) {
		String serviceId = "deleteModuleById";

		try{
			Context context = new ContextImpl();
			context.put("id" ,id);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.lang.Integer delete(org.tinygroup.sdpm.system.dao.pojo.SystemModule systemModule) {
		String serviceId = "delete";

		try{
			Context context = new ContextImpl();
			context.put("systemModule" ,systemModule);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.system.dao.pojo.SystemModule findById(java.lang.Integer id) {
		String serviceId = "findById";

		try{
			Context context = new ContextImpl();
			context.put("id" ,id);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.system.dao.pojo.SystemModule> findAllModules(org.tinygroup.sdpm.system.dao.pojo.SystemModule systemModule) {
		String serviceId = "findAllModules";

		try{
			Context context = new ContextImpl();
			context.put("systemModule" ,systemModule);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.system.dao.pojo.SystemModule addSystemModule(org.tinygroup.sdpm.system.dao.pojo.SystemModule systemModule) {
		String serviceId = "addSystemModule";

		try{
			Context context = new ContextImpl();
			context.put("systemModule" ,systemModule);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.system.dao.pojo.SystemModule editNameAndTitle(org.tinygroup.sdpm.system.dao.pojo.SystemModule systemModule) {
		String serviceId = "editNameAndTitle";

		try{
			Context context = new ContextImpl();
			context.put("systemModule" ,systemModule);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.lang.Integer batchDeleteSystemModule(java.lang.Integer[] ids) {
		String serviceId = "batchDeleteSystemModule";

		try{
			Context context = new ContextImpl();
			context.put("ids" ,ids);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.system.dao.pojo.SystemModule> findModuleList(org.tinygroup.sdpm.system.dao.pojo.SystemModule systemModule) {
		String serviceId = "findModuleList";

		try{
			Context context = new ContextImpl();
			context.put("systemModule" ,systemModule);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.lang.Integer deleteSystemModuleByType(java.lang.String type) {
		String serviceId = "deleteSystemModuleByType";

		try{
			Context context = new ContextImpl();
			context.put("type" ,type);

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