package cn.adbyte.springboot.biz.member.entity;

import cn.adbyte.springboot.biz.dept.entity.DepartmentEntity;
import cn.adbyte.springboot.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * Created by Adam.yao on 2017/10/30.
 * 内容不要轻易改，因为继承Spring安全有使用规范！
 */
@Entity
@Cacheable
@Table(name = "t_member")
public class MemberEntity extends BaseEntity implements UserDetails {

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;
    @Transient
    private Integer deptID;
    @Transient
    private String deptName;
    @Transient
    private Integer orgID;
    @Transient
    private String orgName;
    private String username;
    private String mobile;
    private String email;
    @JsonIgnore
    private String password;
    private String fullname;
    private String qq;
    private Boolean expired;
    private Boolean locked;
    private Boolean credentialsExpired;
    private Boolean enabled;
    @Transient
    private Set<GrantedAuthority> authorities;

    public MemberEntity() {
    }

    public MemberEntity(DepartmentEntity department, String username, String password, String fullname,
                        String qq, String email, String mobile, boolean expired, boolean locked, boolean credentialsExpired,
                        boolean enabled, Set<GrantedAuthority> authorities) {
        this.department = department;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.qq = qq;
        this.email = email;
        this.mobile = mobile;
        this.expired = expired;
        this.locked = locked;
        this.credentialsExpired = credentialsExpired;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public MemberEntity(Boolean del, Date last, Date time, Integer operator, DepartmentEntity department,
                        String username, String password, String fullname, String qq, String email, String mobile,
                        boolean expired, boolean locked, boolean credentialsExpired, boolean enabled,
                        Set<GrantedAuthority> authorities) {
        super(del, last, time, operator);
        this.department = department;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.qq = qq;
        this.email = email;
        this.mobile = mobile;
        this.expired = expired;
        this.locked = locked;
        this.credentialsExpired = credentialsExpired;
        this.enabled = enabled;
        this.authorities = authorities;
    }


    public String getUsername() {
        return username;
    }

    public String getDeptName() {
        deptName = null != getDepartment() ? getDepartment().getName() : null;
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getOrgName() {
        if (null != getDepartment() && null != getDepartment().getOrganization()) {
            orgName = getDepartment().getOrganization().getCompanyName();
        }
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getDeptID() {
        deptID = null != getDepartment() ? getDepartment().getId() : null;
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }

    public Integer getOrgID() {
        if (null != getDepartment() && null != getDepartment().getOrganization()) {
            orgID = getDepartment().getOrganization().getId();
        }
        return orgID;
    }

    public void setOrgID(Integer orgID) {
        this.orgID = orgID;
    }

    @Override
    public boolean isAccountNonExpired() {
        return expired == null ? false : !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked == null ? false : !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsExpired == null ? false : !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled == null ? true : enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean getExpired() {
        return expired == null ? false : expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean getLocked() {
        return locked == null ? false : locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean getCredentialsExpired() {
        return credentialsExpired == null ? false : credentialsExpired;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public boolean getEnabled() {
        return enabled == null ? true : enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public boolean isExpired() {
        return expired == null ? false : expired;
    }

    public boolean isLocked() {
        return locked == null ? false : locked;
    }

    public boolean isCredentialsExpired() {
        return credentialsExpired == null ? false : credentialsExpired;
    }
}
