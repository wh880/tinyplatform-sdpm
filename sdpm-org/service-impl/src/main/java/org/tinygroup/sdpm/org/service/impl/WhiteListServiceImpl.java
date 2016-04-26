package org.tinygroup.sdpm.org.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.org.biz.inter.WhiteListManager;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryWhiteList;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.WhiteListService;

import java.util.List;

/**
 * Created by wangdl16860 on 2016/1/19.
 */
@Component
@Transactional
public class WhiteListServiceImpl implements WhiteListService {
    @Autowired
    private WhiteListManager whiteListManager;

    @Override
    public int[] addWhiteList(String firstUserAccount, List<String> secondUserAccountList) {
        return whiteListManager.add(firstUserAccount, secondUserAccountList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrgUser> findUserListByWhiteFirst(String firstUserAccount) {
        return whiteListManager.findUserListByAccount(firstUserAccount);
    }

    @Override
    public OrgDiaryWhiteList addOneWhite(String firstAccount, String secondAccount) {
        return whiteListManager.addOneWhite(firstAccount, secondAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public OrgDiaryWhiteList findDiaryWhiteByAccounts(String firstAccount, String secondAccount) {
        return whiteListManager.findOneByAccounts(firstAccount, secondAccount);
    }

    @Override
    public Integer deleteDiaryWhiteList(String firstAccount, String secondAccout) {
        return whiteListManager.deleteDiaryWhiteList(firstAccount, secondAccout);
    }
}
