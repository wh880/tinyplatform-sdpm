package org.tinygroup.sdpm.action.document;


import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.dao.complexsearch.SearchInfos;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoc;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDocLib;
import org.tinygroup.sdpm.document.service.inter.DocService;
import org.tinygroup.sdpm.dto.UploadProfile;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.inter.ProductService;
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
import org.tinygroup.sdpm.util.UserUtils;
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
    private DocService docService;
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
        Pager<DocumentDoc> pager = null;
        if (!StringUtil.isBlank(moduleId)) {
            if (moduleId.contains("p") && libId == 1) {
                doc.setDocProduct(Integer.parseInt(moduleId.substring(1)));
                pager = docService.findDocRetPager(limit * (page - 1), limit, doc, null, searchInfos, groupOperate, order, asc);
            } else if (moduleId.contains("p") && libId == 2) {
                doc.setDocProject(Integer.parseInt(moduleId.substring(1)));
                pager = docService.findDocRetPager(limit * (page - 1), limit, doc, null, searchInfos, groupOperate, order, asc);
            } else if ("productDoc".equals(moduleService.findById(Integer.valueOf(moduleId)).getModuleType())) {
                pager = docService.findDocRetProductPager(limit * (page - 1), limit, doc, Integer.parseInt(moduleId), searchInfos, groupOperate, order, asc);
            } else if ("projectDoc".equals(moduleService.findById(Integer.valueOf(moduleId)).getModuleType())) {
                pager = docService.findDocRetProjectPager(limit * (page - 1), limit, doc, Integer.parseInt(moduleId), searchInfos, groupOperate, order, asc);
            } else if ("doc".equals(moduleService.findById(Integer.valueOf(moduleId)).getModuleType())) {
                pager = docService.findDocRetPager(limit * (page - 1), limit, doc, Integer.parseInt(moduleId), searchInfos, groupOperate, order, asc);
            }
        } else {
            pager = docService.findDocRetPager(limit * (page - 1), limit, doc, null, searchInfos, groupOperate, order, asc);
            model.addAttribute("pager", pager);
        }
        model.addAttribute("pager", pager);
        return "/data/datalist.pagelet";
    }

    /**
     * 文档页面跳转方法
     * @param libId
     * @param request
     * @param model
     * @return
     */
    public String returnDoc(Integer libId, HttpServletRequest request, Model model)
    {
        if (libId == null) {
            libId = Integer.parseInt(CookieUtils.getCookie(request, DocAction.COOKIE_DOC_LIB_ID));
        }
        if (libId == 1) {
            Product product = new Product();
            List<Product> list = productService.findProductList(product);
            model.addAttribute("productList", list);
            return "/document/operate/doc/add-doc-product";
        } else if (libId == 2) {
            List<Project> list = projectOperate.getUserProjectList();
            model.addAttribute("projectList", list);
            return "/document/operate/doc/add-doc-project";
        } else {
            SystemModule module = new SystemModule();
            module.setModuleType("doc");
            module.setModuleRoot(libId);
            List<SystemModule> moduleList = moduleService.findModuleList(module);
            model.addAttribute("moduleList", moduleList);
            return "/document/operate/doc/add-doc";
        }
    }

    /**
     * 当选中左边产品或项目的模块时添加文档的跳转
     *
     * @param request
     * @param model
     * @param moduleId
     * @return
     */
    @RequestMapping(value = "/add/path")
    public String createDoc(Integer libId, HttpServletRequest request, Model model,String moduleId) {

        String a=moduleId; //由于module是session里存在的值,改变后会影响项目—任务模块
        //选中左边所属模块时
        if(!moduleId.contains("p"))
        {
            SystemModule systemModule = moduleService.findById(Integer.valueOf(a));
            Integer moduleRoot = systemModule.getModuleRoot();
            String moduleName = systemModule.getModuleName();
            model.addAttribute("moduleRoot",moduleRoot);
            model.addAttribute("docModule",moduleName);
            return returnDoc(libId, request,model);
        }

        //不选所属模块时module会带有字符"p"
        String newModuleId = a.replaceAll("[a-zA-Z]","" ); //去掉moduleId中的字符p
        model.addAttribute("moduleRoot", newModuleId);
        return returnDoc(libId, request,model);

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
    public String addDoc(Integer libId, HttpServletRequest request, Model model) {

        return returnDoc(libId,request,model);
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
        DocumentDoc document = docService.createNewDoc(doc);
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
        DocumentDoc doc = docService.findDocById(docId);
        List<Product> productList = productService.getProductByUser(UserUtils.getUserId(), 0, null, Product.CHOOSE_OPENED);
        List<Project> projectList = projectService.getUserProjectList(UserUtils.getUserId());
        List<SystemModule> listModule = moduleService.findModuleList(module);
        List<DocumentDocLib> libList = docService.findDocLibList(null);

        SystemProfile systemProfile = new SystemProfile();
        systemProfile.setFileObjectId(docId);
        systemProfile.setFileObjectType(ProfileType.DOCUMENT.getType());
        List<SystemProfile> fileList = profileService.findSystemProfile(systemProfile);
        model.addAttribute("fileList", fileList);
        model.addAttribute("docId", docId);
        model.addAttribute("doc", doc);
        model.addAttribute("productList", productList);
        model.addAttribute("projectList", projectList);
        model.addAttribute("listModule", listModule);
        model.addAttribute("libList", libList);
        return "/document/operate/doc/doc-edit.page";
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
        DocumentDoc documentDoc = docService.findDocById(doc.getDocId());
        doc.setDocEditedBy(userUtils.getUser().getOrgUserId());
        docService.editDoc(doc);

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
        DocumentDoc doc = docService.findDocById(docid);
        if (doc == null) {
            return notFoundView();
        }
        if (doc.getDocLibId() != null) {
            DocumentDocLib docLib = docService.findDocLibById(doc.getDocLibId());
            model.addAttribute("docLib", docLib);
        }
        SystemProfile systemProfile = new SystemProfile();
        systemProfile.setFileObjectType(ProfileType.DOCUMENT.getType());
        systemProfile.setFileObjectId(docid);
        List<SystemProfile> list = profileService.findSystemProfile(systemProfile);
        model.addAttribute("fileList", list);
        model.addAttribute("doc", doc);
        return "/document/operate/doc/doc-view.page";
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
        DocumentDoc doc = docService.findDocById(docId);
        DocumentDocLib docLib = docService.findDocLibById(doc.getDocLibId());
        System.out.println(doc.getDocModule());
        if (doc.getDocModule() != 0 && doc.getDocModule() != null) {
            SystemModule module = moduleService.findById(doc.getDocModule());
            model.addAttribute("module", module);
        } else {
            model.addAttribute("module", null);
        }
        model.addAttribute("doc", doc);
        model.addAttribute("docLib", docLib);
        return "/document/rightinfo/doc/basic-info.pagelet";
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
            doc = docService.createNewDoc(doc);
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
            docService.editDoc(doc);
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
        docService.deleteDocById(id);
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
        docService.deleteDocByIds(list);
        return resultMap(true, "删除成功");
    }

    @ResponseBody
    @RequestMapping(value = "/docTitleCheck")
    public Map docTitleCheck(String param, HttpServletRequest request, Integer docId) {

       /* doc.setDocDeleted("0"); //已删除的文档
        String cookieDocLib = CookieUtils.getCookie(request, DocAction.COOKIE_DOC_LIB_ID);
        Integer libId = Integer.valueOf(StringUtil.isBlank(cookieDocLib) ? 0 : Integer.parseInt(cookieDocLib));
        doc.setDocLibId(libId);

        List<DocumentDoc> docList=docService.findDocList(doc);
        if(CollectionUtil.isEmpty(docList))
        {
            return resultMap(true, "文档标题可用！");
        }
        for (DocumentDoc list : docList)
        {
            if(ObjectUtils.equals(docTitle,list.getDocTitle()))
            {
                return resultMap(false, "文档标题重复！");
            }
        }
        return resultMap(true, "文档标题可用！");
    }*/
        String cookieDocLib = CookieUtils.getCookie(request, DocAction.COOKIE_DOC_LIB_ID);
        Integer libId = Integer.valueOf(StringUtil.isBlank(cookieDocLib) ? 0 : Integer.parseInt(cookieDocLib));
        if (param != null) {
            DocumentDoc documentDoc=new DocumentDoc();
            documentDoc.setDocTitle(param);
            documentDoc.setDocLibId(libId);
            documentDoc.setDocDeleted("0");  //0为未删除的文档
            List<DocumentDoc> docList = docService.findDocList(documentDoc);
            if (docList.size() != 0) {
                if (docId == null) {
                    return resultMap(false, "该文档标题已存在");
                } else if (!docId.equals(docList.get(0).getDocId())) {
                    return resultMap(false, "该文档标题已存在");
                } else {
                    return resultMap(true, "");
                }
            } else {
                return resultMap(true, "");
            }
        }
        return resultMap(false, "请输入文档标题");
    }


}
