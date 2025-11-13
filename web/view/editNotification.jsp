<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Chỉnh sửa thông báo</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <style>
        body {
             background: url('${pageContext.request.contextPath}/images/pexels-quang-nguyen-vinh-222549-2162471.jpg') no-repeat center center/cover;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        .form-container {
            background: white;
            padding: 30px;
            border-radius: 10px;
            width: 500px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }
        .form-container h1 {
            text-align: center;
            font-family: 'Poppins', sans-serif;
            color: #007bff;
            font-weight: bold;
        }
        .form-container h1 i {
            margin-right: 10px;
        }
        .form-label i {
            margin-right: 5px;
        }
        .btn-submit {
            background-color: #28a745;
            color: white;
            width: 100%;
        }
        .btn-submit:hover {
            background-color: #2e8b57;
        }
        .btn-cancel {
            background-color: #6c757d;
            color: white;
            width: 100%;
        }
        .btn-cancel:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h1><i class="fas fa-edit"></i>Chỉnh sửa thông báo</h1>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
        <form action="Notifications" method="post">
            <input type="hidden" name="action" value="edit">
            <input type="hidden" name="notificationID" value="${notification.notificationID}">
            <div class="mb-3">
                <label class="form-label"><i class="fas fa-heading"></i>Tiêu đề:</label>
                <input type="text" name="title" class="form-control" value="${notification.title}" required>
            </div>
            <div class="mb-3">
                <label class="form-label"><i class="fas fa-align-left"></i>Nội dung:</label>
                <textarea name="message" class="form-control" rows="3" required>${notification.message}</textarea>
            </div>
            <div class="mb-3">
                <label class="form-label"><i class="fas fa-users"></i>CLB:</label>
                <input type="number" name="clubId" class="form-control" value="${notification.clubId}" required>
            </div>
            <button type="submit" class="btn btn-submit"><i class="fas fa-save"></i> Lưu thay đổi</button>
        </form>
        <div class="text-center mt-3">
            <a href="Notifications" class="btn btn-cancel"><i class="fas fa-arrow-left"></i> Quay lại</a>
        </div>
    </div>

</body>
</html>
