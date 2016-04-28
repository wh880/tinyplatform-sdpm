package org.tinygroup.sdpm.action.document;


import org.apache.commons.lang.ObjectUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDocLib;
import org.tinygroup.sdpm.document.service.inter.DocService;
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
    private DocService docService;

    /**
     * 添加文档库的跳转
     *
     * @return
     */
    @RequiresPermissions("doclib-add")
    @RequestMapping(value = "/toAdd")
    public String addDocLib() {
        return "/document/modal/doclib/add-doclib.pagelet";
    }

    /**
     * 编辑文档库 的跳转
     *
     * @param docLib
     * @param model
     * @return
     */
    @RequiresPermissions("doc-file-edit")
    @RequestMapping(value = "/edit")
    public String editDocLib(@CookieValue(value = DocLibAction.COOKIE_DOCLIB_ID) Integer documentLibId, DocumentDocLib docLib, Model model) {
        docLib = docService.findDocLibById(documentLibId);
        model.addAttribute("doclib", docLib);
        if (documentLibId == 1 || documentLibId == 2) {
            return "/document/modal/doclib/doclib-no-edit.pagelet";
        }
        return "/document/modal/doclib/doclib-edit.pagelet";
    }

    /**
     * 文档库编辑和新增的保存
     *
     * @param docLib
     * @return
     */
    @RequestMapping(value = "/save")
    public String saveDocLib(DocumentDocLib docLib, HttpServletResponse response,String name,Model model) {

        if(!StringUtil.isEmpty(docLib.getDocLibName())) {
            //添加文档库
            if (docLib.getDocLibId() == null) {
                DocumentDocLib lib = new DocumentDocLib();
                lib.setDocLibDeleted("0"); //0表示未删除的产品文档库
                lib.setDocLibName(docLib.getDocLibName());
                List<DocumentDocLib> list = docService.findDocLibByDocLib(lib);
                if (list.size() == 0) {
                    docLib = docService.createNewDocLib(docLib);
                    CookieUtils.setCookie(response, DocLibAction.COOKIE_DOCLIB_ID, docLib.getDocLibId().toString());
                }
            }

            //编辑文档库
            else {
                //编辑时如果文档库标题不变则调用该方法
                System.out.println(docLib.getDocLibName());
                if (ObjectUtils.equals(name, docLib.getDocLibName())) {
                    return "redirect:" + adminPath + "/document?change=true";
                }
                DocumentDocLib lib = new DocumentDocLib();
                lib.setDocLibDeleted("0"); //0表示未删除的产品文档库
                lib.setDocLibName(docLib.getDocLibName());
                List<DocumentDocLib> list = docService.findDocLibByDocLib(lib);
                if (list.size() != 0) {
                    if (!ObjectUtils.equals(docLib.getDocLibId(), list.get(0).getDocLibId())) {
                        return "redirect:" + adminPath + "/document?change=true";
                    }
                } else {
                    docService.editDocLibName(docLib);
                    CookieUtils.setCookie(response, DocLibAction.COOKIE_DOCLIB_ID, docLib.getDocLibId().toString());
                }

            }
        }
        return "redirect:" + adminPath + "/document?change=true";
    }

    /**
     * 删除文档库
     *
     * @param id
     * @return
     */
    @RequiresPermissions(value = {"doc-file-delete"})
    @ResponseBody
    @RequestMapping(value = "/delete")
    public Map delDocLib(@CookieValue(required = false, value = COOKIE_DOCLIB_ID) String documentLibId, Integer id,
                         HttpServletRequest request, HttpServletResponse response) {
        List<DocumentDocLib> list = docService.findDocLibList(null);
        if (id != list.get(0).getDocLibId() && id != list.get(1).getDocLibId()) {
            docService.deleteDocLibById(id);
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
