package com.desire.wuye.controller.admin;

import com.desire.wuye.controller.BaseController;
import com.desire.wuye.controller.BaseInterface;
import com.desire.wuye.model.Xinwen;
import com.jfinal.ext.route.ControllerBind;

/**
 * Created by Administrator on 14-4-13.
 */
@ControllerBind(controllerKey = "/admin/xinwen")
public class XinwenController extends BaseController implements BaseInterface {

    @Override
    public void add() {
    }

    @Override
    public void delete() {
        Xinwen.dao.deleteById(getPara());
        setAttr("msg", "删除成功！");
        renderJson();
    }

    @Override
    public void create() {
        getModel(Xinwen.class).save();
        setAttr("msg","保存成功！");
        renderJson();
    }

    @Override
    public void edit() {
        setAttr("xinwen",Xinwen.dao.findById(getPara()));
    }

    @Override
    public void update() {
        getModel(Xinwen.class).update();
        setAttr("msg", "保存成功！");
        renderJson();
    }

    @Override
    public void index() {
        int a=1;
        if(getParaToInt()!=null)
            a=getParaToInt();
        setAttr("xinwen", Xinwen.dao.paginate(a,PAGE_SIZE,"select * ","from xinwen"));
    }

    @Override
    public void view() {
        setAttr("xinwen",Xinwen.dao.findById(getPara()));
    }

    @Override
    public void change() {

    }
}
