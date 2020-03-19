<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thành công</title>
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/customer-booking-success.css">
</head>
<body class="margin-0">
<%
    if (session == null || session.getAttribute("staff-account-id") == null) {
        response.sendRedirect("staff_login.jsp");
    }
%>
<%@include file="resource/layout/header.jsp"%>
<div id="customer-booking-success-content">

    <div class="div-log-out">
        <a href="staff_logout" class="href-log-out"
           onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Đăng xuất</a>
    </div>

    <div id="customer-booking-success-div-1">
        Thành công
    </div>

    <div id="customer-booking-success-div-2">
        <a href="staff_service_manage_sell.jsp" id="customer-booking-success-href-1"
           onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Bán hàng</a>
    </div><br>
    <a href="staff_home.jsp" id="customer-booking-success-href-2"
       onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Trang chủ</a>

</div>
<%@include file="resource/layout/footer.jsp"%>
<script type="text/javascript" src="js/base.js"></script>
</body>
</html>
