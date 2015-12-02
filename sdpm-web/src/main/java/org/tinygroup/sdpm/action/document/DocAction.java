package org.tinygroup.sdpm.action.document;


import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.dao.complexsearch.SearchInfos;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoc;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDocLib;
import org.tinygroup.sdpm.document.service.inter.DocService;
import org.tinygroup.sdpm.dto.UploadProfile;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.system.dao.pojo.ProfileType;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
import org.tinygroup.sdpm.util.CookieUtils;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 单个文档
 */
@Controller
@RequestMapping(value = "/a/document/doc")
public class DocAction extends BaseController {
    public static final String COOKIE_DOC_LIB_ID = "documentLibId";

    @Autowired
    private DocService docservice;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ProfileService profileService;

    @ModelAttribute
    public void init(Model model) {
        initSearchBar(model, "文档");
    }

    /**
     * 首页查出数据
     *
     * @param moduleId
     * @param request
     * @param page
     * @param limit
     * @param order
     * @param ordertype
     * @param doc
     * @param model
     * @param groupOperate
     * @param searchInfos
     * @return
     */
    @RequestMapping(value = "/list")
    public String docList(String moduleId, HttpServletRequest request,
                          Integer page, Integer limit, String order, String ordertype,
                          DocumentDoc doc, Model model, String groupOperate, SearchInfos searchInfos) {
        doc.setDocDeleted("0");
        boolean asc = true;
        if ("desc".equals(ordertype)) {
            asc = false;
        }
        String cookieDocLib = CookieUtils.getCookie(request, DocAction.COOKIE_DOC_LIB_ID);
        Integer libId = Integer.valueOf(StringUtil.isBlank(cookieDocLib) ? 0 : Integer.parseInt(cookieDocLib));
        doc.setDocLibId(libId);
        String condition = null;
        Pager<DocumentDoc> pager = null;
        if (!StringUtil.isBlank(moduleId)) {
            if (moduleId.contains("p") && libId == 1) {
                doc.setDocProduct(Integer.parseInt(moduleId.substring(1)));
                pager = docservice.findDocRetPager(limit * (page - 1), limit, doc, null, searchInfos, groupOperate, order, asc);
            } else if (moduleId.contains("p") && libId == 2) {
                doc.setDocProject(Integer.parseInt(moduleId.substring(1)));
                pager = docservice.findDocRetPager(limit * (page - 1), limit, doc, null, searchInfos, groupOperate, order, asc);
            } else if ("productDoc".equals(moduleService.findById(Integer.valueOf(moduleId)).getModuleType())) {
                pager = docservice.findDocRetProductPager(limit * (page - 1), limit, doc, Integer.parseInt(moduleId), searchInfos, groupOperate, order, asc);
            } else if ("projectDoc".equals(moduleService.findById(Integer.valueOf(moduleId)).getModuleType())) {
                pager = docservice.findDocRetProjectPager(limit * (page - 1), limit, doc, Integer.parseInt(moduleId),searchInfos, groupOperate, order, asc);
            } else if ("doc".equals(moduleService.findById(Integer.valueOf(moduleId)).getModuleType())) {
                pager = docservice.findDocRetPager(limit * (page - 1), limit, doc,Integer.parseInt(moduleId),searchInfos, groupOperate, order, asc);
            }
        } else {
            pager = docservice.findDocRetPager(limit * (page - 1), limit, doc,null, searchInfos, groupOperate, order, asc);
            model.addAttribute("pager", pager);
        }
        model.addAttribute("pager", pager);
        return "/data/datalist.pagelet";
    }

