package org.tinygroup.sdpm.common.log.obtain.impl;

import org.springframework.stereotype.Repository;
import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;
import org.tinygroup.sdpm.common.log.obtain.inter.Obtain;
import org.tinygroup.sdpm.common.log.obtain.inter.Obtains;
import org.tinygroup.sdpm.common.log.obtain.inter.ObtainHandle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangll13383 on 2015/9/16.
 */
@Repository
public class ObtainHandleImpl implements ObtainHandle {
    private static final Logger LOGGER = LoggerFactory.getLogger(ObtainHandleImpl.class);

    private static Map<String, Obtain> obtainDict = new ConcurrentHashMap<String, Obtain>();

    private  Map<String,String> pathRecord = new ConcurrentHashMap<String, String>();

    public void addObtain(Obtains obtains, String filePath) {
        if(obtains!=null) {
            for (Obtain obtain : obtains.getObtainList()) {
                String key = obtain.getName();
                if(isRepeat(key,filePath)){
                    throw new RuntimeException("obtain:["+key+"]已存在.");
                }
                obtainDict.put(obtain.getName(), obtain);
                pathRecord.put(obtain.getName(),filePath);
            }
        }
    }

    public Object getObjectById(int id,String type){
        Obtain obtain = getDict(type);
        if(obtain==null)return null;
        return getValue(id,obtain);
    }

    public String getInfoUrl(String type){
        return getDict(type).getUrl();
    }

    private Object getValue(int id, Obtain obtain){
        Class[] parameter = new Class[obtain.getParameters().size()];
        Object value = null;
        for(int i = 0;i<obtain.getParameters().size(); i++){
            try {
                parameter[i]=Class.forName(obtain.getParameters().get(i).getType());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            Object serviceWapper = BeanContainerFactory
                    .getBeanContainer(ObtainHandleImpl.class.getClassLoader())
                    .getBean(Class.forName(obtain.getType()));
            Method method = serviceWapper.getClass().getMethod(obtain.getMethod(),parameter);
            value = method.invoke(serviceWapper,id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("obtain moduleType不存在");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return value;
    }

    public Obtain getDict(String key) {
        return obtainDict.get(key);
    }

    public void removeObtains(Obtains obtains) {
        for (Obtain obtain : obtains.getObtainList()) {
            obtainDict.remove(obtain.getName());
        }
    }

    public boolean isRepeat(String key,String filePath){
        if (obtainDict.containsKey(key)){
            LOGGER.logMessage(LogLevel.INFO, "正在进行obtain:[{0}]重复判定..", key);
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
