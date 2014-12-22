package com.cpw.dto;

import java.util.List;

import com.cpw.pojo.SecondLevel;
import com.cpw.pojo.Template;

public class TemplateDto extends Template {

	private String secondLevelName;
	private String accountName;
	private List<SecondLevel> secondLevelList;

	public List<SecondLevel> getSecondLevelList() {
		return secondLevelList;
	}

	public void setSecondLevelList(List<SecondLevel> secondLevelList) {
		this.secondLevelList = secondLevelList;
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
}
