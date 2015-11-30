package org.tinygroup.sdpm.quality.biz.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.condition.CallBackFunction;
import org.tinygroup.sdpm.common.condition.ConditionCarrier;
import org.tinygroup.sdpm.common.condition.ConditionUtils;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.ComplexSearch.SqlUtil;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.quality.biz.inter.BugManager;
import org.tinygroup.sdpm.quality.dao.QualityBugDao;
import org.tinygroup.sdpm.quality.dao.pojo.BugCount;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.system.dao.impl.util.ModuleUtil;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.base.FragmentSql;
import org.tinygroup.tinysqldsl.base.StatementSqlBuilder;
import org.tinygroup.tinysqldsl.expression.FragmentExpressionSql;

@Service        
@Transactional  
public class BugManagerImpl implements BugManager {
	@Autowired
	private QualityBugDao bugdao;
	
	public List<QualityBug> findList(QualityBug bug){			
		return bugdao.query(bug);
	}
	
	public QualityBug add(QualityBug bug){
		Integer no = bugdao.getMaxNo(bug.getProductId());
		bug.setNo(no==null?1:no+1);
		return bugdao.add(bug);
	}
	
	public QualityBug find(Integer id){
		return bugdao.getByKey(id);
	}
	
	public Integer update(QualityBug bug){
		
		return bugdao.edit(bug);
	}
	
	public int[] batchUpdate(List<QualityBug> bugs){
		
		return bugdao.batchUpdate(bugs);
	}
	
	public Pager<QualityBug> findPager(Integer start,Integer limit,String conditions,QualityBug bug,String sortName,boolean asc){
		if(StringUtil.isBlank(sortName)){
			return bugdao.queryPager(start,limit,(((conditions!=null&&!"".equals(conditions))? FragmentExpressionSql.fragmentCondition(conditions):null)),bug);
		}else{
			OrderBy orderby = new OrderBy(NameUtil.resolveNameDesc(sortName),asc);
			return bugdao.queryPager(start, limit, (((conditions!=null&&!"".equals(conditions))?FragmentExpressionSql.fragmentCondition(conditions):null)), bug, orderby);
		}		
	}

	public Integer delete(Integer id) {
		
		return bugdao.softDelete(id);
	}

	public Map<String, List<BugCount>> report(String code, Integer productId) {
		if(StringUtil.isBlank(code))return new HashMap<String, List<BugCount>>();
		String[] codes = code.split(",");
		if(codes!=null&&codes.length>0){
			Map<String,List<BugCount>> bugCountMap = new HashMap<String, List<BugCount>>();
			for(String countCode : codes){
				String[] sqlCodes = countCode.split("_");
				List<BugCount> result = bugdao.getCount(sqlCodes[0],productId);
				bugCountMap.put(countCode,result);
			}
			return bugCountMap;
		}
		return new HashMap<String, List<BugCount>>();
	}

	public Pager<QualityBug> queryStoryChangedBugs(Integer start, Integer limit, ConditionCarrier carrier, QualityBug bug, String sortName, boolean asc) {
		if(StringUtil.isBlank(sortName)){
			return bugdao.queryStoryChangedBugs(start,limit,mergeCondition(carrier),bug);
		}
		return bugdao.queryStoryChangedBugs(start,limit,mergeCondition(carrier),bug,new OrderBy(NameUtil.resolveNameDesc("qualityBug."+sortName),asc));
	}

	public Pager<QualityBug> findBugListPager(Integer start, Integer limit, ConditionCarrier carrier, QualityBug bug, String sortName, boolean asc) {
		if(StringUtil.isBlank(sortName)){
			return bugdao.queryPager(start,limit,mergeCondition(carrier),bug);
		}else{
			OrderBy orderby = new OrderBy(NameUtil.resolveNameDesc(sortName),asc);
			return bugdao.queryPager(start, limit, mergeCondition(carrier), bug, orderby);
		}
	}

	public int[] batchDelete(List<QualityBug> bugIds) {
		
		return bugdao.batchUpdateDel(bugIds);
	}

	private Condition mergeCondition(ConditionCarrier carrier){
		return ConditionUtils.mergeCondition(carrier, new CallBackFunction() {
			public Condition process(ConditionCarrier carrier, String field, Column column) {
				Condition condition = null;
				boolean isIn = carrier.getOperate(field).equals(ConditionUtils.Operate.IN.getOperate());
				String moduleId = (String) carrier.getValue(field)[0];
				if(moduleId.contains("p")){
					if(isIn){
						condition = column.in(ModuleUtil.getConditionByRootWithoutOperate(Integer.parseInt(moduleId.substring(1)),"bug"));
					}else{
						condition = column.notIn(ModuleUtil.getConditionByRootWithoutOperate(Integer.parseInt(moduleId.substring(1)),"bug"));
					}
				}else{
					if(isIn){
						condition = column.in(ModuleUtil.getConditionWithoutOperate(Integer.parseInt(moduleId)));
					}else{
						condition = column.notIn(ModuleUtil.getConditionWithoutOperate(Integer.parseInt(moduleId)));
					}
				}
				return condition;
			}
		});
	}
}
