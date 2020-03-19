<%@ page import="api.model.entity.BookingBill" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12/7/2019
  Time: 2:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check out</title>
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/staff-check-out.css">
</head>
<body class="margin-0" style="height: 134vh">

<%@include file="resource/layout/staff-header.jsp"%>
<div id="staff-check-out-content">
    <%
        if (session == null || session.getAttribute("staff-account-id") == null) {
            response.sendRedirect("staff_login.jsp");
        }
    %>

    <div class="div-log-out">
        <a href="staff_logout" class="href-log-out"
           onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Đăng xuất</a>
    </div>

    <div id="staff-check-out-div-1"></div>

    <div id="staff-check-out-div-2">
        <table id="staff-check-out-table">
            <% if (request.getAttribute("pitchesCheckOut") != null) {
                List<BookingBill> bookingBills = (List<BookingBill>) request.getAttribute("pitchesCheckOut");
                for (int row = 0; row <= bookingBills.size()/3; row++) { %>
            <tr>
                <% for (int col = 0; col < 3 && (row * 3 + col) <  bookingBills.size(); col ++) { %>
                <td class="staff-check-out-td">
                    <form id="staff-check-out-select-pitch-<%=bookingBills.get(row*3 + col).getId()%>" action="staff_process_check_out_booking.jsp">
                        <input type="hidden" name="pitch_name" value="<%=bookingBills.get(row*3 + col).getPitch().getName()%>">
                        <input type="hidden" name="pitch_id" value="<%=bookingBills.get(row*3 + col).getPitch().getId()%>">
                        <input type="hidden" name="pitch_des" value="<%=bookingBills.get(row*3 + col).getPitch().getDescription()%>">
                        <input type="hidden" name="pitch_num_player" value="<%=bookingBills.get(row*3 + col).getPitch().getType().getNumOfPlayer()%>">
                        <input type="hidden" name="pitch_cost" value="<%=bookingBills.get(row*3 + col).getPitch().getType().getUnitCost()%>">
                        <input type="hidden" name="booking_time_in" value="<%=bookingBills.get(row*3+col).getTimeIn()%>">
                        <input type="hidden" name="booking_time_out" value="<%=bookingBills.get(row*3+col).getTimeOut()%>">
                        <input type="hidden" name="booking_id" value="<%=bookingBills.get(row*3+col).getId()%>">
                        <input type="hidden" name="customer_name" value="<%=bookingBills.get(row*3+col).getAccountCustomer().getName()%>">
                        <input type="hidden" name="customer_phone" value="<%=bookingBills.get(row*3+col).getAccountCustomer().getPhone()%>">
                    </form>
                    <button value="<%=bookingBills.get(row*3 + col).getId()%>"
                            id="staff-check-out-btn-select-pitch-<%=bookingBills.get(row*3 + col).getId()%>"
                            onclick="customerSubmitBooking('staff-check-out-select-pitch-' + this.value)"
                            class="staff-check-out-td-background" onmouseover="changeBorderBlack(this)"
                            onmouseout="changeBorderNone(this)">
                    </button>
                    <button class="staff-check-out-td-content"
                            value="<%=bookingBills.get(row*3 + col).getId()%>"
                            onclick="customerSubmitBooking('staff-check-out-select-pitch-' + this.value)"
                            onmouseover="changeBorderBlack(document.getElementById('staff-check-out-btn-select-pitch-' + this.value))"
                            onmouseout="changeBorderNone(document.getElementById('staff-check-out-btn-select-pitch-' + this.value))">
                        <span style="font-size: 3vmax">Sân: <%=bookingBills.get(row*3 + col).getPitch().getName()%></span><br>
                        <span style="font-size: 1.5vmax">Giờ ra: <%=bookingBills.get(row*3 + col).getTimeOut()%></span><br>
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

<%@include file="resource/layout/staff-footer.jsp"%>
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript" src="js/customer-booking.js"></script>
</body>
</html>
