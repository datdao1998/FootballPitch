<%@ page import="api.model.entity.Pitch" %>
<%@ page import="api.model.entity.Service" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="common.constant.FPConstant" %>
<%@ page import="api.model.entity.BookingBill" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pitch Report</title>
    <link rel="stylesheet" type="text/css" href="css/staff-pitch-report.css">
</head>
<body>
<%
    if (session == null || session.getAttribute("staff-account-user-name") == null) {
        response.sendRedirect("staff_login.jsp");
    }
%>
<%@include file="resource/layout/staff-header.jsp"%>
<div id="staff-pitch-report-content">
    <div id="staff-home-user-name">
        <%= session.getAttribute("staff-account-user-name") != null ? session.getAttribute("staff-account-user-name") : ""%>
    </div>
    <div id="list-pitch-report-title">Danh Sách Sân Sử Dụng</div>
    <div id="list-pitch-report">
        <form id="staff-pitch-report-form" action="staff_pitch_report" method="get">
            <label class="staff-pitch-report-label-form-update" >Ngày bắt đầu &nbsp;</label>
            <input type="text" id="staff-pitch-report-input-time-in" name="staff-pitch-report-input-time-in" placeholder=""
                   onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
            <label class="staff-pitch-report-label-form-update">Ngày kết thúc &nbsp;</label>
            <input type="text" id="staff-pitch-report-input-time-out" name="staff-pitch-report-input-time-out" placeholder=""
                   onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
            <label class="staff-pitch-report-label-form-update" for="staff-pitch-report-input-num-player">Số người </label>
            <select id="staff-pitch-report-input-num-player" name="staff-pitch-report-input-num-player"
                    onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
                <option>5</option>
                <option>7</option>
                <option>11</option>
            </select>
        </form>
        <button id="staff-pitch-report-btn-search" onmouseover="changeBgColorWhite(this)" onclick="btnPitchReportSearchClicked()"
                onmouseout="changeBgColorDefault(this)">Tìm kiếm</button>
    </div>
    <%
        if(request.getAttribute("list-booking-bill")!=null){
            ArrayList<Pitch> pitches = (ArrayList<Pitch>) request.getAttribute("set-pitch");
            ArrayList<BookingBill> bookingBills = (ArrayList<BookingBill>) request.getAttribute("list-booking-bill");
    %>
    <div id="pitch-result">
        <table>
            <tr>
                <th class="title">
                    Id
                </th>
                <th class="title">
                    Tên sân
                </th>
                <th class="title">
                    Số người
                </th>
                <th class="title">
                    Tổng tiền
                </th>
                <th class="title">
                    Chi tiết
                </th>
            </tr>
            <%
                for(int i = 0 ; i < pitches.size(); i++){
                    double total = 0;
                    for(int j = 0 ; j < bookingBills.size() ; j++){
                        if(bookingBills.get(j).getPitch().getId() == pitches.get(i).getId()){
                            total = total + pitches.get(i).getType().getUnitCost();
                        }
                    }
            %>
            <tr>
                <th><%=pitches.get(i).getId()%></th>
                <th><%=pitches.get(i).getName()%></th>
                <th><%=pitches.get(i).getType().getNumOfPlayer()%></th>
                <th><%=total%></th>
                <th>
                    <form action="pitch_report_detail" method="get">
                        <%
                            String listBookingBillId = "";
                            for(int ii = 0 ; ii < bookingBills.size(); ii++){
                                if(bookingBills.get(ii).getPitch().getId()==pitches.get(i).getId()){
                                    listBookingBillId = listBookingBillId + " " + bookingBills.get(ii).getId();
                                }
                            }
                        %>
                        <input type="hidden" name="list-booking-bill-id" value="<%=listBookingBillId%>">
                        <input type="submit" class="update-button" value="Chi tiết">
                    </form>
                </th>
            </tr>
            <%
                }
            %>
        </table>
    </div>
    <%
        }
    %>
</div>
<%@include file="resource/layout/staff-footer.jsp"%>
<script type="text/javascript" src="js/staff-pitch-report.js"></script>
</body>
</html>
