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

package org.tinygroup.sdpm.org.wrapper;

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

@Service("roleService")
public class RoleServiceImplWrapper implements org.tinygroup.sdpm.org.service.inter.RoleService {

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

	public org.tinygroup.sdpm.org.dao.pojo.OrgRole findRole(java.lang.Integer id) {
		String serviceId = "findRole";

		try{
			Context context = new ContextImpl();
			context.put("id" ,id);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.tinysqldsl.Pager findRolePager(java.lang.Integer start ,java.lang.Integer limit ,org.tinygroup.sdpm.org.dao.pojo.OrgRole orgRole) {
		String serviceId = "findRolePager";

		try{
			Context context = new ContextImpl();
			context.put("start" ,start);
			context.put("limit" ,limit);
			context.put("orgRole" ,orgRole);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.org.dao.pojo.OrgRole> findRoleList(org.tinygroup.sdpm.org.dao.pojo.OrgRole orgRole) {
		String serviceId = "findRoleList";

		try{
			Context context = new ContextImpl();
			context.put("orgRole" ,orgRole);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.org.dao.pojo.OrgRole addRole(org.tinygroup.sdpm.org.dao.pojo.OrgRole orgRole) {
		String serviceId = "addRole";

		try{
			Context context = new ContextImpl();
			context.put("orgRole" ,orgRole);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.org.dao.pojo.OrgRole updateRole(org.tinygroup.sdpm.org.dao.pojo.OrgRole orgRole) {
		String serviceId = "updateRole";

		try{
			Context context = new ContextImpl();
			context.put("orgRole" ,orgRole);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.lang.Integer deleteRole(java.lang.Integer id) {
		String serviceId = "deleteRole";

		try{
			Context context = new ContextImpl();
			context.put("id" ,id);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.org.dao.pojo.OrgRoleMenu> findRoleMenuListByUser(java.lang.String userId) {
		String serviceId = "findRoleMenuListByUser";

		try{
			Context context = new ContextImpl();
			context.put("userId" ,userId);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.org.dao.pojo.OrgRoleMenu> findMenuByRoleId(java.lang.Integer roleId) {
		String serviceId = "findMenuByRoleId";

		try{
			Context context = new ContextImpl();
			context.put("roleId" ,roleId);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.lang.Integer batchAddRoleMenu(java.util.List<org.tinygroup.sdpm.org.dao.pojo.OrgRoleMenu> orgRoleMenuList) {
		String serviceId = "batchAddRoleMenu";

		try{
			Context context = new ContextImpl();
			context.put("orgRoleMenuList" ,orgRoleMenuList);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.lang.Integer saveRoleMenu(java.lang.Integer roleId ,java.lang.String parentMenuId ,java.lang.String[] newMenuIds) {
		String serviceId = "saveRoleMenu";

		try{
			Context context = new ContextImpl();
			context.put("roleId" ,roleId);
			context.put("parentMenuId" ,parentMenuId);
			context.put("newMenuIds" ,newMenuIds);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public void batchDeleteRoleMenu(java.util.List<org.tinygroup.sdpm.org.dao.pojo.OrgRoleMenu> orgRoleMenuList) {
		String serviceId = "batchDeleteRoleMenu";

		try{
			Context context = new ContextImpl();
			context.put("orgRoleMenuList" ,orgRoleMenuList);

			callService(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public void copyRoleMenu(java.lang.Integer orgRoleIdNew ,java.lang.Integer orgRoleId) {
		String serviceId = "copyRoleMenu";

		try{
			Context context = new ContextImpl();
			context.put("orgRoleIdNew" ,orgRoleIdNew);
			context.put("orgRoleId" ,orgRoleId);

			callService(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.org.dao.pojo.OrgRole> findRoleByUserId(java.lang.String userId) {
		String serviceId = "findRoleByUserId";

		try{
			Context context = new ContextImpl();
			context.put("userId" ,userId);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.org.dao.pojo.OrgRoleUser> findUserByRoleId(java.lang.Integer roleId) {
		String serviceId = "findUserByRoleId";

		try{
			Context context = new ContextImpl();
			context.put("roleId" ,roleId);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public void addRoleUser(java.lang.String[] userIds ,java.lang.Integer roleId) {
		String serviceId = "addRoleUser";

		try{
			Context context = new ContextImpl();
			context.put("userIds" ,userIds);
			context.put("roleId" ,roleId);

			callService(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public void batchAddRolesToUser(java.lang.String userId ,java.lang.Integer[] roleIds) {
		String serviceId = "batchAddRolesToUser";

		try{
			Context context = new ContextImpl();
			context.put("userId" ,userId);
			context.put("roleIds" ,roleIds);

			callService(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public void batchAddRoleUser(java.util.List<org.tinygroup.sdpm.org.dao.pojo.OrgRoleUser> orgRoleUserList) {
		String serviceId = "batchAddRoleUser";

		try{
			Context context = new ContextImpl();
			context.put("orgRoleUserList" ,orgRoleUserList);

			callService(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public org.tinygroup.sdpm.org.dao.pojo.OrgRoleUser updateRoleUser(org.tinygroup.sdpm.org.dao.pojo.OrgRoleUser orgRoleUser) {
		String serviceId = "updateRoleUser";

		try{
			Context context = new ContextImpl();
			context.put("orgRoleUser" ,orgRoleUser);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.lang.Integer deleteRoleUser(java.lang.Integer id) {
		String serviceId = "deleteRoleUser";

		try{
			Context context = new ContextImpl();
			context.put("id" ,id);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public void copyRoleUser(java.lang.Integer orgRoleIdNew ,java.lang.Integer orgRoleId) {
		String serviceId = "copyRoleUser";

		try{
			Context context = new ContextImpl();
			context.put("orgRoleIdNew" ,orgRoleIdNew);
			context.put("orgRoleId" ,orgRoleId);

			callService(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public java.util.List<org.tinygroup.sdpm.org.dao.pojo.OrgRole> getRoleByIds(java.lang.String[] ids) {
		String serviceId = "getRoleByIds";

		try{
			Context context = new ContextImpl();
			context.put("ids" ,ids);

			return callServiceAndCallBack(serviceId,context);
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