package org.tinygroup.sdpm.dict.inter;

import java.util.List;

/**
 * Created by wangll13383 on 2015/10/15.
 */
public interface DictHandle {
    public static String DICT_XSTREAM= "dict";

    public void addDictEntry(DictNodeEntries dictNodeEntries, String filePath) throws Exception;

    public DictNodeEntry getDict(String key);

    public void insertDictNodeEntry();

    public void removeDictNodeEntries(DictNodeEntries dictNodeEntries);
}
