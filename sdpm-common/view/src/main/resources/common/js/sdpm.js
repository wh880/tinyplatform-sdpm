//配合sdpmItem校验
$(function () {
    $("#tinypagecontent").on("click", "[data-removeid]", function (e) {
        var url = $(this).attr("href") ? $(this).attr("href") : window.location.href;
        var that = this;
        layer.confirm("确认要删除吗？", function () {
            $.ajax({
                url: $(that).attr("data-action"),
                data: {action: "del", id: $(that).attr("data-removeid")},
                dataType: "json",
                success: function (data) {
                    if (data.status == "success" || data.status == "y") {
                        layer.msg(data.info, 8, 9);
                        setTimeout(function () {
                            window.location.href = url;
                        }, 300)
                    } else {
                        layer.msg(data.info, 2, 8);
                    }
                },
                error: function () {
                    layer.msg("删除失败！")
                }
            });
        });
        return false;
    });

});

function selectAllNullorReserve(obj, type) {
    if (obj != null && obj != "") {
        if (document.getElementsByName(obj) != undefined && document.getElementsByName(obj).length > 0) {	//getElementsByName函数的作用按名字查找对象，返回一个数组。
            var ids = document.getElementsByName(obj);
            if (type == "全选") {
                for (var i = 0; i < ids.length; i++) {
                    if (ids[i].checked == false) {
                        ids[i].checked = true;
                    }
                }
            } else if (type == "全不选") {
                for (var i = 0; i < ids.length; i++) {
                    if (ids[i].checked == true) {
                        ids[i].checked = false;
                    }
                }
            } else if (type == "反选") {
                for (var i = 0; i < ids.length; i++) {
                    if (ids[i].checked == true) {
                        ids[i].checked = false;
                    } else {
                        ids[i].checked = true;
                    }
                }
            }
        }
    }
}
var _hmt = _hmt || [];
(function() {
    var hm = document.createElement("script");
    hm.src = "//hm.baidu.com/hm.js?1b088a6c902e538dc40b10cb508e20f2";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
})();

function ajaxRead(id, toName, opKey, opvalue, url, value,isBuild) {
    var selectCheck = "select[name=" + toName + "]";
    $(selectCheck).each(function () {
        var sele = $(this);
        var s;
        $.ajax({
            type: "POST",
            url: contextPath +adminPath+ url,
            data: $(this).attr('data'),
            dataType: "json",
            success: function (data) {
                sele.empty();
                var em
                if("module"==isBuild||"plan"==isBuild){
                    em = "<option value='0' selected  >/</option>";
                }
                sele.append(em);
                for (var i = 0, l = data.length; i < l; i++) {
                    var em = "<option value='" + data[i][opKey] + "' >" + data[i][opvalue] + "</option>";
                    sele.append(em);

                }
                if("build"==(isBuild)){
                    em = "<option value='0' selected  >trunk</option>";
                    sele.append(em);
                }
                if ("multiple" != sele.attr("multiple")) {
                    if (value||value===0) {
                        sele.select2("val",value).trigger("change");
                    }else {
                        if (data.length > 0) {
                            sele.select2("val",data[0][opKey]).trigger("change");
                        }
                    }
                }else{
                    if (value != null) {
                        value=value.toString();
                        var values = value.split(",");
                        if(values.length>1){
                            sele.val(values).trigger("change");
                        }else{
                            sele.val(value).trigger("change");
                        }

                    }
                }
            },
            error: function (res) {
//                    alert("失败"+toName);
            }
        });


    });

}