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

                <form id="staff-service-manage-sell-form-search" action="staff_manage_service_sell_search">
                    <input type="text" name="staff-service-manage-sell-name-search"
                           id="staff-service-manage-sell-input-search" placeholder="Tên dịch vụ..."
                    >
                </form>
                <button id="staff-service-manage-sell-btn-search" onmouseout="changeBgColorDefault(this)"
                onmouseover="changeBgColorWhite(this)">Tìm</button>

                <table id="staff-service-manage-sell-table-search">

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
                                <td id="quantity-max-<%=services.get(row).getId()%>"><%=services.get(row).getAvailable()%></td>
                                <form id="staff-service-manage-sell-form-select-<%=services.get(row).getId()%>" action="staff_add_sell_service_to_session">

                                    <input type="hidden" name="staff-service-manage-sell-id-select"
                                           value="<%=services.get(row).getId()%>">

                                    <input type="hidden" name="staff-service-manage-sell-name-search"
                                           value="<%=request.getAttribute("textSearch")%>">

                                </form>
                                <td>
                                    <button value="<%=services.get(row).getId()%>" class="staff-service-manage-sell-btn-select"
                                            onmouseout="changeBgColorDefault(this)"
                                            onmouseover="changeBgColorWhite(this)"
                                            onclick= "staffSelectServiceForSell(this.value)">Chọn</button>
                                </td>
                            </tr>
                        <% } %>
                    <% } %>
                </table>
            </div>

            <div id="staff-service-manage-sell-div-2">

                <div id="staff-service-manage-sell-div-invoice">HÓA ĐƠN</div>

                <table id="staff-service-manage-sell-table-confirm">
                    <tr>
                        <th>Tên</th>
                        <th>Giá</th>
                        <th>Số lượng</th>
                        <th style="width: 10vw;">Thành tiền</th>
                    </tr>
                    <% if (session.getAttribute("staff-sell-service-bill") != null) {
                        ServiceBill serviceBill = (ServiceBill) session.getAttribute("staff-sell-service-bill");
                        List<UsedService> usedServices = serviceBill.getUsedServiceList();
                        Double totalPayment = new Double(0);
                        for (int row = 0; row < usedServices.size(); row ++) {
                            totalPayment += usedServices.get(row).getQuantity() * usedServices.get(row).getService().getPrice(); %>
                            <tr>
                                <td><%=usedServices.get(row).getService().getName()%></td>
                                <td><%=usedServices.get(row).getService().getPrice()%></td>
                                <td>
                                    <form id="staff-service-manage-sell-form-change-quantity-<%=usedServices.get(row).getService().getId()%>"
                                    action="staff_service_manage_sell_change_quantity">

                                        <input type="hidden" name="staff-service-manage-sell-input-quantity-id"
                                        value="<%=usedServices.get(row).getService().getId()%>">

                                        <input type="hidden" name="staff-service-manage-sell-name-search"
                                               value="<%=request.getAttribute("textSearch")%>">

                                        <input id="staff-service-manage-sell-input-quantity-<%=usedServices.get(row).getService().getId()%>"
                                               name="staff-service-manage-sell-input-quantity" style="text-align: center; width: 3vw;
                                               border-radius: 5px; margin-top: 2vh; font-weight: bold;" type="number"
                                               value="<%=usedServices.get(row).getQuantity()%>"
                                               onchange="staffChangeQuantity(this.id)">

                                    </form>
                                </td>
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
                            serviceBill.setTotalPayment(totalPayment);
                            session.setAttribute("staff-sell-service-bill", serviceBill);
                        }
                    %>
                </table>

                <div id="staff-service-manage-div-selling">
                    <a href="staff_service_manage_selling" id="staff-service-manage-href-selling">
                        <button id="staff-service-manage-btn-selling" onmouseover="changeBgColorWhite(this)"
                                onmouseout="changeBgColorDefault(this)">
                            XUẤT KHO
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
