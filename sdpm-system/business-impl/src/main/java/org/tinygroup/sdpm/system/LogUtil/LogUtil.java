package org.tinygroup.sdpm.system.LogUtil;

import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.sdpm.common.log.obtain.inter.Obtain;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.system.biz.impl.ActionManagerImpl;
import org.tinygroup.sdpm.system.biz.impl.HistoryManagerImpl;
import org.tinygroup.sdpm.system.biz.inter.ActionManager;
import org.tinygroup.sdpm.system.biz.inter.HistoryManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemHistory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * Created by wangll13383 on 2015/9/30.
 */
public class LogUtil {
    private static ActionManager actionManager = BeanContainerFactory.getBeanContainer(LogUtil.class.getClassLoader()).getBean(ActionManagerImpl.class);

    private static HistoryManager historyManager = BeanContainerFactory.getBeanContainer(LogUtil.class.getClassLoader()).getBean(HistoryManagerImpl.class);

    public static void log(Object oldObject, Object newObject, SystemAction systemAction){
        systemAction.setActionDate(new Date());
        actionManager.add(systemAction);
        recordEdit(oldObject,newObject,systemAction);
    }
    public static void log(SystemAction systemAction){

    }

    private static void recordEdit( Object oldObject, Object newObject, SystemAction systemAction){
        Field[] fields =  oldObject.getClass().getDeclaredFields();
        for(Field field : fields){
            Object oldValue = reflectValue(oldObject, NameUtil.toMethod(field.getName()));
            Object newValue = reflectValue(newObject,NameUtil.toMethod(field.getName()));
            if(compare(oldValue,newValue)){
                continue;
            }else {
                SystemHistory systemHistory = new SystemHistory();
                systemHistory.setHistoryAction(systemAction.getActionId());
                systemHistory.setHistoryField(field.getName());
                systemHistory.setHistoryOld(String.valueOf(oldObject));
                systemHistory.setHistoryNew(String.valueOf(newObject));
                historyManager.add(systemHistory);
            }

        }
    }

    private static boolean compare(Object oldOne, Object newOne){
        return oldOne.equals(newOne);
    }

    private static Object reflectValue(Object object, String methodName){
        Object value = null;
        try {
           value = object.getClass().getDeclaredMethod(methodName).invoke(object);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return value;
    }

}
