package com.desire.wuye.controller;

import com.desire.wuye.model.User;
import com.desire.wuye.model.Zhuhu;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;

/**
 * Created by Administrator on 14-4-16.
 */
@ControllerBind(controllerKey = "/")
public class IndexController extends Controller {
    public void index(){
        redirect("login");
    }

    public void login(){

    }
    public void check(){
        User user= User.dao.findFirst("select * from user where username=?",getPara("username"));



        if(user!=null&&user.getStr("password").equals(this.getPara("password")))
        {
            this.setSessionAttr("username", this.getPara("username"));
            this.setSessionAttr("role", "admin");
            this.setSessionAttr("id", user.getInt("id"));
            setAttr("msg","admin/zhuhu");
        }
        else{
            Zhuhu u= Zhuhu.dao.findFirst("select * from zhuhu where logname=?",this.getPara("username"));
            if(u!=null&&u.getStr("password").equals(this.getPara("password")))
            {
                this.setSessionAttr("username", this.getPara("username"));
                this.setSessionAttr("role", "user");
                this.setSessionAttr("id", u.getInt("id"));
                setAttr("msg","user/zhuhu");
            }else setAttr("msg","error");
        }
        renderJson();

    }
    public void logout(){
        this.removeSessionAttr("role");
        redirect("/");
    }

    public void change(){
        if(getSessionAttr("role")!=null){
        if(getSessionAttr("role").equals("admin")){
            User.dao.findById(getSessionAttr("id")).set("password",getPara("ps")).update();
            setAttr("msg","修改成功！");
        }else if(getSessionAttr("role").equals("user")){
            Zhuhu.dao.findById(getSessionAttr("id")).set("password",getPara("ps")).update();
            setAttr("msg","修改成功！");
        }
        }else setAttr("msg","错误！");
        renderJson();
    }
}
