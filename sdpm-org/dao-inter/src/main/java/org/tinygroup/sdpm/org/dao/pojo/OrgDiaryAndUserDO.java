package org.tinygroup.sdpm.org.dao.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用于联合查询结果存放
 * Created by wangdl16860 on 2016/1/14.
 */
public class OrgDiaryAndUserDO implements Serializable, Comparable {
    public static final String DELETE_YES = "1";
    public static final String DELETE_NO = "0";
    /**
     * 用户编号
     */
    private String orgUserId;
    /**
     * 部门编号
     */
    private Integer orgDeptId;
    /**
     * 用户名
     */
    private String orgUserAccount;
    /**
     * 密码
     */
    private String orgUserPassword;
    /**
     * 职位
     */
    private String orgUserRole;
    /**
     * 真实姓名
     */
    private String orgUserRealName;
    /**
     * 昵称
     */
    private String orgUserNickName;
    /**
     * 直接上级
     */
    private String orgUserLeader;
    /**
     * 源代码账号
     */
    private String orgUserSubmitter;
    /**
     * 头像
     */
    private String orgUserAvatar;
    /**
     * 出生日期
     */
    private String orgUserBirthday;
    /**
     * 性别
     */
    private String orgUserGender;
    /**
     * 邮箱
     */
    private String orgUserEmail;
    /**
     * Skype
     */
    private String orgUserSkype;
    /**
     * QQ
     */
    private String orgUserQQ;
    /**
     * 雅虎通
     */
    private String orgUserYahoo;
    /**
     * Gtalk
     */
    private String orgUserGTalk;
    /**
     * 旺旺
     */
    private String orgUserWangWang;
    /**
     * 手机
     */
    private String orgUserMobile;
    /**
     * 电话
     */
    private String orgUserPhone;
    /**
     * 通讯地址
     */
    private String orgUserAddress;
    /**
     * 邮编
     */
    private String orgUserZipCode;
    /**
     * 加入日期
     */
    private String orgUserJoin;
    /**
     * 访问次数
     */
    private Integer orgUserVisits;
    /**
     * 最后IP
     */
    private String orgUserIp;
    /**
     * 最后登录
     */
    private Date orgUserLast;
    /**
     * 失败次数
     */
    private Integer orgUserFails;
    /**
     * 上次锁定时间
     */
    private String orgUserLocked;
    /**
     * 是否删除
     */
    private String orgUserDeleted;

    /**
     * <!-- begin-user-doc -->
     * 周报id
     * <p>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    private Integer orgDiaryId;

    /**
     * <!-- begin-user-doc -->
     * 周报开始时间
     * <p>
     * 每周开始的第一天
     * <!-- end-user-doc -->
     *
     * @generated
     */
    private Date orgDiaryBeginDate;

    /**
     * <!-- begin-user-doc -->
     * 周报结束时间
     * <p>
     * 每周最后一天
     * <!-- end-user-doc -->
     *
     * @generated
     */
    private Date orgDiaryEndDate;

    /**
     * <!-- begin-user-doc -->
     * 周报年
     * <p>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    private Integer orgDiaryYear;

    /**
     * <!-- begin-user-doc -->
     * 周报周数
     * <p>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    private Integer orgDiaryWeek;

    /**
     * <!-- begin-user-doc -->
     * 周报创建日期
     * <p>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    private Date orgDiaryCreateDate;

    /**
     * <!-- begin-user-doc -->
     * 每周总结
     * <p>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    private String orgDiarySummary;

    /**
     * <!-- begin-user-doc -->
     * 周报状态
     * <p>
     * 正常-0
     * 删除-1
     * <!-- end-user-doc -->
     *
     * @generated
     */
    private String orgDiaryStatus;

