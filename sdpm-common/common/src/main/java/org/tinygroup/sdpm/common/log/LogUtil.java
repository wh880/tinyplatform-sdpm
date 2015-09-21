package org.tinygroup.sdpm.common.log;

import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.sdpm.common.log.obtain.impl.ObtainHandleImpl;
import org.tinygroup.sdpm.common.log.obtain.inter.Obtain;
import org.tinygroup.sdpm.common.log.obtain.inter.ObtainHandle;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by wangll13383 on 2015/9/21.
 */
public class LogUtil {
    private static ThreadLocal<HttpSession> session = new ThreadLocal<HttpSession>();
    private static Map<String,Obtain> obtainDict;

    public static void setSession(HttpSession httpSession){
        session.set(httpSession);
    }

    public static HttpSession getSession(){
        return session.get();
    }

    static{
        init();
    }

    public static Obtain getObtain(String key){
        return obtainDict.get(key);
    }

    private static void init(){
        ObtainHandle obtainHandle = (ObtainHandleImpl) BeanContainerFactory.getBeanContainer(LogUtil.class.getClassLoader()).getBean(ObtainHandleImpl.class);
        obtainDict = obtainHandle.getDict();
    }

}
