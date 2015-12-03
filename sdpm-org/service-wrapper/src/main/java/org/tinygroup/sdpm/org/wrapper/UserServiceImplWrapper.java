/**
 * Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 * <p>
 * Licensed under the GPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/gpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tinygroup.sdpm.org.wrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.aopcache.annotation.CacheGet;
import org.tinygroup.cepcore.CEPCore;
import org.tinygroup.context.Context;
import org.tinygroup.context.impl.ContextImpl;
import org.tinygroup.event.Event;
import org.tinygroup.event.Parameter;
import org.tinygroup.event.ServiceInfo;
import org.tinygroup.event.ServiceRequest;

import java.util.List;
import java.util.UUID;

@Service("userService")
public class UserServiceImplWrapper implements org.tinygroup.sdpm.org.service.inter.UserService {

    @Autowired
    CEPCore cepcore;

    public CEPCore getCore() {
        return cepcore;
    }

    public void setCore(CEPCore cepcore) {
        this.cepcore = cepcore;
    }

    private Event getEvent(String serviceId, Context context) throws Exception {
        Event event = new Event();
        event.setEventId(UUID.randomUUID().toString());
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setContext(context);
        serviceRequest.setServiceId(serviceId);
        event.setServiceRequest(serviceRequest);
        return event;
    }

    @CacheGet(key = "${id}", group = CACHE_USER_ID)
    public org.tinygroup.sdpm.org.dao.pojo.OrgUser findUser(java.lang.String id) {
        String serviceId = "findUser";

        try {
            Context context = new ContextImpl();
            context.put("id", id);

            return callServiceAndCallBack(serviceId, context);
        } catch (Exception e) {
            throw new RuntimeException(String.format("服务[%s]发生异常", serviceId), e);
        }
    }
    @CacheGet(key = "${account}", group = CACHE_USER_ACCOUNT)
    public org.tinygroup.sdpm.org.dao.pojo.OrgUser findUserByAccount(java.lang.String account) {
        String serviceId = "findUserByAccount";

        try {
            Context context = new ContextImpl();
            context.put("account", account);
            return callServiceAndCallBack(serviceId, context);
        } catch (Exception e) {
            throw new RuntimeException(String.format("服务[%s]发生异常", serviceId), e);
        }
    }

    public org.tinygroup.tinysqldsl.Pager findUserPager(java.lang.Integer start, java.lang.Integer limit, org.tinygroup.sdpm.org.dao.pojo.OrgUser orgUser) {
        String serviceId = "findUserPager";

        try {
            Context context = new ContextImpl();
            context.put("start", start);
            context.put("limit", limit);
            context.put("orgUser", orgUser);

            return callServiceAndCallBack(serviceId, context);
        } catch (Exception e) {
            throw new RuntimeException(String.format("服务[%s]发生异常", serviceId), e);
        }
    }

    public java.util.List<org.tinygroup.sdpm.org.dao.pojo.OrgUser> findUserList(org.tinygroup.sdpm.org.dao.pojo.OrgUser orgUser) {
        String serviceId = "findUserList";

        try {
            Context context = new ContextImpl();
            context.put("orgUser", orgUser);

            return callServiceAndCallBack(serviceId, context);
        } catch (Exception e) {
            throw new RuntimeException(String.format("服务[%s]发生异常", serviceId), e);
        }
    }

    public org.tinygroup.tinysqldsl.Pager findUserByDeptId(java.lang.Integer start, java.lang.Integer limit, java.lang.Integer deptId) {
        String serviceId = "findUserByDeptId";

        try {
            Context context = new ContextImpl();
            context.put("start", start);
            context.put("limit", limit);
            context.put("deptId", deptId);

            return callServiceAndCallBack(serviceId, context);
        } catch (Exception e) {
            throw new RuntimeException(String.format("服务[%s]发生异常", serviceId), e);
        }
    }

    public org.tinygroup.sdpm.org.dao.pojo.OrgUser addUser(org.tinygroup.sdpm.org.dao.pojo.OrgUser orgUser) {
        String serviceId = "addUser";

        try {
            Context context = new ContextImpl();
            context.put("orgUser", orgUser);

            return callServiceAndCallBack(serviceId, context);
        } catch (Exception e) {
            throw new RuntimeException(String.format("服务[%s]发生异常", serviceId), e);
        }
    }

    public org.tinygroup.sdpm.org.dao.pojo.OrgUser updateUser(org.tinygroup.sdpm.org.dao.pojo.OrgUser orgUser) {
        String serviceId = "updateUser";

        try {
            Context context = new ContextImpl();
            context.put("orgUser", orgUser);

            return callServiceAndCallBack(serviceId, context);
        } catch (Exception e) {
            throw new RuntimeException(String.format("服务[%s]发生异常", serviceId), e);
        }
    }

    public java.lang.Integer deleteUser(java.lang.String id) {
        String serviceId = "deleteUser";

        try {
            Context context = new ContextImpl();
            context.put("id", id);

            return callServiceAndCallBack(serviceId, context);
        } catch (Exception e) {
            throw new RuntimeException(String.format("服务[%s]发生异常", serviceId), e);
        }
    }

    public int[] deleteBatchUser(java.util.List<org.tinygroup.sdpm.org.dao.pojo.OrgUser> list) {
        String serviceId = "deleteBatchUser";

        try {
            Context context = new ContextImpl();
            context.put("list", list);

            return callServiceAndCallBack(serviceId, context);
        } catch (Exception e) {
            throw new RuntimeException(String.format("服务[%s]发生异常", serviceId), e);
        }
    }

    public Boolean validatePassword(java.lang.String plainPassword, java.lang.String password) {
        String serviceId = "validatePassword";

        try {
            Context context = new ContextImpl();
            context.put("plainPassword", plainPassword);
            context.put("password", password);

            return callServiceAndCallBack(serviceId, context);
        } catch (Exception e) {
            throw new RuntimeException(String.format("服务[%s]发生异常", serviceId), e);
        }
    }

    public java.util.List<org.tinygroup.sdpm.org.dao.pojo.OrgUser> findUserListByIds(java.lang.String[] userId) {
        String serviceId = "findUserListByIds";

        try {
            Context context = new ContextImpl();
            context.put("userId", userId);

            return callServiceAndCallBack(serviceId, context);
        } catch (Exception e) {
            throw new RuntimeException(String.format("服务[%s]发生异常", serviceId), e);
        }
    }

    public java.util.List<org.tinygroup.sdpm.org.dao.pojo.OrgUser> findTeamUserListByProjectId(java.lang.Integer projectId) {
        String serviceId = "findTeamUserListByProjectId";

        try {
            Context context = new ContextImpl();
            context.put("projectId", projectId);

            return callServiceAndCallBack(serviceId, context);
        } catch (Exception e) {
            throw new RuntimeException(String.format("服务[%s]发生异常", serviceId), e);
        }
    }

    public java.util.List<org.tinygroup.sdpm.org.dao.pojo.OrgUser> userInCondition(java.lang.String condition, java.lang.String[] ids) {
        String serviceId = "userInCondition";

        try {
            Context context = new ContextImpl();
            context.put("condition", condition);
            context.put("ids", ids);

            return callServiceAndCallBack(serviceId, context);
        } catch (Exception e) {
            throw new RuntimeException(String.format("服务[%s]发生异常", serviceId), e);
        }
    }

    private <T> T callServiceAndCallBack(String serviceId, Context context) throws Exception {
        Event event = getEvent(serviceId, context);
        cepcore.process(event);
        ServiceInfo info = cepcore.getServiceInfo(serviceId);
        List<Parameter> resultsParam = info.getResults();
        if (resultsParam == null || resultsParam.size() == 0) {
            return null;
        }
        return event.getServiceRequest().getContext()
                .get(resultsParam.get(0).getName());
    }

}