package org.tinygroup.sdpm.action.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
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
@RequestMapping("/a/service/faq")
public class FaqAction extends BaseController {
    @Autowired
    private FaqService faqService;
    @Autowired
    private FaqTypeService faqTypeService;
    @Autowired
    private ProductService productService;

    /*新增问题*/
    @RequestMapping("/form")
    public String form(Integer id, Model model) {
        if (id != null) {
            ServiceFaq faq = faqService.findFaq(id);
            model.addAttribute("faq", faq);
        }
        Product product = new Product();
        List<Product> slas = productService.findProductList(product);
        model.addAttribute("slas", slas);
        return "/service/faq/addquestion.page";
    }

    /* 保存*/
    @RequestMapping("/save")
    public String save(ServiceFaq faq, Model model) {
        if (faq.getFaqId() == null) {
            faq = faqService.addFaq(faq);
        } else {
            faq = faqService.updateFaq(faq);
        }
        model.addAttribute("faq", faq);
        return "redirect:" + adminPath + "/service/faq/list";
    }

    /*把faqmenu页面的所有问题都查询出来*/
    @RequestMapping("/list")
    public String list(ServiceFaq serviceFaq, Integer id, String faqQuestion,
                       @RequestParam(required = false, defaultValue = "1") int page,
                       @RequestParam(required = false, defaultValue = "10") int pageSize,
                       Model model) {
        Integer start = (page - 1) * pageSize;
        Integer limit = pageSize;
        Pager<ServiceFaq> list;
        if (faqQuestion != null) {
            list = faqService.searchFaq(start, limit, serviceFaq, faqQuestion);
        } else {
            list = faqService.getFaqpage(start, limit, serviceFaq);
        }
        model.addAttribute("pager", list);//list.CurrentPage
        return "/service/faq/faqmenu.page";
    }

    /*删除*/
    @ResponseBody
    @RequestMapping("/delete")
    public Map delete(Integer id) {
        faqService.deleteFaq(id);
        return resultMap(true, "删除成功");
    }

    /*点击问题进去，显示里面的问题和答案。由faqquestion.page跳转过来。*/
    @RequestMapping("/questionAnswer")
    public String questionAnswer(Integer id, Model model) {
        ServiceFaq faqs = faqService.findFaq(id);
        model.addAttribute("faqs", faqs);
        return "/service/faq/questionAnswer.page";
    }

    /*点击问题进去，显示里面的编辑和删除，2015-10-16,将bese.menu.xml中地址直接跳转到form*/
    @RequestMapping(value = "/faqContentEdit")
    public String slaContentEdit(Integer id, Model model) {
        if (id != null) {
            ServiceFaq faq = faqService.findFaq(id);
            model.addAttribute("faq", faq);
        }

        return "/service/faq/addquestion.page";
    }

    /*实现faq里面的左侧树*/
    /*对树的节点进行新增*/
    @ResponseBody
    @RequestMapping("/addTree")
    public Map addDept(Integer faqParentTypeId, String faqType) {
        ServiceFaqType serviceFaqType = new ServiceFaqType();
        serviceFaqType.setFaqParentTypeId(faqParentTypeId);
        serviceFaqType.setFaqType(faqType);
        faqTypeService.addFaqType(serviceFaqType);
        return resultMap(true, "新增成功");
    }

    /**
     * 对树的节点进行编辑
     *
     * @param faqParentTypeId
     * @param faqTypeId
     * @param faqType
     * @return
     */
    @ResponseBody
    @RequestMapping("/editTree")
    public Map editDept(Integer faqParentTypeId, Integer faqTypeId, String faqType) {
        ServiceFaqType type = faqTypeService.findFaqType(faqTypeId);
        type.setFaqType(faqType);
        type.setFaqParentTypeId(faqParentTypeId);
        faqTypeService.updateFaqType(type);
        return resultMap(true, "编辑成功");
    }

    /*树里面的节点的删除*/
    @ResponseBody
    @RequestMapping("/deleteTree")
    public Map deleteDept(Integer faqTypeId) {
        faqTypeService.deleteDept(faqTypeId);
        return resultMap(true, "删除成功");
    }


    @RequestMapping("/listTree")
    public String listTree(ServiceFaq serviceFaq, Integer id, Integer start, Integer limit, Integer faqTypeId,
                           @RequestParam(required = false, defaultValue = "1") int page,
                           @RequestParam(required = false, defaultValue = "10") int pageSize,
                           Model model) {
        start = (page - 1) * pageSize;
        limit = pageSize;
        if (faqTypeId == null || faqTypeId == -1) {
            serviceFaq.setFaqTypeId(null);
            Pager<ServiceFaq> list = faqService.getFaqpage(start, limit, serviceFaq);
            model.addAttribute("pager", list);
        } else {
            Pager<ServiceFaq> list = faqService.findUserByDeptId(start, limit, faqTypeId);
            model.addAttribute("pager", list);
        }
        return "/service/faq/faqmenu.page";
    }

    /*faq左侧树的数据来源*/
    @ResponseBody
    @RequestMapping("/data")
    public List data(String check) {
        ServiceFaqType faq = new ServiceFaqType();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<ServiceFaqType> faqs = faqTypeService.getFaqTypeList(faq);

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
