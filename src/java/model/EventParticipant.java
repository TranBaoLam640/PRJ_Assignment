package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class EventParticipant {
    private int eventParticipantID;
    private int eventId;
    private int clubId;
    private int userID;
    private Event event;
    private Users user;
    private String status;
    

    public EventParticipant(int eventParticipantID, String status, int eventId, int clubId, int userID) {
        this.eventParticipantID = eventParticipantID;
        this.status = status;
        this.eventId = eventId;
        this.clubId = clubId;
        this.userID = userID;
    }

    public EventParticipant() {
    }
    
    

    public int getEventParticipantID() {
        return eventParticipantID;
    }

    public void setEventParticipantID(int eventParticipantID) {
        this.eventParticipantID = eventParticipantID;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }
    
    

    
    
}