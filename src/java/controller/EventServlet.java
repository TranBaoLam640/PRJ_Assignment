/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.EventDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.*;

public class EventServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EventServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EventServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    EventDAO eventDAO = new EventDAO();

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
        RequestDispatcher rs;
        switch (action) {
            case "add":
                if (role.equals("Chairman") || role.equals("Admin")) {
                    rs = request.getRequestDispatcher("view/AddEvent.jsp");
                    rs.forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền thêm sự kiện!");
                }
                break;
            case "edit":
                if (role.equals("Chairman") || role.equals("ViceChairman") || role.equals("TeamLeader") || role.equals("Admin")) {
                    int eventId = Integer.parseInt(request.getParameter("id"));
                    Event event = eventDAO.getEventByID(eventId);
                    if (event == null) {
                        request.setAttribute("message", "Không tìm thấy sự kiện với ID: " + eventId);
                        ArrayList<Event> events = eventDAO.getAllEvents(clubId, isAdmin);
                        request.setAttribute("events", events);
                        rs = request.getRequestDispatcher("view/EventList.jsp");
                        rs.forward(request, response);
                        break;
                    }
                    if (!isAdmin && event.getClubId() != clubId) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền chỉnh sửa sự kiện này!");
                        return;
                    }
                    request.setAttribute("event", event);
                    rs = request.getRequestDispatcher("view/EditEvent.jsp");
                    rs.forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền chỉnh sửa!");
                }
                break;
            case "delete":
                if (role.equals("Chairman") || role.equals("Admin")) {
                    int eventId = Integer.parseInt(request.getParameter("id"));
                    Event event = eventDAO.getEventByID(eventId);
                    if (event == null) {
                        request.setAttribute("message", "Không tìm thấy sự kiện với ID: " + eventId);
                        ArrayList<Event> events = eventDAO.getAllEvents(clubId, isAdmin);
                        request.setAttribute("events", events);
                        rs = request.getRequestDispatcher("view/EventList.jsp");
                        rs.forward(request, response);
                        break;
                    }
                    if (!isAdmin && event.getClubId() != clubId) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền xóa sự kiện này!");
                        return;
                    }
                    eventDAO.deleteEvent(eventId);
                    response.sendRedirect("Event");
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền xóa sự kiện!");
                }
                break;
            case "search":
                String keyword = request.getParameter("keyword");
                ArrayList<Event> events = eventDAO.searchEvent(keyword, clubId, isAdmin);
                if (events.isEmpty() && keyword != null && !keyword.trim().isEmpty()) {
                    request.setAttribute("message", "Không tìm thấy sự kiện nào với từ khóa: " + keyword);
                }
                request.setAttribute("events", events);
                request.setAttribute("keyword", keyword);
                rs = request.getRequestDispatcher("view/EventList.jsp");
                rs.forward(request, response);
                break;
            case "detail":
                int eventId = Integer.parseInt(request.getParameter("id"));
                Event event = eventDAO.getEventByID(eventId);
                if (event == null) {
                    request.setAttribute("message", "Không tìm thấy sự kiện với ID: " + eventId);
                    events = eventDAO.getAllEvents(clubId, isAdmin);
                    request.setAttribute("events", events);
                    rs = request.getRequestDispatcher("view/EventList.jsp");
                    rs.forward(request, response);
                    break;
                }
                if (!isAdmin && event.getClubId() != clubId) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền xem chi tiết sự kiện này!");
                    return;
                }
                ArrayList<EventParticipant> participants = eventDAO.getParticipantsByEventId(eventId);
                int registeredCount = eventDAO.getParticipantCount(eventId, "REGISTERED");
                int attendedCount = eventDAO.getParticipantCount(eventId, "ATTENDED");
                int absentCount = eventDAO.getParticipantCount(eventId, "ABSENT");
                event.setRegisteredCount(registeredCount);
                event.setAttendedCount(attendedCount);
                event.setAbsentCount(absentCount);
                request.setAttribute("event", event);
                request.setAttribute("participants", participants);
                rs = request.getRequestDispatcher("view/EventDetail.jsp");
                rs.forward(request, response);
                break;
            case "register":
                ArrayList<Event> clubEvents = eventDAO.getAllEvents(clubId, isAdmin);
                request.setAttribute("events", clubEvents);
                ArrayList<EventParticipant> activities = eventDAO.getActivityHistory(user.getUserID());
                Map<Integer, Boolean> registeredEvents = new HashMap<>();
                for (EventParticipant activity : activities) {
                    registeredEvents.put(activity.getEventId(), true);
                }
                request.setAttribute("registeredEvents", registeredEvents);
                rs = request.getRequestDispatcher("view/RegisterEvent.jsp");
                rs.forward(request, response);
                break;
            case "list":
            default:
                ArrayList<Event> allEvents = eventDAO.getAllEvents(clubId, isAdmin);
                if (allEvents != null) {
                    for (Event selectedEvent : allEvents) {
                        registeredCount = eventDAO.getParticipantCount(selectedEvent.getEventID(), "REGISTERED");
                        attendedCount = eventDAO.getParticipantCount(selectedEvent.getEventID(), "ATTENDED");
                        absentCount = eventDAO.getParticipantCount(selectedEvent.getEventID(), "ABSENT");
                        selectedEvent.setRegisteredCount(registeredCount);
                        selectedEvent.setAttendedCount(attendedCount);
                        selectedEvent.setAbsentCount(absentCount);
                    }
                }
                request.setAttribute("events", allEvents);
                rs = request.getRequestDispatcher("view/EventList.jsp");
                rs.forward(request, response);
                break;
        }
    }

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
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            if (action.equals("add") && (role.equals("Chairman") || role.equals("Admin"))) {
                java.util.Date utilDate = dateFormat.parse(request.getParameter("EventDate"));
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                int eventClubId = Integer.parseInt(request.getParameter("clubId"));
                if (!isAdmin && eventClubId != clubId) {
                    request.setAttribute("error", "Bạn chỉ có thể thêm sự kiện cho CLB của mình!");
                    RequestDispatcher rs = request.getRequestDispatcher("view/AddEvent.jsp");
                    rs.forward(request, response);
                    return;
                }
                Event newEvent = new Event(
                        0,
                        eventClubId,
                        request.getParameter("EventName"),
                        request.getParameter("Description"),
                        sqlDate,
                        request.getParameter("Location"),
                        request.getParameter("Status")
                );

                boolean success = eventDAO.addEvent(newEvent);
                if (!success) {
                    request.setAttribute("error", "Thêm sự kiện thất bại!");
                    RequestDispatcher rs = request.getRequestDispatcher("view/AddEvent.jsp");
                    rs.forward(request, response);
                    return;
                }
            } else if (action.equals("update") && (role.equals("Chairman") || role.equals("ViceChairman") || role.equals("TeamLeader") || role.equals("Admin"))) {
                int eventId = Integer.parseInt(request.getParameter("eventID"));
                Event event = eventDAO.getEventByID(eventId);
                if (event == null) {
                    request.setAttribute("error", "Không tìm thấy sự kiện với ID: " + eventId);
                    ArrayList<Event> events = eventDAO.getAllEvents(clubId, isAdmin);
                    request.setAttribute("events", events);
                    RequestDispatcher rs = request.getRequestDispatcher("view/EventList.jsp");
                    rs.forward(request, response);
                    return;
                }
                if (!isAdmin && event.getClubId() != clubId) {
                    request.setAttribute("error", "Bạn không có quyền cập nhật sự kiện này!");
                    request.setAttribute("event", event);
                    RequestDispatcher rs = request.getRequestDispatcher("view/EditEvent.jsp");
                    rs.forward(request, response);
                    return;
                }
                java.util.Date utilDate = dateFormat.parse(request.getParameter("EventDate"));
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                int eventClubId = Integer.parseInt(request.getParameter("clubId"));
                if (!isAdmin && eventClubId != clubId) {
                    request.setAttribute("error", "Bạn chỉ có thể cập nhật sự kiện trong CLB của mình!");
                    request.setAttribute("event", event);
                    RequestDispatcher rs = request.getRequestDispatcher("view/EditEvent.jsp");
                    rs.forward(request, response);
                    return;
                }
                Event updateEvent = new Event(
                        eventId,
                        eventClubId,
                        request.getParameter("EventName"),
                        request.getParameter("Description"),
                        sqlDate,
                        request.getParameter("Location"),
                        request.getParameter("Status")
                );
                boolean success = eventDAO.updateEvent(updateEvent);
                if (!success) {
                    request.setAttribute("error", "Cập nhật sự kiện thất bại!");
                    request.setAttribute("event", updateEvent);
                    RequestDispatcher rs = request.getRequestDispatcher("view/EditEvent.jsp");
                    rs.forward(request, response);
                    return;
                }
            } else if (action.equals("register")) {
                String eventIdStr = request.getParameter("eventId");
                try {
                    int eventId = Integer.parseInt(eventIdStr);
                    Event event = eventDAO.getEventByID(eventId);
                    if (event == null) {
                        request.setAttribute("error", "Không tìm thấy sự kiện với ID: " + eventId);
                        doGet(request, response);
                        return;
                    }
                    if (!isAdmin && event.getClubId() != clubId) {
                        request.setAttribute("error", "Bạn chỉ có thể đăng ký sự kiện trong CLB của mình!");
                        doGet(request, response);
                        return;
                    }
                    boolean success = eventDAO.registerEvent(user.getUserID(), eventId);
                    if (success) {
                        request.setAttribute("success", "Đăng ký sự kiện thành công!");
                    } else {
                        request.setAttribute("error", "Bạn đã đăng ký sự kiện này rồi hoặc đăng ký thất bại!");
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "ID sự kiện không hợp lệ!");
                }
                doGet(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error: " + e.getMessage());
            ArrayList<Event> events = eventDAO.getAllEvents(clubId, isAdmin);
            request.setAttribute("events", events);
            RequestDispatcher rs = request.getRequestDispatcher("view/EventList.jsp");
            rs.forward(request, response);
            return;
        }
        response.sendRedirect("Event");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
