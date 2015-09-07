$(function () {

    var storageSupported = (typeof(window.Storage) !== "undefined");

// Functions
//

    var reloadPage = function () {
        location.reload();
    }

    var testTheme = function (name) {
        for (var j = 1; j < demo_themes.length; j++) {
            if (demo_themes[j].name === name) {
                return demo_themes[j].name;
            }
        }
        return 'clean';
    }

    var loadDemoSettings = function () {
        var result = {
            fixed_navbar: false,
            fixed_menu: false,
            rtl: false,
            menu_right: false,
            position_fixed: false,
            footer_fixed: false,
            no_menu: false,
            tinycontainer: false,
            menupos: "left",
            mmstate: "expanded",
            theme: 'clean',
            tiny_FullScreen: false
        };

        if (storageSupported) {

            try {
                result.tiny_FullScreen = window.localStorage.tiny_FullScreen;
                result.fixed_navbar = (window.localStorage.demo_fixed_navbar && window.localStorage.demo_fixed_navbar === '1');
                result.fixed_menu = (window.localStorage.demo_fixed_menu && window.localStorage.demo_fixed_menu === '1');
                result.rtl = (window.localStorage.demo_rtl && window.localStorage.demo_rtl === '1');
                result.footer_fixed = (window.localStorage.demo_footer_fixed && window.localStorage.demo_footer_fixed === '1');
                result.position_fixed = (window.localStorage.demo_position_fixed && window.localStorage.demo_position_fixed === '1');
                //result.menu_right		= (window.localStorage.demo_menu_right && window.localStorage.demo_menu_right === '1');
                result.no_menu = (window.localStorage.demo_no_menu && window.localStorage.demo_no_menu === '1');
                result.tinycontainer = (window.localStorage.demo_tinycontainer && window.localStorage.demo_tinycontainer === '1');
                result.theme = testTheme((window.localStorage.demo_theme) ? window.localStorage.demo_theme : '');
                result.menupos = window.localStorage.demo_menupos ? window.localStorage.demo_menupos : "left";
                result.mmstate = window.localStorage.pa_mmstate ? window.localStorage.pa_mmstate : "expanded";

                return result;
            } catch (e) {
            }
        }

        var key, val, pos, demo_cookies = document.cookie.split(';');
        for (var i = 0, l = demo_cookies.length; i < l; i++) {

            pos = demo_cookies[i].indexOf('=');
            key = demo_cookies[i].substr(0, pos).replace(/^\s+|\s+$/g, '');
            val = demo_cookies[i].substr(pos + 1).replace(/^\s+|\s+$/g, '');

            if (key === 'demo_fixed_navbar') {
                result.fixed_navbar = (val === '1') ? true : false;

            } else if (key === 'demo_fixed_menu') {
                result.fixed_menu = (val === '1') ? true : false;

            } else if (key === 'demo_rtl') {
                result.rtl = (val === '1') ? true : false;

                //} else if (key === 'demo_menu_right') {
                //  result.menu_right = (val === '1') ? true : false;

            } else if (key === 'demo_position_fixed') {
                result.position_fixed = (val === '1') ? true : false;

            } else if (key === 'demo_theme') {
                result.theme = testTheme(val);
            } else if (key === 'demo_footer_fixed') {
                result.footer_fixed = (val === '1') ? true : false;
            } else if (key === 'demo_no_menu') {
                result.no_menu = (val === '1') ? true : false;
            } else if (key === 'demo_tinycontainer') {
                result.tinycontainer = (val === '1') ? true : false;
            } else if (key === 'demo_menupos') {
                result.menupos = val ? val : "left";
            } else if (key === 'pa_mmstate') {
                result.mmstate = val ? val : "expanded";
            }
        }

        return result;
    }

    var saveDemoSettings = function () {
        if (storageSupported) {
            try {
                window.localStorage.demo_fixed_navbar = escape((demo_settings.fixed_navbar) ? '1' : '0');
                window.localStorage.demo_fixed_menu = escape((demo_settings.fixed_menu) ? '1' : '0');
                window.localStorage.demo_rtl = escape((demo_settings.rtl) ? '1' : '0');
                window.localStorage.demo_position_fixed = escape((demo_settings.position_fixed) ? '1' : '0');
                //window.localStorage.demo_menu_right   = escape((demo_settings.menu_right) ? '1' : '0');
                window.localStorage.demo_theme = escape(demo_settings.theme);
                window.localStorage.demo_footer_fixed = escape((demo_settings.footer_fixed) ? '1' : '0');
                window.localStorage.demo_no_menu = escape((demo_settings.no_menu) ? '1' : '0');
                window.localStorage.demo_tinycontainer = escape((demo_settings.tinycontainer) ? '1' : '0');
                window.localStorage.demo_menupos = demo_settings.menupos;
                return;
            } catch (e) {
            }
        }

        document.cookie = 'demo_fixed_navbar=' + escape((demo_settings.fixed_navbar) ? '1' : '0');
        document.cookie = 'demo_fixed_menu=' + escape((demo_settings.fixed_menu) ? '1' : '0');
        document.cookie = 'demo_rtl=' + escape((demo_settings.rtl) ? '1' : '0');
        //document.cookie = 'demo_menu_right='   + escape((demo_settings.menu_right) ? '1' : '0');
        document.cookie = 'demo_position_fixed=' + escape((demo_settings.position_fixed) ? '1' : '0');
        document.cookie = 'demo_theme=' + escape(demo_settings.theme);
        document.cookie = 'demo_footer_fixed=' + escape(demo_settings.footer_fixed);
        document.cookie = 'demo_no_menu=' + escape(demo_settings.no_menu);
        document.cookie = 'demo_tinycontainer=' + escape(demo_settings.tinycontainer);
        document.cookie = 'demo_menupos=' + demo_settings.menupos;
    }

    var getThemesTemplate = function () {
        result = '';
        for (var i = 0, l = demo_themes.length - 1; i <= l; i++) {
            if (i % 2 == 0) {
                result = result + '<div class="demo-themes-row">';
                result = result + '<a href="#" class="demo-theme" data-theme="' + demo_themes[i].name + '"><div class="theme-preview"><img src="' + demo_themes[i].img + '" alt=""></div><div class="overlay"></div><span>' + demo_themes[i].title + '</span></a>';
            } else {
                result = result + '<a href="#" class="demo-theme" data-theme="' + demo_themes[i].name + '"><div class="theme-preview"><img src="' + demo_themes[i].img + '" alt=""></div><div class="overlay"></div><span>' + demo_themes[i].title + '</span></a>';
                result = result + '</div>';
            }
            if (i == l && i % 2 == 0) {
                result = result + '<div class="demo-theme"></div></div>';
            }
        }
        return result;
    }

    var activateTheme = function (btns) {
        document.body.className = document.body.className.replace(/theme\-[a-z0-9\-\_]+/ig, 'theme-' + demo_settings.theme);

        if (!btns) return;
        btns.removeClass('dark');
        if (demo_settings.theme != 'clean' && demo_settings.theme != 'white') {
            btns.addClass('dark');
        }
    }


// Load and apply settings
//

    var panel_width = 260;

    var demo_themes = [
        {name: 'default', title: '默认', img: contextPath + '/common/img/themes/default.png'},
        {name: 'clean', title: '深色', img: contextPath + '/common/img/themes/dark.png'},
        {name: 'asphalt', title: '沥青', img: contextPath + '/common/img/themes/asphalt.png'},
        {name: 'purple-hills', title: '紫色', img: contextPath + '/common/img/themes/purple-hills.png'},
        {name: 'adminflare', title: '深蓝', img: contextPath + '/common/img/themes/deep-blue.png'},
        {name: 'dust', title: '橙色', img: contextPath + '/common/img/themes/orange.png'},
        {name: 'frost', title: '浅蓝', img: contextPath + '/common/img/themes/light-blue.png'},
        {name: 'fresh', title: '绿色', img: contextPath + '/common/img/themes/green.png'},
        {name: 'silver', title: '黑色', img: contextPath + '/common/img/themes/black.png'},
        {name: 'white', title: '时尚', img: contextPath + '/common/img/themes/fashion.png'}
    ];

    var demo_settings = loadDemoSettings();
    if (demo_settings.tiny_FullScreen) {
        //launchFullScreen(document.documentElement);
    }
    if (demo_settings.fixed_navbar) {
        $("body").addClass("main-navbar-fixed")
    }

    if (demo_settings.fixed_menu) {
        $("body").addClass("main-menu-fixed")
    }


    if (demo_settings.menupos == "right") {
        $("body").addClass("main-menu-right")
    }
    if (demo_settings.position_fixed) {
        $("body").addClass("main-position-fixed")
    }
    if (demo_settings.tinycontainer) {
        $("body").addClass("tinycontainer")
    }
    if (demo_settings.footer_fixed) {
        $("body").addClass("footer-fixed")
    }
//collapsed


    if (demo_settings.menupos == "top") {
        $("body").addClass("menu-on-top")

    } else {
        if (demo_settings.mmstate == "collapsed") {
            $("body").addClass("mmc")
        }
    }
    activateTheme();
    init.push(function () {
        $("#main-menu").prepend('<div id="topmenurap" style="display:none">' + $("#main-menu #main-menu-inner").html() + '</div>');
        //var menucount = $(".navigation:first>li").size();
        //$("#topmenurap .navigation>li").css("width", 100 / menucount + "%");
        if (demo_settings.menupos == "top") {
            $("#main-menu #topmenurap").css("display", "block")
            $("#main-menu>.slimScrollDiv").css("display", "none")
        }
    })
    window.TinyAdmin.start(init);

// Templates
//

    var demo_styles = [
        '#demo-themes {',
        ' display:table;',
        ' table-layout:fixed;',
        ' width:100%;',
        ' border-bottom-left-radius: 5px;',
        ' overflow:hidden;',
        '}',
        '#demo-themes .demo-themes-row {',
        ' display:block;',
        '}',
        '#demo-themes .demo-theme {',
        ' display: block;',
        ' float:left;',
        ' text-align:center;',
        ' width:50%;',
        ' padding:5px 0;',
        ' margin-top:1px;',
        ' margin-bottom:1px;',
        ' border-right:#555 1px dashed;',
        ' border-bottom:#555 1px dashed;',
        ' color:#888;',
        ' position:relative;',
        ' overflow:hidden;',
        '}',
        '#demo-themes .demo-theme:hover {',
        ' color:#fff;',
        '}',
        '#demo-themes .theme-preview {',
        ' width:100%;',
        ' position:absolute;',
        ' top:0;',
        ' left:0;',
        ' bottom:0;',
        ' overflow:hidden !important;',
        '}',
        '#demo-themes .theme-preview img {',
        ' width:100%;',
        ' position:absolute;',
        ' display:block;',
        ' top:0;',
        ' left:0;',
        '}',
        '#demo-themes .demo-theme .overlay {',
        ' background:#1d1d1d;',
        ' background:rgba(0,0,0,.8);',
        ' position:absolute;',
        ' top:0;',
        ' bottom:0;',
        ' left:-100%;',
        ' width:100%;',
        '}',
        '#demo-themes .demo-theme span {',
        ' position:relative;',
        ' color:#fff;',
        ' color:rgba(255,255,255,0);',
        '}',
        '#demo-themes .demo-theme span,',
        '#demo-themes .demo-theme .overlay {',
        ' -webkit-transition: all .3s;',
        ' -moz-transition: all .3s;',
        ' -ms-transition: all .3s;',
        ' -o-transition: all .3s;',
        ' transition: all .3s;',
        '}',
        '#demo-themes .demo-theme.active span,',
        '#demo-themes .demo-theme:hover span {',
        ' color: #fff;',
        ' color: rgba(255,255,255,1);',
        '}',
        '#demo-themes .demo-theme.active .overlay,',
        '#demo-themes .demo-theme:hover .overlay {',
        ' left: 0;',
        '}',
        '#demo-settings {',
        ' position: absolute;',
        ' right: -' + (panel_width + 30) + 'px;',
        ' width: ' + (panel_width + 10) + 'px;',
        ' top:-9px;',
        ' padding-right: 10px;  ',
        ' background: #333;',
        ' border-radius: 5px;',
        /* ' -webkit-transition: all .3s;',
         ' -moz-transition: all .3s;',
         ' -ms-transition: all .3s;',
         ' -o-transition: all .3s;',
         ' transition: all .3s;',*/
        ' -webkit-touch-callout: none;',
        ' -webkit-user-select: none;',
        ' -khtml-user-select: none;',
        ' -moz-user-select: none;',
        ' -ms-user-select: none;',
        ' user-select: none;',
        ' z-index: 1002;',
        '}',
        '.main-position-fixed #demo-settings{position:fixed}',
        '#demo-settings.open {',
        ' right: -20px;',
        '}',
        '#demo-settings > .header {',
        ' position: relative;',
        ' z-index: 10000;',
        ' line-height: 20px;',
        ' background: #444;',
        ' color: #fff;',
        ' font-size:  14px;',
        ' font-weight: 600;',
        ' padding: 10px 20px;',
        ' margin: 0;',
        '}',
        '#demo-settings > div {',
        ' position: relative;',
        ' z-index: 10000;',
        ' background: #282828;',
        ' border-bottom-right-radius: 5px;',
        ' border-bottom-left-radius: 5px;',
        '}',
        '#demo-settings-toggler {',
        ' font-size: 21px;',
        ' width: 50px;',
        ' height: 40px;',
        ' padding-right: 10px;',
        ' position: absolute;',
        ' left: -40px;',
        ' top: 0;',
        ' background: #444;',
        ' text-align: center;',
        ' line-height: 40px;',
        ' color: #fff;',
        ' border-radius: 5px;',
        ' z-index: 998;',
        ' text-decoration: none !important;',
        ' -webkit-transition: color .3s;',
        ' -moz-transition: color .3s;',
        ' -ms-transition: color .3s;',
        ' -o-transition: color .3s;',
        ' transition: color .3s;',
        '}',
        '#demo-settings.open #demo-settings-toggler {',
        ' font-size: 16px;',
        ' color: #eee;',
        '}',
        '#demo-settings.open #demo-settings-toggler:hover {',
        ' color: #fff;',
        '}',
        '#demo-settings.open #demo-settings-toggler i:before {',
        ' content: "\\f00d";',
        '}',
        '#demo-settings-list {',
        ' padding: 0;',
        ' margin: 0;',
        '}',
        '#demo-settings-list li {',
        ' padding: 0;',
        ' margin: 0;',
        ' list-style: none;',
        ' position: relative;',
        '}',
        '#demo-settings-list li > span {',
        ' line-height: 20px;',
        ' color: #fff;',
        ' display: block;',
        ' padding: 12px 20px;',
        ' cursor: pointer;',
        '}',
        '#demo-settings-list li + li {',
        ' border-top: 1px solid #333;',
        '}',
        '#demo-settings .demo-checkbox {',
        ' position: absolute;',
        ' right: 20px;',
        ' top: 12px;',
        '}',
        '.right-to-left #demo-settings {',
        ' left: -270px;',
        ' right: auto;',
        ' padding-right: 0;',
        ' padding-left: 10px;',
        '}',
        '.right-to-left #demo-settings.open {',
        ' left: -10px;',
        ' right: auto;',
        '}',
        '.right-to-left #demo-settings-toggler {',
        ' padding-left: 10px;',
        ' padding-right: 0;',
        ' left: auto;',
        ' right: -40px;',
        '}',
        '.right-to-left #demo-settings .demo-checkbox {',
        //' right: auto;',
        //' left: 20px;',
        '}',
        '.demo-checkbox {color:#fff',

        '}'
    ];

    var demo_template = [
        '<div id="demo-settings">',
        ' <a href="#" id="demo-settings-toggler"><i class="fa fa-gear fa-spin"></i></a>',
        ' <h5 class="header">设置选项</h5>',
        ' <div>',
        '   <ul id="demo-settings-list">',
        '     <li class="clearfix">',
        '       <span>固定导航条</span>',
        '       <div class="demo-checkbox"><input type="checkbox" id="demo-fixed-navbar" class="demo-settings-switcher" data-class="switcher-sm"' + ((demo_settings.fixed_navbar) ? ' checked="checked"' : '' ) + '></div>',
        '     </li>',
        '     <li class="clearfix">',
        '       <span>固定主菜单</span>',
        '       <div class="demo-checkbox"><input type="checkbox" id="demo-fixed-menu" class="demo-settings-switcher" data-class="switcher-sm"' + ((demo_settings.fixed_menu) ? ' checked="checked"' : '' ) + '></div>',
        '     </li>',
        //'     <li class="clearfix">',
        //'       <span>左右换位置</span>',
        // '       <div class="demo-checkbox"><input type="checkbox" id="demo-rtl" class="demo-settings-switcher" data-class="switcher-sm"' + ((demo_settings.rtl) ? ' checked="checked"' : '' ) + '></div>',
        // '     </li>',

        '     <li class="clearfix">',
        '       <span>固定导航菜单</span>',
        '       <div class="demo-checkbox"><input type="checkbox" id="demo-position-fixed" class="demo-settings-switcher" data-class="switcher-sm"' + ((demo_settings.position_fixed) ? ' checked="checked"' : '' ) + '></div>',
        '     </li>',
        '     <li class="clearfix">',
        '       <span>固定页脚</span>',
        '       <div class="demo-checkbox"><input type="checkbox" id="demo-footer-fixed" class="demo-settings-switcher" data-class="switcher-sm"' + ((demo_settings.footer_fixed) ? ' checked="checked"' : '' ) + '></div>',
        '     </li>',
        '     <li class="clearfix">',
        '       <span>菜单位置</span>',
        '       <div class="demo-checkbox"> <input type="radio" name="navposition" id="navpositiontop" value="top"' + ((demo_settings.menupos == "top") ? ' checked="checked"' : '' ) + '><label for="navpositiontop">顶部</label> </div>', /*<input type="radio" name="navposition" value="right" id="navpositionright"' + ((demo_settings.menupos=="right") ? ' checked="checked"' : '' ) + '><label for="navpositionright">右边</label> */
        '     </li>',
        /*'     <li class="clearfix">',
         '       <span>菜单在顶部</span>',
         '       <div class="demo-checkbox"><input type="checkbox" id="demo-menu-ontop" class="demo-settings-switcher" data-class="switcher-sm"' + ((demo_settings.menu_ontop) ? ' checked="checked"' : '' ) + '></div>',
         '     </li>',*/

        '     <li class="clearfix">',
        '       <span>固定宽度</span>',
        '       <div class="demo-checkbox"><input type="checkbox" id="demo-tinycontainer" class="demo-settings-switcher" data-class="switcher-sm"' + ((demo_settings.tinycontainer) ? ' checked="checked"' : '' ) + '></div>',
        '     </li>',
        /*'     <li class="clearfix">',
         '       <span>隐藏菜单</span>',
         '       <div class="demo-checkbox"><input type="checkbox" id="demo-no-menu" class="demo-settings-switcher" data-class="switcher-sm"' + ((demo_settings.no_menu) ? ' checked="checked"' : '' ) + '></div>',
         '     </li>',*/
        '   </ul>',
        ' </div>',
        ' <h5 class="header">主题风格</h5>',
        ' <div id="demo-themes">',
        getThemesTemplate(),
        ' </div>',
        '</div>'
    ];

// Initialize
//

    $(function () {
        activateTheme($('#main-menu .btn-outline'));
        $('head').append($('<style>\n' + demo_styles.join('\n') + '\n</style>'));
        //$('.postion-nav').append($(demo_template.join('\n')));
		$('#tinypagecontent').prepend($(demo_template.join('\n')));

        // Activate theme
        $('#demo-themes .demo-theme[data-theme="' + demo_settings.theme + '"]').addClass('active');


        // Add callbacks
        //

        // Initialize switchers
        $('.demo-settings-switcher').switcher({
            theme: 'square',
            on_state_content: '<span class="fa fa-check" style="font-size:12px;"></span>',
            off_state_content: '<span class="fa fa-times" style="font-size:12px;"></span>'
        });

        // Demo panel toggle
        $('#demo-settings-toggler').click(function () {
            $('#demo-settings').toggleClass('open');
            $('#demo-settings-toggler i').toggleClass('fa-spin');
            return false;
        });

        // Toggle switchers on label click
        $('#demo-settings-list li > span').click(function () {
            $(this).parents('li').find('.switcher').click();
        });

        // Fix/unfix main navbar
        $('#demo-fixed-navbar').on($('html').hasClass('ie8') ? "propertychange" : "change", function () {
            var uncheck_menu_chk = (!$(this).is(':checked') && $('#demo-fixed-menu').is(':checked')) ? true : false;
            demo_settings.fixed_navbar = $(this).is(':checked');
            if (!demo_settings.fixed_navbar) {
                $('#demo-position-fixed').switcher('off');
                $('#demo-fixed-menu').switcher('off');
            }
            //if (uncheck_menu_chk) {
            //  $('#demo-fixed-menu').switcher('off');
            // } else {
            saveDemoSettings();
            $("body").toggleClass("main-navbar-fixed");
            // reloadPage();
            // }
        });
        $('input[name="navposition"]').on($('html').hasClass('ie8') ? "propertychange" : "change", function (e) {
            demo_settings.menupos = $(this).val();
            saveDemoSettings();
            $("body").removeClass("main-menu-right menu-on-top");

            if (demo_settings.menupos == "top") {
                $("body").removeClass("mmc");
                $("body").addClass("menu-on-top");
                $("#main-menu #topmenurap").css("display", "block");
                $("#main-menu>.slimScrollDiv").css("display", "none")
            } else {
                if (demo_settings.menupos == "right") {
                    $("body").addClass("main-menu-right");
                }
                $("#main-menu #topmenurap").css("display", "none");
                $("#main-menu>.slimScrollDiv").css("display", "block");
            }
            //reloadPage();
        });
        // Fix/unfix main menu
        $('#demo-fixed-menu').on($('html').hasClass('ie8') ? "propertychange" : "change", function () {
            var check_navbar_chk = ($(this).is(':checked') && !$('#demo-fixed-navbar').is(':checked')) ? true : false;
            demo_settings.fixed_menu = $(this).is(':checked');


            if (!demo_settings.fixed_menu) {
                $('#demo-position-fixed').switcher('off');
            } else {
                $('#demo-fixed-navbar').switcher('on');
            }
            saveDemoSettings();
            $("body").toggleClass("main-menu-fixed");
            //reloadPage();
            // }
        });

        // RTL
        $('#demo-rtl').on($('html').hasClass('ie8') ? "propertychange" : "change", function () {
            demo_settings.rtl = $(this).is(':checked');
            saveDemoSettings();
            $("body").toggleClass("right-to-left");
            // reloadPage();
        });

        $('#demo-menu-ontop').on($('html').hasClass('ie8') ? "propertychange" : "change", function () {
            demo_settings.menu_ontop = $(this).is(':checked');
            if (demo_settings.menu_ontop) {
                $('#demo-no-menu').switcher('off');
                if ($("body").hasClass("mmc")) {
                    $("body").removeClass("mmc");
                }
            }


            saveDemoSettings();
            $("body").toggleClass("menu-on-top");
            // reloadPage();
        });
        // Fix/unfix main menu
        $('#demo-menu-rigth').on($('html').hasClass('ie8') ? "propertychange" : "change", function () {
            demo_settings.menu_right = $(this).is(':checked');
            if (demo_settings.menu_right) {
                $('#demo-menu-ontop').switcher('off');
            }
            saveDemoSettings();
            $("body").toggleClass("main-menu-right");


            //reloadPage();
        });

        $('#demo-position-fixed').on($('html').hasClass('ie8') ? "propertychange" : "change", function () {
            if (demo_settings.position_fixed = $(this).is(':checked')) {
                //demo_settings.fixed_navbar=demo_settings.position_fixed;
                $('#demo-fixed-navbar').switcher('on');
                $('#demo-fixed-menu').switcher('on');
            }

            saveDemoSettings();
            $("body").toggleClass("main-position-fixed");
            //$("body").toggleClass("main-navbar-fixed");
            //reloadPage();
        });
        $('#demo-footer-fixed').on($('html').hasClass('ie8') ? "propertychange" : "change", function () {
            demo_settings.footer_fixed = $(this).is(':checked')
            //if(){
            //demo_settings.fixed_navbar=demo_settings.position_fixed;
            //$('#demo-fixed-navbar').switcher('on');
            //$('#demo-fixed-menu').switcher('on');
            //}

            saveDemoSettings();
            $("body").toggleClass("footer-fixed");
            //$("body").toggleClass("main-navbar-fixed");
            //reloadPage();
        });
        $('#demo-tinycontainer').on($('html').hasClass('ie8') ? "propertychange" : "change", function () {
            demo_settings.tinycontainer = $(this).is(':checked');
            //alert(demo_settings.tinycontainer)	;
            saveDemoSettings();
            $("body").toggleClass("tinycontainer");
            //$("body").toggleClass("main-navbar-fixed");
            //reloadPage();
        });

        // Hide/show main menu
        $('#demo-no-menu').on($('html').hasClass('ie8') ? "propertychange" : "change", function () {
            demo_settings.no_menu = $(this).is(':checked');

            if (demo_settings.no_menu) {
                $('body').addClass('mmc');
                $('#demo-menu-ontop').switcher('off');
            } else {
                $('body').removeClass('mmc');
            }


            saveDemoSettings();
            //$("body").toggleClass("mmc");

        });

        // Change theme
        $('#demo-themes .demo-theme').on('click', function () {
            if ($(this).hasClass('active')) return;
            $('#demo-themes .active').removeClass('active');
            $(this).addClass('active');
            demo_settings.theme = $(this).attr('data-theme');
            saveDemoSettings();
            activateTheme($('#main-menu .btn-outline'));
            return false;
        });


        // Custom menu content demo
        //

        $('#menu-content-demo .close').click(function () {
            var $p = $(this).parents('.menu-content');
            $p.addClass('fadeOut');
            setTimeout(function () {
                $p.css({height: $p.outerHeight(), overflow: 'hidden'}).animate({
                    'padding-top': 0,
                    height: $('#main-navbar').outerHeight()
                }, 500, function () {
                    $p.remove();
                });
            }, 300);
            return false;
        });
    });
});//();