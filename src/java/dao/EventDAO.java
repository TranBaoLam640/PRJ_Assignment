/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Admin
 */
import model.Event;
import model.EventParticipant;
import model.EventFeedback;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Users;

public class EventDAO {

    // Thêm sự kiện mới
    public boolean addEvent(Event event) {
        DBContext db = DBContext.getInstance();
        int rowsAffected = 0;
        try {
            String sql = """
                         INSERT INTO Events (EventName, Description, EventDate, Location, ClubID, Status)
                         VALUES (?, ?, ?, ?, ?, ?)
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, event.getEventName());
            statement.setString(2, event.getDescription());
            statement.setDate(3, (Date) event.getEventDate());
            statement.setString(4, event.getLocation());
            statement.setInt(5, event.getClubId());
            statement.setString(6, "Upcoming");
            rowsAffected = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected > 0;
    }

    // Cập nhật sự kiện
    public boolean updateEvent(Event event) {
        DBContext db = DBContext.getInstance();
        int rowsAffected = 0;
        try {
            String sql = """
                         UPDATE Events SET EventName = ?, Description = ?, EventDate = ?, Location = ?
                         WHERE EventID = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, event.getEventName());
            statement.setString(2, event.getDescription());
            statement.setDate(3, (Date) event.getEventDate());
            statement.setString(4, event.getLocation());
            statement.setInt(5, event.getEventID());
            rowsAffected = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected > 0;
    }

