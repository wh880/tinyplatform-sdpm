package org.tinygroup.sdpm.dict.inter;

import java.util.List;

/**
 * Created by wangll13383 on 2015/10/15.
 */
public interface DictHandle {
    String DICT_XSTREAM = "dictFile";

    void addDictEntry(DictNodeEntries dictNodeEntries, String filePath) throws Exception;

    void insertDictNodeEntry();

    List<DictNodeEntries> getDictNodeEntries();

    void removeDictNodeEntries(DictNodeEntries dictNodeEntries);
}
