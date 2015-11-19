package org.tinygroup.sdpm.action.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.dao.pojo.ProductRelease;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.product.service.ReleaseService;
import org.tinygroup.sdpm.product.service.StoryService;
import org.tinygroup.sdpm.system.dao.pojo.ProfileType;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.inter.ExportService;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.UserUtils;
import org.tinygroup.template.TemplateException;
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
    private ExportService exportService;

    public static String storyIdUtils(String soure, String inde) {
        soure = soure.replaceAll(inde, "").replaceAll(",,", ",");
        if (soure.startsWith(",")) {
            soure = soure.substring(1);
        }
        if (soure.endsWith(",")) {
            soure = soure.substring(0, soure.length() - 1);
        }
        return soure;
    }

    @RequestMapping("/content")
    public String release( HttpServletRequest request, Model model) {

        return "/product/page/project/product-release.page";
    }

    @RequestMapping("/save")
    public String save(@CookieValue(value = "cookieProductId",defaultValue = "0") String cookieProductId,ProductRelease productRelease, HttpServletRequest request, SystemAction systemAction, @RequestParam(value = "file", required = false) MultipartFile[] file, String[] title) throws IOException {
        productRelease.setProductId(Integer.parseInt(cookieProductId));
        ProductRelease release = releaseService.addRelease(productRelease);
        uploads(file, release.getReleaseId(), ProfileType.RELEASE, title);
        LogUtil.logWithComment(LogUtil.LogOperateObject.RELEASE
                , LogUtil.LogAction.OPENED
                , String.valueOf(release.getReleaseId())
                , UserUtils.getUserId()
                , String.valueOf(release.getProductId())
                , null
                , null
                , null
                , systemAction.getActionComment());

        return "redirect:" + "/a/product/release/content";
    }

    @RequestMapping("/update")
    public String update(ProductRelease release, SystemAction systemAction, @RequestParam(value = "file", required = false) MultipartFile[] file, String[] title) throws IOException {
        ProductRelease release1 = releaseService.findRelease(release.getReleaseId());
        releaseService.updateRelease(release);

        uploads(file, release.getReleaseId(), ProfileType.RELEASE, title);


        LogUtil.logWithComment(LogUtil.LogOperateObject.RELEASE
                , LogUtil.LogAction.EDITED
                , String.valueOf(release.getReleaseId())
                , UserUtils.getUserId()
                , String.valueOf(release1.getProductId())
                , null
                , release1
                , release
                , systemAction.getActionComment());

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
                , UserUtils.getUserId()
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
    public String find(Integer releaseId, Model model) {
        ProductRelease release = releaseService.findRelease(releaseId);
        model.addAttribute("release", release);
        return "/product/page/tabledemo/product-release-update.page";
    }

    @RequestMapping("/find/{forwordPager}")
    public String find(@PathVariable(value = "forwordPager") String forwordPager, Integer releaseId, Model model) {
        SystemProfile systemProfile = new SystemProfile();
        systemProfile.setFileObjectType("release");
        systemProfile.setFileDeleted("0");
        systemProfile.setFileObjectId(releaseId);
        List<SystemProfile> systemProfiles = profileService.find(systemProfile);
        ProductRelease release = releaseService.findRelease(releaseId);
        model.addAttribute("release", release);
        model.addAttribute("file", systemProfiles);
        if ("relateStory".equals(forwordPager)) {
            return "/product/page/tabledemo/relation-release/releasebaseinfo.pagelet";
        }

        return "";
    }

    @RequestMapping("/forword/{forwordPager}")
    public String forword(@PathVariable(value = "forwordPager") String forwordPager, Integer releaseId, Model model) {

        model.addAttribute("releaseId", releaseId);

        if ("reRelateStory".equals(forwordPager)) {
            return "/product/page/tabledemo/relation-release/product-al-req.page";
        } else if ("noRelateStory".equals(forwordPager)) {
            return "/product/page/tabledemo/relation-release/product-al-no-req.page";
        } else if ("reRelateBug".equals(forwordPager)) {
            return "/product/page/tabledemo/relation-release/product-al-bug.page";
        } else if ("noRelateBug".equals(forwordPager)) {
            return "/product/page/tabledemo/relation-release/product-al-no-bug.page";
        } else if ("leRelateBug".equals(forwordPager)) {
            return "product/page/tabledemo/relation-release/product-al-le-bug.page";
        }


        return "/product/page/tabledemo/relation-plan/planbaseinfo.pagelet";
    }

    @RequestMapping("list")
    public String list(ProductRelease release,
                       @CookieValue("cookieProductId") String cookieProductId,
                       @RequestParam(required = false, defaultValue = "1") int page,
                       @RequestParam(required = false, defaultValue = "10") int pagesize,
                       @RequestParam(required = false, defaultValue = "releaseId") String order,
                       @RequestParam(required = false, defaultValue = "asc") String ordertype, Model model, HttpServletRequest request) {

        release.setProductId(Integer.parseInt(cookieProductId));
        Pager<ProductRelease> pagerProductRelease = releaseService.findReleasePager(page, pagesize, release, order, ordertype);

        model.addAttribute("productRelease", pagerProductRelease);
        return "/product/data/allproduct-release.pagelet";
    }

    @ResponseBody
    @RequestMapping("/releaseList")
    public List<ProductRelease> findRelease(ProductRelease release) {
        List<ProductRelease> list = releaseService.findReleaseList(release);
        return list;
    }

    @RequestMapping("/addRelease")
    public String addRelease(@CookieValue(value = "cookieProductId",defaultValue = "0") String cookieProductId,HttpServletRequest request, Model model) {
        Product product = productService.findProduct(Integer.parseInt(cookieProductId));
        model.addAttribute("product", product);
        return "/product/page/tabledemo/product-addrelease.page";
    }

    @ResponseBody
    @RequestMapping("/ajaxDelAlWork")
    public Map deleteRel(Integer releaseId, String storyId) {

        ProductRelease release = releaseService.findRelease(releaseId);
        if (release != null && !StringUtil.isBlank(release.getReleaseStories())) {
            List<String> origin = new ArrayList<String>(Arrays.asList(release.getReleaseStories().split(",")));
            origin.remove(String.valueOf(storyId));
            release.setReleaseStories(StringUtil.join(origin.toArray(),","));
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
            release.setReleaseStories(StringUtil.join(origin.toArray(),","));
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
    public Map RelateStory(Integer releaseId,String ids) {
        String[] stories = ids.split(",");
        if (stories.length > 0 && stories != null) {
            ProductRelease release = releaseService.findRelease(releaseId);
            String releaseStories = "";
            if(release!=null&&!StringUtil.isBlank(release.getReleaseStories())){
                releaseStories = release.getReleaseStories();
            }
            for (String id : stories) {
                ProductStory productStory = storyService.findStory(Integer.parseInt(id));
                productStory.setStoryStage("9");
                if ("".equals(releaseStories)) {
                    releaseStories = releaseStories + id;
                }else{
                    releaseStories = releaseStories + "," + id;
                }
                storyService.updateStory(productStory);
            }
            release.setReleaseStories(releaseStories);
            releaseService.updateRelease(release);
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "成功");

        return map;
    }

    @ResponseBody
    @RequestMapping("/ajaxRelateBug")
    public Map RelateBug(Integer releaseId,String ids) {
        String[] bugs = ids.split(",");
        if (bugs.length > 0 && bugs != null) {
            ProductRelease release = releaseService.findRelease(releaseId);
            String releaseBugs = "";
            if(release!=null&&!StringUtil.isBlank(release.getReleaseBugs())){
                releaseBugs = release.getReleaseBugs();
            }
            for (String bugId : bugs) {
                if (release != null && releaseBugs != null) {
                    if ("".equals(releaseBugs)) {
                        releaseBugs = releaseBugs +bugId;
                    } else {
                        releaseBugs = releaseBugs + "," + bugId;
                    }

                }
            }
            release.setReleaseBugs(releaseBugs);
            releaseService.updateRelease(release);
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "成功");
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/judgeReleaseName")
    public Map judgeReleaseName(ProductRelease productRelease) {
        Integer releaseId=null;
        if(productRelease.getProductId()!=null){
            releaseId = productRelease.getProductId();
            productRelease.setReleaseId(null);
        }
        List<ProductRelease> releases = releaseService.findReleaseList(productRelease);
        if (releases.size() >=1) {
            if(releases.size()==1&&releaseId!=null){
                return resultMap(releaseId.equals(releases.get(0).getReleaseId())?false:true,"");
            }
            return resultMap(true, "");
        } else {
            return resultMap(false, "");
        }
    }
    @RequestMapping("/exportDoc")
    public void exportDocument(Integer releaseId, HttpServletResponse response) throws IOException, TemplateException {
        exportService.exportReleaseDoc(response,releaseId);
    }
}
