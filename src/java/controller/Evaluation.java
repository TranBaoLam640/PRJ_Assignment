/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.SemesterDAO;
import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.MemberParticipation;
import model.Semester;
import model.Users;

/**
 *
 * @author Admin
 */
public class Evaluation extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Evaluation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Evaluation at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        boolean isAdmin = user.getRole().equals("Admin");

        if (user == null) {
            response.sendRedirect("Logins");
        } else {
            response.setContentType("text/html;charset-UTF-8");
            String action = request.getParameter("action");

            if (action == null) {

                List<MemberParticipation> participationList = UserDAO.getAllMemberParticipation(user.getClubId(), isAdmin);
                request.setAttribute("list", participationList);
                List<Semester> semester = SemesterDAO.getAllSemester();
                request.setAttribute("semester", semester);

                // Chuyển hướng đến JSP để hiển thị
                request.getRequestDispatcher("view/baocao.jsp").forward(request, response);
            } else if (action.equals("view")) {
                // Lấy userID từ request
                 String semesterName = request.getParameter("semesterName");
                String userid = request.getParameter("userID");
                int id = Integer.parseInt(userid);
                List<Semester> semester = SemesterDAO.getAllSemester();
                request.setAttribute("semester", semester);

                // Lấy thông tin chi tiết của thành viên
                MemberParticipation mp = UserDAO.getMemberParticipation(id,semesterName);

                // Nếu mp == null, khởi tạo đối tượng mới với giá trị mặc định
                if (mp == null) {
                    mp = new MemberParticipation();
                    mp.setUserID(id);
                    mp.setFullName(user.getFullName());
                    mp.setSemesterName("Chưa tham gia kì nào");
                    mp.setEventAttended(0);
                    mp.setTotalEvent(0);
                }

                // Tính toán Participation Level
                String participationLevel = calculateParticipationLevel(mp.getEventAttended(), mp.getTotalEvent());

                // Đưa vào request
                request.setAttribute("lists", mp);
                request.setAttribute("participationLevel", participationLevel);
                request.getRequestDispatcher("view/evaluationIncludeEvent.jsp").forward(request, response);
            } else if (action.equals("filter")) {
                String semesterName = request.getParameter("semesterName");
                List<Semester> semester = SemesterDAO.getAllSemester();
                request.setAttribute("semester", semester);

                List<MemberParticipation> participationList = UserDAO.getAllMemberParticipation(user.getClubId(), isAdmin);
                if (semesterName == null || semesterName.isEmpty()) {
                    participationList = UserDAO.getAllMemberParticipation(user.getClubId(),isAdmin);
                } else {
                    participationList = UserDAO.getMemberParticipationBySemester(semesterName, user.getClubId(), isAdmin);
                }

                request.setAttribute("list", participationList);

                // Chuyển hướng đến JSP để hiển thị
                request.getRequestDispatcher("view/baocao.jsp").forward(request, response);
            }

        }
    }

    /**
     * Tính toán Participation Level
     */
    private String calculateParticipationLevel(int attended, int total) {
        if (total == 0) {
            return "N/A";
        }
        double percentage = (attended * 100.0) / total;
        if (percentage >= 80) {
            return "Excellent";
        }
        if (percentage >= 50) {
            return "Good";
        }
        if (percentage >= 30) {
            return "Average";
        }
        return "Poor";
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
