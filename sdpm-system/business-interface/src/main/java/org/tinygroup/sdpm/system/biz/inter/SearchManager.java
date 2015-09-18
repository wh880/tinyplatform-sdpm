package org.tinygroup.sdpm.system.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.Search;

public interface SearchManager {
    /**
     * 添加搜索
     * @param config
     * @return
     */
    Search add(Search search);

    /**
     * 删除
     * @param config
     * @return
     */
    int delete(Search search);

    /**
     * 修改
     * @param config
     * @return
     */
    int update(Search search);

    /**
     * 根据ID查找
     * @param configId
     * @return
     */
    Search findById(Integer searchId);

    /**
     * 根据对象查找
     * @param config
     * @return
     */
    List<Search> findConfig(Search search);

}
