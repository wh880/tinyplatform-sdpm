package org.tinygroup.sdpm.document.service.inter;

import java.util.List;

import org.tinygroup.sdpm.document.dao.pojo.Doc;
import org.tinygroup.sdpm.document.dao.pojo.Doclib;
import org.tinygroup.tinysqldsl.Pager;
/**
 * @date 2015/09/21
 * @author Alu
 *
 */

public interface DocService {
	
	public Doc createNewDoc(Doc doc);
	//Create a new document library-just a name.
	public Doclib createNewDocLib(Doclib doclib);
	//Edit document
	public int editDoc(Doc doc);
	//Edit document library name.
	public int editDocLibName(Doclib doclib);
	//find
	public Doc findDocById(Integer id);
	public Doclib findDoclibById(Integer id);
	//list
	public List<Doc> findDocByDocClass(Doc doc);
	//page
	public Pager<Doc> findDocRetPager(int start,int limit ,Doc doc);
	public Pager<Doclib> findDoclibRetPager(int start,int limit ,Doclib doclib);
	//delete
	public int deleteDocById(Integer id);
	public int deleteDoclibById(Integer id);
	public int deleteDocByIds(Integer... keys);
	

}
