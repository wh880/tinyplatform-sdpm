package org.tinygroup.sdpm.action.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.docTemplate.inter.DocTemplateResolver;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.dto.UploadProfile;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductRelease;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.utils.FieldUtil;
import org.tinygroup.sdpm.product.service.inter.ProductService;
import org.tinygroup.sdpm.product.service.inter.ReleaseService;
import org.tinygroup.sdpm.product.service.inter.StoryService;
import org.tinygroup.sdpm.project.service.inter.BuildService;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.quality.service.inter.BugService;
import org.tinygroup.sdpm.system.dao.pojo.ProfileType;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
import org.tinygroup.sdpm.util.CookieUtils;
import org.tinygroup.sdpm.util.ExportUtils;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.ProductUtils;
import org.tinygroup.template.TemplateContext;
import org.tinygroup.template.TemplateException;
import org.tinygroup.template.impl.TemplateContextDefault;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 发布控制器
 *
 * @author wangjie14929
 */
@Controller
@RequestMapping("/a/product/release")
public class ReleaseAction extends BaseController {

    @Autowired
    private ReleaseService releaseService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private StoryService storyService;
    @Autowired
    private BugService bugService;
    @Autowired
    private BuildService buildService;
    @Autowired
    private ExportUtils exportUtils;

    @ModelAttribute
    public void init(Model model) {
        initSearchBar(model, "发布");
    }

    @RequestMapping("/content")
    public String release() {
        return "/product/page/list/release/product-release.page";
    }

    @RequestMapping("/save")
    public String save(@CookieValue(value = "cookieProductId", defaultValue = "0") String cookieProductId,
                       ProductRelease productRelease,
                       SystemAction systemAction,
                       String lastAddress,
                       String currentAddress,
                       UploadProfile uploadProfile) throws IOException {
        productRelease.setProductId(Integer.parseInt(cookieProductId));
        ProductRelease release = releaseService.addRelease(productRelease);
        processProfile(uploadProfile, release.getReleaseId(), ProfileType.RELEASE);
        LogUtil.logWithComment(LogUtil.LogOperateObject.RELEASE
                , LogUtil.LogAction.OPENED
                , String.valueOf(release.getReleaseId())
                , userUtils.getUserId()
                , String.valueOf(release.getProductId())
                , null
                , null
                , null
                , systemAction.getActionComment());
        if (!StringUtil.isBlank(currentAddress)) {
            return "redirect:" + currentAddress;
        } else {
            if (!StringUtil.isBlank(lastAddress)) {
                return "redirect:" + lastAddress;
            }
        }
        return "redirect:" + adminPath + "/product/release/content";
    }

