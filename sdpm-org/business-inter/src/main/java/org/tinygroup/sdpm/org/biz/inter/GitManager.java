package org.tinygroup.sdpm.org.biz.inter;

import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryGitDetail;
import org.tinygroup.sdpm.org.dao.pojo.OrgGitCommitInfo;

import java.util.Date;
import java.util.List;

/**
 * Created by gongyl on 2016/3/24.
 */
public interface GitManager {
    /**
     * 查询git发送的提交记录
     */
    public List<OrgGitCommitInfo> query(OrgGitCommitInfo gitInfo);

    /**
     * 记录git发送的代码的提交信息
     */
    public void batchInsertGitCommitInfo(List<OrgGitCommitInfo> list);

    /**
     * 通过用户的EMAIL、日期范围 查询git发送的代码提交信息
     */
    public List<OrgGitCommitInfo> findOrgGitCommitInfoByEmailAndDate(String email, Date beginDate, Date endDate);

    /**
     * 通过日志ID、代码的提交信息 更新周报
     */
    public void batchInsertDiaryGitDetail(Integer diaryId, List<OrgDiaryGitDetail> list);

    /**
     * 查询周报中代码提交的信息
     */
    public List<OrgDiaryGitDetail> query(OrgDiaryGitDetail diaryGitDetail);

    /**
     * 通过日志ID 查询周报中 代码提交信息的详情
     */
    public List<OrgGitCommitInfo> getOrgGitCommitInfoByDiaryId(Integer diaryId);
}
