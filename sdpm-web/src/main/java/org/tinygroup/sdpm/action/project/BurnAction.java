package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.convert.ConvertException;
import org.tinygroup.convert.objectjson.jackson.ObjectToJson;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.sdpm.common.util.DateUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBurn;
import org.tinygroup.sdpm.project.service.inter.BurnService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.util.ProjectUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 燃尽图
 * Created by wangying14938 on 2015-09-22.
 */
@Controller
@RequestMapping("/a/project/burn")
public class BurnAction extends BaseController {
    @Autowired
    private BurnService burnService;
    @Autowired
    private ProjectService projectService;

    /**
     * 1.获取项目的起始、结束日期
     * 2.获取项目起始的预计剩余时间
     * 3.获取项目间隔时间，默认为X天
     * 4.根据间隔计算平均剩余时间
     * 5.根据间隔计算时间戳（用于显示燃尽图下放的时间标记）
     * 6.根据间隔计算任务标记点，按小取整
     * 7.可能需要在新建项目的时候 添加燃尽图
     */

    /**
     * 燃尽图数据初始化
     *
     * @param choose   激活标签
     * @param interval 选择间隔时间
     * @param ajax     判断是否为ajax请求的标识位
     * @param model
     * @return
     */
    @RequestMapping("/init")
    public String initBurn(@CookieValue(value = TaskAction.COOKIE_PROJECT_ID, required = false) Integer projectId,
                           Integer choose, Integer interval, Model model, String ajax) {
        if (interval == null) {
            interval = 3;
        }
        DateFormat format = new SimpleDateFormat("M/d");//日期格式"M/d"
        Project project = ProjectUtils.getProject(projectId.toString());
        Date startData = project.getProjectBegin();
        Date endData = project.getProjectEnd();
        //项目周期
        float period = (float) DateUtils.getDistanceOfTwoDate(startData, endData);

        List<ProjectBurn> projectBurnList = burnService.findBurnByProjectId(projectId);
        //均线起始值
        float topLeft = getMaxLeftTime(projectBurnList);
        //均线斜率
        Float rake = topLeft / period;

        Date nextDay = startData;
        Float tLeft = null;

        List<Float> leftList = new ArrayList<Float>();
        List<Float> averageList = new ArrayList<Float>();
        List<String> dateList = new ArrayList<String>();
        for (int i = 0; i < period; i++, nextDay = DateUtils.addDays(startData, i)) {
            ProjectBurn projectBurn = getProjectBurnByDate(projectBurnList, nextDay);
            if (projectBurn != null) {
                tLeft = projectBurn.getBurnLeft();
            }
            if (0 == i % interval) {
                averageList.add(topLeft - rake * i);
                dateList.add(format.format(nextDay));
                leftList.add(tLeft);
            }
        }
        ObjectToJson mapper = new ObjectToJson();
        try {
            model.addAttribute("average", mapper.convert(averageList));
            model.addAttribute("days", mapper.convert(dateList));
            model.addAttribute("left", mapper.convert(leftList));
        } catch (ConvertException e) {
            logger.logMessage(LogLevel.ERROR, "Json转换出错", e);
        }
        model.addAttribute("interval", interval);
        model.addAttribute("choose", choose);
        model.addAttribute("projectId", projectId);

        return "project/task/projectBurn.page" + (ajax == null ? "" : "let");
    }

    @ResponseBody
    @RequestMapping("/update")
    public Map<String, String> update() {
        burnService.updateDate(null);
        return resultMap(true, "更新成功");
    }

    /**
     * 获取列表中最大剩余工作时长
     *
     * @param list
     * @return
     */
    protected Float getMaxLeftTime(List<ProjectBurn> list) {
        //均线起始值
        float topLeft = 0f;
        if (list != null && !list.isEmpty()) {
            for (ProjectBurn burn : list) {
                if (topLeft < burn.getBurnLeft()) {
                    topLeft = burn.getBurnLeft();
                }
            }
        }
        return topLeft;
    }

    /**
     * 获取列表中最大剩余工作时长
     *
     * @param projectBurnList
     * @param date
     * @return
     */
    protected ProjectBurn getProjectBurnByDate(List<ProjectBurn> projectBurnList, Date date) {
        for (ProjectBurn burn : projectBurnList) {
            if (burn.getBurnDate().getTime() == date.getTime()) {
                return burn;
            }
        }
        return null;
    }


}
