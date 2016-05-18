package org.tinygroup.sdpm.action.quality;

import org.apache.commons.lang.ObjectUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.action.quality.util.QualityUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.dao.complexsearch.SearchInfo;
import org.tinygroup.sdpm.dao.complexsearch.SearchInfos;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.dao.condition.ConditionUtils;
import org.tinygroup.sdpm.dto.UploadProfile;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.service.inter.PlanService;
import org.tinygroup.sdpm.product.service.inter.ProductService;
import org.tinygroup.sdpm.product.service.inter.StoryService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.service.inter.*;
import org.tinygroup.sdpm.quality.dao.pojo.BugCount;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.quality.dao.pojo.QualityCaseStep;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;
import org.tinygroup.sdpm.quality.service.inter.BugService;
import org.tinygroup.sdpm.quality.service.inter.CaseStepService;
import org.tinygroup.sdpm.quality.service.inter.TestCaseService;
import org.tinygroup.sdpm.service.dao.pojo.ServiceRequest;
import org.tinygroup.sdpm.service.service.inter.RequestService;
import org.tinygroup.sdpm.system.dao.pojo.*;
import org.tinygroup.sdpm.system.service.inter.ActionService;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
import org.tinygroup.sdpm.util.*;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


/**
 * Created by chenpeng15668 on 2015-9-22
 */

@Controller
@RequestMapping("/a/quality/bug")
public class BugAction extends BaseController {

    @Autowired
    private BugService bugService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private StoryService storyService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private BuildService buildService;
    @Autowired
    private PlanService planService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProjectProductService projectProductService;
    @Autowired
    private CaseStepService caseStepService;
    @Autowired
    private TestCaseService testCaseService;
    @Autowired
    private RequestService requestService;
    @Autowired
    private ActionService actionService;
    @Autowired
    private ProjectStoryService projectStoryService;

    @ModelAttribute
    public void init(Model model) {
        initSearchBar(model, "Bug");
    }

    @RequestMapping("")
    public String form(QualityBug bug, Model model, HttpServletRequest request) {
        String queryString = request.getQueryString();
        OrgUser user = new OrgUser();
        user.setOrgUserDeleted("0");
        List<OrgUser> users = userService.findUserList(user);
        if (bug != null && bug.getModuleId() != null) {
            request.getSession().setAttribute("bugModuleId", bug.getModuleId());
        } else {
            request.getSession().removeAttribute("bugModuleId");
        }
        if (queryString != null && !queryString.contains("status")) {
            return "redirect:/a/quality/bug?status=tbugstatus&" + queryString;
        }
        if (StringUtil.isBlank(queryString)) {
            return "redirect:/a/quality/bug?status=tbugstatus";
        }
        model.addAttribute("userList", users);
        return "/quality/index/bug/Bug.page";
    }

    @RequestMapping("/findList")
    public String findList(Integer id, Model model) {
        QualityBug bug = new QualityBug();
        bug.setProductId(id);
        List<QualityBug> buglist = bugService.findBugList(bug);
        model.addAttribute("buglist", buglist);
        return "/testManagement/data/BugData.pagelet";
    }

    @RequestMapping("/bugInfo")
    public String bugInfo(Integer bugId, Integer no, Model model, HttpServletRequest request,
                          @CookieValue(value=ProductUtils.COOKIE_PRODUCT_ID)String currentProductId) {
        QualityBug bug = null;
        ConditionCarrier carrier = new ConditionCarrier();
        if (no != null) {
            Integer cookieProductId = Integer.parseInt(CookieUtils.getCookie(request, ProductUtils.COOKIE_PRODUCT_ID));
            if (cookieProductId == null) {
                return notFoundView();
            }
            bug = new QualityBug();
            bug.setProductId(cookieProductId);
            bug.setNo(no);
            List<QualityBug> bugList = bugService.findBugList(bug);
            if (CollectionUtil.isEmpty(bugList)) {
                return notFoundView();
            }
            bug = bugList.get(0);
            bugId = bug.getBugId();
        }
        if (bug == null || bug.getBugId() == null) {
            bug = bugService.findQualityBugById(bugId);
        }
        QualityBug qualityBug = new QualityBug();
        qualityBug.setProductId(bug.getProductId());
        qualityBug.setDeleted(0);
        carrier.put("qualityBug.no",
                ConditionUtils.Operate.GT.getOperate(),
                ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                bug.getNo());
        Pager<QualityBug> bugs = bugService.findBugListPager(0, 1, carrier, qualityBug, "qualityBug.no", false);
        int nextId = 0;
        if (bugs.getRecords().size() > 0) {
            nextId = bugs.getRecords().get(0).getBugId();
        }

        if(bug.getProductId()!=Integer.parseInt(currentProductId))
        {
            return "redirect:"+adminPath+"/quality/bug";
        }

        //获取备注信息
        String actionComment=getBugRemark(bug);
        model.addAttribute("actionComment",actionComment);

        //获取附件信息
        SystemProfile systemProfile = new SystemProfile();
        systemProfile.setFileObjectType("bug");
        systemProfile.setFileDeleted("0");
        systemProfile.setFileObjectId(bug.getBugId());
        List<SystemProfile> list = profileService.findSystemProfile(systemProfile);
        model.addAttribute("file", list);
        model.addAttribute("qualityBug", bug);
        model.addAttribute("nextId", nextId);
        return "/quality/operate/bug/bugInfo.page";
    }

