package com.adam.mipet.user.entity;

import java.util.Date;

import com.adam.common.util.MybatisUtil;

public class MipetUser {
	
	private Integer id; // 主键
	
	private String source; // 用户来源
	
	private String username; // 用户名
	
	private String password; // 登录密码
	
	private String nickname; // 昵称
	
	private Integer gender; // 性别 0表示女 1表示男
	
	private Date birthday; // 生日
	
	private String family; // 家族
	
	private String headPic; // 头像图片
	
	private String phone; // 手机号
	
	private String email; // 邮箱
	
	private Date regTime; // 注册时间
	
	private Date loginTime; // 登录时间
	
	private String loginIp; // 登录IP
	
	private Integer loginTimes; // 登录次数
	
	private Integer status; // 0：未验证、1：正常
	
	private Integer enabled; // 0：启用、1：拉黑
	
	private String clientId;// 用户设备ID
	
	private Integer deleted; // 0：正常、1：删除
	
	private Date updateTime; // 最后修改时间
	
	public MipetUser() {
	
		super();
	}
	
	public MipetUser(Integer id, String nickname, Integer gender, Date birthday, String family, String headPic) {
	
		super();
		this.id = id;
		this.nickname = nickname;
		this.gender = gender;
		this.birthday = birthday;
		this.family = family;
		this.headPic = headPic;
	}
	
	@Override
	public int hashCode() {
	
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((deleted == null) ? 0 : deleted.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((family == null) ? 0 : family.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((headPic == null) ? 0 : headPic.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((loginIp == null) ? 0 : loginIp.hashCode());
		result = prime * result + ((loginTime == null) ? 0 : loginTime.hashCode());
		result = prime * result + ((loginTimes == null) ? 0 : loginTimes.hashCode());
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((regTime == null) ? 0 : regTime.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
	
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MipetUser other = (MipetUser) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (deleted == null) {
			if (other.deleted != null)
				return false;
		} else if (!deleted.equals(other.deleted))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
			return false;
		if (family == null) {
			if (other.family != null)
				return false;
		} else if (!family.equals(other.family))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (headPic == null) {
			if (other.headPic != null)
				return false;
		} else if (!headPic.equals(other.headPic))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (loginIp == null) {
			if (other.loginIp != null)
				return false;
		} else if (!loginIp.equals(other.loginIp))
			return false;
		if (loginTime == null) {
			if (other.loginTime != null)
				return false;
		} else if (!loginTime.equals(other.loginTime))
			return false;
		if (loginTimes == null) {
			if (other.loginTimes != null)
				return false;
		} else if (!loginTimes.equals(other.loginTimes))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (regTime == null) {
			if (other.regTime != null)
				return false;
		} else if (!regTime.equals(other.regTime))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	public String getClientId() {
	
		return clientId;
	}
	
	public void setClientId(String clientId) {
	
		this.clientId = clientId;
	}
	
	public Integer getId() {
	
		return id;
	}
	
	public void setId(Integer id) {
	
		this.id = id;
	}
	
	public String getSource() {
	
		return source;
	}
	
	public void setSource(String source) {
	
		this.source = source;
	}
	
	public String getUsername() {
	
		return username;
	}
	
	public void setUsername(String username) {
	
		this.username = username;
	}
	
	public String getPassword() {
	
		return password;
	}
	
	public void setPassword(String password) {
	
		this.password = password;
	}
	
	public String getNickname() {
	
		return nickname;
	}
	
	public void setNickname(String nickname) {
	
		this.nickname = nickname;
	}
	
	public Integer getGender() {
	
		return gender;
	}
	
	public void setGender(Integer gender) {
	
		this.gender = gender;
	}
	
	public Date getBirthday() {
	
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
	
		this.birthday = birthday;
	}
	
	public String getFamily() {
	
		return family;
	}
	
	public void setFamily(String family) {
	
		this.family = family;
	}
	
	public String getHeadPic() {
	
		return headPic;
	}
	
	public void setHeadPic(String headPic) {
	
		this.headPic = headPic;
	}
	
	public String getPhone() {
	
		return phone;
	}
	
	public void setPhone(String phone) {
	
		this.phone = phone;
	}
	
	public String getEmail() {
	
		return email;
	}
	
	public void setEmail(String email) {
	
		this.email = email;
	}
	
	public Date getRegTime() {
	
		return regTime;
	}
	
	public void setRegTime(Date regTime) {
	
		this.regTime = regTime;
	}
	
	public Date getLoginTime() {
	
		return loginTime;
	}
	
	public void setLoginTime(Date loginTime) {
	
		this.loginTime = loginTime;
	}
	
	public String getLoginIp() {
	
		return loginIp;
	}
	
	public void setLoginIp(String loginIp) {
	
		this.loginIp = loginIp;
	}
	
	public Integer getLoginTimes() {
	
		return loginTimes;
	}
	
	public void setLoginTimes(Integer loginTimes) {
	
		this.loginTimes = loginTimes;
	}
	
	public Integer getStatus() {
	
		return status;
	}
	
	public void setStatus(Integer status) {
	
		this.status = status;
	}
	
	public Integer getEnabled() {
	
		return enabled;
	}
	
	public void setEnabled(Integer enabled) {
	
		this.enabled = enabled;
	}
	
	public Integer getDeleted() {
	
		return deleted;
	}
	
	public void setDeleted(Integer deleted) {
	
		this.deleted = deleted;
	}
	
	public Date getUpdateTime() {
	
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
	
		this.updateTime = updateTime;
	}
	
	public static void main(String[] args) {
	
		String classMapperString = MybatisUtil.getClassMapperString(MipetUser.class);
		System.out.println(classMapperString);
	}
}
