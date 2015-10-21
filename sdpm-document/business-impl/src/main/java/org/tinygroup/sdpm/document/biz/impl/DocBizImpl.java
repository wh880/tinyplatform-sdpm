package org.tinygroup.sdpm.document.biz.impl;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.file.FileDealUtil;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.ComplexSearch.SqlUtil;
import org.tinygroup.sdpm.document.biz.inter.DocBiz;
import org.tinygroup.sdpm.document.dao.DocumentDocDao;
import org.tinygroup.sdpm.document.dao.DocumentDoclibDao;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoc;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoclib;
import org.tinygroup.tinysqldsl.Pager;

import com.thoughtworks.xstream.mapper.Mapper.Null;

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
	private DocumentDocDao docdao;
	@Autowired
	private DocumentDoclibDao doclibdao;
	

	public DocumentDoclib addDocLib(DocumentDoclib doclib) {
		// 添加文档库
		doclib.setDocLibAddedDate(new Date());
		doclib.setDocLibEditedDate(new Date());
	
		return doclibdao.add(doclib);
	}

	public DocumentDoc addDoc(DocumentDoc doc) {
		// 添加文档
		//if(doc.getDocType()!="file")doc.setAttachUrl(null);
		doc.setDocAddedDate(new Date());
		doc.setDocEditedDate(new Date());
		doc.setDocEditedBy(doc.getDocAddedBy());
	
		doc.setDocViews(0);
		return docdao.add(doc);	
	}

	public int updtDoc(DocumentDoc doc) {
		// 更新（编辑）
		doc.setDocAddedBy(docdao.getByKey(doc.getDocId()).getDocAddedBy());
		doc.setDocAddedDate(docdao.getByKey(doc.getDocId()).getDocAddedDate());
		doc.setDocDeleted(docdao.getByKey(doc.getDocId()).getDocDeleted());
		doc.setDocEditedDate(new Date());
		return docdao.edit(doc);
	}

	public int updtDocLib(DocumentDoclib doclib) {
		//更新文档库
		doclib.setDocLibAddedDate(doclibdao.getByKey(doclib.getDocLibId()).getDocLibAddedDate());
		doclib.setDocLibDeleted(doclibdao.getByKey(doclib.getDocLibId()).getDocLibDeleted());
		doclib.setDocLibEditedDate(new Date());
		return doclibdao.edit(doclib);
	}

	public int delDocById(Integer key) {
		DocumentDoc doc = docdao.getByKey(key);
		doc.setDocDeleted(DocumentDoc.DELETE_YES);
		return docdao.edit(doc);
	}

	public int delDocLibById(Integer key) {
		DocumentDoclib doclib = doclibdao.getByKey(key);
		doclib.setDocLibDeleted(DocumentDoclib.DELETE_YES);
		return doclibdao.edit(doclib);
	}

	public DocumentDoc getDocById(Integer key) {
		// 
		return docdao.getByKey(key);
	}

	public List<DocumentDoc> getDocList(DocumentDoc doc) {
		return docdao.query(doc);
	}
	public List<DocumentDoclib> getDoclibList(DocumentDoclib doclib) {
		return doclibdao.query(doclib);
	}


	public DocumentDoclib getDocLibById(Integer key) {
		// 
		return doclibdao.getByKey(key);
	}

	public Pager<DocumentDoc> queryItemWithPage(Integer start,Integer limit,DocumentDoc doc, String statusCondition,SearchInfos conditions,
			String groupOperate, String sortName,boolean asc) {
		// 分页
		String condition = conditions != null ? SqlUtil.toSql(conditions.getInfos(), groupOperate) : "";
		condition = condition != null && !"".equals(condition) ? (statusCondition != null&& !"".equals(statusCondition) ? condition + " and "
				+ statusCondition : condition)
				: statusCondition;
		OrderBy orderBy = null;
		if (sortName != null && !"".equals(sortName)) {
			orderBy = new OrderBy(sortName, asc);
		}
		if (condition != null && !"".equals(condition)) {
			return docdao.complexQuery(start, limit, doc, condition,
					orderBy);
		}
		return docdao.complexQuery(start, limit, doc, null ,orderBy);
	}

	public int[] batchDelDocByIds(List<DocumentDoc> keys) {
		// 
		return docdao.batchUpdateDel(keys);
	}

	public Pager<DocumentDoclib> queryItemWithPage(Integer start, Integer limit,DocumentDoclib doclib,String sortName, boolean asc) {
		// 分页
		if(StringUtil.isBlank(sortName)){
			return doclibdao.queryPager(start, limit, doclib);
		}else{
			OrderBy orderby = new OrderBy(sortName, asc);
			return doclibdao.queryPager(start, limit, doclib , orderby);
		}
	}

}
