package org.tinygroup.sdpm.document.service.inter;

import org.tinygroup.sdpm.dao.complexsearch.SearchInfos;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoc;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDocLib;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * @author Alu
 * @date 2015/09/21
 */
public interface DocService {

    DocumentDoc createNewDoc(DocumentDoc doc);

    DocumentDocLib createNewDocLib(DocumentDocLib docLib);

    Integer editDoc(DocumentDoc doc);

    Integer editDocLibName(DocumentDocLib docLib);

    DocumentDoc findDocById(Integer id);

    DocumentDocLib findDocLibById(Integer id);

    List<DocumentDoc> findDocList(DocumentDoc doc);

    List<DocumentDocLib> findDocLibList(DocumentDocLib docLib);

    Pager<DocumentDoc> findDocRetPager(Integer start, Integer limit, DocumentDoc doc, Integer moduleId, SearchInfos conditions, String groupOperate, String sortName, boolean asc);

    Pager<DocumentDoc> findDocRetProductPager(Integer start, Integer limit, DocumentDoc doc, Integer moduleId, SearchInfos conditions, String groupOperate, String sortName, boolean asc);

    Pager<DocumentDoc> findDocRetProjectPager(Integer start, Integer limit, DocumentDoc doc, Integer moduleId, SearchInfos conditions, String groupOperate, String sortName, boolean asc);

    Integer deleteDocById(Integer id);

    Integer deleteDocLibById(Integer id);

    Integer deleteDocByIds(List<DocumentDoc> ids);


}
