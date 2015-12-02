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
@Service("bugService")
public class BugServiceImplWrapper implements org.tinygroup.sdpm.quality.service.inter.BugService {

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

	public java.util.List<org.tinygroup.sdpm.quality.dao.pojo.QualityBug> findBugList(org.tinygroup.sdpm.quality.dao.pojo.QualityBug bug) {
		String serviceId = "findBugList";

		try{
			Context context = new ContextImpl();
			context.put("bug" ,bug);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.quality.dao.pojo.QualityBug addBug(org.tinygroup.sdpm.quality.dao.pojo.QualityBug bug) {
		String serviceId = "addBug";

		try{
			Context context = new ContextImpl();
			context.put("bug" ,bug);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.quality.dao.pojo.QualityBug findQualityBugById(java.lang.Integer id) {
		String serviceId = "findQualityBugById";

		try{
			Context context = new ContextImpl();
			context.put("id" ,id);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int updateBug(org.tinygroup.sdpm.quality.dao.pojo.QualityBug bug) {
		String serviceId = "updateBug";

		try{
			Context context = new ContextImpl();
			context.put("bug" ,bug);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int[] batchUpdateBug(java.util.List<org.tinygroup.sdpm.quality.dao.pojo.QualityBug> bugs) {
		String serviceId = "batchUpdateBug";

		try{
			Context context = new ContextImpl();
			context.put("bugs" ,bugs);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.lang.Integer deleteBug(java.lang.Integer bugId) {
		String serviceId = "deleteBug";

		try{
			Context context = new ContextImpl();
			context.put("bugId" ,bugId);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.Map bugReport(java.lang.String code ,java.lang.Integer productId) {
		String serviceId = "bugReport";

		try{
			Context context = new ContextImpl();
			context.put("code" ,code);
			context.put("productId" ,productId);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.tinysqldsl.Pager findStoryChangedBugs(java.lang.Integer start ,java.lang.Integer limit ,org.tinygroup.sdpm.dao.condition.ConditionCarrier carrier ,org.tinygroup.sdpm.quality.dao.pojo.QualityBug bug ,java.lang.String sortName ,boolean asc) {
		String serviceId = "findStoryChangedBugs";

		try{
			Context context = new ContextImpl();
			context.put("start" ,start);
			context.put("limit" ,limit);
			context.put("carrier" ,carrier);
			context.put("bug" ,bug);
			context.put("sortName" ,sortName);
			context.put("asc" ,asc);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.tinysqldsl.Pager findBugListPager(java.lang.Integer start ,java.lang.Integer limit ,org.tinygroup.sdpm.dao.condition.ConditionCarrier carrier ,org.tinygroup.sdpm.quality.dao.pojo.QualityBug bug ,java.lang.String sortName ,boolean asc) {
		String serviceId = "findBugListPager";

		try{
			Context context = new ContextImpl();
			context.put("start" ,start);
			context.put("limit" ,limit);
			context.put("carrier" ,carrier);
			context.put("bug" ,bug);
			context.put("sortName" ,sortName);
			context.put("asc" ,asc);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.quality.dao.pojo.QualityBug> getBugsInReleaseDoc(org.tinygroup.sdpm.quality.dao.pojo.QualityBug bug) {
		String serviceId = "getBugsInReleaseDoc";

		try{
			Context context = new ContextImpl();
			context.put("bug" ,bug);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.quality.dao.pojo.QualityBug> bugInCondition(java.lang.String condition ,java.lang.Integer productId) {
		String serviceId = "bugInCondition";

		try{
			Context context = new ContextImpl();
			context.put("condition" ,condition);
			context.put("productId" ,productId);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int[] batchDeleteBug(java.util.List<org.tinygroup.sdpm.quality.dao.pojo.QualityBug> bugIds) {
		String serviceId = "batchDeleteBug";

		try{
			Context context = new ContextImpl();
			context.put("bugIds" ,bugIds);

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