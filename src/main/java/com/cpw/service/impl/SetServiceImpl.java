package com.cpw.service.impl;

import java.util.List;
import java.util.Map;

import com.cpw.dao.ILogInfoDao;
import com.cpw.dao.IUserInfoDao;
import com.cpw.dao.impl.LogInfoDaoImpl;
import com.cpw.dao.impl.UserInfoDaoImpl;
import com.cpw.factory.DaoFactory;
import com.cpw.pojo.LogInfo;
import com.cpw.pojo.UserInfo;
import com.cpw.service.ISetService;
import com.cpw.util.SysConstant;
import com.opensymphony.xwork2.ActionContext;

public class SetServiceImpl implements ISetService {

	IUserInfoDao userInfoDao = (UserInfoDaoImpl) DaoFactory.getDao("userInfo");
	ILogInfoDao logDao = (LogInfoDaoImpl) DaoFactory.getDao("logInfo");

	@Override
	public UserInfo getUserInfoById(int id) {
		return userInfoDao.getUserById(id);
	}

	@Override
	public void updateUserInfo(UserInfo userInfo) {
		// 修改用户session中的值
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		String tempNickName = userInfo.getUserName();
		if (userInfo.getNickName() != null && userInfo.getNickName() != ""){
			tempNickName = userInfo.getNickName();
		}
		session.put(SysConstant.SYS_NICK_TEXT, tempNickName);
		userInfoDao.updateUserInfo(userInfo);
	}

	@Override
	public List<LogInfo> getLogInfo(int pageNum, int pageSize) {
		int firstResult = (pageNum - 1) * pageSize + 1;
		return logDao.getLogInfo(firstResult, pageSize);
	}

	@Override
	public int getLogCount() {
		return logDao.getCount();
	}

	@Override
	public void delLogById(int logId) {
		LogInfo log = new LogInfo();
		log.setLogId(logId);
		logDao.delLogInfoById(log);
	}

}