    @RequestMapping("/update")
    public String update(ProductRelease release,
                         SystemAction systemAction,
                         String lastAddress,
                         UploadProfile uploadProfile) throws IOException {
        ProductRelease release1 = releaseService.findRelease(release.getReleaseId());
        processProfile(uploadProfile, release.getReleaseId(), ProfileType.RELEASE);
        releaseService.updateRelease(release);
        LogUtil.logWithComment(LogUtil.LogOperateObject.RELEASE
                , LogUtil.LogAction.EDITED
                , String.valueOf(release.getReleaseId())
                , userUtils.getUserId()
                , String.valueOf(release1.getProductId())
                , null
                , release1
                , release
                , systemAction.getActionComment());
        if (!StringUtil.isBlank(lastAddress)) {
            return "redirect:" + lastAddress;
        }
        return "redirect:" + adminPath + "/product/release/content";
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Map delete(Integer releaseId, SystemAction systemAction) throws IOException {

        ProductRelease release1 = releaseService.findRelease(releaseId);
        releaseService.deleteRelease(releaseId);
        ProductRelease release = releaseService.findRelease(releaseId);
        Map<String, String> map = new HashMap<String, String>();

        LogUtil.logWithComment(LogUtil.LogOperateObject.RELEASE
                , LogUtil.LogAction.DELETED
                , String.valueOf(releaseId)
                , userUtils.getUserId()
                , String.valueOf(release1.getProductId())
                , null
                , release1
                , release
                , systemAction.getActionComment());

        map.put("status", "success");
        map.put("info", "删除成功");
        return map;
    }

    @RequestMapping("find")
    public String find(Integer releaseId, Model model, @CookieValue(value= ProductUtils.COOKIE_PRODUCT_ID)String currentProductId) {
        ProductRelease release = releaseService.findRelease(releaseId);
        model.addAttribute("release", release);

        if(release.getProductId()!=Integer.parseInt(currentProductId))
        {
            return "redirect:" + adminPath + "/product/release/content";
        }

        SystemProfile systemProfile = new SystemProfile();
        systemProfile.setFileObjectId(releaseId);
        systemProfile.setFileObjectType(ProfileType.RELEASE.getType());
        List<SystemProfile> fileList = profileService.findSystemProfile(systemProfile);
        model.addAttribute("fileList", fileList);
        return "/product/page/update/release/product-release-update";
    }

    @RequestMapping("/find/{forwordPager}")
    public String find(@PathVariable(value = "forwordPager") String forwordPager, Integer releaseId, Model model) {
        SystemProfile systemProfile = new SystemProfile();
        systemProfile.setFileObjectType("release");
        systemProfile.setFileDeleted("0");
        systemProfile.setFileObjectId(releaseId);
        List<SystemProfile> systemProfiles = profileService.findSystemProfile(systemProfile);
        ProductRelease release = releaseService.findRelease(releaseId);
        model.addAttribute("release", release);
        model.addAttribute("file", systemProfiles);
        if ("relateStory".equals(forwordPager)) {
            return "/product/page/relation/release/releasebaseinfo.pagelet";
        }

        return "";
    }

    @RequestMapping("/forword/{forwordPager}")
    public String forword(@PathVariable(value = "forwordPager") String forwordPager,
                          Integer releaseId,
                          Model model,
                          Integer no,
                          HttpServletRequest request,
                          @CookieValue(value=ProductUtils.COOKIE_PRODUCT_ID)String currentProductId) {

        if (no != null) {
            String result = CookieUtils.getCookie(request, productUtils.COOKIE_PRODUCT_ID);
            if (StringUtil.isBlank(result)) {
                return notFoundView();
            }
            Integer cookieProductId = Integer.parseInt(result);
            ProductRelease release = new ProductRelease();
            release.setProductId(cookieProductId);
            release.setNo(no);
            List<ProductRelease> releaseList = releaseService.findReleaseList(release);
            if (releaseList.size() == 0) {
                return notFoundView();
            }
            releaseId = releaseList.get(0).getReleaseId();
        }
        model.addAttribute("releaseId", releaseId);

        ProductRelease release=releaseService.findRelease(releaseId);
        if(release.getProductId()!=Integer.parseInt(currentProductId))
        {
            return "redirect:" + adminPath + "/product/release/content";
        }


        if ("reRelateStory".equals(forwordPager)) {
            return "/product/page/relation/release/product-al-req.page";
        } else if ("noRelateStory".equals(forwordPager)) {
            return "/product/page/relation/release/product-al-no-req.page";
        } else if ("reRelateBug".equals(forwordPager)) {
            return "/product/page/relation/release/product-al-bug.page";
        } else if ("noRelateBug".equals(forwordPager)) {
            return "/product/page/relation/release/product-al-no-bug.page";
        } else if ("leRelateBug".equals(forwordPager)) {
            return "product/page/relation/release/product-al-le-bug.page";
        }


        return "/product/page/relation/plan/planbaseinfo.pagelet";
    }

    @RequestMapping("list")
    public String list(ProductRelease release,
                       @CookieValue("cookieProductId") String cookieProductId,
                       @RequestParam(required = false, defaultValue = "1") int page,
                       @RequestParam(required = false, defaultValue = "10") int pagesize,
                       @RequestParam(required = false, defaultValue = "releaseId") String order,
                       @RequestParam(required = false, defaultValue = "desc") String ordertype, Model model, HttpServletRequest request) {

        release.setProductId(Integer.parseInt(cookieProductId));
        Pager<ProductRelease> pagerProductRelease = releaseService.findReleasePager(page, pagesize, release, order, ordertype);

        model.addAttribute("productRelease", pagerProductRelease);
        return "/product/data/release/allproduct-release.pagelet";
    }

    @ResponseBody
    @RequestMapping("/releaseList")
    public List<ProductRelease> findRelease(ProductRelease release) {
        List<ProductRelease> list = releaseService.findReleaseList(release);
        return list;
    }

    @RequestMapping("/addRelease")
    public String addRelease(@CookieValue(value = "cookieProductId", defaultValue = "0") String cookieProductId, HttpServletRequest request, Model model) {
        Product product = productService.findProductById(Integer.parseInt(cookieProductId));
        model.addAttribute("product", product);
        return "/product/page/add/release/product-addrelease.page";
    }

    @ResponseBody
    @RequestMapping("/ajaxDelAlWork")
    public Map deleteRel(Integer releaseId, String storyId) {

        ProductRelease release = releaseService.findRelease(releaseId);
        if (release != null && !StringUtil.isBlank(release.getReleaseStories())) {
            List<String> origin = new ArrayList<String>(Arrays.asList(release.getReleaseStories().split(",")));
            origin.remove(String.valueOf(storyId));
            release.setReleaseStories(StringUtil.join(origin.toArray(), ","));
            releaseService.updateRelease(release);
        }


        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "成功");

        return map;
    }

