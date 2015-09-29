package org.tinygroup.sdpm.common.util.std;

import org.tinygroup.database.config.table.Table;
import org.tinygroup.database.config.table.TableField;
import org.tinygroup.metadata.config.stdfield.StandardField;
import org.tinygroup.metadata.util.MetadataUtil;
import org.tinygroup.sdpm.common.util.sort.SortUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangll13383 on 2015/9/21.
 */
public class StdUtil {
    private static Map<String,String> stdMap = new ConcurrentHashMap<String, String>();

    private static Map<String,String> tableMap = new ConcurrentHashMap<String, String>();

    public static void stdProcess(List<Table> tables){
        for(Table table : tables){
            tableMap.put(resolveName(table.getName()),table.getName());
            addStd(table);
        }
    }

    public static String getTitle(String name){
        return stdMap.get(name);
    }

    private static void addStd(Object object){
        Table table = null;
        TableField tableField = null;
        if(object instanceof Table){
            table = (Table) object;
            for (TableField field : table.getFieldList()){
                addStd(field);
            }
        }else if (object instanceof  TableField){
            tableField = (TableField) object;
            StandardField standardField = MetadataUtil.getStandardField(tableField.getStandardFieldId(),StdUtil.class.getClassLoader());
            stdMap.put(standardField.getName(),standardField.getTitle());
        }
    }

    private static String resolveName(String name){
        if(name.contains("_")){
            String[] names = name.split("_");
            StringBuffer result = new StringBuffer().append(names[0]);
            for(int i = 1; i < names.length; i++){
                char[] c = names[i].toCharArray();
                c[0] = (char)(c[0]-32);
                result.append(c.toString());
            }
            return result.toString();
        }
        return name;
    }
}
