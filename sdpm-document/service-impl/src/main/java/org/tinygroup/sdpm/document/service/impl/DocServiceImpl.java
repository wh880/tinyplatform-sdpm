package org.tinygroup.sdpm.document.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.document.biz.inter.DocBiz;
import org.tinygroup.sdpm.document.dao.pojo.Doc;
import org.tinygroup.sdpm.document.dao.pojo.Doclib;
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

	public Doc createNewDoc(Doc doc) {
		// 
		return docbiz.addDoc(doc);
	}

	public Doclib createNewDocLib(Doclib doclib) {
		// 
		return docbiz.addDocLib(doclib);
	}

	public int editDoc(Doc doc) {
		// 
		return docbiz.updtDoc(doc);
	}

	public int editDocLibName(Doclib doclib) {
		// 
		return docbiz.updtDocLib(doclib);
	}

	public Doc findDocById(Integer id) {
		// 
		return docbiz.getDocById(id);
	}

	public Doclib findDoclibById(Integer id) {
		// 
		return docbiz.getDocLibById(id);
	}

	public List<Doc> findDocByDocClass(Doc doc) {
		// 
		return docbiz.getDocByEntity(doc);
	}

	public Pager<Doc> findDocRetPager(int start, int limit, Doc doc) {
		// 
		return docbiz.queryItemWithPage(start, limit, doc);
	}
	
	public Pager<Doclib> findDoclibRetPager(int start, int limit, Doclib doclib) {
		// 
		return docbiz.queryItemWithPage(start, limit, doclib);
	}

	public int deleteDocById(Integer id) {
		// 
		return docbiz.delDocById(id);
	}

	public int deleteDoclibById(Integer id) {
		// 
		return docbiz.delDocLibById(id);
	}

	public int deleteDocByIds(Integer... keys) {
		// 
		return docbiz.batchDelDocByIds(keys);
	}

}
