package org.tinygroup.sdpm.document.biz.inter;

import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoc;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoclib;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;


public interface DocBiz {
    //以下接口都非常容易看懂，即使没有备注。
    DocumentDoclib addDocLib(DocumentDoclib doclib);

    DocumentDoc addDoc(DocumentDoc doc);

    int updtDoc(DocumentDoc doc);

    int updtDocLib(DocumentDoclib doclib);

    int delDocById(Integer key);

    int delDocLibById(Integer key);

    DocumentDoc getDocById(Integer key);

    //这个基本不用，或是用来判断更新是否有改动，如果一字都没改动就不操作数据库。
    List<DocumentDoc> getDocList(DocumentDoc doc);

    List<DocumentDoclib> getDoclibList(DocumentDoclib doclib);

    DocumentDoclib getDocLibById(Integer key);

    //查询后分页吧~//可以排序OrderBy id asc
    Pager<DocumentDoc> queryItemWithPage(Integer start, Integer limit, DocumentDoc doc, String statusCondition, SearchInfos conditions, String groupOperate, String sortName, boolean asc);

    Pager<DocumentDoclib> queryItemWithPage(Integer start, Integer limit, DocumentDoclib doclib, String sortName, boolean asc);

    int[] batchDelDocByIds(List<DocumentDoc> keys);

}
