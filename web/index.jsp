<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <title>Trang chủ</title>
  <link rel="stylesheet" type="text/css" href="css/customer-home.css">
  <link rel="stylesheet" type="text/css" href="css/base.css">
</head>
<body class="margin-0">
  <%@include file="resource/layout/header.jsp"%>
  <div id="home-content">
    <div id="home-customer-user-name">
      <%= session.getAttribute("customer-account-user-name") != null ? session.getAttribute("customer-account-user-name") : ""%>
    </div>
    <div id="home-div-1">
      LIFE IS ABOUT TIMING
    </div>
    <div id="home-div-2">
      Just play. Have a fun. Enjoy the game.
    </div>

    <div id="home-div-3">
      <a href="customer_booking.jsp">
        <button id="home-btn-book-field-now" onmouseout="changeBgColorDefault(this)" onmouseover="changeBgColorWhite(this)">
          BOOKING NOW
        </button>
      </a>
    </div>
  </div>
  <%@include file="resource/layout/footer.jsp"%>
  <script type="text/javascript" src="js/base.js"></script>
</body>
</html>
