package dao;

import entities.ThanhVien;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;

public class ThanhVien_DAO {
    private Connection conn;

    public ThanhVien_DAO(Connection conn) {
        this.conn = conn;
    }

    public List<ThanhVien> getAllThanhVien() throws SQLException {
        List<ThanhVien> list = new ArrayList<>();
        String sql = "Select maTV, tenTV, email, ngaySinh, ngayCap FROM ThanhVien";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
        	int diem = tinhDiemTL(rs.getString("maTV"));
            ThanhVien tv = new ThanhVien(
                rs.getString("maTV"),
                rs.getString("tenTV"),
                rs.getString("email"),
                rs.getDate("ngaySinh"),
                diem,
                rs.getDate("ngayCap")
            );
            list.add(tv);
        }
        rs.close();
        stmt.close();
        return list;
    }

    public void addThanhVien(ThanhVien tv) throws SQLException {
        String sql = "INSERT INTO ThanhVien (maTV, tenTV, email, ngaySinh, ngayCap) " +
                     "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, tv.getMaTV());
        pstmt.setString(2, tv.getTenTV());
        pstmt.setString(3, tv.getEmail());
        pstmt.setDate(4, new java.sql.Date(tv.getNgaySinh().getTime()));
        pstmt.setDate(5, new java.sql.Date(tv.getNgayCap().getTime()));
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void updateThanhVien(ThanhVien tv) throws SQLException {
        String sql = "UPDATE ThanhVien SET tenTV = ? WHERE maTV = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, tv.getTenTV());
        pstmt.setString(2, tv.getMaTV());
        pstmt.executeUpdate();
        pstmt.close();
    }

    public boolean isMaTVExistsInKhachHang(String maTV) throws SQLException {
        String sql = "SELECT COUNT(*) FROM KhachHang WHERE soDienThoai = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, maTV);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            rs.close();
            pstmt.close();
            return count > 0;
        }
        rs.close();
        pstmt.close();
        return false;
    }
    
    public static ThanhVien timThanhVienTheoMa(String ma) {
    	ThanhVien tv = null;
    	String sql = "SELECT * FROM ThanhVien WHERE maTV = ?";
        PreparedStatement pstmt;
		try {
			ConnectDB.getInstance().connect();
		    Connection conN = ConnectDB.getInstance().getConnection();
			pstmt = conN.prepareStatement(sql);
			 pstmt.setString(1, ma);
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            String matv = rs.getString("maTV");
	            String tentv = rs.getString("tenTV");
	            String email = rs.getString("email");
	            int diemTL = tinhDiemTL(matv);
	            Date ngaySinh = rs.getDate("ngaySinh");
				Date ngayCap = rs.getDate("ngayCap");
	            tv= new ThanhVien(matv, tentv, email, ngaySinh, diemTL, ngayCap);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return tv;
    }
    
    public static int tinhDiemTL(String matv) {
    	String sql = "SELECT SUM(diemTL) AS TongDiemTichLuy\r\n"
    			+ "FROM HoaDon\r\n"
    			+ "WHERE maTV = ? \r\n"
    			+ "  AND thoiGianThanhToan >= DATEADD(MONTH, -12, GETDATE());\r\n"
    			+ "";
		try {
			ConnectDB.getInstance().connect();
		    Connection conN = ConnectDB.getInstance().getConnection();
		    PreparedStatement pstmt = conN.prepareStatement(sql);
		    pstmt.setString(1, matv);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int diem = rs.getInt("TongDiemTichLuy");
				return diem;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
    }
    
}