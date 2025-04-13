package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import connectDB.ConnectDB;

public class ChiTietDonDatBan_DAO {
	public static String getMaDGMTheoNgayVaBan(LocalDate ngayDat, String maBan) {
	    String maDGM = null;
	    ConnectDB.getInstance().connect();
	    Connection conN = ConnectDB.getInstance().getConnection();
	    
	    String sql = "SELECT ctddb.maDGM " +
	                 "FROM ChiTietDonDatBan ctddb " +
	                 "JOIN DonDatBan ddb ON ctddb.maDDB = ddb.maDDB " +
	                 "WHERE CONVERT(DATE, ddb.thoiGianDat) = ? " +
	                 "AND ctddb.maBan = ?";
	    
	    try {
	        PreparedStatement pst = conN.prepareStatement(sql);
	        pst.setDate(1, java.sql.Date.valueOf(ngayDat));
	        pst.setString(2, maBan);
	        
	        ResultSet rs = pst.executeQuery();
	        if (rs.next()) {
	            maDGM = rs.getString("maDGM");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return maDGM;
	}
	public static boolean capNhatMaDGMTheoNgayVaBan(LocalDate ngayDat, String maBan, String maDGM) {
	    ConnectDB.getInstance().connect();
	    Connection conN = ConnectDB.getInstance().getConnection();
	    boolean success = false;
	    
	    String sql = "UPDATE ChiTietDonDatBan " +
	                 "SET maDGM = ? " +
	                 "WHERE maBan = ? AND maDDB IN (" +
	                 "   SELECT maDDB FROM DonDatBan " +
	                 "   WHERE CONVERT(DATE, thoiGianDat) = ?" +
	                 ")";
	    
	    try {
	        conN.setAutoCommit(false); // Start transaction
	        
	        PreparedStatement pst = conN.prepareStatement(sql);
	        pst.setString(1, maDGM);
	        pst.setString(2, maBan);
	        pst.setDate(3, java.sql.Date.valueOf(ngayDat));
	        
	        int rowsAffected = pst.executeUpdate();
	        if (rowsAffected > 0) {
	            conN.commit(); // Commit transaction if successful
	            success = true;
	        } else {
	            conN.rollback(); // Rollback if no rows affected
	        }
	    } catch (SQLException e) {
	        try {
	            conN.rollback(); // Rollback on error
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        e.printStackTrace();
	    } finally {
	        try {
	            conN.setAutoCommit(true); // Reset auto-commit
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return success;
	}
	public static int demChiTietDonDatBanTrongNgay() {
	    int count = 0;
	    String query = " SELECT COUNT(*)\r\n"
	    		+ "        FROM ChiTietDonDatBan CT\r\n"
	    		+ "        JOIN DonDatBan DDB ON CT.maDDB = DDB.maDDB\r\n"
	    		+ "        WHERE CAST(DDB.thoiGianNhan AS DATE) = CAST(GETDATE() AS DATE)";
	    ConnectDB.getInstance().connect();
	    Connection conN = ConnectDB.getInstance().getConnection();
	    try {
	         PreparedStatement stmt = conN.prepareStatement(query);
	         ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            count = rs.getInt(1);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return count;
	}
	public static int demTongSoLuongMonTrongNgay() {
	    int tongSoLuong = 0;
	    String query = """
	            SELECT SUM(soLuong)
	            FROM ChiTietDonGoiMon ct
	            JOIN DonGoiMon dgm ON ct.maDGM = dgm.maDGM
	            WHERE CAST(dgm.thoiGianGM AS DATE) = CAST(GETDATE() AS DATE)
	            """;

	    ConnectDB.getInstance().connect();
	    Connection conN = ConnectDB.getInstance().getConnection();

	    try {
	        PreparedStatement stmt = conN.prepareStatement(query);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            tongSoLuong = rs.getInt(1);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return tongSoLuong;
	}


}
