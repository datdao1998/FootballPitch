<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12/7/2019
  Time: 10:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Detail</title>
    <link rel="stylesheet" type="text/css" href="css/staff-service-manage-add-available-detail.css">
    <link rel="stylesheet" type="text/css" href="css/base.css">
</head>
<body>
<%
    if (session == null || session.getAttribute("staff-account-id") == null) {
        response.sendRedirect("staff_login.jsp");
    }
%>
<%@include file="resource/layout/staff-header.jsp"%>
    <div id="staff-service-manage-detail-content">
        <div class="div-log-out">
            <a href="staff_logout" class="href-log-out"
               onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Đăng xuất</a>
        </div>
        <form id="staff-service-manage-detail-form" action="staff_service_manage_update_available">
            <input type="hidden" id="staff-service-manage-detail-id" name="staff-service-manage-detail-id" value="<%=request.getParameter("staff-service-manage-add-available-id-select")%>">
            <div id="staff-service-manage-detail-div-1">
                <div class="staff-service-manage-detail-label">Tên dịch vụ</div>
                <input disabled class="staff-service-manage-detail-input" id="staff-service-manage-detail-service-name"
                       type="text" value="<%=request.getParameter("staff-service-manage-add-available-name-search")%>"><br>

                <div class="staff-service-manage-detail-label">Loại</div>
                <input disabled class="staff-service-manage-detail-input" id="staff-service-manage-detail-service-type"
                       type="text" value="<%=request.getParameter("staff-service-manage-add-available-type-search")%>"><br>

                <div class="staff-service-manage-detail-label">Giá</div>
                <input disabled class="staff-service-manage-detail-input" id="staff-service-manage-detail-service-price"
                       type="text" value="<%=request.getParameter("staff-service-manage-add-available-price-search")%>"><br>

                <div class="staff-service-manage-detail-label">Đơn vị tính</div>
                <input disabled class="staff-service-manage-detail-input" id="staff-service-manage-detail-service-unit"
                       type="text" value="<%=request.getParameter("staff-service-manage-add-available-unit-search")%>"><br>

                <div class="staff-service-manage-detail-label">Còn lại</div>
                <input disabled class="staff-service-manage-detail-input" id="staff-service-manage-detail-service-available"
                       type="number" value="<%=request.getParameter("staff-service-manage-add-available-available-search")%>"><br>
            </div>

            <div id="staff-service-manage-detail-div-2">
                <div class="staff-service-manage-detail-label">Nhập số lượng mới</div>
                <input type="number" id="staff-service-manage-add-available-input-add" name="staff-service-manage-add-available-input-add"
                       onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)" onchange="staffChangeQuantity()" value="<%=Integer.parseInt(request.getParameter("staff-service-manage-add-available-available-search"))+1%>">
            </div>

            <div id="staff-service-manage-detail-div-3">
                <button id="staff-service-manage-detail-btn-add-available" onmouseout="changeBgColorDefault(this)"
                        onmouseover="changeBgColorWhite(this)">Thêm</button>
            </div>

        </form>

        <a href="staff_manage_service_add_available_search" id="staff-service-manage-detail-btn-esc"
           onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">
            Quay lại
        </a>

    </div>
<%@include file="resource/layout/staff-footer.jsp"%>
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript" src="js/staff-service-add-available-detail.js"></script>
</body>
</html>
