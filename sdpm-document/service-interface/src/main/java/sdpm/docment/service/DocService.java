package sdpm.docment.service;

import org.tinygroup.sdpm.document.pojo.Doc;
import org.tinygroup.sdpm.document.pojo.DocLib;


public interface DocService {
	public void addDocLib(DocLib doclib);
	public void addDoc(Doc doc);
	public int updtDoc(Doc doc);
	public int updtDocLib(DocLib doclib);
	public int delDoc(Integer key);
	public int delDocLib(Integer key);
	public Doc getDocById(Integer key);
	//public <T> List<T> queryDocById(Integer key);
	//public <T> List<T> queryItemWithPage(Integer start,Integer limited,Object T);
	public int batchDelDocByIds(Integer...keys);

}
