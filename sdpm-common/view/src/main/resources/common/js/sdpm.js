//配合sdpmItem校验

$(function () {
    $("form[name=thisform").Validform({
        tiptype: function (msg, o, cssctl) {
            var objtip = o.obj.parent().parent().siblings(".validmsg");
            cssctl(objtip, o.type);
            objtip.text(msg);
        },
    });
    //删除按钮功能
    $("#tinypagecontent").on("click", "[data-removeid]", function (e) {
        var url = $(this).attr("href") ? $(this).attr("href") : window.location.href;
        $.ajax({
            url: $(this).attr("data-action"),
            data: {action: "del", id: $(this).attr("data-removeid")},
            dataType: "json",
            success: function (data) {
                if (data.status == "success") {
                    layer.msg(data.info);
                    setTimeout(function () {
                        window.location.href = url;
                    }, 300)
                } else {
                    layer.msg(data.info);
                }

            },
            error: function () {
                layer.msg("error")
            }
        })
        return false;
        /*var url=$(this).attr("href");
         layer.confirm("ddddd",function(){
         window.location.href=url;
         // console.log("fdsf")
         });
         return false;*/
    });
});