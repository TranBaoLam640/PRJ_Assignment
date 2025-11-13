package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.ReportDAO;
import dao.SemesterDAO;
import dao.UserDAO;
import model.Report;
import model.Users;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import model.MemberParticipation;
import model.Semester;

public class ReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        String action = request.getParameter("action");
        if (user == null) {
            response.sendRedirect("Logins");
            return;
        }

        // Lấy tham số semesterName từ request
        String semesterName = request.getParameter("semesterName");

        // Lấy danh sách báo cáo của CLB
        List<Report> reportList = ReportDAO.getReportsByClubID(user.getClubId());
        for (Report rps : reportList) {

            rps.setClub();
        }

        // Nếu semesterName null hoặc rỗng, gán giá trị mặc định lấy từ báo cáo đầu tiên (nếu có)
        if (semesterName == null || semesterName.isEmpty()) {
            if (!reportList.isEmpty()) {
                semesterName = reportList.get(0).getSemester();
            } else {
                semesterName = "Học kỳ mặc định"; // Hoặc giá trị phù hợp khác
            }
        }

        // Kiểm tra nếu action là "add" (để chuyển qua trang tạo báo cáo)
        if ("add".equals(action)) {
            ArrayList<Semester> se = SemesterDAO.getAllSemester();
            request.setAttribute("reports", se);
            request.getRequestDispatcher("view/addReport.jsp").forward(request, response);
            return;
        } else if ("edit".equals(action)) {
            ArrayList<Semester> se = SemesterDAO.getAllSemester();
            request.setAttribute("reports", se);
            String semester = request.getParameter("semesterName");
            Report rpt = ReportDAO.getReportsByClubIDAndSemester(user.getClubId(), semester);
            request.setAttribute("report", rpt);
            request.getRequestDispatcher("view/editReport.jsp").forward(request, response);
            return;
        }
        Report selectedReport = ReportDAO.getReportsByClubIDAndSemester(user.getClubId(), semesterName);
        if (selectedReport != null) {
            selectedReport.setClub();
            request.setAttribute("report", selectedReport);
        }

        // Lấy báo cáo dựa trên semesterName
        // Đẩy dữ liệu lên request
        request.setAttribute("reports", reportList);
        request.setAttribute("semesterName", semesterName);
        request.getRequestDispatcher("view/thongke.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        String action = request.getParameter("action");
        if (user == null) {
            response.sendRedirect("Logins");
            return;
        }
        if (action.equals("create")) {
            String semester = request.getParameter("semesterName");
            String memberChanges = request.getParameter("memberChanges");
            String eventSummary = request.getParameter("eventSummary");
            String participationStats = request.getParameter("participationStats");

            Report report = new Report();
            report.setClubId(user.getClubId());
            report.setMemberChanges(memberChanges);
            report.setSemester(semester);
            report.setEventSummary(eventSummary);
            report.setParticipationStats(participationStats);

            boolean success = ReportDAO.addReport(report);
            if (success) {
                response.sendRedirect("Report");
            } else {
                // In ra log hoặc hiển thị thông báo lỗi
                response.getWriter().println("Lỗi khi tạo báo cáo. Vui lòng thử lại!");
            }
        } else if (action.equals("edit")) {
            String reportIDStr = request.getParameter("reportID");
            int reportID = Integer.parseInt(reportIDStr);  // chuyển sang số nguyên
            String semester = request.getParameter("semesterName");
            String memberChanges = request.getParameter("memberChanges");
            String eventSummary = request.getParameter("eventSummary");
            String participationStats = request.getParameter("participationStats");

            Report report = new Report();
            report.setReportID(reportID); // set ReportID
            report.setClubId(user.getClubId());
            report.setMemberChanges(memberChanges);
            report.setSemester(semester);
            report.setEventSummary(eventSummary);
            report.setParticipationStats(participationStats);

            boolean success = ReportDAO.updateReport(report);
            if (success) {
                response.sendRedirect("Report");
            } else {
                // In ra log hoặc hiển thị thông báo lỗi
                response.getWriter().println("Lỗi khi tạo báo cáo. Vui lòng thử lại!");
            }

        }
    }
}
