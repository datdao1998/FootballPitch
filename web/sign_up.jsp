<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng ký</title>
    <link rel="stylesheet" type="text/css" href="css/sign-up.css">
</head>
<body id="body-sign-up">

    <%@include file="resource/layout/header.jsp"%>
        <div id="sign-up-content">
            <div id="div-form-sign-up">
                <form id="form-sign-up" action="sign_up" method="post">
                    <div class="float-left" id="div-sign-up-1">
                        <input name="input-sign-up-name" id="input-sign-up-name" class="input-sign-up" type="text" placeholder="Họ và tên"><br>
                        <input name="input-sign-up-email" id="input-sign-up-email" class="input-sign-up" type="text" placeholder="Email"><br>
                        <input name="input-sign-up-phone" id="input-sign-up-phone" class="input-sign-up" type="number" placeholder="Số điện thoại"><br>
                    </div>
                    <div class="float-left" id="div-sign-up-2">
                        <input name="input-sign-up-user-name" id="input-sign-up-user-name" class="input-sign-up" type="text" placeholder="Tên đăng nhập">
                        <label id="sign-up-user-name-existed"></label><br>
                        <input name="input-sign-up-password" id="input-sign-up-password" class="input-sign-up" type="password" placeholder="Mật khẩu"><br>
                        <input name="input-sign-up-re-password" id="input-sign-up-re-password" class="input-sign-up" type="password" placeholder="Nhập lại mật khẩu"><br>
                    </div>
                </form>

                <button id="btn-submit-sign-up">Đăng ký</button>
            </div>
        </div>
    <%@include file="resource/layout/footer.jsp"%>

    <script type="text/javascript" src="js/sign-up.js"></script>
</body>
</html>
