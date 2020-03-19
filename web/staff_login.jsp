<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng nhập</title>
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/staff-login.css">
</head>
<body class="margin-0">
<div id="staff-login-content">
    <div id="staff-login-div-1">
        <img src="imgs/logo.png" id="staff-login-logo">
        <div id="staff-login-logo-des">
            <span style="color: #fdbd00">FOOTBALL   </span>
            <span style="color: white">PITCH</span>
        </div>
    </div>
    <div id="staff-login-page-notification">
        <h3>This page for staff only</h3>
    </div>
    <div id="staff-login-div-2">
        <form id="staff-login-form" method="post" action="staff/login">
            <label class="staff-login-label" for="staff-login-input-user-name">Tên đăng nhập</label>
            <input type="text" id="staff-login-input-user-name" name="staff-login-input-user-name"><br>
            <label class="staff-login-label" for="staff-login-input-user-name">Mật khẩu</label>
            <input type="password" id="staff-login-input-password" name="staff-login-input-password">
        </form>
    </div>

    <div id="staff-login-div-3">
        <button id="staff-login-btn-login"
                onmouseout="changeBgColorDefault(this)"
                onmouseover="changeBgColorWhite(this)">Đăng nhập</button>
    </div>
    <div id="staff-login-account-authenticate">
        <%= session.getAttribute("staff-account-authenticate") != null ?
                session.getAttribute("staff-account-authenticate") : ""%>

    </div>

</div>
<%@include file="resource/layout/staff-footer.jsp"%>
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript" src="js/staff-login.js"></script>
</body>
</html>