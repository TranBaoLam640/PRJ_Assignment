<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Thêm thông báo mới</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

        <style>
            
            body {
             background: url('${pageContext.request.contextPath}/images/pexels-quang-nguyen-vinh-222549-2162471.jpg') no-repeat center center/cover;
                font-family: 'Arial', sans-serif;
            }
            .container {
                margin-top: 50px;
                max-width: 600px;
                background: white;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            }
            h2 {
                color: #007bff;
                text-align: center;
                margin-bottom: 20px;
            }
            .form-control {
                border-radius: 8px;
            }
            .btn-primary {
                background-color: #28a745;
                border: none;
                border-radius: 8px;
                padding: 10px;
                width: 100%;
                font-size: 16px;
                transition: 0.3s;
            }
            .btn-primary:hover {
                background-color: #2e8b57;
            }
            .btn-secondary {
                width: 100%;
                margin-top: 10px;
                border-radius: 8px;
            }
        </style>
    </head>
    <body>
       
        <%
            String error = request.getParameter("error");
        %>

        <div class="container">
            <h2><i class="fas fa-plus-circle"></i> Thêm thông báo mới</h2>

            <!-- Hiển thị lỗi nếu có -->
            <% if (error != null) { %>
            <div class="alert alert-danger text-center">
                <%= error %>
            </div>
            <% } %>

            <form action="Notifications" method="post">
                <input type="hidden" name="action" value="add">

                <div class="mb-3">
                    <label for="title" class="form-label"><i class="fas fa-heading"></i> Tiêu đề</label>
                    <input type="text" class="form-control" id="title" name="title" required>
                </div>

                <div class="mb-3">
                    <label for="message" class="form-label"><i class="fas fa-align-left"></i> Nội dung</label>
                    <textarea class="form-control" id="message" name="message" rows="4" required></textarea>
                </div>

                <div class="mb-3">
                    <label for="clubId" class="form-label"><i class="fas fa-users"></i> CLB</label>
                    <input type="number" class="form-control" id="clubId" name="clubId" required>
                </div>

                <button type="submit" class="btn btn-primary"><i class="fas fa-save"></i> Tạo thông báo</button>
                <a href="Notifications" class="btn btn-secondary"><i class="fas fa-arrow-left"></i> Quay lại</a>
            </form>
        </div>

     

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
   
    </body>
</html>