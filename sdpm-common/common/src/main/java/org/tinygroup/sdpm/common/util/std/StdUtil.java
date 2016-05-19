package org.tinygroup.sdpm.common.util.std;

import org.tinygroup.database.config.table.Table;
import org.tinygroup.database.config.table.TableField;
import org.tinygroup.metadata.config.stdfield.StandardField;
import org.tinygroup.metadata.util.MetadataUtil;
import org.tinygroup.sdpm.common.util.common.NameUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangll13383 on 2015/9/21.
 */
public class StdUtil {
    private static Map<String, Map<String, String>> tableStdMap = new ConcurrentHashMap<String, Map<String, String>>();

    private static Map<String, String> tablePrimary = new HashMap<String, String>();

    private static Map<String, String> tableMap = new ConcurrentHashMap<String, String>();

    private static Map<String, String> tableMapping = new HashMap<String, String>();

    static {
        tableMapping.put("user", "org_user");
        tableMapping.put("story", "product_story");
        tableMapping.put("plan", "product_plan");
        tableMapping.put("release", "product_release");
        tableMapping.put("project", "project");
        tableMapping.put("product", "product");
        tableMapping.put("build", "project_build");
        tableMapping.put("bug", "quality_bug");
        tableMapping.put("case", "quality_test_case");
        tableMapping.put("testtask", "quality_test_task");
        tableMapping.put("doclib", "document_doclib");
        tableMapping.put("doc", "document_doc");
        tableMapping.put("sla", "service_sla");
        tableMapping.put("client", "service_client");
        tableMapping.put("faq", "service_faq");
        tableMapping.put("reply", "service_reply");
        tableMapping.put("review", "service_review");
        tableMapping.put("request", "service_request");
        tableMapping.put("task", "project_task");
        tableMapping.put("plan", "product_plan");
        tableMapping.put("holiday", "holiday");
        tableMapping.put("productLine", "product_line");
    }

    public static void stdProcess(List<Table> tables) {
        for (Table table : tables) {

            tableMap.put(NameUtil.resolveNameAsc(table.getName()), table.getName());
            Map<String, String> stdMap = new HashMap<String, String>();
            addStd(table, table, stdMap);
            tableStdMap.put(table.getName(), stdMap);
        }
    }

    public static boolean containsField(String fieldName) {
        for (String key : tableStdMap.keySet()) {
            if (tableStdMap.get(key).containsKey(fieldName)) {
                return true;
            }
        }
        return false;
    }

    public static String getField(String tableName, String fieldName) {
        String table = tableMapping.get(tableName);
        if (table == null) {
            return null;
        }
        return tableStdMap.get(table).get(fieldName);
    }

    public static Map<String, String> getField(String tableName) {
        return tableStdMap.get(tableName);
    }

    public static String getPrimary(String tableName) {
        return tablePrimary.get(tableName);
    }

    private static void addStd(Table t, Object object, Map<String, String> stdMap) {
        Table table;
        TableField tableField;
        if (object instanceof Table) {
            table = (Table) object;
            for (TableField field : table.getFieldList()) {
                addStd(t, field, stdMap);
            }
        } else if (object instanceof TableField) {
            tableField = (TableField) object;
            StandardField standardField = MetadataUtil.getStandardField(tableField.getStandardFieldId(), StdUtil.class.getClassLoader());
            try {
                stdMap.put(NameUtil.resolveNameAsc(standardField.getName()), standardField.getTitle());
            } catch (Exception e) {
                throw new RuntimeException("标准字段" + standardField.getId() + "出错");

            }
            if (tableField.getPrimary()) {
                tablePrimary.put(t.getName(), NameUtil.resolveNameAsc(standardField.getName()));
            }
        }
    }


}
