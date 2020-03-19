<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 07/12/2019
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm Mới Dịch Vụ</title>
    <link rel="stylesheet" type="text/css" href="css/staff_service_manage_add.css">
    <link rel="stylesheet" type="text/css" href="css/base.css">
</head>
<body>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="resource/layout/staff-header.jsp"%>
<div id="staff-service-add-service-content">

    <%
        if (session == null || session.getAttribute("staff-account-id") == null) {
            response.sendRedirect("staff_login.jsp");
        }
    %>

    <div class="div-log-out">
        <a href="staff_logout" class="href-log-out"
           onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Đăng xuất</a>
    </div>
    <div id="div-form-staff-service-add-service">
        <form id="form-staff-service-add-service" action="staff_service_manage_add" method="post">
            <div class="float-left" id="div-staff-service-add-service-1">
                <input name="input-staff-service-add-service-name" id="input-staff-service-add-service-name" class="input-staff-service-add-service" type="text" placeholder="Tên"><br>
                <input name="input-staff-service-add-service-type" id="input-staff-service-add-service-type" class="input-staff-service-add-service" type="text" placeholder="Loại"><br>
                <input name="input-staff-service-add-service-available" id="input-staff-service-add-service-available" class="input-staff-service-add-service" type="number" placeholder="Số Lượng"><br>
            </div>
            <div class="float-left" id="div-staff-service-add-service-2">
                <input name="input-staff-service-add-service-unit" id="input-staff-service-add-service-unit" class="input-staff-service-add-service" type="text" placeholder="Đơn Vị"><br>
                <input name="input-staff-service-add-service-price" id="input-staff-service-add-service-price" class="input-staff-service-add-service" type="number" placeholder="Giá"><br>
            </div>
        </form>

        <button id="btn-submit-staff-service-add-service">Thêm Vào</button>
    </div>
</div>
<%@include file="resource/layout/staff-footer.jsp"%>

<script type="text/javascript" src="js/staff-service-manage-add.js"></script>
<script type="text/javascript" src="js/base.js"></script>


</body>
</html>
