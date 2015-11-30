package org.tinygroup.sdpm.quality.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.quality.biz.inter.CaseStepManager;
import org.tinygroup.sdpm.quality.dao.QualityCaseStepDao;
import org.tinygroup.sdpm.quality.dao.pojo.QualityCaseStep;

import java.util.List;

@Service        //注解，告诉spring创建一个实现类的实例
@Transactional  //开启事务
public class CaseStepManagerImpl implements CaseStepManager {
	@Autowired
	private QualityCaseStepDao casestepdao;
	
	public QualityCaseStep find(Integer id){
		return casestepdao.getByKey(id);
	}
	
	public QualityCaseStep add(QualityCaseStep casestep){
		
		return casestepdao.add(casestep);
	}
	
	public Integer delete(Integer id){
		
		return casestepdao.deleteByKeys(id);
	}
	
	public List<QualityCaseStep> findList(QualityCaseStep casestep){
		return casestepdao.query(casestep);
	}

	public int[] batchAdd(List<QualityCaseStep> qualityCaseSteps) {
		return casestepdao.batchInsert(qualityCaseSteps);
	}

	public Integer getMaxVersion(Integer caseId) {
		return casestepdao.getMaxVersion(caseId);
	}
}
