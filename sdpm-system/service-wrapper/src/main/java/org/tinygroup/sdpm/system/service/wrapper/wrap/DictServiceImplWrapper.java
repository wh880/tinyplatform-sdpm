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

package org.tinygroup.sdpm.system.service.wrapper.wrap;

import java.util.List;
import java.util.UUID;

import org.tinygroup.cepcore.CEPCore;
import org.tinygroup.context.Context;
import org.tinygroup.context.impl.ContextImpl;
import org.tinygroup.event.Event;
import org.tinygroup.event.Parameter;
import org.tinygroup.event.ServiceInfo;
import org.tinygroup.event.ServiceRequest;

public class DictServiceImplWrapper implements org.tinygroup.sdpm.system.service.inter.DictService {

	CEPCore cepCore;


	private Event getEvent(String serviceId,Context context) throws Exception{
		Event event = new Event();
		event.setEventId(UUID.randomUUID().toString());
		ServiceRequest serviceRequest = new ServiceRequest();
		serviceRequest.setContext(context);
		serviceRequest.setServiceId(serviceId);
		event.setServiceRequest(serviceRequest);
		return event;
	}

	public org.tinygroup.sdpm.system.dao.pojo.SystemDict addDict(org.tinygroup.sdpm.system.dao.pojo.SystemDict dict) {
		String serviceId = "system_addDict";

		try{
			Context context = new ContextImpl();
			context.put("dict" ,dict);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int deleteDict(java.lang.Integer dictId) {
		String serviceId = "system_deleteDict";

		try{
			Context context = new ContextImpl();
			context.put("dictId" ,dictId);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int updateDict(org.tinygroup.sdpm.system.dao.pojo.SystemDict dict) {
		String serviceId = "system_updateDict";

		try{
			Context context = new ContextImpl();
			context.put("dict" ,dict);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.system.dao.pojo.SystemDict findDict(java.lang.Integer dictId) {
		String serviceId = "system_findDict";

		try{
			Context context = new ContextImpl();
			context.put("dictId" ,dictId);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.system.dao.pojo.SystemDict> findDictList(org.tinygroup.sdpm.system.dao.pojo.SystemDict dict ,java.lang.String columnName ,boolean asc) {
		String serviceId = "system_findDictList";

		try{
			Context context = new ContextImpl();
			context.put("dict" ,dict);
			context.put("columnName" ,columnName);
			context.put("asc" ,asc);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.tinysqldsl.Pager<org.tinygroup.sdpm.system.dao.pojo.SystemDict> findDictPager(int start ,int limit ,org.tinygroup.sdpm.system.dao.pojo.SystemDict dict ,java.lang.String columnName ,boolean asc) {
		String serviceId = "system_findDictPager";

		try{
			Context context = new ContextImpl();
			context.put("start" ,start);
			context.put("limit" ,limit);
			context.put("dict" ,dict);
			context.put("columnName" ,columnName);
			context.put("asc" ,asc);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int[] updateBatchDict(java.util.List<org.tinygroup.sdpm.system.dao.pojo.SystemDict> dicts) {
		String serviceId = "system_updateBatchDict";

		try{
			Context context = new ContextImpl();
			context.put("dicts" ,dicts);

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