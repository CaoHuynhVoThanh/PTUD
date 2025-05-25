package dao;

import entities.ThanhVien;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuanLyThanhVien_DAO {
    private Connection conn;

    public QuanLyThanhVien_DAO(Connection conn) {
        this.conn = conn;
    }

    public List<ThanhVien> getAllThanhVien() throws SQLException {
        List<ThanhVien> list = new ArrayList<>();
        String sql = "SELECT soDienThoai AS maTV, tenTV, email, ngaySinh, hangThe, diemTichLuy, ngayCap FROM ThanhVien";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            ThanhVien tv = new ThanhVien(
                rs.getString("maTV"),
                rs.getString("tenTV"),
                rs.getString("email"),
                rs.getDate("ngaySinh"),
                rs.getString("hangThe"),
                rs.getInt("diemTichLuy"),
                rs.getDate("ngayCap")
            );
            list.add(tv);
        }
        rs.close();
        stmt.close();
        return list;
    }

    public void addThanhVien(ThanhVien tv) throws SQLException {
        String sql = "INSERT INTO ThanhVien (soDienThoai, tenTV, email, ngaySinh, hangThe, diemTichLuy, ngayCap) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, tv.getMaTV());
        pstmt.setString(2, tv.getTenTV());
        pstmt.setString(3, tv.getEmail());
        pstmt.setDate(4, new java.sql.Date(tv.getNgaySinh().getTime()));
        pstmt.setString(5, tv.getHangThe());
        pstmt.setInt(6, tv.getDiemTichLuy());
        pstmt.setDate(7, new java.sql.Date(tv.getNgayCap().getTime()));
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void updateThanhVien(ThanhVien tv) throws SQLException {
        String sql = "UPDATE ThanhVien SET tenTV = ? WHERE soDienThoai = ?";
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
}