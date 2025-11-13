<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.MemberParticipation" %>
<%@ page import="model.Semester" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Set" %>
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
            <h2 class="text-center">Member Participation List</h2>

            <!-- Dropdown chọn học kỳ -->
            <div class="mb-3">
                <label for="semesterFilter" class="form-label">Select Semester:</label>
                <form action="Evaluation" method="GET">
                    <input type="hidden" name="action" value="filter">
                    <select name="semesterName" id="semesterFilter" class="form-select" onchange="this.form.submit()">
                        <option value="">All</option>
                        <%
                            List<Semester> mp = (List<Semester>) request.getAttribute("semester");
                            Set<String> semesters = new HashSet<>();
                            if (mp != null) {
                                for (Semester member : mp) {
                                    semesters.add(member.getSemesterName());
                                }
                            }

                            String selectedSemester = request.getParameter("semesterName");

                            for (String semester : semesters) {
                        %>
                        <option value="<%= semester %>" <%= (selectedSemester != null && selectedSemester.equals(semester)) ? "selected" : "" %>>
                            <%= semester %>
                        </option>
                        <%
                            }
                        %>
                    </select>
                </form>
            </div>

            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                    <tr>
                        <th>UserID</th>
                        <th>Full Name</th>
                        <th>Semester</th>
                        <th>Events Attended</th>
                        <th>Total Events</th>
                        <th>View</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<MemberParticipation> participationList = (List<MemberParticipation>) request.getAttribute("list");
                        if (participationList != null && !participationList.isEmpty()) {
                            for (MemberParticipation member : participationList) {
                    %>
                    <tr>
                        <td><%= member.getUserID() %></td>
                        <td><%= member.getFullName() %></td>
                        <td><%= member.getSemesterName() %></td>
                        <td><%= member.getEventAttended() %></td>
                        <td><%= member.getTotalEvent() %></td>
                        <td>
                            <button class="btn btn-primary btn-sm" onclick="location.href = 'Evaluation?action=view&userID=<%= member.getUserID() %>&semesterName=<%=  member.getSemesterName() %>'">
                                <i class="fas fa-eye"></i> View
                            </button>
                        </td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="6" class="text-center">No data available</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>

        <jsp:include page="footer.jsp" />

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
