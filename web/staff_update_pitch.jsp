<%@ page import="api.model.entity.Pitch" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="common.constant.FPConstant" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/7/2019
  Time: 11:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Pitch</title>
    <link rel="stylesheet" type="text/css" href="css/staff-update-pitch.css">
    <link rel="stylesheet" type="text/css" href="css/base.css">
</head>
<body>
<%
    if (session == null || session.getAttribute("staff-account-user-name") == null) {
        response.sendRedirect("staff_login.jsp");
    }
%>
<%@include file="resource/layout/staff-header.jsp"%>
<div id="staff-update-pitch-content">
    <div class="div-log-out">
        <a href="staff_logout" class="href-log-out"
           onmouseover="changeColorWhite(this)" onmouseout="changeColorDefault(this)">Đăng xuất</a>
    </div>
    <div id="list-pitch-title">Danh Sách Sân</div>
    <div id="list-pitch">
        <form id="staff-update-pitch-form" action="staff_update_pitch" method="get">
            <label class="staff-update-pitch-label-form-update" >Tên Sân &nbsp;</label>
            <input type="text" id="staff-update-pitch-input-name" name="staff-update-pitch-input-name" placeholder="Tên sân"
                   onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
            <label class="staff-update-pitch-label-form-update">Mô tả &nbsp;</label>
            <input type="text" id="staff-update-pitch-input-description" name="staff-add-pitch-input-description" placeholder="Mô tả"
                   onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
            <label class="staff-update-pitch-label-form-update" for="staff-update-pitch-input-num-player">Số Người</label>
            <select id="staff-update-pitch-input-num-player" name="staff-update-pitch-input-num-player"
                    onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
                <option>5</option>
                <option>7</option>
                <option>11</option>
            </select>
        </form>
        <button id="staff-update-pitch-btn-search" onmouseover="changeBgColorWhite(this)" onclick="btnUpdatePitchSearchClicked()"
                onmouseout="changeBgColorDefault(this)">Tìm kiếm</button>
    </div>
    <%
        if(request.getAttribute("list-pitch")!=null){
    %>
    <div id="pitch-result">
        <table>
            <tr>
                <th class="title">
                    Id
                </th>
                <th class="title">
                    Tên
                </th>
                <th class="title">
                    Mô tả
                </th>
                <th class="title">
                    Số người
                </th>
                <th class="title">
                    Sửa
                </th>
            </tr>
            <%
                ArrayList<Pitch> pitches = (ArrayList<Pitch>) request.getAttribute("list-pitch");
                for(int i = 0 ; i < pitches.size() ; i ++){
            %>
            <tr>
                <th><%=pitches.get(i).getId()%></th>
                <th><%=pitches.get(i).getName()%></th>
                <th><%=pitches.get(i).getDescription()%></th>
                <th><%=pitches.get(i).getType().getNumOfPlayer()%></th>
                <th>
                    <form action="pitch_detail" method="get">
                        <input type="hidden" name="pitchId" value="<%=pitches.get(i).getId()%>">
                        <input type="submit" class="update-button" value="Sửa">
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
<script type="text/javascript" src="js/staff-update-pitch.js">
    function btnUpdatePitchSearchClicked() {
        var name = document.getElementById("staff-update-pitch-input-name").value
        var description = document.getElementById("staff-update-pitch-input-description").value
        if (name.includes("!") || name.includes("/") || name.includes("|") || name.includes("#") ||
            name.includes("$") || name.includes("%") || name.includes("^") || name.includes(">") ||
            name.includes("<") || name.includes("*") || name.includes("(") || name.includes(")") ||
            name.includes(",") || name.includes("?") || name.includes(";") || name.includes(":") ||
            name.includes("&") || name.includes("-") || name.includes("+") || name.includes("=")||
            name.includes(" ")){
            alert("Tên sân không được chứa ký tự đặc biệt");
            return;
        }
        if (description.includes("!") || description.includes("/") || description.includes("|") || description.includes("#") ||
            description.includes("$") || description.includes("%") || description.includes("^") || description.includes(">") ||
            description.includes("<") || description.includes("*") || description.includes("(") || description.includes(")") ||
            description.includes(",") || description.includes("?") || description.includes(";") || description.includes(":") ||
            description.includes("&") || description.includes("-") || description.includes("+") || description.includes("=")){
            alert("Mô tả không được chứa ký tự đặc biệt");
            return;
        }
        document.getElementById("staff-update-pitch-form").submit();
    }
</script>
<script type="text/javascript" src="js/base.js"></script>
</body>
</html>
