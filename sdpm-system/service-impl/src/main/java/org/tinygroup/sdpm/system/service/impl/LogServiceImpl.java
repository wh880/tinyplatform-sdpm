package org.tinygroup.sdpm.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.sdpm.common.log.LogPrepareUtil;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.system.biz.inter.ActionManager;
import org.tinygroup.sdpm.system.biz.inter.HistoryManager;
import org.tinygroup.sdpm.system.dao.SystemActionDao;
import org.tinygroup.sdpm.system.dao.SystemHistoryDao;
import org.tinygroup.sdpm.system.dao.impl.SystemActionDaoImpl;
import org.tinygroup.sdpm.system.dao.impl.SystemHistoryDaoImpl;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemHistory;
import org.tinygroup.sdpm.system.service.inter.LogService;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * Created by wangll13383 on 2015/10/8.
 */
public class LogServiceImpl implements LogService{
    @Autowired
    private ActionManager actionManager;
    @Autowired
    private HistoryManager historyManager;

    public void log(Object oldObject, Object newObject, SystemAction systemAction){
        systemAction.setActionDate(new Date());
        actionManager.add(systemAction);
        if(oldObject != null) {
            recordEdit(oldObject, newObject, systemAction);
        }

    }
    public void log(SystemAction systemAction){
        log(null,null,systemAction);
    }

    private void recordEdit( Object oldObject, Object newObject, SystemAction systemAction){
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

    private boolean compare(Object oldOne, Object newOne){
        return oldOne.equals(newOne);
    }

    private Object reflectValue(Object object, String methodName){
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
