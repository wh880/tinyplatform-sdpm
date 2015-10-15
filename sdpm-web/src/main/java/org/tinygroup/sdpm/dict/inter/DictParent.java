package org.tinygroup.sdpm.dict.inter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangll13383 on 2015/10/15.
 */
public class DictParent {
    protected static Map<String, Map<String,String>> dictMap = new ConcurrentHashMap<String, Map<String,String>>();
    protected static Map<String, List<String>> entryLinkMap = new ConcurrentHashMap<String, List<String>>();
    protected static Map<String, String> entryMapping = new ConcurrentHashMap<String, String>();
    protected static List<String> entryLink;
}