    @RequestMapping("/bugBasicInfo")
    public String bugBasicInfo(Integer bugId, Model model) {
        QualityBug bug = bugService.findQualityBugById(bugId);
        QualityTestCase qualityTestCase = new QualityTestCase();
        qualityTestCase.setCaseFromBug(bugId);
        qualityTestCase.setDeleted(0);
        List<QualityTestCase> cases = testCaseService.findTestCaseList(qualityTestCase);
        if (!StringUtil.isBlank(bug.getBugOpenedBuild())) {
            String[] buildIds = bug.getBugOpenedBuild().split(",");
            List<ProjectBuild> projectBuilds = buildService.getBuildByIds(buildIds);
            List<String> buildIdList = Arrays.asList(buildIds);
            if (buildIdList.contains("0")) {
                model.addAttribute("trunk", "trunk");
            }
            model.addAttribute("openedBuilds", projectBuilds);
        }
        if (!StringUtil.isBlank(bug.getBugAssignedTo())) {
            String[] userIds = bug.getBugAssignedTo().split(",");
            List<OrgUser> assignUsers = userService.findUserListByIds(userIds);
            model.addAttribute("assignUsers", assignUsers);
        }
        model.addAttribute("caseFromBug", cases);
        model.addAttribute("qualityBug", bug);
        return "/quality/rightinfo/bug/bugRightInfo.pagelet";
    }

    @RequestMapping("/findBug")
    public String findBugPager(@CookieValue(value = ProductUtils.COOKIE_PRODUCT_ID, defaultValue = "0") Integer cookieProductId,
                               Integer start,
                               Integer limit,
                               SearchInfos infos,
                               String groupOperate,
                               @RequestParam(defaultValue = "bugId") String order,
                               @RequestParam(defaultValue = "desc") String ordertype,
                               String status,
                               QualityBug bug,
                               Model model,
                               HttpServletRequest request) {
        boolean isSearch = false;
        for (SearchInfo info : infos.getInfos()) {
            if (!StringUtil.isBlank(info.getValue())) {
                isSearch = true;
                break;
            }
        }
        if (isSearch) {
            status = "";
            bug = new QualityBug();
        }
        boolean asc = true;
        if ("desc".equals(ordertype)) {
            asc = false;
        }
        if ("tbuglong".equals(status)) {
            order = "bugOpenedDate";
            ordertype = "desc";
        }
        ConditionCarrier carrier = new ConditionCarrier();
        QualityUtil.getCondition(status, carrier);
        carrier.putSearch("bugSearch", infos, groupOperate);

        bug.setModuleId((Integer) request.getSession().getAttribute("bugModuleId"));
        bug.setProductId(cookieProductId);
        bug.setDeleted(0);
        if (bug.getModuleId() != null) {
            carrier.putModuleIn("moduleId", String.valueOf(bug.getModuleId()));
            bug.setModuleId(null);
        }
        Pager<QualityBug> bugpager = null;
        if ("tbugneedchange".equals(status)) {
            bugpager = bugService.findStoryChangedBugs(start, limit, carrier, bug, order, asc);
        } else {
            bugpager = bugService.findBugListPager(start, limit, carrier, bug, order, asc);
        }
        model.addAttribute("bugpager", bugpager);
        return "/quality/data/bug/BugData.pagelet";
    }

    @RequestMapping("/reportForm")
    public String reportForm() {
        return "/quality/operate/bug/reportform.page";
    }

    @RequiresPermissions("tmakesure")
    @RequestMapping("/makesure")
    public String makeSure(Integer bugId, Model model,@CookieValue(value=ProductUtils.COOKIE_PRODUCT_ID)String currentProductId) {
        QualityBug bug = bugService.findQualityBugById(bugId);

        if(bug.getProductId()!=Integer.parseInt(currentProductId))
        {
            return "redirect:"+adminPath+"/quality/bug";
        }

        List<OrgUser> orgUsers = userService.findUserList(null);

        //读取备注信息
        String actionComment=getBugRemark(bug);
        model.addAttribute("actionComment",actionComment);

        model.addAttribute("bug", bug);
        model.addAttribute("userList", orgUsers);
        return "/quality/operate/bug/makesure.page";
    }

