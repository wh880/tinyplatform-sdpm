package org.tinygroup.sdpm.system.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.system.biz.inter.DictManager;
import org.tinygroup.sdpm.system.dao.SystemDictDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemDict;

@Service
@Transactional
public class DictManagerImpl implements DictManager {
	
	@Autowired
	private SystemDictDao systemDictDao;
	
	public SystemDict add(SystemDict dict) {

		return systemDictDao.add(dict);
	}

	public int delete(Integer dictId) {

		SystemDict systemDict = new SystemDict();
		systemDict.setDictId(dictId);
		systemDict.setDeleted(SystemDict.DELETE_YES);
		return systemDictDao.edit(systemDict);
	}

	public int update(SystemDict dict) {

		return systemDictDao.edit(dict);
	}

	public SystemDict find(Integer dictId) {

		return systemDictDao.getByKey(dictId);
	}

	public List<SystemDict> findList(SystemDict dict) {

		return systemDictDao.query(dict);
	}

}