    @ResponseBody
    @RequestMapping("/ajaxDelBug")
    public Map deleteRelBug(Integer releaseId, String bugId) {

        ProductRelease release = releaseService.findRelease(releaseId);
        if (release != null && !StringUtil.isBlank(release.getReleaseBugs())) {
            List<String> origin = new ArrayList<String>(Arrays.asList(release.getReleaseBugs().split(",")));
            origin.remove(String.valueOf(bugId));
            release.setReleaseBugs(StringUtil.join(origin.toArray(), ","));
            releaseService.updateRelease(release);
        }

        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "成功");

        return map;
    }

    @ResponseBody
    @RequestMapping("/ajaxBatchDelAlWork")
    public Map deleteBatchStory(Integer releaseId, String ids) {

        ProductRelease release = releaseService.findRelease(releaseId);
        if (release != null && !StringUtil.isBlank(release.getReleaseStories())) {
            List<String> origin = new ArrayList<String>(Arrays.asList(release.getReleaseStories().split(",")));
            List<String> remove = new ArrayList<String>(Arrays.asList(ids.split(",")));
            origin.removeAll(remove);
            release.setReleaseStories(StringUtil.join(origin.toArray(), ","));
            releaseService.updateRelease(release);
        }


        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "成功");

        return map;
    }

    @ResponseBody
    @RequestMapping("/ajaxBatchDelBug")
    public Map deleteBatchBug(Integer releaseId, String ids) {

        ProductRelease release = releaseService.findRelease(releaseId);
        if (release != null && !StringUtil.isBlank(release.getReleaseBugs())) {
            List<String> origin = new ArrayList<String>(Arrays.asList(release.getReleaseBugs().split(",")));
            List<String> remove = new ArrayList<String>(Arrays.asList(ids.split(",")));
            origin.removeAll(remove);
            release.setReleaseBugs(StringUtil.join(origin.toArray(), ","));
            releaseService.updateRelease(release);
        }


        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "成功");

        return map;
    }

    @ResponseBody
    @RequestMapping("/ajaxRelateStory")
    public Map RelateStory(Integer releaseId, String ids) {
        String[] stories = ids.split(",");
        if (stories.length > 0) {
            ProductRelease release = releaseService.findRelease(releaseId);
            String releaseStories = "";
            if (release != null && !StringUtil.isBlank(release.getReleaseStories())) {
                releaseStories = release.getReleaseStories();
            }
            List<String> origin = new ArrayList<String>(Arrays.asList(releaseStories.split(",")));
            for (String id : stories) {
                ProductStory productStory = storyService.findStory(Integer.parseInt(id));
                productStory.setStoryStage("9");
                if (!origin.contains(id)) {
                    origin.add(id);
                }
                storyService.updateStory(productStory);
            }
            if(release!=null){
                release.setReleaseStories(StringUtil.join(origin.toArray(), ","));
                releaseService.updateRelease(release);
            }
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "成功");

        return map;
    }

    @ResponseBody
    @RequestMapping("/ajaxRelateBug")
    public Map RelateBug(Integer releaseId, String ids) {
        String[] bugs = ids.split(",");
        if (bugs.length > 0 ) {
            ProductRelease release = releaseService.findRelease(releaseId);
            String releaseBugs = "";
            if (release != null && !StringUtil.isBlank(release.getReleaseBugs())) {
                releaseBugs = release.getReleaseBugs();
            }
            List<String> origin = new ArrayList<String>(Arrays.asList(releaseBugs.split(",")));
            for (String bugId : bugs) {
                if (!origin.contains(bugId)) {
                    origin.add(bugId);
                }
            }
            if(release!=null){
                release.setReleaseBugs(StringUtil.join(origin.toArray(), ","));
                releaseService.updateRelease(release);
            }
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "成功");
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/judgeReleaseName")
    public Map judgeReleaseName(String param, Integer releaseId, Integer productId) {
        if (param != null) {
            String releaseName = param;
            ProductRelease release = new ProductRelease();
            release.setReleaseName(releaseName);
            release.setProductId(productId);
            release.setDeleted(FieldUtil.DELETE_NO);
            List<ProductRelease> releases = releaseService.findReleaseList(release);
            if (releases.size() != 0) {
                if (releaseId == null) {
                    return resultMap(false, "该发布已存在");
                } else if (!releaseId.equals(releases.get(0).getReleaseId())) {
                    return resultMap(false, "该发布已存在");
                } else {
                    return resultMap(true, "");
                }
            } else {
                return resultMap(true, "");
            }
        }
        return resultMap(false, "请输入发布名称");
    }

    @RequestMapping("/exportDoc")
    public void exportDocument(Integer releaseId, HttpServletResponse response) throws IOException, TemplateException {
        TemplateContext context = new TemplateContextDefault();
        if (releaseId != null) {
            ProductRelease release = releaseService.findRelease(releaseId);
            String releaseStory = release.getReleaseStories();
            List<Integer> sIds = null;
            if (!StringUtil.isBlank(releaseStory)) {
                sIds = new ArrayList<Integer>();
                String[] ids = releaseStory.split(",");
                for (String id : ids) {
                    if (StringUtil.isBlank(id)) {
                        continue;
                    }
                    sIds.add(Integer.parseInt(id));
                }
            }
            List<ProductStory> stories = new ArrayList<ProductStory>();
            if (sIds != null && sIds.size() > 0) {
                Integer[] nIds = new Integer[sIds.size()];
                stories = storyService.getStoryWithSpecInIds(true, sIds.toArray(nIds));
            }
            context.put("storyList", stories);

            QualityBug bug = new QualityBug();
            bug.setProductId(release.getProductId());
            bug.setBugOpenedBuild(release.getReleaseBuild());
            String releaseBugs = release.getReleaseBugs();
            String inCondition = StringUtil.isBlank(releaseBugs) ? "" : releaseBugs;
            ConditionCarrier carrier;
            carrier = new ConditionCarrier();
            carrier.putIns("qualityBug.bugId", inCondition.split(","));

            context.put("bugInList", bugService.findBugListPager(
                    0, 100, carrier, bug, null, false).getRecords());
            carrier = new ConditionCarrier();
            bug.setBugStatus("1");
            context.put("bugNoInList", bugService.findBugListPager(
                    0, 100, carrier, bug, null, false).getRecords());
            context.put("ExportUtils", exportUtils);
            context.put("productService", productService);
        }
        exportUtils.mergeTemplate(DocTemplateResolver.RELEASE, context, response, "发布文档");
    }
}
