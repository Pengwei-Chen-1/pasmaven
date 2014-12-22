package com.cpw.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 一级分类
 * 
 * @author pengwei_chen
 * @date 2014-3-10 上午10:24:37
 */
@Entity
@Table(name="pas_firstlevel")
public class FirstLevel {

	private int firstLevelId;
	private int userId;
	private String firstLevelName;
	private String image;
	private String ioType;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="FIRSTLEVEL_ID")
	public int getFirstLevelId() {
		return firstLevelId;
	}

	public void setFirstLevelId(int firstLevelId) {
		this.firstLevelId = firstLevelId;
	}

	@Column(name="USER_ID")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name="FIRSTLEVEL_NAME")
	public String getFirstLevelName() {
		return firstLevelName;
	}

	public void setFirstLevelName(String firstLevelName) {
		this.firstLevelName = firstLevelName;
	}

	@Column(name="IMAGE")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name="IOTYPE")
	public String getIoType() {
		return ioType;
	}

	public void setIoType(String ioType) {
		this.ioType = ioType;
	}

	@Override
	public String toString() {
		return "一级分类编号：" + firstLevelId + "用户编号：" + userId + "分类名称："
				+ firstLevelName + "图片：" + image + "收支类型：" + ioType;
	}

}
