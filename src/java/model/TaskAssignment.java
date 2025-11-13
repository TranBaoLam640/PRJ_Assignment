package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Admin
 */
public class TaskAssignment {

    private int taskID;
    private int eventID;
    private int assignedUserID;
    private String taskDescription;
    private String status;
    private String fullName;
    private String eventName;
    private String clubName;
    private Event event;
    private Users assignedUser;
    
    public TaskAssignment() {
    }

    public TaskAssignment(int taskID, int eventID, int assignedUserID, String taskDescription, String status) {
        this.taskID = taskID;
        this.eventID = eventID;
        this.assignedUserID = assignedUserID;
        this.taskDescription = taskDescription;
        this.status = status;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getAssignedUserID() {
        return assignedUserID;
    }

    public void setAssignedUserID(int assignedUserID) {
        this.assignedUserID = assignedUserID;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Users getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(Users assignedUser) {
        this.assignedUser = assignedUser;
    }

}
