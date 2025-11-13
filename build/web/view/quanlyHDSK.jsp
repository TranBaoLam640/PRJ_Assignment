<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý hoạt động và sự kiện</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="../css/styles.css">
    
</head>
<body>
    
    <jsp:include page="topnav.jsp"/>
<div class="container">
    <h1>Quản lý - Quản lý hoạt động và sự kiện</h1>

    <!-- Bảng danh sách hoạt động và sự kiện -->
    <div class="table-container">
        <h4>Danh sách hoạt động và sự kiện</h4>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên hoạt động/sự kiện</th>
                    <th>Loại</th>
                    <th>Trạng thái</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>Hội thảo công nghệ</td>
                    <td>Sự kiện độc lập</td>
                    <td><i class="fas fa-spinner fa-spin" style="color: orange;"></i> Đang thực hiện</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Họp CLB hàng tháng</td>
                    <td>Hoạt động thường xuyên</td>
                    <td><i class="fas fa-check-circle" style="color: green;"></i> Hoàn thành</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Giải đấu eSports</td>
                    <td>Sự kiện đặc biệt</td>
                    <td><i class="fas fa-times" style="color: red;"></i> Hủy bỏ</td>
                </tr>
            </tbody>
        </table>
    </div>

    <!-- Bảng ghi chú tình trạng hoạt động -->
    <div class="table-container">
        <h4>Ghi chú tình trạng hoạt động</h4>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>STT</th>
                    <th>Hoạt động</th>
                    <th>Trạng thái</th>
                    <th>Ghi chú</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>Hội thảo công nghệ</td>
                    <td><i class="fas fa-spinner fa-spin" style="color: orange;"></i> Đang thực hiện</td>
                    <td>Đang chuẩn bị nội dung</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Họp CLB hàng tháng</td>
                    <td><i class="fas fa-check-circle" style="color: green;"></i> Hoàn thành</td>
                    <td>Đã diễn ra vào ngày 10/03</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Giải đấu eSports</td>
                    <td><i class="fas fa-times" style="color: red;"></i> Hủy bỏ</td>
                    <td>Không có số lượng đăng ký</td>
                </tr>
            </tbody>
        </table>
    </div>

    <!-- Bảng phân công trách nhiệm -->
    <div class="table-container">
        <h4>Phân công trách nhiệm</h4>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>STT</th>
                    <th>Nhiệm vụ</th>
                   <th>Người phụ trách</th>
                    <th>Trạng thái</th>
                </tr>            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>Chuẩn bị nội dung hội thảo</td>
                    <td>Nguyễn Văn A</td>
                    <td><i class="fas fa-spinner fa-spin" style="color: orange;"></i> Đang thực hiện</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Tổ chức họp CLB</td>
                    <td>Trần Thị B</td>
                    <td><i class="fas fa-check-circle" style="color: green;"></i> Hoàn thành</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Quảng bá giải đấu eSports</td>
                    <td>Lê Văn C</td>
                    <td><i class="fas fa-times" style="color: red;"></i> Hủy bỏ</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
    <jsp:include page="footer.jsp"/>
<!-- Bootstrap JS -->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
