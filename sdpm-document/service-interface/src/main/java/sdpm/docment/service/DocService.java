package sdpm.docment.service;

import java.math.BigInteger;
import java.util.List;

import org.tinygroup.sdpm.document.pojo.Doc;
import org.tinygroup.sdpm.document.pojo.DocLib;
import org.tinygroup.tinysqldsl.Pager;


public interface DocService {
	public Doc createNewDoc(Doc doc);
	public DocLib createNewDocLib(DocLib doclib);
	public int updtDoc(Doc doc);
	public int updtDocLib(DocLib doclib);
	public Doc findById(BigInteger id);
	public DocLib findDoclibById(Integer id);
	public List<Doc> findByIds(BigInteger...keys);
	public List<Doc> findByIds(Doc doc);
	public Pager<Doc> findWithPager(int start,int limit ,Doc doc);
	public int deleteDocById(BigInteger id);
	public int deleteDocByIds(BigInteger... keys);
	

}
