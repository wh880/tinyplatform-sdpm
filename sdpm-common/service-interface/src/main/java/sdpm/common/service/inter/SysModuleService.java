package sdpm.common.service.inter;

import java.util.List;

import sdpm.common.dao.inter.pojo.SysModule;

public interface SysModuleService {
	/**
	 * 编辑
	 * @param sysModule
	 * @return
	 */
	public SysModule edit(SysModule sysModule);
	/**
	 * 保存
	 * @return
	 */
	public List<SysModule> save();
	/**
	 * 删除ById
	 * @param id
	 */
	public void deleteById(int id);
	
}
