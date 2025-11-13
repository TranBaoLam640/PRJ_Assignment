/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author gigabyte
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDBConnection {
    public static void main(String[] args) {
        // Thông tin kết nối
        String user = "sa";
        String password = "123";
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Assignment;TrustServerCertificate=true;encrypt=false;";

        try {
            // Tải driver SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Thử kết nối
            Connection conn = DriverManager.getConnection(url, user, password);
            
            if (conn != null) {
                System.out.println("✅ Kết nối thành công với SQL Server!");
                conn.close(); // Đóng kết nối sau khi kiểm tra
            } else {
                System.out.println("❌ Kết nối thất bại!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Lỗi: Không tìm thấy driver SQL Server.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi kết nối.");
            e.printStackTrace();
        }
    }
}

