package org.tinygroup.sdpm.action.system;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.system.dao.pojo.SystemConfig;
import org.tinygroup.sdpm.system.service.inter.ConfigService;

import java.util.List;

/**
 * Created by wangll13383 on 2015/12/18.
 */
@Controller
@RequestMapping("a/system/config")
public class ConfigAction extends BaseController{
    @Autowired
    ConfigService configService;

    @RequestMapping("")
    public String index(Model model){
        List<SystemConfig> configs = configService.findConfigList();
        model.addAttribute("list",configs);
        return "system/page/config/index.page";
    }
    @RequestMapping("toUpdate")
    public String update(Integer configId, Model model){
        model.addAttribute("config",configService.findConfig(configId));
        return "system/page/config/configEdit.pagelet";
    }
    @RequiresPermissions("config-edit")
    @RequestMapping("update")
    public String update(SystemConfig config){
        configService.updateConfig(config);
        return "redirect:"+adminPath+"/system/config";
    }
}
