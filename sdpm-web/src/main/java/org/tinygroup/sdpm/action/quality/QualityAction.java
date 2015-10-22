package org.tinygroup.sdpm.action.quality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wangll13383 on 2015/10/8.
 */
@Controller
@RequestMapping("/a/quality")
public class QualityAction extends BaseController {
    @Autowired
    private ProductService productService;
    @RequestMapping("")
    public String qualityAction(HttpServletRequest request){

        return "redirect:/a/quality/bug?status=tbugstatus"+(request.getQueryString()==null?"":("&"+request.getQueryString()));

    }
    @ResponseBody
    @RequestMapping("/changeProduct")
    public boolean changeProduct(Integer productId,HttpServletRequest request){
        if(productId!=null){
            request.getSession().setAttribute("qualityProductId", productId);
            return true;
        }else{
            request.getSession().removeAttribute("qualityProductId");
            return false;
        }
    }
}
