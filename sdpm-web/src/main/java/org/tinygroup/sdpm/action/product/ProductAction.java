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
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.ActionService;
import org.tinygroup.sdpm.system.service.inter.HistoryService;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.ProductUtils;
import org.tinygroup.sdpm.util.ProjectUtils;
import org.tinygroup.sdpm.util.UserUtils;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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
    @Autowired
    private TeamService teamService;

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
        if(product.getProductLineId()!=null&&product.getProductLineId()!=0) {
            productLine=String.valueOf(product.getProductLineId());
        }
        product.setProductLineId(Integer.parseInt(productLine));
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
//                       @CookieValue(value = "cookieProductLineId",defaultValue = "0") String cookieProductLineId,
                       @RequestParam(required = false, defaultValue = "1") int page,
                       @RequestParam(required = false, defaultValue = "10") int pagesize,
                       @RequestParam(required = false, defaultValue = "productId") String order,
                       @RequestParam(required = false, defaultValue = "asc") String ordertype, Model model) {

//        if(productLineId!=null&&productLineId>0){
//            product.setProductLineId(productLineId);
//        }else {
//            if(!StringUtil.isBlank(cookieProductLineId)) {
//                product.setProductLineId(Integer.parseInt(cookieProductLineId));
//            }
//        }

//        product.setDeleted(0);
        Map<String,List<Product>> productMap = new HashMap<String, List<Product>>();
        List<Product> products = productService.getProductByUserWithCount(UserUtils.getUserId());
        List<Integer> idList = new ArrayList<Integer>();
        for(Product product1 : products){
            idList.add(product1.getProductLineId());
        }
        Integer[] ids = new Integer[idList.size()];
        List<ProductLine> lines = productLineService.getProductLineByIds(idList.toArray(ids));/*productService.getTeamRoleProductLineIds(UserUtils.getUserId())*/
        for(Product product1:products){
            if(productMap.containsKey(String.valueOf(product1.getProductLineId()))){
                productMap.get(String.valueOf(product1.getProductLineId())).add(product1);
            }else{
                List<Product> ps = new ArrayList<Product>();
                ps.add(product1);
                productMap.put(String.valueOf(product1.getProductLineId()),ps);
            }
        }
        model.addAttribute("productLines",lines);
        model.addAttribute("productMap", productMap);
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
            return "/product/page/tabledemo/addProduct";
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

    @ResponseBody
    @RequestMapping(value = "/judgeProductName")
    public Map judgeProductName(Product product) {
        Integer productId=null;
        if(product.getProductId()!=null){
            productId = product.getProductId();
            product.setProductId(null);
        }
        List<Product> products = productService.findProductList(product);
        if (products.size() >=1) {
            if(products.size()==1&&productId!=null){
                return resultMap(productId.equals(products.get(0).getProductId())?false:true,"");
            }
            return resultMap(true, "该产品已存在");
        } else {
            return resultMap(false, "");
        }
    }
    @RequestMapping("addModule")
    public String toAddModule(Integer pId,Model model){
        model.addAttribute("pId",pId);
        return "/product/page/tabledemo/addModule.pagelet";
    }
    @RequestMapping("team")
    public String team(){
        return "/product/page/team/productTeam.page";
    }
    @RequestMapping("team/teamManage")
    public String deleteTeamMember(@CookieValue(value = "cookieProductId",defaultValue = "0")String productId,
                                   Model model){
        Integer pId = Integer.valueOf(productId);
        if(pId!=0){
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
    public String teamManage(@CookieValue(value = "cookieProductId",defaultValue = "0")String productId,
                             Model model, Integer start, Integer limit, String order, String ordertype){
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
    public String teamManageSave(@CookieValue(value = "cookieProductId",defaultValue = "0")String productId,Teams teams, HttpServletRequest request, HttpServletResponse response) {
        if (productId == "0") {
            return "redirect:"+adminPath+"/product";
        }
        List<ProjectTeam> updateList = new ArrayList<ProjectTeam>();
        List<ProjectTeam> addList = new ArrayList<ProjectTeam>();

        List<ProjectTeam> teamList = teams.getTeamList();
        //删选没有账号的team
        for (int i = 0; i < teamList.size(); i++) {
            if (StringUtil.isBlank(teamList.get(i).getTeamUserId())) {
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
        return "redirect:"+adminPath+"/product/team";
    }
    @RequestMapping("team/nextTr")
    public String getNextTeamTr(Integer a,Model model){
        List<OrgUser> userList = userService.findUserList(null);
        model.addAttribute("userList", userList);
        OrgRole role = new OrgRole();
        role.setDeleted(0);
        role.setOrgRoleType(OrgRole.ROLE_TYPE_PRODUCT);
        List<OrgRole> roleList = roleService.findRoleList(role);
        model.addAttribute("roleList", roleList);
        model.addAttribute("a",a+1);
        return "/product/page/team/teamAddTr.pagelet";
    }
    @ResponseBody
    @RequestMapping("team/del")
    public Map deleteTeamMember(Integer id){
        teamService.delete(id);
        Map<String,String> result = new HashMap<String, String>();
        result.put("status","success");
        return result;
    }
}
