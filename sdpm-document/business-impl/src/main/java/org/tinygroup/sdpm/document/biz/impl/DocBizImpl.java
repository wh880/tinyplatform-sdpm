package org.tinygroup.sdpm.document.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.document.biz.inter.DocBiz;
import org.tinygroup.sdpm.document.dao.DocDao;
import org.tinygroup.sdpm.document.dao.DoclibDao;
import org.tinygroup.sdpm.document.dao.pojo.Doc;
import org.tinygroup.sdpm.document.dao.pojo.Doclib;
import org.tinygroup.tinysqldsl.Pager;

/**
 * 除了ID和传递进来的参数,个别参数需要在业务层处理，没有的添加不必要的过滤掉
 * 这里没有处理的字段（除了ID）传进来实体前都需要存在，否则会造成数据库字段混乱和缺失
 * 以下的方法都一样，字段完整很重要，因为自动生成的持久层并不会做太多的工作。要保证传进来的 该有则有。
 * 凡是编辑的（更新的）数据都需要从数据库里读出创建时间，然后重新写回去， 因为自动生成的数据库持久层没有那么智能，他只会重写实体传进来的所有字段
 * 就是所有的东西重写（除了ID）很笨的！
 * @date 2015/09/21
 * @author alu
 */
@Service
@Transactional
public class DocBizImpl implements DocBiz {
	//
	@Autowired
	private DocDao docdao;
	@Autowired
	private DoclibDao doclibdao;
	

	public Doclib addDocLib(Doclib doclib) {
		// 添加文档库
		doclib.setDocLibAddedDate(new Date());
		doclib.setDocLibEditedDate(new Date());
		doclib.setDocLibDeleted("N");//这个标志是char类型（一个字节）
		return doclibdao.add(doclib);
	}

	public Doc addDoc(Doc doc) {
		// 添加文档
		//if(doc.getDocType()!="file")doc.setAttachUrl(null);
		doc.setDocAddedDate(new Date());
		doc.setDocEditedDate(new Date());
		doc.setDocEditedBy(doc.getDocAddedBy());
		doc.setDocDeleted("N");
		doc.setDocViews(0);
		return docdao.add(doc);	
	}

	public int updtDoc(Doc doc) {
		// 更新（编辑）
		doc.setDocAddedBy(docdao.getByKey(doc.getDocId()).getDocAddedBy());
		doc.setDocAddedDate(docdao.getByKey(doc.getDocId()).getDocAddedDate());
		doc.setDocDeleted(docdao.getByKey(doc.getDocId()).getDocDeleted());
		doc.setDocEditedDate(new Date());
		return docdao.edit(doc);
	}

	public int updtDocLib(Doclib doclib) {
		//更新文档库
		doclib.setDocLibAddedDate(doclibdao.getByKey(doclib.getDocLibId()).getDocLibAddedDate());
		doclib.setDocLibDeleted(doclibdao.getByKey(doclib.getDocLibId()).getDocLibDeleted());
		doclib.setDocLibEditedDate(new Date());
		return doclibdao.edit(doclib);
	}

	public int delDocById(Integer key) {
		// 如果是假动作，那就是关键位标志置为“D”
		//Doc doc = docdao.getByKey(key);
		//doc.setDocDeleted("D");
		//return docdao.edit(doc);
		return docdao.deleteByKey(key);
	}

	public int delDocLibById(Integer key) {
		// 如果是假动作，那就是关键位标志置为“D”
		//DocLib doclib = doclibdao.getByKey(key);
		//doclib.setDocDeleted("D");
		//return doclibdao.edit(doclib);
		return doclibdao.deleteByKey(key);
	}

	public Doc getDocById(Integer key) {
		// 
		return docdao.getByKey(key);
	}

	public List<Doc> getDocList(Doc doc) {
		return docdao.query(doc);
	}
	public List<Doclib> getDoclibList(Doclib doclib) {
		return doclibdao.query(doclib);
	}


	public Doclib getDocLibById(Integer key) {
		// 
		return doclibdao.getByKey(key);
	}

	public Pager<Doc> queryItemWithPage(Integer start, Integer limited, Doc doc) {
		// 分页
		return docdao.queryPager(start, limited, doc);
	}

	public int batchDelDocByIds(Integer... keys) {
		// 
		return docdao.deleteByKeys(keys);
	}

	public Pager<Doclib> queryItemWithPage(Integer start, Integer limited, Doclib doclib) {
		// 分页
		return doclibdao.queryPager(start, limited, doclib);
	}
}
