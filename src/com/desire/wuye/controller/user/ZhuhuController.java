package com.desire.wuye.controller.user;

import com.desire.wuye.controller.BaseController;
import com.desire.wuye.controller.BaseInterface;
import com.desire.wuye.model.Zhuhu;
import com.jfinal.ext.route.ControllerBind;

/**
 * Created by Administrator on 14-4-13.
 */
@ControllerBind(controllerKey = "/user/zhuhu")
public class ZhuhuController extends BaseController implements BaseInterface {

    @Override
    public void add() {
    }

    @Override
    public void delete() {
        Zhuhu.dao.deleteById(getPara());
        setAttr("msg", "删除成功！");
        renderJson();
    }

    @Override
    public void create() {
        getModel(Zhuhu.class).save();
        setAttr("msg","保存成功！");
        renderJson();
    }

    @Override
    public void edit() {
        setAttr("zhuhu",Zhuhu.dao.findById(getSessionAttr("id")));
    }

    @Override
    public void update() {
        getModel(Zhuhu.class).update();
        setAttr("msg", "保存成功！");
        renderJson();
    }

    @Override
    public void index() {
        setAttr("zhuhu", Zhuhu.dao.findById(getSessionAttr("id")));
    }

    @Override
    public void view() {
        setAttr("zhuhu",Zhuhu.dao.findById(getPara()));
    }

    @Override
    public void change() {

    }
}
