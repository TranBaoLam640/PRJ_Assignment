/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.EventDAO;
import dao.TaskAssignmentDAO;
import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import model.Event;
import model.TaskAssignment;
import model.Users;

@WebServlet(name = "TaskServlet", urlPatterns = {"/Task"})
public class TaskServlet extends HttpServlet {

    private EventDAO eventDAO = new EventDAO();
    private TaskAssignmentDAO taskAssignmentDAO = new TaskAssignmentDAO();
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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

        ArrayList<TaskAssignment> assignments = taskAssignmentDAO.getDetailedTasksByEventId(clubId, isAdmin);
        ArrayList<Users> users = userDAO.getAllUsers(clubId, isAdmin);
        ArrayList<Event> events = eventDAO.getAllEvents(clubId, isAdmin);

        if (action != null) {
            if ("filterClub".equals(action)) {
                String clubIdParam = request.getParameter("clubId");
                if (clubIdParam != null && !clubIdParam.isEmpty()) {
                    try {
                        int filterClubId = Integer.parseInt(clubIdParam);
                        if (!isAdmin && filterClubId != clubId) {
                            request.setAttribute("message", "Bạn chỉ có thể lọc nhiệm vụ trong CLB của mình!");
                        } else {
                            assignments = taskAssignmentDAO.getTasksByClub(filterClubId);
                        }
                    } catch (NumberFormatException e) {
                        request.setAttribute("message", "ClubID không hợp lệ!");
                    }
                }
            } else if ("filterStatus".equals(action)) {
                String status = request.getParameter("status");
                if (status != null && !status.isEmpty()) {
                    assignments = taskAssignmentDAO.getTasksByStatus(status, clubId, isAdmin);
                }
            } else if ("search".equals(action)) {
                String keyword = request.getParameter("keyword");
                if (keyword != null && !keyword.isEmpty()) {
                    assignments = taskAssignmentDAO.searchTasks(keyword, clubId, isAdmin);
                }
            } else if ("edit".equals(action) && (role.equals("Chairman") || role.equals("ViceChairman") || role.equals("TeamLeader") || role.equals("Admin"))) {
                try {
                    int taskId = Integer.parseInt(request.getParameter("taskId"));
                    TaskAssignment task = taskAssignmentDAO.getTaskById(taskId);
                    if (task != null) {
                        Event event = eventDAO.getEventByID(task.getEventID());
                        if (!isAdmin && event.getClubId() != clubId) {
                            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền chỉnh sửa nhiệm vụ này!");
                            return;
                        }
                        request.setAttribute("taskToEdit", task);
                        request.setAttribute("users", users);
                        request.setAttribute("events", events);
                        RequestDispatcher rs = request.getRequestDispatcher("view/EditTask.jsp");
                        rs.forward(request, response);
                        return;
                    } else {
                        request.setAttribute("message", "Không tìm thấy nhiệm vụ!");
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("message", "ID nhiệm vụ không hợp lệ!");
                }
            }
        }

        request.setAttribute("assignments", assignments);
        request.setAttribute("users", users);
        request.setAttribute("events", events);
        request.setAttribute("currentUserRole", role);
        RequestDispatcher rs = request.getRequestDispatcher("view/Task.jsp");
        rs.forward(request, response);
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
        String role = user.getRole();
        int clubId = user.getClubId();
        boolean isAdmin = role.equals("Admin");
        String action = request.getParameter("action");

        ArrayList<TaskAssignment> assignments = taskAssignmentDAO.getDetailedTasksByEventId(clubId, isAdmin);
        ArrayList<Users> users = userDAO.getAllUsers(clubId, isAdmin);
        ArrayList<Event> events = eventDAO.getAllEvents(clubId, isAdmin);

        if (action != null) {
            if ("assignRole".equals(action) && (role.equals("Chairman") || role.equals("ViceChairman") || role.equals("Admin") || role.equals("TeamLeader"))) {
                try {
                    int eventId = Integer.parseInt(request.getParameter("eventId"));
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    String roleName = request.getParameter("roleName");

                    Event event = eventDAO.getEventByID(eventId);
                    Users assignedUser = userDAO.getUserByID(userId);
                    if (!isAdmin && (event.getClubId() != clubId || assignedUser.getClubId() != clubId)) {
                        request.setAttribute("message", "Bạn chỉ có thể phân công nhiệm vụ cho sự kiện và thành viên trong CLB của mình!");
                        request.setAttribute("assignments", assignments);
                        request.setAttribute("users", users);
                        request.setAttribute("events", events);
                        request.setAttribute("currentUserRole", role);
                        RequestDispatcher rs = request.getRequestDispatcher("view/Task.jsp");
                        rs.forward(request, response);
                        return;
                    }

                    if (roleName == null || roleName.trim().isEmpty()) {
                        request.setAttribute("message", "Vai trò không được để trống!");
                    } else if (!userDAO.checkExist(userId)) {
                        request.setAttribute("message", "User ID " + userId + " không tồn tại!");
                    } else {
                        boolean isAssigned = taskAssignmentDAO.assignTask(eventId, userId, roleName);
                        request.setAttribute("message", isAssigned ? "Phân công nhiệm vụ thành công!" : "Phân công nhiệm vụ thất bại!");
                        assignments = taskAssignmentDAO.getDetailedTasksByEventId(clubId, isAdmin);
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("message", "ID không hợp lệ!");
                }
            } else if ("delete".equals(action) && (role.equals("Chairman") || role.equals("ViceChairman") || role.equals("Admin"))) {
                try {
                    int taskId = Integer.parseInt(request.getParameter("taskId"));
                    TaskAssignment task = taskAssignmentDAO.getTaskById(taskId);
                    Event event = eventDAO.getEventByID(task.getEventID());
                    if (!isAdmin && event.getClubId() != clubId) {
                        request.setAttribute("message", "Bạn không có quyền xóa nhiệm vụ này!");
                        request.setAttribute("assignments", assignments);
                        request.setAttribute("users", users);
                        request.setAttribute("events", events);
                        request.setAttribute("currentUserRole", role);
                        RequestDispatcher rs = request.getRequestDispatcher("view/Task.jsp");
                        rs.forward(request, response);
                        return;
                    }
                    boolean isDeleted = taskAssignmentDAO.deleteTask(taskId);
                    request.setAttribute("message", isDeleted ? "Xóa nhiệm vụ thành công!" : "Xóa nhiệm vụ thất bại!");
                    assignments = taskAssignmentDAO.getDetailedTasksByEventId(clubId, isAdmin);
                } catch (NumberFormatException e) {
                    request.setAttribute("message", "ID nhiệm vụ không hợp lệ!");
                }
            } else if ("update".equals(action) && (role.equals("Chairman") || role.equals("ViceChairman") || role.equals("TeamLeader") || role.equals("Admin"))) {
                try {
                    int taskId = Integer.parseInt(request.getParameter("taskId"));
                    int eventId = Integer.parseInt(request.getParameter("eventId"));
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    String taskDescription = request.getParameter("taskDescription");
                    String status = request.getParameter("status");

                    Event event = eventDAO.getEventByID(eventId);
                    Users assignedUser = userDAO.getUserByID(userId);
                    if (!isAdmin && (event.getClubId() != clubId || assignedUser.getClubId() != clubId)) {
                        request.setAttribute("message", "Bạn chỉ có thể cập nhật nhiệm vụ cho sự kiện và thành viên trong CLB của mình!");
                        TaskAssignment task = taskAssignmentDAO.getTaskById(taskId);
                        request.setAttribute("taskToEdit", task);
                        request.setAttribute("users", users);
                        request.setAttribute("events", events);
                        RequestDispatcher rs = request.getRequestDispatcher("view/editTask.jsp");
                        rs.forward(request, response);
                        return;
                    }

                    if (taskDescription == null || taskDescription.trim().isEmpty()) {
                        request.setAttribute("message", "Nhiệm vụ không được để trống!");
                        TaskAssignment task = taskAssignmentDAO.getTaskById(taskId);
                        request.setAttribute("taskToEdit", task);
                        request.setAttribute("users", users);
                        request.setAttribute("events", events);
                        RequestDispatcher rs = request.getRequestDispatcher("view/editTask.jsp");
                        rs.forward(request, response);
                        return;
                    }

                    boolean isUpdated = taskAssignmentDAO.updateTask(taskId, eventId, userId, taskDescription.trim(), status);
                    request.setAttribute("message", isUpdated ? "Cập nhật nhiệm vụ thành công!" : "Cập nhật nhiệm vụ thất bại!");
                    TaskAssignment updatedTask = taskAssignmentDAO.getTaskById(taskId);
                    request.setAttribute("taskToEdit", updatedTask);
                    request.setAttribute("users", users);
                    request.setAttribute("events", events);
                    RequestDispatcher rs = request.getRequestDispatcher("view/EditTask.jsp");
                    rs.forward(request, response);
                    return;

                } catch (NumberFormatException e) {
                    request.setAttribute("message", "Dữ liệu không hợp lệ!");
                }
            }
        }

        request.setAttribute("assignments", assignments);
        request.setAttribute("users", users);
        request.setAttribute("events", events);
        request.setAttribute("currentUserRole", role);
        RequestDispatcher rs = request.getRequestDispatcher("view/Task.jsp");
        rs.forward(request, response);
    }
}
