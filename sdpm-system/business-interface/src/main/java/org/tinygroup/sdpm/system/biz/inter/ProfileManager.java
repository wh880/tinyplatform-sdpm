package org.tinygroup.sdpm.system.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;

public interface ProfileManager {
	/**
	 * 附件添加
	 * @param SystemProfile
	 * @return
	 */
	SystemProfile add(SystemProfile SystemProfile);
	/**
	 * 批量添加附件
	 * @param SystemProfile
	 * @return 附件List
	 */
	int[] batchAdd(List<SystemProfile> SystemProfiles);
	/**
	 * 查询
	 * @param SystemProfile
	 * @return
	 */
	List<SystemProfile> find(SystemProfile SystemProfile);
	/**
	 * 删除附件
	 * @param SystemProfile
	 * @return
	 */
	Integer delete(SystemProfile SystemProfile);
	/**
	 * 更新附件信息
	 * @param SystemProfile
	 * @return
	 */
	SystemProfile updataSystemProfile(SystemProfile SystemProfile);
	
}
