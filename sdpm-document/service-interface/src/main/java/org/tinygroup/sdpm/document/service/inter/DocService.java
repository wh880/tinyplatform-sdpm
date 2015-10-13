package org.tinygroup.sdpm.document.service.inter;

import java.util.List;

import org.tinygroup.sdpm.document.dao.pojo.DocumentDoc;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoclib;
import org.tinygroup.tinysqldsl.Pager;
/**
 * @date 2015/09/21
 * @author Alu
 *
 */

public interface DocService {
	
	public DocumentDoc createNewDoc(DocumentDoc doc);
	//Create a new document library-just a name.
	public DocumentDoclib createNewDocLib(DocumentDoclib doclib);
	//Edit document
	public int editDoc(DocumentDoc doc);
	//Edit document library name.
	public int editDocLibName(DocumentDoclib doclib);
	//find
	public DocumentDoc findDocById(Integer id);
	public DocumentDoclib findDoclibById(Integer id);
	//list
	public List<DocumentDoc> findDocList(DocumentDoc doc);
	public List<DocumentDoclib> findDoclibList(DocumentDoclib doclib);
	//page
	public Pager<DocumentDoc> findDocRetPager(Integer start,Integer limit,DocumentDoc doc,String sortName,boolean asc);
	public Pager<DocumentDoclib> findDoclibRetPager(Integer start,Integer limit,DocumentDoclib doclib,String sortName,boolean asc);
	//delete
	public int deleteDocById(Integer id);
	public int deleteDoclibById(Integer id);
	public int[] deleteDocByIds(List<DocumentDoc> ids);
	

}
