/**
 * Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 * <p/>
 * Licensed under the GPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.gnu.org/licenses/gpl.html
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tinygroup.sdpm.action;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.util.UserUtils;

import javax.servlet.http.HttpSession;

@Controller
public class DefaultAction extends BaseController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() {
        if (UserUtils.getPrincipal() != null) {
            return "redirect:" + adminPath + "/";
        }
        return "login/login.pagelet";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password) {        // 如果已经登录，则跳转到管理首页
        if (UserUtils.getPrincipal() != null) {
            return "redirect:" + adminPath;
        }
        return "login/login.pagelet";
    }

    @RequestMapping("a")
    public String index() {
        return "main/index.page";
    }

    @RequestMapping("saveTree")
    @ResponseBody
    public void saveTree(String treeString, HttpSession session) {
        session.setAttribute("menuList",JSON.parse(treeString));
    }

}