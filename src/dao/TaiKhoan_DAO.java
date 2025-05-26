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
    
    public static String getMatKhau(String ma) {
        PreparedStatement stmt = null;
        ConnectDB.getInstance();
        String mk = null;
		Connection conN = ConnectDB.getInstance().getConnection();
        try {
            String sql = "Select MatKhau from TaiKhoan where MaTK=?";
            stmt = conN.prepareStatement(sql);
            stmt.setString(1, ma);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	mk = rs.getString(1);
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mk;
    }
    
    public static boolean resetMK(String manv, String mk) {
    	 PreparedStatement stmt = null;
         ConnectDB.getInstance();
 		Connection conN = ConnectDB.getInstance().getConnection();
         try {
             String sql = "Update TaiKhoan Set MatKhau=? Where MaTK=?";
             stmt = conN.prepareStatement(sql);
             stmt.setString(1, mk);
             stmt.setString(2, manv);
             stmt.executeUpdate();
             
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return true;
    }
}
