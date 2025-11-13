package controller;

import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.MembershipRequest;
import model.Users;

public class ApproveServlet extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("Logins");
            return;
        }
        // Lấy vai trò của user
        String role = user.getRole();
        List<MembershipRequest> requests;
        session.setAttribute("role", user.getRole());


        try {
            if ("Member".equalsIgnoreCase(role)) {
                // 🔹 Thành viên xem tất cả yêu cầu của chính họ
                requests = UserDAO.getMembershipRequestsByUserId(user.getUserID());
            } else {
                // 🔹 Admin hoặc Chairman chỉ xem yêu cầu "Pending" của CLB mà họ quản lý
                requests = UserDAO.getMembershipRequests(user.getClubId());
            }

            for (MembershipRequest requestItem : requests) {
                requestItem.setUser();
                requestItem.setClub();
            }
            request.setAttribute("pendingRequests", requests);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/approve.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi tải danh sách yêu cầu.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("Logins");
            return;
        }

        try {
            int requestID = Integer.parseInt(request.getParameter("requestID"));
            String action = request.getParameter("action");
            MembershipRequest membershipRequest = UserDAO.getMembershipRequestById(requestID);

            if (membershipRequest == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Yêu cầu không tồn tại.");
                return;
            }

            if ("approve".equals(action)) {
                UserDAO.updateMembershipRequestStatus(requestID, "Approved", membershipRequest.getUserId(), membershipRequest.getClubId());
            } else if ("reject".equals(action)) {
                UserDAO.updateMembershipRequestStatus(requestID, "Rejected", membershipRequest.getUserId(), membershipRequest.getClubId());
            }

            // Chuyển hướng về trang duyệt yêu cầu sau khi xử lý
            response.sendRedirect("Approve");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID yêu cầu không hợp lệ.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi xử lý yêu cầu.");
        }
    }
}
