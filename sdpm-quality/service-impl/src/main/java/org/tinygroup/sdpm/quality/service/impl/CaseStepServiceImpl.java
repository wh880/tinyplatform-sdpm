package org.tinygroup.sdpm.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.quality.biz.inter.CaseStepManager;
import org.tinygroup.sdpm.quality.dao.pojo.CaseStep;
import org.tinygroup.sdpm.quality.service.inter.CaseStepService;

@Component
public class CaseStepServiceImpl implements CaseStepService {

	@Autowired
	private CaseStepManager casestepmanager;
	
	public CaseStep findById(int id){
		return casestepmanager.find(id);
	}
	
	public CaseStep addCaseStep(CaseStep casestep){
		
		return casestepmanager.add(casestep);
	}
	
	public int deleteById(int id){
		return casestepmanager.delete(id);
	}
	
	public List<CaseStep> findCaseStepList(CaseStep casestep){
		return casestepmanager.findList(casestep);
	}
}
