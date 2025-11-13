package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import model.Club;
import dao.DBContext;

/**
 * DAO class for handling database operations related to Club entity.
 */
public class ClubDAO {

    public static Club getClubByName(String name) {
        DBContext db = DBContext.getInstance(); // Lấy instance của DBContext
        Club club = null; // Chỉ cần một object thay vì ArrayList

        try {
            String sql = "SELECT * FROM Clubs WHERE ClubName = ?"; // Truy vấn SQL
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) { // Nếu có dữ liệu, tạo đối tượng Club
                club = new Club();
                club.setClubID(rs.getInt("ClubID"));
                club.setClubName(rs.getString("ClubName"));
                club.setDescription(rs.getString("Description"));
                club.setEstablishedDate(rs.getDate("EstablishedDate")); // Sử dụng getDate() để tránh lỗi chuyển đổi
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi để dễ debug
        }
        return club; // Trả về null nếu không tìm thấy
    }
    
    
    public static Club getClubByID(int id) {
        DBContext db = DBContext.getInstance(); // Lấy instance của DBContext
        Club club = null; // Chỉ cần một object thay vì ArrayList

        try {
            String sql = "SELECT * FROM Clubs WHERE ClubID = ?"; // Truy vấn SQL
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) { // Nếu có dữ liệu, tạo đối tượng Club
                club = new Club();
                club.setClubID(rs.getInt("ClubID"));
                club.setClubName(rs.getString("ClubName"));
                club.setDescription(rs.getString("Description"));
                club.setEstablishedDate(rs.getDate("EstablishedDate")); // Sử dụng getDate() để tránh lỗi chuyển đổi
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi để dễ debug
        }
        return club; // Trả về null nếu không tìm thấy
    }

//    // Kiểm tra phương thức
//    public static void main(String[] args) {
//        Club club = ClubDAO.getClubByName("FPT Debate Club");
//        if (club != null) {
//            System.out.println("ClubID: " + club.getClubID()
//                    + ", Name: " + club.getClubName()
//                    + ", Description: " + club.getDescription()
//                    + ", Established Date: " + club.getEstablishedDate());
//        } else {
//            System.out.println("Club not found!");
//        }
//    }
}
