package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class MemberActivity {
    private int activityID;
    private int userId;
    private int eventId;
    private Users user;
    private Event event;
    private String participationLevel;

    public MemberActivity(int activityID, int userId, int eventId, String participationLevel) {
        this.activityID = activityID;
        this.userId = userId;
        this.eventId = eventId;
        this.participationLevel = participationLevel;
    }
    
    
    public MemberActivity(int activityID, Users user, Event event, String participationLevel) {
        this.activityID = activityID;
        this.user = user;
        this.event = event;
        this.participationLevel = participationLevel;
    }

    public MemberActivity(int activityID, String participationLevel, int userId, int eventId) {
        this.activityID = activityID;
        this.participationLevel = participationLevel;
        this.userId = userId;
        this.eventId = eventId;
    }    
    
    public MemberActivity() {
    }
    // Getters and Setters

    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getParticipationLevel() {
        return participationLevel;
    }

    public void setParticipationLevel(String participationLevel) {
        this.participationLevel = participationLevel;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    
    
}