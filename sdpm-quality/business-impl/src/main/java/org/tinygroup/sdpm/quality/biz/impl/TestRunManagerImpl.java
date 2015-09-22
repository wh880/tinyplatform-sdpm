package org.tinygroup.sdpm.quality.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.quality.biz.inter.TestRunManager;
import org.tinygroup.sdpm.quality.dao.TestRunDao;
import org.tinygroup.sdpm.quality.dao.pojo.TestRun;

@Service
@Transactional
public class TestRunManagerImpl implements TestRunManager {

		@Autowired
		private TestRunDao testrundao;
		
		public List<TestRun> findList(TestRun testrun){
			return testrundao.query(testrun);
		}
		
		public int update(TestRun testrun){
			testrun.setTestRunLastRunDate(new Date());
			return testrundao.edit(testrun);
		}
		
		public int[] batchUpdate(List<TestRun> testruns){
			TestRun testrun = new TestRun();
			testrun.setTestRunLastRunDate(new Date());
			return testrundao.batchUpdate(testruns);
		}
}
