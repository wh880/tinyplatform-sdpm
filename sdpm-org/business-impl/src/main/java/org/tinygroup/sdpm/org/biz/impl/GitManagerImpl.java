package org.tinygroup.sdpm.org.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.sdpm.org.biz.inter.GitManager;
import org.tinygroup.sdpm.org.dao.OrgDiaryGitDetailDao;
import org.tinygroup.sdpm.org.dao.OrgGitCommitInfoDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryGitDetail;
import org.tinygroup.sdpm.org.dao.pojo.OrgGitCommitInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gongyl on 2016/3/24.
 */
@Service
public class GitManagerImpl implements GitManager {
    @Autowired
    private OrgGitCommitInfoDao orgGitCommitInfoDao;
    @Autowired
    private OrgDiaryGitDetailDao orgDiaryGitDetailDao;

    @Override
    public List<OrgGitCommitInfo> query(OrgGitCommitInfo gitInfo) {
        return orgGitCommitInfoDao.query(gitInfo);
    }

    @Override
    public void batchInsertGitCommitInfo(List<OrgGitCommitInfo> list) {
        orgGitCommitInfoDao.batchInsert(list);
    }

    @Override
    public List<OrgGitCommitInfo> findOrgGitCommitInfoByEmailAndDate(String email, Date beginDate, Date endDate) {
        if(email==null||"".equals(email)){
            return new ArrayList<OrgGitCommitInfo>();
        }
        return orgGitCommitInfoDao.findOrgGitCommitInfoByEmailAndDate(email, beginDate, endDate);
    }

    @Override
    public void batchInsertDiaryGitDetail(Integer diaryId, List<OrgDiaryGitDetail> list) {
        orgDiaryGitDetailDao.DeleteByDiaryId(diaryId);
        orgDiaryGitDetailDao.batchInsert(list);
    }

    @Override
    public List<OrgDiaryGitDetail> query(OrgDiaryGitDetail diaryGitDetail) {
        return orgDiaryGitDetailDao.query(diaryGitDetail);
    }

    @Override
    public List<OrgGitCommitInfo> getOrgGitCommitInfoByDiaryId(Integer diaryId) {
        return orgDiaryGitDetailDao.getOrgGitCommitInfoByDiaryId(diaryId);
    }
}
