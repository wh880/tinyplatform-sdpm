package org.tinygroup.sdpm.action;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FilenameUtils;
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

    @RequestMapping("a/system/about")
    public String about() {
        return "common/about";
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
    public String ajaxUploadImage(@RequestParam(value = "upfile", required = false) MultipartFile uploadFile,
                                  HttpServletResponse response) {
        Map<String, String> map = new HashMap<String, String>();

        try {
            String origName = uploadFile.getOriginalFilename();
            String ext = FilenameUtils.getExtension(origName);
            String fileUrl = fileRepository.storeByExt(UPLOAD_PATH, ext, uploadFile);
            fileUrl = fileRepository.resolverFilePath(fileUrl, UPLOAD_PATH);
            map.put("url", fileUrl);
            map.put("state", "SUCCESS");
        } catch (IOException e) {
            logger.logMessage(LogLevel.ERROR, "文件上传失败", e);
            map.put("state", "n");
        }
        return renderString(response, map);
    }

}