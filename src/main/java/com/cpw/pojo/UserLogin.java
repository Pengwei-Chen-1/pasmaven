package com.cpw.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户登录类
 * 
 * @author pengwei_chen
 * @date 2014-3-10 上午9:08:15
 */
@Entity
@Table(name = "pas_userlogin")
public class UserLogin {

	private String userName;
	private String userPass;

	@Id
	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USER_PASS")
	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	@Override
	public String toString() {
		return "用户名：" + userName + "密码：" + userPass;
	}
}
