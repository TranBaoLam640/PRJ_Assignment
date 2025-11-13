<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    HttpSession sess = request.getSession();
    String username = (String) sess.getAttribute("username");
%>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Phân quyền chi tiết</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/styles.css" >
    <style>
        .table th, .table td {
            text-align: center;
        }
    </style>
</head>
<body>

    <jsp:include page="topnav.jsp" />

    <div class="container mt-4">
        <h1 class="text-center">Bảng phân quyền chi tiết</h1>
        <table class="table table-bordered mt-4">
            <thead>
                <tr>
                    <th>Chức năng</th>
                    <th>Chủ nhiệm</th>
                    <th>Phó chủ nhiệm</th>
                    <th>Trưởng nhóm</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Quản lý thành viên</td>
                    <td><input type="checkbox" checked disabled></td>
                    <td><input type="checkbox" checked disabled></td>
                    <td><input type="checkbox" disabled></td>
                </tr>
                <tr>
                    <td>Quản lý sự kiện</td>
                    <td><input type="checkbox" checked disabled></td>
                    <td><input type="checkbox" disabled></td>
                    <td><input type="checkbox" disabled></td>
                </tr>
                <tr>
                    <td>Xem báo cáo thống kê</td>
                    <td><input type="checkbox" checked disabled></td>
                    <td><input type="checkbox" checked disabled></td>
                    <td><input type="checkbox" disabled></td>
                </tr>
                <tr>
                    <td>Thông báo</td>
                    <td><input type="checkbox" checked disabled></td>
                    <td><input type="checkbox" checked disabled></td>
                    <td><input type="checkbox" disabled></td>
                </tr>
            </tbody>
        </table>
        <div class="text-center mt-4">
            <a href="javascript:history.back()" class="btn btn-secondary">Quay lại</a>
            <button class="btn btn-primary">Lưu</button>
        </div>
    </div>

    <jsp:include  page="footer.jsp"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script> <!<!-- Bắt buộc phải có  -->
</body>
</html>