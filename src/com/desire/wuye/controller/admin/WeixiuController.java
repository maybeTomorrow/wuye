package com.desire.wuye.controller.admin;

import com.desire.wuye.controller.BaseController;
import com.desire.wuye.controller.BaseInterface;
import com.desire.wuye.model.Weixiu;
import com.jfinal.ext.route.ControllerBind;

/**
 * Created by Administrator on 14-4-13.
 */
@ControllerBind(controllerKey = "/admin/weixiu")
public class WeixiuController extends BaseController implements BaseInterface {

    @Override
    public void add() {
    }

    @Override
    public void delete() {
        Weixiu.dao.deleteById(getPara());
        setAttr("msg", "删除成功！");
        renderJson();
    }

    @Override
    public void create() {
        getModel(Weixiu.class).save();
        setAttr("msg","保存成功！");
        renderJson();
    }

    @Override
    public void edit() {
        setAttr("weixiu",Weixiu.dao.findById(getPara()));
    }

    @Override
    public void update() {
        getModel(Weixiu.class).update();
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
            setAttr("weixiu", Weixiu.dao.paginate(a,PAGE_SIZE,"select * ","from weixiu where no like ? or title like ? or address like ? or date like ? or freedate like ? or contact like ? or phone like ? or state like ?  "
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query));
        }
        else {setAttr("weixiu", Weixiu.dao.paginate(a,PAGE_SIZE,"select * ","from weixiu"));
        }
        if(getPara("print")!=null){
            render("print.html");
        }
    }

    @Override
    public void view() {
        setAttr("weixiu",Weixiu.dao.findById(getPara()));
    }

    @Override
    public void change() {
        Weixiu.dao.findById(getPara()).set("state",getPara("state")).update();
        setAttr("msg", "保存成功！");
        renderJson();
    }
}
