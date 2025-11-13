<%-- 
    Document   : AddMember
    Created on : Mar 14, 2025, 2:13:19 AM
    Author     : chang 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Thêm thành viên</title>
    </head>
    <body>
        <h1>Thêm thành viên</h1>
        <form action="MemberServlet" method="post">
            <input type="hidden" name="action" value="add">
            <label>Họ tên:</label><input type="text" name="fullName" required><br>
            <label>Email:</label><input type="email" name="email" required><br>
            <label>Vai trò:</label>
            <select name="role">
                <option value="Member">Thành viên</option>
                <option value="TeamLeader">Trưởng nhóm</option>
                <option value="ViceChairman">Phó chủ nhiệm</option>
                <option value="Chairman">Chủ nhiệm</option>
            </select><br>
            <label>ID CLB:</label><input type="number" name="clubId" required><br>
            <button type="submit">Thêm</button>
        </form>
        <a href="${pageContext.request.contextPath}/MemberServlet">Quay lại</a>
    </body>
</html>