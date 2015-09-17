package org.tinygroup.sdpm.service.service.inter;

import org.tinygroup.sdpm.service.dao.pojo.Sla;

import java.util.List;

/**
 * Created by Administrator on 2015-09-17.
 */
public interface slaService {
    List<Sla> findSlaList(Sla sla);
    /*查找客户的协议到表单*/

    Sla findSla(Sla sla);
    /*查找客户的协议*/

    Sla add(Sla sla);
    /* 新增协议*/

    Sla delete(Sla sla);
    /* 删除协议 */


    List<Sla> deleteBatch(Integer productId);
    /*批量删除*/
}