    @RequiresPermissions("tmakesure")
    @ResponseBody
    @RequestMapping("batch/sure")
    public Map makeSure(String ids) {
        String[] bugIds = ids.split(",");
        if (bugIds.length > 0) {
            for (String id : bugIds) {
                QualityBug bug = bugService.findQualityBugById(Integer.valueOf(id));
                if (bug.getBugConfirmed() != null && bug.getBugConfirmed() == 0) {
                    bug.setBugConfirmed(1);
                    bugService.updateBug(bug);
                    LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
                            , LogUtil.LogAction.BUGCONFIRMED
                            , String.valueOf(bug.getBugId())
                            , userUtils.getUserId()
                            , String.valueOf(bug.getProductId())
                            , String.valueOf(bug.getProjectId())
                            , null
                            , null
                            , null);
                }
            }
        }

        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "成功");
        return map;
    }

    @RequiresPermissions("tsolution")
    @ResponseBody
    @RequestMapping("batch/resolve")
    public Map resolve(String ids, String resolutionType) {
        String[] bugIds = ids.split(",");
        if (bugIds.length > 0) {
            for (String id : bugIds) {
                QualityBug bug = bugService.findQualityBugById(Integer.valueOf(id));
                if (bug.getBugStatus() != null && Integer.parseInt(bug.getBugStatus()) == 1) {
                    bug.setBugConfirmed(1);
                    bug.setBugStatus("2");
                    bug.setBugResolution(resolutionType);
                    bug.setBugResolvedDate(new Date());
                    bug.setBugResolvedBy(userUtils.getUserId());
                    bugService.updateBug(bug);
                    LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
                            , LogUtil.LogAction.RESOLVED
                            , String.valueOf(bug.getBugId())
                            , userUtils.getUserId()
                            , String.valueOf(bug.getProductId())
                            , String.valueOf(bug.getProjectId())
                            , null
                            , null
                            , null);
                }
            }
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "成功");
        return map;
    }


    @RequiresPermissions("tassign")
    @ResponseBody
    @RequestMapping("batch/assign")
    public Map assign(String ids, String userId) {
        String[] bugIds = ids.split(",");
        if (bugIds.length > 0) {
            for (String id : bugIds) {
                QualityBug bug = bugService.findQualityBugById(Integer.valueOf(id));
                bug.setBugAssignedDate(new Date());
                bug.setBugAssignedTo(userId);
                bugService.updateBug(bug);
                LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
                        , LogUtil.LogAction.ASSIGNED
                        , String.valueOf(bug.getBugId())
                        , userUtils.getUserId()
                        , String.valueOf(bug.getProductId())
                        , String.valueOf(bug.getProjectId())
                        , null
                        , null
                        , null);
            }
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "成功");
        return map;
    }

    /*
        测试模块批量关闭功能
     */
    @ResponseBody
    @RequiresPermissions("batchshutdownquality")
    @RequestMapping(value = "batch/close")
    public Map close(String ids) {
        String[] bugIds = ids.split(",");
        if (bugIds.length > 0) {
            for (String id : bugIds) {
                QualityBug bug = bugService.findQualityBugById(Integer.valueOf(id));
                bug.setBugClosedBy(userUtils.getUserId());
                bug.setBugClosedDate(new Date());
                bug.setBugStatus("3");
                bugService.updateBug(bug);

                LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
                        , LogUtil.LogAction.CLOSED
                        , String.valueOf(bug.getBugId())
                        , userUtils.getUserId()
                        , String.valueOf(bug.getProductId())
                        , String.valueOf(bug.getProjectId())
                        , null
                        , null
                        , null);
            }

        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "关闭成功");
        return map;

    }

    @RequestMapping("/sure")
    public String makeSure(QualityBug bug, SystemAction systemAction, String lastAddress) {
        QualityBug qualityBugOld = bugService.findQualityBugById(bug.getBugId());
        if (qualityBugOld.getBugAssignedTo() != bug.getBugAssignedTo()) {
            bug.setBugAssignedDate(new Date());
        }
        bug.setBugConfirmed(1);
        bugService.updateBug(bug);


        LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
                , LogUtil.LogAction.BUGCONFIRMED
                , String.valueOf(bug.getBugId())
                , userUtils.getUserId()
                , String.valueOf(bug.getProductId())
                , String.valueOf(bug.getProjectId())
                , qualityBugOld
                , bug
                , systemAction.getActionComment());
        if (!StringUtil.isBlank(lastAddress)) {
            return "redirect:" + lastAddress;
        }
        return "redirect:" + "/a/quality/bug";
    }


