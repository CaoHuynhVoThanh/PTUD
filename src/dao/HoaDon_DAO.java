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
	    
	    String sql = "SELECT *";
	    PreparedStatement stmt = null;

	    try {
	        stmt = con.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            String maHD = rs.getString("maHD");
	            String maNV = rs.getString("maNV");
	            Timestamp thoiGianTT = rs.getTimestamp("thoiGianThanhToan");
	            String maCTKM = rs.getString("maCTKM");
	            String phuongThuc = rs.getString("phuongThucThanhToan");
	            String maTV = rs.getString("maTV");
	            double diemTL = rs.getDouble("diemTL");

	            HoaDon hd = new HoaDon(maHD, maNV, thoiGianTT.toLocalDateTime(), maCTKM, phuongThuc, maTV, diemTL);
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
	    	    SELECT SUM(thanhTien + thueVAT + phuThu - phiCoc - phiMon) AS TongTien
	    	    FROM (
	    	        SELECT 
	    	            hd.maHD,
	    	            SUM(m.donGia * ctgm.soLuong) AS thanhTien,
	    	            SUM(m.donGia * ctgm.soLuong) * 0.1 AS thueVAT,
	    	            ISNULL(kv.phuThu, 0) AS phuThu,
	    	            ISNULL(c.phiCoc, 0) AS phiCoc,
	    	            SUM(CASE 
	    	                    WHEN ctgm.soLuongDaThanhToan > 0 THEN m.donGia * ctgm.soLuongDaThanhToan
	    	                    ELSE 0 
	    	                END) AS phiMon
	    	        FROM HoaDon hd
	    	        JOIN DonDatBan ddb ON hd.maHD = ddb.maHD
	    	        JOIN ChiTietDonDatBan ctdb ON ddb.maDDB = ctdb.maDDB
	    	        JOIN DonGoiMon dgm ON ctdb.maDGM = dgm.maDGM
	    	        JOIN ChiTietDonGoiMon ctgm ON dgm.maDGM = ctgm.maDGM
	    	        JOIN Mon m ON ctgm.maMon = m.maMon
	    	        JOIN Ban b ON ctdb.maBan = b.maBan
	    	        LEFT JOIN KhuVuc kv ON b.maKV = kv.maKV
	    	        LEFT JOIN Coc c ON b.loaiBan = c.maCoc
	    	        WHERE CAST(hd.thoiGianThanhToan AS DATE) = ?
	    	        GROUP BY hd.maHD, kv.phuThu, c.phiCoc
	    	    ) AS SubQuery
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
	    	    SELECT SUM(thanhTien + thueVAT + phuThu - phiCoc - phiMon) AS TongTien
	    	    FROM (
	    	        SELECT 
	    	            hd.maHD,
	    	            SUM(m.donGia * ctgm.soLuong) AS thanhTien,
	    	            SUM(m.donGia * ctgm.soLuong) * 0.1 AS thueVAT,
	    	            ISNULL(kv.phuThu, 0) AS phuThu,
	    	            ISNULL(c.phiCoc, 0) AS phiCoc,
	    	            SUM(CASE 
	    	                    WHEN ctgm.soLuongDaThanhToan > 0 THEN m.donGia * ctgm.soLuongDaThanhToan
	    	                    ELSE 0 
	    	                END) AS phiMon
	    	        FROM HoaDon hd
	    	        JOIN DonDatBan ddb ON hd.maHD = ddb.maHD
	    	        JOIN ChiTietDonDatBan ctdb ON ddb.maDDB = ctdb.maDDB
	    	        JOIN DonGoiMon dgm ON ctdb.maDGM = dgm.maDGM
	    	        JOIN ChiTietDonGoiMon ctgm ON dgm.maDGM = ctgm.maDGM
	    	        JOIN Mon m ON ctgm.maMon = m.maMon
	    	        JOIN Ban b ON ctdb.maBan = b.maBan
	    	        LEFT JOIN KhuVuc kv ON b.maKV = kv.maKV
	    	        LEFT JOIN Coc c ON b.loaiBan = c.maCoc
	    	        WHERE CAST(hd.thoiGianThanhToan AS DATE) = CAST(GETDATE() AS DATE)
	    	          AND hd.phuongThucThanhToan = N'Tiền mặt'
	    	        GROUP BY hd.maHD, kv.phuThu, c.phiCoc
	    	    ) AS SubQuery
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
	        INSERT INTO HoaDon (maHD, maNV, thoiGianThanhToan, maCTKM, phuongThucThanhToan, maTV, diemTL)
	        VALUES (?, ?, ?, ?, ?, ?, ?)
	        """;

	    Connection con = ConnectDB.getInstance().getConnection();
	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
	        stmt.setString(1, maHD);
	        stmt.setString(2, maNV);
	        stmt.setTimestamp(3, Timestamp.valueOf(thoiGianThanhToan));
	        stmt.setString(4, maCTKM);
	        stmt.setString(5, phuongThucThanhToan);
	        stmt.setString(6, maTV);
	        stmt.setDouble(7, diemTL); 

	        int rows = stmt.executeUpdate();
	        return rows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}

	public static ArrayList<HoaDon> layHoaDonTheoNgay(LocalDate ngay) {
	    ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
	    Connection con = ConnectDB.getInstance().getConnection();
	    
	    String sql = "SELECT * FROM HoaDon WHERE CAST(thoiGianThanhToan AS DATE) = ?";
	    PreparedStatement stmt = null;

	    try {
	        stmt = con.prepareStatement(sql);
	        stmt.setDate(1, java.sql.Date.valueOf(ngay));
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            String maHD = rs.getString("maHD");
	            String maNV = rs.getString("maNV");
	            Timestamp thoiGianTT = rs.getTimestamp("thoiGianThanhToan");
	            String maCTKM = rs.getString("maCTKM");
	            String phuongThuc = rs.getString("phuongThucThanhToan");
	            String maTV = rs.getString("maTV");
	            double diemTL = rs.getDouble("diemTL");

	            HoaDon hd = new HoaDon(maHD, maNV, thoiGianTT.toLocalDateTime(), maCTKM, phuongThuc, maTV, diemTL);
	            dsHoaDon.add(hd);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsHoaDon;
	}
	public static ArrayList<HoaDon> layHoaDonTheoTuan(LocalDate ngay) {
    ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
    String query = """
            SELECT *
            FROM HoaDon
            WHERE thoiGianThanhToan >= ? AND thoiGianThanhToan < ?
            """;

    Connection con = ConnectDB.getInstance().getConnection();
    try (PreparedStatement stmt = con.prepareStatement(query)) {
        // Calculate the start (Monday) and end (Sunday) of the week
        LocalDate startOfWeek = ngay.with(java.time.DayOfWeek.MONDAY);
        LocalDate endOfWeek = ngay.with(java.time.DayOfWeek.SUNDAY).plusDays(1); // Include Sunday

        stmt.setDate(1, java.sql.Date.valueOf(startOfWeek));
        stmt.setDate(2, java.sql.Date.valueOf(endOfWeek));
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String maHD = rs.getString("maHD");
            String maNV = rs.getString("maNV");
            Timestamp thoiGianTT = rs.getTimestamp("thoiGianThanhToan");
            String maCTKM = rs.getString("maCTKM");
            String phuongThuc = rs.getString("phuongThucThanhToan");
            String maTV = rs.getString("maTV");
            double diemTL = rs.getDouble("diemTL");

            HoaDon hd = new HoaDon(maHD, maNV, thoiGianTT.toLocalDateTime(), maCTKM, phuongThuc, maTV, diemTL);
            dsHoaDon.add(hd);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return dsHoaDon;
}


	public static ArrayList<HoaDon> layHoaDonTheoNam(LocalDate ngay) {
	    ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
	    String query = """
	            SELECT *
	            FROM HoaDon
	            WHERE YEAR(thoiGianThanhToan) = ?
	            """;

	    Connection con = ConnectDB.getInstance().getConnection();
	    try (PreparedStatement stmt = con.prepareStatement(query)) {
	        stmt.setInt(1, ngay.getYear());
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	        	String maHD = rs.getString("maHD");
	            String maNV = rs.getString("maNV");
	            Timestamp thoiGianTT = rs.getTimestamp("thoiGianThanhToan");
	            String maCTKM = rs.getString("maCTKM");
	            String phuongThuc = rs.getString("phuongThucThanhToan");
	            String maTV = rs.getString("maTV");
	            double diemTL = rs.getDouble("diemTL");

	            HoaDon hd = new HoaDon(maHD, maNV, thoiGianTT.toLocalDateTime(), maCTKM, phuongThuc, maTV, diemTL);
	            dsHoaDon.add(hd);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsHoaDon;
	}
	public static double tinhTongTien(String maHD) {
	    Connection con = ConnectDB.getInstance().getConnection();
	    double tongTien = 0.0;

	    String sql = """
	        SELECT SUM(thanhTien + thueVAT + phuThu - phiCoc - phiMon) AS TongTien
	        FROM (
	            SELECT 
	                hd.maHD,
	                SUM(m.donGia * ctgm.soLuong) AS thanhTien,
	                SUM(m.donGia * ctgm.soLuong) * 0.1 AS thueVAT,
	                ISNULL(kv.phuThu, 0) AS phuThu,
	                ISNULL(c.phiCoc, 0) AS phiCoc,
	                SUM(CASE 
	                        WHEN ctgm.soLuongDaThanhToan > 0 THEN m.donGia * ctgm.soLuongDaThanhToan
	                        ELSE 0 
	                    END) AS phiMon
	            FROM HoaDon hd
	            JOIN DonDatBan ddb ON hd.maHD = ddb.maHD
	            JOIN ChiTietDonDatBan ctdb ON ddb.maDDB = ctdb.maDDB
	            JOIN DonGoiMon dgm ON ctdb.maDGM = dgm.maDGM
	            JOIN ChiTietDonGoiMon ctgm ON dgm.maDGM = ctgm.maDGM
	            JOIN Mon m ON ctgm.maMon = m.maMon
	            JOIN Ban b ON ctdb.maBan = b.maBan
	            LEFT JOIN KhuVuc kv ON b.maKV = kv.maKV
	            LEFT JOIN Coc c ON b.loaiBan = c.maCoc
	            WHERE hd.maHD = ?
	            GROUP BY hd.maHD, kv.phuThu, c.phiCoc
	        ) AS SubQuery
	    """;

	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
	        stmt.setString(1, maHD);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            tongTien = rs.getDouble("TongTien");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return tongTien;
	}


}
