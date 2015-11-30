package org.tinygroup.sdpm.action.quality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.util.CookieUtils;
import org.tinygroup.sdpm.util.ProductUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangll13383 on 2015/10/8.
 */
@Controller
@RequestMapping("/a/quality")
public class QualityAction extends BaseController {
    @Autowired
    private ProductService productService;

    @RequestMapping("")
    public String qualityAction(@CookieValue(value = "qualityProductId", defaultValue = "0") String qualityProductId, HttpServletRequest request, HttpServletResponse response) {
        if ("0".equals(qualityProductId) && ProductUtils.getAllProductListByUser().size() > 0) {
            CookieUtils.setCookie(response, "qualityProductId", String.valueOf(ProductUtils.getAllProductListByUser().get(0).getProductId()));
        }
        return "redirect:/a/quality/bug?status=tbugstatus" + (request.getQueryString() == null ? "" : ("&" + request.getQueryString()));

    }

    @ResponseBody
    @RequestMapping("/changeProduct")
    public boolean changeProduct(Integer productId, HttpServletRequest request) {
        if (productId != null) {
            request.getSession().setAttribute("qualityProductId", productId);
            return true;
        } else {
            request.getSession().removeAttribute("qualityProductId");
            return false;
        }
    }
}
