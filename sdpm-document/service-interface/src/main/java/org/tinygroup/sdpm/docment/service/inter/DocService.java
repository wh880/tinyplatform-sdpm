package org.tinygroup.sdpm.docment.service.inter;

import java.util.List;

import org.tinygroup.sdpm.docment.pojo.Doc;
import org.tinygroup.sdpm.docment.pojo.DocLib;
import org.tinygroup.sdpm.docment.pojo.Historydoc;
import org.tinygroup.tinysqldsl.Pager;
/**
 * @date 2015/09/21
 * @author Alu
 *
 */

public interface DocService {
	//Create a new document.如果我的biz层没有把historydoc集合到一起，那么service就可以传递2参数，
	//比如：(Doc doc,History his)
	public Doc createNewDoc(Doc doc);
	//Create a new document library-just a name.
	public DocLib createNewDocLib(DocLib doclib);
	//Edit document
	public int editDoc(Doc doc);
	//Edit document library name.
	public int editDocLibName(DocLib doclib);
	//find
	public Doc findDocById(Integer id);
	//文档操作记录
	public List<Historydoc> getEditRecord(Integer docid);
	public DocLib findDoclibById(Integer id);
	//list
	public List<Doc> findDocByDocClass(Doc doc);
	//page
	public Pager<Doc> findDocRetPager(int start,int limit ,Doc doc);
	//delete
	public int deleteDocById(Integer id);
	public int deleteDoclibById(Integer id);
	public int deleteDocByIds(Integer... keys);
	

}
