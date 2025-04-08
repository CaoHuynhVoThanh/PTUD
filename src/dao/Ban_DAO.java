package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entities.Ban;

public class Ban_DAO {
	public static ArrayList<Ban> getAllBan(LocalDate thoiGianNhan) {
	    ConnectDB.getInstance().connect();
	    Connection conN = ConnectDB.getInstance().getConnection();
	    ArrayList<Ban> dsBan = new ArrayList<>();

	    String sql = "SELECT b.maBan, b.loaiBan, b.soBan, b.viTri, b.tinhTrang, " +
	                 "kv.tenKV AS tenKhuVuc, kv.phuThu AS phuThuKhuVuc, ddb.trangThai AS trangThaiDatBan " +
	                 "FROM Ban b " +
	                 "LEFT JOIN KhuVuc kv ON b.maKV = kv.maKV " +
	                 "LEFT JOIN ChiTietDonDatBan ctddb ON b.maBan = ctddb.maBan " +
	                 "LEFT JOIN DonDatBan ddb ON ctddb.maDDB = ddb.maDDB " +
	                 "WHERE CONVERT(DATE, ddb.thoiGianNhan) = ? OR ddb.thoiGianNhan IS NULL";
	    try {
	        PreparedStatement pst = conN.prepareStatement(sql);
	        pst.setDate(1, java.sql.Date.valueOf(thoiGianNhan)); // LocalDate -> java.sql.Date
	        System.out.println(thoiGianNhan);
	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            String ma = rs.getString("maBan");
	            int loai = rs.getInt("loaiBan");
	            String so = rs.getString("soBan");
	            int vt = rs.getInt("viTri");
	            int tt = rs.getInt("tinhTrang");
	            String tenkv = rs.getString("tenKhuVuc");
	            Double phuPhi = rs.getDouble("phuThuKhuVuc");
	            Object value = rs.getObject("trangThaiDatBan");
	            int k = 0;

	            if (tt == 0) {
	                k = 0;
	            } else {
	                if (value == null) {
	                    k = 1;
	                } else {
	                    boolean ck = (boolean) value;
	                    k = ck ? 2 : 3;
	                }
	            }

	            Ban ban = new Ban(ma, loai, vt, so, k, tenkv, phuPhi);
	            dsBan.add(ban);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsBan;
	}
    public static ArrayList<Integer> getDistinctViTri() {
        ArrayList<Integer> danhSachViTri = new ArrayList<>();
        String sql = "SELECT DISTINCT viTri FROM Ban ORDER BY viTri";
        ConnectDB.getInstance().connect();;
		Connection conN = ConnectDB.getInstance().getConnection();
        try{
             PreparedStatement stmt = conN.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int viTri = rs.getInt("viTri");
                danhSachViTri.add(viTri);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachViTri;
    }
	
}
