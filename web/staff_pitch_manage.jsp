<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/7/2019
  Time: 11:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pitch Manage</title>
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/staff-pitch-manage.css">
</head>
<body>
<%
    if (session == null || session.getAttribute("staff-account-user-name") == null) {
        response.sendRedirect("staff_login.jsp");
    }
%>
<%@include file="resource/layout/staff-header.jsp"%>
<div id="staff-pitch-manage-content">
    <div class="div-log-out">
        <a href="staff_logout" class="href-log-out"
           onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Đăng xuất</a>
    </div>
    <div id="staff-pitch-manage-div-1">
        <button id="staff-pitch-manage-btn-add-pitch" onclick="btnAddPitchClicked()"
                onmouseout="changeBgColorDefault(this)"
                onmouseover="changeBgColorWhite(this)">Thêm sân</button>
        <form action="staff_update_pitch_list" method="GET"  >
            <button id="staff-pitch-manage-btn-update-pitch" onmouseout="changeBgColorDefault(this)" onmouseover="changeBgColorWhite(this)">
                Sửa sân
            </button>
        </form>
    </div>
</div>
<%@include file="resource/layout/staff-footer.jsp"%>
<script type="text/javascript" src="js/staff-pitch-manage.js"></script>
<script type="text/javascript" src="js/base.js"></script>
</body>
</html>
