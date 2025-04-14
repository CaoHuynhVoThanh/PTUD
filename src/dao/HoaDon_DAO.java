package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entities.HoaDon;

public class HoaDon_DAO {
	public static int demHoaDonTrongNgay() {
	    int count = 0;
	    String query = """
	            SELECT COUNT(*)
	            FROM HoaDon HD
	            JOIN DonDatBan DDB ON HD.maHD = DDB.maHD
	            WHERE CAST(DDB.thoiGianNhan AS DATE) = CAST(GETDATE() AS DATE)
	            """;
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
	public static ArrayList<HoaDon> layTatCaHoaDon() {
	    ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
	    Connection con = ConnectDB.getInstance().getConnection();
	    
	    String sql = "SELECT maHD, maNV, thoiGianThanhToan, maCTKM, tongTien, phuongThucThanhToan, maTV, diemTL FROM HoaDon";
	    PreparedStatement stmt = null;

	    try {
	        stmt = con.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            String maHD = rs.getString("maHD");
	            String maNV = rs.getString("maNV");
	            Timestamp thoiGianTT = rs.getTimestamp("thoiGianThanhToan");
	            String maCTKM = rs.getString("maCTKM");
	            double tongTien = rs.getDouble("tongTien");
	            String phuongThuc = rs.getString("phuongThucThanhToan");
	            String maTV = rs.getString("maTV");
	            double diemTL = rs.getDouble("diemTL");

	            HoaDon hd = new HoaDon(maHD, maNV, thoiGianTT.toLocalDateTime(), maCTKM, tongTien, phuongThuc, maTV, diemTL);
	            dsHoaDon.add(hd);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsHoaDon;
	}


	public static double tinhTongDoanhThuTheoNgay(LocalDate ngay) {
	    double tongDoanhThu = 0;
	    String query = """
	            SELECT SUM(tongTien)
	            FROM HoaDon
	            WHERE CAST(thoiGianThanhToan AS DATE) = ?
	            """;

	    ConnectDB.getInstance().connect();
	    Connection conN = ConnectDB.getInstance().getConnection();

	    try {
	        PreparedStatement stmt = conN.prepareStatement(query);
	        stmt.setDate(1, java.sql.Date.valueOf(ngay));
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            tongDoanhThu = rs.getDouble(1);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return tongDoanhThu;
	}

	public static double tinhTongDoanhThuTienMatTrongNgay() {
	    double tongDoanhThu = 0;
	    String query = """
	            SELECT SUM(tongTien)
	            FROM HoaDon
	            WHERE CAST(thoiGianThanhToan AS DATE) = CAST(GETDATE() AS DATE)
	              AND phuongThucThanhToan = N'Tiền mặt'
	            """;

	    ConnectDB.getInstance().connect();
	    Connection conN = ConnectDB.getInstance().getConnection();

	    try {
	        PreparedStatement stmt = conN.prepareStatement(query);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            tongDoanhThu = rs.getDouble(1);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return tongDoanhThu;
	}
	
	public static boolean insertHoaDon(String maHD, String maNV, LocalDateTime thoiGianThanhToan,
	        String maCTKM, double tongTien, String phuongThucThanhToan, String maTV, double diemTL) {

	    String sql = """
	        INSERT INTO HoaDon (maHD, maNV, thoiGianThanhToan, maCTKM, tongTien, phuongThucThanhToan, maTV, diemTL)
	        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
	        """;

	    Connection con = ConnectDB.getInstance().getConnection();
	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
	        stmt.setString(1, maHD);
	        stmt.setString(2, maNV);
	        stmt.setTimestamp(3, Timestamp.valueOf(thoiGianThanhToan));
	        stmt.setString(4, maCTKM);
	        stmt.setDouble(5, tongTien);
	        stmt.setString(6, phuongThucThanhToan);
	        stmt.setString(7, maTV);
	        stmt.setDouble(8, diemTL); 

	        int rows = stmt.executeUpdate();
	        return rows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}


}
