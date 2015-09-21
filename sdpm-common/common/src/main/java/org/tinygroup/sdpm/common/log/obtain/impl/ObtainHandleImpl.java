package org.tinygroup.sdpm.common.log.obtain.impl;

import org.springframework.stereotype.Repository;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;
import org.tinygroup.sdpm.common.log.obtain.inter.Obtain;
import org.tinygroup.sdpm.common.log.obtain.inter.Obtains;
import org.tinygroup.sdpm.common.log.obtain.inter.ObtainHandle;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangll13383 on 2015/9/16.
 */
@Repository
public class ObtainHandleImpl implements ObtainHandle {
    protected static final Logger LOGGER = LoggerFactory.getLogger(ObtainHandleImpl.class);

    private static Map<String, Obtain> obtainDict = new ConcurrentHashMap<String, Obtain>();

    private  Map<String,String> pathRecord = new ConcurrentHashMap<String, String>();

    public void addObtain(Obtains obtains, String filePath) {
        if(obtains!=null) {
            for (Obtain obtain : obtains.getObtainList()) {
                String key = obtain.getName();
                if(isRepeat(key,filePath)){
                    throw new RuntimeException("typeinfo:["+key+"]已存在.");
                }
                obtainDict.put(obtain.getName(), obtain);
                pathRecord.put(obtain.getName(),filePath);
            }
        }
    }

    public Map<String, Obtain> getDict() {
        return obtainDict;
    }

    public void removeObtains(Obtains obtains) {
        for (Obtain obtain : obtains.getObtainList()) {
            obtainDict.remove(obtain.getName());
        }
    }

    public boolean isRepeat(String key,String filePath){
        if (obtainDict.containsKey(key)){
            LOGGER.logMessage(LogLevel.INFO, "正在进行obtain:[{0}]重复判定", key);
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
