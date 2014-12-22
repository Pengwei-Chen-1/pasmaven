package com.cpw.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 收入类
 * 
 * @author pengwei_chen
 * @date 2014-3-10 下午2:40:52
 */
@Entity
@Table(name = "pas_income")
public class Income {

	private int incomeId;
	private int userId;
	private String image;
	private int firstLevelId;
	private int secondLevelId;
	private int accountId;
	private int projectId;
	private int memberId;
	private double money;
	private Date createTime;
	private String remark;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INCOME_ID")
	public int getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
	}

	@Column(name = "USER_ID")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "IMAGE")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "FIRSTLEVEL_ID")
	public int getFirstLevelId() {
		return firstLevelId;
	}

	public void setFirstLevelId(int firstLevelId) {
		this.firstLevelId = firstLevelId;
	}

	@Column(name = "SECONDLEVEL_ID")
	public int getSecondLevelId() {
		return secondLevelId;
	}

	public void setSecondLevelId(int secondLevelId) {
		this.secondLevelId = secondLevelId;
	}

	@Column(name = "ACCOUNT_ID")
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Column(name = "PROJECT_ID")
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	@Column(name = "MEMBER_ID")
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	@Column(name = "MONEY", precision = 10, scale = 2)
	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "收入编号：" + incomeId;
	}
}
