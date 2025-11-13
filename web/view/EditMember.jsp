<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.*" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Chỉnh sửa thành viên</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        <style>
            body {
                background: url('${pageContext.request.contextPath}/images/pexels-quang-nguyen-vinh-222549-2162471.jpg') no-repeat center center/cover;
                height: 100vh;
                display: flex;
                justify-content: center; /* Đưa form sang phải */
                align-items: center;
                padding-right: 50px; /* Khoảng cách từ lề phải */
            }
            .form-container {
                background: white;
                padding: 20px;
                border-radius: 10px;
                width: 600px;
                color: white;
            }
            .form-container h1 {
                text-align: center;
                font-family: 'Poppins', sans-serif; /* Font chữ đẹp hơn */
                white-space: nowrap; /* Tránh xuống dòng */

            }
            .btn-update {
                background-color: #007bff; /* Màu xanh mặc định */
                color: white;
            }
            .btn-update:hover {
                background-color: #0056b3; /* Màu xanh đậm khi hover */
            }
        </style>
    </head>
    <body>

        <div class="form-container">
            <h1 style="color: #dc3545;"><i class="fas fa-user-edit"></i> Cập nhật thông tin thành viên</h1>

            <form action="MemberServlet" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="userId" value="${member.getUserID()}">
                <div class="mb-3">
                    <label class="form-label">Họ tên:</label>
                    <input type="text" name="fullName" class="form-control" value="${member.fullName}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Email:</label>
                    <input type="email" name="email" class="form-control" value="${member.email}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Vai trò:</label>
                    <select name="role" class="form-select">
                        <option value="Member" ${member.role == 'Member' ? 'selected' : ''}>Thành viên</option>
                        <option value="TeamLeader" ${member.role == 'TeamLeader' ? 'selected' : ''}>Trưởng nhóm</option>
                        <option value="ViceChairman" ${member.role == 'ViceChairman' ? 'selected' : ''}>Phó chủ nhiệm</option>
                        <option value="Chairman" ${member.role == 'Chairman' ? 'selected' : ''}>Chủ nhiệm</option>
                        <option value="Admin" ${member.role == 'Admin' ? 'selected' : ''}>Quản trị viên</option>

                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label">ID CLB:</label>
                    <input type="number" name="clubId" class="form-control" value="${member.clubId}" readonly="" required>
                </div>
                <button type="submit" class="btn btn-update w-100">Cập nhật</button>
            </form>
            <div class="text-center mt-3">
                <a href="${pageContext.request.contextPath}/MemberServlet" class="text-black">Quay lại</a>
            </div>
        </div>
    </body>
</html>