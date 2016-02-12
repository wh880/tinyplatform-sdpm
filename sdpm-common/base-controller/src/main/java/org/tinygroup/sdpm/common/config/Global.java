package org.tinygroup.sdpm.common.config;

import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.tinygroup.sdpm.util.PropertiesLoader;

import java.util.Map;

/**
 * 全局配置类
 */
public class Global {

    /**
     * 显示/隐藏
     */
    public static final String SHOW = "1";
    public static final String HIDE = "0";
    /**
     * 是/否
     */
    public static final String YES = "1";
    public static final String NO = "0";
    /**
     * 对/错
     */
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    /**
     * 上传文件基础虚拟路径
     */
    public static final String USERFILES_BASE_URL = "/userfiles/";
    /**
     * 当前对象实例
     */
    private static Global global = new Global();
    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = Maps.newHashMap();
    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader loader = new PropertiesLoader("web.properties");

    /**
     * 获取当前对象实例
     */
    public static Global getInstance() {
        return global;
    }

    /**
     * 获取配置
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null) {
            value = loader.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }

    /**
     * 获取管理端根路径
     */
    public static String getAdminPath() {
        return getConfig("adminPath");
    }

    /**
     * 获取URL后缀
     */
    public static String getUrlSuffix() {
        return getConfig("urlSuffix");
    }

    /**
     * 是否是演示模式，演示模式下不能修改用户、角色、密码、菜单、授权
     */
    public static Boolean isDemoMode() {
        String dm = getConfig("demoMode");
        return "true".equals(dm) || "1".equals(dm);
    }


}