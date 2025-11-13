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
    <title>Quản lý -Quản lý Ban quản trị</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>

    <jsp:include page="topnav.jsp" />

    <!-- Bảng quản lý -->
    <div class="container mt-4">
        <h1>Quản lý - Quản lý ban Quản trị</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>STT</th>
                    <th>Họ và Tên</th>
                    
                    <th>Vai Trò</th>
                    <th>Email</th>
                    <th>Số điện thoại</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>Chủ nhiệm</td>
                    <td>001</td>
                    <td>Quản trị</td>
                    <td>0123456789</td>
                    <td>
                        <button class="btn btn-warning" onclick="editRecord(1)">
                            <i class="fas fa-pencil-alt icon-edit"></i>
                        </button>
                    </td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Trưởng phòng</td>
                    <td>002</td>
                    <td>Quản trị</td>
                    <td>0987654321</td>
                    <td>
                        <button class="btn btn-warning" onclick="editRecord(2)">
                            <i class="fas fa-pencil-alt icon-edit"></i>
                        </button>
                    </td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Trưởng phòng</td>
                    <td>002</td>
                    <td>Quản trị</td>
                    <td>0987654321</td>
                    <td>
                        <button class="btn btn-warning" onclick="editRecord(3)">
                            <i class="fas fa-pencil-alt icon-edit"></i>
                        </button>
                    </td>
                </tr>
                <!-- Thêm các dòng khác ở đây nếu cần -->
            </tbody>
        </table>
    </div>
   <jsp:include page="footer.jsp" />
    <script>
        function editRecord(id) {
            alert('Chỉnh sửa bản ghi có ID: ' + id);
            // Chuyển hướng đến trang chỉnh sửa hoặc xử lý logic khác ở đây
        }
    </script>

   
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script> <!<!-- Bắt buộc phải có  -->
</body>
</html>