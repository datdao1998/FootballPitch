<%--
  Created by IntelliJ IDEA.
  User: ABC
  Date: 12/6/2019
  Time: 12:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Huỷ đặt sân thành công</title>
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/customer-booking-success.css">
</head>
<body class="margin-0">
<%
    if (session == null || session.getAttribute("customer-account-id") == null) {
        response.sendRedirect("customer_login.jsp");
    }
%>
<%@include file="resource/layout/header.jsp"%>
<div id="customer-booking-success-content">

    <div class="div-log-out">
        <a href="customer_log_out" class="href-log-out"
           onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Đăng xuất</a>
    </div>

    <div id="customer-booking-success-div-1">
        Hủy đặt sân thành công
    </div>

    <div id="customer-booking-success-div-2" style="margin-left: -10vw">
        <a href="customer_search_booked_pitch" id="customer-booking-success-href-1"
           onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Xem danh sách sân đặt</a>
    </div><br>
    <a href="index.jsp" id="customer-booking-success-href-2"
       onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Trang chủ</a>

</div>
<%@include file="resource/layout/footer.jsp"%>
<script type="text/javascript" src="js/base.js"></script>
</body>
</html>
