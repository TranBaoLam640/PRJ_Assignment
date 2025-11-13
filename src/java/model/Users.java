package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.List;

public class Users {

    private int userID;
    private String fullName;
    private String email;
    private String password;
    private String role;
    private int clubId;

    private Club club;
    private List<EventParticipant> eventParticipants;
    private List<MemberActivity> memberActivities;
    private List<EventFeedback> eventFeedbacks;
    private List<TaskAssignment> taskAssignments;

    public Users(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public Users(int userID, String fullName, String email, String password, String role, int clubId) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.clubId = clubId;
    }

    public Users() {
    }

    public int getUserID() {
        return userID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Club getClub() {
        return club;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public List<EventParticipant> getEventParticipants() {
        return eventParticipants;
    }

    public List<MemberActivity> getMemberActivities() {
        return memberActivities;
    }

    public List<EventFeedback> getEventFeedbacks() {
        return eventFeedbacks;
    }

    public List<TaskAssignment> getTaskAssignments() {
        return taskAssignments;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public void setEventParticipants(List<EventParticipant> eventParticipants) {
        this.eventParticipants = eventParticipants;
    }

    public void setMemberActivities(List<MemberActivity> memberActivities) {
        this.memberActivities = memberActivities;
    }

    public void setEventFeedbacks(List<EventFeedback> eventFeedbacks) {
        this.eventFeedbacks = eventFeedbacks;
    }

    public void setTaskAssignments(List<TaskAssignment> taskAssignments) {
        this.taskAssignments = taskAssignments;
    }

}
