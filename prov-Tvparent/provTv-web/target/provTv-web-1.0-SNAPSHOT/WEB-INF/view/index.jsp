<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" target="view_window" href="css/style.css" />
<script src="js/jquery-1.7.2.js"></script>

<title>IPTV后台管理系统</title>
</head>
<script type="text/javascript">

	function menu(data){
		$("#"+data).parent().siblings().attr('class','class');
		var text = $("#"+data).text();
		$("#"+data).parent().attr('class','nav_active');
		$("#a").text(text);
		$("#"+data+"s").siblings().hide();
		$("#"+data+"s").show(); 
		$("#"+data+"s").children(":first").addClass('active_type').siblings().removeClass('active_type');
		var go = $("#"+data+"s").children(":first").find("a").attr("href");
		$("#"+data).attr("href",go);
	}
	$().ready(function() {

	$('div#securitys li a').bind('click', function(){
	    $(this).parent().addClass('active_type').siblings().removeClass('active_type');
	});
    
	$('div#releaseManagements li a').bind('click', function(){
	    $(this).parent().addClass('active_type').siblings().removeClass('active_type');
	});

        $('div#vodManagements li a').bind('click', function(){
            $(this).parent().addClass('active_type').siblings().removeClass('active_type');
        });

        $('div#mobiles li a').bind('click', function(){
            $(this).parent().addClass('active_type').siblings().removeClass('active_type');
        });

        $('div#statistics li a').bind('click', function(){
            $(this).parent().addClass('active_type').siblings().removeClass('active_type');
        });

    });
</script>

<body>
<div class="res_fix">
	<div class="nav_bg">
		<p class="nav_logo"></p>
    	<ul class="nav_box wp">
        	<li><a id="first" href="javascript:void(0);" target="iframe" onclick="menu('first')">首页</a></li>
        	<li class=""><a id="tvManage" href="javascript:;" target="iframe" onclick="menu('tvManage')">电视管理</a></li>
        	<!--<li class=""><a id="brandManage" href="javascript:;" target="iframe" onclick="menu('brandManage')">厂家管理</a></li>-->
        </ul>	
    	<ul class="nav_name">

        	<li><img src="image/nav_01.png" /></li>
            <li><a href="logout.do" target="_self">[退出]</a></li>
        </ul>
        <i class="clear"></i>
    </div>

</div>

<table style="position: fixed;top: 75px;"  height="100%" cellpadding="0" cellspacing="0">
	<tr>
    	<td style="background:#3e3e3e; width: 200px;">
        	<div class="left_box fl">
                                    <p class="left_tit"><span></span><a id="a">首页</a></p>
                                <ul>
                                	<div id="firsts" style="display: none">
                                    </div>
                                    <div id="tvManages" style="display: none">
                                    <li><a href="findTv.do" target="iframe">电视管理</a></li>
                                    </div>
                                    <div id="brandManages" style="display: none">
                                    <li><a href="findBrand.do" target="iframe">厂家管理</a></li>
                                    </div>
                                </ul>
             </div>
        </td>
    	<td>
        	 <!--右边内容开始-->
                        <div style="position:fixed;top:50px;width: 100%;">
                        <iframe src="" id="iframe" name="iframe" style="overflow: visible;" frameborder="0" width="100%" height="1000" scrolling="yes" onload="window.parent"></iframe>
                        </div>
                        <script> //iframe自适应高度
                            //
                            $(document).ready(function () {
                                $('span.bar-btn').click(function () {
                                    $('ul.bar-list').toggle('fast');
                                });
                            });	
                        
                            $(document).ready(function(){
                                var pagestyle = function() {
                                    var iframe = $("#workspace");
                                    var h = $(window).height() - iframe.offset().top;
                                    var w = $(window).width() - iframe.offset().left;
                                    if(h < 300) h = 300;
                                    if(w < 973) w = 973;
                                    iframe.height(h);
                                    iframe.width(w);
                                }
                                pagestyle();
                                $(window).resize(pagestyle);
                                //turn location
                                if($.cookie('now_menu_id') != null){
                                    openItem($.cookie('now_menu_id'),$.cookie('now_sub_menu_id'));
                                }else{
                                    $('#mainMenu>ul').first().css('display','block');
                                    var first_sub_menu = $('#mainMenu>ul').first().find("a").first();
                                    $(first_sub_menu).addClass('selected');
                                    //第一次进入后台时，默认定到欢迎界面
                                    $("#nav>ul>li").first().children("a").addClass('actived');
                                    $('#workspace').attr('src',$(first_sub_menu).attr("url"));
                                }
                                $('#iframe_refresh').click(function(){
                                    var fr = document.frames ? document.frames("workspace") : document.getElementById("workspace").contentWindow;;
                                    fr.location.reload();
                                });
                        
                            });
                            
                        </script> 
                        <!--/右边结束-->
        </td>
    </tr>
    
</table>

</body>
</html>