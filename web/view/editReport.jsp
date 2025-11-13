<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, java.util.Set, java.util.HashSet, java.util.ArrayList, java.util.Collections" %>
<%@ page import="model.Users, model.Semester, model.Report" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sửa Báo Cáo Hoạt Động</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
</head>
<body>
<%
    // Lấy danh sách học kỳ (kiểu Semester) được đẩy từ Servlet
    List<Semester> semesterList = (List<Semester>) request.getAttribute("reports");
    if (semesterList == null) {
        semesterList = new ArrayList<>();
    }
    
    // Tạo danh sách học kỳ duy nhất theo tên (để hiển thị dropdown)
    Set<String> semesterSet = new HashSet<>();
    for (Semester sem : semesterList) {
        semesterSet.add(sem.getSemesterName());
    }
    List<String> semesters = new ArrayList<>(semesterSet);
    Collections.sort(semesters); // Sắp xếp theo thứ tự alphabet (có thể điều chỉnh nếu cần)
    
    // Lấy đối tượng Report cần chỉnh sửa từ request
    Report report = (Report) request.getAttribute("report");
    // Xác định học kỳ được chọn: ưu tiên giá trị từ report, nếu có tham số từ request thì cập nhật lại
    String selectedSemester = report != null ? report.getSemester() : "";
    String paramSemester = request.getParameter("semesterName");
    if (paramSemester != null && !paramSemester.trim().isEmpty()) {
        selectedSemester = paramSemester;
    }
%>

<div class="container py-5">
    <h2 class="text-center text-primary mb-4">Sửa Báo Cáo Hoạt Động</h2>
    <form action="Report" method="post">
        <!-- Xác định action để Servlet xử lý chỉnh sửa báo cáo -->
        <input type="hidden" name="action" value="edit">
        <!-- Ẩn ReportID nếu cần sử dụng để xác định báo cáo cần sửa -->
        <input type="hidden" name="reportID" value="<%= report != null ? report.getReportID() : "" %>">
        
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
        
        <div class="mb-3">
            <label for="memberChanges" class="form-label">Thay đổi thành viên</label>
            <textarea class="form-control" id="memberChanges" name="memberChanges" rows="3" placeholder="Nhập chi tiết thay đổi thành viên"><%= report != null ? report.getMemberChanges() : "" %></textarea>
        </div>
        
        <div class="mb-3">
            <label for="eventSummary" class="form-label">Tóm tắt sự kiện</label>
            <textarea class="form-control" id="eventSummary" name="eventSummary" rows="3" placeholder="Nhập tóm tắt các sự kiện đã tổ chức"><%= report != null ? report.getEventSummary() : "" %></textarea>
        </div>
        
        <div class="mb-3">
            <label for="participationStats" class="form-label">Thống kê tham gia</label>
            <textarea class="form-control" id="participationStats" name="participationStats" rows="3" placeholder="Nhập thống kê tham gia"><%= report != null ? report.getParticipationStats() : "" %></textarea>
        </div>
        
        <div class="mb-3">
            <button type="submit" class="btn btn-warning w-100">
                <i class="bi bi-pencil"></i> Lưu Thay Đổi
            </button>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
