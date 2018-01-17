<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<title></title>

<style type="text/css">
        .black_overlay
        {
            display: none;
            position: absolute;
            top: 0%;
            left: 0%;
            width: 100%;
            height: 100%;
            background-color: black;
            z-index: 1001;
            -moz-opacity: 0.8;
            opacity: .80;
            filter: alpha(opacity=80);
        }
        .white_content
        {
            display: none;
            position: absolute;
            top: 10%;
            left: 10%;
            width: 40%;
            height: 15%;
            border: 5px solid #ea470e;
            background-color: white;
            z-index: 1002;
            overflow: auto;
        }
        .white_content_small
        {
            display: none;
            position: absolute;
            top: 20%;
            left: 30%;
            width: 40%;
            height: 50%;
            border: 16px solid lightblue;
            background-color: white;
            z-index: 1002;
            overflow: auto;
        }
    </style>

<script type="text/javascript">
	$(document).ready(function() {
		$(".page_a").click(function() {
			var pagenu = $(this).attr("value");
			$("#pageNo").val(pagenu);
			$("#beginTim").val($("#beginTime").val());
			$("#endTim").val($("#endTime").val());
			$("#gForm").submit();
		});

		$("#province").bind("change",function() {
			var provceId = $(this).val();
			$.ajax({
				url : "findCityByProvince.do", //后台webservice里的方法名称  
				type : "post",
				data : {
					"prov" : $(this).val()
				},
				dataType : "json",
				success : function(data) {
					var flag = data.success;
					var citys = data.listcitys;
					var optionstring;
					if (flag = true) {
						for (var j = 0; j < citys.length; j++) {
							optionstring += "<option value=\"" + citys[j].cityId + "\" >"+ citys[j].cityName+ "</option>";
						}
						$("#city").html("<option selected='selected' value='0'>--全部--</option>"+ optionstring);
					} else {
						alert("获取地市失败");
					}
				}
			});
		});
	});
</script>
<script type="text/javascript">
        //弹出隐藏层
        function ShowDiv(show_div, bg_div,tv_id,tv_mac) {
            document.getElementById(show_div).style.display = 'block';
            document.getElementById(bg_div).style.display = 'block';
            var bgdiv = document.getElementById(bg_div);
            bgdiv.style.width = document.body.scrollWidth;
            // bgdiv.style.height = $(document).height();
            $("#" + bg_div).height($(document).height());
            $("#tvsmac").val(tv_mac);
            $("#tvId").val(tv_id);
        };
        //关闭弹出层
        function CloseDiv(show_div, bg_div) {
            document.getElementById(show_div).style.display = 'none';
            document.getElementById(bg_div).style.display = 'none';
        };
        
        function relatedProvince(){
        	var prov = $('#relatedProvince option:selected').val();
    		var tvId = $("#tvId").val();
    		var tvMac = $("#tvsmac").val();
    		$.ajax({
				url : "tvProvince.do", //后台webservice里的方法名称  
				type : "post",
				data : {"tvId":tvId,"tvMac":tvMac,"tvProv":prov},
				dataType : "json",
				success : function(data) {
					var flag = data.success;
					if (flag = true) {
						$(".pro"+tvId).html(data.provName);
						 document.getElementById("MyDiv").style.display = 'none';
				         document.getElementById("fade").style.display = 'none';
					} else {
						alert("保存失败");
					}
				}
			});
    		
        };
        
        function checkFile(){
        	var file = document.file_upload_name.excel_file.value ;  
            if(file==null||file==""){  
                alert("你还没有选择任何文件，不能上传!") ;
                return ;  
            }else{
            	$("#file_upload_id").submit();
            }
        }
    </script>
