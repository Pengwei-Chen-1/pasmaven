package com.cpw.action;

import java.util.List;
import java.util.Map;

import com.cpw.factory.ServiceFactory;
import com.cpw.pojo.FirstLevel;
import com.cpw.pojo.UserInfo;
import com.cpw.pojo.UserLogin;
import com.cpw.service.ITallyService;
import com.cpw.service.IUserService;
import com.cpw.service.impl.TallyServiceImpl;
import com.cpw.service.impl.UserServiceImpl;
import com.cpw.util.LogUtil;
import com.cpw.util.MD5Util;
import com.cpw.util.SysConstant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport {

	IUserService userService = (UserServiceImpl) ServiceFactory.getService("user");
	ITallyService tallyServic = (TallyServiceImpl) ServiceFactory.getService("tally");

	private UserInfo userInfo;
	private UserLogin userLogin;
	private String messgae;

	public String getMessgae() {
		return messgae;
	}

	public void setMessgae(String messgae) {
		this.messgae = messgae;
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * 用户登录验证
	 * 
	 * @return 用户信息是否正确
	 */
	public String login() {
		String userName = userLogin.getUserName();
		String userPass = MD5Util.MD5(userLogin.getUserPass());
		UserLogin userLoginData = userService.getUserLoginByName(userName);
		UserInfo userInfo = userService.getUserInfoByName(userName);
		if (userLoginData == null) {
			setMessgae("wrongName");
			return "login_Fail";
		} else if (!userPass.equals(userLoginData.getUserPass())) {
			setMessgae("wrongPass");
			return "login_Fail";
		} else if (userPass.equals(userLoginData.getUserPass())) {
			SysConstant.SYS_USER_NAME = userLoginData.getUserName();
			SysConstant.SYS_USER_ID = userInfo.getUserId();
			LogUtil.addLogin();
			// 将用户名存入session中
			ActionContext actionContext = ActionContext.getContext();
			Map<String, Object> session = actionContext.getSession();
			session.put(SysConstant.SYS_NAME_TEXT, userLoginData.getUserName());

			String tempNickName = userLoginData.getUserName();
			if (userInfo.getNickName() != null && userInfo.getNickName() != "") {
				tempNickName = userInfo.getNickName();
			}
			session.put(SysConstant.SYS_NICK_TEXT, tempNickName);
			return "login_suc";
		}
		return "login_Fail";
	}

	/**
	 * 用户信息注册
	 * 
	 * @return
	 */
	public String register() {
		UserLogin userLoginTemp = new UserLogin();
		userLoginTemp.setUserName(userLogin.getUserName());
		userLoginTemp.setUserPass(MD5Util.MD5(userLogin.getUserPass()));
		userService.addUserInfoAndLogin(userLoginTemp);
		int userId = userService.getUserInfoByName(userLoginTemp.getUserName()).getUserId();
		SysConstant.SYS_USER_ID = userId;
		initData();
		setMessgae("registerSuccess");
		return "register_suc";
	}

	/**
	 * 初始化用户数据
	 */
	public void initData() {
		// 添加账户
		userService.addAcount("现金账户");
		userService.addAcount("银行卡");
		userService.addAcount("支付宝");
		userService.addAcount("信用卡");
		userService.addAcount("其他");
		// 添加商家
		userService.addBusiness("餐厅");
		userService.addBusiness("超市");
		userService.addBusiness("银行");
		userService.addBusiness("其他");
		// 添加家庭成员
		userService.addFamily("本人");
		userService.addFamily("配偶");
		userService.addFamily("子女");
		userService.addFamily("父母");
		userService.addFamily("家庭公用");
		// 添加项目
		userService.addProject("日常花销");
		userService.addProject("出差");
		userService.addProject("旅游");
		userService.addProject("过年");
		userService.addProject("其他");
		// 添加一级分类
		userService.addFirstLevel("食品酒水", "O");
		userService.addFirstLevel("衣服饰品", "O");
		userService.addFirstLevel("行车交通", "O");
		userService.addFirstLevel("居家物业", "O");
		userService.addFirstLevel("其他杂项", "O");
		userService.addFirstLevel("职业收入", "I");
		userService.addFirstLevel("其他收入", "I");
		// 添加二级分类
		List<FirstLevel> allFirstList = tallyServic.getAllFirstLevel();
		for (FirstLevel firstLevel : allFirstList) {
			if (firstLevel.getFirstLevelName().equals("食品酒水")) {
				userService.addSecondLevel("早午晚餐", firstLevel);
				userService.addSecondLevel("水果零食", firstLevel);
				userService.addSecondLevel("烟酒茶", firstLevel);
			} else if (firstLevel.getFirstLevelName().equals("衣服饰品")) {
				userService.addSecondLevel("衣服裤子", firstLevel);
				userService.addSecondLevel("鞋帽包包", firstLevel);
				userService.addSecondLevel("化妆饰品", firstLevel);
			} else if (firstLevel.getFirstLevelName().equals("行车交通")) {
				userService.addSecondLevel("公共交通", firstLevel);
				userService.addSecondLevel("打车租车", firstLevel);
				userService.addSecondLevel("私家车费用", firstLevel);
			} else if (firstLevel.getFirstLevelName().equals("居家物业")) {
				userService.addSecondLevel("日常用品", firstLevel);
				userService.addSecondLevel("水电煤气", firstLevel);
				userService.addSecondLevel("物业管理", firstLevel);
			} else if (firstLevel.getFirstLevelName().equals("其他杂项")) {
				userService.addSecondLevel("其他支出", firstLevel);
				userService.addSecondLevel("意外丢失", firstLevel);
				userService.addSecondLevel("烂账损失", firstLevel);
			} else if (firstLevel.getFirstLevelName().equals("职业收入")) {
				userService.addSecondLevel("工资收入", firstLevel);
				userService.addSecondLevel("补助", firstLevel);
				userService.addSecondLevel("加班收入", firstLevel);
				userService.addSecondLevel("兼职收入", firstLevel);
			} else if (firstLevel.getFirstLevelName().equals("其他收入")) {
				userService.addSecondLevel("礼金收入", firstLevel);
				userService.addSecondLevel("中奖收入", firstLevel);
				userService.addSecondLevel("意外来钱", firstLevel);
			}
		}
	}

	/**
	 * 用户退出
	 * 
	 * @return
	 */
	public String logout() {
		LogUtil.addLogout();
		// 清除session中数据
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.clear();

		SysConstant.SYS_USER_NAME = "";
		SysConstant.SYS_USER_ID = 0;
		return "logout_suc";
	}
}
