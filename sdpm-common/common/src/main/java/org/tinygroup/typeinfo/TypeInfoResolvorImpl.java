package org.tinygroup.typeinfo;

import org.tinygroup.vfs.FileObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by wangll13383 on 2015/9/6.
 */
public class TypeInfoResolvorImpl implements TypeInfoResolvor {

    private static Map<String, TypeInfo> typeDict = new ConcurrentHashMap<String, TypeInfo>();

    private  Map<String,String> pathRecord = new ConcurrentHashMap<String, String>();

    public void addTypeInfo(TypeInfos typeInfos,String filePath) {
        if(typeInfos!=null) {
            for (TypeInfo typeinfo : typeInfos.getTypeInfoList()) {
                String key = typeinfo.getTypesName();
                if(isRepeat(key,filePath)){
                    throw new RuntimeException("typeinfo:["+key+"]已存在.");
                }
                typeDict.put(typeinfo.getTypesName(), typeinfo);
                pathRecord.put(typeinfo.getTypesName(),filePath);
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
                pathRecord.remove(typeinfo.getTypesName());
            }
        }
    }

    public boolean isRepeat(String key,String filePath){
        if (typeDict.containsKey(key)){
            String path = pathRecord.get(key);
            if(path.contains(".jar")){
                if(filePath.contains(".jar")){
                    return true;
                }else{
                    return false;
                }
            }else{
                if(filePath.contains(".jar")){
                    return false;
                }else{
                    return true;
                }
            }
        }
        return false;
    }
}
