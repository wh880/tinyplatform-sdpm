package org.tinygroup.typeinfo;

import org.tinygroup.vfs.FileObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangll13383 on 2015/9/6.
 */
public class TypeInfoResolvorImpl implements TypeInfoResolvor {

    private static Map<String, TypeInfo> typeDict = new ConcurrentHashMap<String, TypeInfo>();

    public void addTypeInfo(TypeInfos typeInfos) {
        if(typeInfos!=null) {
            for (TypeInfo typeinfo : typeInfos.getTypeInfoList()) {
                if (typeDict.containsKey(typeinfo.getTypesName())){
                    throw new RuntimeException("typeinfos:["+typeinfo.getTypesName()+"]已存在" );
                }
                typeDict.put(typeinfo.getTypesName(), typeinfo);
            }
        }
    }

    public Map<String, TypeInfo> getDict() {
        return typeDict;
    }

    public void removeTypeInfo(TypeInfos typeInfos) {
        if(typeInfos!=null) {
            for (TypeInfo typeinfo : typeInfos.getTypeInfoList()) {
                typeDict.remove(typeinfo.getTypesName());
            }
        }
    }

}
