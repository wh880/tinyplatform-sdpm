package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.project.dao.pojo.Burn;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-17.
 */
public interface burnService {
    /**
     * 新增，在项目变动后需要增加字段
     */
    public int add(Burn burn);

    /**
     * 根据项目id找到对应的所有数据，前台生成燃尽图
     */
    public List<Burn> findById(int projectId);
}
