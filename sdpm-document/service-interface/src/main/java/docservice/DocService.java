package docservice;

import java.util.List;


public interface DocService {
	public void addDocLib();
	public void addDoc();
	public void updtDoc();
	public void updtDocLib();
	public void delDoc();
	public void delDocLib();
	public <T> List<T> queryDocById(Integer id);

}
