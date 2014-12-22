package com.cpw.factory;

import com.cpw.service.impl.ClassifyServiceImpl;
import com.cpw.service.impl.FlexServiceImpl;
import com.cpw.service.impl.SetServiceImpl;
import com.cpw.service.impl.TallyServiceImpl;
import com.cpw.service.impl.UserServiceImpl;

/**
 * Service层工程模式类
 * @author pengwei_chen
 * @date 2014-3-18 下午5:53:33
 */
public class ServiceFactory {

	public static Object getService(String serviceName){
		Object baseService = null;
		if(serviceName.equals("user")){
			baseService = new UserServiceImpl();
		} else if(serviceName.equals("tally")){
			baseService = new TallyServiceImpl();
		} else if(serviceName.equals("flex")){
			baseService = new FlexServiceImpl();
		} else if(serviceName.equals("classify")){
			baseService = new ClassifyServiceImpl();
		} else if(serviceName.equals("set")){
			baseService = new SetServiceImpl();
		}
		return baseService;
	}
}
