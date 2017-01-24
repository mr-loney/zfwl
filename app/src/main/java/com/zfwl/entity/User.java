package com.zfwl.entity;

/**
 * Created by ZZB on 2016/12/7.
 */
public class User {
    private long id;//会员ID
    private String account;//用户帐号=手机号码
    private String realname;//真实姓名
    private String nickname;//昵称
    private String avatar;//头像
    private int sex;//性别：0：未知，1：男；2：女
    private String password;//密码
    private String phone;//手机号码
    private String idcard;//身份证号码
    private String email;//邮箱
    private int status;//用户状态 0：未激活，1：已激活；2：禁用
    private int isBlacklist;//黑名单标识：0：否，1：是
    private int memberType;//会员类型：0：未选则，1：车主，2：司机，3：后台管理员
    private long cdate;//创建时间戳
    private long mdate;//更新时间戳
    private int isCompletedInfo;//会员信息完整标识：0：否，1：完整
    private String remark;//备注

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsBlacklist() {
        return isBlacklist;
    }

    public void setIsBlacklist(int isBlacklist) {
        this.isBlacklist = isBlacklist;
    }

    public int getMemberType() {
        return memberType;
    }

    public void setMemberType(int memberType) {
        this.memberType = memberType;
    }

    public long getCdate() {
        return cdate;
    }

    public void setCdate(long cdate) {
        this.cdate = cdate;
    }

    public long getMdate() {
        return mdate;
    }

    public void setMdate(long mdate) {
        this.mdate = mdate;
    }

    public int getIsCompletedInfo() {
        return isCompletedInfo;
    }

    public void setIsCompletedInfo(int isCompletedInfo) {
        this.isCompletedInfo = isCompletedInfo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", realname='" + realname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sex=" + sex +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", idcard='" + idcard + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", isBlacklist=" + isBlacklist +
                ", memberType=" + memberType +
                ", cdate=" + cdate +
                ", mdate=" + mdate +
                ", isCompletedInfo=" + isCompletedInfo +
                ", remark='" + remark + '\'' +
                '}';
    }
}
