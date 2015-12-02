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
import org.tinygroup.sdpm.dto.project.Teams;
import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.RoleService;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.productLine.service.ProductLineService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.sdpm.project.service.dto.BurnDTO;
import org.tinygroup.sdpm.project.service.inter.*;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;
import org.tinygroup.sdpm.quality.service.inter.TestCaseService;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemHistory;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.UserUtils;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 产品控制器
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
    private ProductLineService productLineService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BuildService buildService;
    @Autowired
    private TestCaseService testCaseService;
    @Autowired
    private BurnService burnService;
    @Autowired
    private DocService docService;
    @Autowired
    private ProjectProductService projectProductService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TeamService teamService;
    @ModelAttribute
    public void init(Model model) {
        initSearchBar(model, "产品");
    }

    @RequestMapping("/{forward}/content")
    public String doc(@PathVariable(value = "forward") String forward, HttpServletRequest request, Model model) {

        if ("doc".equals(forward)) {
            return "/product/page/list/doc/archive-list.page";
        } else if ("project".equals(forward)) {
            return "/product/page/list/project/product-project-list.page";
        } else if ("dynamic".equals(forward)) {
            return "/product/page/list/dynamic/dymanicdata.page";
        }
        return "";
    }


    @RequestMapping("")
    public String productAction(@CookieValue(value = "cookieProductLineId", defaultValue = "0") String cookieProductLineId, HttpServletResponse response, HttpServletRequest request) {
        if ("0".equals(cookieProductLineId)) {
            productUtils.prepareForFirst(response);
        }
        return "redirect:" + adminPath + "/product/story?choose=1" + (request.getQueryString() == null? "" : ("&" + request.getQueryString()));
    }


    @RequestMapping("/save")
    public String save(Product product, SystemAction systemAction, HttpServletRequest request, String lastAddress) {


        product.setProductCreatedBy(userUtils.getUserId());
        product.setProductCreatedDate(new Date());
        product.setProductStatus("0");

        product = productService.addProduct(product);
        productUtils.removeProductList();
        productUtils.removeAllProductListByUser();
        LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCT,
                LogUtil.LogAction.OPENED,
                String.valueOf(product.getProductId()),
                userUtils.getUserId(),
                String.valueOf(product.getProductId()),
                null,
                null,
                null,
                systemAction.getActionComment());
        if(!StringUtil.isBlank(lastAddress)){
            return "redirect:"+lastAddress;
        }

        return "redirect:" + adminPath + "/product/allproduct/addition";

    }

    @RequestMapping("/update")
    public String update(Product product, HttpServletRequest request, SystemAction systemAction) throws IOException {
        Product product1 = productService.findProduct(product.getProductId());
        productService.updateProduct(product);
        productUtils.removeProductList();
        productUtils.removeProductList(String.valueOf(
                product.getProductLineId().equals(product1.getProductId()) ?
                        product1.getProductLineId() : product.getProductLineId()
        ));
        productUtils.removeAllProductListByUser();
        productUtils.removeProductListByProductLineUser(String.valueOf(
                product.getProductLineId().equals(product1.getProductId()) ?
                        product1.getProductLineId() : product.getProductLineId()
        ));
        LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCT,
                LogUtil.LogAction.EDITED,
                String.valueOf(product.getProductId()),
                userUtils.getUserId(),
                String.valueOf(product1.getProductId()),
                null,
                product1,
                product,
                systemAction.getActionComment());

        return "/product/page/list/product/product-listall.page";
    }

    @RequestMapping("/edit")
    public String edit(Product product, HttpServletRequest request, String lastAddress) {

        productService.updateProduct(product);
        productUtils.removeProductList();
        productUtils.removeProductList(String.valueOf(product.getProductLineId()));
        if(!StringUtil.isBlank(lastAddress)){
            return "redirect:"+lastAddress;
        }
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
    public Map delete(Integer productId, HttpServletRequest request, SystemAction systemAction) {
        Product product1 = productService.findProduct(productId);
        productService.deleteProduct(productId);
        Product product = productService.findProduct(productId);
/*
        productUtils.removeProductList();
        productUtils.removeProductList(String.valueOf(product.getProductLineId()));
        productUtils.removeAllProductListByUser();
        productUtils.removeProductListByProductLineUser(String.valueOf(product1.getProductLineId()));
*/
        LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCT,
                LogUtil.LogAction.DELETED,
                String.valueOf(productId),
                userUtils.getUserId(),
                String.valueOf(product1.getProductId()),
                null,
                product1,
                product,
                systemAction.getActionComment());

        Map<String, String> result = new HashMap<String, String>();
        result.put("status", "y");
        return result;
    }

    @RequestMapping("/find")
    public String find(Integer productId, Model model) {

        Product product = productService.findProduct(productId);
        model.addAttribute("product", product);
        return "/product/page/update/module/product-module-editor.page";
    }

    @RequestMapping("/find/{forward}")
    public String find(@CookieValue(value = "cookieProductId", defaultValue = "0") String cookieProductId,
                       @PathVariable(value = "forward") String forward, Integer productId, Model model, HttpServletRequest request) {
        if ("close".equals(forward)) return "/product/page/operate/product/overview-close.pagelet";
        if (productId != null) cookieProductId = String.valueOf(productId);
        Product product = new Product();
        if (Integer.parseInt(cookieProductId) > 0) {
            product = productService.findProduct(Integer.parseInt(cookieProductId));
            if(product.getProductId()==null){
                return notFoundView();
            }
        }
        SystemHistory history = new SystemHistory();

        model.addAttribute("product", product);

        if ("overview".equals(forward)) {

            return "/product/page/view/product/overview.page";
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
            model.addAttribute("projectSum", projectProducts.size());
            model.addAttribute("buildSum", builds.size());
            model.addAttribute("testCaseSum", testCases.size());
            model.addAttribute("docSum", documentDocs.size());
            if (!StringUtil.isBlank(product.getProductWhiteList())) {
                String[] ids = product.getProductWhiteList().split(",");
                List<OrgRole> roles = roleService.getRoleByIds(ids);
                model.addAttribute("whiteLists", roles);
            }
            return "/product/page/view/product/baseinfo.pagelet";
        }
        return "";
    }

    @RequestMapping("/list")
    public String list(String choose,Model model) {
        Integer deleted = null;
        if("open".equals(choose)){
            deleted=0;
        }else if("deleted".equals(choose)){
            deleted=1;
        }
        model.addAttribute("productMap", productService.getUserProductsWithCountMap(UserUtils.getUserId(),deleted));
        return "/product/data/product/allproductdata.pagelet";
    }

    @RequestMapping("/findManager")
    public String findManager(Integer productId, Model model) {

        if (!StringUtil.isBlank(productService.findProduct(productId).getProductOwner())) {
            OrgUser productOwner = userService.findUser(productService.findProduct(productId).getProductOwner());
            model.addAttribute("productOwner", productOwner);
        }
        if (!StringUtil.isBlank(productService.findProduct(productId).getProductQualityManager())) {
            OrgUser productQualityManager = userService.findUser(productService.findProduct(productId).getProductQualityManager());
            model.addAttribute("productQualityManager", productQualityManager);
        }
        if (!StringUtil.isBlank(productService.findProduct(productId).getProductDeliveryManager())) {
            OrgUser productDeliveryManager = userService.findUser(productService.findProduct(productId).getProductDeliveryManager());
            model.addAttribute("productDeliveryManager", productDeliveryManager);
        }
        return "/organization/userbaseinfo.pagelet";
    }

    @ResponseBody
    @RequestMapping("/productList")
    public List<Product> findProduct(Product product, String type, String productLineId) {
        if ("user".equals(type)) return productUtils.getProductListByProductLineUser(productLineId);

        List<Product> list = productService.findProductList(product);

        return list;
    }


    @RequestMapping("/{forward}/addition")
    public String addpro(
            @PathVariable(value = "forward") String forward, HttpServletRequest request, Model model) {
        if ("addproduct".equals(forward)) {
            List<ProductLine> productLineList = productLineService.getUserProductLine(userUtils.getUserId());
            model.addAttribute("productLineList",productLineList);
            return "/product/page/add/product/addProduct";
        } else if ("allproduct".equals(forward)) {
            return "/product/page/list/product/product-listall.page";
        }
        return "";
    }

    @RequestMapping("/addDoc")
    public String addDoc(HttpServletRequest request, Model model) {
        return "/product/page/add/add-doc";
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

    @ResponseBody
    @RequestMapping(value = "/judgeProductName")
    public Map judgeProductName(String param,Integer productId,Integer productLineId) {
        if (param != null) {
            String productName = param;
            Product product = new Product();
            product.setProductName(productName);
            product.setProductLineId(productLineId);
            List<Product> productList = productService.findProductList(product);
            if (productList.size() != 0) {
                if(productId==null){
                    return resultMap(false, "该产品已存在");
                }else if(!productId.equals(productList.get(0).getProductId())){
                    return resultMap(false, "该产品已存在");
                }else{
                    return resultMap(true, "");
                }
            } else {
                return resultMap(true, "");
            }
        }
        return resultMap(false, "请输入产品名称");
    }

    @ResponseBody
    @RequestMapping(value = "/judgeProductCode")
    public Map judgeProductCode(String param,Integer productId,Integer productLineId) {
        if (param != null) {
            String productCode = param;
            Product product = new Product();
            product.setProductCode(productCode);
            product.setProductLineId(productLineId);
            List<Product> productList = productService.findProductList(product);
            if (productList.size() != 0) {
                if(productId==null){
                    return resultMap(false, "该产品编号已存在");
                }else if(!productId.equals(productList.get(0).getProductId())){
                    return resultMap(false, "该产品编号已存在");
                }else{
                    return resultMap(true, "");
                }
            } else {
                return resultMap(true, "");
            }
        }
        return resultMap(false, "请输入产品编号");
    }

    @RequestMapping("addModule")
    public String toAddModule(Integer pId, Model model) {
        model.addAttribute("pId", pId);
        return "/product/page/add/module/addModule.pagelet";
    }

    @RequestMapping("team")
    public String team() {
        return "/product/page/team/productTeam.page";
    }

    @RequestMapping("team/teamManage")
    public String deleteTeamMember(@CookieValue(value = "cookieProductId", defaultValue = "0") String productId,
                                   Model model) {
        Integer pId = Integer.valueOf(productId);
        if (pId != 0) {
            List<ProjectTeam> teamList = teamService.findTeamByProductId(pId);
            model.addAttribute("teamList", teamList);
            String[] ids = new String[teamList.size()];

            List<OrgUser> userList = userService.findUserList(null);
            model.addAttribute("userList", userList);
            OrgRole role = new OrgRole();
            role.setDeleted(0);
            role.setOrgRoleType(OrgRole.ROLE_TYPE_PRODUCT);
            List<OrgRole> roleList = roleService.findRoleList(role);
            model.addAttribute("roleList", roleList);
        }
        return "/product/page/team/teamManage.page";
    }

    @RequestMapping("team/teamData")
    public String teamManage(@CookieValue(value = "cookieProductId", defaultValue = "0") String productId,
                             Model model, Integer start, Integer limit, String order, String ordertype) {
        Integer pId = Integer.valueOf(productId);
        if (pId != 0) {
            ProjectTeam team = new ProjectTeam();
            team.setProductId(pId);
            Pager<ProjectTeam> pager = teamService.findPager(team, start, limit, order, ordertype);
            model.addAttribute("teamPager", pager);
        }
        return "/product/page/team/teamData.pagelet";
    }

    @RequestMapping("/teamSave")
    public String teamManageSave(@CookieValue(value = "cookieProductId", defaultValue = "0") String productId, Teams teams, HttpServletRequest request, HttpServletResponse response) {
        if (productId == "0") {
            return "redirect:" + adminPath + "/product";
        }
        List<ProjectTeam> updateList = new ArrayList<ProjectTeam>();
        List<ProjectTeam> addList = new ArrayList<ProjectTeam>();

        List<ProjectTeam> teamList = teams.getTeamList();
        //删选没有账号的team
        for (int i = 0; i < teamList.size(); i++) {
            if (StringUtil.isBlank(teamList.get(i).getTeamUserId())||StringUtil.isBlank(teamList.get(i).getTeamRole())) {
                teamList.remove(teamList.get(i));
                i--;
            }
        }
        //根据是否有teamId分为增加列表和更新列表
        for (ProjectTeam team : teamList) {
            if (team.getId() != null) {
                team.setProductId(Integer.valueOf(productId));
                updateList.add(team);
            } else {
                team.setProjectId(0);
                team.setProductId(Integer.valueOf(productId));
                if (team.getTeamDays() == null) {
                    team.setTeamDays((float) 0.0);
                }
                if (team.getTeamHours() == null) {
                    team.setTeamHours((float) 0.0);
                }
                addList.add(team);
            }
        }
        teamService.batchAdd(addList);
        teamService.batchUpdate(updateList);
        return "redirect:" + adminPath + "/product/team";
    }

    @RequestMapping("team/nextTr")
    public String getNextTeamTr(Integer a, Model model) {
        List<OrgUser> userList = userService.findUserList(null);
        model.addAttribute("userList", userList);
        OrgRole role = new OrgRole();
        role.setDeleted(0);
        role.setOrgRoleType(OrgRole.ROLE_TYPE_PRODUCT);
        List<OrgRole> roleList = roleService.findRoleList(role);
        model.addAttribute("roleList", roleList);
        model.addAttribute("a", a + 1);
        return "/product/page/team/teamAddTr.pagelet";
    }

    @ResponseBody
    @RequestMapping("team/del")
    public Map deleteTeamMember(Integer id) {
        teamService.delete(id);
        Map<String, String> result = new HashMap<String, String>();
        result.put("status", "success");
        return result;
    }

    @ResponseBody
    @RequestMapping("ajax/productInCondition")
    public List<Product> productInCondition(String key, String initKey, HttpServletRequest request){
        if(initKey!=null){
            if(initKey.indexOf(",")>0){
                String[] ids = initKey.split(",");
                Integer[] iIds = new Integer[ids.length];
                for(int i = 0;i<iIds.length; i++){
                    iIds[i] = Integer.parseInt(ids[i]);
                }
                return productService.findProductListByIds(iIds);
            }
            List<Product> result = new ArrayList<Product>();
            result.add(productService.findProduct(Integer.parseInt(initKey)));
            return result;
        }
        List<Product> products = productService.getProductByUser(userUtils.getUserId(),0,null);
        Integer[] pIds = new Integer[products.size()];
        for(int i =0; i<pIds.length; i++){
            pIds[i] = products.get(i).getProductId();
        }
        return productService.productInCondition(key,pIds);
    }
}
