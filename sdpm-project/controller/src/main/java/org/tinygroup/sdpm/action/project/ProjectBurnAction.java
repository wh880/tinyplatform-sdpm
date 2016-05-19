package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.project.service.dto.BurnDTO;
import org.tinygroup.sdpm.project.service.inter.BurnService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
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
    @Autowired
    private UserService userService;

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
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/init")
    public String initBurn(Integer choose, Integer interval, Model model,
                           HttpServletRequest request, HttpServletResponse response) {
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return redirectProjectForm();
        }
        BurnDTO burnDTO = burnService.initBurn(projectId, interval);
        model.addAttribute("burn", burnDTO);
        model.addAttribute("interval", interval);
        model.addAttribute("choose", choose);
        model.addAttribute("projectId", projectId);

        List<OrgUser> teamUserList = userService.findTeamUserListByProjectId(projectId);
        model.addAttribute("teamUserList", teamUserList);

        return "project/operate/task/special/projectBurn";
    }

    @ResponseBody
    @RequestMapping("/update")
    public Map<String, String> update(HttpServletRequest request, HttpServletResponse response) {
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return resultMap(false, "更新失败，请选择更新项目");
        }
        burnService.updateBurnByProjectId(projectId);
        return resultMap(true, "更新成功");
    }


}
