package com.cpw.dto;

import java.util.List;

import com.cpw.pojo.SecondLevel;

/**
 * 一级分类
 * 
 * @author pengwei_chen
 * @date 2014-4-14 下午6:09:13
 */
public class FirstClassify {

	private int firstId;
	private String firstName;
	private String firstImage;
	private double firstMoney;
	private List<SecondLevel> secondList;

	public double getFirstMoney() {
		return firstMoney;
	}

	public void setFirstMoney(double firstMoney) {
		this.firstMoney = firstMoney;
	}

	public int getFirstId() {
		return firstId;
	}

	public void setFirstId(int firstId) {
		this.firstId = firstId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstImage() {
		return firstImage;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}

	public List<SecondLevel> getSecondList() {
		return secondList;
	}

	public void setSecondList(List<SecondLevel> secondList) {
		this.secondList = secondList;
	}
}
