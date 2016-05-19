package org.tinygroup.sdpm.document.service.inter;

import org.tinygroup.aopcache.annotation.CacheGet;
import org.tinygroup.aopcache.annotation.CacheRemove;
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

    String CACHE_DOC_LIB_LIST = "docLibList";

    DocumentDoc createNewDoc(DocumentDoc doc);

    @CacheRemove(removeGroups = CACHE_DOC_LIB_LIST)
    DocumentDocLib createNewDocLib(DocumentDocLib docLib);

    Integer editDoc(DocumentDoc doc);

    @CacheRemove(removeGroups = CACHE_DOC_LIB_LIST)
    Integer editDocLibName(DocumentDocLib docLib);

    DocumentDoc findDocById(Integer id);

    DocumentDocLib findDocLibById(Integer id);

    List<DocumentDoc> findDocList(DocumentDoc doc);

    @CacheGet(key = "docLibList${docLib?.docLibId}", group = CACHE_DOC_LIB_LIST)
    List<DocumentDocLib> findDocLibList(DocumentDocLib docLib);

    Pager<DocumentDoc> findDocRetPager(Integer start, Integer limit, DocumentDoc doc, Integer moduleId, SearchInfos conditions, String groupOperate, String sortName, boolean asc);

    Pager<DocumentDoc> findDocRetProductPager(Integer start, Integer limit, DocumentDoc doc, Integer moduleId, SearchInfos conditions, String groupOperate, String sortName, boolean asc);

    Pager<DocumentDoc> findDocRetProjectPager(Integer start, Integer limit, DocumentDoc doc, Integer moduleId, SearchInfos conditions, String groupOperate, String sortName, boolean asc);

    Integer deleteDocById(Integer id);

    @CacheRemove(removeGroups = CACHE_DOC_LIB_LIST)
    Integer deleteDocLibById(Integer id);

    Integer deleteDocByIds(List<DocumentDoc> ids);

    List<DocumentDocLib> findDocLibByDocLib(DocumentDocLib lib);
}
