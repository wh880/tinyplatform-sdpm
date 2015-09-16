package org.tinygroup.sdpm.common.service.inter;

import java.util.List;

import org.tinygroup.sdpm.common.dao.pojo.File;


public interface FileService {
	/**
	 * 
	 * @param 添加附件
	 * @return 附件
	 */
    File add(File file);
   /**
    * 批量添加附件
    * @return
    */
    List<File> bechAdd();
   /**
    * 查询附件信息
    * @return 附件信息表
    */
    List<File> query();
 
}
