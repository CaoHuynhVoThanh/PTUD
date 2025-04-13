package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
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
	    String sql = "SELECT maHD, maNV, thoiGianThanhToan, maCTKM, tongTien, phuongThucThanhToan FROM HoaDon";
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
	
	            HoaDon hd = new HoaDon(maHD, maNV, thoiGianTT.toLocalDateTime(), maCTKM, tongTien, phuongThuc);
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



}
