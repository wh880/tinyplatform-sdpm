package org.tinygroup.sdpm.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.system.biz.inter.HistoryManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemHistory;
import org.tinygroup.sdpm.system.service.inter.HistoryService;
@Component
public class HistoryServiceImpl implements HistoryService {
	@Autowired
	private HistoryManager historyManager;
	public SystemHistory add(SystemHistory systemHistory) {
		// TODO Auto-generated method stub
		return historyManager.add(systemHistory);
	}

	public SystemHistory updata(SystemHistory systemHistory) {
		// TODO Auto-generated method stub
		return historyManager.updata(systemHistory);
	}

	public Integer delete(SystemHistory systemHistory) {
		// TODO Auto-generated method stub
		return historyManager.delete(systemHistory);
	}

	public List<SystemHistory> find(SystemHistory systemHistory) {
		// TODO Auto-generated method stub
		return historyManager.find(systemHistory);
	}

}
