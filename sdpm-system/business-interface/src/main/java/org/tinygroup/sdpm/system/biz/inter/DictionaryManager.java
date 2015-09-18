package org.tinygroup.sdpm.system.biz.inter;

public interface DictionaryManager {
    /**
     * 添加字典
     * @param config
     * @return
     */
    Dict add(Dict dict);

    /**
     * 删除
     * @param config
     * @return
     */
    int delete(Dict dict);

    /**
     * 修改
     * @param config
     * @return
     */
    int update(Dict dict);

    /**
     * 根据ID查找
     * @param configId
     * @return
     */
    Dict findById(Integer dictId);

    /**
     * 根据对象查找
     * @param config
     * @return
     */
    List<Dict> findConfig(Dict dict);

}
