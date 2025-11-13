<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, java.util.Set, java.util.HashSet" %>
<%@ page import="model.Users, model.Report" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Báo cáo & Thống kê</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script>
            function filterBySemester() {
                var semester = document.getElementById("semesterFilter").value;
                window.location.href = "Report?action=filter&semesterName=" + semester;
            }
        </script>
    </head>
    <body>
        <jsp:include page="topnav.jsp" />
        <% 
            Users user = (Users) session.getAttribute("user");
            if (user == null) { 
                response.sendRedirect("Logins");
                return;
            } 

            // Lấy danh sách báo cáo từ request
            Report rp = (Report) request.getAttribute("report");
            List<Report> reports = (List<Report>) request.getAttribute("reports");

            // Tạo danh sách học kỳ duy nhất
            Set<String> semesters = new HashSet<>();
            if (reports != null) {
                for (Report report : reports) {
                    semesters.add(report.getSemester());
                }
            }

             // Lấy giá trị học kỳ được chọn từ request
            String selectedSemester = request.getParameter("semesterName");
            if ((selectedSemester == null || selectedSemester.trim().isEmpty()) && semesters != null && !semesters.isEmpty()) {
            // Vì semesters là HashSet nên thứ tự không đảm bảo, nếu cần thứ tự thì chuyển sang List và sắp xếp
            selectedSemester = (String) semesters.toArray()[0];
            }
        %>

        <div class="container py-5">
            <h2 class="text-center text-primary mb-4">Báo cáo & Thống kê <%= rp.getClub().getClubName()%></h2>
            <h2 class="text-center text-primary mb-4"><%= selectedSemester %></h2>
            <div class="d-flex justify-content-between mb-3">
                <!-- Bộ lọc học kỳ -->
                <form action="Report" method="GET">
                    <input type="hidden" name="action" value="filter">
                    <select name="semesterName" id="semesterFilter" class="form-select" onchange="this.form.submit()">
                        <% for (String semester : semesters) { %>
                        <option value="<%= semester %>" <%= (selectedSemester != null && selectedSemester.equals(semester)) ? "selected" : "" %>>
                            <%= semester %>
                        </option>
                        <% } %>
                    </select>
                </form>

                
            </div>

            <!-- Hiển thị nút "Tạo báo cáo" nếu là Team Leader -->
            <% if ("TeamLeader".equalsIgnoreCase(user.getRole()) || "Admin".equalsIgnoreCase(user.getRole()) ) { %>
            <div class="text-end mb-3">
                <a href="Report?action=add" class="btn btn-success">
                    <i class="bi bi-file-plus"></i> Tạo Báo Cáo
                </a>
                 <a href="Report?action=edit&semesterName=<%= selectedSemester %>" class="btn btn-warning">
                    <i class="bi bi-pencil"></i> Sửa Báo Cáo
                </a>
            </div>
            <% } %>

            <!-- Thống kê -->
            <div class="row g-4">
                <div class="col-md-4">
                    <div class="card h-100 text-center shadow">
                        <div class="card-body">
                            <div class="display-4 text-primary mb-2">
                                <i class="bi bi-people"></i>
                            </div>
                            <h2 class="card-title mb-3"><%=rp.getParticipationStats()%></h2>
                            <p class="card-text text-muted">Thông kê tham gia</p>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card h-100 text-center shadow">
                        <div class="card-body">
                            <div class="display-4 text-success mb-2">
                                <i class="bi bi-graph-up"></i>
                            </div>
                            <h2 class="card-title mb-3"><%=rp.getMemberChanges()%></h2>
                            <p class="card-text text-muted">Thay đổi thành viên</p>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card h-100 text-center shadow">
                        <div class="card-body">
                            <div class="display-4 text-warning mb-2">
                                <i class="bi bi-star"></i>
                            </div>
                            <h2 class="card-title mb-3"><%=rp.getEventSummary()%></h2>
                            <p class="card-text text-muted">Tổng kết sự kiện</p>
                        </div>
                    </div>
                </div>

            </div>
        </div>
<!-- Footer -->
<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script> <!<!-- Bắt buộc phải có  -->
    </body>
</html>
