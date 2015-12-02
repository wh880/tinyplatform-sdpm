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

package org.tinygroup.sdpm.document.wrapper;

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

@Service("docService")
public class DocServiceImplWrapper implements org.tinygroup.sdpm.document.service.inter.DocService {

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

	public org.tinygroup.sdpm.document.dao.pojo.DocumentDoc createNewDoc(org.tinygroup.sdpm.document.dao.pojo.DocumentDoc doc) {
		String serviceId = "createNewDoc";

		try{
			Context context = new ContextImpl();
			context.put("doc" ,doc);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.document.dao.pojo.DocumentDocLib createNewDocLib(org.tinygroup.sdpm.document.dao.pojo.DocumentDocLib docLib) {
		String serviceId = "createNewDocLib";

		try{
			Context context = new ContextImpl();
			context.put("docLib" ,docLib);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int editDoc(org.tinygroup.sdpm.document.dao.pojo.DocumentDoc doc) {
		String serviceId = "editDoc";

		try{
			Context context = new ContextImpl();
			context.put("doc" ,doc);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int editDocLibName(org.tinygroup.sdpm.document.dao.pojo.DocumentDocLib docLib) {
		String serviceId = "editDocLibName";

		try{
			Context context = new ContextImpl();
			context.put("docLib" ,docLib);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.document.dao.pojo.DocumentDoc findDocById(Integer id) {
		String serviceId = "findDocById";

		try{
			Context context = new ContextImpl();
			context.put("id" ,id);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.document.dao.pojo.DocumentDocLib findDocLibById(Integer id) {
		String serviceId = "findDocLibById";

		try{
			Context context = new ContextImpl();
			context.put("id" ,id);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.document.dao.pojo.DocumentDoc> findDocList(org.tinygroup.sdpm.document.dao.pojo.DocumentDoc doc) {
		String serviceId = "findDocList";

		try{
			Context context = new ContextImpl();
			context.put("doc" ,doc);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.document.dao.pojo.DocumentDocLib> findDocLibList(org.tinygroup.sdpm.document.dao.pojo.DocumentDocLib docLib) {
		String serviceId = "findDocLibList";

		try{
			Context context = new ContextImpl();
			context.put("docLib" ,docLib);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.tinysqldsl.Pager findDocRetProductPager(Integer start ,Integer limit ,org.tinygroup.sdpm.document.dao.pojo.DocumentDoc doc ,Integer moduleId ,org.tinygroup.sdpm.dao.complexsearch.SearchInfos conditions ,String groupOperate ,String sortName ,boolean asc) {
		String serviceId = "findDocRetProductPager";

		try{
			Context context = new ContextImpl();
			context.put("start" ,start);
			context.put("limit" ,limit);
			context.put("doc" ,doc);
			context.put("moduleId" ,moduleId);
			context.put("conditions" ,conditions);
			context.put("groupOperate" ,groupOperate);
			context.put("sortName" ,sortName);
			context.put("asc" ,asc);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.tinysqldsl.Pager findDocRetProjectPager(Integer start ,Integer limit ,org.tinygroup.sdpm.document.dao.pojo.DocumentDoc doc ,Integer moduleId ,org.tinygroup.sdpm.dao.complexsearch.SearchInfos conditions ,String groupOperate ,String sortName ,boolean asc) {
		String serviceId = "findDocRetProjectPager";

		try{
			Context context = new ContextImpl();
			context.put("start" ,start);
			context.put("limit" ,limit);
			context.put("doc" ,doc);
			context.put("moduleId" ,moduleId);
			context.put("conditions" ,conditions);
			context.put("groupOperate" ,groupOperate);
			context.put("sortName" ,sortName);
			context.put("asc" ,asc);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.tinysqldsl.Pager findDocRetPager(Integer start ,Integer limit ,org.tinygroup.sdpm.document.dao.pojo.DocumentDoc doc ,Integer moduleId ,org.tinygroup.sdpm.dao.complexsearch.SearchInfos conditions ,String groupOperate ,String sortName ,boolean asc) {
		String serviceId = "findDocRetPager";

		try{
			Context context = new ContextImpl();
			context.put("start" ,start);
			context.put("limit" ,limit);
			context.put("doc" ,doc);
			context.put("moduleId" ,moduleId);
			context.put("conditions" ,conditions);
			context.put("groupOperate" ,groupOperate);
			context.put("sortName" ,sortName);
			context.put("asc" ,asc);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.tinysqldsl.Pager findDocLibRetPager(Integer start ,Integer limit ,org.tinygroup.sdpm.document.dao.pojo.DocumentDocLib docLib ,String sortName ,boolean asc) {
		String serviceId = "findDocLibRetPager";

		try{
			Context context = new ContextImpl();
			context.put("start" ,start);
			context.put("limit" ,limit);
			context.put("docLib" ,docLib);
			context.put("sortName" ,sortName);
			context.put("asc" ,asc);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int deleteDocById(Integer id) {
		String serviceId = "deleteDocById";

		try{
			Context context = new ContextImpl();
			context.put("id" ,id);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int deleteDocLibById(Integer id) {
		String serviceId = "deleteDocLibById";

		try{
			Context context = new ContextImpl();
			context.put("id" ,id);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int[] deleteDocByIds(java.util.List<org.tinygroup.sdpm.document.dao.pojo.DocumentDoc> ids) {
		String serviceId = "deleteDocByIds";

		try{
			Context context = new ContextImpl();
			context.put("ids" ,ids);

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