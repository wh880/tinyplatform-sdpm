package org.tinygroup.sdpm.action.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.tinygroup.beancontainer.BeanContainer;
import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.sdpm.common.docTemplate.impl.DocTemplateResolverImpl;
import org.tinygroup.sdpm.common.docTemplate.inter.DocTemplateResolver;
import org.tinygroup.sdpm.system.service.impl.util.ExportUtil;
import org.tinygroup.template.TemplateContext;
import org.tinygroup.template.TemplateEngine;
import org.tinygroup.template.TemplateException;
import org.tinygroup.template.application.DefaultStaticClassOperator;
import org.tinygroup.vfs.FileObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangll13383 on 2015/12/1.
 */
public class ExportUtils {
    private final static String REGXP_FOR_HTML = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签

    private static DocTemplateResolver docTemplateResolver = BeanContainerFactory.getBeanContainer(ExportUtils.class.getClassLoader()).getBean(DocTemplateResolverImpl.class);
    @Autowired
    private static TemplateEngine docTemplateEngine = BeanContainerFactory.getBeanContainer(ExportUtils.class.getClassLoader()).getBean("docTemplateEngine");

    public static void mergeTemplate(String type, TemplateContext context, HttpServletResponse response, String name) throws IOException, TemplateException {
        Calendar today = Calendar.getInstance();
        FileObject fileObject = docTemplateResolver.getDocTemplate(type);
        response.reset();
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment; filename="
                + (toUTF8(name) + today.get(Calendar.YEAR) + today.get(Calendar.MONTH) + today.get(Calendar.DAY_OF_MONTH) + today.get(Calendar.HOUR) + today.get(Calendar.MINUTE) + ".doc"));
        OutputStream out = response.getOutputStream();
        docTemplateEngine.registerStaticClassOperator(new DefaultStaticClassOperator("ExportUtils", ExportUtils.class));
        docTemplateEngine.renderTemplate(fileObject.getFileName(), context, out);
        out.close();
    }

    public static String filterHtml(String str) {
        Pattern pattern = Pattern.compile(REGXP_FOR_HTML);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result1 = matcher.find();
        while (result1) {
            matcher.appendReplacement(sb, "");
            result1 = matcher.find();
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static String toUTF8(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }
}
