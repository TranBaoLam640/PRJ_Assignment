<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Chỉnh sửa sự kiện</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        <style>
            body {
                background: url('${pageContext.request.contextPath}/images/pexels-quang-nguyen-vinh-222549-2162471.jpg') no-repeat center center/cover;
                height: 100vh;
                display: flex;
                justify-content: center;
                align-items: center;
                padding-right: 50px;
            }
            .form-container {
                background: white;
                padding: 20px;
                border-radius: 10px;
                width: 550px;
                color: white;
            }
            .form-container h1 {
                text-align: center;
                font-family: 'Poppins', sans-serif;
                white-space: nowrap;
            }
            .btn-update {
                background-color: #007bff;
                color: white;
            }
            .btn-update:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="form-container">
            <h1 style="color: #dc3545;"><i class="fas fa-user-edit"></i>Cập nhật thông tin sự kiện</h1>
            <c:if test="${not empty error}">
                <p style="color: red;">${error}</p>
            </c:if>
            <form action="Event" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="eventID" value="${event.eventID}">
                <div class="mb-3">
                    <label class="form-label">Tên sự kiện:</label>
                    <input type="text" name="EventName" class="form-control" value="${event.eventName}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Mô tả:</label>
                    <textarea name="Description" class="form-control" rows="3">${event.description}</textarea>
                </div>
                <div class="mb-3">
                    <label class="form-label">Ngày diễn ra:</label>
                    <fmt:formatDate value="${event.eventDate}" pattern="yyyy-MM-dd'T'HH:mm" var="formattedDate"/>
                    <input type="datetime-local" name="EventDate" class="form-control" value="${formattedDate}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Địa điểm:</label>
                    <input type="text" name="Location" class="form-control" value="${event.location}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">ID CLB:</label>
                    <input type="number" name="clubId" class="form-control" value="${event.clubId}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Trạng thái:</label>
                    <select name="Status" class="form-control" required>
                        <option value="Upcoming" ${event.status == 'Upcoming' ? 'selected' : ''}>Sắp diễn ra</option>
                        <option value="Ongoing" ${event.status == 'Ongoing' ? 'selected' : ''}>Đang diễn ra</option>
                        <option value="Completed" ${event.status == 'Completed' ? 'selected' : ''}>Đã hoàn thành</option>
                        <option value="Canceled" ${event.status == 'Canceled' ? 'selected' : ''}>Đã hủy</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-update w-100">Cập nhật</button>
            </form>
            <div class="text-center mt-3">
                <a href="Event" class="text-black">Hủy</a>
            </div>
        </div>

    </body>
</html>