</head>
<body>
		<div class="right_menu">
			<form action="findTv.do" method="post" id="findT" name="findT">
				<ul>
					<li class="menu_inp">时间范围<span class="mar_l_25"> <input
							class="Wdate form-control" style="" type="text" name="beginTime"
							value="${beginTime}" id="beginTime" readonly="readonly"
							data-msg-required="起始时间不能为空"
							onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							placeholder="起始时间" /> -- <input class="Wdate form-control"
							style="" type="text" name="endTime" value="${endTime}"
							id="endTime" readonly="readonly"
							onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							placeholder="结束时间" /></span></li>
					<br />
					<li class="menu_sel fl">平台名称 <span> <select
							id="platformName" name="platformName">
								<option selected="selected" value="">全部</option>
								<option value="1">烽火</option>
								<option value="2">中兴</option>
								<option value="3">华为</option>
						</select>
					</span></li>
					<li class="menu_inp"><span class=""><input
							name="titleOrAuther" id="titleOrAuther" type="text" value=""
							placeholder="mac地址" /></span></li>
					<br />
					<li class="menu_sel  fl">地市 <select id="city" name="city"
						required="required" style="margin-left:63px;">
							<option selected="selected" value="" style="margin-left: 10px;">--全部--</option>
							<c:forEach var="cit" items="${citys}" varStatus="status">
								<option value="${cit.id}"
									<c:if test="${citId eq cit.id}">selected="selected"</c:if>>${cit.name}</option>
							</c:forEach>
					</select></li>
					<li class="menu_sel fl">激活状态<span> <select style="width:80px;"
                    							id="activationState" name="activationState" >
                    								<option selected="selected" value="">全部</option>
                    								<option value="1">已激活</option>
                    								<option value="2">未激活</option>
                    								<option value="3">已冻结</option>
                    						</select>
                    					</span></li>
					<li><a href="javascript:;" class="so_box"
						onclick="querysContent();">查询</a></li>
				</ul>
				<input type="hidden" id="provinceId" name="provinceId" value="" />
				<input type="hidden" id="areaId" name="areaId" value="" />

			</form>
		</div>

		<div class="right_infor">
			<table id="contT" name="contT"
				style="table-layout: fixed;">
				<thead class="thead">
					<tr>
						<th width="50">序号</th>
						<th width="100">mac</th>
						<th width="100">地市</th>
						<th width="80">平台名称</th>
						<th width="80">激活状态</th>
					</tr>
				</thead>
				<tbody id="itemContainer">
					<c:if test="${!empty tvs}">
						<c:forEach items="${tvs}" var="tv" varStatus="status">
							<tr class="list_edit">
								<td>${statusBase+status.index+1}</td>
								<td>${tv.mac}</td>
								<td class="pro${tv.id}">${empty tv.provName ? "未分配省份" : tv.provName}</td>
								<td>${tv.deviceName}</td>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<div class="page_box_15"></div>
			<div class="page_z">
				当前第${pageNum}页 <a href="#" class="page_a" id="first" value="1">首页</a>
				<a href="#" class="page_a" id="lastOne"
					<c:if test="${page.currentPage eq 1}">value=1</c:if>
					<c:if test="${page.currentPage ne 1}">value="${page.currentPage-1}"</c:if>><<</a>
				<c:if test="${!empty listpaNo}">
					<c:forEach items="${listpaNo}" var="pagen">
						<a href="#" class="page_a" id=page${pagen} value=${pagen}>${pagen}</a>
					</c:forEach>
				</c:if>
				<a href="#" class="page_a" id="nextOne"
					<c:if test="${page.currentPage eq pageAll}">value="${pageAll}"</c:if>
					<c:if test="${page.currentPage ne pageAll}">value="${page.currentPage+1}"</c:if>>>></a>
				<a href="#" class="page_a" id="last" value=${pageAll}>尾页</a>
			</div>
			<i class="clear"></i>

			<form action="findTv.do" method="post" id="gForm" name="gForm"
				style="display: none">
				<input id="pageNo" name="pageNo" type="text" value=""></input> <input
					id="beginTim" name="beginTim" type="text" value=""></input> <input
					id="endTim" name="endTim" type="text" value=""></input>
			</form>

		</div>
		<div id="fade" class="black_overlay"> </div>
	<div id="MyDiv" class="white_content">
		<form action="tvProvince.do" method="post" id="brPro" name="brPro"> 
		<div style="text-align: right; cursor: default; height: 40px;">
			<br /> <span style="font-size: 16px;"
				onclick="CloseDiv('MyDiv','fade')">关闭</span>
		</div>
		<ul>
			<li class="menu_sel fl">
			  <span class=""><input name="tvsmac" id="tvsmac" type="text" value="" placeholder="mac地址" readonly="readonly" /></span>
			 </li>
			<li class="menu_sel">关联省份
			 <span> 
				<select id="relatedProvince" name="relatedProvince">
						<option selected="selected" value="">----</option>
						<option value="17">辽宁</option>
						<option value="25">山东</option>
				</select>
			 </span>
			</li>
			<li><a href="javascript:;" class="so_box"
						onclick="relatedProvince();">保存</a></li>
		</ul>
		<input id="tvId" name="tvId" type="hidden" value=""></input>
		<input id="tvMac" name="tvMac" type="hidden" value=""></input>
		<input id="tvProv" name="tvProv" type="hidden" value=""></input>
		</form>
	</div>
</body>
</html>