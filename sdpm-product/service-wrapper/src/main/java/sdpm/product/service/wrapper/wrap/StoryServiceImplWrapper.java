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

package sdpm.product.service.wrapper.wrap;

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
import org.tinygroup.sdpm.common.util.sql.SearchInfos;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;

@Component("storyService")
public class StoryServiceImplWrapper implements org.tinygroup.sdpm.product.service.StoryService {
	@Autowired
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

	public org.tinygroup.sdpm.product.dao.pojo.ProductStory addStory(org.tinygroup.sdpm.product.dao.pojo.ProductStory story) {
		String serviceId = "product_addStory";

		try{
			Context context = new ContextImpl();
			context.put("story" ,story);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int deleteStory(java.lang.Integer storyId) {
		String serviceId = "product_deleteStory";

		try{
			Context context = new ContextImpl();
			context.put("storyId" ,storyId);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int updateStory(org.tinygroup.sdpm.product.dao.pojo.ProductStory story) {
		String serviceId = "product_updateStory";

		try{
			Context context = new ContextImpl();
			context.put("story" ,story);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.product.dao.pojo.ProductStory findStory(java.lang.Integer storyId) {
		String serviceId = "product_findStory";

		try{
			Context context = new ContextImpl();
			context.put("storyId" ,storyId);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int[] updateBatch(java.util.List<org.tinygroup.sdpm.product.dao.pojo.ProductStory> stories) {
		String serviceId = "product_updateBatch";

		try{
			Context context = new ContextImpl();
			context.put("stories" ,stories);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.product.dao.pojo.ProductStory> findStoryList(org.tinygroup.sdpm.product.dao.pojo.ProductStory story ,java.lang.String columnName ,boolean asc) {
		String serviceId = "product_findStoryList";

		try{
			Context context = new ContextImpl();
			context.put("story" ,story);
			context.put("columnName" ,columnName);
			context.put("asc" ,asc);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.tinysqldsl.Pager<org.tinygroup.sdpm.product.dao.pojo.ProductStory> findStoryPager(int start, int limit, ProductStory story, SearchInfos searchInfos, String groupOperate, String columnName, boolean asc) {
		String serviceId = "product_findStoryPager";

		try{
			Context context = new ContextImpl();
			context.put("start" ,start);
			context.put("limit" ,limit);
			context.put("story" ,story);
			context.put("searchInfos" ,searchInfos);
			context.put("groupOperate" ,groupOperate);
			context.put("columnName" ,columnName);
			context.put("asc" ,asc);

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