package org.tinygroup.sdpm.action.document;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoclib;
import org.tinygroup.sdpm.document.service.inter.DocService;
import org.tinygroup.sdpm.util.CmsUtils;
import org.tinygroup.sdpm.util.CookieUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文档库
 */
@Controller
@RequestMapping(value = "/a/document/doclib")
public class DocLibAction extends BaseController {

    public static final String COOKIE_DOCLIB_ID = "documentLibId";

    @Autowired
    private DocService docservice;

    /**
     * 添加文档库的跳转
     *
     * @return
     */
    @RequiresPermissions(value = {"doclib-add"})
    @RequestMapping(value = "/toAdd")
    public String addDocLib() {
        return "/document/add-doclib.pagelet";
    }

    /**
     * 编辑文档库 的跳转
     *
     * @param docLib
     * @param model
     * @return
     */
    @RequiresPermissions("doclib-edit")
    @RequestMapping(value = "/edit")
    public String editDocLib(@CookieValue(value = DocLibAction.COOKIE_DOCLIB_ID) Integer documentLibId, DocumentDoclib docLib, Model model) {
        docLib = docservice.findDoclibById(documentLibId);
        model.addAttribute("doclib", docLib);
        if (documentLibId == 1 || documentLibId == 2) {
            return "/document/doclib-no-edit.pagelet";
        }
        return "/document/doclib-edit.pagelet";
    }

    /**
     * 文档库编辑和新增的保存
     *
     * @param docLib
     * @return
     */
    @RequestMapping(value = "/save")
    public String saveDocLib(DocumentDoclib docLib, HttpServletResponse response) {
        if (docLib.getDocLibId() == null) {
            docLib = docservice.createNewDocLib(docLib);
        } else {
            docservice.editDocLibName(docLib);
        }
        CookieUtils.setCookie(response, DocLibAction.COOKIE_DOCLIB_ID, docLib.getDocLibId().toString());
        CmsUtils.removeDocLibList();
        return "redirect:" + adminPath + "/document?change=true";
    }

    /**
     * 删除文档库
     *
     * @param id
     * @return
     */
    @RequiresPermissions(value = {"doclib-delete"})
    @ResponseBody
    @RequestMapping(value = "/delete")
    public Map delDocLib(@CookieValue(required = false, value = COOKIE_DOCLIB_ID) String documentLibId, Integer id,
    					HttpServletRequest request, HttpServletResponse response) {
        List<DocumentDoclib> list = docservice.findDoclibList(null);
        if (id != list.get(0).getDocLibId() && id != list.get(1).getDocLibId()) {
            docservice.deleteDoclibById(id);
            CmsUtils.removeDocLibList();
            documentLibId = "1";
            CookieUtils.setCookie(response, COOKIE_DOCLIB_ID, documentLibId.toString(), -1);
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "success");
            map.put("info", "删除成功");
            return map;
        }
        return null;
    }

}
