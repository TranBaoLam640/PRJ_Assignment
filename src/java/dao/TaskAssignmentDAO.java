/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 *
 * @author chang
 */
public class TaskAssignmentDAO {

    public TaskAssignment getTaskById(int taskId) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                         SELECT ta.*, u.FullName, e.EventName, c.ClubName 
                         FROM TaskAssignments ta 
                         JOIN Users u ON ta.AssignedUserID = u.UserID 
                         JOIN Events e ON ta.EventID = e.EventID 
                         JOIN Clubs c ON e.ClubID = c.ClubID 
                         WHERE ta.TaskID = ?
                            """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, taskId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                TaskAssignment task = new TaskAssignment(
                        rs.getInt("TaskID"),
                        rs.getInt("EventID"),
                        rs.getInt("AssignedUserID"),
                        rs.getString("TaskDescription"),
                        rs.getString("Status")
                );
                task.setFullName(rs.getString("FullName"));
                task.setEventName(rs.getString("EventName"));
                task.setClubName(rs.getString("ClubName"));
                return task;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Thêm phân công nhiệm vụ cho hoạt động
    public boolean assignTask(int eventId, int userId, String roleName) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                         INSERT INTO TaskAssignments (EventID, AssignedUserID, TaskDescription, Status) VALUES (?, ?, ?, ?)
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, eventId);
            statement.setInt(2, userId);
            statement.setString(3, roleName);
            statement.setString(4, "Completed");
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật trạng thái nhiệm vụ
    public boolean updateTaskStatus(int taskID, String status) {
        DBContext db = DBContext.getInstance();
        int rowsAffected = 0;
        try {
            String sql = """
                         UPDATE TaskAssignments SET Status = ? WHERE TaskID = ?
                        """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, status);
            statement.setInt(2, taskID);
            rowsAffected = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected > 0;
    }

    // Lấy danh sách nhiệm vụ theo sự kiện
    public ArrayList<TaskAssignment> getTasksByEventId(int eventId) {
        ArrayList<TaskAssignment> tasks = new ArrayList<>();
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                         SELECT * FROM TaskAssignments WHERE EventID = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, eventId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                TaskAssignment task = new TaskAssignment(
                        rs.getInt("TaskID"),
                        rs.getInt("ActivityID"),
                        rs.getInt("AssignedUserID"),
                        rs.getString("TaskDescription"),
                        rs.getString("Status")
                );
                tasks.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public ArrayList<TaskAssignment> getDetailedTasksByEventId(int clubId, boolean isAdmin) {
        DBContext db = DBContext.getInstance();
        ArrayList<TaskAssignment> tasks = new ArrayList<>();
        try {
            String sql = isAdmin
                    ? """
              SELECT ta.*, u.FullName, e.EventName, c.ClubName 
              FROM TaskAssignments ta 
              JOIN Users u ON ta.AssignedUserID = u.UserID 
              JOIN Events e ON ta.EventID = e.EventID 
              JOIN Clubs c ON e.ClubID = c.ClubID
              """
                    : """
              SELECT ta.*, u.FullName, e.EventName, c.ClubName 
              FROM TaskAssignments ta 
              JOIN Users u ON ta.AssignedUserID = u.UserID 
              JOIN Events e ON ta.EventID = e.EventID 
              JOIN Clubs c ON e.ClubID = c.ClubID 
              WHERE e.ClubID = ?
              """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            if (!isAdmin) {
                statement.setInt(1, clubId);
            }
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                TaskAssignment task = new TaskAssignment(
                        rs.getInt("TaskID"),
                        rs.getInt("EventID"),
                        rs.getInt("AssignedUserID"),
                        rs.getString("TaskDescription"),
                        rs.getString("Status")
                );
                task.setFullName(rs.getString("FullName")); // thêm FullName trong model
                task.setEventName(rs.getString("EventName")); // thêm eventName trong model
                task.setClubName(rs.getString("ClubName")); // thêmclubName trong model
                tasks.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    // Xóa nhiệm vụ
    public boolean deleteTask(int taskId) {
        DBContext db = DBContext.getInstance();
        Connection conn = null;
        try {
            String sql = "DELETE FROM TaskAssignments WHERE TaskID = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, taskId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa nhiệm vụ: " + e.getMessage());
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            return false;
        }
    }

    // Cập nhật nhiệm vụ
    public boolean updateTask(int taskId, int eventId, int userId, String taskDescription, String status) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = "UPDATE TaskAssignments SET EventID = ?, AssignedUserID = ?, TaskDescription = ?, Status = ? WHERE TaskID = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, eventId);
            statement.setInt(2, userId);
            statement.setString(3, taskDescription);
            statement.setString(4, status);
            statement.setInt(5, taskId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // Lọc theo clbn
    public ArrayList<TaskAssignment> getTasksByClub(int clubId) {
        DBContext db = DBContext.getInstance();
        ArrayList<TaskAssignment> tasks = new ArrayList<>();
        try {
            String sql = """
                         SELECT ta.*, u.FullName, e.EventName, c.ClubName 
                         FROM TaskAssignments ta 
                         JOIN Users u ON ta.AssignedUserID = u.UserID 
                         JOIN Events e ON ta.EventID = e.EventID 
                         JOIN Clubs c ON e.ClubID = c.ClubID 
                         WHERE e.ClubID = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, clubId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                TaskAssignment task = new TaskAssignment(
                        rs.getInt("TaskID"),
                        rs.getInt("EventID"),
                        rs.getInt("AssignedUserID"),
                        rs.getString("TaskDescription"),
                        rs.getString("Status")
                );
                task.setFullName(rs.getString("FullName"));
                task.setEventName(rs.getString("EventName"));
                task.setClubName(rs.getString("ClubName"));
                tasks.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    // Lọc theo trạng thái
    public ArrayList<TaskAssignment> getTasksByStatus(String status, int clubId, boolean isAdmin) {
        DBContext db = DBContext.getInstance();
        ArrayList<TaskAssignment> tasks = new ArrayList<>();
        try {
            String sql = isAdmin
                    ? """
              SELECT ta.*, u.FullName, e.EventName, c.ClubName 
              FROM TaskAssignments ta 
              JOIN Users u ON ta.AssignedUserID = u.UserID 
              JOIN Events e ON ta.EventID = e.EventID 
              JOIN Clubs c ON e.ClubID = c.ClubID 
              WHERE ta.Status = ?
              """
                    : """
              SELECT ta.*, u.FullName, e.EventName, c.ClubName 
              FROM TaskAssignments ta 
              JOIN Users u ON ta.AssignedUserID = u.UserID 
              JOIN Events e ON ta.EventID = e.EventID 
              JOIN Clubs c ON e.ClubID = c.ClubID 
              WHERE ta.Status = ? AND e.ClubID = ?
              """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, status);
            if (!isAdmin) {
                statement.setInt(2, clubId);
            }
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                TaskAssignment task = new TaskAssignment(
                        rs.getInt("TaskID"),
                        rs.getInt("EventID"),
                        rs.getInt("AssignedUserID"),
                        rs.getString("TaskDescription"),
                        rs.getString("Status")
                );
                task.setFullName(rs.getString("FullName"));
                task.setEventName(rs.getString("EventName"));
                task.setClubName(rs.getString("ClubName"));
                tasks.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    // Tìm kiếm 
    public ArrayList<TaskAssignment> searchTasks(String keyword, int clubId, boolean isAdmin) {
        DBContext db = DBContext.getInstance();
        ArrayList<TaskAssignment> tasks = new ArrayList<>();
        try {
            String sql = isAdmin
                    ? """
              SELECT ta.*, u.FullName, e.EventName, c.ClubName 
              FROM TaskAssignments ta 
              JOIN Users u ON ta.AssignedUserID = u.UserID 
              JOIN Events e ON ta.EventID = e.EventID 
              JOIN Clubs c ON e.ClubID = c.ClubID 
              WHERE u.FullName LIKE ? OR ta.TaskDescription LIKE ? OR e.EventName LIKE ?
              """
                    : """
              SELECT ta.*, u.FullName, e.EventName, c.ClubName 
              FROM TaskAssignments ta 
              JOIN Users u ON ta.AssignedUserID = u.UserID 
              JOIN Events e ON ta.EventID = e.EventID 
              JOIN Clubs c ON e.ClubID = c.ClubID 
              WHERE (u.FullName LIKE ? OR ta.TaskDescription LIKE ? OR e.EventName LIKE ?) AND e.ClubID = ?
              """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setString(3, "%" + keyword + "%");
            if (!isAdmin) {
                statement.setInt(4, clubId);
            }
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                TaskAssignment task = new TaskAssignment(
                        rs.getInt("TaskID"),
                        rs.getInt("EventID"),
                        rs.getInt("AssignedUserID"),
                        rs.getString("TaskDescription"),
                        rs.getString("Status")
                );
                task.setFullName(rs.getString("FullName"));
                task.setEventName(rs.getString("EventName"));
                task.setClubName(rs.getString("ClubName"));
                tasks.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
