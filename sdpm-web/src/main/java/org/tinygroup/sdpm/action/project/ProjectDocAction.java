package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.util.CookieUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoc;
import org.tinygroup.sdpm.document.service.inter.DocService;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
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


    @RequestMapping("/findList")
    public String findList(Model model, HttpServletRequest request, Integer start, Integer limit, String order, String ordertype) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));

        DocumentDoc doc = new DocumentDoc();
        doc.setDocProject(projectId);
        Pager<DocumentDoc> docPager = docService.findDocRetPager(start, limit, doc, null, null, null, order, "asc".equals(ordertype) ? true : false);
        model.addAttribute("docPager", docPager);
        return "project/document/tableData.pagelet";
    }

    @ResponseBody
    @RequestMapping("/batchDel")
    public Map<String, String> batcheDel(String ids) {
        String[] id = ids.split(",");
        List<DocumentDoc> docList = new ArrayList<DocumentDoc>();
        for (int i = 0; i < id.length; i++) {
            DocumentDoc doc = new DocumentDoc();
            doc.setDocId(Integer.parseInt(id[i]));
            doc.setDocDeleted(doc.DELETE_YES);
            docList.add(doc);
        }
        int[] res = docService.deleteDocByIds(docList);
        Map<String, String> map = new HashMap<String, String>();
        if (res.length > 0) {
            map.put("status", "y");
            map.put("info", "删除成功");
        } else {
            map.put("status", "n");
            map.put("info", "删除失败");
        }
        return map;
    }

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

    @RequestMapping("/preAdd")
    public String preAdd(Model model) {
        List<Project> projectList = projectService.findList();
        List<Product> productList = productService.findProductList(new Product());
        //所属分类

        model.addAttribute("projectList", projectList);
        model.addAttribute("productList", productList);
        return "project/document/add.page";
    }

    @RequestMapping("/save")
    public String save(DocumentDoc doc) {
        DocumentDoc resDoc = docService.createNewDoc(doc);
        return "project/document/index.page";
    }
}
