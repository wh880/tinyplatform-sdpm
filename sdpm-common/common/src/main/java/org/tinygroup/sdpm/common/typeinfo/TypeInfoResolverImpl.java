package org.tinygroup.sdpm.common.typeinfo;

import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangll13383 on 2015/9/6.
 */
public class TypeInfoResolverImpl implements TypeInfoResolver {

    protected static final Logger LOGGER = LoggerFactory.getLogger(TypeInfoResolverImpl.class);

    private static Map<String, TypeInfo> typeDict = new ConcurrentHashMap<String, TypeInfo>();

    private  Map<String,String> pathRecord = new ConcurrentHashMap<String, String>();

    public void addTypeInfo(TypeInfos typeInfos,String filePath) {
        if(typeInfos!=null) {
            for (TypeInfo typeinfo : typeInfos.getTypeInfoList()) {
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

}
