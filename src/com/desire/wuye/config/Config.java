package com.desire.wuye.config;

import com.jfinal.config.*;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.plugin.tablebind.SimpleNameStyles;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.plugin.c3p0.C3p0Plugin;

public class Config extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true);
		me.setBaseViewPath("/WEB-INF/views");

	}
	

	@Override
	public void configRoute(Routes me) {
		me.add(new AutoBindRoutes());
	}

	@Override
	public void configPlugin(Plugins me) {
		C3p0Plugin cp = new C3p0Plugin("jdbc:mysql://127.0.0.1/wuye?characterEncoding=utf8","root", "123456");
		me.add(cp);
		AutoTableBindPlugin atbp = new AutoTableBindPlugin(cp,
				SimpleNameStyles.LOWER_UNDERLINE);
		me.add(atbp);
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
		me.add(new AllInterceptor());
        me.add(new LoginInterceptor());
        me.add(new ParaInterceptor());
	}

	@Override
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("ctx_path"));
	}

	public void main(String[] args){
	}
}
