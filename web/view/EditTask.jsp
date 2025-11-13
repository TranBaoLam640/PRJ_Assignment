<%-- 
    Document   : EditTask
    Created on : Mar 16, 2025, 6:46:29 PM
    Author     : chang 
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Chỉnh sửa nhiệm vụ</title>
    </head>
    <body>
        <h1>Chỉnh sửa nhiệm vụ</h1>
        <c:if test="${not empty message}">
            <p>${message}</p>
        </c:if>

        <c:if test="${not empty taskToEdit}">
            <form action="Task?action=update" method="post">
                <input type="hidden" name="taskId" value="${taskToEdit.taskID}">
                <label>Sự kiện:</label>
                <select name="eventId" required>
                    <c:forEach var="event" items="${events}">
                        <option value="${event.eventID}" ${event.eventID == taskToEdit.eventID ? 'selected' : ''}>${event.eventName}</option>
                    </c:forEach>
                </select><br>
                <label>Thành viên:</label>
                <select name="userId" required>
                    <c:forEach var="user" items="${users}">
                        <option value="${user.userID}" ${user.userID == taskToEdit.assignedUserID ? 'selected' : ''}>${user.fullName}</option>
                    </c:forEach>
                </select><br>
                <label>Nhiệm vụ:</label>
                <input type="text" name="taskDescription" value="${taskToEdit.taskDescription}" required><br>
                <label>Trạng thái:</label>
                <select name="status" required>
                    <option value="Completed" ${taskToEdit.status == 'Completed' ? 'selected' : ''}>Completed</option>
                    <option value="In Progress" ${taskToEdit.status == 'In Progress' ? 'selected' : ''}>In Progress</option>
                    <option value="Pending" ${taskToEdit.status == 'Pending' ? 'selected' : ''}>Pending</option>
                </select><br>
                <input type="submit" value="Lưu">
            </form>
            <a href="Task">Quay lại</a>
        </c:if>
        <c:if test="${empty taskToEdit}">
            <p>Không tìm thấy nhiệm vụ để chỉnh sửa!</p>
            <a href="Task">Quay lại</a>
        </c:if>
    </body>
</html>