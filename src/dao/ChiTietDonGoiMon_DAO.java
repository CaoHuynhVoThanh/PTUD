package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entities.ChiTietDonGoiMon;

public class ChiTietDonGoiMon_DAO {
	public static boolean themChiTietDonGoiMon(ChiTietDonGoiMon chiTietDGM) {
		int n = 0;
		ConnectDB.getInstance();
		Connection conN = ConnectDB.getInstance().getConnection();
		PreparedStatement pstm = null;
		String sql = "INSERT INTO ChiTietDonGoiMon ( maMon, maDGM, soLuong, soLuongDaThanhToan) VALUES (?,?,?,?)";
		try {
			pstm = conN.prepareStatement(sql);
			pstm.setString(1, chiTietDGM.getMaMon());
			pstm.setString(2, chiTietDGM.getMaDGM());
			pstm.setInt(3, chiTietDGM.getSoLuong());
			pstm.setInt(4, chiTietDGM.getSoLuongDaThanhToan());
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
	public static ArrayList<ChiTietDonGoiMon> getChiTietDonGoiMonTheoMaDGM(String maDGM) {
	    ArrayList<ChiTietDonGoiMon> dsChiTiet = new ArrayList<>();
	    ConnectDB.getInstance();
	    Connection conN = ConnectDB.getInstance().getConnection();
	    try {
	        String sql = "SELECT * FROM ChiTietDonGoiMon WHERE maDGM = ?";
	        PreparedStatement stmt = conN.prepareStatement(sql);
	        stmt.setString(1, maDGM);
	        ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	            ChiTietDonGoiMon ct = new ChiTietDonGoiMon(
	                rs.getString("maMon"),
	                rs.getString("maDGM"),
	                rs.getInt("soLuong"),
	                rs.getInt("soLuongDaThanhToan")
	            );
	            dsChiTiet.add(ct);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsChiTiet;
	}
	public static boolean xoaChiTietTheoMaDGM(String maDGM) {
	    int n = 0;
	    ConnectDB.getInstance();
	    Connection conN = ConnectDB.getInstance().getConnection();
	    PreparedStatement pstm = null;
	    String sql = "DELETE FROM ChiTietDonGoiMon WHERE LTRIM(RTRIM(maDGM)) = ?";
	    try {
	        pstm = conN.prepareStatement(sql);
	        pstm.setString(1, maDGM);
	        n = pstm.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pstm != null)
	                pstm.close();
	        } catch (Exception e2) {
	            e2.printStackTrace();
	        }
	    }
	    return n > 0;
	}
	public static int layTongSoLuongMonBanRa() {
	    int tongSoLuong = 0;
	    ConnectDB.getInstance();
	    Connection conN = ConnectDB.getInstance().getConnection();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        String sql = "SELECT SUM(soLuong) AS tongSoLuong FROM ChiTietDonGoiMon";
	        stmt = conN.prepareStatement(sql);
	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            tongSoLuong = rs.getInt("tongSoLuong");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return tongSoLuong;
	}


}

