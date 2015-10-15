package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.util.CookieUtils;
import org.tinygroup.sdpm.common.util.DateUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBurn;
import org.tinygroup.sdpm.project.service.inter.BurnService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.project.service.inter.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wangying14938 on 2015-09-22.燃尽图
 */
@Controller
@RequestMapping("/a/project/burn")
public class BurnAction extends BaseController {
    @Autowired
    private BurnService burnService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/init")
    public String initBurn(HttpServletRequest request, Model model, Integer choose, Integer interval) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        if (interval == null) {
            interval = 5;
        }
        /**
         * 1.获取项目的起始、结束日期
         * 2.获取项目起始的预计剩余时间
         * 3.获取项目间隔时间，默认为X天
         * 4.根据间隔计算平均剩余时间
         * 5.根据间隔计算时间戳（用于显示燃尽图下放的时间标记）
         * 6.根据间隔计算任务标记点，按小取整
         * 7.可能需要在新建项目的时候 添加燃尽图
         */
        DateFormat format = new SimpleDateFormat("yyyy-M-d");
        DateFormat format2 = new SimpleDateFormat("\"M\\/d\"");
        Project project = projectService.findById(projectId);
        Date startData = project.getProjectBegin();
        Date endData = project.getProjectEnd();

        //拼出燃尽图底下的日期
        StringBuffer days = new StringBuffer();
        //计算均值
        StringBuffer average = new StringBuffer();
        //设定具体数值点
        StringBuffer left = new StringBuffer();
        //设定的开始与结束日期间隔
        double disDays = DateUtils.getDistanceOfTwoDate(startData, endData);
        List<ProjectBurn> list = burnService.findBurnByProjectId(projectId);
        //均线起始值
        double topLeft = 0;
        if (list != null || !list.isEmpty()) {
            for (ProjectBurn burn : list) {
                if (topLeft < burn.getBurnLeft()) {
                    topLeft = burn.getBurnLeft();
                }
            }
        }

        //均线斜率
        double rake = topLeft / disDays;

        Date nextDay = startData;
        Date nextPoint = startData;
        double tLeft = 0;
        int i = 0;
        while (nextDay.before(endData)) {
            nextDay = DateUtils.addDays(startData, i);
            for (ProjectBurn burn : list) {
                if (burn.getBurnDate().getTime() == nextDay.getTime()) {
                    tLeft = burn.getBurnLeft();
                }
            }

            if (0 == i % interval) {
                //拼日期
                if (days.length() == 0) {
                    days.append(format2.format(nextDay));
                } else {
                    days.append("," + format2.format(nextDay));
                }

                if (average.length() == 0) {
                    average.append((topLeft - (rake * i)));
                } else {
                    average.append("," + (topLeft - (rake * i)));
                }

                if (!nextDay.after(new Date())) {
                    if (left.length() == 0) {
                        left.append(tLeft);
                    } else {
                        left.append("," + tLeft);
                    }
                }
            }
            i++;
        }

        model.addAttribute("average", average);
        model.addAttribute("days", days);
        model.addAttribute("left", left);

        model.addAttribute("choose", choose);
        return "project/task/projectBurn.page";
    }
}
