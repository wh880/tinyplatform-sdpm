//配合sdpmItem校验

$(function () {
    $("#tinypagecontent").on("click", "[data-removeid]", function (e) {
        var url = $(this).attr("href") ? $(this).attr("href") : window.location.href;
        console.log($(this).attr("data-action"));
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