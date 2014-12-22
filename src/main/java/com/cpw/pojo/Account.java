package com.cpw.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 账户类
 * 
 * @author pengwei_chen
 * @date 2014-3-10 上午10:22:16
 */
@Entity
@Table(name = "pas_account")
public class Account {

	private int accountId;
	private int userId;
	private String accountName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCOUNT_ID")
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Column(name = "ACCOUNT_NAME")
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Column(name = "USER_ID")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "账户编号：" + accountId + "用户编号：" + userId + "账户名称：" + accountName;
	}
}
