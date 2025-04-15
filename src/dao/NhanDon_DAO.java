package dao;

import java.sql.Connection;
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

	    String sql = "SELECT ddb.maDDB, ddb.maKH, ddb.maNV, ddb.soKhach, ddb.thoiGianDat, ddb.thoiGianNhan, ddb.tienCoc, ddb.trangThai, COUNT(DISTINCT ct.MaBan) AS SoBan, kh.SoDienThoai " +
	                 "FROM [dbo].[DonDatBan] ddb " +
	                 "JOIN [dbo].[ChiTietDonDatBan] ct ON ddb.[maDDB] = ct.[maDDB] " +
	                 "JOIN [dbo].[KhachHang] kh ON ddb.[maKH] = kh.[maKH] " +
	                 "GROUP BY ddb.maDDB, ddb.maKH, ddb.maNV, ddb.soKhach, ddb.thoiGianDat, ddb.thoiGianNhan, ddb.tienCoc, ddb.trangThai, kh.SoDienThoai;";
	    try {
	        PreparedStatement pst = conN.prepareStatement(sql);
	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	        	String maDon = rs.getString("maDDB");
	        	String maKH = rs.getString("maKH");
	        	String maNV = rs.getString("maNV");
	        	int soKhach = rs.getInt("SoKhach");
	        	Timestamp thoiGianDat = rs.getTimestamp("thoiGianDat");
	            Timestamp thoiGianNhan = rs.getTimestamp("thoiGianNhan");
	            double tienCoc = rs.getDouble("tienCoc");
	            int trangThai = rs.getInt("trangThai");
	            int soBan = rs.getInt("SoBan");
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
	            DonDatBanView ddbv = new DonDatBanView(ddb, soBan, sdt);
	            dsDonView.add(ddbv);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsDonView;
	}
}
