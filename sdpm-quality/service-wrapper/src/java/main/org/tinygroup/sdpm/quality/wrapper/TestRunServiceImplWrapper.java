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

package org.tinygroup.sdpm.quality.wrapper;

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
@Service("testRunService")
public class TestRunServiceImplWrapper implements org.tinygroup.sdpm.quality.service.inter.TestRunService {

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

	public java.util.List<org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun> findTestRunList(org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun testRun) {
		String serviceId = "findTestRunList";

		try{
			Context context = new ContextImpl();
			context.put("testRun" ,testRun);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int updateTestRun(org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun testRun) {
		String serviceId = "updateTestRun";

		try{
			Context context = new ContextImpl();
			context.put("testRun" ,testRun);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int[] batchUpdateTestRun(java.util.List<org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun> testRuns) {
		String serviceId = "batchUpdateTestRun";

		try{
			Context context = new ContextImpl();
			context.put("testRuns" ,testRuns);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun addTestRun(org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun run) {
		String serviceId = "addTestRun";

		try{
			Context context = new ContextImpl();
			context.put("run" ,run);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun findTestRunById(java.lang.Integer id) {
		String serviceId = "findTestRunById";

		try{
			Context context = new ContextImpl();
			context.put("id" ,id);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int deleteTestRun(java.lang.Integer runId) {
		String serviceId = "deleteTestRun";

		try{
			Context context = new ContextImpl();
			context.put("runId" ,runId);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.tinysqldsl.Pager findTestRunPager(java.lang.Integer start ,java.lang.Integer limit ,org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun testRun ,org.tinygroup.sdpm.dao.condition.ConditionCarrier carrier ,java.lang.String sortName ,boolean asc) {
		String serviceId = "findTestRunPager";

		try{
			Context context = new ContextImpl();
			context.put("start" ,start);
			context.put("limit" ,limit);
			context.put("testRun" ,testRun);
			context.put("carrier" ,carrier);
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