package org.tinygroup.sdpm.org.biz.inter;

import java.util.Date;
import java.util.List;

import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryGitDetail;
import org.tinygroup.sdpm.org.dao.pojo.OrgGitCommitInfo;

/**
 * Created by Administrator on 2016/3/24.
 */
public interface GitManager {
    public List<OrgGitCommitInfo> query(OrgGitCommitInfo gitInfo);
    public void batchInsertGitCommitInfo(List<OrgGitCommitInfo> list);
    public String getNameByEmail(String email);
    public List<OrgGitCommitInfo> findOrgGitCommitInfoByNameAndDate(String name,Date beginDate,Date endDate);
    public void batchInsertDiaryGitDetail(List<OrgDiaryGitDetail> list);
    public List<OrgDiaryGitDetail> query(OrgDiaryGitDetail diaryGitDetail);
    public List<OrgGitCommitInfo> getOrgGitCommitInfoByDiaryId(Integer diaryId);
}
