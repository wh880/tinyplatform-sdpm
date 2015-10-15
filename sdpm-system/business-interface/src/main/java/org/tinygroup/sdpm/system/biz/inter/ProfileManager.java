package org.tinygroup.sdpm.system.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;

public interface ProfileManager {
	/**
	 * 附件添加
	 * @param SystemProfile
	 * @return
	 */
	SystemProfile add(SystemProfile systemProfile);
	/**
	 * 批量添加附件
	 * @param SystemProfile
	 * @return 附件List
	 */
	int[] batchAdd(List<SystemProfile> systemProfiles);
	/**
	 * 查询
	 * @param SystemProfile
	 * @return
	 */
	List<SystemProfile> find(SystemProfile systemProfile);
	/**
	 * 删除附件
	 * @param SystemProfile
	 * @return
	 */
	Integer delete(SystemProfile systemProfile);
	/**
	 * 更新附件信息
	 * @param SystemProfile
	 * @return
	 */
	SystemProfile updataSystemProfile(SystemProfile systemProfile);
	/**
	 * 
	 * @param systemProfile
	 * @return
	 */
	SystemProfile editFileTitle(SystemProfile systemProfile);
	/**
	 * 软删除
	 * @param id
	 * @return
	 */
	Integer softDelete(Integer id);
	/**
	 * 通过Id查询
	 * @param id
	 * @return
	 */
	SystemProfile findById(Integer id);
	
}
