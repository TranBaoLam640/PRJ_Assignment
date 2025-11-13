/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import model.Users;
import dao.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.*;

/**
 *
 * @author Admin
 */
public class UserDAO {

    public static boolean checkExist(int userId) {
        DBContext db = DBContext.getInstance();//1 
        try {
            String sql = """
                         select 1 
                         from Users where UserID = ?
                         """;//2 
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }

    public static ArrayList<Users> getAllUsersName() {
        DBContext db = DBContext.getInstance();//1 
        ArrayList<Users> user = new ArrayList<Users>();//khoi tao connection
        try {
            String sql = """
                         select UserID, FullName 
                         from Users
                         """;//2 
            PreparedStatement statement = db.getConnection().prepareStatement(sql);//3 cbi cau lenh sql
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Users users = new Users(
                        rs.getInt("UserID"),
                        rs.getString("FullName"),
                        null,
                        null,
                        null,
                        0
                );
                user.add(users);
            }
        } catch (Exception e) {
            return null;
        }
        return user;
    }

    public static ArrayList<Users> getAllUsers(int clubId, boolean isAdmin) {
        DBContext db = DBContext.getInstance();//1 
        ArrayList<Users> user = new ArrayList<Users>();
        try {
            String sql = isAdmin ? "select * from Users" : "select * from users where ClubID = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            if (!isAdmin) {
                statement.setInt(1, clubId);
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Users users = new Users(
                        rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Role"),
                        rs.getInt("ClubID")
                );
                user.add(users);
            }
        } catch (Exception e) {
            return null;
        }

