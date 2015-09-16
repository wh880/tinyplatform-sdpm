package sdpm.common.service.inter;

import java.util.List;

import sdpm.common.dao.inter.pojo.File;

public interface FileService {
	/**
	 * 
	 * @param 添加附件
	 * @return 附件
	 */
   public File add(File file);
   /**
    * 批量添加附件
    * @return
    */
   public List<File> bechAdd();
   /**
    * 查询附件信息
    * @return 附件信息表
    */
   public List<File> query();
 
}
