package org.tinygroup.sdpm.action.product;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.action.product.util.StoryUtil;
import org.tinygroup.sdpm.common.condition.ConditionCarrier;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.ComplexSearch.SqlUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.dto.UploadProfile;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.impl.FieldUtil;
import org.tinygroup.sdpm.product.dao.pojo.*;
import org.tinygroup.sdpm.product.service.*;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.service.inter.BuildService;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.project.service.inter.TaskService;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;
import org.tinygroup.sdpm.quality.service.inter.BugService;
import org.tinygroup.sdpm.quality.service.inter.TestCaseService;
import org.tinygroup.sdpm.service.dao.pojo.ServiceRequest;
import org.tinygroup.sdpm.service.service.inter.RequestService;
import org.tinygroup.sdpm.system.dao.pojo.ProfileType;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
import org.tinygroup.sdpm.util.CookieUtils;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.ProductUtils;
import org.tinygroup.sdpm.util.UserUtils;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private PlanService planService;
    @Autowired
    private BuildService buildService;
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
    private ProjectProductService projectProductService;
    @Autowired
    private ProfileService profileService;

    /**
     * @param story
     * @param groupOperate
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("")
    public String storyAction(ProductStory story, String groupOperate,
                              Model model, HttpServletRequest request,
                              HttpServletResponse response) {

        String queryString = request.getQueryString();
        if (queryString != null && !queryString.contains("choose")) {
            return "redirect:" + adminPath + "/product/story?choose=1&"
                    + queryString;
        }
        if (queryString == null) {
            return "redirect:" + adminPath + "/product/story?choose=1";
        }

        return "/product/page/project/togglebox.page";
    }

    /**
     * 提需求的跳转
     *
     * @param request
     * @param model
     * @return
     */
    @RequiresPermissions("pro-demand-add")
    @RequestMapping("/addstory")
    public String addStory(HttpServletRequest request, Model model, String lastAddress) {
        List<ServiceRequest> requests = requestService.getRequestList(null);
        model.addAttribute("requestList", requests);
        if (!StringUtil.isBlank(lastAddress)) {
            return "redirect:" + lastAddress;
        }
        return "/product/page/tabledemo/product-demand-add.page";
    }

    /**
     * 保存新的需求同时实现附件上传和记录历史
     *
     * @param systemAction
     * @param productStory
     * @param storySpec
     * @param file
     * @param title
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/save")
    public String save(
            @CookieValue(value = "cookieProductId") String cookieProductId,
            SystemAction systemAction, String type,
            ProductStory productStory,
            ProductStorySpec storySpec,
            @RequestParam(value = "file", required = false) MultipartFile[] file,
            String[] title, HttpServletRequest request, UploadProfile uploadProfile) throws IOException {
        if (productStory.getPlanId() != null && productStory.getPlanId() > 0) {
            productStory.setStoryStage("2");
        } else {
            productStory.setStoryStage("1");
        }
        productStory.setProductId(Integer.parseInt(cookieProductId));
        if ("0".equals(productStory.getStoryReviewedBy())) {
            productStory.setStoryStatus("1");
        } else {
            productStory.setStoryStatus("0");
        }
        productStory.setStoryVersion(1);
        productStory.setStoryOpenedBy(UserUtils.getUserId());
        productStory.setStoryOpenedDate(new Date());

        storySpec.setStoryVersion(1);
        ProductStory story = storyService.addStory(productStory, storySpec);
        processProfile(uploadProfile, story.getStoryId(), ProfileType.STORY);

        if ("copy".equals(type)) {
            LogUtil.logWithComment(LogUtil.LogOperateObject.STORY,
                    LogUtil.LogAction.COPY,
                    String.valueOf(story.getStoryId()),
                    UserUtils.getUserId(),
                    String.valueOf(story.getProductId()),
                    null,
                    null,
                    null,
                    systemAction.getActionComment());
            return "redirect:" + adminPath + "/product/storySpec/find/productDemandDetail?storyId=" + story.getStoryId();
        } else {
            LogUtil.logWithComment(LogUtil.LogOperateObject.STORY,
                    LogUtil.LogAction.OPENED, String.valueOf(story.getStoryId()),
                    UserUtils.getUserId(), String.valueOf(story.getProductId()),
                    null, null, null, systemAction.getActionComment());
            return "redirect:" + adminPath + "/product/story?choose=1&currentPageId=3";
        }
    }

    @ResponseBody
    @RequestMapping("/ajax/user")
    public List<OrgUser> getUser(OrgUser orgUser) {
        List<OrgUser> orgUsers = userService.findUserList(orgUser);
        return orgUsers;
    }

    @ResponseBody
    @RequestMapping("/ajax/plan")
    public List<ProductPlan> getPlan(ProductPlan productPlan) {
        if (productPlan.getProductId() == null || productPlan.getProductId() < 1) return new ArrayList<ProductPlan>();
        productPlan.setDeleted(0);
        List<ProductPlan> productPlans = planService.findPlanList(productPlan);
        return productPlans;
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
        productStory.setStoryLastEditedBy(UserUtils.getUser().getOrgUserId());
        productStory.setStoryLastEditedDate(new Date());
        if (story.getStoryStatus() == "1") {
            if (productStory.getPlanId() != null && productStory.getPlanId() > 0) {
                productStory.setStoryStage("2");
            }
        }
        processProfile(uploadProfile, productStory.getStoryId(), ProfileType.STORY);
        storyService.updateStory(productStory);

        LogUtil.logWithComment(LogUtil.LogOperateObject.STORY,
                LogUtil.LogAction.EDITED,
                String.valueOf(productStory.getStoryId()),
                UserUtils.getUserId(), String.valueOf(story.getProductId()),
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
        return "/product/page/project/togglebox.page";
    }

    @RequestMapping("/list/data")
    public String listData(Integer moduleId, Integer start, Integer limit,
                           ProductStory productStory, Model model) {
        if (moduleId == null || moduleId == -1) {

        } else {

        }
        return "";
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

        if (stories.length == 0 || stories == null) {
            return false;
        }
        for (ProductStory story : stories) {
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
        story.setStoryClosedBy(UserUtils.getUserId());
        story.setStoryClosedDate(new Date());
        storyService.deleteStory(story);
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "删除成功");

        LogUtil.logWithComment(LogUtil.LogOperateObject.STORY,
                LogUtil.LogAction.DELETED, String.valueOf(story.getStoryId()),
                UserUtils.getUserId(), String.valueOf(story.getProductId()),
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
                UserUtils.getUserId(), String.valueOf(story.getProductId()),
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
        model.addAttribute("requestList",requests);
        model.addAttribute("story", productStory);
        return "/product/page/tabledemo/editbaseinfo.pagelet";
    }

    /**
     * 根据多个id查找多个需求
     *
     * @param storyId
     * @param model
     * @return
     */
    @RequestMapping("/findByKeys")
    public String findByKeys(Integer[] storyId, Model model) {
        List<ProductStory> storyList = storyService.findStoryList(storyId);
        model.addAttribute("storyList", storyList);
        return "/product/page/tabledemo/product-demand-del.pagelet";
    }

    @RequestMapping("/{forwordPager}/findPager")
    public String find(Integer storyId,
                       @PathVariable(value = "forwordPager") String forwordPager,
                       Model model, SystemAction systemAction) {
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

        if ("productDemandClose".equals(forwordPager)) {
            return "/product/page/tabledemo/product-demand-close.page";
        } else if ("productDemandReview".equals(forwordPager)) {

            return "/product/page/tabledemo/product-demand-review.page";
        } else if ("productDemandChange".equals(forwordPager)) {
            SystemProfile systemProfile = new SystemProfile();
            systemProfile.setFileObjectId(storyId);
            systemProfile.setFileObjectType(ProfileType.STORY.getType());
            List<SystemProfile> fileList = profileService.findSystemProfile(systemProfile);
            model.addAttribute("fileList", fileList);
            return "/product/page/tabledemo/product-demand-change.page";
        } else if ("productDemandDetail".equals(forwordPager)) {

            ProjectTask task = new ProjectTask();
            task.setTaskStory(storyId);
            List<ProjectTask> taskList = taskService.findListTask(task);
            model.addAttribute("taskList", taskList);

            QualityTestCase testCase1 = new QualityTestCase();
            testCase.setDeleted(0);
            testCase.setStoryId(storyId);
            List<QualityTestCase> testCases = testCaseService.findTestCaseList(testCase);
            model.addAttribute("caseList", testCases);
            return "/product/page/tabledemo/hrefbaseinfo.pagelet";
        }

        return "";
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
        Integer maxVersion = storySpecService.getMaxVersion(productStory.getStoryId());
        ProductStory story = storyService.findStory(productStory.getStoryId());
        if (productStory.getStoryReviewedBy().equals("0")) {
            productStory.setStoryStatus("1");
//            productStory.setStoryReviewedBy(null);
        } else {
            productStory.setStoryStatus("3");
        }
        productStory.setStoryVersion(maxVersion == null ? 1 : maxVersion + 1);
        storyService.updateStory(productStory);
        storySpec.setStoryVersion(productStory.getStoryVersion());
        storySpecService.add(storySpec);
        processProfile(uploadProfile, productStory.getStoryId(), ProfileType.STORY);

        LogUtil.logWithComment(LogUtil.LogOperateObject.STORY,
                LogUtil.LogAction.CHANGED,
                String.valueOf(story.getStoryId()),
                UserUtils.getUserId(),
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
        if (reviewRequest == 0) {
            productStory.setStoryStatus("1");
        } else if (reviewRequest == 2) {
            productStory.setStoryStatus("4");
        }
        ProductStory story = storyService.findStory(productStory.getStoryId());
        storyService.updateStory(productStory);
        LogUtil.logWithComment(LogUtil.LogOperateObject.STORY,
                LogUtil.LogAction.REVIEWED,
                String.valueOf(productStory.getStoryId()),
                UserUtils.getUserId(),
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
        productStory.setStoryClosedBy(UserUtils.getUserId());
        productStory.setStoryClosedDate(new Date());
        productStory.setDeleted(FieldUtil.DELETE_YES);
        productStory.setStoryStatus("2");
        storyService.deleteStory(productStory);
        LogUtil.logWithComment(LogUtil.LogOperateObject.STORY,
                LogUtil.LogAction.CLOSED,
                String.valueOf(productStory.getStoryId()),
                UserUtils.getUserId(), String.valueOf(story.getProductId()),
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
     * @param request
     * @return
     */
    @RequestMapping("/search")

    public String storySearchAction(@CookieValue(value = "cookieProductId", defaultValue = "0") String cookieProductId, int start, int pagesize, ProductStory story,
                                    String type, String choose, String groupOperate,
                                    SearchInfos searchInfos, String order, String ordertype,
                                    Model model, HttpServletRequest request) {
        if (!"2".equals(choose) && !"11".equals(choose)) {
            story.setDeleted(0);
        }
        if (story.getProductId() == null || story.getProductId() == 0) {
            story.setProductId(Integer.parseInt(cookieProductId));
        }
        ConditionCarrier carrier = new ConditionCarrier();
        String condition = StoryUtil.getStatusCondition(choose);
        carrier.putStatus("status", condition);
        if (story.getModuleId() != null && story.getModuleId() > 0) {
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
            carrier.putIdNotIn("productStory.storyId", ids);
        }
        carrier.putSearch("search", searchInfos, groupOperate);

        Pager<ProductStory> p = storyService.findStoryByCondition(start, pagesize, story, carrier, order, "asc".equals(ordertype) ? true : false);
        model.addAttribute("storyList", p);

        return "product/data/tabledata.pagelet";
    }

    /**
     * 计划、发布里的关联需求、未关联需求数据查找和搜索功能的实现
     *
     * @param relate
     * @param start
     * @param pagesize
     * @param story
     * @param choose
     * @param groupOperate
     * @param searchInfos
     * @param order
     * @param ordertype
     * @param model
     * @param request
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
                                  String choose,
                                  String groupOperate,
                                  SearchInfos searchInfos,
                                  @RequestParam(required = false, defaultValue = "storyId") String order,
                                  @RequestParam(required = false, defaultValue = "asc") String ordertype,
                                  Model model, HttpServletRequest request) {


        story.setProductId(Integer.parseInt(cookieProductId));

        String condition = "";
        if (searchInfos != null) {
            if (searchInfos.getInfos().size() > 0
                    || searchInfos.getInfos() != null) {
                String sql = SqlUtil
                        .toSql(searchInfos.getInfos(), groupOperate);
                if (!StringUtil.isBlank(sql)) {
                    condition += sql;
                }
            }
        }
        if ("noRelate".equals(type)) {
            condition = (StringUtil.isBlank(condition.trim()) ? " "
                    : (condition + " and "))
                    + " product_story.plan_id <> "
                    + story.getPlanId();
            story.setPlanId(null);
        }
        Pager<ProductStory> p = storyService.findPager(start,
                pagesize, story, condition, order,
                "asc".equals(ordertype) ? true : false);
        model.addAttribute("storyList", p);
        if (releaseId != null && releaseId > 0) {
            ProductRelease release = releaseService.findRelease(releaseId);
            if (release != null) {
                String releaseStories = release.getReleaseStories();
                String inCondition = StringUtil.isBlank(releaseStories) ? "" : releaseStories;
                if ("noWork".equals(type)) {
                    if (!StringUtil.isBlank(inCondition)) {
                        condition = (StringUtil.isBlank(condition.trim()) ? " " : (condition + " and ")) + "product_story.story_id not in (" + inCondition + ") ";
                    }
                    p = storyService.findProjectLinkedStory(start,
                            pagesize, story, condition, order,
                            "asc".equals(ordertype) ? true : false);
                } else {
                    if (StringUtil.isBlank(inCondition)) {
                        p = new Pager<ProductStory>(0, 0, new ArrayList<ProductStory>());
                    } else {
                        condition = (StringUtil.isBlank(condition.trim()) ? " " : (condition + " and ")) + "product_story.story_id in (" + inCondition + ")";
                        p = storyService.findPager(start,
                                pagesize, story, condition, order,
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
                                String type,
                                @RequestParam(required = false, defaultValue = "asc") String ordertype,
                                Model model, HttpServletRequest request) {
        if (Integer.parseInt(cookieProductId) > 0) {
            bug.setProductId(Integer.parseInt(cookieProductId));
        }
        String condition = "";
        if (searchInfos != null) {
            if (searchInfos.getInfos().size() > 0
                    || searchInfos.getInfos() != null) {
                String sql = SqlUtil
                        .toSql(searchInfos.getInfos(), groupOperate);
                if (!StringUtil.isBlank(sql)) {
                    condition += sql;
                }
            }
        }
        if ("noRelateBug".equals(relate)) {
            condition = (StringUtil.isBlank(condition.trim()) ? " "
                    : (condition + " and "))
                    + " quality_bug.plan_id <> "
                    + bug.getPlanId();
            bug.setPlanId(null);
        }
        Pager<QualityBug> p = bugService.findBugListPager(
                pagesize * (page - 1), pagesize, condition, bug, order,
                "asc".equals(ordertype) ? true : false);
        model.addAttribute("bugList", p);
        if (releaseId != null && releaseId > 0) {
            ProductRelease release = releaseService.findRelease(releaseId);
            if (release != null) {
                bug.setBugOpenedBuild(String.valueOf(release.getBuildId()));
                String releaseBugs = release.getReleaseBugs();
                String inCondition = StringUtil.isBlank(releaseBugs) ? "" : releaseBugs;
                if ("reRelateBugRelease".equals(relate)) {
                    if (StringUtil.isBlank(inCondition)) {
                        p = new Pager<QualityBug>(0, 0, new ArrayList<QualityBug>());
                    } else {
                        condition = (StringUtil.isBlank(condition.trim()) ? " " : (condition + " and ")) + "quality_bug.bug_id in (" + inCondition + ")";
                        p = bugService.findBugListPager(
                                pagesize * (page - 1), pagesize, condition, bug, order,
                                "asc".equals(ordertype) ? true : false);
                    }
                } else if ("noRelateBugRelease".equals(relate)) {
                    bug.setBugStatus("2");
                    if (!StringUtil.isBlank(inCondition)) {
                        condition = (StringUtil.isBlank(condition.trim()) ? " " : (condition + " and ")) + "quality_bug.bug_id not in (" + inCondition + ") ";
                    }
                    p = bugService.findBugListPager(
                            pagesize * (page - 1), pagesize, condition, bug, order,
                            "asc".equals(ordertype) ? true : false);
                } else {
                    bug.setBugStatus("1");
                    p = bugService.findBugListPager(
                            pagesize * (page - 1), pagesize, condition, bug, order,
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
        Map<String, List<StoryCount>> map = storyService.report(chexkitem, story);
        model.addAttribute("map", map);
        model.addAttribute("fields", chexkitem);
        return "/product/page/tabledemo/product-report.page";
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
                    , UserUtils.getUserId()
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
            return "product/page/tabledemo/product-report.page";
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
    public String remark(ProductStory story, SystemAction systemAction) {
        LogUtil.logWithComment(LogUtil.LogOperateObject.STORY,
                LogUtil.LogAction.REMARK, String.valueOf(story.getStoryId()),
                UserUtils.getUserId(), String.valueOf(story.getProductId()),
                null, null, null, systemAction.getActionComment());
        return "redirect:" + adminPath
                + "/product/storySpec/find/productDemandDetail?storyId="
                + story.getStoryId();

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
        return "/product/page/tabledemo/product-demand-copy.page";
    }

    @ResponseBody
    @RequestMapping(value = "/judgeStoryName")
    public Map judgeStoryName(String param, Integer storyId, Integer productId) {
        if (param != null) {
            String storyName = param;
            ProductStory story = new ProductStory();
            story.setStoryTitle(storyName);
            story.setProductId(productId);
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
    public List<ProductStory> userInCondition(String key, Integer initKey, Integer productId, HttpServletRequest request){
        if(initKey!=null){
            List<ProductStory> result = new ArrayList<ProductStory>();
            result.add(storyService.findStory(initKey));
            return result;
        }
        return storyService.storyInCondition(key, productId==null?Integer.parseInt(CookieUtils.getCookie(request,ProductUtils.COOKIE_PRODUCT_ID)):productId);
    }
}
