package org.tinygroup.sdpm.org.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.sdpm.org.biz.inter.WhiteListManager;
import org.tinygroup.sdpm.org.dao.OrgDiaryWhiteListDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryWhiteList;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangdl16860 on 2016/1/19.
 */
@Service
public class WhiteListManagerImpl implements WhiteListManager {
    @Autowired
    private OrgDiaryWhiteListDao orgDiaryWhiteListDao;

    @Override
    public int[] add(String firstUserAccount, List<String> secondUserAccountList) {
        List<OrgDiaryWhiteList> list = new ArrayList<OrgDiaryWhiteList>();
        for (int i = 0; i < secondUserAccountList.size(); i++) {
            OrgDiaryWhiteList orgWhiteList = new OrgDiaryWhiteList();
            orgWhiteList.setOrgDiaryWhiteListFirstAccount(firstUserAccount);
            orgWhiteList.setOrgDiaryWhiteListSecondAccount(secondUserAccountList.get(i));
        }
        return orgDiaryWhiteListDao.batchInsert(list);
    }

    @Override
    public List<OrgUser> findUserListByAccount(String userAccount) {
        return orgDiaryWhiteListDao.findUserListByAccount(userAccount);
    }

    @Override
    public OrgDiaryWhiteList addOneWhite(String firstAccount, String secondAccount) {
        OrgDiaryWhiteList orgWhiteList = new OrgDiaryWhiteList();
        orgWhiteList.setOrgDiaryWhiteListFirstAccount(firstAccount);
        orgWhiteList.setOrgDiaryWhiteListSecondAccount(secondAccount);
        return orgDiaryWhiteListDao.add(orgWhiteList);
    }

    @Override
    public OrgDiaryWhiteList findOneByAccounts(String firstAccount, String secondAccount) {
        List<OrgDiaryWhiteList> list = orgDiaryWhiteListDao.findOneByAccounts(firstAccount, secondAccount);
        if (CollectionUtil.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public Integer deleteDiaryWhiteList(String firstAccount, String secondAccout) {
        return orgDiaryWhiteListDao.deleteByAccounts(firstAccount, secondAccout);
    }
}
