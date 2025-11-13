<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="model.Users" %>

<%
    HttpSession sess = request.getSession();
    Users user = (Users) sess.getAttribute("user");
    String clubName = request.getParameter("clubName");
    if (user == null) {
        response.sendRedirect("Logins");
        return;
    }
%>

<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng ký Câu lạc bộ</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
        <style>
            body {
                background: url('images/club-day-2022-5-910x607.jpg') no-repeat center center fixed;
                background-size: cover;
                font-family: 'Poppins', sans-serif;
            }
           
            .container-form {
                min-height: 100vh;
                display: flex;
                justify-content: center;
                align-items: center;
                padding: 20px;
            }
            .card {
                max-width: 800px;
                width: 100%;
                border-radius: 15px;
                overflow: hidden;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            }
            .card-header {
                background-color: #ff6600;
                color: white;
                font-weight: 600;
            }
            .form-control, .btn {
                border-radius: 10px;
            }
            .btn-success {
                background-color: #ff6600;
                border-color: #ff6600;
            }
            .btn-success:hover {
                background-color: #e65c00;
                border-color: #e65c00;
            }
        </style>
    </head>
    
    <body>
        <jsp:include page="topnav.jsp" />

        <div class="container-fluid container-form">
            <div class="card">
                <div class="card-header text-center">
                    <h4>Đăng ký tham gia <%= clubName %></h4>
                </div>
                <div class="card-body">
                    <form action="SignUpCLB" method="post">
                        <input type="hidden" name="clubName" value="<%= clubName %>">
                        <div class="mb-3">
                            <label class="form-label">Họ và tên</label>
                            <input type="text" class="form-control" name="fullName" value="<%= user.getFullName() %>" readonly>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" class="form-control" name="email" value="<%= user.getEmail() %>" readonly>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Lý do tham gia</label>
                            <textarea class="form-control" name="reason" rows="3" required></textarea>
                        </div>
                        <button type="submit" class="btn btn-success w-100">Gửi đăng ký</button>
                        <% String error = (String) request.getAttribute("error"); %>
                        <% if (error != null) { %>
                        <div class="alert alert-danger mt-2"><%= error %></div>
                        <% } %>
                        <% String success = (String) request.getAttribute("success"); %>
                        <% if (success != null) { %>
                        <div class="alert alert-success mt-2"><%= success %></div>
                        <% } %>
                    </form>
                </div>
            </div>
        </div>

                        <jsp:include page="footer.jsp" />
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script> <!<!-- Bắt buộc phải có  -->
     
    </body>
</html>
