/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.ClubDAO;
import dao.UserDAO;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class MembershipRequest {

    private int requestID;
    private int UserId;
    private int ClubId;
    public String Reason;
    private Users user;
    private Club club;
    private String status;
    private String requestDate;

    public MembershipRequest(int UserId, int ClubId, String Reason, String status, String requestDate) {
        this.UserId = UserId;
        this.ClubId = ClubId;
        this.Reason = Reason;
        this.status = status;
        this.requestDate = requestDate;
    }

    public MembershipRequest(int requestID, int UserId, int ClubId, String Reason, String status, String requestDate) {
        this.requestID = requestID;
        this.UserId = UserId;
        this.ClubId = ClubId;
        this.Reason = Reason;
        this.status = status;
        this.requestDate = requestDate;
    }

    public MembershipRequest() {
    }

    public MembershipRequest(int userID, int clubID, String reason) {
        this.UserId = userID;
        this.ClubId = clubID;
        this.Reason = reason;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public Users getUser() {
        return user;
    }

    public void setUser() {
        this.user = UserDAO.getUserByID(UserId);
    }

    public Club getClub() {
        return club;
    }

    public void setClub() {
        this.club = ClubDAO.getClubByID(ClubId);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public int getClubId() {
        return ClubId;
    }

    public void setClubId(int ClubId) {
        this.ClubId = ClubId;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }

}
