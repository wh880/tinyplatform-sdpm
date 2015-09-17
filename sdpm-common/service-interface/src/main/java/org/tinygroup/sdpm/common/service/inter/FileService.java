package org.tinygroup.sdpm.common.service.inter;

import java.util.List;

import org.tinygroup.sdpm.common.dao.pojo.File;


public interface FileService {
	/**
	 * 添加附件
	 * @param 
	 * @return 附件
	 */
    File add(File file);
   /**
    * 批量添加附件
    * @return
    */
    List<File> bechAdd(List<File> files);
   /**
    * 查询附件信息
    * @param
    * @return 附件信息表
    */
    List<File> find(File file);
    /**
     * 删除附件信息
     * @param file
     * @return
     */
    int delete(File file);
    /**
     * 编辑附件
     * @param file
     * @return Flie 对象
     */
    
    File edit(File file);
 
}
