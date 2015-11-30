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
	
	DocumentDoc createNewDoc(DocumentDoc doc);
	//Create a new document library-just a name.
	DocumentDocLib createNewDocLib(DocumentDocLib doclib);
	//Edit document
	int editDoc(DocumentDoc doc);
	//Edit document library name.
	int editDocLibName(DocumentDocLib doclib);
	//find
	DocumentDoc findDocById(Integer id);

	DocumentDocLib findDoclibById(Integer id);
	//list
	List<DocumentDoc> findDocList(DocumentDoc doc);

	List<DocumentDocLib> findDoclibList(DocumentDocLib doclib);
	//page
	Pager<DocumentDoc> findDocRetPager(Integer start,Integer limit,DocumentDoc doc,Integer moduleId, SearchInfos conditions,String groupOperate, String sortName,boolean asc);

	Pager<DocumentDoc> findDocRetProductPager(Integer start,Integer limit,DocumentDoc doc, Integer moduleId, SearchInfos conditions,String groupOperate, String sortName,boolean asc);

	Pager<DocumentDoc> findDocRetProjectPager(Integer start,Integer limit,DocumentDoc doc, Integer moduleId, SearchInfos conditions,String groupOperate, String sortName,boolean asc);

	Pager<DocumentDocLib> findDoclibRetPager(Integer start, Integer limit, DocumentDocLib doclib, String sortName, boolean asc);
	//delete
	int deleteDocById(Integer id);
	int deleteDoclibById(Integer id);
	int[] deleteDocByIds(List<DocumentDoc> ids);
	

}
