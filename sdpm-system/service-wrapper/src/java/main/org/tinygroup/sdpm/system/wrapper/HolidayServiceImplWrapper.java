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

@Service("holidayService")
public class HolidayServiceImplWrapper implements org.tinygroup.sdpm.system.service.inter.HolidayService {

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

	public org.tinygroup.sdpm.system.dao.pojo.Holiday updateHoliday(org.tinygroup.sdpm.system.dao.pojo.Holiday holiday) {
		String serviceId = "updateHoliday";

		try{
			Context context = new ContextImpl();
			context.put("holiday" ,holiday);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.system.dao.pojo.Holiday deleteHoliday(org.tinygroup.sdpm.system.dao.pojo.Holiday holiday) {
		String serviceId = "deleteHoliday";

		try{
			Context context = new ContextImpl();
			context.put("holiday" ,holiday);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.system.dao.pojo.Holiday> findHolidayList(org.tinygroup.sdpm.system.dao.pojo.Holiday holiday) {
		String serviceId = "findHolidayList";

		try{
			Context context = new ContextImpl();
			context.put("holiday" ,holiday);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.tinysqldsl.Pager findByPage(java.lang.Integer start ,java.lang.Integer limit ,org.tinygroup.sdpm.system.dao.pojo.Holiday holiday ,java.lang.String sortName ,boolean asc) {
		String serviceId = "findByPage";

		try{
			Context context = new ContextImpl();
			context.put("start" ,start);
			context.put("limit" ,limit);
			context.put("holiday" ,holiday);
			context.put("sortName" ,sortName);
			context.put("asc" ,asc);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.system.dao.pojo.Holiday> batchAddHoliday(java.util.List<org.tinygroup.sdpm.system.dao.pojo.Holiday> holidayList) {
		String serviceId = "batchAddHoliday";

		try{
			Context context = new ContextImpl();
			context.put("holidayList" ,holidayList);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.system.dao.pojo.Holiday findHolidayById(java.lang.Integer id) {
		String serviceId = "findHolidayById";

		try{
			Context context = new ContextImpl();
			context.put("id" ,id);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.system.dao.pojo.Holiday> findHolidayByIds(java.lang.Integer[] ids) {
		String serviceId = "findHolidayByIds";

		try{
			Context context = new ContextImpl();
			context.put("ids" ,ids);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public void batchSoftDeleteHoliday(java.util.List<org.tinygroup.sdpm.system.dao.pojo.Holiday> list) {
		String serviceId = "batchSoftDeleteHoliday";

		try{
			Context context = new ContextImpl();
			context.put("list" ,list);

			callService(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	private void callService(String serviceId,Context context) throws Exception{
		Event event = getEvent(serviceId,context);
		cepcore.process(event);
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