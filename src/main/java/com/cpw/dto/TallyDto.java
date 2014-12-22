package com.cpw.dto;

/**
 * 存放查询后的时间以及金额
 * 
 * @author pengwei_chen
 * @date 2014-4-23 上午8:58:41
 */
public class TallyDto {

	private String beginDate;
	private String endDate;
	private double allSpendingMoney;
	private double allIncomeMoney;

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public double getAllSpendingMoney() {
		return allSpendingMoney;
	}

	public void setAllSpendingMoney(double allSpendingMoney) {
		this.allSpendingMoney = allSpendingMoney;
	}

	public double getAllIncomeMoney() {
		return allIncomeMoney;
	}

	public void setAllIncomeMoney(double allIncomeMoney) {
		this.allIncomeMoney = allIncomeMoney;
	}
}
