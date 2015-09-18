package org.tinygroup.sdpm.service.service.inter;

import org.tinygroup.sdpm.service.dao.pojo.Reply;

import java.util.List;

/**
 * Created by Administrator on 2015-09-17.
 */
public interface ReplyService {
    /**
     * 根据主键id查找解决中的请求
     *
     * @param id 主键
     * @return
     */
    Reply find(String id);

    /**
     * 根据产品模块id解决中的请求
     *
     * @param moldeId 主键
     * @return
     */
    Reply findByMolde(String moldeId);

    /**
     * 根据条件查询List
     *
     * @param reply 用于查询条件
     * @return
     */
    List<Reply> getList(Reply reply);

    /**
     * 将请求指派成员去解决
     *
     * @param reply 新增实体类
     * @return
     */
    Reply add(Reply reply);

    /**
     * 更新faq
     *
     * @param reply 需要更新的实体类
     * @return
     */
    Reply update(Reply reply);

    /**
     * 根据id对回复进行删除请求
     * 对请求进行软删除
     *
     * @param id 主键
     * @return
     */
    Integer delete(String id);

    /**
     * 根据id对回复进行批量删除请求
     * 对请求进行软删除
     *
     * @param id 主键
     * @return
     */
    Integer deleteBatch(Integer id);
}
