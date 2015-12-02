package org.tinygroup.sdpm.action.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.sdpm.product.service.StoryService;
import org.tinygroup.sdpm.product.service.StorySpecService;
import org.tinygroup.sdpm.system.dao.pojo.ProfileType;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
import org.tinygroup.sdpm.util.CookieUtils;
import org.tinygroup.sdpm.util.ProductUtils;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/a/product/storySpec")
public class StorySpecAction extends BaseController {

    @Autowired
    private StorySpecService specService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private StoryService storyService;

    @RequestMapping("/find")
    public String find(Integer storyId, Model model) {
        ProductStory story = storyService.findStory(storyId);
        ProductStorySpec storySpec = new ProductStorySpec();
        storySpec.setStoryVersion(story.getStoryVersion());
        storySpec.setStoryId(storyId);
        List<ProductStorySpec> storySpecs = specService.findStorySpecList(storySpec, null, null);
        storySpec = storySpecs != null && storySpecs.size() > 0 ? storySpecs.get(0) : new ProductStorySpec();
        model.addAttribute("storySpec", storySpec);


        SystemProfile systemProfile = new SystemProfile();
        systemProfile.setFileObjectId(storyId);
        systemProfile.setFileObjectType(ProfileType.STORY.getType());
        List<SystemProfile> fileList = profileService.findSystemProfile(systemProfile);
        model.addAttribute("fileList", fileList);
        return "/product/page/update/story/demand-edit.page";
    }

    @RequestMapping("/find/{forward}")
    public String find(@PathVariable(value = "forward") String forward,
                       ProductStory story,
                       Model model,
                       SystemProfile systemProfile,
                       HttpServletRequest request) {
        ProductStory productStory = null;
        if (story.getNo() != null) {
            Integer cookieProductId = Integer.parseInt(CookieUtils.getCookie(request, ProductUtils.COOKIE_PRODUCT_ID));
            if (cookieProductId == null) {
                return notFoundView();
            }
            productStory = new ProductStory();
            productStory.setProductId(cookieProductId);
            productStory.setNo(story.getNo());
            List<ProductStory> storyList = storyService.findStoryList(productStory);
            if (storyList.size() == 0) {
                return notFoundView();
            }
            productStory = storyList.get(0);
        }
        if (productStory == null || productStory.getStoryId() == null) {
            productStory = storyService.findStory(story.getStoryId());
        }
        ProductStorySpec storySpec = specService.findStorySpec(productStory.getStoryId(), productStory.getStoryVersion());
        model.addAttribute("storySpec", storySpec);
        model.addAttribute("story", productStory);
        systemProfile.setFileObjectType("story");
        systemProfile.setFileDeleted("0");
        systemProfile.setFileObjectId(story.getStoryId());
        List<SystemProfile> list = profileService.findSystemProfile(systemProfile);
        model.addAttribute("file", list);
        if ("productDemandDetail".equals(forward)) {
            return "/product/page/view/story/demdtablehref.page";
        }
        return "";
    }

    @RequestMapping("storyVersion")
    public String storyVersion() {
        return "/product/page/version/allVersion.pagelet";
    }

    @RequestMapping("storyVersionData")
    public String storyHistoryVersion(Integer storyId, Model model, Integer start, Integer limit, @RequestParam(defaultValue = "storyVersion") String order, @RequestParam(defaultValue = "asc") String ordertype) {
        ProductStory story = storyService.findStory(storyId);
        ProductStorySpec spec = new ProductStorySpec();
        spec.setStoryId(storyId);
        Pager<ProductStorySpec> specs = specService.findStorySpecPager(start, limit, spec, order, ordertype);
        model.addAttribute("story", story);
        model.addAttribute("versions", specs);
        return "/product/page/version/allVersionData.pagelet";
    }

    @RequestMapping("versionRollback")
    public String storyVersionRollback(Integer storyId, Integer storyVersion) {
        ProductStorySpec spec = specService.findStorySpec(storyId, storyVersion);
        ProductStory story = storyService.findStory(storyId);
        story.setStoryTitle(spec.getStoryTitle());
        story.setStoryVersion(storyVersion);
        storyService.updateStory(story);
        return "redirect:/a/product/storySpec/find/productDemandDetail?storyId=" + storyId;
    }
}
