package org.tinygroup.sdpm.org.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.org.biz.inter.GitManager;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryGitDetail;
import org.tinygroup.sdpm.org.dao.pojo.OrgGitCommitInfo;
import org.tinygroup.sdpm.org.service.inter.GitService;

/**
 * Created by Administrator on 2016/3/24.
 */
@Component
public class GitServiceImpl implements GitService {
    @Autowired
    private GitManager gitManager;
    @Override
    public List<OrgGitCommitInfo> queryCommits(OrgGitCommitInfo gitInfo) {
        return gitManager.query(gitInfo);
    }
    @Override
    public void batchInsertGitCommitInfo(List<OrgGitCommitInfo> list) {
        gitManager.batchInsertGitCommitInfo(list);
    }
    @Override
    public String getUserIdByEmail(String email){
        return gitManager.getUserIdByEmail(email);
    }
    @Override
    public List<OrgGitCommitInfo> findOrgGitCommitInfoByIdAndDate(String id, Date beginDate, Date endDate){
        return gitManager.findOrgGitCommitInfoByIdAndDate(id, beginDate, endDate);
    }
	@Override
	public void batchInsertDiaryGitDetail(Integer diaryId,List<OrgDiaryGitDetail> list) {
		gitManager.batchInsertDiaryGitDetail(diaryId,list);
	}
	@Override
	public List<OrgDiaryGitDetail> query(OrgDiaryGitDetail diaryGitDetail) {
		return gitManager.query(diaryGitDetail);
	}
	@Override
	public List<OrgGitCommitInfo> getOrgGitCommitInfoByDiaryId(Integer diaryId){
		return gitManager.getOrgGitCommitInfoByDiaryId(diaryId);
	}
}