        if (user.isEmpty()) {
            return null;
        } else {
            return user;
        }
    }

    public static Users getRoleByUserId(int userID) {
        DBContext db = DBContext.getInstance();//1 
        ArrayList<Users> user = new ArrayList<Users>();//khoi tao connection
        try {
            String sql = """
                         select Role 
                         from Users
                         where UserID = ?
                         """;//2 
            PreparedStatement statement = db.getConnection().prepareStatement(sql);//3 cbi cau lenh sql
            statement.setInt(1, userID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Users users = new Users(
                        rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Role"),
                        rs.getInt("ClubID")
                );
                user.add(users);
            }
        } catch (Exception e) {
            return null;
        }

        if (user.isEmpty()) {
            return null;
        } else {
            return user.get(0);
        }
    }

    public static Users addUser(Users user) {
        DBContext db = DBContext.getInstance();//1 
        int rs = 0;
        try {
            String sql = """
                         INSERT INTO Users (FullName, Email, Password, Role, ClubID) VALUES (?, ?, ?, ?, ?)
                         """;//2 
            PreparedStatement statement = db.getConnection().prepareStatement(sql);//3 cbi cau lenh sql
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword() != null ? user.getPassword() : "");
            statement.setString(4, user.getRole());
            statement.setInt(5, user.getClubId());
            rs = statement.executeUpdate();
        } catch (Exception e) {
            return null;
        }
        if (rs == 0) {
            return null;
        } else {
            return user;
        }
    }

    public static Users updateUsers(Users user) {
        DBContext db = DBContext.getInstance();
        int rs = 0;
        try {
            String sql = """
                     UPDATE Users SET FullName = ?, Email = ?, Role = ?, ClubID = ? WHERE UserID = ?
                     """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getRole());
            statement.setInt(4, user.getClubId());
            statement.setInt(5, user.getUserID());
            rs = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
        if (rs == 0) {
            return null;
        }
        return user;
    }

    public static boolean deleteUsers(int userID) {
        DBContext db = DBContext.getInstance();//1 
        int rs = 0;
        try {
            String sql = """                 
                         DELETE FROM MembershipRequests WHERE UserID = ?;
                         DELETE FROM Users WHERE UserID = ?;
                         """;//2 
            PreparedStatement statement = db.getConnection().prepareStatement(sql);//3 cbi cau lenh sql
            statement.setInt(1, userID);
            statement.setInt(2, userID);
            rs = statement.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return rs > 0;
    }

    public static Users getUserByID(int userID) {
        DBContext db = DBContext.getInstance();
        Users user = null;
        try {
            String sql = """
                         select * 
                         from Users
                         where UserID = ?
                         """;//2 
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, userID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = new Users(
                        rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Role"),
                        rs.getInt("ClubID")
                );
            }
        } catch (Exception e) {
            return null;
        }
        return user;
    }

    public static ArrayList<MemberActivity> getActivityHistory(int userId) {
        DBContext db = DBContext.getInstance();
        ArrayList<MemberActivity> activities = new ArrayList<>();
        ResultSet rs = null;
        try {
            String sql = """
                         SELECT ma.ActivityID, ma.UserID, ma.EventID, ma.ParticipationLevel,
                         u.FullName, u.Email, u.Role, u.ClubID, e.EventName, e.EventDate
                         FROM MemberActivities ma JOIN Users u ON ma.UserID = u.UserID
                         JOIN Events e ON ma.EventID = e.EventID
                         WHERE ma.UserID = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, userId);
            rs = statement.executeQuery();
            while (rs.next()) {
                Users user = new Users(
                        rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        null, // Không cần lấy mật khẩu
                        rs.getString("Role"),
                        rs.getInt("ClubID")
                );
                Event event = new Event(
                        rs.getInt("EventID"),
                        rs.getString("EventName"),
                        rs.getDate("EventDate")
                );
                MemberActivity activity = new MemberActivity(
                        rs.getInt("ActivityID"),
                        user,
                        event,
                        rs.getString("ParticipationLevel")
                );
                activities.add(activity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activities;
    }

    public static Users getUserByEmail(String email) {
        DBContext db = DBContext.getInstance();//1 
        ArrayList<Users> user = new ArrayList<Users>();//khoi tao connection
        try {
            String sql = """
                         select * 
                         from Users
                         where Email = ? && password = ?
                         """;//2 
            PreparedStatement statement = db.getConnection().prepareStatement(sql);//3 cbi cau lenh sql
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Users users = new Users(
                        rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Role"),
                        rs.getInt("ClubID")
                );
                user.add(users);
            }
        } catch (Exception e) {
            return null;
        }

        if (user.isEmpty()) {
            return null;
        } else {
            return user.get(0);
        }
    }

    public static Users authenticateUser(String email, String password) {
        DBContext db = DBContext.getInstance();//1 
        ArrayList<Users> user = new ArrayList<Users>();//khoi tao connection
        try {
            String sql = """
                         SELECT * FROM Users WHERE Email = ? AND Password = ?
                         """;//2 
            PreparedStatement statement = db.getConnection().prepareStatement(sql);//3 cbi cau lenh sql
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Users users = new Users(
                        rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Role"),
                        rs.getInt("ClubID")
                );
                user.add(users);
            }
        } catch (Exception e) {
            return null;
        }

        if (user.isEmpty()) {
            return null;
        } else {
            return user.get(0);
        }
    }

    public static ArrayList<Users> SearchUsers(String keyword, int clubId, boolean isAdmin) {
        DBContext db = DBContext.getInstance();
        ArrayList<Users> user = new ArrayList<Users>();
        keyword = keyword.trim().toLowerCase();
        try {
            String sql = isAdmin
                    ? "SELECT * FROM Users WHERE LOWER(FullName) LIKE ? OR LOWER(Email) LIKE ? OR CAST(UserID AS NVARCHAR(10)) LIKE ?"
                    : "SELECT * FROM Users WHERE (LOWER(FullName) LIKE ? OR LOWER(Email) LIKE ? OR CAST(UserID AS NVARCHAR(10)) LIKE ?) AND ClubID = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setString(3, "%" + keyword + "%");
            if (!isAdmin) {
                statement.setInt(4, clubId);
            }
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Users users = new Users(
                        rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Role"),
                        rs.getInt("ClubID")
                );
                user.add(users);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return user;
    }

    public static MembershipRequest addMembershipRequest(MembershipRequest membershipRequest) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
//// dùng để duyệt đơn

    public static List<MembershipRequest> getMembershipRequestsByUserId(int userID) {
        List<MembershipRequest> requests = new ArrayList<>();
        DBContext db = DBContext.getInstance();

        try {
            String sql = "SELECT * FROM MembershipRequests WHERE UserID = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, userID);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                MembershipRequest request = new MembershipRequest(
                        rs.getInt("RequestID"),
                        rs.getInt("UserID"),
                        rs.getInt("ClubID"),
                        rs.getString("Reason"),
                        rs.getString("Status"),
                        rs.getString("RequestDate")
                );
                requests.add(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requests;
    }

    public static boolean updateMembershipRequestStatus(int requestID, String status, int userID, int clubID) {
        DBContext db = DBContext.getInstance();

        try {
            String updateRequestQuery = "UPDATE MembershipRequests SET Status = ? WHERE RequestID = ?";
            String updateUserQuery = "UPDATE Users SET ClubID = ? WHERE UserID = ?";

            PreparedStatement psRequest = db.getConnection().prepareStatement(updateRequestQuery);
            PreparedStatement psUser = db.getConnection().prepareStatement(updateUserQuery);

            db.getConnection().setAutoCommit(false);

            // Cập nhật trạng thái yêu cầu
            psRequest.setString(1, status);
            psRequest.setInt(2, requestID);
            psRequest.executeUpdate();

            if ("Approved".equals(status)) {
                // Nếu duyệt, cập nhật ClubID của user
                psUser.setInt(1, clubID);
                psUser.setInt(2, userID);
                psUser.executeUpdate();
            }

            db.getConnection().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                db.getConnection().rollback(); // Hoàn tác nếu có lỗi
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            return false;
        }
    }

    public static MembershipRequest getMembershipRequestById(int requestID) {
        DBContext db = DBContext.getInstance();
        MembershipRequest request = null;

        try {
            String sql = "SELECT * FROM MembershipRequests WHERE RequestID = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, requestID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int userID = rs.getInt("UserID");
                int clubID = rs.getInt("ClubID");
                String reason = rs.getString("Reason");
                String status = rs.getString("Status");
                String requestDate = rs.getString("RequestDate");
                request = new MembershipRequest(requestID, userID, clubID, reason, status, requestDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return request;
    }

    public static ArrayList<MembershipRequest> getMembershipRequests(int clubID) {
        DBContext db = DBContext.getInstance(); // Khởi tạo kết nối DB
        ArrayList<MembershipRequest> MemberRequest = new ArrayList<MembershipRequest>(); // Danh sách kết quả
        try {
            // Chuẩn bị câu lệnh SQL sử dụng LIKE để tìm kiếm
            String sql = """
                         SELECT * FROM MembershipRequests WHERE ClubID = ? AND Status = 'Pending'
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, clubID);
            ResultSet rs = statement.executeQuery();

            // Duyệt kết quả và thêm vào danh sách
            while (rs.next()) {
                MembershipRequest request = new MembershipRequest(
                        rs.getInt("RequestID"),
                        rs.getInt("UserID"),
                        rs.getInt("ClubID"),
                        rs.getString("Reason"),
                        rs.getString("Status"),
                        rs.getString("RequestDate")
                );

                MemberRequest.add(request);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        // Trả về danh sách hoặc null nếu không có kết quả
        return MemberRequest;
    }

    public static List<MemberParticipation> getMemberParticipationBySemester(String semesterName, int clubId, boolean isAdmin) {
        DBContext db = DBContext.getInstance();
        ArrayList<MemberParticipation> user = new ArrayList<>();
        try {
            String sql = "SELECT "
                    + "    u.UserID, "
                    + "    u.FullName, "
                    + "    COALESCE(s.SemesterName, 'Chưa tham gia kì nào') AS SemesterName, "
                    + "    COUNT(CASE WHEN ep.Status = 'Attended' AND e.ClubID = u.ClubID THEN 1 END) AS EventAttended, "
                    + "    COALESCE((SELECT COUNT(*) "
                    + "              FROM Events e2 "
                    + "              WHERE e2.SemesterID = s.SemesterID "
                    + "                AND e2.ClubID = u.ClubID), 0) AS TotalEvent "
                    + "FROM Users u "
                    + "LEFT JOIN EventParticipants ep ON u.UserID = ep.UserID "
                    + "LEFT JOIN Events e ON ep.EventID = e.EventID AND e.ClubID = u.ClubID "
                    + "LEFT JOIN Semesters s ON e.SemesterID = s.SemesterID "
                    + "LEFT JOIN Clubs c ON u.ClubID = c.ClubID "
                    + "WHERE s.SemesterName = ? ";

            // Nếu không phải admin, lọc theo clubId
            if (!isAdmin) {
                sql += "AND u.ClubID = ? ";
            }

            // Sắp xếp theo UserID từ bé đến lớn
            sql += "GROUP BY u.UserID, u.FullName, u.ClubID, c.ClubName, COALESCE(s.SemesterName, 'Chưa tham gia kì nào'), s.SemesterID "
                    + "ORDER BY u.UserID ASC, COALESCE(s.SemesterName, 'Chưa tham gia kì nào'), u.FullName";

            PreparedStatement statement = db.getConnection().prepareStatement(sql);

            // Set tham số
            statement.setString(1, semesterName);
            if (!isAdmin) {
                statement.setInt(2, clubId);
            }

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                MemberParticipation mp = new MemberParticipation(
                        rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("SemesterName"),
                        rs.getInt("EventAttended"),
                        rs.getInt("TotalEvent")
                );
                user.add(mp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return user.isEmpty() ? null : user;
    }

    public static MemberParticipation getMemberParticipation(int userId, String semester) {
        DBContext db = DBContext.getInstance();
        MemberParticipation mp = null;
        try {
            String sql = "SELECT "
                    + "    u.UserID, "
                    + "    u.FullName, "
                    + "    COALESCE(s.SemesterName, 'Chưa tham gia kì nào') AS SemesterName, "
                    + "    COUNT(CASE WHEN ep.Status = N'Attended' AND e.ClubID = u.ClubID THEN 1 END) AS EventAttended, "
                    + "    COALESCE((SELECT COUNT(*) "
                    + "              FROM Events e2 "
                    + "              WHERE e2.SemesterID = s.SemesterID "
                    + "                AND e2.ClubID = u.ClubID), 0) AS TotalEvent "
                    + "FROM Users u "
                    + "LEFT JOIN EventParticipants ep ON u.UserID = ep.UserID "
                    + "LEFT JOIN Events e ON ep.EventID = e.EventID AND e.ClubID = u.ClubID "
                    + "LEFT JOIN Semesters s ON e.SemesterID = s.SemesterID "
                    + "WHERE u.UserID = ? AND s.SemesterName = ? "
                    + "GROUP BY u.UserID, u.FullName, COALESCE(s.SemesterName, 'Chưa tham gia kì nào'), s.SemesterID, u.ClubID "
                    + "ORDER BY COALESCE(s.SemesterName, 'Chưa tham gia kì nào'), u.FullName";

            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setString(2, semester);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                mp = new MemberParticipation(
                        rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("SemesterName"),
                        rs.getInt("EventAttended"),
                        rs.getInt("TotalEvent")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return mp;
    }

    public static ArrayList<MemberParticipation> getAllMemberParticipation(int id, boolean isAdmin) {
        DBContext db = DBContext.getInstance();
        ArrayList<MemberParticipation> user = new ArrayList<>();
        try {
            String sql = "SELECT "
                    + "    u.UserID, "
                    + "    u.FullName, "
                    + "    COALESCE(s.SemesterName, 'Chưa tham gia kì nào') AS SemesterName, "
                    + "    COUNT(CASE WHEN ep.Status = 'Attended' AND e.ClubID = u.ClubID THEN 1 END) AS EventAttended, "
                    + "    COALESCE((SELECT COUNT(*) "
                    + "              FROM Events e2 "
                    + "              WHERE e2.SemesterID = s.SemesterID "
                    + "                AND e2.ClubID = u.ClubID), 0) AS TotalEvent "
                    + "FROM Users u "
                    + "LEFT JOIN EventParticipants ep ON u.UserID = ep.UserID "
                    + "LEFT JOIN Events e ON ep.EventID = e.EventID AND e.ClubID = u.ClubID "
                    + "LEFT JOIN Semesters s ON e.SemesterID = s.SemesterID "
                    + "LEFT JOIN Clubs c ON u.ClubID = c.ClubID ";

            // Nếu không phải admin thì lọc theo ClubID
            if (!isAdmin) {
                sql += "WHERE u.ClubID = ? ";
            }

            // Sắp xếp theo UserID từ bé đến lớn
            sql += "GROUP BY u.UserID, u.FullName, u.ClubID, c.ClubName, COALESCE(s.SemesterName, 'Chưa tham gia kì nào'), s.SemesterID "
                    + "ORDER BY u.UserID ASC, COALESCE(s.SemesterName, 'Chưa tham gia kì nào'), u.FullName";

            PreparedStatement statement = db.getConnection().prepareStatement(sql);

            // Nếu không phải admin, đặt tham số cho ClubID
            if (!isAdmin) {
                statement.setInt(1, id);
            }

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                MemberParticipation mp = new MemberParticipation(
                        rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("SemesterName"),
                        rs.getInt("EventAttended"),
                        rs.getInt("TotalEvent")
                );
                user.add(mp);
            }
        } catch (Exception e) {
            return null;
        }

        return user.isEmpty() ? null : user;
    }

    public boolean updateMembershipRequest(int requestID, String status) {
        DBContext db = DBContext.getInstance(); // Khởi tạo kết nối DB
        int rs = 0;
        try {
            String sql = """
                         UPDATE MembershipRequests SET Status = ? WHERE RequestID = ?
                         """;//2 
            PreparedStatement statement = db.getConnection().prepareStatement(sql);//3 cbi cau lenh sql
            statement.setString(1, status); // status có thể là "Approved" hoặc "Rejected"
            statement.setInt(2, requestID);
            rs = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<MembershipRequest> getPendingMembershipRequests(int clubID) {
        DBContext db = DBContext.getInstance(); // Khởi tạo kết nối DB
        ArrayList<MembershipRequest> MemberRequest = new ArrayList<MembershipRequest>(); // Danh sách kết quả
        try {
            // Chuẩn bị câu lệnh SQL sử dụng LIKE để tìm kiếm
            String sql = """
                         SELECT * FROM MembershipRequests WHERE ClubID = ? AND Status = 'Pending'
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, clubID);
            ResultSet rs = statement.executeQuery();

            // Duyệt kết quả và thêm vào danh sách
            while (rs.next()) {
                MembershipRequest request = new MembershipRequest(
                        rs.getInt("RequestID"),
                        rs.getInt("UserID"),
                        rs.getInt("ClubID"),
                        rs.getString("Reason"),
                        rs.getString("Status"),
                        rs.getString("RequestDate")
                );

                MemberRequest.add(request);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        // Trả về danh sách hoặc null nếu không có kết quả
        return MemberRequest;
    }

    public static MembershipRequest checkRequest(int UserID) {
        DBContext db = DBContext.getInstance();//1 
        ArrayList<MembershipRequest> MemberRequest = new ArrayList<MembershipRequest>(); // Danh sách kết quả
        try {
            String sql = """
                         select * from MembershipRequests Where UserID = ? AND Status = 'Pending'
                         """;//2 
            PreparedStatement statement = db.getConnection().prepareStatement(sql);//3 cbi cau lenh sql
            statement.setInt(1, UserID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                MembershipRequest request = new MembershipRequest(
                        rs.getInt("RequestID"),
                        rs.getInt("UserID"),
                        rs.getInt("ClubID"),
                        rs.getString("Reason"),
                        rs.getString("Status"),
                        rs.getString("RequestDate")
                );
                MemberRequest.add(request);
            }
        } catch (Exception e) {
            return null;
        }

        if (MemberRequest.isEmpty()) {
            return null;
        } else {
            return MemberRequest.get(0);
        }
    }

    public static MembershipRequest addMembershipRequest(int userID, int ClubID, String Reason) {
        DBContext db = DBContext.getInstance();//1 
        MembershipRequest mr = null; // Tạo đối tượng để lưu kết quả
        try {
            String sql = """
                                 INSERT INTO MembershipRequests(UserID, ClubID, Reason) VALUES(?, ?, ?);
                                
                         """;//2 
            PreparedStatement statement = db.getConnection().prepareStatement(sql);//3 cbi cau lenh sql
            statement.setInt(1, userID);
            statement.setInt(2, ClubID);
            statement.setString(3, Reason);
            int rs = statement.executeUpdate();
            if (rs > 0) {
                // Nếu insert thành công, tạo đối tượng MembershipRequest để trả về
                mr = new MembershipRequest(userID, ClubID, Reason);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Hiển thị lỗi trong console để debug
            return null;
        }

        return mr; // 
    }

    public static Users signUpUser(Users user) {
        DBContext db = DBContext.getInstance();//1 
        int rs = 0;
        try {
            String sql = """
                         INSERT INTO Users (FullName, Email, Password, Role, ClubID) VALUES (?, ?, ?, 'Member', null)
                         """;//2 
            PreparedStatement statement = db.getConnection().prepareStatement(sql);//3 cbi cau lenh sql
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            rs = statement.executeUpdate();
        } catch (Exception e) {
            return null;
        }
        if (rs == 0) {
            return null;
        } else {
            return user;
        }
    }

}
