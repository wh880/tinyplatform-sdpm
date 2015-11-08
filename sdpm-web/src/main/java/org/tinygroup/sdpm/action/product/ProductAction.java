package org.tinygroup.sdpm.action.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoc;
import org.tinygroup.sdpm.document.service.inter.DocService;
import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleUser;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.RoleService;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.productLine.service.ProductLineService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.service.dto.BurnDTO;
import org.tinygroup.sdpm.project.service.inter.BuildService;
import org.tinygroup.sdpm.project.service.inter.BurnService;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;
import org.tinygroup.sdpm.quality.service.inter.TestCaseService;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemHistory;
import org.tinygroup.sdpm.system.service.inter.ActionService;
import org.tinygroup.sdpm.system.service.inter.HistoryService;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.ProductUtils;
import org.tinygroup.sdpm.util.UserUtils;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.weblayer.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品控制器
 *
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/a/product")
public class ProductAction extends BaseController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ActionService actionService;
    @Autowired
    private ProductLineService productLineService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BuildService buildService;
    @Autowired
    private TestCaseService testCaseService;
    @Autowired
    private ProjectService productProject;
    @Autowired
    private BurnService burnService;
    @Autowired
    private DocService docService;
    @Autowired
    private ProjectProductService projectProductService;
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/{forward}/content")
    public String doc(@PathVariable(value = "forward") String forward, HttpServletRequest request, Model model) {

        if ("doc".equals(forward)) {
            return "/product/page/project/archive-list.page";
        } else if ("project".equals(forward)) {
            return "/product/page/project/product-project-list.page";
        } else if ("dynamic".equals(forward)) {
            return "/product/page/project/dymanicdata.page";
        }
        return "";
    }



    @RequestMapping("")
    public String productAction(@CookieValue(value = "cookieProductLineId",defaultValue = "0")String cookieProductLineId,HttpServletResponse response,HttpServletRequest request) {
        if("0".equals(cookieProductLineId)) {
            ProductUtils.prepareForFirst(response);
        }
        return "redirect:"+adminPath+"/product/story?choose=1" + (request.getQueryString() == null ? "" : ("&" + request.getQueryString()));
    }


    @RequestMapping("/save")
    public String save(@CookieValue(value = "cookieProductLineId") String cookieProductLineId,Product product,SystemAction systemAction, HttpServletRequest request) {
        String productLine = cookieProductLineId;
        if(product.getProductLineId()==null&&product.getProductLineId()<1) {
            product.setProductLineId(Integer.parseInt(productLine));
        }else{
            productLine=String.valueOf(product.getProductLineId());
        }
        product.setProductLineId(Integer.parseInt(cookieProductLineId));
        product.setProductCreatedBy(UserUtils.getUserId());
        product.setProductCreatedDate(new Date());
        product.setProductStatus("0");
        
        product = productService.addProduct(product);
        ProductUtils.removeProductList();
        ProductUtils.removeProductList(productLine);
        ProductUtils.removeAllProductListByUser();
        ProductUtils.removeProductListByProductLineUser(productLine);
        LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCT,
                LogUtil.LogAction.OPENED,
                String.valueOf(product.getProductId()),
                UserUtils.getUserId(),
                String.valueOf(product.getProductId()),
                null,
                null,
                null,
                systemAction.getActionComment());

        return "redirect:" + adminPath + "/product/allproduct/addition";

    }

    @RequestMapping("/update")
    public String update(Product product, HttpServletRequest request, SystemAction systemAction) throws IOException {
        Product product1 = productService.findProduct(product.getProductId());
        productService.updateProduct(product);
        ProductUtils.removeProductList();
        ProductUtils.removeProductList(String.valueOf(
                product.getProductLineId().equals(product1.getProductId())?
                product1.getProductLineId():product.getProductLineId()
                ));
        ProductUtils.removeAllProductListByUser();
        ProductUtils.removeProductListByProductLineUser(String.valueOf(
                product.getProductLineId().equals(product1.getProductId())?
                product1.getProductLineId():product.getProductLineId()
                ));
        LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCT,
                LogUtil.LogAction.EDITED,
                String.valueOf(product.getProductId()),
                UserUtils.getUserId(),
                String.valueOf(product1.getProductId()),
                null,
                product1,
                product,
                systemAction.getActionComment());

        return "redirect:" + "/product/page/tabledemo/product-listall.page";
    }

    @RequestMapping("/edit")
    public String edit(Product product, HttpServletRequest request) {

        productService.updateProduct(product);
        ProductUtils.removeProductList();
        ProductUtils.removeProductList(String.valueOf(product.getProductLineId()));
        return "redirect:" + adminPath + "/product/find/overview?productId=" + product.getProductId();
    }

    @ResponseBody
    @RequestMapping("/sessionset")
    public boolean sessionSet(Integer productId, HttpServletRequest request) {
        if (productId != null) {
            request.getSession().setAttribute("sessionProductId", productId);
            return true;
        } else {
            request.getSession().removeAttribute("sessionProductId");
            return false;
        }

    }
    @ResponseBody
    @RequestMapping("/delete")
    public Map delete(Integer productId, HttpServletRequest request,SystemAction systemAction) {
        Product product1 = productService.findProduct(productId);
        productService.deleteProduct(productId);
        Product product = productService.findProduct(productId);
        ProductUtils.removeProductList();
        ProductUtils.removeProductList(String.valueOf(product.getProductLineId()));
        ProductUtils.removeAllProductListByUser();
        ProductUtils.removeProductListByProductLineUser(String.valueOf(product1.getProductLineId()));
        LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCT,
                LogUtil.LogAction.DELETED,
                String.valueOf(productId),
                UserUtils.getUserId(),
                String.valueOf(product1.getProductId()),
                null,
                product1,
                product,
                systemAction.getActionComment());

        Map<String,String> result = new HashMap<String, String>();
        result.put("status","y");
        return result;
    }

    @RequestMapping("/find")
    public String find(Integer productId, Model model) {

        Product product = productService.findProduct(productId);
        model.addAttribute("product", product);
        return "/product/page/tabledemo/product-module-editor.page";
    }

    @RequestMapping("/find/{forward}")
    public String find(@CookieValue(value = "cookieProductId",defaultValue = "0") String cookieProductId,
            @PathVariable(value = "forward") String forward, Integer productId, Model model, HttpServletRequest request) {
        if("close".equals(forward))return "/product/page/tabledemo/overview-close.pagelet";
        if(productId!=null)cookieProductId=String.valueOf(productId);
        Product product = new Product();
        if(Integer.parseInt(cookieProductId)>0) {
            product = productService.findProduct(Integer.parseInt(cookieProductId));
        }
        SystemHistory history = new SystemHistory();

        model.addAttribute("product", product);

        if ("overview".equals(forward)) {
        	
            return "/product/page/project/overview.page";
        } else if ("baseinfo".equals(forward)) {
            List<ProjectProduct> projectProducts = projectProductService.findProjects(Integer.parseInt(cookieProductId));
            ProjectBuild build = new ProjectBuild();
            build.setBuildProduct(Integer.parseInt(cookieProductId));
            build.setBuildDeleted("0");
            List<ProjectBuild> builds = buildService.findListBuild(build);
            QualityTestCase testCase = new QualityTestCase();
            testCase.setProductId(Integer.parseInt(cookieProductId));
            testCase.setDeleted(0);
            List<QualityTestCase> testCases = testCaseService.findTestCaseList(testCase);
            DocumentDoc documentDoc = new DocumentDoc();
            documentDoc.setDocProduct(Integer.parseInt(cookieProductId));
            documentDoc.setDocDeleted("0");
            List<DocumentDoc> documentDocs = docService.findDocList(documentDoc);
            model.addAttribute("projectSum",projectProducts.size());
            model.addAttribute("buildSum",builds.size());
            model.addAttribute("testCaseSum",testCases.size());
            model.addAttribute("docSum",documentDocs.size());
            if(!StringUtil.isBlank(product.getProductWhiteList())){
                String[] ids = product.getProductWhiteList().split(",");
                List<OrgRole> roles = roleService.getRoleByIds(ids);
                model.addAttribute("whiteLists",roles);
            }
            return "/product/page/tabledemo/baseinfo.pagelet";
        }
        return "";
    }

    @RequestMapping("/list")
    public String list(Product product,String treeId,Integer productLineId,
                       @CookieValue(value = "cookieProductLineId",defaultValue = "0") String cookieProductLineId,
                       @RequestParam(required = false, defaultValue = "1") int page,
                       @RequestParam(required = false, defaultValue = "10") int pagesize,
                       @RequestParam(required = false, defaultValue = "productId") String order,
                       @RequestParam(required = false, defaultValue = "asc") String ordertype, Model model) {

        if(productLineId!=null&&productLineId>0){
            product.setProductLineId(productLineId);
        }else {
            if(!StringUtil.isBlank(cookieProductLineId)) {
                product.setProductLineId(Integer.parseInt(cookieProductLineId));
            }
        }

        product.setDeleted(0);
        Pager<Product> pagerProduct = productService.findProductPager(page, pagesize, product, order, ordertype);

        model.addAttribute("pagerProduct", pagerProduct);
        return  "/product/data/allproductdata.pagelet";
    }

    @RequestMapping("/findManager")
    public String findManager(Integer productId, Model model) {

        if(!StringUtil.isBlank(productService.findProduct(productId).getProductOwner())){
            OrgUser productOwner = userService.findUser(productService.findProduct(productId).getProductOwner());
            model.addAttribute("productOwner", productOwner);
        }
        if(!StringUtil.isBlank(productService.findProduct(productId).getProductQualityManager())){
            OrgUser productQualityManager = userService.findUser(productService.findProduct(productId).getProductQualityManager());
            model.addAttribute("productQualityManager", productQualityManager);
        }
        if(!StringUtil.isBlank(productService.findProduct(productId).getProductDeliveryManager())){
            OrgUser productDeliveryManager = userService.findUser(productService.findProduct(productId).getProductDeliveryManager());
            model.addAttribute("productDeliveryManager", productDeliveryManager);
        }
        return "/organization/userbaseinfo.pagelet";
    }

    @ResponseBody
    @RequestMapping("/productList")
    public List<Product> findProduct(Product product,String type,String productLineId) {
        if("user".equals(type))return ProductUtils.getProductListByProductLineUser(productLineId);

        List<Product> list = productService.findProductList(product);

        return list;
    }


    @RequestMapping("/{forward}/addition")
    public String addpro(
            @PathVariable(value = "forward") String forward,HttpServletRequest request, Model model) {
		if ("addproduct".equals(forward)) {
			return "/product/page/tabledemo/addProduct.page";
		} else if ("allproduct".equals(forward)) {
            return "/product/page/tabledemo/product-listall.page";
        }
		return "";
    }

    @RequestMapping("/addDoc")
    public String addDoc(HttpServletRequest request, Model model) {
       return  "/product/page/tabledemo/add-doc.page";
    }

    @RequestMapping("project/listData")
    public String list(Project project,
                       @CookieValue(value = "cookieProductId", defaultValue = "0") String cookieProductId,
                       @RequestParam(required = false, defaultValue = "0") int start,
                       @RequestParam(required = false, defaultValue = "10") int pagesize,
                       @RequestParam(required = false, defaultValue = "projectId") String order,
                       @RequestParam(required = false, defaultValue = "asc") String ordertype, Model model, HttpServletRequest request) {

        List<ProjectProduct> projects = projectProductService.findProjects(Integer.parseInt(cookieProductId));
        Integer[] ids = new Integer[projects.size()];
        for (int i = 0; i < projects.size(); i++) {
            ids[i] = projects.get(i).getProjectId();
        }
        Pager<Project> pagerProject = projectService.findProjects(start, pagesize, NameUtil.resolveNameDesc(order), ordertype, ids);
        Integer interval = 2;
        if (pagerProject != null) {
            for (Project project1 : pagerProject.getRecords()) {
                BurnDTO burnDTO = burnService.initBurn(project1.getProjectId(), interval);
                project1.setBurnValue(burnDTO.getLeftValues());
            }
        }
        model.addAttribute("projectPager", pagerProject);
        return "/project/listData.pagelet";
    }
}
