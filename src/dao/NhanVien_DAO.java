package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entities.NhanVien;

public class NhanVien_DAO {
	public static boolean themNhanVien(NhanVien nv) {
		int n = 0;
		ConnectDB.getInstance();
		Connection conN = ConnectDB.getInstance().getConnection();
		PreparedStatement pstm = null;
		String sql = "INSERT INTO NhanVien ( maNV, tenNV, email, soDienThoai, diaChi, chucVu, ngaySinh, trangThai) VALUES (?,?,?,?,?,?,?,?)";
		try {
			pstm = conN.prepareStatement(sql);
			pstm.setString(1, nv.getMaNV());
			pstm.setString(2, nv.getTenNV());
			pstm.setString(3, nv.getEmail());
			pstm.setString(4, nv.getSdt());
			pstm.setString(5, nv.getDiaChi());
			pstm.setString(6, nv.getChucVu());
			pstm.setTimestamp(7, Timestamp.valueOf(nv.getNgaySinh().atStartOfDay()));
			pstm.setBoolean(8, nv.isTrangThai());
			n = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return n > 0;
    }
	public static boolean suaNhanVien(NhanVien nv) {
	    int n = 0;
	    ConnectDB.getInstance();
	    Connection conN = ConnectDB.getInstance().getConnection();
	    PreparedStatement pstm = null;
	    
	    String sql = "UPDATE NhanVien SET tenNV = ?, email = ?, soDienThoai = ?, diaChi = ?, chucVu = ?, ngaySinh = ?, trangThai = ? WHERE maNV = ?";
	    
	    try {
	        pstm = conN.prepareStatement(sql);
	        pstm.setString(1, nv.getTenNV());
	        pstm.setString(2, nv.getEmail());
	        pstm.setString(3, nv.getSdt());
	        pstm.setString(4, nv.getDiaChi());
	        pstm.setString(5, nv.getChucVu());
	        pstm.setTimestamp(6, Timestamp.valueOf(nv.getNgaySinh().atStartOfDay()));
	        pstm.setBoolean(7, nv.isTrangThai());
	        pstm.setString(8, nv.getMaNV()); // WHERE maNV = ?
	        
	        n = pstm.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pstm != null) {
	                pstm.close();
	            }
	        } catch (Exception e2) {
	            e2.printStackTrace();
	        }
	    }
	    
	    return n > 0;
	}
	public static ArrayList<NhanVien> timNhanVienTheoTen(String tenNV) {
	    ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
	    ConnectDB.getInstance();
	    Connection conN = ConnectDB.getInstance().getConnection();
	    PreparedStatement pstm = null;
	    ResultSet rs = null;

	    String sql = "SELECT * FROM NhanVien WHERE tenNV LIKE ?";

	    try {
	        pstm = conN.prepareStatement(sql);
	        pstm.setString(1, "%" + tenNV + "%"); // tìm gần đúng theo tên
	        rs = pstm.executeQuery();

	        while (rs.next()) {
	            String maNV = rs.getString("maNV");
	            String ten = rs.getString("tenNV");
	            String email = rs.getString("email");
	            String sdt = rs.getString("soDienThoai");
	            String diaChi = rs.getString("diaChi");
	            String chucVu = rs.getString("chucVu");
	            LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
	            boolean trangThai = rs.getBoolean("trangThai");

	            NhanVien nv = new NhanVien(maNV, ten, email, sdt, diaChi, chucVu, ngaySinh, trangThai);
	            dsNhanVien.add(nv);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstm != null) pstm.close();
	        } catch (Exception e2) {
	            e2.printStackTrace();
	        }
	    }

	    return dsNhanVien;
	}
	public static NhanVien timNhanVienTheoMa(String maNV) {
	    NhanVien nv = null;
	    ConnectDB.getInstance();
	    Connection conN = ConnectDB.getInstance().getConnection();
	    PreparedStatement pstm = null;
	    ResultSet rs = null;

	    String sql = "SELECT * FROM NhanVien WHERE maNV = ?";

	    try {
	        pstm = conN.prepareStatement(sql);
	        pstm.setString(1, maNV);
	        rs = pstm.executeQuery();

	        if (rs.next()) {
	            String ten = rs.getString("tenNV");
	            String email = rs.getString("email");
	            String sdt = rs.getString("soDienThoai");
	            String diaChi = rs.getString("diaChi");
	            String chucVu = rs.getString("chucVu");
	            LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
	            boolean trangThai = rs.getBoolean("trangThai");

	            nv = new NhanVien(maNV, ten, email, sdt, diaChi, chucVu, ngaySinh, trangThai);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstm != null) pstm.close();
	        } catch (Exception e2) {
	            e2.printStackTrace();
	        }
	    }

	    return nv;
	}
	public static ArrayList<NhanVien> getAllNhanVien() {
	    ArrayList<NhanVien> dsNV = new ArrayList<>();
	    Connection con = ConnectDB.getInstance().getConnection();
	    String sql = "SELECT * FROM NhanVien"; // Câu lệnh SELECT thường
	    PreparedStatement stmt = null;

	    try {
	        stmt = con.prepareStatement(sql); // Không dùng CallableStatement nữa
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            String maNV = rs.getString("maNV");
	            String tenNV = rs.getString("tenNV");
	            String email = rs.getString("email");
	            String soDienThoai = rs.getString("soDienThoai");
	            String diaChi = rs.getString("diaChi");
	            String chucVu = rs.getString("chucVu");
	            LocalDate ngaySinh = rs.getTimestamp("ngaySinh").toLocalDateTime().toLocalDate();
	            boolean trangThai = rs.getBoolean("trangThai");
	            NhanVien nv = new NhanVien(maNV, tenNV, email, soDienThoai, diaChi, chucVu, ngaySinh, trangThai);
	            dsNV.add(nv);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsNV;
	}


}
