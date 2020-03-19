<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12/6/2019
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chi tiết</title>
    <link rel="stylesheet" type="text/css" href="css/customer-view-booking.css">
    <link rel="stylesheet" type="text/css" href="css/base.css">
</head>
<body class="magin-0">
<%
    if (session == null || session.getAttribute("customer-account-id") == null ||
            request.getParameter("booking_time_in") == null) {
        response.sendRedirect("customer_login.jsp");
    }
    if (request.getParameter("pitch_id") == null) {
        response.sendRedirect("customer_booking.jsp");
    }
%>
<%@include file="resource/layout/header.jsp"%>
<div id="customer-view-booking-content">
    <div class="div-log-out">
        <a href="customer_log_out" class="href-log-out"
           onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Đăng xuất</a>
    </div>
    <form id="customer-view-booking-form" action="customer_cancel_booking">
        <input type="hidden" name="customer-view-booking-pitch-id" value="<%=request.getParameter("booking_id")%>">
        <div id="customer-view-booking-div-1">
            <div class="customer-view-booking-label">Họ và tên</div>
            <input disabled class="customer-view-booking-input" id="customer-view-booking-customer-name"
                   type="text" value="<%=session.getAttribute("customer-account-name")%>"><br>

            <div class="customer-view-booking-label">Số điện thoại</div>
            <input disabled class="customer-view-booking-input" id="customer-view-booking-customer-phone"
                   type="text" value="<%=session.getAttribute("customer-account-phone")%>"><br>

            <div class="customer-view-booking-label">Tên sân</div>
            <input disabled class="customer-view-booking-input" id="customer-view-booking-pitch-name"
                   type="text" value="<%=request.getParameter("pitch_name")%>"><br>

            <div class="customer-view-booking-label">Giờ vào</div>
            <input disabled class="customer-view-booking-input" id="customer-view-booking-time-in"
                   name="customer-view-booking-time-in"
                   type="text" value="<%=request.getParameter("booking_time_in")%>"><br>
        </div>

        <div id="customer-view-booking-div-2">
            <div class="customer-view-booking-label">Mô tả</div>
            <input disabled class="customer-view-booking-input" id="customer-view-booking-pitch-des"
                   type="text" value="<%=request.getParameter("pitch_des")%>"><br>

            <div class="customer-view-booking-label">Số người</div>
            <input disabled class="customer-view-booking-input" id="customer-view-booking-pitch-num-player"
                   type="text" value="<%=request.getParameter("pitch_num_player")%>"><br>

            <div class="customer-view-booking-label">Giá</div>
            <input disabled class="customer-view-booking-input" id="customer-view-booking-pitch-cost" type="text"
                   value="<%=request.getParameter("pitch_cost")%>"><br>

            <div class="customer-view-booking-label">Giờ ra</div>
            <input disabled class="customer-view-booking-input" id="customer-view-booking-time-out"
                   name="customer-view-booking-time-out"
                   type="text" value="<%=request.getParameter("booking_time_out")%>"><br>
        </div>
    </form>

    <button id="customer-view-booking-btn-cancel" onmouseout="changeBgColorDefault(this)"
            onmouseover="changeBgColorWhite(this)">Huỷ đặt sân</button>

    <a href="customer_search_booked_pitch" id="customer-view-booking-btn-esc"
       onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">
        Quay lại
    </a>

</div>
<%@include file="resource/layout/footer.jsp"%>
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript" src="js/customer-cancel-booking.js"></script>

</body>
</html>
