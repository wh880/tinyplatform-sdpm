package org.tinygroup.sdpm.document.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.document.dao.pojo.DocumentDoc;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoclib;
import org.tinygroup.tinysqldsl.Pager;


public interface DocBiz {
	//以下接口都非常容易看懂，即使没有备注。
	public DocumentDoclib addDocLib(DocumentDoclib doclib);
	public DocumentDoc addDoc(DocumentDoc doc);
	public int updtDoc(DocumentDoc doc);
	public int updtDocLib(DocumentDoclib doclib);
	public int delDocById(Integer key);
	public int delDocLibById(Integer key);
	public DocumentDoc getDocById(Integer key);
	//这个基本不用，或是用来判断更新是否有改动，如果一字都没改动就不操作数据库。
	public List<DocumentDoc> getDocList(DocumentDoc doc);
	public List<DocumentDoclib> getDoclibList(DocumentDoclib doclib);
	public DocumentDoclib getDocLibById(Integer key);
	//查询后分页吧~//可以排序OrderBy id asc
	public Pager<DocumentDoc> queryItemWithPage(Integer start,Integer limit,DocumentDoc doc,String sortName,boolean asc);
	public Pager<DocumentDoclib> queryItemWithPage(Integer start, Integer limit,DocumentDoclib doclib,String sortName, boolean asc);
	public int batchDelDocByIds(Integer...keys);

}
