package org.tinygroup.sdpm.service.biz.inter;

import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.service.dao.pojo.ServiceRequest;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
public interface RequestManager {
    /**
     * 根据主键id查找请求
     *
     * @param id 主键
     * @return
     */
    ServiceRequest find(Integer id);

    /**
     * 根据产品模块id查找请求
     *
     * @param moldeId 主键
     * @return
     */
    ServiceRequest findByMolde(String moldeId);

    /**
     * 根据条件查询List
     *
     * @param request 用于查询条件
     * @return
     */
    List<ServiceRequest> getList(ServiceRequest request);

    /**
     * 新增请求
     *
     * @param request 新增实体类
     * @return
     */
    ServiceRequest add(ServiceRequest request);

    /**
     * 更新faq
     *
     * @param request 需要更新的实体类
     * @return
     */
    ServiceRequest update(ServiceRequest request);

    /**
     * 根据id进行软删除请求
     *
     * @param id 主键
     * @return
     */
    Integer delete(Integer id);

    /**
     * 根据list进行批量软删除请求
     *
     * @param
     * @return
     */
    int[] deleteBatch(List<ServiceRequest> list);

    /**
     * 分页查找所有请求
     *
     * @return
     */
    Pager<ServiceRequest> findPager(Integer start, Integer limit, Integer status, ServiceRequest serviceRequest, Integer treeId, ConditionCarrier carrier, String order, String ordertype);

    /**
     * 查找数据库的由我解决或由我回复的记录，区别在于operation
     *
     * @return
     */
    Pager<ServiceRequest> findOperationByMe(Integer start, Integer limit, OrgUser user, ServiceRequest serviceRequest, Integer treeId, Integer operation, String order, String ordertype);

    /**
     * 关闭请求
     *
     * @return
     */
    Integer close(ServiceRequest clientRequest);

    /**
     * 保存回复
     *
     * @return
     */
    Integer saveReply(ServiceRequest clientRequest);

    /**
     * 根据status改变request表中的状态
     *
     * @param
     * @return
     */
    Integer changeStatus(Integer id);

    /**
     * 指派回复
     *
     * @param
     * @return
     */
    int[] updateReply(List<ServiceRequest> list);

    /**
     * 指派回访
     *
     * @param
     * @return
     */
    int[] updateReview(List<ServiceRequest> list);

    /**
     * 根据查询名称查询
     *
     * @param condition
     * @return
     */
    List<ServiceRequest> requestInCondition(String condition, Integer limit);
}

