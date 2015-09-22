package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.service.inter.TaskService;

/**
 * Created by shenly13343 on 2015-09-22.
 */
@Controller
@RequestMapping("/project/task")
public class TaskAction extends BaseController {
    @Autowired
    private TaskService taskService;

    @RequestMapping("/add")
    public String addTask(ProjectTask projectTask, Model model) {
        return "project/page/index.page";
    }

}
