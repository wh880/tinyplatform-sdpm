package org.tinygroup.sdpm.quality.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.ComplexSearch.SqlUtil;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.quality.biz.inter.TestCaseManager;
import org.tinygroup.sdpm.quality.dao.QualityTestCaseDao;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;
import org.tinygroup.tinysqldsl.Pager;

/**
 * Created by chenpeng15668 on 2015-9-24
 */
@Service
@Transactional
public class TestCaseManagerImpl implements TestCaseManager {
	
	@Autowired
	private QualityTestCaseDao testcasedao;
	
	public List<QualityTestCase> findList(QualityTestCase testcase){
		return testcasedao.query(testcase);
	}
	
	public QualityTestCase find(Integer id){
		return testcasedao.getByKey(id);
	}
	
	public QualityTestCase add(QualityTestCase testcase){
		testcase.setCaseScriptedDate(new Date());
		return testcasedao.add(testcase);
	}
	
	public Integer update(QualityTestCase testcase){
		testcase.setCaseLastEditedDate(new Date());
		return testcasedao.edit(testcase);
	}
	
	public int[] batchUpdate(List<QualityTestCase> testcases){
		QualityTestCase testcase = new QualityTestCase();
		testcase.setCaseLastEditedDate(new Date());
		return testcasedao.batchUpdate(testcases);
	}
	
	public Integer delete(Integer id){
		QualityTestCase testcase = new QualityTestCase();
		testcase.setCaseId(id);
		testcase.setDeleted(QualityTestCase.DELETE_YES);
		return testcasedao.edit(testcase);
	}
	
	public int[] batchDelete(List<QualityTestCase> testcases){
		for(QualityTestCase testCase:testcases){
			testCase.setDeleted(QualityTestCase.DELETE_YES);
		}
		return testcasedao.batchUpdate(testcases);
	}
	
	public Pager<QualityTestCase> findPager(Integer start,Integer limit,QualityTestCase testcase,String sortName,boolean asc){
		if(StringUtil.isBlank(sortName)){
			return testcasedao.queryPager(start, limit, testcase);
		}
		OrderBy order = new OrderBy(NameUtil.resolveNameDesc(sortName), asc);
		return testcasedao.queryPager(start, limit, testcase, order);
	}
	
	public Pager<QualityTestCase> findPagerRel(Integer start,Integer limit,QualityTestCase testcase, String statusCondition, SearchInfos conditions,
            String groupOperate,String columnName,boolean asc){
		
        String condition = conditions != null ? SqlUtil.toSql(conditions.getInfos(), groupOperate) : "";
        condition = condition != null && !"".equals(condition) ? (statusCondition != null && !"".equals(statusCondition) ? condition + " and "
                + statusCondition : condition)
                : statusCondition;
        OrderBy orderBy = null;
        if (columnName != null && !"".equals(columnName)) {
            orderBy = new OrderBy("quality_test_case."+NameUtil.resolveNameDesc(columnName), asc);
        }
        if (condition != null && !"".equals(condition)) {
            return testcasedao.queryPagerRel(start, limit, testcase, condition, orderBy);
        }
        return testcasedao.queryPager(start, limit, testcase, orderBy);
    }
}
