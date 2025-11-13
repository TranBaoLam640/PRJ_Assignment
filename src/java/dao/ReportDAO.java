/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Report;
import dao.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ReportDAO {

    // Thêm báo cáo mới
    public static boolean addReport(Report report) {
        DBContext db = DBContext.getInstance();
        int rowsAffected = 0;
        try {
            String sql = """
                         INSERT INTO Reports (ClubID, Semester, MemberChanges, EventSummary, ParticipationStats)
                         VALUES (?, ?, ?, ?, ?)
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, report.getClubId());
            statement.setString(2, report.getSemester());
            statement.setString(3, report.getMemberChanges());
            statement.setString(4, report.getEventSummary());
            statement.setString(5, report.getParticipationStats());
            rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean updateReport(Report report) {
        DBContext db = DBContext.getInstance();
        int rowsAffected = 0;
        try {
            String sql = """
                         UPDATE Reports SET Semester = ?, MemberChanges = ?, EventSummary = ?, ParticipationStats = ? WHERE ReportID = ? AND ClubID = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, report.getSemester());
            statement.setString(2, report.getMemberChanges());
            statement.setString(3, report.getEventSummary());
            statement.setString(4, report.getParticipationStats());
            statement.setInt(5, report.getReportID());
            statement.setInt(6, report.getClubId());
            rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    
//    public static void main(String[] args) {
//        
//        boolean success = updateReport(new Report(45,2, "Spring 2025", "Thêm 5 người", "Tổ chức 3 sự kiện", "80% người tham gia"));
//        System.out.println(success);
//    }

    // Lấy danh sách báo cáo theo ClubID
    public ArrayList<Report> getReportsByClub(int clubID) {
        ArrayList<Report> reports = new ArrayList<>();
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                         SELECT * FROM Reports WHERE ClubID = ? ORDER BY CreatedDate DESC
                        """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, clubID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Report report = new Report(
                        rs.getInt("ReportID"),
                        rs.getInt("ClubID"),
                        rs.getString("Semester"),
                        rs.getString("MemberChanges"),
                        rs.getString("EventSummary"),
                        rs.getString("ParticipationStats"),
                        rs.getString("CreatedDate")
                );
                reports.add(report);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reports;
    }

    // Lấy báo cáo theo ReportID
    public Report getReportByID(int reportID) {
        DBContext db = DBContext.getInstance();
        ArrayList<Report> reports = new ArrayList<>();
        try {
            String sql = """
                         SELECT * FROM Reports WHERE ReportID = ?
                        """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, reportID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Report report = new Report(
                        rs.getInt("ReportID"),
                        rs.getInt("ClubID"),
                        rs.getString("Semester"),
                        rs.getString("MemberChanges"),
                        rs.getString("EventSummary"),
                        rs.getString("ParticipationStats"),
                        rs.getString("CreatedDate"));
                reports.add(report);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reports.get(0);
    }

    public static Report getReportsByClubIDAndSemester(int clubId, String semester) {
        DBContext db = DBContext.getInstance();
        Report report = null; // Khởi tạo giá trị mặc định
        try {
            String sql = """
                     SELECT reportID, clubId, semester, memberChanges, eventSummary, participationStats, createdDate 
                     FROM Reports 
                     WHERE clubId = ? AND Semester = ?
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, clubId);
            statement.setString(2, semester);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) { // Kiểm tra nếu có dữ liệu
                report = new Report();
                report.setReportID(rs.getInt("ReportID"));
                report.setClubId(rs.getInt("ClubID"));
                report.setSemester(rs.getString("Semester"));
                report.setMemberChanges(rs.getString("MemberChanges"));
                report.setEventSummary(rs.getString("EventSummary"));
                report.setParticipationStats(rs.getString("ParticipationStats"));
                report.setCreatedDate(rs.getString("CreatedDate"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return report; // Trả về null nếu không có dữ liệu
    }

    public static List<Report> getReportsByClubID(int clubId) {
        DBContext db = DBContext.getInstance();
        ArrayList<Report> reports = new ArrayList<>();
        try {
            String sql = """
                         SELECT reportID, clubId, semester, memberChanges, eventSummary, participationStats, createdDate 
                         FROM Reports 
                         WHERE clubId = ?
                        """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, clubId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Report report = new Report();
                report.setReportID(rs.getInt("ReportID"));
                report.setClubId(rs.getInt("ClubID"));
                report.setSemester(rs.getString("Semester"));
                report.setMemberChanges(rs.getString("MemberChanges"));
                report.setEventSummary(rs.getString("EventSummary"));
                report.setParticipationStats(rs.getString("ParticipationStats"));
                report.setCreatedDate(rs.getString("CreatedDate"));

                reports.add(report);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return reports;
    }
    
}
