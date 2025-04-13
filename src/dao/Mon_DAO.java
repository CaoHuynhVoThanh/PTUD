package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
}
