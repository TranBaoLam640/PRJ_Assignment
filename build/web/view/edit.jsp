<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh sửa thành viên</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

    <jsp:include page="topnav.jsp" />

<div class="container mt-5">
    <h2 class="text-center">Chỉnh sửa thành viên</h2>

    <form action="MemberServlet" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="userId" value="${member.userID}">

        <div class="mb-3">
            <label for="fullName" class="form-label">Họ và Tên:</label>
            <input type="text" class="form-control" id="fullName" name="fullName" value="${member.fullName}" required>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">Email:</label>
            <input type="email" class="form-control" id="email" name="email" value="${member.email}" required>
        </div>

        <div class="mb-3">
            <label for="role" class="form-label">Vai trò:</label>
            <select class="form-select" id="role" name="role" required>
                <option value="Member" ${member.role == 'Member' ? 'selected' : ''}>Thành viên</option>
                <option value="TeamLeader" ${member.role == 'TeamLeader' ? 'selected' : ''}>Trưởng nhóm</option>
                <option value="ViceChairman" ${member.role == 'ViceChairman' ? 'selected' : ''}>Phó chủ nhiệm</option>
                <option value="Chairman" ${member.role == 'Chairman' ? 'selected' : ''}>Chủ nhiệm</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="clubId" class="form-label">ID CLB:</label>
            <input type="number" class="form-control" id="clubId" name="clubId" value="${member.clubId}" required>
        </div>

        <button type="submit" class="btn btn-success">Cập nhật</button>
        <a href="MemberServlet" class="btn btn-secondary">Hủy</a>
    </form>
</div>

<jsp:include page="footer.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
