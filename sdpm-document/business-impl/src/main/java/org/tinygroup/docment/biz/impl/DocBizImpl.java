package org.tinygroup.docment.biz.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.tinygroup.docment.biz.inter.DocBiz;
import org.tinygroup.sdpm.document.pojo.Doc;
import org.tinygroup.sdpm.document.pojo.DocLib;
import org.tinygroup.tinysqldsl.Pager;

/**
 * 除了ID和传递进来的参数,个别参数需要在业务层处理，没有的添加不必要的过滤掉
 * 这里没有处理的字段（除了ID）传进来实体前都需要存在，否则会造成数据库字段混乱和缺失
 * 以下的方法都一样，字段完整很重要，因为自动生成的持久层并不会做太多的工作。要保证传进来的 该有则有。
 * 凡是编辑的（更新的）数据都需要从数据库里读出创建时间，然后重新写回去， 因为自动生成的数据库持久层没有那么智能，他只会重写实体传进来的所有字段
 * 就是所有的东西重写（除了ID）很笨的！
 * 
 * @author alu
 */
public class DocBizImpl implements DocBiz {

	public DocLib addDocLib(DocLib doclib) {
		// 
		return null;
	}

	public Doc addDoc(Doc doc) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updtDoc(Doc doc) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updtDocLib(DocLib doclib) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delDoc(BigInteger key) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delDocLib(Integer key) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Doc getDocById(BigInteger key) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pager<Doc> queryItemWithPage(Integer start, Integer limited, Doc doc) {
		// TODO Auto-generated method stub
		return null;
	}

	public int batchDelDocByIds(BigInteger... keys) {
		// TODO Auto-generated method stub
		return 0;
	}

	public DocLib getDocLibById(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

}
