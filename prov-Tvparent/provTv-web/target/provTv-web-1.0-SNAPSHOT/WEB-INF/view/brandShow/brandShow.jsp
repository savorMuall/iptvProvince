</html><%@ page language="java" contentType="text/html; charset=UTF-8"
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
<script type="text/javascript">
 $(document).ready(function(){
	 $(".page_a").click(function(){
		 var pagenu = $(this).attr("value");
		 $("#pageNo").val(pagenu);
		 $("#beginTim").val($("#beginTime").val());
		 $("#endTim").val($("#endTime").val());
		 $("#gForm").submit();
	 });
 });
</script>
</head>
<body>
<div class="right_menu">
    <ul>
        <li class="menu_inp30">时间范围<span class="mar_l_25">
                <input class="Wdate form-control"  style="" type="text" name="beginTime" value="${beginTime}" id="beginTime" readonly="readonly" data-msg-required="起始时间不能为空" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="起始时间"/> -- <input class="Wdate form-control" style="" type="text" name="endTime" value="${endTime}" id="endTime" readonly="readonly"  onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="结束时间"/></span></li>
        <br />
        <li class="menu_sel fl">芯片类型 <span>
					<select id="platformName" name="platformName">
                        <option selected="selected" value="">----</option>
                            <option value="1" >联发科</option>
                            <option value="2" >海思</option>
                            <option value="3" >展讯</option>
                    </select>
				</span></li>
        <li class="menu_inp30"><span class=""><input name="titleOrAuther" id="titleOrAuther" type="text" value="" placeholder="厂家名称"/></span></li>
        <div class="page_box_26"></div>
       	<li class="menu_sel fl">安卓版本 <span> <select
					id="platformName" name="platformName">
						<option selected="selected" value="">----</option>
						<option value="1">1.2</option>
						<option value="2">1.3</option>
						<option value="3">1.4</option>
				</select>
			</span></li>
        <li><a href="javascript:;" class="so_box" onclick="querysContent();">查询</a></li>
    </ul>
</div>
<div class="right_infor42">
    <table id="contT" name="contT" style="table-layout:fixed;">
        <thead class="thead" style="height:30px;">
        <tr>
            <th width="50">序号</th>
            <th width="130">厂家名称</th>
            <th width="100">型号</th>
            <th width="100">芯片</th>
            <th width="80">安卓版本</th>
            <th width="80">rom内存大小</th>
            <th width="120">主频</th>
            <th width="100">编号</th>
            <th width="130">创建时间</th>
        </tr>
        </thead>
        <tbody id="itemContainer">
        	<c:if test="${!empty brands}">
        		<c:forEach items="${brands}" var="brand" varStatus="status">
        			<tr class="list_edit">
        				<td>${statusBase+status.index+1}</td>
        				<td>${brand.name}</td>
        				<td>${brand.model}</td>
        				<td>${brand.chip}</td>
        				<td>${brand.androidVersion}</td>
        				<td>${brand.romMemory}</td>
        				<td>${brand.freQuency}</td>
        				<td>${brand.number}</td>
        				<td><fmt:formatDate value="${brand.createTime}" pattern="yyyy-MM-dd" /></td>
        			</tr>
        		</c:forEach>
        	</c:if>
        </tbody>
    </table>
    <div class="page_box_15"></div>
        <div class="page_z">
          	 当前第${pageNum}页  
            <a href="#" class="page_a"  id="first" value="1">首页</a> 
            <a href="#" class="page_a" id="lastOne" <c:if test="${page.currentPage eq 1}">value=1</c:if><c:if test="${page.currentPage ne 1}">value="${page.currentPage-1}"</c:if>><<</a>
            <c:if test="${!empty listpaNo}">
            	<c:forEach items="${listpaNo}" var="pagen">	
					<a href="#" class="page_a" id=page${pagen} value=${pagen}>${pagen}</a>
				</c:forEach>
            </c:if>
            <a href="#" class="page_a" id="nextOne" <c:if test="${page.currentPage eq pageAll}">value="${pageAll}"</c:if><c:if test="${page.currentPage ne pageAll}">value="${page.currentPage+1}"</c:if>>>></a>
            <a href="#" class="page_a" id="last" value=${pageAll}>尾页</a>
		</div>
        <i class="clear"></i>
        
        <form action="findBrand.do" method="post" id="gForm" name="gForm" style="display:none">
       		<input id="pageNo" name="pageNo" type="text" value=""></input>
       		<input id="beginTim" name="beginTim" type="text" value=""></input>
       		<input id="endTim" name="endTim" type="text" value=""></input>
        </form>
        
</div>
</body>
</html>