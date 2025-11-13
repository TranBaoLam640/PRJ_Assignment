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
    <title>Quản lý -Quản lý thành viên</title>
    <link rel="stylesheet" href="../css/styles.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"> 
</head>
<body>

    <jsp:include page="topnav.jsp"/>
            
    <div class="container">
    <h1>Quản lý - Quản lý sự kiện</h1>

    <div class="table-container">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Stt</th>
                    <th>Tên sự kiện</th>
                    <th>Ngày tổ chức</th>
                    <th>Địa điểm</th>
                    <th>Trạng thái</th>
                    <th>Số lượng</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>Đại hội thể thao</td>
                    <td>01/01/2023</td>
                    <td>Đại học FPT</td>
                   <td><i class="fas fa-spinner fa-spin" style="color: orange;"></i> Đang tổ chức</td>
                    <td>100</td>
                    <td>
                        <a href="#" class="btn btn-info">Xem chi tiết</a>
                        <a href="#" class="btn btn-warning">Sửa</a>
                        <a href="#" class="btn btn-danger">Xóa</a>
                        <a href="#" class="btn btn-success">Gửi thông báo</a>
                    </td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Hội thảo công nghệ</td>
                    <td>15/02/2023</td>
                    <td>Hà Nội</td>
                                      <td><i class="fas fa-clock" style="color: blue;"></i> Chưa diễn ra</td>
                    <td>200</td>
                    <td>
                        <a href="#" class="btn btn-info">Xem chi tiết</a>
                        <a href="#" class="btn btn-warning">Sửa</a>
                        <a href="#" class="btn btn-danger">Xóa</a>
                        <a href="#" class="btn btn-success">Gửi thông báo</a>
                    </td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Chương trình tình nguyện</td>
                    <td>30/03/2023</td>
                    <td>Thành phố Hồ Chí Minh</td>
                   <td><i class="fas fa-times" style="color: red;"></i> Hủy bỏ</td>
                    <td>50</td>
                    <td>
                        <a href="#" class="btn btn-info">Xem chi tiết</a>
                        <a href="#" class="btn btn-warning">Sửa</a>
                        <a href="#" class="btn btn-danger">Xóa</a>
                        <a href="#" class="btn btn-success">Gửi thông báo</a>
                    </td>
                </tr>
                
                <tr>
                    <td>4</td>
                    <td>Chương trình tình nguyện</td>
                    <td>30/03/2023</td>
                    <td>Thành phố Hồ Chí Minh</td>
                   <td><i class="fas fa-check-circle" style="color: green;"></i> Đã tổ chức</td>
                    <td>50</td>
                    <td>
                        <a href="#" class="btn btn-info">Xem chi tiết</a>
                        <a href="#" class="btn btn-warning">Sửa</a>
                        <a href="#" class="btn btn-danger">Xóa</a>
                        <a href="#" class="btn btn-success">Gửi thông báo</a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

    <a  class="btn btn-success">Thêm sự kiện</a> <!<!-- href="AddEvent.jsp" -->
</div>
 <jsp:include page="footer.jsp"/>
    <script>
        function editRecord(id) {
            alert('Chỉnh sửa bản ghi có ID: ' + id);
            // Chuyển hướng đến trang chỉnh sửa hoặc xử lý logic khác ở đây
        }
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script> <!<!-- Bắt buộc phải có  -->
</body>
</html>