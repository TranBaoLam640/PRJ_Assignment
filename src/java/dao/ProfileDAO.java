package dao;

import dao.DBContext;
import model.Users;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfileDAO {
    
    // ✅ Lấy danh sách tất cả user (Admin sử dụng)
    public static List<Users> getAllUsers() {
        List<Users> userList = new ArrayList<>();
        DBContext db = DBContext.getInstance();

        try {
            String sql = "SELECT * FROM Users";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Users user = new Users(
                        rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Role"),
                        rs.getInt("ClubID")
                );
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    // ✅ Lấy thông tin user theo email (cho cá nhân)
    public static Users getUserByEmail(String email) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = "SELECT * FROM Users WHERE Email = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Users(
                        rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Role"),
                        rs.getInt("ClubID")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ Cập nhật thông tin user
    public static boolean updateUser(int userID, String fullName, String password, int clubId) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = "UPDATE Users SET FullName = ?, Password = ?, ClubID = ? WHERE UserID = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, fullName);
            statement.setString(2, password);
            statement.setInt(3, clubId);
            statement.setInt(4, userID);

            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
