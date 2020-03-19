<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng nhập</title>
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/customer-login.css">
</head>
<body class="margin-0">
    <div id="customer-login-content">

        <div id="customer-login-div-1">
            <img src="imgs/logo.png" id="customer-login-logo">
            <div id="customer-login-logo-des">
                <span style="color: #fdbd00">FOOTBALL   </span>
                <span style="color: white">PITCH</span>
            </div>
        </div>

        <div id="customer-login-div-2">
            <form id="customer-login-form" method="post" action="customer/login">
                <label class="customer-login-label" for="customer-login-input-user-name">Tên đăng nhập</label>
                <input type="text" id="customer-login-input-user-name" name="customer-login-input-user-name"><br>
                <label class="customer-login-label" for="customer-login-input-user-name">Mật khẩu</label>
                <input type="password" id="customer-login-input-password" name="customer-login-input-password">
            </form>
        </div>

        <div id="customer-login-div-3">
            <button id="customer-login-btn-login"
                onmouseout="changeBgColorDefault(this)"
                onmouseover="changeBgColorWhite(this)">Đăng nhập</button>
        </div>
        <div id="customer-login-account-authenticate">
            <%= session.getAttribute("customer-account-authenticate") != null ?
                    session.getAttribute("customer-account-authenticate") : ""%>
        </div>

    </div>
    <%@include file="resource/layout/footer.jsp"%>
    <script type="text/javascript" src="js/base.js"></script>
    <script type="text/javascript" src="js/customer-login.js"></script>
</body>
</html>
