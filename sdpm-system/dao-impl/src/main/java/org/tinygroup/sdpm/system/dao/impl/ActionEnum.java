package org.tinygroup.sdpm.system.dao.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangll13383 on 2015/10/14.
 */
public class ActionEnum {

    private static Map<String, String> tableMap = new HashMap<String, String>();
    private static Map<String, String> primaryMap = new HashMap<String, String>();
    private static Map<String, String> nameMap = new HashMap<String, String>();
    private static Map<String, String> urlMap = new HashMap<String, String>();

    static {
        tableMap.put("user", "org_user");
        tableMap.put("story", "product_story");
        tableMap.put("task", "project_task");
        tableMap.put("plan", "product_plan");
        tableMap.put("release", "product_release");
        tableMap.put("project", "project");
        tableMap.put("product", "product");
        tableMap.put("build", "project_build");
        tableMap.put("bug", "quality_bug");
        tableMap.put("case", "quality_test_case");
        tableMap.put("testtask", "quality_test_task");
        tableMap.put("todo", "todo");
        tableMap.put("doclib", "document_doclib");
        tableMap.put("doc", "document_doc");
        tableMap.put("holiday", "holiday");
        tableMap.put("productLine", "product_line");
        tableMap.put("sla", "service_sla");


        primaryMap.put("user", "org_user_id");
        primaryMap.put("story", "story_id");
        primaryMap.put("task", "task_id");
        primaryMap.put("plan", "plan_id");
        primaryMap.put("release", "release_id");
        primaryMap.put("project", "project_id");
        primaryMap.put("product", "product_id");
        primaryMap.put("build", "build_id");
        primaryMap.put("bug", "bug_id");
        primaryMap.put("case", "case_id");
        primaryMap.put("testtask", "testversion_id");
        primaryMap.put("todo", "todo_id");
        primaryMap.put("doclib", "doc_lib_id");
        primaryMap.put("doc", "doc_id");
        primaryMap.put("holiday", "holiday_id");
        primaryMap.put("productLine", "product_line_id");
        primaryMap.put("sla", "sla_id");

        nameMap.put("user", "org_user_account");
        nameMap.put("story", "story_title");
        nameMap.put("task", "task_name");
        nameMap.put("plan", "plan_name");
        nameMap.put("release", "release_name");
        nameMap.put("project", "project_name");
        nameMap.put("product", "product_name");
        nameMap.put("build", "build_name");
        nameMap.put("bug", "bug_title");
        nameMap.put("case", "case_title");
        nameMap.put("testtask", "testtask_title");
        nameMap.put("todo", "todo_name");
        nameMap.put("doclib", "doc_lib_name");
        nameMap.put("doc", "doc_title");
        nameMap.put("holiday", "holiday_name");
        nameMap.put("productLine", "product_line_name");
        nameMap.put("sla", "sla_title");

        urlMap.put("user", "/org/user/show/profile?choose=3&id=");
        urlMap.put("story", "/product/storySpec/find/productDemandDetail?storyId=");
        urlMap.put("task", "/project/task/findTask?taskId=");
        urlMap.put("plan", "/product/plan/forword/reRelateStory?planId=");
        urlMap.put("release", "/product/release/forword/reRelateStory?releaseId=");
        urlMap.put("project", "/project/view?projectId=");
        urlMap.put("product", "/product/find/overview?productId=");
        urlMap.put("build", "/project/build/productalbug?buildId=");
        urlMap.put("bug", "/quality/bug/bugInfo?bugId=");
        urlMap.put("case", "/quality/testCase/case/viewInfo?id=");
        urlMap.put("testtask", "/quality/version/taskToCase?currentPageId=5&status=tverallcase&testversionId=");
        urlMap.put("todo", "todo_name");
        urlMap.put("doclib", "/document?docLibId=");
        urlMap.put("doc", "/document/doc/view?docid=");
        urlMap.put("holiday", "/system/holiday/view?holidayId=");
        urlMap.put("productLine", "/productLine/find/overview?productLineId=");
        urlMap.put("sla", "/service/sla/slaContent?id=");
    }

    public static String getTable(String type) {
        return tableMap.get(type);
    }

    public static String getPrimary(String type) {
        return primaryMap.get(type);
    }

    public static String getName(String type) {
        return nameMap.get(type);
    }

    public static String getUrl(String type) {
        return urlMap.get(type);
    }

}