//    @ResponseBody
    @RequestMapping("addComment")
    public String recordComment(String actionComment, Integer bugId) {
        QualityBug bug = bugService.findQualityBugById(bugId);
        LogUtil.logWithComment(LogUtil.LogOperateObject.BUG,
                LogUtil.LogAction.REMARK, String.valueOf(bugId),
                userUtils.getUserId(), String.valueOf(bug.getProductId()),
                null, null, null, actionComment);
       /* Map<String, String> result = new HashMap<String, String>();
        result.put("status", "success");
        return result;*/
        return "redirect:" + adminPath
                + "/quality/bug/bugInfo?bugId="
                + bugId;
    }

    @RequiresPermissions("tassign")
    @RequestMapping("/assign")
    public String assign(Integer bugId, Model model,@CookieValue(value=ProductUtils.COOKIE_PRODUCT_ID)String currentProductId) {
        QualityBug bug = bugService.findQualityBugById(bugId);

        if(bug.getProductId()!=Integer.parseInt(currentProductId))
        {
            return "redirect:"+adminPath+"/quality/bug";
        }

        List<OrgUser> users = userService.findUserList(null);

        //读取备注信息
        String actionComment=getBugRemark(bug);
        model.addAttribute("actionComment",actionComment);

        model.addAttribute("bug", bug);
        model.addAttribute("userList", users);
        return "/quality/operate/bug/assign.page";
    }

    @RequestMapping("/assignTo")
    public String assign(QualityBug bug, SystemAction systemAction, String lastAddress) {
        QualityBug qualityBugOld = bugService.findQualityBugById(bug.getBugId());
        bug.setBugAssignedDate(new Date());
        bugService.updateBug(bug);


        LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
                , LogUtil.LogAction.ASSIGNED
                , String.valueOf(bug.getBugId())
                , userUtils.getUserId()
                , String.valueOf(bug.getProductId())
                , String.valueOf(bug.getProjectId())
                , qualityBugOld
                , bug
                , systemAction.getActionComment());
        if (!StringUtil.isBlank(lastAddress)) {
            return "redirect:" + lastAddress;
        }
        return "redirect:" + "/a/quality/bug";
    }

    @RequiresPermissions("tbugdelete")
    @ResponseBody
    @RequestMapping("/delete")
    public Map delete(Integer bugId) {
        QualityBug bug = bugService.findQualityBugById(bugId);
        bugService.deleteBug(bugId);
        LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
                , LogUtil.LogAction.DELETED
                , String.valueOf(bugId)
                , userUtils.getUserId()
                , String.valueOf(bug.getProductId())
                , String.valueOf(bug.getProjectId())
                , null
                , null
                , null);
        return resultMap(true, "删除成功");
    }

    @RequiresPermissions("tsolution")
    @RequestMapping("/toSolve")
    public String solve(Integer bugId, Model model,@CookieValue(value = ProductUtils.COOKIE_PRODUCT_ID)String currentProductId) {
        QualityBug bug = bugService.findQualityBugById(bugId);

        if(bug.getProductId()!=Integer.parseInt(currentProductId))
        {
            return "redirect:"+adminPath+"/quality/bug";
        }

        bug.setBugResolvedDate(new Date());
        List<OrgUser> orgUsers = userService.findUserList(null);
        ProjectBuild build = new ProjectBuild();
        build.setBuildDeleted(ProjectBuild.DELETE_NO);
        build.setBuildProduct(bug.getProductId());
        List<ProjectBuild> builds = buildService.findListBuild(build);
        QualityBug qualityBug = new QualityBug();
        qualityBug.setDeleted(0);
        List<QualityBug> bugList = bugService.findBugList(qualityBug);

        SystemProfile systemProfile = new SystemProfile();
        systemProfile.setFileObjectId(bugId);
        systemProfile.setFileObjectType(ProfileType.BUG.getType());
        List<SystemProfile> fileList = profileService.findSystemProfile(systemProfile);
        model.addAttribute("fileList", fileList);

        //读取备注信息
        String actionComment=getBugRemark(bug);
        model.addAttribute("actionComment",actionComment);

        model.addAttribute("buildList", builds);
        model.addAttribute("userList", orgUsers);
        model.addAttribute("bug", bug);
        model.addAttribute("bugList", bugList);

        return "/quality/operate/bug/solution.page";
    }

    @RequestMapping("/solve")
    public String solve(QualityBug bug,
                        SystemAction systemAction,
                        @RequestParam(value = "file", required = false) MultipartFile[] file,
                        String[] title, UploadProfile uploadProfile, String lastAddress) throws IOException {
        QualityBug qualityBug = bugService.findQualityBugById(bug.getBugId());
        if (qualityBug.getBugAssignedTo() != bug.getBugAssignedTo()) {
            bug.setBugAssignedDate(new Date());
        }
        bug.setBugResolvedBy(userUtils.getUserId() != null ? userUtils.getUserId() : "0");
        bug.setBugStatus("2");
        bugService.updateBug(bug);
        processProfile(uploadProfile, bug.getBugId(), ProfileType.BUG);

        LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
                , LogUtil.LogAction.RESOLVED
                , String.valueOf(bug.getBugId())
                , userUtils.getUserId()
                , String.valueOf(bug.getProductId())
                , String.valueOf(bug.getProjectId())
                , qualityBug
                , bug
                , systemAction.getActionComment());
        if (!StringUtil.isBlank(lastAddress)) {
            return "redirect:" + lastAddress;
        }
        return "redirect:" + "/a/quality/bug";
    }

    @RequiresPermissions("tshutdown")
    @RequestMapping("/toClose")
    public String close(Integer bugId, Model model,@CookieValue(value=ProductUtils.COOKIE_PRODUCT_ID)String currentProductId) {
        QualityBug bug = bugService.findQualityBugById(bugId);

        if(bug.getProductId()!=Integer.parseInt(currentProductId))
        {
            return "redirect:"+adminPath+"/quality/bug";
        }

        bug.setBugClosedDate(new Date());

        //读取备注信息
        String actionComment=getBugRemark(bug);
        model.addAttribute("actionComment",actionComment);

        model.addAttribute("bug", bug);
        return "/quality/operate/bug/shutdown.page";
    }

    @RequestMapping("/close")
    public String close(QualityBug bug, SystemAction systemAction, String lastAddress) {
        QualityBug qualityBug = bugService.findQualityBugById(bug.getBugId());
        bug.setBugClosedBy(userUtils.getUserId());
        bug.setBugClosedDate(new Date());
        bug.setBugStatus("3");
        bugService.updateBug(bug);

        LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
                , LogUtil.LogAction.CLOSED
                , String.valueOf(bug.getBugId())
                , userUtils.getUserId()
                , String.valueOf(bug.getProductId())
                , String.valueOf(bug.getProjectId())
                , qualityBug
                , bug
                , systemAction.getActionComment());

        if (!StringUtil.isBlank(lastAddress)) {
            return "redirect:" + lastAddress;
        }
        return "redirect:" + "/a/quality/bug";
    }

    @RequiresPermissions("tedition")
    @RequestMapping("/toEdit")
    public String edit(Integer bugId, Model model,@CookieValue(value=ProductUtils.COOKIE_PRODUCT_ID)String currentProductId) {
        QualityBug bug = bugService.findQualityBugById(bugId);

        if(bug.getProductId()!=Integer.parseInt(currentProductId))
        {
            return "redirect:"+adminPath+"/quality/bug";
        }


        //读取备注信息
        String actionComment=getBugRemark(bug);
        model.addAttribute("actionComment",actionComment);

        //读取文档信息
        SystemProfile systemProfile = new SystemProfile();
        systemProfile.setFileObjectId(bug.getBugId());
        systemProfile.setFileObjectType(ProfileType.BUG.getType());
        List<SystemProfile> fileList = profileService.findSystemProfile(systemProfile);
        model.addAttribute("fileList", fileList);

        model.addAttribute("bug", bug);
        return "/quality/operate/bug/edition";
    }

    //读取备注信息
    private String getBugRemark(QualityBug qualityBug)
    {
        SystemAction systemAction=new SystemAction();
        systemAction.setActionObjectId(qualityBug.getBugId().toString());
        systemAction.setActionObjectType("bug");
        List<SystemAction> actions = actionService.findAction(systemAction, "actionId", false);//false表示倒序
        if(actions.size()==0)
        {
            return "";
        }
        return actions.get(0).getActionComment();//0表示降序排列后的第一条，即为最新那一条
    }

    @RequestMapping("/edit")
    public String edit(QualityBug bug, SystemAction systemAction,
                       String lastAddress, UploadProfile uploadProfile) throws IOException {

        QualityBug qualityBug = bugService.findQualityBugById(bug.getBugId());

        bug.setBugLastEditedBy(userUtils.getUserId() != null ? userUtils.getUserId() : "0");
        bug.setBugLastEditedDate(new Date());
        bugService.updateBug(bug);

        processProfile(uploadProfile, bug.getBugId(), ProfileType.BUG);

        LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
                , LogUtil.LogAction.EDITED
                , String.valueOf(bug.getBugId())
                , userUtils.getUserId()
                , String.valueOf(bug.getProductId())
                , String.valueOf(bug.getProjectId())
                , qualityBug
                , bug
                , systemAction.getActionComment());
        if (!StringUtil.isBlank(lastAddress)) {
            return "redirect:" + lastAddress;
        }
        return "redirect:" + "/a/quality/bug";
    }

    @RequestMapping("/editionPaging")
    public String editionPaging(Integer bugId, Model model) {
        Product product = new Product();
        product.setProductId(bugId);
        List<Product> products = productService.findProductList(product);
        QualityBug bug = bugService.findQualityBugById(bugId);
        Project p = new Project();
        p.setProjectDeleted("0");
        List<Project> projects = projectService.findProjectList(p, null, null);
        List<OrgUser> orgUsers = userService.findUserList(null);
        model.addAttribute("bugProductList", products);
        model.addAttribute("projectList", projects);
        model.addAttribute("userList", orgUsers);
        model.addAttribute("bug", bug);
        return "/quality/rightinfo/bug/editionpaging.pagelet";
    }

    @RequiresPermissions("bug-add")
    @RequestMapping("/add")
    public String add(Model model,Integer projectId) {
        List<Project> projects = projectService.getUserProjectList(UserUtils.getUserId());
        List<OrgUser> orgUsers = userService.findUserList(null);
        List<ProductStory> storyList = projectStoryService.findStoryByProject(projectId);
        model.addAttribute("projectList", projects);
        model.addAttribute("userList", orgUsers);
        model.addAttribute("projectId", projectId);
        model.addAttribute("storyList", storyList);
        return "/quality/operate/bug/proposeBug.page";
    }

    @RequiresPermissions("bug-add")
    @RequestMapping("/toCopy")
    public String copy(Integer bugId, Model model,@CookieValue(value=ProductUtils.COOKIE_PRODUCT_ID)String currentProductId) {
        QualityBug bug = bugService.findQualityBugById(bugId);

        if(bug.getProductId()!=Integer.parseInt(currentProductId))
        {
            return "redirect:"+adminPath+"/quality/bug";
        }

        List<Project> projects = projectService.findProjectList(null, null, null);
        List<OrgUser> orgUsers = userService.findUserList(null);
        model.addAttribute("userList", orgUsers);
        model.addAttribute("projectList", projects);
        model.addAttribute("bug", bug);

//        SystemProfile systemProfile = new SystemProfile();
//        systemProfile.setFileObjectId(bugId);
//        systemProfile.setFileObjectType(ProfileType.BUG.getType());
//        List<SystemProfile> fileList = profileService.findSystemProfile(systemProfile);
//        model.addAttribute("fileList", fileList);
        return "/quality/operate/bug/copyBug.page";
    }

    @ResponseBody
    @RequestMapping("/ajax/project")
    public List<Project> getProject(ProjectProduct projectProduct) {
        if (projectProduct.getProductId() == null/*||projectProduct.getProductId() < 1*/) {
            return new ArrayList<Project>();
        }
        List<ProjectProduct> projectProducts = projectProductService.findProjectByProductId(projectProduct.getProductId());
        List<Integer> ids = new ArrayList<Integer>();
        for (ProjectProduct projectProduct1 : projectProducts) {
            ids.add(projectProduct1.getProjectId());
        }
        List<Project> projects = projectService.findByProjectList(ids);
        return projects == null ? new ArrayList<Project>() : projects;
    }

    @ResponseBody
    @RequestMapping("/ajax/plan")
    public List<ProductPlan> getPlan(ProductPlan productPlan) {
        if (productPlan.getProductId() < 1) {
            return new ArrayList<ProductPlan>();
        }
        List<ProductPlan> moduleList = planService.findPlanList(productPlan);
        return moduleList;
    }

    @ResponseBody
    @RequestMapping("/ajax/module")
    public List<SystemModule> getModule(SystemModule systemModule) {
        if (systemModule.getModuleRoot() < 1) {
            return new ArrayList<SystemModule>();
        }
        systemModule.setModuleType("story");
        List<SystemModule> moduleList = moduleService.findModuleList(systemModule);
        for (SystemModule module : moduleList) {
            module.setModuleName(ModuleUtil.getPath(module.getModuleId(), "/", null, false));
        }
        return moduleList;
    }

    @ResponseBody
    @RequestMapping("/ajax/story")
    public List<ProductStory> getStory(ProductStory productStory) {
        if (productStory.getProductId() < 1) {
            return new ArrayList<ProductStory>();
        }
        if (productStory.getModuleId() == 0) {
            productStory.setModuleId(null);
        }
        return storyService.findStoryListByOrder(productStory, null, null);
    }

    @ResponseBody
    @RequestMapping("/ajax/task")
    public List<ProjectTask> getStory(ProjectTask projectTask) {
        if (projectTask.getTaskProject() < 1) {
            return new ArrayList<ProjectTask>();
        }
        return taskService.findListTask(projectTask);
    }

    @ResponseBody
    @RequestMapping("/ajax/build")
    public List<ProjectBuild> getBuild(ProjectBuild projectBuild) {
        if (projectBuild.getBuildProduct() < 1 || projectBuild.getBuildProduct() == null) {
            return new ArrayList<ProjectBuild>();
        }
        projectBuild.setBuildDeleted("0");
        return buildService.findListBuild(projectBuild);
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public String copySave(QualityBug bug, SystemAction systemAction, HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile[] file,
                           UploadProfile uploadProfile) throws IOException {
        bug.setBugConfirmed(0);
        bug.setBugStatus("1");
        bug.setDeleted(0);
        bug.setBugOpenedDate(new Date());
        bug.setBugActivatedCount(0);
        if (bug.getStoryId() != null) {
            bug.setStoryVersion(storyService.findStory(bug.getStoryId()).getStoryVersion());
        }
        bug.setBugOpenedBy(userUtils.getUserId() != null ? userUtils.getUserId() : "0");
        bug = bugService.addBug(bug);
        processProfile(uploadProfile, bug.getBugId(), ProfileType.BUG);
        LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
                , LogUtil.LogAction.OPENED
                , String.valueOf(bug.getBugId())
                , userUtils.getUserId()

                , String.valueOf(bug.getProductId())
                , String.valueOf(bug.getProjectId())
                , null
                , null
                , systemAction.getActionComment());
        return "redirect:" + "/a/quality/bug";
    }

    @RequestMapping(value = "/save")
    public String save(QualityBug bug, SystemAction systemAction,
                       HttpServletRequest request,
                       String lastAddress,
                       String currentAddress,
                       UploadProfile uploadProfile) throws IOException {

        if (!StringUtil.isBlank(bug.getBugAssignedTo())) {
            bug.setBugAssignedDate(new Date());
        }
        bug.setBugConfirmed(0);
        bug.setDeleted(0);
        bug.setBugOpenedDate(new Date());
        bug.setBugActivatedCount(0);
        bug.setBugOpenedBy(userUtils.getUserId() != null ? userUtils.getUserId() : "0");
        if (bug.getStoryId() != null) {
            bug.setStoryVersion(storyService.findStory(bug.getStoryId()).getStoryVersion());
        }
        QualityBug qBug = bugService.addBug(bug);

        processProfile(uploadProfile, qBug.getBugId(), ProfileType.BUG);

        LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
                , LogUtil.LogAction.OPENED
                , String.valueOf(qBug.getBugId())
                , userUtils.getUserId()
                , String.valueOf(bug.getProductId())
                , String.valueOf(bug.getProjectId())
                , null
                , null
                , systemAction.getActionComment());


        if (!StringUtil.isBlank(currentAddress)) {
            return "redirect:" + currentAddress;
        }
        if (StringUtil.isBlank(currentAddress)&&!StringUtil.isBlank(lastAddress)) {
            if (lastAddress.contains("add"))
                return "redirect:" + "/a/quality/bug";
            return "redirect:" + lastAddress;
        }

        return "redirect:" + "/a/quality/bug";
    }

    @RequestMapping("/projectFindList")
    public String projectFindList(Model model, Integer projectId, Integer start, Integer limit, String order, String ordertype,
                                  HttpServletRequest request, HttpServletResponse response) {
        if (projectId == null) {
            projectId = projectOperate.getCurrentProjectId(request, response);
        }
        if (projectId == null) {
            return redirectProjectForm();
        }
        boolean asc = "asc".equals(ordertype) ? true : false;
        QualityBug bug = new QualityBug();
        bug.setProjectId(projectId);
        bug.setDeleted(0);
        bug.setBugStatus(QualityBug.STATUS_CLOSED);
        Pager<QualityBug> bugPage = bugService.findBugListPager(start, limit, null, bug, order, asc);
        model.addAttribute("bugPage", bugPage);
        return "project/data/bug/bugViewTableData.pagelet";
    }

    //批量删除（软） 产品下面的计划、发布关联BUG表上使用的
    @ResponseBody
    @RequestMapping(value = "/batchDelete")
    public Map bctchDelBug(String ids) {
        Map<String, String> map = new HashMap<String, String>();
        if (ids == null) {
            map.put("status", "fail");
            map.put("info", "删除失败");
            return map;
        }
        List<QualityBug> list = new ArrayList<QualityBug>();
        for (String s : ids.split(",")) {
            QualityBug bug = new QualityBug();
            bug.setBugId(Integer.valueOf(s));
            bug.setDeleted(1);
            list.add(bug);
        }
        bugService.batchDeleteBug(list);
        for (QualityBug bug : list) {
            LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
                    , LogUtil.LogAction.DELETED
                    , String.valueOf(bug.getBugId())
                    , userUtils.getUserId()
                    , String.valueOf(bug.getProductId())
                    , String.valueOf(bug.getProjectId())
                    , null
                    , null
                    , null);
        }
        map.put("status", "success");
        map.put("info", "删除成功");
        return map;
    }


    //批量删除（软） 产品下面的计划、发布关联BUG表上使用的
    @ResponseBody
    @RequestMapping(value = "/batchDelBugStory")
    public Map batchBugDelStory(String ids) {
        Map<String, String> map = new HashMap<String, String>();
        if (ids == null) {
            map.put("status", "fail");
            map.put("info", "删除失败");
            return map;
        }
        for (String s : ids.split(",")) {
            QualityBug bug = new QualityBug();
            bug.setBugId(Integer.valueOf(s));
            bug.setPlanId(0);
            bugService.updateBug(bug);
        }

        map.put("status", "success");
        map.put("info", "删除成功");
        return map;
    }

    //产品计划、发布关联BUG下面的搜索按钮使用
    @RequestMapping("/linkBug")
    public String bugAction(ProductPlan plan, QualityBug bug, String groupOperate, Model model, HttpServletRequest request, HttpServletResponse response) {
        String queryString = request.getQueryString();
   /*    if(queryString!=null&&!queryString.contains("choose")){
            return "redirect:/product/story?choose=1&"+queryString;
        }*/
        return "/product/plan/forword/noRelateBug?planId" + plan.getPlanId();
    }

    @RequestMapping("/searchBug")
    public String SearchAction(@CookieValue String cookieProductId, int start, int limit, ProductStory story, String choose, String groupOperate, SearchInfos searchInfos, String order, String ordertype, Model model) {

        story.setProductId(Integer.parseInt(cookieProductId));
        ConditionCarrier carrier = new ConditionCarrier();
        carrier.putSearch("storySearch", searchInfos, groupOperate);
        StoryUtil.getStatusCondition(choose, carrier);

        Pager<ProductStory> p = storyService.findStoryPagerRel(start, limit, story, carrier, order, "asc".equals(ordertype) ? true : false);
        model.addAttribute("storyList", p);
        return "product/data/story/tabledata.pagelet";
    }

    @RequiresPermissions("linkbug")
    @ResponseBody
    @RequestMapping("/ajaxUpdate")
    public Map deleteRel(QualityBug bug) {
        bugService.updateBug(bug);
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "成功");

        return map;
    }

    @ResponseBody
    @RequestMapping("/updateBatch")
    public boolean updateBatch(@RequestBody QualityBug[] bugs) {
        if (bugs == null || bugs.length == 0) {
            return false;
        }
        for (QualityBug bug : bugs) {
            bugService.updateBug(bug);
        }
        return true;
    }

    @RequestMapping("toBug")
    public String toBug(Integer[] ids, Integer caseId, Model model) {
        StringBuffer bugStep = new StringBuffer("");
        for (Integer id : ids) {
            QualityCaseStep step = caseStepService.findCaseStepById(id);
            bugStep.append("【步骤】<br>");
            bugStep.append(step.getCaseStepDesc() + "<br>");
            bugStep.append("【期望】<br>");
            bugStep.append(step.getCaseStepExpect() + "<br>");
            bugStep.append("【结果】<br>");
        }
        List<Project> projects = projectService.findProjectList(null, null, null);
        List<OrgUser> orgUsers = userService.findUserList(null);
        model.addAttribute("bugStep", bugStep);
        model.addAttribute("projectList", projects);
        model.addAttribute("userList", orgUsers);
        model.addAttribute("fromCase", caseId);
        return "/quality/operate/bug/proposeBug.page";
    }


    @RequestMapping("/getReport")
    public String report(
            @CookieValue(ProductUtils.COOKIE_PRODUCT_ID) String cookieProductId,
            String checkItem, Model model, HttpServletRequest request) {

        Map<String, List<BugCount>> map = bugService.bugReport(checkItem, Integer.parseInt(cookieProductId));
        model.addAttribute("map", map);
        model.addAttribute("fields", checkItem);
        return "/quality/operate/bug/reportform.page";
    }

    @RequestMapping("toReactive")
    public String toReactive() {
        return "/quality/modal/bug/product-demand-reactive.page";
    }

    @RequestMapping("/reactive")
    public String reactive(Integer bugId, String actionComment) {
        QualityBug bug = bugService.findQualityBugById(bugId);
        if (bug.getBugActivatedCount() != null) {
            bug.setBugActivatedCount(bug.getBugActivatedCount() + 1);
        } else {
            bug.setBugActivatedCount(1);
        }
        bug.setBugStatus("1");
        bugService.updateBug(bug);
        LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
                , LogUtil.LogAction.ACTIVATED
                , String.valueOf(bug.getBugId())
                , userUtils.getUserId()
                , String.valueOf(bug.getProductId())
                , String.valueOf(bug.getProjectId())
                , null
                , null
                , actionComment);
        return "redirect:/a/quality/bug";
    }

    @ResponseBody
    @RequestMapping("ajax/bugInCondition")
    public List<QualityBug> bugInCondition(String key, Integer initKey, Integer productId, HttpServletRequest request) {
        if (initKey != null) {
            List<QualityBug> result = new ArrayList<QualityBug>();
            result.add(bugService.findQualityBugById(initKey));
            return result;
        }
        return bugService.bugInCondition(key, Integer.parseInt(configService.getConfigBySection(SystemConfig.SEARCH_CONFIG).getConfigKey()), productId);
    }

    @RequestMapping("requestToBug")
    public String requestToBug(Integer id, Model model) {
        ServiceRequest request = requestService.findRequest(id);
        StringBuffer bugStep = new StringBuffer("【请求】<br>");
        bugStep.append(request.getRequestSpec());
        bugStep.append("【步骤】<br>");
        bugStep.append("【期望】<br>");
        bugStep.append("【结果】<br>");
        List<Project> projects = projectService.findProjectList(null, null, null);
        List<OrgUser> orgUsers = userService.findUserList(null);

        model.addAttribute("bugStep", bugStep);
        model.addAttribute("bugTitle", request.getRequestTitle() + "【请求】");
        model.addAttribute("projectList", projects);
        model.addAttribute("userList", orgUsers);
        return "/quality/operate/bug/proposeBug.page";
    }

    @ResponseBody
    @RequestMapping(value = "/bugTitleCheck")
    public Map userNameCheck(@RequestParam("param") String bugTitle, Integer productId) {
        String status=QualityBug.STATUS_CLOSED;
        List<QualityBug> list=bugService.findBugByProductIdAndBugTitle(bugTitle,productId,status);
        if(!CollectionUtil.isEmpty(list)) {
            for (QualityBug bug : list) {
                if (ObjectUtils.equals(bugTitle, bug.getBugTitle())) {
                    return resultMap(false, "Bug标题重复！");
                }
            }
        }
        return resultMap(true, "Bug标题可用！");
    }

    @ResponseBody
    @RequestMapping("/judgeBugNameExist")
    public Map judgeBugNameExist(@RequestParam("param") String bugTitle,String oldBugTitle,Integer productId)
    {
        if(bugTitle==null)
        {
            return resultMap(false,"请输入标题");
        }

        if(ObjectUtils.equals(bugTitle,oldBugTitle))
        {
            return resultMap(true,"");
        }

        QualityBug bug=new QualityBug();
        String status=QualityBug.STATUS_CLOSED;
        bug.setProductId(productId);
        bug.setDeleted(Integer.parseInt(QualityBug.STATUS_ACTIVE));
        bug.setBugTitle(bugTitle);
        List<QualityBug> bugList=bugService.findBugByProductIdAndBugTitle(bugTitle,productId,status);
        if(bugList.size()==0)
        {
            return resultMap(true,"标题可用");
        }

        return resultMap(false,"标题已存在");
    }

}
