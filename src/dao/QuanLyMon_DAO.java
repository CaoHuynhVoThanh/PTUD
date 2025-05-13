package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entities.Mon;

public class QuanLyMon_DAO {
	public static ArrayList<Mon> getAllMon() {
	    ConnectDB.getInstance().connect();
	    Connection conN = ConnectDB.getInstance().getConnection();
	    ArrayList<Mon> dsMon = new ArrayList<>();

	    String sql = "SELECT * FROM Mon";
	    try {
	        PreparedStatement pst = conN.prepareStatement(sql);

	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            String ma = rs.getString("maMon");
	            String ten = rs.getString("tenMon");
	            String loai = rs.getString("loaiMon");
	            Double donGia = rs.getDouble("donGia");
	            String hinhAnh = rs.getString("hinhAnh");
	            
	            Mon ban = new Mon(ma, ten, loai, donGia, hinhAnh);
	            dsMon.add(ban);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsMon;
	}
	
	public static boolean updateMon(Mon mon) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getInstance().getConnection();
	    
	    String sql = "UPDATE Mon SET tenMon = ?, loaiMon = ?, donGia = ?, hinhAnh = ? WHERE maMon = ?";
	    
	    try {
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, mon.getTenMon());
	        pst.setString(2, mon.getLoaiMon());
	        pst.setDouble(3, mon.getDonGia());
	        pst.setString(4, mon.getHinhAnh());
	        pst.setString(5, mon.getMaMon());

	        int rowsAffected = pst.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static boolean deleteMon(String maMon) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getInstance().getConnection();
	    
	    String sql = "DELETE FROM Mon WHERE maMon = ?";
	    
	    try {
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, maMon);

	        int rowsAffected = pst.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static ArrayList<Mon> searchMonByTen(String tenMon) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getInstance().getConnection();
	    ArrayList<Mon> dsMon = new ArrayList<>();

	    String sql = "SELECT * FROM Mon WHERE tenMon LIKE ?";

	    try {
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, "%" + tenMon + "%"); // tìm gần đúng

	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            String ma = rs.getString("maMon");
	            String ten = rs.getString("tenMon");
	            String loai = rs.getString("loaiMon");
	            Double donGia = rs.getDouble("donGia");
	            String hinhAnh = rs.getString("hinhAnh");

	            Mon mon = new Mon(ma, ten, loai, donGia, hinhAnh);
	            dsMon.add(mon);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsMon;
	}
	
	public static ArrayList<Mon> searchMonByLoai(String loaiMon) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getInstance().getConnection();
	    ArrayList<Mon> dsMon = new ArrayList<>();

	    String sql = "SELECT * FROM Mon WHERE loaiMon = ?";

	    try {
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, loaiMon); // so sánh chính xác loại món

	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            String ma = rs.getString("maMon");
	            String ten = rs.getString("tenMon");
	            String loai = rs.getString("loaiMon");
	            Double donGia = rs.getDouble("donGia");
	            String hinhAnh = rs.getString("hinhAnh");

	            Mon mon = new Mon(ma, ten, loai, donGia, hinhAnh);
	            dsMon.add(mon);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsMon;
	}
	
	
	public static boolean addMon(Mon mon) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getInstance().getConnection();

	    String sql = "INSERT INTO Mon (maMon, tenMon, loaiMon, donGia, hinhAnh) VALUES (?, ?, ?, ?, ?)";

	    try {
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, mon.getMaMon());
	        pst.setString(2, mon.getTenMon());
	        pst.setString(3, mon.getLoaiMon());
	        pst.setDouble(4, mon.getDonGia());
	        pst.setString(5, mon.getHinhAnh());

	        int rowsAffected = pst.executeUpdate();
	        return rowsAffected > 0; 
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}






}
