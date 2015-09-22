package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.project.service.inter.TaskService;

/**
 * Created by wangying14938 on 2015-09-22.任务
 */
@Controller
@RequestMapping("/project/task")
public class TaskAction extends BaseController {
    @Autowired
    private TaskService taskService;
//    @RequestMapping("/form")
//    public String form(String id, Model model) {
//        if (!StringUtil.isBlank(id)) {
//            OrgUser user = taskService.;
//            model.addAttribute("user", user);
//        }
//        return "organization/common/addUser.page";
//    }
//
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public String save(OrgUser user, Model model) {
//        if (StringUtil.isBlank(user.getOrgUserId())) {
//            taskService.addTask();
//        } else {
//            taskService.updateTask();
//        }
//        model.addAttribute("user", user);
//        return "organization/common/addUser.page";
//    }
}
