<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12/7/2019
  Time: 3:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ProcessCheckOutBooking</title>
    <link rel="stylesheet" type="text/css" href="css/staff-process-check-out-booking.css">
    <link rel="stylesheet" type="text/css" href="css/base.css">
</head>
<body class="margin-0">
<%
    if (session == null || session.getAttribute("staff-account-id") == null ||
            request.getParameter("booking_time_in") == null) {
        response.sendRedirect("staff_login.jsp");
    }
    if (request.getParameter("pitch_id") == null) {
        response.sendRedirect("staff_check_out.jsp");
    }
%>

<%@include file="resource/layout/staff-header.jsp"%>
<div id="staff-check-out-booking-content">
    <div class="div-log-out">
        <a href="staff_logout" class="href-log-out"
           onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Đăng xuất</a>
    </div>

    <form id="staff-check-out-booking-form" action="staff_process_check_out_booking">
        <input type="hidden" name="staff-check-out-booking-pitch-id" value="<%=request.getParameter("booking_id")%>">
        <div id="staff-check-out-booking-div-1">
            <div class="staff-check-out-booking-label">Họ và tên</div>
            <input disabled class="staff-check-out-booking-input" id="staff-check-out-booking-customer-name"
                   type="text" value="<%=request.getParameter("customer_name")%>"><br>

            <div class="staff-check-out-booking-label">Số điện thoại</div>
            <input disabled class="staff-check-out-booking-input" id="staff-check-out-booking-customer-phone"
                   type="text" value="<%=request.getParameter("customer_phone")%>"><br>

            <div class="staff-check-out-booking-label">Tên sân</div>
            <input disabled class="staff-check-out-booking-input" id="staff-check-out-booking-pitch-name"
                   type="text" value="<%=request.getParameter("pitch_name")%>"><br>

            <div class="staff-check-out-booking-label">Giờ vào</div>
            <input disabled class="staff-check-out-booking-input" id="staff-check-out-booking-time-in"
                   name="staff-check-out-booking-time-in"
                   type="text" value="<%=request.getParameter("booking_time_in")%>"><br>
        </div>

        <div id="staff-check-out-booking-div-2">
            <div class="staff-check-out-booking-label">Mô tả</div>
            <input disabled class="staff-check-out-booking-input" id="staff-check-out-booking-pitch-des"
                   type="text" value="<%=request.getParameter("pitch_des")%>"><br>

            <div class="staff-check-out-booking-label">Số người</div>
            <input disabled class="staff-check-out-booking-input" id="staff-check-out-booking-pitch-num-player"
                   type="text" value="<%=request.getParameter("pitch_num_player")%>"><br>

            <div class="staff-check-out-booking-label">Giá</div>
            <input disabled class="staff-check-out-booking-input" id="staff-check-out-booking-pitch-cost" type="text"
                   value="<%=request.getParameter("pitch_cost")%>"><br>

            <div class="staff-check-out-booking-label">Giờ ra</div>
            <input disabled class="staff-check-out-booking-input" id="staff-check-out-booking-time-out"
                   name="staff-check-out-booking-time-out"
                   type="text" value="<%=request.getParameter("booking_time_out")%>"><br>
        </div>
    </form>

    <button id="staff-check-out-booking-btn-check-out" onmouseout="changeBgColorDefault(this)"
            onmouseover="changeBgColorWhite(this)">Check Out</button>

    <a href="staff_check_out" id="staff-check-out-booking-btn-esc"
       onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">
        Quay lại
    </a>

</div>
<%@include file="resource/layout/staff-footer.jsp"%>
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript" src="js/staff-check-out-booking.js"></script>
</body>
</html>
