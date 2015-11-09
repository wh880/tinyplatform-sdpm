package org.tinygroup.sdpm.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.quality.biz.inter.CaseStepManager;
import org.tinygroup.sdpm.quality.dao.pojo.QualityCaseStep;
import org.tinygroup.sdpm.quality.service.inter.CaseStepService;

@Component
public class CaseStepServiceImpl implements CaseStepService {

	@Autowired
	private CaseStepManager casestepmanager;
	
	public QualityCaseStep findById(int id){
		return casestepmanager.find(id);
	}
	
	public QualityCaseStep addCaseStep(QualityCaseStep casestep){
		
		return casestepmanager.add(casestep);
	}
	
	public int deleteById(int id){
		return casestepmanager.delete(id);
	}
	
	public List<QualityCaseStep> findCaseStepList(QualityCaseStep casestep){
		return casestepmanager.findList(casestep);
	}

	public int[] batchAdd(List<QualityCaseStep> qualityCaseSteps) {
		return casestepmanager.batchAdd(qualityCaseSteps);
	}

	public Integer getMaxVersion(Integer caseId) {
		return casestepmanager.getMaxVersion(caseId);
	}
}
