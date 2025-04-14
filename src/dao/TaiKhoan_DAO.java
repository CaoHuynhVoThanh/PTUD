package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
public class TaiKhoan_DAO {

    public static boolean themTaiKhoan(String maTK, String matKhau) {
        PreparedStatement stmt = null;
        ConnectDB.getInstance();
		Connection conN = ConnectDB.getInstance().getConnection();
        try {
            String sql = "INSERT INTO TaiKhoan (MaTK, MatKhau) VALUES (?, ?)";
            stmt = conN.prepareStatement(sql);
            stmt.setString(1, maTK);
            stmt.setString(2, matKhau); // Có thể mã hóa nếu cần
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
