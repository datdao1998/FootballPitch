<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trang chủ nhân viên</title>
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/staff-home.css">
</head>
<body class="margin-0">
    <%@include file="resource/layout/staff-header.jsp"%>
    <div id="staff-home-content">

        <div id="staff-home-user-name">
            <%= session.getAttribute("staff-account-user-name") != null ? session.getAttribute("staff-account-user-name") : ""%>
        </div>

        <div id="staff-home-div-check-in">
            <a href="staff_check_in" class="staff-home-href">
                <div  class="staff-home-div" onmouseout="changeBgColorDefault(this)" onmouseover="changeBgColorWhite(this)">
                    CHECK IN
                </div>
            </a>
        </div>

        <div id="staff-home-div-check-out">
            <a href="staff_check_out" class="staff-home-href">
                <div class="staff-home-div" id="staff-home-href-check-out"
                     onmouseout="changeBgColorDefault(this)" onmouseover="changeBgColorWhite(this)">
                    CHECK OUT
                </div>
            </a>
        </div>

        <div id="staff-home-div-service">
            <a href="staff_service_manage.jsp" class="staff-home-href">
                <div class="staff-home-div" onmouseout="changeBgColorDefault(this)" onmouseover="changeBgColorWhite(this)">
                    SERVICE
                </div>
            </a>
        </div>

        <div id="staff-home-div-pitch">
            <a href="staff_pitch_manage.jsp" class="staff-home-href">
                <div class="staff-home-div" onmouseout="changeBgColorDefault(this)" onmouseover="changeBgColorWhite(this)">
                    PITCH
                </div>
            </a>
        </div>
        <div id="staff-home-div-report">
            <a href="staff_home_report.jsp" class="staff-home-href">
                <div class="staff-home-div" onmouseout="changeBgColorDefault(this)" onmouseover="changeBgColorWhite(this)">
                    REPORT
                </div>
            </a>
        </div>

    </div>
    <%@include file="resource/layout/staff-footer.jsp"%>
    <script type="text/javascript" src="js/base.js"></script>
</body>
</html>
