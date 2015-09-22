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

package org.tinygroup.sdpm.productLine.wrapper;

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
@Component
public class ProductLineServiceImplWrapper implements org.tinygroup.sdpm.productLine.service.ProductLineService {
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

	public org.tinygroup.sdpm.productLine.dao.pojo.ProductLine addProductLine(org.tinygroup.sdpm.productLine.dao.pojo.ProductLine productLine) {
		String serviceId = "productline_addProductLine";

		try{
			Context context = new ContextImpl();
			context.put("productLine" ,productLine);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int updateProductLine(org.tinygroup.sdpm.productLine.dao.pojo.ProductLine productLine) {
		String serviceId = "productline_updateProductLine";

		try{
			Context context = new ContextImpl();
			context.put("productLine" ,productLine);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.productLine.dao.pojo.ProductLine findProductLine(java.lang.Integer productLineId) {
		String serviceId = "productline_findProductLine";

		try{
			Context context = new ContextImpl();
			context.put("productLineId" ,productLineId);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int[] updateBatchProductLine(java.util.List<org.tinygroup.sdpm.productLine.dao.pojo.ProductLine> productLines) {
		String serviceId = "productline_updateBatchProductLine";

		try{
			Context context = new ContextImpl();
			context.put("productLines" ,productLines);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.productLine.dao.pojo.ProductLine> findProductLineList(org.tinygroup.sdpm.productLine.dao.pojo.ProductLine productLine ,java.lang.String columnName ,boolean asc) {
		String serviceId = "productline_findProductLineList";

		try{
			Context context = new ContextImpl();
			context.put("productLine" ,productLine);
			context.put("columnName" ,columnName);
			context.put("asc" ,asc);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.tinysqldsl.Pager<org.tinygroup.sdpm.productLine.dao.pojo.ProductLine> findProductLinePager(int start ,int limit ,org.tinygroup.sdpm.productLine.dao.pojo.ProductLine productLine ,java.lang.String columnName ,boolean asc) {
		String serviceId = "productline_findProductLinePager";

		try{
			Context context = new ContextImpl();
			context.put("start" ,start);
			context.put("limit" ,limit);
			context.put("productLine" ,productLine);
			context.put("columnName" ,columnName);
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