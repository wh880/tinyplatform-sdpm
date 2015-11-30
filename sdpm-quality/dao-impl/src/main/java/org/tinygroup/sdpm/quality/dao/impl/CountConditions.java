package org.tinygroup.sdpm.quality.dao.impl;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.base.FragmentSql;
import org.tinygroup.tinysqldsl.base.Table;
import org.tinygroup.tinysqldsl.formitem.FromItem;
import org.tinygroup.tinysqldsl.selectitem.SelectItem;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangll13383 on 2015/10/30.
 */
public class CountConditions {

    private static Map<String, String> selectMap = new HashMap<String, String>();
    private static Map<String, FromItem> fromMap = new HashMap<String, FromItem>();
    private static Map<String, Column> columnMap = new HashMap<String, Column>();
    private static Map<String, Condition> havingConditionMap = new HashMap<String, Condition>();

    static {
        selectMap.put("A", "project.`project_name` AS NAME , \n" +
                "COUNT(quality_bug.bug_id) AS number , \n" +
                "COUNT(quality_bug.bug_id)/(\n" +
                "SELECT COUNT(0) FROM product LEFT JOIN quality_bug ON product.`product_id` = quality_bug.`product_id` WHERE product.product_id=%d) AS percent");
        selectMap.put("B", "project_build.`build_name` AS NAME , \n" +
                "COUNT(quality_bug.bug_id) AS number , \n" +
                "COUNT(quality_bug.bug_id)/(\n" +
                "SELECT COUNT(0) FROM product LEFT JOIN quality_bug ON product.`product_id` = quality_bug.`product_id` WHERE product.product_id=%d) AS percent");
        selectMap.put("C", "system_module.`module_name`AS NAME , \n" +
                "COUNT(quality_bug.bug_id) AS number , \n" +
                "COUNT(quality_bug.bug_id)/(\n" +
                "SELECT COUNT(0) FROM product LEFT JOIN quality_bug ON product.`product_id` = quality_bug.`product_id` WHERE product.product_id=%d) AS percent");
        selectMap.put("D", "openDate AS NAME , \n" +
                "COUNT(quality_bug.bug_id) AS number , \n" +
                "COUNT(quality_bug.bug_id)/(\n" +
                "SELECT COUNT(0) FROM product LEFT JOIN quality_bug ON product.`product_id` = quality_bug.`product_id` WHERE product.product_id=%d) AS percent");
        selectMap.put("E", "quality_bug.resolveDate AS NAME , \n" +
                "COUNT(quality_bug.bug_id) AS number , \n" +
                "COUNT(quality_bug.bug_id)/(\n" +
                "SELECT COUNT(0) FROM product LEFT JOIN quality_bug ON product.`product_id` = quality_bug.`product_id` WHERE product.product_id=%d) AS percent");
        selectMap.put("F", "quality_bug.closeDate AS NAME , \n" +
                "COUNT(quality_bug.bug_id) AS number , \n" +
                "COUNT(quality_bug.bug_id)/(\n" +
                "SELECT COUNT(0) FROM product LEFT JOIN quality_bug ON product.`product_id` = quality_bug.`product_id` WHERE product.product_id=%d) AS percent");
        selectMap.put("G", "org_user.`org_user_real_name` AS NAME , \n" +
                "COUNT(quality_bug.bug_id) AS number , \n" +
                "COUNT(quality_bug.bug_id)/(\n" +
                "SELECT COUNT(0) FROM product LEFT JOIN quality_bug ON product.`product_id` = quality_bug.`product_id` WHERE product.product_id=%d) AS percent");
        selectMap.put("H", "org_user.`org_user_real_name` AS NAME , \n" +
                "COUNT(quality_bug.bug_id) AS number , \n" +
                "COUNT(quality_bug.bug_id)/(\n" +
                "SELECT COUNT(0) FROM product LEFT JOIN quality_bug ON product.`product_id` = quality_bug.`product_id` WHERE product.product_id=%d) AS percent");
        selectMap.put("I", "org_user.`org_user_real_name` AS NAME , \n" +
                "COUNT(quality_bug.bug_id) AS number , \n" +
                "COUNT(quality_bug.bug_id)/(\n" +
                "SELECT COUNT(0) FROM product LEFT JOIN quality_bug ON product.`product_id` = quality_bug.`product_id` WHERE product.product_id=%d) AS percent");
        selectMap.put("J", "quality_bug.`bug_severity` AS NAME , \n" +
                "COUNT(quality_bug.bug_id) AS number , \n" +
                "COUNT(quality_bug.bug_id)/(\n" +
                "SELECT COUNT(0) FROM product LEFT JOIN quality_bug ON product.`product_id` = quality_bug.`product_id` WHERE product.product_id=%d) AS percent");
        selectMap.put("K", "quality_bug.`bug_resolution` AS NAME , \n" +
                "COUNT(quality_bug.bug_id) AS number , \n" +
                "COUNT(quality_bug.bug_id)/(\n" +
                "SELECT COUNT(0) FROM product LEFT JOIN quality_bug ON product.`product_id` = quality_bug.`product_id` WHERE product.product_id=%d) AS percent");
        selectMap.put("L", "quality_bug.`bug_status` AS NAME , \n" +
                "COUNT(quality_bug.bug_id) AS number , \n" +
                "COUNT(quality_bug.bug_id)/(\n" +
                "SELECT COUNT(0) FROM product LEFT JOIN quality_bug ON product.`product_id` = quality_bug.`product_id` WHERE product.product_id=%d) AS percent");
        selectMap.put("M", "quality_bug.`bug_activated_count` AS NAME , \n" +
                "COUNT(quality_bug.bug_id) AS number , \n" +
                "COUNT(quality_bug.bug_id)/(\n" +
                "SELECT COUNT(0) FROM product LEFT JOIN quality_bug ON product.`product_id` = quality_bug.`product_id` WHERE product.product_id=%d) AS percent");
        selectMap.put("N", "quality_bug.`bug_type` AS NAME , \n" +
                "COUNT(quality_bug.bug_id) AS number , \n" +
                "COUNT(quality_bug.bug_id)/(\n" +
                "SELECT COUNT(0) FROM product LEFT JOIN quality_bug ON product.`product_id` = quality_bug.`product_id` WHERE product.product_id=%d) AS percent");
        selectMap.put("O", "org_user.`org_user_real_name` AS NAME , \n" +
                "COUNT(quality_bug.bug_id) AS number , \n" +
                "COUNT(quality_bug.bug_id)/(\n" +
                "SELECT COUNT(0) FROM product LEFT JOIN quality_bug ON product.`product_id` = quality_bug.`product_id` WHERE product.product_id=%d) AS percent");

        fromMap.put("A", FragmentSql.fragmentFrom("project LEFT JOIN quality_bug ON project.project_id=quality_bug.`project_id`"));
        fromMap.put("B", FragmentSql.fragmentFrom("project_build LEFT JOIN quality_bug ON quality_bug.`bug_opened_build` = project_build.`build_id`"));
        fromMap.put("C", FragmentSql.fragmentFrom("system_module LEFT JOIN quality_bug ON system_module.`module_id` = quality_bug.`module_id`"));
        fromMap.put("D", FragmentSql.fragmentFrom("(SELECT quality_bug.*, DATE_FORMAT(quality_bug.`bug_opened_date`, \"%Y-%m-%d\") openDate FROM quality_bug) quality_bug"));
        fromMap.put("E", FragmentSql.fragmentFrom("(SELECT quality_bug.*, DATE_FORMAT(quality_bug.`bug_resolved_date`, \"%Y-%m-%d\") resolveDate FROM quality_bug) quality_bug"));
        fromMap.put("F", FragmentSql.fragmentFrom("(SELECT quality_bug.*, DATE_FORMAT(quality_bug.`bug_closed_date`, \"%Y-%m-%d\") closeDate FROM quality_bug WHERE quality_bug.`bug_closed_date` IS NOT NULL) quality_bug"));
        fromMap.put("G", FragmentSql.fragmentFrom("org_user LEFT JOIN quality_bug ON org_user.`org_user_id` = quality_bug.`bug_opened_by`"));
        fromMap.put("H", FragmentSql.fragmentFrom("org_user LEFT JOIN quality_bug ON org_user.`org_user_id` = quality_bug.`bug_resolved_by`"));
        fromMap.put("I", FragmentSql.fragmentFrom("org_user LEFT JOIN quality_bug ON org_user.`org_user_id` = quality_bug.`bug_closed_by`"));
        fromMap.put("J", FragmentSql.fragmentFrom("quality_bug"));
        fromMap.put("K", FragmentSql.fragmentFrom("quality_bug"));
        fromMap.put("L", FragmentSql.fragmentFrom("quality_bug"));
        fromMap.put("M", FragmentSql.fragmentFrom("quality_bug"));
        fromMap.put("N", FragmentSql.fragmentFrom("quality_bug"));
        fromMap.put("O", FragmentSql.fragmentFrom("org_user LEFT JOIN quality_bug ON org_user.`org_user_id` = quality_bug.`bug_assigned_to`"));

        columnMap.put("A", new Column(new Table("project"), "project_id"));
        columnMap.put("B", new Column(new Table("project_build"), "build_id"));
        columnMap.put("C", new Column(new Table("system_module"), "module_id"));
        columnMap.put("D", new Column(new Table("quality_bug"), "openDate"));
        columnMap.put("E", new Column(new Table("quality_bug"), "resolveDate"));
        columnMap.put("F", new Column(new Table("quality_bug"), "closeDate"));
        columnMap.put("G", new Column(new Table("org_user"), "org_user_id"));
        columnMap.put("H", new Column(new Table("org_user"), "org_user_id"));
        columnMap.put("I", new Column(new Table("org_user"), "org_user_id"));
        columnMap.put("J", new Column(new Table("quality_bug"), "bug_severity"));
        columnMap.put("K", new Column(new Table("quality_bug"), "bug_resolution"));
        columnMap.put("L", new Column(new Table("quality_bug"), "bug_status"));
        columnMap.put("M", new Column(new Table("quality_bug"), "bug_activated_count"));
        columnMap.put("N", new Column(new Table("quality_bug"), "bug_type"));
        columnMap.put("O", new Column(new Table("quality_bug"), "bug_assigned_to"));

        havingConditionMap.put("all", FragmentSql.fragmentCondition("COUNT(quality_bug.bug_id) <> 0"));
        havingConditionMap.put("M", FragmentSql.fragmentCondition("COUNT(quality_bug.bug_id) <> 0 AND quality_bug.`bug_activated_count` IS NOT NULL"));
    }

    public static SelectItem getSelectItem(String code, Integer productId) {
        return FragmentSql.fragmentSelect(String.format(selectMap.get(code), productId));
    }

    public static FromItem getFromItem(String code) {
        return fromMap.get(code);
    }

    public static Column getGroupByColumn(String code) {
        return columnMap.get(code);
    }

    public static Condition getHavingCondition(String code) {
        if ("M".equals(code)) {
            return havingConditionMap.get(code);
        } else {
            return havingConditionMap.get("all");
        }
    }
}
