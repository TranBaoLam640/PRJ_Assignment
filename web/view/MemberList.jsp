<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.*" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách thành viên</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <jsp:include page="topnav.jsp"/>
    <div class="container mt-4">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h1 class="mb-0">Danh sách thành viên</h1>
            <form action="MemberServlet" method="get" class="d-flex" style="width: 250px;">
                <input type="hidden" name="action" value="search">
                <div class="input-group">
                    <button type="submit" class="input-group-text"><i class="fas fa-search"></i></button>
                    <input type="text" name="keyword" class="form-control" placeholder="Tìm kiếm..." style="height: 32px;">
                </div>
            </form>

        </div>
        <table class="table table-bordered">
            <thead class="table-warning">
                <tr>
                    <th>ID</th>
                    <th>Họ và Tên</th>
                    <th>Email</th>
                    <th>Vai trò</th>
                    <th>CLB</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="member" items="${members}">
                    <tr>
                        <td>${member.getUserID()}</td>
                        <td>${member.getFullName()}</td>
                        <td>${member.getEmail()}</td>
                        <td>${member.getRole()}</td>
                        <td>${member.getClubId()}</td>
                        <td>
                            <c:if test="${sessionScope.user.role == 'Chairman' || sessionScope.user.role == 'ViceChairman'|| sessionScope.user.role == 'Admin'}">
                                <a href="MemberServlet?action=edit&id=${member.userID}" class="btn btn-warning btn-sm">
                                    <i class="fas fa-edit"></i> Sửa
                                </a>
                            </c:if>
                            <c:if test="${sessionScope.user.role == 'Chairman'|| sessionScope.user.role == 'Admin'}">
                                <a href="MemberServlet?action=delete&id=${member.getUserID()}" class="btn btn-danger btn-sm" onclick="return confirm('Xác nhận xóa?')">
                                    <i class="fas fa-trash"></i> Xóa
                                </a>
                            </c:if>
                            <a href="MemberServlet?action=history&id=${member.userID}" class="btn btn-info btn-sm">
                                <i class="fas fa-history"></i> Lịch sử
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <c:if test="${sessionScope.user.role == 'Chairman'|| sessionScope.user.role == 'Admin'}">
            <a href="MemberServlet?action=add" class="btn btn-success mb-3">
                <i class="fas fa-user-plus"></i> Thêm thành viên
            </a>
        </c:if>
    </div>
    <jsp:include page="footer.jsp"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
