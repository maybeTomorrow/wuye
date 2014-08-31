package com.desire.wuye.controller.admin;

import com.desire.wuye.controller.BaseController;
import com.desire.wuye.controller.BaseInterface;
import com.desire.wuye.model.Zhuhu;
import com.jfinal.ext.route.ControllerBind;

/**
 * Created by Administrator on 14-4-13.
 */
@ControllerBind(controllerKey = "/admin/zhuhu")
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
        setAttr("zhuhu",Zhuhu.dao.findById(getPara()));
    }

    @Override
    public void update() {
        getModel(Zhuhu.class).update();
        setAttr("msg", "保存成功！");
        renderJson();
    }

    @Override
    public void index() {
        int a=1;
        if(getParaToInt()!=null)
            a=getParaToInt();
        String query="%"+getPara("query")+"%";
        if(!query.equals("%null%")){
            setAttr("zhuhu", Zhuhu.dao.paginate(a,PAGE_SIZE,"select * ","from zhuhu where name like ? or phone like ? or mail like ? or sfzh like ? or type like ? or indate like ? or work like ? or no like ?  "
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query));
        }
        else {
            setAttr("zhuhu", Zhuhu.dao.paginate(a,PAGE_SIZE,"select * ","from zhuhu"));
        }
        if(getPara("print")!=null){
            render("print.html");
        }
    }

    @Override
    public void view() {
        setAttr("zhuhu",Zhuhu.dao.findById(getPara()));
    }

    @Override
    public void change() {

    }
}
