<%@ page import="api.model.entity.Pitch" %>
<%@ page import="api.model.entity.Service" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/7/2019
  Time: 11:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Service</title>
    <link rel="stylesheet" type="text/css" href="css/staff-update-service.css">
    <link rel="stylesheet" type="text/css" href="css/base.css">
</head>
<body>
<%
    if (session == null || session.getAttribute("staff-account-user-name") == null) {
        response.sendRedirect("staff_login.jsp");
    }
%>
<%@include file="resource/layout/staff-header.jsp"%>
<div id="staff-update-service-content">

    <div class="div-log-out">
        <a href="staff_logout" class="href-log-out"
           onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Đăng xuất</a>
    </div>

    <div id="list-service-title">Danh Sách Dịch Vụ</div>
    <div id="list-service">
        <form id="staff-update-service-form" action="staff_update_service" method="get">
            <label class="staff-update-service-label-form-update" >Tên &nbsp;</label>
            <input type="text" id="staff-update-service-input-name" name="staff-update-service-input-name" placeholder="Tên Dịch Vụ"
                   onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
            <label class="staff-update-service-label-form-update">Kiểu &nbsp;</label>
            <input type="text" id="staff-update-service-input-type" name="staff-add-service-input-type" placeholder="Kiểu"
                   onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
        </form>
        <button id="staff-update-service-btn-search" onmouseover="changeBgColorWhite(this)" onclick="btnUpdateServiceSearchClicked()"
                onmouseout="changeBgColorDefault(this)">Tìm kiếm</button>
    </div>
    <%
        if(request.getAttribute("list-service")!=null){
    %>
    <div id="service-result">
        <table>
            <tr>
                <th class="title">
                    Id
                </th>
                <th class="title">
                    Tên
                </th>
                <th class="title">
                    Kiểu
                </th>
                <th class="title">
                    Đơn vị
                </th>
                <th class="title">
                    Giá
                </th>
                <th class="title">
                    Khả dụng
                </th>
                <th class="title">
                    Cập nhật
                </th>
            </tr>
            <%
                ArrayList<Service> services = (ArrayList<Service>) request.getAttribute("list-service");
                for(int i =0 ; i < services.size() ;i ++){
            %>
            <tr>
                <th><%=services.get(i).getId()%></th>
                <th><%=services.get(i).getName()%></th>
                <th><%=services.get(i).getType()%></th>
                <th><%=services.get(i).getUnit()%></th>
                <th><%=services.get(i).getPrice()%></th>
                <th><%=services.get(i).getAvailable()%></th>
                <th>
                    <form action="service_detail" method="get">
                        <input type="hidden" name="serviceId" value="<%=services.get(i).getId()%>">
                        <input type="submit" class="update-button" value="Sửa" onmouseout="changeBgColorDefault(this)"
                        onmouseover="changeBgColorWhite(this)">
                    </form>
                </th>
            </tr>
            <%
                }
            %>
        </table>
    </div>
    <%
        }
    %>
</div>
<%@include file="resource/layout/staff-footer.jsp"%>
<script type="text/javascript" src="js/staff-update-service.js"></script>
<script type="text/javascript" src="js/base.js"></script>
</body>
</html>
