package org.tinygroup.sdpm.system.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.system.biz.inter.HistoryManager;
import org.tinygroup.sdpm.system.dao.HistoryDao;
import org.tinygroup.sdpm.system.dao.pojo.History;
@Service
@Transactional
public class HistoryManagerImpl implements HistoryManager {
	@Autowired
	private HistoryDao histroyDao;

	public History add(History history) {
		// TODO Auto-generated method stub
		return histroyDao.add(history);
	}

	public History updata(History history) {
		// TODO Auto-generated method stub
		histroyDao.edit(history);
		return history;
	}

	public Integer delete(History history) {
		// TODO Auto-generated method stub
		int pk=history.getHistoryId();
		return histroyDao.deleteByKey(pk);
	}

	public List<History> find(History history) {
		// TODO Auto-generated method stub
		return histroyDao.query(history);
	}
	

}
