package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
	public static boolean insertKhachHang(String maKH, String tenKH, String soDienThoai, LocalDate ngayTao) {
		ConnectDB.getInstance().connect();;
		Connection conN = ConnectDB.getInstance().getConnection();
        String sql = "INSERT INTO KhachHang (maKH, tenKH, soDienThoai, ngayTao) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement pst = conN.prepareStatement(sql);

            pst.setString(1, maKH);
            pst.setString(2, tenKH);
            pst.setString(3, soDienThoai);
            pst.setDate(4, Date.valueOf(ngayTao));

            int rowsInserted = pst.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	public static KhachHang getKhachHangMoiNhatCuaBan(String maBan) {
	    KhachHang kh = null;
	    ConnectDB.getInstance().connect();;
		Connection conN = ConnectDB.getInstance().getConnection();
		String sql = "SELECT TOP 1 kh.maKH, kh.tenKH, kh.soDienThoai, kh.ngayTao " +
	             "FROM Ban b " +
	             "JOIN ChiTietDonDatBan ct ON b.maBan = ct.maBan " +
	             "JOIN DonDatBan ddb ON ct.maDDB = ddb.maDDB " +
	             "JOIN KhachHang kh ON ddb.maKH = kh.maKH " +
	             "WHERE b.maBan = ? AND CONVERT(date, ddb.thoiGianDat) = CONVERT(date, GETDATE()) " +
	             "ORDER BY ddb.thoiGianDat DESC";

	    
	    try (PreparedStatement ps = conN.prepareStatement(sql)) {
	        ps.setString(1, maBan);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            LocalDate ngayTao = rs.getDate("ngayTao").toLocalDate();
	            kh = new KhachHang(
	                rs.getString("maKH"),
	                rs.getString("tenKH"),
	                rs.getString("soDienThoai"),
	                ngayTao
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return kh;
	}

}
