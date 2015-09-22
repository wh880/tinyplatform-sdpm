package org.tinygroup.sdpm.docment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.docment.biz.inter.DocBiz;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.docment.service.inter.DocService;
import org.tinygroup.sdpm.document.dao.pojo.Doc;
import org.tinygroup.sdpm.document.dao.pojo.DocLib;
import org.tinygroup.sdpm.document.dao.pojo.Historydoc;
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

	public DocLib createNewDocLib(DocLib doclib) {
		// 
		return docbiz.addDocLib(doclib);
	}

	public int editDoc(Doc doc) {
		// 
		return docbiz.updtDoc(doc);
	}

	public int editDocLibName(DocLib doclib) {
		// 
		return docbiz.updtDocLib(doclib);
	}

	public Doc findDocById(Integer id) {
		// 
		return docbiz.getDocById(id);
	}

	public DocLib findDoclibById(Integer id) {
		// 
		return docbiz.getDocLibById(id);
	}
	
	public List<Historydoc> getEditRecord(Integer docid)
	{
		//获取历史记录
		//取数据
		//List<Historydoc> list = docbiz.docHistory(docid);
		//for(int i=list.size();i>=0;i--){
		//list.get(i).getRecTime();list.get(i).getRecWho();}
		return docbiz.docHistory(docid);
	}

	public List<Doc> findDocByDocClassOrderBy(Doc doc, OrderBy... orderbys) {
		// 
		return docbiz.getDocListOrderBy(doc);
	}

	public Pager<Doc> findDocRetPager(int start, int limit, Doc doc,OrderBy...orderbys) {
		// 
		return docbiz.queryItemWithPage(start, limit, doc,orderbys);
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
