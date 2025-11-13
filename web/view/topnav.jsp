<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="model.Users" %>
<!-- Header -->
<header class="navbar navbar-expand-lg navbar-light navbar-custom shadow-sm">
    <div class="container">
        <a class="navbar-brand me-auto" href="#">
            <img src="${pageContext.request.contextPath}/images/logofpt.png" height="50">
        </a>

        <!-- Navbar toggle button for mobile -->
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <nav class="navbar-nav mx-auto">
                <a class="nav-link active" href="<%= request.getContextPath() %>/" id="home-link">Trang chủ</a>
                <!-- Quản lý -->
                <div class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle management-link" href="#" id="navbarDropdown1" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        Quản lý
                    </a>
                    <ul class="dropdown-menu management-dropdown" aria-labelledby="navbarDropdown1">
                        <li><a class="dropdown-item" href="Event?action=register">Đăng ký sự kiện</a></li>
                        <li><a class="dropdown-item" href="MemberServlet">Quản lý thành viên</a></li>
                        <li><a class="dropdown-item" href="Event">Quản lý sự kiện</a></li>
                        <li><a class="dropdown-item" href="Task">Quản lý nhiệm vụ</a></li>
                    </ul>
                </div>


                <!-- Báo cáo -->
                <div class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        Báo cáo
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown2">
                        <li><a class="dropdown-item" href="Evaluation">Đánh giá hoạt động</a></li>
                        <li><a class="dropdown-item" href="Report">Báo cáo & Thống kê</a></li>
                    </ul>
                </div>

                <!-- Thông báo -->
                <div class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown3" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        Thông báo
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown3">
                        <li><a class="dropdown-item" href="Notifications">Thông báo sự kiện</a></li>
                        <li><a class="dropdown-item" href="Approve">Duyệt đơn</a></li>
                    </ul>
                </div>

                <a class="nav-link" href="profile">Thông tin Cá nhân</a>
            </nav>

            <div>
                <% 
                    Users user = (Users) session.getAttribute("user"); 
                    if (user == null) { 
                %>
                <a href="Signups" class="btn btn-white btn-sm">Đăng ký</a>
                <a href="Logins" class="btn btn-white btn-sm">Đăng nhập</a>
                <% } else { %>
                <span class="text-white me-2">Xin chào, <%= user.getFullName() %></span>
                <a href="${pageContext.request.contextPath}/Logout" class="btn btn-white btn-sm">
                    <i class="fas fa-sign-out-alt "></i> <!-- Biểu tượng logout -->
                </a>
                <% } %>
            </div>
        </div>
    </div>
</header>
