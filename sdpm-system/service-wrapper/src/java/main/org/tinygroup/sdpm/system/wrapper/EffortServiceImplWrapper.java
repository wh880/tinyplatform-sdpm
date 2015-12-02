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

@Service("effortService")
public class EffortServiceImplWrapper implements org.tinygroup.sdpm.system.service.inter.EffortService {

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

	public java.lang.Integer batchEffortSave(java.util.List<org.tinygroup.sdpm.system.dao.pojo.SystemEffort> list) {
		String serviceId = "batchEffortSave";

		try{
			Context context = new ContextImpl();
			context.put("list" ,list);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.system.dao.pojo.SystemEffort saveSystemEffort(org.tinygroup.sdpm.system.dao.pojo.SystemEffort systemEffort) {
		String serviceId = "saveSystemEffort";

		try{
			Context context = new ContextImpl();
			context.put("systemEffort" ,systemEffort);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.system.dao.pojo.SystemEffort> findSystemEffortByAccount(java.lang.String account) {
		String serviceId = "findSystemEffortByAccount";

		try{
			Context context = new ContextImpl();
			context.put("account" ,account);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.system.dao.pojo.SystemEffort> findSystemEffortList(org.tinygroup.sdpm.system.dao.pojo.SystemEffort systemEffort) {
		String serviceId = "findSystemEffortList";

		try{
			Context context = new ContextImpl();
			context.put("systemEffort" ,systemEffort);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.system.dao.pojo.SystemEffort> findSystemEffortBetweenDate(org.tinygroup.sdpm.system.dao.pojo.SystemEffort systemEffort ,java.util.Date beginDate ,java.util.Date endDate) {
		String serviceId = "findSystemEffortBetweenDate";

		try{
			Context context = new ContextImpl();
			context.put("systemEffort" ,systemEffort);
			context.put("beginDate" ,beginDate);
			context.put("endDate" ,endDate);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.system.dao.pojo.SystemEffort> findSystemEffortByProjectId(java.lang.Integer projectId) {
		String serviceId = "findSystemEffortByProjectId";

		try{
			Context context = new ContextImpl();
			context.put("projectId" ,projectId);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.tinysqldsl.Pager findSystemEffortPage(java.lang.Integer start ,java.lang.Integer limit ,org.tinygroup.sdpm.system.dao.pojo.SystemEffort SystemEffort ,java.lang.String sortName ,boolean asc) {
		String serviceId = "findSystemEffortPage";

		try{
			Context context = new ContextImpl();
			context.put("start" ,start);
			context.put("limit" ,limit);
			context.put("SystemEffort" ,SystemEffort);
			context.put("sortName" ,sortName);
			context.put("asc" ,asc);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.system.dao.pojo.SystemEffort> findSystemEffortListByOrder(org.tinygroup.sdpm.system.dao.pojo.SystemEffort systemEffort ,java.lang.String order ,java.lang.String orderType) {
		String serviceId = "findSystemEffortListByOrder";

		try{
			Context context = new ContextImpl();
			context.put("systemEffort" ,systemEffort);
			context.put("order" ,order);
			context.put("orderType" ,orderType);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.lang.Integer batchDelete(java.lang.Integer[] ids) {
		String serviceId = "batchDelete";

		try{
			Context context = new ContextImpl();
			context.put("ids" ,ids);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.tinysqldsl.Pager findSystemEffortPagerByDate(java.lang.Integer start ,java.lang.Integer limit ,org.tinygroup.sdpm.system.dao.pojo.SystemEffort effort ,java.util.Date startDate ,java.util.Date endDate ,java.lang.String sortName ,boolean asc) {
		String serviceId = "findSystemEffortPagerByDate";

		try{
			Context context = new ContextImpl();
			context.put("start" ,start);
			context.put("limit" ,limit);
			context.put("effort" ,effort);
			context.put("startDate" ,startDate);
			context.put("endDate" ,endDate);
			context.put("sortName" ,sortName);
			context.put("asc" ,asc);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.system.dao.pojo.SystemEffort findSystemEffortById(java.lang.Integer id) {
		String serviceId = "findSystemEffortById";

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