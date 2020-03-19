<%@ page import="api.model.entity.BookingBill" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12/5/2019
  Time: 6:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booked</title>
    <link rel="stylesheet" type="text/css" href="css/booked.css">
    <link rel="stylesheet" type="text/css" href="css/base.css">
</head>
<body class="magin-0">

<%
    if (session == null || session.getAttribute("customer-account-id") == null) {
        response.sendRedirect("customer_login.jsp");
    }
%>
    <%@include file="resource/layout/header.jsp"%>
<div id="customer-booking-content">
    <div class="div-log-out">
        <a href="customer_log_out" class="href-log-out"
           onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Đăng xuất</a>
    </div>
    <div id="customer-booking-div-1"></div>

    <div id="customer-booking-div-2">
        <table id="customer-booking-table">
            <% if (request.getAttribute("pitchesBooked") != null) {
                List<BookingBill> bookingBills = (List<BookingBill>) request.getAttribute("pitchesBooked");
                for (int row = 0; row <= bookingBills.size()/3; row++) { %>
            <tr>
                <% for (int col = 0; col < 3 && (row * 3 + col) <  bookingBills.size(); col ++) { %>
                <td class="customer-booking-td">
                    <form id="customer-booking-select-pitch-<%=bookingBills.get(row*3 + col).getId()%>" action="customer_view_booking.jsp">
                        <input type="hidden" name="pitch_name" value="<%=bookingBills.get(row*3 + col).getPitch().getName()%>">
                        <input type="hidden" name="pitch_id" value="<%=bookingBills.get(row*3 + col).getPitch().getId()%>">
                        <input type="hidden" name="pitch_des" value="<%=bookingBills.get(row*3 + col).getPitch().getDescription()%>">
                        <input type="hidden" name="pitch_num_player" value="<%=bookingBills.get(row*3 + col).getPitch().getType().getNumOfPlayer()%>">
                        <input type="hidden" name="pitch_cost" value="<%=bookingBills.get(row*3 + col).getPitch().getType().getUnitCost()%>">
                        <input type="hidden" name="booking_time_in" value="<%=bookingBills.get(row*3+col).getTimeIn()%>">
                        <input type="hidden" name="booking_time_out" value="<%=bookingBills.get(row*3+col).getTimeOut()%>">
                        <input type="hidden" name="booking_id" value="<%=bookingBills.get(row*3+col).getId()%>">
                    </form>
                    <button value="<%=bookingBills.get(row*3 + col).getId()%>"
                            id="customer-booking-btn-select-pitch-<%=bookingBills.get(row*3 + col).getId()%>"
                            onclick="customerSubmitBooking('customer-booking-select-pitch-' + this.value)"
                            class="customer-booking-td-background" onmouseover="changeBorderBlack(this)"
                            onmouseout="changeBorderNone(this)">
                    </button>
                    <button class="customer-booking-td-content"
                            value="<%=bookingBills.get(row*3 + col).getId()%>"
                            onclick="customerSubmitBooking('customer-booking-select-pitch-' + this.value)"
                            onmouseover="changeBorderBlack(document.getElementById('customer-booking-btn-select-pitch-' + this.value))"
                            onmouseout="changeBorderNone(document.getElementById('customer-booking-btn-select-pitch-' + this.value))">
                        <span style="font-size: 3vmax">Sân: <%=bookingBills.get(row*3 + col).getPitch().getName()%></span><br>
                        <span style="font-size: 1.5vmax">Giờ vào: <%=bookingBills.get(row*3 + col).getTimeIn()%></span><br>
                        <span style="font-size: 1.5vmax">Giá: <%=bookingBills.get(row*3 + col).getPitch().getType().getUnitCost()%></span><br>
                    </button>
                </td>
                <% } %>
            </tr>
            <% } %>
            <% } %>
        </table>
    </div>
</div>
    <%@include file="resource/layout/footer.jsp"%>
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript" src="js/customer-booking.js"></script>
</body>
</html>
