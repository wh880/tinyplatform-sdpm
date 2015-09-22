package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.project.service.inter.BurnService;

/**
 * Created by wangying14938 on 2015-09-22.燃尽图
 */
@Controller
@RequestMapping("/project/task")
public class burnAction extends BaseController {
    @Autowired
    private BurnService burnService;
}
