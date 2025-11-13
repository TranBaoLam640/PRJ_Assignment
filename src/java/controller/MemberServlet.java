/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.*;
import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;

/**
 *
 * @author gigabyte
 */
public class MemberServlet extends HttpServlet {

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
            out.println("<title>Servlet MemberServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MemberServlet at " + request.getContextPath() + "</h1>");
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
            response.sendRedirect("Logins");
            return;
        }
        String role = user.getRole();
        int clubId = user.getClubId();
        boolean isAdmin = role.equals("Admin");
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "add":
                if (role.equals("Chairman")|| role.equals("ViceChairman")  || role.equals("Admin")) {
                    RequestDispatcher rs = request.getRequestDispatcher("view/AddMember.jsp");
                    rs.forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền thêm thành viên!");
                }
                break;
            case "edit":
                if (role.equals("Chairman") || role.equals("Admin")) {
                    int userId = Integer.parseInt(request.getParameter("id"));
                    Users member = UserDAO.getUserByID(userId);
                    if (!isAdmin && member.getClubId() != clubId) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền chỉnh sửa thành viên này!");
                        return;
                    }
                    request.setAttribute("member", member);
                    RequestDispatcher rs = request.getRequestDispatcher("view/EditMember.jsp");
                    rs.forward(request, response);
                }
                break;
            case "delete":
                if (role.equals("Chairman") || role.equals("Admin")|| role.equals("ViceChairman")) {
                    int userId = Integer.parseInt(request.getParameter("id"));
                    Users member = UserDAO.getUserByID(userId);
                    if (!isAdmin && member.getClubId() != clubId) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền xóa thành viên này!");
                        return;
                    }
                    UserDAO.deleteUsers(userId);
                    response.sendRedirect("MemberServlet");
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền xóa thành viên!");
                }
                break;
            case "history":
                int userId = Integer.parseInt(request.getParameter("id"));
                Users member = UserDAO.getUserByID(userId);
                if (!isAdmin && member.getClubId() != clubId) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền xem lịch sử hoạt động của thành viên này!");
                    return;
                }
                ArrayList<MemberActivity> activities = UserDAO.getActivityHistory(userId);
                request.setAttribute("activities", activities);
                RequestDispatcher rs = request.getRequestDispatcher("view/ActivityHistory.jsp");
                rs.forward(request, response);
                break;
            case "search":
                String keyword = request.getParameter("keyword");
                request.setAttribute("keyword", keyword);
                ArrayList<Users> members = UserDAO.SearchUsers(keyword, clubId, isAdmin);
                request.setAttribute("members", members);
                rs = request.getRequestDispatcher("view/MemberList.jsp");
                rs.forward(request, response);
                break;
            case "list":
            default:
                ArrayList<Users> allMembers = UserDAO.getAllUsers(clubId, isAdmin);
                request.setAttribute("members", allMembers);
                rs = request.getRequestDispatcher("view/MemberList.jsp");
                rs.forward(request, response);
                break;
        }
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
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("view/login.jsp");
            return;
        }
        String role = user.getRole();
        int clubId = user.getClubId();
        boolean isAdmin = role.equals("Admin");
        String action = request.getParameter("action");

        if (action.equals("add") && (role.equals("Chairman") || role.equals("Admin")|| role.equals("ViceChairman"))) {
            int memberClubId = Integer.parseInt(request.getParameter("clubId"));
            if (!isAdmin && memberClubId != clubId) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn chỉ có thể thêm thành viên vào CLB của mình!");
                return;
            }
            Users newUser = new Users(
                    0,
                    request.getParameter("fullName"),
                    request.getParameter("email"),
                    "default_password",
                    request.getParameter("role"),
                    memberClubId
            );
            UserDAO.addUser(newUser);
        } else if (action.equals("update") && (role.equals("Chairman") || role.equals("Admin"))) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            Users currentUser = UserDAO.getUserByID(userId);
            if (!isAdmin && currentUser.getClubId() != clubId) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền cập nhật thành viên này!");
                return;
            }
            int memberClubId = Integer.parseInt(request.getParameter("clubId"));
            if (!isAdmin && memberClubId != clubId) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn chỉ có thể cập nhật thành viên trong CLB của mình!");
                return;
            }
            Users updatedUser = new Users(
                    userId,
                    request.getParameter("fullName"),
                    request.getParameter("email"),
                    currentUser.getPassword(),
                    request.getParameter("role"),
                    memberClubId
            );
            UserDAO.updateUsers(updatedUser);
        }
        response.sendRedirect("MemberServlet");
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
