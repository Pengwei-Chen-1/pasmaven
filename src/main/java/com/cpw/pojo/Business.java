package com.cpw.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商家信息类
 * 
 * @author pengwei_chen
 * @date 2014-3-10 上午9:26:23
 */
@Entity
@Table(name="pas_business")
public class Business {

	private int businessId;
	private int userId;
	private String businessName;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="BUSINESS_ID")
	public int getBusinessId() {
		return businessId;
	}

	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	@Column(name="USER_ID")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name="BUSINESS_NAME")
	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	@Override
	public String toString() {
		return "商家编号：" + businessId + "用户编号：" + userId + "商家名称：" + businessName;
	}
}
