package org.tinygroup.docment.biz.inter;

import java.math.BigInteger;

import org.tinygroup.sdpm.document.pojo.Doc;
import org.tinygroup.sdpm.document.pojo.DocLib;
import org.tinygroup.tinysqldsl.Pager;


public interface DocBiz {
	public DocLib addDocLib(DocLib doclib);
	public Doc addDoc(Doc doc);
	public int updtDoc(Doc doc);
	public int updtDocLib(DocLib doclib);
	public int delDoc(BigInteger key);
	public int delDocLib(Integer key);
	public Doc getDocById(BigInteger key);
	public DocLib getDocLibById(Integer key);
	public Pager<Doc> queryItemWithPage(Integer start,Integer limited,Doc doc);
	public int batchDelDocByIds(BigInteger...keys);

}
