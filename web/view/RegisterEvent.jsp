<%-- 
    Document   : RegisterEvent
    Created on : Mar 20, 2025, 1:45:25 AM
    Author     : chang 
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    
<head>
    <title>Đăng ký tham gia sự kiện</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <jsp:include page="topnav.jsp"/>
    <!-- Main content -->
    <div class="container mt-4">
        <h1>Đăng ký tham gia sự kiện</h1>
        
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Tên sự kiện</th>
                    <th>Mô tả</th>
                    <th>Ngày diễn ra</th>
                    <th>Địa điểm</th>
                    <th>Thao tác</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="event" items="${events}">
                    <tr>
                        <td>${event.eventName}</td>
                        <td>${event.description}</td>
                        <td><fmt:formatDate value="${event.eventDate}" pattern="dd/MM/yyyy"/></td>
                        <td>${event.location}</td>
                        <td>
                            <c:if test="${not empty registeredEvents[event.eventID]}">
                                <span style="color: green;">Đã đăng ký</span>
                            </c:if>
                            <c:if test="${empty registeredEvents[event.eventID]}">
                                <form action="Event" method="post" style="display: inline;">
                                    <input type="hidden" name="action" value="register">
                                    <input type="hidden" name="eventId" value="${event.eventID}">
                                    <button type="submit" class="btn btn-primary btn-sm">Đăng ký</button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/Users" class="btn btn-secondary">Quay lại</a>
    </div>

    
    <!-- Footer -->
<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script> <!<!-- Bắt buộc phải có  -->
</body>
</html>