package org.tinygroup.sdpm.action;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.convert.objectjson.fastjson.ObjectToJson;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.util.UploadUtils;
import org.tinygroup.springmvc.multipart.TinyMultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DefaultAction extends BaseController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() {
        if (userUtils.getPrincipal() != null) {
            return "redirect:" + adminPath + "/home";
        }
        return "login/login.pagelet";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login() {        // 如果已经登录，则跳转到管理首页
        if (userUtils.getPrincipal() != null) {
            return "redirect:" + adminPath;
        }
        return "login/login.pagelet";
    }

    @RequestMapping("saveTree")
    @ResponseBody
    public void saveTree(String treeString, HttpSession session) {
        session.setAttribute("menuList", JSON.parse(treeString));
    }

    @RequestMapping("a/system/about")
    public String about() {
        return "common/about";
    }

    @RequestMapping("a/system/us")
    public String us() {
        return "common/us";
    }

    @RequestMapping("error")
    public String error() {
        return "error/50x";
    }

    /**
     * 图片异步上传
     *
     * @param uploadFile
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/ajaxUploadImage")
    public String ajaxUploadImage(@RequestParam(value = "upfile", required = false) TinyMultipartFile uploadFile,
                                  HttpServletResponse response) {
        Map<String, String> map = new HashMap<String, String>();
        if (uploadFile.toFileObject() == null || StringUtil.isBlank(uploadFile.toFileObject().getAbsolutePath())) {
            map.put("state", "n");
        } else {
            String fileUrl = uploadFile.toFileObject().getAbsolutePath();
            fileUrl = UploadUtils.resolverFilePath(fileUrl, UPLOAD_PATH);
            map.put("url", fileUrl);
            map.put("state", "SUCCESS");
        }
        ObjectToJson objectToJson = new ObjectToJson();
        return renderString(response, objectToJson.convert(map), "text/html");
    }

}