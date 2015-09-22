package org.tinygroup.sdpm.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.system.biz.inter.HistoryManager;
import org.tinygroup.sdpm.system.dao.pojo.History;
import org.tinygroup.sdpm.system.service.inter.HistoryService;
@Component
public class HistoryServiceImpl implements HistoryService {
	@Autowired
	private HistoryManager historyManager;
	public History add(History history) {
		// TODO Auto-generated method stub
		return historyManager.add(history);
	}

	public History updata(History history) {
		// TODO Auto-generated method stub
		return historyManager.updata(history);
	}

	public Integer delete(History history) {
		// TODO Auto-generated method stub
		return historyManager.delete(history);
	}

	public List<History> find(History history) {
		// TODO Auto-generated method stub
		return historyManager.find(history);
	}

}
