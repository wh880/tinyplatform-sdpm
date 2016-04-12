package org.tinygroup.sdpm.dao.update;

import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.common.util.std.StdUtil;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;
import org.tinygroup.tinysqldsl.base.Value;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class InsertUtil {
    public static Insert getInsert(Table table, Object object) {
        try {

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
                        continue;
                    }
                }
                if (method != null) {
                    value = method.invoke(object);
                }
                if (value != null) {
                    String tableField = field.getName();
                    String primaryField = StdUtil.getPrimary(table.getName()) != null ? StdUtil
                            .getPrimary(table.getName()).toLowerCase() : "";
                    if (!tableField.toLowerCase().equals(primaryField)) {
                        values.add(new Column(table, NameUtil
                                .resolveNameDesc(tableField)).value(value));
                    } else {
                        primaryValue = value;
                    }
                }

            }
            Column primary = new Column(NameUtil.resolveNameDesc(StdUtil
                    .getPrimary(NameUtil.resolveNameDesc(table.getName()))));
            primary.setTable(table);
            Value[] values1 = new Value[values.size()];

            return Insert.insertInto(table).values(values.toArray(values1));

        } catch (Exception e) {
            return null;
        }
    }
}
