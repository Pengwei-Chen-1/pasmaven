package com.cpw.interceptor;

import java.util.Map;

import com.cpw.util.SysConstant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 登录验证拦截器，判断用户是否登录
 * 
 * @author pengwei_chen
 * @date 2014-4-15 下午1:12:41
 */
@SuppressWarnings("serial")
public class LoginInterceptor extends AbstractInterceptor {

	private static String LOGIN_PAGE = "login";

	@Override
	public String intercept(ActionInvocation ac) throws Exception {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		String userName = (String) session.get(SysConstant.SYS_NAME_TEXT);
		String actionName = ac.getInvocationContext().getName();
		// 如果是登录请求和注册，不拦截
		if (actionName.equals("user_login") || actionName.equals("user_register")) {
			return ac.invoke();
		} else if (userName == null || userName.equals("")) {
			return LOGIN_PAGE;
		}
		return ac.invoke();
	}
}
