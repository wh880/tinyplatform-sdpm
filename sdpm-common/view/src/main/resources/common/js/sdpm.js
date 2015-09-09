//配合sdpmItem校验

$(function () {
    $("form[name=thisform").Validform({
        tiptype: function (msg, o, cssctl) {
            var objtip = o.obj.parent().parent().siblings(".validmsg");
            cssctl(objtip, o.type);
            objtip.text(msg);
        },
    });
});