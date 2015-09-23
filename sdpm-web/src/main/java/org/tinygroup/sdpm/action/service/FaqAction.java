package org.tinygroup.sdpm.action.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.service.dao.pojo.Faq;
import org.tinygroup.sdpm.service.service.inter.FaqService;
/*import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.commons.tools.StringUtil;*/

@Controller
@RequestMapping("/service/faq")
public class FaqAction extends BaseController {
    @Autowired
    private FaqService faqService;

    @RequestMapping("/form")
    public String form(Faq faq, Model model) {
        /*if (faq.getFaqId() == null) {}*/
      /*  Faq faq=faqService.findFaq(id);
            model.addAttribute("faq",faq);*/
            faqService.addFaq(faq);

        return "/service/faq/addquestion.page";
    }


}