    /**
     * <!-- begin-user-doc -->
     * 最后修改日期
     * <p>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    private Date orgDiaryModifyDate;

    private String diaryDateTime;

    private Integer detailCountStatus;

    public Integer getOrgDiaryId() {
        return orgDiaryId;
    }

    public void setOrgDiaryId(Integer orgDiaryId) {
        this.orgDiaryId = orgDiaryId;
    }

    public Date getOrgDiaryBeginDate() {
        return orgDiaryBeginDate;
    }

    public void setOrgDiaryBeginDate(Date orgDiaryBeginDate) {
        this.orgDiaryBeginDate = orgDiaryBeginDate;
    }

    public Date getOrgDiaryEndDate() {
        return orgDiaryEndDate;
    }

    public void setOrgDiaryEndDate(Date orgDiaryEndDate) {
        this.orgDiaryEndDate = orgDiaryEndDate;
    }

    public Integer getOrgDiaryYear() {
        return orgDiaryYear;
    }

    public void setOrgDiaryYear(Integer orgDiaryYear) {
        this.orgDiaryYear = orgDiaryYear;
    }

    public Integer getOrgDiaryWeek() {
        return orgDiaryWeek;
    }

    public void setOrgDiaryWeek(Integer orgDiaryWeek) {
        this.orgDiaryWeek = orgDiaryWeek;
    }

    public Date getOrgDiaryCreateDate() {
        return orgDiaryCreateDate;
    }

    public void setOrgDiaryCreateDate(Date orgDiaryCreateDate) {
        this.orgDiaryCreateDate = orgDiaryCreateDate;
    }

    public String getOrgDiarySummary() {
        return orgDiarySummary;
    }

    public void setOrgDiarySummary(String orgDiarySummary) {
        this.orgDiarySummary = orgDiarySummary;
    }

    public String getOrgDiaryStatus() {
        return orgDiaryStatus;
    }

    public void setOrgDiaryStatus(String orgDiaryStatus) {
        this.orgDiaryStatus = orgDiaryStatus;
    }

    public Date getOrgDiaryModifyDate() {
        return orgDiaryModifyDate;
    }

    public void setOrgDiaryModifyDate(Date orgDiaryModifyDate) {
        this.orgDiaryModifyDate = orgDiaryModifyDate;
    }

    public String getOrgUserId() {
        return orgUserId;
    }

    public void setOrgUserId(String orgUserId) {
        this.orgUserId = orgUserId;
    }

    public Integer getOrgDeptId() {
        return orgDeptId;
    }

    public void setOrgDeptId(Integer orgDeptId) {
        this.orgDeptId = orgDeptId;
    }

    public String getOrgUserAccount() {
        return orgUserAccount;
    }

    public void setOrgUserAccount(String orgUserAccount) {
        this.orgUserAccount = orgUserAccount;
    }

    public String getOrgUserPassword() {
        return orgUserPassword;
    }

    public void setOrgUserPassword(String orgUserPassword) {
        this.orgUserPassword = orgUserPassword;
    }

    public String getOrgUserRole() {
        return orgUserRole;
    }

    public void setOrgUserRole(String orgUserRole) {
        this.orgUserRole = orgUserRole;
    }

    public String getOrgUserRealName() {
        return orgUserRealName;
    }

    public void setOrgUserRealName(String orgUserRealName) {
        this.orgUserRealName = orgUserRealName;
    }

    public String getOrgUserNickName() {
        return orgUserNickName;
    }

    public void setOrgUserNickName(String orgUserNickName) {
        this.orgUserNickName = orgUserNickName;
    }

    public String getOrgUserSubmitter() {
        return orgUserSubmitter;
    }

    public void setOrgUserSubmitter(String orgUserSubmitter) {
        this.orgUserSubmitter = orgUserSubmitter;
    }

    public String getOrgUserAvatar() {
        return orgUserAvatar;
    }

    public void setOrgUserAvatar(String orgUserAvatar) {
        this.orgUserAvatar = orgUserAvatar;
    }

    public String getOrgUserBirthday() {
        return orgUserBirthday;
    }

    public void setOrgUserBirthday(String orgUserBirthday) {
        this.orgUserBirthday = orgUserBirthday;
    }

    public String getOrgUserGender() {
        return orgUserGender;
    }

    public void setOrgUserGender(String orgUserGender) {
        this.orgUserGender = orgUserGender;
    }

    public String getOrgUserEmail() {
        return orgUserEmail;
    }

    public void setOrgUserEmail(String orgUserEmail) {
        this.orgUserEmail = orgUserEmail;
    }

    public String getOrgUserSkype() {
        return orgUserSkype;
    }

    public void setOrgUserSkype(String orgUserSkype) {
        this.orgUserSkype = orgUserSkype;
    }

    public String getOrgUserQQ() {
        return orgUserQQ;
    }

    public void setOrgUserQQ(String orgUserQQ) {
        this.orgUserQQ = orgUserQQ;
    }

    public String getOrgUserYahoo() {
        return orgUserYahoo;
    }

    public void setOrgUserYahoo(String orgUserYahoo) {
        this.orgUserYahoo = orgUserYahoo;
    }

    public String getOrgUserGTalk() {
        return orgUserGTalk;
    }

    public void setOrgUserGTalk(String orgUserGTalk) {
        this.orgUserGTalk = orgUserGTalk;
    }

    public String getOrgUserWangWang() {
        return orgUserWangWang;
    }

    public void setOrgUserWangWang(String orgUserWangWang) {
        this.orgUserWangWang = orgUserWangWang;
    }

    public String getOrgUserMobile() {
        return orgUserMobile;
    }

    public void setOrgUserMobile(String orgUserMobile) {
        this.orgUserMobile = orgUserMobile;
    }

    public String getOrgUserPhone() {
        return orgUserPhone;
    }

    public void setOrgUserPhone(String orgUserPhone) {
        this.orgUserPhone = orgUserPhone;
    }

    public String getOrgUserAddress() {
        return orgUserAddress;
    }

    public void setOrgUserAddress(String orgUserAddress) {
        this.orgUserAddress = orgUserAddress;
    }

    public String getOrgUserZipCode() {
        return orgUserZipCode;
    }

    public void setOrgUserZipCode(String orgUserZipCode) {
        this.orgUserZipCode = orgUserZipCode;
    }

    public String getOrgUserJoin() {
        return orgUserJoin;
    }

    public void setOrgUserJoin(String orgUserJoin) {
        this.orgUserJoin = orgUserJoin;
    }

    public Integer getOrgUserVisits() {
        return orgUserVisits;
    }

    public void setOrgUserVisits(Integer orgUserVisits) {
        this.orgUserVisits = orgUserVisits;
    }

    public String getOrgUserIp() {
        return orgUserIp;
    }

    public void setOrgUserIp(String orgUserIp) {
        this.orgUserIp = orgUserIp;
    }

    public Date getOrgUserLast() {
        return orgUserLast;
    }

    public void setOrgUserLast(Date orgUserLast) {
        this.orgUserLast = orgUserLast;
    }

    public Integer getOrgUserFails() {
        return orgUserFails;
    }

    public void setOrgUserFails(Integer orgUserFails) {
        this.orgUserFails = orgUserFails;
    }

    public String getOrgUserLocked() {
        return orgUserLocked;
    }

    public void setOrgUserLocked(String orgUserLocked) {
        this.orgUserLocked = orgUserLocked;
    }

    public String getOrgUserDeleted() {
        return orgUserDeleted;
    }

    public void setOrgUserDeleted(String orgUserDeleted) {
        this.orgUserDeleted = orgUserDeleted;
    }

    public String getOrgUserLeader() {
        return orgUserLeader;
    }

    public void setOrgUserLeader(String orgUserLeader) {
        this.orgUserLeader = orgUserLeader;
    }

    public String getDiaryDateTime() {
        return diaryDateTime;
    }

    public void setDiaryDateTime(String diaryDateTime) {
        this.diaryDateTime = diaryDateTime;
    }

    @Override
    public int compareTo(Object o) {
        return -this.orgDiaryWeek.compareTo(((OrgDiaryAndUserDO) o).orgDiaryWeek);
    }

    public Integer getDetailCountStatus() {
        return detailCountStatus;
    }

    public void setDetailCountStatus(Integer detailCountStatus) {
        this.detailCountStatus = detailCountStatus;
    }
}
