package com.desire.wuye.controller.user;

import com.desire.wuye.controller.BaseController;
import com.desire.wuye.controller.BaseInterface;
import com.desire.wuye.model.Fang;
import com.jfinal.ext.route.ControllerBind;

/**
 * Created by Administrator on 14-4-13.
 */
@ControllerBind(controllerKey = "/user/fang")
public class FangController extends BaseController implements BaseInterface {

    @Override
    public void add() {
    }

    @Override
    public void delete() {
        Fang.dao.deleteById(getPara());
        setAttr("msg", "删除成功！");
        renderJson();
    }

    @Override
    public void create() {
        getModel(Fang.class).save();
        setAttr("msg","保存成功！");
        renderJson();
    }

    @Override
    public void edit() {
        setAttr("fang",Fang.dao.findById(getPara()));
    }

    @Override
    public void update() {
        getModel(Fang.class).update();
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
            setAttr("fang", Fang.dao.paginate(a,PAGE_SIZE,"select * ","from fang where dylc like ? or fjzt like ? or fwcx like ? or fwxh like ? or htbh like ? or htrq like ? or fkfs like ? or sfzh like ?  "
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query));
        }
        else {setAttr("fang", Fang.dao.paginate(a,PAGE_SIZE,"select * ","from fang"));
        }
    }

    @Override
    public void view() {
        setAttr("fang",Fang.dao.findById(getPara()));
    }

    @Override
    public void change() {

    }
}
