package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    private static DBContext instance = new DBContext();
    private Connection connection;

    public static DBContext getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public DBContext() {
        try {
            if (connection == null || connection.isClosed()) {
                String user = "sa";
                String password = "sa12345";
                String url = "jdbc:sqlserver://localhost:14330;databaseName=Assignments;TrustServerCertificate=true;";

                System.out.println("🔹 Đang tải driver JDBC...");
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                System.out.println("🔹 Đang kết nối tới: " + url);
                connection = DriverManager.getConnection(url, user, password);

                System.out.println("✅ Kết nối DB thành công!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Lỗi: Không tìm thấy driver JDBC SQL Server!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi kết nối tới cơ sở dữ liệu!");
            System.out.println("👉 Mã lỗi: " + e.getErrorCode());
            System.out.println("👉 SQLState: " + e.getSQLState());
            System.out.println("👉 Chi tiết: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("❌ Lỗi không xác định khi kết nối!");
            e.printStackTrace();
        }
    }

    // Test kết nối
    public static void main(String[] args) {
        DBContext db = DBContext.getInstance();
        Connection conn = db.getConnection();
        if (conn != null) {
            System.out.println("✅ Test kết nối: thành công");
        } else {
            System.out.println("❌ Test kết nối: thất bại");
        }
    }
}
