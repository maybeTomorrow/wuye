package com.desire.wuye.controller.user;

import com.desire.wuye.controller.BaseController;
import com.desire.wuye.controller.BaseInterface;
import com.desire.wuye.model.Tousu;
import com.jfinal.ext.route.ControllerBind;

/**
 * Created by Administrator on 14-4-13.
 */
@ControllerBind(controllerKey = "/user/tousu")
public class TousuController extends BaseController implements BaseInterface {

    @Override
    public void add() {
    }
    public void xuzhi(){

    }

    @Override
    public void delete() {
        Tousu.dao.deleteById(getPara());
        setAttr("msg", "删除成功！");
        renderJson();
    }

    @Override
    public void create() {
        getModel(Tousu.class).set("zhuhu_id",getSessionAttr("id")).set("state","未受理").save();
        setAttr("msg","保存成功！");
        renderJson();
    }

    @Override
    public void edit() {
        setAttr("tousu",Tousu.dao.findById(getPara()));
    }

    @Override
    public void update() {
        getModel(Tousu.class).update();
        setAttr("msg", "保存成功！");
        renderJson();
    }

    @Override
    public void index() {
        int a=1;
        if(getParaToInt()!=null)
            a=getParaToInt();
        setAttr("tousu", Tousu.dao.paginate(a,PAGE_SIZE,"select * ","from tousu where zhuhu_id=?",getSessionAttr("id")));
    }

    @Override
    public void view() {
        setAttr("tousu",Tousu.dao.findById(getPara()));
    }

    @Override
    public void change() {
        Tousu.dao.findById(getPara()).set("pf",getPara("pf")).update();
        setAttr("msg", "保存成功！");
        renderJson();
    }

}
