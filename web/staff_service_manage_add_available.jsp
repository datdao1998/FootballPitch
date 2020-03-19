<%@ page import="api.model.entity.Service" %>
<%@ page import="java.util.List" %>
<%@ page import="api.model.entity.UsedService" %>
<%@ page import="api.model.entity.ServiceBill" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nhập kho</title>
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/staff-service-add-available.css">
</head>
<body class="margin-0">
<%@include file="resource/layout/staff-header.jsp"%>

<div id="staff-service-manage-add-available-content">

    <%
        if (session == null || session.getAttribute("staff-account-id") == null) {
            response.sendRedirect("staff_login.jsp");
        }
    %>

    <div class="div-log-out">
        <a href="staff_logout" class="href-log-out"
           onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Đăng xuất</a>
    </div>

    <div id="staff-service-manage-add-available-div-1">

        <form id="staff-service-manage-add-available-form-search" action="staff_manage_service_add_available_search">
            <input type="text" name="staff-service-manage-add-available-name-search"
                   id="staff-service-manage-add-available-input-search" placeholder="Tên dịch vụ..."
            >
        </form>
        <button id="staff-service-manage-add-available-btn-search" onmouseout="changeBgColorDefault(this)"
                onmouseover="changeBgColorWhite(this)">Tìm</button>

        <table id="staff-service-manage-add-available-table-search">

            <tr>
                <th>Tên</th>
                <th>Loại</th>
                <th>Giá</th>
                <th style="width: 7.5vw">Đơn vị tính</th>
                <th>Còn lại</th>
                <th>Chọn</th>
            </tr>

            <% if (request.getAttribute("servicesAvailable") != null) {
                List<Service> services = (List<Service>) request.getAttribute("servicesAvailable");
                for (int row = 0; row < services.size(); row ++) { %>
            <tr>
                <td><%=services.get(row).getName()%></td>
                <td><%=services.get(row).getType()%></td>
                <td><%=services.get(row).getPrice()%></td>
                <td><%=services.get(row).getUnit()%></td>
                <td><%=services.get(row).getAvailable()%></td>
                <form id="staff-service-manage-add-available-form-select-<%=services.get(row).getId()%>" action="staff_service_manage_add_available_detail.jsp">
                    <input type="hidden" name="staff-service-manage-add-available-id-select"
                           value="<%=services.get(row).getId()%>">
                    <input type="hidden" name="staff-service-manage-add-available-name-search"
                           value="<%=services.get(row).getName()%>">
                    <input type="hidden" name="staff-service-manage-add-available-type-search"
                           value="<%=services.get(row).getType()%>">
                    <input type="hidden" name="staff-service-manage-add-available-price-search"
                           value="<%=services.get(row).getPrice()%>">
                    <input type="hidden" name="staff-service-manage-add-available-unit-search"
                           value="<%=services.get(row).getUnit()%>">
                    <input type="hidden" name="staff-service-manage-add-available-available-search"
                           value="<%=services.get(row).getAvailable()%>">

                </form>
                <td>
                    <button value="<%=services.get(row).getId()%>" class="staff-service-manage-add-available-btn-select"
                            onmouseout="changeBgColorDefault(this)"
                            onmouseover="changeBgColorWhite(this)"
                            onclick= "staffSelectServiceForAddAvailable(this.value)">Thêm</button>
                </td>
            </tr>
            <% } %>
            <% } %>
        </table>
    </div>

</div>

<%@include file="resource/layout/staff-footer.jsp"%>
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript" src="js/staff-service-add-available.js"></script>
</body>
</html>
