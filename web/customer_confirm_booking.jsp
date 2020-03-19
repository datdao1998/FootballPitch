<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Xác nhận</title>
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/customer-confirm-booking.css">
</head>
<body class="margin-0">
    <%
        if (session == null || session.getAttribute("customer-account-id") == null ||
                session.getAttribute("customer-booking-time-in") == null) {
            response.sendRedirect("customer_login.jsp");
        }
        if (request.getParameter("pitch_id") == null) {
            response.sendRedirect("customer_booking.jsp");
        }
    %>
    <%@include file="resource/layout/header.jsp"%>
    <div id="customer-confirm-booking-content">
        <div class="div-log-out">
            <a href="customer_log_out" class="href-log-out"
               onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Đăng xuất</a>
        </div>
        <form id="customer-confirm-booking-form" action="customer_confirm_booking">
            <input type="hidden" name="customer-confirm-booking-pitch-id" value="<%=request.getParameter("pitch_id")%>">
            <div id="customer-confirm-booking-div-1">
                <div class="customer-confirm-booking-label">Họ và tên</div>
                <input disabled class="customer-confirm-booking-input" id="customer-confirm-booking-customer-name"
                       type="text" value="<%=session.getAttribute("customer-account-name")%>"><br>

                <div class="customer-confirm-booking-label">Số điện thoại</div>
                <input disabled class="customer-confirm-booking-input" id="customer-confirm-booking-customer-phone"
                       type="text" value="<%=session.getAttribute("customer-account-phone")%>"><br>

                <div class="customer-confirm-booking-label">Tên sân</div>
                <input disabled class="customer-confirm-booking-input" id="customer-confirm-booking-pitch-name"
                       type="text" value="<%=request.getParameter("pitch_name")%>"><br>

                <div class="customer-confirm-booking-label">Giờ vào</div>
                <input disabled class="customer-confirm-booking-input" id="customer-confirm-booking-time-in"
                        name="customer-confirm-booking-time-in"
                       type="text" value="<%=session.getAttribute("customer-booking-time-in")%>"><br>
            </div>

            <div id="customer-confirm-booking-div-2">
                <div class="customer-confirm-booking-label">Mô tả</div>
                <input disabled class="customer-confirm-booking-input" id="customer-confirm-booking-pitch-des"
                       type="text" value="<%=request.getParameter("pitch_des")%>"><br>

                <div class="customer-confirm-booking-label">Số người</div>
                <input disabled class="customer-confirm-booking-input" id="customer-confirm-booking-pitch-num-player"
                       type="text" value="<%=request.getParameter("pitch_num_player")%>"><br>

                <div class="customer-confirm-booking-label">Giá</div>
                <input disabled class="customer-confirm-booking-input" id="customer-confirm-booking-pitch-cost" type="text"
                       value="<%=request.getParameter("pitch_cost")%>"><br>

                <div class="customer-confirm-booking-label">Giờ ra</div>
                <input disabled class="customer-confirm-booking-input" id="customer-confirm-booking-time-out"
                       name="customer-confirm-booking-time-out"
                       type="text" value="<%=session.getAttribute("customer-booking-time-out")%>"><br>
            </div>
        </form>

        <button id="customer-confirm-booking-btn-confirm" onmouseout="changeBgColorDefault(this)"
                onmouseover="changeBgColorWhite(this)" onclick="customerSubmitConfirmBooking()">Đặt sân</button>
        <a href="customer_booking.jsp" id="customer-confirm-booking-cancel"
            onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">
            Quay lại
        </a>

    </div>
    <%@include file="resource/layout/footer.jsp"%>
    <script type="text/javascript" src="js/base.js"></script>
    <script>
        function customerSubmitConfirmBooking() {
            document.getElementById("customer-confirm-booking-form").submit();
        }
    </script>
</body>
</html>
