package com.desire.wuye.config;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class LoginInterceptor implements Interceptor {
	public void intercept(ActionInvocation ai) {
		if(ai.getControllerKey().startsWith("/admin"))
		{
            String role=ai.getController().getSessionAttr("role");
            if(role!=null&&role.equals("admin")){
		    ai.invoke();
            }
            else ai.getController().redirect("/login");
		}else if(ai.getControllerKey().startsWith("/user"))
        {
            String role=ai.getController().getSessionAttr("role");
            if(role!=null&&role.equals("user")){
                ai.invoke();
            }
            else ai.getController().redirect("/login");
        }else ai.invoke();

}
}