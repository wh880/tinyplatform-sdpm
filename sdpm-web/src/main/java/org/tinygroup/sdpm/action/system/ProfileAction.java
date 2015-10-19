package org.tinygroup.sdpm.action.system;

import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLEngineResult.Status;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
@Controller
@RequestMapping("a/system/profile")
public class ProfileAction extends BaseController{
	@Autowired
	ProfileService profileService;
	@RequestMapping("find/{type}")
	public String  find(int fileId,@PathVariable(value="type")String type,Model model){
		if("doc".equals(type)){
			SystemProfile profile=profileService.findById(fileId);
			model.addAttribute("profile", profile);
			return "/document/file-edit.pagelet";
		}
		return null;
	}
	
	@RequiresPermissions(value="{doc-file-edit}")
	@RequestMapping("edit/{type}")
	public String eidt(SystemProfile profile,@PathVariable(value="type")String type,Model model){
		if("doc".equals(type)){
			profileService.editTitle(profile);
			return "a/document/doc/view?docid="+profile.getFileObjectId();
		}
		return null;
	}
	
	@RequiresPermissions(value="{doc-file-delete}")
	@ResponseBody
	@RequestMapping("delete/{type}")
	public Map delete(Integer delId,@PathVariable(value="type")String type){
		if("doc".equals(type)){
			profileService.softDelete(delId);
			Map<String, String> map = new HashMap<String, String>();
			map.put("status", "success");
			map.put("info", "删除成功");
			return map;
		}
		return null;
	}
}
