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

@Service("actionService")
public class ActionServiceImplWrapper implements org.tinygroup.sdpm.system.service.inter.ActionService {

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

	public java.util.List<org.tinygroup.sdpm.system.dao.pojo.SystemAction> findAction(org.tinygroup.sdpm.system.dao.pojo.SystemAction systemAction ,java.lang.String orderby ,boolean asc) {
		String serviceId = "findAction";

		try{
			Context context = new ContextImpl();
			context.put("systemAction" ,systemAction);
			context.put("orderby" ,orderby);
			context.put("asc" ,asc);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.tinysqldsl.Pager findSystemActionPager(int page ,int pageSize ,org.tinygroup.sdpm.system.dao.pojo.SystemAction action ,java.lang.String order ,java.lang.String ordertype) {
		String serviceId = "findSystemActionPager";

		try{
			Context context = new ContextImpl();
			context.put("page" ,page);
			context.put("pageSize" ,pageSize);
			context.put("action" ,action);
			context.put("order" ,order);
			context.put("ordertype" ,ordertype);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.tinysqldsl.Pager queryActionPager(int start ,int limit ,String chooseDate ,org.tinygroup.sdpm.system.dao.pojo.SystemAction systemAction ,java.lang.String order ,java.lang.String ordertype) {
		String serviceId = "queryActionPager";

		try{
			Context context = new ContextImpl();
			context.put("start" ,start);
			context.put("limit" ,limit);
			context.put("chooseDate" ,chooseDate);
			context.put("systemAction" ,systemAction);
			context.put("order" ,order);
			context.put("ordertype" ,ordertype);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.tinysqldsl.Pager queryActionBetweenDate(int start ,int limit ,org.tinygroup.sdpm.system.dao.pojo.SystemAction action ,java.lang.String startDate ,java.lang.String endDate ,java.lang.String sortName ,boolean asc) {
		String serviceId = "queryActionBetweenDate";

		try{
			Context context = new ContextImpl();
			context.put("start" ,start);
			context.put("limit" ,limit);
			context.put("action" ,action);
			context.put("startDate" ,startDate);
			context.put("endDate" ,endDate);
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