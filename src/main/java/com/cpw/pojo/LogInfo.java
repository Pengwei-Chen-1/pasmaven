package com.cpw.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 日志类
 * 
 * @author pengwei_chen
 * @date 2014-3-10 上午9:14:40
 */
@Entity
@Table(name="pas_log")
public class LogInfo {

	private int logId;
	private int userId;
	private String operateType;
	private String operateDes;
	private Date operateTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LOG_ID")
	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	@Column(name = "USER_ID")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "OPERATE_TYPE")
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	@Column(name = "OPERATE_DES")
	public String getOperateDes() {
		return operateDes;
	}

	public void setOperateDes(String operateDes) {
		this.operateDes = operateDes;
	}

	@Column(name = "OPERATE_TIME")
	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	@Override
	public String toString() {
		return "日志编号：" + logId + "用户编号：" + userId + "操作类型：" + operateType
				+ "操作描述：" + operateDes + "操作日期：" + operateTime;
	}
}
