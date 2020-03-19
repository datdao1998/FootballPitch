<%@ page import="api.model.entity.Service" %>
<%@ page import="java.util.List" %>
<%@ page import="api.model.entity.UsedService" %>
<%@ page import="api.model.entity.ServiceBill" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bán hàng</title>
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/staff-service-sell.css">
</head>
<body class="margin-0">
<%@include file="resource/layout/staff-header.jsp"%>

<div id="staff-service-manage-sell-content">

    <%
        if (session == null || session.getAttribute("staff-account-id") == null) {
            response.sendRedirect("staff_login.jsp");
        }
    %>

    <div class="div-log-out">
        <a href="staff_logout" class="href-log-out"
           onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Đăng xuất</a>
    </div>

    <div id="staff-service-manage-sell-div-1">


        <table id="staff-service-manage-sell-table-search">

            <tr>
                <th style="width: 9vw">Tên Nhân Viên</th>
                <th style="width: 9vw">Tổng Tiền</th>
                <th>Ngày</th>
                <th>Chi Tiết</th>
            </tr>

            <% if (request.getAttribute("servicesBill") != null) {
                List<ServiceBill> servicesBill = (List<ServiceBill>) request.getAttribute("servicesBill");
                for (int row = 0; row < servicesBill.size(); row ++) { %>
                    <tr>
                        <td><%=servicesBill.get(row).getAccount().getName()%></td>
                        <td><%=servicesBill.get(row).getTotalPayment()%></td>
                        <td><%=servicesBill.get(row).getTime()%></td>
                        <form id="staff-service-manage-sell-form-select-<%=row%>" action="staff_home_report_search">
                            <input type="hidden" name="booked-service-input-date"
                                   value="<%=request.getAttribute("start_time")%>">
                            <input type="hidden" name="booked-service-end-input-date"
                                   value="<%=request.getAttribute("end_time")%>">
                            <input type="hidden" name="row"
                                   value="<%=row%>">
                        </form>
                        <td>
                            <button value="<%=row%>" class="staff-service-manage-sell-btn-select"
                                    onmouseout="changeBgColorDefault(this)"
                                    onmouseover="changeBgColorWhite(this)"
                                    onclick= "staffSelectSViceBillDetail(this.value)">Xem</button>
                        </td>
                    </tr>
            <% } %>
            <% } %>
            <script>
                function staffSelectSViceBillDetail(id) {
                    document.getElementById("staff-service-manage-sell-form-select-" + id).submit();
                }
            </script>
        </table>
    </div>

    <div id="staff-service-manage-sell-div-2">

        <div id="staff-service-manage-sell-div-invoice">CHI TIẾT</div>

        <table id="staff-service-manage-sell-table-confirm">
            <tr>
                <th>Tên</th>
                <th>Giá</th>
                <th>Số lượng</th>
                <th style="width: 10vw;">Thành tiền</th>
            </tr>
            <% if (request.getAttribute("listUservice") != null) {
                List<UsedService>  usedServices= (List<UsedService>) request.getAttribute("listUservice");
                Double totalPayment = new Double(0);
                for (int row = 0; row < usedServices.size(); row ++) {
                    totalPayment += usedServices.get(row).getQuantity() * usedServices.get(row).getService().getPrice(); %>
            <tr>
                <td><%=usedServices.get(row).getService().getName()%></td>
                <td><%=usedServices.get(row).getService().getPrice()%></td>
                <td><%=usedServices.get(row).getQuantity()%></td>
                <td>
                    <%=usedServices.get(row).getQuantity() * usedServices.get(row).getService().getPrice()%>
                </td>
            </tr>
            <% } %>

            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td style="color: #FDAB0A; font-size: 1.5vmax; font-family: Bahnschrift;">
                    Tổng: <%=totalPayment%>
                </td>
            </tr>

            <%
                }
            %>
        </table>

        <div id="staff-service-manage-div-selling">
            <a href="staff_home.jsp" id="staff-service-manage-href-selling">
                <button id="staff-service-manage-btn-selling" onmouseover="changeBgColorWhite(this)"
                        onmouseout="changeBgColorDefault(this)">
                    TRANG CHỦ
                </button>
            </a>
        </div>
    </div>
</div>

<%@include file="resource/layout/staff-footer.jsp"%>
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript" src="js/staff-service-sell.js"></script>
</body>
</html>
