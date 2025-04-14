package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entities.Ban;
import entities.KhuVuc;

public class QuanLyBan_DAO {

    // Lấy danh sách tất cả bàn
    public ArrayList<Ban> getAllBan() {
        ArrayList<Ban> dsBan = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT b.maBan, b.loaiBan, b.viTri, b.soBan, b.tinhTrang, b.maKV, kv.tenKV " +
                        "FROM Ban b " +
                        "JOIN KhuVuc kv ON b.maKV = kv.maKV";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String maBan = rs.getString("maBan");
                int loaiBan = rs.getInt("loaiBan");
                int viTri = rs.getInt("viTri");
                String soBan = rs.getString("soBan");
                int tinhTrang = rs.getInt("tinhTrang");
                String tenKV = rs.getString("tenKV");
                Ban ban = new Ban(maBan, loaiBan, viTri, soBan, tinhTrang, tenKV, 0.0);
                dsBan.add(ban);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsBan;
    }

    // Lấy danh sách khu vực
    public ArrayList<KhuVuc> getAllKhuVuc() {
        ArrayList<KhuVuc> dsKhuVuc = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT maKV, tenKV FROM KhuVuc";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String maKV = rs.getString("maKV");
                String tenKV = rs.getString("tenKV");
                KhuVuc kv = new KhuVuc(maKV, tenKV, 0.0);
                dsKhuVuc.add(kv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsKhuVuc;
    }

    // Lấy danh sách vị trí riêng biệt
    public ArrayList<Integer> getDistinctViTri() {
        ArrayList<Integer> viTriList = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT DISTINCT viTri FROM Ban WHERE viTri BETWEEN 1 AND 5 ORDER BY viTri";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                viTriList.add(rs.getInt("viTri"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return viTriList;
    }

    // Lấy danh sách số bàn khả dụng (loại trừ số bàn đã tồn tại trong khu vực và vị trí)
    public ArrayList<String> getAvailableSoBan(String maKV, int viTri) {
        ArrayList<String> existingSoBan = new ArrayList<>();
        ArrayList<String> availableSoBan = new ArrayList<>();

        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getInstance().getConnection();
            
            // Lấy tất cả số bàn hiện có trong khu vực và vị trí
            String sql = "SELECT soBan FROM Ban WHERE maKV = ? AND viTri = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, maKV);
            pstmt.setInt(2, viTri);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                String soBan = rs.getString("soBan");
                existingSoBan.add(soBan);
            }
            
            // Tạo danh sách số bàn khả dụng (từ 01 đến 99, loại trừ các số đã tồn tại)
            for (int i = 1; i <= 99; i++) {
                String soBan = String.format("%02d", i);
                if (!existingSoBan.contains(soBan)) {
                    availableSoBan.add(soBan);
                }
            }
            
            return availableSoBan;
        } catch (SQLException e) {
            e.printStackTrace();
            return availableSoBan;
        }
    }

    // Thêm bàn
    public boolean themBan(String maBan, int loaiBan, int viTri, String soBan, String maKV, int tinhTrang) {
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getInstance().getConnection();
            
            // Kiểm tra xem maBan đã tồn tại chưa
            String sqlCheck = "SELECT COUNT(*) FROM Ban WHERE maBan = ?";
            PreparedStatement pstmtCheck = con.prepareStatement(sqlCheck);
            pstmtCheck.setString(1, maBan);
            ResultSet rsCheck = pstmtCheck.executeQuery();
            if (rsCheck.next() && rsCheck.getInt(1) > 0) {
                return false; // Mã bàn đã tồn tại
            }

            String sql = "INSERT INTO Ban (maBan, loaiBan, viTri, soBan, maKV, tinhTrang) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, maBan);
            pstmt.setInt(2, loaiBan);
            pstmt.setInt(3, viTri);
            pstmt.setString(4, soBan);
            pstmt.setString(5, maKV);
            pstmt.setInt(6, tinhTrang);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật bàn
    public boolean capNhatBan(String maBan, int loaiBan, int tinhTrang) {
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getInstance().getConnection();

            String sql = "UPDATE Ban SET loaiBan = ?, tinhTrang = ? WHERE maBan = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, loaiBan);
            pstmt.setInt(2, tinhTrang);
            pstmt.setString(3, maBan);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lọc bàn
    public ArrayList<Ban> locBan(String tenKhuVuc, Integer viTri, Integer tinhTrang) {
        ArrayList<Ban> dsBan = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getInstance().getConnection();
            StringBuilder sql = new StringBuilder(
                "SELECT b.maBan, b.loaiBan, b.viTri, b.soBan, b.tinhTrang, b.maKV, kv.tenKV " +
                "FROM Ban b " +
                "JOIN KhuVuc kv ON b.maKV = kv.maKV " +
                "WHERE 1=1"
            );
            ArrayList<Object> params = new ArrayList<>();

            if (tenKhuVuc != null && !tenKhuVuc.isEmpty()) {
                sql.append(" AND kv.tenKV = ?");
                params.add(tenKhuVuc);
            }
            if (viTri != null) {
                sql.append(" AND b.viTri = ?");
                params.add(viTri);
            }
            if (tinhTrang != null) {
                sql.append(" AND b.tinhTrang = ?");
                params.add(tinhTrang);
            }

            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String maBan = rs.getString("maBan");
                int loaiBan = rs.getInt("loaiBan");
                int viTri1 = rs.getInt("viTri");
                String soBan = rs.getString("soBan");
                int tinhTrang1 = rs.getInt("tinhTrang");
                String tenKV = rs.getString("tenKV");
                Ban ban = new Ban(maBan, loaiBan, viTri1, soBan, tinhTrang1, tenKV, 0.0);
                dsBan.add(ban);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsBan;
    }
}