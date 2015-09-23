package org.tinygroup.sdpm.action.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.service.dao.pojo.ServiceFaq;
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
    public String form(ServiceFaq faq, Model model) {
        /*if (faq.getFaqId() == null) {}*/
      /*  Faq faq=faqService.findFaq(id);
            model.addAttribute("faq",faq);*/
        if (faq != null)
        {
            model.addAttribute("faq",faq);
        }
        return "/service/faq/addquestion.page";
    }

    @RequestMapping("/save")
    public String save(ServiceFaq faq ,Model model)
    {
        if (faq.getFaqId()==null)
        {
            faqService.addFaq(faq);
        }
        else
        {
            faqService.updateFaq(faq);
        }
        model.addAttribute("faq",faq);
        return "/service/faq/faqmenu.page";
    }


}
