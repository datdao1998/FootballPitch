<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trang chủ nhân viên</title>
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/staff-home-report.css">
</head>
<body class="margin-0">
<%@include file="resource/layout/staff-header.jsp"%>
<div id="staff-report-home-content">

    <div id="staff-home-user-name">
        <%= session.getAttribute("staff-account-user-name") != null ? session.getAttribute("staff-account-user-name") : ""%>
    </div>

    <div id="staff-report-home-div-check-in">
        <a href="staff_home_report_pitch.jsp" class="staff-report-home-href">
            <div  class="staff-report-home-div" onmouseout="changeBgColorDefault(this)" onmouseover="changeBgColorWhite(this)">
                PITCH
            </div>
        </a>
    </div>

    <div id="staff-report-home-div-check-out">
        <a href="staff_home_report_service.jsp" class="staff-report-home-href">
            <div class="staff-report-home-div" id="staff-report-home-href-check-out"
                 onmouseout="changeBgColorDefault(this)" onmouseover="changeBgColorWhite(this)">
                SERVICE
            </div>
        </a>
    </div>

</div>
<%@include file="resource/layout/staff-footer.jsp"%>
<script type="text/javascript" src="js/base.js"></script>
</body>
</html>
