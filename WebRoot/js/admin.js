/**
 * Created by Administrator on 14-4-16.
 */
$(document).ready(function(){
      $('.picker').calendar();
    $("#page-select").change(function(){
        var i=$(this);
        var para="";
        var method="";
        if($("#method").val()!='index/'){
            method=$("#method").val();
        }
        if(window.location.href.indexOf("?")!=-1){
            para="?"+window.location.href.replace(/.*\?/,"");
        }
        window.location.href=$("#flag").val()+method+i.val()+para;
    });
    $("select").each(function(){
        var s=$(this);
    if(s.attr("data")!="undefined"&&s.attr("data")!=""){
        s.find('option[value="'+$(this).attr("data")+'"]').attr("selected","selected");
    }
    });
    $('input[type="radio"]').each(function(){
        var s=$(this);
        if(s.parent().attr("data")!="undefined"&&s.parent().attr("data")== s.val()){
            s.attr("checked","checked");
        }
    });
    $("#state-select").change(function(){
        var i=$("#page-select");
        window.location.href=$("#flag").val()+i.val()+"?state="+$(this).val()+"&type="+$("#type-select").val();
    });
    if($("#type-select").attr("data")!=""){
        $('#type-select option[value="'+$("#type-select").attr("data")+'"]').attr("selected","selected");
    }
    $("#type-select").change(function(){
        var i=$("#page-select");
        window.location.href=$("#flag").val()+i.val()+"?type="+$(this).val()+"&state="+$("#state-select").val();
    });

    $(".linker li").bind("click",function(event){
        window.location.href=$(this).attr("href");

        event.stopPropagation();    //  阻止事件冒泡
    });


    $(".btn_active").click(function(){
        var id=$(this).parent().attr("data");
        if($(this).attr("active")=='delete'){
            if(confirm("确定删除？")){
                post($("#flag").val()+$(this).attr("active")+"/"+id,{})
                return true;
            }else{
                return false;
            }
        }
        if($(this).attr("active")=='change'){
            post($("#flag").val()+$(this).attr("active")+"/"+id,{"state":$(this).text()});
            return true;
        }
        window.location.href=$("#flag").val()+$(this).attr("active")+"/"+id;
    });


    $("#change_password").click(function(){
        var new_ps=prompt("输入新密码！");
        if(new_ps&&new_ps!=''){
            post("change",{'ps':new_ps});
        }
    });
});

function setPager(pageNumber,totalPage){
    pageNumber=Number(pageNumber);
    totalPage=Number(totalPage);
    if(pageNumber==''||pageNumber==0)
    {
        pageNumber=1;
    }
    if(totalPage==''||totalPage==0)
    {
        totalPage=1;
    }
    for(var i=1;i<=totalPage;i++){
        $("#page-select").append(new Option(i,i));
    }
    $("#page-select option[value='"+pageNumber+"']").attr("selected","selected");
}

function alertMsg(msg){
    if(msg==''){
        return false;
    }
    else alert(msg);
}

function subform(form){
    var form=$(form);
    post(form.attr("action"),form.serialize());
}

function post(url,data){
    $.ajax({
        url:url,
        type:'post',
        async:false,
        data:data,
        dataType:'json',
        error:function(){alert("网络错误")},
        success:function(rs){
            alert(rs.msg);

            window.location.reload();
        }
    });
}