    /**
     * 添加文档的跳转
     *
     * @param request
     * @param model
     * @return
     */
    @RequiresPermissions(value = {"add-doc"})
    @RequestMapping(value = "/add")
    public String createDoc(Integer libId, HttpServletRequest request, Model model) {
        if (libId == null) {
            libId = Integer.parseInt(CookieUtils.getCookie(request, DocAction.COOKIE_DOC_LIB_ID));
        }
        if (libId == 1) {
            Product product = new Product();
            List<Product> list = productService.findProductList(product);
            model.addAttribute("productList", list);
            return "/document/add-doc-product";
        } else if (libId == 2) {
            List<Project> list = projectOperate.getUserProjectList();
            model.addAttribute("projectList", list);
            return "/document/add-doc-project";
        } else {
            SystemModule module = new SystemModule();
            module.setModuleType("doc");
            module.setModuleRoot(libId);
            List<SystemModule> moduleList = moduleService.findModuleList(module);
            model.addAttribute("moduleList", moduleList);
            return "/document/add-doc";
        }
    }

    /**
     * 保存文档
     *
     * @param request
     * @param systemAction
     * @param doc
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/addSave", method = RequestMethod.POST)
    public String addSave(HttpServletRequest request, SystemAction systemAction,
                          DocumentDoc doc, Model model,
                          String lastAddress, UploadProfile uploadProfile) throws IOException {
        List<Product> product = productService.findProductList(new Product());
        doc.setDocLibId(Integer.parseInt(CookieUtils.getCookie(request, DocAction.COOKIE_DOC_LIB_ID)));
        doc.setDocDeleted("0");
        doc.setDocAddedBy(userUtils.getUser().getOrgUserId());
        DocumentDoc document = docservice.createNewDoc(doc);
        processProfile(uploadProfile, document.getDocId(), ProfileType.DOCUMENT);

        model.addAttribute("productList", product);

        LogUtil.logWithComment(LogUtil.LogOperateObject.DOC,
                LogUtil.LogAction.OPENED,
                String.valueOf(document.getDocId()),
                userUtils.getUserId(),
                String.valueOf(doc.getDocProduct()),
                String.valueOf(doc.getDocProject()),
                null,
                null,
                systemAction.getActionComment());

        if (!StringUtil.isBlank(lastAddress)) {
            return "redirect:" + lastAddress;
        }
        if (request.getSession().getAttribute("moduleId") == null) {
            return "redirect:" + adminPath + "/document?docChange=true";
        }
        return "redirect:" + adminPath + "/document?docChange=true&moduleId=" + request.getSession().getAttribute("moduleId");
    }

    /**
     * 编辑文档的跳转
     *
     * @param model
     * @param docId
     * @return
     */
    @RequiresPermissions(value = {"docedit"})
    @RequestMapping(value = "/edit")
    public String editDoc(Model model, Integer docId) {
        SystemModule module = new SystemModule();
        module.setModuleType("doc");
        DocumentDoc doc = docservice.findDocById(docId);
        List<Product> list1 = productService.findProductList(new Product());
        List<Project> list2 = projectService.findList();
        List<SystemModule> listModule = moduleService.findModuleList(module);
        List<DocumentDocLib> libList = docservice.findDocLibList(null);

        SystemProfile systemProfile = new SystemProfile();
        systemProfile.setFileObjectId(docId);
        systemProfile.setFileObjectType(ProfileType.DOCUMENT.getType());
        List<SystemProfile> fileList = profileService.findSystemProfile(systemProfile);
        model.addAttribute("fileList", fileList);

        model.addAttribute("doc", doc);
        model.addAttribute("productList", list1);
        model.addAttribute("projectList", list2);
        model.addAttribute("listModule", listModule);
        model.addAttribute("libList", libList);
        return "/document/doc-edit.page";
    }

    /**
     * 编辑文档的保存
     *
     * @param doc
     * @param systemAction
     * @return
     */
    @RequestMapping(value = "/editSave", method = RequestMethod.POST)
    public String editSave(DocumentDoc doc,
                           String lastAddress,
                           SystemAction systemAction,
                           UploadProfile uploadProfile) throws IOException {
        DocumentDoc documentDoc = docservice.findDocById(doc.getDocId());
        doc.setDocEditedBy(userUtils.getUser().getOrgUserId());
        docservice.editDoc(doc);

        processProfile(uploadProfile, doc.getDocId(), ProfileType.DOCUMENT);

        LogUtil.logWithComment(LogUtil.LogOperateObject.DOC,
                LogUtil.LogAction.EDITED,
                String.valueOf(doc.getDocId()),
                userUtils.getUserId(),
                String.valueOf(doc.getDocProduct()),
                String.valueOf(doc.getDocProject()),
                documentDoc,
                doc,
                systemAction.getActionComment());
        if (!StringUtil.isBlank(lastAddress)) {
            return "redirect:" + lastAddress;
        }
        return "redirect:" + adminPath + "/document?docChange=true";
    }

