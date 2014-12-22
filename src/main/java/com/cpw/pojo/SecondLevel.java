package com.cpw.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 二级分类
 * 
 * @author pengwei_chen
 * @date 2014-3-10 下午2:07:55
 */
@Entity
@Table(name="pas_secondlevel")
public class SecondLevel {

	private int secondLevelId;
	private int userId;
	private String secondLevelName;
	private String image;
	private String ioType;
	private int firstLevelId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SECONDLEVEL_ID")
	public int getSecondLevelId() {
		return secondLevelId;
	}

	public void setSecondLevelId(int secondLevelId) {
		this.secondLevelId = secondLevelId;
	}

	@Column(name = "USER_ID")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "SECONDLEVEL_NAME")
	public String getSecondLevelName() {
		return secondLevelName;
	}

	public void setSecondLevelName(String secondLevelName) {
		this.secondLevelName = secondLevelName;
	}

	@Column(name = "IMAGE")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "IOTYPE")
	public String getIoType() {
		return ioType;
	}

	public void setIoType(String ioType) {
		this.ioType = ioType;
	}

	@Column(name = "FIRSTLEVEL_ID")
	public int getFirstLevelId() {
		return firstLevelId;
	}

	public void setFirstLevelId(int firstLevelId) {
		this.firstLevelId = firstLevelId;
	}

	@Override
	public String toString() {
		return "二级分类编号：" + secondLevelId + "用户编号：" + userId + "分类名称："
				+ secondLevelName + "图片：" + image + "收支类型：" + ioType
				+ "一级分类编号：" + firstLevelId;
	}
}
