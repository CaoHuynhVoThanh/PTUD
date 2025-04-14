package dao;

import java.sql.Connection;
import java.sql.Date;
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
	             "kv.tenKV AS tenKhuVuc, kv.phuThu AS phuThuKhuVuc, " +
	             "c.phiCoc " +
	             "FROM Ban b " +
	             "LEFT JOIN KhuVuc kv ON b.maKV = kv.maKV " +
	             "LEFT JOIN Coc c ON b.loaiBan = c.maCoc";
	    
	    try {
	        PreparedStatement pst = conN.prepareStatement(sql);
	        ResultSet rs = pst.executeQuery();
	        System.out.println(pst.toString());
	        while (rs.next()) {
	            String ma = rs.getString("maBan");
	            int loai = rs.getInt("loaiBan");
	            String so = rs.getString("soBan");
	            int vt = rs.getInt("viTri");
	            int tt = rs.getInt("tinhTrang");
	            String tenkv = rs.getString("tenKhuVuc");
	            Double phuPhi = rs.getDouble("phuThuKhuVuc");
	            Double phiCoc = rs.getDouble("phiCoc");
	            
	            String sql2 = "SELECT ddb.trangThai " +
	                    "FROM DonDatBan ddb " +
	                    "JOIN ChiTietDonDatBan ctddb ON ddb.maDDB = ctddb.maDDB " +
	                    "WHERE ctddb.maBan = ? AND CONVERT(DATE, ddb.thoiGianDat) = ?";
	            PreparedStatement pstmt = conN.prepareStatement(sql2);
	            pstmt.setString(1, ma);
	            pstmt.setDate(2, Date.valueOf(thoiGianNhan));
		        ResultSet rs2 = pstmt.executeQuery();
		        if (rs2.next()) {
		        	if (rs2.getInt(1)==0) tt=2;
		        	else tt=3;
		        }
	            Ban ban = new Ban(ma, loai, vt, so, tt, tenkv, phuPhi, phiCoc);
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
    
    public static double layTienCoc(int soGhe) {
    	ConnectDB.getInstance().connect();
	    Connection conN = ConnectDB.getInstance().getConnection();
    	String sql = "SELECT c.phiCoc FROM Ban b JOIN Coc c ON b.maCoc = c.maCoc WHERE b.maBan = ?";
    	double tien=0.0;
        try {
             PreparedStatement pstmt = conN.prepareStatement(sql);

            pstmt.setInt(1, soGhe);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                tien = rs.getDouble("phiCoc");
                System.out.println("Tiền cọc cho bàn " + soGhe + ": " + tien + " VNĐ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tien;
    }
    
    public static boolean updateTrangThai(String maBanCu, String maBanMoi) {
    	ConnectDB.getInstance().connect();
	    Connection conN = ConnectDB.getInstance().getConnection();
	    String sql = "UPDATE ChiTietDonDatBan SET maBan = ? WHERE maBan = ?";

	   try{
		   PreparedStatement stmt = conN.prepareStatement(sql);		
		   stmt.setString(1, maBanMoi);
           stmt.setString(2, maBanCu);

           int rowsAffected = stmt.executeUpdate();
		
		   stmt.executeUpdate();
	   }catch (SQLException e) {
	       e.printStackTrace();
	       return false;
	   }
	return true;
    }
    

    public static ArrayList<Ban> getBanInfoByMaDDB(String maDDB) {
        String sql = """
                SELECT b.maBan, b.loaiBan, b.viTri, b.soBan, b.tinhTrang,
                       kv.tenKV, kv.phuThu,
                       c.phiCoc
                FROM ChiTietDonDatBan ct
                JOIN Ban b ON ct.maBan = b.maBan
                JOIN KhuVuc kv ON b.maKV = kv.maKV
                JOIN Coc c ON b.loaiBan = c.maCoc
                WHERE ct.maDDB = ?
            """;
        
        ArrayList<Ban> danhSachBan = new ArrayList<>();
        ConnectDB.getInstance().connect();
        Connection conN = ConnectDB.getInstance().getConnection();

        try (PreparedStatement stmt = conN.prepareStatement(sql)) {
            stmt.setString(1, maDDB);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String maBan = rs.getString("maBan");
                    int loaiBan = rs.getInt("loaiBan");
                    int viTri = rs.getInt("viTri");
                    String soBan = rs.getString("soBan");
                    int tinhTrang = rs.getInt("tinhTrang");
                    String tenKV = rs.getString("tenKV");
                    double phuPhi = rs.getDouble("phuThu");
                    double phiCoc = rs.getDouble("phiCoc");

                    Ban ban = new Ban(maBan, loaiBan, viTri, soBan, tinhTrang, tenKV, phuPhi, phiCoc);
                    danhSachBan.add(ban);
                }

                if (danhSachBan.isEmpty()) {
                    System.out.println("Không tìm thấy bàn nào với maDDB = " + maDDB);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachBan;
    }

}

