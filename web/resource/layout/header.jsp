<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/layout.css">
</head>
<body class="magin-0">
    <div id="header">

    <div id="header-logo">
    <a href="index.jsp" id="logo-link-home">
    <img id="img-logo-link-home" src="imgs/logo.png" alt="HOME">
    </a>
    </div>

    <div id="header-home">
    <a href="index.jsp" class="header-text" onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)" >
    HOME
    </a>
    </div>

    <div id="header-booking">
    <a href="customer_booking.jsp" class="header-text" onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">
    BOOKING
    </a>
    </div>

    <div id="header-booked">
    <a href="customer_search_booked_pitch" class="header-text" onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">
    BOOKED
    </a>
    </div>

    <div id="header-sign-up">
    <a href="sign_up.jsp" class="header-text" onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">
    SIGN  UP
    </a>
    </div>

    <div id="header-about-us">
    <a href="about_us.jsp" class="header-text" onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">
    ABOUT  US
    </a>
    </div>

    <div id="header-account">
    <a href="customer_login.jsp">
    <img src="imgs/icon-account.png" style="max-height: 3vmax;">
    </a>
    </div>

    </div>
    <script type="text/javascript" src="js/base.js"></script>
</body>
</html>
