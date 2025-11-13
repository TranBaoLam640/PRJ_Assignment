<%-- 
    Document   : EventList
    Created on : Mar 15, 2025, 10:17:20 PM
    Author     : chang 
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.*" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Danh sách sự kiện</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <style>
            .no-results {
                color: red;
                font-weight: bold;
                text-align: center;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="topnav.jsp"/>
        <div class="container mt-4">
            <h1>Danh sách sự kiện</h1>

            <form action="Event" method="get" class="mb-3">
                <input type="hidden" name="action" value="search">
                <div class="input-group">
                    <input type="text" name="keyword" class="form-control" 
                           placeholder="Tìm theo tên sự kiện, địa điểm, hoặc CLB..." 
                           value="${keyword}">
                    <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                    <a href="Event" class="btn btn-secondary ms-2">Hủy</a>
                </div>
            </form>

            <c:if test="${not empty message}">
                <p class="no-results">${message}</p>
            </c:if>

            <c:if test="${sessionScope.user.role == 'Chairman' || sessionScope.user.role == 'Admin'|| sessionScope.user.role == 'ViceChairman'}">
                <a href="Event?action=add" class="btn btn-success mb-3">Thêm sự kiện</a>
            </c:if>

            <table class="table table-bordered">
                <thead class="table-warning">
                    <tr>
                        <th>ID</th>
                        <th>Tên sự kiện</th>
                        <th>Mô tả</th>
                        <th>Ngày diễn ra</th>
                        <th>Địa điểm</th>
                        <th>ID CLB</th>
                        <th>Đã đăng ký</th>
                        <th>Đã tham dự</th>
                        <th>Vắng mặt</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="event" items="${events}">
                        <tr>
                            <td>${event.eventID}</td>
                            <td>${event.eventName}</td>
                            <td>${event.description}</td>
                            <td>${event.eventDate}</td>
                            <td>${event.location}</td>
                            <td>${event.clubId}</td>
                            <td>${event.registeredCount}</td>
                            <td>${event.attendedCount}</td>
                            <td>${event.absentCount}</td>
                            <td>${event.status}</td>
                            <td>
                                <c:if test="${sessionScope.user.role == 'Chairman'|| sessionScope.user.role == 'Admin'}">
                                    <a href="Event?action=edit&id=${event.eventID}" class="btn btn-sm btn-primary">Sửa</a>
                                </c:if>
                                <c:if test="${sessionScope.user.role == 'Chairman'|| sessionScope.user.role == 'Admin'|| sessionScope.user.role == 'ViceChairman'}">
                                    <a href="Event?action=delete&id=${event.eventID}" class="btn btn-sm btn-danger" onclick="return confirm('Xác nhận xóa?')">Xóa</a>
                                </c:if>
                                <a href="Event?action=detail&id=${event.eventID}" class="btn btn-sm btn-info me-1">Chi tiết</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="${pageContext.request.contextPath}/Users" class="btn btn-secondary">Quay lại</a>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <jsp:include page="footer.jsp"/>
    </body>
</html>