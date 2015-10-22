package org.tinygroup.sdpm.quality.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.quality.biz.inter.TestRunManager;
import org.tinygroup.sdpm.quality.dao.QualityTestRunDao;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.base.FragmentSql;

@Service
@Transactional
public class TestRunManagerImpl implements TestRunManager {

		@Autowired
		private QualityTestRunDao testrundao;
		
		public List<QualityTestRun> findList(QualityTestRun testrun){
			return testrundao.query(testrun);
		}
		
		public int update(QualityTestRun testrun){
			testrun.setTestRunLastRunDate(new Date());
			return testrundao.edit(testrun);
		}
		
		public int[] batchUpdate(List<QualityTestRun> testruns){
			QualityTestRun testrun = new QualityTestRun();
			testrun.setTestRunLastRunDate(new Date());
			return testrundao.batchUpdate(testruns);
		}

	public QualityTestRun add(QualityTestRun run) {
		return testrundao.add(run);
	}

	public Pager<QualityTestRun> findPager(Integer start, Integer limit, QualityTestRun testRun,String condition, String sortName, boolean asc) {
		Condition condition1 = StringUtil.isBlank(condition)?null: FragmentSql.fragmentCondition(condition);
		if(StringUtil.isBlank(sortName)){
			return testrundao.queryPager(start,limit,testRun,condition1);
		}
		return testrundao.queryPager(start,limit,testRun,condition1,new OrderBy(sortName,asc));
	}
}
