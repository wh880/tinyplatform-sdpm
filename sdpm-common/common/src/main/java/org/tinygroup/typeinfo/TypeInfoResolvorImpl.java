package org.tinygroup.typeinfo;

import org.tinygroup.vfs.FileObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangll13383 on 2015/9/6.
 */
public class TypeInfoResolvorImpl implements TypeInfoResolvor {
    private List<FileObject> fileLists = new ArrayList<FileObject>();

    private static Map<String, TypeInfo> typeDict = new HashMap<String, TypeInfo>();

    public void addTypeInfo(TypeInfos typeInfos) {
        if(typeInfos!=null) {
            for (TypeInfo typeinfo : typeInfos.getTypeInfoList()) {
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
