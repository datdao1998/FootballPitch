<%@ page import="java.util.ArrayList" %>
<%@ page import="api.model.entity.Pitch" %>
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
    <link rel="stylesheet" type="text/css" href="css/pitch-detail.css">
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
    if(request.getAttribute("pitch-target")!=null){
        Pitch pitch = (Pitch) request.getAttribute("pitch-target");
%>
<div id="pitch-detail-content">
    <div class="div-log-out">
        <a href="staff_logout" class="href-log-out"
           onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Đăng xuất</a>
    </div>
    <div id="pitch-id">Mã Sân : <%=pitch.getId()%></div>
    <div id="pitch-form">
        <form id="staff-update-pitch-form" action="update_pitch" method="get">
            <input type="hidden" value="<%=pitch.getId()%>" name="updated-pitch-id">
            <label class="staff-pitch-detail-label-form-add" >Tên Sân &nbsp;</label>
            <input type="text" id="staff-pitch-detail-input-name" name="staff-pitch-detail-input-name" value="<%=pitch.getName()%>"
                   onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
            <br>
            <br>
            <label class="staff-pitch-detail-label-form-add">Mô tả &nbsp;</label>
            <input type="text" id="staff-pitch-detail-input-description" name="staff-pitch-detail-input-description" value="<%=pitch.getDescription()%>"
                   onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
            <br>
            <br>
            <label class="staff-pitch-detail-label-form-add" for="staff-pitch-detail-input-num-player">Số Người</label>
            <select id="staff-pitch-detail-input-num-player" name="staff-pitch-detail-input-num-player"
                    onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
                <option value="<%=FPConstant.TypeId.TYPE_5%>">5</option>
                <option value="<%=FPConstant.TypeId.TYPE_7%>">7</option>
                <option value="<%=FPConstant.TypeId.TYPE_11%>">11</option>
            </select>
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
<script type="text/javascript" src="js/staff-pitch-detail.js"></script>
<script type="text/javascript" src="js/base.js"></script>
</body>
</html>
