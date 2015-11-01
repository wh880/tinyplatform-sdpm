package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.project.service.dto.BurnDTO;
import org.tinygroup.sdpm.project.service.inter.BurnService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;

import java.util.Map;

/**
 * 燃尽图
 * Created by wangying14938 on 2015-09-22.
 */
@Controller
@RequestMapping("/a/project/burn")
public class ProjectBurnAction extends BaseController {
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
     * @param model
     * @return
     */
    @RequestMapping("/init")
    public String initBurn(@CookieValue(value = TaskAction.COOKIE_PROJECT_ID, required = false) Integer projectId,
                           Integer choose, Integer interval, Model model) {
        if (projectId == null) {
            addMessage(model, "请选择项目来生成燃尽图！");
            return "project/task/projectBurn.page";
        }
        BurnDTO burnDTO = burnService.initBurn(projectId, interval);
        model.addAttribute("burn", burnDTO);
        model.addAttribute("interval", interval);
        model.addAttribute("choose", choose);
        model.addAttribute("projectId", projectId);

        return "project/task/projectBurn.page";
    }

    @ResponseBody
    @RequestMapping("/update")
    public Map<String, String> update() {
        burnService.updateDate(null);
        return resultMap(true, "更新成功");
    }


}
