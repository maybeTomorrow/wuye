package com.desire.wuye.controller.user;

import com.desire.wuye.controller.BaseController;
import com.desire.wuye.controller.BaseInterface;
import com.desire.wuye.model.Cewei;
import com.jfinal.ext.route.ControllerBind;

/**
 * Created by Administrator on 14-4-13.
 */
@ControllerBind(controllerKey = "/user/cewei")
public class CeweiController extends BaseController implements BaseInterface {

    @Override
    public void add() {
    }

    @Override
    public void delete() {
        Cewei.dao.deleteById(getPara());
        setAttr("msg", "删除成功！");
        renderJson();
    }

    @Override
    public void create() {
        getModel(Cewei.class).save();
        setAttr("msg","保存成功！");
        renderJson();
    }

    @Override
    public void edit() {
        setAttr("cewei",Cewei.dao.findById(getPara()));
    }

    @Override
    public void update() {
        getModel(Cewei.class).update();
        setAttr("msg", "保存成功！");
        renderJson();
    }

    @Override
    public void index() {
        int a=1;
        if(getParaToInt()!=null)
            a=getParaToInt();
        setAttr("cewei", Cewei.dao.paginate(a,PAGE_SIZE,"select c.* ","from zhuhu z, cewei c where z.no=c.zhuhu_no and z.id =?",getSessionAttr("id")));
    }

    @Override
    public void view() {
        setAttr("cewei",Cewei.dao.findById(getPara()));
    }

    @Override
    public void change() {

    }
}
