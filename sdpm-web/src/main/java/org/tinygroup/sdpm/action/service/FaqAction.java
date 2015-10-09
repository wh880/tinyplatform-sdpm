package org.tinygroup.sdpm.action.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.service.dao.pojo.ServiceFaq;
import org.tinygroup.sdpm.service.service.inter.FaqService;

import java.util.List;
/*import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.commons.tools.StringUtil;*/

@Controller
@RequestMapping("/service/faq")
public class FaqAction extends BaseController {
    @Autowired
    private FaqService faqService;

    /*新增问题*/
    @RequestMapping("/form")
    public String form(Integer id, Model model) {
        if (id != null)
        {
            ServiceFaq faq = faqService.findFaq(id);
            model.addAttribute("faq",faq);
        }
        return "/service/faq/addquestion.page";
    }

    /* 保存*/
    @RequestMapping("/save")
    public String save(ServiceFaq faq ,Model model)
    {
        if (faq.getFaqId()==null)
        {
            faq = faqService.addFaq(faq);
        }
        else
        {
            faq = faqService.updateFaq(faq);
        }
        model.addAttribute("faq",faq);
        return "/service/faq/faqmenu.page";
    }

    /*对问题进行“编辑”*/
    @RequestMapping("/edit")
    public String edit(Integer id, Model model) {
        if (id != null) {
            ServiceFaq faq = faqService.findFaq(id);
            model.addAttribute("faq", faq);
        }
        return "/service/faq/addquestion.page";
    }

    /*把faqmenu页面的所有问题都查询出来*/
    @RequestMapping("/list")
    public String list(ServiceFaq faq,Model model)
    {
        List<ServiceFaq> list=faqService.getFaqList(faq);
        model.addAttribute("list",list);
        return "/service/faq/faqmenu.page";
    }
    /*删除*/
    @RequestMapping("/delete")
    public String delete(Integer id)
    {
        faqService.deleteFaq(id);
        return "/service/faq/faqmenu.page";
    }



}
