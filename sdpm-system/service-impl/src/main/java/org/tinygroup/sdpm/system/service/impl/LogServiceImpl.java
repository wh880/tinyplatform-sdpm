package org.tinygroup.sdpm.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.common.util.std.StdUtil;
import org.tinygroup.sdpm.org.biz.inter.UserManager;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.system.biz.inter.ActionManager;
import org.tinygroup.sdpm.system.biz.inter.HistoryManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemHistory;
import org.tinygroup.sdpm.system.service.inter.LogService;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangll13383 on 2015/10/8.
 */
@Component
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    private ActionManager actionManager;
    @Autowired
    private HistoryManager historyManager;
    @Autowired
    private UserManager userManager;

    public void log(Object oldObject, Object newObject, SystemAction systemAction) {
        systemAction.setActionDate(new Date());
        systemAction = actionManager.add(systemAction);
        if (oldObject != null) {
            recordEdit(oldObject, newObject, systemAction);
        }
    }

    private void recordEdit(Object oldObject, Object newObject, SystemAction systemAction) {
        Field[] fields = oldObject.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (!StdUtil.containsField(field.getName())) continue;
            Object oldValue;
            Object newValue;
            try {
                oldValue = reflectValue(oldObject, NameUtil.toMethod(field.getName()));
                newValue = reflectValue(newObject, NameUtil.toMethod(field.getName()));
            } catch (NoSuchMethodException n) {
                continue;
            }
            if (beforeAndCompare(oldValue, newValue)) {
                continue;
            } else {

                SystemHistory systemHistory = new SystemHistory();
                systemHistory.setHistoryAction(systemAction.getActionId());
                systemHistory.setHistoryField(field.getName());
                systemHistory.setHistoryOld(String.valueOf((oldValue == null ? "空" : dataChange(oldValue))));
                systemHistory.setHistoryNew(String.valueOf((newValue == null ? "空" : dataChange(newValue))));
                historyManager.add(systemHistory);
            }

        }
    }

    private boolean beforeAndCompare(Object oldOne, Object newOne) {
        Object oldValue = dataChange(oldOne);
        Object newValue = dataChange(newOne);
        return compare(oldValue, newValue);
    }

    private boolean compare(Object oldOne, Object newOne) {
        if (oldOne == null && newOne == null) {
            return true;
        } else if (newOne == null) {
            return true;
        } else if (oldOne == null) {
            return false;
        }
        return oldOne.equals(newOne);
    }

    private Object reflectValue(Object object, String methodName) throws NoSuchMethodException {
        Object value = null;
        try {
            value = object.getClass().getDeclaredMethod(methodName).invoke(object);
        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        }
        return value;
    }
    @Transactional(readOnly = true)
    private Object dataChange(Object object) {
        if (object instanceof Date) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = (Date) object;
            return format.format(date);
        } else if (validateUserId(object)) {
            OrgUser orgUser = userManager.find((String) object);
            if (orgUser != null) {
                return orgUser.getOrgUserRealName();
            } else {
                return "";
            }
        }
        return object;
    }

    private boolean validateUserId(Object value) {
        if (value instanceof String) {
            if (((String) value).matches("(([a-z])|(\\d)){32}")) {
                return true;
            }
        }
        return false;
    }

}
