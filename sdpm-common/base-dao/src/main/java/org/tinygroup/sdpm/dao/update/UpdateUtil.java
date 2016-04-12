package org.tinygroup.sdpm.dao.update;

import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.common.util.std.StdUtil;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;
import org.tinygroup.tinysqldsl.base.Value;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangll13383 on 2015/9/29.
 */
public class UpdateUtil {
    public static Update getUpdate(Table table, Object object) {
        List<Value> values = new ArrayList<Value>();
        Field[] fields = object.getClass().getDeclaredFields();
        Object primaryValue = null;
        for (Field field : fields) {
            Method method = null;
            Object value = null;
            if (StdUtil.getField(table.getName()).containsKey(field.getName())) {
                try {
                    method = object.getClass().getMethod(NameUtil.toMethod(field.getName()));
                } catch (NoSuchMethodException e) {

                }
            }
            if (method != null) {
                try {
                    value = method.invoke(object);
                } catch (IllegalAccessException e) {

                } catch (InvocationTargetException e) {

                }
            }
            if (value != null) {
                String tableField = field.getName();
                String primaryField = StdUtil.getPrimary(table.getName()) != null ? StdUtil.getPrimary(table.getName()).toLowerCase() : "";
                if (!tableField.toLowerCase().equals(primaryField)) {
                    Column column = new Column(NameUtil.resolveNameDesc(tableField));
                    column.setTable(table);
                    values.add(new Value(column, value));
                } else {
                    primaryValue = value;
                }
            }
        }
        Column primary = new Column(NameUtil.resolveNameDesc(StdUtil.getPrimary(NameUtil.resolveNameDesc(table.getName()))));
        primary.setTable(table);
        Value[] values1 = new Value[values.size()];
        return Update.update(table).set(values.toArray(values1)).where(primary.eq(primaryValue));
    }

}
