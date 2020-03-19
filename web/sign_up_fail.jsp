<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lỗi</title>
    <link rel="stylesheet" type="text/css" href="css/sign-up.css">
</head>
<body id="body-sign-up-fail">

    <%@include file="resource/layout/header.jsp"%>
        <div id="sign-up-fail-content">
            <div id="div-sign-up-fail">

                <div id="sign-up-fail-div1">
                    Đăng ký không thành công
                </div>

                <div id="sign-up-fail-div2">
                    <a href="sign_up.jsp" id="sign-up-fail-href-cancel">Quay lại</a>
                </div>
            </div>
        </div>
        <%@include file="resource/layout/footer.jsp"%>
        <script>
        document.getElementById("sign-up-fail-href-cancel").onmouseover = changeColorWhite;
        document.getElementById("sign-up-fail-href-cancel").onmouseout = changeColorDefault;
        function changeColorWhite() {
            document.getElementById("sign-up-fail-href-cancel").setAttribute("style", "color: white");
        }
        function changeColorDefault() {
            document.getElementById("sign-up-fail-href-cancel").setAttribute("style", "color: #FDAB0A");
        }
    </script>
</body>
</html>