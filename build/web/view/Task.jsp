<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <title>Quản lý nhiệm vụ</title>
    <style>
        .action-btn {
            margin: 0 5px;
        }
        .filter-form, .search-form {
            margin: 10px 0;
        }
    </style>
</head>
<body>
    <jsp:include page="topnav.jsp" />
    <div class="container mt-4">
        <h1 class="text-center">Quản lý nhiệm vụ</h1>
        
        <c:if test="${not empty message}">
            <div class="alert alert-info text-center">${message}</div>
        </c:if>

        <!-- Form lọc theo Club -->
        <c:if test="${currentUserRole == 'Admin'}">
            <div class="filter-form">
                <form action="Task" method="get" class="form-inline">
                    <input type="hidden" name="action" value="filterClub">
                    <label class="mr-2">Lọc theo CLB:</label>
                    <select name="clubName" class="form-control" onchange="this.form.submit()">
                        <option value="">Tất cả</option>
                        <c:forEach var="assignment" items="${assignments}">
                            <option value="${assignment.clubName}">${assignment.clubName}</option>
                        </c:forEach>
                    </select>
                </form>
            </div>
        </c:if>

        <!-- Form lọc theo Trạng thái -->

        <!-- Form tìm kiếm -->
        <div class="search-form">
            <form action="Task" method="get" class="form-inline">
                <input type="hidden" name="action" value="search">
                <label class="mr-2">Tìm kiếm:</label>
                <input type="text" name="keyword" placeholder="Nhập tên thành viên, nhiệm vụ, hoặc sự kiện" class="form-control mr-2"><br>
                <input type="submit" value="Tìm" class="btn btn-primary">
            </form>
        </div>

        <h3 class="mt-4">Bảng phân công nhiệm vụ</h3>
        <c:if test="${not empty assignments}">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Thành viên</th>
                        <th>Nhiệm vụ</th>
                        <th>Sự kiện</th>
                        <th>Tên CLB</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="assignment" items="${assignments}">
                        <tr>
                            <td>${assignment.fullName}</td>
                            <td>${assignment.taskDescription}</td>
                            <td>${assignment.eventName}</td>
                            <td>${assignment.clubName}</td>
                            <td>${assignment.status}</td>
                            <td>
                                <c:if test="${currentUserRole == 'Chairman' || currentUserRole == 'ViceChairman'|| sessionScope.user.role == 'Admin'|| sessionScope.user.role == 'TeamLeader'}">
                                    <form action="Task?action=delete" method="post" style="display:inline;">
                                        <input type="hidden" name="taskId" value="${assignment.taskID}">
                                        <input type="submit" value="Xóa" class="btn btn-danger action-btn " onclick="return confirm('Bạn có chắc muốn xóa?');">
                                    </form>
                                    <form action="Task" method="get" style="display:inline;">
                                        <input type="hidden" name="action" value="edit">
                                        <input type="hidden" name="taskId" value="${assignment.taskID}">
                                        <input type="submit" value="Sửa" class="btn btn-warning action-btn ">
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty assignments}">
            <p class="text-center">Chưa có nhiệm vụ nào được phân công.</p>
        </c:if>

        <!-- Form phân công nhiệm vụ -->
        <c:if test="${currentUserRole == 'Chairman' || currentUserRole == 'ViceChairman'|| currentUserRole == 'Admin'|| sessionScope.user.role == 'TeamLeader'}">
            <h3 class="mt-4">Phân công nhiệm vụ</h3>
            <form action="Task?action=assignRole" method="post">
                <div class="form-group">
                    <label>Chọn sự kiện:</label>
                    <select name="eventId" class="form-control" required>
                        <c:forEach var="event" items="${events}">
                            <option value="${event.eventID}">${event.eventName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Chọn thành viên:</label>
                    <select name="userId" class="form-control" required>
                        <c:forEach var="user" items="${users}">
                            <option value="${user.userID}">${user.fullName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Nhiệm vụ:</label>
                    <select name="roleName" class="form-control" required>
                        <option value="Thư ký">Thư ký</option>
                        <option value="Hậu cần">Hậu cần</option>
                        <option value="Truyền thông">Truyền thông</option>
                        <option value="Đối ngoại">Đối ngoại</option>
                    </select>
                </div>
                <input type="submit" value="Phân công" class="btn btn-success mt-2">
            </form>
        </c:if>

        <a href="Users" class="btn btn-secondary mt-4">Quay lại</a>
    </div>
    
    <!-- Footer -->
    <jsp:include page="footer.jsp"/>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>