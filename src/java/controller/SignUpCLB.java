/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ClubDAO;
import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Club;
import model.MembershipRequest;
import model.Users;

/**
 *
 * @author Admin
 */
public class SignUpCLB extends HttpServlet {

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
            out.println("<title>Servlet SignUpCLB</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpCLB at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("Logins"); // Chuyển hướng nếu chưa đăng nhập
            return;
        }

        String clubName = request.getParameter("clubName");

        if (clubName == null || clubName.trim().isEmpty()) {
            clubName = "Câu lạc bộ chưa xác định"; // Giá trị mặc định
        }

        request.setAttribute("clubName", clubName);
        RequestDispatcher rs = request.getRequestDispatcher("view/dangkyCLB.jsp");
        rs.forward(request, response);
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
//        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        String clubName = request.getParameter("clubName");

// Kiểm tra clubName có bị null hoặc rỗng không
        if (clubName == null || clubName.trim().isEmpty()) {
            request.setAttribute("error", "Tên câu lạc bộ không hợp lệ.");
            RequestDispatcher rs = request.getRequestDispatcher("view/dangkyCLB.jsp");
            rs.forward(request, response);
            return;
        }

// Gọi ClubDAO để lấy thông tin CLB
        Club clubs = ClubDAO.getClubByName(clubName);

// Kiểm tra nếu clubs bị null
        if (clubs == null) {
            request.setAttribute("error", "Không tìm thấy câu lạc bộ: " + clubName);
            RequestDispatcher rs = request.getRequestDispatcher("view/dangkyCLB.jsp");
            rs.forward(request, response);
            return;
        }

// Nếu clubs hợp lệ, tiếp tục xử lý...
        String reason = request.getParameter("reason");
        MembershipRequest mr = UserDAO.checkRequest(user.getUserID());
        if (mr == null) {
            String succcess = "Request successfully";
            mr = UserDAO.addMembershipRequest(user.getUserID(), clubs.getClubID(), reason);
            request.setAttribute("mr", mr);
            request.setAttribute("success", succcess);

        } else {
            request.setAttribute("error", "Bạn đã gửi yêu cầu trước đó.");
        }

        RequestDispatcher rs = request.getRequestDispatcher("view/dangkyCLB.jsp");
        rs.forward(request, response);
        return;

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
