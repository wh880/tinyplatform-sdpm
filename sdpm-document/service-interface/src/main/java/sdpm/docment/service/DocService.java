package sdpm.docment.service;

import javax.print.Doc;

public interface DocService {
	public void addDocLib(Object doclib);
	public void addDoc(Object doc);
	public int updtDoc(Object doc);
	public int updtDocLib(Object doclib);
	public int delDoc(Integer key);
	public int delDocLib(Integer key);
	public Doc getDocById(Integer key);
	//public <T> List<T> queryDocById(Integer key);
	//public <T> List<T> queryItemWithPage(Integer start,Integer limited,Object T);
	public int batchDelDocByIds(Integer...keys);

}
