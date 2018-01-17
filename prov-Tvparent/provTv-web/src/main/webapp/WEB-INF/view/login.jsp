<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="js/jquery-1.7.2.js"></script>
<script src="js/common.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<link rel="stylesheet" type="text/css" target="view_window" href="css/style.css" />

<title>无标题文档</title>
</head>
<script type="text/javascript">
    function check(){
        if (window != top) {
            top.location.href = location.href;
        }
    }
function loginCheck(){
	var form=document.getElementById("sysForm");
	form.submit();
}

</script>
<body onload="check();">
    <div class="loginRegist_box">
        <div class="g_img0"></div>
        <div class="g_img1"></div>
        <div class="g_img2"></div>
    </div>
    
    <div class="loginRegist_bar wp">
    	<div class="loginRegist_left fl"><img  src="image/logo.png"/>
        	<p class="loginRegist_bg"></p>
            <p class="loginRegist_tit">山东省份管理系统</p>
        </div>
        
        <div class="loginRegist_right fr">
        	<img src="image/h.png" />
            <div class="loginRegist_infor">
            	<p class="loginRegist_name">登录系统</p>
            	<form id="subm" name="subm" action="login.do" method="post">
            	<div class="loginRegist_account">
                	<em class="login_icon"></em>
                    <input id="username" type="text" class="on" name="username" value="" placeholder="请输入用户名" required>
                    <div class="error_tit">
					</div>
                    
                </div>
                <div class="loginRegist_psd">
                	<em class="login_icon2"></em>
                    <input id="password" type="password" class="on" name="password" placeholder="请输入密码" required>
                    <div class="error_tit error_tit_psd">${error}
					</div>
                 
                </div>
                <div class="free_landing cl">
						 <label><input type="checkbox"  name="rememberMe" >&nbsp;&nbsp;记住用户名密码 </label>
					</div>
                <div class="loginRegist_btn">
                		<button type="submit" style="background:#2A2A2A;color:#FCFCFC; outline:none;border:0px;height:40px;width:310px">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</button>
						<!-- <a href="javascript:;" onclick="loginCheck()">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</a>  -->
				</div>
				</form>   
            </div>
        
        </div>
        
    </div>




</body>
</html>
