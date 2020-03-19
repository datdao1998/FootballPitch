    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <html>
        <head>
        <link rel="stylesheet" type="text/css" href="css/staff-layout.css">
        </head>
        <body class="magin-0">
        <div id="header">
            <div id="header-logo">
                 <a href="staff_home.jsp" id="logo-link-home">
                    <img id="img-logo-link-home" src="imgs/logo.png" alt="HOME">
                </a>
            </div>
            <div id="staff-header-home">
                <a href="staff_home.jsp" class="header-text" onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)" >
                HOME
                </a>
             </div>

            <div id="staff-header-check-in">
                <a href="staff_check_in" class="header-text" onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">
                CHECK IN
                </a>
            </div>

            <div id="staff-header-check-out">
                <a href="staff_check_out" class="header-text" onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">
                CHECK OUT
                </a>
            </div>

            <div id="staff-header-service-manage">
                <a href="staff_service_manage.jsp" class="header-text" onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">
                SERVICE
                </a>
            </div>

            <div id="staff-header-pitch-manage">
                <a href="staff_pitch_manage.jsp" class="header-text" onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">
                PITCH
                </a>
            </div>

            <div id="header-account">
                <a href="staff_login.jsp">
                <img src="imgs/icon-account.png" style="max-height: 3vmax;">
                </a>
            </div>

        </div>
        <script type="text/javascript" src="js/base.js"></script>
        </body>
        </html>
