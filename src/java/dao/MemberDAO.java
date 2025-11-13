/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.MemberActivity;
import java.sql.PreparedStatement;//cbi cau lenh sql chay
import java.sql.ResultSet;
import model.Event;
import model.Users;

/**
 *
 * @author Admin
 */
public class MemberDAO {

    // Lấy danh sách hoạt động của thành viên theo UserID
    public List<MemberActivity> getMemberActivities(int userID) {
        DBContext db = DBContext.getInstance();
        List<MemberActivity> activities = new ArrayList<>();
        try {
            String sql = "SELECT * FROM MemberActivities WHERE UserID = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, userID);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                MemberActivity activity = new MemberActivity(
                        rs.getInt("ActivityID"),
                        rs.getString("ParticipationLevel"),
                        rs.getInt("UserID"),
                        rs.getInt("EventID")
                );
                activities.add(activity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activities;
    }

    public String classifyMember(int userID) {
        int totalEvents = getTotalEvents();
        int attendedEvents = getAttendedEvents(userID);

        if (totalEvents == 0) {
            return "Chưa có sự kiện";
        }

        double participationRate = (double) attendedEvents / totalEvents * 100;

        if (participationRate > 80) {
            return "Tích cực";
        } else if (participationRate >= 50) {
            return "Bình thường";
        } else {
            return "Không tích cực";
        }
    }

    private int getTotalEvents() {
        DBContext db = DBContext.getInstance();
        int total = 0;
        try {
            String sql = """
                         SELECT COUNT(*) FROM Events
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    private int getAttendedEvents(int userID) {
        DBContext db = DBContext.getInstance();
        int attended = 0;
        try {
            String sql = """
                         SELECT COUNT(*) FROM EventParticipants WHERE UserID = ? AND ParticipationLevel = 'Attended'
                        """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, userID);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                attended = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attended;
    }
}
    