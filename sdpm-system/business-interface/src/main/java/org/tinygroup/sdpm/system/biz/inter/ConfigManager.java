package org.tinygroup.sdpm.system.biz.inter;

public interface ConfigManager {
    /**
     * 添加配置
     * @param config
     * @return
     */
    Config add(Config config);

    /**
     * 删除
     * @param config
     * @return
     */
    int delete(Config config);

    /**
     * 修改
     * @param config
     * @return
     */
    int update(Config config);

    /**
     * 根据ID查找
     * @param configId
     * @return
     */
    Config findById(Integer configId);

    /**
     * 根据对象查找
     * @param config
     * @return
     */
    List<Config> findConfig(Config config);

}
