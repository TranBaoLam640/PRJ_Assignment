package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Admin
 */
import java.util.Date;
import java.util.List;
import javax.management.Notification;
import org.apache.catalina.User;

public class Club {

    private int clubID;
    private String clubName;
    private String description;
    private Date establishedDate;
    private List<User> members;
    private List<Event> events;
    private List<Notification> notifications;
    private List<Report> reports;

    public Club(int clubID, String clubName, String description, Date establishedDate) {
        this.clubID = clubID;
        this.clubName = clubName;
        this.description = description;
        this.establishedDate = establishedDate;
    }

    public Club(int clubID, String clubName, String description, Date establishedDate, List<User> members, List<Event> events, List<Notification> notifications, List<Report> reports) {
        this.clubID = clubID;
        this.clubName = clubName;
        this.description = description;
        this.establishedDate = establishedDate;
        this.members = members;
        this.events = events;
        this.notifications = notifications;
        this.reports = reports;
    }

    public Club() {
    }
    
    

    public int getClubID() {
        return clubID;
    }

    public String getClubName() {
        return clubName;
    }

    public String getDescription() {
        return description;
    }

    public Date getEstablishedDate() {
        return establishedDate;
    }

    public List<User> getMembers() {
        return members;
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setClubID(int clubID) {
        this.clubID = clubID;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEstablishedDate(Date establishedDate) {
        this.establishedDate = establishedDate;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    
}
