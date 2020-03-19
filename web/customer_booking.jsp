<%@ page import="api.model.entity.Pitch" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đặt sân</title>
    <link rel="stylesheet" type="text/css" href="css/customer-booking.css">
    <link rel="stylesheet" type="text/css" href="css/base.css">
</head>
<body class="margin-0">
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

        <div id="customer-booking-div-1">
            <form id="customer-booking-form-search" action="customer_search_available_pitch" method="get">
                <label class="customer-booking-label-form-search" for="customer-booking-input-time">Chọn giờ &nbsp;</label>
                <select id="customer-booking-input-time" name="customer-booking-input-time"
                        onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
                    <option>08:00:00</option>
                    <option>09:30:00</option>
                    <option>13:00:00</option>
                    <option>14:30:00</option>
                    <option>16:00:00</option>
                    <option>17:30:00</option>
                    <option>19:00:00</option>
                    <option>20:30:00</option>
                </select>
                <label class="customer-booking-label-form-search" for="customer-booking-input-date">Chọn ngày &nbsp;</label>
                <input type="text" id="customer-booking-input-date" name="customer-booking-input-date" placeholder="dd/mm/yyyy"
                       onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
                <label class="customer-booking-label-form-search" for="customer-booking-input-num-player">Loại sân &nbsp;</label>
                <select id="customer-booking-input-num-player" name="customer-booking-input-num-player"
                        onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
                    <option>5</option>
                    <option>7</option>
                    <option>11</option>
                </select>
            </form>
            <button id="customer-booking-btn-search" onmouseover="changeBgColorWhite(this)"
            onmouseout="changeBgColorDefault(this)">Tìm kiếm</button>
        </div>

        <div id="customer-booking-div-2">
            <table id="customer-booking-table">
                <% if (request.getAttribute("pitchsAvailable") != null) {
                    List<Pitch> pitches = (List<Pitch>) request.getAttribute("pitchsAvailable");
                    for (int row = 0; row <= pitches.size()/3; row++) { %>
                        <tr>
                            <% for (int col = 0; col < 3 && (row * 3 + col) < pitches.size(); col ++) { %>
                                <td class="customer-booking-td">
                                    <form id="customer-booking-select-pitch-<%=pitches.get(row*3 + col).getId()%>" action="customer_confirm_booking.jsp">
                                        <input type="hidden" name="pitch_name" value="<%=pitches.get(row*3 + col).getName()%>">
                                        <input type="hidden" name="pitch_id" value="<%=pitches.get(row*3 + col).getId()%>">
                                        <input type="hidden" name="pitch_des" value="<%=pitches.get(row*3 + col).getDescription()%>">
                                        <input type="hidden" name="pitch_num_player" value="<%=pitches.get(row*3 + col).getType().getNumOfPlayer()%>">
                                        <input type="hidden" name="pitch_cost" value="<%=pitches.get(row*3 + col).getType().getUnitCost()%>">
                                    </form>
                                    <button value="<%=pitches.get(row*3 + col).getId()%>"
                                            id="customer-booking-btn-select-pitch-<%=pitches.get(row*3 + col).getId()%>"
                                            onclick="customerSubmitBooking('customer-booking-select-pitch-' + this.value)"
                                            class="customer-booking-td-background" onmouseover="changeBorderBlack(this)"
                                            onmouseout="changeBorderNone(this)">
                                    </button>
                                    <button class="customer-booking-td-content"
                                            value="<%=pitches.get(row*3 + col).getId()%>"
                                            onclick="customerSubmitBooking('customer-booking-select-pitch-' + this.value)"
                                            onmouseover="changeBorderBlack(document.getElementById('customer-booking-btn-select-pitch-' + this.value))"
                                            onmouseout="changeBorderNone(document.getElementById('customer-booking-btn-select-pitch-' + this.value))">
                                        <span style="font-size: 3vmax">Sân: <%=pitches.get(row*3 + col).getName()%></span><br>
                                        <span style="font-size: 1.5vmax">Mô tả: <%=pitches.get(row*3 + col).getDescription()%></span><br>
                                        <span style="font-size: 1.5vmax">Giá: <%=pitches.get(row*3 + col).getType().getUnitCost()%></span><br>
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
