package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;

import connectDB.ConnectDB;
import entities.Ban;
import entities.DonDatBan;
import entities.DonDatBanView;

public class NhanDon_DAO {
	public boolean nhanBan(String maDDB) {
	    String sql = "UPDATE DonDatBan SET trangThai = 0 WHERE maDDB = ? AND trangThai <> 0";

	    try (Connection con = ConnectDB.getInstance().getConnection();
	         PreparedStatement pst = con.prepareStatement(sql)) {

	        pst.setString(1, maDDB);
	        int updated = pst.executeUpdate();

	        return updated > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean huyDonVaCapNhatTatCaBan(String maDDB) {
	    Connection con = null;
	    PreparedStatement pstHuyDon = null;
	    PreparedStatement pstGetBan = null;
	    PreparedStatement pstUpdateBan = null;
	    ResultSet rs = null;

	    try {
	        con = ConnectDB.getInstance().getConnection();
	        con.setAutoCommit(false); // Transaction bắt đầu

	        // 1. Hủy đơn đặt bàn
	        String sqlHuyDon = "UPDATE DonDatBan SET trangThai = 2 WHERE maDDB = ? AND trangThai <> 0";
	        pstHuyDon = con.prepareStatement(sqlHuyDon);
	        pstHuyDon.setString(1, maDDB);
	        int updated = pstHuyDon.executeUpdate();
	        if (updated == 0) {
	            con.rollback();
	            return false;
	        }

	        // 2. Lấy danh sách mã bàn liên quan đến đơn
	        String sqlGetBan = "SELECT maBan FROM ChiTietDonDatBan WHERE maDDB = ?";
	        pstGetBan = con.prepareStatement(sqlGetBan);
	        pstGetBan.setString(1, maDDB);
	        rs = pstGetBan.executeQuery();

	        // 3. Cập nhật trạng thái từng bàn về False (trống)
	        String sqlUpdateBan = "UPDATE Ban SET tinhTrang = 1 WHERE maBan = ?";
	        pstUpdateBan = con.prepareStatement(sqlUpdateBan);

	        while (rs.next()) {
	            String maBan = rs.getString("maBan");
	            pstUpdateBan.setString(1, maBan);
	            pstUpdateBan.executeUpdate();
	        }

	        con.commit(); // Giao dịch thành công
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (con != null) con.rollback(); // Giao dịch thất bại
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        return false;
	    } finally {
	        // Đóng tài nguyên
	        try {
	            if (rs != null) rs.close();
	            if (pstHuyDon != null) pstHuyDon.close();
	            if (pstGetBan != null) pstGetBan.close();
	            if (pstUpdateBan != null) pstUpdateBan.close();
	            if (con != null) con.setAutoCommit(true);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public static ArrayList<Map<String, Object>> timDonTheoSoDienThoai(String soDienThoai) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getInstance().getConnection();
	    ArrayList<Map<String, Object>> dsDonView = new ArrayList<>();

	    String sql = "SELECT ddb.maDDB, ddb.maKH, ddb.maNV, ddb.soKhach, ddb.thoiGianDat, ddb.thoiGianNhan, ddb.tienCoc, ddb.trangThai, " +
	                 "COUNT(DISTINCT ct.MaBan) AS SoBan, COUNT(DISTINCT ctdgm.soLuong) AS SoMon, kh.SoDienThoai, kh.tenKH " +
	                 "FROM DonDatBan ddb " +
	                 "JOIN ChiTietDonDatBan ct ON ddb.maDDB = ct.maDDB " +
	                 "JOIN KhachHang kh ON ddb.maKH = kh.maKH " +
	                 "JOIN ChiTietDonGoiMon ctdgm ON ctdgm.maDGM = ct.maDGM " +
	                 "WHERE kh.SoDienThoai = ? " +
	                 "GROUP BY ddb.maDDB, ddb.maKH, ddb.maNV, ddb.soKhach, ddb.thoiGianDat, ddb.thoiGianNhan, ddb.tienCoc, ddb.trangThai, kh.SoDienThoai, kh.tenKH";

	    try (PreparedStatement pst = con.prepareStatement(sql)) {
	        pst.setString(1, soDienThoai);
	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            int trangThai = rs.getInt("trangThai");
	            if (trangThai == 1) {  // chỉ lấy đơn đang hoạt động
	                Map<String, Object> don = new HashMap<>();
	                don.put("maDDB", rs.getString("maDDB"));
	                don.put("maKH", rs.getString("maKH"));
	                don.put("maNV", rs.getString("maNV"));
	                don.put("soKhach", rs.getInt("soKhach"));
	                don.put("thoiGianDat", rs.getTimestamp("thoiGianDat"));
	                don.put("thoiGianNhan", rs.getTimestamp("thoiGianNhan"));
	                don.put("tienCoc", rs.getDouble("tienCoc"));
	                don.put("trangThai", trangThai);
	                don.put("soBan", rs.getInt("SoBan"));
	                don.put("soMon", rs.getInt("SoMon"));
	                don.put("soDienThoai", rs.getString("SoDienThoai"));
	                don.put("tenKH", rs.getString("tenKH"));

	                dsDonView.add(don);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsDonView;
	}


	public static ArrayList<Map<String, Object>> timDonTheoNgayNhan(java.util.Date ngayChon) {
	    ArrayList<Map<String, Object>> dsDonView = new ArrayList<>();
	    Connection con = ConnectDB.getInstance().getConnection();

	    String sql = "SELECT ddb.maDDB, ddb.maKH, ddb.maNV, ddb.soKhach, ddb.thoiGianDat, ddb.thoiGianNhan, " +
	                 "ddb.tienCoc, ddb.trangThai, COUNT(DISTINCT ct.MaBan) AS SoBan, " +
	                 "COUNT(DISTINCT ctdgm.soLuong) AS SoMon, kh.SoDienThoai, kh.tenKH " +
	                 "FROM DonDatBan ddb " +
	                 "JOIN ChiTietDonDatBan ct ON ddb.maDDB = ct.maDDB " +
	                 "JOIN KhachHang kh ON ddb.maKH = kh.maKH " +
	                 "JOIN ChiTietDonGoiMon ctdgm ON ctdgm.maDGM = ct.maDGM " +
	                 "WHERE CAST(ddb.thoiGianNhan AS DATE) = ? " +
	                 "GROUP BY ddb.maDDB, ddb.maKH, ddb.maNV, ddb.soKhach, ddb.thoiGianDat, ddb.thoiGianNhan, " +
	                 "ddb.tienCoc, ddb.trangThai, kh.SoDienThoai, kh.tenKH";

	    try (PreparedStatement pst = con.prepareStatement(sql)) {
	        pst.setDate(1, new java.sql.Date(ngayChon.getTime()));
	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            int trangThai = rs.getInt("trangThai");
	            if (trangThai == 1) {
	                Map<String, Object> don = new HashMap<>();
	                don.put("maDDB", rs.getString("maDDB"));
	                don.put("maKH", rs.getString("maKH"));
	                don.put("maNV", rs.getString("maNV"));
	                don.put("soKhach", rs.getInt("soKhach"));
	                don.put("thoiGianDat", rs.getTimestamp("thoiGianDat"));
	                don.put("thoiGianNhan", rs.getTimestamp("thoiGianNhan"));
	                don.put("tienCoc", rs.getDouble("tienCoc"));
	                don.put("trangThai", trangThai);
	                don.put("soBan", rs.getInt("SoBan"));
	                don.put("soMon", rs.getInt("SoMon"));
	                don.put("soDienThoai", rs.getString("SoDienThoai"));
	                don.put("tenKH", rs.getString("tenKH"));

	                dsDonView.add(don);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsDonView;
	}

	
	
	public static List<Map<String, Object>> getAllDon() {
		ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getInstance().getConnection();
	    List<Map<String, Object>> dsDonView = new ArrayList<>();

	    String sql = "SELECT ddb.maDDB, ddb.maKH, ddb.maNV, ddb.soKhach, ddb.thoiGianDat, ddb.thoiGianNhan, ddb.tienCoc, ddb.trangThai, " +
	                 "COUNT(DISTINCT ct.MaBan) AS SoBan, COUNT(DISTINCT ctdgm.maMon) AS SoMon, kh.SoDienThoai, kh.tenKH " +
	                 "FROM [dbo].[DonDatBan] ddb " +
	                 "JOIN [dbo].[ChiTietDonDatBan] ct ON ddb.maDDB = ct.maDDB " +
	                 "JOIN [dbo].[KhachHang] kh ON ddb.maKH = kh.maKH " +
	                 "LEFT JOIN [dbo].[ChiTietDonGoiMon] ctdgm ON ctdgm.maDGM = ct.maDGM " +
	                 "GROUP BY ddb.maDDB, ddb.maKH, ddb.maNV, ddb.soKhach, ddb.thoiGianDat, ddb.thoiGianNhan, ddb.tienCoc, ddb.trangThai, kh.SoDienThoai, kh.tenKH";

	    try (PreparedStatement pst = con.prepareStatement(sql);
	         ResultSet rs = pst.executeQuery()) {

	        while (rs.next()) {
	            Map<String, Object> row = new HashMap<>();
	            row.put("maDDB", rs.getString("maDDB"));
	            row.put("maKH", rs.getString("maKH"));
	            row.put("tenKH", rs.getString("tenKH"));
	            row.put("maNV", rs.getString("maNV"));
	            row.put("soKhach", rs.getInt("soKhach"));
	            row.put("thoiGianDat", rs.getTimestamp("thoiGianDat"));
	            row.put("thoiGianNhan", rs.getTimestamp("thoiGianNhan"));
	            row.put("tienCoc", rs.getDouble("tienCoc"));
	            row.put("trangThai", rs.getInt("trangThai"));
	            row.put("soBan", rs.getInt("SoBan"));
	            row.put("soMon", rs.getInt("SoMon"));
	            row.put("soDienThoai", rs.getString("SoDienThoai"));

	            if (rs.getInt("trangThai") == 1) {
	                dsDonView.add(row);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsDonView;
	}


	public boolean giaHanThoiGianNhan(String maDDB, LocalDateTime thoiGianMoi) {
		ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getInstance().getConnection();
	    String sql = "UPDATE DonDatBan SET thoiGianNhan = ? WHERE maDDB = ?";
	    PreparedStatement stmt = null;
	    try {
	    	stmt = con.prepareStatement(sql);
	        stmt.setTimestamp(1, Timestamp.valueOf(thoiGianMoi));
	        stmt.setString(2, maDDB);

	        int rowsUpdated = stmt.executeUpdate();
	        return rowsUpdated > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (stmt != null) stmt.close();
	            if (con != null) con.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}





}
