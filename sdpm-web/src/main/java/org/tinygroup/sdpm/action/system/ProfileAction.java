package org.tinygroup.sdpm.action.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
@Controller
@RequestMapping("system/profile")
public class ProfileAction extends BaseController{
	@Autowired
	ProfileService profileService;
	@RequestMapping("find/{type}")
	public String  find(SystemProfile profile,@PathVariable(value="type")String type,Model model){
		if("doc".equals(type)){
			profileService.find(profile);
			return null;
		}
		return null;
	}
	@RequestMapping("edit/{type}")
	public String eidt(SystemProfile profile,@PathVariable(value="type")String type,Model model){
		if("doc".equals(type)){
			profileService.editTitle(profile);
			return null;
		}
		return null;
	}
	@RequestMapping("edit/{type}")
	public String delete(Integer id,@PathVariable(value="type")String type){
		if("doc".equals(type)){
			profileService.softDelete(id);
			return null;
		}
		return null;
	}
}
