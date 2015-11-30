package org.tinygroup.sdpm.common.typeinfo;

import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.vfs.FileObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TypeInfoUtil {
    private final static String TYPE_INFOS_FILE_EXT = "typeinfo";

    private static List<FileObject> typeInfosFile = new ArrayList<FileObject>();

    private static Map<String, TypeInfo> typeDict;

    private static Map<String, String> result = new LinkedHashMap<String, String>();

    static {
        init();
    }

    public static Map getUrlMap(String type, int id) {
        result.clear();
        if (typeDict.get(type) != null) {
            for (Info info : typeDict.get(type).getInfos()) {
                if (typeDict.get(info.getUrlResource()) != null)
                    result.put(info.getInfoTile(), formatUrl(info.getInfoUrl(), info.getInfoParameter(), id));
            }
        }
        return result;
    }

    public static String formatUrl(String url, String parameter, int id) {
        if (parameter != null && !"".equals(parameter)) {
            url = url + "?" + parameter + "=" + id;
        }
        return url.startsWith("/") ? url : "/" + url;
    }

    private static void init() {
        TypeInfoResolver resolver = BeanContainerFactory.getBeanContainer(TypeInfoUtil.class.getClassLoader()).getBean("typeInfoResolver");
        typeDict = resolver.getDict();
    }
}
