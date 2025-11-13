<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, java.util.Set, java.util.HashSet, java.util.ArrayList, java.util.Collections" %>
<%@ page import="model.Users, model.Semester" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tạo Báo Cáo Hoạt Động</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
</head>
<body>
<%
    // Lấy danh sách học kỳ được đẩy từ Servlet (với kiểu Semester)
    List<Semester> semesterList = (List<Semester>) request.getAttribute("reports");
    if (semesterList == null) {
        semesterList = new ArrayList<>();  // Nếu null thì khởi tạo danh sách rỗng
    }
    
    // Tạo danh sách học kỳ duy nhất
    Set<String> semesterSet = new HashSet<>();
    for (Semester sem : semesterList) {
        semesterSet.add(sem.getSemesterName());
    }
    // Chuyển sang List để có thể sắp xếp nếu cần
    List<String> semesters = new ArrayList<>(semesterSet);
    Collections.sort(semesters); // Sắp xếp theo thứ tự alphabet; bạn có thể thay đổi theo yêu cầu
    
    // Lấy giá trị học kỳ được chọn từ request (nếu có)
    String selectedSemester = request.getParameter("semesterName");
    if (selectedSemester == null || selectedSemester.trim().isEmpty()) {
        if (!semesters.isEmpty()) {
            selectedSemester = semesters.get(0);
        }
    }
%>

<div class="container py-5">
    <h2 class="text-center text-primary mb-4">Tạo Báo Cáo Hoạt Động</h2>
    <form action="Report" method="post">
        <!-- Xác định action để Servlet xử lý tạo báo cáo -->
        <input type="hidden" name="action" value="create">
        
        <div class="mb-3">
            <label for="semesterSelect" class="form-label">Chọn Học Kỳ</label>
            <select name="semesterName" id="semesterSelect" class="form-select">
                <% for (String semester : semesters) { %>
                    <option value="<%= semester %>" <%= (selectedSemester != null && selectedSemester.equals(semester)) ? "selected" : "" %>>
                        <%= semester %>
                    </option>
                <% } %>
            </select>
        </div>
        
        <!-- Các trường nhập liệu cho báo cáo -->
        <div class="mb-3">
            <label for="memberChanges" class="form-label">Thay đổi thành viên</label>
            <textarea class="form-control" id="memberChanges" name="memberChanges" rows="3" placeholder="Nhập chi tiết thay đổi thành viên"></textarea>
        </div>
        
        <div class="mb-3">
            <label for="eventSummary" class="form-label">Tóm tắt sự kiện</label>
            <textarea class="form-control" id="eventSummary" name="eventSummary" rows="3" placeholder="Nhập tóm tắt các sự kiện đã tổ chức"></textarea>
        </div>
        
        <div class="mb-3">
            <label for="participationStats" class="form-label">Thống kê tham gia</label>
            <textarea class="form-control" id="participationStats" name="participationStats" rows="3" placeholder="Nhập thống kê tham gia"></textarea>
        </div>
        
        <div class="mb-3">
            <button type="submit" class="btn btn-success w-100">
                <i class="bi bi-file-earmark-plus"></i> Tạo Báo Cáo
            </button>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
