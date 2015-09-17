package sdpm.docment.service.impl;

import org.tinygroup.sdpm.document.dao.inter.DocDao;
import org.tinygroup.sdpm.document.pojo.Doc;
import org.tinygroup.sdpm.document.pojo.DocLib;

import sdpm.docment.service.DocService;

@Service
public class DocServiceImpl implements DocService{
	//@Autowired
	private DocDao docdao;

	public void addDocLib(DocLib doclib) {
		// TODO Auto-generated method stub
		
	}

	public void addDoc(Doc doc) {
		// TODO Auto-generated method stub
		
	}

	public int updtDoc(Doc doc) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updtDocLib(DocLib doclib) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delDoc(Integer key) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delDocLib(Integer key) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Doc getDocById(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	public int batchDelDocByIds(Integer... keys) {
		// TODO Auto-generated method stub
		return 0;
	}

}
