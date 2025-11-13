<%-- 
    Document   : ActivityHistory
    Created on : Mar 14, 2025, 10:17:41 PM
    Author     : chang 
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.*" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Lịch sử tham gia hoạt động</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    </head>
    <body>
        <jsp:include page="topnav.jsp"/>
        <div class="container mt-4">
            <h1>Lịch sử tham gia hoạt động</h1>
            <c:if test="${  not empty activities}">
                <h3>Thành viên: ${activities[0].user.fullName} (Email: ${activities[0].user.email})</h3>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID Hoạt động</th>
                            <th>Tên sự kiện</th>
                            <th>Ngày diễn ra</th>
                            <th>Mức độ tham gia</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="activity" items="${activities}">
                            <tr>
                                <td>${activity.activityID}</td>
                                <td>${activity.event.eventName}</td>
                                <td>${activity.event.eventDate}</td>
                                <td>${activity.participationLevel}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${ empty activities}">
                <p>Thành viên này chưa tham gia hoạt động nào.</p>
            </c:if>
            <a href="${pageContext.request.contextPath}/MemberServlet" class="btn btn-secondary">Quay lại</a>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <jsp:include page="footer.jsp"/>
    </body>
</html>