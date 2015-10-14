package org.tinygroup.sdpm.document.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
//import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.document.biz.inter.DocBiz;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoc;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoclib;
import org.tinygroup.sdpm.document.service.inter.DocService;
import org.tinygroup.tinysqldsl.Pager;

/**
 * 因为业务需求比较简单，所以分层处理的优势不明显，显得有点多余。但是~
 * @date 2015/09/21
 * @author Alu
 *
 */
@Service
@Transactional
public class DocServiceImpl implements DocService{
	@Autowired
	private DocBiz docbiz;

	public DocumentDoc createNewDoc(DocumentDoc doc) {
		// 
		return docbiz.addDoc(doc);
	}

	public DocumentDoclib createNewDocLib(DocumentDoclib doclib) {
		// 
		return docbiz.addDocLib(doclib);
	}

	public int editDoc(DocumentDoc doc) {
		// 
		return docbiz.updtDoc(doc);
	}

	public int editDocLibName(DocumentDoclib doclib) {
		// 
		return docbiz.updtDocLib(doclib);
	}

	public DocumentDoc findDocById(Integer id) {
		// 
		return docbiz.getDocById(id);
	}

	public DocumentDoclib findDoclibById(Integer id) {
		// 
		return docbiz.getDocLibById(id);
	}

	public List<DocumentDoc> findDocList(DocumentDoc doc) {
		// 
		return docbiz.getDocList(doc);
	}
	public List<DocumentDoclib> findDoclibList(DocumentDoclib doclib) {
		// 
		return docbiz.getDoclibList(doclib);
	}

	public Pager<DocumentDoc> findDocRetPager(Integer start,Integer limit,DocumentDoc doc,SearchInfos conditions,String groupOperate, String sortName,boolean asc) {
		// 
		return docbiz.queryItemWithPage(start, limit, doc, conditions, groupOperate, sortName, asc);
	}
	
	public Pager<DocumentDoclib> findDoclibRetPager(Integer start, Integer limit,DocumentDoclib doclib,String sortName, boolean asc) {
		// 
		return docbiz.queryItemWithPage(start, limit, doclib,sortName, asc);
	}

	public int deleteDocById(Integer id) {
		// 
		return docbiz.delDocById(id);
	}

	public int deleteDoclibById(Integer id) {
		// 
		return docbiz.delDocLibById(id);
	}

	public int[] deleteDocByIds(List<DocumentDoc> ids) {
		// 
		return docbiz.batchDelDocByIds(ids);
	}

}
