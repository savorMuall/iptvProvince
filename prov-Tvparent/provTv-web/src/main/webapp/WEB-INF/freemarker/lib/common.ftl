<#import "/plugin/tags.ftl" as tags />

<#macro newHead selected="INDEX" >
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>寻味传媒酒店管理系统</title>
        <link rel="stylesheet" href="<@tags.spring.url value="/script/css/savorCss/style.css" />" />
        <script type="text/javascript" charset="UTF-8" src="<@com.tags.spring.url value='/script/my-js/savorJs/jquery.min.js' />"></script>
        <script type="text/javascript" charset="UTF-8" src="<@com.tags.spring.url value='/script/my-js/savorJs/common.js' />"></script>
        <script type="text/javascript" charset="UTF-8" src="<@com.tags.spring.url value='/script/my-js/savorJs/WdatePicker.js' />"></script>
        <script type="text/javascript" charset="UTF-8" src="<@com.tags.spring.url value='/script/my-js/savorJs/bootstrap.min.js' />"></script>

        </head>
        <body class="fixed  pace-done">
            <div class="left_frame">
                <@leftHeader/>
                <@leftMenu />
            </div>
            <div class="right_frame">
                <div class="right_frame_top">
                    <@rightHeader/>
                </div>


            <#-- 在页面填充：right_frame_bottom -->
</#macro>

<#macro newFooter>
            </div>
        </body>
    </html>
</#macro>

<#macro leftHeader>
    <link rel="stylesheet" href="<@tags.spring.url value="/script/css/savorCss/bootstrap.min.css" />" />
    <link rel="stylesheet" href="<@tags.spring.url value="/script/css/savorCss/style.css" />" />
    <div><a href="index.do" class="logo"><span><img src="<@tags.spring.url value="/images/savorImage/logo.png" />"/></span></a></div>
</#macro>

<#macro rightHeader>
<div class="navbar-right">
    <div class="head_photo"><img src="<@tags.spring.url value="/images/savorImage/head_photo.png" />"/></div>
    <div class="head_photo_1"><img src="<@tags.spring.url value="/images/savorImage/head_photo_1.png" />"/></div>
    <div class="head_photo_r"><span>欢迎您，管理员！</span><span><a href="">注销</a></span></div>
    <div class="clear"></div>
</div>
</#macro>

<#macro leftMenu>
    <script type="text/javascript" charset="UTF-8" src="<@com.tags.spring.url value='/script/my-js/savorJs/jquery.min.js' />"></script>
    <script type="text/javascript" charset="UTF-8" src="<@com.tags.spring.url value='/script/my-js/savorJs/common.js' />"></script>
    <script type="text/javascript" charset="UTF-8" src="<@com.tags.spring.url value='/script/my-js/savorJs/WdatePicker.js' />"></script>
    <link rel="stylesheet" href="<@tags.spring.url value="/script/css/savorCss/bootstrap.min.css" />" />
    <link rel="stylesheet" href="<@tags.spring.url value="/script/css/savorCss/style.css" />" />

    <div class="sidebar" style="overflow: hidden; width: auto; height: 1070px;">
        <ul class="sidebar-menu">
            <li class="sub-nav">
                <a href="#">
                    <i class="fa fa-home"><img src="<@tags.spring.url value="/images/savorImage/icon_3.png" />" align="absmiddle"/></i>
                    <span>电视设置</span>
                    <i class="fa pull-right fa-angle-right"></i>
                </a>
                <ul class="sub-menu" style="display: none;">
                    <li><a href="findTvList">节目匹配</a></li>
                    <li><a href="channel">节目详情</a></li>
                </ul>
            </li>
        </ul>
    </div>
</#macro>

<#macro area>
<div class="mod_select">
    <ul>
        <select id="t_detail" onchange="Change()">
            <script type="text/javascript">
                $.ajax({
                    url: "<@tags.spring.url value="/area/findAreaList" />",
                    type: "POST",
                    dataType: "json",
                    success: function (data) {
                        var sb = "";
                        var list = data.result;
                        for (var i = 0; i < list.length; i++) {
                            sb = sb + "<option value=" + list[i].id ;
                            if(${areaId!'1'} == list[i].id){
                                sb = sb + " selected"
                            }
                            sb = sb + ">" + list[i].name + "</option>";
                        }
                        $("#t_detail").append(sb);
                    }
                });

            </script>
        </select>
    </ul>
</div>

</#macro>
