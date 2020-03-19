<%@ page import="java.util.ArrayList" %>
<%@ page import="api.model.entity.Pitch" %>
<%@ page import="common.constant.FPConstant" %>
<%@ page import="api.model.entity.BookingBill" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/7/2019
  Time: 5:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/pitch-report-detail.css">
</head>
<body>
<%
    if (session == null || session.getAttribute("staff-account-user-name") == null) {
        response.sendRedirect("staff_login.jsp");
    }
%>
<%@include file="resource/layout/staff-header.jsp"%>
<%
   ArrayList<BookingBill> bookingBills = (ArrayList<BookingBill>) request.getAttribute("list-booking-bill-matched");
%>
<div id="pitch-report-detail-content">
    <div id="staff-home-user-name">
        <%= session.getAttribute("staff-account-user-name") != null ? session.getAttribute("staff-account-user-name") : ""%>
    </div>
    <div id="pitch-report-id">Tên sân : <%=bookingBills.get(0).getPitch().getName()%></div>
    <div>
        <table>
            <tr>
                <th class="title">
                    Mã hóa đơn
                </th>
                <th class="title">
                    Giờ vào
                </th>
                <th class="title">
                    Giờ ra
                </th>
                <th class="title">
                    Tên KH
                </th>
                <th class="title">
                    Tiền Sân
                </th>
            </tr>
            <%
                for(int i = 0; i < bookingBills.size() ; i++){
            %>
            <tr>
                <th><%=bookingBills.get(i).getId()%></th>
                <th><%=bookingBills.get(i).getTimeIn()%></th>
                <th><%=bookingBills.get(i).getTimeOut()%></th>
                <th><%=bookingBills.get(i).getAccountCustomer().getName()%></th>
                <th><%=bookingBills.get(i).getPitch().getType().getUnitCost()%></th>
            </tr>
            <%
                }
            %>
            <%
                Double a = bookingBills.get(0).getPitch().getType().getUnitCost()*bookingBills.size();
            %>
            <tr>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th>Tổng tiền : <%=a%></th>
            </tr>
        </table>
    </div>



</div>
<%@include file="resource/layout/staff-footer.jsp"%>
</body>
</html>
