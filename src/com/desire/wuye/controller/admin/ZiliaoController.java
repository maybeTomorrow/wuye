package com.desire.wuye.controller.admin;

import com.desire.wuye.controller.BaseController;
import com.desire.wuye.controller.BaseInterface;
import com.desire.wuye.model.Ziliao;
import com.jfinal.ext.route.ControllerBind;

/**
 * Created by Administrator on 14-4-13.
 */
@ControllerBind(controllerKey = "/admin/ziliao")
public class ZiliaoController extends BaseController implements BaseInterface {

    @Override
    public void add() {
    }

    @Override
    public void delete() {
        Ziliao.dao.deleteById(getPara());
        setAttr("msg", "删除成功！");
        renderJson();
    }

    @Override
    public void create() {
        getModel(Ziliao.class).save();
        setAttr("msg","保存成功！");
        renderJson();
    }

    @Override
    public void edit() {
        setAttr("ziliao",Ziliao.dao.findById(getPara()));
    }

    @Override
    public void update() {
        getModel(Ziliao.class).update();
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
            setAttr("ziliao", Ziliao.dao.paginate(a,PAGE_SIZE,"select * ","from ziliao where dylc like ? or fjzt like ? or fwcx like ? or fwxh like ? or htbh like ? or htrq like ? or fkfs like ? or sfzh like ?  "
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query));
        }
        else {setAttr("ziliao", Ziliao.dao.paginate(a,PAGE_SIZE,"select * ","from ziliao"));
        }
    }

    @Override
    public void view() {
        setAttr("ziliao",Ziliao.dao.findById(getPara()));
    }

    @Override
    public void change() {

    }
}
