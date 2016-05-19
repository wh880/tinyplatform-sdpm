package org.tinygroup.sdpm.action.product;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.commons.tools.ArrayUtil;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.dao.complexsearch.SearchInfos;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.dao.condition.ConditionUtils;
import org.tinygroup.sdpm.dto.UploadProfile;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.*;
import org.tinygroup.sdpm.product.dao.utils.FieldUtil;
import org.tinygroup.sdpm.product.service.inter.*;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.sdpm.project.service.inter.*;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;
import org.tinygroup.sdpm.quality.service.inter.BugService;
import org.tinygroup.sdpm.quality.service.inter.TestCaseService;
import org.tinygroup.sdpm.service.dao.pojo.ServiceRequest;
import org.tinygroup.sdpm.service.service.inter.RequestService;
import org.tinygroup.sdpm.system.dao.pojo.*;
import org.tinygroup.sdpm.system.service.inter.ActionService;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.ProductUtils;
import org.tinygroup.sdpm.util.ProjectOperate;
import org.tinygroup.sdpm.util.StoryUtil;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * 需求的控制器
 * Created by xubin、wangjie14929
 */
@Controller
@RequestMapping("/a/product/story")
public class StoryAction extends BaseController {
    @Autowired
    private StoryService storyService;
    @Autowired
    private StorySpecService storySpecService;
    @Autowired
    private BugService bugService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private UserService userService;
    @Autowired
    private PlanService planService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TestCaseService testCaseService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ReleaseService releaseService;
    @Autowired
    private RequestService requestService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private BuildService buildService;
    @Autowired
    private ActionService actionService;


    /**
     * @param request
     * @return
     */
    @RequestMapping("")
    public String storyAction(HttpServletRequest request,@CookieValue(value = "cookieProductId", defaultValue = "0") String cookieProductId,Model model) {
        String queryString = request.getQueryString();
        List<ProjectTeam> teams = teamService.findTeamByProductId(Integer.parseInt(cookieProductId));
        String[] ids = new String[teams.size()];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = teams.get(i).getTeamUserId();
        }
        List<OrgUser> orgUsers = userService.findUserListByIds(ids);
        model.addAttribute("orgUsers", orgUsers);
        if (queryString != null && !queryString.contains("choose")) {
            return "redirect:" + adminPath + "/product/story?choose=1&"
                    + queryString;
        }
        if (queryString == null) {
            return "redirect:" + adminPath + "/product/story?choose=1";
        }

