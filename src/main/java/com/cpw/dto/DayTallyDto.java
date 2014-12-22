package com.cpw.dto;

import java.util.List;

public class DayTallyDto {

	private String month;
	private String day;
	private double spendingMoney;
	private double incomeMoney;
	private List<IOTallyDto> ioTallyList;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public double getSpendingMoney() {
		return spendingMoney;
	}

	public void setSpendingMoney(double spendingMoney) {
		this.spendingMoney = spendingMoney;
	}

	public double getIncomeMoney() {
		return incomeMoney;
	}

	public void setIncomeMoney(double incomeMoney) {
		this.incomeMoney = incomeMoney;
	}

	public List<IOTallyDto> getIoTallyList() {
		return ioTallyList;
	}

	public void setIoTallyList(List<IOTallyDto> ioTallyList) {
		this.ioTallyList = ioTallyList;
	}
}
