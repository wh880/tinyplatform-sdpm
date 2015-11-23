package org.tinygroup.sdpm.system.service.impl.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangll13383 on 2015/11/23.
 */
public class ExportUtil {
    private final static String REGXP_FOR_HTML = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签
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
}
