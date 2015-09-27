package org.tinygroup.sdpm.system.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.system.biz.inter.DictManager;
import org.tinygroup.sdpm.system.dao.SystemDictDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemDict;
import org.tinygroup.tinysqldsl.Pager;

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

	public int[] updateBatch(List<SystemDict> dicts) {
		
		return systemDictDao.batchUpdate(dicts);
	}

	public List<SystemDict> findList(SystemDict dict, String columnName, boolean asc) {
		
		return systemDictDao.query(dict, new OrderBy(columnName, asc));
	}

	public Pager<SystemDict> findPager(int start, int limit, SystemDict dict, String columnName,
			boolean asc) {
		Pager<SystemDict> pagerDict= systemDictDao.queryPager(start, limit, dict,  new OrderBy(columnName, asc));
//		Pager<SystemDict> pager;
		for(int i=0;i<pagerDict.getRecords().size();i++){
			if(pagerDict.getRecords().get(i).getDeleted()==1){
				pagerDict.getRecords().remove(i);
			}
		}
		return pagerDict;
	}


}
