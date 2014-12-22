package com.cpw.dto;

import java.util.List;

import com.cpw.pojo.SecondLevel;
import com.cpw.pojo.Spending;

public class IOTallyDto extends Spending {

	private String ioTallyType;
	private String secondLevelName;
	private String accountName;
	private String businessName;
	private String projectName;
	private List<SecondLevel> secondList;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<SecondLevel> getSecondList() {
		return secondList;
	}

	public void setSecondList(List<SecondLevel> secondList) {
		this.secondList = secondList;
	}

	public String getIoTallyType() {
		return ioTallyType;
	}

	public void setIoTallyType(String ioTallyType) {
		this.ioTallyType = ioTallyType;
	}

	public String getSecondLevelName() {
		return secondLevelName;
	}

	public void setSecondLevelName(String secondLevelName) {
		this.secondLevelName = secondLevelName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
}
