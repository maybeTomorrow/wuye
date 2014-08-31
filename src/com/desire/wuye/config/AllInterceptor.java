package com.desire.wuye.config;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class AllInterceptor implements Interceptor {

	public void intercept(ActionInvocation ai) {
        ai.getController().setAttr("flag",ai.getControllerKey());
        ai.getController().setAttr("method",ai.getMethodName());
		ai.invoke();
}
}