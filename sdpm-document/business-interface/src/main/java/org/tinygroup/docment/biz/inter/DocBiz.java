package org.tinygroup.docment.biz.inter;

import java.util.List;

import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.document.dao.pojo.Doc;
import org.tinygroup.sdpm.document.dao.pojo.DocLib;
import org.tinygroup.sdpm.document.dao.pojo.Historydoc;
import org.tinygroup.tinysqldsl.Pager;


public interface DocBiz {
	//以下接口都非常容易看懂，即使没有备注。批量添加和更新没有实现，因为不需要。
	public DocLib addDocLib(DocLib doclib);
	public Doc addDoc(Doc doc);
	public int updtDoc(Doc doc);
	public int updtDocLib(DocLib doclib);
	public int delDocById(Integer key);
	//public int delDocByIds(Integer...keys);
	public int batchDelDocByIds(Integer...keys);
	public int delDocLibById(Integer key);
	//public int delDocLibByIds(Integer...keys);
	public Doc getDocById(Integer key);
	public DocLib getDocLibById(Integer key);
	//这个基本不用，或是用来判断更新是否有改动，如果一字都没改动就不操作数据库。
	public List<Doc> getDocListOrderBy(Doc doc,OrderBy...orderbies);
	public List<Historydoc> docHistory(Integer docid);
	//查询后分页吧~
	public Pager<Doc> queryItemWithPage(Integer start,Integer limited,Doc doc,OrderBy...orderbies);

}
