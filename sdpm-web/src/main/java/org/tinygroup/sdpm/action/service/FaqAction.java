package org.tinygroup.sdpm.action.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.service.dao.pojo.ServiceFaq;
import org.tinygroup.sdpm.service.dao.pojo.ServiceFaqType;
import org.tinygroup.sdpm.service.service.inter.FaqService;
import org.tinygroup.sdpm.service.service.inter.FaqTypeService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/service/faq")
public class FaqAction extends BaseController {
    @Autowired
    private FaqService faqService;
    @Autowired
    private FaqTypeService faqTypeService;

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
        return "redirect:/service/faq/list";
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
    public String list(ServiceFaq serviceFaq, Integer id, Integer start, Integer limit,
                       @RequestParam(required = false, defaultValue = "1") int page,
                       @RequestParam(required = false, defaultValue = "10") int pageSize,
                       @RequestParam(required = false, defaultValue = "faqId") String order,
                       @RequestParam(required = false, defaultValue = "asc") String ordertype,
                       Model model)
    {
        /*查询问题总条数*/
        /*Integer totalNum = faqService.selectcount(id);*/
        /*List<ServiceFaq> list=faqService.getFaqList(faq);*/
        /*分页*/
        start = (page - 1) * pageSize;
        limit = pageSize;
        Pager<ServiceFaq> list = faqService.getFaqpage(start, limit, serviceFaq);
        model.addAttribute("pager", list);//list.CurrentPage
        //model.addAttribute("totalNum", totalNum);
        return "/service/faq/faqmenu.page";
    }
    /*删除*/
    @ResponseBody
    @RequestMapping("/delete")
    public Map delete(Integer id)
    {
        faqService.deleteFaq(id);
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "删除成功");
        return map;
    }

    /*点击问题进去，显示里面的问题和答案。由faqquestion.page跳转过来。*/
    @RequestMapping("/questionAnswer")
    public String questionAnswer(Integer id, Model model) {
        ServiceFaq faqs = faqService.findFaq(id);
        model.addAttribute("faqs", faqs);
        return "/service/faq/questionAnswer.page";
    }

    /*点击问题进去，显示里面的编辑和删除*/
    @RequestMapping(value = "/faqContentEdit")
    public String slaContentEdit(Integer id, Model model) {
        if (id != null) {
            ServiceFaq faq = faqService.findFaq(id);
            model.addAttribute("faq", faq);
        }

        return "/service/faq/addquestion.page";
    }

    @ResponseBody
    @RequestMapping(value = "/faqContentDelete")
    public Map faqTitleDelete(Integer id) {
        faqService.deleteFaq(id);
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "y");
        map.put("info", "删除成功");
        return map;
    }

    /*实现faq里面的左侧树*/
    @RequestMapping("/addTree")
    public String addDept(Integer faqParentTypeId, String faqType) {
        ServiceFaqType serviceFaqType = new ServiceFaqType();
        serviceFaqType.setFaqParentTypeId(faqParentTypeId);
        serviceFaqType.setFaqType(faqType);
        faqTypeService.addFaqType(serviceFaqType);
        return "organization/user/user.page";
    }

    @RequestMapping("/editTree")
    public String editDept(Integer faqTypeId, String faqType) {
        ServiceFaqType type = faqTypeService.findFaqType(faqTypeId);
        type.setFaqType(faqType);
        faqTypeService.updateFaqType(type);
        return "service/faq/faqmenu.page";
    }

    @RequestMapping("/deleteTree")
    public String deleteDept(Integer faqTypeId) {
        faqTypeService.deleteFaqType(faqTypeId);
        return "service/faq/faqmenu.page";
    }

    @ResponseBody
    @RequestMapping("/data")
    public List data(String check) {
        ServiceFaqType faq = new ServiceFaqType();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<ServiceFaqType> faqs = faqTypeService.getFaqTypeList(faq);
        if (check == null || !check.equals("n")) {
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("id", -1);
            map1.put("pId", 0);
            map1.put("open", true);
            map1.put("add", true);
            map1.put("edit", true);
            map1.put("name", "所有问题");
            list.add(map1);
        }

        for (ServiceFaqType d : faqs) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", d.getFaqTypeId());
            map.put("pId", d.getFaqParentTypeId());
            map.put("open", true);
            map.put("add", true);
            map.put("edit", true);
            map.put("name", d.getFaqType());
            list.add(map);
        }
        return list;
    }
}
