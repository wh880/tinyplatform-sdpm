package org.tinygroup.sdpm.action.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.action.product.util.StoryUtil;
import org.tinygroup.sdpm.common.log.LogPrepareUtil;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.ComplexSearch.SqlUtil;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.impl.FieldUtil;
import org.tinygroup.sdpm.product.dao.pojo.*;
import org.tinygroup.sdpm.product.service.*;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.service.inter.BuildService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.project.service.inter.TaskService;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;
import org.tinygroup.sdpm.quality.service.inter.BugService;
import org.tinygroup.sdpm.quality.service.inter.TestCaseService;
import org.tinygroup.sdpm.system.dao.pojo.ProfileType;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.ModuleUtil;
import org.tinygroup.sdpm.util.UserUtils;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 需求的控制器 Created by xubin、wangjie14929
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
        List<ProductAndLine> i = buildService
                .getProductLineTree(new ProductLine());

        String queryString = request.getQueryString();
        if (queryString != null && !queryString.contains("choose")) {
            return "redirect:" + adminPath + "/product/story?choose=1&"
                    + queryString;
        }
        OrgUser user = new OrgUser();
        ProductPlan plan = new ProductPlan();
        if (request.getSession().getAttribute("sessionProductId") != null) {
            plan.setProductId((Integer) request.getSession().getAttribute(
                    "sessionProductId"));
        }

        List<OrgUser> userList = userService.findUserList(new OrgUser());
        List<ProductPlan> planList = planService
                .findPlanList(new ProductPlan());

        model.addAttribute("userList", userList);
        model.addAttribute("planList", planList);
        return "/product/page/project/togglebox.page";
    }

    /**
     * 提需求的跳转
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/addstory")
    public String addstory(HttpServletRequest request, Model model) {
        int productId = -1;
        if (request.getSession().getAttribute("sessionProductId") != null) {
            productId = (Integer) request.getSession().getAttribute(
                    "sessionProductId");
        }
        Product product = productService.findProduct(productId);
        model.addAttribute("product", product);
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
            SystemAction systemAction, String type,
            ProductStory productStory,
            ProductStorySpec storySpec,
            @RequestParam(value = "file", required = false) MultipartFile[] file,
            String[] title, HttpServletRequest request) throws IOException {

        if (request.getSession().getAttribute("sessionProductId") != null) {
            productStory.setProductId((Integer) (request.getSession()
                    .getAttribute("sessionProductId")));
        }
        productStory.setStoryStatus("0");
        productStory.setStoryVersion(0);
        productStory.setStoryOpenedBy(UserUtils.getUserId());
        productStory.setStoryOpenedDate(new Date());

        storySpec.setStoryVersion(0);
        ProductStory story = storyService.addStory(productStory, storySpec);
        uploads(file, story.getStoryId(), ProfileType.STORY, title);

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


    /**
     * 实现编辑的保存同时记录编辑历史
     *
     * @param systemAction
     * @param productStory
     * @param file
     * @param title
     * @return
     * @throws IOException
     */
    @RequestMapping("/update")
    public String update(
            SystemAction systemAction,
            ProductStory productStory,
            @RequestParam(value = "file", required = false) MultipartFile[] file,
            String[] title) throws IOException {
        ProductStory story = storyService.findStory(productStory.getStoryId());
        productStory.setStoryLastEditedBy(UserUtils.getUser().getOrgUserId());
        productStory.setStoryLastEditedDate(new Date());
        storyService.updateStory(productStory);
        OrgUser user = (OrgUser) LogPrepareUtil.getSession().getAttribute(
                "user");

        uploads(file, story.getStoryId(), ProfileType.STORY, title);

        LogUtil.logWithComment(LogUtil.LogOperateObject.STORY,
                LogUtil.LogAction.EDITED,
                String.valueOf(productStory.getStoryId()),
                UserUtils.getUserId(), String.valueOf(story.getProductId()),
                null, story, productStory, systemAction.getActionComment());
        return "redirect:" + "/product/page/project/togglebox.page";
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
        /*
         * List<ProductStory> productStories = new ArrayList<ProductStory>(); if
		 * (stories != null && stories.length > 0) { productStories =
		 * Arrays.asList(stories); } return
		 * storyService.updateBatchStory(productStories);
		 */

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
        storyService.deleteStory(story.getStoryId());
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
        // storyId =new Integer[]{33,34,35,36};
        List<ProductStory> storyList = storyService.findStoryList(storyId);
        model.addAttribute("storyList", storyList);
        return "/product/page/tabledemo/product-demand-del.pagelet";
    }

    @RequestMapping("/{forwordPager}/findPager")
    public String find(Integer storyId,
                       @PathVariable(value = "forwordPager") String forwordPager,
                       Model model, SystemAction systemAction) {

        ProductStory productStory = storyService.findStory(storyId);
        ProductStorySpec storySpec = storySpecService.findStorySpec(storyId);
        List<ProductStory> stories = storyService.findProductName(storyId);

        QualityTestCase testCase = new QualityTestCase();
        testCase.setStoryId(storyId);
        List<QualityTestCase> testCaseList = testCaseService
                .findTestCaseList(testCase);

        QualityBug bug = new QualityBug();
        bug.setStoryId(storyId);
        List<QualityBug> bugList = bugService.findBugList(bug);
        List<OrgUser> userList = userService.findUserListByIds(productStory
                .getStoryMailto().split(","));
        List<Project> projectList = projectService.getProjectByStoryId(storyId);

        model.addAttribute("story", productStory);
        model.addAttribute("storySpec", storySpec);
        model.addAttribute("testCaseList", testCaseList);
        model.addAttribute("bugList", bugList);
        model.addAttribute("projectList", projectList);
        model.addAttribute("storyMailTo", userList);
        model.addAttribute("stories", stories);

        if ("productDemandClose".equals(forwordPager)) {
            return "/product/page/tabledemo/product-demand-close.page";
        } else if ("productDemandReview".equals(forwordPager)) {

            return "/product/page/tabledemo/product-demand-review.page";
        } else if ("productDemandChange".equals(forwordPager)) {
            return "/product/page/tabledemo/product-demand-change.page";
        } else if ("productDemandDetail".equals(forwordPager)) {

            ProjectTask task = new ProjectTask();
            task.setTaskStory(storyId);
            List<ProjectTask> taskList = taskService.findListTask(task);
            model.addAttribute("taskList", taskList);

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
            String[] title) throws IOException {
        ProductStory story = storyService.findStory(productStory.getStoryId());
        storyService.updateStory(productStory);
        int version = storySpecService.getNewStoryVersion(productStory
                .getStoryId());
        storySpec.setStoryVersion(version + 1);
        storySpecService.add(storySpec);
        OrgUser user = (OrgUser) LogPrepareUtil.getSession().getAttribute(
                "user");

        uploads(file, story.getStoryId(), ProfileType.STORY, title);

        LogUtil.logWithComment(LogUtil.LogOperateObject.STORY,
                LogUtil.LogAction.CHANGED,
                String.valueOf(productStory.getStoryId()),
                UserUtils.getUserId(),
                String.valueOf(productStory.getProductId()), null, story,
                productStory, systemAction.getActionComment());
        return "redirect:" + "/product/page/project/togglebox.page";
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
    public String reviewed(SystemAction systemAction, ProductStory productStory)
            throws IOException {
        ProductStory story = storyService.findStory(productStory.getStoryId());
        storyService.updateStory(productStory);
        OrgUser user = (OrgUser) LogPrepareUtil.getSession().getAttribute(
                "user");

        LogUtil.logWithComment(LogUtil.LogOperateObject.STORY,
                LogUtil.LogAction.REVIEWED,
                String.valueOf(productStory.getStoryId()),
                UserUtils.getUserId(),
                String.valueOf(productStory.getProductId()), null, story,
                productStory, systemAction.getActionComment());
        return "redirect:" + "/product/page/project/togglebox.page";
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
        productStory.setDeleted(FieldUtil.DELETE_YES);
        storyService.updateStory(productStory);
        LogUtil.logWithComment(LogUtil.LogOperateObject.STORY,
                LogUtil.LogAction.CLOSED,
                String.valueOf(productStory.getStoryId()),
                UserUtils.getUserId(), String.valueOf(story.getProductId()),
                null, story, productStory, systemAction.getActionComment());
        return "redirect:" + "/product/page/project/togglebox.page";
    }

    /**
     * 实现了需求的搜索按钮功能
     *
     * @param page         当前页
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
    public String storySearchAction(int page, int pagesize, ProductStory story,
                                    String type, String choose, String groupOperate,
                                    SearchInfos searchInfos, String order, String ordertype,
                                    Model model, HttpServletRequest request) {

        if (request.getSession().getAttribute("sessionProductId") != null) {
            story.setProductId((Integer) (request.getSession()
                    .getAttribute("sessionProductId")));
        }
        /*
         * if (story.getModuleId()==-1) { story.setModuleId(null); }
		 */
        String condition = StoryUtil.getStatusCondition(choose, request);
        if (story.getModuleId() != null && story.getModuleId() > 0) {
            SystemModule module = new SystemModule();
            module.setModuleId(story.getModuleId());
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("in (");
            ModuleUtil
                    .getConditionByModule(stringBuffer, module, moduleService);
            stringBuffer.append(")");
            condition = condition + " and "
                    + NameUtil.resolveNameDesc("moduleId") + " "
                    + stringBuffer.toString();
        }
        story.setModuleId(null);

        if ("noCase".equals(type)) {
            QualityTestCase testCase = new QualityTestCase();
            testCase.setProductId(story.getProductId());
            List<Integer> list = testCaseService.getStoryIds(testCase);
            if (list.size() > 0 || list != null) {
                String ids = list.toString().substring(1,
                        list.toString().length() - 1);
                condition = (StringUtil.isBlank(condition) ? " " : condition
                        + " and ")
                        + "product_story.story_id not in (" + ids + ") ";
            }

        }
        Pager<ProductStory> p = storyService.findStoryPager(pagesize
                        * (page - 1), pagesize, story, condition, searchInfos,
                groupOperate, order, "asc".equals(ordertype) ? true : false);
        model.addAttribute("storyList", p);

        return "product/data/tabledata.pagelet";
    }

    /**
     * 计划、发布里的关联需求、未关联需求数据查找和搜索功能的实现
     *
     * @param relate
     * @param page
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
    public String storyListAction(
            @PathVariable(value = "relate") String relate,
            int page,
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

        if (request.getSession().getAttribute("sessionProductId") != null) {
            story.setProductId((Integer) (request.getSession()
                    .getAttribute("sessionProductId")));
        }

        // String condition = StoryUtil.getStatusCondition(choose, request);

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

        if (releaseId != null && releaseId > 0) {
            ProductRelease release = releaseService.findRelease(releaseId);
            if (release != null) {
                String releaseStories = release.getReleaseStories();
                if (releaseStories != null) {
                    if ("alWork".equals(type)) {
                        if ("".equals(releaseStories)) {
                            condition = (StringUtil.isBlank(condition.trim()) ? " " : (condition + " and ")) + "product_story.story_id is null ";
                        } else {
                            condition = (StringUtil.isBlank(condition.trim()) ? " " : (condition + " and ")) + "product_story.story_id in (" + releaseStories + ") ";
                        }

                    }
                    if ("noWork".equals(type) && !StringUtil.isBlank(releaseStories)) {
                        condition = (StringUtil.isBlank(condition.trim()) ? " " : (condition + " and ")) + "product_story.story_id not in (" + releaseStories + ") ";
                    }
                }
            }
        }


        Pager<ProductStory> p = storyService.findPager(pagesize * (page - 1),
                pagesize, story, condition, order,
                "asc".equals(ordertype) ? true : false);
        model.addAttribute("storyList", p);

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
    public String bugListAction(@PathVariable(value = "relate") String relate,
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
        // bug.setProductId((Integer)(request.getSession().getAttribute("sessionProductId")));

        if (request.getSession().getAttribute("sessionProductId") != null) {
            bug.setProductId((Integer) (request.getSession()
                    .getAttribute("sessionProductId")));
        }

        // String condition = StoryUtil.getStatusCondition(choose, request);

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

        if (releaseId != null && releaseId > 0) {
            ProductRelease release = releaseService.findRelease(releaseId);
            if (release != null) {
                String releaseBugs = release.getReleaseBugs();
                if (releaseBugs != null) {
                    if ("reRelateBugRelease".equals(relate)) {
                        if ("".endsWith(releaseBugs)) {
                            condition = (StringUtil.isBlank(condition.trim()) ? " " : (condition + " and ")) + "quality_bug.bug_id is null";
                        } else {
                            condition = (StringUtil.isBlank(condition.trim()) ? " " : (condition + " and ")) + "quality_bug.bug_id in (" + releaseBugs + ") ";
                        }

                    }
                    if ("noRelateBugRelease".equals(relate) && !StringUtil.isBlank(releaseBugs)) {
                        condition = (StringUtil.isBlank(condition.trim()) ? " " : (condition + " and ")) + "quality_bug.bug_id not in (" + releaseBugs + ") ";
                    }
                    if ("leRelateBugRelease".equals(relate) && !StringUtil.isBlank(releaseBugs)) {
                        //condition = (StringUtil.isBlank(condition.trim()) ? " " : (condition + " and ")) + "product_story.story_id not in ("+releaseBugs+") ";
                    }
                }
            }
        }


        Pager<QualityBug> p = bugService.findBugListPager(
                pagesize * (page - 1), pagesize, condition, bug, order,
                "asc".equals(ordertype) ? true : false);
        model.addAttribute("bugList", p);

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
        List<ProductStory> list = storyService.findStoryList(story);
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
    public String report(ProductStory story, String chexkitem, Model model) {

		/*
		 * List<StoryCount> productStoryCount =
		 * storyService.productStoryCount(story); List<StoryCount>
		 * modelStoryCount = storyService.modelStoryCount(story);
		 * List<StoryCount> planStoryCount = storyService.planStoryCount(story);
		 * 
		 * model.addAttribute("productStoryCount", productStoryCount);
		 * model.addAttribute("modelStoryCount", modelStoryCount);
		 * model.addAttribute("planStoryCount", planStoryCount);
		 */
        Map<String, List<StoryCount>> map = storyService.report(chexkitem,
                story);
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
        ProductStorySpec storySpec = storySpecService.findStorySpec(storyId);
        model.addAttribute("story", productStory);
        model.addAttribute("storySpec", storySpec);
        return "/product/page/tabledemo/product-demand-copy.page";
    }

}
