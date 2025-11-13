package controller;

import dao.ProfileDAO;
import model.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user"); // ✅ Lấy Users từ session

        if (user == null) {
            response.sendRedirect("Logins"); // Nếu chưa đăng nhập, chuyển về trang login
            return;
        }

        String action = request.getParameter("action");
        if ("edit".equals(action)) {
            // ✅ Hiển thị trang chỉnh sửa profile
            request.setAttribute("user", user);
            request.getRequestDispatcher("view/editProfile.jsp").forward(request, response);
        } else {
            // ✅ Hiển thị profile người đang đăng nhập
            request.setAttribute("user", user);
            request.getRequestDispatcher("view/profile.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // ✅ Xử lý cập nhật thông tin người dùng
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("Logins");
            return;
        }

        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        int clubId = Integer.parseInt(request.getParameter("clubId"));

        boolean success = ProfileDAO.updateUser(user.getUserID(), fullName, password, clubId);
        if (success) {
            // ✅ Cập nhật thông tin session
            user.setFullName(fullName);
            user.setPassword(password);
            user.setClubId(clubId);
            session.setAttribute("user", user);
            response.sendRedirect("profile");
        } else {
            request.setAttribute("error", "Cập nhật thất bại!");
            request.setAttribute("user", user);
            request.getRequestDispatcher("view/editProfile.jsp").forward(request, response);
        }
    }
}
