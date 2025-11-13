<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Users" %>

<%
    Users user = (Users) request.getAttribute("user");
    if (user == null) {
        response.sendRedirect("profile");
        return;
    }
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa hồ sơ</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <style>
        body {
            background: url('${pageContext.request.contextPath}/images/pexels-quang-nguyen-vinh-222549-2162471.jpg') no-repeat center center/cover;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .form-container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            width: 600px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        .form-container h1 {
            text-align: center;
            font-family: 'Poppins', sans-serif;
            color: #dc3545;
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
        <h1><i class="fas fa-user-edit"></i> Chỉnh sửa hồ sơ</h1>

        <form action="profile" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="userID" value="<%= user.getUserID() %>">

            <div class="mb-3">
                <label class="form-label">Họ và tên:</label>
                <input type="text" name="fullName" class="form-control" value="<%= user.getFullName() %>" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Mật khẩu mới:</label>
                <input type="password" name="password" class="form-control" placeholder="Nhập mật khẩu mới" required>
            </div>

            <div class="mb-3">
                <label class="form-label">ID CLB:</label>
                <input type="number" name="clubId" class="form-control" value="<%= user.getClubId() %>" required>
            </div>

            <button type="submit" class="btn btn-update w-100">Lưu thay đổi</button>
        </form>

        <div class="text-center mt-3">
            <a href="profile" class="text-black">Quay lại</a>
        </div>
    </div>

</body>
</html>
