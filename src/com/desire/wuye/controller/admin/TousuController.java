package com.desire.wuye.controller.admin;

import com.desire.wuye.controller.BaseController;
import com.desire.wuye.controller.BaseInterface;
import com.desire.wuye.model.Tousu;
import com.jfinal.ext.route.ControllerBind;

/**
 * Created by Administrator on 14-4-13.
 */
@ControllerBind(controllerKey = "/admin/tousu")
public class TousuController extends BaseController implements BaseInterface {

    @Override
    public void add() {
    }

    @Override
    public void delete() {
        Tousu.dao.deleteById(getPara());
        setAttr("msg", "删除成功！");
        renderJson();
    }

    @Override
    public void create() {
        getModel(Tousu.class).save();
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
        String query="%"+getPara("query")+"%";
        if(!query.equals("%null%")){
            setAttr("tousu", Tousu.dao.paginate(a,PAGE_SIZE,"select * ","from tousu where no like ? or title like ? or address like ? or date like ? or contact like ? or phone like ? or state like ? or remark like ?  "
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query));
        }
        else {setAttr("tousu", Tousu.dao.paginate(a,PAGE_SIZE,"select * ","from tousu"));
        }
        if(getPara("print")!=null){
            render("print.html");
        }
    }

    @Override
    public void view() {
        setAttr("tousu",Tousu.dao.findById(getPara()));
    }

    @Override
    public void change() {
        Tousu.dao.findById(getPara()).set("state",getPara("state")).update();
        setAttr("msg", "保存成功！");
        renderJson();
    }
}
