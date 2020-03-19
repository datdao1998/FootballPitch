<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quản lý service</title>
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/staff-service.css">
    <link rel="stylesheet" type="text/css" href="css/staff-home.css">
</head>
<body class="margin-0">
    <%@include file="resource/layout/staff-header.jsp"%>

        <div id = "staff-service-manage-content">

            <%
                if (session == null || session.getAttribute("staff-account-id") == null) {
                    response.sendRedirect("staff_login.jsp");
                }
            %>

            <div class="div-log-out">
                <a href="staff_logout" class="href-log-out"
                   onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Đăng xuất</a>
            </div>

            <div id="staff-service-manage-div-1">
                <a href="staff_service_manage_sell.jsp" class="staff-service-manage-href">
                    <div id="staff-service-manage-sell" onmouseover="changeBgColorWhite(this)" onmouseout="changeBgColorDefault(this)">
                        BÁN HÀNG
                    </div>
                </a>
            </div>

            <div id="staff-service-manage-div-2">
                <a href="staff_service_manage_add_available.jsp" class="staff-service-manage-href">
                    <div id="staff-service-manage-warehouse" onmouseover="changeBgColorWhite(this)" onmouseout="changeBgColorDefault(this)">
                        NHẬP KHO
                    </div>
                </a>
            </div>

            <div id="staff-service-manage-div-3">
                <a href="staff_service_manage_add.jsp" class="staff-service-manage-href">
                    <div id="staff-service-manage-add" onmouseover="changeBgColorWhite(this)" onmouseout="changeBgColorDefault(this)">
                        THÊM MỚI
                    </div>
                </a>
            </div>

            <div id="staff-service-manage-div-4">
                <a href="staff_update_service.jsp" class="staff-service-manage-href">
                    <div id="staff-service-manage-update" onmouseover="changeBgColorWhite(this)" onmouseout="changeBgColorDefault(this)">
                        SỬA ĐỔI
                    </div>
                </a>
            </div>
        </div>

    <%@include file="resource/layout/staff-footer.jsp"%>
    <script type="text/javascript" src="js/base.js"></script>
</body>
</html>