        return "/product/page/list/story/story.page";
    }

    /**
     * 提需求的跳转
     *
     * @param model
     * @return
     */
    @RequiresPermissions("product-demand-add")
    @RequestMapping("/addstory")
    public String addStory(Model model,
                           String lastAddress, String moduleId, Integer storyId,@CookieValue(required = false, value = ProjectOperate.COOKIE_PROJECT_ID) String currentProjectId) {
        List<ServiceRequest> requests = requestService.getRequestList(null);
        model.addAttribute("requestList", requests);
        model.addAttribute("moduleId", moduleId);
        if (!StringUtil.isBlank(lastAddress)) {
            return "redirect:" + lastAddress;
        }
        return "/product/page/add/story/product-demand-add.page";
    }


    /**
     * 保存新的需求同时实现附件上传和记录历史
     *
     * @param systemAction
     * @param productStory
     * @param storySpec
     * @return
     * @throws IOException
     */
    @RequestMapping("/save")
    public String save(
            SystemAction systemAction,
            String type,
            ProductStory productStory,
            ProductStorySpec storySpec,
            UploadProfile uploadProfile,
            String lastAddress,
            String currentAddress) throws IOException {
        ProductStory story = storyService.addStory(productStory, storySpec, userUtils.getUserId());
        processProfile(uploadProfile, story.getStoryId(), ProfileType.STORY);

        if ("copy".equals(type)) {
            LogUtil.logWithComment(LogUtil.LogOperateObject.STORY,
                    LogUtil.LogAction.COPY,
                    String.valueOf(story.getStoryId()),
                    userUtils.getUserId(),
                    String.valueOf(story.getProductId()),
                    null,
                    null,
                    null,
                    systemAction.getActionComment());

            if (!StringUtil.isBlank(lastAddress)) {
                return "redirect:" + lastAddress;
            }

            return "redirect:" + adminPath + "/product/storySpec/find/productDemandDetail?storyId=" + story.getStoryId();
        } else {
            LogUtil.logWithComment(LogUtil.LogOperateObject.STORY,
                    LogUtil.LogAction.OPENED, String.valueOf(story.getStoryId()),
                    userUtils.getUserId(), String.valueOf(story.getProductId()),
                    null, null, null, systemAction.getActionComment());
            if (!StringUtil.isBlank(currentAddress)) {
                return "redirect:" + currentAddress;
            } else {
                if (!StringUtil.isBlank(lastAddress)) {
                    return "redirect:" + lastAddress;
                }
            }
            return "redirect:" + adminPath + "/product/story?choose=1&currentPageId=3";
        }
    }

    @ResponseBody
    @RequestMapping("/ajax/user")
    public List<OrgUser> getUser(@CookieValue(value = "cookieProductId", defaultValue = "0") String cookieProductId) {
        List<ProjectTeam> teams = teamService.findTeamByProductId(Integer.parseInt(cookieProductId));
        String[] ids = new String[teams.size()];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = teams.get(i).getTeamUserId();
        }
        List<OrgUser> orgUsers = userService.findUserListByIds(ids);
        return orgUsers == null ? new ArrayList<OrgUser>() : orgUsers;
    }

    @ResponseBody
    @RequestMapping("/ajax/plan")
    public List<ProductPlan> getPlan(ProductPlan productPlan) {
        if (productPlan.getProductId() == null || productPlan.getProductId() < 1) return new ArrayList<ProductPlan>();
        productPlan.setDeleted(0);
        List<ProductPlan> productPlans = planService.findPlanList(productPlan);
        return productPlans == null ? new ArrayList<ProductPlan>() : productPlans;
    }

    /**
     * 实现编辑的保存同时记录编辑历史
     *
     * @param systemAction
     * @param productStory
     * @return
     * @throws IOException
     */
    @RequestMapping("/update")
    public String update(SystemAction systemAction, ProductStory productStory,
                         String lastAddress, UploadProfile uploadProfile) throws IOException {
        ProductStory story = storyService.findStory(productStory.getStoryId());
        productStory.setStoryLastEditedBy(userUtils.getUser().getOrgUserId());
        productStory.setStoryLastEditedDate(new Date());
        if (story.getStoryStatus() == "1") {
            if (productStory.getPlanId() != null && productStory.getPlanId() > 0) {
                productStory.setStoryStage("2");
            }
        }
        processProfile(uploadProfile, productStory.getStoryId(), ProfileType.STORY);

        if(productStory.getModuleId()==null)
        {
            productStory.setModuleId(0);
        }
        if(productStory.getPlanId()==null)
        {
            productStory.setPlanId(0);
        }

        storyService.updateStory(productStory);

        LogUtil.logWithComment(LogUtil.LogOperateObject.STORY,
                LogUtil.LogAction.EDITED,
                String.valueOf(productStory.getStoryId()),
                userUtils.getUserId(), String.valueOf(story.getProductId()),
                null, story, productStory, systemAction.getActionComment());
        if (!StringUtil.isBlank(lastAddress)) {
            return "redirect:" + lastAddress;
        }
        return "redirect:" + "/a/product/story";
    }

    /**
     * 跳转到需求主页
     *
     * @return
     */
    @RequestMapping("/list")
    public String list() {
        return "/product/page/list/story/story.page";
    }

    /**
     * 实现对需求的批量编辑
     *
     * @param stories
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateBatch")
    public boolean updateBatch(@RequestBody ProductStory[] stories) {
        if (stories == null || stories.length == 0) {
            return false;
        }
        List<ProductStory> storyList = new ArrayList<ProductStory>();
        if (stories[0].getStoryStatus() != null) {
            String status = stories[0].getStoryStatus();
            for (int i = 0; i < stories.length; i++) {
                ProductStory story = storyService.findStory(stories[i].getStoryId());
                if (Integer.parseInt(story.getStoryStatus()) == 0 || Integer.parseInt(story.getStoryStatus()) == 3) {
                    story.setStoryStatus(status);
                    storyList.add(story);
                }
            }
        } else {
            storyList = Arrays.asList(stories);
        }

        for (ProductStory story : storyList) {
            storyService.updateStory(story);
        }
        return true;
    }

    /**
     * 批量关闭
     *
     * @param stories
     * @return
     */
    @RequestMapping("/closeBatch")
    public String closeBatch(StoryCollection stories) {

        storyService.updateBatchStory(stories.getProductStories());
        return "redirect:" + adminPath + "/product/story?currentPageId=3";
    }

    /**
     * 软删除并记录删除历史
     *
     * @param story
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Map delete(ProductStory story) {
        story.setDeleted(FieldUtil.DELETE_YES);
        story.setStoryClosedBy(userUtils.getUserId());
        story.setStoryClosedDate(new Date());
        storyService.deleteStory(story);
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "删除成功");

        LogUtil.logWithComment(LogUtil.LogOperateObject.STORY,
                LogUtil.LogAction.DELETED, String.valueOf(story.getStoryId()),
                userUtils.getUserId(), String.valueOf(story.getProductId()),
                null, null, null, null);

        return map;
    }

    @ResponseBody
    @RequestMapping("/ajaxUpdate")
    public Map deleteRel(ProductStory story) {

        storyService.updateStory(story);
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "成功");

        LogUtil.logWithComment(LogUtil.LogOperateObject.STORY,
                LogUtil.LogAction.DELETED, String.valueOf(story.getStoryId()),
                userUtils.getUserId(), String.valueOf(story.getProductId()),
                null, null, null, null);

        return map;
    }

    /**
     * 查询需求页面的右边模块的信息
     *
     * @param storyId
     * @param model
     * @return
     */
    @RequestMapping("/find")
    public String find(Integer storyId, Model model) {

        ProductStory productStory = storyService.findStory(storyId);
        List<ServiceRequest> requests = requestService.getRequestList(null);
        model.addAttribute("requestList", requests);
        model.addAttribute("story", productStory);
        return "/product/page/update/story/editbaseinfo.pagelet";
    }

    /**
     * 批量关闭
     */
    @ResponseBody
    @RequestMapping("/batchclose")
    public Map batchclose(SystemAction systemAction,String ids) {
        String[] storyIds = ids.split(",");
        if(storyIds.length>0) {
            for (String id : storyIds) {
                ProductStory productStory = storyService.findStory(Integer.valueOf(id));
                productStory.setStoryClosedBy(userUtils.getUserId());
                productStory.setStoryClosedDate(new Date());
                productStory.setDeleted(FieldUtil.DELETE_YES);
                productStory.setStoryStatus("2");
                storyService.deleteStory(productStory);
                LogUtil.logWithComment(LogUtil.LogOperateObject.STORY,
                        LogUtil.LogAction.CLOSED,
                        String.valueOf(productStory.getStoryId()),
                        userUtils.getUserId(), String.valueOf(productStory.getProductId()),
                        null, productStory, productStory, systemAction.getActionComment());
            }
        }

        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "关闭成功");
        return map;
        //return "/product/page/list/story/story.page";
    }

    @RequestMapping("/{forwordPager}/findPager")
    public String find(Integer storyId,
                       @PathVariable(value = "forwordPager") String forwordPager,
                       Model model,
                       @CookieValue(value= ProductUtils.COOKIE_PRODUCT_ID)String currentProductId) {
        ProductStory productStory = storyService.findStory(storyId);
        ProductStorySpec storySpec = new ProductStorySpec();
        storySpec.setStoryId(storyId);
        storySpec.setStoryVersion(productStory.getStoryVersion());
        List<ProductStorySpec> storySpecs = storySpecService.findStorySpecList(storySpec, null, null);
        storySpec = storySpecs != null && storySpecs.size() > 0 ? storySpecs.get(0) : new ProductStorySpec();
        List<ProductStory> stories = storyService.findProductName(storyId);

        QualityTestCase testCase = new QualityTestCase();
        testCase.setStoryId(storyId);
        List<QualityTestCase> testCaseList = testCaseService
                .findTestCaseList(testCase);

        QualityBug bug = new QualityBug();
        bug.setStoryId(storyId);
        List<QualityBug> bugList = bugService.findBugList(bug);
        if (!StringUtil.isBlank(productStory.getStoryMailto())) {
            List<OrgUser> userList = userService.findUserListByIds(productStory.getStoryMailto().split(","));
            model.addAttribute("storyMailTo", userList);
        }
        List<Project> projectList = projectService.getProjectByStoryId(storyId);

        model.addAttribute("story", productStory);
        model.addAttribute("storySpec", storySpec);
        model.addAttribute("testCaseList", testCaseList);
        model.addAttribute("bugList", bugList);
        model.addAttribute("projectList", projectList);
        model.addAttribute("stories", stories);

        //读取备注信息
        String actionComment=getStoryRemark(productStory);

        if(productStory.getProductId()!=Integer.parseInt(currentProductId))
        {
            return "redirect:" + adminPath + "/product/story";
        }

        if ("productDemandClose".equals(forwordPager))
        {
            model.addAttribute("actionComment",actionComment);
            return "/product/page/operate/story/product-demand-close.page";
        }
        if ("productDemandReview".equals(forwordPager))
        {
            model.addAttribute("actionComment",actionComment);
            return "/product/page/operate/story/product-demand-review.page";
        }
        if ("productDemandChange".equals(forwordPager))
        {
            model.addAttribute("actionComment",actionComment);

            //读取文档信息
            SystemProfile systemProfile = new SystemProfile();
            systemProfile.setFileObjectId(storyId);
            systemProfile.setFileObjectType(ProfileType.STORY.getType());
            List<SystemProfile> fileList = profileService.findSystemProfile(systemProfile);
            model.addAttribute("fileList", fileList);
            return "/product/page/operate/story/product-demand-change.page";
        }
        if ("productDemandDetail".equals(forwordPager))
        {

            ProjectTask task = new ProjectTask();
            task.setTaskStory(storyId);
            List<ProjectTask> taskList = taskService.findListTask(task);
            model.addAttribute("taskList", taskList);

            QualityTestCase testCase1 = new QualityTestCase();
            testCase1.setDeleted(0);
            testCase1.setStoryId(storyId);
            List<QualityTestCase> testCases = testCaseService.findTestCaseList(testCase1);
            model.addAttribute("caseList", testCases);


            if (!StringUtil.isBlank(productStory.getStoryLinkStories())) {
                String storyLinkStories = productStory.getStoryLinkStories();
                List<Integer> idList = new ArrayList<Integer>();
                String[] split = storyLinkStories.split(",");
                if (ArrayUtil.isEmptyArray(split)) {
                    idList.add(Integer.parseInt(storyLinkStories));
                } else {
                    for (String s : split) {
                        idList.add(Integer.parseInt(s));
                    }
                }
                List<ProductStory> storyList = storyService.findStoryListByIds(idList.toArray(new Integer[0]));
                model.addAttribute("storyList", storyList);
            }
            return "/product/page/view/story/hrefbaseinfo.pagelet";
        }

        return "";
    }

    /**
     * 获得需求备注信息
     */
    public String getStoryRemark(ProductStory productStory)
    {
        SystemAction systemAction=new SystemAction();
        systemAction.setActionObjectId(productStory.getStoryId().toString());
        systemAction.setActionObjectType("story");
        List<SystemAction> actions = actionService.findAction(systemAction, "actionId", false);
        if(actions.size()==0)
        {
            return "";
        }
        return actions.get(0).getActionComment();//0表示降序排列后的第一条，即为最新那一条
    }

    /**
     * 需求变更同时记录变更历史
     *
     * @param systemAction
     * @param productStory
     * @param storySpec
     * @param file
     * @param title
     * @return
     * @throws IOException
     */
    @RequestMapping("/changed")
    public String changed(
            SystemAction systemAction,
            ProductStory productStory,
            ProductStorySpec storySpec,
            @RequestParam(value = "file", required = false) MultipartFile[] file,
            String[] title,
            UploadProfile uploadProfile) throws IOException {
        Integer maxVersion = storySpecService.getStoryMaxVersion(productStory.getStoryId());
        ProductStory story = storyService.findStory(productStory.getStoryId());
        if (productStory.getStoryReviewedBy().equals("0")) {
            productStory.setStoryStatus("1");
        } else {
            productStory.setStoryStatus("3");
        }
        productStory.setStoryVersion(maxVersion == null ? 1 : maxVersion + 1);
        storyService.updateStory(productStory);
        storySpec.setStoryVersion(productStory.getStoryVersion());
        storySpecService.addProductStorySpec(storySpec);
        processProfile(uploadProfile, productStory.getStoryId(), ProfileType.STORY);

        LogUtil.logWithComment(LogUtil.LogOperateObject.STORY,
                LogUtil.LogAction.CHANGED,
                String.valueOf(story.getStoryId()),
                userUtils.getUserId(),
                String.valueOf(story.getProductId()), null, story,
                productStory, systemAction.getActionComment());
        return "redirect:" + "/a/product/story";
    }

    /**
     * 需求评审并记录评审历史
     *
     * @param systemAction
     * @param productStory
     * @return
     * @throws IOException
     */
    @RequestMapping("/reviewed")
    public String reviewed(SystemAction systemAction, Integer reviewRequest, ProductStory productStory)
            throws IOException {
        switch (reviewRequest) {
            case 0:
                productStory.setStoryStatus("1");
                break;
            case 1:
                productStory.setStoryStatus("0");
                break;
            case 2:
                productStory.setStoryStatus("2");
                break;
            case 3:
                productStory.setStoryStatus("4");
                break;
        }
        ProductStory story = storyService.findStory(productStory.getStoryId());
        storyService.updateStory(productStory);
        LogUtil.logWithComment(LogUtil.LogOperateObject.STORY,
                LogUtil.LogAction.REVIEWED,
                String.valueOf(productStory.getStoryId()),
                userUtils.getUserId(),
                String.valueOf(story.getProductId()), null, story,
                productStory, systemAction.getActionComment());
        return "redirect:" + "/a/product/story";
    }

    /**
     * 关闭需求并记录了关闭的备注历史
     *
     * @param systemAction
     * @param productStory
     * @return
     */
    @RequestMapping("/close")
    public String close(SystemAction systemAction, ProductStory productStory) {
        ProductStory story = storyService.findStory(productStory.getStoryId());
        productStory.setStoryClosedBy(userUtils.getUserId());
        productStory.setStoryClosedDate(new Date());
        productStory.setDeleted(FieldUtil.DELETE_YES);
        productStory.setStoryStatus("2");
        storyService.deleteStory(productStory);
        LogUtil.logWithComment(LogUtil.LogOperateObject.STORY,
                LogUtil.LogAction.CLOSED,
                String.valueOf(productStory.getStoryId()),
                userUtils.getUserId(), String.valueOf(story.getProductId()),
                null, story, productStory, systemAction.getActionComment());
        return "redirect:" + "/a/product/story";
    }

    /**
     * 实现了需求的搜索按钮功能
     *
     * @param start        当前页
     * @param pagesize     每页数量
     * @param story        需求对象
     * @param choose
     * @param groupOperate 搜索信息
     * @param searchInfos
     * @param order
     * @param ordertype
     * @param model
     * @return
     */
    @RequestMapping("/search")
    public String storySearchAction(@CookieValue(value = "cookieProductId", defaultValue = "0") String cookieProductId,
                                    int start,
                                    int pagesize,
                                    ProductStory story,
                                    String type,
                                    String choose,
                                    String moduleId,
                                    String groupOperate,
                                    SearchInfos searchInfos,
                                    @RequestParam(required = false, defaultValue = "storyId") String order,
                                    @RequestParam(required = false, defaultValue = "desc") String ordertype,
                                    Model model) {
        if (!"2".equals(choose) && !"11".equals(choose) && !"6".equals(choose)) {
            story.setDeleted(0);
        }
        if (story.getProductId() == null || story.getProductId() == 0) {
            story.setProductId(Integer.parseInt(cookieProductId));
        }
        ConditionCarrier carrier = new ConditionCarrier();
        StoryUtil.getStatusCondition(choose, carrier);
        if (story.getModuleId() != null && story.getModuleId() > 0) {
            carrier.putModuleIn("productStory.moduleId", String.valueOf(story.getModuleId()));
        }
        if (!StringUtil.isBlank(moduleId)) {
            carrier.putModuleIn("productStory.moduleId", String.valueOf(story.getModuleId()));
        }
        story.setModuleId(null);

        if ("noCase".equals(type)) {
            QualityTestCase testCase = new QualityTestCase();
            testCase.setProductId(story.getProductId());
            List<Integer> list = testCaseService.getStoryIds(testCase);
            String[] ids = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                ids[i] = String.valueOf(list.get(i));
            }
            carrier.putNotIns("productStory.storyId", ids);
        }
        carrier.putSearch("search", searchInfos, groupOperate);

        Pager<ProductStory> p = storyService.findStoryByCondition(start, pagesize, story, carrier, order, "asc".equals(ordertype) ? true : false);
        model.addAttribute("storyList", p);

        return "product/data/story/tabledata.pagelet";
    }

    /**
     * 计划、发布里的关联需求、未关联需求数据查找和搜索功能的实现
     *
     * @param relate
     * @param start
     * @param pagesize
     * @param story
     * @param groupOperate
     * @param searchInfos
     * @param order
     * @param ordertype
     * @param model
     * @return
     */
    @RequestMapping("/search/{relate}")
    public String storyListAction(@CookieValue("cookieProductId") String cookieProductId,
                                  @PathVariable(value = "relate") String relate,
                                  int start,
                                  int pagesize,
                                  String type,
                                  Integer releaseId,
                                  ProductStory story,
                                  String groupOperate,
                                  SearchInfos searchInfos,
                                  @RequestParam(required = false, defaultValue = "storyId") String order,
                                  @RequestParam(required = false, defaultValue = "desc") String ordertype,
                                  Model model) {


        story.setProductId(Integer.parseInt(cookieProductId));

        ConditionCarrier carrier = new ConditionCarrier();
        if (searchInfos != null) {
            carrier.putSearch("storySearch", searchInfos, groupOperate);
        }
        if ("noRelate".equals(type)) {
            carrier.put("productStory.planId",
                    ConditionUtils.Operate.NEQ.getOperate(),
                    ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                    story.getPlanId());
            story.setPlanId(null);
        }
        Pager<ProductStory> p = storyService.findStoryByCondition(start,
                pagesize, story, carrier, order,
                "asc".equals(ordertype) ? true : false);
        model.addAttribute("storyList", p);
        if (releaseId != null && releaseId > 0) {
            ProductRelease release = releaseService.findRelease(releaseId);
            if (release != null) {
                String releaseStories = release.getReleaseStories();
                String inCondition = StringUtil.isBlank(releaseStories) ? "" : releaseStories;
                if ("noWork".equals(type)) {
                    if (!StringUtil.isBlank(inCondition)) {
                        carrier.putNotIns("productStory.storyId", inCondition.split(","));
                    }
                    p = storyService.findProjectLinkedStory(start,
                            pagesize, story, carrier, order,
                            "asc".equals(ordertype) ? true : false);
                } else {
                    if (StringUtil.isBlank(inCondition)) {
                        p = new Pager<ProductStory>(0, 0, new ArrayList<ProductStory>());
                    } else {
                        carrier.putIns("productStory.storyId", inCondition.split(","));
                        p = storyService.findStoryByCondition(start,
                                pagesize, story, carrier, order,
                                "asc".equals(ordertype) ? true : false);
                    }
                }
                model.addAttribute("storyList", p);
            }
        }
        if ("reRelateStory".equals(relate)) {
            return "/product/data/plan/product-al-req-data.pagelet";
        } else if ("noRelateStory".equals(relate)) {
            return "/product/data/plan/product-al-no-req-data.pagelet";
        } else if ("reRelateStoryRelease".equals(relate)) {
            return "/product/data/release/product-al-req-data.pagelet";
        } else if ("noRelateStoryRelease".equals(relate)) {
            return "/product/data/release/product-al-no-req-data.pagelet";
        }
        return "";
    }

    /**
     * 计划、发布里的关联BUG和未关联BUG的查找和搜索功能实现
     *
     * @param relate      占位符
     * @param page        当前页数
     * @param pagesize    每页数量
     * @param bug         bug对象
     * @param searchInfos 搜索按钮内搜素信息
     * @param order       排列顺序
     * @param ordertype   排序依据
     * @param model
     * @return 返回数据集合
     */
    @RequestMapping("/bugSearch/{relate}")
    public String bugListAction(@CookieValue(value = "cookieProductId", defaultValue = "0") String cookieProductId, @PathVariable(value = "relate") String relate,
                                int page,
                                int pagesize,
                                Integer releaseId,
                                QualityBug bug,
                                SearchInfos searchInfos,
                                String groupOperate,
                                @RequestParam(required = false, defaultValue = "bugId") String order,
                                @RequestParam(required = false, defaultValue = "desc") String ordertype,
                                Model model) {
        if (Integer.parseInt(cookieProductId) > 0) {
            bug.setProductId(Integer.parseInt(cookieProductId));
        }
        ConditionCarrier carrier = new ConditionCarrier();
        if (searchInfos != null) {
            carrier.putSearch("bugSearch", searchInfos, groupOperate);
        }
        if ("noRelateBug".equals(relate)) {
            carrier.put("qualityBug.planId",
                    ConditionUtils.Operate.NEQ.getOperate(),
                    ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                    bug.getPlanId());
            bug.setPlanId(null);
        }
        Pager<QualityBug> p = bugService.findBugListPager(
                pagesize * (page - 1), pagesize, carrier, bug, order,
                "asc".equals(ordertype) ? true : false);
        model.addAttribute("bugList", p);
        if (releaseId != null && releaseId > 0) {
            ProductRelease release = releaseService.findRelease(releaseId);
            if (release != null) {
                String[] ids = release.getReleaseBuild().split(",");
                ProjectBuild build = buildService.findBuild(Integer.parseInt(ids[0]));
                bug.setProductId(build.getBuildProduct());
                bug.setBugOpenedBuild(release.getReleaseBuild());
                String releaseBugs = release.getReleaseBugs();
                String inCondition = StringUtil.isBlank(releaseBugs) ? "" : releaseBugs;
                if ("reRelateBugRelease".equals(relate)) {
                    if (StringUtil.isBlank(inCondition)) {
                        p = new Pager<QualityBug>(0, 0, new ArrayList<QualityBug>());
                    } else {
                        carrier.putIns("qualityBug.bugId", inCondition.split(","));
                        p = bugService.findBugListPager(
                                pagesize * (page - 1), pagesize, carrier, bug, order,
                                "asc".equals(ordertype) ? true : false);
                    }
                } else if ("noRelateBugRelease".equals(relate)) {
                    bug.setBugStatus("2");
                    if (!StringUtil.isBlank(inCondition)) {
                        carrier.putNotIns("qualityBug.bugId", inCondition.split(","));
                    }
                    p = bugService.findBugListPager(
                            pagesize * (page - 1), pagesize, carrier, bug, order,
                            "asc".equals(ordertype) ? true : false);
                } else {
                    bug.setBugStatus("1");
                    p = bugService.findBugListPager(
                            pagesize * (page - 1), pagesize, carrier, bug, order,
                            "asc".equals(ordertype) ? true : false);
                }
                model.addAttribute("bugList", p);
            }

        }

        if ("reRelateBug".equals(relate)) {
            return "/product/data/plan/product-al-bug-data.pagelet";
        } else if ("noRelateBug".equals(relate)) {
            return "/product/data/plan/product-al-no-bug-data.pagelet";
        } else if ("reRelateBugRelease".equals(relate)) {
            return "/product/data/release/product-al-bug-data.pagelet";
        } else if ("noRelateBugRelease".equals(relate)) {
            return "/product/data/release/product-al-no-bug-data.pagelet";
        } else if ("leRelateBugRelease".equals(relate)) {
            return "/product/data/release/product-al-le-bug-data.pagelet";
        }
        return "";
    }

    /**
     * 实现需求表单的需求选择
     *
     * @param story
     * @return
     */
    @ResponseBody
    @RequestMapping("/storyList")
    public List<ProductStory> findStory(ProductStory story) {
        if (story.getProductId() == null || story.getProductId() == 0) return new ArrayList<ProductStory>();
        Integer storyId = null;
        if (story.getStoryId() != null) {
            storyId = story.getStoryId();
            story.setStoryId(null);
        }
        story.setDeleted(0);
        List<ProductStory> list = storyService.findStoryList(story);
        if (storyId != null) {
            for (ProductStory productStory : list) {
                if (productStory.getStoryId() == storyId) {
                    list.remove(productStory);
                    break;
                }
            }
        }
        return list;
    }

    /**
     * 用于产品需求下面的模块树，实现对需求的分类整理
     *
     * @param check
     * @return
     */
    @ResponseBody
    @RequestMapping("/data")
    public List data(String check) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<SystemModule> moduleList = moduleService
                .findModuleList(new SystemModule());
        if (check == null || !check.equals("n")) {
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("id", -1);
            map1.put("pId", 0);
            map1.put("name", "所有部门");
            list.add(map1);
        }

        for (SystemModule d : moduleList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", d.getModuleId());
            map.put("pId", d.getModuleParent());
            map.put("open", true);
            map.put("add", true);
            map.put("edit", true);
            map.put("name", d.getModuleName());
            list.add(map);
        }
        return list;
    }

    /**
     * 生成报表数据
     *
     * @param story
     * @param chexkitem
     * @param model
     * @return
     */
    @RequestMapping("/report")
    public String report(
            @CookieValue("cookieProductId") String cookieProductId,
            ProductStory story, String chexkitem, Model model, HttpServletRequest request) {

        story.setProductId(Integer.parseInt(cookieProductId));
        Map<String, List<StoryCount>> map = storyService.StoryCountReport(chexkitem, story);
        model.addAttribute("map", map);
        model.addAttribute("fields", chexkitem);
        return "/product/page/report/product-report.page";
    }

    /**
     * 需求的批量删除
     *
     * @param ids
     * @return 用于需求的批量删除
     */
    @ResponseBody
    @RequestMapping(value = "/batchDelete")
    public Map bctchDelStory(String ids) {
        Map<String, String> map = new HashMap<String, String>();
        if (ids == null) {
            map.put("status", "fail");
            map.put("info", "删除失败");
            return map;
        }
        List<ProductStory> list = new ArrayList<ProductStory>();
        for (String s : ids.split(",")) {
            ProductStory story = new ProductStory();
            story.setStoryId(Integer.valueOf(s));
            story.setDeleted(1);
            list.add(story);
        }
        storyService.deleteBatchStory(list);
        for (ProductStory story : list) {
            LogUtil.logWithComment(LogUtil.LogOperateObject.STORY
                    , LogUtil.LogAction.DELETED
                    , String.valueOf(story.getProductId())
                    , userUtils.getUserId()
                    , String.valueOf(story.getProductId())
                    , null
                    , null
                    , null
                    , null);
        }

        map.put("status", "success");
        map.put("info", "删除成功");
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/batchDelPlanStory")
    public Map bctchPlanDelStory(String ids) {
        Map<String, String> map = new HashMap<String, String>();
        if (ids == null) {
            map.put("status", "fail");
            map.put("info", "删除失败");
            return map;
        }
        for (String s : ids.split(",")) {
            ProductStory story = new ProductStory();
            story.setStoryId(Integer.valueOf(s));
            story.setPlanId(0);
            storyService.updateStory(story);
        }


        map.put("status", "success");
        map.put("info", "删除成功");
        return map;
    }

    /**
     * @param forwordPager 占位符判断
     * @return 产品报表页面
     */
    @RequestMapping("/toPager/{forwordPager}")
    public String toPager(
            @PathVariable(value = "forwordPager") String forwordPager) {

        if ("storyReport".equals(forwordPager)) {
            return "product/page/report/product-report.page";
        }
        return "";
    }

    /**
     * 用于需求概况的纪录备注
     *
     * @param story        需求对象
     * @param systemAction 备注纪录
     * @return 回到概况页面
     */
    @RequestMapping("/remark")
    public String remark(ProductStory story, SystemAction systemAction,Model model) {
        LogUtil.logWithComment(LogUtil.LogOperateObject.STORY,
                LogUtil.LogAction.REMARK, String.valueOf(story.getStoryId()),
                userUtils.getUserId(), String.valueOf(story.getProductId()),
                null, null, null, systemAction.getActionComment());

        return "redirect:" + adminPath
                + "/product/storySpec/find/productDemandDetail?storyId="
                + story.getStoryId();

    }

    /**
     * 请求转需求
     *
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("product-demand-add")
    @RequestMapping("requestToStory")
    public String requestToStory(Integer id, Model model) {
        ServiceRequest request = requestService.findRequest(id);
        ProductStory story = new ProductStory();
        story.setStoryTitle(request.getRequestTitle());
        ProductStorySpec storySpec = new ProductStorySpec();
        storySpec.setStorySpec(request.getRequestSpec());
        List<ServiceRequest> requests = requestService.getRequestList(null);
        model.addAttribute("requestList", requests);
        model.addAttribute("story", story);
        model.addAttribute("storySpec", storySpec);
        model.addAttribute("request", request);
        return "/product/page/add/story/product-demand-add.page";
    }

    /**
     * 查找出要复制需求的信息
     *
     * @param storyId
     * @param model
     * @return
     */
    @RequestMapping("/findcopy")
    public String findcopy(Integer storyId, Model model) {
        ProductStory productStory = storyService.findStory(storyId);
        ProductStorySpec storySpec = storySpecService.findStorySpec(storyId, productStory.getStoryVersion());
        List<ServiceRequest> requests = requestService.getRequestList(null);
        model.addAttribute("requestList", requests);
        model.addAttribute("story", productStory);
        model.addAttribute("storySpec", storySpec);
        return "/product/page/operate/story/product-demand-copy.page";
    }

    @ResponseBody
    @RequestMapping(value = "/judgeStoryName")
    public Map judgeStoryName(String param, Integer storyId, Integer productId) {
        if (param != null) {
            String storyName = param;
            ProductStory story = new ProductStory();
            story.setStoryTitle(storyName);
            story.setProductId(productId);
            story.setDeleted(FieldUtil.DELETE_NO);
            List<ProductStory> stories = storyService.findStoryList(story);
            if (stories.size() != 0) {
                if (storyId == null) {
                    return resultMap(false, "该需求已存在");
                } else if (!storyId.equals(stories.get(0).getStoryId())) {
                    return resultMap(false, "该需求已存在");
                } else {
                    return resultMap(true, "");
                }
            } else {
                return resultMap(true, "");
            }
        }
        return resultMap(false, "请输入需求名称");
    }

    @ResponseBody
    @RequestMapping("ajax/storyInCondition")
    public List<ProductStory> storyInCondition(String key, Integer initKey, Integer productId, HttpServletRequest request) {
        if (initKey != null) {
            List<ProductStory> result = new ArrayList<ProductStory>();
            result.add(storyService.findStory(initKey));
            return result;
        }
        List<ProductStory> productStories = storyService.storyInCondition(key, Integer.parseInt(configService.getConfigBySection(SystemConfig.SEARCH_CONFIG).getConfigKey()), productId);
        return productStories;
    }
}
