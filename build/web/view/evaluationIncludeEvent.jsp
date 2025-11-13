<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.MemberParticipation" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Member Participation</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    </head>
    <body>
        <jsp:include page="topnav.jsp"/>
        <div class="container mt-5">
            <h2 class="text-center">Danh sách thành viên tham gia</h2>
            <table class="table table-bordered table-striped">
                <thead class="table-warning">
                    <tr>
                        <th>UserID</th>
                        <th>Full Name</th>
                        <th>Semester</th>
                        <th>Events Attended</th>
                        <th>Total Events</th>
                        <th>Evaluation</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        MemberParticipation participationList = (MemberParticipation) request.getAttribute("lists");
                    String participationLevel = (String) request.getAttribute("participationLevel");
                    %>

                    <tr>
                        <td><%= participationList.getUserID() %></td>
                        <td><%= participationList.getFullName() %></td>
                        <td><%= participationList.getSemesterName() %></td>
                        <td><%= participationList.getEventAttended() %></td>
                        <td><%= participationList.getTotalEvent() %></td>
                        <td><%= participationLevel %></td>
                    </tr>                
                </tbody>
            </table>
        </div>
        <jsp:include page="footer.jsp" />

    </body>
</html>
