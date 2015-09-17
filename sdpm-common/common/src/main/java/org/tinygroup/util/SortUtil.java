package org.tinygroup.util;

import com.google.common.collect.Table;
import org.tinygroup.tinysqldsl.base.Column;

import java.lang.reflect.Field;

/**
 * Created by wangll13383 on 2015/9/17.
 */
public class SortUtil {
    public static Column getSortColumn(String field , Table table){
        Field f = null;
        try {
            f =  table.getClass().getDeclaredField(resolverName(field));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        Column result = null;
        try {
            result = (Column) f.get(table);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String resolverName(String name){
        if(!name.contains("_")){
            char[] n = name.toCharArray();
            StringBuffer result = new StringBuffer();
            for(char c :n){
                if(c>=65&&c<=97){
                    result.append("_").append(c);
                }else{
                    result.append((char)(c-32));
                }

            }
            return result.toString();
        }
        return name;
    }
}
