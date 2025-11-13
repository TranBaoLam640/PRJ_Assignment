<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, model.Notification" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Quản lý thông báo</title>

        <!-- Bootstrap & FontAwesome -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"><link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

        <style>
            body {
                background-color: #f4f6f9;
                font-family: 'Arial', sans-serif;
            }
            .container {
                margin-top: 40px;
            }
            .table-container {
                background: white;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            }
            h2 {
                color: #007bff;
                text-align: center;
                margin-bottom: 20px;
            }
            table {
                border-radius: 8px;
                overflow: hidden;
            }
            th {
                background-color: #007bff;
                color: white;
                text-align: center;
            }
            td {
                text-align: center;
            }
            .btn {
                transition: 0.3s ease;
            }
            .btn-warning {
                background-color: #ffc107;
                color: white;
            }
            .btn-danger {
                background-color: #dc3545;
                color: white;
            }
            .btn-warning:hover, .btn-danger:hover {
                opacity: 0.85;
            }
            .add-btn {
                display: flex;
                justify-content: flex-end;
                margin-bottom: 15px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="topnav.jsp"/>
        <%
            String role = (String) session.getAttribute("role");
            boolean isAdmin = role != null && (role.equals("Admin") || role.equals("Chairman"));
            String error = (String) request.getAttribute("error"); // Lấy lỗi từ request
        %>

        <div class="container">
            <h2><i class="fas fa-bell"></i> Danh sách thông báo</h2>
            <!-- Hiển thị lỗi nếu có -->
            <% if (error != null) { %>
            <div class="alert-error text-center">
                <i class="fas fa-exclamation-circle"></i> <%= error %>
            </div>
            <% } %>

            <div class="table-container">
                <!-- Nếu là Admin, hiển thị nút Thêm thông báo -->
                <% if (isAdmin) { %>
                <div class="add-btn">
                    <a href="Notifications?action=add" class="btn btn-primary">
                        <i class="fas fa-plus-circle"></i> Thêm thông báo
                    </a>
                </div>
                <% } %>

                <!-- Bảng danh sách thông báo -->
                <div class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <td>Id</td>
                                <th>Tiêu đề</th>
                                <th>Nội dung</th>
                                <th>Ngày tạo</th>
                                <th>CLB</th>
                                    <% if (isAdmin) { %>
                                <th>Hành động</th>
                                    <% } %>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Notification> notifications = (List<Notification>) request.getAttribute("notifications");
                                if (notifications != null && !notifications.isEmpty()) {
                                    for (Notification notification : notifications) {
                            %>
                            <tr>
                                <td><%= notification.getNotificationID() %></td>
                                <td><%= notification.getTitle() %></td>
                                <td><%= notification.getMessage() %></td>
                                <td><%= notification.getCreatedDate() %></td>
                                <td><%= notification.getClubId() %></td>
                                <% if (isAdmin) { %>
                                <td>
                                    <a href="Notifications?action=edit&notificationID=<%= notification.getNotificationID() %>" 
                                       class="btn btn-warning btn-sm"><i class="fas fa-edit"></i> Sửa</a>
                                    <a href="Notifications?action=delete&notificationID=<%= notification.getNotificationID() %>" 
                                       class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc chắn muốn xóa?');">
                                        <i class="fas fa-trash-alt"></i> Xóa</a>
                                </td>
                                <% } %>
                            </tr>
                            <%
                                    }
                                } else {
                            %>
                            <tr>
                                <td colspan="5" class="text-center text-muted">Không có thông báo nào</td>
                            </tr>
                            <% } %>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>
