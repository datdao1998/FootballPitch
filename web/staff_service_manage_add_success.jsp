<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm Thành Công</title>
    <link rel="stylesheet" type="text/css" href="css/sign-up.css">
    <link rel="stylesheet" type="text/css" href="css/base.css">
</head>
<body id="body-sign-up-success">
<%
    if (request.getAttribute("nameService") == null) {
        response.sendRedirect("index.jsp");
    }
%>
<%@include file="resource/layout/staff-header.jsp"%>
<div id="sign-up-success-content">

    <%
        if (session == null || session.getAttribute("staff-account-id") == null) {
            response.sendRedirect("staff_login.jsp");
        }
    %>

    <div class="div-log-out">
        <a href="staff_logout" class="href-log-out"
           onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Đăng xuất</a>
    </div>
    <div id="sign-up-success-div1">
        Thêm  Thành Công mặt hàng  <br>
        <span style="color: #FDAB0A"><%= request.getAttribute("nameService")%></span>
    </div>

</div>
<%@include file="resource/layout/staff-footer.jsp"%>


<script type="text/javascript" src="js/base.js"></script>
</body>
</html>
