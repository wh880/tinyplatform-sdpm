package org.tinygroup.sdpm.common.util.update;

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
    public static Update getUpdate(Table table, Object object){
        List<Value> values = new ArrayList<Value>();
        Field[] fields = object.getClass().getDeclaredFields();
        Object primaryValue = null;
        for(Field field : fields){
            Method method = null;
            Object value = null;
            try {
                method = object.getClass().getMethod(getMethodName(field.getName()));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            try {
                value = method.invoke(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            if(value != null){
                String tableField = resolverName(field.getName());
                String primaryField = StdUtil.getPrimary(table.getName())!=null?StdUtil.getPrimary(table.getName()).toLowerCase():"";
                if(!tableField.toLowerCase().equals(primaryField)){
                    Column column = new Column(tableField);
                    column.setTable(table);
                    values.add(new Value(column,value));
                }else{
                    primaryValue = value;
                }
            }
        }
        Column primary = new Column(StdUtil.getPrimary(table.getName()));
        primary.setTable(table);
        Value[] values1 = new Value[values.size()];
        return Update.update(table).set( values.toArray(values1)).where(primary.eq(primaryValue));
    }

    private static String getMethodName(String fieldName){
        char[] c = fieldName.toCharArray();
        c[0] = (char)(c[0]-32);
        return "get"+String.valueOf(c);
    }

    private static String resolverName(String name){
        if(!name.contains("_")){
            char[] n = name.toCharArray();
            StringBuffer result = new StringBuffer();
            for(char c :n){
                if(c>=65&&c<=97){
                    result.append("_").append((char)(c+32));
                }else{
                    result.append((c));
                }

            }
            return result.toString();
        }
        return name;
    }
}
