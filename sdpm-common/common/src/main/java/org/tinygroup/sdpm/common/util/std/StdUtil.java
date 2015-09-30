package org.tinygroup.sdpm.common.util.std;

import org.tinygroup.database.config.table.Table;
import org.tinygroup.database.config.table.TableField;
import org.tinygroup.metadata.config.stdfield.StandardField;
import org.tinygroup.metadata.util.MetadataUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangll13383 on 2015/9/21.
 */
public class StdUtil {
    private static Map<String,Map<String,String>> tableStdMap = new ConcurrentHashMap<String, Map<String,String>>();

    private static Map<String,String> tablePrimary = new HashMap<String, String>();

    private static Map<String,String> tableMap = new ConcurrentHashMap<String, String>();

    public static void stdProcess(List<Table> tables){
        for(Table table : tables){
            tableMap.put(resolveName(table.getName()),table.getName());
            Map<String,String> stdMap = new HashMap<String, String>();
            addStd(table,table,stdMap);
            tableStdMap.put(table.getName(),stdMap);
        }
    }

    public static String getField(String tableName, String feildName) {
        return tableStdMap.get(tableMap.get(tableName)).get(feildName);
    }

    public static Map<String, String> getField(String table) {
        return tableStdMap.get(table);
    }

    public static String getPrimary(String tableName){
        return tablePrimary.get(tableName);
    }

    private static void addStd(Table t,Object object, Map<String,String> stdMap){
        Table table = null;
        TableField tableField = null;
        if(object instanceof Table){
            table = (Table) object;
            for (TableField field : table.getFieldList()){
                addStd(t,field,stdMap);
            }
        }else if (object instanceof  TableField){
            tableField = (TableField) object;
            StandardField standardField = MetadataUtil.getStandardField(tableField.getStandardFieldId(),StdUtil.class.getClassLoader());
            stdMap.put(standardField.getName(),standardField.getTitle());
            if(tableField.getPrimary()){
                tablePrimary.put(t.getName(),standardField.getName());
            }
        }
    }

    private static String resolveName(String name){
        if(name.contains("_")){
            String[] names = name.split("_");
            StringBuffer result = new StringBuffer().append(names[0]);
            for(int i = 1; i < names.length; i++){
                char[] c = names[i].toCharArray();
                c[0] = (char)(c[0]-32);
                result.append(String.valueOf(c));
            }
            return result.toString();
        }
        return name;
    }
}
