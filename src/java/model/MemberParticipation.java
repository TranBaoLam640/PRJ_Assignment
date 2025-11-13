/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.EventDAO;

/**
 *
 * @author Admin
 */
public class MemberParticipation {

    private int userID;
    private String fullName;
    private String semesterName;
    private int eventAttended;
    private int totalEvent;
    private String participationLevel;
    //private int eventId;
    //private Event event;
    

    public MemberParticipation(int userID, String fullName, String semesterName, int eventAttended, int totalEvent) {
        this.userID = userID;
        this.fullName = fullName;
        this.semesterName = semesterName;
        this.eventAttended = eventAttended;
        this.totalEvent = totalEvent;
//        this.eventId = eventId;
    }

    public MemberParticipation() {
    }

  

//    public Event getEvent() {
//        return event;
//    }
//
//    public void includeEvent() {
//        this.event = EventDAO.getEventById(eventId);
//    }

    // Getter và Setter
    public int getUserID() {
        return userID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public int getEventAttended() {
        return eventAttended;
    }

    public int getTotalEvent() {
        return totalEvent;
    }

    public String getParticipationLevel() {
        return participationLevel;
    }

    
//    public int getEventId() {
//        return eventId;
//    }
//
//    public void setEventId(int eventId) {
//        this.eventId = eventId;
//    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public void setEventAttended(int eventAttended) {
        this.eventAttended = eventAttended;
    }

    public void setTotalEvent(int totalEvent) {
        this.totalEvent = totalEvent;
    }

    public void setParticipationLevel(String participationLevel) {
        this.participationLevel = participationLevel;
    }
    
    
}

