<%@ page import="api.model.entity.Pitch" %>
<%@ page import="api.model.entity.Service" %>
<%@ page import="common.constant.FPConstant" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/7/2019
  Time: 5:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/service-detail.css">
    <link rel="stylesheet" type="text/css" href="css/base.css">
</head>
<body>
<%
    if (session == null || session.getAttribute("staff-account-user-name") == null) {
        response.sendRedirect("staff_login.jsp");
    }
%>
<%@include file="resource/layout/staff-header.jsp"%>
<%
    if(request.getAttribute("service-target")!=null){
        Service service = (Service) request.getAttribute("service-target");
%>
<div id="service-detail-content">
    <div class="div-log-out">
        <a href="staff_logout" class="href-log-out"
           onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Đăng xuất</a>
    </div>
    <div id="service-id">Mã Dịch Vụ : <%=service.getId()%></div>
    <div id="service-form">
        <form id="staff-update-service-form" action="update_service" method="get">
            <input type="hidden" value="<%=service.getId()%>" name="updated-service-id">
            <label class="staff-service-detail-label-form-add" >Tên</label>
            <input type="text" id="staff-service-detail-input-name" name="staff-service-detail-input-name" value="<%=service.getName()%>"
                   onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
            <br>
            <br>
            <label class="staff-service-detail-label-form-add">Kiểu &nbsp;</label>
            <input type="text" id="staff-service-detail-input-type" name="staff-service-detail-input-type" value="<%=service.getType()%>"
                   onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
            <br>
            <br>
            <label class="staff-service-detail-label-form-add">Giá</label>
            <input type="text" id="staff-service-detail-input-price" name="staff-service-detail-input-price" value="<%=service.getPrice()%>"
                   onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
        </form>
        <div id="update-button-div">
            <button id="update-button" onclick="updateButtonClicked()">Cập nhật</button>
        </div>
    </div>
    <%
        }
    %>
</div>
<%@include file="resource/layout/staff-footer.jsp"%>
<script type="text/javascript" src="js/staff-service-detail.js"></script>
<script type="text/javascript" src="js/base.js"></script>
</body>
</html>
