<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>电视管理</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- jquery 组件引入 -->
<script src="js/jquery-1.7.2.js"></script>

<!-- bootstrap 组件引入 -->
<script src="js/bootstrap/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.css" />

<!-- bootstrap table组件以及中文包的引入-->
<script src="js/bootstrap/bootstrap-table.js"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap-table.css" />
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.0/locale/bootstrap-table-zh-CN.js"></script>

</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h4>基本</h4>
            </div>
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <div class="example">
                        	<div class="col-sm-4">
                        		<input class="form-contron" id="starttime"> &nbsp;&nbsp;-
                        	</div>
                        	<div class="col-sm-4">
                        		<input class="form-contron" id="endtime">
                        	</div>
                        	<div class="col-sm-4">
                        		<input class="form-contron" id="area">
                        	</div>
                            <table id="showTable">
                                <!-- <thead>
                                    <tr>
                                        <th data-field="Tid">ID</th>
                                        <th data-field="First">姓名</th>
                                        <th data-field="sex">性别</th>
                                        <th data-field="Score">评分</th>
                                    </tr>
                                </thead> -->
                            </table>
                        </div>
                    </div>
                </div>
            </div>
                <!-- <div class="ibox float-e-margins">
                	<table id="showTable"></table>
                
                </div> -->
        </div>
    </div>




	<div class="row">
		<!-- <div class="col-sm-9">
			<ul>
				<div class="dropdown">
					<button type="button" class="btn dropdown-toggle"
						id="dropdownMenu1" data-toggle="dropdown">
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu"
						aria-labelledby="dropdownMenu1">
						<li role="presentation"><a role="menuitem" tabindex="-1"
							href="#">北京</a></li>
						<li role="presentation"><a role="menuitem" tabindex="-1"
							href="#">上海</a></li>
						<li role="presentation"><a role="menuitem" tabindex="-1"
							href="#">广州</a></li>
						<li role="presentation" class="divider"></li>
						<li role="presentation"><a role="menuitem" tabindex="-1"
							href="#">深圳</a></li>
					</ul>
				</div>

				<li class="menu_inp fl">创建时间<span class="mar_l_25"> <input
						class="Wdate form-control" style="" type="text" name="beginT"
						value="" id="beginT" readonly="readonly" required
						data-msg-required="起始时间不能为空"
						onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endT\')}',skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						placeholder="起始时间" /> -- <input class="Wdate form-control"
						style="" type="text" name="endT" value="" id="endT"
						readonly="readonly" required data-msg-required="起始时间不能为空"
						onfocus="WdatePicker({minDate: '#F{$dp.$D(\'beginT\')}' ,skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						placeholder="结束时间" /></span></li>
				<li><a href="javascript:;" onclick="querysContent();"
					class="so_box">查询</a></li>
			</ul>
		</div> -->
		
		<div class="row">
		
		</div>
		
		<table>
			<thead class="thead">
				<tr>
					<th>序号</th>
					<th>批次类型</th>
					<th>批次名称</th>
					<th>设备类型人</th>
					<th>数量</th>
					<th>操作系统</th>
					<th>电视机型号</th>
					<th>省份</th>
				</tr>
			</thead>
			<tbody id="itemContainer">
				<c:if test="${!empty tvMsgs}">
					<c:forEach items="${tvMsgs}" var="entity" varStatus="status">
						<tr class="list_edit">
							<td><input type="checkbox" name="checkedId"
								index="${status.index+1}" value="${entity.id}"
								class="check_input" style="display: none;"><span
								class="mar_l_5">${status.index+1}</span></td>
							<td>${entity.deviceId}</td>
							<td>${entity.deviceName}</td>
							<td>${entity.deviceType}</td>
							<td>${entity.number}</td>
							<td>${entity.operateSystem}</td>
							<td>${entity.tvModel}</td>
							<td>${entity.areaId}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
<div class="dropdown">
    <button type="button" class="btn dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">主题
        <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
        <li role="presentation">
            <a role="menuitem" tabindex="-1" href="#">Java</a>
        </li>
        <li role="presentation">
            <a role="menuitem" tabindex="-1" href="#">数据挖掘</a>
        </li>
        <li role="presentation">
            <a role="menuitem" tabindex="-1" href="#">数据通信/网络</a>
        </li>
        <li role="presentation" class="divider"></li>
        <li role="presentation">
            <a role="menuitem" tabindex="-1" href="#">分离的链接</a>
        </li>
    </ul>
</div>
</body>
<script type="text/javascript">
$(function(){
	alert("3333");
	init_table();
	queryData();
})
function init_table(){
	$("#showTable").bootstrapTable({
		striped: true, //表格显示条纹 
		pagination: true,
		singleSelect: false,
		pageList: [5, 10, 15, 20, 25], //记录数可选列表 
	    search: true, //是否启用查询 
	    //showColumns: true, //显示下拉框勾选要显示的列 
		pageSize: 10,
		pageNumber:1,
		columns: [
		    {
		        field: 'type',
		        title: '批次类型'
		    }, {
		        field: '',
		        title: '批次名称'
		    }, {
		        field: '',
		        title: '设备类型人'
		    }
		],
		onLoadSuccess: function(){
			
		}
	});
}
	
function queryData(){
	alert("1234");
	 $.ajax({
		 url: "queryTv.do",
		 type: "POST",
		 dataType: "text",
		 success: function (data) {
		 }
	 });
}

</script>
</html>