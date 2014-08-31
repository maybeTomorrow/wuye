package com.desire.wuye.config;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

import java.util.Enumeration;

/**
 * Created by Administrator on 14-4-28.
 */
public class ParaInterceptor implements Interceptor {

    @Override
    public void intercept(ActionInvocation ai) {
        Enumeration<String> e=ai.getController().getParaNames();
        while(e.hasMoreElements()){
            String p=e.nextElement();
            ai.getController().setAttr(p,ai.getController().getPara(p));
        }
        ai.invoke();
    }
}
