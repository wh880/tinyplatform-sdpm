package org.tinygroup.sdpm.quality.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.quality.biz.inter.TestRunManager;
import org.tinygroup.sdpm.quality.dao.QualityTestRunDao;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun;

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
}
