package dao;

import model.Notification;
import dao.DBContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {

    // Lấy danh sách tất cả thông báo
    public static List<Notification> getAllNotifications() {
        List<Notification> notifications = new ArrayList<>();
        DBContext db = DBContext.getInstance();

        try {
            String query = "SELECT * FROM Notifications ORDER BY CreatedDate DESC";
            PreparedStatement statement = db.getConnection().prepareStatement(query);//3 cbi cau lenh sql
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Notification notification = new Notification();
                notification.setNotificationID(rs.getInt("NotificationID"));
                notification.setTitle(rs.getString("Title"));
                notification.setMessage(rs.getString("Message"));
                notification.setCreatedDate(rs.getString("CreatedDate"));
                notification.setClubId(rs.getInt("ClubID"));
                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    // Lấy danh sách thông báo theo ID CLB
    public static List<Notification> getNotificationsByClub(int clubId) {
        List<Notification> notifications = new ArrayList<>();
        DBContext db = DBContext.getInstance();

        try {
            String query = "SELECT * FROM Notifications WHERE ClubID = ? ORDER BY CreatedDate DESC";
            PreparedStatement stmt = db.getConnection().prepareStatement(query);
            stmt.setInt(1, clubId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Notification notification = new Notification();
                notification.setNotificationID(rs.getInt("NotificationID"));
                notification.setTitle(rs.getString("Title"));
                notification.setMessage(rs.getString("Message"));
                notification.setCreatedDate(rs.getString("CreatedDate"));
                notification.setClubId(rs.getInt("ClubID"));
                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }
    // Lấy danh sách thông báo theo ClubID

   public static List<Notification> getNotificationsByClubId(int clubID, String role) {
    List<Notification> notifications = new ArrayList<>();
    DBContext db = DBContext.getInstance();

    try {
        String query;
        PreparedStatement stmt;

        if ("Admin".equalsIgnoreCase(role)) {
            // Admin lấy tất cả thông báo không lọc theo ClubID
            query = "SELECT * FROM Notifications ORDER BY CreatedDate DESC";
            stmt = db.getConnection().prepareStatement(query);
        } else {
            // Thành viên CLB chỉ lấy thông báo theo ClubID
            query = "SELECT * FROM Notifications WHERE ClubID = ? ORDER BY CreatedDate DESC";
            stmt = db.getConnection().prepareStatement(query);
            stmt.setInt(1, clubID);
        }

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Notification notification = new Notification();
            notification.setNotificationID(rs.getInt("NotificationID"));
            notification.setTitle(rs.getString("Title"));
            notification.setMessage(rs.getString("Message"));
            notification.setCreatedDate(rs.getString("CreatedDate"));
            notification.setClubId(rs.getInt("ClubID"));
            notifications.add(notification);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return notifications;
}


    // Lấy thông báo theo ID
    public static Notification getNotificationById(int notificationID) {
        List<Notification> notifications = new ArrayList<>();
        DBContext db = DBContext.getInstance();

        try {
            String query = "SELECT * FROM Notifications WHERE NotificationID = ?";
            PreparedStatement stmt = db.getConnection().prepareStatement(query);
            stmt.setInt(1, notificationID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Notification notification = new Notification();
                notification.setNotificationID(rs.getInt("NotificationID"));
                notification.setTitle(rs.getString("Title"));
                notification.setMessage(rs.getString("Message"));
                notification.setCreatedDate(rs.getString("CreatedDate"));
                notification.setClubId(rs.getInt("ClubID"));
                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications.get(0);
    }

    // Thêm thông báo mới
    public static boolean addNotification(Notification notification) {

        DBContext db = DBContext.getInstance();
        try {
            String query = "INSERT INTO Notifications (Title, Message, CreatedDate, ClubID) VALUES (?, ?, GETDATE(), ?)";
            PreparedStatement stmt = db.getConnection().prepareStatement(query);
            stmt.setString(1, notification.getTitle());
            stmt.setString(2, notification.getMessage());
            stmt.setInt(3, notification.getClubId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa thông báo theo ID
    public static boolean deleteNotification(int notificationID) {
        DBContext db = DBContext.getInstance();

        try {
            String query = "DELETE FROM Notifications WHERE NotificationID = ?";
            PreparedStatement stmt = db.getConnection().prepareStatement(query);
            stmt.setInt(1, notificationID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật thông báo
    public static boolean updateNotification(Notification notification) {

        DBContext db = DBContext.getInstance();
        try {
            String sql = "UPDATE Notifications SET title = ?, message = ?, clubId = ? WHERE notificationID = ?";
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            stmt.setString(1, notification.getTitle());
            stmt.setString(2, notification.getMessage());
            stmt.setInt(3, notification.getClubId());
            stmt.setInt(4, notification.getNotificationID());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
