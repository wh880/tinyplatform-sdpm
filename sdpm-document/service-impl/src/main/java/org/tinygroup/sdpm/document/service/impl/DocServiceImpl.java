package org.tinygroup.sdpm.document.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.ArrayUtil;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.dao.complexsearch.SearchInfos;
import org.tinygroup.sdpm.document.biz.inter.DocBiz;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoc;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDocLib;
import org.tinygroup.sdpm.document.service.inter.DocService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;


/**
 * 文档
 *
 * @author Alu
 * @date 2015/09/21
 */
@Service
@Transactional
public class DocServiceImpl implements DocService {
    @Autowired
    private DocBiz docbiz;

    public DocumentDoc createNewDoc(DocumentDoc doc) {
        return docbiz.addDoc(doc);
    }

    public DocumentDocLib createNewDocLib(DocumentDocLib docLib) {
        return docbiz.addDocLib(docLib);
    }

    public Integer editDoc(DocumentDoc doc) {
        return docbiz.updtDoc(doc);
    }

    public Integer editDocLibName(DocumentDocLib docLib) {
        return docbiz.updtDocLib(docLib);
    }
    @Transactional(readOnly = true)
    public DocumentDoc findDocById(Integer id) {
        return docbiz.getDocById(id);
    }
    @Transactional(readOnly = true)
    public DocumentDocLib findDocLibById(Integer id) {
        return docbiz.getDocLibById(id);
    }
    @Transactional(readOnly = true)
    public List<DocumentDoc> findDocList(DocumentDoc doc) {
        return docbiz.getDocList(doc);
    }
    @Transactional(readOnly = true)
    public List<DocumentDocLib> findDocLibList(DocumentDocLib docLib) {
        return docbiz.getDoclibList(docLib);
    }

    @Transactional(readOnly = true)
    public Pager<DocumentDoc> findDocRetProductPager(Integer start, Integer limit, DocumentDoc doc, Integer moduleId, SearchInfos conditions, String groupOperate, String sortName, boolean asc) {
        return docbiz.queryProductItemWithPage(start, limit, doc, moduleId, conditions, groupOperate, sortName, asc);
    }
    @Transactional(readOnly = true)
    public Pager<DocumentDoc> findDocRetProjectPager(Integer start, Integer limit, DocumentDoc doc, Integer moduleId, SearchInfos conditions, String groupOperate, String sortName, boolean asc) {
        return docbiz.queryProjectItemWithPage(start, limit, doc, moduleId, conditions, groupOperate, sortName, asc);
    }
    @Transactional(readOnly = true)
    public Pager<DocumentDoc> findDocRetPager(Integer start, Integer limit, DocumentDoc doc, Integer moduleId, SearchInfos conditions, String groupOperate, String sortName, boolean asc) {
        return docbiz.queryItemWithPage(start, limit, doc, moduleId, conditions, groupOperate, sortName, asc);
    }


    public Integer deleteDocById(Integer id) {
        return docbiz.delDocById(id);
    }

    public Integer deleteDocLibById(Integer id) {
        return docbiz.delDocLibById(id);
    }

    public Integer deleteDocByIds(List<DocumentDoc> ids) {
        int[] len = docbiz.batchDelDocByIds(ids);
        if (ArrayUtil.isEmptyArray(len)) {
            return 0;
        }
        return len.length;
    }

    @Override
    public List<DocumentDocLib> findDocLibByDocLib(DocumentDocLib lib) {
        if(StringUtil.isEmpty(lib.getDocLibName())) {
            return null;
        }
        return docbiz.findDocLibByDocLib(lib);
    }

}
