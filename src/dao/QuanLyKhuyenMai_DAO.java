package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entities.KhuyenMai;
import entities.Mon;

public class QuanLyKhuyenMai_DAO {
	public static ArrayList<KhuyenMai> getAllKhuyenMai() {
	    ConnectDB.getInstance().connect();
	    Connection conN = ConnectDB.getInstance().getConnection();
	    ArrayList<KhuyenMai> dsKM = new ArrayList<>();

	    String sql = "SELECT * FROM ChuongTrinhKhuyenMai";
	    try {
	        PreparedStatement pst = conN.prepareStatement(sql);
	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            String ma = rs.getString("maCTKM");
	            String ten = rs.getString("tenCTKM");
	            double phanTram = rs.getDouble("phanTramKM");
	            Timestamp tgBatDau = rs.getTimestamp("thoiGianBatDau");
	            Timestamp tgKetThuc = rs.getTimestamp("thoiGianKetThuc");

	            LocalDateTime batDau = tgBatDau != null ? tgBatDau.toLocalDateTime() : null;
	            LocalDateTime ketThuc = tgKetThuc != null ? tgKetThuc.toLocalDateTime() : null;

	            KhuyenMai km = new KhuyenMai(ma, ten, phanTram, batDau, ketThuc, "Vàng", "Khuyến mãi theo % hóa đơn");
	            dsKM.add(km);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsKM;
	}
	
	public static boolean deleteKhuyenMai(String maKM) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getInstance().getConnection();
	    
	    String sql = "DELETE FROM ChuongTrinhKhuyenMai WHERE maCTKM = ?";
	    
	    try {
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, maKM);

	        int rowsAffected = pst.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
//	public static ArrayList<KhuyenMai> searchKhuyenMaiByLoaiTV(String loaiTV) {
//	    ConnectDB.getInstance().connect();
//	    Connection con = ConnectDB.getInstance().getConnection();
//	    ArrayList<KhuyenMai> dsKM = new ArrayList<>();
//
//	    String sql = "SELECT * FROM ChuongTrinhKhuyenMai WHERE loaiThanhVien = ?";
//
//	    try {
//	        PreparedStatement pst = con.prepareStatement(sql);
//	        pst.setString(1, loaiTV);
//
//	        ResultSet rs = pst.executeQuery();
//	        while (rs.next()) {
//	            String ma = rs.getString("maMon");
//	            String ten = rs.getString("tenMon");
//	            String loai = rs.getString("loaiMon");
//	            Double donGia = rs.getDouble("donGia");
//	            String hinhAnh = rs.getString("hinhAnh");
//
//	            Mon mon = new Mon(ma, ten, loai, donGia, hinhAnh);
//	            dsMon.add(mon);
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//
//	    return dsKM;
//	}
	public static ArrayList<KhuyenMai> timKhuyenMaiKetThucTruoc(LocalDateTime ngayChon) {
	    ArrayList<KhuyenMai> dsKM = new ArrayList<>();
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getInstance().getConnection();

	    String sql = "SELECT * FROM ChuongTrinhKhuyenMai WHERE thoiGianKetThuc < ?";
	    try {
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setTimestamp(1, Timestamp.valueOf(ngayChon));

	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	        	String ma = rs.getString("maCTKM");
	            String ten = rs.getString("tenCTKM");
	            double phanTram = rs.getDouble("phanTramKM");
	            LocalDateTime batDau = rs.getTimestamp("thoiGianBatDau").toLocalDateTime();
	            LocalDateTime ketThuc = rs.getTimestamp("thoiGianKetThuc").toLocalDateTime();

	            KhuyenMai km = new KhuyenMai(ma, ten, phanTram, batDau, ketThuc, "Vàng", "Khuyến mãi theo % hóa đơn");
	            dsKM.add(km);          
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsKM;
	}
	
	public static boolean themKhuyenMai(KhuyenMai km) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getInstance().getConnection();

	    String sql = "INSERT INTO ChuongTrinhKhuyenMai (maCTKM, tenCTKM, phanTramKM, thoiGianBatDau, thoiGianKetThuc) VALUES (?, ?, ?, ?, ?)";

	    try {
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, km.getMa());
	        pst.setString(2, km.getTen());
	        pst.setDouble(3, km.getPhanTram());
	        pst.setTimestamp(4, Timestamp.valueOf(km.getThoiGianBatDau()));
	        pst.setTimestamp(5, Timestamp.valueOf(km.getThoiGianKetThuc()));

	        int rowsAffected = pst.executeUpdate();
	        return rowsAffected > 0; 
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	
	public static boolean updateKhuyenMai(KhuyenMai khuyenMai) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getInstance().getConnection();
	    String sql = "UPDATE ChuongTrinhKhuyenMai SET tenCTKM = ?, phanTramKM = ?, thoiGianBatDau = ?, thoiGianKetThuc = ? WHERE maCTKM = ?";

	    try {
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, khuyenMai.getTen());
	        pst.setDouble(2, khuyenMai.getPhanTram());
	        // Chuyển đổi từ java.util.Date sang java.sql.Date
	        LocalDateTime localDateTimeBatDau = khuyenMai.getThoiGianBatDau();
	        java.sql.Date ngayBatDauSQL = java.sql.Date.valueOf(localDateTimeBatDau.toLocalDate());
	        
	        LocalDateTime localDateTimeKetThuc = khuyenMai.getThoiGianKetThuc();
	        java.sql.Date ngayKetThucSQL = java.sql.Date.valueOf(localDateTimeKetThuc.toLocalDate());
	        pst.setDate(3, ngayBatDauSQL);
	        pst.setDate(4, ngayKetThucSQL);
	        pst.setString(5, khuyenMai.getMa());

	        // Thực thi câu lệnh SQL
	        int rowsAffected = pst.executeUpdate();

	        // Kiểm tra số dòng bị ảnh hưởng, nếu > 0 có nghĩa là cập nhật thành công
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public static String sinhMaKhuyenMai() {
	    try {
	        ConnectDB.getInstance().connect();
	        Connection con = ConnectDB.getInstance().getConnection();

	        LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
	        String datePart = now.format(formatter);

	        String prefix = "KM" + datePart;

	        String sql = "SELECT maCTKM FROM ChuongTrinhKhuyenMai WHERE maCTKM LIKE ?";

	        try (PreparedStatement pst = con.prepareStatement(sql)) {
	            pst.setString(1, prefix + "%");

	            ResultSet rs = pst.executeQuery();
	            int maxSoThuTu = 0;

	            while (rs.next()) {
	                String ma = rs.getString("maCTKM");
	                if (ma.length() >= 10) {
	                    try {
	                        int soThuTu = Integer.parseInt(ma.substring(8));
	                        if (soThuTu > maxSoThuTu) {
	                            maxSoThuTu = soThuTu;
	                        }
	                    } catch (NumberFormatException ignored) {
	                        // Bỏ qua nếu không đúng định dạng
	                    }
	                }
	            }

	            int soThuTuMoi = maxSoThuTu + 1;
	            String maMoi = String.format("KM%s%02d", datePart, soThuTuMoi);
	            return maMoi;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}


}
