<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.MembershipRequest" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Danh sách Yêu cầu Tham gia</title>
    
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
    <!-- Custom CSS -->
    <style>
        /* Đặt ảnh nền cho toàn trang */
        body {
            background: url('${pageContext.request.contextPath}/images/pngtree-3d-rendering-of-furniture-inside-an-empty-high-school-classroom-image_3759504.jpg') no-repeat center center fixed;
            background-size: cover;
        }

        /* Container chứa bảng */
        .table-container {
            background: antiquewhite;
            padding: 20px;
            border-radius: 12px; /* Bo góc */
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.25); /* Đổ bóng */
        }

        /* Căn giữa bảng trong màn hình */
        .center-table {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh; /* Chiều cao toàn màn hình */
        }

        /* Tiêu đề bảng */
        h2 {
            color: #fff; /* Chữ trắng để nổi bật trên ảnh nền */
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5); /* Hiệu ứng bóng */
        }

        /* Header của bảng */
        .table thead {
            background-color: #0d6efd !important; /* Màu xanh đậm */
            color: white;
        }

        /* Nút chấp nhận */
        .btn-approve {
            background-color: #198754; /* Xanh lá */
            color: white;
            border: none;
            padding: 6px 12px;
            border-radius: 5px;
        }

        /* Nút từ chối */
        .btn-reject {
            background-color: #bb2d3b; /* Đỏ nhạt hơn */
            color: white;
            border: none;
            padding: 6px 12px;
            border-radius: 5px;
        }

        /* Hiệu ứng hover */
        .btn-approve:hover {
            background-color: #157347;
        }

        .btn-reject:hover {
            background-color: #a71d2a;
        }
    </style>
</head>
<body>

    <jsp:include page="topnav.jsp" />

    <%
        String role = (String) session.getAttribute("role");
        boolean isAdmin = role != null && (role.equals("Admin") || role.equals("Chairman"));
        String error = (String) request.getAttribute("error");
    %>

    <div class="container center-table">
        <div class="table-container">
            <h2 class="text-center mb-4"><i class="fas fa-bell"></i> Danh sách Yêu cầu Tham gia</h2>

            <!-- Hiển thị lỗi nếu có -->
            <% if (error != null) { %>
                <div class="alert alert-danger text-center">
                    <i class="fas fa-exclamation-circle"></i> <%= error %>
                </div>
            <% } %>

            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Tên Người Dùng</th>
                        <th>Câu Lạc Bộ</th>
                        <th>Lý Do</th>
                        <th>Trạng thái</th>
                        <% if (isAdmin) { %>
                        <th>Hành động</th>
                        <% } %>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<MembershipRequest> requests = (List<MembershipRequest>) request.getAttribute("pendingRequests");
                        if (requests != null && !requests.isEmpty()) {
                            int count = 1;
                            for (MembershipRequest req : requests) {
                    %>
                    <tr>
                        <td><%= count++ %></td>
                        <td><%= req.getUser().getFullName() %></td>
                        <td><%= req.getClub().getClubName() %></td>
                        <td><%= req.getReason() %></td>
                        <td><%= req.getStatus() %></td>
                        <% if (isAdmin) { %>
                        <td>
                            <form method="POST" action="Approve" class="d-inline">
                                <input type="hidden" name="requestID" value="<%= req.getRequestID() %>">
                                <button type="submit" name="action" value="approve" class="btn btn-approve">
                                    ✔ Chấp nhận
                                </button>
                            </form>
                            <form method="POST" action="Approve" class="d-inline">
                                <input type="hidden" name="requestID" value="<%= req.getRequestID() %>">
                                <button type="submit" name="action" value="reject" class="btn btn-reject">
                                    ✖ Từ chối
                                </button>
                            </form>
                        </td>
                        <% } %>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="6" class="text-center">Không có yêu cầu nào đang chờ xử lý.</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>

    <jsp:include page="footer.jsp" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script> <!<!-- Bắt buộc phải có  -->
</body>
</html>
