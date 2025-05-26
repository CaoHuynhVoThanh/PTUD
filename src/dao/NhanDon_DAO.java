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

import connectDB.ConnectDB;
import entities.Ban;
import entities.DonDatBan;
import entities.DonDatBanView;

public class NhanDon_DAO {
	public static ArrayList<DonDatBanView> getAllDon() {
	    ConnectDB.getInstance().connect();
	    Connection conN = ConnectDB.getInstance().getConnection();
	    ArrayList<DonDatBanView> dsDonView = new ArrayList<>();

	    String sql = "SELECT ddb.maDDB, ddb.maKH, ddb.maNV, ddb.soKhach, ddb.thoiGianDat, ddb.thoiGianNhan, ddb.tienCoc, ddb.trangThai, COUNT(DISTINCT ct.MaBan) AS SoBan,COUNT(DISTINCT ctdgm.soLuong) AS SoMon,kh.SoDienThoai, kh.tenKH " +
	                 "FROM [dbo].[DonDatBan] ddb " +
	                 "JOIN [dbo].[ChiTietDonDatBan] ct ON ddb.maDDB = ct.maDDB " +
	                 "JOIN [dbo].[KhachHang] kh ON ddb.maKH = kh.maKH " +
	                 "JOIN [dbo].[ChiTietDonGoiMon] ctdgm ON ctdgm.maDGM = ct.maDGM " +
	                 "GROUP BY ddb.maDDB, ddb.maKH, ddb.maNV, ddb.soKhach, ddb.thoiGianDat, ddb.thoiGianNhan, ddb.tienCoc, ddb.trangThai, kh.SoDienThoai, kh.tenKH;";
	    try {
	        PreparedStatement pst = conN.prepareStatement(sql);
	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	        	String maDon = rs.getString("maDDB");
	        	String maKH = rs.getString("maKH");
	        	String tenKH = rs.getString("tenKH");
	        	String maNV = rs.getString("maNV");
	        	int soKhach = rs.getInt("SoKhach");
	        	Timestamp thoiGianDat = rs.getTimestamp("thoiGianDat");
	            Timestamp thoiGianNhan = rs.getTimestamp("thoiGianNhan");
	            double tienCoc = rs.getDouble("tienCoc");
	            int trangThai = rs.getInt("trangThai");
	            int soBan = rs.getInt("SoBan");
	            int soMon = rs.getInt("SoMon");
	            String sdt = rs.getString("SoDienThoai");
	            
	            LocalDateTime thoiGianNhanBan = null;
	            if (thoiGianNhan != null) {
	            	thoiGianNhanBan = thoiGianNhan.toLocalDateTime();
//	            	if (thoiGianNhanBan != null) {
//	            	    int hour = thoiGianNhanBan.getHour();
//	            	    int minute = thoiGianNhanBan.getMinute();
//	            	    int second = thoiGianNhanBan.getSecond();
//	            	    
//	            	    thoiGianNhanBan 
////	            	    System.out.println("Giờ: " + hour + ", Phút: " + minute + ", Giây: " + second);
//	            	}
	            }
	            LocalDateTime thoiGianDatBan = null;
	            if (thoiGianDat != null) {
	            	thoiGianDatBan = thoiGianDat.toLocalDateTime();
	            }

	            DonDatBan ddb = new DonDatBan(maDon, "", maNV, maKH, thoiGianDatBan, thoiGianNhanBan, soKhach, tienCoc, trangThai);
	            if(trangThai==1) {
	            	DonDatBanView ddbv = new DonDatBanView(ddb, soBan, soMon, sdt, tenKH, tienCoc);
		            dsDonView.add(ddbv);
	            }	            
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsDonView;
	}
	
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
	        String sqlUpdateBan = "UPDATE Ban SET tinhTrang = 0 WHERE maBan = ?";
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
	
	public static ArrayList<DonDatBanView> timDonTheoSoDienThoai(String soDienThoai) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getInstance().getConnection();
	    ArrayList<DonDatBanView> dsDonView = new ArrayList<>();

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
	            String maDon = rs.getString("maDDB");
	            String maKH = rs.getString("maKH");
	            String tenKH = rs.getString("tenKH");
	            String maNV = rs.getString("maNV");
	            int soKhach = rs.getInt("SoKhach");
	            Timestamp thoiGianDat = rs.getTimestamp("thoiGianDat");
	            Timestamp thoiGianNhan = rs.getTimestamp("thoiGianNhan");
	            double tienCoc = rs.getDouble("tienCoc");
	            int trangThai = rs.getInt("trangThai");
	            int soBan = rs.getInt("SoBan");
	            int soMon = rs.getInt("SoMon");
	            String sdt = rs.getString("SoDienThoai");

	            LocalDateTime thoiGianNhanBan = thoiGianNhan != null ? thoiGianNhan.toLocalDateTime() : null;
	            LocalDateTime thoiGianDatBan = thoiGianDat != null ? thoiGianDat.toLocalDateTime() : null;

	            DonDatBan ddb = new DonDatBan(maDon, "", maNV, maKH, thoiGianDatBan, thoiGianNhanBan, soKhach, tienCoc, trangThai);
	            
	            if (trangThai == 1) {
	                DonDatBanView ddbv = new DonDatBanView(ddb, soBan, soMon, sdt, tenKH, tienCoc);
	                dsDonView.add(ddbv);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsDonView;
	}

	public static ArrayList<DonDatBanView> timDonTheoNgayNhan(java.util.Date ngayChon) {
	    ArrayList<DonDatBanView> dsDonView = new ArrayList<>();
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
	            // Mapping tương tự getAllDon()
	            String maDon = rs.getString("maDDB");
	            String maKH = rs.getString("maKH");
	            String tenKH = rs.getString("tenKH");
	            String maNV = rs.getString("maNV");
	            int soKhach = rs.getInt("SoKhach");
	            Timestamp thoiGianDat = rs.getTimestamp("thoiGianDat");
	            Timestamp thoiGianNhan = rs.getTimestamp("thoiGianNhan");
	            double tienCoc = rs.getDouble("tienCoc");
	            int trangThai = rs.getInt("trangThai");
	            int soBan = rs.getInt("SoBan");
	            int soMon = rs.getInt("SoMon");
	            String sdt = rs.getString("SoDienThoai");

	            LocalDateTime tgNhan = thoiGianNhan != null ? thoiGianNhan.toLocalDateTime() : null;
	            LocalDateTime tgDat = thoiGianDat != null ? thoiGianDat.toLocalDateTime() : null;

	            DonDatBan ddb = new DonDatBan(maDon, "", maNV, maKH, tgDat, tgNhan, soKhach, tienCoc, trangThai);
	            if (trangThai == 1) {
	                DonDatBanView ddbv = new DonDatBanView(ddb, soBan, soMon, sdt, tenKH, tienCoc);
	                dsDonView.add(ddbv);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsDonView;
	}






}
