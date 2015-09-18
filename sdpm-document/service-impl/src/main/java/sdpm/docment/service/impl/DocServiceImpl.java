package sdpm.docment.service.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.docment.biz.inter.DocBiz;
import org.tinygroup.sdpm.document.pojo.Doc;
import org.tinygroup.sdpm.document.pojo.DocLib;
import org.tinygroup.tinysqldsl.Pager;

import sdpm.docment.service.DocService;

/**
 * @author alu
 */

@Service
public class DocServiceImpl implements DocService {
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

	public int updtDoc(Doc doc) {
		// 
		return docbiz.updtDoc(doc);
	}

	public int updtDocLib(DocLib doclib) {
		// 
		return docbiz.updtDocLib(doclib);
	}

	public Doc findById(BigInteger id) {
		// 
		return docbiz.getDocById(id);
	}

	public DocLib findDoclibById(Integer id) {
		// 
		return docbiz.getDocLibById(id);
	}

	public List<Doc> findByIds(BigInteger... keys) {
		// 
		return null;
	}

	public List<Doc> findByIds(Doc doc) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pager<Doc> findWithPager(int start, int limit, Doc doc) {
		// TODO Auto-generated method stub
		return null;
	}

	public int deleteDocById(BigInteger id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteDocByIds(BigInteger... keys) {
		// TODO Auto-generated method stub
		return 0;
	}

}
