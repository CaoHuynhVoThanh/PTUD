package dao;

import entities.ThanhVien;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QuanLyThanhVien_DAO {
    private Connection conn;

    public QuanLyThanhVien_DAO(Connection conn) {
        this.conn = conn;
    }

    public List<ThanhVien> getAllThanhVien() throws SQLException {
        List<ThanhVien> list = new ArrayList<>();
        String sql = "SELECT tv.maTV, tv.maKH, kh.tenKH, tv.email, tv.ngaySinh, tv.HangThe, tv.DiemTichLuy, tv.NgayCap " +
                     "FROM ThanhVien tv JOIN KhachHang kh ON tv.maKH = kh.maKH";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            ThanhVien tv = new ThanhVien(
                rs.getString("maTV"),
                rs.getString("maKH"),
                rs.getString("tenKH"),
                rs.getString("email"),
                rs.getDate("ngaySinh"),
                rs.getString("HangThe"),
                rs.getInt("DiemTichLuy"),
                rs.getDate("NgayCap")
            );
            list.add(tv);
        }
        return list;
    }

    public void addThanhVien(ThanhVien tv) throws SQLException {
        String sql = "INSERT INTO ThanhVien (maTV, maKH, email, ngaySinh, HangThe, DiemTichLuy, NgayCap) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, tv.getMaTV());
        pstmt.setString(2, tv.getMaKH());
        pstmt.setString(3, tv.getEmail());
        pstmt.setDate(4, new java.sql.Date(tv.getNgaySinh().getTime()));
        pstmt.setString(5, tv.getHangThe());
        pstmt.setInt(6, tv.getDiemTichLuy());
        pstmt.setDate(7, new java.sql.Date(tv.getNgayCap().getTime()));
        pstmt.executeUpdate();
    }

    public void updateThanhVien(ThanhVien tv) throws SQLException {
        String sql = "UPDATE ThanhVien SET email = ?, ngaySinh = ? WHERE maTV = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, tv.getEmail());
        pstmt.setDate(2, new java.sql.Date(tv.getNgaySinh().getTime()));
        pstmt.setString(3, tv.getMaTV());
        pstmt.executeUpdate();
    }

    public String generateMaTV() throws SQLException {
        String sql = "SELECT MAX(maTV) FROM ThanhVien";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            String maxMaTV = rs.getString(1);
            if (maxMaTV != null) {
                int num = Integer.parseInt(maxMaTV.substring(2)) + 1;
                return String.format("%02d%06d", LocalDate.now().getYear() % 100, num);
            }
        }
        return String.format("%02d%06d", LocalDate.now().getYear() % 100, 1);
    }
}