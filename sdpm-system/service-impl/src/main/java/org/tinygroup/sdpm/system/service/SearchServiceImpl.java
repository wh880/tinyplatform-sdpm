package org.tinygroup.sdpm.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.system.biz.inter.SearchManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemSearch;

@Component("searchService")
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	private SearchManager searchManager;
	
	public SystemSearch addSearch(SystemSearch search) {
		
		return searchManager.add(search);
	}

	public int deleteSearch(Integer searchId) {
		
		return searchManager.delete(searchId);
	}

	public int updateSearch(SystemSearch search) {
		
		return searchManager.update(search);
	}

	public SystemSearch findSearch(Integer searchId) {
	
		return searchManager.find(searchId);
	}

	public List<SystemSearch> findSearchList(SystemSearch search) {
		
		return searchManager.finList(search);
			
	}


}
