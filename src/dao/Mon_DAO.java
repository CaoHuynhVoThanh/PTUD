package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import connectDB.ConnectDB;
import entities.Mon;

public class Mon_DAO {
	public static ArrayList<Mon> getAllMonAn() {
	    ArrayList<Mon> dsMon = new ArrayList<Mon>();
	    Connection con = ConnectDB.getInstance().getConnection();
	    String sql = "SELECT maMon, tenMon, loaiMon, donGia, hinhAnh FROM Mon WHERE loaiMon <> N'Đồ uống'"; // Câu lệnh SELECT thường
	    PreparedStatement stmt = null;

	    try {
	        stmt = con.prepareStatement(sql); // Không dùng CallableStatement nữa
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            String maMon = rs.getString("maMon");
	            String tenMon = rs.getString("tenMon");
	            String loaiMon = rs.getString("loaiMon");
	            double donGia = rs.getDouble("donGia");
	            String hinhAnh = rs.getString("hinhAnh");
	            Mon mon = new Mon(maMon, tenMon, loaiMon, donGia, hinhAnh);
	            dsMon.add(mon);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsMon;
	}
	public static ArrayList<Mon> getAllDoUong() {
	    ArrayList<Mon> dsMon = new ArrayList<Mon>();
	    Connection con = ConnectDB.getInstance().getConnection();
	    String sql = "SELECT maMon, tenMon, loaiMon, donGia, hinhAnh FROM Mon WHERE loaiMon = N'Đồ uống'"; // Câu lệnh SELECT thường
	    PreparedStatement stmt = null;

	    try {
	        stmt = con.prepareStatement(sql); // Không dùng CallableStatement nữa
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            String maMon = rs.getString("maMon");
	            String tenMon = rs.getString("tenMon");
	            String loaiMon = rs.getString("loaiMon");
	            double donGia = rs.getDouble("donGia");
	            String hinhAnh = rs.getString("hinhAnh");
	            Mon mon = new Mon(maMon, tenMon, loaiMon, donGia, hinhAnh);
	            dsMon.add(mon);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsMon;
	}
	public static Mon getMonTheoTen(String tenMon) {
        for (Mon mon : getAllMonAn()) {
            if (mon.getTenMon().equalsIgnoreCase(tenMon)) {
                return mon;
            }
        }
        return null;
    }
	public static Mon getMonTheoMa(String maMon) {
        for (Mon mon : getAllMonAn()) {
            if (mon.getMaMon().equals(maMon)) {
                return mon;
            }
        }
        return null;
    }
	public static Map<Mon, Integer> layDanhSachMonVaLuotDung(LocalDate ngay) {
	    Map<Mon, Integer> danhSach = new HashMap<>();
	    Connection con = ConnectDB.getInstance().getConnection();
	    String sql = """
	            SELECT m.*, SUM(ct.soLuong + ct.soLuongDaThanhToan) AS luotDung
	            FROM Mon m
	            JOIN ChiTietDonGoiMon ct ON m.maMon = ct.maMon
	            JOIN DonGoiMon dgm ON ct.maDGM = dgm.maDGM
	            WHERE CAST(dgm.thoiGianGM AS DATE) = ?
	            GROUP BY m.maMon, m.tenMon, m.loaiMon, m.donGia, m.hinhAnh
	        """;

	    try (
	         PreparedStatement stmt = con.prepareStatement(sql)) {
	        stmt.setDate(1, java.sql.Date.valueOf(ngay));
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                Mon mon = new Mon(rs.getString("maMon"), rs.getString("tenMon"), rs.getString("loaiMon"), rs.getDouble("donGia"), rs.getString("hinhAnh"));

	                int luotDung = rs.getInt("luotDung");
	                danhSach.put(mon, luotDung);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return danhSach;
	}

}
