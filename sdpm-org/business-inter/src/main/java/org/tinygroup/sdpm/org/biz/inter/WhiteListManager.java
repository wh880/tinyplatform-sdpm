package org.tinygroup.sdpm.org.biz.inter;

import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryWhiteList;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;

import java.util.List;

/**
 * 白名单相关操作
 * Created by wangdl16860 on 2016/1/19.
 */
public interface WhiteListManager {
    /**
     * 添加白名单用户
     */
    int[] add(String firstUserAccount, List<String> secondUserAccountList);

    /**
     * 查找该用户的白名单用户
     */
    List<OrgUser> findUserListByAccount(String userAccount);

    /**
     * 添加单个白名单信息
     */
    OrgDiaryWhiteList addOneWhite(String firstAccount,String secondAccount);

    /**
     * 查询在白名单表中是否存在某一对确定Account的白名单关系
     */
    OrgDiaryWhiteList findOneByAccounts(String firstAccount,String secondAccount);

    /**
     * 删除白名单关系
     * @param firstAccount
     * @param secondAccout
     * @return
     */
    Integer deleteDiaryWhiteList(String firstAccount,String secondAccout);

}
