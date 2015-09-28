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

@Component
public class ProductServiceImplWrapper implements org.tinygroup.sdpm.product.service.ProductService {
	
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

	public org.tinygroup.sdpm.product.dao.pojo.Product addProduct(org.tinygroup.sdpm.product.dao.pojo.Product product) {
		String serviceId = "product_addProduct";

		try{
			Context context = new ContextImpl();
			context.put("product" ,product);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int updateProduct(org.tinygroup.sdpm.product.dao.pojo.Product product) {
		String serviceId = "product_updateProduct";

		try{
			Context context = new ContextImpl();
			context.put("product" ,product);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int deleteProduct(java.lang.Integer productId) {
		String serviceId = "product_deleteProduct";

		try{
			Context context = new ContextImpl();
			context.put("productId" ,productId);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.product.dao.pojo.Product findProduct(java.lang.Integer productId) {
		String serviceId = "product_findProduct";

		try{
			Context context = new ContextImpl();
			context.put("productId" ,productId);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.product.dao.pojo.Product> findProductList(org.tinygroup.sdpm.product.dao.pojo.Product product ,java.lang.String order ,java.lang.String ordertype) {
		String serviceId = "product_findProductList";

		try{
			Context context = new ContextImpl();
			context.put("product" ,product);
			context.put("order" ,order);
			context.put("ordertype" ,ordertype);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.tinysqldsl.Pager<org.tinygroup.sdpm.product.dao.pojo.Product> findProductPager(int page ,int limit ,org.tinygroup.sdpm.product.dao.pojo.Product product ,java.lang.String order ,java.lang.String ordertype) {
		String serviceId = "product_findProductPager";

		try{
			Context context = new ContextImpl();
			context.put("page" ,page);
			context.put("limit" ,limit);
			context.put("product" ,product);
			context.put("order" ,order);
			context.put("ordertype" ,ordertype);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public int[] updateBatchProduct(java.util.List<org.tinygroup.sdpm.product.dao.pojo.Product> products) {
		String serviceId = "product_updateBatchProduct";

		try{
			Context context = new ContextImpl();
			context.put("products" ,products);

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