package org.tinygroup.sdpm.org.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.sdpm.org.biz.inter.GitManager;
import org.tinygroup.sdpm.org.dao.OrgDiaryGitDetailDao;
import org.tinygroup.sdpm.org.dao.OrgGitCommitInfoDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryGitDetail;
import org.tinygroup.sdpm.org.dao.pojo.OrgGitCommitInfo;

/**
 * Created by Administrator on 2016/3/24.
 */
@Service
@Transactional
public class GitManagerImpl implements GitManager{
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
    	orgGitCommitInfoDao.batchDeleteGitCommitInfoById(list);
    	orgGitCommitInfoDao.batchInsert(list);
    }
    @Override
    public String getUserIdByEmail(String email){
        return orgGitCommitInfoDao.getUserIdByEmail(email);
    }
    @Override
    public List<OrgGitCommitInfo> findOrgGitCommitInfoByIdAndDate(String id, Date beginDate, Date endDate){
       return orgGitCommitInfoDao.findOrgGitCommitInfoByIdAndDate(id, beginDate, endDate);
    }
	@Override
	public void batchInsertDiaryGitDetail(Integer diaryId,List<OrgDiaryGitDetail> list) {
		orgDiaryGitDetailDao.DeleteByDiaryId(diaryId);
		orgDiaryGitDetailDao.batchInsert(list);
	}
	@Override
	public List<OrgDiaryGitDetail> query(OrgDiaryGitDetail diaryGitDetail) {
		return orgDiaryGitDetailDao.query(diaryGitDetail);
	}
	@Override
	public List<OrgGitCommitInfo> getOrgGitCommitInfoByDiaryId(Integer diaryId){
		return orgDiaryGitDetailDao.getOrgGitCommitInfoByDiaryId(diaryId);
	}
}
