<%@ page import="common.constant.FPConstant" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/7/2019
  Time: 11:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Pitch</title>
    <link rel="stylesheet" type="text/css" href="css/staff-add-pitch.css">
    <link rel="stylesheet" type="text/css" href="css/base.css">
</head>
<body>
<%
    if (session == null || session.getAttribute("staff-account-user-name") == null) {
        response.sendRedirect("staff_login.jsp");
    }
%>
<%@include file="resource/layout/staff-header.jsp"%>
<div id="staff-add-pitch-content">
    <div class="div-log-out">
        <a href="staff_logout" class="href-log-out"
           onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Đăng xuất</a>
    </div>
    <div id="staff-add-pitch-div-1">
        <form id="staff-add-pitch-form" action="staff_add_pitch" method="get">
            <label class="staff-add-pitch-label-form-add" >Tên Sân &nbsp;</label>
            <input type="text" id="staff-add-pitch-input-name" name="staff-add-pitch-input-name" placeholder="Tên sân"
                   onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
            <label class="staff-add-pitch-label-form-add">Mô tả &nbsp;</label>
            <input type="text" id="staff-add-pitch-input-description" name="staff-add-pitch-input-description" placeholder="Mô tả"
                   onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
            <label class="staff-add-pitch-label-form-add" for="staff-add-pitch-input-num-player">Số Người</label>
            <select id="staff-add-pitch-input-num-player" name="staff-add-pitch-input-num-player"
                    onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
                <option value="<%=FPConstant.TypeId.TYPE_5%>">5</option>
                <option value="<%=FPConstant.TypeId.TYPE_7%>">7</option>
                <option value="<%=FPConstant.TypeId.TYPE_11%>">11</option>
            </select>
        </form>
        <div id="staff-add-pitch-add-btn-div">
            <button id="staff-add-pitch-add-btn" onmouseover="changeBgColorWhite(this)" onclick="btnAddPitchClicked()"
                    onmouseout="changeBgColorDefault(this)">Thêm Sân</button>
        </div>
    </div>
</div>
<script type="text/javascript" src="js/staff-add-pitch.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<%@include file="resource/layout/staff-footer.jsp"%>
</body>
</html>
