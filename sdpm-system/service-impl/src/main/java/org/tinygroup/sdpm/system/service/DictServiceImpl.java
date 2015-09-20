package org.tinygroup.sdpm.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.system.biz.inter.DictManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemDict;

@Component("dictService")
public class DictServiceImpl implements DictService {
	
	@Autowired
	private DictManager dictManager;
	
	public SystemDict addDict(SystemDict dict) {

		return dictManager.add(dict);
	}

	public int deleteDict(Integer dictId) {

		return dictManager.delete(dictId);
	}

	public int updateDict(SystemDict dict) {

		return dictManager.update(dict);
	}

	public SystemDict findDict(Integer dictId) {

		return dictManager.find(dictId);
	}

	public List<SystemDict> findDictList(SystemDict dict) {

		return dictManager.findList(dict);
	}

}