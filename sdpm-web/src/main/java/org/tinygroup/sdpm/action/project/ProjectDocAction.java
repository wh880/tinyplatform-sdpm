package org.tinygroup.sdpm.action.project;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoc;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDocLib;
import org.tinygroup.sdpm.document.service.inter.DocService;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shenly13343 on 2015-10-15.
 */
@Controller
@RequestMapping("/a/project/doc")
public class ProjectDocAction extends BaseController {
    @Autowired
    private DocService docService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ModuleService moduleService;

    @RequiresPermissions("document")
    @RequestMapping("/index")
    public String jumpDocIndex() {
        return "project/index/document/index.page";
    }

    @RequestMapping("/findList")
    public String findList(Model model, HttpServletRequest request, HttpServletResponse response, Integer start, Integer limit, String order, String ordertype) {
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return redirectProjectForm();
        }
        DocumentDoc doc = new DocumentDoc();
        doc.setDocProject(projectId);
        Pager<DocumentDoc> docPager = docService.findDocRetPager(start, limit, doc, null, null, null, order, "asc".equals(ordertype) ? true : false);
        model.addAttribute("docPager", docPager);
        return "project/data/doc/tableData.pagelet";
    }

    @ResponseBody
    @RequestMapping("/batchDel")
    public Map<String, String> batchDel(String ids) {
        String[] id = ids.split(",");
        List<DocumentDoc> docList = new ArrayList<DocumentDoc>();
        for (int i = 0; i < id.length; i++) {
            DocumentDoc doc = new DocumentDoc();
            doc.setDocId(Integer.parseInt(id[i]));
            doc.setDocDeleted(DocumentDoc.DELETE_YES);
            docList.add(doc);
        }
        Integer res = docService.deleteDocByIds(docList);
        if (res > 0) {
            return resultMap(true, "删除成功");
        } else {
            return resultMap(false, "删除失败");
        }
    }

    @RequiresPermissions("pro-document-delete")
    @ResponseBody
    @RequestMapping("/del")
    public Map<String, String> del(Integer id) {
        int res = docService.deleteDocById(id);
        Map<String, String> map = new HashMap<String, String>();
        if (res > 0) {
            map.put("status", "y");
            map.put("info", "删除成功");
        } else {
            map.put("status", "n");
            map.put("info", "删除失败");
        }
        return map;
    }

    @RequiresPermissions("pro-document-report")
    @RequestMapping("/preAdd")
    public String preAdd(Model model) {
        SystemModule module = new SystemModule();
        List<Product> listProduct = productService.findProductList(new Product());
        List<Project> listProject = projectService.findList();
        List<SystemModule> listModule = moduleService.findModuleList(module);
        List<DocumentDocLib> libList = docService.findDocLibList(null);
        model.addAttribute("productList", listProduct);
        model.addAttribute("projectList", listProject);
        model.addAttribute("listModule", listModule);
        model.addAttribute("libList", libList);
        return "project/operate/doc/add";
    }

    @RequestMapping("/save")
    public String save(DocumentDoc doc) {
        DocumentDoc resDoc = docService.createNewDoc(doc);
        return "project/index/document/index.page";
    }
}
