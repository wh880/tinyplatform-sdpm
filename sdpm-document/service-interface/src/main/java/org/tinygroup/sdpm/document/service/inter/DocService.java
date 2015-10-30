package org.tinygroup.sdpm.document.service.inter;

import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoc;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDocLib;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;
/**
 * @date 2015/09/21
 * @author Alu
 *
 */
public interface DocService {
	
	public DocumentDoc createNewDoc(DocumentDoc doc);
	//Create a new document library-just a name.
	public DocumentDocLib createNewDocLib(DocumentDocLib doclib);
	//Edit document
	public int editDoc(DocumentDoc doc);
	//Edit document library name.
	public int editDocLibName(DocumentDocLib doclib);
	//find
	public DocumentDoc findDocById(Integer id);

	public DocumentDocLib findDoclibById(Integer id);
	//list
	public List<DocumentDoc> findDocList(DocumentDoc doc);

	public List<DocumentDocLib> findDoclibList(DocumentDocLib doclib);
	//page
	public Pager<DocumentDoc> findDocRetPager(Integer start,Integer limit,DocumentDoc doc, String statusCondition, SearchInfos conditions,String groupOperate, String sortName,boolean asc);

	public Pager<DocumentDocLib> findDoclibRetPager(Integer start, Integer limit, DocumentDocLib doclib, String sortName, boolean asc);
	//delete
	public int deleteDocById(Integer id);
	public int deleteDoclibById(Integer id);
	public int[] deleteDocByIds(List<DocumentDoc> ids);
	

}
