package controller;

import dao.NotificationDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Notification;
import model.Users;

public class Notifications extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        // Kiểm tra nếu user chưa đăng nhập, chuyển hướng đến trang đăng nhập
        if (user == null) {
            response.sendRedirect("Logins");
            return;
        }
        
        

        // Đặt vai trò vào session để sử dụng trong giao diện
        session.setAttribute("role", user.getRole());

        // Lấy action từ request
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "add":
                    request.getRequestDispatcher("view/addNotification.jsp").forward(request, response);
                    return;

                case "delete":
                    handleDeleteNotification(request, response);
                    return;

                case "edit":
                    handleEditNotification(request, response);
                    return;

                default:
                    request.setAttribute("error", "Hành động không hợp lệ!");
                    break;
            }
        }

        // Nếu không có action, hiển thị danh sách thông báo
        List<Notification> notifications = NotificationDAO.getNotificationsByClubId(user.getClubId(), user.getRole());
        request.setAttribute("notifications", notifications);
        request.getRequestDispatcher("view/notification.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String title = request.getParameter("title");
            String content = request.getParameter("message");
            String id = request.getParameter("clubId");
            int ids = Integer.parseInt(id);

            if (title != null && !title.trim().isEmpty() && content != null && !content.trim().isEmpty()) {
                Notification newNotification = new Notification();
                newNotification.setTitle(title);
                newNotification.setMessage(content);
                newNotification.setClubId(ids);

                NotificationDAO.addNotification(newNotification);
            } else {
                request.setAttribute("error", "Tiêu đề và nội dung không được để trống.");
            }
            
            response.sendRedirect("Notifications");
        } else if ("edit".equals(action)) {
            handleUpdateNotification(request, response);
        } else {
            response.sendRedirect("Notifications");
        }
    }

    private void handleDeleteNotification(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("notificationID");
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        // Kiểm tra nếu user chưa đăng nhập, chuyển hướng đến trang đăng nhập
        if (user == null) {
            response.sendRedirect("Logins");
            return;
        }

        if (id != null && id.matches("\\d+")) {
            int notificationId = Integer.parseInt(id);
            boolean success = NotificationDAO.deleteNotification(notificationId);

            if (!success) {
                request.setAttribute("error", "Không tìm thấy ID cần xóa.");
            }
        } else {
            request.setAttribute("error", "ID không hợp lệ.");
        }

        // Load lại danh sách thông báo
        List<Notification> notifications = NotificationDAO.getNotificationsByClubId(user.getClubId(), user.getRole());
        request.setAttribute("notifications", notifications);
        request.getRequestDispatcher("view/notification.jsp").forward(request, response);
    }

    private void handleEditNotification(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("notificationID");

        if (id != null && id.matches("\\d+")) {
            int notificationId = Integer.parseInt(id);
            Notification notification = NotificationDAO.getNotificationById(notificationId);

            if (notification != null) {
                request.setAttribute("notification", notification);
                request.getRequestDispatcher("view/editNotification.jsp").forward(request, response);
                return;
            }
        }

        request.setAttribute("error", "Không tìm thấy thông báo cần chỉnh sửa.");
        request.getRequestDispatcher("view/notification.jsp").forward(request, response);
    }

    private void handleUpdateNotification(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("notificationID");
        String title = request.getParameter("title");
        String content = request.getParameter("message");
        String clubId = request.getParameter("clubId");

        if (id != null && id.matches("\\d+") && title != null && !title.trim().isEmpty() && content != null && !content.trim().isEmpty()) {
            int notificationId = Integer.parseInt(id);
            int clubIds = Integer.parseInt(clubId);
            Notification updatedNotification = new Notification();
            updatedNotification.setNotificationID(notificationId);
            updatedNotification.setTitle(title);
            updatedNotification.setMessage(content);
            updatedNotification.setClubId(clubIds);

            NotificationDAO.updateNotification(updatedNotification);
        } else {
            request.setAttribute("error", "Dữ liệu không hợp lệ.");
        }

        response.sendRedirect("Notifications");
    }
}
