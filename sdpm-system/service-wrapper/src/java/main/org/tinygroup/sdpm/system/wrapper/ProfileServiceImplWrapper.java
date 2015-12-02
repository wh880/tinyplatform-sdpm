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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.cepcore.CEPCore;
import org.tinygroup.context.Context;
import org.tinygroup.context.impl.ContextImpl;
import org.tinygroup.event.Event;
import org.tinygroup.event.Parameter;
import org.tinygroup.event.ServiceInfo;
import org.tinygroup.event.ServiceRequest;

import java.util.List;
import java.util.UUID;
@Service("profileService")
public class ProfileServiceImplWrapper implements org.tinygroup.sdpm.system.service.inter.ProfileService {

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

	public org.tinygroup.sdpm.system.dao.pojo.SystemProfile addSystemProfile(org.tinygroup.sdpm.system.dao.pojo.SystemProfile systemProfile) {
		String serviceId = "addSystemProfile";

		try{
			Context context = new ContextImpl();
			context.put("systemProfile" ,systemProfile);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.lang.Integer batchAddSystemProfile(java.util.List<org.tinygroup.sdpm.system.dao.pojo.SystemProfile> systemProfiles) {
		String serviceId = "batchAddSystemProfile";

		try{
			Context context = new ContextImpl();
			context.put("systemProfiles" ,systemProfiles);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.lang.Integer batchUpdateSystemProfile(java.util.List<org.tinygroup.sdpm.system.dao.pojo.SystemProfile> list) {
		String serviceId = "batchUpdateSystemProfile";

		try{
			Context context = new ContextImpl();
			context.put("list" ,list);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.system.dao.pojo.SystemProfile> findSystemProfile(org.tinygroup.sdpm.system.dao.pojo.SystemProfile systemProfile) {
		String serviceId = "findSystemProfile";

		try{
			Context context = new ContextImpl();
			context.put("systemProfile" ,systemProfile);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.lang.Integer deleteSystemProfile(org.tinygroup.sdpm.system.dao.pojo.SystemProfile systemProfile) {
		String serviceId = "deleteSystemProfile";

		try{
			Context context = new ContextImpl();
			context.put("systemProfile" ,systemProfile);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.system.dao.pojo.SystemProfile editSystemProfile(org.tinygroup.sdpm.system.dao.pojo.SystemProfile systemProfile) {
		String serviceId = "editSystemProfile";

		try{
			Context context = new ContextImpl();
			context.put("systemProfile" ,systemProfile);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.system.dao.pojo.SystemProfile editSystemProfileTitle(org.tinygroup.sdpm.system.dao.pojo.SystemProfile systemProfile) {
		String serviceId = "editSystemProfileTitle";

		try{
			Context context = new ContextImpl();
			context.put("systemProfile" ,systemProfile);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.lang.Integer softDeleteSystemProfile(java.lang.Integer id) {
		String serviceId = "softDeleteSystemProfile";

		try{
			Context context = new ContextImpl();
			context.put("id" ,id);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.system.dao.pojo.SystemProfile findSystemProfileById(java.lang.Integer id) {
		String serviceId = "findSystemProfileById";

		try{
			Context context = new ContextImpl();
			context.put("id" ,id);

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