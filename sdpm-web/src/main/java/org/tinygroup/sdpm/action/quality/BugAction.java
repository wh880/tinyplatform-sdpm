package org.tinygroup.sdpm.action.quality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.quality.service.inter.BugService;

/**
 * Created by chenpeng15668 on 2015-9-22
 */

@Controller
@RequestMapping("/quality/bug")
public class BugAction {

	@Autowired
	private BugService bugService;
}
