package com.desire.wuye.controller.user;

import com.desire.wuye.controller.BaseController;
import com.desire.wuye.controller.BaseInterface;
import com.desire.wuye.model.Renshi;
import com.jfinal.ext.route.ControllerBind;

/**
 * Created by Administrator on 14-4-13.
 */
@ControllerBind(controllerKey = "/user/renshi")
public class RenshiController extends BaseController implements BaseInterface {

    @Override
    public void add() {
    }

    @Override
    public void delete() {
        Renshi.dao.deleteById(getPara());
        setAttr("msg", "删除成功！");
        renderJson();
    }

    @Override
    public void create() {
        getModel(Renshi.class).save();
        setAttr("msg","保存成功！");
        renderJson();
    }

    @Override
    public void edit() {
        setAttr("renshi",Renshi.dao.findById(getPara()));
    }

    @Override
    public void update() {
        getModel(Renshi.class).update();
        setAttr("msg", "保存成功！");
        renderJson();
    }

    @Override
    public void index() {
        int a=1;
        if(getParaToInt()!=null)
            a=getParaToInt();
        setAttr("renshi", Renshi.dao.paginate(a,PAGE_SIZE,"select * ","from renshi"));
    }

    @Override
    public void view() {
        setAttr("renshi",Renshi.dao.findById(getPara()));
    }

    @Override
    public void change() {

    }
}
