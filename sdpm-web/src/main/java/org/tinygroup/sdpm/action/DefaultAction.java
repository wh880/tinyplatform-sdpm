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

package org.tinygroup.sdpm.action;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.sdpm.action.system.FileRepository;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.util.UserUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DefaultAction extends BaseController {
    @Autowired
    private FileRepository fileRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() {
        if (UserUtils.getPrincipal() != null) {
            return "redirect:" + adminPath + "/home";
        }
        return "login/login.pagelet";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login() {        // 如果已经登录，则跳转到管理首页
        if (UserUtils.getPrincipal() != null) {
            return "redirect:" + adminPath;
        }
        return "login/login.pagelet";
    }

    @RequestMapping("saveTree")
    @ResponseBody
    public void saveTree(String treeString, HttpSession session) {
        session.setAttribute("menuList", JSON.parse(treeString));
    }

    /**
     * 图片异步上传
     *
     * @param upFile
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/ajaxUploadImage")
    public String ajaxUploadImage(@RequestParam(value = "upfile", required = false) MultipartFile upFile,
                                  HttpServletResponse response) {
        Map<String, String> map = new HashMap<String, String>();

        String origName = upFile.getOriginalFilename();
        try {
            String fileUrl = fileRepository.storeByExt(UPLOAD_PATH, origName, upFile);
            fileUrl = fileRepository.resolverFilePath(fileUrl, UPLOAD_PATH);
            map.put("url", fileUrl);
            map.put("state", "SUCCESS");

        } catch (IOException e) {
            logger.logMessage(LogLevel.ERROR, "文件上传失败", e);
            map.put("state", "n");
        }
//        StringBuffer buffer = new StringBuffer();
//        buffer.append("{'url':")
//                .append("'")
//                .append(fileUrl).append("'").append(",")
//                .append("'state':").append("'SUCCESS'").append("}");
//        webContext.getResponse().setContentType("text/html");
//        webContext.getResponse().getWriter().write(buffer.toString());
        return renderString(response, map);
    }

}