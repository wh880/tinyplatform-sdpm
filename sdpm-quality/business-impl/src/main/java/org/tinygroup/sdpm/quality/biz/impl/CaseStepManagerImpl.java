package org.tinygroup.sdpm.quality.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.quality.biz.inter.CaseStepManager;
import org.tinygroup.sdpm.quality.dao.CaseStepDao;
import org.tinygroup.sdpm.quality.dao.pojo.CaseStep;

@Service        //注解，告诉spring创建一个实现类的实例
@Transactional  //开启事务
public class CaseStepManagerImpl implements CaseStepManager {
	@Autowired
	private CaseStepDao casestepdao;
	
	public CaseStep find(int id){
		return casestepdao.getByKey(id);
	}
	
	public CaseStep add(CaseStep casestep){
		
		return casestepdao.add(casestep);
	}
	
	public int delete(int id){
		
		return casestepdao.deleteByKeys(id);
	}
	
	public List<CaseStep> findList(CaseStep casestep){
		return casestepdao.query(casestep);
	}
}
