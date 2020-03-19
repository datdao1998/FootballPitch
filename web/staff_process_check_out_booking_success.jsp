<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12/7/2019
  Time: 4:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check Out thành công</title>
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/staff-process-check-out-booking-success.css">
</head>
<body class="margin-0">
<%
    if (session == null || session.getAttribute("staff-account-id") == null) {
        response.sendRedirect("staff_login.jsp");
    }
%>

<%@include file="resource/layout/staff-header.jsp"%>
<div id="staff-process-check-out-booking-success-content">

    <div class="div-log-out">
        <a href="staff_logout" class="href-log-out"
           onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Đăng xuất</a>
    </div>

    <div id="staff-process-check-out-booking-success-div-1">
        Check out thành công
    </div>

    <div id="staff-process-check-out-booking-success-div-2" style="margin-left: -10vw">
        <a href="staff_check_out" id="staff-process-check-out-booking-success-href-1"
           onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Xem danh sách sân check in</a>
    </div><br>


    <a href="staff_home.jsp" id="staff-process-check-out-booking-success-href-2"
       onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Trang chủ</a>


</div>


<%@include file="resource/layout/staff-footer.jsp"%>
</body>
</html>