    public boolean deleteEvent(int eventID) {
        DBContext db = DBContext.getInstance();
        int rowsAffected = 0;
        try {
            String sqlParticipants = "DELETE FROM EventParticipants WHERE EventID = ?";
            PreparedStatement stmtParticipants = db.getConnection().prepareStatement(sqlParticipants);
            stmtParticipants.setInt(1, eventID);
            stmtParticipants.executeUpdate();

            String sqlFeedback = "DELETE FROM EventFeedback WHERE EventID = ?";
            PreparedStatement stmtFeedback = db.getConnection().prepareStatement(sqlFeedback);
            stmtFeedback.setInt(1, eventID);
            stmtFeedback.executeUpdate();

            String sqlEvent = "DELETE FROM Events WHERE EventID = ?";
            PreparedStatement stmtEvent = db.getConnection().prepareStatement(sqlEvent);
            stmtEvent.setInt(1, eventID);
            rowsAffected = stmtEvent.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected > 0;
    }

    // Tìm sự kiện
    public static ArrayList<Event> searchEvent(String keyword, int clubId, boolean isAdmin) {
        DBContext db = DBContext.getInstance();
        ArrayList<Event> events = new ArrayList<Event>();
        keyword = keyword.trim().toLowerCase();
        try {
            String sql = isAdmin
                    ? """
              SELECT * FROM Events 
              WHERE LOWER(EventName) LIKE ? 
                 OR CAST(EventID AS NVARCHAR(10)) LIKE ? 
                 OR LOWER(Location) LIKE ?
                 OR CAST(ClubID AS NVARCHAR(10)) LIKE ?
              """
                    : """
              SELECT * FROM Events 
              WHERE (LOWER(EventName) LIKE ? 
                 OR CAST(EventID AS NVARCHAR(10)) LIKE ? 
                 OR LOWER(Location) LIKE ?
                 OR CAST(ClubID AS NVARCHAR(10)) LIKE ?) 
                 AND ClubID = ?
              """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setString(3, "%" + keyword + "%");
            statement.setString(4, "%" + keyword + "%");
            if (!isAdmin) {
                statement.setInt(5, clubId);
            }
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Event event = new Event(
                        rs.getInt("EventID"),
                        rs.getInt("ClubID"),
                        rs.getString("EventName"),
                        rs.getString("Description"),
                        rs.getDate("EventDate"),
                        rs.getString("Location"),
                        rs.getString("Status")
                );
                events.add(event);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return events;
    }

    // Lấy sự kiện theo ID
    public Event getEventByID(int eventID) {
        DBContext db = DBContext.getInstance();
        Event event = null;
        try {
            String sql = """
                         SELECT * FROM Events WHERE EventID = ?
                        """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, eventID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                event = new Event(
                        rs.getInt("EventID"),
                        rs.getInt("ClubID"),
                        rs.getString("EventName"),
                        rs.getString("Description"),
                        rs.getDate("EventDate"),
                        rs.getString("Location"),
                        rs.getString("Status")
                );
                String storedStatus = rs.getString("Status");
                if (storedStatus.equals("Canceled")) {
                    event.setStatus("Canceled");
                } else {
                    java.sql.Date eventDate = (java.sql.Date) event.getEventDate();
                    java.util.Date currentDate = new java.util.Date();
                    java.sql.Date today = new java.sql.Date(currentDate.getTime());
                    if (eventDate.before(today)) {
                        event.setStatus("Completed");
                    } else if (eventDate.equals(today)) {
                        event.setStatus("Ongoing");
                    } else {
                        event.setStatus("Upcoming");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

    public String getEventNameByID(int eventID) {
        DBContext db = DBContext.getInstance();
        ArrayList<Event> event = new ArrayList<Event>();
        String eventName = null;
        try {
            String sql = """
                         SELECT EventName FROM Events WHERE EventID = ?
                        """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, eventID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                eventName = rs.getString("EventName");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventName;
    }

    // Lấy danh sách sự kiện
    public ArrayList<Event> getAllEvents(int clubId, boolean isAdmin) {
        DBContext db = DBContext.getInstance();
        ArrayList<Event> events = new ArrayList<Event>();
        try {
            String sql = isAdmin
                    ? "SELECT * FROM Events"
                    : "SELECT * FROM Events WHERE ClubID = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            if (!isAdmin) {
                statement.setInt(1, clubId);
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Event event = new Event(
                        rs.getInt("EventID"),
                        rs.getInt("ClubID"),
                        rs.getString("EventName"),
                        rs.getString("Description"),
                        rs.getDate("EventDate"),
                        rs.getString("Location"),
                        rs.getString("Status")
                );

                events.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }

    public boolean registerEvent(int userId, int eventId) {
        DBContext db = DBContext.getInstance();
        try {
            String checkSql = "SELECT COUNT(*) FROM EventParticipants WHERE EventID = ? AND UserID = ?";
            PreparedStatement checkStmt = db.getConnection().prepareStatement(checkSql);
            checkStmt.setInt(1, eventId);
            checkStmt.setInt(2, userId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return false;
            }

            String sql = "INSERT INTO EventParticipants (EventID, UserID, Status) VALUES (?, ?, ?)";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, eventId);
            statement.setInt(2, userId);
            statement.setString(3, "Registered");
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<EventParticipant> getActivityHistory(int userId) {
        DBContext db = DBContext.getInstance();
        ArrayList<EventParticipant> activities = new ArrayList<>();
        try {
            String sql = "SELECT * FROM EventParticipants WHERE UserID = ? AND Status = 'Registered'";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                EventParticipant participant = new EventParticipant();
                participant.setEventParticipantID(rs.getInt("EventParticipantID"));
                participant.setEventId(rs.getInt("EventID"));
                participant.setUserID(rs.getInt("UserID"));
                participant.setStatus(rs.getString("Status"));
                activities.add(participant);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activities;
    }

    // Lấy số lượng người tham gia sự kiện theo trạng thái
    public ArrayList<EventParticipant> getParticipantsByEventId(int eventId) {
        DBContext db = DBContext.getInstance();
        ArrayList<EventParticipant> participants = new ArrayList<>();
        try {
            String sql = """
                     SELECT ep.EventParticipantID, ep.EventID, ep.UserID, ep.Status, 
                            u.UserID, u.FullName, u.Role, e.ClubID
                     FROM EventParticipants ep
                     JOIN Users u ON ep.UserID = u.UserID
                     JOIN Events e ON ep.EventID = e.EventID
                     WHERE ep.EventID = ?
                     """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, eventId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                EventParticipant participant = new EventParticipant();
                participant.setEventParticipantID(rs.getInt("EventParticipantID"));
                participant.setEventId(rs.getInt("EventID"));
                participant.setClubId(rs.getInt("ClubID"));
                participant.setStatus(rs.getString("Status"));

                Users user = new Users();
                user.setUserID(rs.getInt("UserID"));
                user.setFullName(rs.getString("FullName"));
                user.setRole(rs.getString("Role"));
                participant.setUser(user);

                participants.add(participant);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return participants;
    }

    public int getParticipantCount(int eventId, String status) {
        DBContext db = DBContext.getInstance();
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) AS count FROM EventParticipants WHERE EventID = ? AND Status = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, eventId);
            statement.setString(2, status);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return count;
    }

    // Thêm đánh giá cho sự kiện
    public boolean addEventFeedback(EventFeedback feedback) {
        DBContext db = DBContext.getInstance();
        int rowsAffected = 0;
        try {
            String sql = """
                         INSERT INTO EventFeedback (EventID, UserID, Rating, Comment, CreatedDate)
                         VALUES (?, ?, ?, ?, ?)
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, feedback.getEvent().getEventID());
            statement.setInt(2, feedback.getUser().getUserID());
            statement.setInt(3, feedback.getRating());
            statement.setString(4, feedback.getComment());
            statement.setString(5, feedback.getCreatedDate());
            rowsAffected = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected > 0;
    }

}
