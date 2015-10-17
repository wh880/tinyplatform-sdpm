package org.tinygroup.sdpm.common.typeinfo;

import java.util.Map;

/**
 * Created by wangll13383 on 2015/9/6.
 */
public interface TypeInfoResolvor {
    String TYPEINFO_XSTREAM_ = "typeinfo";

    void addTypeInfo(TypeInfos typeInfos, String filePath);

    Map<String, TypeInfo> getDict();

    void removeTypeInfo(TypeInfos typeInfos);
}
