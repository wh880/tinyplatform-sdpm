package org.tinygroup.sdpm.org.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.org.biz.inter.GitManager;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryGitDetail;
import org.tinygroup.sdpm.org.dao.pojo.OrgGitCommitInfo;
import org.tinygroup.sdpm.org.service.inter.GitService;

import java.util.Date;
import java.util.List;

/**
 * Created by gongyl on 2016/3/24.
 */
@Component
@Transactional
public class GitServiceImpl implements GitService {
    @Autowired
    private GitManager gitManager;

    @Override
    @Transactional(readOnly = true)
    public List<OrgGitCommitInfo> queryCommits(OrgGitCommitInfo gitInfo) {
        return gitManager.query(gitInfo);
    }

    @Override
    public void batchInsertGitCommitInfo(List<OrgGitCommitInfo> list) {
        gitManager.batchInsertGitCommitInfo(list);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrgGitCommitInfo> findOrgGitCommitInfoByEmailAndDate(String email, Date beginDate, Date endDate) {
        return gitManager.findOrgGitCommitInfoByEmailAndDate(email, beginDate, endDate);
    }

    @Override
    public void batchInsertDiaryGitDetail(Integer diaryId, List<OrgDiaryGitDetail> list) {
        gitManager.batchInsertDiaryGitDetail(diaryId, list);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrgDiaryGitDetail> query(OrgDiaryGitDetail diaryGitDetail) {
        return gitManager.query(diaryGitDetail);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrgGitCommitInfo> getOrgGitCommitInfoByDiaryId(Integer diaryId) {
        return gitManager.getOrgGitCommitInfoByDiaryId(diaryId);
    }
}
