package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entities.DonGoiMon;
public class DonGoiMon_DAO {
	public static boolean themDonGoiMon(DonGoiMon don) {
		int n = 0;
		ConnectDB.getInstance();
		Connection conN = ConnectDB.getInstance().getConnection();
		PreparedStatement pstm = null;
		String sql = "INSERT INTO DonGoiMon ( maDGM, thoiGianGM, ghiChu) VALUES (?,?, ?)";
		try {
			pstm = conN.prepareStatement(sql);
			pstm.setString(1, don.getMaDGM());
			pstm.setTimestamp(2, Timestamp.valueOf(don.getThoiGianGM()));
			pstm.setString(3, don.getGhiChu());
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
	public static int demSoDonTrongNgay(LocalDate date) {
	    int count = 0;
	    String sql = "SELECT COUNT(*) FROM DonGoiMon WHERE CAST(thoiGianGM AS DATE) = ?";
	    ConnectDB.getInstance();
	    Connection conN = ConnectDB.getInstance().getConnection();
	    PreparedStatement stmt = null;
	    try {
	        stmt = conN.prepareStatement(sql);
	        stmt.setDate(1, java.sql.Date.valueOf(date)); // Gán giá trị cho tham số 1
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            count = rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return count;
	}
	public static ArrayList<DonGoiMon> getDonGoiMonTheoBan(String maBan) {
	    ArrayList<DonGoiMon> dsDon = new ArrayList<>();
	    ConnectDB.getInstance();
	    Connection conN = ConnectDB.getInstance().getConnection();
	    try {
	        String sql = "SELECT d.* FROM DonGoiMon d " +
	                     "JOIN ChiTietDonDatBan c ON d.maDGM = c.maDGM " +
	                     "WHERE c.maBan = ?";
	        PreparedStatement stmt = conN.prepareStatement(sql);
	        stmt.setString(1, maBan);
	        ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	            DonGoiMon don = new DonGoiMon(
	                rs.getString("maDGM"),
	                rs.getTimestamp("thoiGianGM").toLocalDateTime(),
	                rs.getString("ghiChu")
	            );
	            dsDon.add(don);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsDon;
	}
}
