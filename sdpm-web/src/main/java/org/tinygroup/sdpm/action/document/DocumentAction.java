package org.tinygroup.sdpm.action.document;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoc;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDocLib;
import org.tinygroup.sdpm.document.service.inter.DocService;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;
import org.tinygroup.sdpm.system.dao.pojo.ProfileType;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.sdpm.util.*;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/a/document")
public class DocumentAction extends BaseController {

    public static final String COOKIE_DOCLIB_ID = "documentLibId";
    @Autowired
    private DocService docservice;
    @Autowired
    private ProductService productService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ProjectProductService projectProductService;
    @Autowired
    private UserService userService;

    /**
     * 主页
     *
     * @param documentLibId
     * @param docLib
     * @param moduleId
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("")
    public String docIndex(@CookieValue(required = false, value = COOKIE_DOCLIB_ID) String documentLibId,
                           DocumentDocLib docLib, String moduleId,
                           HttpServletRequest request, HttpServletResponse response, Model model) {
        if (null != docLib.getDocLibId()) {
            documentLibId = docLib.getDocLibId().toString();
            CookieUtils.setCookie(response, COOKIE_DOCLIB_ID, documentLibId.toString());
        } else {
            if (null == documentLibId) {
                List<DocumentDocLib> list = docservice.findDoclibList(null);
                if (list != null && !list.isEmpty()) {
                    documentLibId = list.get(0).getDocLibId().toString();
                    CookieUtils.setCookie(response, COOKIE_DOCLIB_ID, documentLibId.toString());
                }
            }
        }
        model.addAttribute(COOKIE_DOCLIB_ID, documentLibId);

        List<OrgUser> userList = userService.findUserList(null);

        List<SystemModule> moduleList = moduleService.findModuleList(new SystemModule());

        model.addAttribute("userList", userList);
        model.addAttribute("productList", ProductUtils.getProductList());
        model.addAttribute("projectList", ProjectUtils.getUserProjectList());
        model.addAttribute("moduleList", moduleList);
        model.addAttribute("libList", CmsUtils.getDocLibList());
        request.getSession().setAttribute("moduleId", moduleId);
        return "/document/document.page";
    }


    /**
     * 添加项目文档中项目和分类的二级联动
     *
     * @param systemModule
     * @param projectId
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajax/module")
    public List<SystemModule> getModule(SystemModule systemModule, Integer projectId) {
        if (projectId == 0) {
            return moduleService.findModules(systemModule);
        }
        systemModule.setModuleRoot(projectId);
        systemModule.setModuleType("projectDoc");
        return moduleService.findModules(systemModule);
    }

    /**
     * 添加产品文档中产品和分类的二级联动
     *
     * @param systemModule
     * @param productId
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajax/module1")
    public List<SystemModule> getModule1(SystemModule systemModule, Integer productId) {
        if (productId == 0) {
            return moduleService.findModules(systemModule);
        }
        systemModule.setModuleRoot(productId);
        systemModule.setModuleType("productDoc");
        return moduleService.findModules(systemModule);
    }

    /**
     * 添加项目文档中项目和产品的二级联动
     *
     * @param product
     * @param projectId
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajax/product")
    public List<Product> getProduct(Product product, Integer projectId) {
        if (projectId == 0) {
            return projectProductService.findLinkProduct();
        }
        List<ProjectProduct> projectProductList = projectProductService.findProducts(projectId);

        List<Product> list2 = productService.findProductList(product);
        Integer[] i = new Integer[projectProductList.size()];
        List<Integer> list1 = new ArrayList<Integer>();
        for (int j = 0; j < projectProductList.size(); j++) {
            if (list2.get(j).getDeleted() == 0) {
                list1.add(projectProductList.get(j).getProductId());
            }
        }
        List<Product> productList = productService.findProductListByIds(list1.toArray(i));
        return productList;
    }

    /**
     * 编辑文档中文档库和分类的二级联动
     *
     * @param libId
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajax/moduleByDoclib")
    public List<SystemModule> getModuleByDocLib(Integer libId) {
        SystemModule module = new SystemModule();
        module.setModuleRoot(libId);
        module.setModuleType("doc");
        List<SystemModule> list = moduleService.findModuleList(module);
        return list;
    }

    /**
     * 编辑文档中项目和分类的二级联动
     *
     * @param projectId
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajax/moduleByProject")
    public List<SystemModule> getModuleByProject(Integer projectId) {
        SystemModule module = new SystemModule();
        module.setModuleType("projectDoc");
        module.setModuleRoot(projectId);
        List<SystemModule> list = moduleService.findModuleList(module);
        return list;
    }

    /**
     * 编辑文档中产品和分类的二级联动
     *
     * @param productId
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajax/moduleByProduct")
    public List<SystemModule> getModuleByProduct(Integer productId) {
        SystemModule module = new SystemModule();
        module.setModuleType("productDoc");
        module.setModuleRoot(productId);
        List<SystemModule> list = moduleService.findModuleList(module);
        return list;
    }

    /**
     * 编辑文档中产品和文档库的二级联动
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajax/productByDocLib")
    public List<Product> getProductByDocLib() {
        Product product = new Product();
        List<Product> list = productService.findProductList(product);
        return list;
    }

    //产品文档相关
    @RequestMapping("/product/doc")
    public String product(HttpServletRequest request, Model model) {
        Product product = new Product();
        List<Product> list = productService.findProductList(new Product());
        if (list.size() > 0) {
            if (null == request.getSession().getAttribute("sessionProductId") || product.getProductId() == null) {
                request.getSession().setAttribute("sessionProductId", list.get(0).getProductId());
            } else {
                request.getSession().setAttribute("sessionProductId", product.getProductId());
            }
        }
        model.addAttribute("productList", list);
        return "/product/page/project/archive-list.page";
    }

    @RequestMapping("/product/doc/list")
    public String productList(DocumentDoc doc, HttpServletRequest request, Integer start, Integer limit, String order,
                              String ordertype, String groupOperate, SearchInfos searchInfos, Model model) {
        doc.setDocLibId(1);
        doc.setDocDeleted("0");
        boolean asc = true;
        if ("desc".equals(ordertype)) {
            asc = false;
        }
        Pager<DocumentDoc> docpager = docservice.findDocRetPager(start, limit, doc, null, searchInfos, groupOperate, NameUtil.resolveNameDesc(order), asc);
        model.addAttribute("pager", docpager);
        return "/product/data/archivedata.pagelet";
    }

    @RequestMapping("/product/findDoc")
    public String findDocument(Integer docId, Model model) {
        DocumentDoc doc = docservice.findDocById(docId);
        model.addAttribute("doc", doc);
        return "/document/doc-edit.page";
    }

    @RequestMapping("/product/{type}/updateDoc")
    public String saveDocument( DocumentDoc doc, @PathVariable(value = "type") String type, @RequestParam(value = "file", required = false) MultipartFile[] file, String[] title) throws IOException {
        if ("save".equals(type)) {
            doc.setDocAddedBy(UserUtils.getUserId());
            DocumentDoc document = docservice.createNewDoc(doc);
            uploads(file, document.getDocId(), ProfileType.DOCUMENT, title);
            LogUtil.logWithComment(LogUtil.LogOperateObject.DOC,
                    LogUtil.LogAction.CREATED,
                    String.valueOf(document.getDocId()),
                    UserUtils.getUserId(),
                    String.valueOf(document.getDocProduct()),
                    String.valueOf(document.getDocProject()),
                    null,
                    null,
                    null);
        } else {
            DocumentDoc document = docservice.findDocById(doc.getDocId());
            docservice.editDoc(doc);
            LogUtil.logWithComment(LogUtil.LogOperateObject.DOC,
                    LogUtil.LogAction.EDITED,
                    String.valueOf(document.getDocId()),
                    UserUtils.getUserId(),
                    String.valueOf(document.getDocProduct()),
                    String.valueOf(document.getDocProject()),
                    document,
                    doc,
                    null);
        }
        return "redirect:" + "/a/product/doc/content";
    }

    @ResponseBody
    @RequestMapping("/docList")
    public List<DocumentDoc> findDocumentDoc(DocumentDoc doc) {
        return docservice.findDocList(doc);
    }

    //测试页面专用
    @RequestMapping("/test")
    public String test() {
        return "testManagement/page/tabledemo/batchExecute.page";
    }
}