    /**
     * 文档信息页面
     *
     * @param model
     * @param docid
     * @return
     */
    @RequestMapping("/view")
    public String docView(Model model, Integer docid) {
        DocumentDoc doc = docservice.findDocById(docid);
        if (doc == null) {
            return notFoundView();
        }
        if (doc.getDocLibId() != null) {
            DocumentDocLib docLib = docservice.findDocLibById(doc.getDocLibId());
            model.addAttribute("docLib", docLib);
        }
        SystemProfile systemProfile = new SystemProfile();
        systemProfile.setFileObjectType(ProfileType.DOCUMENT.getType());
        systemProfile.setFileObjectId(docid);
        List<SystemProfile> list = profileService.findSystemProfile(systemProfile);
        model.addAttribute("fileList", list);
        model.addAttribute("doc", doc);
        return "/document/doc-view.page";
    }

    /**
     * 文档信息的基本信息页面
     *
     * @param request
     * @param docId
     * @param model
     * @return
     */
    @RequestMapping("/viewInfo")
    public String viewInfo(HttpServletRequest request, Integer docId, Model model) {
        DocumentDoc doc = docservice.findDocById(docId);
        DocumentDocLib docLib = docservice.findDocLibById(doc.getDocLibId());
        System.out.println(doc.getDocModule() != 0 && doc.getDocModule() != null);
        if (doc.getDocModule() != 0 && doc.getDocModule() != null) {
            SystemModule module = moduleService.findById(doc.getDocModule());
            model.addAttribute("module", module);
        } else {
            model.addAttribute("module", null);
        }
        model.addAttribute("doc", doc);
        model.addAttribute("docLib", docLib);
        return "/document/basic-info.pagelet";
    }

    /**
     * 添加文档和编辑文档的保存
     *
     * @param request
     * @param doc
     * @param model
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveDoc(HttpServletRequest request, DocumentDoc doc, Model model) {
        if (doc.getDocId() == null || doc.getDocId() == 0) {
            doc = docservice.createNewDoc(doc);
            LogUtil.logWithComment(LogUtil.LogOperateObject.DOC,
                    LogUtil.LogAction.CREATED,
                    String.valueOf(doc.getDocId()),
                    userUtils.getUserId(),
                    String.valueOf(doc.getDocProduct()),
                    String.valueOf(doc.getDocProject()),
                    null,
                    null,
                    null);
        } else {
            docservice.editDoc(doc);
        }
        return "redirect:" + adminPath + "/document";
    }

    /**
     * 删除单条文档
     *
     * @param id
     * @return
     */
    @RequiresPermissions(value = {"docdelete", "doc-view-delete"}, logical = Logical.OR)
    @ResponseBody
    @RequestMapping(value = "/delete")
    public Map delDoc(Integer id) {
        docservice.deleteDocById(id);
        return resultMap(true, "删除成功");
    }

    /**
     * 批量删除文档
     *
     * @param ids
     * @return
     */
    @RequiresPermissions(value = {"batch-delete"})
    @ResponseBody
    @RequestMapping(value = "/batchDelete")
    public Map batchDelDoc(String ids) {
        if (StringUtil.isBlank(ids)) {
            return resultMap(false, "请至少选择一条数据");
        }
        List<DocumentDoc> list = new ArrayList<DocumentDoc>();
        for (String s : ids.split(",")) {
            DocumentDoc doc = new DocumentDoc();
            doc.setDocId(Integer.valueOf(s));
            doc.setDocDeleted("1");
            list.add(doc);
        }
        docservice.deleteDocByIds(list);
        return resultMap(true, "删除成功");
    }

}
