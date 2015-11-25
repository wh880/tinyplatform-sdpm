package org.tinygroup.sdpm.productLine.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.org.dao.OrgRoleUserDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleUser;
import org.tinygroup.sdpm.productLine.biz.inter.ProductLineManager;
import org.tinygroup.sdpm.productLine.dao.ProductLineDao;
import org.tinygroup.sdpm.productLine.dao.impl.FieldUtil;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.base.FragmentSql;


@Service
@Transactional
public class ProductLineManagerImpl implements ProductLineManager{
	
	@Autowired
	private ProductLineDao productLineDao;
	@Autowired
	private OrgRoleUserDao orgRoleUserDao;
	
	public ProductLine add(ProductLine productLine) {

		return productLineDao.add(productLine);
	}

	public int update(ProductLine productLine) {

		return productLineDao.edit(productLine);
	}

	public ProductLine find(Integer productLineId) {

		return productLineDao.getByKey(productLineId);
	}

	public int delete(Integer productLineId) {
		
		return productLineDao.softDelete(productLineId);
	}

	public List<ProductLine> findlist(ProductLine productLine, String order, String ordertype) {
		if(StringUtil.isBlank(order)){
			return productLineDao.query(productLine);
		}
		return productLineDao.query(productLine,  new OrderBy(NameUtil.resolveNameDesc(order), !("desc".equals(ordertype))?true:false));
	}

	public Pager<ProductLine> findPager(int start, int pagesize,String condition, ProductLine productLine, String order,
			String ordertype) {
		Condition condition1 = null;
		if(!StringUtil.isBlank(condition)){
			condition1 = FragmentSql.fragmentCondition(condition);
		}
		if(!StringUtil.isBlank(order)) {
			return productLineDao.findList(start, pagesize, condition1, productLine,
					new OrderBy("product_line." + NameUtil.resolveNameDesc(order), !("desc".equals(ordertype)) ? true : false));
		}
		return productLineDao.findList(start, pagesize,condition1, productLine);
	}

	public Pager<ProductLine> findProductLinePagerInIds(int start, int limit, String condition, ProductLine productLine, Integer[] ids,String order,String ordertype) {
		if(ids==null||ids.length==0)return new Pager<ProductLine>(0,0,new ArrayList<ProductLine>());
		Condition condition1 = null;
		if(!StringUtil.isBlank(condition)){
			condition1 = FragmentSql.fragmentCondition(condition);
		}
		if(!StringUtil.isBlank(order)) {
			return productLineDao.findList(start, limit, condition1, productLine,ids,
					new OrderBy("product_line." + NameUtil.resolveNameDesc(order), !("desc".equals(ordertype)) ? true : false));
		}
		return productLineDao.findList(start, limit,condition1, productLine,ids);
	}

	public List<ProductLine> getProductLineByIds(Integer... ids) {
		if(ids==null||ids.length==0){
			return new ArrayList<ProductLine>();
		}
		return productLineDao.getByKeys(ids);
	}

	public List<ProductLine> getUserProductLine(String userId) {
		List<ProductLine> productLines = productLineDao.getUserProductLines(userId);

		ProductLine productLine = new ProductLine();
		productLine.setDeleted(0);
		productLine.setAcl(ProductLine.ACl_TEAM_AND_ROLE);
		List<ProductLine> productLineList = productLineDao.query(productLine);
		OrgRoleUser role = new OrgRoleUser();
		role.setOrgUserId(userId);
		List<OrgRoleUser> orgRoles = orgRoleUserDao.query(role);
		return mergeUserProductLines(productLines,productLineList,orgRoles);
	}

	public int[] updateBatch(List<ProductLine> productLine) {
		
		return productLineDao.batchUpdate(productLine);
	}

	public List<ProductLine> findList(ProductLine productLine) {
	
		return productLineDao.query(productLine);
	}

	private List<ProductLine> mergeUserProductLines(List<ProductLine> productLineWithOutRole,List<ProductLine> productLineWithRole,List<OrgRoleUser> roleUsers){
		if(productLineWithRole.size()==0)return productLineWithOutRole;
		for(OrgRoleUser orgRoleUser : roleUsers) {
			for (ProductLine productLine:productLineWithRole) {
				String whiteList = productLine.getProductLineWhiteList();
				if (whiteList != null){
					String[] ids = whiteList.split(",");
					List<String> idList = Arrays.asList(ids);
					if(idList.contains(String.valueOf(orgRoleUser.getOrgRoleId()))&&!productLineWithOutRole.contains(productLine)){
						productLineWithOutRole.add(productLine);
					}
				}
			}
		}
		return productLineWithOutRole;
	}

	

	

}
