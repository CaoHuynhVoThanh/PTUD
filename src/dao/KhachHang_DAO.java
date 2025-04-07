package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connectDB.ConnectDB;
import entities.Ban;
import entities.KhachHang;

public class KhachHang_DAO {
	public String getLatestMaDDBForMaBan(String maBan) {
		ConnectDB.getInstance().connect();;
		Connection conN = ConnectDB.getInstance().getConnection();
	    String sql = "SELECT ctddb.maDDB "
	               + "FROM ChiTietDonDatBan ctddb "
	               + "JOIN DonDatBan ddb ON ctddb.maDDB = ddb.maDDB "
	               + "WHERE ctddb.maBan = ? "
	               + "ORDER BY ddb.ThoiGianNhan DESC "
	               + "LIMIT 1";
	    try {
	        PreparedStatement stmt = conN.prepareStatement(sql);       
	        stmt.setString(1, maBan);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getString("maDDB");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }    
	    return null;
	}
	
	public static int getSLKHHomNay() {
		ConnectDB.getInstance().connect();;
		Connection conN = ConnectDB.getInstance().getConnection();
		String sql = "SELECT COUNT(*) AS soLuongKhachHang FROM KhachHang WHERE CAST(ngayTao AS DATE) = CAST(GETDATE() AS DATE);";
		int sl=0;
		try {
			Statement st = conN.createStatement();
	        ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	        	sl = rs.getInt("soLuongKhachHang");
	        	}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return sl;
	}
}
