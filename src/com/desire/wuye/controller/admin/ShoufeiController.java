package com.desire.wuye.controller.admin;

import com.desire.wuye.controller.BaseController;
import com.desire.wuye.controller.BaseInterface;
import com.desire.wuye.model.Shoufei;
import com.jfinal.ext.route.ControllerBind;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 14-4-13.
 */
@ControllerBind(controllerKey = "/admin/shoufei")
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
        String from="from shoufei where 1=1 ";
        if(getPara("state")!=null&&!getPara("state").equals("")){
            from+=" and state like '%"+getPara("state")+"%'";
        }
        if(getPara("type")!=null&&!getPara("type").equals("")){
            from+=" and type like '%"+getPara("type")+"%'";
        }
        setAttr("shoufei", Shoufei.dao.paginate(a,PAGE_SIZE,"select * ",from));

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
        String no=getPara("no");
        int a=1;
        if(getParaToInt()!=null)
            a=getParaToInt();
        String from=" from shoufei where 1=1";

        if(date_b!=null&&!date_b.equals("")){
            from+=" and date >= '"+date_b+"'";
        }
        if(date_e!=null&&!date_e.equals("")){
            from+=" and date <= '"+date_e+"'";
        }

        if(state!=null&&!state.equals("")){
            from+=" and state ='"+state+"'";
        }

        if(type!=null&&!type.equals("")){
            from+=" and type ='"+type+"'";
        }
        if(no!=null&&!no.equals("")){
            from+=" and no ='"+no+"' or  name ='"+no+"'";
        }
        System .out .println(from);
        setAttr("tj2", Shoufei.dao.find("SELECT sum(cost) cost,DATE_FORMAT(date,'%Y-%m') dates "+from+" group by dates"));
        setAttr("tj1",Shoufei.dao.find("SELECT sum(cost) cost, type "+from+" group by type"));
        setAttr("tj3",Shoufei.dao.find("SELECT sum(cost) cost, state "+from+" group by state"));
        setAttr("shoufei", Shoufei.dao.paginate(a,PAGE_SIZE,"select * ",from));
        setAttr("zsf",Shoufei.dao.findFirst("select sum(cost) count" + from));
        if(getPara("print")!=null){
            render("print.html");
        }
    }

    public void yezhu(){
        String type=getPara("type");
        String state=getPara("state");
        String date_b=getPara("date_b");
        String date_e=getPara("date_e");
        String no=getPara("no");
        int a=1;
        if(getParaToInt()!=null)
            a=getParaToInt();
        String from=" from shoufei where 1=1";
        String from2=" from shoufei where 1=1";

        if(date_b!=null&&!date_b.equals("")){
            from+=" and date >= '"+date_b+"'";
        }
        if(date_e!=null&&!date_e.equals("")){
            from+=" and date <= '"+date_e+"'";
        }

        if(state!=null&&!state.equals("")){
            from+=" and state ='"+state+"'";
        }

        if(type!=null&&!type.equals("")){
            from+=" and type ='"+type+"'";
        }
        if(no!=null&&!no.equals("")){
            from+=" and no ='"+no+"' or  sfzh ='"+no+"'";
            from2+=" and no ='"+no+"' or  sfzh ='"+no+"'";
        }
        System .out .println(from);
        DateFormat df=new SimpleDateFormat("yyyy-MM");
        String nd=df.format(new Date());
        from2+=" and date like '"+nd+"%'";
        setAttr("tj3",Shoufei.dao.find("SELECT sum(cost) cost, type "+from2+" group by type"));
        setAttr("tj2", Shoufei.dao.find("SELECT sum(cost) cost,DATE_FORMAT(date,'%Y-%m') dates "+from+" group by dates"));
        setAttr("tj1",Shoufei.dao.find("SELECT sum(cost) cost, type "+from+" group by type"));
        setAttr("shoufei", Shoufei.dao.paginate(a,PAGE_SIZE,"select * ",from));
        setAttr("zsf",Shoufei.dao.findFirst("select sum(cost) count" + from));
        if(getPara("print")!=null){
            render("print.html");
        }
    }


    public void yue(){
        String date=getPara("date");
        if(date!=null){
            date=date.substring(0,7);
        }else {
            DateFormat df=new SimpleDateFormat("yyyy-MM");
            date=df.format(new Date());
        }
        System.out.println(date);
        int a=1;
        if(getParaToInt()!=null)
            a=getParaToInt();
        String from=" from shoufei where date like '"+date+"%' and state = '已缴' ";

        setAttr("tj1", Shoufei.dao.find("SELECT sum(cost) cost, type "+from+" group by type"));

        setAttr("shoufei", Shoufei.dao.paginate(a, PAGE_SIZE, "select * ", from));
        setAttr("zsf",Shoufei.dao.findFirst("select sum(cost) count"+from ));
    }


    public void tixing(){
        String sql="select * FROM shoufei where state='未缴'";
        setAttr("shoufei",Shoufei.dao.find(sql));
    }
}
