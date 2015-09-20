package org.tinygroup.sdpm.common.typeinfo;

import java.util.Map;

/**
 * Created by wangll13383 on 2015/9/6.
 */
public interface TypeInfoResolvor {
    public static String TYPEINFO_XSTREAM_= "typeinfo";

    public void addTypeInfo(TypeInfos typeInfos,String filePath);

    public Map<String,TypeInfo> getDict();

    public void removeTypeInfo(TypeInfos typeInfos);
}
