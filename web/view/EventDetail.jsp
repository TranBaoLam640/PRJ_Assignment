<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết sự kiện</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        h2 {
            text-align: center;
            color: #333;
            margin-top: 20px;
        }
        .event-details, .participant-list {
            background: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            margin: 20px auto;
            max-width: 800px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .back-button {
            display: block;
            text-align: center;
            margin-top: 20px;
            text-decoration: none;
            color: white;
            background-color: #007bff;
            padding: 10px 20px;
            border-radius: 5px;
        }
        .back-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <jsp:include page="topnav.jsp"/>

    <h2>Chi tiết sự kiện</h2>

    <c:if test="${not empty message}">
        <p class="text-center">${message}</p>
    </c:if>

    <c:if test="${not empty event}">
        <div class="event-details">
            <p><strong>ID:</strong> ${event.eventID}</p>
            <p><strong>Tên sự kiện:</strong> ${event.eventName}</p>
            <p><strong>Mô tả:</strong> ${event.description}</p>
            <p><strong>Ngày diễn ra:</strong> ${event.eventDate}</p>
            <p><strong>Địa điểm:</strong> ${event.location}</p>
            <p><strong>ID CLB:</strong> ${event.clubId}</p>
        </div>

        <div class="participant-list">
            <h3>Danh sách người tham gia</h3>
            <c:if test="${empty participants}">
                <p>Chưa có người tham gia sự kiện này.</p>
            </c:if>
            <c:if test="${not empty participants}">
                <table>
                    
                    <tr>
                        <th>ID</th>
                        <th>Tên người dùng</th>
                        <th>Vai trò</th>
                        <th>Trạng thái</th>
                    </tr>
                    
                   
                    <c:forEach var="participant" items="${participants}">
                        <tr>
                            <td>${participant.eventParticipantID}</td>
                            <td>${participant.user.fullName}</td>
                            <td>${participant.user.role}</td>
                            <td>${participant.status}</td>
                        </tr>
                    </c:forEach>
           
                </table>
            </c:if>
        </div>
    </c:if>

    <a href="Event" class="back-button">Quay lại</a>

    <!-- Footer -->
    <jsp:include page="footer.jsp"/>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script> 
</body>
</html>