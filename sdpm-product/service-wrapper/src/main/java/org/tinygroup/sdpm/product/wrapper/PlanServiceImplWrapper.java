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

package org.tinygroup.sdpm.product.wrapper;

import java.util.List;
import java.util.UUID;

import org.tinygroup.cepcore.CEPCore;
import org.tinygroup.context.Context;
import org.tinygroup.context.impl.ContextImpl;
import org.tinygroup.event.Event;
import org.tinygroup.event.Parameter;
import org.tinygroup.event.ServiceInfo;
import org.tinygroup.event.ServiceRequest;

public class PlanServiceImplWrapper implements org.tinygroup.sdpm.product.service.PlanService {

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

	public org.tinygroup.sdpm.product.dao.pojo.ProductPlan addPlan(org.tinygroup.sdpm.product.dao.pojo.ProductPlan plan) {
		String serviceId = "product_addPlan";

		try{
			Context context = new ContextImpl();
			context.put("plan" ,plan);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int updatePlan(org.tinygroup.sdpm.product.dao.pojo.ProductPlan plan) {
		String serviceId = "product_updatePlan";

		try{
			Context context = new ContextImpl();
			context.put("plan" ,plan);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int deletePlan(java.lang.Integer planId) {
		String serviceId = "product_deletePlan";

		try{
			Context context = new ContextImpl();
			context.put("planId" ,planId);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.product.dao.pojo.ProductPlan findPlan(java.lang.Integer planId) {
		String serviceId = "product_findPlan";

		try{
			Context context = new ContextImpl();
			context.put("planId" ,planId);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int[] updateBatchPlan(java.util.List<org.tinygroup.sdpm.product.dao.pojo.ProductPlan> plan) {
		String serviceId = "product_updateBatchPlan";

		try{
			Context context = new ContextImpl();
			context.put("plan" ,plan);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.product.dao.pojo.ProductPlan> findPlanList(org.tinygroup.sdpm.product.dao.pojo.ProductPlan plan ,java.lang.String columnName ,boolean asc) {
		String serviceId = "product_findPlanList";

		try{
			Context context = new ContextImpl();
			context.put("plan" ,plan);
			context.put("columnName" ,columnName);
			context.put("asc" ,asc);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.tinysqldsl.Pager<org.tinygroup.sdpm.product.dao.pojo.ProductPlan> findProductPlanPager(int start ,int limit ,org.tinygroup.sdpm.product.dao.pojo.ProductPlan plan ,java.lang.String columnName ,boolean asc) {
		String serviceId = "product_findProductPlanPager";

		try{
			Context context = new ContextImpl();
			context.put("start" ,start);
			context.put("limit" ,limit);
			context.put("plan" ,plan);
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