package org.tinygroup.sdpm.action.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.inter.ProfileService;

import java.util.Map;

/**
 * 附件管理
 */
@Controller
@RequestMapping("a/system/profile")
public class ProfileAction extends BaseController {
    @Autowired
    ProfileService profileService;

    @RequestMapping("find/{type}")
    public String find(Integer fileId, @PathVariable(value = "type") String type, Model model) {
        if ("doc".equals(type)) {
            SystemProfile profile = profileService.findSystemProfileById(fileId);
            model.addAttribute("profile", profile);
            return "/document/file-edit.pagelet";
        }
        if ("story".equals(type)) {
            SystemProfile profile = profileService.findSystemProfileById(fileId);
            model.addAttribute("profile", profile);
            return "/product/page/tabledemo/file-edit.pagelet";
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("edit/{type}")
    public Map<String, String> edit(SystemProfile profile, @PathVariable(value = "type") String type) {
        if ("doc".equals(type)) {
            profileService.editSystemProfileTitle(profile);
            return resultMap(true, "修改成功");
        }
        if ("story".equals(type)) {
            profileService.editSystemProfileTitle(profile);
            return resultMap(true, "修改成功");
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("delete")
    public Map delete(Integer id) {
        profileService.softDeleteSystemProfile(id);
        return resultMap(true, "删除成功");
    }
}
