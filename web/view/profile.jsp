<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Users" %>

<%
    Users user = (Users) request.getAttribute("user");

    if (user == null) {
        response.sendRedirect("Logins");
        return;
    }
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Hồ sơ cá nhân</title>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        
           /* Thiết lập ảnh nền cho toàn bộ trang */
    body {
        background: url('${pageContext.request.contextPath}/images/481116918_1047798794043198_4149919712015941173_n.jpg') no-repeat center center fixed;
        background-size: cover;
    }
        .profile-container {
            max-width: 600px; /* Điều chỉnh độ rộng */
            min-height: 350px; /* Đặt chiều cao tối thiểu */
            padding: 30px;
            background: #f8f9fa;
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            margin: 50px auto;
        }
        .profile-container h2 {
            text-align: center;
            color: #6f42c1;
        }
        .btn-edit {
            display: block;
            width: 100%;
            text-align: center;
        }

        /* Responsive */
        @media (max-width: 768px) {
            .profile-container {
                max-width: 90%;
            }
        }

        @media (min-width: 1200px) {
            .profile-container {
                max-width: 800px; /* Rộng hơn trên màn hình lớn */
            }
        }
    </style>
</head>
<body>

    <!-- Gọi phần Header/Topnav -->
    <jsp:include page="topnav.jsp" />

    <!-- Nội dung chính -->
    <div class="d-flex justify-content-center align-items-center vh-100">
        <div class="profile-container">
            <h2>Thông tin cá nhân</h2>
            <p><strong>Họ và tên:</strong> <%= user.getFullName() %></p>
            <p><strong>Email:</strong> <%= user.getEmail() %></p>
            <p><strong>Vai trò:</strong> <%= user.getRole() %></p>
            <p><strong>Club ID:</strong> <%= user.getClubId() %></p>
            
            <a href="profile?action=edit" class="btn btn-primary btn-edit">Chỉnh sửa hồ sơ</a>
        </div>
    </div>

  <!-- Footer -->
<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script> <!<!-- Bắt buộc phải có  -->

</body>
</html>
