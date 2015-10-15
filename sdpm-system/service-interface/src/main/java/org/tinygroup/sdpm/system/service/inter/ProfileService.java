package org.tinygroup.sdpm.system.service.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;


public interface ProfileService {
	/**
	 * 添加附件
	 * @param 
	 * @return 附件
	 */
    SystemProfile add(SystemProfile systemProfile);
   /**
    * 批量添加附件
    * @return
    */
    int[] batchAdd(List<SystemProfile> systemProfiles);
   /**
    * 查询附件信息
    * @param
    * @return 附件信息表
    */
    List<SystemProfile> find(SystemProfile systemProfile);
    /**
     * 删除附件信息
     * @param SystemProfile
     * @return
     */
    int delete(SystemProfile systemProfile);
    /**
     * 编辑附件
     * @param SystemProfile
     * @return Flie 对象
     */
    
    SystemProfile edit(SystemProfile systemProfile);
    /**
     * 编辑文件名
     * @param systemProfile
     * @return
     */
    SystemProfile editTitle(SystemProfile systemProfile);
    /**
     * 软删除
     * @param id
     * @return
     */
    
    Integer softDelete(Integer id);
 
}
