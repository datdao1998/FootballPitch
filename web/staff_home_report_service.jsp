<%@ page import="api.model.entity.Pitch" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Xem thông kê chi tiết dịch vụ đã được bán </title>
    <link rel="stylesheet" type="text/css" href="css/staff-home-report-service.css">
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 15px;
        }
    </style>
</head>
<body class="margin-0">

<%@include file="resource/layout/staff-header.jsp"%>
<div id="booked-service-content">

    <div id="booked-service-div-1" style=" background-size: 100vw 80vh;">
        <form id="booked-service-form-search" action="staff_home_report_search" method="get">
            <label class="booked-service-form-search" for="booked-service-input-date">Bắt Đầu</label>
            <input type="text" id="booked-service-input-date" name="booked-service-input-date" placeholder="dd/mm/yyyy"
                   onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
            <label class="booked-service-form-search" for="booked-service-end-input-date">Kết Thúc </label>
            <input type="text" id="booked-service-end-input-date" name="booked-service-end-input-date" placeholder="dd/mm/yyyy"
                   onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
            <input type="hidden" name="row" value="-1">
            <br>
            <br>
        </form>
        <div  style="width: 100vw; margin-right: 2vw; margin-top: 2vh; float: right">
            <button id="booked-service-btn-search" >Tìm kiếm</button>
        </div>
    </div>
</div>
<%--<div style="width: 65vw; margin-right: 3vw; background-size: 80vw 75vh; margin-top: 3vh; float: right">--%>
<%--    <table class = "table" id = "t01" >--%>
<%--        <tr>--%>
<%--            <th>STT</th>--%>
<%--            <th>name</th>--%>
<%--            <th>type</th>--%>
<%--            <th>unit</th>--%>
<%--            <th>price</th>--%>
<%--            <th>into money</th>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>1</td>--%>
<%--            <td>Jill</td>--%>
<%--            <td>Smith</td>--%>
<%--            <td>50</td>--%>
<%--            <td>50</td>--%>
<%--            <td>50</td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>2</td>--%>
<%--            <td>Eve</td>--%>
<%--            <td>Jackson</td>--%>
<%--            <td>94</td>--%>
<%--            <td>50</td>--%>
<%--            <td>50</td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>3</td>--%>
<%--            <td>John</td>--%>
<%--            <td>Doe</td>--%>
<%--            <td>80</td>--%>
<%--            <td>50</td>--%>
<%--            <td>50</td>--%>
<%--        </tr>--%>
<%--    </table>--%>
<%--</div>--%>

<%@include file="resource/layout/staff-footer.jsp"%>
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript" src="js/staff_home_report_service.js"></script>


</body>
</html>