package org.tinygroup.sdpm.system.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.system.biz.inter.HistoryManager;
import org.tinygroup.sdpm.system.dao.SystemHistoryDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemHistory;
@Service
@Transactional
public class HistoryManagerImpl implements HistoryManager {
	@Autowired
	private SystemHistoryDao histroyDao;

	public SystemHistory add(SystemHistory systemHistory) {
		// TODO Auto-generated method stub
		return histroyDao.add(systemHistory);
	}

	public SystemHistory updata(SystemHistory systemHistory) {
		// TODO Auto-generated method stub
		histroyDao.edit(systemHistory);
		return systemHistory;
	}

	public Integer delete(SystemHistory systemHistory) {
		// TODO Auto-generated method stub
		int pk=systemHistory.getHistoryId();
		return histroyDao.deleteByKey(pk);
	}

	public List<SystemHistory> find(SystemHistory systemHistory) {
		// TODO Auto-generated method stub
		return histroyDao.query(systemHistory);
	}
	

}
