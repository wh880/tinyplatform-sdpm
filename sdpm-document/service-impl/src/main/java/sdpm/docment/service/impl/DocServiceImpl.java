package sdpm.docment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.sdpm.document.dao.inter.DocDao;
import org.tinygroup.sdpm.document.dao.inter.DocLibDao;
import org.tinygroup.sdpm.document.pojo.Doc;
import org.tinygroup.sdpm.document.pojo.DocLib;

import sdpm.docment.service.DocService;

@Service
public class DocServiceImpl implements DocService{
	@Autowired
	private DocDao docdao;
	@Autowired
	private DocLibDao doclibdao;

	public void addDocLib(DocLib doclib) {
		//
		if(doclib.getDocLibid()!=null)
			doclibdao.add(doclib);
		//return "the doclib already exist.";
		
	}

	public void addDoc(Doc doc) {
		//
		if(doc.getDocId()!=null)
			docdao.add(doc);
		//return "the doc already exist.";
	}

	public int updtDoc(Doc doc) {
		// 
		return docdao.edit(doc);
	}

	public int updtDocLib(DocLib doclib) {
		// 
		return doclibdao.edit(doclib);
	}

	public int delDoc(Integer key) {
		// 
		return docdao.deleteByKey(key);
	}

	public int delDocLib(Integer key) {
		// 
		return doclibdao.deleteByKey(key);
	}

	public Doc getDocById(Integer key) {
		// 
		return docdao.getByKey(key);
	}

	public int batchDelDocByIds(Integer... keys) {
		// 
		return docdao.deleteByKeys(keys);
	}

}
