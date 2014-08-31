package com.desire.wuye.controller.user;

import com.desire.wuye.controller.BaseController;
import com.desire.wuye.controller.BaseInterface;
import com.desire.wuye.model.Shoufei;
import com.jfinal.ext.route.ControllerBind;

/**
 * Created by Administrator on 14-4-13.
 */
@ControllerBind(controllerKey = "/user/shoufei")
public class ShoufeiController extends BaseController implements BaseInterface {

    @Override
    public void add() {
    }

    @Override
    public void delete() {
        Shoufei.dao.deleteById(getPara());
        setAttr("msg", "删除成功！");
        renderJson();
    }

    @Override
    public void create() {
        getModel(Shoufei.class).save();
        setAttr("msg","保存成功！");
        renderJson();
    }

    @Override
    public void edit() {
        setAttr("shoufei",Shoufei.dao.findById(getPara()));
    }

    @Override
    public void update() {
        getModel(Shoufei.class).update();
        setAttr("msg", "保存成功！");
        renderJson();
    }

    @Override
    public void index() {
        int a=1;
        if(getParaToInt()!=null)
            a=getParaToInt();
        String from="from  zhuhu z, shoufei s where z.no=s.no and z.id =? ";
        if(getPara("state")!=null&&!getPara("state").equals("")){
            from+=" and s.state like '%"+getPara("state")+"%'";
        }
        if(getPara("type")!=null&&!getPara("type").equals("")){
            from+=" and s.type like '%"+getPara("type")+"%'";
        }
        setAttr("shoufei", Shoufei.dao.paginate(a,PAGE_SIZE,"select * ",from,getSessionAttr("id")));


    }

    @Override
    public void view() {
        setAttr("shoufei",Shoufei.dao.findById(getPara()));
    }

    @Override
    public void change() {

    }
        public void zhang(){
            String type=getPara("type");
            String state=getPara("state");
            String date_b=getPara("date_b");
            String date_e=getPara("date_e");
            int a=1;
            if(getParaToInt()!=null)
                a=getParaToInt();
            String from="from zhuhu z, shoufei s where z.no=s.no and z.id =? ";
            String from2="from zhuhu z, shoufei s where z.no=s.no and z.id =? and s.state='已缴'";

            if(date_b!=null&&!date_b.equals("")){
                from+=" and s.date >= '"+date_b+"'";
                from2+=" and s.date >= '"+date_b+"'";
            }
            if(date_e!=null&&!date_e.equals("")){
                from+=" and s.date <= '"+date_e+"'";
                from2+=" and s.date <= '"+date_e+"'";
            }

            if(type!=null&&!type.equals("")){
                from+=" and s.type ='"+type+"'";
                from2+=" and s.type ='"+type+"'";
            }

            if(state!=null&&!state.equals("")){
                from+=" and s.state ='"+state+"'";

            }
            setAttr("tj", Shoufei.dao.find("SELECT sum(cost) cost, s.type types "+from2+" group by types",getSessionAttr("id")));
            setAttr("tj3",Shoufei.dao.find("SELECT sum(cost) cost, state "+from+" group by state",getSessionAttr("id")));
         //   setAttr("tj1",Shoufei.dao.find("SELECT sum(cost) cost,date dates "+from+" group by dates",getSessionAttr("id")));
         //   setAttr("tj2", Shoufei.dao.find("SELECT sum(cost) cost,DATE_FORMAT(date,'%Y-%m') dates "+from+" group by dates",getSessionAttr("id")));
            setAttr("shoufei", Shoufei.dao.paginate(a,PAGE_SIZE,"select * ",from,getSessionAttr("id")));
            setAttr("result",Shoufei.dao.findFirst("select sum(cost) count "+from,getSessionAttr("id")));
            if(getPara("print")!=null){
                render("print.html");
            }
        }

    public void fenxi(){
        String type=getPara("type");
        String date_b=getPara("date_b");
        String date_e=getPara("date_e");
        int a=1;
        if(getParaToInt()!=null)
            a=getParaToInt();
        String from="from zhuhu z, shoufei s where z.no=s.no and z.id =? and s.state ='已缴'";

        if(date_b!=null&&!date_b.equals("")){
            from+=" and date >= '"+date_b+"'";
        }
        if(date_e!=null&&!date_e.equals("")){
            from+=" and date <= '"+date_e+"'";
        }


        if(type!=null&&!type.equals("")){
            from+=" and s.type ='"+type+"'";
        }

        setAttr("tj", Shoufei.dao.find("SELECT sum(cost) cost, s.type types "+from+" group by types",getSessionAttr("id")));
        setAttr("tj2", Shoufei.dao.find("SELECT sum(cost) cost,DATE_FORMAT(date,'%Y-%m') dates "+from+" group by dates",getSessionAttr("id")));
        setAttr("shoufei", Shoufei.dao.paginate(a,PAGE_SIZE,"select * ",from,getSessionAttr("id")));
        setAttr("result",Shoufei.dao.findFirst("select sum(cost) count "+from,getSessionAttr("id")));
        if(getPara("print")!=null){
            render("print.html");
        }
    }
}
