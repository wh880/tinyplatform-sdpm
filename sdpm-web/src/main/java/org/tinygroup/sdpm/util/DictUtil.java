package org.tinygroup.sdpm.util;

import org.springframework.beans.factory.InitializingBean;
import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.context.Context;
import org.tinygroup.context.impl.ContextImpl;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangll13383 on 2015/9/28.
 */
public class DictUtil implements InitializingBean {
    private static Map<String,Map<String,String>> dictMap = new ConcurrentHashMap<String, Map<String, String>>();


    private void init(){
        ServiceUtil serviceUtil = BeanContainerFactory.getBeanContainer(DictUtil.class.getClassLoader()).getBean("serviceUtil");
        List<SystemModule> moduleList = (List<SystemModule>) serviceUtil.callService("findModule", new CallBackFunction() {
            public Context getContext() {
                Context context = new ContextImpl();

                return context;
            }
        });
    }
    public static Map<String,String> getDict(String dictType){
        return dictMap.get(dictType);
    }

    public void afterPropertiesSet() throws Exception {
        init();
    }
}
