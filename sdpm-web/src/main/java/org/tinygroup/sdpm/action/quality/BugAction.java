package org.tinygroup.sdpm.action.quality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.quality.dao.pojo.Bug;
import org.tinygroup.sdpm.quality.service.inter.BugService;

/**
 * Created by chenpeng15668 on 2015-9-22
 */

@Controller
@RequestMapping("/quality/bug")
public class BugAction extends BaseController {

	@Autowired
	private BugService bugService;
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String save(Bug bug,Model model){
		return "testManagement/page/Bug.page";
	}
}
