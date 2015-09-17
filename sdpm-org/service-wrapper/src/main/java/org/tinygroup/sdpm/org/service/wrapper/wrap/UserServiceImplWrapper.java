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

package org.tinygroup.sdpm.org.service.wrapper.wrap;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.tinygroup.cepcore.CEPCore;
import org.tinygroup.context.Context;
import org.tinygroup.context.impl.ContextImpl;
import org.tinygroup.event.Event;
import org.tinygroup.event.Parameter;
import org.tinygroup.event.ServiceInfo;
import org.tinygroup.event.ServiceRequest;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
@Repository
public class UserServiceImplWrapper implements org.tinygroup.sdpm.org.service.inter.UserService {
	@Autowired
	CEPCore cepCore;

	public CEPCore getCore() {
		return cepCore;
	}

	public void setCore(CEPCore core) {
		this.cepCore = core;
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

	public org.tinygroup.sdpm.org.dao.pojo.OrgUser find(java.lang.String id) {
		String serviceId = "find";

		try{
			Context context = new ContextImpl();
			context.put("id" ,id);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public List<OrgUser> getList(org.tinygroup.sdpm.org.dao.pojo.OrgUser orgUser) {
		String serviceId = "getList";

		try{
			Context context = new ContextImpl();
			context.put("orgUser" ,orgUser);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.org.dao.pojo.OrgUser add(org.tinygroup.sdpm.org.dao.pojo.OrgUser orgUser) {
		String serviceId = "add";

		try{
			Context context = new ContextImpl();
			context.put("orgUser" ,orgUser);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.org.dao.pojo.OrgUser update(org.tinygroup.sdpm.org.dao.pojo.OrgUser orgUser) {
		String serviceId = "update";

		try{
			Context context = new ContextImpl();
			context.put("orgUser" ,orgUser);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.lang.Integer delete(java.lang.String id) {
		String serviceId = "delete";

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
		cepCore.process(event);
		ServiceInfo info = cepCore.getServiceInfo(serviceId);
		List<Parameter> resultsParam = info.getResults();
		if (resultsParam==null||resultsParam.size() == 0) {
			return null;
	}
		return event.getServiceRequest().getContext()
			.get(resultsParam.get(0).getName());
	}

}