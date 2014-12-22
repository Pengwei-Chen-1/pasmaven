package com.cpw.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 家庭成员类
 * 
 * @author pengwei_chen
 * @date 2014-3-10 上午9:20:39
 */
@Entity
@Table(name = "pas_family")
public class Family {

	private int memberId;
	private int userId;
	private String memberName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEMBER_ID")
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	@Column(name = "USER_ID")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "MEMBER_NAME")
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public String toString() {
		return "成员编号：" + memberId + "用户编号：" + userId + "成员姓名：" + memberName;
	}
}
