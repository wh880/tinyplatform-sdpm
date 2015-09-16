package sdpm.common.service.inter;

import java.util.List;

import sdpm.common.dao.inter.pojo.SysModule;

public interface SysModuleService {
	/**
	 * 编辑
	 * @param sysModule
	 * @return
	 */
	 SysModule edit(SysModule sysModule);
	/**
	 * 保存
	 * @return
	 */
	 SysModule save(SysModule sysModule);
	/**
	 * 删除ById
	 * @param id
	 */
	 int deleteById(int id);
	
	/**
	 * 根据根节点进行查询
	 * @param root
	 * @return
	 */
	 List<SysModule> queryByRoot(SysModule sysModule);
}
