<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng ký thành công</title>
    <link rel="stylesheet" type="text/css" href="css/sign-up.css">
</head>
<body id="body-sign-up-success">
        <%
            if (request.getAttribute("userNameSignUpSuccess") == null) {
                response.sendRedirect("index.jsp");
            }
        %>
        <%@include file="resource/layout/header.jsp"%>
        <div id="sign-up-success-content">
            <div id="sign-up-success-div1">
                Đăng ký thành công với tên đăng nhập: <br>
                <span style="color: #FDAB0A"><%= request.getAttribute("userNameSignUpSuccess")%></span>
            </div>
            <div id="sign-up-success-div2">
                <a href="customer_login.jsp">
                    <button id="btn-sign-up-success-login">
                        Đăng nhập
                    </button>
                </a>
            </div>
        </div>
        <%@include file="resource/layout/footer.jsp"%>

        <script>
        document.getElementById("btn-sign-up-success-login").onmouseover = changeColorWhite;
        document.getElementById("btn-sign-up-success-login").onmouseout = changeColorDefault;

        function changeColorWhite() {
            document.getElementById("btn-sign-up-success-login").setAttribute("style", "background-color: white");
        }
        function changeColorDefault() {
            document.getElementById("btn-sign-up-success-login").setAttribute("style", "background-color: rgb(253, 189, 0)");
        }
    </script>
</body>
</html>
