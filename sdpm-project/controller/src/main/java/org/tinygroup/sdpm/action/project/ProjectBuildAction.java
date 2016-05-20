package org.tinygroup.sdpm.action.project;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tinygroup.commons.tools.ArrayUtil;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.dao.complexsearch.SearchInfos;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.dto.UploadProfile;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.service.inter.StoryService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.sdpm.project.service.inter.*;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.quality.service.inter.BugService;
import org.tinygroup.sdpm.system.dao.pojo.ProfileType;
import org.tinygroup.sdpm.system.dao.pojo.SystemConfig;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.ProjectOperate;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 版本
 * Created by wangying14938 on 2015-09-22.
 */
@Controller
@RequestMapping("/a/project/build")
public class ProjectBuildAction extends BaseController {
    @Autowired
    UserService userService;
    @Autowired
    private BuildService buildService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectStoryService projectStoryService;
    @Autowired
    private ProjectProductService projectProductService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private BugService bugService;
    @Autowired
    private StoryService storyService;
    @Autowired
    private TeamService teamService;
    @RequiresPermissions("version")
    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response,@CookieValue(value= ProjectOperate.COOKIE_PROJECT_ID)String currentProjectId)
    {
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null)
        {
            return redirectProjectForm();
        }
        Project project = projectService.findProjectById(projectId);
        model.addAttribute("project", project);

        List<ProjectProduct> projectProductList=projectProductService.findProductListByProjectId(Integer.parseInt(currentProjectId));
        if(projectProductList.size()==0)
        {
            model.addAttribute("linkInfo",1);
        }else
        {
            model.addAttribute("linkInfo",0);
        }

        return "project/index/build/index";
    }

    @RequestMapping("/productBuildList")
    public String productBuildList(ProjectBuild build, Model model,
                                   Integer start, Integer limit, @RequestParam(required = false, defaultValue = "build_id") String order,
                                   @RequestParam(required = false, defaultValue = "desc") String ordertype) {
        boolean asc = "asc".equals(ordertype) ? true : false;
        Pager<ProjectBuild> pager = buildService.findPagerBuild(build, start, limit, order, asc);
        model.addAttribute("buildPager", pager);
        return "project/data/build/tableData.pagelet";
    }


    @RequestMapping("/find")
    public String find(Model model, Integer start, Integer limit, @RequestParam(required = false, defaultValue = "build_id") String order,
                       @RequestParam(required = false, defaultValue = "desc") String orderType,
                       HttpServletRequest request, HttpServletResponse response) {
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return redirectProjectForm();
        }
        boolean asc = "asc".equals(orderType) ? true : false;
        Pager<ProjectBuild> pager = buildService.findBuildPagerWithOrder(projectId, start, limit, order, asc);
        model.addAttribute("buildPager", pager);
        return "project/data/build/tableData.pagelet";
    }

    /**
     * 查看当前版本对应的bug
     *
     * @return
     */
    @RequiresPermissions("pro-version-look")
    @RequestMapping("/look")
    public String look() {
        return "project/index/bug/index";
    }

    @RequiresPermissions(value = {"pro-version-edit", "pro-version-add"}, logical = Logical.OR)
    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, HttpServletResponse response,
                       Integer buildId, Model model,
                       @CookieValue(value=ProjectOperate.COOKIE_PROJECT_ID)String currentProjectId) {
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return redirectProjectForm();
        }
        if (buildId != null) {
            SystemProfile systemProfile = new SystemProfile();
            systemProfile.setFileObjectId(buildId);
            systemProfile.setFileObjectType(ProfileType.BUILD.getType());
            List<SystemProfile> fileList = profileService.findSystemProfile(systemProfile);
            model.addAttribute("fileList", fileList);
        }

        List<Product> linkProductByProjectId = projectProductService.findLinkProductByProjectId(projectId);
        model.addAttribute("productList", linkProductByProjectId);

        SystemModule module = new SystemModule();
        module.setModuleType("project");
        module.setModuleRoot(projectId);
        model.addAttribute("teamList", userService.findTeamUserListByProjectId(projectId));
        if (buildId != null && buildId != 0) {
            ProjectBuild build = buildService.findBuild(buildId);

            if(build.getBuildProject()!=Integer.parseInt(currentProjectId))
            {
                return "redirect:"+adminPath+"/project/build/index";
            }

            model.addAttribute("build", build);
        }
        return "project/operate/build/edit";
    }


    @RequestMapping(value = "/addSave", method = RequestMethod.POST)
    public String addSave(ProjectBuild build, Model model, UploadProfile uploadProfile, String lastAddress) throws IOException {
        ProjectBuild temp;
        if (build.getBuildId() == null) {
            build.setBuildBuilder(userUtils.getUserId());
            temp = buildService.addBuild(build);
            processProfile(uploadProfile, temp.getBuildId(), ProfileType.BUILD);
            LogUtil.logWithComment(LogUtil.LogOperateObject.BUILD,
                    LogUtil.LogAction.OPENED,
                    String.valueOf(temp.getBuildId()),
                    userUtils.getUserId(),
                    String.valueOf(temp.getBuildProduct()),
                    String.valueOf(temp.getBuildProject()),
                    null,
                    null,
                    null);
            if (!StringUtil.isBlank(lastAddress)) {
                return "redirect:" + lastAddress;
            }
        } else {
            buildService.updateBuild(build);
            processProfile(uploadProfile, build.getBuildId(), ProfileType.BUILD);
            LogUtil.logWithComment(LogUtil.LogOperateObject.BUILD,
                    LogUtil.LogAction.EDITED,
                    String.valueOf(build.getBuildId()),
                    userUtils.getUserId(),
                    String.valueOf(build.getBuildProduct()),
                    String.valueOf(build.getBuildProject()),
                    null,
                    null,
                    null);
        }
        model.addAttribute("build", build);
        return "project/index/build/index.page";
    }

    @RequiresPermissions("pro-version-delete")
    @ResponseBody
    @RequestMapping(value = "/delete")
    public Map<String, String> delete(Integer id) {
        buildService.softDeleteBuild(id);
        ProjectBuild temp = buildService.findBuild(id);
        LogUtil.logWithComment(LogUtil.LogOperateObject.BUILD,
                LogUtil.LogAction.OPENED,
                String.valueOf(temp.getBuildId()),
                userUtils.getUserId(),
                String.valueOf(temp.getBuildProduct()),
                String.valueOf(temp.getBuildProject()),
                null,
                null,
                null);
        return resultMap(true, "删除成功");
    }

    @RequestMapping("/product-al-bug")
    public String jumpalBug() {
        return "/project/operate/build/relation/product-al-bug.page";
    }

    @RequestMapping("/product-al-le-bug")
    public String jumpleBug() {
        return "/project/operate/build/relation/product-al-le-bug.page";
    }

    @RequestMapping("/product-al-no-bug")
    public String jumpanoBug() {
        return "/project/operate/build/relation/product-al-no-bug.page";
    }

    @RequiresPermissions("pro-version-delete")
    @ResponseBody
    @RequestMapping(value = "/batchDelete")
    public Map batchDelDoc(String ids) {
        if (StringUtil.isBlank(ids)) {
            return resultMap(false, "请至少选择一条数据");
        }
        List<ProjectBuild> list = new ArrayList<ProjectBuild>();
        for (String s : ids.split(",")) {
            ProjectBuild build = new ProjectBuild();
            build.setBuildId(Integer.valueOf(s));
            build.setBuildDeleted("1");
            list.add(build);
        }
        for (int i = 0, n = list.size(); i < n; i++) {
            LogUtil.logWithComment(LogUtil.LogOperateObject.BUILD,
                    LogUtil.LogAction.DELETED,
                    String.valueOf(list.get(i).getBuildId()),
                    userUtils.getUserId(),
                    String.valueOf(list.get(i).getBuildProduct()),
                    String.valueOf(list.get(i).getBuildProject()),
                    null,
                    null,
                    null);
        }

        buildService.deleteBuildByIds(list);
        return resultMap(true, "删除成功");
    }


    @RequestMapping("/productalbug")
    public String productalbug(Integer buildId, Model model,
                               @CookieValue(value=ProjectOperate.COOKIE_PROJECT_ID)String currentProjectId) {
        ProjectBuild build = buildService.findBuild(buildId);

        if(build.getBuildProject()!=Integer.parseInt(currentProjectId))
        {
            return "redirect:"+adminPath+"/project/build/index";
        }

        model.addAttribute("build", build);
        //还需要查询其他相关任务剩余时间的信息
        return "/project/operate/build/relation/product-al-bug.page";
    }

    @RequestMapping("/releasebaseinfo")
    public String releaseBaseInfo(Integer buildId, Model model) {
        ProjectBuild build = buildService.findBuild(buildId);
        model.addAttribute("build", build);
        //还需要查询其他相关任务剩余时间的信息
        return "/project/view/rightinfo/build/buildbaseinfo.pagelet";
    }

    //    @RequiresPermissions("projectBuild-forword")
    @RequestMapping("/forword/{forwordPager}")
    public String forward(@PathVariable(value = "forwordPager") String forwordPager, Integer buildId, Model model,
                          @CookieValue(value = ProjectOperate.COOKIE_PROJECT_ID) String currentProjectId,
                          @CookieValue(value = "cookieProductId", defaultValue = "0") String cookieProductId) {
        List<ProjectTeam> teams = teamService.findTeamByProductId(Integer.parseInt(cookieProductId));
        String[] ids = new String[teams.size()];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = teams.get(i).getTeamUserId();
        }
        List<OrgUser> orgUsers = userService.findUserListByIds(ids);
        model.addAttribute("orgUsers", orgUsers);

        ProjectBuild build = buildService.findBuild(buildId);
        OrgUser user = new OrgUser();
        user.setOrgUserDeleted("0");
        List<OrgUser> users = userService.findUserList(user);
        model.addAttribute("userList", users);
        if(build.getBuildProject()!=Integer.parseInt(currentProjectId))
        {
            return "redirect:"+adminPath+"/project/build/index";
        }

        model.addAttribute("build", build);
        if ("alBug".equals(forwordPager)) {
            return "project/operate/build/relation/product-al-bug.page";
        } else if ("alnoBug".equals(forwordPager)) {
            return "project/operate/build/relation/product-al-no-bug.page";
        } else if ("alleBug".equals(forwordPager)) {
            return "project/operate/build/relation/product-al-le-bug.page";
        } else if ("alnoReq".equals(forwordPager)) {
            return "project/operate/build/relation/product-al-no-req.page";

        } else /* ("alReq".equals(forwordPager)) */ {
            return "project/operate/build/relation/product-al-req.page";
        }

    }


    @RequestMapping("/bugSearch/{relate}")
    public String bugListAction(@PathVariable(value = "relate") String relate,
                                int start,
                                int limit,
                                QualityBug bug,
                                int id,
                                String groupOperate,
                                SearchInfos searchInfos,
                                @RequestParam(required = false, defaultValue = "bugId") String order,
                                @RequestParam(required = false, defaultValue = "desc") String ordertype,
                                Model model) {

        ConditionCarrier carrier = new ConditionCarrier();
        if (searchInfos != null) {
            carrier.putSearch("bugSearch", searchInfos, groupOperate);
        }
        ProjectBuild build = buildService.findBuild(id);
        bug.setDeleted(0);
        bug.setBugStatus("2");
        bug.setBugOpenedBuild(String.valueOf(id));
        if ("reRelateBug".equals(relate)) {
            carrier.putIns("qualityBug.bugId", build.getBuildBugs().split(","));
            Pager<QualityBug> p = bugService.findBugListPager(start, limit, carrier, bug, order, "asc".equals(ordertype) ? true : false);
            model.addAttribute("bugList", p);
            return "/project/data/build/relation/product-al-bug-data.pagelet";
        } else if ("noRelateBug".equals(relate)) {
            carrier.putNotIns("qualityBug.bugId", build.getBuildBugs().split(","));
            Pager<QualityBug> p = bugService.findBugListPager(start, limit, carrier, bug, order, "asc".equals(ordertype) ? true : false);
            model.addAttribute("bugList", p);
            return "/project/data/build/relation/product-al-no-bug-data.pagelet";
        } else if ("leRelateBugRelease".equals(relate)) {
            bug.setBugStatus("1");
            Pager<QualityBug> p = bugService.findBugListPager(start, limit, carrier, bug, order, "asc".equals(ordertype) ? true : false);
            model.addAttribute("bugList", p);
            return "/project/data/build/relation/product-al-le-bug-data.pagelet";
        }

        return "";
    }

    /**
     * 关联需求
     *
     * @param ids
     * @param buildId
     * @return
     */
    @ResponseBody
    @RequestMapping("/releateReq")
    public Map<String, String> releateReq(String ids, Integer buildId) {
        if (StringUtil.isBlank(ids)) {
            return resultMap(false, "请至少选择一条数据");
        }
        ProjectBuild build = buildService.findBuild(buildId);
        List<String> origin = new ArrayList<String>(Arrays.asList(build.getBuildStories().split(",")));
        List<String> add = new ArrayList<String>(Arrays.asList(ids.split(",")));
        origin.addAll(add);
        build.setBuildStories(StringUtil.join(origin, ","));
        buildService.updateBuild(build);
        return resultMap(true, "关联成功");
    }

    @ResponseBody
    @RequestMapping("/releateBug")
    public Map<String, String> releateBug(String ids, Integer buildId) {
        if (StringUtil.isBlank(ids)) {
            return resultMap(false, "请至少选择一条数据");
        }
        ProjectBuild build = buildService.findBuild(buildId);
        List<String> origin = new ArrayList<String>(Arrays.asList(build.getBuildBugs().split(",")));
        List<String> add = new ArrayList<String>(Arrays.asList(ids.split(",")));
        origin.addAll(add);
        build.setBuildBugs(StringUtil.join(origin, ","));
        buildService.updateBuild(build);
        return resultMap(true, "关联成功");
    }

    @RequestMapping("/search/reRelateStory")
    public String storyListAction(int start, int limit, int id, String groupOperate, SearchInfos searchInfos,
                                  Model model) {
        Pager<ProductStory> p = projectStoryService.findStoryPager(start, limit, id, searchInfos, groupOperate);
        model.addAttribute("storys", p);

        return "/project/data/build/relation/product-al-req-data.pagelet";
    }

    @RequestMapping("/search/noRelateStory")
    public String storyNoListAction(int start,
                                    int limit,
                                    int id,
                                    String groupOperate,
                                    SearchInfos searchInfos,
                                    String order,
                                    String ordertype,
                                    Model model) {
        ProjectBuild build = buildService.findBuild(id);
        ConditionCarrier carrier = new ConditionCarrier();
        ProductStory story = new ProductStory();
        story.setDeleted(0);
        story.setProductId(build.getBuildProduct());
        if (searchInfos != null) {
            carrier.putSearch("storySearch", searchInfos, groupOperate);
        }
        carrier.putNotIns("productStory.storyId", build.getBuildStories().split(","));
        Pager<ProductStory> p = storyService.findStoryByCondition(start, limit, story, carrier, order, "asc".equals(ordertype) ? true : false);
        model.addAttribute("storys", p);

        return "/project/data/build/relation/product-al-no-req-data.pagelet";
    }

    @ResponseBody
    @RequestMapping("/deletereleate")
    public Map deleteReleate(Integer storyId, Integer buildId) {
        ProjectBuild build = buildService.findBuild(buildId);
        List<String> origin = new ArrayList<String>(Arrays.asList(build.getBuildStories().split(",")));
        origin.remove(String.valueOf(storyId));
        build.setBuildStories(StringUtil.join(origin, ","));
        buildService.updateBuild(build);
        return resultMap(true, "解除关联成功");

    }

    @ResponseBody
    @RequestMapping("/deletereleateBug")
    public Map deletereleateBug(Integer bugId, Integer buildId) {
        ProjectBuild build = buildService.findBuild(buildId);
        List<String> origin = new ArrayList<String>(Arrays.asList(build.getBuildBugs().split(",")));
        origin.remove(String.valueOf(bugId));
        build.setBuildBugs(StringUtil.join(origin, ","));
        buildService.updateBuild(build);
        return resultMap(true, "解除关联成功");
    }

    @ResponseBody
    @RequestMapping(value = "/batchDeleteReq")
    public Map batchDelReq(String ids, Integer buildId) {
        if (StringUtil.isBlank(ids)) {
            return resultMap(false, "请至少选择一条数据");
        }
        ProjectBuild build = buildService.findBuild(buildId);
        List<String> origin = new ArrayList<String>(Arrays.asList(build.getBuildStories().split(",")));
        List<String> remove = new ArrayList<String>(Arrays.asList(ids.split(",")));
        origin.removeAll(remove);
        build.setBuildStories(StringUtil.join(origin, ","));
        buildService.updateBuild(build);
        return resultMap(true, "删除成功");
    }

    @ResponseBody
    @RequestMapping(value = "/batchDeleteBug")
    public Map batchDeleteBug(String ids, Integer buildId) {
        if (StringUtil.isBlank(ids)) {
            return resultMap(false, "请至少选择一条数据");
        }
        ProjectBuild build = buildService.findBuild(buildId);
        List<String> origin = new ArrayList<String>(Arrays.asList(build.getBuildBugs().split(",")));
        List<String> remove = new ArrayList<String>(Arrays.asList(ids.split(",")));
        origin.removeAll(remove);
        build.setBuildBugs(StringUtil.join(origin, ","));
        buildService.updateBuild(build);
        return resultMap(true, "删除成功");
    }


    @ResponseBody
    @RequestMapping("/buildList")
    public List<ProjectBuild> findProjectBuild(ProjectBuild build, String from) {
        if ("product".equals(from)) {
            if (build.getBuildProduct() == null && build.getBuildProduct() < 1) {
                return new ArrayList<ProjectBuild>();
            } else {
                build.setBuildDeleted("0");
            }
        }
        return buildService.findListBuild(build);
    }

    @ResponseBody
    @RequestMapping("ajax/buildInCondition")
    public List<ProjectBuild> buildInCondition(String key, String initKey, Integer productId, Integer projectId, String withoutTrunk) {
        if (initKey != null) {
            String[] ids = initKey.split(",");
            if (!ArrayUtil.isEmptyArray(ids)) {
                List<ProjectBuild> projectBuildList = buildService.getBuildByIds(ids);
                boolean hasTrunk = false;
                for (String id : ids) {
                    if ("0".equals(id)) {
                        hasTrunk = true;
                    }
                }
                if (hasTrunk) {
                    ProjectBuild projectBuild = new ProjectBuild();
                    projectBuild.setBuildName("trunk");
                    projectBuild.setBuildId(0);
                    projectBuildList.add(projectBuild);
                }
                return projectBuildList;
            }
            List<ProjectBuild> result = new ArrayList<ProjectBuild>();
            if ("0".equals(initKey)) {
                ProjectBuild projectBuild = new ProjectBuild();
                projectBuild.setBuildName("trunk");
                projectBuild.setBuildId(0);
                result.add(projectBuild);
            } else {
                result.add(buildService.findBuild(Integer.parseInt(initKey)));
            }
            return result;
        }
        if (!StringUtil.isBlank(withoutTrunk)) {
            return buildService.buildInCondition(key, Integer.parseInt(configService.getConfigBySection(SystemConfig.SEARCH_CONFIG).getConfigKey()), productId, projectId);
        }
        ProjectBuild projectBuild = new ProjectBuild();
        projectBuild.setBuildName("trunk");
        projectBuild.setBuildId(0);
        List<ProjectBuild> projectBuildList = new ArrayList<ProjectBuild>();
        projectBuildList.add(projectBuild);
        projectBuildList.addAll(buildService.buildInCondition(key, Integer.parseInt(configService.getConfigBySection(SystemConfig.SEARCH_CONFIG).getConfigKey()), productId, projectId));
        return projectBuildList;
    }

    /**
     * 判断版本名称在同一产品下存在性
     * @param param
     * @param projectId
     * @param buildNamee
     * @return
     */
    @ResponseBody
    @RequestMapping("/judgeBuildNameExist")
    public Map judgeBuildNameExist(String param,Integer projectId,String buildNamee,Integer productId)
    {
        if(param==null)
        {
            return resultMap(false, "请输入版本名称");
        }

        if(StringUtil.equals(param,buildNamee))
        {
            return  resultMap(true,"");
        }

        String buildName = param;
        ProjectBuild build =new ProjectBuild();
        build.setBuildName(param);
        build.setBuildProject(projectId);
        build.setBuildProduct(productId);
        build.setBuildDeleted(ProjectBuild.DELETE_NO);
        List<ProjectBuild> builds = buildService.findListBuild(build);
        if (builds.size() != 0) {
            return resultMap(false, "该版本已存在");
        } else
        {
            return resultMap(true, "");
        }
    }
}
