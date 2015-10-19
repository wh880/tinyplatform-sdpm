package org.tinygroup.sdpm.action.statistic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;

/**
 * Created by MCK on 2015/10/19.
 */
@Controller
@RequestMapping("a/statistic")
public class StatisticAction extends BaseController {
    @RequestMapping("index")
    public String index (Model model){
         return "/statistic/page/product.page";
    }
}
