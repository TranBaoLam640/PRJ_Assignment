/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
import dao.ClubDAO;
import java.util.Date;

public class Report {
    private int reportID;
    private int clubId;
    private Club club;
    private String semester;
    private String memberChanges;
    private String eventSummary;
    private String participationStats;
    private String createdDate;
    
    // Getters and Setters

    public Report(int reportID,  int clubId, String semester, String memberChanges, String eventSummary, String participationStats, String createdDate) {
        this.reportID = reportID;
        this.semester = semester;
        this.memberChanges = memberChanges;
        this.eventSummary = eventSummary;
        this.participationStats = participationStats;
        this.createdDate = createdDate;
        this.clubId = clubId;
    }

    public Report(int reportID, int clubId, String semester, String memberChanges, String eventSummary, String participationStats) {
        this.reportID = reportID;
        this.clubId = clubId;
        this.semester = semester;
        this.memberChanges = memberChanges;
        this.eventSummary = eventSummary;
        this.participationStats = participationStats;
    }
    
    

    public Report(int clubId, String semester, String memberChanges, String eventSummary, String participationStats) {
        this.clubId = clubId;
        this.semester = semester;
        this.memberChanges = memberChanges;
        this.eventSummary = eventSummary;
        this.participationStats = participationStats;
        this.createdDate = createdDate;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public Report() {
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public Club getClub() {
        return club;
    }

    public void setClub() {
        this.club = ClubDAO.getClubByID(clubId);
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getMemberChanges() {
        return memberChanges;
    }

    public void setMemberChanges(String memberChanges) {
        this.memberChanges = memberChanges;
    }

    public String getEventSummary() {
        return eventSummary;
    }

    public void setEventSummary(String eventSummary) {
        this.eventSummary = eventSummary;
    }

    public String getParticipationStats() {
        return participationStats;
    }

    public void setParticipationStats(String participationStats) {
        this.participationStats = participationStats;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    
    
    
}
