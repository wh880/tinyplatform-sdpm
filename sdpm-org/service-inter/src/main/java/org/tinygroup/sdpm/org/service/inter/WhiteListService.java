package org.tinygroup.sdpm.org.service.inter;

import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryWhiteList;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;

import java.util.List;

/**
 * 白名单相关操作Service接口
 * Created by wangdl16860 on 2016/1/19.
 */
public interface WhiteListService {
    /**
     * 批量添加白名单信息
     */
    int[] addWhiteList(String firstUserAccount,List<String> secondUserAccountList);

    /**
     * 查找该用户的相关白名单用户
     */
    List<OrgUser> findUserListByWhiteFirst(String firstUserAccount);

    /**
     * 单个添加白名单信息
     */
    OrgDiaryWhiteList addOneWhite(String firstAccount,String secondAccount);

    /**
     * 查看该对人是否存在于白名单中
     */
    OrgDiaryWhiteList findDiaryWhiteByAccounts(String firstAccount,String secondAccount);

    /**
     * 删除白名单关系
     * @param firstAccount
     * @param secondAccout
     * @return
     */
    Integer deleteDiaryWhiteList(String firstAccount,String secondAccout);

}
