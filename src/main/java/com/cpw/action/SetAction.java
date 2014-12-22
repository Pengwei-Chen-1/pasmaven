package com.cpw.action;

import java.util.ArrayList;
import java.util.List;

import com.cpw.dto.PageInfo;
import com.cpw.factory.ServiceFactory;
import com.cpw.pojo.LogInfo;
import com.cpw.pojo.UserInfo;
import com.cpw.service.ISetService;
import com.cpw.service.impl.SetServiceImpl;
import com.cpw.util.SysConstant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 设置action
 * 
 * @author pengwei_chen
 * @date 2014-3-30 下午12:18:01
 */
@SuppressWarnings("serial")
public class SetAction extends ActionSupport {

	ISetService setService = (SetServiceImpl) ServiceFactory.getService("set");

	private String msg;
	private String pageNum;

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String init() {
		setMsg("set");
		ActionContext context = ActionContext.getContext();
		context.put("userInfo", getUserInfo());
		context.put("logList", getLogInfo());
		context.put("pageInfo", getPageInfo());
		if (getPageNum() != null) {
			context.put("flag", "log");
		}
		return SUCCESS;
	}

	/**
	 * 获取用户信息
	 * 
	 * @return 用户信息
	 */
	public UserInfo getUserInfo() {
		UserInfo userInfo = new UserInfo();
		int userId = SysConstant.SYS_USER_ID;
		userInfo = setService.getUserInfoById(userId);
		return userInfo;
	}

	/**
	 * 获取日志信息
	 * 
	 * @return
	 */
	public List<LogInfo> getLogInfo() {
		int pageNum = 1;
		if (getPageNum() != null) {
			pageNum = Integer.parseInt(getPageNum());
		}
		List<LogInfo> logList = new ArrayList<LogInfo>();
		logList = setService.getLogInfo(pageNum, SysConstant.PAGE_SIZE);
		return logList;
	}

	/**
	 * 获取分页信息
	 * 
	 * @return
	 */
	public PageInfo getPageInfo() {
		PageInfo pageInfo = new PageInfo();
		int pageNum = 1;
		if (getPageNum() != null) {
			pageNum = Integer.parseInt(getPageNum());
		}
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(SysConstant.PAGE_SIZE);
		pageInfo.setCount(setService.getLogCount());
		return pageInfo;
	}
}
