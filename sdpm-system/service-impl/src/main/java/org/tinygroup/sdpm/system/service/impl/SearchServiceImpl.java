package org.tinygroup.sdpm.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.system.biz.inter.SearchManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemSearch;
import org.tinygroup.sdpm.system.service.inter.SearchService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

@Component
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

	public List<SystemSearch> findSearchList(SystemSearch search,String columnName,boolean asc) {
		
		return searchManager.findList(search, columnName, asc);
			
	}

	public Pager<SystemSearch> findSearchPager(int start, int limit, SystemSearch search, String columnName,
			boolean asc) {

		return searchManager.findPager(start, limit, search, columnName, asc);
	}

	public int[] updateBatchSearch(List<SystemSearch> searches) {

		return searchManager.updateBatch(searches);
	}


}
