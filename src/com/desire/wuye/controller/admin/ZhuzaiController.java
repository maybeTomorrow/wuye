package com.desire.wuye.controller.admin;

import com.desire.wuye.controller.BaseController;
import com.desire.wuye.controller.BaseInterface;
import com.desire.wuye.model.Zhuzai;
import com.jfinal.ext.route.ControllerBind;

/**
 * Created by Administrator on 14-4-13.
 */
@ControllerBind(controllerKey = "/admin/zhuzai")
public class ZhuzaiController extends BaseController implements BaseInterface {

    @Override
    public void add() {
    }

    @Override
    public void delete() {
        Zhuzai.dao.deleteById(getPara());
        setAttr("msg", "删除成功！");
        renderJson();
    }

    @Override
    public void create() {
        getModel(Zhuzai.class).save();
        setAttr("msg","保存成功！");
        renderJson();
    }

    @Override
    public void edit() {
        setAttr("zhuzai",Zhuzai.dao.findById(getPara()));
    }

    @Override
    public void update() {
        getModel(Zhuzai.class).update();
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
            setAttr("zhuzai", Zhuzai.dao.paginate(a,PAGE_SIZE,"select * ","from zhuzai where gs like ? or bm like ? or mc like ? or dz like ? or kgs like ? or fzr like ? or lxr like ? or dz like ?  "
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query
                    ,query));
        }
        else {setAttr("zhuzai", Zhuzai.dao.paginate(a,PAGE_SIZE,"select * ","from zhuzai"));
        }
    }

    @Override
    public void view() {
        setAttr("zhuzai",Zhuzai.dao.findById(getPara()));
    }

    @Override
    public void change() {

    }
}
