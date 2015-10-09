package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.util.CookieUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.service.inter.BurnService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.project.service.inter.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by wangying14938 on 2015-09-22.燃尽图
 */
@Controller
@RequestMapping("/project/burn")
public class BurnAction extends BaseController {
    @Autowired
    private BurnService burnService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/init")
    public String initBurn(HttpServletRequest request, Model model, Integer choose, String interval) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        /**
         * 1.获取项目的起始、结束日期
         * 2.获取项目起始的预计剩余时间
         * 3.获取项目间隔时间，默认为X天
         * 4.根据间隔计算平均剩余时间
         * 5.根据间隔计算时间戳（用于显示燃尽图下放的时间标记）
         * 6.根据间隔计算任务标记点，按小取整
         * 7.可能需要在新建项目的时候 添加燃尽图
         */
        DateFormat format = new SimpleDateFormat("yyy-MM-dd");
        Project project = projectService.findById(projectId);
        String startData = format.format(project.getProjectBegin());
        String endData = format.format(project.getProjectEnd());

        model.addAttribute("choose", choose);
        return "project/task/projectBurn.page";
    }
}
