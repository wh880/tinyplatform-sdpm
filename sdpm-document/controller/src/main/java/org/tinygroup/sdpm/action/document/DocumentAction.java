package org.tinygroup.sdpm.action.document;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.dao.complexsearch.SearchInfos;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoc;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDocLib;
import org.tinygroup.sdpm.document.service.inter.DocService;
import org.tinygroup.sdpm.dto.UploadProfile;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.utils.FieldUtil;
import org.tinygroup.sdpm.product.service.inter.ProductService;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;
import org.tinygroup.sdpm.system.dao.pojo.ProfileType;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.sdpm.util.CookieUtils;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/a/document")
public class DocumentAction extends BaseController {

    public static final String COOKIE_DOCLIB_ID = "documentLibId";
    @Autowired
    private DocService docService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ProjectProductService projectProductService;

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
                List<DocumentDocLib> list = docService.findDocLibList(null);
                if (list != null && !list.isEmpty()) {
                    documentLibId = list.get(0).getDocLibId().toString();
                    CookieUtils.setCookie(response, COOKIE_DOCLIB_ID, documentLibId.toString());
                }
            }
        }
        model.addAttribute(COOKIE_DOCLIB_ID, documentLibId);

        request.getSession().setAttribute("moduleId", moduleId);
        return "/document/index/document.page";
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
        if (projectId == null) {
            return new ArrayList<SystemModule>();
        }
        systemModule.setModuleRoot(projectId);
        systemModule.setModuleType("projectDoc");
        return moduleService.findModuleList(systemModule);
    }

    /**
     * 添加产品文档中产品和分类的二级联动
     *
     * @param systemModule
     * @param productId
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajax/productModule")
    public List<SystemModule> getModule1(SystemModule systemModule, Integer productId) {
        if (productId == null) {
            return new ArrayList<SystemModule>();
        }
        systemModule.setModuleRoot(productId);
        systemModule.setModuleType("productDoc");
        return moduleService.findModuleList(systemModule);
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
        if (projectId == null) {
            return new ArrayList<Product>();
        }
        List<ProjectProduct> projectProductList = projectProductService.findProductListByProjectId(projectId);

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
        if (libId == null) return new ArrayList<SystemModule>();
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
        if (projectId == null) return new ArrayList<SystemModule>();
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
        if (productId == null) return new ArrayList<SystemModule>();
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
        return "/product/page/list/doc/archive-list.page";
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
        Pager<DocumentDoc> docpager = docService.findDocRetPager(start, limit, doc, null, searchInfos, groupOperate, NameUtil.resolveNameDesc(order), asc);
        model.addAttribute("pager", docpager);
        return "/product/data/doc/archivedata.pagelet";
    }

    @RequestMapping("/product/findDoc")
    public String findDocument(Integer docId, Model model) {
        DocumentDoc doc = docService.findDocById(docId);
        model.addAttribute("doc", doc);
        return "/document/operate/doc/doc-edit.page";
    }

    @RequestMapping("/product/{type}/updateDoc")
    public String saveDocument(DocumentDoc doc,Integer docProduct,Integer docLibId,Integer docDeleted,
                               @PathVariable(value = "type") String type,String productId,
                               String lastAddress,
                               UploadProfile uploadProfile) throws IOException {
        if ("save".equals(type)) {
            doc.setDocAddedBy(userUtils.getUserId());
            doc.setDocProduct(Integer.parseInt(productId));
            doc.setDocLibId(docLibId);
            doc.setDocModule(docDeleted);
            DocumentDoc document = docService.createNewDoc(doc);

            processProfile(uploadProfile, document.getDocId(), ProfileType.DOCUMENT);

            LogUtil.logWithComment(LogUtil.LogOperateObject.DOC,
                    LogUtil.LogAction.CREATED,
                    String.valueOf(document.getDocId()),
                    userUtils.getUserId(),
                    String.valueOf(document.getDocProduct()),
                    String.valueOf(document.getDocProject()),
                    null,
                    null,
                    null);
        } else {
            DocumentDoc document = docService.findDocById(doc.getDocId());
            docService.editDoc(doc);
            LogUtil.logWithComment(LogUtil.LogOperateObject.DOC,
                    LogUtil.LogAction.EDITED,
                    String.valueOf(document.getDocId()),
                    userUtils.getUserId(),
                    String.valueOf(document.getDocProduct()),
                    String.valueOf(document.getDocProject()),
                    document,
                    doc,
                    null);
        }
        if (!StringUtil.isBlank(lastAddress)) {
            return "redirect:" + lastAddress;
        }
        return "redirect:" + adminPath + "/product/doc/content";
    }

    @ResponseBody
    @RequestMapping("/docList")
    public List<DocumentDoc> findDocumentDoc(DocumentDoc doc) {
        return docService.findDocList(doc);
    }

    //测试页面专用
    @RequestMapping("/test")
    public String test() {
        return "testManagement/page/tabledemo/batchExecute.page";
    }

    @ResponseBody
    @RequestMapping(value = "/judgeDocName")
    public Map judgeProductLineName(String param, Integer docModule, Integer docId) {
        if (param != null) {
            String docTitle = param;
            DocumentDoc doc = new DocumentDoc();
            doc.setDocTitle(docTitle);
            doc.setDocModule(docModule);
            doc.setDocDeleted(FieldUtil.DELETE_NO_S);
            List<DocumentDoc> documentDocs = docService.findDocList(doc);
            if (documentDocs.size() != 0) {
                if (docId == null) {
                    return resultMap(false, "该文档已存在");
                } else if (!docId.equals(documentDocs.get(0).getDocId())) {
                    return resultMap(false, "该文档已存在");
                } else {
                    return resultMap(true, "");
                }
            } else {
                return resultMap(true, "");
            }
        }
        return resultMap(false, "请输入文档名称");
    }
}
