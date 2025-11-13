/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.TaskAssignment;
import dao.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Event;
import model.Users;

/**
 *
 * @author Admin
 */
public class ActivityDAO {
// Thêm phân công nhiệm vụ

    public boolean addTaskAssignment(TaskAssignment task) {
        DBContext db = DBContext.getInstance();
        int rowsAffected = 0;
        try {
            String sql = """
                         INSERT INTO TaskAssignments (EventID, AssignedUserID, TaskDescription, Status)
                         VALUES (?, ?, ?, ?)
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, task.getEvent().getEventID());
            statement.setInt(2, task.getAssignedUser().getUserID());
            statement.setString(3, task.getTaskDescription());
            statement.setString(4, task.getStatus());
            rowsAffected = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected > 0;
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
    public ArrayList<TaskAssignment> getTaskAssignmentsByEvent(int eventID) {
        ArrayList<TaskAssignment> tasks = new ArrayList<>();
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                         SELECT * FROM TaskAssignments WHERE EventID = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, eventID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                TaskAssignment task = new TaskAssignment();
                task.setTaskID(rs.getInt("TaskID"));

                // Tạo đối tượng Event và gán ID
                Event event = new Event();
                event.setEventID(rs.getInt("EventID"));
                task.setEvent(event);

                // Tạo đối tượng Users và gán ID
                Users assignedUser = new Users();
                assignedUser.setUserID(rs.getInt("AssignedUserID"));
                task.setAssignedUser(assignedUser);

                task.setTaskDescription(rs.getString("TaskDescription"));
                task.setStatus(rs.getString("Status"));

                tasks.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    // Lấy danh sách nhiệm vụ theo người dùng
    public List<TaskAssignment> getTaskAssignmentsByUser(int userID) {
        List<TaskAssignment> tasks = new ArrayList<>();
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                         SELECT * FROM TaskAssignments WHERE AssignedUserID = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, userID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                TaskAssignment task = new TaskAssignment();
                task.setTaskID(rs.getInt("TaskID"));

                // Tạo đối tượng Event và gán ID
                Event event = new Event();
                event.setEventID(rs.getInt("EventID"));
                task.setEvent(event);

                // Tạo đối tượng Users và gán ID
                Users assignedUser = new Users();
                assignedUser.setUserID(rs.getInt("AssignedUserID"));
                task.setAssignedUser(assignedUser);

                task.setTaskDescription(rs.getString("TaskDescription"));
                task.setStatus(rs.getString("Status"));

                tasks.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
