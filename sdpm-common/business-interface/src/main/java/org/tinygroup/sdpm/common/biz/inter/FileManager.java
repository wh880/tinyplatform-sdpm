package org.tinygroup.sdpm.common.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.common.dao.pojo.File;

public interface FileManager {
	/**
	 * 附件添加
	 * @param file
	 * @return
	 */
	File add(File file);
	/**
	 * 批量添加附件
	 * @param file
	 * @return 附件List
	 */
	List<File> batchAdd(List<File> file);
	/**
	 * 查询
	 * @param file
	 * @return
	 */
	List<File> find(File file);
	/**
	 * 删除附件
	 * @param file
	 * @return
	 */
	int delete(File file);
	/**
	 * 更新附件信息
	 * @param file
	 * @return
	 */
	File updataFile(File file);
	
}
